package com.sitech.shop.domain.broadband;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class AclNumberObj extends BaseObj{
	private String id;//唯一标示
	private Integer acl;//策略号值
	private String type;//策略号类型：防火墙、VLAN、交换机等
	private String oper_type;//操作类型：出、入
	private String isused;//是否使用
	private String user_id; //用户id
	private Integer rate;//速率
	
	public AclNumberObj() {
		super();
	}

	public AclNumberObj(String type, String oper_type) {
		super();
		this.type = type;
		this.oper_type = oper_type;
	}
	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getAcl() {
		return acl;
	}

	public void setAcl(Integer acl) {
		this.acl = acl;
	}

	public String getOper_type() {
		return oper_type;
	}

	public void setOper_type(String oper_type) {
		this.oper_type = oper_type;
	}

	@Override
	public String toString() {
		return "AclNumberObj [id=" + id + ", acl=" + acl + ", type=" + type
				+ ", oper_type=" + oper_type + ", isused=" + isused
				+ ", user_id=" + user_id + "]";
	}

}
