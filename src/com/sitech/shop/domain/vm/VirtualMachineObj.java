package com.sitech.shop.domain.vm;

public class VirtualMachineObj {
	private String user_id;
	private String name;
	private String vmCode;
	private String connectCode;
	private String oper_system;
	private String publicIP;
	private String powerState;
	private String runTime;
	private Integer cpu;
	private Integer memoryInMB;
	private Double storageInGB;

	public Integer getCpu() {
		return cpu;
	}

	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}

	public Integer getMemoryInMB() {
		return memoryInMB;
	}

	public void setMemoryInMB(Integer memoryInMB) {
		this.memoryInMB = memoryInMB;
	}

	public Double getStorageInGB() {
		return storageInGB;
	}

	public void setStorageInGB(Double storageInGB) {
		this.storageInGB = storageInGB;
	}

	public String getConnectCode() {
		return connectCode;
	}

	public void setConnectCode(String connectCode) {
		this.connectCode = connectCode;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVmCode() {
		return vmCode;
	}

	public void setVmCode(String vmCode) {
		this.vmCode = vmCode;
	}

	public String getOper_system() {
		return oper_system;
	}

	public void setOper_system(String oper_system) {
		this.oper_system = oper_system;
	}

	public String getPublicIP() {
		return publicIP;
	}

	public void setPublicIP(String publicIP) {
		this.publicIP = publicIP;
	}

	public String getPowerState() {
		return powerState;
	}

	public void setPowerState(String powerState) {
		this.powerState = powerState;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

}
