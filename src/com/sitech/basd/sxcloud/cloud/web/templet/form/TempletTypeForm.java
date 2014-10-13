package com.sitech.basd.sxcloud.cloud.web.templet.form;

import java.util.List;

public class TempletTypeForm {
	private String ID;
	private String NAME;
	private String TYPE;
	private String TYPE_DESC;
	private int flag;	//增加修改的标示
	private List resultList;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String name) {
		NAME = name;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String type) {
		TYPE = type;
	}
	
	public String getTYPE_DESC() {
		return TYPE_DESC;
	}
	public void setTYPE_DESC(String type_desc) {
		TYPE_DESC = type_desc;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	
}
