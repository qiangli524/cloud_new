package com.sitech.ssd.ah.busiMonitor.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class UserManageObj extends BaseObj {
	private String id;
	private String userName;
	private String ip;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

}
