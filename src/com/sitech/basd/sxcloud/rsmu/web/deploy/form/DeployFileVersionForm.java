package com.sitech.basd.sxcloud.rsmu.web.deploy.form;

import java.util.List;

public class DeployFileVersionForm {
	private String ID; // 版本唯一标识
	private String NAME;// 版本包名称
	private int APPID;// 应用编号
	private int SOFTWAREID;// 应用编号
	private String SOFRTPARTH;// 版本号的路径
	private String APPNAME;// 版本包所属应用的名称
	private double NO;// 版本号
	private String STATUS;// 版本状态
	private String LOCATION;// 版本包存放位置
	private String DESCRIBTION;// 版本描述
	private String CREATED_USER;// 创建人
	private String CREATED_TIME;// 创建时间
	private String USED_TIME;// 使用时间
	private String STRIDS;// 所选的IDS
	private String DELETE_BY;// 删除人
	private String DELETE_TIME;// 删除时间
	private String SYSID;// 系统编号
	private int flag;// 增改标识
	private List fileVersionList;
	private List appList;// 应用ID列表
	private String SOFTID;//软件包ID

	public String getSOFTID() {
		return SOFTID;
	}

	public void setSOFTID(String softid) {
		SOFTID = softid;
	}

	public String getSTRIDS() {
		return STRIDS;
	}

	public void setSTRIDS(String strids) {
		STRIDS = strids;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public int getAPPID() {
		return APPID;
	}

	public void setAPPID(int appid) {
		APPID = appid;
	}

	public double getNO() {
		return NO;
	}

	public void setNO(double no) {
		NO = no;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String status) {
		STATUS = status;
	}

	public String getLOCATION() {
		return LOCATION;
	}

	public void setLOCATION(String location) {
		LOCATION = location;
	}

	public String getDESCRIBTION() {
		return DESCRIBTION;
	}

	public void setDESCRIBTION(String describtion) {
		DESCRIBTION = describtion;
	}

	public String getCREATED_USER() {
		return CREATED_USER;
	}

	public void setCREATED_USER(String created_user) {
		CREATED_USER = created_user;
	}

	public String getCREATED_TIME() {
		return CREATED_TIME;
	}

	public void setCREATED_TIME(String created_time) {
		CREATED_TIME = created_time;
	}

	public String getUSED_TIME() {
		return USED_TIME;
	}

	public void setUSED_TIME(String used_time) {
		USED_TIME = used_time;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public List getFileVersionList() {
		return fileVersionList;
	}

	public void setFileVersionList(List fileVersionList) {
		this.fileVersionList = fileVersionList;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
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

	public int getSOFTWAREID() {
		return SOFTWAREID;
	}

	public void setSOFTWAREID(int softwareid) {
		SOFTWAREID = softwareid;
	}

	public String getSOFRTPARTH() {
		return SOFRTPARTH;
	}

	public void setSOFRTPARTH(String sofrtparth) {
		SOFRTPARTH = sofrtparth;
	}

	public String getDELETE_BY() {
		return DELETE_BY;
	}

	public void setDELETE_BY(String delete_by) {
		DELETE_BY = delete_by;
	}

	public String getDELETE_TIME() {
		return DELETE_TIME;
	}

	public void setDELETE_TIME(String delete_time) {
		DELETE_TIME = delete_time;
	}

	public String getSYSID() {
		return SYSID;
	}

	public void setSYSID(String sysid) {
		SYSID = sysid;
	}

}
