package com.sitech.basd.resource.domain.global;

import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;
import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class VmGlobalObj extends BasePrivilegeObj {
	private String id;
	private String host_uuid; // 主机唯一标示
	private String cluster_uuid; // 集群唯一标示
	private String virtual_type; // 虚拟化类型
	private String connect_uuid; // 连接唯一标示
	private String project_id; // 项目编号
	private String user_id; // 人员id
	private String business_id;// 业务系统id
	private String start_time;// 开始时间（供资源日历使用）
	private String end_time;// 结束时间（供资源日历使用）
	private String cpu_usage;
	private String mem_usage;
	private String store_usage;
	private String vm_uuid;
	private String center_uuid;

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

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getCpu_usage() {
		return cpu_usage;
	}

	public void setCpu_usage(String cpu_usage) {
		this.cpu_usage = cpu_usage;
	}

	public String getMem_usage() {
		return mem_usage;
	}

	public void setMem_usage(String mem_usage) {
		this.mem_usage = mem_usage;
	}

	public String getStore_usage() {
		return store_usage;
	}

	public void setStore_usage(String store_usage) {
		this.store_usage = store_usage;
	}

	public List<String> getUuidList() {
		return uuidList;
	}

	public void setUuidList(List<String> uuidList) {
		this.uuidList = uuidList;
	}

	public String getVm_uuid() {
		return vm_uuid;
	}

	public void setVm_uuid(String vm_uuid) {
		this.vm_uuid = vm_uuid;
	}

	public String getCenter_uuid() {
		return center_uuid;
	}

	public void setCenter_uuid(String center_uuid) {
		this.center_uuid = center_uuid;
	}
}
