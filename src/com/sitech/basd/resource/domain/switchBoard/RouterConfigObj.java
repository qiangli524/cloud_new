package com.sitech.basd.resource.domain.switchBoard;

public class RouterConfigObj {
	private String source_ip;// 源IP地址
	private String subnet_mask;// 子网掩码
	private String ip;// 目标IP地址

	public String getSource_ip() {
		return source_ip;
	}

	public void setSource_ip(String source_ip) {
		this.source_ip = source_ip;
	}

	public String getSubnet_mask() {
		return subnet_mask;
	}

	public void setSubnet_mask(String subnet_mask) {
		this.subnet_mask = subnet_mask;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
