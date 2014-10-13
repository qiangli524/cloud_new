package com.sitech.basd.sxcloud.rsmu.domain.deploy.version;



/** 
 * 
 * <p>Title: TbCloud3AppFileVersionVO</p>
 * <p>Description: TODO(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author wangqxa
 * @version 1.0
 * @createtime 2013-3-25 下午4:59:50
 *
 */
public class TbCloud3AppFileVersionVO {
	// 主键，实体ID
	private String id;
	// 文件或目录名称
	private String fileName;
	// 文件或目录路径
	private String filePath;
	// 文件版本
	private int version;
	// 应用版本
	private String appVersion;
	// 插入时间
	private String insertDate;
	private int appId;
	// 全路径
	private String fileFullPath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getFileFullPath() {
		return fileFullPath;
	}

	public void setFileFullPath(String fileFullPath) {
		this.fileFullPath = fileFullPath;
	}

}
