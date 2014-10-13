package com.sitech.basd.sxcloud.rsmu.domain.system;

import java.io.Serializable;

import com.sitech.basd.common.domain.BasePrivilegeObj;

@SuppressWarnings("serial")
public class TbSysUsergroupObj extends BasePrivilegeObj implements Serializable, Cloneable {
	private int ID = 0;
	private String GROUPNAME;
	private int STATUS = -1;
	private String REMARK;
	private String CREATETIME;
	private int CREATEUSER;
	private String NAME;
	private int LEADER = 0;
	private String createTime;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getLEADER() {
		return LEADER;
	}

	public void setLEADER(int leader) {
		LEADER = leader;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(String cREATETIME) {
		CREATETIME = cREATETIME;
	}

	public int getCREATEUSER() {
		return CREATEUSER;
	}

	public void setCREATEUSER(int createuser) {
		CREATEUSER = createuser;
	}

	public String getGROUPNAME() {
		return GROUPNAME;
	}

	public void setGROUPNAME(String groupname) {
		GROUPNAME = groupname;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int status) {
		STATUS = status;
	}

}
