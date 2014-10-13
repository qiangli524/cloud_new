package com.sitech.basd.yicloud.web.portgroup.form;

import java.util.List;

public class PortGroupForm {
	private int id;
	private String name;
	private int vlanId;
	private String type;
	private int vSwitchId;
	private int flag;
	private List resultList;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVlanId() {
		return vlanId;
	}
	public void setVlanId(int vlanId) {
		this.vlanId = vlanId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getVSwitchId() {
		return vSwitchId;
	}
	public void setVSwitchId(int switchId) {
		vSwitchId = switchId;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	
}
