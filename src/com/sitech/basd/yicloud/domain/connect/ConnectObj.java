package com.sitech.basd.yicloud.domain.connect;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class ConnectObj extends BaseObj implements Serializable, Cloneable {

	private int ID; // ID
	private int INTERFACE_A; // A端外围接口ID
	private String INTERFACE_A_NAME; // A端外围接口名称
	private int INTERFACE_Z; // B端外围接口ID
	private String INTERFACE_Z_NAME; // B端外围接口名称
	private String STATUS; // 连通状态[连通/未连通/堵塞]ID
	private String STATUSNAME; // 连通状态[连通/未连通/堵塞]名称

	private static final long serialVersionUID = -5088107042336234427L;

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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
