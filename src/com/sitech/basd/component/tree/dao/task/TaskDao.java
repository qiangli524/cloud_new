package com.sitech.basd.component.tree.dao.task;

import java.util.List;

import com.sitech.basd.component.tree.domain.task.DeployExampleObj;
import com.sitech.basd.component.tree.domain.task.TaskLogObj;
import com.sitech.basd.component.tree.domain.task.TaskObj;
import com.sitech.basd.component.tree.domain.task.TaskRelationObj;

public interface TaskDao {

	/**
	 * 查询所有的订单
	 * 
	 * @param obj
	 * @return
	 */
	public List<TaskObj> queryTaskInfoList(TaskObj obj);

	/**
	 * 查询所有的订单
	 * 
	 * @param obj
	 * @return
	 */
	public List<TaskRelationObj> queryRelationTaskInfoList(TaskRelationObj obj);

	/**
	 * 查询所有不在订单关系表中的任务
	 * 
	 * @param obj
	 * @return
	 */
	public List<TaskObj> queryForListBaseNotInOrderRelation(TaskObj obj);

	/**
	 * 查询task和order之间的关系
	 * 
	 * @param obj
	 * @return
	 */
	public List<TaskObj> queryTaskRealtiontOrderList(TaskObj obj);

	/**
	 * 查询任务的状态
	 * 
	 * @param obj
	 * @return
	 */
	public TaskObj queryTaskStatus(TaskObj obj);

	/**
	 * 插入数据
	 * 
	 * @param obj
	 * @return
	 */
	public int insertByObj(TaskObj obj);

	/**
	 * 插入数据
	 * 
	 * @param obj
	 * @return
	 */
	public int insertRelationByObj(TaskRelationObj obj);

	/**
	 * 删除数据
	 * 
	 * @param obj
	 * @return
	 */
	public int delByObj(TaskObj obj);

	/**
	 * 删除数据
	 * 
	 * @param obj
	 * @return
	 */
	public int delRelationByObj(TaskRelationObj obj);

	/**
	 * 移除任务和订单之间的关系
	 * 
	 * @param obj
	 * @return
	 */
	public int deleteTaskRelationOrderByObj(TaskObj obj);

	/**
	 * 更新数据
	 * 
	 * @param obj
	 * @return
	 */
	public int updateTaskByObj(TaskObj obj);

	/**
	 * 更新数据
	 * 
	 * @param obj
	 * @return
	 */
	public int updateRelationByObj(TaskRelationObj obj);

	/**
	 * 查询此基准应用下所有的实例
	 * 
	 * @param obj
	 * @return
	 */
	public List<DeployExampleObj> queryExampleAndUserNameByTreeIdList(DeployExampleObj obj);

	public int countTaskNum(TaskObj taskObj);

	public List<TaskObj> queryAllMappingsByObj(TaskObj taskObj);

	/**
	 * @Title: queryLog
	 * @param obj
	 * @author siweichao
	 * @return int
	 * @version 1.0
	 */
	public List<TaskLogObj> queryLog(TaskLogObj obj);

	/**
	 * 查询所有不在任务中的部署实例
	 * 
	 * @param obj
	 * @return
	 */
	public List<DeployExampleObj> queryExampleListNotInTask(DeployExampleObj obj);

	/**
	 * 获取在任务中的实例，并且此任务已经完成
	 * 
	 * @param obj
	 * @return
	 */
	public List<DeployExampleObj> queryExampleListInTaskAndComplete(DeployExampleObj obj);

	/**
	 * 
	 * @Title: queryTaskReportList
	 * @Description: 查询任务报告
	 * @param
	 * @return List<TaskLogObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-20 下午11:03:01
	 */
	public List<TaskLogObj> queryTaskReportList(TaskLogObj obj);

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
	public List<TaskLogObj> exportTaskReportList(TaskLogObj obj);

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
	public TaskLogObj querySuccessCount(TaskLogObj obj);

	/**
	 * 
	 * @Title: queryFailCount
	 * @Description: 查询失败个数
	 * @param
	 * @return TaskLogObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-23 上午11:07:53
	 */
	public TaskLogObj queryFailCount(TaskLogObj obj);

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
	public List<TaskLogObj> querySuccessTaskLog(TaskLogObj obj);

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
	public List<TaskLogObj> queryFailTaskLog(TaskLogObj obj);

	/**
	 * 
	 * @Title: queryBackupType
	 * @Description:查询BackupType
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-25 下午9:15:05
	 */
	public String queryBackupType(TaskObj obj);
}
