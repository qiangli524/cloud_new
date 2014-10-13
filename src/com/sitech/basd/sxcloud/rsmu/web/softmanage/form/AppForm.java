package com.sitech.basd.sxcloud.rsmu.web.softmanage.form;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;

public class AppForm {
	private int ID;
	private int APPID;
	private String APPNAME;
	private String STATUS;
	private String CATCH_STATUS;
	private int TYPE;
	private String REMARK;
	private String APP_IDENTIFY;
	private String APP_IDENTIFY_BF;
	private String DEPLOYPATH = null;
	private String HOSEUSERNAME = null;
	private String STARTSHELL = null;
	private String STOPSHELL = null;
	private String PORT1 = "";
	private String PORT2 = "";
	private String PORT3 = "";
	private String PORT1_HIDDEN = "";
	private String PORT2_HIDDEN = "";
	private String PORT3_HIDDEN = "";
	private int PORT1_STATUS = 0;
	private int PORT2_STATUS = 0;
	private int PORT3_STATUS = 0;
	private String PORTALL;
	private List resultList;
	private List appList;
	private List bizList;// 业务系统集合
	private String SYS_ID;// 业务系统Id
	private String oldSysId;//原业务系统Id
	private String START_STOP_FLAG; // //0：停止，1:正在停止，2：启动，3：正在启动，只要一台机器启动为已启动标志，所有机器停止变为停止标志
	private String RELEASE_FLAG; // 负载均衡返回结果
	private int STRATEGYTYPE; // 1：录像部署 2：基准部署
	private int STRATEGY; // 当录像部署时，方案为录像编号。 当基准部署时，基准主机编号
	private String BASEPATH; // 为基准部署时 选择基准路径
	private String OPERATIONNAME; // 业务名称
	// 可选择Web主机资源池主机列表
	private List<TbBusiHostObj> busiHostList;
	// 上线或者回滚路径
	private String onlinePath;
	// 应用Linux进程标示，用于ps -ef | grep 查看进程
	private String appMark;
	
	private List HOSEUSERLIST;
	private String HOSEUSERID;
	
	public String getOldSysId() {
		return oldSysId;
	}

	public void setOldSysId(String oldSysId) {
		this.oldSysId = oldSysId;
	}

	public String getHOSEUSERID() {
		return HOSEUSERID;
	}

	public void setHOSEUSERID(String hoseuserid) {
		HOSEUSERID = hoseuserid;
	}

	public List getHOSEUSERLIST() {
		return HOSEUSERLIST;
	}

	public void setHOSEUSERLIST(List hoseuserlist) {
		HOSEUSERLIST = hoseuserlist;
	}

	public String getAppMark() {
		return appMark;
	}

	public void setAppMark(String appMark) {
		this.appMark = appMark;
	}

	public String getOnlinePath() {
		return onlinePath;
	}

	public void setOnlinePath(String onlinePath) {
		this.onlinePath = onlinePath;
	}

	public List<TbBusiHostObj> getBusiHostList() {
		return busiHostList;
	}

	public void setBusiHostList(List<TbBusiHostObj> busiHostList) {
		this.busiHostList = busiHostList;
	}

	public String getAPP_IDENTIFY() {
		return APP_IDENTIFY;
	}

	public void setAPP_IDENTIFY(String app_identify) {
		APP_IDENTIFY = app_identify;
	}

	public String getAPP_IDENTIFY_BF() {
		return APP_IDENTIFY_BF;
	}

	public void setAPP_IDENTIFY_BF(String app_identify_bf) {
		APP_IDENTIFY_BF = app_identify_bf;
	}

	public int getAPPID() {
		return APPID;
	}

	public void setAPPID(int appid) {
		APPID = appid;
	}

	public List getAppList() {
		return appList;
	}

	public void setAppList(List appList) {
		this.appList = appList;
	}

	public String getAPPNAME() {
		return APPNAME;
	}

	public void setAPPNAME(String appname) {
		APPNAME = appname;
	}

	public String getBASEPATH() {
		return BASEPATH;
	}

	public void setBASEPATH(String basepath) {
		BASEPATH = basepath;
	}

	public String getCATCH_STATUS() {
		return CATCH_STATUS;
	}

