package com.sitech.basd.sxcloud.cloud.web.ip.form;

import java.util.List;

@SuppressWarnings("rawtypes")
public class IpForm {

	private String IP_ID; // IP编号
	private String ISUSED; // 是否已用
	private String ISBLOCKED; // 是否锁定
	private String IPADDRESS; // IP地址
	private String INS_DATE; // 时间
	private String NET_ID; // 网络编号
	private String NAME; // 网络名称
	private String STARTIPADDRESS; // IP开始地址 为某个网络增加IP段
	private String ENDIPADDRESS; // IP结束地址 为某个网络增加IP段
	private String QUERYIPADDRESS;
	private String IP_TYPE;// IP地址类型
	private int choose;
	private int flag = 0;
	private List netList = null;
	private List resultList = null;
	private String queryNetId;

	public String getIP_TYPE() {
		return IP_TYPE;
	}

	public void setIP_TYPE(String iP_TYPE) {
		IP_TYPE = iP_TYPE;
	}

	public String getQueryNetId() {
		return queryNetId;
	}

	public void setQueryNetId(String queryNetId) {
		this.queryNetId = queryNetId;
	}

	public String getQUERYIPADDRESS() {
		return QUERYIPADDRESS;
	}

	public void setQUERYIPADDRESS(String queryipaddress) {
		QUERYIPADDRESS = queryipaddress;
	}

	public String getIP_ID() {
		return IP_ID;
	}

	public void setIP_ID(String ip_id) {
		IP_ID = ip_id;
	}

	public String getISUSED() {
		return ISUSED;
	}

	public void setISUSED(String isused) {
		ISUSED = isused;
	}

	public String getISBLOCKED() {
		return ISBLOCKED;
	}

	public void setISBLOCKED(String isblocked) {
		ISBLOCKED = isblocked;
	}

	public String getIPADDRESS() {
		return IPADDRESS;
	}

	public void setIPADDRESS(String ipaddress) {
		IPADDRESS = ipaddress;
	}

	public String getINS_DATE() {
		return INS_DATE;
	}

	public void setINS_DATE(String ins_date) {
		INS_DATE = ins_date;
	}

	public String getNET_ID() {
		return NET_ID;
	}

	public void setNET_ID(String net_id) {
		NET_ID = net_id;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public List getNetList() {
		return netList;
	}

	public void setNetList(List netList) {
		this.netList = netList;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getSTARTIPADDRESS() {
		return STARTIPADDRESS;
	}

	public void setSTARTIPADDRESS(String startipaddress) {
		STARTIPADDRESS = startipaddress;
	}

	public String getENDIPADDRESS() {
		return ENDIPADDRESS;
	}

	public void setENDIPADDRESS(String endipaddress) {
		ENDIPADDRESS = endipaddress;
	}

	public int getChoose() {
		return choose;
	}

	public void setChoose(int choose) {
		this.choose = choose;
	}
}
