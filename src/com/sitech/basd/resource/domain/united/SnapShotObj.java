package com.sitech.basd.resource.domain.united;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: SnapShotObj
 * </p>
 * <p>
 * Description: 快照类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author taoxue
 * @version 1.0
 * @createtime 2014-4-10 下午6:04:13
 * 
 */
public class SnapShotObj extends BaseObj {
	private String id;
	private String name;
	private String connect_id;
	private String snapshot_uuid;
	private String description;
	private String vm_uuid;
	private String vm_name;
	private String create_time;
	private String user_id;
	private String vm_type;
	
	private List<String> connectCodeList;// vCenter标示列表，北京电信使用

	public List<String> getConnectCodeList() {
		return connectCodeList;
	}

	public void setConnectCodeList(List<String> connectCodeList) {
		this.connectCodeList = connectCodeList;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public String getSnapshot_uuid() {
		return snapshot_uuid;
	}

	public void setSnapshot_uuid(String snapshot_uuid) {
		this.snapshot_uuid = snapshot_uuid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVm_uuid() {
		return vm_uuid;
	}

	public void setVm_uuid(String vm_uuid) {
		this.vm_uuid = vm_uuid;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getVm_type() {
		return vm_type;
	}

	public void setVm_type(String vm_type) {
		this.vm_type = vm_type;
	}

	public String getVm_name() {
		return vm_name;
	}

	public void setVm_name(String vm_name) {
		this.vm_name = vm_name;
	}

}
