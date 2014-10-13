package com.sitech.basd.sxcloud.rsmu.domain.deploy.version;

/**
 * 
 * <p>
 * Title: TbCloud3AppVersionVO
 * </p>
 * <p>
 * Description: 应用版本
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
 * @createtime 2013-3-23 上午10:05:50
 * 
 */
public class TbCloud3AppVersionVO {
	// 唯一标示UUID
	private String id;
	// 基准应用ID
	private int app_id;
	// 升级版本
	private int version;
	// 插入时间
	private String insertTime;
	// 日期时间版本
	private String day_version;

	public String getDay_version() {
		return day_version;
	}

	public void setDay_version(String day_version) {
		this.day_version = day_version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getApp_id() {
		return app_id;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
}
