package com.sitech.basd.sxcloud.cloud.domain.virtual;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: TbCloud2VirtualConfigObj
 * </p>
 * <p>
 * Description: 虚拟机用户信息-属性类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Mar 27, 2012 11:32:03 AM
 * 
 */
public class TbCloud2VirtualConfigObj extends BaseObj implements Serializable,
		Cloneable {
	private int ID; // 编号
	private String HOSTID; // 虚拟机编号
	private int TYPE; // 1:超级管理员；2:普通用户
	private String HOSTUSERNAME; // 虚拟机用户名
	private String HOSTNAME;// 虚拟机名称
	private String HOSRPWD; // 虚拟机密码
	private String SPACE;// 虚拟机磁盘空间
	private String UPDATETTIME; // 更新时间
	private String IP;
	private int STATUS; // 0：创建 1：已创建 2：删除 3：已删除
	private int EXECUTE_FLAG; // 0：未扫描 1：已扫描

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(String hostid) {
		HOSTID = hostid;
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

	public void setHOSTUSERNAME(String hostusername) {
		HOSTUSERNAME = hostusername;
	}

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}

	public String getHOSRPWD() {
		return HOSRPWD;
	}

	public void setHOSRPWD(String hosrpwd) {
		HOSRPWD = hosrpwd;
	}

	public String getSPACE() {
		return SPACE;
	}

	public void setSPACE(String space) {
		SPACE = space;
	}

	public String getUPDATETTIME() {
		return UPDATETTIME;
	}

	public void setUPDATETTIME(String updatettime) {
		UPDATETTIME = updatettime;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int status) {
		STATUS = status;
	}

	public int getEXECUTE_FLAG() {
		return EXECUTE_FLAG;
	}

	public void setEXECUTE_FLAG(int execute_flag) {
		EXECUTE_FLAG = execute_flag;
	}

}
