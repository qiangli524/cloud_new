package com.sitech.basd.component.web.config.form;

import java.util.List;

public class ConfigForm {
	private List resultList;
	private int user_id;// 主机配置表的用户id
	private String name;// 名称
	private String type;// 0xml，1properties，2其它
	private String category;// 0通用，1部署使用，2其它
	private String description;
	private String path;// 路径
	private String upload_user;// 上传人
	private String mod_user;// 修改人
	private String insert_time;// 插入时间
	private String update_time;// 更新时间
	private String ip;
	private String username;
	private String password;
	private Integer tactics;// 1FIFO 2Container 3Fair

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUpload_user() {
		return upload_user;
	}

	public void setUpload_user(String upload_user) {
		this.upload_user = upload_user;
	}

	public String getMod_user() {
		return mod_user;
	}

	public void setMod_user(String mod_user) {
		this.mod_user = mod_user;
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

	public Integer getTactics() {
		return tactics;
	}

	public void setTactics(Integer tactics) {
		this.tactics = tactics;
	}

}
