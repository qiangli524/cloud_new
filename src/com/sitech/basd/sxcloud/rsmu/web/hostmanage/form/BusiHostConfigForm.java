package com.sitech.basd.sxcloud.rsmu.web.hostmanage.form;

import java.util.List;

@SuppressWarnings("serial")
public class BusiHostConfigForm {

	private int ID; // 编号
	private int HOSTID; // 主机编号
	private int TYPE; // 用户类型
	private String HOSTUSERNAME; // 主机用户名
	private String HOSTUSERNAME_BF; // 主机用户名备份字段 外加
	private String HOSRPWD; // 主机密码
	private String reHOSRPWD; // 重复主机密码
	private String SPACE; // 主机磁盘空间
	private String UPDATETTIME; // 时间
	private String IP;
	private List resultList;
	private String HOSTNAME; // 主机名称
	private List busihostList;
	private List appList;
	private String SPECIALPATH; // 例外文件路径
	private String CREDITUSER; // 信任关系用户
	private int APPID; // 信任关系用户

	private int TYPE_BF; // 用户类型备份

	private String sshPort;
	private String sshPwd;
	private String logPath;// 日志文件路径
	private String appPath;// 访问应用地址
	private String deploy_path;// 应用部署路径
	private String base_path;// 基准应用部署路径
	private String start_shell;// 启动脚本路径;
	private String shutdown_shell;// 停止脚本路径
	private String ifexample; // 是否生成部署实例,'0':生成,'1':不生成
	private Integer isCredituser;// 是否信任关系'0':非信任关系,'1':信任关系

	public Integer getIsCredituser() {
		return isCredituser;
	}

	public void setIsCredituser(Integer isCredituser) {
		this.isCredituser = isCredituser;
	}

	public String getDeploy_path() {
		return deploy_path;
	}

	public void setDeploy_path(String deploy_path) {
		this.deploy_path = deploy_path;
	}

	public String getBase_path() {
		return base_path;
	}

	public void setBase_path(String base_path) {
		this.base_path = base_path;
	}

	public String getStart_shell() {
		return start_shell;
	}

	public void setStart_shell(String start_shell) {
		this.start_shell = start_shell;
	}

	public String getShutdown_shell() {
		return shutdown_shell;
	}

	public void setShutdown_shell(String shutdown_shell) {
		this.shutdown_shell = shutdown_shell;
	}

	public String getIfexample() {
		return ifexample;
	}

	public void setIfexample(String ifexample) {
		this.ifexample = ifexample;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(int hOSTID) {
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

	public String getReHOSRPWD() {
		return reHOSRPWD;
	}

	public void setReHOSRPWD(String reHOSRPWD) {
		this.reHOSRPWD = reHOSRPWD;
	}

	/*
	 * 清空ActionForm
	 */
	public void reset() {
		this.ID = 0;
		this.HOSTID = 0;
		this.TYPE = 0;
		this.HOSTNAME = null;
		this.HOSRPWD = null;
		this.UPDATETTIME = null;
		this.HOSTUSERNAME = null;
		this.HOSTUSERNAME_BF = null;
		this.TYPE_BF = 0;
	}

	public List getAppList() {
		return appList;
	}

	public void setAppList(List appList) {
		this.appList = appList;
	}

	public String getCREDITUSER() {
		return CREDITUSER;
	}

	public void setCREDITUSER(String credituser) {
		CREDITUSER = credituser;
	}

	public String getSPECIALPATH() {
		return SPECIALPATH;
	}

	public void setSPECIALPATH(String specialpath) {
		SPECIALPATH = specialpath;
	}

	public int getAPPID() {
		return APPID;
	}

	public void setAPPID(int appid) {
		APPID = appid;
	}

	public String getSshPort() {
		return sshPort;
	}

	public void setSshPort(String sshPort) {
		this.sshPort = sshPort;
	}

	public String getSshPwd() {
		return sshPwd;
	}

	public void setSshPwd(String sshPwd) {
		this.sshPwd = sshPwd;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}
}
