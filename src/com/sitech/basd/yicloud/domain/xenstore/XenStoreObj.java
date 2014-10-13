package com.sitech.basd.yicloud.domain.xenstore;

import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;

public class XenStoreObj extends BasePrivilegeObj {
	private int id;
	private String store_uuid;
	private String name;
	private String sr_url;
	private String sr_size;
	private String type;
	private String dependent_host_uuid;
	private String sr_desc;
	private String state;
	private String free_size;
	private String purchase_space;// 制备空间
	private String use_per;
	private String shared;
	private String contraoper;
	private String vm_name;
	private String connect_id;
	private List<String> hostidlist;
	private String deviceId;// 存储设备id
	private String deviceName;// 存储设备名称
	private String deviceType;// 存储设备类型

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public String getPurchase_space() {
		return purchase_space;
	}

	public void setPurchase_space(String purchase_space) {
		this.purchase_space = purchase_space;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public List<String> getHostidlist() {
		return hostidlist;
	}

	public void setHostidlist(List<String> hostidlist) {
		this.hostidlist = hostidlist;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public String getVm_name() {
		return vm_name;
	}

	public void setVm_name(String vm_name) {
		this.vm_name = vm_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContraoper() {
		return contraoper;
	}

	public void setContraoper(String contraoper) {
		this.contraoper = contraoper;
	}

	public String getShared() {
		return shared;
	}

	public String getUse_per() {
		return use_per;
	}

	public void setUse_per(String use_per) {
		this.use_per = use_per;
	}

	public String isShared() {
		return shared;
	}

	public void setShared(String share) {
		this.shared = share;
	}

	public String getFree_size() {
		return free_size;
	}

	public void setFree_size(String free_size) {
		this.free_size = free_size;
	}

	public String getStore_uuid() {
		return store_uuid;
	}

	public void setStore_uuid(String store_uuid) {
		this.store_uuid = store_uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSr_url() {
		return sr_url;
	}

	public void setSr_url(String sr_url) {
		this.sr_url = sr_url;
	}

	public String getSr_size() {
		return sr_size;
	}

	public void setSr_size(String sr_size) {
		this.sr_size = sr_size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDependent_host_uuid() {
		return dependent_host_uuid;
	}

	public void setDependent_host_uuid(String dependent_host_uuid) {
		this.dependent_host_uuid = dependent_host_uuid;
	}

	public String getSr_desc() {
		return sr_desc;
	}

	public void setSr_desc(String sr_desc) {
		this.sr_desc = sr_desc;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
