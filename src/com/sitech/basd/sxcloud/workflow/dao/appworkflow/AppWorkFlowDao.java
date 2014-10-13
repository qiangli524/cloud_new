package com.sitech.basd.sxcloud.workflow.dao.appworkflow;

import java.util.List;

import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.AppNeedObj;
import com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.OrderInfoObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.DisposeManObj;


public interface AppWorkFlowDao {
	/**
     * @Title:获取流程发起人
     * @Copyright: Copyright (c) 2012-02-28
     * @Company: si-tech
     * @author duangh
     * @version 1.0
    */
	public List<DisposeManObj> getStartProcessDisposeMan(int process_id,String userId);
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
	* @Title: 删除应用部署申请中的信息
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
     * @Title:新增申请的应用部署信息
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
	* @Title: 同意申请时将IP 信息插入表中
	* @Copyright: Copyright (c) 2012-03-15
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int updateAppIpInfo(AppNeedObj obj);
	/**
	 * 
	* @Title: 同意申请时添加防篡改信息
	* @Copyright: Copyright (c) 2012-03-15
	* @Company: si-tech
	* @author duangh
	* @version 1.0
	 */
	public int updateDefend(AppNeedObj obj);
	
}
