package com.sitech.basd.yicloud.domain.opersystem;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * Title: TbYicloudBusinessAppObj Description: 操作系统类 Company: SI-TECH Copyright:
 * Copyright (c)2012
 * 
 * @author duangh
 * @version 1.0
 */
public class OperSystemObj extends BaseObj {
	private int ID; // 编号
	private String NAME; // 操作系统名称
	private String VERSION; // 操作系统版本
	private String REMARK; // 备注
	private String UPDATETIME; // 上传时间
	private String TYPE;// 操作系统类型；0:物理主机；1：虚拟机
	private String PARTH;// 操作系统保存路径
	private String OPERTYPE; // 操作系统类型,0是Unix,1是Linux,2是hplinux,3是aix，4是windows

	public String getOPERTYPE() {
		return OPERTYPE;
	}

	public void setOPERTYPE(String opertype) {
		OPERTYPE = opertype;
	}

	public String getPARTH() {
		return PARTH;
	}

	public void setPARTH(String parth) {
		PARTH = parth;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getVERSION() {
		return VERSION;
	}

	public void setVERSION(String version) {
		VERSION = version;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

}
