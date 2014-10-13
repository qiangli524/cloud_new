package com.sitech.basd.ibmmanager.domain;

/**
 * 
 * <p>
 * Title: PowerObj
 * </p>
 * <p>
 * Description: Power表中属性
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
 * @createtime 2013-11-9 下午2:03:12
 * 
 */
public class PowerObj {
	private String hmcid;// hmcID
	private String sysname;// 主机名字
	private String machineserialnumber;// 主机ID
	private String totalcpunum;// cpu总个数
	private String totalmem;// 内存总数
	private String installed_mem;// 已安装内存

	public String getHmcid() {
		return hmcid;
	}

	public void setHmcid(String hmcid) {
		this.hmcid = hmcid;
	}

	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public String getMachineserialnumber() {
		return machineserialnumber;
	}

	public void setMachineserialnumber(String machineserialnumber) {
		this.machineserialnumber = machineserialnumber;
	}

	public String getTotalcpunum() {
		return totalcpunum;
	}

	public void setTotalcpunum(String totalcpunum) {
		this.totalcpunum = totalcpunum;
	}

	public String getTotalmem() {
		return totalmem;
	}

	public void setTotalmem(String totalmem) {
		this.totalmem = totalmem;
	}

	public String getInstalled_mem() {
		return installed_mem;
	}

	public void setInstalled_mem(String installed_mem) {
		this.installed_mem = installed_mem;
	}

}
