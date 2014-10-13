package com.sitech.basd.yicloud.domain.datastore;

/**
 * 
 * <p>
 * Title: DataStoreInfo
 * </p>
 * <p>
 * Description: 数据存储信息类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Jul 26, 2012 1:16:14 PM
 * 
 */
public class DataStoreInfo {
	private String name;// 存储名称
	private String storageUrl;//
	private String capacity;// 容量
	private String freeSpace;// 剩余空间
	private String type;// 设备类型
	private String collTime;// 采集时间
	private Boolean accessible;
	private String accessMode;
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStorageUrl() {
		return storageUrl;
	}

	public void setStorageUrl(String storageUrl) {
		this.storageUrl = storageUrl;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getFreeSpace() {
		return freeSpace;
	}

	public void setFreeSpace(String freeSpace) {
		this.freeSpace = freeSpace;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCollTime() {
		return collTime;
	}

	public void setCollTime(String collTime) {
		this.collTime = collTime;
	}

	public Boolean getAccessible() {
		return accessible;
	}

	public void setAccessible(Boolean accessible) {
		this.accessible = accessible;
	}

	public String getAccessMode() {
		return accessMode;
	}

	public void setAccessMode(String accessMode) {
		this.accessMode = accessMode;
	}
}