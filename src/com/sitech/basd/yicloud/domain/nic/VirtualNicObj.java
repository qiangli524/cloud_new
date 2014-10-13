package com.sitech.basd.yicloud.domain.nic;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class VirtualNicObj extends BaseObj{
	private int id;
	private String name;
	private String ipAddress;
	private String uuid;
	private String nicName;
	private String portgroupName;
	
	public String getPortgroupName() {
		return portgroupName;
	}
	public void setPortgroupName(String portgroupName) {
		this.portgroupName = portgroupName;
	}
	public String getNicName() {
		return nicName;
	}
	public void setNicName(String nicName) {
		this.nicName = nicName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
