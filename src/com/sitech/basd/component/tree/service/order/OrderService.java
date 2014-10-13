package com.sitech.basd.component.tree.service.order;

import java.util.List;

import com.sitech.basd.component.tree.domain.order.OrderObj;
import com.sitech.basd.component.tree.domain.order.OrderRelationTaskObj;

public interface OrderService {

	/**
	 * 查询
	 * 
	 * @param obj
	 * @return
	 */
	public List<OrderObj> queryOrderInfoList(OrderObj obj);

	/**
	 * 查询所有的订单和其任务的关系
	 * 
	 * @param obj
	 * @return
	 */
	public List<OrderObj> queryRelationForTaskList(OrderObj obj);

	/**
	 * 查询订单的状态
	 * 
	 * @param obj
	 * @return
	 */
	public OrderObj queryOrderStatus(OrderObj obj);

	/**
	 * 插入
	 * 
	 * @param obj
	 * @return
	 */
	public int insertByObj(OrderObj obj);

	/**
	 * 插入
	 * 
	 * @param obj
	 * @return
	 */
	public int insertRelationTaskByObj(OrderRelationTaskObj obj);

	/**
	 * 删除
	 * 
	 * @param obj
	 * @return
	 */
	public int delByObj(OrderObj obj);

	/**
	 * 删除
	 * 
	 * @param obj
	 * @return
	 */
	public int delRelationTaskByObj(OrderRelationTaskObj obj);

	/**
	 * 更新
	 * 
	 * @param obj
	 * @return
	 */
	public int updateOrderByObj(OrderObj obj);

	/**
	 * 查询订单和任务的关系
	 * 
	 * @param obj
	 * @return
	 */
	public List<OrderRelationTaskObj> queryOrderRelationTaskList(OrderRelationTaskObj obj);

	/**
	 * 查询订单数量
	 * 
	 * @param orderObj
	 * @return
	 */
	public int countOrderNum(OrderObj orderObj);

	public List<OrderObj> queryAll();
	
	/**
	 * 通过版本id查询文件清单列表
	 * @Title: queryFileListFromSoftware
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<OrderObj>
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-9-10 上午11:12:04
	 */
	public List<OrderObj> queryFileListFromSoftware(OrderObj orderObj);
}
