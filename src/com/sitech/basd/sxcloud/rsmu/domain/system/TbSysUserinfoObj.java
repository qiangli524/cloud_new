package com.sitech.basd.sxcloud.rsmu.domain.system;

import java.io.Serializable;

import com.sitech.basd.common.domain.BasePrivilegeObj;

public class TbSysUserinfoObj extends BasePrivilegeObj implements Serializable, Cloneable {

	private static final long serialVersionUID = -1606470152806412110L;

	private int ID;
	private String ACCOUNT;
	private String PASSWORD;
	private String NAME;
	private String MOBILE;
	private String EMAIL;
	private int STATUS;
	private String CREATETIME;
	private int CREATEUSER;
	private String DATAAUTHORITY;// 在安徽移动暂时用于存储用户所属部门（为了避免影响系统功能）
	private String GROUP_NAME;// 用户组名称（安徽需求），注意此时用户只能属于一个用户组
	private String groupId;		//用户组ID
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGROUP_NAME() {
		return GROUP_NAME;
	}

	public void setGROUP_NAME(String gROUP_NAME) {
		GROUP_NAME = gROUP_NAME;
	}

	public String getDATAAUTHORITY() {
		return DATAAUTHORITY;
	}

	public void setDATAAUTHORITY(String dATAAUTHORITY) {
		DATAAUTHORITY = dATAAUTHORITY;
	}

	public String getACCOUNT() {
		return ACCOUNT;
	}

	public void setACCOUNT(String account) {
		ACCOUNT = account;
	}

	public String getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(String createtime) {
		CREATETIME = createtime;
	}

	public int getCREATEUSER() {
		return CREATEUSER;
	}

	public void setCREATEUSER(int createuser) {
		CREATEUSER = createuser;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String email) {
		EMAIL = email;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mobile) {
		MOBILE = mobile;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String password) {
		PASSWORD = password;
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
