package com.sitech.basd.ibmmanager.domain;

/**
 * 
 * <p>
 * Title: IBMManagerTreeObj
 * </p>
 * <p>
 * Description: IBM小型机管理树 相关属性
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-11-2 下午5:42:01
 * 
 */
public class IBMManagerTreeObj {
	private String id; // 主键UUID
	private String name; // 节点名称
	private String parent_id;// 父节点ID
	private String uuid;// 节点实体ID
	private String vtype;// 实体类型：HMC-0、x86等
	private String type;// 节点类型
	private String state;// 电源状态

	private String icon;// 生成树的图标路径
	private Boolean isParent = true; // 是否是父节点
	private Boolean highlight = false;// 判断节点是否被选中
	private String tree_uuid;// 树节点的uuid
	private Boolean nocheck = true;
	private Boolean checked = false;// 是否处于选中状态

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

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getHighlight() {
		return highlight;
	}

	public void setHighlight(Boolean highlight) {
		this.highlight = highlight;
	}

	public String getTree_uuid() {
		return tree_uuid;
	}

	public void setTree_uuid(String tree_uuid) {
		this.tree_uuid = tree_uuid;
	}

	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
