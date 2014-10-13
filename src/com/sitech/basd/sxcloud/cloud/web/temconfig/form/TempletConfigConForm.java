package com.sitech.basd.sxcloud.cloud.web.temconfig.form;

import java.util.List;

public class TempletConfigConForm {
	private String ID;
	private String KEY;
	private String VALUE;
	private String TYPE;
	@SuppressWarnings("unchecked")
	private List resultList; //保存返回信息
	private int flag;//保存修改类型
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
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tpye) {
		TYPE = tpye;
	}
	@SuppressWarnings("unchecked")
	public List getResultList() {
		return resultList;
	}
	@SuppressWarnings("unchecked")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
}
