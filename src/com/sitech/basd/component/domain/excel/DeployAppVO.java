package com.sitech.basd.component.domain.excel;

/**
 * 
 * <p>
 * Title: DeployAppVO
 * </p>
 * <p>
 * Description: 部署实例数据库熟悉VO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-29 下午1:57:31
 * 
 */
public class DeployAppVO extends BaseAppVO {
	// 部署实例ID
	private Integer deployExampleId;
	// 部署实例实体名称
	private String deployExampleName;
	// 部署文件路径--相对于基准应用的文件路径
	private String deployPath;
	// 启动脚本路径
	private String startShell;
	// 停止脚本路径
	private String shutdownShell;
	// 是否重启
	private Integer isRestart;
	// 是否备份
	private Integer isBackup;
	// Tb_busi_host_config Id
	private Integer hostConfigId;

	public String getDeployExampleName() {
		return deployExampleName;
	}

	public void setDeployExampleName(String deployExampleName) {
		this.deployExampleName = deployExampleName;
	}

	public Integer getHostConfigId() {
		return hostConfigId;
	}

	public void setHostConfigId(Integer hostConfigId) {
		this.hostConfigId = hostConfigId;
	}

	public Integer getIsRestart() {
		return isRestart;
	}

	public void setIsRestart(Integer isRestart) {
		this.isRestart = isRestart;
	}

	public Integer getIsBackup() {
		return isBackup;
	}

	public void setIsBackup(Integer isBackup) {
		this.isBackup = isBackup;
	}

	public Integer getDeployExampleId() {
		return deployExampleId;
	}

	public void setDeployExampleId(Integer deployExampleId) {
		this.deployExampleId = deployExampleId;
	}

	public String getDeployPath() {
		return deployPath;
	}

	public void setDeployPath(String deployPath) {
		this.deployPath = deployPath;
	}

	public String getStartShell() {
		return startShell;
	}

	public void setStartShell(String startShell) {
		this.startShell = startShell;
	}

	public String getShutdownShell() {
		return shutdownShell;
	}

	public void setShutdownShell(String shutdownShell) {
		this.shutdownShell = shutdownShell;
	}
}
