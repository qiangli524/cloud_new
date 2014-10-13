package com.sitech.basd.yicloud.web.datastore.form;

import java.util.List;

@SuppressWarnings("rawtypes")
public class DataStoreForm {
	private int ID;
	private String NAME;// 存储名称
	private String STORAGE_URL;//
	private String CAPACITY;// 容量
	private String FREE_SPACE;// 剩余空间
	private String TYPE;// 设备类型
	private Byte[] INFO;
	private Byte[] CAPABILITY;
	private int DATACENTER_ID;// 数据中心ID
	private int IORM_ENABLED;//
	private int IORM_CONGESTION_THRESHOLD;
	private int MAINTENANCE_MODE;
	private String REMOTE_HOST;
	private String REMOTE_PATH;
	private List resultList;
	private String queryName;
	private String queryType;
	private String queryState;
	private String totalStore; //存储总量
	private String totalUseStore;//存储总量已使用
	private String totalFreeStore;//存储总量剩余
	private String totalUserper;//存储总量使用百分比
	private String hostId; //主机ID
	private String deviceId;//存储设备ID
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getTotalStore() {
		return totalStore;
	}

	public void setTotalStore(String totalStore) {
		this.totalStore = totalStore;
	}

	public String getTotalUseStore() {
		return totalUseStore;
	}

	public void setTotalUseStore(String totalUseStore) {
		this.totalUseStore = totalUseStore;
	}

	public String getTotalFreeStore() {
		return totalFreeStore;
	}

	public void setTotalFreeStore(String totalFreeStore) {
		this.totalFreeStore = totalFreeStore;
	}

	public String getTotalUserper() {
		return totalUserper;
	}

	public void setTotalUserper(String totalUserper) {
		this.totalUserper = totalUserper;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
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

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getSTORAGE_URL() {
		return STORAGE_URL;
	}

	public void setSTORAGE_URL(String storage_url) {
		STORAGE_URL = storage_url;
	}

	public String getCAPACITY() {
		return CAPACITY;
	}

	public void setCAPACITY(String capacity) {
		CAPACITY = capacity;
	}

	public String getFREE_SPACE() {
		return FREE_SPACE;
	}

	public void setFREE_SPACE(String free_space) {
		FREE_SPACE = free_space;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public Byte[] getINFO() {
		return INFO;
	}

	public void setINFO(Byte[] info) {
		INFO = info;
	}

	public Byte[] getCAPABILITY() {
		return CAPABILITY;
	}

	public void setCAPABILITY(Byte[] capability) {
		CAPABILITY = capability;
	}

	public int getDATACENTER_ID() {
		return DATACENTER_ID;
	}

	public void setDATACENTER_ID(int datacenter_id) {
		DATACENTER_ID = datacenter_id;
	}

	public int getIORM_ENABLED() {
		return IORM_ENABLED;
	}

	public void setIORM_ENABLED(int iorm_enabled) {
		IORM_ENABLED = iorm_enabled;
	}

	public int getIORM_CONGESTION_THRESHOLD() {
		return IORM_CONGESTION_THRESHOLD;
	}

	public void setIORM_CONGESTION_THRESHOLD(int iorm_congestion_threshold) {
		IORM_CONGESTION_THRESHOLD = iorm_congestion_threshold;
	}

	public int getMAINTENANCE_MODE() {
		return MAINTENANCE_MODE;
	}

	public void setMAINTENANCE_MODE(int maintenance_mode) {
		MAINTENANCE_MODE = maintenance_mode;
	}

	public String getREMOTE_HOST() {
		return REMOTE_HOST;
	}

	public void setREMOTE_HOST(String remote_host) {
		REMOTE_HOST = remote_host;
	}

	public String getREMOTE_PATH() {
		return REMOTE_PATH;
	}

	public void setREMOTE_PATH(String remote_path) {
		REMOTE_PATH = remote_path;
	}
}
