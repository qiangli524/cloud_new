package com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;


public class TaskInfoObj extends BaseObj implements Serializable, Cloneable {
	private int TASK_ID;				   //当前任务编号
	
	private int NODE_ID;  				   //节点编号,如发起环节、审批一环节、审批环节二编号
	
	private String DISPOSE_MAN;			   //任务处理人唯一标识,终止节点为NULL值

	private String REMARK;				   //任务意见
	
	private String RECEIVE_TIME ;          //任务接受时间
	
	private String DISPOSE_TIME ;          //任务处理时间
	
	private String NODE_NAME ;             //节点名称
	
	private String NODE_REMARK ;            //节点备注
	
	public String getNODE_NAME() {
		return NODE_NAME;
	}

	public void setNODE_NAME(String node_name) {
		NODE_NAME = node_name;
	}

	public String getDISPOSE_MAN() {
		return DISPOSE_MAN;
	}

	public void setDISPOSE_MAN(String dispose_man) {
		DISPOSE_MAN = dispose_man;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

	public int getTASK_ID() {
		return TASK_ID;
	}

	public void setTASK_ID(int task_id) {
		TASK_ID = task_id;
	}

	public String getDISPOSE_TIME() {
		return DISPOSE_TIME;
	}

	public void setDISPOSE_TIME(String dispose_time) {
		DISPOSE_TIME = dispose_time;
	}

	public int getNODE_ID() {
		return NODE_ID;
	}

	public void setNODE_ID(int node_id) {
		NODE_ID = node_id;
	}

	public String getRECEIVE_TIME() {
		return RECEIVE_TIME;
	}

	public void setRECEIVE_TIME(String receive_time) {
		RECEIVE_TIME = receive_time;
	}

	public String getNODE_REMARK() {
		return NODE_REMARK;
	}

	public void setNODE_REMARK(String node_remark) {
		NODE_REMARK = node_remark;
	}
}
