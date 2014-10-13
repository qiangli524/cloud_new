package com.sitech.basd.component.tree.domain.task;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TaskRelationObj extends BaseObj{
	
	private String Temp;//一个临时标记 字段
	
	private String TASK_ID;//任务id
	
	private String EXAMPLE_ID;//实例ID
	
	private Integer DEPLOY_STATUS;//0待处理，1正处理，2处理成功，3部署失败
	
	private Integer EXECUTE_LEVEL;//任务执行的优先级别
	
	private String DEPLOY_RESPORT;//部署报告
	
	private Integer APP_ID;//教程
	
	public Integer getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(Integer aPP_ID) {
		APP_ID = aPP_ID;
	}

	public String getTemp() {
		return Temp;
	}

	public void setTemp(String temp) {
		Temp = temp;
	}

	public String getTASK_ID() {
		return TASK_ID;
	}

	public void setTASK_ID(String tASK_ID) {
		TASK_ID = tASK_ID;
	}

	public String getEXAMPLE_ID() {
		return EXAMPLE_ID;
	}

	public void setEXAMPLE_ID(String eXAMPLE_ID) {
		EXAMPLE_ID = eXAMPLE_ID;
	}

	public Integer getDEPLOY_STATUS() {
		return DEPLOY_STATUS;
	}

	public void setDEPLOY_STATUS(Integer dEPLOY_STATUS) {
		DEPLOY_STATUS = dEPLOY_STATUS;
	}

	public Integer getEXECUTE_LEVEL() {
		return EXECUTE_LEVEL;
	}

	public void setEXECUTE_LEVEL(Integer eXECUTE_LEVEL) {
		EXECUTE_LEVEL = eXECUTE_LEVEL;
	}

	public String getDEPLOY_RESPORT() {
		return DEPLOY_RESPORT;
	}

	public void setDEPLOY_RESPORT(String dEPLOY_RESPORT) {
		DEPLOY_RESPORT = dEPLOY_RESPORT;
	}
	
}