	public void setCATCH_STATUS(String catch_status) {
		CATCH_STATUS = catch_status;
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

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getOPERATIONNAME() {
		return OPERATIONNAME;
	}

	public void setOPERATIONNAME(String operationname) {
		OPERATIONNAME = operationname;
	}

	public String getPORT1() {
		return PORT1;
	}

	public void setPORT1(String port1) {
		PORT1 = port1;
	}

	public String getPORT1_HIDDEN() {
		return PORT1_HIDDEN;
	}

	public void setPORT1_HIDDEN(String port1_hidden) {
		PORT1_HIDDEN = port1_hidden;
	}

	public int getPORT1_STATUS() {
		return PORT1_STATUS;
	}

	public void setPORT1_STATUS(int port1_status) {
		PORT1_STATUS = port1_status;
	}

	public String getPORT2() {
		return PORT2;
	}

	public void setPORT2(String port2) {
		PORT2 = port2;
	}

	public String getPORT2_HIDDEN() {
		return PORT2_HIDDEN;
	}

	public void setPORT2_HIDDEN(String port2_hidden) {
		PORT2_HIDDEN = port2_hidden;
	}

	public int getPORT2_STATUS() {
		return PORT2_STATUS;
	}

	public void setPORT2_STATUS(int port2_status) {
		PORT2_STATUS = port2_status;
	}

	public String getPORT3() {
		return PORT3;
	}

	public void setPORT3(String port3) {
		PORT3 = port3;
	}

	public String getPORT3_HIDDEN() {
		return PORT3_HIDDEN;
	}

	public void setPORT3_HIDDEN(String port3_hidden) {
		PORT3_HIDDEN = port3_hidden;
	}

	public int getPORT3_STATUS() {
		return PORT3_STATUS;
	}

	public void setPORT3_STATUS(int port3_status) {
		PORT3_STATUS = port3_status;
	}

	public String getPORTALL() {
		return PORTALL;
	}

	public void setPORTALL(String portall) {
		PORTALL = portall;
	}

	public String getRELEASE_FLAG() {
		return RELEASE_FLAG;
	}

	public void setRELEASE_FLAG(String release_flag) {
		RELEASE_FLAG = release_flag;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getSTART_STOP_FLAG() {
		return START_STOP_FLAG;
	}

	public void setSTART_STOP_FLAG(String start_stop_flag) {
		START_STOP_FLAG = start_stop_flag;
	}

	public String getSTARTSHELL() {
		return STARTSHELL;
	}

	public void setSTARTSHELL(String startshell) {
		STARTSHELL = startshell;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String status) {
		STATUS = status;
	}

	public String getSTOPSHELL() {
		return STOPSHELL;
	}

	public void setSTOPSHELL(String stopshell) {
		STOPSHELL = stopshell;
	}

	public int getSTRATEGY() {
		return STRATEGY;
	}

	public void setSTRATEGY(int strategy) {
		STRATEGY = strategy;
	}

	public int getSTRATEGYTYPE() {
		return STRATEGYTYPE;
	}

	public void setSTRATEGYTYPE(int strategytype) {
		STRATEGYTYPE = strategytype;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int type) {
		TYPE = type;
	}

	/*
	 * 清空ActionForm
	 */
	public void reset() {
		this.ID = 0;
		this.APPNAME = null;
		this.STATUS = null;
		this.TYPE = 0;
		this.REMARK = null;
		this.APP_IDENTIFY = null;
		this.PORT1_STATUS = 0;
		this.PORT2_STATUS = 0;
		this.PORT3_STATUS = 0;
		this.PORT1_STATUS = 0;
		this.PORT2_STATUS = 0;
		this.PORT3_STATUS = 0;
		this.resultList = null;
		this.START_STOP_FLAG = null;
		this.RELEASE_FLAG = null;
		this.STRATEGYTYPE = 0;
		this.STRATEGY = 0;
		this.BASEPATH = null;
		this.APP_IDENTIFY_BF = null;
		this.OPERATIONNAME = null;
	}

	public List getBizList() {
		return bizList;
	}

	public void setBizList(List bizList) {
		this.bizList = bizList;
	}

	public String getSYS_ID() {
		return SYS_ID;
	}

	public void setSYS_ID(String sys_id) {
		SYS_ID = sys_id;
	}

}
