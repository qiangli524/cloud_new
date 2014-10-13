package com.sitech.basd.sxcloud.rsmu.web.deploy.form;

import java.util.List;

@SuppressWarnings("serial")
public class DeployConfigForm {

	private int ID; // '编号',
	private int HOSTID; // '主机编号',
	private int APPID; // '应用编号',
	private int HOSTCONFIG; // '主机配置编号',
	private String UPDATEUSER; // '更新者',
	private String UPDATETIME; // '更新时间',
	private String REMARK; // '备注',

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

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getHOSTCONFIG() {
		return HOSTCONFIG;
	}

	public void setHOSTCONFIG(int hostconfig) {
		HOSTCONFIG = hostconfig;
	}

	public int getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(int hostid) {
		HOSTID = hostid;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
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

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
	}

	public String getUPDATEUSER() {
		return UPDATEUSER;
	}

	public void setUPDATEUSER(String updateuser) {
		UPDATEUSER = updateuser;
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
	/*
	 * 清空ActionForm
	 */
}
