package com.sitech.basd.resource.domain.united;

import com.sitech.basd.common.domain.BasePrivilegeObj;

public class ResourceStatisticsObj extends BasePrivilegeObj {
	private String key;

	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
