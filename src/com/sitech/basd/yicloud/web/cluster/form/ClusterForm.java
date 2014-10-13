package com.sitech.basd.yicloud.web.cluster.form;

import java.util.List;

public class ClusterForm {
	private String name;// 集群名称
	private String type;// 集群类型
	private String hastate;// 集群HA状态
	private String drsstate;// 集群DRS状态
	private List resultList;
	private List forSelectList;
	private List vmList;
	private List groupList;
	private String host_monitor;// 主机监控
	private String state;// 接入控制
	private String admission;// 接入控制策略
	private String num;// 故障主机数目
	private String cpu;// 预留cpu
	private String mem;// 预留内存
	private String host;// 指定故障切换主机
	private String level;// 自动化级别
	private String priority;// 应用优先级

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMem() {
		return mem;
	}

	public void setMem(String mem) {
		this.mem = mem;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHost_monitor() {
		return host_monitor;
	}

	public void setHost_monitor(String host_monitor) {
		this.host_monitor = host_monitor;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAdmission() {
		return admission;
	}

	public void setAdmission(String admission) {
		this.admission = admission;
	}

	public List getGroupList() {
		return groupList;
	}

	public void setGroupList(List groupList) {
		this.groupList = groupList;
	}

	public List getVmList() {
		return vmList;
	}

	public void setVmList(List vmList) {
		this.vmList = vmList;
	}

	public List getForSelectList() {
		return forSelectList;
	}

	public void setForSelectList(List forSelectList) {
		this.forSelectList = forSelectList;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHastate() {
		return hastate;
	}

	public void setHastate(String hastate) {
		this.hastate = hastate;
	}

	public String getDrsstate() {
		return drsstate;
	}

	public void setDrsstate(String drsstate) {
		this.drsstate = drsstate;
	}
}
