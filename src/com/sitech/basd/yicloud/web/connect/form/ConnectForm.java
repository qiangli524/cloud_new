package com.sitech.basd.yicloud.web.connect.form;

import java.util.List;

public class ConnectForm  {

	private int ID; // ID
	private int INTERFACE_A; // A端外围接口ID
	private String INTERFACE_A_NAME; // A端外围接口名称
	private int INTERFACE_Z; // B端外围接口ID
	private String INTERFACE_Z_NAME; // B端外围接口名称
	private String STATUS; // 连通状态[连通/未连通/堵塞]ID
	private String STATUSNAME; // 连通状态[连通/未连通/堵塞]名称

	private List resultList = null; // 结果列表
	private List interfaceList = null; // A，Z端外围接口下拉列表显示
	private List connectstatusList = null; // 连通状态列表作为下拉列表显示

	private int flag;// 判断是插入还是更新

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getINTERFACE_A() {
		return INTERFACE_A;
	}

	public void setINTERFACE_A(int interface_a) {
		INTERFACE_A = interface_a;
	}

	public String getINTERFACE_A_NAME() {
		return INTERFACE_A_NAME;
	}

	public void setINTERFACE_A_NAME(String interface_a_name) {
		INTERFACE_A_NAME = interface_a_name;
	}

	public int getINTERFACE_Z() {
		return INTERFACE_Z;
	}

	public void setINTERFACE_Z(int interface_z) {
		INTERFACE_Z = interface_z;
	}

	public String getINTERFACE_Z_NAME() {
		return INTERFACE_Z_NAME;
	}

	public void setINTERFACE_Z_NAME(String interface_z_name) {
		INTERFACE_Z_NAME = interface_z_name;
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

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public List getInterfaceList() {
		return interfaceList;
	}

	public void setInterfaceList(List interfaceList) {
		this.interfaceList = interfaceList;
	}

	public List getConnectstatusList() {
		return connectstatusList;
	}

	public void setConnectstatusList(List connectstatusList) {
		this.connectstatusList = connectstatusList;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
