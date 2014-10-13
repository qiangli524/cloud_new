package com.sitech.basd.component.tree.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.component.tree.dao.order.OrderDao;
import com.sitech.basd.component.tree.domain.order.OrderObj;
import com.sitech.basd.component.tree.domain.order.OrderRelationTaskObj;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	public List<OrderObj> queryOrderInfoList(OrderObj obj) {
		return orderDao.queryOrderInfoList(obj);
	}

	@Override
	public int insertByObj(OrderObj obj) {
		return orderDao.insertByObj(obj);
	}

	@Override
	public int delByObj(OrderObj obj) {
		return orderDao.delByObj(obj);
	}

	@Override
	public int updateOrderByObj(OrderObj obj) {
		return orderDao.updateOrderByObj(obj);
	}

	@Override
	public int insertRelationTaskByObj(OrderRelationTaskObj obj) {
		return orderDao.insertRelationTaskByObj(obj);
	}

	@Override
	public int delRelationTaskByObj(OrderRelationTaskObj obj) {
		return orderDao.delRelationTaskByObj(obj);
	}

	@Override
	public List<OrderObj> queryRelationForTaskList(OrderObj obj) {
		return orderDao.queryRelationForTaskList(obj);
	}

	@Override
	public List<OrderRelationTaskObj> queryOrderRelationTaskList(OrderRelationTaskObj obj) {
		return orderDao.queryOrderRelationTaskList(obj);
	}

	@Override
	public OrderObj queryOrderStatus(OrderObj obj) {
		return orderDao.queryOrderStatus(obj);
	}

	@Override
	public int countOrderNum(OrderObj orderObj) {
		return orderDao.countOrderNum(orderObj);
	}

	@Override
	public List<OrderObj> queryAll() {
		// TODO Auto-generated method stub
		return orderDao.queryAll();
	}
	
	@Override
	public List<OrderObj> queryFileListFromSoftware(OrderObj orderObj) {
		// TODO Auto-generated method stub
		return orderDao.queryFileListFromSoftware(orderObj);
	}
	
	
}
