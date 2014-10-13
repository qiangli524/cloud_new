package com.sitech.basd.sxcloud.workflow.service.appworkflow;

import java.util.HashMap;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.workflow.dao.appworkflow.AppWorkFlowDao;
import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.AppNeedObj;
import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.OrderInfoObj;


public class AppWorkFlowServiceImpl extends BaseService implements AppWorkFlowService {

	/**
 	 * @Title: 判断当前用户是否具备新建工单权限
 	 * @prama: applicationType:申请单类型 如上线、下线流程
 	 * @prama: userId:         当前用户唯一标识
 	 * @Copyright: Copyright (c) 2012-02-28
 	 * @Company: si-tech
 	 * @author duangh
 	 * @version 1.0
	 * @return  true:具备当前工单新建权限 false:没有权限发起工单权限
 	 */
	public boolean canCreateNewApplication(int applicationType,String userId){
		List lst = appWorkFlowDao.getStartProcessDisposeMan(applicationType, userId) ;
		return (lst==null||lst.size()==0) ? false : true ; 
	}
	/**
	 * 
	* @Title: 查询应用部署申请
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public List<AppNeedObj> queryAppNeedList(AppNeedObj obj){
		return appWorkFlowDao.queryAppNeedList(obj);
	}
	
	/**
	 * 
	* @Title: 新增应用部署申请
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int addAppNeedInfo(AppNeedObj obj){
		return appWorkFlowDao.addAppNeedInfo(obj);
	}
	
	/**
	 * 
	* @Title: 删除应用部署申请
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int deleteAppNeedInfo(String requestNo){
		return appWorkFlowDao.deleteAppNeedInfo(requestNo);
	}
	
	/**
	 * 
	* @Title: 删除应用部署申请的信息
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int deleteAppNeedOrder(String requestNo){
		return appWorkFlowDao.deleteAppNeedOrder(requestNo);
	}

	/**
	 * 
	* @Title: 修改草稿箱中的应用部署申请
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int alterAppNeedInfo(AppNeedObj obj){
		return appWorkFlowDao.alterAppNeedInfo(obj);
	}
	
	/**
     * @Title:更新工单信息
     * @Copyright: Copyright (c) 2012-02-29
     * @Company: si-tech
     * @author duangh
     * @version 1.0
    */
	public int updateByObj(AppNeedObj obj){
		return appWorkFlowDao.updateByObj(obj);
	}
	/**
     * @Title:新增的应用部署信息
     * @Copyright: Copyright (c) 2012-03-01
     * @Company: si-tech
     * @author duangh
     * @version 1.0
    */
	public int insertAppInfo(OrderInfoObj obj){
		return appWorkFlowDao.insertAppInfo(obj);
	}
	/**
     * @Title:修改申请的应用部署信息
     * @Copyright: Copyright (c) 2012-03-01
     * @Company: si-tech
     * @author duangh
     * @version 1.0
    */
	public int updateOrderInfo(OrderInfoObj obj){
		return appWorkFlowDao.updateOrderInfo(obj);
	}
	/**
	 * 
	* @Title: 查询应用部署信息
	* @Copyright: Copyright (c) 2012-03-05
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public List<OrderInfoObj> queryAppInfoList(OrderInfoObj obj){
		return appWorkFlowDao.queryAppInfoList(obj);
	}
	/**
	 * 
	* @Title: 查询并返回一个具体的应用部署信息
	* @Copyright: Copyright (c) 2012-03-07
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public OrderInfoObj queryAppInfo(OrderInfoObj obj){
		return appWorkFlowDao.queryAppInfo(obj);
	}
	/**
	 * 
	* @Title: 查询并返回一个具体申请的应用部署信息
	* @Copyright: Copyright (c) 2012-03-07
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public AppNeedObj queryAppOrderInfo(AppNeedObj obj){
		return appWorkFlowDao.queryAppOrderInfo(obj);
	}
	/**
	 * 
	* @Title: 同意申请时将IP 信息插入表中
	* @Copyright: Copyright (c) 2012-03-15
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int updateAppIpInfo(AppNeedObj obj){
		return appWorkFlowDao.updateAppIpInfo(obj);
	}
	/**
	 * 
	 * @Title: 同意申请时将IP 信息插入表中
	 * @Copyright: Copyright (c) 2012-03-16
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateDefend(AppNeedObj obj){
		return appWorkFlowDao.updateDefend(obj);
	}
	/**
	 * @Title:将查询的模板中的KV1..KVn分开放在MAP中
	 * @Copyright: Copyright (c) 2012-03-21
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public HashMap getKvMap(OrderInfoObj obj) {
		HashMap kvmap = new HashMap();
		OrderInfoObj tempObj = this.queryAppInfo(obj);
		if (tempObj.getKV1() != null) {
			kvmap.put(tempObj.getKV1().split(":")[0], tempObj.getKV1().split(
					":")[1]);
		}
		if (tempObj.getKV2() != null) {
			kvmap.put(tempObj.getKV2().split(":")[0], tempObj.getKV2().split(
					":")[1]);
		}
		if (tempObj.getKV3() != null) {
			kvmap.put(tempObj.getKV3().split(":")[0], tempObj.getKV3().split(
					":")[1]);
		}
		if (tempObj.getKV4() != null) {
			kvmap.put(tempObj.getKV4().split(":")[0], tempObj.getKV4().split(
					":")[1]);
		}
		if (tempObj.getKV5() != null) {
			kvmap.put(tempObj.getKV5().split(":")[0], tempObj.getKV5().split(
					":")[1]);
		}
		if (tempObj.getKV6() != null) {
			kvmap.put(tempObj.getKV6().split(":")[0], tempObj.getKV6().split(
					":")[1]);
		}
		if (tempObj.getKV7() != null) {
			kvmap.put(tempObj.getKV7().split(":")[0], tempObj.getKV7().split(
					":")[1]);
		}
		if (tempObj.getKV8() != null) {
			kvmap.put(tempObj.getKV8().split(":")[0], tempObj.getKV8().split(
					":")[1]);
		}
		if (tempObj.getKV9() != null) {
			kvmap.put(tempObj.getKV9().split(":")[0], tempObj.getKV9().split(
					":")[1]);
		}
		if (tempObj.getKV10() != null) {
			kvmap.put(tempObj.getKV10().split(":")[0], tempObj.getKV10().split(
					":")[1]);
		}
		if (tempObj.getKV11() != null) {
			kvmap.put(tempObj.getKV11().split(":")[0], tempObj.getKV11().split(
					":")[1]);
		}

		return kvmap;
	}
	AppWorkFlowDao appWorkFlowDao;

	public void setAppWorkFlowDao(AppWorkFlowDao appWorkFlowDao) {
		this.appWorkFlowDao = appWorkFlowDao;
	}
}
