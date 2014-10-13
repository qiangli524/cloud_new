package com.sitech.basd.cloud3.domain.departproject;

import java.io.File;
import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;

public class DepartProjectObj extends BasePrivilegeObj {
	private String ID;// 主键
	private String PROJECT_NO; // 项目编号
	private String PROJECT_NAME; // 项目名称
	private Integer CPU_COUNT; // CPU预分配总量
	private Integer MEMORY_SIZE; // 内存预分配总量
	private Integer STORAGE_SIZE; // 存储预分配总量
	private Integer IP_COUNT; // IP预分配总量
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
	private String vmCount;// 虚拟机个数
	private File basisObj;// 附件对象，用来辅助上传的字段
	private String basis;// 附件路径

	private String DEPART_ID;// 部门id
	private String DEPART_NAME;// 部门名称

	private String NETNAME;// 网络域名称
	private String PROJECT_LEADERNAME;// 项目负责人名称
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	public String getPROJECT_NO() {
		return PROJECT_NO;
	}

	public void setPROJECT_NO(String pROJECT_NO) {
		PROJECT_NO = pROJECT_NO;
	}
	public String getPROJECT_NAME() {
		return PROJECT_NAME;
	}

	public void setPROJECT_NAME(String pROJECT_NAME) {
		PROJECT_NAME = pROJECT_NAME;
	}

	public Integer getCPU_COUNT() {
		return CPU_COUNT;
	}

	public void setCPU_COUNT(Integer cPU_COUNT) {
		CPU_COUNT = cPU_COUNT;
	}

	public Integer getMEMORY_SIZE() {
		return MEMORY_SIZE;
	}

	public void setMEMORY_SIZE(Integer mEMORY_SIZE) {
		MEMORY_SIZE = mEMORY_SIZE;
	}

	public Integer getSTORAGE_SIZE() {
		return STORAGE_SIZE;
	}

	public void setSTORAGE_SIZE(Integer sTORAGE_SIZE) {
		STORAGE_SIZE = sTORAGE_SIZE;
	}

	public Integer getIP_COUNT() {
		return IP_COUNT;
	}

	public void setIP_COUNT(Integer iP_COUNT) {
		IP_COUNT = iP_COUNT;
	}
	public String getPROJECT_LEADER() {
		return PROJECT_LEADER;
	}

	public void setPROJECT_LEADER(String pROJECT_LEADER) {
		PROJECT_LEADER = pROJECT_LEADER;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	public String getCONTACT_PHONE() {
		return CONTACT_PHONE;
	}

	public void setCONTACT_PHONE(String cONTACT_PHONE) {
		CONTACT_PHONE = cONTACT_PHONE;
	}
	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getNETWORK_DOMAIN() {
		return NETWORK_DOMAIN;
	}

	public void setNETWORK_DOMAIN(String nETWORK_DOMAIN) {
		NETWORK_DOMAIN = nETWORK_DOMAIN;
	}

	public String getNetid() {
		return netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}
	public String getUPDATE_USER() {
		return UPDATE_USER;
	}

	public void setUPDATE_USER(String uPDATE_USER) {
		UPDATE_USER = uPDATE_USER;
	}
	public String getUPDATE_TIME() {
		return UPDATE_TIME;
	}

	public void setUPDATE_TIME(String uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}
	public String getCREATED_USER() {
		return CREATED_USER;
	}

	public void setCREATED_USER(String cREATED_USER) {
		CREATED_USER = cREATED_USER;
	}
	public String getCREATED_TIME() {
		return CREATED_TIME;
	}

	public void setCREATED_TIME(String cREATED_TIME) {
		CREATED_TIME = cREATED_TIME;
	}

	public String getDELETE_BY() {
		return DELETE_BY;
	}

	public void setDELETE_BY(String dELETE_BY) {
		DELETE_BY = dELETE_BY;
	}

	public String getDELETE_TIME() {
		return DELETE_TIME;
	}

	public void setDELETE_TIME(String dELETE_TIME) {
		DELETE_TIME = dELETE_TIME;
	}
	public String getSTRIDS() {
		return STRIDS;
	}

	public void setSTRIDS(String sTRIDS) {
		STRIDS = sTRIDS;
	}
	public List getDepartProjectList() {
		return departProjectList;
	}
	public void setDepartProjectList(List departProjectList) {
		this.departProjectList = departProjectList;
	}
	public String getVmCount() {
		return vmCount;
	}
	public void setVmCount(String vmCount) {
		this.vmCount = vmCount;
	}
	public File getBasisObj() {
		return basisObj;
	}
	public void setBasisObj(File basisObj) {
		this.basisObj = basisObj;
	}
	public String getBasis() {
		return basis;
	}
	public void setBasis(String basis) {
		this.basis = basis;
	}
	public String getDEPART_ID() {
		return DEPART_ID;
	}
	public void setDEPART_ID(String dEPART_ID) {
		DEPART_ID = dEPART_ID;
	}
	public String getDEPART_NAME() {
		return DEPART_NAME;
	}
	public void setDEPART_NAME(String dEPART_NAME) {
		DEPART_NAME = dEPART_NAME;
	}

	public String getNETNAME() {
		return NETNAME;
	}

	public void setNETNAME(String nETNAME) {
		NETNAME = nETNAME;
	}

	public String getPROJECT_LEADERNAME() {
		return PROJECT_LEADERNAME;
	}

	public void setPROJECT_LEADERNAME(String pROJECT_LEADERNAME) {
		PROJECT_LEADERNAME = pROJECT_LEADERNAME;
	}

}
