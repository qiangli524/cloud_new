package com.sitech.ssd.ah.paas.domain.host;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: PaasHostInfoObj
 * </p>
 * <p>
 * Description: Paas主机表实体类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-4-2 下午5:03:15
 * 
 */
public class PaasHostInfoObj extends BaseObj {
	private String id;// 物理主键
	private String host_name;// 主机名称用于监控
	private String host_type;// 主机类型 0物理机 2虚拟机
	private String service_type;// 服务器类型 0x86服务器 1机架服务器 2vmware虚拟机
								// 3xen虚拟机4kvm虚拟机 5其他
	private String os;// 操作系统
	private Double mem_size;// 内存大小
	private Double local_disk_size;// 本地存储总大小
	private Double swap_size;// 交换区大小
	private String ip;// IP地址
	private Integer cpu_num;// cpu个数
	private String cluster_id;// 集群id
	private String cluster_name;// 集群名称
	private String label_name;// 主机名字
	private Integer status;// 主机状态
	private int state;// 节点状态

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public String getHost_type() {
		return host_type;
	}
	public void setHost_type(String host_type) {
		this.host_type = host_type;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public Double getMem_size() {
		return mem_size;
	}
	public void setMem_size(Double mem_size) {
		this.mem_size = mem_size;
	}
	public Double getLocal_disk_size() {
		return local_disk_size;
	}
	public void setLocal_disk_size(Double local_disk_size) {
		this.local_disk_size = local_disk_size;
	}
	public Double getSwap_size() {
		return swap_size;
	}
	public void setSwap_size(Double swap_size) {
		this.swap_size = swap_size;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getCpu_num() {
		return cpu_num;
	}
	public void setCpu_num(Integer cpu_num) {
		this.cpu_num = cpu_num;
	}
	public String getCluster_id() {
		return cluster_id;
	}
	public void setCluster_id(String cluster_id) {
		this.cluster_id = cluster_id;
	}
	public String getCluster_name() {
		return cluster_name;
	}
	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
	}

	public String getLabel_name() {
		return label_name;
	}

	public void setLabel_name(String label_name) {
		this.label_name = label_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	

}
