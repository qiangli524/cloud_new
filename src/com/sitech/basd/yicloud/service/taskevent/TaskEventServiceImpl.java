package com.sitech.basd.yicloud.service.taskevent;

import java.util.List;

import com.sitech.basd.yicloud.dao.taskevent.TaskEventDao;
import com.sitech.basd.yicloud.domain.taskevent.EventObj;
import com.sitech.basd.yicloud.domain.taskevent.TaskObj;

public class TaskEventServiceImpl implements TaskEventService {
	private TaskEventDao taskEventDao;

	public void setTaskEventDao(TaskEventDao taskEventDao) {
		this.taskEventDao = taskEventDao;
	}

	/**
	 * 
	 * @Title: queryForTaskList
	 * @Description: 获取任务列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 21, 2012 1:24:02 PM
	 */
	public List queryForTaskList(TaskObj obj) {
		return taskEventDao.queryForTaskList(obj);
	}

	/**
	 * 
	 * @Title: queryForEventList
	 * @Description: 获取事件列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 21, 2012 1:25:02 PM
	 */
	public List queryForEventList(EventObj obj) {
		return taskEventDao.queryForEventList(obj);
	}
}
