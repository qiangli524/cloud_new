package com.sitech.ssd.gx.domain.huaweientitytree;

public class HuaweiTreeObj {
	private String id;
	private String name; // 实体名称
	private String icon;// 生成树的图标路径
	private int type;// 实体类型
	private String entityId; // 实体Id
	private Boolean isParent = true; // 是否是父节点
	private String state = "-1";// 虚拟机的状态,0是启动，1是停止，2是挂起
	private String authority;//虚拟机的权限	// ；vmware主机的状态，0是进入维护模式，1是退出维护模式
	private Boolean highlight = false;// 判断节点是否被选中
	private String tree_code;// 树节点的uuid
	private String dcCode; //数据中心唯一标识
	
	public String getDcCode() {
		return dcCode;
	}
	public void setDcCode(String dcCode) {
		this.dcCode = dcCode;
	}
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Boolean getHighlight() {
		return highlight;
	}
	public void setHighlight(Boolean highlight) {
		this.highlight = highlight;
	}
	public String getTree_code() {
		return tree_code;
	}
	public void setTree_code(String tree_code) {
		this.tree_code = tree_code;
	}
}

