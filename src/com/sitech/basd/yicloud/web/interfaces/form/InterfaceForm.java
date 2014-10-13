package com.sitech.basd.yicloud.web.interfaces.form;

import java.util.List;


public class InterfaceForm {

	private int ID; // ID
	private int DEVICE_ID; // 设备主机ID
	private String DEVICENAME; // 设备主机ID名称
	private String CODE; // 设备外围编号
	private String STATUS; // 端口状态
	private String STATUSNAME; // 端口状态名称
	private String TYPE; // 外围接口类型
	private String TYPENAME; // 外围接口类型名称
	private int BELONG_NETWORKS; // 所属网络

	private List resultList = null; // 结果列表
	private List deviceList = null; // 设备主机列表作为下拉列表显示
	private List statusList = null; // 端口状态列表作为下拉列表显示
	private List typeList = null; // 外围接口类型列表作为下拉列表显示

	private int flag;// 判断是插入还是更新

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getDEVICE_ID() {
		return DEVICE_ID;
	}

	public void setDEVICE_ID(int device_id) {
		DEVICE_ID = device_id;
	}

	public String getDEVICENAME() {
		return DEVICENAME;
	}

	public void setDEVICENAME(String devicename) {
		DEVICENAME = devicename;
	}

	public String getCODE() {
		return CODE;
	}

	public void setCODE(String code) {
		CODE = code;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String status) {
		STATUS = status;
	}

	public String getSTATUSNAME() {
		return STATUSNAME;
	}

	public void setSTATUSNAME(String statusname) {
		STATUSNAME = statusname;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public String getTYPENAME() {
		return TYPENAME;
	}

	public void setTYPENAME(String typename) {
		TYPENAME = typename;
	}

	public int getBELONG_NETWORKS() {
		return BELONG_NETWORKS;
	}

	public void setBELONG_NETWORKS(int belong_networks) {
		BELONG_NETWORKS = belong_networks;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public List getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List deviceList) {
		this.deviceList = deviceList;
	}

	public List getStatusList() {
		return statusList;
	}

	public void setStatusList(List statusList) {
		this.statusList = statusList;
	}

	public List getTypeList() {
		return typeList;
	}

	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
