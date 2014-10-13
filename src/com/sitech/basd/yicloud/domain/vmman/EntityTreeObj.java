package com.sitech.basd.yicloud.domain.vmman;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class EntityTreeObj extends BaseObj implements Serializable, Cloneable {

	private int id;
	private String name; //实体名称
	private String typeName;// 类型名称
	private int parentId; //父节点ID
	private String entityId; //实体ID-VMID、主机ID等
	private String type;//实体类型-VM、HOST、DataCenter等
	private static final long serialVersionUID = -1714469510588445079L;

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

}
