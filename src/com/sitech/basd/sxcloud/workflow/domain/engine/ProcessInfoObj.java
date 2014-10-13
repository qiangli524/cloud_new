package com.sitech.basd.sxcloud.workflow.domain.engine;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProcessInfoObj  implements Serializable, Cloneable {
	private String applicationId;     //工单唯一编号唯一标识
	private int applicationType ;  //工单类型、如上线、下线流程类型，表TB_ASLM_FLOW_PROCESS(PROCESS_ID)
	private int taskId;            //任务处理编号
	private String userId ;        //用户编号,用户唯一标识
	private int processNode ;      //节点编号,如发起环节、审批一环节、审批环节二编号
	private int processNodeStatus ;//节点状态,(0:草稿,只可能在发起流程出现,1:等待处理,2:已处理,3:被打回)
	private String receiveTime ;   //工单接受时间 
	private String disposeTime ;   //工单处理时间
	private int operate ;          //工单状态 1:正常流转,2:挂起,3:工单撤回
	private String disposeMan ;    //流程当前处理人,流程当前待办人
	private String description;
	
	

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisposeMan() {
		return disposeMan;
	}
	public void setDisposeMan(String disposeMan) {
		this.disposeMan = disposeMan;
	}
	public String getDisposeTime() {
		return disposeTime;
	}
	public void setDisposeTime(String disposeTime) {
		this.disposeTime = disposeTime;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public int getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(int applicationType) {
		this.applicationType = applicationType;
	}
	public int getProcessNode() {
		return processNode;
	}
	public void setProcessNode(int processNode) {
		this.processNode = processNode;
	}
	public int getProcessNodeStatus() {
		return processNodeStatus;
	}
	public void setProcessNodeStatus(int processNodeStatus) {
		this.processNodeStatus = processNodeStatus;
	}
	public int getOperate() {
		return operate;
	}
	public void setOperate(int operate) {
		this.operate = operate;
	}

}
