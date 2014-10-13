package com.sitech.basd.yicloud.web.showVm.form;

import java.util.List;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;

public class ShowVmForm {
	private List resultList;
	private String VH_NAME;
	private String EQ_NAME;
	private String VH_TYPE;
	private String EQ_ID;
	private String VH_IP;
	private String VH_SYSTEM;
	private String VH_CPU;
	private String VH_MEM;
	private String VH_STORAGE;
	private String NICNUM;
	private String VH_STAT;
	private String VH_UUID;
	private String TREENODE_ID;
	private String POOL_UUID;
	private String HOST_UUID;
	private String ENTITY_ID;
	private String MatchState;
	private String APP_NAME;
	private String queryHostIp;
	private String queryName;
	private String queryVHIP;
	private String queryType;
	private String queryState;
	private List queryHostIpList;
	private String VH_NETWORK;
	private String H_ID;
	private String flag;
	
	
	
	private String center_uuid;
	/** 数据中心列表 */
	private List<UnitedTreeObj> dataCenterList;

	public List getQueryHostIpList() {
		return queryHostIpList;
	}

	public void setQueryHostIpList(List queryHostIpList) {
		this.queryHostIpList = queryHostIpList;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryState() {
		return queryState;
	}

	public void setQueryState(String queryState) {
		this.queryState = queryState;
	}

	public String getVH_IP() {
		return VH_IP;
	}

	public void setVH_IP(String vh_ip) {
		VH_IP = vh_ip;
	}

	public String getQueryHostIp() {
		return queryHostIp;
	}

	public void setQueryHostIp(String queryHostIp) {
		this.queryHostIp = queryHostIp;
	}

	public String getAPP_NAME() {
		return APP_NAME;
	}

	public void setAPP_NAME(String app_name) {
		APP_NAME = app_name;
	}

	public String getMatchState() {
		return MatchState;
	}

	public void setMatchState(String matchState) {
		MatchState = matchState;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getPOOL_UUID() {
		return POOL_UUID;
	}

	public void setPOOL_UUID(String pool_uuid) {
		POOL_UUID = pool_uuid;
	}

	public String getHOST_UUID() {
		return HOST_UUID;
	}

	public void setHOST_UUID(String host_uuid) {
		HOST_UUID = host_uuid;
	}

	public String getENTITY_ID() {
		return ENTITY_ID;
	}

	public void setENTITY_ID(String entity_id) {
		ENTITY_ID = entity_id;
	}

	public String getTREENODE_ID() {
		return TREENODE_ID;
	}

	public void setTREENODE_ID(String treenode_id) {
		TREENODE_ID = treenode_id;
	}

	public String getVH_NAME() {
		return VH_NAME;
	}

	public void setVH_NAME(String vh_name) {
		VH_NAME = vh_name;
	}

	public String getEQ_NAME() {
		return EQ_NAME;
	}

	public void setEQ_NAME(String eq_name) {
		EQ_NAME = eq_name;
	}

	public String getVH_TYPE() {
		return VH_TYPE;
	}

	public void setVH_TYPE(String vh_type) {
		VH_TYPE = vh_type;
	}

	public String getEQ_ID() {
		return EQ_ID;
	}

	public void setEQ_ID(String eq_id) {
		EQ_ID = eq_id;
	}

	public String getVH_SYSTEM() {
		return VH_SYSTEM;
	}

	public void setVH_SYSTEM(String vh_system) {
		VH_SYSTEM = vh_system;
	}

	public String getVH_CPU() {
		return VH_CPU;
	}

	public void setVH_CPU(String vh_cpu) {
		VH_CPU = vh_cpu;
	}

	public String getVH_MEM() {
		return VH_MEM;
	}

	public void setVH_MEM(String vh_mem) {
		VH_MEM = vh_mem;
	}

	public String getVH_STORAGE() {
		return VH_STORAGE;
	}

	public void setVH_STORAGE(String vh_storage) {
		VH_STORAGE = vh_storage;
	}

	public String getNICNUM() {
		return NICNUM;
	}

	public void setNICNUM(String nicnum) {
		NICNUM = nicnum;
	}

	public String getVH_STAT() {
		return VH_STAT;
	}

	public void setVH_STAT(String vh_stat) {
		VH_STAT = vh_stat;
	}

	public String getVH_UUID() {
		return VH_UUID;
	}

	public void setVH_UUID(String vh_uuid) {
		VH_UUID = vh_uuid;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getH_ID() {
		return H_ID;
	}

	public void setH_ID(String h_id) {
		H_ID = h_id;
	}

	public String getVH_NETWORK() {
		return VH_NETWORK;
	}

	public void setVH_NETWORK(String vh_network) {
		VH_NETWORK = vh_network;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getQueryVHIP() {
		return queryVHIP;
	}

	public void setQueryVHIP(String queryVHIP) {
		this.queryVHIP = queryVHIP;
	}

	public List<UnitedTreeObj> getDataCenterList() {
		return dataCenterList;
	}

	public void setDataCenterList(List<UnitedTreeObj> dataCenterList) {
		this.dataCenterList = dataCenterList;
	}

	public String getCenter_uuid() {
		return center_uuid;
	}

	public void setCenter_uuid(String center_uuid) {
		this.center_uuid = center_uuid;
	}
	
}
