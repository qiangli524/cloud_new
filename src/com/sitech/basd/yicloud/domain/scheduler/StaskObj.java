package com.sitech.basd.yicloud.domain.scheduler;

public class StaskObj {
	
	private String TASK_ID ;// --任务编号，自动生成
	private String  STRATEGY_ID  ;// --所属策略ID
	private String  TYPE ;//  --任务类型 0 修改虚拟机 1 删除虚拟机 2 迁移虚拟机
	private String  VH_ID ;//  --虚拟机ID，采用uuid
	private String  HOST_ID ;//   --主机ID --采用uuid
	private String  VH_CPU  ;// --虚拟机CPU个数
	private String  VH_MEM ;// --虚拟机内存大小
	private String  VH_STORYAGE ;// --虚拟机存储大小
	private String  STATUS  ;//  --任务状态 0未处理 1成功 2 失败
	private String  TEMP_ID  ;// --模板ID 预留，暂时不用
	private String  INS_DATE  ;// --任务生成时间
	private String  END_DATE   ;//  --任务完成时间
	public String getTASK_ID() {
		return TASK_ID;
	}
	public void setTASK_ID(String task_id) {
		TASK_ID = task_id;
	}
	public String getSTRATEGY_ID() {
		return STRATEGY_ID;
	}
	public void setSTRATEGY_ID(String strategy_id) {
		STRATEGY_ID = strategy_id;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String type) {
		TYPE = type;
	}
	public String getVH_ID() {
		return VH_ID;
	}
	public void setVH_ID(String vh_id) {
		VH_ID = vh_id;
	}
	public String getHOST_ID() {
		return HOST_ID;
	}
	public void setHOST_ID(String host_id) {
		HOST_ID = host_id;
	}
	public String getVH_CPU() {
		return VH_CPU;
	}
	public void setVH_CPU(String vh_cpu) {
		VH_CPU = vh_cpu;
	}
	public String getVH_MEM() {
		return VH_MEM;
	}
	public void setVH_MEM(String vh_mem) {
		VH_MEM = vh_mem;
	}
	public String getVH_STORYAGE() {
		return VH_STORYAGE;
	}
	public void setVH_STORYAGE(String vh_storyage) {
		VH_STORYAGE = vh_storyage;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String status) {
		STATUS = status;
	}
	public String getTEMP_ID() {
		return TEMP_ID;
	}
	public void setTEMP_ID(String temp_id) {
		TEMP_ID = temp_id;
	}
	public String getINS_DATE() {
		return INS_DATE;
	}
	public void setINS_DATE(String ins_date) {
		INS_DATE = ins_date;
	}
	public String getEND_DATE() {
		return END_DATE;
	}
	public void setEND_DATE(String end_date) {
		END_DATE = end_date;
	}
	
	
	

}
