package com.sitech.basd.yicloud.domain.middleware;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * Title: TbYicloudBusinessAppObj Description: 中间件类 Company: SI-TECH Copyright:
 * Copyright (c)2012
 * 
 * @author duangh
 * @version 1.0
 */
public class MiddlewareObj extends BaseObj {
	private int ID; // 编号
	private String NAME; // 中间件名称
	private String VERSION; // 中间件版本
	private String REMARK; // 备注
	private String UPDATETIME; // 上传时间
	private String PARTH; // 中间件保存路径

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

}
