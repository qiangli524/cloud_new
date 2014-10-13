package com.sitech.basd.sxcloud.workflow.service.resworkflow;

import java.util.List;

import com.sitech.basd.sxcloud.workflow.dao.resworkflow.ResourceWorkflowDao;
import com.sitech.basd.sxcloud.workflow.domain.resourceorder.ResourceOrderInfoObj;
import com.sitech.basd.sxcloud.workflow.domain.resourceorder.VMHost;

public class ResourceWorkflowServiceImpl implements ResourceWorkflowService {
	/**
	 * 
	 * @Title: 查询新建工单列表
	 * @Copyright: Copyright (c) 2011-7-7
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<ResourceOrderInfoObj> queryOrderList(ResourceOrderInfoObj obj)

	{
		return resourceWorkflowDao.queryOrderList(obj);
	}

	/**
	 * 
	 * @Title: 查询工单详细信息
	 * @Copyright: Copyright (c) 2011-7-7
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public ResourceOrderInfoObj queryOrderInfo(ResourceOrderInfoObj obj)

	{
		return resourceWorkflowDao.queryOrderInfo(obj);
	}

	/**
	 * 
	 * @Title: 新增新建工单列表
	 * @Copyright: Copyright (c) 2011-7-7
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int addOrderInfo(ResourceOrderInfoObj obj) {
		return resourceWorkflowDao.addOrderInfo(obj);
	}

	/**
	 * 
	 * @Title: 删除新建工单信息
	 * @Copyright: Copyright (c) 2011-7-9
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int deleteOrderInfo(String requestNo) {
		return resourceWorkflowDao.deleteOrderInfo(requestNo);
	}

	/**
	 * 
	 * @Title: 修改草稿箱中的工单
	 * @Copyright: Copyright (c) 2011-7-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int alterOrderInfo(ResourceOrderInfoObj obj) {
		return resourceWorkflowDao.alterOrderInfo(obj);
	}

	/**
	 * @Title:更新工单信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author sunwu
	 * @version 1.0
	 */
	public int updateByObj(ResourceOrderInfoObj obj) {
		return resourceWorkflowDao.updateByObj(obj);
	}

	/**
	 * @Title:查询虚拟机ID
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author sunwu
	 * @version 1.0
	 */
	public List<ResourceOrderInfoObj> queryVirtualId() {
		return resourceWorkflowDao.queryVirtualId();
	}

	private ResourceWorkflowDao resourceWorkflowDao;

	public ResourceWorkflowDao getResourceWorkflowDao() {
		return resourceWorkflowDao;
	}

	public void setResourceWorkflowDao(ResourceWorkflowDao resourceWorkflowDao) {
		this.resourceWorkflowDao = resourceWorkflowDao;
	}

	@Override
	public List<ResourceOrderInfoObj> queryWorkOrderList(
			ResourceOrderInfoObj obj) {
		return resourceWorkflowDao.queryWorkOrderList(obj);
	}

	@Override
	public VMHost queryOrderListByVMHost(VMHost obj) {
		return resourceWorkflowDao.queryOrderListByVMHost(obj);
	}
}
