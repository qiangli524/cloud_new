package com.sitech.basd.resource.domain.switchBoard;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class SwitchInterfaceObj extends BaseObj {
	private String id;
	private String interf_name;// 接口名称
	private String interf_status;// 接口状态0：UP，1：down
	private String workModle;// 接口的工作模式 0FULL，1HALF
	private String interf_speed;// 接口速率:1000
	private String auto_negotiation;// 自协商：0true 1false
	private String switch_id;
	private String insert_time;// 登记时间
	private String update_time;// 更新时间
	private String vlan_id;// vlan表中主键
	private String vlan_name;// vlan名字
	private String vlanId;
	private String descript;

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getVlanId() {
		return vlanId;
	}

	public void setVlanId(String vlanId) {
		this.vlanId = vlanId;
	}

	public String getVlan_id() {
		return vlan_id;
	}

	public void setVlan_id(String vlan_id) {
		this.vlan_id = vlan_id;
	}

	public String getVlan_name() {
		return vlan_name;
	}

	public void setVlan_name(String vlan_name) {
		this.vlan_name = vlan_name;
	}

	public String getSwitch_id() {
		return switch_id;
	}

	public void setSwitch_id(String switch_id) {
		this.switch_id = switch_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInterf_name() {
		return interf_name;
	}

	public void setInterf_name(String interf_name) {
		this.interf_name = interf_name;
	}

	public String getInterf_status() {
		return interf_status;
	}

	public void setInterf_status(String interf_status) {
		this.interf_status = interf_status;
	}

	public String getWorkModle() {
		return workModle;
	}

	public void setWorkModle(String workModle) {
		this.workModle = workModle;
	}

	public String getInterf_speed() {
		return interf_speed;
	}

	public void setInterf_speed(String interf_speed) {
		this.interf_speed = interf_speed;
	}

	public String getAuto_negotiation() {
		return auto_negotiation;
	}

	public void setAuto_negotiation(String auto_negotiation) {
		this.auto_negotiation = auto_negotiation;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

}
