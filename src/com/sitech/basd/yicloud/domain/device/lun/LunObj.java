package com.sitech.basd.yicloud.domain.device.lun;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * @Title LunObj
 * @Description LUN对象
 * @author lipp
 * @date 2014-6-1 下午5:04:13
 * @version 1.0
 * @Company si-tech
 */
public class LunObj extends BaseObj{

	private String uuid;//主键
	
	private String name;//lun块名称
	
	private Integer is_mapping;//是否映射
	
	private Double capacity;//容量
	
	private Integer healthy;//健康状态
	
	private Integer depth;//分条深度
	
	private Integer controller;//归属控制器
	
	private Integer write_tactics;//写策略
	
	private String diskGroupId;//所属磁盘组

	private String diskGroupName;//所属磁盘组名称
	
	private String deviceName;//所属存储设备名称
	
	private String deviceId;//所属存储设备id
	
	private Integer compare = 0;//比较符
	
	private List<String> uuidList;//主键集合
	
	private Integer num;//数量
	
	private String lun_type;
	
	private String disk_group_id;
	
	public String getDisk_group_id() {
		return disk_group_id;
	}

	public void setDisk_group_id(String disk_group_id) {
		this.disk_group_id = disk_group_id;
	}

	public String getLun_type() {
		return lun_type;
	}

	public void setLun_type(String lun_type) {
		this.lun_type = lun_type;
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

	public Integer getIs_mapping() {
		return is_mapping;
	}

	public void setIs_mapping(Integer is_mapping) {
		this.is_mapping = is_mapping;
	}

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public Integer getHealthy() {
		return healthy;
	}

	public void setHealthy(Integer healthy) {
		this.healthy = healthy;
	}

	public String getDiskGroupId() {
		return diskGroupId;
	}

	public void setDiskGroupId(String diskGroupId) {
		this.diskGroupId = diskGroupId;
	}

	public String getDiskGroupName() {
		return diskGroupName;
	}

	public void setDiskGroupName(String diskGroupName) {
		this.diskGroupName = diskGroupName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Integer getCompare() {
		return compare;
	}

	public void setCompare(Integer compare) {
		this.compare = compare;
	}

	public List<String> getUuidList() {
		return uuidList;
	}

	public void setUuidList(List<String> uuidList) {
		this.uuidList = uuidList;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Integer getController() {
		return controller;
	}

	public void setController(Integer controller) {
		this.controller = controller;
	}

	public Integer getWrite_tactics() {
		return write_tactics;
	}

	public void setWrite_tactics(Integer write_tactics) {
		this.write_tactics = write_tactics;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
}
