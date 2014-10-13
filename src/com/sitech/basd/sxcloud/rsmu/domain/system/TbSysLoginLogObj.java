package com.sitech.basd.sxcloud.rsmu.domain.system;

import java.io.Serializable;

import com.sitech.basd.common.domain.BasePrivilegeObj;

@SuppressWarnings("serial")
public class TbSysLoginLogObj extends BasePrivilegeObj implements Serializable, Cloneable {
	private int USERID;
	private int ID = 0;
	private String ACCOUNT;
	private String IP;
	private String HOSTNAME;
	private String LOGINTIME;
	private String LOGOUTTIME;
	private String RESULT;
	private String REMARK;
	private String start_logintime;// 登录时间 开始
	private String end_logintime; // 登录时间 结束

	public String getStart_logintime() {
		return start_logintime;
	}

	public void setStart_logintime(String start_logintime) {
		this.start_logintime = start_logintime;
	}

	public String getEnd_logintime() {
		return end_logintime;
	}

	public void setEnd_logintime(String end_logintime) {
		this.end_logintime = end_logintime;
	}

	public String getACCOUNT() {
		return ACCOUNT;
	}

	public void setACCOUNT(String account) {
		ACCOUNT = account;
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

	public String getLOGINTIME() {
		return LOGINTIME;
	}

	public void setLOGINTIME(String logintime) {
		LOGINTIME = logintime;
	}

	public String getLOGOUTTIME() {
		return LOGOUTTIME;
	}

	public void setLOGOUTTIME(String logouttime) {
		LOGOUTTIME = logouttime;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

	public String getRESULT() {
		return RESULT;
	}

	public void setRESULT(String result) {
		RESULT = result;
	}

	public int getUSERID() {
		return USERID;
	}

	public void setUSERID(int userid) {
		USERID = userid;
	}

}
