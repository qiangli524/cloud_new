package com.sitech.ssd.ah.reportform.vm.domain;

import java.io.Serializable;
import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;

/**
 * 呵呵
 * <p>
 * Title: VmReportForm
 * </p>
 * <p>
 * Description: TODO(用一句话描述该文件做什么)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author shijc
 * @version 1.0
 * @createtime 2013-12-18 下午4:07:28
 * 
 */
public class VmReportForm extends BasePrivilegeObj implements Serializable {

	private String hyId;
	private String eqId;

	private String startDate;// 报表开始时间
	private String endDate;// 报表结束时间
	private String tableName;// 表的名称
	private int cpuStartUsage;// 最低使用率
	private int cpuEndUsage;// 最高使用率
	private int memStartUsage;// 最低使用率
	private int memEndUsage;// 最高使用率
	private int diskStartUsage;
	private int diskEndUsage;
	private int networkStartUsage;
	private int networkEndUsage;

	private String cpuUsageType;//
	private String memUsageType;//
	private String diskUsageType;//
	private String networkUsageType;//

	private String vmId;// 虚拟机的connected+“_”+uuid
	private String hostId;// 虚拟机的connected+“_”+uuid
	private String vmName;// 虚拟机的名称
	private String hostName;// 主机的名称
	private String network;// 网络域
	private String busi;// 承载业务
	private String busiParent;// 业务子系统
	private String username;// 业务联系人
	private String ip;// ip地址
	private String kpiId;// kpi
	private String vmType;//虚拟机的类型
	private String hostType;//物理机的类型
	private String sysType;// 文件系统类型

	private String disk_ioReadMaxValue;// 磁盘读
	private String disk_ioReadMinValue;// 磁盘读
	private String disk_ioWriteMaxValue;// 磁盘写
	private String disk_ioWriteMinValue;// 磁盘写

	private String network_ioUpMaxValue;// 网络上行
	private String network_ioUpMinValue;// 网络上行
	private String network_ioDownMaxValue;// 网络下行
	private String network_ioDownMinValue;// 网络下行

	private String cpuAll;
	private String cpu_maxKpiValue;// 最大的kpivalue
	private String cpu_maxKpiValueTime;// 最大的kpivalue
	private String cpu_avgKpiValue;// 平均
	private String cpu_avgKpiValueTime;// 平均
	private String cpu_minKpiValue;// 最小
	private String cpu_minKpiValueTime;// 最小

	private String memAll;
	private String mem_maxKpiValue;// 最大的kpivalue
	private String mem_maxKpiValueTime;// 最大的kpivalue
	private String mem_avgKpiValue;// 平均
	private String mem_avgKpiValueTime;// 平均
	private String mem_minKpiValue;// 最小
	private String mem_minKpiValueTime;// 最小

	private String storeUsed_maxKpiValue;// 存储使用量的峰值
	private String storeAll;// 存储总量
	private String storeUsedPercent; // 存储使用百分比

	private String sr_maxKpiValue;// 最大的kpivalue
	private String sr_maxKpiValueTime;// 最大的kpivalue
	private String sr_avgKpiValue;// 平均
	private String sr_avgKpiValueTime;// 平均
	private String sr_minKpiValue;// 最小
	private String sr_minKpiValueTime;// 最小
	private int dots;// 呈现点数
	private String coll_time;// 搜集时间
	/** 数据中心列表 */
	private List<UnitedTreeObj> dataCenterList;
	/** 数据中心uuid */
	private String center_uuid;
	/** 数据展示topN */
	private int top_num;
	/** 标志位查询类别 */
	private String cpu_mem;
	/** 标志位查询图形类别 */
	private String top_line;
	/** 标志是否为了添加页面 */
	private int isForAdd;
	private String uuid;

	private String insert_time;
	private String busi_status;

	public String getUuid() {
		return uuid;
	}

