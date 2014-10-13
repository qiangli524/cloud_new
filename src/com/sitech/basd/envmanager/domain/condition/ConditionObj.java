package com.sitech.basd.envmanager.domain.condition;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class ConditionObj  extends BaseObj implements Serializable, Cloneable {

	
	private int DEVICE_ID;// 设备ID
	private String DEVICE_NAME;// 设备名称
	private String DEVICE_NAME_EN;// 设备英文名称
	private String DEVICE_CODE;// 设备编码
	private String DEVICE_TYPE;// 设备类型
	private String DEVICE_DESC;// 设备描述
	private String STATUSE;// 设备状态
	private String TYPE_NAME;// 设备类型名

	public String getTYPE_NAME() {
		return TYPE_NAME;
	}

	public void setTYPE_NAME(String type_name) {
		TYPE_NAME = type_name;
	}

	public int getDEVICE_ID() {
		return DEVICE_ID;
	}

	public void setDEVICE_ID(int device_id) {
		DEVICE_ID = device_id;
	}

	public String getDEVICE_NAME() {
		return DEVICE_NAME;
	}

	public void setDEVICE_NAME(String device_name) {
		DEVICE_NAME = device_name;
	}

	public String getDEVICE_NAME_EN() {
		return DEVICE_NAME_EN;
	}

	public void setDEVICE_NAME_EN(String device_name_en) {
		DEVICE_NAME_EN = device_name_en;
	}

	public String getDEVICE_CODE() {
		return DEVICE_CODE;
	}

	public void setDEVICE_CODE(String device_code) {
		DEVICE_CODE = device_code;
	}

	public String getDEVICE_TYPE() {
		return DEVICE_TYPE;
	}

	public void setDEVICE_TYPE(String device_type) {
		DEVICE_TYPE = device_type;
	}

	public String getDEVICE_DESC() {
		return DEVICE_DESC;
	}

	public void setDEVICE_DESC(String device_desc) {
		DEVICE_DESC = device_desc;
	}

	public String getSTATUSE() {
		return STATUSE;
	}

	public void setSTATUSE(String statuse) {
		STATUSE = statuse;
	}

}
