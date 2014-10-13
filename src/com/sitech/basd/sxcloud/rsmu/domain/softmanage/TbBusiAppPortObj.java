package com.sitech.basd.sxcloud.rsmu.domain.softmanage;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiAppPortObj extends BaseObj implements Serializable, Cloneable{

	private int ID;
	private int APPID;
	private String PORT;
	private int ISDEFAULT;
	
	public int getAPPID() {
		return APPID;
	}
	public void setAPPID(int appid) {
		APPID = appid;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public int getISDEFAULT() {
		return ISDEFAULT;
	}
	public void setISDEFAULT(int isdefault) {
		ISDEFAULT = isdefault;
	}
	public String getPORT() {
		return PORT;
	}
	public void setPORT(String port) {
		PORT = port;
	}
	
	
}
