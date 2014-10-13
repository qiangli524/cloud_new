package com.sitech.basd.sxcloud.cloud.domain.temconfig;

import java.io.Serializable;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TempletTypeConfigObj extends BaseObj implements Serializable, Cloneable {
	private String ID;
	private String TYPE_NAME;
	private String TYPE_DESC;
	private String TYPE;
	private int flag; //保存修改类型
	private List resultList; //保存返回的列表信息
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public String getTYPE_NAME() {
		return TYPE_NAME;
	}
	public void setTYPE_NAME(String type_name) {
		TYPE_NAME = type_name;
	}
	public String getTYPE_DESC() {
		return TYPE_DESC;
	}
	public void setTYPE_DESC(String type_desc) {
		TYPE_DESC = type_desc;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String type) {
		TYPE = type;
	}
	
}
