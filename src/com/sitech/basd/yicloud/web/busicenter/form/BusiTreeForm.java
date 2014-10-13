package com.sitech.basd.yicloud.web.busicenter.form;

import java.util.List;

public class BusiTreeForm {
	private List resultList;// 结果列表
	private String appId;// 应用Id
	private String id;
	private String name;// 名称
	private String process;// 进程名
	private String desc;// 描述
	private String status;// 状态
	private String standardHostIP;// 基准机IP
	private String standardPath;// 基准机应用基准路径
	private String deployPath;// 部署机上线文件路径
	private String startsh;// 启动脚本
	private String stopsh;// 停止脚本

	// 业务系统个数
	private int busiSysNum;
	// 基准应用个数
	private int busiAppNum;
	// 部署实例个数
	private int busiDepNum;

	// 连接数据库信息
	private String serviceName; // 服务名
	private String port; // 端口号
	private String ipAddress; // ip地址

	// 部署实例相关
	private String hostUserName;
	private String deployHostIp;
	private String deployeEndtime;
	private String start_stop_flag;
	private String deploy_flag;
	private String script;

	// 数据库相关
	private String flag;
	private String service_name;
	private String tabale_space;
	private String ipaddr;
	private String usrname;
	private String password;
	// 部署实例名称
	private String exampleName;
	// 部署实例访问地址
	private String appAccessPath;
	// 实例部署结果日志
	private String deployPercent;
	// 服务状态
	private String serveState;
	// 是否为WEB服务
	private String deployExampleType;

	public String getDeployExampleType() {
		return deployExampleType;
	}

	public void setDeployExampleType(String deployExampleType) {
		this.deployExampleType = deployExampleType;
	}

	public String getServeState() {
		return serveState;
	}

	public void setServeState(String serveState) {
		this.serveState = serveState;
	}

	public String getDeployPercent() {
		return deployPercent;
	}

	public void setDeployPercent(String deployPercent) {
		this.deployPercent = deployPercent;
	}

	public String getAppAccessPath() {
		return appAccessPath;
	}

	public void setAppAccessPath(String appAccessPath) {
		this.appAccessPath = appAccessPath;
	}

	public String getExampleName() {
		return exampleName;
	}

	public void setExampleName(String exampleName) {
		this.exampleName = exampleName;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getTabale_space() {
		return tabale_space;
	}

	public void setTabale_space(String tabale_space) {
		this.tabale_space = tabale_space;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getUsrname() {
		return usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDeployHostIp() {
		return deployHostIp;
	}

	public void setDeployHostIp(String deployHostIp) {
		this.deployHostIp = deployHostIp;
	}

	public String getDeployeEndtime() {
		return deployeEndtime;
	}

	public void setDeployeEndtime(String deployeEndtime) {
		this.deployeEndtime = deployeEndtime;
	}

	public String getStart_stop_flag() {
		return start_stop_flag;
	}

	public void setStart_stop_flag(String start_stop_flag) {
		this.start_stop_flag = start_stop_flag;
	}

	public String getDeploy_flag() {
		return deploy_flag;
	}

	public void setDeploy_flag(String deploy_flag) {
		this.deploy_flag = deploy_flag;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getHostUserName() {
		return hostUserName;
	}

	public void setHostUserName(String hostUserName) {
		this.hostUserName = hostUserName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getBusiSysNum() {
		return busiSysNum;
	}

	public void setBusiSysNum(int busiSysNum) {
		this.busiSysNum = busiSysNum;
	}

	public int getBusiAppNum() {
		return busiAppNum;
	}

	public void setBusiAppNum(int busiAppNum) {
		this.busiAppNum = busiAppNum;
	}

	public int getBusiDepNum() {
		return busiDepNum;
	}

	public void setBusiDepNum(int busiDepNum) {
		this.busiDepNum = busiDepNum;
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

	public String getDeployPath() {
		return deployPath;
	}

	public void setDeployPath(String deployPath) {
		this.deployPath = deployPath;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

}
