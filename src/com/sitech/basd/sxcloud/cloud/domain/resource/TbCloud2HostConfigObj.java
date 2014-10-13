package com.sitech.basd.sxcloud.cloud.domain.resource;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbCloud2HostConfigObj extends BaseObj implements Serializable,
		Cloneable {
	private int ID; // 编号
	private String HOSTID; // 主机编号
	private int TYPE; // 1:超级管理员；2:普通用户
	private String HOSTUSERNAME; // 主机用户名
	private String HOSTNAME;// 主机名称
	private String HOSRPWD; // 主机密码
	private String SPACE;// 主机磁盘空间
	private String UPDATETTIME; // 更新时间
	private String IP;
	private int STATUS; // 0：创建 1：已创建 2：删除 3：已删除
	private int EXECUTE_FLAG; // 0：未扫描 1：已扫描
	private String domain;
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}

	public String getUPDATETTIME() {
		return UPDATETTIME;
	}

	public void setUPDATETTIME(String updatettime) {
		UPDATETTIME = updatettime;
	}

	public String getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(String hostid) {
		HOSTID = hostid;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int type) {
		TYPE = type;
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

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hOSTNAME) {
		HOSTNAME = hOSTNAME;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

	public int getEXECUTE_FLAG() {
		return EXECUTE_FLAG;
	}

	public void setEXECUTE_FLAG(int eXECUTEFLAG) {
		EXECUTE_FLAG = eXECUTEFLAG;
	}

}
