package com.sitech.basd.tasklist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.tasklist.dao.TaskListDAO;
import com.sitech.basd.tasklist.domain.TaskListObj;

@Service("taskListService")
public class TaskListServiceImpl implements TaskListService {

	@Autowired
	private TaskListDAO taskListDAO;

	@Override
	public List<TaskListObj> queryTaskList(TaskListObj obj) {
		// TODO Auto-generated method stub

		return taskListDAO.queryTaskList(obj);
	}

	@Override
	public int insertTaskList(TaskListObj obj) {
		// TODO Auto-generated method stub
		// return taskListDAO.insertTaskList(obj);
		return 0;

	}

}
