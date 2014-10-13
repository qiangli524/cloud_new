package com.sitech.basd.component.domain.excel;

/**
 * 
 * <p>
 * Title: BaseAppVO
 * </p>
 * <p>
 * Description: 实例信息父类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-1-3 下午5:25:27
 * 
 */
public class BaseAppVO {
	// 主机SSH登录用户名
	protected String hostUsername;
	// 主机Ssh登录密码
	protected String sshPwd;
	// 主机Ssh登录端口
	protected Integer sshPort;
	// 主机信任关系用户
	protected String creditUser;
	// 部署应用根路径 /opt/apache-tomcat-6.0.35/webapps/manager
	protected String basePath;
	// 部署主机IP
	protected String hostIp;
	// 基准应用ID
	protected Integer appId;
	// 应用名称
	protected String appName;
	// 是否是基准应用
	protected String ifStandardApp;
	// tb_user_managerId
	protected String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIfStandardApp() {
		return ifStandardApp;
	}

	public void setIfStandardApp(String ifStandardApp) {
		this.ifStandardApp = ifStandardApp;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getHostUsername() {
		return hostUsername;
	}

	public void setHostUsername(String hostUsername) {
		this.hostUsername = hostUsername;
	}

	public String getSshPwd() {
		return sshPwd;
	}

	public void setSshPwd(String sshPwd) {
		this.sshPwd = sshPwd;
	}

	public Integer getSshPort() {
		return sshPort;
	}

	public void setSshPort(Integer sshPort) {
		this.sshPort = sshPort;
	}

	public String getCreditUser() {
		return creditUser;
	}

	public void setCreditUser(String creditUser) {
		this.creditUser = creditUser;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
}
