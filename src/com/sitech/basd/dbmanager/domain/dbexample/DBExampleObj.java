package com.sitech.basd.dbmanager.domain.dbexample;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class DBExampleObj extends BaseObj {

	private String id;
	private String example_name;
	private String example_ip;
	private Integer iscomplete;
	private String data_file_path;
	private String sys_pass;
	private String username;
	private String password;
	private String userid;//user_manage表的id
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

	public String getData_file_path() {
	    return data_file_path;
	}

	public void setData_file_path(String data_file_path) {
	    this.data_file_path = data_file_path;
	}

	public String getSys_pass() {
	    return sys_pass;
	}

	public void setSys_pass(String sys_pass) {
	    this.sys_pass = sys_pass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExample_name() {
		return example_name;
	}

	public void setExample_name(String example_name) {
		this.example_name = example_name;
	}

	public String getExample_ip() {
		return example_ip;
	}

	public void setExample_ip(String example_ip) {
		this.example_ip = example_ip;
	}

	public Integer getIscomplete() {
	    return iscomplete;
	}

	public void setIscomplete(Integer iscomplete) {
	    this.iscomplete = iscomplete;
	}

	public String getUserid() {
	    return userid;
	}

	public void setUserid(String userid) {
	    this.userid = userid;
	}
}
