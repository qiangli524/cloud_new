package com.sitech.basd.sxcloud.cloud.domain.defend;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class DefendObj extends BaseObj implements Serializable, Cloneable {
	private String HOSTNAME; // 所属物理主机名称
	private int DEFEND_ID; // 防篡改编号
	private String DEFEND_DIR; // 受控目录
	private String HOST_ID; // 所属物理主机编号
	private String INS_DATE; // 更新时间
	private int ENABLE = -1; // 是否生效
	private int ID; // 主机表ID，查询下拉列表使用
	private String HOST_MAC; // 防篡改主机mac地址
	private String PROCESS_ID; // 流程ID：1,定时调度流程2.添加应用流程
	private String MAC; // 主机mac地址
	private String EXCUTE_FLAG;// 执行标记0：未添加，1：已添加 ，2：正在添加
	private String DEFEND_TYPE; // 操作类型 ；0是添加防篡改，1是修改防篡改，2是删除防篡改
	private String APP_ID; // 应用编号
	private int TYPE; // 操作类型。1是添加操作，2是修改操作，3是删除操作
	private String PLATFORM_IP; // 防篡改:平台IP
	private String IP; // 防篡改主机管理IP
	private String PLATFORM_ID; // 防篡改:平台ID-MAC地址
	private static final long serialVersionUID = 5952689219411916553L; // 取消警告标志，同@SuppressWarnings("serial")

	public int getDEFEND_ID() {
		return DEFEND_ID;
	}

	public void setDEFEND_ID(int defend_id) {
		DEFEND_ID = defend_id;
	}

	public String getDEFEND_DIR() {
		return DEFEND_DIR;
	}

	public void setDEFEND_DIR(String defend_dir) {
		DEFEND_DIR = defend_dir;
	}

	public String getHOST_ID() {
		return HOST_ID;
	}

	public void setHOST_ID(String host_id) {
		HOST_ID = host_id;
	}

	public String getINS_DATE() {
		return INS_DATE;
	}

	public void setINS_DATE(String ins_date) {
		INS_DATE = ins_date;
	}

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getHOST_MAC() {
		return HOST_MAC;
	}

	public void setHOST_MAC(String host_mac) {
		HOST_MAC = host_mac;
	}

	public String getPROCESS_ID() {
		return PROCESS_ID;
	}

	public void setPROCESS_ID(String process_id) {
		PROCESS_ID = process_id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mac) {
		MAC = mac;
	}

	public String getEXCUTE_FLAG() {
		return EXCUTE_FLAG;
	}

	public void setEXCUTE_FLAG(String excute_flag) {
		EXCUTE_FLAG = excute_flag;
	}

	public String getDEFEND_TYPE() {
		return DEFEND_TYPE;
	}

	public void setDEFEND_TYPE(String defend_type) {
		DEFEND_TYPE = defend_type;
	}

	public String getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(String app_id) {
		APP_ID = app_id;
	}

	public int getENABLE() {
		return ENABLE;
	}

	public void setENABLE(int enable) {
		ENABLE = enable;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int type) {
		TYPE = type;
	}

	public String getPLATFORM_IP() {
		return PLATFORM_IP;
	}

	public void setPLATFORM_IP(String platform_ip) {
		PLATFORM_IP = platform_ip;
	}

	public String getPLATFORM_ID() {
		return PLATFORM_ID;
	}

	public void setPLATFORM_ID(String platform_id) {
		PLATFORM_ID = platform_id;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}
}
