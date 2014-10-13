package com.sitech.basd.component.tree.dao.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.component.tree.domain.order.OrderObj;
import com.sitech.basd.component.tree.domain.order.OrderRelationTaskObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("orderDao")
public class OrderDaoImpl extends BaseDao implements OrderDao {
	/**
	 * 
	 * @Title: OrderInfoList
	 * @Description: 查询配置文件列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 10:04:03 AM
	 */
	@SuppressWarnings("unchecked")
	public List<OrderObj> queryOrderInfoList(OrderObj obj) {
		List<OrderObj> list = new ArrayList<OrderObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("Order.countOrderNum", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("Order.queryForList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Order.queryForList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public int insertByObj(OrderObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().insert("Order.insertByObj", obj);
			if (obj1 == null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper
					.error("Order.insertByObj:" + sqlException.getMessage() + getClass().getName());
		}
		return ent;
	}

	@Override
	public int delByObj(OrderObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().delete("Order.deleteByObj", obj);
			if (obj1 != null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper
					.error("Order.deleteByObj:" + sqlException.getMessage() + getClass().getName());
		}
		return ent;
	}

	public int updateOrderByObj(OrderObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().update("Order.updateByObj", obj);
			if (obj1 != null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper
					.error("Order.updateByObj:" + sqlException.getMessage() + getClass().getName());
		}
		return ent;
	}

	@Override
	public int insertRelationTaskByObj(OrderRelationTaskObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().insert("Order.insertRelationTaskByObj", obj);
			if (obj1 == null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Order.insertRelationTaskByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ent;
	}

	@Override
	public int delRelationTaskByObj(OrderRelationTaskObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().delete("Order.deleteRelationTaskByObj", obj);
			if (obj1 != null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Order.deleteRelationTaskByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ent;
	}

	@Override
	public List<OrderObj> queryRelationForTaskList(OrderObj obj) {
		List<OrderObj> list = new ArrayList<OrderObj>();
		try {
			list = getSqlMap().queryForList("Order.queryRelationForTaskList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Order.queryRelationForTaskList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List<OrderRelationTaskObj> queryOrderRelationTaskList(OrderRelationTaskObj obj) {
		List<OrderRelationTaskObj> list = new ArrayList<OrderRelationTaskObj>();
		try {
			list = getSqlMap().queryForList("Order.queryOrderRelationTaskList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Order.queryOrderRelationTaskList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public OrderObj queryOrderStatus(OrderObj obj) {
		OrderObj list = new OrderObj();
		try {
			list = (OrderObj) getSqlMap().queryForObject("Order.queryForList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Order.queryForList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public int countOrderNum(OrderObj orderObj) {
		int ret = -1;
		try {
			Object obj = getSqlMap().queryForObject("Order.countOrderNum", orderObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("Order.countOrderNum: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderObj> queryAll() {
		List<OrderObj> list = null;
		try {
			list = getSqlMap().queryForList("Order.queryAll");
		} catch (Exception e) {
			LogHelper.error("Order.queryAll: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
	
	/**
	 * 通过版本id查询文件清单
	 * @Title: queryFileListFromSoftware
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<OrderObj>
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-9-10 下午1:21:24
	 */
	public List<OrderObj> queryFileListFromSoftware(OrderObj orderObj) {
		List<OrderObj> list = null;
		try {
			list = getSqlMap().queryForList("Order.queryFileListFromSoftware",orderObj);
		} catch (Exception e) {
			LogHelper.error("Order.queryFileListFromSoftware: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
	
}
