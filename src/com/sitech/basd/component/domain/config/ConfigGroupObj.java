package com.sitech.basd.component.domain.config;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class ConfigGroupObj extends BaseObj {
	private String id;
	private String name;
	private String description;
	private String num;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
