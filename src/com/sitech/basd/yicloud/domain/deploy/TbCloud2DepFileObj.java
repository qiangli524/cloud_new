package com.sitech.basd.yicloud.domain.deploy;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbCloud2DepFileObj extends BaseObj implements Serializable, Cloneable {
	private int ID;
	private String HOSTIP;
	private int APP_ID;
	private int FILE_ID; 
	private int STATUS;
	
	public int getAPP_ID() {
		return APP_ID;
	}
	public void setAPP_ID(int app_id) {
		APP_ID = app_id;
	}
	public int getFILE_ID() {
		return FILE_ID;
	}
	public void setFILE_ID(int file_id) {
		FILE_ID = file_id;
	}
	public String getHOSTIP() {
		return HOSTIP;
	}
	public void setHOSTIP(String hostip) {
		HOSTIP = hostip;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public int getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(int status) {
		STATUS = status;
	} 
}
