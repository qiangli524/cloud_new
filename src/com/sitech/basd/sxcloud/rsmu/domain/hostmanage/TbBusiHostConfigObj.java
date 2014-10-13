package com.sitech.basd.sxcloud.rsmu.domain.hostmanage;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiHostConfigObj extends BaseObj implements Serializable, Cloneable {
	private int ID; // 编号
	private int HOSTID; // 主机编号
	private int TYPE; // 1:超级管理员；2:普通用户
	private String HOSTUSERNAME; // 主机用户名
	private String HOSTNAME;// 主机名称
	private String HOSRPWD; // 主机密码
	private String SPACE;// 主机磁盘空间
	private String UPDATETTIME; // 更新时间
	private String IP;
	private int STATUS; // 0：创建 1：已创建 2：删除 3：已删除
	private int EXECUTE_FLAG; // 0：未扫描 1：已扫描
	private String SPECIALPATH; // 例外文件路径
	private String CREDITUSER; // 信任关系用户
	private String APPNAME; // 基准应用名称
	private String APPID; // 基准应用名称

	// ssh用户信息
	private String sshPort;
	private String sshPwd;
	private String logPath;// 日志文件路径
	private String appPath = null;// 访问应用地址
	private String deploy_path;// 应用部署路径
	private String base_path;// 基准应用部署路径
	private String start_shell;// 启动脚本路径;
	private String shutdown_shell;// 停止脚本路径
	private String ifexample; // 是否生成部署实例,'0':生成,'1':不生成
	private Integer isCredituser = 0;// s是否是信任关系用户0：不是1：是

	private String USER_MANAGE_ID = null;
	private String HOSTCONFIGID = null;

	private String EXAMPLE_NAME;
	private Integer HOSTPORT;

	public String getEXAMPLE_NAME() {
		return EXAMPLE_NAME;
	}

	public void setEXAMPLE_NAME(String example_name) {
		EXAMPLE_NAME = example_name;
	}

	public String getHOSTCONFIGID() {
		return HOSTCONFIGID;
	}

	public void setHOSTCONFIGID(String hostconfigid) {
		HOSTCONFIGID = hostconfigid;
	}

	public String getUSER_MANAGE_ID() {
		return USER_MANAGE_ID;
	}

	public void setUSER_MANAGE_ID(String user_manage_id) {
		USER_MANAGE_ID = user_manage_id;
	}

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

	public String getCREDITUSER() {
		return CREDITUSER;
	}

	public void setCREDITUSER(String credituser) {
		CREDITUSER = credituser;
	}

	public int getEXECUTE_FLAG() {
		return EXECUTE_FLAG;
	}

	public void setEXECUTE_FLAG(int execute_flag) {
		EXECUTE_FLAG = execute_flag;
	}

	public String getHOSRPWD() {
		return HOSRPWD;
	}

	public void setHOSRPWD(String hosrpwd) {
		HOSRPWD = hosrpwd;
	}

	public int getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(int hostid) {
		HOSTID = hostid;
	}

	public String getHOSTNAME() {
		return HOSTNAME;
	}

	public void setHOSTNAME(String hostname) {
		HOSTNAME = hostname;
	}

	public String getHOSTUSERNAME() {
		return HOSTUSERNAME;
	}

	public void setHOSTUSERNAME(String hostusername) {
		HOSTUSERNAME = hostusername;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}

	public String getSPACE() {
		return SPACE;
	}

	public void setSPACE(String space) {
		SPACE = space;
	}

	public String getSPECIALPATH() {
		return SPECIALPATH;
	}

	public void setSPECIALPATH(String specialpath) {
		SPECIALPATH = specialpath;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int status) {
		STATUS = status;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int type) {
		TYPE = type;
	}

	public String getUPDATETTIME() {
		return UPDATETTIME;
	}

	public void setUPDATETTIME(String updatettime) {
		UPDATETTIME = updatettime;
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

	/**
	 *
	 * @return the hOSTPORT
	 */
	public Integer getHOSTPORT() {
		return HOSTPORT;
	}

	/**
	 *
	 * @param hOSTPORT the hOSTPORT to set
	 */
	public void setHOSTPORT(Integer hOSTPORT) {
		HOSTPORT = hOSTPORT;
	}

}
