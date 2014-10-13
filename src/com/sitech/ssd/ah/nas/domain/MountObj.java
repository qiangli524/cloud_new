package com.sitech.ssd.ah.nas.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class MountObj extends BaseObj{

	private String mountName;//挂载nas存储的实体
	private String ip;//业务地址
	private String nasIp;
	private String busi;//挂载实体所属业务
	private String type;//实体类型
	
	
	public String getMountName() {
		return mountName;
	}
	public void setMountName(String mountName) {
		this.mountName = mountName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getNasIp() {
		return nasIp;
	}
	public void setNasIp(String nasIp) {
		this.nasIp = nasIp;
	}
	public String getBusi() {
		return busi;
	}
	public void setBusi(String busi) {
		this.busi = busi;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
