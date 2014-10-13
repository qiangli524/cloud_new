package com.sitech.basd.sxcloud.rsmu.domain.deploy.deployfileversion;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class DeployFileVersionObj extends BaseObj {
	private String ID; // 版本唯一标识
	private String NAME; // 版本名称
	private String APPNAME; // 版本名称
	private int APPID;// 应用编号
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
	private String SYSID; // 应用所属系统ID
	private int flag;// 增改标识
	private List fileVersionList;
	private String MD5CHECKSTR; // 版本包MD5校验串
	private String fileInfoList; //版本包中文件信息(名字，大小，updatetime)
	private String SOFTID;//软件包ID
	private String FILEPATH;//版本包中文件清单路径
	private String FILESIZE;//软件包中文件清单大小
	private String FILEUPDATETIME;//软件包中文件清单更新时间

	public String getSOFTID() {
		return SOFTID;
	}

	public void setSOFTID(String softid) {
		SOFTID = softid;
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

	public String getAPPNAME() {
		return APPNAME;
	}

	public void setAPPNAME(String appname) {
		APPNAME = appname;
	}

	public String getSTRIDS() {
		return STRIDS;
	}

	public void setSTRIDS(String strids) {
		STRIDS = strids;
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

	public String getMD5CHECKSTR() {
		return MD5CHECKSTR;
	}

	public void setMD5CHECKSTR(String md5checkstr) {
		MD5CHECKSTR = md5checkstr;
	}

	public String getFileInfoList() {
		return fileInfoList;
	}

	public void setFileInfoList(String fileInfoList) {
		this.fileInfoList = fileInfoList;
	}

	public String getFILEPATH() {
		return FILEPATH;
	}

	public void setFILEPATH(String filepath) {
		FILEPATH = filepath;
	}

	public String getFILESIZE() {
		return FILESIZE;
	}

	public void setFILESIZE(String filesize) {
		FILESIZE = filesize;
	}

	public String getFILEUPDATETIME() {
		return FILEUPDATETIME;
	}

	public void setFILEUPDATETIME(String fileupdatetime) {
		FILEUPDATETIME = fileupdatetime;
	}

}
