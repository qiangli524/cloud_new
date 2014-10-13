package com.sitech.basd.cloud3.domain.app;

/**
 * 
 * <p>
 * Title: TbCloud3AppFileTreeVO
 * </p>
 * <p>
 * Description: 应用部署目录、文件树实体类
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
 * @createtime 2013-3-16 下午1:10:35
 * 
 */
public class TbCloud3AppFileTreeVO {
	// 主键，实体ID
	private String id;
	// 文件或目录名称
	private String fileName;
	// 文件或目录路径
	private String filePath;
	// 文件类型：文件Or目录
	private int fileType;
	// 文件是否已修改或更新
	private int ifUpdate;
	// 父节点ID
	private String parent_id;
	// 应用ID
	private String appId;
	private Boolean isParent;

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

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

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public int getIfUpdate() {
		return ifUpdate;
	}

	public void setIfUpdate(int ifUpdate) {
		this.ifUpdate = ifUpdate;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
}
