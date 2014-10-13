package com.sitech.basd.component.tree.dao.order;

import java.util.List;

import com.sitech.basd.component.tree.domain.order.OrderObj;
import com.sitech.basd.component.tree.domain.order.OrderRelationTaskObj;

public interface OrderDao {

	/**
	 * 查询所有的订单
	 * 
	 * @param obj
	 * @return
	 */
	public List<OrderObj> queryOrderInfoList(OrderObj obj);

	/**
	 * 查询所有的订单和关联的任务id
	 * 
	 * @param obj
	 * @return
	 */
	public List<OrderObj> queryRelationForTaskList(OrderObj obj);

	/**
	 * 插入数据
	 * 
	 * @param obj
	 * @return
	 */
	public int insertByObj(OrderObj obj);

	public int insertRelationTaskByObj(OrderRelationTaskObj obj);

	/**
	 * 查询订单和任务的关系
	 * 
	 * @param obj
	 * @return
	 */
	public List<OrderRelationTaskObj> queryOrderRelationTaskList(OrderRelationTaskObj obj);

	/**
	 * 查询订单的状态
	 * 
	 * @param obj
	 * @return
	 */
	public OrderObj queryOrderStatus(OrderObj obj);

	/**
	 * 删除数据
	 * 
	 * @param obj
	 * @return
	 */
	public int delByObj(OrderObj obj);

	/**
	 * 删除数据
	 * 
	 * @param obj
	 * @return
	 */
	public int delRelationTaskByObj(OrderRelationTaskObj obj);

	/**
	 * 更新数据
	 * 
	 * @param obj
	 * @return
	 */
	public int updateOrderByObj(OrderObj obj);

	public int countOrderNum(OrderObj orderObj);

	public List<OrderObj> queryAll();

	public List<OrderObj> queryFileListFromSoftware(OrderObj orderObj);

}
