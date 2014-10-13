package com.sitech.basd.sxcloud.cloud.dao.workorder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloudEntityUser;
import com.sitech.basd.sxcloud.cloud.domain.workorder.WorkOrderObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 接收bocmc同步资源的工单，进行页面的展示
 * 
 * @author lipengpeng
 * 
 */
@Repository("workOrderDao")
public class WorkOrderDaoImpl extends BaseDao implements WorkOrderDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkOrderObj> queryByObj(WorkOrderObj workOrderObj) {
		List<WorkOrderObj> list = new ArrayList<WorkOrderObj>();
		try {
			if (workOrderObj.getPagination() != null) {
				workOrderObj.setFIRSTROWNUM(workOrderObj.getPagination()
						.getFirstRownum());
				workOrderObj.setPAGESIZE(workOrderObj.getPagination()
						.getPageSize());
				workOrderObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"WorkOrder.countByObj", workOrderObj))
								.intValue());
			}
			list = getSqlMap().queryForList("WorkOrder.queryByObj",
					workOrderObj);
		} catch (Exception e) {
			LogHelper.error("WorkOrder.queryWorkOrderList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkOrderObj> queryResourceList(WorkOrderObj workOrderObj) {
		List<WorkOrderObj> list = new ArrayList<WorkOrderObj>();
		try {
			list = getSqlMap().queryForList("WorkOrder.queryResourceList",
					workOrderObj);
		} catch (Exception e) {
			LogHelper.error("WorkOrder.queryResourceList: " + e.getMessage()
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
	public int updateByObj(WorkOrderObj obj) {
		int ret = 1;
		try {
			Object o = getSqlMap().update("WorkOrder.updateByObj", obj);
			if (o != null) {
				ret = (Integer) o;
			}
		} catch (Exception e) {
			LogHelper.error("WorkOrder.updateByObj: " + e.getMessage()
					+ e.getClass().getName());
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
			Object o = getSqlMap()
					.update("WorkOrder.updateWorkOrderTable", obj);
			if (o != null) {
				ret = (Integer) o;
			}
		} catch (Exception e) {
			LogHelper.error("WorkOrder.updateWorkOrderTable: " + e.getMessage()
					+ e.getClass().getName());
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
	public int deleteResourceByObj(WorkOrderObj workOrderObj) {
		int ret = -1;
		try {
			ret = getSqlMap().delete("WorkOrder.deleteResourceByObj",
					workOrderObj);
		} catch (Exception e) {
			LogHelper.error("WorkOrder.deleteResourceByObj: " + e.getMessage()
					+ e.getClass().getName());
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
			Object o = getSqlMap()
					.insert("WorkOrder.insertWorkOrderTable", obj);
			if (o != null) {
				ret = (Integer) o;
			}
		} catch (Exception e) {
			LogHelper.error("WorkOrder.insertWorkOrderTable: " + e.getMessage()
					+ e.getClass().getName());
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
	public int insertResource(WorkOrderObj workOrderObj) {
		int ret = 1;
		try {
			Object obj = getSqlMap().insert("WorkOrder.insertResource",
					workOrderObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("WorkOrder.insertResource: " + e.getMessage()
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
			Object obj = getSqlMap().delete("WorkOrder.deleteWorkOrderByObj",
					workOrderObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("WorkOrder.deleteWorkOrderByObj: " + e.getMessage()
					+ e.getClass().getName());
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

	/**
	 * @Title: queruForHostInfo
	 * @Description: 查询指定主机的相关信息
	 * @param
	 * @return WorkOrderObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-19 下午3:05:46
	 */
	@Override
	public WorkOrderObj queruForHostInfo(WorkOrderObj wobj) {
		WorkOrderObj wrobj = new WorkOrderObj();
		try {
			Object obj = getSqlMap().queryForObject(
					"WorkOrder.queruForHostInfo", wobj);
			if (obj != null) {
				wrobj = (WorkOrderObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("WorkOrder.queruForHostInfo: " + e.getMessage()
					+ e.getClass().getName());
		}
		return wrobj;
	}

	/**
	 * @Title: queryUsedByWorkorder
	 * @Description: 查询工单中已使用
	 * @param
	 * @return List<WorkOrderObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-23 上午11:53:58
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkOrderObj> queryUsedByWorkorder(WorkOrderObj wobj) {
		List<WorkOrderObj> list = new ArrayList<WorkOrderObj>();
		try {
			list = getSqlMap().queryForList("WorkOrder.queryUsedByWorkorder",
					wobj);
		} catch (Exception e) {
			LogHelper.error("WorkOrder.queryUsedByWorkorder: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbCloud2HostInfoObj> queryHostObjByIOS(
			String ios) {
		List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
		try {
			list = getSqlMap().queryForList("WorkOrder.queryHostObjByIOS",
					ios);
		} catch (Exception e) {
			LogHelper.error("WorkOrder.queryHostObjByIOS: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@Override
	public void insertTbCloudEntityUser(TbCloudEntityUser entityObj) {
		try {
			 getSqlMap().insert("WorkOrder.insertIntoEntityUser",
					entityObj);
		} catch (Exception e) {
			LogHelper.error("WorkOrder.insertIntoEntityUser: " + e.getMessage()
					+ e.getClass().getName());
		}
	}

}
