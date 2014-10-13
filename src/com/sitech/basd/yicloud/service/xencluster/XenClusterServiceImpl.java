package com.sitech.basd.yicloud.service.xencluster;

import java.util.List;
import java.util.Map;

import com.sitech.basd.common.ResponseCode;
import com.sitech.basd.yicloud.dao.xencluster.XenClusterDao;
import com.sitech.basd.yicloud.dao.xentree.XenEntityTreeDao;
import com.sitech.basd.yicloud.domain.xentree.XenClusterObj;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.ws.Operation;
import com.sitech.ws.web.NoticeUtil;

public class XenClusterServiceImpl implements XenClusterService {
	/**
	 * @Title:保存集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int insertByObj(XenClusterObj obj) {
		return xenClusterDao.insertByObj(obj);
	}

	/**
	 * @Title:查询单个集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public XenClusterObj queryByObj(XenClusterObj obj) {
		return xenClusterDao.queryByObj(obj);
	}

	/**
	 * @Title:更改集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int updateByObj(XenClusterObj obj) {
		// int id = obj.getId();
		String code = obj.getC_uuid();
		// 查询原来集群的状态
		XenClusterObj tempObj = new XenClusterObj();
		// tempObj.setId(id);
		tempObj.setC_uuid(code);
		tempObj = xenClusterDao.queryByObj(tempObj);

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
			String haUrl = "/vmware/cluster/edit/[dcName:" + dcName + "].[clName:" + code
					+ "].[clHA:HA].[HAEnable:" + String.valueOf(haflag) + "]/";
			String haResult = InvokeUtil.invoke(haUrl);
			Map ps = ParamParser.makeup(haResult);
			responsecode = (String) ps.get(ResponseCode.RESPONSE_CODE);
		}
		if (!tempObj.getDrsstate().equals(drsstate)) {
			String drsUrl = "/vmware/cluster/edit/[dcName:" + dcName + "].[clName:" + code
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
			xenClusterDao.updateByObj(obj);
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
	public String deleteByObj(XenClusterObj obj) {
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
			xenClusterDao.deleteByObj(obj);
		}
		String json = "{'result':'" + responsecode + "','resMsg':'" + resMsg + "'}";
		return json;
	}

	/**
	 * 
	 * @Title: queryForCollection
	 * @Description: 查询集群的uuid等信息
	 * @param
	 * @return XenClusterObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:10:06 AM
	 */
	public XenClusterObj queryForCollection(XenClusterObj obj) {
		return xenClusterDao.queryForCollection(obj);
	}

	/**
	 * 
	 * @Title: queryForConn
	 * @Description: 插入集群信息（xen）
	 * @param
	 * @return XenEntityTreeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:50:41 AM
	 */
	public int insertForConnection(XenClusterObj obj) {
		return xenClusterDao.insertForConnection(obj);
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
		return xenClusterDao.getIdSequence();
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
	public List queryListByObj(XenClusterObj obj) {
		return xenClusterDao.queryListByObj(obj);
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
	public int updateByObjUuid(XenClusterObj obj) {
		return xenClusterDao.updateByObjUuid(obj);
	}

	private XenClusterDao xenClusterDao;
	private XenEntityTreeDao xenEntityTreeDao;

	public void setXenEntityTreeDao(XenEntityTreeDao xenEntityTreeDao) {
		this.xenEntityTreeDao = xenEntityTreeDao;
	}

	public void setXenClusterDao(XenClusterDao xenClusterDao) {
		this.xenClusterDao = xenClusterDao;
	}

}
