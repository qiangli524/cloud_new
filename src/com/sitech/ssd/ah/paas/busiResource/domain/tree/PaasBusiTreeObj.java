package com.sitech.ssd.ah.paas.busiResource.domain.tree;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>
 * Title: PaasBusiTreeObj
 * </p>
 * <p>
 * Description: paas业务资源树节点对象
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-7-24 下午5:15:44
 * 
 */
public class PaasBusiTreeObj extends BaseObj {
	// 与表结构对应
	private String id;
	private String uuid;// 节点唯一标示
	private String name;// 节点名字
	private String entity_id;// 实体ID
	private String node_type;// 节点类型
	private String parent_id;
	private String server_type;// 服务类型
	private String icon;
	private Boolean isParent = true; // 是否是父节点
	private Boolean nocheck = true;
	// 新添属性
	private String eq_name;
	private String eq_ip;
	private String vmName;
	private String ip;
	private String center_uuid;
	private List dataCenterList;
	private int busiNum;// boss节点下的业务数

	public int getBusiNum() {
		return busiNum;
	}

	public void setBusiNum(int busiNum) {
		this.busiNum = busiNum;
	}

	public List getDataCenterList() {
		return dataCenterList;
	}

	public void setDataCenterList(List dataCenterList) {
		this.dataCenterList = dataCenterList;
	}

	public String getCenter_uuid() {
		return center_uuid;
	}

	public void setCenter_uuid(String center_uuid) {
		this.center_uuid = center_uuid;
	}

	public String getEq_name() {
		return eq_name;
	}

	public void setEq_name(String eq_name) {
		this.eq_name = eq_name;
	}

	public String getEq_ip() {
		return eq_ip;
	}

	public void setEq_ip(String eq_ip) {
		this.eq_ip = eq_ip;
	}

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
}
