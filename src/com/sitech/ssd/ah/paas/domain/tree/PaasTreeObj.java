package com.sitech.ssd.ah.paas.domain.tree;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: PaasTreeObj
 * </p>
 * <p>
 * Description:paas资源树相关属性
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-3-28 上午9:15:54
 * 
 */
public class PaasTreeObj extends BaseObj {
	private String id;
	private String name;// 节点名字
	private String entity_id;// 实体ID
	private String parent_id;// 父节点ID
	private String node_type;// 节点类型
	private String server_type;// 服务类型
	private String host_address;// 服务器地址
	private int host_port;// 主机端口

	private String icon;
	private Boolean isParent = true; // 是否是父节点
	private Boolean nocheck = true;
	private String flag;
	private String busi_id; //业务资源视图表tb_busi_system_tree的id
	private String title;//用于树节点显示异常信息
	private String table;
	
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

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
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

	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getServer_type() {
		return server_type;
	}

	public void setServer_type(String server_type) {
		this.server_type = server_type;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getHost_address() {
		return host_address;
	}

	public void setHost_address(String host_address) {
		this.host_address = host_address;
	}

	public int getHost_port() {
		return host_port;
	}

	public void setHost_port(int host_port) {
		this.host_port = host_port;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getBusi_id() {
		return busi_id;
	}

	public void setBusi_id(String busi_id) {
		this.busi_id = busi_id;
	}

}
