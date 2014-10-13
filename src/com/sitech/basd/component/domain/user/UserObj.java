package com.sitech.basd.component.domain.user;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class UserObj extends BaseObj {
	private String id;
	private String ip;
	private String username;
	private String password;
	private String mac;
	private String insert_time;
	private String update_time;
	private String example_name;// 部署实例名称
	private String example_id;// 部署实例的序列id
	private String type;// 0普通用户，1管理员用户，2信息关系用户；默认为普通用户
	private String purpose;// 用户用途
	private Integer hostport;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExample_name() {
		return example_name;
	}

	public void setExample_name(String example_name) {
		this.example_name = example_name;
	}

	public String getExample_id() {
		return example_id;
	}

	public void setExample_id(String example_id) {
		this.example_id = example_id;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 *
	 * @return the hostport
	 */
	public Integer getHostport() {
		return hostport;
	}

	/**
	 *
	 * @param hostport the hostport to set
	 */
	public void setHostport(Integer hostport) {
		this.hostport = hostport;
	}

	
}
