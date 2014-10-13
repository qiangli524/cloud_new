package com.sitech.basd.resource.domain.mframe;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class MFrameObj extends BaseObj {
	private String id; // 机框唯一标识
	private String frame_num; // 机框编号
	private String frame_name; // 机框名称
	private Integer frame_status;// 机框状态
	private String ip; // ip地址
	private String oem; // 所属厂商
	private String position; // 机框位置
	private String frame_desc; // 机框描述
	private String insert_time;
	private String update_time;
	private String insert_user;
	private String update_user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFrame_num() {
		return frame_num;
	}

	public void setFrame_num(String frame_num) {
		this.frame_num = frame_num;
	}

	public String getFrame_name() {
		return frame_name;
	}

	public void setFrame_name(String frame_name) {
		this.frame_name = frame_name;
	}

	public Integer getFrame_status() {
		return frame_status;
	}

	public void setFrame_status(Integer frame_status) {
		this.frame_status = frame_status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOem() {
		return oem;
	}

	public void setOem(String oem) {
		this.oem = oem;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFrame_desc() {
		return frame_desc;
	}

	public void setFrame_desc(String frame_desc) {
		this.frame_desc = frame_desc;
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

	public String getInsert_user() {
		return insert_user;
	}

	public void setInsert_user(String insert_user) {
		this.insert_user = insert_user;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

}
