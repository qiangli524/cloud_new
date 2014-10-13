package com.sitech.basd.sxcloud.cloud.domain.net;

import java.io.Serializable;

import com.sitech.basd.common.domain.BasePrivilegeObj;

@SuppressWarnings("serial")
public class TbCloud2NetInfoObj extends BasePrivilegeObj implements Serializable, Cloneable {
	private String net_uuid;// xen网络uuid
	private String NET_ID; // 网络ID
	private String NAME; // 网络名称
	private String DESCRIPTION; // 网络描述
	private String DOMAIN; // 域名
	private String FREECOUNT; // 可用IP地址
	private String USEDCOUNT; // 已使用IP个数
	private String ISDEFAULT; // 是否缺省网络
	private String GATEWAY1; // 网关地址1
	private String GATEWAY2; // 网关地址2
	private String DNS1; // DNS 1
	private String DNS2; // DNS 2
	private String NETWORKID; // 网络标识
	private String WINS1; // WINS 地址1
	private String WINS2; // WINS 地址2
	private String HOSTNAMEPREFIX; // 主机名前缀
	private String SUBNET; // 子网掩码
	private String USEDHCP; // 是否使用DHCP
	private String WORKGROUP; // 工作组
	private String DOMAINSUFFIXES; // 域后缀搜索列表
	private String COMPUTERNAMEPREFIX; // 计算机名前缀
	private String PROJECT_ID; // 所属项目编号
	private String IBM_NET_ID;//
	private String NET_TYPE; // 网络类型（IBM或KVM）
	private String VLAN_ID; // vlan的唯一标示

	public String getNet_uuid() {
		return net_uuid;
	}

	public void setNet_uuid(String net_uuid) {
		this.net_uuid = net_uuid;
	}

	public String getNET_TYPE() {
		return NET_TYPE;
	}

	public void setNET_TYPE(String net_type) {
		NET_TYPE = net_type;
	}

	public String getCOMPUTERNAMEPREFIX() {
		return COMPUTERNAMEPREFIX;
	}

	public void setCOMPUTERNAMEPREFIX(String computernameprefix) {
		COMPUTERNAMEPREFIX = computernameprefix;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String description) {
		DESCRIPTION = description;
	}

	public String getDNS1() {
		return DNS1;
	}

	public void setDNS1(String dns1) {
		DNS1 = dns1;
	}

	public String getDNS2() {
		return DNS2;
	}

	public void setDNS2(String dns2) {
		DNS2 = dns2;
	}

	public String getDOMAIN() {
		return DOMAIN;
	}

	public void setDOMAIN(String domain) {
		DOMAIN = domain;
	}

	public String getDOMAINSUFFIXES() {
		return DOMAINSUFFIXES;
	}

	public void setDOMAINSUFFIXES(String domainsuffixes) {
		DOMAINSUFFIXES = domainsuffixes;
	}

	public String getFREECOUNT() {
		return FREECOUNT;
	}

	public void setFREECOUNT(String freecount) {
		FREECOUNT = freecount;
	}

	public String getGATEWAY1() {
		return GATEWAY1;
	}

	public void setGATEWAY1(String gateway1) {
		GATEWAY1 = gateway1;
	}

	public String getGATEWAY2() {
		return GATEWAY2;
	}

	public void setGATEWAY2(String gateway2) {
		GATEWAY2 = gateway2;
	}

	public String getHOSTNAMEPREFIX() {
		return HOSTNAMEPREFIX;
	}

	public void setHOSTNAMEPREFIX(String hostnameprefix) {
		HOSTNAMEPREFIX = hostnameprefix;
	}

	public String getISDEFAULT() {
		return ISDEFAULT;
	}

	public void setISDEFAULT(String isdefault) {
		ISDEFAULT = isdefault;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getNET_ID() {
		return NET_ID;
	}

	public void setNET_ID(String net_id) {
		NET_ID = net_id;
	}

	public String getNETWORKID() {
		return NETWORKID;
	}

	public void setNETWORKID(String networkid) {
		NETWORKID = networkid;
	}

	public String getPROJECT_ID() {
		return PROJECT_ID;
	}

	public void setPROJECT_ID(String project_id) {
		PROJECT_ID = project_id;
	}

	public String getSUBNET() {
		return SUBNET;
	}

	public void setSUBNET(String subnet) {
		SUBNET = subnet;
	}

	public String getUSEDCOUNT() {
		return USEDCOUNT;
	}

	public void setUSEDCOUNT(String usedcount) {
		USEDCOUNT = usedcount;
	}

	public String getUSEDHCP() {
		return USEDHCP;
	}

	public void setUSEDHCP(String usedhcp) {
		USEDHCP = usedhcp;
	}

	public String getWINS1() {
		return WINS1;
	}

	public void setWINS1(String wins1) {
		WINS1 = wins1;
	}

	public String getWINS2() {
		return WINS2;
	}

	public void setWINS2(String wins2) {
		WINS2 = wins2;
	}

	public String getWORKGROUP() {
		return WORKGROUP;
	}

	public void setWORKGROUP(String workgroup) {
		WORKGROUP = workgroup;
	}

	public String getIBM_NET_ID() {
		return IBM_NET_ID;
	}

	public void setIBM_NET_ID(String iBM_NET_ID) {
		IBM_NET_ID = iBM_NET_ID;
	}

	public String getVLAN_ID() {
		return VLAN_ID;
	}

	public void setVLAN_ID(String vLAN_ID) {
		VLAN_ID = vLAN_ID;
	}

}
