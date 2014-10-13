package com.sitech.basd.yicloud.domain.device.disk;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;


/**
 * <p>Title: DiskInfoObj</p>
 * <p>Description: TODO(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author yanggl
 * @version 1.0
 * @createtime 2014-6-7 下午5:54:18
 *
 */
public class DiskInfoObj extends BaseObj{

	private String disk_number;
	
	private String disk_id;
	
	private String disk_name;
	
	private String disk_type;
	
	private String diskgroup_id;
	
	private String diskgroup_name;//所属存储设备名称
	
	private String disk_size;
	
	private String diskgroup_Id;
	
	public String getDiskgroup_Id() {
		return diskgroup_Id;
	}

	public void setDiskgroup_Id(String diskgroup_Id) {
		this.diskgroup_Id = diskgroup_Id;
	}

	public String getDisk_size() {
		return disk_size;
	}

	public void setDisk_size(String disk_size) {
		this.disk_size = disk_size;
	}

	public String getDisk_number() {
		return disk_number;
	}

	public void setDisk_number(String disk_number) {
		this.disk_number = disk_number;
	}

	public String getDisk_id() {
		return disk_id;
	}

	public void setDisk_id(String disk_id) {
		this.disk_id = disk_id;
	}

	public String getDisk_name() {
		return disk_name;
	}

	public void setDisk_name(String disk_name) {
		this.disk_name = disk_name;
	}

	public String getDisk_type() {
		return disk_type;
	}

	public void setDisk_type(String disk_type) {
		this.disk_type = disk_type;
	}

	public String getDiskgroup_id() {
		return diskgroup_id;
	}

	public void setDiskgroup_id(String diskgroup_id) {
		this.diskgroup_id = diskgroup_id;
	}

	public String getDiskgroup_name() {
		return diskgroup_name;
	}

	public void setDiskgroup_name(String diskgroup_name) {
		this.diskgroup_name = diskgroup_name;
	}

}
