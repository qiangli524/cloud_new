package com.sitech.basd.tasklist.domain;

import java.util.List;

public class TaskRecordObj {
	private String ID;				// 物理主键
	private String WORKCONTENT;		// 记录内容
	private String PARTNER;			// 配合人
	private String COMPLETE_DATE;	// 计划完成时间
	private Integer TASK_STATUS;	// 任务状态:完成1，未完成0
	private String COMPLETE_RATE;	// 完成比例
	private String NOTE;			// 备注
	private String SUBMIT_PERSION;	// 提交人
	private String SUBMIT_DATE;		// 每次更新时间，每次修改时自动记录
	private String TASK_ID;			// 物理主键 与任务ID相关联
	private String user_id;
	private String userName;
	private List<String> resultList;//删除记录时的ID集合
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getResultList() {
		return resultList;
	}
	public void setResultList(List<String> resultList) {
		this.resultList = resultList;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getWORKCONTENT() {
		return WORKCONTENT;
	}
	public void setWORKCONTENT(String wORKCONTENT) {
		WORKCONTENT = wORKCONTENT;
	}
	public String getPARTNER() {
		return PARTNER;
	}
	public void setPARTNER(String pARTNER) {
		PARTNER = pARTNER;
	}
	public String getCOMPLETE_DATE() {
		return COMPLETE_DATE;
	}
	public void setCOMPLETE_DATE(String cOMPLETE_DATE) {
		COMPLETE_DATE = cOMPLETE_DATE;
	}
	public Integer getTASK_STATUS() {
		return TASK_STATUS;
	}
	public void setTASK_STATUS(Integer tASK_STATUS) {
		TASK_STATUS = tASK_STATUS;
	}
	public String getCOMPLETE_RATE() {
		return COMPLETE_RATE;
	}
	public void setCOMPLETE_RATE(String cOMPLETE_RATE) {
		COMPLETE_RATE = cOMPLETE_RATE;
	}
	public String getNOTE() {
		return NOTE;
	}
	public void setNOTE(String nOTE) {
		NOTE = nOTE;
	}
	public String getSUBMIT_PERSION() {
		return SUBMIT_PERSION;
	}
	public void setSUBMIT_PERSION(String sUBMIT_PERSION) {
		SUBMIT_PERSION = sUBMIT_PERSION;
	}
	public String getSUBMIT_DATE() {
		return SUBMIT_DATE;
	}
	public void setSUBMIT_DATE(String sUBMIT_DATE) {
		SUBMIT_DATE = sUBMIT_DATE;
	}
	public String getTASK_ID() {
		return TASK_ID;
	}
	public void setTASK_ID(String tASK_ID) {
		TASK_ID = tASK_ID;
	}
}
