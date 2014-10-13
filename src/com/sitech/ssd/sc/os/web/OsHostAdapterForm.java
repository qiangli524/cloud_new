package com.sitech.ssd.sc.os.web;

import java.io.Serializable;
import java.util.List;

import com.sitech.ssd.sc.os.domain.HostAdapter;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;

public class OsHostAdapterForm implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
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
	
	private String template_id;//模版编号
	private List<HostAdapter> adapterList;
	private List<OsTemplate> templList;
	
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
	public List<HostAdapter> getAdapterList() {
		return adapterList;
	}
	public void setAdapterList(List<HostAdapter> adapterList) {
		this.adapterList = adapterList;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public List<OsTemplate> getTemplList() {
		return templList;
	}
	public void setTemplList(List<OsTemplate> templList) {
		this.templList = templList;
	}

}
