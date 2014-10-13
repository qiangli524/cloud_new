package com.sitech.basd.component.tree.domain.task;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TaskObj extends BaseObj{	
	
	private String ID; //进程的ID
	
	private String TASK_ID;//任务编号
	
	private Integer STATUS;//任务状态
	
	private Integer TASK_TYPE;//任务类型
	
	private Integer SUCCESS_COUNT;//上线文件清单
	
	private Integer FAILURE_COUNT;//上线文件清单编号
	
	private String EXECUTE_RESULT;//有效时间
	
	private String REPORT_PATH;//许可证编号
	
	private String ADD_TIME;//添加时间
	
	private Integer EXAMPLE_COUNT;//实例总数
	
	private String ORDER_ID;//订单id
	
	private Integer APP_ID;//应用的id
	
	private String START_TIME;//任务的开始时间
	
	private String COMPLETE_TIME;//任务的完成时间
	
	private Integer ISCOMPLETE;//0为完成 1已完成
	
	public Integer getISCOMPLETE() {
		return ISCOMPLETE;
	}

	public void setISCOMPLETE(Integer iSCOMPLETE) {
		ISCOMPLETE = iSCOMPLETE;
	}

	public String getSTART_TIME() {
		return START_TIME;
	}

	public void setSTART_TIME(String sTART_TIME) {
		START_TIME = sTART_TIME;
	}

	public String getCOMPLETE_TIME() {
		return COMPLETE_TIME;
	}

	public void setCOMPLETE_TIME(String cOMPLETE_TIME) {
		COMPLETE_TIME = cOMPLETE_TIME;
	}

	public Integer getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(Integer aPP_ID) {
		APP_ID = aPP_ID;
	}

	public String getORDER_ID() {
		return ORDER_ID;
	}

	public void setORDER_ID(String oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}

	public Integer getEXAMPLE_COUNT() {
		return EXAMPLE_COUNT;
	}

	public void setEXAMPLE_COUNT(Integer eXAMPLECOUNT) {
		EXAMPLE_COUNT = eXAMPLECOUNT;
	}

	public String getTASK_ID() {
		return TASK_ID;
	}

	public void setTASK_ID(String tASKID) {
		TASK_ID = tASKID;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Integer getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}

	public Integer getTASK_TYPE() {
		return TASK_TYPE;
	}

	public void setTASK_TYPE(Integer tASKTYPE) {
		TASK_TYPE = tASKTYPE;
	}

	public Integer getSUCCESS_COUNT() {
		return SUCCESS_COUNT;
	}

	public void setSUCCESS_COUNT(Integer sUCCESSCOUNT) {
		SUCCESS_COUNT = sUCCESSCOUNT;
	}

	public Integer getFAILURE_COUNT() {
		return FAILURE_COUNT;
	}

	public void setFAILURE_COUNT(Integer fAILURECOUNT) {
		FAILURE_COUNT = fAILURECOUNT;
	}

	public String getEXECUTE_RESULT() {
		return EXECUTE_RESULT;
	}

	public void setEXECUTE_RESULT(String eXECUTERESULT) {
		EXECUTE_RESULT = eXECUTERESULT;
	}

	public String getREPORT_PATH() {
		return REPORT_PATH;
	}

	public void setREPORT_PATH(String rEPORTPATH) {
		REPORT_PATH = rEPORTPATH;
	}

	public String getADD_TIME() {
		return ADD_TIME;
	}

	public void setADD_TIME(String aDDTIME) {
		ADD_TIME = aDDTIME;
	}
	
}