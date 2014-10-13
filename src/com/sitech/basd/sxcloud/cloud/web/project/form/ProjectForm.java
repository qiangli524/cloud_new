package com.sitech.basd.sxcloud.cloud.web.project.form;

import java.util.List;

@SuppressWarnings("serial")
public class ProjectForm {
	private String ID; // 项目用户关联表中的id
	private String PROJECT_ID; // 项目编号
	private String NAME; // 项目名称
	private String DESCRIPTION; // 项目描述
	private String ISDEFAULT; // 是否缺省
	private String ISSTAGING; // 是否基础
	private String ISPUBLIC; // 是否公用
	private String USER_ID; // 用户编号
	private String USER_NAME; // 用户名
	private String INS_DATE; // 创建用户的时间
	private String USER_ROLE; // 用户角色
	private String USER_STATUS; // 用户的选择状态
	private String ISADMIN; // 用户是否是管理员
	private int flag = 0;
	/** 用户状态和ID拼接字符串a */
	private String USER_STAT_ID;
	@SuppressWarnings("unchecked")
	private List resultList = null;

	public String getPROJECT_ID() {
		return PROJECT_ID;
	}

	public void setPROJECT_ID(String project_id) {
		PROJECT_ID = project_id;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
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

	public String getISSTAGING() {
		return ISSTAGING;
	}

	public void setISSTAGING(String isstaging) {
		ISSTAGING = isstaging;
	}

	public String getISPUBLIC() {
		return ISPUBLIC;
	}

	public void setISPUBLIC(String ispublic) {
		ISPUBLIC = ispublic;
	}

	@SuppressWarnings("unchecked")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String user_id) {
		USER_ID = user_id;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String user_name) {
		USER_NAME = user_name;
	}

	public String getINS_DATE() {
		return INS_DATE;
	}

	public void setINS_DATE(String ins_date) {
		INS_DATE = ins_date;
	}

	public String getUSER_ROLE() {
		return USER_ROLE;
	}

	public void setUSER_ROLE(String user_role) {
		USER_ROLE = user_role;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getUSER_STATUS() {
		return USER_STATUS;
	}

	public void setUSER_STATUS(String user_status) {
		USER_STATUS = user_status;
	}

	public String getISADMIN() {
		return ISADMIN;
	}

	public void setISADMIN(String isadmin) {
		ISADMIN = isadmin;
	}

	public String getUSER_STAT_ID() {
		return USER_STAT_ID;
	}

	public void setUSER_STAT_ID(String user_stat_id) {
		USER_STAT_ID = user_stat_id;
	}
}
