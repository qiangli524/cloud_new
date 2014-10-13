package com.sitech.basd.yicloud.web.busisystree.form;

/**
 * 
 * <p>
 * Title: TbBusiSysTreeForm
 * </p>
 * <p>
 * Description: 业务系统树页面实体类
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
 * @createtime 2013-5-20 下午1:35:55
 * 
 */
public class TbBusiSysTreeForm {
	// 主键-UUID
	private String id;
	// 节点名称
	private String name;
	// 0-根节点1-业务系统2-基准应用3-部署实例
	private Integer type;
	// 父节点ID
	private String parentId;
	// 实体ID与业务系统表、基准应用表、部署实例表关联
	private String busiId;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

}
