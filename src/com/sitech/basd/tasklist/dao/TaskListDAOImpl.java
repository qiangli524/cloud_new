package com.sitech.basd.tasklist.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGrpmemberObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.tasklist.domain.TaskListObj;
import com.sitech.basd.tasklist.domain.TaskRecordObj;

@Repository("taskListDAO")
public class TaskListDAOImpl extends BaseDao implements TaskListDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskListObj> queryTaskList(TaskListObj obj) {
		// TODO Auto-generated method stub

		List<TaskListObj> list = new ArrayList<TaskListObj>();
		try {

			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("tasklist.queryForCount", obj))
								.intValue()); // 分页查询的基本信息
				// }
			}
			list = getSqlMap().queryForList("tasklist.queryTaskList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("tasklist.queryTaskList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public void updateTaskList(TaskListObj obj) {
		// TODO Auto-generated method stub
		try {
			getSqlMap().update("tasklist.updateTaskList", obj);
		} catch (Exception e) {
			LogHelper.error("tasklist.updateTaskList: " + e.getMessage() + e.getClass().getName());
		}
	}

	@Override
	public void insertTaskList(TaskListObj obj) {
		// TODO Auto-generated method stub
		try {
			getSqlMap().insert("tasklist.insertTaskList", obj);
		} catch (Exception e) {
			LogHelper.error("tasklist.insertTaskList: " + e.getMessage() + e.getClass().getName());
		}
	}

	@Override
	public void delTaskList(TaskListObj obj) {
		// TODO Auto-generated method stub
		try {
			getSqlMap().delete("tasklist.deleteTaskList", obj);
		} catch (Exception e) {
			LogHelper.error("tasklist.deleteTaskList: " + e.getMessage() + e.getClass().getName());
		}

	}
	
	/**
	 * add start by qism
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskRecordObj> queryRecordList(TaskListObj obj) {
		List<TaskRecordObj> list = new ArrayList<TaskRecordObj>();
		try {

			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("tasklist.queryForRecordCount", obj))
								.intValue()); // 分页查询的基本信息
				// }
			}
			list = getSqlMap().queryForList("tasklist.queryRecordList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("tasklist.queryRecordList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public void delRecordList(TaskRecordObj obj) {
		// TODO Auto-generated method stub
		try {
			getSqlMap().delete("tasklist.deleteRecordList", obj);
		} catch (Exception e) {
			LogHelper.error("tasklist.deleteRecordList: " + e.getMessage() + e.getClass().getName());
		}
	}

	@Override
	public void insertTaskRecordList(TaskRecordObj obj) {
		// TODO Auto-generated method stub
		try {
			getSqlMap().insert("tasklist.insertRecordList", obj);
		} catch (Exception e) {
			LogHelper.error("tasklist.insertRecordList: " + e.getMessage() + e.getClass().getName());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskRecordObj> queryRecordById(TaskRecordObj obj) {
		List<TaskRecordObj> list = new ArrayList<TaskRecordObj>();
		try {
			list = getSqlMap().queryForList("tasklist.queryRecordListById", obj);
		} catch (Exception sqlException) {
			LogHelper.error("tasklist.queryRecordListById:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public void updateRecordById(TaskRecordObj obj) {
		// TODO Auto-generated method stub
		try {
			getSqlMap().update("tasklist.updateRecordListById", obj);
		} catch (Exception e) {
			LogHelper.error("tasklist.updateRecordListById: " + e.getMessage() + e.getClass().getName());
		}
	}
	/**add end**/

	@SuppressWarnings("unchecked")
	@Override
	public List<TbSysGrpmemberObj> queryResponPersonList() {
		// TODO Auto-generated method stub
		List<TbSysGrpmemberObj> list = new ArrayList<TbSysGrpmemberObj>();
		try {
			list = getSqlMap().queryForList("tasklist.queryResponPersonList", null);
		} catch (Exception sqlException) {
			LogHelper.error("tasklist.queryResponPersonList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}
}
