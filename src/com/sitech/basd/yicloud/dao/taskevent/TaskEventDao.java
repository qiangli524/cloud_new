package com.sitech.basd.yicloud.dao.taskevent;

import java.util.List;

import com.sitech.basd.yicloud.domain.taskevent.EventObj;
import com.sitech.basd.yicloud.domain.taskevent.TaskObj;

public interface TaskEventDao {
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
	public List queryForTaskList(TaskObj obj);

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
	public List queryForEventList(EventObj obj);
}
