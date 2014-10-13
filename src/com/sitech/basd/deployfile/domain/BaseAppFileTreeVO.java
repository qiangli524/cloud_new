package com.sitech.basd.deployfile.domain;

import java.util.Date;

/**
 * 
 * <p>
 * Title: BaseAppTreeVO
 * </p>
 * <p>
 * Description: 基准应用文件树
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-8-25 上午10:35:18
 * 
 */
public class BaseAppFileTreeVO {
	private String id; // 主键
	private String name;// 文件名字
	private String file_url;// 文件路径
	private String itype; // 类型（file 文件，directory 目录）
	private String parent_id;// 父节点ID
	private Date update_time;// 插入时间
	private String baseappId;// 关联基准应用ID
	private String alias;// 文件节点存储至Swift别名
	private String status;// 文件状态：0，在Swift已删除，1，文件正常

	private String icon;// 生成树的图标路径
	private Boolean isParent = true; // 是否是父节点
	private Boolean highlight = false;// 判断节点是否被选中
	private String tree_uuid;// 树节点的uuid
	private Boolean nocheck = false;// false是checkbox，true没有checkbox
	private Boolean checked = false;// 是否处于选中状态

	public String getItype() {
		return itype;
	}

	public void setItype(String itype) {
		this.itype = itype;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getBaseappId() {
		return baseappId;
	}

	public void setBaseappId(String baseappId) {
		this.baseappId = baseappId;
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

}
