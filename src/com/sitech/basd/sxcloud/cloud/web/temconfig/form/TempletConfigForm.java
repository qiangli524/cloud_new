package com.sitech.basd.sxcloud.cloud.web.temconfig.form;

import java.util.List;

public class TempletConfigForm {
	private String ID;
	private String TYPE_NAME;
	private String TYPE_DESC;
	private String TYPE;
	private int flag; // 保存修改类型
	private List resultList; //保存返回信息
	public String getID() {
		return ID;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setID(String id) {
		ID = id;
	}
	public String getTYPE_NAME() {
		return TYPE_NAME;
	}
	public void setTYPE_NAME(String tpye_name) {
		TYPE_NAME = tpye_name;
	}
	public String getTYPE_DESC() {
		return TYPE_DESC;
	}
	public void setTYPE_DESC(String tpye_desc) {
		TYPE_DESC = tpye_desc;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tpye) {
		TYPE = tpye;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
}
