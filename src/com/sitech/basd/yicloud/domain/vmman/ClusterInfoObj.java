package com.sitech.basd.yicloud.domain.vmman;

public class ClusterInfoObj {
	private int ID;
	private String NAME;
	private String hastate;// 集群HA状态
	private String drsstate;// 集群DRS状态
	private String c_uuid;

	public String getC_uuid() {
		return c_uuid;
	}

	public void setC_uuid(String c_uuid) {
		this.c_uuid = c_uuid;
	}

	public String getHastate() {
		return hastate;
	}

	public void setHastate(String hastate) {
		this.hastate = hastate;
	}

	public String getDrsstate() {
		return drsstate;
	}

	public void setDrsstate(String drsstate) {
		this.drsstate = drsstate;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

}
