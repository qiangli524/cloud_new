package com.sitech.basd.sxcloud.workflow.service.appworkflow;

import java.util.HashMap;
import java.util.List;

import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.AppNeedObj;
import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.OrderInfoObj;


public interface AppWorkFlowService{

	/**
 	 * @Title: 判断当前用户是否具备新建工单权限
 	 * @prama: applicationType:申请单类型 如资源申请流程，应用部署流程
 	 * @prama: userId:         当前用户唯一标识
 	 * @Copyright: Copyright (c) 2012-02-28
 	 * @Company: si-tech
 	 * @author duangh
 	 * @version 1.0
	 * @return  true:具备当前工单新建权限 false:没有权限发起工单权限
 	 */
	public boolean canCreateNewApplication(int applicationType,String userId) ;
	/**
	 * 
	* @Title: 查询应用部署申请
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public List<AppNeedObj> queryAppNeedList(AppNeedObj obj);
	
	/**
	 * 
	* @Title: 新增应用部署申请
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int addAppNeedInfo(AppNeedObj obj);
	
	/**
	 * 
	* @Title: 删除应用部署申请
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int deleteAppNeedInfo(String requestNo);
	/**
	 * 
	* @Title: 删除应用部署申请的信息
	* @Copyright: Copyright (c) 2012-03-07
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int deleteAppNeedOrder(String requestNo);

	/**
	 * 
	* @Title: 修改草稿箱中的应用部署申请
	* @Copyright: Copyright (c) 2012-02-29
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int alterAppNeedInfo(AppNeedObj obj);
	
	/**
     * @Title:更新工单信息
     * @Copyright: Copyright (c) 2012-02-29
     * @Company: si-tech
     * @author duangh
     * @version 1.0
    */
	public int updateByObj(AppNeedObj obj);
	
	/**
     * @Title:新增的应用部署信息
     * @Copyright: Copyright (c) 2012-03-01
     * @Company: si-tech
     * @author duangh
     * @version 1.0
    */
	public int insertAppInfo(OrderInfoObj obj);
	/**
     * @Title:修改申请的应用部署信息
     * @Copyright: Copyright (c) 2012-03-01
     * @Company: si-tech
     * @author duangh
     * @version 1.0
    */
	public int updateOrderInfo(OrderInfoObj obj);
	/**
	 * 
	* @Title: 查询应用部署信息
	* @Copyright: Copyright (c) 2012-03-05
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public List<OrderInfoObj> queryAppInfoList(OrderInfoObj obj);
	/**
	 * 
	* @Title: 查询并返回一个具体的应用部署信息
	* @Copyright: Copyright (c) 2012-03-07
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public OrderInfoObj queryAppInfo(OrderInfoObj obj);
	/**
	 * 
	* @Title: 查询并返回一个具体申请的应用部署信息
	* @Copyright: Copyright (c) 2012-03-07
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public AppNeedObj queryAppOrderInfo(AppNeedObj obj);
	/**
	 * 
	* @Title: 同意申请时将IP ，防篡改等信息插入表中
	* @Copyright: Copyright (c) 2012-03-15
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int updateAppIpInfo(AppNeedObj obj);
	/**
	 * 
	* @Title: 同意申请时添加防篡改信息
	* @Copyright: Copyright (c) 2012-03-16
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int updateDefend(AppNeedObj obj);
	/**
	 * @Title:将查询的模板中的KV1..KVn分开放在MAP中
	 * @Copyright: Copyright (c) 2012-03-21
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public HashMap getKvMap(OrderInfoObj obj);
	
	
}
