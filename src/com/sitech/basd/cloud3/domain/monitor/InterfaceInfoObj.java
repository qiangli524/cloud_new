package com.sitech.basd.cloud3.domain.monitor;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class InterfaceInfoObj extends BaseObj {
	private String id;
	private String inter_type;
	private String inter_ip;
	private String inter_port;
	private String inter_url;
	private String inter_state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInter_type() {
		return inter_type;
	}

	public void setInter_type(String inter_type) {
		this.inter_type = inter_type;
	}

	public String getInter_ip() {
		return inter_ip;
	}

	public void setInter_ip(String inter_ip) {
		this.inter_ip = inter_ip;
	}

	public String getInter_port() {
		return inter_port;
	}

	public void setInter_port(String inter_port) {
		this.inter_port = inter_port;
	}

	public String getInter_url() {
		return inter_url;
	}

	public void setInter_url(String inter_url) {
		this.inter_url = inter_url;
	}

	public String getInter_state() {
		return inter_state;
	}

	public void setInter_state(String inter_state) {
		this.inter_state = inter_state;
	}

}
