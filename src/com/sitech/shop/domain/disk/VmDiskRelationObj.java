package com.sitech.shop.domain.disk;

/**
 * <p>
 * Title: VmDiskRelationObj
 * </p>
 * <p>
 * Description: 虚拟磁盘和虚拟机的关系
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipp
 * @version 1.0
 * @createtime 2014-4-18 下午3:20:41
 * 
 */
public class VmDiskRelationObj {

	private String id;// 主键
	private String vm_uuid;// 虚拟机标识
	private String connect_id;// 链接标识
	private String virdisk_id;// 虚拟磁盘标识

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVm_uuid() {
		return vm_uuid;
	}

	public void setVm_uuid(String vm_uuid) {
		this.vm_uuid = vm_uuid;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public String getVirdisk_id() {
		return virdisk_id;
	}

	public void setVirdisk_id(String virdisk_id) {
		this.virdisk_id = virdisk_id;
	}

}
