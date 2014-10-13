package com.sitech.basd.sxcloud.cloud.domain.globalconfig;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbGlobalConfigObj extends BaseObj implements Serializable,
		Cloneable {
	private String KEY; // 配置关键字
	private String VALUE; // 配置值
	private String CFG_DESC; // 配置描述
	private String ID;

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getKEY() {
		return KEY;
	}

	public void setKEY(String key) {
		KEY = key;
	}

	public String getVALUE() {
		return VALUE;
	}

	public void setVALUE(String value) {
		VALUE = value;
	}

	public String getCFG_DESC() {
		return CFG_DESC;
	}

	public void setCFG_DESC(String cfg_desc) {
		CFG_DESC = cfg_desc;
	}

	public void CFG_DESC(String cfg_desc) {
		CFG_DESC = cfg_desc;
	}
}
