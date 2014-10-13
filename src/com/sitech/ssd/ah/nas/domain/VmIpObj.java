package com.sitech.ssd.ah.nas.domain;

public class VmIpObj {

	private String id;//唯一标示
	private String vm_uuid;//虚拟机uuid
	private String ip;//虚拟机IP地址
	private String connectid;//连接池ID
	private String vtype;//虚拟化类型1、vmware2、xen
	private String ipType;//ip类型，1、内网(私网)2、外网（公网）  （可根据需求添加）
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVm_uuid() {
		return vm_uuid;
	}
	public void setVm_uuid(String vm_uuid) {
		this.vm_uuid = vm_uuid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getConnectid() {
		return connectid;
	}
	public void setConnectid(String connectid) {
		this.connectid = connectid;
	}
	public String getVtype() {
		return vtype;
	}
	public void setVtype(String vtype) {
		this.vtype = vtype;
	}
	public String getIpType() {
		return ipType;
	}
	public void setIpType(String ipType) {
		this.ipType = ipType;
	}
	
}
