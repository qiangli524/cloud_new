package com.sitech.basd.sxcloud.cloud.web.net.form;

import java.util.List;

import domain.TbDirectoryCodeVO;

@SuppressWarnings("serial")
public class NetForm {

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
	private String USEDHCP = "1"; // 是否使用DHCP
	private String WORKGROUP; // 工作组
	private String DOMAINSUFFIXES; // 域后缀搜索列表
	private String COMPUTERNAMEPREFIX; // 计算机名前缀
	private String PROJECT_ID; // 所属项目编号
	private String NET_TYPE; // 5业务网络 6、管理网络 7、NAS网络
	private String netList = null; // 网络列表
	@SuppressWarnings("unused")
	private boolean chooseIpm;
	@SuppressWarnings("unchecked")
	private List resultList = null; // 查询列表
	private String QUERY_NAME;
	private String QUERY_NET_TYPE;
	private String flag = null; // 增、改标识
	private String VLAN_ID;
	// 域列表，配置数据
	private List<TbDirectoryCodeVO> domainList;

	public List<TbDirectoryCodeVO> getDomainList() {
		return domainList;
	}

	public void setDomainList(List<TbDirectoryCodeVO> domainList) {
		this.domainList = domainList;
	}

	public String getQUERY_NAME() {
		return QUERY_NAME;
	}

	public void setQUERY_NAME(String qUERYNAME) {
		QUERY_NAME = qUERYNAME;
	}

	public String getQUERY_NET_TYPE() {
		return QUERY_NET_TYPE;
	}

	public void setQUERY_NET_TYPE(String qUERYNETTYPE) {
		QUERY_NET_TYPE = qUERYNETTYPE;
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

	@SuppressWarnings("unchecked")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public void setResultList(List resultList) {
		this.resultList = resultList;
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

	public String getNetList() {
		return netList;
	}

	public void setNetList(String netList) {
		this.netList = netList;
	}

	public boolean isChooseIpm() {
		return chooseIpm;
	}

	public void setChooseIpm(boolean chooseIpm) {
		this.chooseIpm = chooseIpm;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getVLAN_ID() {
		return VLAN_ID;
	}

	public void setVLAN_ID(String vLAN_ID) {
		VLAN_ID = vLAN_ID;
	}

}
