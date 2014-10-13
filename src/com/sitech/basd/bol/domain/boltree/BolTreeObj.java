package com.sitech.basd.bol.domain.boltree;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>
 * Title: RolTreeObj
 * </p>
 * <p>
 * Description: 资源视图树表实体类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-10-31 上午11:03:34
 * 
 */
public class BolTreeObj extends BaseObj {

	private String id;// 主键
	private String name;// 节点名称
	private String parent_id;// 父节点ID
	private String uuid;// 实体标识
	private String type;// 节点类型
	private float state;//节点状态

	private Boolean isParent = true; // 是否是父节点
	private String icon;// 树上的图标
	private Boolean nocheck = true;

	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getId() {
		return id;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
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

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public float getState() {
		return state;
	}

	public void setState(float state) {
		this.state = state;
	}

}
