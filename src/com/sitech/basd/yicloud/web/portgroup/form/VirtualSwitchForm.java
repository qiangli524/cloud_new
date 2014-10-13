package com.sitech.basd.yicloud.web.portgroup.form;

import java.util.List;

public class VirtualSwitchForm {
	private int id;
	private String name;
	private int numPorts;
	private int numPortSavailable;
	private String hostName;
	private List resultList;
	private List hostList;
	private String host;
	private String nic;
	private String vlan;
	private String ip_type;
	private String ip;
	private String sub_net;
	private String vm_kernel;

	public String getIp_type() {
		return ip_type;
	}

	public void setIp_type(String ip_type) {
		this.ip_type = ip_type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSub_net() {
		return sub_net;
	}

	public void setSub_net(String sub_net) {
		this.sub_net = sub_net;
	}

	public String getVm_kernel() {
		return vm_kernel;
	}

	public void setVm_kernel(String vm_kernel) {
		this.vm_kernel = vm_kernel;
	}

	public String getVlan() {
		return vlan;
	}

	public void setVlan(String vlan) {
		this.vlan = vlan;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public List getHostList() {
		return hostList;
	}

	public void setHostList(List hostList) {
		this.hostList = hostList;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
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

	public int getNumPorts() {
		return numPorts;
	}

	public void setNumPorts(int numPorts) {
		this.numPorts = numPorts;
	}

	public int getNumPortSavailable() {
		return numPortSavailable;
	}

	public void setNumPortSavailable(int numPortSavailable) {
		this.numPortSavailable = numPortSavailable;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
}
