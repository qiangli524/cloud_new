package com.sitech.basd.sxcloud.cloud.domain.templet;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TempletTypeObj extends BaseObj implements Serializable, Cloneable {
	private String ID;
	private String TYPE;
	private String NAME;
	private String TYPE_DESC;
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String type) {
		TYPE = type;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String name) {
		NAME = name;
	}
	public String getTYPE_DESC() {
		return TYPE_DESC;
	}
	public void setTYPE_DESC(String type_desc) {
		TYPE_DESC = type_desc;
	}
	
}
