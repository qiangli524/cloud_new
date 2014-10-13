package com.sitech.basd.sxcloud.rsmu.domain.cloudschedu;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class RealServerObj extends BaseObj implements Serializable, Cloneable {
	private int id; // *编号唯一标识
	private String virtualAddress; // 虚拟服务器ID  //virtualAddress
	private String name; // 真实服务器名字
	private String realAddress; // 真实ip地址和端口 //realAddress
	private String weight; // 标识该真实服务器权重
	private String forwarding_method; //
	private String request; // 该真实服务器健康检查请求内容，如index.html
	private String receive; // 该真实服务器健康检查响应内容，如OK
	private String optr_id;
	private String opt_time;
	private String virtual_name;
	private String INFO;

	public String getVirtual_name() {
		return virtual_name;
	}

	public void setVirtual_name(String virtual_name) {
		this.virtual_name = virtual_name;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getForwarding_method() {
		return forwarding_method;
	}

	public void setForwarding_method(String forwarding_method) {
		this.forwarding_method = forwarding_method;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public String getOptr_id() {
		return optr_id;
	}

	public void setOptr_id(String optr_id) {
		this.optr_id = optr_id;
	}

	public String getOpt_time() {
		return opt_time;
	}

	public void setOpt_time(String opt_time) {
		this.opt_time = opt_time;
	}

	public String getRealAddress() {
		return realAddress;
	}

	public void setRealAddress(String realAddress) {
		this.realAddress = realAddress;
	}

	public String getVirtualAddress() {
		return virtualAddress;
	}

	public void setVirtualAddress(String virtualAddress) {
		this.virtualAddress = virtualAddress;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getINFO() {
		return INFO;
	}

	public void setINFO(String info) {
		INFO = info;
	}

}
