package com.sitech.basd.sxcloud.rsmu.web.deploy.form;

import java.util.List;



public class ExampleResumeForm{

	private int ID; // 编号
	private String NAME;	//名称
	private int HOSTID; // 主机编号
	private String CREATETIME; // 创建时间时间
	private int RESUME_FLAG; // 0：未恢复1：正在恢复2:已恢复
	private int APPID;      //应用 id
	private String IP; 	//主机名称
	private String APPNAME ;  //应用名称
	private String error_msg;
	@SuppressWarnings("unchecked")
	private List resultList;
	@SuppressWarnings("unchecked")
	private List hostList;
	@SuppressWarnings("unchecked")
	private List appList = null; //应用集合

	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public int getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(int hOSTID) {
		HOSTID = hOSTID;
	}

	public String getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(String cREATETIME) {
		CREATETIME = cREATETIME;
	}

	public int getRESUME_FLAG() {
		return RESUME_FLAG;
	}

	public void setRESUME_FLAG(int rESUMEFLAG) {
		RESUME_FLAG = rESUMEFLAG;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}

	@SuppressWarnings("unchecked")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	@SuppressWarnings("unchecked")
	public List getHostList() {
		return hostList;
	}

	@SuppressWarnings("unchecked")
	public void setHostList(List hostList) {
		this.hostList = hostList;
	}

	public int getAPPID() {
		return APPID;
	}

	public void setAPPID(int aPPID) {
		APPID = aPPID;
	}

	public String getAPPNAME() {
		return APPNAME;
	}

	public void setAPPNAME(String aPPNAME) {
		APPNAME = aPPNAME;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String errorMsg) {
		error_msg = errorMsg;
	}

	@SuppressWarnings("unchecked")
	public List getAppList() {
		return appList;
	}

	@SuppressWarnings("unchecked")
	public void setAppList(List appList) {
		this.appList = appList;
	}
	
}
