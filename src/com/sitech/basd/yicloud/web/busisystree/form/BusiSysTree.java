package com.sitech.basd.yicloud.web.busisystree.form;

/**
 * 
 * <p>
 * Title: BusiSysTree
 * </p>
 * <p>
 * Description: 业务系统树数据类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-20 下午2:05:29
 * 
 */
public class BusiSysTree {

	private String id;
	private String name; // 实体名称
	private String icon;// 生成树的图标路径
	private Integer type;// 实体类型
	private String entityId; // 实体Id
	private Boolean isParent = true; // 是否是父节点
	private String state = "-1";// 虚拟机的状态,0是启动，1是停止，2是挂起
	private String authority;// 虚拟机的权限 // ；vmware主机的状态，0是进入维护模式，1是退出维护模式
	private Boolean highlight = false;// 判断节点是否被选中
	private String tree_uuid;// 树节点的uuid
	private Boolean nocheck = true;
	private Boolean checked = false;// 是否处于选中状态
	private String hostIP;// 节点所在主机IP
	private String userName;// 主机用户
	private String pId;

	public String getPId() {
		return pId;
	}

	public void setPId(String id) {
		pId = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

}
