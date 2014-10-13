package com.sitech.basd.resource.domain.global;

import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;

public class HostGlobalObj extends BasePrivilegeObj {
	private String id;
	private String host_uuid; // 主机唯一标示
	private String cluster_uuid; // 集群唯一标示
	private String virtual_type; // 虚拟化类型
	private String connect_uuid; // 连接唯一标示
	private String cup_mainFre; // cpu主频
	private String cpu_num; // cpu数量
	private String cpu_core; // cpu核数
	private String mem_all; // 内存总的大小
	private String store_all; // 本地存储总的大小
	private String vitual_num; // 虚拟机个数
	private String vCpu_visible; // 可虚拟化vcpu
	private String vCpu_distribute; // 可分配的vcpu
	private String cpu_usage; // cpu使用率
	private String mem_useage;// 内存使用率
	private String store_usage;// 存储使用率
	private String network_domain;// 网络域
	private String center_uuid;// 数据中心
	private String eq_name;// 主机名称
	private String eq_ip;// ip

	private List<String> uuidList;//

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHost_uuid() {
		return host_uuid;
	}

	public void setHost_uuid(String host_uuid) {
		this.host_uuid = host_uuid;
	}

	public String getCluster_uuid() {
		return cluster_uuid;
	}

	public void setCluster_uuid(String cluster_uuid) {
		this.cluster_uuid = cluster_uuid;
	}

	public String getVirtual_type() {
		return virtual_type;
	}

	public void setVirtual_type(String virtual_type) {
		this.virtual_type = virtual_type;
	}

	public String getConnect_uuid() {
		return connect_uuid;
	}

	public void setConnect_uuid(String connect_uuid) {
		this.connect_uuid = connect_uuid;
	}

	public String getCup_mainFre() {
		return cup_mainFre;
	}

	public void setCup_mainFre(String cup_mainFre) {
		this.cup_mainFre = cup_mainFre;
	}

	public String getCpu_num() {
		return cpu_num;
	}

	public void setCpu_num(String cpu_num) {
		this.cpu_num = cpu_num;
	}

	public String getCpu_core() {
		return cpu_core;
	}

	public void setCpu_core(String cpu_core) {
		this.cpu_core = cpu_core;
	}

	public String getMem_all() {
		return mem_all;
	}

	public void setMem_all(String mem_all) {
		this.mem_all = mem_all;
	}

	public String getStore_all() {
		return store_all;
	}

	public void setStore_all(String store_all) {
		this.store_all = store_all;
	}

	public String getVitual_num() {
		return vitual_num;
	}

	public void setVitual_num(String vitual_num) {
		this.vitual_num = vitual_num;
	}

	public String getvCpu_visible() {
		return vCpu_visible;
	}

	public void setvCpu_visible(String vCpu_visible) {
		this.vCpu_visible = vCpu_visible;
	}

	public String getvCpu_distribute() {
		return vCpu_distribute;
	}

	public void setvCpu_distribute(String vCpu_distribute) {
		this.vCpu_distribute = vCpu_distribute;
	}

	public String getCpu_usage() {
		return cpu_usage;
	}

	public void setCpu_usage(String cpu_usage) {
		this.cpu_usage = cpu_usage;
	}

	public String getMem_useage() {
		return mem_useage;
	}

	public void setMem_useage(String mem_useage) {
		this.mem_useage = mem_useage;
	}

	public String getStore_usage() {
		return store_usage;
	}

	public void setStore_usage(String store_usage) {
		this.store_usage = store_usage;
	}

	public String getNetwork_domain() {
		return network_domain;
	}

	public void setNetwork_domain(String network_domain) {
		this.network_domain = network_domain;
	}

	public String getEq_name() {
		return eq_name;
	}

	public void setEq_name(String eq_name) {
		this.eq_name = eq_name;
	}

	public String getEq_ip() {
		return eq_ip;
	}

	public void setEq_ip(String eq_ip) {
		this.eq_ip = eq_ip;
	}

	public String getCenter_uuid() {
		return center_uuid;
	}

	public void setCenter_uuid(String center_uuid) {
		this.center_uuid = center_uuid;
	}

	public List<String> getUuidList() {
		return uuidList;
	}

	public void setUuidList(List<String> uuidList) {
		this.uuidList = uuidList;
	}

}