	public String getHostType() {
		return hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public String getEqId() {
		return eqId;
	}

	public void setEqId(String eqId) {
		this.eqId = eqId;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

	public String getBusi_status() {
		return busi_status;
	}

	public void setBusi_status(String busi_status) {
		this.busi_status = busi_status;
	}

	public String getDiskUsageType() {
		return diskUsageType;
	}

	public void setDiskUsageType(String diskUsageType) {
		this.diskUsageType = diskUsageType;
	}

	public String getNetworkUsageType() {
		return networkUsageType;
	}

	public void setNetworkUsageType(String networkUsageType) {
		this.networkUsageType = networkUsageType;
	}

	public int getDiskStartUsage() {
		return diskStartUsage;
	}

	public void setDiskStartUsage(int diskStartUsage) {
		this.diskStartUsage = diskStartUsage;
	}

	public int getDiskEndUsage() {
		return diskEndUsage;
	}

	public void setDiskEndUsage(int diskEndUsage) {
		this.diskEndUsage = diskEndUsage;
	}

	public int getNetworkStartUsage() {
		return networkStartUsage;
	}

	public void setNetworkStartUsage(int networkStartUsage) {
		this.networkStartUsage = networkStartUsage;
	}

	public int getNetworkEndUsage() {
		return networkEndUsage;
	}

	public void setNetworkEndUsage(int networkEndUsage) {
		this.networkEndUsage = networkEndUsage;
	}

	public int getIsForAdd() {
		return isForAdd;
	}

	public void setIsForAdd(int isForAdd) {
		this.isForAdd = isForAdd;
	}

	public String getColl_time() {
		return coll_time;
	}

	public void setColl_time(String coll_time) {
		this.coll_time = coll_time;
	}

	private List resultList;

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getTop_line() {
		return top_line;
	}

	public String getDisk_ioReadMaxValue() {
		return disk_ioReadMaxValue;
	}

	public void setDisk_ioReadMaxValue(String disk_ioReadMaxValue) {
		this.disk_ioReadMaxValue = disk_ioReadMaxValue;
	}

	public String getDisk_ioReadMinValue() {
		return disk_ioReadMinValue;
	}

	public void setDisk_ioReadMinValue(String disk_ioReadMinValue) {
		this.disk_ioReadMinValue = disk_ioReadMinValue;
	}

	public String getDisk_ioWriteMaxValue() {
		return disk_ioWriteMaxValue;
	}

	public void setDisk_ioWriteMaxValue(String disk_ioWriteMaxValue) {
		this.disk_ioWriteMaxValue = disk_ioWriteMaxValue;
	}

	public String getDisk_ioWriteMinValue() {
		return disk_ioWriteMinValue;
	}

	public void setDisk_ioWriteMinValue(String disk_ioWriteMinValue) {
		this.disk_ioWriteMinValue = disk_ioWriteMinValue;
	}

	public String getNetwork_ioUpMaxValue() {
		return network_ioUpMaxValue;
	}

	public void setNetwork_ioUpMaxValue(String network_ioUpMaxValue) {
		this.network_ioUpMaxValue = network_ioUpMaxValue;
	}

	public String getNetwork_ioUpMinValue() {
		return network_ioUpMinValue;
	}

	public void setNetwork_ioUpMinValue(String network_ioUpMinValue) {
		this.network_ioUpMinValue = network_ioUpMinValue;
	}

	public String getNetwork_ioDownMaxValue() {
		return network_ioDownMaxValue;
	}

	public void setNetwork_ioDownMaxValue(String network_ioDownMaxValue) {
		this.network_ioDownMaxValue = network_ioDownMaxValue;
	}

	public String getNetwork_ioDownMinValue() {
		return network_ioDownMinValue;
	}

	public void setNetwork_ioDownMinValue(String network_ioDownMinValue) {
		this.network_ioDownMinValue = network_ioDownMinValue;
	}

	public void setTop_line(String top_line) {
		this.top_line = top_line;
	}

	public int getDots() {
		return dots;
	}

	public void setDots(int dots) {
		this.dots = dots;
	}

	public String getCpu_mem() {
		return cpu_mem;
	}

	public void setCpu_mem(String cpu_mem) {
		this.cpu_mem = cpu_mem;
	}

	public int getTop_num() {
		return top_num;
	}

	public void setTop_num(int top_num) {
		this.top_num = top_num;
	}

	private String PROJECT_NAME;

	public String getVmId() {
		return vmId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getHyId() {
		return hyId;
	}

	public void setHyId(String hyId) {
		this.hyId = hyId;
	}

	public String getCpu_maxKpiValueTime() {
		return cpu_maxKpiValueTime;
	}

	public void setCpu_maxKpiValueTime(String cpu_maxKpiValueTime) {
		this.cpu_maxKpiValueTime = cpu_maxKpiValueTime;
	}

	public String getCpu_avgKpiValueTime() {
		return cpu_avgKpiValueTime;
	}

	public String getCpuUsageType() {
		return cpuUsageType;
	}

	public void setCpuUsageType(String cpuUsageType) {
		this.cpuUsageType = cpuUsageType;
	}

	public String getMemUsageType() {
		return memUsageType;
	}

	public void setMemUsageType(String memUsageType) {
		this.memUsageType = memUsageType;
	}

	public void setCpu_avgKpiValueTime(String cpu_avgKpiValueTime) {
		this.cpu_avgKpiValueTime = cpu_avgKpiValueTime;
	}

	public String getCpu_minKpiValueTime() {
		return cpu_minKpiValueTime;
	}

	public void setCpu_minKpiValueTime(String cpu_minKpiValueTime) {
		this.cpu_minKpiValueTime = cpu_minKpiValueTime;
	}

	public String getMem_maxKpiValueTime() {
		return mem_maxKpiValueTime;
	}

	public void setMem_maxKpiValueTime(String mem_maxKpiValueTime) {
		this.mem_maxKpiValueTime = mem_maxKpiValueTime;
	}

	public String getMem_avgKpiValueTime() {
		return mem_avgKpiValueTime;
	}

	public void setMem_avgKpiValueTime(String mem_avgKpiValueTime) {
		this.mem_avgKpiValueTime = mem_avgKpiValueTime;
	}

	public String getMem_minKpiValueTime() {
		return mem_minKpiValueTime;
	}

	public void setMem_minKpiValueTime(String mem_minKpiValueTime) {
		this.mem_minKpiValueTime = mem_minKpiValueTime;
	}

	public String getSr_maxKpiValueTime() {
		return sr_maxKpiValueTime;
	}

	public void setSr_maxKpiValueTime(String sr_maxKpiValueTime) {
		this.sr_maxKpiValueTime = sr_maxKpiValueTime;
	}

	public String getSr_avgKpiValueTime() {
		return sr_avgKpiValueTime;
	}

	public void setSr_avgKpiValueTime(String sr_avgKpiValueTime) {
		this.sr_avgKpiValueTime = sr_avgKpiValueTime;
	}

	public String getSr_minKpiValueTime() {
		return sr_minKpiValueTime;
	}

	public void setSr_minKpiValueTime(String sr_minKpiValueTime) {
		this.sr_minKpiValueTime = sr_minKpiValueTime;
	}

	public int getCpuStartUsage() {
		return cpuStartUsage;
	}

	public void setCpuStartUsage(int cpuStartUsage) {
		this.cpuStartUsage = cpuStartUsage;
	}

	public int getCpuEndUsage() {
		return cpuEndUsage;
	}

	public void setCpuEndUsage(int cpuEndUsage) {
		this.cpuEndUsage = cpuEndUsage;
	}

	public int getMemStartUsage() {
		return memStartUsage;
	}

	public void setMemStartUsage(int memStartUsage) {
		this.memStartUsage = memStartUsage;
	}

	public int getMemEndUsage() {
		return memEndUsage;
	}

	public void setMemEndUsage(int memEndUsage) {
		this.memEndUsage = memEndUsage;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setVmId(String vmId) {
		this.vmId = vmId;
	}

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getBusi() {
		return busi;
	}

	public void setBusi(String busi) {
		this.busi = busi;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}

	public String getVmType() {
		return vmType;
	}

	public void setVmType(String vmType) {
		this.vmType = vmType;
	}

	public String getCpu_maxKpiValue() {
		return cpu_maxKpiValue;
	}

	public void setCpu_maxKpiValue(String cpu_maxKpiValue) {
		this.cpu_maxKpiValue = cpu_maxKpiValue;
	}

	public String getCpu_avgKpiValue() {
		return cpu_avgKpiValue;
	}

	public void setCpu_avgKpiValue(String cpu_avgKpiValue) {
		this.cpu_avgKpiValue = cpu_avgKpiValue;
	}

	public String getCpu_minKpiValue() {
		return cpu_minKpiValue;
	}

	public void setCpu_minKpiValue(String cpu_minKpiValue) {
		this.cpu_minKpiValue = cpu_minKpiValue;
	}

	public String getMem_maxKpiValue() {
		return mem_maxKpiValue;
	}

	public void setMem_maxKpiValue(String mem_maxKpiValue) {
		this.mem_maxKpiValue = mem_maxKpiValue;
	}

	public String getMem_avgKpiValue() {
		return mem_avgKpiValue;
	}

	public void setMem_avgKpiValue(String mem_avgKpiValue) {
		this.mem_avgKpiValue = mem_avgKpiValue;
	}

	public String getMem_minKpiValue() {
		return mem_minKpiValue;
	}

	public String getSr_maxKpiValue() {
		return sr_maxKpiValue;
	}

	public void setSr_maxKpiValue(String sr_maxKpiValue) {
		this.sr_maxKpiValue = sr_maxKpiValue;
	}

	public String getSr_avgKpiValue() {
		return sr_avgKpiValue;
	}

	public void setSr_avgKpiValue(String sr_avgKpiValue) {
		this.sr_avgKpiValue = sr_avgKpiValue;
	}

	public String getSr_minKpiValue() {
		return sr_minKpiValue;
	}

	public void setSr_minKpiValue(String sr_minKpiValue) {
		this.sr_minKpiValue = sr_minKpiValue;
	}

	public void setMem_minKpiValue(String mem_minKpiValue) {
		this.mem_minKpiValue = mem_minKpiValue;
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

	public String getPROJECT_NAME() {
		return PROJECT_NAME;
	}

	public void setPROJECT_NAME(String pROJECT_NAME) {
		PROJECT_NAME = pROJECT_NAME;
	}

	public String getStoreAll() {
		return storeAll;
	}

	public void setStoreAll(String storeAll) {
		this.storeAll = storeAll;
	}

	public String getStoreUsed_maxKpiValue() {
		return storeUsed_maxKpiValue;
	}

	public void setStoreUsed_maxKpiValue(String storeUsed_maxKpiValue) {
		this.storeUsed_maxKpiValue = storeUsed_maxKpiValue;
	}

	public String getStoreUsedPercent() {
		return storeUsedPercent;
	}

	public void setStoreUsedPercent(String storeUsedPercent) {
		this.storeUsedPercent = storeUsedPercent;
	}

	public String getBusiParent() {
		return busiParent;
	}

	public void setBusiParent(String busiParent) {
		this.busiParent = busiParent;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCpuAll() {
		return cpuAll;
	}

	public void setCpuAll(String cpuAll) {
		this.cpuAll = cpuAll;
	}

	public String getMemAll() {
		return memAll;
	}

	public void setMemAll(String memAll) {
		this.memAll = memAll;
	}

	public String getSysType() {
		return sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}

}
