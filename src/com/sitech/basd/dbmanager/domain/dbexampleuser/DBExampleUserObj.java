package com.sitech.basd.dbmanager.domain.dbexampleuser;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class DBExampleUserObj extends BaseObj {
	private String id;
	private String example_username;
	private String example_password;
	private int is_lock;
	private int is_submit;
	private String example_id;

	public int getIs_submit() {
		return is_submit;
	}

	public void setIs_submit(int is_submit) {
		this.is_submit = is_submit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExample_username() {
		return example_username;
	}

	public void setExample_username(String example_username) {
		this.example_username = example_username;
	}

	public String getExample_password() {
		return example_password;
	}

	public void setExample_password(String example_password) {
		this.example_password = example_password;
	}

	public int getIs_lock() {
		return is_lock;
	}

	public void setIs_lock(int is_lock) {
		this.is_lock = is_lock;
	}

	public String getExample_id() {
		return example_id;
	}

	public void setExample_id(String example_id) {
		this.example_id = example_id;
	}

}
