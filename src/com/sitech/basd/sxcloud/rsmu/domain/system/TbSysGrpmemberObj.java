package com.sitech.basd.sxcloud.rsmu.domain.system;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbSysGrpmemberObj extends BaseObj implements Serializable,
		Cloneable {
	private int USERID = 0;
	private int GROUPID = 0;
	private String USERNAME;

	public int getGROUPID() {
		return GROUPID;
	}

	public void setGROUPID(int groupid) {
		GROUPID = groupid;
	}

	public int getUSERID() {
		return USERID;
	}

	public void setUSERID(int userid) {
		USERID = userid;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String username) {
		USERNAME = username;
	}

}
