package com.sitech.basd.workflow.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.cloud.domain.workorder.WorkOrderObj;
import com.sitech.basd.workflow.domain.WorkOrderResourceObj;

@Repository("workOrderResourceDao")
public class WorkOrderResourceDaoImpl extends
		CloudIbatisBaseDaoImpl<WorkOrderResourceObj, Integer> implements
		WorkOrderResourceDao {


	/**
	 * @Title: queryForListByObj
	 * @Description: 查询一批记录
	 * @param
	 * @return List<WorkOrderResourceObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-21 上午10:10:26
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkOrderResourceObj> queryForListByObj(
			WorkOrderResourceObj workOrderResourceObj) throws Exception {
		List<WorkOrderResourceObj> list = new ArrayList<WorkOrderResourceObj>();
		try {
			list = sqlMapClient
					.queryForList("WorkOrderResource.queryForListByObj",
							workOrderResourceObj);
		} catch (Exception e) {
			logger.error(
					"WorkOrderResource.queryForListByObj: " + e.getMessage()
							+ e.getClass().getName(), e);
			throw new Exception(e);
		}
		return list;
	}

	/**
	 * @Title: updateByObj
	 * @Description: 根据对象更改
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-21 上午10:10:52
	 */
	@Override
	public int insertByObj(WorkOrderResourceObj workOrderResourceObj)
			throws Exception {
		int ret = -1;
		try {
			sqlMapClient.insert("WorkOrderResource.insertByObj",
					workOrderResourceObj);
			ret = 0;
		} catch (Exception e) {
			logger.error("WorkOrderResource.insertByObj: " + e.getMessage()
					+ e.getClass().getName(), e);
			throw new Exception(e);
		}
		return ret;
	}

	/**
	 * @Title: updateByObj
	 * @Description: 根据对象更改
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-21 上午10:10:52
	 */
	@Override
	public int updateByObj(WorkOrderResourceObj workOrderResourceObj)
			throws Exception {
		int ret = -1;
		try {
			Object obj = sqlMapClient.update("WorkOrderResource.updateByObj",
					workOrderResourceObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			logger.error("WorkOrderResource.updateByObj: " + e.getMessage()
					+ e.getClass().getName(), e);
			throw new Exception(e);
		}
		return ret;
	}

	/**
	 * @Title: insertWorkOrderTable
	 * @Description: 插入工单表
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-21 下午12:28:40
	 */
	@Override
	public int insertWorkOrderTable(WorkOrderObj workOrdereObj)
			throws Exception {
		int ret = -1;
		try {
			sqlMapClient.insert("WorkOrder.insertWorkOrderTable",workOrdereObj);
			ret = 0;
		} catch (Exception e) {
			logger.error(
					"WorkOrder.insertWorkOrderTable: " + e.getMessage()
							+ e.getClass().getName(), e);
			throw new Exception(e);
		}
		return ret;
	}

	/**
	 * @Title: updateWorkOrderTable
	 * @Description: 更新工单表
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-21 下午12:30:12
	 */
	@Override
	public int updateWorkOrderTable(WorkOrderResourceObj workOrderResourceObj)
			throws Exception {
		int ret = -1;
		try {
			Object obj = sqlMapClient.update(
					"WorkOrderResource.updateWorkOrderTable",
					workOrderResourceObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			logger.error(
					"WorkOrderResource.updateWorkOrderTable: " + e.getMessage()
							+ e.getClass().getName(), e);
			throw new Exception(e);
		}
		return ret;
	}
	/**
	 * @Title: queryByProject
	 * @Description: 通过项目id查对象
	 * @param
	 * @return List<VmHostInfoObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-8-20 上午16:48:30
	 */
	@SuppressWarnings("unchecked")
	public List<WorkOrderResourceObj> queryByProject(String projectId)
			throws Exception {
		List<WorkOrderResourceObj> list = new ArrayList<WorkOrderResourceObj>();
		try {
			list = sqlMapClient.queryForList(
					"WorkOrderResource.queryByProject", projectId);
		} catch (SQLException e) {
			logger.error("WorkOrderResource.queryByProject: " + e.getMessage()
					+ e.getClass().getName(), e);
			throw new Exception(e);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkOrderResourceObj> queryUsedByProject(String projectId) {
		List<WorkOrderResourceObj> list = new ArrayList<WorkOrderResourceObj>();
		try {
			list = sqlMapClient.queryForList(
					"WorkOrderResource.queryUsedByProject", projectId);
		} catch (Exception e) {
			logger.error("WorkOrderResource.queryUsedByProject: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForHostIdByUuid
	 * @Description: 通过工单uuid查询该工单已经占用过哪些主机
	 * @param
	 * @return List<String>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-6 上午8:50:12
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryForHostIdByUuid(WorkOrderResourceObj obj) {
		List<String> list = new ArrayList<String>();
		try {
			list = sqlMapClient.queryForList(
					"WorkOrderResource.queryForHostIdByUuid", obj);
		} catch (Exception e) {
			logger.error("WorkOrderResource.queryForHostIdByUuid: "
					+ e.getMessage() + e.getClass().getName());
		}
		return list;
	}

}
