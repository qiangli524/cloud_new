package com.sitech.basd.sxcloud.workflow.domain.engine;

import java.io.Serializable;

public class FlowRuleObj implements Serializable, Cloneable{

private static final long serialVersionUID = 1L;
private int PROCESS_ID ;    //流程编号
private int PER_NODEID ;    //前置节点编号
private int CURR_NODEID ;   //当前节点
private int NEXT_NODEID ;   //后驱节点
public int getCURR_NODEID() {
	return CURR_NODEID;
}
public void setCURR_NODEID(int curr_nodeid) {
	CURR_NODEID = curr_nodeid;
}
public int getNEXT_NODEID() {
	return NEXT_NODEID;
}
public void setNEXT_NODEID(int next_nodeid) {
	NEXT_NODEID = next_nodeid;
}
public int getPER_NODEID() {
	return PER_NODEID;
}
public void setPER_NODEID(int per_nodeid) {
	PER_NODEID = per_nodeid;
}
public int getPROCESS_ID() {
	return PROCESS_ID;
}
public void setPROCESS_ID(int process_id) {
	PROCESS_ID = process_id;
}

}
