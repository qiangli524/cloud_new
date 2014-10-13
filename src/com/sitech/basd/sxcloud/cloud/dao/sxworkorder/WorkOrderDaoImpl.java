package com.sitech.basd.sxcloud.cloud.dao.sxworkorder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.sxcloud.cloud.domain.sxworkorder.OrderTaskObj;
import com.sitech.basd.sxcloud.cloud.domain.sxworkorder.WorkOrderObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 接收bocmc同步资源的工单，进行页面的展示
 * 
 * @author lipengpeng
 * 
 */
@Repository("sxworkOrderDao")
public class WorkOrderDaoImpl extends BaseDao implements WorkOrderDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkOrderObj> queryWorkOrderList(WorkOrderObj workOrderObj) {
		List<WorkOrderObj> list = new ArrayList<WorkOrderObj>();
		try {
			if (workOrderObj.getPagination() != null) {
				workOrderObj.setFIRSTROWNUM(workOrderObj.getPagination()
						.getFirstRownum());
				workOrderObj.setPAGESIZE(workOrderObj.getPagination()
						.getPageSize());
				workOrderObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"sxWorkOrder.countByObj", workOrderObj))
								.intValue());
			}
			list = getSqlMap().queryForList("sxWorkOrder.queryWorkOrderList",
					workOrderObj);
		} catch (Exception e) {
			LogHelper.error("sxWorkOrder.queryWorkOrderList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderTaskObj> queryResourceList(OrderTaskObj orderTaskObj) {
		List<OrderTaskObj> list = new ArrayList<OrderTaskObj>();
		try {
			list = getSqlMap().queryForList("sxWorkOrder.queryResourceList",
					orderTaskObj);
		} catch (Exception e) {
			LogHelper.error("sxWorkOrder.queryResourceList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForWholeListByObj
	 * @Description: 查询工单和资源的合体
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-23 上午11:54:21
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkOrderObj> queryForWholeListByObj(WorkOrderObj workOrderObj) {
		List<WorkOrderObj> list = new ArrayList<WorkOrderObj>();
		try {
			list = getSqlMap().queryForList("WorkOrder.queryForWholeListByObj",
					workOrderObj);
		} catch (Exception e) {
			LogHelper.error("WorkOrder.queryForWholeListByObj: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: updateByObj
	 * @Description: 更新一条记录
	 * @param
	 * @return
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-23 下午2:55:46
	 */
	@Override
	public int updateResourceByObj(OrderTaskObj obj) {
		int ret = 1;
		try {
			Object o = getSqlMap().update("sxWorkOrder.updateResourceByObj",
					obj);
			if (o != null) {
				ret = (Integer) o;
			}
		} catch (Exception e) {
			LogHelper.error("sxWorkOrder.updateResourceByObj: "
					+ e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: updateWorkOrderTable
	 * @Description: 更新工单表
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-23 下午3:04:30
	 */
	@Override
	public int updateWorkOrderTable(WorkOrderObj obj) {
		int ret = 1;
		try {
			Object o = getSqlMap().update("sxWorkOrder.updateWorkOrderTable",
					obj);
			if (o != null) {
				ret = (Integer) o;
			}
		} catch (Exception e) {
			LogHelper.error("sxWorkOrder.updateWorkOrderTable: "
					+ e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: queryProjectList
	 * @Description: 查询项目集合
	 * @param
	 * @return List<DepartProjectObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-29 上午10:40:10
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DepartProjectObj> queryProjectList() {
		List<DepartProjectObj> list = new ArrayList<DepartProjectObj>();
		try {
			list = getSqlMap().queryForList("WorkOrder.queryProjectList");
		} catch (Exception e) {
			LogHelper.error("WorkOrder.queryProjectList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: deleteResourceByObj
	 * @Description: 删除工单下的任务
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-7 下午12:19:59
	 */
	@Override
	public int deleteResourceByObj(OrderTaskObj orderTaskObj) {
		int ret = -1;
		try {
			ret = getSqlMap().delete("sxWorkOrder.deleteResourceByObj",
					orderTaskObj);
		} catch (Exception e) {
			LogHelper.error("sxWorkOrder.deleteResourceByObj: "
					+ e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryByProject
	 * @Description: 查询项目资源使用量
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-7 下午8:59:52
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkOrderObj> queryUsedByProject(String project_ID) {
		List<WorkOrderObj> list = new ArrayList<WorkOrderObj>();
		try {
			list = getSqlMap().queryForList("WorkOrder.queryUsedByProject",
					project_ID);
		} catch (Exception e) {
			LogHelper.error("WorkOrder.queryUsedByProject: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: insertWorkOrderTable
	 * @Description: 插入工单表
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-9 上午8:44:30
	 */
	@Override
	public int insertWorkOrderTable(WorkOrderObj obj) {
		int ret = 1;
		try {
			Object o = getSqlMap().insert("sxWorkOrder.insertWorkOrderTable",
					obj);
			if (o != null) {
				ret = (Integer) o;
			}
		} catch (Exception e) {
			LogHelper.error("sxWorkOrder.insertWorkOrderTable: "
					+ e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: insertResource
	 * @Description: 插入资源表
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-9 上午9:07:47
	 */
	@Override
	public int insertResource(OrderTaskObj orderTaskObj) {
		int ret = 1;
		try {
			Object obj = getSqlMap().insert("sxWorkOrder.insertResource",
					orderTaskObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("sxWorkOrder.insertResource: " + e.getMessage()
					+ e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: queryByProject
	 * @Description: 查询已经创建成功的资源占用量
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-11 下午7:24:21
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkOrderObj> queryByProject(String projectid) {
		List<WorkOrderObj> list = new ArrayList<WorkOrderObj>();
		try {
			list = getSqlMap().queryForList("WorkOrder.queryByProject",
					projectid);
		} catch (Exception e) {
			LogHelper.error("WorkOrder.queryByProject: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: deleteWorkOrderByObj
	 * @Description: 删除工单记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-12 下午8:04:25
	 */
	@Override
	public int deleteWorkOrderByObj(WorkOrderObj workOrderObj) {
		int ret = 1;
		try {
			Object obj = getSqlMap().delete("sxWorkOrder.deleteWorkOrderByObj",
					workOrderObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("sxWorkOrder.deleteWorkOrderByObj: "
					+ e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: queryUnDealedResource
	 * @Description: 查询处理状态不是成功的资源
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-10-16 下午2:28:11
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkOrderObj> queryUnDealedResource(WorkOrderObj workOrderObj) {
		List<WorkOrderObj> list = new ArrayList<WorkOrderObj>();
		try {
			list = getSqlMap().queryForList("WorkOrder.queryUnDealedResource",
					workOrderObj);
		} catch (Exception e) {
			LogHelper.error("WorkOrder.queryUnDealedResource: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

}
