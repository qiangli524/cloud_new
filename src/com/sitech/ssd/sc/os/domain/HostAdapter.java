package com.sitech.ssd.sc.os.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
  * @Title: 主机网卡信息
  * @Description:
  * 
  * @Copyight: Copyright (c) 2014
  * @Company: SI-Tech
  * @Author: JamTau
  * @Date 2014-7-9 下午05:24:26
 */
public class HostAdapter extends BaseObj {
	
	private String id;//主键ID
	private String os_host_id;//主机ID
    private String nic_order;//网卡序号     
	private String mac;//MAC地址  
	private String ip_address;//IP地址
	private String subnet_mask;//子网掩码        
	private String default_gateway;//默认网关      
	private String prederred_dns;//首选DNS
	private String alternate_dns;//备用DNS        
	private String nic_type;//网卡类型       
	private String purpose;//用途
	
	public HostAdapter(){}
	
	public HostAdapter(String os_host_id){
		this.os_host_id = os_host_id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOs_host_id() {
		return os_host_id;
	}
	public void setOs_host_id(String os_host_id) {
		this.os_host_id = os_host_id;
	}
	public String getNic_order() {
		return nic_order;
	}
	public void setNic_order(String nic_order) {
		this.nic_order = nic_order;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getSubnet_mask() {
		return subnet_mask;
	}
	public void setSubnet_mask(String subnet_mask) {
		this.subnet_mask = subnet_mask;
	}
	public String getDefault_gateway() {
		return default_gateway;
	}
	public void setDefault_gateway(String default_gateway) {
		this.default_gateway = default_gateway;
	}
	public String getPrederred_dns() {
		return prederred_dns;
	}

	public void setPrederred_dns(String prederred_dns) {
		this.prederred_dns = prederred_dns;
	}

	public String getAlternate_dns() {
		return alternate_dns;
	}
	public void setAlternate_dns(String alternate_dns) {
		this.alternate_dns = alternate_dns;
	}
	public String getNic_type() {
		return nic_type;
	}
	public void setNic_type(String nic_type) {
		this.nic_type = nic_type;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
}