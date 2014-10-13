package com.sitech.basd.sxcloud.workflow.domain.engine;

import java.io.Serializable;

public class DisposeManObj  implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	private int applicationType ;  //工单类型、如上线、下线流程类型，表TB_ASLM_FLOW_PROCESS(PROCESS_ID)
	private int currNodeId ;       //当前节点编号、如发起环节、审批一环节、审批环节二编号
	private String userId ;        //用户编号、用户唯一标识
	private String groupName ;     //工作组名称
	private int  nextNodeId  ;     //下一节点编号
	private int  oprate      ;     //任务操作,任务操作，(1:普通操作,2:挂起,3:工单回撤【只能回撤下一节点全然处理的工单】,4:打回)
	public int getOprate() {
		return oprate;
	}
	public void setOprate(int oprate) {
		this.oprate = oprate;
	}
	public int getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(int applicationType) {
		this.applicationType = applicationType;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCurrNodeId() {
		return currNodeId;
	}
	public void setCurrNodeId(int currNodeId) {
		this.currNodeId = currNodeId;
	}
	public int getNextNodeId() {
		return nextNodeId;
	}
	public void setNextNodeId(int nextNodeId) {
		this.nextNodeId = nextNodeId;
	}


}
