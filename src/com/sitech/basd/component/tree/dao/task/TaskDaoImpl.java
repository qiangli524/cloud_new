package com.sitech.basd.component.tree.dao.task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.component.tree.domain.task.DeployExampleObj;
import com.sitech.basd.component.tree.domain.task.TaskLogObj;
import com.sitech.basd.component.tree.domain.task.TaskObj;
import com.sitech.basd.component.tree.domain.task.TaskRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("taskDao")
public class TaskDaoImpl extends BaseDao implements TaskDao {
	/**
	 * 
	 * @Title: TaskInfoList
	 * @Description: 查询配置文件列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 10:04:03 AM
	 */
	@Override
	public List<TaskObj> queryTaskInfoList(TaskObj obj) {
		List<TaskObj> list = new ArrayList<TaskObj>();
		try {
			list = getSqlMap().queryForList("Task.queryForList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper
					.error("Task.queryForList:" + sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

	@Override
	public int insertByObj(TaskObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().insert("Task.insertByObj", obj);
			if (obj1 == null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.insertByObj:" + sqlException.getMessage() + getClass().getName());
		}
		return ent;
	}

	@Override
	public int delByObj(TaskObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().delete("Task.deleteByObj", obj);
			if (obj1 != null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.deleteByObj:" + sqlException.getMessage() + getClass().getName());
		}
		return ent;
	}

	@Override
	public int updateTaskByObj(TaskObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().update("Task.updateByObj", obj);
			if (obj1 != null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.updateByObj:" + sqlException.getMessage() + getClass().getName());
		}
		return ent;
	}

	@Override
	public int insertRelationByObj(TaskRelationObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().insert("Task.insertRelationByObj", obj);
			if (obj1 == null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.insertRelationByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ent;
	}

	@Override
	public List<TaskRelationObj> queryRelationTaskInfoList(TaskRelationObj obj) {
		List<TaskRelationObj> list = new ArrayList<TaskRelationObj>();
		try {
			list = getSqlMap().queryForList("Task.queryRelationForList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.queryRelationForList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public int delRelationByObj(TaskRelationObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().delete("Task.deleteRelationByObj", obj);
			if (obj1 != null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.deleteRelationByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ent;
	}

	@Override
	public int updateRelationByObj(TaskRelationObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().update("Task.updateRelationByObj", obj);
			if (obj1 != null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.updateRelationByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ent;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskObj> queryTaskRealtiontOrderList(TaskObj obj) {
		List<TaskObj> list = new ArrayList<TaskObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Task.queryTaskRealtiontOrderListCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("Task.queryTaskRealtiontOrderList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.queryTaskRealtiontOrderList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public int deleteTaskRelationOrderByObj(TaskObj obj) {
		int ent = -1;
		try {
			Object obj1 = getSqlMap().delete("Task.deleteTaskRelationOrderByObj", obj);
			if (obj1 != null) {
				ent = 0;
			}
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.deleteTaskRelationOrderByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ent;
	}

	@Override
	public List<TaskObj> queryForListBaseNotInOrderRelation(TaskObj obj) {
		List<TaskObj> list = new ArrayList<TaskObj>();
		try {
			list = getSqlMap().queryForList("Task.queryForListNotInOrderRelation", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.queryForListNotInOrderRelation:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List<DeployExampleObj> queryExampleAndUserNameByTreeIdList(DeployExampleObj obj) {
		List<DeployExampleObj> list = new ArrayList<DeployExampleObj>();
		try {
			list = getSqlMap().queryForList("Task.queryExampleAndUserNameByTreeIdList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.queryExampleAndUserNameByTreeIdList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public TaskObj queryTaskStatus(TaskObj obj) {
		TaskObj list = new TaskObj();
		try {
			list = (TaskObj) getSqlMap().queryForObject("Task.queryTaskStatus", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.queryTaskStatus:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public int countTaskNum(TaskObj taskObj) {
		int ret = -1;
		try {
			Object obj = getSqlMap().queryForObject("Task.countTaskNum", taskObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("Task.countTaskNum: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskObj> queryAllMappingsByObj(TaskObj taskObj) {
		List<TaskObj> list = new ArrayList<TaskObj>();
		try {
			list = getSqlMap().queryForList("Task.queryAllMappingsByObj", taskObj);
		} catch (Exception e) {
			LogHelper.error("Task.queryAllMappingsByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryLog
	 * @param obj
	 * @author siweichao
	 * @return int
	 * @version 1.0
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<TaskLogObj> queryLog(TaskLogObj obj) {
		List<TaskLogObj> list = new ArrayList<TaskLogObj>();
		try {
			list = getSqlMap().queryForList("Task.queryLog", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Task.queryLog:" + sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

	@Override
	public List<DeployExampleObj> queryExampleListNotInTask(DeployExampleObj obj) {
		List<DeployExampleObj> list = new ArrayList<DeployExampleObj>();
		try {
			list = getSqlMap().queryForList("Task.queryExampleListNotInTask", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.queryExampleListNotInTask:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public List<DeployExampleObj> queryExampleListInTaskAndComplete(DeployExampleObj obj) {
		List<DeployExampleObj> list = new ArrayList<DeployExampleObj>();
		try {
			list = getSqlMap().queryForList("Task.queryExampleListInTaskAndComplete", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("Task.queryExampleListInTaskAndComplete:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryTaskReport
	 * @Description: 查询任务报告
	 * @param
	 * @return List<TaskLogObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-20 下午11:03:01
	 */
	@Override
	public List<TaskLogObj> queryTaskReportList(TaskLogObj obj) {
		List<TaskLogObj> list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination()
						.setTotalCount(
								((Integer) getSqlMap().queryForObject(
										"Task.queryTaskReportListCount", obj)).intValue());
				list = getSqlMap().queryForList("Task.queryTaskReportList", obj);
			}
		} catch (Exception sqlexception) {
			LogHelper.error("Task.queryTaskReportList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: exportTaskReportList
	 * @Description: 导出任务报告
	 * @param
	 * @return List<TaskLogObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-22 下午10:12:30
	 */
	@Override
	public List<TaskLogObj> exportTaskReportList(TaskLogObj obj) {
		List<TaskLogObj> list = null;
		try {
			list = getSqlMap().queryForList("Task.exportTaskReportList", obj);
		} catch (SQLException e) {
			LogHelper.error("Task.exportTaskReportList:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: querySuccessCount
	 * @Description: 查询成功个数
	 * @param
	 * @return TaskLogObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-23 上午11:07:53
	 */
	@Override
	public TaskLogObj querySuccessCount(TaskLogObj obj) {
		TaskLogObj taLogObj = new TaskLogObj();
		try {
			taLogObj = (TaskLogObj) getSqlMap().queryForObject("Task.querySuccessCount", obj);
		} catch (SQLException e) {
			LogHelper.error("Task.querySuccessCount:" + e.getMessage() + getClass().getName());
		}
		return taLogObj;
	}

	/**
	 * 
	 * @Title: queryfailCount
	 * @Description: 查询失败个数
	 * @param
	 * @return TaskLogObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-23 上午11:07:53
	 */
	@Override
	public TaskLogObj queryFailCount(TaskLogObj obj) {
		TaskLogObj taLogObj = new TaskLogObj();
		try {
			taLogObj = (TaskLogObj) getSqlMap().queryForObject("Task.queryFailCount", obj);
		} catch (SQLException e) {
			LogHelper.error("Task.queryFailCount:" + e.getMessage() + getClass().getName());
		}
		return taLogObj;
	}

	/**
	 * 
	 * @Title: querySuccessTaskLog
	 * @Description: 查询成功任务日志
	 * @param
	 * @return List<TaskLogObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-23 上午11:47:44
	 */
	@Override
	public List<TaskLogObj> querySuccessTaskLog(TaskLogObj obj) {
		List<TaskLogObj> list = null;
		try {
			list = getSqlMap().queryForList("Task.querySuccessTaskLog", obj);
		} catch (SQLException e) {
			LogHelper.error("Task.querySuccessTaskLog:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryFailTaskLog
	 * @Description: 查询失败任务日志
	 * @param
	 * @return List<TaskLogObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-23 上午11:47:44
	 */
	@Override
	public List<TaskLogObj> queryFailTaskLog(TaskLogObj obj) {
		List<TaskLogObj> list = null;
		try {
			list = getSqlMap().queryForList("Task.queryFailTaskLog", obj);
		} catch (SQLException e) {
			LogHelper.error("Task.queryFailTaskLog:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	@Override
	public String queryBackupType(TaskObj obj) {
		String string = null;
		try {
			string = (String) getSqlMap().queryForObject("Task.queryBackupType", obj);
		} catch (SQLException e) {
			LogHelper.error("Task.queryBackupType:" + e.getMessage() + getClass().getName());
		}
		return string;
	}
}
