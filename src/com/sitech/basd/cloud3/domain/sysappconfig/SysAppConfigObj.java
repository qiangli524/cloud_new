package com.sitech.basd.cloud3.domain.sysappconfig;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>Title: SysAppConfigObj</p>
 * <p>Description: 基准应用环境检测</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-27 上午10:52:19
 *
 */
public class SysAppConfigObj extends BaseObj{
	
	private Integer CONFIG_ID;//基准应用编号 
	
	private String SYS_ORDER;//操作系统要求：
	
	private String JDK_ORDER;//JDK要求：
	
	private String USER_ORDER;//用户要求：
	
	private String PORT_ORDER;//端口要求：
	
	private String SERVER_ORDER;//服务要求：
	
	private String MIDDLEWARE_ORDER;//中间件要求：
	
	private String CONFIG_ORDER;//部署配置要求：高配（cpu、内存、存储等要求）、中配、低配
	
	private String CPUUSE_ORDER;//部署机CPU利用率要求：
	
	private String MEMORYUSE_ORDER;//部署机内存利用率要求：
	
	private String HEAPUSE_ORDER;//部署机存储利用率要求：
	
	private String HEAPLEFT_ORDER;//剩余存储大小
	
	private String PATHAUTHORITY;//部署路径权限检测
	
	private String NETIO_ORDER;//部署机网络IO要求：
	
	private String SYS_ORDER_FLAG;//是否进行系统检测
	
	private String JDK_ORDER_FLAG;//是否进行jdk检测
	
	private String USER_ORDER_FLAG;//是否进行用户检测
	
	private String MIDDLEWARE_ORDER_FLAG;//是否进行中间件检测
	
	private String CPUUSE_ORDER_FLAG;//是否进行cpu利用率检测
	
	private String MEMORYUSE_ORDER_FLAG;//是否进行内存利用率检测
	
	private String HEAPUSE_ORDER_FLAG;//是否进行存储利用率检测
	
	private String PORT_FLAG;//是否进行端口检测
	
	private String SERVER_FLAG;//是否进行服务器检测
	
	private String CONFIG_FLAG;//是否进行配置检测
	
	private String NET_FLAG;//是否进行网络检测
	
	private String HEAPLEFT_FLAG;//是否进行剩余存储检测
	
	private String PATH_FLAG;//是否进行路径检测
	
	private String APPNAME;//基准应用名称
	
