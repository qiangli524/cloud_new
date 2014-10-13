package com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;


@SuppressWarnings("serial")
public class AppNeedObj extends BaseObj implements Serializable, Cloneable {
	private int ID;		//		序号，自增
	private String NEED_NUMBERS;	//需求编号
	private String NEED_SPONSOR;		//需求发起人
	private String NEED_CONT;		//需求内容
	private String START_DATE;		//需求发起时间
	private String END_DATE;		//需求结束时间
	private int NEED_STATUS;//需求状态:0 草稿；1 正在处理；2处理结束
	private int APP_IPMODEL;		//应用部署的IP模式
	private String APP_SIZE; //应用部署大小
	private String APP_IP;		//应用部署的IP
	private String taskId = null;		//任务编号
	private int processNode = 0;		//节点编号,如发起环节、审批一环节、审批环节二编号
	private int defendflag; 	//是否添加防篡改
	private String DEFEND_DIR;		//保存防篡改目录
	private String APPID;		//部署的应用ID
	private String APPNAME ;   //部署应用名称
	private String DisposeMan;  //处理人
	private String IP;
	private String VLANIP;  //服务IP
	public String getVLANIP() {
		return VLANIP;
	}
	public void setVLANIP(String vlanip) {
		VLANIP = vlanip;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String ip) {
		IP = ip;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getNEED_NUMBERS() {
		return NEED_NUMBERS;
	}
	public void setNEED_NUMBERS(String need_numbers) {
		NEED_NUMBERS = need_numbers;
	}
	public String getNEED_SPONSOR() {
		return NEED_SPONSOR;
	}
	public void setNEED_SPONSOR(String need_sponsor) {
		NEED_SPONSOR = need_sponsor;
	}
	public String getNEED_CONT() {
		return NEED_CONT;
	}
	public void setNEED_CONT(String need_cont) {
		NEED_CONT = need_cont;
	}
	public String getSTART_DATE() {
		return START_DATE;
	}
	public void setSTART_DATE(String start_date) {
		START_DATE = start_date;
	}
	public String getEND_DATE() {
		return END_DATE;
	}
	public void setEND_DATE(String end_date) {
		END_DATE = end_date;
	}
	public int getNEED_STATUS() {
		return NEED_STATUS;
	}
	public void setNEED_STATUS(int need_status) {
		NEED_STATUS = need_status;
	}
	public int getAPP_IPMODEL() {
		return APP_IPMODEL;
	}
	public void setAPP_IPMODEL(int app_ipmodel) {
		APP_IPMODEL = app_ipmodel;
	}
	public String getAPP_IP() {
		return APP_IP;
	}
	public void setAPP_IP(String app_ip) {
		APP_IP = app_ip;
	}
	public String getAPP_SIZE() {
		return APP_SIZE;
	}
	public void setAPP_SIZE(String app_size) {
		APP_SIZE = app_size;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public int getProcessNode() {
		return processNode;
	}
	public void setProcessNode(int processNode) {
		this.processNode = processNode;
	}
	public String getDisposeMan() {
		return DisposeMan;
	}
	public void setDisposeMan(String disposeMan) {
		DisposeMan = disposeMan;
	}
	public String getDEFEND_DIR() {
		return DEFEND_DIR;
	}
	public void setDEFEND_DIR(String defend_dir) {
		DEFEND_DIR = defend_dir;
	}
	public int getDefendflag() {
		return defendflag;
	}
	public void setDefendflag(int defendflag) {
		this.defendflag = defendflag;
	}
	public String getAPPID() {
		return APPID;
	}
	public void setAPPID(String appid) {
		APPID = appid;
	}
	public String getAPPNAME() {
		return APPNAME;
	}
	public void setAPPNAME(String appname) {
		APPNAME = appname;
	}
	
}
