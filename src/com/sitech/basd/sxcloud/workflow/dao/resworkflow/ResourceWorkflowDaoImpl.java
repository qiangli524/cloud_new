package com.sitech.basd.sxcloud.workflow.dao.resworkflow;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.workflow.domain.resourceorder.ResourceOrderInfoObj;
import com.sitech.basd.sxcloud.workflow.domain.resourceorder.VMHost;

public class ResourceWorkflowDaoImpl extends BaseDao implements
		ResourceWorkflowDao {
	/**
	 * 
	 * @Title: 新建工单
	 * @Copyright: Copyright (c) 2011-7-7
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int addOrderInfo(ResourceOrderInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("ResourceWorkflow.addOrderInfo", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			LogHelper.debug("OrderService.addOrderInfo:" + e.getMessage()
					+ getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * 
	 * @Title: 查询草稿箱工单列表
	 * @Copyright: Copyright (c) 2011-7-7
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<ResourceOrderInfoObj> queryOrderList(ResourceOrderInfoObj obj) {
		List<ResourceOrderInfoObj> list = new ArrayList<ResourceOrderInfoObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"ResourceWorkflow.queryOrderForCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("ResourceWorkflow.queryOrderList",
					obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("ResourceWorkflow.queryOrderList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: 查询工单详细信息
	 * @Copyright: Copyright (c) 2011-7-7
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public ResourceOrderInfoObj queryOrderInfo(ResourceOrderInfoObj obj) {

		List lst = null;
		ResourceOrderInfoObj tempObj = null;
		lst = queryOrderList(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (ResourceOrderInfoObj) lst.get(0);
		}
		return tempObj;

	}

	/**
	 * 
	 * @Title: 删除工单信息
	 * @Copyright: Copyright (c) 2011-7-9
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int deleteOrderInfo(String requestNo) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("ResourceWorkflow.deleteOrderInfo",
					requestNo);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			LogHelper.debug("ResourceWorkflow.deleteOrderInfo:"
					+ e.getMessage() + getClass().getName());
			ret = -1;
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().update("ResourceWorkflow.alterOrderInfo",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("ResourceWorkflow.alterOrderInfo:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新工单信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author sunwu
	 * @version 1.0
	 */
	public int updateByObj(ResourceOrderInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("ResourceWorkflow.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("ResourceWorkflow.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询虚拟机ID
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author sunwu
	 * @version 1.0
	 */
	public List<ResourceOrderInfoObj> queryVirtualId() {
		List<ResourceOrderInfoObj> list = null;
		try {
			list = getSqlMap().queryForList("ResourceWorkflow.queryVirtualId");
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("ResourceWorkflow.queryVirtualId:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return list;

	}

	@Override
	public List<ResourceOrderInfoObj> queryWorkOrderList(
			ResourceOrderInfoObj obj) {
		List<ResourceOrderInfoObj> list = new ArrayList<ResourceOrderInfoObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"ResourceWorkflow.queryOrderForCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("ResourceWorkflow.queryWorkOrderList",
					obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("ResourceWorkflow.queryWorkOrderList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return list;
	}

	@Override
	public VMHost queryOrderListByVMHost(VMHost obj) {
		VMHost vmHost=new VMHost();
		try {
			vmHost = (VMHost) getSqlMap().queryForObject("ResourceWorkflow.queryWorkOrderByvmHost",obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("ResourceWorkflow.queryWorkOrderByvmHost:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return vmHost;
	}
}
