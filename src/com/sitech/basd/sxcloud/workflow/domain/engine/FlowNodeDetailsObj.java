package com.sitech.basd.sxcloud.workflow.domain.engine;

import java.io.Serializable;
import java.util.List;

public class FlowNodeDetailsObj implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	private int TASK_ID ;                  //任务编号
	private String NODE_NAME ;             //节点名称
	private String NODE_REMARK ;           //节点备注，如审批意见，派单意见，实施完成意见
	private int NODE_TYPE  ;               // 节点类型：
	private List<Integer>   TASK_OPERATE ;          // 节点操作权限, FlowConstant【流程操作权限】 
	private List<NextDisposeManObj>   NODE_NEXT_DISPOSEMAN ;           //节点下一处理人
	private List<FlowTaskObj>  TASK_TRACK  ;                           //任务轨迹
	public String getNODE_NAME() {
		return NODE_NAME;
	}
	public void setNODE_NAME(String node_name) {
		NODE_NAME = node_name;
	}

	public List<NextDisposeManObj> getNODE_NEXT_DISPOSEMAN() {
		return NODE_NEXT_DISPOSEMAN;
	}
	public void setNODE_NEXT_DISPOSEMAN(List<NextDisposeManObj> node_next_disposeman) {
		NODE_NEXT_DISPOSEMAN = node_next_disposeman;
	}
	public String getNODE_REMARK() {
		return NODE_REMARK;
	}
	public void setNODE_REMARK(String node_remark) {
		NODE_REMARK = node_remark;
	}  

	public int getTASK_ID() {
		return TASK_ID;
	}
	public void setTASK_ID(int task_id) {
		TASK_ID = task_id;
	}
	public List<FlowTaskObj> getTASK_TRACK() {
		return TASK_TRACK;
	}
	public void setTASK_TRACK(List<FlowTaskObj> task_track) {
		TASK_TRACK = task_track;
	}
	public int getNODE_TYPE() {
		return NODE_TYPE;
	}
	public void setNODE_TYPE(int node_type) {
		NODE_TYPE = node_type;
	}
	public List<Integer> getTASK_OPERATE() {
		return TASK_OPERATE;
	}
	public void setTASK_OPERATE(List<Integer> task_operate) {
		TASK_OPERATE = task_operate;
	}
	
}
