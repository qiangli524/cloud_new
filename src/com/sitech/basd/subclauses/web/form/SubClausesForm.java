package com.sitech.basd.subclauses.web.form;

import java.util.List;

/**
 * 
 * <p>
 * Title: SubClausesForm
 * </p>
 * <p>
 * Description: 服务条目管理Form
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author zhuxla
 * @version 1.0
 * @createtime 2014-4-18
 * 
 */
public class SubClausesForm {

	private String id; //主键id(服务编码)
	private String name;  //名称
	private String indate;  //有效时间
	private String role;  //权限
	private String declare;  //说明
	private String resource_id;  //资源模板编码id(资源表id)
	private String resource_name;  //资源模板名称
	private String resource_info;  //资源规格
	private String type_id;  //服务类别id(tb_service_type表id)
	private String state;  //状态(0=无效,1=有效)
	private String resource_type; //模板类别(1:虚拟模板，
	private String acType; //操作类型，1增加,2修改
	private String publish_state; //发布状态，0未发布，1已发布
	private List<?> resultList;
	private List<?> ResourceList;
	
	
	public String getPublish_state() {
		return publish_state;
	}
	public void setPublish_state(String publish_state) {
		this.publish_state = publish_state;
	}
	public List<?> getResourceList() {
		return ResourceList;
	}
	public void setResourceList(List<?> resourceList) {
		ResourceList = resourceList;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String getAcType() {
		return acType;
	}
	public void setAcType(String acType) {
		this.acType = acType;
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
	public List<?> getResultList() {
		return resultList;
	}
	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}
}
