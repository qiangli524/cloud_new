package com.sitech.basd.sxcloud.rsmu.domain.deploy.version;

/**
 * 
 * <p>
 * Title: TbCloud3AppRollbackInfo
 * </p>
 * <p>
 * Description: 应用部署-上线-回滚记录信息类
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
 * @createtime 2013-3-25 下午10:21:18
 * 
 */
public class TbCloud3AppRollbackInfoVO {
	// 主键
	private String id;
	// 基准应用ID
	private int appId;
	// 执行状态：0:未回滚，1:正在回滚2:已回滚3：回滚失败
	private int status;
	// 插入记录时间
	private String insertTime;
	// 回滚时间
	private String rollbackTime;
	// 扫描状态
	private int executeFlag;
	// 执行百分比
	private String executePercent;
	// 部署实例IDs
	private String deployIds;
	// 恢复类型
	private int resume_type;
	// 日期版本
	private String day_version;

	public int getResume_type() {
		return resume_type;
	}

	public void setResume_type(int resume_type) {
		this.resume_type = resume_type;
	}

	public String getDay_version() {
		return day_version;
	}

	public void setDay_version(String day_version) {
		this.day_version = day_version;
	}

	public String getDeployIds() {
		return deployIds;
	}

	public void setDeployIds(String deployIds) {
		this.deployIds = deployIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getRollbackTime() {
		return rollbackTime;
	}

	public void setRollbackTime(String rollbackTime) {
		this.rollbackTime = rollbackTime;
	}

	public int getExecuteFlag() {
		return executeFlag;
	}

	public void setExecuteFlag(int executeFlag) {
		this.executeFlag = executeFlag;
	}

	public String getExecutePercent() {
		return executePercent;
	}

	public void setExecutePercent(String executePercent) {
		this.executePercent = executePercent;
	}

}
