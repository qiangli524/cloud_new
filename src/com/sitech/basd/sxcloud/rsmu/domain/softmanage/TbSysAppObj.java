package com.sitech.basd.sxcloud.rsmu.domain.softmanage;

import java.io.Serializable;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbSysAppObj extends BaseObj implements Serializable, Cloneable {
	private int ID = 0; // 应用编号
	private String APPNAME = null; // 应用名称
	private String STATUS = null; // 应用状态
	// 1：已注册，2：部署，3：已部署，4：注销，5：已注销,6：升级，7：已升级
	private int TYPE = 0; // 升级类型 1：集中式升级，2：分布式升级
	private String REMARK = null; // 备注
	private String APP_IDENTIFY = null; // 应用英文标识,为标识，用于创建软件存放目录

	private String DEPLOYPATH = null; // 部署路径
	private String HOSEUSERNAME = null; // 用户名
	private String STARTSHELL = null; // 启动脚本
	private String STOPSHELL = null; // 停止脚本

	private String PORT1; // 应用端口
	private String PORT2;
	private String PORT3;
	private String PORTALL;
	private String START_STOP_FLAG; // 0：停止，1:正在停止，2：启动，3：正在启动，只要一台机器启动为已启动标志，所有机器停止变为停止标志
	private String RELEASE_FLAG; // 向负载均衡返回的结果，1:未发布，2：发布，3：已发布，4：停止发布，5：已停止发布，只要一台机器发布了就变为已发布，所有机器已停止发布就变为已停止发布
	private String LONGID;
	private String DEPLOY_FLAG_NAME;
	private String DEPLOY_FLAG_AN;
	private String START_STOP_FLAG_NAME;
	private int STRATEGYTYPE = 0; // 1：录像部署 2：基准部署
	private int STRATEGY = 0; // 当录像部署时，方案为录像编号。 当基准部署时，基准主机编号
	private String BASEPATH = null; // 为基准部署时 选择基准路径
    private String BUSISYS; //EXCEL批量导入实例时的所属业务系统
	
	private String OPERATIONNAME = null; // 业务名称
	private String CATCH_STATUS = null; // 捕获状态
	private String CATCH_TIME = null; // 捕获时间
	private String SYS_ID;// 业务系统Id
	// 上线或者回滚路径
	private String onlinePath;
	private String versionDesc;
	// 应用Linux进程标示，用于ps -ef | grep 查看进程
	private String appMark;
	private String HOSEUSERID;

	private String HOST_CONFIG_ID;

	private List<String> bizids;
	/** 基准应用所在主机IP */
	private String hostIP;
	
	private List<Integer> idlist;

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public String getHOST_CONFIG_ID() {
		return HOST_CONFIG_ID;
	}

	public void setHOST_CONFIG_ID(String host_config_id) {
		HOST_CONFIG_ID = host_config_id;
	}

	public String getHOSEUSERID() {
		return HOSEUSERID;
	}

	public void setHOSEUSERID(String hoseuserid) {
		HOSEUSERID = hoseuserid;
	}

	public String getAppMark() {
		return appMark;
	}

	public void setAppMark(String appMark) {
		this.appMark = appMark;
	}

	public String getVersionDesc() {
		return versionDesc;
	}

	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}

	public String getOnlinePath() {
		return onlinePath;
	}

	public void setOnlinePath(String onlinePath) {
		this.onlinePath = onlinePath;
	}

	public String getSYS_ID() {
		return SYS_ID;
	}

	public void setSYS_ID(String sys_id) {
		SYS_ID = sys_id;
	}

	public String getCATCH_STATUS() {
		return CATCH_STATUS;
	}

	public void setCATCH_STATUS(String catch_status) {
		CATCH_STATUS = catch_status;
	}

	public String getCATCH_TIME() {
		return CATCH_TIME;
	}

	public void setCATCH_TIME(String catch_time) {
		CATCH_TIME = catch_time;
	}

	public int getSTRATEGYTYPE() {
		return STRATEGYTYPE;
	}

	public void setSTRATEGYTYPE(int sTRATEGYTYPE) {
		STRATEGYTYPE = sTRATEGYTYPE;
	}

	public int getSTRATEGY() {
		return STRATEGY;
	}

	public void setSTRATEGY(int sTRATEGY) {
		STRATEGY = sTRATEGY;
	}

	public String getBASEPATH() {
		return BASEPATH;
	}

	public void setBASEPATH(String bASEPATH) {
		BASEPATH = bASEPATH;
	}

	public String getAPP_IDENTIFY() {
		return APP_IDENTIFY;
	}

	public void setAPP_IDENTIFY(String app_identify) {
		APP_IDENTIFY = app_identify;
	}

	public String getAPPNAME() {
		return APPNAME;
	}

	public void setAPPNAME(String appname) {
		APPNAME = appname;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String status) {
		STATUS = status;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int type) {
		TYPE = type;
	}

	public String getDEPLOYPATH() {
		return DEPLOYPATH;
	}

	public void setDEPLOYPATH(String deploypath) {
		DEPLOYPATH = deploypath;
	}

	public String getHOSEUSERNAME() {
		return HOSEUSERNAME;
	}

	public void setHOSEUSERNAME(String hoseusername) {
		HOSEUSERNAME = hoseusername;
	}

	public String getSTARTSHELL() {
		return STARTSHELL;
	}

	public void setSTARTSHELL(String startshell) {
		STARTSHELL = startshell;
	}

	public String getSTOPSHELL() {
		return STOPSHELL;
	}

	public void setSTOPSHELL(String stopshell) {
		STOPSHELL = stopshell;
	}

	public String getPORT1() {
		return PORT1;
	}

	public void setPORT1(String port1) {
		PORT1 = port1;
	}

	public String getPORT2() {
		return PORT2;
	}

	public void setPORT2(String port2) {
		PORT2 = port2;
	}

	public String getPORT3() {
		return PORT3;
	}

	public void setPORT3(String port3) {
		PORT3 = port3;
	}

	public String getPORTALL() {
		return PORTALL;
	}

	public void setPORTALL(String portall) {
		PORTALL = portall;
	}

	public String getSTART_STOP_FLAG() {
		return START_STOP_FLAG;
	}

	public void setSTART_STOP_FLAG(String sTARTSTOPFLAG) {
		START_STOP_FLAG = sTARTSTOPFLAG;
	}

	public String getRELEASE_FLAG() {
		return RELEASE_FLAG;
	}

	public void setRELEASE_FLAG(String rELEASEFLAG) {
		RELEASE_FLAG = rELEASEFLAG;
	}

	public String getLONGID() {
		return LONGID;
	}

	public void setLONGID(String lONGID) {
		LONGID = lONGID;
	}

	public String getDEPLOY_FLAG_NAME() {
		return DEPLOY_FLAG_NAME;
	}

	public void setDEPLOY_FLAG_NAME(String dEPLOYFLAGNAME) {
		DEPLOY_FLAG_NAME = dEPLOYFLAGNAME;
	}

	public String getDEPLOY_FLAG_AN() {
		return DEPLOY_FLAG_AN;
	}

	public void setDEPLOY_FLAG_AN(String dEPLOYFLAGAN) {
		DEPLOY_FLAG_AN = dEPLOYFLAGAN;
	}

	public String getSTART_STOP_FLAG_NAME() {
		return START_STOP_FLAG_NAME;
	}

	public void setSTART_STOP_FLAG_NAME(String sTARTSTOPFLAGNAME) {
		START_STOP_FLAG_NAME = sTARTSTOPFLAGNAME;
	}

	public String getOPERATIONNAME() {
		return OPERATIONNAME;
	}

	public void setOPERATIONNAME(String oPERATIONNAME) {
		OPERATIONNAME = oPERATIONNAME;
	}

	public List<String> getBizids() {
		return bizids;
	}

	public void setBizids(List<String> bizids) {
		this.bizids = bizids;
	}

	public List<Integer> getIdlist() {
		return idlist;
	}

	public void setIdlist(List<Integer> idlist) {
		this.idlist = idlist;
	}

	public String getBUSISYS() {
		return BUSISYS;
	}

	public void setBUSISYS(String busisys) {
		BUSISYS = busisys;
	}

}
