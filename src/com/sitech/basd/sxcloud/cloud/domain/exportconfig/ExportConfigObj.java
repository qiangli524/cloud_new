package com.sitech.basd.sxcloud.cloud.domain.exportconfig;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class ExportConfigObj extends BaseObj implements Serializable, Cloneable {
	private String KEY; // 配置关键字
	private String VALUE; // 配置值
	private String CFG_DESC; // 配置描述
	private int TYPE; // 语句类型
	private String ID;
	private String flag; // 增、改标识

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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int type) {
		TYPE = type;
	}
}
