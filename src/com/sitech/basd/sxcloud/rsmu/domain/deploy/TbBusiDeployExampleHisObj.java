package com.sitech.basd.sxcloud.rsmu.domain.deploy;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiDeployExampleHisObj extends BaseObj implements Serializable,
		Cloneable {

	/*
	 * 部署实例管理
	 */
	private int ID; // '编号',
	private String LONGID;
	private int HOSTID; // '主机编号',
	private String DEPLOYESTARTTIME; // '部署开始时间',
	private String DEPLOYEENDTIME; // '部署结束时间',
	// private String STRATEGYID; // '策略编号',
	private String DEPLOY_FLAG; // 0：未部署 1：已部署 ，2：卸载，3：已卸载
	private String START_STOP_FLAG; // '0：停止 1：启动',
	private String UPDATETIME; // '启停时间',
	private String RESULT; // 返回结果
	private String RELEASE_FLAG; // 负载均衡返回结果
	private int APPID; // 应用id
	private String DEPLOY_FLAG_NAME;
	private String DEPLOY_FLAG_AN;
	private String START_STOP_FLAG_NAME;
	private String START_STOP_FLAG_AN;

	private String IP; // 主机名称
	private String VLANIP; // 服务IP
	// private String STRATEGYNAME;//策略名称
	private String APPNAME; // 应用名称
	private String DEPLOYPATH; // 部署上线路径
	private String APPPORT; // 部署实例访问端口
	private String KEYNAME; // 部署实例访问中间件
	private String VLAN; // 部署Vlan

	private String DEPLOY_PERCENT; // 部署或卸载完成百分比
	private int START_STOP_PERCENT;// 启动或停止完成百分比
	private String NEED_NUMBER; // 申请时的需求编号
	private int processNode; // 流程编号；1是资源申请；2是应用部署申请
	private int taskId; // 处理工单时的流水号
	private int isrestart; // 是否重启应用标志位
	private int isbackup; // 是否备份应用标志位

	public String getDEPLOYPATH() {
		return DEPLOYPATH;
	}

	public void setDEPLOYPATH(String deploypath) {
		DEPLOYPATH = deploypath;
	}

	public int getIsbackup() {
		return isbackup;
	}

	public void setIsbackup(int isbackup) {
		this.isbackup = isbackup;
	}

	public int getIsrestart() {
		return isrestart;
	}

	public void setIsrestart(int isrestart) {
		this.isrestart = isrestart;
	}

	public int getProcessNode() {
		return processNode;
	}

	public void setProcessNode(int processNode) {
		this.processNode = processNode;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getNEED_NUMBER() {
		return NEED_NUMBER;
	}

	public void setNEED_NUMBER(String need_number) {
		NEED_NUMBER = need_number;
	}

	public String getDEPLOY_FLAG() {
		return DEPLOY_FLAG;
	}

	public void setDEPLOY_FLAG(String deploy_flag) {
		DEPLOY_FLAG = deploy_flag;
	}

	public String getDEPLOYEENDTIME() {
		return DEPLOYEENDTIME;
	}

	public void setDEPLOYEENDTIME(String deployeendtime) {
		DEPLOYEENDTIME = deployeendtime;
	}

	public String getDEPLOYESTARTTIME() {
		return DEPLOYESTARTTIME;
	}

	public void setDEPLOYESTARTTIME(String deployestarttime) {
		DEPLOYESTARTTIME = deployestarttime;
	}

	public int getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(int hostid) {
		HOSTID = hostid;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getSTART_STOP_FLAG() {
		return START_STOP_FLAG;
	}

	public void setSTART_STOP_FLAG(String start_stop_flag) {
		START_STOP_FLAG = start_stop_flag;
	}

	/*
	 * public String getSTRATEGYID() { return STRATEGYID; } public void
	 * setSTRATEGYID(String strategyid) { STRATEGYID = strategyid; }
	 */
	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}

	public String getVLANIP() {
		return VLANIP;
	}

	public void setVLANIP(String vLANIP) {
		VLANIP = vLANIP;
	}

	public String getRELEASE_FLAG() {
		return RELEASE_FLAG;
	}

	public void setRELEASE_FLAG(String release_flag) {
		RELEASE_FLAG = release_flag;
	}

	public String getRESULT() {
		return RESULT;
	}

	public void setRESULT(String result) {
		RESULT = result;
	}

	/*
	 * public String getSTRATEGYNAME() { return STRATEGYNAME; } public void
	 * setSTRATEGYNAME(String strategyname) { STRATEGYNAME = strategyname; }
	 */
	public int getAPPID() {
		return APPID;
	}

	public void setAPPID(int appid) {
		APPID = appid;
	}

	public String getAPPNAME() {
		return APPNAME;
	}

	public void setAPPNAME(String appname) {
		APPNAME = appname;
	}

	public String getDEPLOY_FLAG_NAME() {
		return DEPLOY_FLAG_NAME;
	}

	public void setDEPLOY_FLAG_NAME(String deploy_flag_name) {
		DEPLOY_FLAG_NAME = deploy_flag_name;
	}

	public String getDEPLOY_FLAG_AN() {
		return DEPLOY_FLAG_AN;
	}

	public void setDEPLOY_FLAG_AN(String deploy_flag_an) {
		DEPLOY_FLAG_AN = deploy_flag_an;
	}

	public String getSTART_STOP_FLAG_AN() {
		return START_STOP_FLAG_AN;
	}

	public void setSTART_STOP_FLAG_AN(String start_stop_flag_an) {
		START_STOP_FLAG_AN = start_stop_flag_an;
	}

	public String getSTART_STOP_FLAG_NAME() {
		return START_STOP_FLAG_NAME;
	}

	public void setSTART_STOP_FLAG_NAME(String start_stop_flag_name) {
		START_STOP_FLAG_NAME = start_stop_flag_name;
	}

	public String getLONGID() {
		return LONGID;
	}

	public void setLONGID(String longid) {
		LONGID = longid;
	}

	public String getAPPPORT() {
		return APPPORT;
	}

	public void setAPPPORT(String appport) {
		APPPORT = appport;
	}

	public String getKEYNAME() {
		return KEYNAME;
	}

	public void setKEYNAME(String keyname) {
		KEYNAME = keyname;
	}

	public String getDEPLOY_PERCENT() {
		return DEPLOY_PERCENT;
	}

	public void setDEPLOY_PERCENT(String dEPLOYPERCENT) {
		DEPLOY_PERCENT = dEPLOYPERCENT;
	}

	public int getSTART_STOP_PERCENT() {
		return START_STOP_PERCENT;
	}

	public void setSTART_STOP_PERCENT(int start_stop_percent) {
		START_STOP_PERCENT = start_stop_percent;
	}

	public String getVLAN() {
		return VLAN;
	}

	public void setVLAN(String vlan) {
		VLAN = vlan;
	}

}
