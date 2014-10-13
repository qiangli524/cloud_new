package com.sitech.basd.sxcloud.cloud.web.virtual.form;

import java.util.List;

@SuppressWarnings("serial")
public class VirtualPoolForm {

	private String VH_ID; // 虚拟机编号
	private String VH_NAME; // 虚拟机名称
	private String VH_TYPE; // 虚拟机类型
	private String VH_IP; // 虚拟机IP
	private String PROTOCOL; // 监控类型
	private String PORT; // 端口
	private String eq_id;// 所属物理主机编号
	private String eq_ip;//物理主机IP地址
	public String getEq_ip() {
		return eq_ip;
	}

	public void setEq_ip(String eq_ip) {
		this.eq_ip = eq_ip;
	}

	private List eqidList; // 物理主机编号下拉列表
	private int flag;
	private List resultList = null;

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getVH_NAME() {
		return VH_NAME;
	}

	public void setVH_NAME(String vh_name) {
		VH_NAME = vh_name;
	}

	public String getVH_ID() {
		return VH_ID;
	}

	public void setVH_ID(String vh_id) {
		VH_ID = vh_id;
	}

	public String getVH_TYPE() {
		return VH_TYPE;
	}

	public void setVH_TYPE(String vh_type) {
		VH_TYPE = vh_type;
	}

	public String getVH_IP() {
		return VH_IP;
	}

	public void setVH_IP(String vh_ip) {
		VH_IP = vh_ip;
	}

	public String getPROTOCOL() {
		return PROTOCOL;
	}

	public void setPROTOCOL(String protocol) {
		PROTOCOL = protocol;
	}

	public String getPORT() {
		return PORT;
	}

	public void setPORT(String port) {
		PORT = port;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}

	public List getEqidList() {
		return eqidList;
	}

	public void setEqidList(List eqidList) {
		this.eqidList = eqidList;
	}

}
