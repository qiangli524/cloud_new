package com.sitech.basd.sxcloud.cloud.domain.net;

import java.io.Serializable;
import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;

@SuppressWarnings("serial")
public class TbCloud2IpInfoObj extends BasePrivilegeObj implements Serializable, Cloneable {
	private String IP_ID; // IP地址编号
	private String ISUSED; // 是否可用
	private String ISBLOCKED; // 是否阻塞
	private String IPADDRESS; // IP地址
	private String NET_ID; // 网络ID
	private String INS_DATE; // 注册时间
	private String NAME; // 网络名称
	private String STARTIPADDRESS; // IP开始地址 为某个网络增加IP段
	private String ENDIPADDRESS; // IP结束地址 为某个网络增加IP段
	private String IP_TYPE;

	private List<String> netList;

	public List<String> getNetList() {
		return netList;
	}

	public void setNetList(List<String> netList) {
		this.netList = netList;
	}

	public String getIP_TYPE() {
		return IP_TYPE;
	}

	public void setIP_TYPE(String ip_type) {
		IP_TYPE = ip_type;
	}

	public String getIP_ID() {
		return this.IP_ID;
	}

	public void setIP_ID(String ip_id) {
		this.IP_ID = ip_id;
	}

	public String getISUSED() {
		return this.ISUSED;
	}

	public void setISUSED(String isused) {
		this.ISUSED = isused;
	}

	public String getISBLOCKED() {
		return this.ISBLOCKED;
	}

	public void setISBLOCKED(String isblocked) {
		this.ISBLOCKED = isblocked;
	}

	public String getIPADDRESS() {
		return this.IPADDRESS;
	}

	public void setIPADDRESS(String ipaddress) {
		this.IPADDRESS = ipaddress;
	}

	public String getNET_ID() {
		return this.NET_ID;
	}

	public void setNET_ID(String net_id) {
		this.NET_ID = net_id;
	}

	public String getINS_DATE() {
		return this.INS_DATE;
	}

	public void setINS_DATE(String ins_date) {
		this.INS_DATE = ins_date;
	}

	public String getNAME() {
		return this.NAME;
	}

	public void setNAME(String name) {
		this.NAME = name;
	}

	public String getSTARTIPADDRESS() {
		return this.STARTIPADDRESS;
	}

	public void setSTARTIPADDRESS(String startipaddress) {
		this.STARTIPADDRESS = startipaddress;
	}

	public String getENDIPADDRESS() {
		return this.ENDIPADDRESS;
	}

	public void setENDIPADDRESS(String endipaddress) {
		this.ENDIPADDRESS = endipaddress;
	}

}
