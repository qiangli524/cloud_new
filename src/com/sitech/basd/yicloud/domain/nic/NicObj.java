package com.sitech.basd.yicloud.domain.nic;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class NicObj extends BaseObj{
	private int id;
	private String name;
	private String linkduplex;
	private int linkspeed;
	private String status;
	private String uuid;
	private String mac;
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
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
	public String getLinkduplex() {
		return linkduplex;
	}
	public void setLinkduplex(String linkduplex) {
		this.linkduplex = linkduplex;
	}
	public int getLinkspeed() {
		return linkspeed;
	}
	public void setLinkspeed(int linkspeed) {
		this.linkspeed = linkspeed;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
