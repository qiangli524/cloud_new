package com.sitech.basd.component.dao.support;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sitech.basd.component.dao.user.UserDao;
import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.util.session.UserSession;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTreeLimit;
import com.sitech.utils.randomid.RandomUUID;

@Repository("xlsSupportDao")
public class XlsSupportDaoImpl extends BaseDao implements XlsSupportDao {
	private static final String STATUS = "1";
	private static final String ERROR = "error";
	private static final String SUCCESS = "success";
	@Autowired
	private UserDao userDao;

	// 基准应用ID，因为实例中关联了基准应用ID,存入实例表中时要用到这个ID
	private int appID = 0;
	// 应用部署树中基准应用ID,插入实例节点时要做这个ID 做为父节点
	private String appTreeID;
	// 业务系统ID，导入时给一个默认的业务系统
	private String sysID;

	/**
	 * 
	 * @Title: importFromXls
	 * @Description:从excel导入数据，包括主机，用户和生成部署实例
	 * @author duangh
	 * @date Jul 29, 2013 4:15:41 PM
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String importFromXls(Map<String, List> importMap) {
		String result = "";
		List<TbBusiHostObj> appHostList = importMap.get("appHostList");
		List<UserObj> appUserList = importMap.get("appUserList");
		List<TbBusiHostConfigObj> appConfigList = importMap.get("appConfigList");
		List<TbBusiHostObj> hostList = importMap.get("hostList");
		List<UserObj> userList = importMap.get("userList");
		List<TbBusiHostConfigObj> configList = importMap.get("configList");
		List<TbBusiDeployExampleObj> exampleList = importMap.get("exampleList");
		List<TbSysAppObj> appList = importMap.get("appList");
		SqlMapClient sqlMapClient = getSqlMap();
		try {
			// 开始事务 start transaction
			sqlMapClient.startTransaction();
			sqlMapClient.startBatch();
			/** 基准应用信息 */
			insertHostAndAppBatch(appHostList, appUserList, appConfigList, appList);
			/** 生成实例信息 */
			insertHostAndExampleBatch(hostList, userList, configList, exampleList);
			sqlMapClient.executeBatch();
			sqlMapClient.commitTransaction();
			result = SUCCESS;
		} catch (Exception e) {
			result = ERROR;
			LogHelper.error("importFromXls:" + e.getMessage() + getClass().getName());
		} finally {
			try {
				sqlMapClient.endTransaction();
			} catch (SQLException e) {
				// just endTransaction
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: insertHostAndAppBatch
	 * @Description:批量插入主机，用户，配置和基准应用信息，从基准应用中读取出来的，操作同insertHostAndConfigBatch,根据模板只有一条数据
	 * @author duangh
	 * @date Aug 2, 2013 5:49:45 PM
	 * @return
	 * @throws Exception
	 */
	public String insertHostAndAppBatch(List<TbBusiHostObj> appHostList, List<UserObj> appuserList,
			List<TbBusiHostConfigObj> appConfigList, List<TbSysAppObj> appList) throws Exception {
		String result = "";
		try {
			for (int i = 0; i < appHostList.size(); i++) {
				TbBusiHostObj hostObj = appHostList.get(i);
				hostObj.setSTATUS(STATUS);
				hostObj.setVLANIP(hostObj.getIP());
				List<TbBusiHostObj> exist = getSqlMap().queryForList("TbBusiHost.queryIfExist", hostObj);
				int hostID;
				// if don't exist then insert
				if (exist == null || exist.size() == 0) {
					Object hostO = getSqlMap().insert("TbBusiHost.insertTbBusiHostByObj", hostObj);
					hostID = Integer.parseInt(hostO.toString());

				} else {
					hostObj = exist.get(0);
					hostID = hostObj.getID();
				}
				UserObj userObj = appuserList.get(i);
				String userId;
				List<UserObj> existUser = getSqlMap().queryForList("UserManage.queryIfExist", userObj);
				if (existUser == null || existUser.size() == 0) {
					userId = RandomUUID.getUuid();
					userObj.setId(userId);
					/*
					 * 加密用户密码
					 */
					userObj = userDao.encryptPwd(userObj);
					getSqlMap().insert("UserManage.insertByObj", userObj);
				} else {
					userId = existUser.get(0).getId();
				}
				TbBusiHostConfigObj configObj = appConfigList.get(i);
				String hostConfigID = RandomUUID.getUuid();
				configObj.setHOSTID(hostID);
				configObj.setUSER_MANAGE_ID(userId);
				configObj.setHOSTCONFIGID(hostConfigID);
				getSqlMap().insert("TbBusiHostConfig.insertByObj", configObj);

				TbSysAppObj appObj = appList.get(i);
				/**
				 * 
				 * @author chenjlc 20131122 优化，导入时根据EXCEL内容判断应用所属业务系统
				 */
				TbBusiSysTree queryObj = new TbBusiSysTree();
				queryObj.setType(1);
				queryObj.setName(appObj.getBUSISYS());
				/**
				 * end
				 */
				List<TbBusiSysTree> queryList = getSqlMap().queryForList("TbBusiSysTree.queryForTree", queryObj);
				queryObj = queryList.get(0);
				sysID = queryObj.getBusiId();

				appObj.setHOST_CONFIG_ID(hostConfigID);
				appObj.setSTRATEGYTYPE(2);// 2为基准部署
				appObj.setSTRATEGY(hostID);
				appObj.setSYS_ID(sysID);

				Object o = getSqlMap().insert("TbSysApp.insertByObj", appObj);
				appID = Integer.parseInt(o.toString());
				// table Tb_Busi_Sys_Tree
				TbBusiSysTree treeObj = new TbBusiSysTree();
				appTreeID = RandomUUID.getUuid();
				treeObj.setId(appTreeID);
				treeObj.setName(appObj.getHostIP() + "@" + appObj.getAPPNAME());
				treeObj.setType(2);

				treeObj.setBusiId(o.toString());
				treeObj.setHostIP(appObj.getHostIP());
				treeObj.setParentId(queryObj.getId());
				getSqlMap().insert("TbBusiSysTree.insertTbBusiSysTree", treeObj);

				// table tb_busi_sys_tree_limit 权限信息
				TbBusiSysTreeLimit limit = new TbBusiSysTreeLimit();
				limit.setId(RandomUUID.getUuid());
				limit.setTreeNodeId(appTreeID);
				HttpSession session = UserSession.getHttpSession();
				String account = session.getAttribute("account").toString();
				limit.setUsername(account);
				getSqlMap().insert("TbBusiSysTreeLimit.insertTbBusiSysTreeLimit", limit);

			}
			result = SUCCESS;
		} catch (Exception e) {
			LogHelper.error("insertAppHostAndConfigBatch:" + e.getMessage() + getClass().getName());
			result = ERROR;
			throw e;
		}
		return result;
	}

	/**
	 * 
	 * @Title: insertHostAndExampleBatch
	 * @Description:批量插入主机，主机用户，主机配置和生成部署实例
	 * @author duangh
	 * @date Aug 2, 2013 5:49:45 PM
	 * @return
	 * @throws Exception
	 */
	public String insertHostAndExampleBatch(List<TbBusiHostObj> hostList, List<UserObj> userList,
			List<TbBusiHostConfigObj> configList, List<TbBusiDeployExampleObj> exampleList) throws Exception {
		String result = "";
		try {
			for (int i = 0; i < hostList.size(); i++) {
				TbBusiHostObj hostObj = hostList.get(i);
				hostObj.setSTATUS(STATUS);
				hostObj.setVLANIP(hostObj.getIP());
				int hostID;
				List<TbBusiHostObj> exist = getSqlMap().queryForList("TbBusiHost.queryTbBusiHostForListByObj", hostObj);
				// if don't exist then insert
				if (exist == null || exist.size() == 0) {
					Object hostO = getSqlMap().insert("TbBusiHost.insertTbBusiHostByObj", hostObj);
					hostID = Integer.parseInt(hostO.toString());
				} else {
					hostObj = exist.get(0);
					hostID = hostObj.getID();
				}

				UserObj userObj = userList.get(i);
				String userId;
				List<UserObj> existUser = getSqlMap().queryForList("UserManage.queryForList", userObj);
				if (existUser == null || existUser.size() == 0) {
					userId = RandomUUID.getUuid();
					userObj.setId(userId);
					/*
					 * 加密用户密码
					 */
					userObj = userDao.encryptPwd(userObj);
					getSqlMap().insert("UserManage.insertByObj", userObj);
				} else {
					userId = existUser.get(0).getId();
				}

				TbBusiHostConfigObj configObj = configList.get(i);
				String hostConfigID = RandomUUID.getUuid();
				configObj.setHOSTID(hostID);
				configObj.setUSER_MANAGE_ID(userId);
				configObj.setHOSTCONFIGID(hostConfigID);
				getSqlMap().insert("TbBusiHostConfig.insertByObj", configObj);
				TbBusiDeployExampleObj exampleObj = exampleList.get(i);
				exampleObj.setHOST_CONFIG_ID(hostConfigID);
				exampleObj.setHOSTID(hostID);
				exampleObj.setDEPLOYEXAMPLE_TYPE("0");
				exampleObj.setDEPLOY_FLAG("0");// 未部署状态
				exampleObj.setSTART_STOP_FLAG("1");// 停止状态
				exampleObj.setDEPLOY_PERCENT("请等待");
				exampleObj.setSYS_ID(sysID);
				exampleObj.setIsbackup(0);
				exampleObj.setIsrestart(0);
				exampleObj.setTRUST_FLAG("0");
				exampleObj.setAPPID(appID);
				exampleObj.setVLANIP(exampleObj.getIP());
				Object o = getSqlMap().insert("TbBusiDeployExample.insertByObj", exampleObj);
				// table Tb_Busi_Sys_Tree
				TbBusiSysTree treeObj = new TbBusiSysTree();
				String treeId = RandomUUID.getUuid();
				treeObj.setId(treeId);
				treeObj.setName(exampleObj.getExampleName());
				treeObj.setType(3);
				// 查询所在基准应用的树节点
				treeObj.setParentId(appTreeID);// 得到基准应用节点ID
				treeObj.setBusiId(o.toString());
				treeObj.setHostIP(exampleObj.getIP());
				getSqlMap().insert("TbBusiSysTree.insertTbBusiSysTree", treeObj);

				// table tb_busi_sys_tree_limit 权限信息
				TbBusiSysTreeLimit limit = new TbBusiSysTreeLimit();
				limit.setId(RandomUUID.getUuid());
				limit.setTreeNodeId(treeId);
				HttpSession session = UserSession.getHttpSession();
				String account = session.getAttribute("account").toString();
				limit.setUsername(account);
				getSqlMap().insert("TbBusiSysTreeLimit.insertTbBusiSysTreeLimit", limit);

			}
			result = SUCCESS;
		} catch (Exception e) {
			LogHelper.error("insertHostAndExampleBatch:" + e.getMessage() + getClass().getName());
			result = ERROR;
			throw e;
		}
		return result;
	}
}
