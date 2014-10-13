package com.sitech.basd.resource.domain.united;

import com.sitech.basd.common.domain.BasePrivilegeObj;

/**
 * 
 * <p>
 * Title: ResourceOutlineObj
 * </p>
 * <p>
 * Description: 首页展示相关属性
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-1-22 下午2:46:30
 * 
 */
public class ResourceOutlineObj extends BasePrivilegeObj {

	private int ipAllCount;//ip总量
	
	private int ipUsedCount;//ip使用量
	
	private String domainName;//网络域名称
	
	private String key;

	private String storeValid;
	
	private String storeAll;

	private String storeUsed;
	
	private String StoreFree;

	private String type;

	public String getStoreValid() {
		return storeValid;
	}

	public void setStoreValid(String storeValid) {
		this.storeValid = storeValid;
	}

	public String getStoreAll() {
		return storeAll;
	}

	public void setStoreAll(String storeAll) {
		this.storeAll = storeAll;
	}

	public String getStoreUsed() {
		return storeUsed;
	}

	public void setStoreUsed(String storeUsed) {
		this.storeUsed = storeUsed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getIpAllCount() {
		return ipAllCount;
	}

	public void setIpAllCount(int ipAllCount) {
		this.ipAllCount = ipAllCount;
	}

	public int getIpUsedCount() {
		return ipUsedCount;
	}

	public void setIpUsedCount(int ipUsedCount) {
		this.ipUsedCount = ipUsedCount;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getStoreFree() {
		return StoreFree;
	}

	public void setStoreFree(String storeFree) {
		StoreFree = storeFree;
	}
}
