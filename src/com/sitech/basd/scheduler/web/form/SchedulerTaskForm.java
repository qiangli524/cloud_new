package com.sitech.basd.scheduler.web.form;

import java.util.List;

public class SchedulerTaskForm {

	private String[] taskId;
	private String executeState;
	private String vhName;
	private String vhIp;
	private String executeAction;
	private String executeStartDate;
	private String executeEndDate;
	private String vmType;
	private List<?> resultList = null;

	public String[] getTaskId() {
		return taskId;
	}

	public void setTaskId(String[] taskId) {
		this.taskId = taskId;
	}

	public String getExecuteState() {
		return executeState;
	}

	public void setExecuteState(String executeState) {
		this.executeState = executeState;
	}

	public String getVhName() {
		return vhName;
	}

	public void setVhName(String vhName) {
		this.vhName = vhName;
	}

	public String getVhIp() {
		return vhIp;
	}

	public void setVhIp(String vhIp) {
		this.vhIp = vhIp;
	}

	public String getExecuteAction() {
		return executeAction;
	}

	public void setExecuteAction(String executeAction) {
		this.executeAction = executeAction;
	}

	public String getExecuteStartDate() {
		return executeStartDate;
	}

	public void setExecuteStartDate(String executeStartDate) {
		this.executeStartDate = executeStartDate;
	}

	public String getExecuteEndDate() {
		return executeEndDate;
	}

	public void setExecuteEndDate(String executeEndDate) {
		this.executeEndDate = executeEndDate;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public String getVmType() {
		return vmType;
	}

	public void setVmType(String vmType) {
		this.vmType = vmType;
	}

}
