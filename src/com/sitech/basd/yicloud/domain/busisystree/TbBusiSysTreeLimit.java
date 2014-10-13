package com.sitech.basd.yicloud.domain.busisystree;

/**
 * 
 * <p>
 * Title: TbBusiSysTreeLimit
 * </p>
 * <p>
 * Description: TB_BUSI_SYS_TREE-业务系统树-用户权限关联表
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
 * @createtime 2013-5-20 下午3:13:18
 * 
 */
public class TbBusiSysTreeLimit {
	// 主键UUID
	private String id;
	// TB_BUSI_SYS_TREE树节点ID
	private String treeNodeId;
	// 用户名
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTreeNodeId() {
		return treeNodeId;
	}

	public void setTreeNodeId(String treeNodeId) {
		this.treeNodeId = treeNodeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
