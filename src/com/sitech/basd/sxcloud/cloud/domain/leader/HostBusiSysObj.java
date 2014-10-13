package com.sitech.basd.sxcloud.cloud.domain.leader;

import java.io.Serializable;

public class HostBusiSysObj implements Cloneable, Serializable {

	
	private String app_id;
	private String app_name;
	private String app_desc;
	private String process;
	private String mem;
	
	private String host_id;
	private String host_ip;
	private String eq_id;
	
	private String sys_id;
	private String sys_name;
	public String getApp_desc() {
		return app_desc;
	}
	public void setApp_desc(String app_desc) {
		this.app_desc = app_desc;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getEq_id() {
		return eq_id;
	}
	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}
	public String getHost_id() {
		return host_id;
	}
	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}
	public String getHost_ip() {
		return host_ip;
	}
	public void setHost_ip(String host_ip) {
		this.host_ip = host_ip;
	}
	public String getMem() {
		return mem;
	}
	public void setMem(String mem) {
		this.mem = mem;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getSys_id() {
		return sys_id;
	}
	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}
	public String getSys_name() {
		return sys_name;
	}
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	
	
}
