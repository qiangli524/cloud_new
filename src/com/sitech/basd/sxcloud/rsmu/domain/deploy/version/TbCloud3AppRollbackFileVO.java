package com.sitech.basd.sxcloud.rsmu.domain.deploy.version;

/**
 * 
 * <p>
 * Title: TbCloud3AppRollbackFileVO
 * </p>
 * <p>
 * Description: 应用部署-上线文件回滚文件类
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
 * @createtime 2013-3-25 下午10:18:33
 * 
 */
public class TbCloud3AppRollbackFileVO {
	// 回滚记录ID
	private String rollbackId;
	// 回滚文件ID
	private String rollbackFilePath;
	// 插入时间
	private String insertTime;
	private String id;
	private String rollbackFileFullPath;

	public String getRollbackFilePath() {
		return rollbackFilePath;
	}

	public void setRollbackFilePath(String rollbackFilePath) {
		this.rollbackFilePath = rollbackFilePath;
	}

	public String getRollbackFileFullPath() {
		return rollbackFileFullPath;
	}

	public void setRollbackFileFullPath(String rollbackFileFullPath) {
		this.rollbackFileFullPath = rollbackFileFullPath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRollbackId() {
		return rollbackId;
	}

	public void setRollbackId(String rollbackId) {
		this.rollbackId = rollbackId;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

}
