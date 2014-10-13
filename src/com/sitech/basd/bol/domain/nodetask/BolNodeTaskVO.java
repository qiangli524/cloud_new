package com.sitech.basd.bol.domain.nodetask;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * \
 * <p>
 * Title: BolNodeTaskVO
 * </p>
 * <p>
 * Description: BM3.5 节点任务VO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-2-24 下午4:22:38
 * 
 */
public class BolNodeTaskVO extends BaseObj{
	private String taskId;
	private String taskDesc;
	private int taskState;
	private String taskTime;
	private String lastTime;
	private String taskResult;
	private int taskReceiver;
	private int taskType;
	private String taskAddMess;
	private String taskTrack;

	public String getTaskTrack() {
		return taskTrack;
	}

	public void setTaskTrack(String taskTrack) {
		this.taskTrack = taskTrack;
	}

	public String getTaskAddMess() {
		return taskAddMess;
	}

	public void setTaskAddMess(String taskAddMess) {
		this.taskAddMess = taskAddMess;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public int getTaskState() {
		return taskState;
	}

	public void setTaskState(int taskState) {
		this.taskState = taskState;
	}

	public String getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(String taskTime) {
		this.taskTime = taskTime;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public String getTaskResult() {
		return taskResult;
	}

	public void setTaskResult(String taskResult) {
		this.taskResult = taskResult;
	}

	public int getTaskReceiver() {
		return taskReceiver;
	}

	public void setTaskReceiver(int taskReceiver) {
		this.taskReceiver = taskReceiver;
	}

	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}
}
