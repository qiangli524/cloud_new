package com.sitech.basd.yicloud.dao.taskevent;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.taskevent.EventObj;
import com.sitech.basd.yicloud.domain.taskevent.TaskObj;

public class TaskEventDaoImpl extends BaseDao implements TaskEventDao {
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
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TaskEvent.queryForTaskList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TaskEvent.queryForTaskList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
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
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TaskEvent.queryForEventList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TaskEvent.queryForEventList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
}
