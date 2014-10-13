package com.sitech.basd.sxcloud.rsmu.web.upgrade.form;

import java.util.List;

@SuppressWarnings("serial")
public class UpgradeExampleResumeForm {

	private int ID; // 编号
	private String NAME; // 名称
	private int HOSTID; // 主机编号
	private String CREATETIME; // 创建时间时间
	private int RESUME_FLAG; // 0：未恢复1：正在恢复2:已恢复
	private int APPID; // 应用 id
	private String IP; // 主机名称
	private String APPNAME; // 应用名称
	private String error_msg;
	private List resultList;
	private List hostList;
	private List appList = null; // 应用集合

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

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public List getHostList() {
		return hostList;
	}

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

	public List getAppList() {
		return appList;
	}

	public void setAppList(List appList) {
		this.appList = appList;
	}

	/*
	 * 清空ActionForm
	 */

}
