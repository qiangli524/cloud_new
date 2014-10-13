package com.sitech.basd.sxcloud.workflow.domain.engine;

import java.io.Serializable;

public class FlowTaskObj implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	private int TASK_ID      	        ;  //当前任务编号
	private int PROCESS_ID       	    ;  //工单类型、如上线、下线流程类型，表TB_ASLM_FLOW_PROCESS(PROCESS_ID)
	private int NODE_ID     	        ;  //节点编号,如发起环节、审批一环节、审批环节二编号
	private String PROCESS_SERIALNUMBER  ; //申请单流水号，工单唯一标识
	private int PER_TASK_ID      	    ;  //前置任务编号,起始节点为值为0
	private String DISPOSE_MAN        ;    //任务处理人唯一标识,终止节点为NULL值
	private int TASK_STATUS     	    ;  //任务状态，(0:草稿,只可能在发起流程出现,1:等待处理,2:已处理)
	private int TASK_OPERATE     ;         //任务操作，(1:普通操作,2:挂起,3:工单回撤【只能回撤下一节点全然处理的工单】,4:被打回)
	private String TASK_REMARK =null ;     //任务审批意见
	private String RECEIVE_TIME ;          //任务接受时间
	private String DISPOSE_TIME ;          //任务处理时间
	private String NODE_NAME ;             //节点名称
	private String NODE_REMARK ;           //节点备注，如审批意见，派单意见，实施完成意见
	public String getNODE_REMARK() {
		return NODE_REMARK;
	}
	public void setNODE_REMARK(String node_remark) {
		NODE_REMARK = node_remark;
	}
	public String getNODE_NAME() {
		return NODE_NAME;
	}
	public void setNODE_NAME(String node_name) {
		NODE_NAME = node_name;
	}
	public String getTASK_REMARK() {
		return TASK_REMARK;
	}
	public void setTASK_REMARK(String task_remark) {
		TASK_REMARK = task_remark;
	}
	public String getDISPOSE_MAN() {
		return DISPOSE_MAN;
	}
	public void setDISPOSE_MAN(String dispose_man) {
		DISPOSE_MAN = dispose_man;
	}
	public int getNODE_ID() {
		return NODE_ID;
	}
	public void setNODE_ID(int node_id) {
		NODE_ID = node_id;
	}
	public int getPER_TASK_ID() {
		return PER_TASK_ID;
	}
	public void setPER_TASK_ID(int per_task_id) {
		PER_TASK_ID = per_task_id;
	}
	public int getPROCESS_ID() {
		return PROCESS_ID;
	}
	public void setPROCESS_ID(int process_id) {
		PROCESS_ID = process_id;
	}
	
	public String getDISPOSE_TIME() {
		return DISPOSE_TIME;
	}
	public void setDISPOSE_TIME(String dispose_time) {
		DISPOSE_TIME = dispose_time;
	}
	public String getPROCESS_SERIALNUMBER() {
		return PROCESS_SERIALNUMBER;
	}
	public void setPROCESS_SERIALNUMBER(String process_serialnumber) {
		PROCESS_SERIALNUMBER = process_serialnumber;
	}
	public String getRECEIVE_TIME() {
		return RECEIVE_TIME;
	}
	public void setRECEIVE_TIME(String receive_time) {
		RECEIVE_TIME = receive_time;
	}
	public int getTASK_ID() {
		return TASK_ID;
	}
	public void setTASK_ID(int task_id) {
		TASK_ID = task_id;
	}
	public int getTASK_STATUS() {
		return TASK_STATUS;
	}
	public void setTASK_STATUS(int task_status) {
		TASK_STATUS = task_status;
	}
	public int getTASK_OPERATE() {
		return TASK_OPERATE;
	}
	public void setTASK_OPERATE(int task_operate) {
		TASK_OPERATE = task_operate;
	}
	
}
