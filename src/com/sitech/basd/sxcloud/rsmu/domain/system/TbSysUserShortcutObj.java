package com.sitech.basd.sxcloud.rsmu.domain.system;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbSysUserShortcutObj extends BaseObj implements Serializable,
		Cloneable {
	private int ID;
	private int USERID;
	private int FUNCID;
	private String SUBMIT_TIME;

	public int getFUNCID() {
		return FUNCID;
	}

	public void setFUNCID(int funcid) {
		FUNCID = funcid;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getSUBMIT_TIME() {
		return SUBMIT_TIME;
	}

	public void setSUBMIT_TIME(String submit_time) {
		SUBMIT_TIME = submit_time;
	}

	public int getUSERID() {
		return USERID;
	}

	public void setUSERID(int userid) {
		USERID = userid;
	}

}
