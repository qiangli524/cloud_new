/**
 * Copyright (c) 2013 SI-TECH Software, Inc.
 * All right reserved. 
 * 
 * 云管理平台
 */
package com.sitech.basd.cloud3.domain.charts;

/**
 *<P>
 * 类功能简述（例如：XXX功能）
 *</p>
 * Jul 2, 2014,3:16:20 PM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
public class MonitorObj {
	//指定时间段内cpu使用率的平均值
	private Double cpuUsedRate;
	//指定时间段内men使用率的平均值
	private Double menUsedRate;
	//磁盘读写平均值与最大值
	private Double diskReadAvg;
	private Double diskReadMax;
	private Double diskWriteAvg;
	private Double diskWriteMax;
	//网络读写平均值与最大值
	private Double netReadAvg;
	private Double netReadMax;
	private Double netWriteAvg;
	private Double netWriteMax;
	
	/**
	 *
	 * @return the cpuUsedRate
	 */
	public Double getCpuUsedRate() {
		return cpuUsedRate;
	}
	/**
	 *
	 * @param cpuUsedRate the cpuUsedRate to set
	 */
	public void setCpuUsedRate(Double cpuUsedRate) {
		this.cpuUsedRate = cpuUsedRate;
	}
	/**
	 *
	 * @return the menUsedRate
	 */
	public Double getMenUsedRate() {
		return menUsedRate;
	}
	/**
	 *
	 * @param menUsedRate the menUsedRate to set
	 */
	public void setMenUsedRate(Double menUsedRate) {
		this.menUsedRate = menUsedRate;
	}
	/**
	 *
	 * @return the diskReadAvg
	 */
	public Double getDiskReadAvg() {
		return diskReadAvg;
	}
	/**
	 *
	 * @param diskReadAvg the diskReadAvg to set
	 */
	public void setDiskReadAvg(Double diskReadAvg) {
		this.diskReadAvg = diskReadAvg;
	}
	/**
	 *
	 * @return the diskReadMax
	 */
	public Double getDiskReadMax() {
		return diskReadMax;
	}
	/**
	 *
	 * @param diskReadMax the diskReadMax to set
	 */
	public void setDiskReadMax(Double diskReadMax) {
		this.diskReadMax = diskReadMax;
	}
	/**
	 *
	 * @return the diskWriteAvg
	 */
	public Double getDiskWriteAvg() {
		return diskWriteAvg;
	}
	/**
	 *
	 * @param diskWriteAvg the diskWriteAvg to set
	 */
	public void setDiskWriteAvg(Double diskWriteAvg) {
		this.diskWriteAvg = diskWriteAvg;
	}
	/**
	 *
	 * @return the diskWriteMax
	 */
	public Double getDiskWriteMax() {
		return diskWriteMax;
	}
	/**
	 *
	 * @param diskWriteMax the diskWriteMax to set
	 */
	public void setDiskWriteMax(Double diskWriteMax) {
		this.diskWriteMax = diskWriteMax;
	}
	/**
	 *
	 * @return the netReadAvg
	 */
	public Double getNetReadAvg() {
		return netReadAvg;
	}
	/**
	 *
	 * @param netReadAvg the netReadAvg to set
	 */
	public void setNetReadAvg(Double netReadAvg) {
		this.netReadAvg = netReadAvg;
	}
	/**
	 *
	 * @return the netReadMax
	 */
	public Double getNetReadMax() {
		return netReadMax;
	}
	/**
	 *
	 * @param netReadMax the netReadMax to set
	 */
	public void setNetReadMax(Double netReadMax) {
		this.netReadMax = netReadMax;
	}
	/**
	 *
	 * @return the netWriteAvg
	 */
	public Double getNetWriteAvg() {
		return netWriteAvg;
	}
	/**
	 *
	 * @param netWriteAvg the netWriteAvg to set
	 */
	public void setNetWriteAvg(Double netWriteAvg) {
		this.netWriteAvg = netWriteAvg;
	}
	/**
	 *
	 * @return the netWriteMax
	 */
	public Double getNetWriteMax() {
		return netWriteMax;
	}
	/**
	 *
	 * @param netWriteMax the netWriteMax to set
	 */
	public void setNetWriteMax(Double netWriteMax) {
		this.netWriteMax = netWriteMax;
	}
	
	
}
