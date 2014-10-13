package com.sitech.basd.ibmmanager.domain;

/**
 * 
 * <p>
 * Title: ReturnList
 * </p>
 * <p>
 * Description: 虚拟机电源 JSON字符串需要的属性 只需要电源状态
 * 				(poweredOff:关机 ,poweredOn开机,standBy挂起,unknown未知)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-11-20 上午9:16:58
 * 
 */
public class ReturnList {
	private String name;
	private String uptime;
	private String uuid;
	private String numCpu;
	private String overallStatus;
	private String powerState;// 电源状态
	private String memorySizeMB;
	private String maxCpuUsage;
	private String hostMemoryUsage;
	private String connectionState;
	private String staticMemoryEntitlement;
	private String guestMemoryUsage;
	private String overallCpuUsage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNumCpu() {
		return numCpu;
	}

	public void setNumCpu(String numCpu) {
		this.numCpu = numCpu;
	}

	public String getOverallStatus() {
		return overallStatus;
	}

	public void setOverallStatus(String overallStatus) {
		this.overallStatus = overallStatus;
	}

	public String getPowerState() {
		return powerState;
	}

	public void setPowerState(String powerState) {
		this.powerState = powerState;
	}

	public String getMemorySizeMB() {
		return memorySizeMB;
	}

	public void setMemorySizeMB(String memorySizeMB) {
		this.memorySizeMB = memorySizeMB;
	}

	public String getMaxCpuUsage() {
		return maxCpuUsage;
	}

	public void setMaxCpuUsage(String maxCpuUsage) {
		this.maxCpuUsage = maxCpuUsage;
	}

	public String getHostMemoryUsage() {
		return hostMemoryUsage;
	}

	public void setHostMemoryUsage(String hostMemoryUsage) {
		this.hostMemoryUsage = hostMemoryUsage;
	}

	public String getConnectionState() {
		return connectionState;
	}

	public void setConnectionState(String connectionState) {
		this.connectionState = connectionState;
	}

	public String getStaticMemoryEntitlement() {
		return staticMemoryEntitlement;
	}

	public void setStaticMemoryEntitlement(String staticMemoryEntitlement) {
		this.staticMemoryEntitlement = staticMemoryEntitlement;
	}

	public String getGuestMemoryUsage() {
		return guestMemoryUsage;
	}

	public void setGuestMemoryUsage(String guestMemoryUsage) {
		this.guestMemoryUsage = guestMemoryUsage;
	}

	public String getOverallCpuUsage() {
		return overallCpuUsage;
	}

	public void setOverallCpuUsage(String overallCpuUsage) {
		this.overallCpuUsage = overallCpuUsage;
	}

}
