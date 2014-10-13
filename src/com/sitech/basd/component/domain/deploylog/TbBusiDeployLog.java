package com.sitech.basd.component.domain.deploylog;

import java.util.Date;

public class TbBusiDeployLog {
	// 主键ID
	private String ID;
	// 部署实例ID
	private Integer EXAMPLE_ID;
	// 部署过程日志
	private String EXAMPLE_LOG;
	// 插入信息时间
	private Date INSERTTIME;
	// 部署实例关联任务ID
	private String TASK_ID;
	// 部署实例关联订单ID
	private String ORDER_ID;
	// 是否部署成功0：失败，1：成功
	private Integer ISSUCCESS;
	// 是否完成 0：未完成 1： 已完成
	private Integer ISCOMPLETE;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public Integer getEXAMPLE_ID() {
		return EXAMPLE_ID;
	}
	public void setEXAMPLE_ID(Integer eXAMPLE_ID) {
		EXAMPLE_ID = eXAMPLE_ID;
	}
	public String getEXAMPLE_LOG() {
		return EXAMPLE_LOG;
	}
	public void setEXAMPLE_LOG(String eXAMPLE_LOG) {
		EXAMPLE_LOG = eXAMPLE_LOG;
	}
	public Date getINSERTTIME() {
		return INSERTTIME;
	}
	public void setINSERTTIME(Date iNSERTTIME) {
		INSERTTIME = iNSERTTIME;
	}
	public String getTASK_ID() {
		return TASK_ID;
	}
	public void setTASK_ID(String tASK_ID) {
		TASK_ID = tASK_ID;
	}
	public String getORDER_ID() {
		return ORDER_ID;
	}
	public void setORDER_ID(String oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}
	public Integer getISSUCCESS() {
		return ISSUCCESS;
	}
	public void setISSUCCESS(Integer iSSUCCESS) {
		ISSUCCESS = iSSUCCESS;
	}
	public Integer getISCOMPLETE() {
		return ISCOMPLETE;
	}
	public void setISCOMPLETE(Integer iSCOMPLETE) {
		ISCOMPLETE = iSCOMPLETE;
	}
	
}
