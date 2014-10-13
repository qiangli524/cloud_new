package com.sitech.basd.sxcloud.cloud.domain.project;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbCloud2ProjectInfoObj extends BaseObj implements Serializable,
		Cloneable {
	private String ID; // 项目用户关联表中的ID
	private String PROJECT_ID; // 项目编号
	private String NAME; // 项目名称
	private String DESCRIPTION; // 项目描述
	private String ISDEFAULT; // 是否缺省
	private String ISSTAGING; // 是否基础
	private String ISPUBLIC; // 是否公用
	private String USER_ID; // 用户ID
	private String USER_ROLE; // 用户在项目中的角色
	private String USER_STATUS; // 用户是否被选择的状态
	/** 用户状态和ID拼接字符串a */
	private String USER_STAT_ID;

	public String getUSER_STAT_ID() {
		return USER_STAT_ID;
	}

	public void setUSER_STAT_ID(String user_stat_id) {
		USER_STAT_ID = user_stat_id;
	}

	public String getUSER_STATUS() {
		return USER_STATUS;
	}

	public void setUSER_STATUS(String user_status) {
		USER_STATUS = user_status;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String description) {
		DESCRIPTION = description;
	}

	public String getISDEFAULT() {
		return ISDEFAULT;
	}

	public void setISDEFAULT(String isdefault) {
		ISDEFAULT = isdefault;
	}

	public String getISPUBLIC() {
		return ISPUBLIC;
	}

	public void setISPUBLIC(String ispublic) {
		ISPUBLIC = ispublic;
	}

	public String getISSTAGING() {
		return ISSTAGING;
	}

	public void setISSTAGING(String isstaging) {
		ISSTAGING = isstaging;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getPROJECT_ID() {
		return PROJECT_ID;
	}

	public void setPROJECT_ID(String project_id) {
		PROJECT_ID = project_id;
	}

	public String getUSER_ROLE() {
		return USER_ROLE;
	}

	public void setUSER_ROLE(String user_role) {
		USER_ROLE = user_role;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String user_id) {
		USER_ID = user_id;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

}
