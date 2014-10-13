package com.sitech.basd.resource.domain.host;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class IPMIInfo extends BaseObj implements Serializable, Cloneable {
	/** serialVersionUID */
	private static final long serialVersionUID = 8345491929844299845L;
	private String id;
	private String ip;
	private String version = "IPMIVERSIONV20";// ipmi版本，1.5，2.0
	private String user;// IPMI管理用户
	private String pwd;// IPMI用户密码

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
