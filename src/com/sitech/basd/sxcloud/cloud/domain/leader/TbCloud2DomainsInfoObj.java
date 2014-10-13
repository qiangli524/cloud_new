package com.sitech.basd.sxcloud.cloud.domain.leader;

/**
 * 域信息
 * @author zhangwj
 * @Date 2011.12.1
 */
public class TbCloud2DomainsInfoObj {
	
	private String REGION_ID = null;   //域编号
	
	private String REGION_NAME = null; //域名称
	private String REGION_DESC = null; //域描述

	public String getREGION_DESC() {
		return REGION_DESC;
	}

	public void setREGION_DESC(String region_desc) {
		REGION_DESC = region_desc;
	}

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
	
	
}
