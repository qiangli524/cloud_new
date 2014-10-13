package com.sitech.ssd.ah.boss.domain.monitor;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>
 * Title: BossTreeObj
 * </p>
 * <p>
 * Description: Boss树对象
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-8-25 上午11:12:19
 * 
 */
public class BossTreeObj extends BaseObj {
	private String uuid;// 节点id
	private String parent_name;// 父节点ID
	private String name;// 节点名
	private String type;// 节点类型 0根节点、1集群、2池、3主机
	private String icon;// 图标
	private Boolean isParent = false; // 是否是父节点
	private String grand_name;// 父父节点名

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public String getGrand_name() {
		return grand_name;
	}

	public void setGrand_name(String grand_name) {
		this.grand_name = grand_name;
	}

}
