package com.sitech.basd.sxcloud.cloud.web.monitor.form;

import java.util.List;

public class MonitorForm {
	private String EQ_ID; // 物理服务器编号
	private String HY_ID; // 虚拟服务器编号
	private String APP_ID; // 应用编号
	private String KPI_ID; // KPI ID
	private String KPI_VALUE; // 采集值
	private String COLL_TIME; // 采集时间
	private String EVENT_INFO; // 时间信息
	private List eventManageList; // 最新事件列表
	private List resourceList; // 资源使用情况列表
	private String CPU; // CPU
	private String Memory; // 内存
	private String Storage; // 存储器
	private String hostname; // 主机名
	private int port; // 端口
	private String version; // 版本
	private String cloudType; // 类型
	private String state; // 云状态描述
	private String username; // 管理员标识
	private List workloadsList;// 工作负载列表
	private String value; // 工作负载状态 ；；所统计资源状态值
	private int count1; // 工作负载状态的个数
	private int count2; // 工作负载状态的个数
	private int count3; // 工作负载状态的个数
	private int count4; // 工作负载状态的个数
	private int count5; // 工作负载状态的个数
	private String label; // 所统计资源状态标签--描述
	private List resultList = null;

	public List getResultList() {
		return resultList;
	}

	public List getWorkloadsList() {
		return workloadsList;
	}

	public void setWorkloadsList(List workloadsList) {
		this.workloadsList = workloadsList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getEQ_ID() {
		return EQ_ID;
	}

	public void setEQ_ID(String eq_id) {
		EQ_ID = eq_id;
	}

	public String getHY_ID() {
		return HY_ID;
	}

	public void setHY_ID(String hy_id) {
		HY_ID = hy_id;
	}

	public String getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(String app_id) {
		APP_ID = app_id;
	}

	public String getKPI_ID() {
		return KPI_ID;
	}

	public void setKPI_ID(String kpi_id) {
		KPI_ID = kpi_id;
	}

	public String getKPI_VALUE() {
		return KPI_VALUE;
	}

	public void setKPI_VALUE(String kpi_value) {
		KPI_VALUE = kpi_value;
	}

	public String getCOLL_TIME() {
		return COLL_TIME;
	}

	public void setCOLL_TIME(String coll_time) {
		COLL_TIME = coll_time;
	}

	public String getEVENT_INFO() {
		return EVENT_INFO;
	}

	public void setEVENT_INFO(String event_info) {
		EVENT_INFO = event_info;
	}

	public List getEventManageList() {
		return eventManageList;
	}

	public void setEventManageList(List eventManageList) {
		this.eventManageList = eventManageList;
	}

	public List getResourceList() {
		return resourceList;
	}

	public void setResourceList(List resourceList) {
		this.resourceList = resourceList;
	}

	public String getCPU() {
		return CPU;
	}

	public void setCPU(String cpu) {
		CPU = cpu;
	}

	public String getMemory() {
		return Memory;
	}

	public void setMemory(String memory) {
		Memory = memory;
	}

	public String getStorage() {
		return Storage;
	}

	public void setStorage(String storage) {
		Storage = storage;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCloudType() {
		return cloudType;
	}

	public void setCloudType(String cloudType) {
		this.cloudType = cloudType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

	public int getCount3() {
		return count3;
	}

	public void setCount3(int count3) {
		this.count3 = count3;
	}

	public int getCount4() {
		return count4;
	}

	public void setCount4(int count4) {
		this.count4 = count4;
	}

	public int getCount5() {
		return count5;
	}

	public void setCount5(int count5) {
		this.count5 = count5;
	}

}
