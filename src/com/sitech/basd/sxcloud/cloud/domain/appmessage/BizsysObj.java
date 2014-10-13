package com.sitech.basd.sxcloud.cloud.domain.appmessage;

import java.io.Serializable;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class BizsysObj extends BaseObj implements Serializable, Cloneable {
	private String SYS_ID; // 系统编码
	private String SYS_NAME; // 系统名称
	private String REGION_ID; // 所属域编码
	private String SYS_DESC; // 系统描述
	private String STATUS; // 系统状态
	@SuppressWarnings("unchecked")
	private List resultList = null;

	public String getSYS_ID() {
		return SYS_ID;
	}

	public void setSYS_ID(String sys_id) {
		SYS_ID = sys_id;
	}

	public String getSYS_NAME() {
		return SYS_NAME;
	}

	public void setSYS_NAME(String sys_name) {
		SYS_NAME = sys_name;
	}

	public String getREGION_ID() {
		return REGION_ID;
	}

	public void setREGION_ID(String region_id) {
		REGION_ID = region_id;
	}

	public String getSYS_DESC() {
		return SYS_DESC;
	}

	public void setSYS_DESC(String sys_desc) {
		SYS_DESC = sys_desc;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String status) {
		STATUS = status;
	}

	@SuppressWarnings("unchecked")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
}
