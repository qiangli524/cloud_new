package com.sitech.basd.sxcloud.rsmu.domain.system;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class FuncRoleObj extends BaseObj implements Serializable, Cloneable {
	private int USERID;
	private String ACCOUNT;
	private String FUNCID;

	public String getACCOUNT() {
		return ACCOUNT;
	}

	public void setACCOUNT(String account) {
		ACCOUNT = account;
	}

	public String getFUNCID() {
		return FUNCID;
	}

	public void setFUNCID(String funcid) {
		FUNCID = funcid;
	}

	public int getUSERID() {
		return USERID;
	}

	public void setUSERID(int userid) {
		USERID = userid;
	}

}
