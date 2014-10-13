package com.sitech.basd.ibmmanager.domain;

/**
 * 
 * <p>
 * Title: LogicalPartitionObj
 * </p>
 * <p>
 * Description: 逻辑分区表中属性
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
 * @createtime 2013-11-9 下午2:03:44
 * 
 */
public class LogicalPartitionObj {
	private String sysname;// 主机名字
	private String machineserialnumber;// 主机ID
	private String lpar_id;// lparID
	private String lpar_name;// lpar名字
	private String host_name;
	private String lpar_env;// 环境
	private String os_version;// 版本
	private String curr_proc_mode;// CPU类型
	private String desired_proc_units;
	private String desired_procs;// cpu
	private String memory_size;// 内存
	private String tableName;// lpar各个指标的表名

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

	public String getLpar_id() {
		return lpar_id;
	}

	public void setLpar_id(String lpar_id) {
		this.lpar_id = lpar_id;
	}

	public String getLpar_name() {
		return lpar_name;
	}

	public void setLpar_name(String lpar_name) {
		this.lpar_name = lpar_name;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getLpar_env() {
		return lpar_env;
	}

	public void setLpar_env(String lpar_env) {
		this.lpar_env = lpar_env;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getCurr_proc_mode() {
		return curr_proc_mode;
	}

	public void setCurr_proc_mode(String curr_proc_mode) {
		this.curr_proc_mode = curr_proc_mode;
	}

	public String getDesired_proc_units() {
		return desired_proc_units;
	}

	public void setDesired_proc_units(String desired_proc_units) {
		this.desired_proc_units = desired_proc_units;
	}

	public String getDesired_procs() {
		return desired_procs;
	}

	public void setDesired_procs(String desired_procs) {
		this.desired_procs = desired_procs;
	}

	public String getMemory_size() {
		return memory_size;
	}

	public void setMemory_size(String memory_size) {
		this.memory_size = memory_size;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
