package com.sitech.basd.deployfile.domain;

/**
 * 
 * <p>
 * Title: DeployFileTreeObj
 * </p>
 * <p>
 * Description: 部署文件树相关属性
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
 * @createtime 2013-12-16 上午11:36:40
 * 
 */
public class DeployFileTreeObj {
	private String id; // 主键
	private String name;// 文件名字
	private String file_url;// 文件路径
	private String parent_id;// 父节点ID
	private String state;// 状态(增加，删除，修改)
	private String type;// 文件类型(0,固定节点 1,文件夹 2,文件)
	private String order_id;// 订单ID
	private String update_time;// 插入时间
	private String taskId;// 任务ID
	private String orderId;// 订单ID

	private String icon;// 生成树的图标路径
	private Boolean isParent = true; // 是否是父节点
	private Boolean highlight = false;// 判断节点是否被选中
	private String tree_uuid;// 树节点的uuid
	private Boolean nocheck = false;// false是checkbox，true没有checkbox
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

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

}