	public String getSYS_ORDER_FLAG() {
		return SYS_ORDER_FLAG;
	}
	public void setSYS_ORDER_FLAG(String sys_order_flag) {
		SYS_ORDER_FLAG = sys_order_flag;
	}
	public String getJDK_ORDER_FLAG() {
		return JDK_ORDER_FLAG;
	}
	public void setJDK_ORDER_FLAG(String jdk_order_flag) {
		JDK_ORDER_FLAG = jdk_order_flag;
	}
	public String getUSER_ORDER_FLAG() {
		return USER_ORDER_FLAG;
	}
	public void setUSER_ORDER_FLAG(String user_order_flag) {
		USER_ORDER_FLAG = user_order_flag;
	}
	public String getMIDDLEWARE_ORDER_FLAG() {
		return MIDDLEWARE_ORDER_FLAG;
	}
	public void setMIDDLEWARE_ORDER_FLAG(String middleware_order_flag) {
		MIDDLEWARE_ORDER_FLAG = middleware_order_flag;
	}
	public String getCPUUSE_ORDER_FLAG() {
		return CPUUSE_ORDER_FLAG;
	}
	public void setCPUUSE_ORDER_FLAG(String cpuuse_order_flag) {
		CPUUSE_ORDER_FLAG = cpuuse_order_flag;
	}
	public String getMEMORYUSE_ORDER_FLAG() {
		return MEMORYUSE_ORDER_FLAG;
	}
	public void setMEMORYUSE_ORDER_FLAG(String memoryuse_order_flag) {
		MEMORYUSE_ORDER_FLAG = memoryuse_order_flag;
	}
	public String getHEAPUSE_ORDER_FLAG() {
		return HEAPUSE_ORDER_FLAG;
	}
	public void setHEAPUSE_ORDER_FLAG(String heapuse_order_flag) {
		HEAPUSE_ORDER_FLAG = heapuse_order_flag;
	}
	public Integer getCONFIG_ID() {
		return CONFIG_ID;
	}
	public void setCONFIG_ID(Integer cONFIG_ID) {
		CONFIG_ID = cONFIG_ID;
	}
	public String getSYS_ORDER() {
		return SYS_ORDER;
	}
	public void setSYS_ORDER(String sYS_ORDER) {
		SYS_ORDER = sYS_ORDER;
	}
	public String getJDK_ORDER() {
		return JDK_ORDER;
	}
	public void setJDK_ORDER(String jDK_ORDER) {
		JDK_ORDER = jDK_ORDER;
	}
	public String getUSER_ORDER() {
		return USER_ORDER;
	}
	public void setUSER_ORDER(String uSER_ORDER) {
		USER_ORDER = uSER_ORDER;
	}
	public String getPORT_ORDER() {
		return PORT_ORDER;
	}
	public void setPORT_ORDER(String pORT_ORDER) {
		PORT_ORDER = pORT_ORDER;
	}
	public String getSERVER_ORDER() {
		return SERVER_ORDER;
	}
	public void setSERVER_ORDER(String sERVER_ORDER) {
		SERVER_ORDER = sERVER_ORDER;
	}
	public String getMIDDLEWARE_ORDER() {
		return MIDDLEWARE_ORDER;
	}
	public void setMIDDLEWARE_ORDER(String mIDDLEWARE_ORDER) {
		MIDDLEWARE_ORDER = mIDDLEWARE_ORDER;
	}
	public String getCONFIG_ORDER() {
		return CONFIG_ORDER;
	}
	public void setCONFIG_ORDER(String cONFIG_ORDER) {
		CONFIG_ORDER = cONFIG_ORDER;
	}
	public String getCPUUSE_ORDER() {
		return CPUUSE_ORDER;
	}
	public void setCPUUSE_ORDER(String cPUUSE_ORDER) {
		CPUUSE_ORDER = cPUUSE_ORDER;
	}
	public String getMEMORYUSE_ORDER() {
		return MEMORYUSE_ORDER;
	}
	public void setMEMORYUSE_ORDER(String mEMORYUSE_ORDER) {
		MEMORYUSE_ORDER = mEMORYUSE_ORDER;
	}
	public String getHEAPUSE_ORDER() {
		return HEAPUSE_ORDER;
	}
	public void setHEAPUSE_ORDER(String hEAPUSE_ORDER) {
		HEAPUSE_ORDER = hEAPUSE_ORDER;
	}
	public String getHEAPLEFT_ORDER() {
		return HEAPLEFT_ORDER;
	}
	public void setHEAPLEFT_ORDER(String hEAPLEFT_ORDER) {
		HEAPLEFT_ORDER = hEAPLEFT_ORDER;
	}
	public String getPATHAUTHORITY() {
		return PATHAUTHORITY;
	}
	public void setPATHAUTHORITY(String pATHAUTHORITY) {
		PATHAUTHORITY = pATHAUTHORITY;
	}
	public String getNETIO_ORDER() {
		return NETIO_ORDER;
	}
	public void setNETIO_ORDER(String nETIO_ORDER) {
		NETIO_ORDER = nETIO_ORDER;
	}
	public String getPORT_FLAG() {
		return PORT_FLAG;
	}
	public void setPORT_FLAG(String pORT_FLAG) {
		PORT_FLAG = pORT_FLAG;
	}
	public String getSERVER_FLAG() {
		return SERVER_FLAG;
	}
	public void setSERVER_FLAG(String sERVER_FLAG) {
		SERVER_FLAG = sERVER_FLAG;
	}
	public String getCONFIG_FLAG() {
		return CONFIG_FLAG;
	}
	public void setCONFIG_FLAG(String cONFIG_FLAG) {
		CONFIG_FLAG = cONFIG_FLAG;
	}
	public String getNET_FLAG() {
		return NET_FLAG;
	}
	public void setNET_FLAG(String nET_FLAG) {
		NET_FLAG = nET_FLAG;
	}
	public String getHEAPLEFT_FLAG() {
		return HEAPLEFT_FLAG;
	}
	public void setHEAPLEFT_FLAG(String hEAPLEFT_FLAG) {
		HEAPLEFT_FLAG = hEAPLEFT_FLAG;
	}
	public String getPATH_FLAG() {
		return PATH_FLAG;
	}
	public void setPATH_FLAG(String pATH_FLAG) {
		PATH_FLAG = pATH_FLAG;
	}
	public String getAPPNAME() {
		return APPNAME;
	}
	public void setAPPNAME(String aPPNAME) {
		APPNAME = aPPNAME;
	}
	
}
