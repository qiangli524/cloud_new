package com.sitech.basd.sxcloud.cloud.web.virtual.form;

import java.util.List;

@SuppressWarnings("serial")
public class TbCloud2VirtualConfigForm {

	private int ID; // 编号
	private String HOSTID; // 主机编号
	private String VH_ID; // 虚拟机编号
	private int TYPE; // 用户类型
	private String HOSTUSERNAME; // 主机用户名
	private String HOSTUSERNAME_BF; // 主机用户名备份字段 外加
	private String HOSRPWD; // 主机密码
	private String SPACE; // 主机磁盘空间
	private String UPDATETTIME; // 时间
	private String IP;
	private int flag;
	private List resultList;
	private String HOSTNAME; // 主机名称
	private List busihostList;
	private int TYPE_BF; // 用户类型备份
	private int STATUS;// 用户状态

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int status) {
		STATUS = status;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(String hOSTID) {
		HOSTID = hOSTID;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int tYPE) {
		TYPE = tYPE;
	}

	public String getHOSTUSERNAME() {
		return HOSTUSERNAME;
	}

	public void setHOSTUSERNAME(String hOSTUSERNAME) {
		HOSTUSERNAME = hOSTUSERNAME;
	}

	public String getHOSRPWD() {
		return HOSRPWD;
	}

	public void setHOSRPWD(String hOSRPWD) {
		HOSRPWD = hOSRPWD;
	}

	public String getSPACE() {
		return SPACE;
	}

	public void setSPACE(String sPACE) {
		SPACE = sPACE;
	}

	public String getUPDATETTIME() {
		return UPDATETTIME;
	}

	public void setUPDATETTIME(String uPDATETTIME) {
		UPDATETTIME = uPDATETTIME;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hOSTNAME) {
		HOSTNAME = hOSTNAME;
	}

	public List getBusihostList() {
		return busihostList;
	}

	public void setBusihostList(List busihostList) {
		this.busihostList = busihostList;
	}

	public String getHOSTUSERNAME_BF() {
		return HOSTUSERNAME_BF;
	}

	public void setHOSTUSERNAME_BF(String hostusername_bf) {
		HOSTUSERNAME_BF = hostusername_bf;
	}

	public int getTYPE_BF() {
		return TYPE_BF;
	}

	public void setTYPE_BF(int tYPEBF) {
		TYPE_BF = tYPEBF;
	}

	public String getVH_ID() {
		return VH_ID;
	}

	public void setVH_ID(String vh_id) {
		VH_ID = vh_id;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
