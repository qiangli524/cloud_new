package com.sitech.basd.sxcloud.rsmu.domain.system;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TempSysUserShortctObj extends BaseObj implements Serializable,
		Cloneable {
	private int USERID;
	private int FUNCID;

	public int getFUNCID() {
		return FUNCID;
	}

	public void setFUNCID(int funcid) {
		FUNCID = funcid;
	}

	public int getUSERID() {
		return USERID;
	}

	public void setUSERID(int userid) {
		USERID = userid;
	}

}
