package com.sitech.basd.sxcloud.cloud.web.vusermanage.form;

import java.util.List;

@SuppressWarnings("serial")
public class VuserManageForm {

	private String USER_ID; // 用户编号
	private String USER_NAME; // 用户名
	private String NAME; // 名字
	private String EMAILNOTIFICATIONS; // 邮件通知
	private String EMAIL; // 邮箱
	private String ISAPPROVER; // 是否审核人
	private String ISADMIN; // 是否管理员
	private String ROLE; // 角色
	private String INS_DATE; // 入库时间
	@SuppressWarnings("unused")
	private String MOBILE; // 电话
	@SuppressWarnings("unused")
	private String ACCOUNT; // 账户
	private int ID; // 编号
	private String PASSWORD; // 密码
	private String STATUS; // 状态
	private String CREATETIME; // 创建时间
	private String CREATEUSER; // 创建用户
	private String DATAAUTHORITY; // 应用编号集
	@SuppressWarnings("unchecked")
	private List resultList = null;
	@SuppressWarnings("unchecked")
	private List userList = null;
	private int flag = 0;

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

	@SuppressWarnings("unchecked")
	public List getUserList() {
		return userList;
	}

	@SuppressWarnings("unchecked")
	public void setUserList(List userList) {
		this.userList = userList;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
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
}
