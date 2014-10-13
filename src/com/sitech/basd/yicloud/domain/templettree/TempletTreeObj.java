package com.sitech.basd.yicloud.domain.templettree;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TempletTreeObj extends BaseObj implements Serializable, Cloneable {
	private int id;
	private String name;
	private int type; //类型：1代表目录；2代表templet
	private int parent_id; //父节点的id
	private String templet_id; //对应tempelt的id

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getTemplet_id() {
		return templet_id;
	}

	public void setTemplet_id(String templet_id) {
		this.templet_id = templet_id;
	}

}
