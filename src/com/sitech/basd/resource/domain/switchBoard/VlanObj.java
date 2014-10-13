package com.sitech.basd.resource.domain.switchBoard;

import com.sitech.basd.common.domain.BasePrivilegeObj;
import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class VlanObj extends BasePrivilegeObj {
	private String id;
	private String vlan_id; // vlan编号
	private String vlan_name; // vlan名称
	private String vlan_status; // vlan状态 1Dynamic 0static
	private String switch_id; // vlan所属交换机编号
	private String IP_ID;// ip表中主键
	private String IPADDRESS;// IP地址

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVlan_id() {
		return vlan_id;
	}

	public void setVlan_id(String vlan_id) {
		this.vlan_id = vlan_id;
	}

	public String getVlan_name() {
		return vlan_name;
	}

	public void setVlan_name(String vlan_name) {
		this.vlan_name = vlan_name;
	}

	public String getVlan_status() {
		return vlan_status;
	}

	public void setVlan_status(String vlan_status) {
		this.vlan_status = vlan_status;
	}

	public String getSwitch_id() {
		return switch_id;
	}

	public void setSwitch_id(String switch_id) {
		this.switch_id = switch_id;
	}

	public String getIP_ID() {
		return IP_ID;
	}

	public void setIP_ID(String iP_ID) {
		IP_ID = iP_ID;
	}

	public String getIPADDRESS() {
		return IPADDRESS;
	}

	public void setIPADDRESS(String iPADDRESS) {
		IPADDRESS = iPADDRESS;
	}


}