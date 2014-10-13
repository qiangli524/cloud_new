package com.sitech.basd.yicloud.domain.device.diskgroup;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * @Title DiskGroupObj
 * @Description 磁盘组对象
 * @author lipp
 * @date 2014-6-1 下午5:03:59
 * @version 1.0
 * @Company si-tech
 */
public class DiskGroupObj extends BaseObj{

	private String uuid;//主键
	
	private String name;//磁盘组名称
	
	private String raidLevel;//raid级别
	
	private Double maxSeriesFreeCapacity;//最大连续空闲空间
	
	private String storeDeviceId;//所属存储设备id
	
	private String deviceName;//所属存储设备名称
	
	private Integer compare = 0;//比较符
	
	private Integer lun_num;//该磁盘组上Lun的块数
	
	private List<String> uuidList;//主键集合
	
	private String disk_id;
	private String disk_name;
	private String disk_seq;
	private String disk_number;
	private String disk_num;
	
	public String getDisk_num() {
		return disk_num;
	}

	public void setDisk_num(String disk_num) {
		this.disk_num = disk_num;
	}

	public String getDisk_number() {
		return disk_number;
	}

	public void setDisk_number(String disk_number) {
		this.disk_number = disk_number;
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

	public String getRaidLevel() {
		return raidLevel;
	}

	public void setRaidLevel(String raidLevel) {
		this.raidLevel = raidLevel;
	}

	public Double getMaxSeriesFreeCapacity() {
		return maxSeriesFreeCapacity;
	}

	public void setMaxSeriesFreeCapacity(Double maxSeriesFreeCapacity) {
		this.maxSeriesFreeCapacity = maxSeriesFreeCapacity;
	}

	public String getStoreDeviceId() {
		return storeDeviceId;
	}

	public void setStoreDeviceId(String storeDeviceId) {
		this.storeDeviceId = storeDeviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public List<String> getUuidList() {
		return uuidList;
	}

	public void setUuidList(List<String> uuidList) {
		this.uuidList = uuidList;
	}

	public Integer getCompare() {
		return compare;
	}

	public void setCompare(Integer compare) {
		this.compare = compare;
	}

	public Integer getLun_num() {
		return lun_num;
	}

	public void setLun_num(Integer lun_num) {
		this.lun_num = lun_num;
	}

	public String getDisk_id() {
		return disk_id;
	}

	public void setDisk_id(String disk_id) {
		this.disk_id = disk_id;
	}

	public String getDisk_name() {
		return disk_name;
	}

	public void setDisk_name(String disk_name) {
		this.disk_name = disk_name;
	}

	public String getDisk_seq() {
		return disk_seq;
	}

	public void setDisk_seq(String disk_seq) {
		this.disk_seq = disk_seq;
	}
	
}
