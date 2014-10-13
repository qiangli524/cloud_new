package com.sitech.basd.yicloud.domain.busitree;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class BusiTreeObj extends BaseObj implements Serializable, Cloneable {

	private int id;
	private String name; //实体名称
	private int parentId; //父节点ID
	private String busiId; //业务类型Id，appid等
	private String type;//业务类型，等
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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

}
