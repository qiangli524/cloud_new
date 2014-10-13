package com.sitech.basd.yicloud.domain.busisystree;

import java.util.List;

/**
 * 
 * <p>
 * Title: TbBusiSysTree
 * </p>
 * <p>
 * Description: 业务系统树数据库Obj
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
public class TbBusiSysTree {
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
	// 登录用户名字
	private String username;
	// 基准应用或者部署实例所在主机
	private String hostIP;

	private int appNum;
	private int sysNum;
	private int depNum;

	private List<String> pidlist;
	
	public int getAppNum() {
		return appNum;
	}

	public void setAppNum(int appNum) {
		this.appNum = appNum;
	}

	public int getSysNum() {
		return sysNum;
	}

	public void setSysNum(int sysNum) {
		this.sysNum = sysNum;
	}

	public int getDepNum() {
		return depNum;
	}

	public void setDepNum(int depNum) {
		this.depNum = depNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public List<String> getPidlist() {
		return pidlist;
	}

	public void setPidlist(List<String> pidlist) {
		this.pidlist = pidlist;
	}

}
