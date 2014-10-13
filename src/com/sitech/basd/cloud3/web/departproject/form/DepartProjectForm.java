package com.sitech.basd.cloud3.web.departproject.form;

import java.util.List;

public class DepartProjectForm {
	private String ID;// 主键
	private String PROJECT_NO; // 项目编号
	private String PROJECT_NAME; // 项目名称
	private int CPU_COUNT; // CPU预分配总量
	private int MEMORY_SIZE; // 内存预分配总量
	private int STORAGE_SIZE; // 存储预分配总量
	private int IP_COUNT; // IP预分配总量
	private String PROJECT_LEADER; // 项目总负责人
	private String account;// 项目负责人的账号
	private String CONTACT_PHONE; // 联系人电话
	private String EMAIL; // 邮箱
	private String NETWORK_DOMAIN; // 网络域
	private String netid; // 网络域的ID
	private String UPDATE_USER; // 最后更新人
	private String UPDATE_TIME; // 最后更新时间
	private String CREATED_USER; // 创建人
	private String CREATED_TIME; // 创建时间
	private String DELETE_BY; // 删除人
	private String DELETE_TIME; // 删除时间
	private String STRIDS; // 多条IDS
	private List departProjectList; // 项目信息列表
	private int Flag; // 增改标识

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getPROJECT_NO() {
		return PROJECT_NO;
	}

	public void setPROJECT_NO(String project_no) {
		PROJECT_NO = project_no;
	}

	public String getPROJECT_NAME() {
		return PROJECT_NAME;
	}

	public void setPROJECT_NAME(String project_name) {
		PROJECT_NAME = project_name;
	}

	public int getCPU_COUNT() {
		return CPU_COUNT;
	}

	public void setCPU_COUNT(int cpu_count) {
		CPU_COUNT = cpu_count;
	}

	public int getMEMORY_SIZE() {
		return MEMORY_SIZE;
	}

	public void setMEMORY_SIZE(int memory_size) {
		MEMORY_SIZE = memory_size;
	}

	public int getSTORAGE_SIZE() {
		return STORAGE_SIZE;
	}

	public void setSTORAGE_SIZE(int storage_size) {
		STORAGE_SIZE = storage_size;
	}

	public int getIP_COUNT() {
		return IP_COUNT;
	}

	public void setIP_COUNT(int ip_count) {
		IP_COUNT = ip_count;
	}

	public String getPROJECT_LEADER() {
		return PROJECT_LEADER;
	}

	public void setPROJECT_LEADER(String project_leader) {
		PROJECT_LEADER = project_leader;
	}

	public String getCONTACT_PHONE() {
		return CONTACT_PHONE;
	}

	public void setCONTACT_PHONE(String contact_phone) {
		CONTACT_PHONE = contact_phone;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String email) {
		EMAIL = email;
	}

	public String getNETWORK_DOMAIN() {
		return NETWORK_DOMAIN;
	}

	public void setNETWORK_DOMAIN(String network_domain) {
		NETWORK_DOMAIN = network_domain;
	}

	public String getUPDATE_USER() {
		return UPDATE_USER;
	}

	public void setUPDATE_USER(String update_user) {
		UPDATE_USER = update_user;
	}

	public String getUPDATE_TIME() {
		return UPDATE_TIME;
	}

	public void setUPDATE_TIME(String update_time) {
		UPDATE_TIME = update_time;
	}

	public String getCREATED_USER() {
		return CREATED_USER;
	}

	public void setCREATED_USER(String created_user) {
		CREATED_USER = created_user;
	}

	public String getCREATED_TIME() {
		return CREATED_TIME;
	}

	public void setCREATED_TIME(String created_time) {
		CREATED_TIME = created_time;
	}

	public String getSTRIDS() {
		return STRIDS;
	}

	public void setSTRIDS(String strids) {
		STRIDS = strids;
	}

	public List getDepartProjectList() {
		return departProjectList;
	}

	public void setDepartProjectList(List departProjectList) {
		this.departProjectList = departProjectList;
	}

	public int getFlag() {
		return Flag;
	}

	public void setFlag(int flag) {
		Flag = flag;
	}

	public String getDELETE_BY() {
		return DELETE_BY;
	}

	public void setDELETE_BY(String delete_by) {
		DELETE_BY = delete_by;
	}

	public String getDELETE_TIME() {
		return DELETE_TIME;
	}

	public void setDELETE_TIME(String delete_time) {
		DELETE_TIME = delete_time;
	}

	public String getNetid() {
		return netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
