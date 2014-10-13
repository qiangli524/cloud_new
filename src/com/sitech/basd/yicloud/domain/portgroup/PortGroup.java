package com.sitech.basd.yicloud.domain.portgroup;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class PortGroup extends BaseObj{
	public int id;
	public String name;
	public int vlanId;
	public String vswitchName;
	public int vswitchId;
	public String type; //0:普通端口组，1：分布式端口组
	public String pguuid;
	public int numVm;
	
	public int getNumVm() {
		return numVm;
	}

	public void setNumVm(int numVm) {
		this.numVm = numVm;
	}

	public String getPguuid() {
		return pguuid;
	}

	public void setPguuid(String pguuid) {
		this.pguuid = pguuid;
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

	public String getVswitchName() {
		return vswitchName;
	}

	public void setVswitchName(String vswitchName) {
		this.vswitchName = vswitchName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVswitchId() {
		return vswitchId;
	}

	public void setVswitchId(int vswitchId) {
		this.vswitchId = vswitchId;
	}
}
