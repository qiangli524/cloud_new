package domain.service;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class NodeManagerObj extends BaseObj {
	private String id;
	private String rack;
	private String node_state;
	private String node_address;
	private String visit_address;
	private String health_status;
	private String last_health_update;
	private String health_report;
	private Integer containers_num;
	private Double used_mem;
	private Double available_mem;
	private String host_id;
	private String cluster_id;
	private String host_ip;

	public String getHost_ip() {
		return host_ip;
	}

	public void setHost_ip(String host_ip) {
		this.host_ip = host_ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRack() {
		return rack;
	}

	public void setRack(String rack) {
		this.rack = rack;
	}

	public String getNode_state() {
		return node_state;
	}

	public void setNode_state(String node_state) {
		this.node_state = node_state;
	}

	public String getNode_address() {
		return node_address;
	}

	public void setNode_address(String node_address) {
		this.node_address = node_address;
	}

	public String getVisit_address() {
		return visit_address;
	}

	public void setVisit_address(String visit_address) {
		this.visit_address = visit_address;
	}

	public String getHealth_status() {
		return health_status;
	}

	public void setHealth_status(String health_status) {
		this.health_status = health_status;
	}

	public String getLast_health_update() {
		return last_health_update;
	}

	public void setLast_health_update(String last_health_update) {
		this.last_health_update = last_health_update;
	}

	public String getHealth_report() {
		return health_report;
	}

	public void setHealth_report(String health_report) {
		this.health_report = health_report;
	}

	public Integer getContainers_num() {
		return containers_num;
	}

	public void setContainers_num(Integer containers_num) {
		this.containers_num = containers_num;
	}

	public Double getUsed_mem() {
		return used_mem;
	}

	public void setUsed_mem(Double used_mem) {
		this.used_mem = used_mem;
	}

	public Double getAvailable_mem() {
		return available_mem;
	}

	public void setAvailable_mem(Double available_mem) {
		this.available_mem = available_mem;
	}

	public String getHost_id() {
		return host_id;
	}

	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}

	public String getCluster_id() {
		return cluster_id;
	}

	public void setCluster_id(String cluster_id) {
		this.cluster_id = cluster_id;
	}

}
