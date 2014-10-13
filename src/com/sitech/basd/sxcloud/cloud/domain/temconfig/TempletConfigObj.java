package com.sitech.basd.sxcloud.cloud.domain.temconfig;

import java.io.Serializable;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TempletConfigObj extends BaseObj implements Serializable, Cloneable {
	private String ID;
	private String KEY;
	private String VALUE;
	private String TYPE;
	private int flag;
	@SuppressWarnings("unchecked")
	private List resultList;//保存返回信息
	
	@SuppressWarnings("unchecked")
	public List getResultList() {
		return resultList;
	}
	@SuppressWarnings("unchecked")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
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
	public void setTYPE(String type) {
		TYPE = type;
	}
	
}
