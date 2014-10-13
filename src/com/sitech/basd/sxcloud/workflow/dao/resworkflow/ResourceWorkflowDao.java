package com.sitech.basd.sxcloud.workflow.dao.resworkflow;

import java.util.List;

import com.sitech.basd.sxcloud.workflow.domain.resourceorder.ResourceOrderInfoObj;
import com.sitech.basd.sxcloud.workflow.domain.resourceorder.VMHost;

public interface ResourceWorkflowDao {
	/**
	 * 
	 * @Title: 查询新建工单列表
	 * @Copyright: Copyright (c) 2011-7-7
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<ResourceOrderInfoObj> queryOrderList(ResourceOrderInfoObj obj);
	
	/**
	 * 查询工单表中记录的所有的信息
	  
	* queryOrderListByVMHost(这里用一句话描述这个方法的作用)    
	  
	* @param   name   
	  
	* @param  @return    设定文件   
	  
	* @return String    DOM对象   
	  
	* @Exception 异常对象   
	  
	* @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public VMHost queryOrderListByVMHost(VMHost obj);
	
	/**
	 * 查询所有的新建工单
	  
	* queryWorkOrderList(这里用一句话描述这个方法的作用)    
	  
	* @param   name   
	  
	* @param  @return    设定文件   
	  
	* @return String    DOM对象   
	  
	* @Exception 异常对象   
	  
	* @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<ResourceOrderInfoObj> queryWorkOrderList(ResourceOrderInfoObj obj);
	
	/**
	 * 
	 * @Title: 查询新建工单列表
	 * @Copyright: Copyright (c) 2011-7-7
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public ResourceOrderInfoObj queryOrderInfo(ResourceOrderInfoObj obj);

	/**
	 * 
	 * @Title: 添加新建工单信息
	 * @Copyright: Copyright (c) 2011-7-7
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int addOrderInfo(ResourceOrderInfoObj obj);

	/**
	 * 
	 * @Title: 删除新建工单信息
	 * @Copyright: Copyright (c) 2011-7-9
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int deleteOrderInfo(String requestNo);

	/**
	 * 
	 * @Title: 修改草稿箱中的工单
	 * @Copyright: Copyright (c) 2011-7-13
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int alterOrderInfo(ResourceOrderInfoObj obj);

	/**
	 * @Title:更新工单信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author sunwu
	 * @version 1.0
	 */
	public int updateByObj(ResourceOrderInfoObj obj);

	/**
	 * @Title:查询虚拟机ID
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author sunwu
	 * @version 1.0
	 */
	public List<ResourceOrderInfoObj> queryVirtualId();
}
