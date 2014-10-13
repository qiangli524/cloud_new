package com.sitech.basd.sxcloud.cloud.domain.device;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class DictionaryObj extends BaseObj implements Serializable, Cloneable {

	private int ID;
	private String CODE;
	private String NAME;
	private String GROUP_NAME;
	private static final long serialVersionUID = 847108303847912642L;

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getCODE() {
		return CODE;
	}

	public void setCODE(String code) {
		CODE = code;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getGROUP_NAME() {
		return GROUP_NAME;
	}

	public void setGROUP_NAME(String group_name) {
		GROUP_NAME = group_name;
	}

}
