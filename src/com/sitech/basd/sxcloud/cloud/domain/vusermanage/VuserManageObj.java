package com.sitech.basd.sxcloud.cloud.domain.vusermanage;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class VuserManageObj extends BaseObj implements Serializable, Cloneable {

	private String USER_ID; // 用户编号
	private String USER_NAME; // 用户名
	private String NAME; // 名字
	private String EMAILNOTIFICATIONS; // 邮件通知
	private String EMAIL; // 邮箱
	private String ISAPPROVER; // 是否审核人
	private String ISADMIN; // 是否管理员
	private String ROLE; // 角色
	private String INS_DATE; // 入库时间
	private String MOBILE; // 电话
	private String ACCOUNT; // 账户
	private int ID; // 编号
	private String PASSWORD; // 密码
	private String STATUS; // 状态
	private String CREATETIME; // 创建时间
	private String CREATEUSER; // 创建用户
	private String DATAAUTHORITY; // 应用编号集
	private String USER_ROLE; // 用户在项目中的角色
	private String USER_STATUS; // 用户在项目中是否被选中的状态
	private String PRO_ID; // 用户在项目用户关联表中的ID
	private String PROJECT_ID; // 项目用户关联表中的项目的ID

	public String getPROJECT_ID() {
		return PROJECT_ID;
	}

	public void setPROJECT_ID(String project_id) {
		PROJECT_ID = project_id;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String password) {
		PASSWORD = password;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String status) {
		STATUS = status;
	}

	public String getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(String createtime) {
		CREATETIME = createtime;
	}

	public String getCREATEUSER() {
		return CREATEUSER;
	}

	public void setCREATEUSER(String createuser) {
		CREATEUSER = createuser;
	}

	public String getDATAAUTHORITY() {
		return DATAAUTHORITY;
	}

	public void setDATAAUTHORITY(String dataauthority) {
		DATAAUTHORITY = dataauthority;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getACCOUNT() {
		return ACCOUNT;
	}

	public void setACCOUNT(String account) {
		ACCOUNT = account;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mobile) {
		MOBILE = mobile;
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

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getEMAILNOTIFICATIONS() {
		return EMAILNOTIFICATIONS;
	}

	public void setEMAILNOTIFICATIONS(String emailnotifications) {
		EMAILNOTIFICATIONS = emailnotifications;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String email) {
		EMAIL = email;
	}

	public String getISAPPROVER() {
		return ISAPPROVER;
	}

	public void setISAPPROVER(String isapprover) {
		ISAPPROVER = isapprover;
	}

	public String getISADMIN() {
		return ISADMIN;
	}

	public void setISADMIN(String isadmin) {
		ISADMIN = isadmin;
	}

	public String getROLE() {
		return ROLE;
	}

	public void setROLE(String role) {
		ROLE = role;
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

	public String getUSER_STATUS() {
		return USER_STATUS;
	}

	public void setUSER_STATUS(String user_status) {
		USER_STATUS = user_status;
	}

	public String getPRO_ID() {
		return PRO_ID;
	}

	public void setPRO_ID(String pro_id) {
		PRO_ID = pro_id;
	}

}
