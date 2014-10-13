package com.sitech.ssd.ah.boss.domain.monitor;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>
 * Title: MonitorObj
 * </p>
 * <p>
 * Description: boss进程监控实体
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-8-4 下午4:58:38
 * 
 */
public class BossMonitorObj extends BaseObj {
	private String uid;// 物理主键
	private String cluster_id;// 集群节点名
	private String app_pool;// 程序池标识
	private String host_ip;// 主机的ip地址
	private String user_uid;// 主机用户ID
	private String install_status;// 安装状态：1安装完成，2已卸载
	private String nodename;// 节点别名(这里专指：程序池名)
	private String program_name;// 进程名
	private String note;// 备注
	private Integer running_port;// 进程占用端口
	private Integer status;// 状态：1主，2备，3未运行，4操作中，5操作完成
	private String operator_id;// 0允许启动，1禁止启动
	private String startScript;// 启动脚本
	private String stopScript;// 停止脚本
	// 另添
	private String cluster_name;
	private List cluster_ids;
	private List app_pools;
	private List host_ips;
	private String username;// 主机的用户名
	private String password;// 密码

	public String getCluster_name() {
		return cluster_name;
	}

	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
	}

	public String getStartScript() {
		return startScript;
	}

	public void setStartScript(String startScript) {
		this.startScript = startScript;
	}

	public String getStopScript() {
		return stopScript;
	}

	public void setStopScript(String stopScript) {
		this.stopScript = stopScript;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public String getProgram_name() {
		return program_name;
	}

	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCluster_id() {
		return cluster_id;
	}

	public void setCluster_id(String cluster_id) {
		this.cluster_id = cluster_id;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public String getApp_pool() {
		return app_pool;
	}

	public void setApp_pool(String app_pool) {
		this.app_pool = app_pool;
	}

	public String getHost_ip() {
		return host_ip;
	}

	public void setHost_ip(String host_ip) {
		this.host_ip = host_ip;
	}

	public String getUser_uid() {
		return user_uid;
	}

	public void setUser_uid(String user_uid) {
		this.user_uid = user_uid;
	}

	public String getInstall_status() {
		return install_status;
	}

	public void setInstall_status(String install_status) {
		this.install_status = install_status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getRunning_port() {
		return running_port;
	}

	public void setRunning_port(Integer running_port) {
		this.running_port = running_port;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List getHost_ips() {
		return host_ips;
	}

	public void setHost_ips(List host_ips) {
		this.host_ips = host_ips;
	}

	public List getCluster_ids() {
		return cluster_ids;
	}

	public void setCluster_ids(List cluster_ids) {
		this.cluster_ids = cluster_ids;
	}

	public List getApp_pools() {
		return app_pools;
	}

	public void setApp_pools(List app_pools) {
		this.app_pools = app_pools;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}