package com.sitech.basd.yicloud.domain.xen;

import java.util.ArrayList;
import java.util.List;

public class Host extends Pool {

	private String hostUuid;

	private String hostName;

	private List<Vm> vms = new ArrayList<Vm>();

	private List<Sr> srs = new ArrayList<Sr>();

	private String hostIp;

	private Integer hostCpuNum;

	private String hostCpuDesc;

	private boolean isMaster;

	private double hostCpuSpeed;

	private long memSize;

	// 宿主机允许允许虚拟机最大数量
	private Integer maxHostRunningVms;
	// 宿主机允许最大VCPU核数
	private Integer maxHostSupportedVcpus;
	// 宿主机已使用CPU主频-单位MHZ
	private Integer usedCpuMHz;
	// 宿主机已使用内存-单位MB
	private Integer userMemMb;
	// 宿主机最大CPU-单位MHZ
	private Integer maxCpuMHz;
	// 宿主机已使用Vcpu
	private Integer usedHostVcpus;

	public Integer getMaxHostRunningVms() {
		return maxHostRunningVms;
	}

	public void setMaxHostRunningVms(Integer maxHostRunningVms) {
		this.maxHostRunningVms = maxHostRunningVms;
	}

	public Integer getMaxHostSupportedVcpus() {
		return maxHostSupportedVcpus;
	}

	public void setMaxHostSupportedVcpus(Integer maxHostSupportedVcpus) {
		this.maxHostSupportedVcpus = maxHostSupportedVcpus;
	}

	public Integer getUsedCpuMHz() {
		return usedCpuMHz;
	}

	public void setUsedCpuMHz(Integer usedCpuMHz) {
		this.usedCpuMHz = usedCpuMHz;
	}

	public Integer getUserMemMb() {
		return userMemMb;
	}

	public void setUserMemMb(Integer userMemMb) {
		this.userMemMb = userMemMb;
	}

	public Integer getMaxCpuMHz() {
		return maxCpuMHz;
	}

	public void setMaxCpuMHz(Integer maxCpuMHz) {
		this.maxCpuMHz = maxCpuMHz;
	}

	public Integer getUsedHostVcpus() {
		return usedHostVcpus;
	}

	public void setUsedHostVcpus(Integer usedHostVcpus) {
		this.usedHostVcpus = usedHostVcpus;
	}

	public List<Sr> getSrs() {
		return srs;
	}

	public void setSrs(List<Sr> srs) {
		this.srs = srs;
	}

	public long getMemSize() {
		return memSize;
	}

	public void setMemSize(long memSize) {
		this.memSize = memSize;
	}

	public double getHostCpuSpeed() {
		return hostCpuSpeed;
	}

	public void setHostCpuSpeed(double hostCpuSpeed) {
		this.hostCpuSpeed = hostCpuSpeed;
	}

	public String getHostUuid() {
		return hostUuid;
	}

	public void setHostUuid(String hostUuid) {
		this.hostUuid = hostUuid;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public Integer getHostCpuNum() {
		return hostCpuNum;
	}

	public void setHostCpuNum(Integer hostCpuNum) {
		this.hostCpuNum = hostCpuNum;
	}

	public String getHostCpuDesc() {
		return hostCpuDesc;
	}

	public void setHostCpuDesc(String hostCpuDesc) {
		this.hostCpuDesc = hostCpuDesc;
	}

	public boolean isMaster() {
		return isMaster;
	}

	public void setMaster(boolean isMaster) {
		this.isMaster = isMaster;
	}

	public List<Vm> getVms() {
		return vms;
	}

	public void setVms(List<Vm> vms) {
		this.vms = vms;
	}

}
