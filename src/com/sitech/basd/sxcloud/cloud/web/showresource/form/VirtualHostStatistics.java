package com.sitech.basd.sxcloud.cloud.web.showresource.form;

/**
 * 
 * <p>
 * Title: VirtualHostStatistics
 * </p>
 * <p>
 * Description: 虚拟化宿主机统计信息
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-8-11 上午10:14:39
 * 
 */
public class VirtualHostStatistics extends HostStatistics {
	// 宿主机vCpu
	private int allVcpu;
	private int usedVcpu;
	private int freeVcpu;

	public int getAllVcpu() {
		return allVcpu;
	}

	public void setAllVcpu(int allVcpu) {
		this.allVcpu = allVcpu;
	}

	public int getUsedVcpu() {
		return usedVcpu;
	}

	public void setUsedVcpu(int usedVcpu) {
		this.usedVcpu = usedVcpu;
	}

	public int getFreeVcpu() {
		return freeVcpu;
	}

	public void setFreeVcpu(int freeVcpu) {
		this.freeVcpu = freeVcpu;
	}

}
