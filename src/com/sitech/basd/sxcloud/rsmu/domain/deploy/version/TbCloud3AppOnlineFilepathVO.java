package com.sitech.basd.sxcloud.rsmu.domain.deploy.version;

/**
 * 
 * <p>
 * Title: TbCloud3AppFileVersionVO
 * </p>
 * <p>
 * Description: TODO(用一句话描述该文件做什么)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author wangqxa
 * @version 1.0
 * @createtime 2013-3-25 下午4:59:50
 * 
 */
public class TbCloud3AppOnlineFilepathVO {
	// 主键，应用ID
	private int appid;
	// 文件或目录名称
	private String filePath;
	private String insertTime;
	private String day_version;
	private String id;
	private String versionDesc;

	public String getVersionDesc() {
		return versionDesc;
	}

	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

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

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
