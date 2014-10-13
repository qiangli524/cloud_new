package com.sitech.basd.tasklist.service;

import java.util.List;

import com.sitech.basd.tasklist.domain.TaskListObj;

public interface TaskListService {

	public List<TaskListObj> queryTaskList(TaskListObj obj);

	public int insertTaskList(TaskListObj obj);

}
