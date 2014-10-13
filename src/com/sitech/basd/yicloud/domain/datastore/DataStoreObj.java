package com.sitech.basd.yicloud.domain.datastore;

import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;

public class DataStoreObj extends BasePrivilegeObj {
	private int ID;
	private String NAME;// 存储名称
	private String STORAGE_URL;//
	private String CAPACITY;// 容量
	private String FREE_SPACE;// 剩余空间
	private String TYPE;// 设备类型
	private Byte[] INFO;
	private Byte[] CAPABILITY;
	private String DATACENTER_ID;// 数据中心ID
	private int IORM_ENABLED;//
	private int IORM_CONGESTION_THRESHOLD;
	private int MAINTENANCE_MODE;
	private String HOST_ID;// 存储所属的主机ID
	private String CLUSTER_ID;// 存储所在集群Id
	private String REMOTE_HOST;
	private String REMOTE_PATH;
	private String readOnly;// 挂载nfs时是否为只读模式
	private String store_uuid;// xen中存储的uuid
	private String shared;// 是否共享
	private String use_per;// 已使用百分比
	private String STATE;// 存储状态
	private String CONHOSTNUM;// 连接主机数
	// 集群标识 VMware-集群ID Xen-资源池pool-UUID(共有)
	private String connectId;
	private String deviceId;// 存储设备id
	private String deviceName;// 存储设备名称
	private String deviceType;// 存储设备类型
	private String dataStoreType;// 存储类型
	private String purchaseSpace;// 置备空间
	private String storetype; // 存储接入端设备类型：vmFile\hostFile

	public String getPurchaseSpace() {
		return purchaseSpace;
	}

	public void setPurchaseSpace(String purchaseSpace) {
		this.purchaseSpace = purchaseSpace;
	}

	public String getDataStoreType() {
		return dataStoreType;
	}

	public void setDataStoreType(String dataStoreType) {
		this.dataStoreType = dataStoreType;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	private List<String> hostidlist;

	public List<String> getHostidlist() {
		return hostidlist;
	}

	public void setHostidlist(List<String> hostidlist) {
		this.hostidlist = hostidlist;
	}

	public String getConnectId() {
		return connectId;
	}

	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}

	public String getCONHOSTNUM() {
		return CONHOSTNUM;
	}

	public void setCONHOSTNUM(String cONHOSTNUM) {
		CONHOSTNUM = cONHOSTNUM;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String state) {
		STATE = state;
	}

	public String getUse_per() {
		return use_per;
	}

	public void setUse_per(String use_per) {
		this.use_per = use_per;
	}

	public String getShared() {
		return shared;
	}

	public void setShared(String shared) {
		this.shared = shared;
	}

	public String getStore_uuid() {
		return store_uuid;
	}

	public void setStore_uuid(String store_uuid) {
		this.store_uuid = store_uuid;
	}

	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
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

	public String getHOST_ID() {
		return HOST_ID;
	}

	public void setHOST_ID(String host_id) {
		HOST_ID = host_id;
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

	public String getDATACENTER_ID() {
		return DATACENTER_ID;
	}

	public void setDATACENTER_ID(String datacenter_id) {
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

	public String getCLUSTER_ID() {
		return CLUSTER_ID;
	}

	public void setCLUSTER_ID(String cluster_id) {
		CLUSTER_ID = cluster_id;
	}

	public String getStoretype() {
		return storetype;
	}

	public void setStoretype(String storetype) {
		this.storetype = storetype;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("NAME:" + this.getNAME() + ",");
		sb.append("STORAGE_URL:" + this.getSTORAGE_URL() + ",");
		sb.append("CAPACITY:" + this.getCAPACITY() + ",");
		sb.append("FREE_SPACE:" + this.getFREE_SPACE() + ",");
		sb.append("TYPE:" + this.getTYPE() + ",");
		sb.append("DATACENTER_ID:" + this.getDATACENTER_ID() + ",");
		sb.append("CLUSTER_ID:" + this.getCLUSTER_ID() + ",");
		sb.append("HOST_ID:" + this.getHOST_ID() + ",");
		sb.append("store_uuid:" + this.getStore_uuid());
		sb.append("}");
		return sb.toString();
	}

	public boolean equals(Object obj) {
		if (obj instanceof DataStoreObj) {
			DataStoreObj tempObj = (DataStoreObj) obj;
			if (this.getNAME().equals(tempObj.getNAME())
					// && this.getSTORAGE_URL().equals(tempObj.getSTORAGE_URL())
					&& this.getCAPACITY().equals(tempObj.getCAPACITY())
					&& this.getFREE_SPACE().equals(tempObj.getCAPACITY())
					&& this.getTYPE().equals(tempObj.getTYPE())
					&& String.valueOf(this.DATACENTER_ID).equals(tempObj.getDATACENTER_ID())
					&& String.valueOf(this.getCLUSTER_ID()).equals(tempObj.getCLUSTER_ID())
					&& String.valueOf(this.getHOST_ID()).equals(tempObj.getHOST_ID())
					&& String.valueOf(this.getStore_uuid()).equals(tempObj.getStore_uuid())) {
				return true;
			}
		}
		return false;
	}
}
