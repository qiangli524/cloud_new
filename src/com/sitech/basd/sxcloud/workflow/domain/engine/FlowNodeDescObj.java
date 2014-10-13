package com.sitech.basd.sxcloud.workflow.domain.engine;

import java.io.Serializable;

public class FlowNodeDescObj implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	private int NODE_ID;    //节点编号
	private int PROCESS_ID; //流程编号 如上线、下线流程编号
	private String NODE_NAME; //节点名称
	private String NODE_DESC; //节点描述
	private int NODE_TYPE;    //节点类型：1:开始节点,2:普通节点,3:抢单节点,4:协同开始节点,5:协同结束节点&普通节点,6:协同结束节点&抢单节点,7:协同结束节点&协同开始节点,8:派单节点,100:结束【4、5成对出现】
	private String NODE_REMARK ;           //节点备注，如审批意见，派单意见，实施完成意见
	public String getNODE_REMARK() {
		return NODE_REMARK;
	}
	public void setNODE_REMARK(String node_remark) {
		NODE_REMARK = node_remark;
	}
	public String getNODE_DESC() {
		return NODE_DESC;
	}
	public void setNODE_DESC(String node_desc) {
		NODE_DESC = node_desc;
	}
	public int getNODE_ID() {
		return NODE_ID;
	}
	public void setNODE_ID(int node_id) {
		NODE_ID = node_id;
	}
	public String getNODE_NAME() {
		return NODE_NAME;
	}
	public void setNODE_NAME(String node_name) {
		NODE_NAME = node_name;
	}
	public int getNODE_TYPE() {
		return NODE_TYPE;
	}
	public void setNODE_TYPE(int node_type) {
		NODE_TYPE = node_type;
	}
	public int getPROCESS_ID() {
		return PROCESS_ID;
	}
	public void setPROCESS_ID(int process_id) {
		PROCESS_ID = process_id;
	}
}
