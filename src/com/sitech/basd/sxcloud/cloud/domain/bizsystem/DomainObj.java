package com.sitech.basd.sxcloud.cloud.domain.bizsystem;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class DomainObj extends BaseObj implements Serializable, Cloneable{
	private String REGION_ID; //区域编号
	private String REGION_NAME; //区域名称
	private String REGION_DESC;  //描述
	public String getREGION_ID() {
		return REGION_ID;
	}
	public void setREGION_ID(String region_id) {
		REGION_ID = region_id;
	}
	public String getREGION_NAME() {
		return REGION_NAME;
	}
	public void setREGION_NAME(String region_name) {
		REGION_NAME = region_name;
	}
	public String getREGION_DESC() {
		return REGION_DESC;
	}
	public void setREGION_DESC(String region_desc) {
		REGION_DESC = region_desc;
	}
	
}
