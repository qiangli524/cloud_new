package com.sitech.shop.domain.broadband;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class RuleNumberObj extends BaseObj{
	private String id;
	private Integer rule;
	private String isused;
	private String user_id;
	private String acl_id;
	private String type;//防火墙，交换机区分
	
	public RuleNumberObj() {
		super();
	}

	public RuleNumberObj(String id) {
		super();
		this.id = id;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRule() {
		return rule;
	}

	public void setRule(Integer rule) {
		this.rule = rule;
	}

	public String getIsused() {
		return isused;
	}

	public void setIsused(String isused) {
		this.isused = isused;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAcl_id() {
		return acl_id;
	}

	public void setAcl_id(String acl_id) {
		this.acl_id = acl_id;
	}

	@Override
	public String toString() {
		return "RuleNumberObj [id=" + id + ", rule=" + rule + ", isused="
				+ isused + ", user_id=" + user_id + ", acl_id=" + acl_id
				+ ", type=" + type + "]";
	}
	
}
