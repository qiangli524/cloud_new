package com.sitech.basd.yicloud.domain.xentree;

public class XenTreeObj {

	private int id;
	private String name; // 实体名称
	private String icon;// 生成树的图标路径
	private String type;// 实体类型
	private String entityId; // 实体Id
	private Boolean isParent = true; // 是否是父节点
	private String state = "-1";// 虚拟机的状态,0是启动，1是停止，2是挂起
	// ；vmware主机的状态，0是进入维护模式，1是退出维护模式
	private Boolean highlight = false;// 判断节点是否被选中
	private String tree_uuid;// 树节点的uuid
	private String authority;// 虚拟机权限
	private boolean operation;

	public boolean isOperation() {
		return operation;
	}

	public void setOperation(boolean operation) {
		this.operation = operation;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getTree_uuid() {
		return tree_uuid;
	}

	public void setTree_uuid(String tree_uuid) {
		this.tree_uuid = tree_uuid;
	}

	public Boolean getHighlight() {
		return highlight;
	}

	public void setHighlight(Boolean highlight) {
		this.highlight = highlight;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

}
