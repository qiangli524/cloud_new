package com.sitech.basd.sxcloud.rsmu.web.deploy.form;

import java.util.List;

@SuppressWarnings("serial")
public class DeployExampleForm {

	/*
	 * 部署实例管理
	 */
	private int ID; // '编号',
	private int HOSTID; // '主机编号',
	private String DEPLOYETIME; // '部署时间',
	// private int STRATEGYID; // '策略编号',
	private String DEPLOY_FLAG; // 0：未部署 1：已部署 ，2：卸载，3：已卸载
	private String START_STOP_FLAG; // '0：停止 1：启动',
	private String UPDATETIME; // '启停时间',
	private String RESULT; // 返回结果
	private String RELEASE_FLAG; // 负载均衡返回结果
	private List resultList = null;
	private String IP; // 主机名称
	private String VLANIP; // 服务IP
	// private String STRATEGYNAME;//策略名称
	private int APPID; // '应用编号',
	private String standardHostIP; // '基准机IP',
	private String standardPath; // '基准机应用部署路径',
	private String deployPath; // '部署机应用部署路径',
	private String startsh; // '启动脚本',
	private String stopsh; // '停止脚本',
	private String APPNAME; // 应用名称
	private String APPPORT; // 部署实例访问端口
	private String KEYNAME; // 部署实例访问中间件
	// private List strategyList=null;//策略集合
	private List hostList = null;// 主机集合
	private List appList = null; // 应用集合
	private String VLAN; // 部署Vlan
	private String NEED_NUMBERS; // 申请时的工单编号
	private String FLOW_TYPE; // 申请时工作流中的类型
	private int isrestart; // 是否重启应用标志位
	private int isbackup; // 是否备份应用标志位
	private String checkboxhostids; // 选择要部署的主机列表
	private List bizList;// 业务系统集合
	private String BIZID;// 业务系统Id
	private String versionDesc;// 业务系统Id
	// 上线或回滚文件
	private String onlinePath;
	private String DEPLOYFILEPATH;
	private List HOSEUSERLIST;
	private String HOSEUSERID;
	// 部署实例名称
	private String exampleName;
	// 部署实例访问路径
	private String appVisitPath;
	// 信任关系
	private String TRUST_FLAG;
	// 是否为WEB
	private String DEPLOYEXAMPLE_TYPE;
	//业务系统ID
	private String SYS_ID;
	//任务ID
	private String TASK_ID;

	public String getSYS_ID() {
		return SYS_ID;
	}

	public void setSYS_ID(String sYS_ID) {
		SYS_ID = sYS_ID;
	}

	public String getDEPLOYEXAMPLE_TYPE() {
		return DEPLOYEXAMPLE_TYPE;
	}

	public void setDEPLOYEXAMPLE_TYPE(String deployexample_type) {
		DEPLOYEXAMPLE_TYPE = deployexample_type;
	}

	public String getTRUST_FLAG() {
		return TRUST_FLAG;
	}

	public void setTRUST_FLAG(String trust_flag) {
		TRUST_FLAG = trust_flag;
	}

	public String getAppVisitPath() {
		return appVisitPath;
	}

	public void setAppVisitPath(String appVisitPath) {
		this.appVisitPath = appVisitPath;
	}

	public String getExampleName() {
		return exampleName;
	}

	public void setExampleName(String exampleName) {
		this.exampleName = exampleName;
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

	public String getDEPLOYFILEPATH() {
		return DEPLOYFILEPATH;
	}

	public void setDEPLOYFILEPATH(String deployfilepath) {
		DEPLOYFILEPATH = deployfilepath;
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

	public List getBizList() {
		return bizList;
	}

	public void setBizList(List bizList) {
		this.bizList = bizList;
	}

	public String getBIZID() {
		return BIZID;
	}

	public void setBIZID(String bizid) {
		BIZID = bizid;
	}

	public String getCheckboxhostids() {
		return checkboxhostids;
	}

	public void setCheckboxhostids(String checkboxhostids) {
		this.checkboxhostids = checkboxhostids;
	}

	public String getDeployPath() {
		return deployPath;
	}

	public void setDeployPath(String deployPath) {
		this.deployPath = deployPath;
	}

	public String getStandardHostIP() {
		return standardHostIP;
	}

	public void setStandardHostIP(String standardHostIP) {
		this.standardHostIP = standardHostIP;
	}

	public String getStandardPath() {
		return standardPath;
	}

	public void setStandardPath(String standardPath) {
		this.standardPath = standardPath;
	}

	public String getStartsh() {
		return startsh;
	}

	public void setStartsh(String startsh) {
		this.startsh = startsh;
	}

	public String getStopsh() {
		return stopsh;
	}

	public void setStopsh(String stopsh) {
		this.stopsh = stopsh;
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

	public String getFLOW_TYPE() {
		return FLOW_TYPE;
	}

	public void setFLOW_TYPE(String flow_type) {
		FLOW_TYPE = flow_type;
	}

	public String getNEED_NUMBERS() {
		return NEED_NUMBERS;
	}

	public void setNEED_NUMBERS(String need_numbers) {
		NEED_NUMBERS = need_numbers;
	}

	public List getHostList() {
		return hostList;
	}

	public void setHostList(List hostList) {
		this.hostList = hostList;
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

	public String getDEPLOY_FLAG() {
		return DEPLOY_FLAG;
	}

	public void setDEPLOY_FLAG(String deploy_flag) {
		DEPLOY_FLAG = deploy_flag;
	}

	public String getDEPLOYETIME() {
		return DEPLOYETIME;
	}

	public void setDEPLOYETIME(String deployetime) {
		DEPLOYETIME = deployetime;
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

	/*
	 * public int getSTRATEGYID() { return STRATEGYID; } public void
	 * setSTRATEGYID(int strategyid) { STRATEGYID = strategyid; }
	 */
	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
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
	 * public List getStrategyList() { return strategyList; } public void
	 * setStrategyList(List strategyList) { this.strategyList = strategyList; }
	 * public String getSTRATEGYNAME() { return STRATEGYNAME; } public void
	 * setSTRATEGYNAME(String strategyname) { STRATEGYNAME = strategyname; }
	 */

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

	public String getVLAN() {
		return VLAN;
	}

	public void setVLAN(String vlan) {
		VLAN = vlan;
	}

	/*
	 * 清空ActionForm
	 */
	public void reset() {
		this.APPID = 0;
		this.ID = 0;
		this.HOSTID = 0;
		this.DEPLOYETIME = null;
		this.DEPLOY_FLAG = null;
		this.START_STOP_FLAG = null;
		this.UPDATETIME = null;
		this.resultList = null;
		this.APPPORT = null;
		this.KEYNAME = null;
	}

	public String getTASK_ID() {
		return TASK_ID;
	}

	public void setTASK_ID(String task_id) {
		TASK_ID = task_id;
	}

}
