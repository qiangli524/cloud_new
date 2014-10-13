package com.sitech.basd.tasklist.dao;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGrpmemberObj;
import com.sitech.basd.tasklist.domain.TaskListObj;
import com.sitech.basd.tasklist.domain.TaskRecordObj;

public interface TaskListDAO {
	public List<TaskListObj> queryTaskList(TaskListObj obj);

	public void updateTaskList(TaskListObj obj);

	public void insertTaskList(TaskListObj obj);

	public void delTaskList(TaskListObj obj);
	/**
	 * @Title: queryRecordList
	 * @Description: 通过任务ID查询相关详细的记录
	 * @param
	 * @return List<TaskRecordObj>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-16 下午3:36:00
	 */
	public List<TaskRecordObj> queryRecordList(TaskListObj obj);
	/**
	 * @Title: delRecordList
	 * @Description: 删除记录
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-16 下午4:44:19
	 */
	public void delRecordList(TaskRecordObj obj);
	/**
	 * @Title: insertTaskRecordList
	 * @Description: 添加记录
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-17 上午9:59:19
	 */
	public void insertTaskRecordList(TaskRecordObj obj);
	/**
	 * @Title: queryRecordById
	 * @Description: 通过ID查询记录
	 * @param
	 * @return TaskRecordObj
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-17 上午11:58:22
	 */
	public List<TaskRecordObj> queryRecordById(TaskRecordObj obj);
	/**
	 * @Title: updateRecordById
	 * @Description: 实际修改动作
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-18 上午9:24:53
	 */
	public void updateRecordById(TaskRecordObj obj);
	/**
	 * @Title: queryResponPersonList
	 * @Description: 查询责任人供选择
	 * @param
	 * @return List<String>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-18 下午2:29:09
	 */
	public List<TbSysGrpmemberObj> queryResponPersonList();
}
