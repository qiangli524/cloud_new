package com.sitech.basd.yicloud.service.cluster;

import java.util.List;
import java.util.Map;

import com.sitech.basd.common.ResponseCode;
import com.sitech.basd.yicloud.dao.cluster.ClusterDao;
import com.sitech.basd.yicloud.dao.entitytree.EntityTreeDao;
import com.sitech.basd.yicloud.domain.cluster.ClusterObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.ws.Operation;
import com.sitech.ws.web.NoticeUtil;

public class ClusterServiceImpl implements ClusterService {
	/**
	 * @Title:保存集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int insertByObj(ClusterObj obj) {
		int ret = -1;
		String dcName = obj.getDcName();
		String clName = obj.getName();
		String parentId = obj.getParentId();
		String entityid = obj.getC_uuid();// 数据中心entityId，即code唯一标识
		String url = "/vmware/cluster/create/[dcName:" + entityid + "].[clName:" + clName + "]/";
		String addResult = InvokeUtil.invoke(url);
		Map ps = ParamParser.makeup(addResult);
		String result = (String) ps.get(ResponseCode.RESPONSE_CODE);
		if (result.equals(ResponseCode.SUCCESS)) {// 通过接口添加集群成功
			String code = (String) ps.get(ResponseCode.CODE);// code , 唯一标识
			obj.setC_uuid(code);
			/** 通知 */
			try {
				NoticeUtil.getInstance().updateCMDBDC(entityid, Operation.OPER_REL_MODI);// cmdb
			} catch (Exception e) {

			}
			try {
				NoticeUtil.getInstance().addCluster(code);
			} catch (Exception ex) {

			}
			// 未开启HA和DRS
			obj.setDrsstate("0");
			obj.setHastate("0");
			ret = clusterDao.insertByObj(obj);
			if (ret != -1) {// 集群创建成功后插入节点数据
				EntityTreeObj treeObj = new EntityTreeObj();
				treeObj.setParentId(Integer.parseInt(parentId));
				treeObj.setName(clName);
				treeObj.setEntityId(code);
				treeObj.setType(TypeConstant.VMWARE_CLUSTER);
				entityTreeDao.insertTreeNode(treeObj);
			}
		}
		return ret;

	}

	/**
	 * @Title:查询单个集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public ClusterObj queryByObj(ClusterObj obj) {
		return clusterDao.queryByObj(obj);
	}

	/**
	 * @Title:更改集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int updateByObj(ClusterObj obj) {
		// int id = obj.getId();
		String code = obj.getC_uuid();
		// 查询原来集群的状态
		ClusterObj tempObj = new ClusterObj();
		// tempObj.setId(id);
		tempObj.setC_uuid(code);
		tempObj = clusterDao.queryByObj(tempObj);

		String dcName = obj.getDcName();// 数据中心名称
		String clname = obj.getName();// 集群名称
		String hastate = obj.getHastate();
		String drsstate = obj.getDrsstate();
		boolean haflag = false;
		boolean drsflag = false;
		if (hastate.equals("0")) {
			haflag = false;
		} else if (hastate.equals("1")) {
			haflag = true;
		}
		if (drsstate.equals("0")) {
			drsflag = false;
		} else if (drsstate.equals("1")) {
			drsflag = true;
		}
		String responsecode = "-1";
		// 如果和数据库中的值不一致再调用接口更改HA和DRS的状态
		if (!tempObj.getHastate().equals(hastate)) {
			String haUrl = "/vmware/cluster/switch/[dcName:" + dcName + "].[clName:" + code
					+ "].[clHA:HA].[HAEnable:" + String.valueOf(haflag) + "]/";
			String haResult = InvokeUtil.invoke(haUrl);
			Map ps = ParamParser.makeup(haResult);
			responsecode = (String) ps.get(ResponseCode.RESPONSE_CODE);
		}
		if (!tempObj.getDrsstate().equals(drsstate)) {
			String drsUrl = "/vmware/cluster/switch/[dcName:" + dcName + "].[clName:" + code
					+ "].[clDRS:DRS].[DRSEnable:" + String.valueOf(drsflag) + "]/";
			String drsResult = InvokeUtil.invoke(drsUrl);
			Map ps = ParamParser.makeup(drsResult);
			responsecode = (String) ps.get(ResponseCode.RESPONSE_CODE);
		}
		// 调用接口更改状态成功后更新数据库
		if (responsecode.equals("1")) {
			/** 通知 */
			try {
				NoticeUtil.getInstance().updateCMDBCluster(code, Operation.OPER_MODI);// cmdb
			} catch (Exception e) {

			}
			try {
				NoticeUtil.getInstance().updateCluster(code);
			} catch (Exception ex) {

			}
			clusterDao.updateByObj(obj);
		}
		return Integer.parseInt(responsecode);
	}

	/**
	 * @Title:删除集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public String deleteByObj(ClusterObj obj) {
		String dcName = obj.getDcName();
		String name = obj.getName();
		String code = obj.getC_uuid();
		String delUrl = "/vmware/cluster/delete/[dcName:" + dcName + "].[clName:" + code + "]/";
		String delResult = InvokeUtil.invoke(delUrl);
		Map ps = ParamParser.makeup(delResult);
		String responsecode = "-1";
		String resMsg = null;
		responsecode = (String) ps.get("responseCode");
		resMsg = (String) ps.get("resMsg");
		if (responsecode.equals("1")) {
			/** 通知 */
			try {
				NoticeUtil.getInstance().delCMDBCluster(code, Operation.OPER_DEL);// cmdb
			} catch (Exception e) {

			}
			try {
				NoticeUtil.getInstance().delCluster(code);
			} catch (Exception ex) {

			}
			clusterDao.deleteByObj(obj);
		}
		String json = "{'result':'" + responsecode + "','resMsg':'" + resMsg + "'}";
		return json;
	}

	/**
	 * 
	 * @Title: queryForCollection
	 * @Description: 查询集群的uuid等信息
	 * @param
	 * @return ClusterObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:10:06 AM
	 */
	public ClusterObj queryForCollection(ClusterObj obj) {
		return clusterDao.queryForCollection(obj);
	}

	/**
	 * 
	 * @Title: queryForConn
	 * @Description: 插入集群信息（xen）
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:50:41 AM
	 */
	public int insertForConnection(ClusterObj obj) {
		return clusterDao.insertForConnection(obj);
	}

	/**
	 * 
	 * @Title: getIdSequence
	 * @Description: 获取集群ID序列
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 22, 2012 3:21:14 PM
	 */
	public int getIdSequence() {
		return clusterDao.getIdSequence();
	}

	/**
	 * 
	 * @Title: queryListByObj
	 * @Description: 查询集群信息列表
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 25, 2012 10:09:37 PM
	 */
	public List queryListByObj(ClusterObj obj) {
		return clusterDao.queryListByObj(obj);
	}

	/**
	 * 
	 * @Title: updateByObjUuid
	 * @Description:通过Uuid更新数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 26, 2012 11:35:08 AM
	 */
	public int updateByObjUuid(ClusterObj obj) {
		return clusterDao.updateByObjUuid(obj);
	}

	/**
	 * 
	 * @Title: queryForGroupId
	 * @Description: 生成一个DRS组编号
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 4:39:16 PM
	 */
	public int queryForGroupId() {
		return clusterDao.queryForGroupId();
	}

	/**
	 * 
	 * @Title: addDRSGroup
	 * @Description: 增加一个DRS组
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 4:33:29 PM
	 */
	public int addDRSGroup(Map map) {
		return clusterDao.addDRSGroup(map);
	}

	/**
	 * 
	 * @Title: addDRSGroup
	 * @Description: 增加一个DRS组
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 4:33:29 PM
	 */
	public int addDRSGroupDetail(Map map) {
		return clusterDao.addDRSGroupDetail(map);
	}

	/**
	 * 
	 * @Title: queryGroupList
	 * @Description: 查询DRS列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 21, 2013 10:50:04 AM
	 */
	public List queryGroupList(ClusterObj obj) {
		return clusterDao.queryGroupList(obj);
	}

	/**
	 * 
	 * @Title: deleteGroup
	 * @Description: 删除DRS组
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 25, 2013 4:04:28 PM
	 */
	public int deleteGroup(Map map) {
		return clusterDao.deleteGroup(map);
	}

	/**
	 * 
	 * @Title: deleteGroupDetail
	 * @Description: 删除DRS成员
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 25, 2013 4:04:42 PM
	 */
	public int deleteGroupDetail(Map map) {
		return clusterDao.deleteGroupDetail(map);
	}

	/**
	 * 
	 * @Title: deleteAllGroup
	 * @Description: 删除所有的组（用于同步数据）
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 2, 2013 2:39:27 PM
	 */
	public int deleteAllGroup() {
		return clusterDao.deleteAllGroup();
	}

	/**
	 * 
	 * @Title: listGroupMember
	 * @Description: 查询组成员
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 2, 2013 3:49:00 PM
	 */
	public List listGroupMember(Map map) {
		return clusterDao.listGroupMember(map);
	}

	private ClusterDao clusterDao;
	private EntityTreeDao entityTreeDao;

	public void setEntityTreeDao(EntityTreeDao entityTreeDao) {
		this.entityTreeDao = entityTreeDao;
	}

	public void setClusterDao(ClusterDao clusterDao) {
		this.clusterDao = clusterDao;
	}
}
