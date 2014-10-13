package com.sitech.basd.yicloud.domain.cluster;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class ClusterObj extends BaseObj implements Serializable, Cloneable {
	private int id;
	private String dcName;// 集群所在数据中心名称
	private String name;// 集群名称
	private String type;// 集群类型
	private String hastate;// 集群HA状态
	private String drsstate;// 集群DRS状态
	private String c_uuid;// 唯一标识
	private String parentId;
	private String group_name;
	private String group_id;
	private String entityId;

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getC_uuid() {
		return c_uuid;
	}

	public void setC_uuid(String c_uuid) {
		this.c_uuid = c_uuid;
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

	public String getHastate() {
		return hastate;
	}

	public void setHastate(String hastate) {
		this.hastate = hastate;
	}

	public String getDrsstate() {
		return drsstate;
	}

	public void setDrsstate(String drsstate) {
		this.drsstate = drsstate;
	}

	public String getDcName() {
		return dcName;
	}

	public void setDcName(String dcName) {
		this.dcName = dcName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
