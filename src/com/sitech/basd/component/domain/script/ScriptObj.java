package com.sitech.basd.component.domain.script;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>
 * Title: ScriptObj
 * </p>
 * <p>
 * Description: TODO(用一句话描述该文件做什么)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-12-26 上午9:45:24
 * 
 */
public class ScriptObj extends BaseObj {

	private String id; // varchar(256) not null primary key,--id
	private String user_id; // varchar(20),--IP用户编号：外键
	private String name;
	private String type;
	private String path;
	private String description;
	private int count;
	private String first_time;
	private String last_time;
	private String upload_person;
	private String update_person;
	private String create_time;
	private String update_time;
	private int grade;
	private String ip;
	private String username;
	private String password;
	private String interval;// 时间间隔
	private String unit;// 时间间隔单位

	private String example_id;
	// 部署实例ID转码后拼接，已,分割，用于查询业务中心树中基准应用上脚本列表
	private String encodeExampleStr;
	private String params;// 脚本参数
	private String app_type;// 脚本应用类型

	public String getApp_type() {
		return app_type;
	}

	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}

	public String getEncodeExampleStr() {
		return encodeExampleStr;
	}

	public void setEncodeExampleStr(String encodeExampleStr) {
		this.encodeExampleStr = encodeExampleStr;
	}

	public String getExample_id() {
		return example_id;
	}

	public void setExample_id(String example_id) {
		this.example_id = example_id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getFirst_time() {
		return first_time;
	}

	public void setFirst_time(String first_time) {
		this.first_time = first_time;
	}

	public String getLast_time() {
		return last_time;
	}

	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}

	public String getUpload_person() {
		return upload_person;
	}

	public void setUpload_person(String upload_person) {
		this.upload_person = upload_person;
	}

	public String getUpdate_person() {
		return update_person;
	}

	public void setUpdate_person(String update_person) {
		this.update_person = update_person;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
