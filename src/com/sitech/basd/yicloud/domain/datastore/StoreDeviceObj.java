package com.sitech.basd.yicloud.domain.datastore;

import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;

public class StoreDeviceObj extends BasePrivilegeObj {
	private String id;
	private String uuid;
	private String name;// 存储名称
	private String storeNum;// 存储块个数
	private String capacity;// 总量
	private String freeSpace;// 剩余量
	private String ip;// ip地址
	private Integer type;// 类型
	private Integer queryType;
	private String queryName;
	private Integer usedStoreNum;// 已使用的存储块
	private List<String> idList;
	private String typeName;
	private String cache_space;// cache容量
	private String raid_way;// raid方式
	private String supporter;// 厂商
	private String port_num;// 端口数量
	private String valid_space;// 磁盘有效容量

	/** 存储设备挂载的存储块的总容量 */
	private String storeBlockTotalSize;
	/** 存储设备挂载的存储块的可用容量 */
	private String storeBlockFreeSpace;
	/** 存储设备挂载的存储块的置备空间 */
	private String purchaseSpace;
	private String device_num;// 设备编号
	private String location;// 设备存放位置
	private String status;// 设备状态
	private Integer disk_group_num;//磁盘组数量

	public String getDevice_num() {
		return device_num;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setDevice_num(String device_num) {
		this.device_num = device_num;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCache_space() {
		return cache_space;
	}

	public void setCache_space(String cache_space) {
		this.cache_space = cache_space;
	}

	public String getRaid_way() {
		return raid_way;
	}

	public void setRaid_way(String raid_way) {
		this.raid_way = raid_way;
	}

	public String getSupporter() {
		return supporter;
	}

	public void setSupporter(String supporter) {
		this.supporter = supporter;
	}

	public String getPort_num() {
		return port_num;
	}

	public void setPort_num(String port_num) {
		this.port_num = port_num;
	}

	public String getValid_space() {
		return valid_space;
	}

	public void setValid_space(String valid_space) {
		this.valid_space = valid_space;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<String> getIdList() {
		return idList;
	}

	public void setIdList(List<String> idList) {
		this.idList = idList;
	}

	public Integer getUsedStoreNum() {
		return usedStoreNum;
	}

	public void setUsedStoreNum(Integer usedStoreNum) {
		this.usedStoreNum = usedStoreNum;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
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

	public String getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(String storeNum) {
		this.storeNum = storeNum;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getFreeSpace() {
		return freeSpace;
	}

	public void setFreeSpace(String freeSpace) {
		this.freeSpace = freeSpace;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStoreBlockTotalSize() {
		return storeBlockTotalSize;
	}

	public void setStoreBlockTotalSize(String storeBlockTotalSize) {
		this.storeBlockTotalSize = storeBlockTotalSize;
	}

	public String getStoreBlockFreeSpace() {
		return storeBlockFreeSpace;
	}

	public void setStoreBlockFreeSpace(String storeBlockFreeSpace) {
		this.storeBlockFreeSpace = storeBlockFreeSpace;
	}

	public String getPurchaseSpace() {
		return purchaseSpace;
	}

	public void setPurchaseSpace(String purchaseSpace) {
		this.purchaseSpace = purchaseSpace;
	}

	public Integer getDisk_group_num() {
		return disk_group_num;
	}

	public void setDisk_group_num(Integer disk_group_num) {
		this.disk_group_num = disk_group_num;
	}

}
