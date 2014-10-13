package com.sitech.basd.component.domain.config;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class ConfigRelationObj extends BaseObj {
	private String group_id;// 配置文件组id
	private String config_id;// 配置文件id

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getConfig_id() {
		return config_id;
	}

	public void setConfig_id(String config_id) {
		this.config_id = config_id;
	}

}
