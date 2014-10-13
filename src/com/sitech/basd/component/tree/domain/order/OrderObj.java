package com.sitech.basd.component.tree.domain.order;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class OrderObj extends BaseObj {

	private String ID; // 进程的ID

	private String ORDER_ID;// 订单id

	private Integer APP_ID;// 应用ID

	private String FILE_LIST;// 上线文件清单

	private String FILE_LIST_NUM;// 上线文件清单编号

	private String EFFECT_TIME;// 有效时间

	private String AUTHORIZATION;// 许可证编号

	private String ADD_TIME;// 添加时间

	private Integer TASK_COUNT;// 任务个数

	private String TASK_ID;// taskid

	// private Integer STATUS;// 上线状态 0为提交，1已提交

	private Integer ISCOMPLETE;// 是否完成

	private String VERSIONID;// 版本ID

	private Integer TASK_WAIT_COUNT;// 任务待处理个数

	private Integer TASK_DEAL_COUNT;// 任务处中个数

	private Integer TASK_COMPLETE_COUNT;// 任务处理完成个数

	private Integer IS_SUBMIT;// 是否已经提交

	private String versionPath;

	private String backupType;// 订单关联部署实例备份策略

	public Integer getIS_SUBMIT() {
		return IS_SUBMIT;
	}

	public void setIS_SUBMIT(Integer iS_SUBMIT) {
		IS_SUBMIT = iS_SUBMIT;
	}

	public Integer getTASK_WAIT_COUNT() {
		return TASK_WAIT_COUNT;
	}

	public void setTASK_WAIT_COUNT(Integer tASK_WAIT_COUNT) {
		TASK_WAIT_COUNT = tASK_WAIT_COUNT;
	}

	public Integer getTASK_DEAL_COUNT() {
		return TASK_DEAL_COUNT;
	}

	public void setTASK_DEAL_COUNT(Integer tASK_DEAL_COUNT) {
		TASK_DEAL_COUNT = tASK_DEAL_COUNT;
	}

	public Integer getTASK_COMPLETE_COUNT() {
		return TASK_COMPLETE_COUNT;
	}

	public void setTASK_COMPLETE_COUNT(Integer tASK_COMPLETE_COUNT) {
		TASK_COMPLETE_COUNT = tASK_COMPLETE_COUNT;
	}

	public Integer getISCOMPLETE() {
		return ISCOMPLETE;
	}

	public void setISCOMPLETE(Integer iSCOMPLETE) {
		ISCOMPLETE = iSCOMPLETE;
	}

	// public Integer getSTATUS() {
	// return STATUS;
	// }
	//
	// public void setSTATUS(Integer sTATUS) {
	// STATUS = sTATUS;
	// }

	public String getTASK_ID() {
		return TASK_ID;
	}

	public void setTASK_ID(String tASK_ID) {
		TASK_ID = tASK_ID;
	}

	public Integer getTASK_COUNT() {
		return TASK_COUNT;
	}

	public void setTASK_COUNT(Integer tASK_COUNT) {
		TASK_COUNT = tASK_COUNT;
	}

	public String getORDER_ID() {
		return ORDER_ID;
	}

	public void setORDER_ID(String oRDERID) {
		ORDER_ID = oRDERID;
	}

	public String getFILE_LIST_NUM() {
		return FILE_LIST_NUM;
	}

	public void setFILE_LIST_NUM(String fILELISTNUM) {
		FILE_LIST_NUM = fILELISTNUM;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Integer getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(Integer aPPID) {
		APP_ID = aPPID;
	}

	public String getFILE_LIST() {
		return FILE_LIST;
	}

	public void setFILE_LIST(String fILELIST) {
		FILE_LIST = fILELIST;
	}

	public String getEFFECT_TIME() {
		return EFFECT_TIME;
	}

	public void setEFFECT_TIME(String eFFECTTIME) {
		EFFECT_TIME = eFFECTTIME;
	}

	public String getAUTHORIZATION() {
		return AUTHORIZATION;
	}

	public void setAUTHORIZATION(String aUTHORIZATION) {
		AUTHORIZATION = aUTHORIZATION;
	}

	public String getADD_TIME() {
		return ADD_TIME;
	}

	public void setADD_TIME(String aDDTIME) {
		ADD_TIME = aDDTIME;
	}

	public String getVERSIONID() {
		return VERSIONID;
	}

	public void setVERSIONID(String vERSIONID) {
		VERSIONID = vERSIONID;
	}

	public String getVersionPath() {
		return versionPath;
	}

	public void setVersionPath(String versionPath) {
		this.versionPath = versionPath;
	}

	public String getBackupType() {
		return backupType;
	}

	public void setBackupType(String backupType) {
		this.backupType = backupType;
	}

}