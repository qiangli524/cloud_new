package com.sitech.shop.domain.balance;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class LoadBalanceObj extends BaseObj {

	private String id; // 唯一标示
	private String name; // 负载名称
	private String type; // 负载类型：1-公网负载，2-内网负载
	private String purchase_way;// 负载均衡方式：2-包年包月、1-按小时
	private String begin_time; // 开始时间
	private String end_time; // 结束时间
	private String state; // 状态
	private String user_id; // 用户id
	private String area_id; // 所属区域
	private String service_status;// /服务状态。例如：已经过期，即将过期等

	public String getService_status() {
		return service_status;
	}

	public void setService_status(String service_status) {
		this.service_status = service_status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPurchase_way() {
		return purchase_way;
	}

	public void setPurchase_way(String purchase_way) {
		this.purchase_way = purchase_way;
	}

	public String getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

}
