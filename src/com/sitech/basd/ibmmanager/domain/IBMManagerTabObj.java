package com.sitech.basd.ibmmanager.domain;

/**
 * 
 * <p>
 * Title: IBMManagerTabObj
 * </p>
 * <p>
 * Description: Tab页上需要的 属性
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
 * @createtime 2013-11-14 下午3:18:16
 * 
 */
public class IBMManagerTabObj {

	private Integer ibmCpuCount;// IBM CPU 总量
	private Integer ibmCpuUsedCount;// IBM CPU 已用量
	private Integer ibmCpuUnUsedCount;// IBM CPU 未用量
	private Double ibmMemCount;// IBM 内存总量
	private Double ibmMemUsedCount;// IBM内存已用量
	private Double ibmMemUnUsedCount;// IBM内存未用量
	private Integer cloudOSCpuCount;// X86 CPU总量
	private Integer cloudOSCpuUsedCount;// X86 cpu已用量
	private Integer cloudOSCpuUnUsedCount;// x86 cpu未用量
	private Double cloudOSMemCount;// X86 内存总量
	private Double cloudOSMemUsedCount;// X86 内存 已用量
	private Double cloudOSMemUnUsedCount;// X86 内存未用量
	private Integer hmcCount; // IBM 的个数
	private Integer clusterCount; // X86的个数
	private Integer powerCount; // power个数
	private Integer lparCount;// lpar个数
	private Integer hostCount;// 主机的个数
	private Integer vmCount; // 虚拟机的个数

	public Integer getIbmCpuCount() {
		return ibmCpuCount;
	}

	public void setIbmCpuCount(Integer ibmCpuCount) {
		this.ibmCpuCount = ibmCpuCount;
	}

	public Integer getIbmCpuUsedCount() {
		return ibmCpuUsedCount;
	}

	public void setIbmCpuUsedCount(Integer ibmCpuUsedCount) {
		this.ibmCpuUsedCount = ibmCpuUsedCount;
	}

	public Integer getIbmCpuUnUsedCount() {
		return ibmCpuUnUsedCount;
	}

	public void setIbmCpuUnUsedCount(Integer ibmCpuUnUsedCount) {
		this.ibmCpuUnUsedCount = ibmCpuUnUsedCount;
	}

	public Double getIbmMemCount() {
		return ibmMemCount;
	}

	public void setIbmMemCount(Double ibmMemCount) {
		this.ibmMemCount = ibmMemCount;
	}

	public Double getIbmMemUsedCount() {
		return ibmMemUsedCount;
	}

	public void setIbmMemUsedCount(Double ibmMemUsedCount) {
		this.ibmMemUsedCount = ibmMemUsedCount;
	}

	public Double getIbmMemUnUsedCount() {
		return ibmMemUnUsedCount;
	}

	public void setIbmMemUnUsedCount(Double ibmMemUnUsedCount) {
		this.ibmMemUnUsedCount = ibmMemUnUsedCount;
	}

	public Integer getCloudOSCpuCount() {
		return cloudOSCpuCount;
	}

	public void setCloudOSCpuCount(Integer cloudOSCpuCount) {
		this.cloudOSCpuCount = cloudOSCpuCount;
	}

	public Integer getCloudOSCpuUsedCount() {
		return cloudOSCpuUsedCount;
	}

	public void setCloudOSCpuUsedCount(Integer cloudOSCpuUsedCount) {
		this.cloudOSCpuUsedCount = cloudOSCpuUsedCount;
	}

	public Integer getCloudOSCpuUnUsedCount() {
		return cloudOSCpuUnUsedCount;
	}

	public void setCloudOSCpuUnUsedCount(Integer cloudOSCpuUnUsedCount) {
		this.cloudOSCpuUnUsedCount = cloudOSCpuUnUsedCount;
	}

	public Double getCloudOSMemCount() {
		return cloudOSMemCount;
	}

	public void setCloudOSMemCount(Double cloudOSMemCount) {
		this.cloudOSMemCount = cloudOSMemCount;
	}

	public Double getCloudOSMemUsedCount() {
		return cloudOSMemUsedCount;
	}

	public void setCloudOSMemUsedCount(Double cloudOSMemUsedCount) {
		this.cloudOSMemUsedCount = cloudOSMemUsedCount;
	}

	public Double getCloudOSMemUnUsedCount() {
		return cloudOSMemUnUsedCount;
	}

	public void setCloudOSMemUnUsedCount(Double cloudOSMemUnUsedCount) {
		this.cloudOSMemUnUsedCount = cloudOSMemUnUsedCount;
	}

	public Integer getHmcCount() {
		return hmcCount;
	}

	public void setHmcCount(Integer hmcCount) {
		this.hmcCount = hmcCount;
	}

	public Integer getClusterCount() {
		return clusterCount;
	}

	public void setClusterCount(Integer clusterCount) {
		this.clusterCount = clusterCount;
	}

	public Integer getPowerCount() {
		return powerCount;
	}

	public void setPowerCount(Integer powerCount) {
		this.powerCount = powerCount;
	}

	public Integer getLparCount() {
		return lparCount;
	}

	public void setLparCount(Integer lparCount) {
		this.lparCount = lparCount;
	}

	public Integer getHostCount() {
		return hostCount;
	}

	public void setHostCount(Integer hostCount) {
		this.hostCount = hostCount;
	}

	public Integer getVmCount() {
		return vmCount;
	}

	public void setVmCount(Integer vmCount) {
		this.vmCount = vmCount;
	}

}
