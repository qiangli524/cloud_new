package com.sitech.basd.resource.domain.united.vm;

import java.io.Serializable;

import com.sitech.basd.common.domain.BasePrivilegeObj;

/**
 * 
 * <p>
 * Title: VmDatastoreRelationObj
 * </p>
 * <p>
 * Description: 虚拟机和数据存储相关表的实体
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author liuqi
 * @version 1.0
 * @createtime 2014-9-16 上午9:45:51
 * 
 */
@SuppressWarnings("all")
public class VmDatastoreRelationObj extends BasePrivilegeObj implements Serializable, Cloneable {

	private String id;
	private String vm_uuid;
	private String connectid;
	private String host_uuid;
	private String datastore_uuid;
	// 虚拟机种类
	private String vtype;

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

	public String getConnectid() {
		return connectid;
	}

	public void setConnectid(String connectid) {
		this.connectid = connectid;
	}

	public String getHost_uuid() {
		return host_uuid;
	}

	public void setHost_uuid(String host_uuid) {
		this.host_uuid = host_uuid;
	}

	public String getDatastore_uuid() {
		return datastore_uuid;
	}

	public void setDatastore_uuid(String datastore_uuid) {
		this.datastore_uuid = datastore_uuid;
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

}
