package com.sitech.basd.sxcloud.rsmu.web.upgrade.form;

import java.util.List;

@SuppressWarnings("serial")
public class UpgradeExampleForm {

	private int ID; // 编号
	private int HOSTID; // 主机编号
	private String UPGRADETIME; // 升级时间
	private int UPGRADE_FLAG; // 0：未升级1：已升级
	private int START_STOP_FLAG; // 0：停止 1：启动
	private String UPDATETIME; // 启停时间
	private int APPID; // 应用 id
	private String HOSTNAME;
	private String APPNAME; // 应用名称
	private String error_msg;
	private List resultList;
	private List hostList;
	private List appList = null; // 应用集合

	private int APPPORT; // 应用端口号
	private String KEYNAME; // 中间键名称
	private List protList; // 端口集合

	public List getHostList() {
		return hostList;
	}

	public void setHostList(List hostList) {
		this.hostList = hostList;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(int hostid) {
		HOSTID = hostid;
	}

	public int getUPGRADE_FLAG() {
		return UPGRADE_FLAG;
	}

	public void setUPGRADE_FLAG(int upgrade_flag) {
		UPGRADE_FLAG = upgrade_flag;
	}

	public int getSTART_STOP_FLAG() {
		return START_STOP_FLAG;
	}

	public void setSTART_STOP_FLAG(int start_stop_flag) {
		START_STOP_FLAG = start_stop_flag;
	}

	public String getUPGRADETIME() {
		return UPGRADETIME;
	}

	public void setUPGRADETIME(String upgradetime) {
		UPGRADETIME = upgradetime;
	}

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}

	/*
	 * 清空ActionForm
	 */

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public int getAPPID() {
		return APPID;
	}

	public void setAPPID(int appid) {
		APPID = appid;
	}

	public String getAPPNAME() {
		return APPNAME;
	}

	public void setAPPNAME(String appname) {
		APPNAME = appname;
	}

	public List getAppList() {
		return appList;
	}

	public void setAppList(List appList) {
		this.appList = appList;
	}

	public int getAPPPORT() {
		return APPPORT;
	}

	public void setAPPPORT(int aPPPORT) {
		APPPORT = aPPPORT;
	}

	public String getKEYNAME() {
		return KEYNAME;
	}

	public void setKEYNAME(String kEYNAME) {
		KEYNAME = kEYNAME;
	}

	public List getProtList() {
		return protList;
	}

	public void setProtList(List protList) {
		this.protList = protList;
	}

}
