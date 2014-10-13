package com.sitech.basd.yicloud.domain.dictionary;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;


public class DictionaryObj extends BaseObj implements Serializable, Cloneable {
	
	private int ID;
	private int PARENT_ID;// 上级节点
	private String CODE;
	private String NAME;
	private String GROUP_NAME;
	private String TYPE;// 类型
	private int UPPER_ID;// 上级业务ID
	private int BIND_DEVICE;//
	private String NAME_ZH;
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
	
	public int getPARENT_ID() {
		return PARENT_ID;
	}

	public void setPARENT_ID(int parent_id) {
		PARENT_ID = parent_id;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public int getUPPER_ID() {
		return UPPER_ID;
	}

	public void setUPPER_ID(int upper_id) {
		UPPER_ID = upper_id;
	}

	public int getBIND_DEVICE() {
		return BIND_DEVICE;
	}

	public void setBIND_DEVICE(int bind_device) {
		BIND_DEVICE = bind_device;
	}

	public String getNAME_ZH() {
		return NAME_ZH;
	}

	public void setNAME_ZH(String name_zh) {
		NAME_ZH = name_zh;
	}


}
