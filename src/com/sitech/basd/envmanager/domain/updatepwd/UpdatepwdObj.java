package com.sitech.basd.envmanager.domain.updatepwd;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class UpdatepwdObj  extends BaseObj{
	
	private int pwd_id ; //NUMBER,密码ID
	private String IPADDRESS ; //VARCHAR2(30),--IP地址
	private String PWD_USER  ; //VARCHAR2(30),--用户名
	private String PWD_NEW  ; //VARCHAR2(30),--用户密码
	private int DEVICE_ID ; //NUMBER --设备id
	
	public int getPwd_id() {
		return pwd_id;
	}
	public void setPwd_id(int pwd_id) {
		this.pwd_id = pwd_id;
	}
	public String getIPADDRESS() {
		return IPADDRESS;
	}
	public void setIPADDRESS(String ipaddress) {
		IPADDRESS = ipaddress;
	}
	public String getPWD_USER() {
		return PWD_USER;
	}
	public void setPWD_USER(String pwd_user) {
		PWD_USER = pwd_user;
	}
	public String getPWD_NEW() {
		return PWD_NEW;
	}
	public void setPWD_NEW(String pwd_new) {
		PWD_NEW = pwd_new;
	}
	public int getDEVICE_ID() {
		return DEVICE_ID;
	}
	public void setDEVICE_ID(int device_id) {
		DEVICE_ID = device_id;
	}
	
	
	
	
	

}
