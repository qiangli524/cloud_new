package com.sitech.basd.yicloud.domain.snapshot;

public class Snapshot {
	private String val;
	private boolean current = false;//是否是当前快照
	private String vmName;// 虚拟机名称
	private String name;// 快照名称
	private String time;// 快照时间
	private String description;// 快照描述
	private String snap_uuid;// 快照的UUID

	public String getSnap_uuid() {
		return snap_uuid;
	}

	public void setSnap_uuid(String snap_uuid) {
		this.snap_uuid = snap_uuid;
	}

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

}
