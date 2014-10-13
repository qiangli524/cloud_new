package com.sitech.basd.sxcloud.workflow.domain.resourceorder;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class ResourceOrderInfoObj extends BaseObj implements Serializable,
		Cloneable {
	private int ID;// 工单编号
	private String NEED_NUMBERS;// 需求编号
	private String NEED_START;// 需求发起时间
	private String NEED_STATUS;// 工单状态
	private String NEED_SPONSOR;// 工单发起人
	private String NEED_END;// 要求完成时间
	private String taskId = null; // 任务编号
	private int processNode = 0; // 节点编号,如发起环节、审批一环节、审批环节二编号
	private String DisposeMan; // 处理人
	private String content;// 具体内容
	private String TYPE;// 资源类别值
	private String TYPE_NAME;// 资源类别名称
	private String WORKLOAD_ID;// 虚拟机ID
	private String VH_STAT;// 虚拟机状态
	private String VH_ID_IBM;// 虚拟机编号

	private String APPLY_TYPE;//申请类型	必填                                         


	public String getAPPLY_TYPE() {
		return APPLY_TYPE;
	}

	public void setAPPLY_TYPE(String aPPLYTYPE) {
		APPLY_TYPE = aPPLYTYPE;
	}

	public String getVH_ID_IBM() {
		return VH_ID_IBM;
	}

	public void setVH_ID_IBM(String vh_id_ibm) {
		VH_ID_IBM = vh_id_ibm;
	}

	public String getVH_STAT() {
		return VH_STAT;
	}

	public void setVH_STAT(String vh_stat) {
		VH_STAT = vh_stat;
	}

	public String getWORKLOAD_ID() {
		return WORKLOAD_ID;
	}

	public void setWORKLOAD_ID(String workload_id) {
		WORKLOAD_ID = workload_id;
	}

	public String getTYPE_NAME() {
		return TYPE_NAME;
	}

	public void setTYPE_NAME(String type_name) {
		TYPE_NAME = type_name;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public int getProcessNode() {
		return processNode;
	}

	public void setProcessNode(int processNode) {
		this.processNode = processNode;
	}

	public String getDisposeMan() {
		return DisposeMan;
	}

	public void setDisposeMan(String disposeMan) {
		DisposeMan = disposeMan;
	}

	public String getNEED_END() {
		return NEED_END;
	}

	public void setNEED_END(String nEEDEND) {
		NEED_END = nEEDEND;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNEED_NUMBERS() {
		return NEED_NUMBERS;
	}

	public void setNEED_NUMBERS(String nEEDNUMBERS) {
		NEED_NUMBERS = nEEDNUMBERS;
	}

	public String getNEED_START() {
		return NEED_START;
	}

	public void setNEED_START(String nEEDSTART) {
		NEED_START = nEEDSTART;
	}

	public String getNEED_STATUS() {
		return NEED_STATUS;
	}

	public void setNEED_STATUS(String nEEDSTATUS) {
		NEED_STATUS = nEEDSTATUS;
	}

	public String getNEED_SPONSOR() {
		return NEED_SPONSOR;
	}

	public void setNEED_SPONSOR(String nEEDSPONSOR) {
		NEED_SPONSOR = nEEDSPONSOR;
	}

}
