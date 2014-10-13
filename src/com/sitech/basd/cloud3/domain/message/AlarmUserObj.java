package com.sitech.basd.cloud3.domain.message;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class AlarmUserObj extends BaseObj {
	private String id;
	private String name;
	private String email;
	private String telephone;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
