package com.sitech.basd.envmanager.domain.condevice;

public class VirtualObj {
	
	private int ip_id; // NUMBER,--ID
	private String VIRTUAL_IP ; //VARCHAR2(256),--虚拟IP地址
	private String DEPART ; //VARCHAR2(256),--使用部门
	
	private int device_id;

	public int getIp_id() {
		return ip_id;
	}

	public void setIp_id(int ip_id) {
		this.ip_id = ip_id;
	}

	public String getVIRTUAL_IP() {
		return VIRTUAL_IP;
	}

	public void setVIRTUAL_IP(String virtual_ip) {
		VIRTUAL_IP = virtual_ip;
	}

	public String getDEPART() {
		return DEPART;
	}

	public void setDEPART(String depart) {
		DEPART = depart;
	}

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}


	

}
