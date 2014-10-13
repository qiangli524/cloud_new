package com.sitech.basd.ibmmanager.domain;

/**
 * 
 * <p>
 * Title: IBMIndexObj
 * </p>
 * <p>
 * Description: IBM性能指标相关属性
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
 * @createtime 2013-11-19 下午6:50:11
 * 
 */
public class IBMIndexObj {
	private String machine_serial_number;// power标识
	private String lpar_id;// lpar标识
	private String lpar_name;// lpar名字
	private String host_name;// 主机名字
	private String card_name; // FC卡名
	private String disk_name;// 磁盘
	private String disk_read_speed;// 磁盘读速度
	private String disk_write_speed;// 磁盘写速度
	private String disk_io_read_speed;
	private String disk_io_write_speed;
	private String read_speed;// 读速度
	private String write_speed;// 写速度
	private String package_read_speed;
	private String package_write_speed;
	private String update_time;// 时间
	private String default_pvid;
	private String flag;// IBM指标标识
	private String tableName;

	public String getMachine_serial_number() {
		return machine_serial_number;
	}

	public void setMachine_serial_number(String machine_serial_number) {
		this.machine_serial_number = machine_serial_number;
	}

	public String getRead_speed() {
		return read_speed;
	}

	public void setRead_speed(String read_speed) {
		this.read_speed = read_speed;
	}

	public String getWrite_speed() {
		return write_speed;
	}

	public void setWrite_speed(String write_speed) {
		this.write_speed = write_speed;
	}

	public String getPackage_read_speed() {
		return package_read_speed;
	}

	public void setPackage_read_speed(String package_read_speed) {
		this.package_read_speed = package_read_speed;
	}

	public String getPackage_write_speed() {
		return package_write_speed;
	}

	public void setPackage_write_speed(String package_write_speed) {
		this.package_write_speed = package_write_speed;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
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

	public String getDefault_pvid() {
		return default_pvid;
	}

	public void setDefault_pvid(String default_pvid) {
		this.default_pvid = default_pvid;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getDisk_name() {
		return disk_name;
	}

	public void setDisk_name(String disk_name) {
		this.disk_name = disk_name;
	}

	public String getDisk_read_speed() {
		return disk_read_speed;
	}

	public void setDisk_read_speed(String disk_read_speed) {
		this.disk_read_speed = disk_read_speed;
	}

	public String getDisk_write_speed() {
		return disk_write_speed;
	}

	public void setDisk_write_speed(String disk_write_speed) {
		this.disk_write_speed = disk_write_speed;
	}

	public String getDisk_io_read_speed() {
		return disk_io_read_speed;
	}

	public void setDisk_io_read_speed(String disk_io_read_speed) {
		this.disk_io_read_speed = disk_io_read_speed;
	}

	public String getDisk_io_write_speed() {
		return disk_io_write_speed;
	}

	public void setDisk_io_write_speed(String disk_io_write_speed) {
		this.disk_io_write_speed = disk_io_write_speed;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
