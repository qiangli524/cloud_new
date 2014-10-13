package com.sitech.basd.sxcloud.rsmu.web.deploy.form;

import java.util.List;

@SuppressWarnings("serial")
public class DeployGeneralForm {

	/*
	 * 部署个性化配置
	 */
	private int ID; // '编号',
	private int APPID; // '应用编号',
	private int HOSTID; // '主机编号',
	private String TARGET; // '目标字符串',
	private String REPLACEMENT; // '替换字符串',
	private String UPDATETIME; // '更新时间'
	private String start_time;// 时间 开始
	private String end_time; // 时间 结束
	private String APPNAME; // 应用名称
	private String HOSTNAME; // 主机名称
	private List resultList = null;
	private List appList = null;
	private List hostList = null;

	public int getAPPID() {
		return APPID;
	}

	public void setAPPID(int appid) {
		APPID = appid;
	}

	public List getAppList() {
		return appList;
	}

	public void setAppList(List appList) {
		this.appList = appList;
	}

	public String getAPPNAME() {
		return APPNAME;
	}

	public void setAPPNAME(String appname) {
		APPNAME = appname;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(int hostid) {
		HOSTID = hostid;
	}

	public List getHostList() {
		return hostList;
	}

	public void setHostList(List hostList) {
		this.hostList = hostList;
	}

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getREPLACEMENT() {
		return REPLACEMENT;
	}

	public void setREPLACEMENT(String replacement) {
		REPLACEMENT = replacement;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getTARGET() {
		return TARGET;
	}

	public void setTARGET(String target) {
		TARGET = target;
	}

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
	}

	/*
	 * 清空ActionForm
	 */
}
