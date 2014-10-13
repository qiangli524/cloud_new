package com.sitech.basd.resource.domain.united;

import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;

public class UnitedTreeObj extends BasePrivilegeObj {
	private String id;
	private String name;
	private String parent_id;// 父节点的ID
	private String vtype;// 虚拟化类型
	private String type;// 实体类型
	private String uuid;// 实体唯一标示
	private String connect_id;// /链接的唯一标示
	private String parent_uuid;// 父节点的唯一标识
	private List<String> pidlist;// 父节点id集合
	private String flag;// 只显示主机或虚拟机的标识
	private Integer user_id;// 拥有该资源权限的用户ID
	private String oper;
	private List netDomList;// 网络域
	private List subNetList;// 子网络域
	private String pratentId;// 添加Vlan时
	private String domPratentId;
	private String ip;// 查询时暂时存放IP地址
	private String parent_name;// 父节点名称
	private List<String> uniqueIdList;//
	private List<String> conn_uuidList;//

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getParent_uuid() {
		return parent_uuid;
	}

	public void setParent_uuid(String parent_uuid) {
		this.parent_uuid = parent_uuid;
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

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public List<String> getPidlist() {
		return pidlist;
	}

	public void setPidlist(List<String> pidlist) {
		this.pidlist = pidlist;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public List getNetDomList() {
		return netDomList;
	}

	public void setNetDomList(List netDomList) {
		this.netDomList = netDomList;
	}

	public List getSubNetList() {
		return subNetList;
	}

	public void setSubNetList(List subNetList) {
		this.subNetList = subNetList;
	}

	public String getPratentId() {
		return pratentId;
	}

	public void setPratentId(String pratentId) {
		this.pratentId = pratentId;
	}

	public String getDomPratentId() {
		return domPratentId;
	}

	public void setDomPratentId(String domPratentId) {
		this.domPratentId = domPratentId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public List<String> getUniqueIdList() {
		return uniqueIdList;
	}

	public void setUniqueIdList(List<String> uniqueIdList) {
		this.uniqueIdList = uniqueIdList;
	}

	public List<String> getConn_uuidList() {
		return conn_uuidList;
	}

	public void setConn_uuidList(List<String> conn_uuidList) {
		this.conn_uuidList = conn_uuidList;
	}

}
