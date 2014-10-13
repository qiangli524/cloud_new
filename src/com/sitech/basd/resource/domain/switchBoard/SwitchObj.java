package com.sitech.basd.resource.domain.switchBoard;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class SwitchObj extends BaseObj {
	private String id;
	private String switch_name; // 交换机的名称
	private String switch_type; // 交换机的型号
	private String switch_ip; // 管理交换机的
	private String switch_status; // 交换机的状态 0：unlock 1：lock
	private String switch_vision; // 交换机的版本
	private String switch_des; // 交换机的 描述
	private String switch_oem; // 交换机的厂商
	private String insert_time; // 交换机的登记时间
	private String update_time; // 交换机的更新时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSwitch_name() {
		return switch_name;
	}

	public void setSwitch_name(String switch_name) {
		this.switch_name = switch_name;
	}

	public String getSwitch_type() {
		return switch_type;
	}

	public void setSwitch_type(String switch_type) {
		this.switch_type = switch_type;
	}

	public String getSwitch_ip() {
		return switch_ip;
	}

	public void setSwitch_ip(String switch_ip) {
		this.switch_ip = switch_ip;
	}

	public String getSwitch_status() {
		return switch_status;
	}

	public void setSwitch_status(String switch_status) {
		this.switch_status = switch_status;
	}

	public String getSwitch_vision() {
		return switch_vision;
	}

	public void setSwitch_vision(String switch_vision) {
		this.switch_vision = switch_vision;
	}

	public String getSwitch_des() {
		return switch_des;
	}

	public void setSwitch_des(String switch_des) {
		this.switch_des = switch_des;
	}

	public String getSwitch_oem() {
		return switch_oem;
	}

	public void setSwitch_oem(String switch_oem) {
		this.switch_oem = switch_oem;
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
