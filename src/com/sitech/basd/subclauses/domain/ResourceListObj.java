package com.sitech.basd.subclauses.domain;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class ResourceListObj extends BaseObj implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id; //模板id
	private String name;  //模板名称
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
	
}
