package com.sitech.basd.resource.domain.top;

import com.sitech.basd.common.domain.BasePrivilegeObj;

public class TopTargetObj extends BasePrivilegeObj implements Comparable<TopTargetObj> {
	private String name;
	private String ip;
	private double cpu_usage;
	private double mem_usage;
	private double store_usage;
	private String net_name;
	private String businame;
	private String entity_id;
	private String connect_uuid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public double getCpu_usage() {
		return cpu_usage;
	}

	public void setCpu_usage(double cpu_usage) {
		this.cpu_usage = cpu_usage;
	}

	public double getMem_usage() {
		return mem_usage;
	}

	public void setMem_usage(double mem_usage) {
		this.mem_usage = mem_usage;
	}

	public double getStore_usage() {
		return store_usage;
	}

	public void setStore_usage(double store_usage) {
		this.store_usage = store_usage;
	}

	public String getNet_name() {
		return net_name;
	}

	public void setNet_name(String net_name) {
		this.net_name = net_name;
	}

	public String getBusiname() {
		return businame;
	}

	public void setBusiname(String businame) {
		this.businame = businame;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getConnect_uuid() {
		return connect_uuid;
	}

	public void setConnect_uuid(String connect_uuid) {
		this.connect_uuid = connect_uuid;
	}

	@Override
	public int compareTo(TopTargetObj o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
