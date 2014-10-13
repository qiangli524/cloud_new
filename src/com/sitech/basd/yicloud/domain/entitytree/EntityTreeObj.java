package com.sitech.basd.yicloud.domain.entitytree;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class EntityTreeObj extends BaseObj implements Serializable, Cloneable {

	private int id;
	private String name; // 实体名称
	private String typeName;// 类型名称
	private int parentId; // 父节点ID
	private String entityId; // 实体ID-VMID、主机ID等
	private String type;// 实体类型-VM、HOST、DataCenter等
	private String userId;// 用户Id
	private String userName;// 用户名
	private static final long serialVersionUID = -1714469510588445079L;
	private String c_uuid;// 集群uuid(xen)
	private String h_uuid;// 主机uuid(xen)
	private String vh_uuid;// 虚拟机uuid(xen)
	private String temp;// 备用类型(用于查询)
	private String authority;// 虚拟机权限
	private String group_id;// 供DRS组管理使用
	private String flag;// 主机、虚拟机权限分配区分

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getC_uuid() {
		return c_uuid;
	}

	public void setC_uuid(String c_uuid) {
		this.c_uuid = c_uuid;
	}

	public String getH_uuid() {
		return h_uuid;
	}

	public void setH_uuid(String h_uuid) {
		this.h_uuid = h_uuid;
	}

	public String getVh_uuid() {
		return vh_uuid;
	}

	public void setVh_uuid(String vh_uuid) {
		this.vh_uuid = vh_uuid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
