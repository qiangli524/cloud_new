package com.sitech.basd.subclauses.domain;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class SubClausesObj extends BaseObj implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id; //主键id(服务编码)
	private String name;  //名称
	private String indate;  //有效时间
	private String role;  //权限
	private String declare;  //说明
	private String resource_id;  //资源模板编码id(资源表id)
	private String resource_name;  //资源模板名称
	private String resource_info;  //资源规格
	private String resource_type; //模板类别(1:虚拟模板，
	private String type_id;  //服务类别id(tb_service_type表id)
	private String state;  //状态(0=无效,1=有效)
	private String publish_state; //发布状态，0未发布，1已发布
	
	public String getPublish_state() {
		return publish_state;
	}
	public void setPublish_state(String publish_state) {
		this.publish_state = publish_state;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
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
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDeclare() {
		return declare;
	}
	public void setDeclare(String declare) {
		this.declare = declare;
	}
	public String getResource_id() {
		return resource_id;
	}
	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public String getResource_info() {
		return resource_info;
	}
	public void setResource_info(String resource_info) {
		this.resource_info = resource_info;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
