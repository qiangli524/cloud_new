package com.sitech.basd.sxcloud.rsmu.web.hostmanage.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


@SuppressWarnings("serial")
public class BusiHostForm{
	 private int  ID               ;    //编号
	 private String  IP               ;    //主机IP
	 private String  IP_BF;				  //主机IP备份字段  外加
	 private String  HOSTNAME         ;    //主机名称
	 private String  UPDATETIME       ;    //更新时间
	 private String  STATUS           	  ;    //0：不可用，1：可用，这个字段页面设置修改用
	 private String  FREQUENCY        ;    //主机主频
	 private String  MEMORY           ;    //内存
	 private String  OPERATE_SYSTEM   ;    //操作系统
	 private String  MANUFACTURERS    ;    //主机厂商
	 private String  VLAN;
	 private int  HOSTPORT;
	 private List resultList ;
	 private String strs;
	 private String appid;   //应用编号
	 private String VLANIP;					//vlanip
	 private String VLAN_BF;				//
	 private String MAC;			//主机mac地址
	 private List resultList1 ;
	 private List resultList2 ;
	 private List resultList3 ;
	 private List resultList4 ;

	public String getFREQUENCY() {
		return FREQUENCY;
	}

	public void setFREQUENCY(String frequency) {
		FREQUENCY = frequency;
	}

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}

	public String getIP_BF() {
		return IP_BF;
	}

	public void setIP_BF(String ip_bf) {
		IP_BF = ip_bf;
	}

	public String getMANUFACTURERS() {
		return MANUFACTURERS;
	}

	public void setMANUFACTURERS(String manufacturers) {
		MANUFACTURERS = manufacturers;
	}

	public String getMEMORY() {
		return MEMORY;
	}

	public void setMEMORY(String memory) {
		MEMORY = memory;
	}

	public String getOPERATE_SYSTEM() {
		return OPERATE_SYSTEM;
	}

	public void setOPERATE_SYSTEM(String operate_system) {
		OPERATE_SYSTEM = operate_system;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String status) {
		STATUS = status;
	}

	public String getStrs() {
		return strs;
	}

	public void setStrs(String strs) {
		this.strs = strs;
	}

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
	}

	public int getHOSTPORT() {
		return HOSTPORT;
	}

	public void setHOSTPORT(int hostport) {
		HOSTPORT = hostport;
	}

	public String getVLAN() {
		return VLAN;
	}

	public void setVLAN(String vlan) {
		VLAN = vlan;
	}
	
	public String getVLANIP() {
		return VLANIP;
	}

	public void setVLANIP(String vLANIP) {
		VLANIP = vLANIP;
	}
	
	public String getVLAN_BF() {
		return VLAN_BF;
	}

	public void setVLAN_BF(String vLANBF) {
		VLAN_BF = vLANBF;
	}
	
	

	public List getResultList1() {
		return resultList1;
	}

	public void setResultList1(List resultList1) {
		this.resultList1 = resultList1;
	}

	public List getResultList2() {
		return resultList2;
	}

	public void setResultList2(List resultList2) {
		this.resultList2 = resultList2;
	}

	public List getResultList3() {
		return resultList3;
	}

	public void setResultList3(List resultList3) {
		this.resultList3 = resultList3;
	}

	public List getResultList4() {
		return resultList4;
	}

	public void setResultList4(List resultList4) {
		this.resultList4 = resultList4;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mac) {
		MAC = mac;
	}

	

	
}
