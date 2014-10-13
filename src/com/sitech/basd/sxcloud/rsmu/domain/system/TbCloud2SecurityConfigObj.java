package com.sitech.basd.sxcloud.rsmu.domain.system;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TbCloud2SecurityConfigObj extends BaseObj{
	private int id = 0;
	// 配置值，以,分割
	private String value = null;
	// 配置类型 0 为IP 检测，1 用户名 检测，2 登录次数检测
	private Integer type = null;
	// 配置描述
	private String desc = null;
	// 配置状态 0 禁用，1 启用
	private Integer status = null;
	// 更新时间，这个时间之后可以操作
	private String upd_date = null;
	private Integer queryType = null;
	private Integer queryStatus =null;
	
	public Integer getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(Integer queryStatus) {
		this.queryStatus = queryStatus;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUpd_date() {
		return upd_date;
	}

	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}

}
