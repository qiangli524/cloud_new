package com.sitech.basd.sxcloud.cloud.web.showresource.form;

import com.sitech.basd.common.domain.BasePrivilegeObj;

public class AlarmHostStatistics extends BasePrivilegeObj {
	private String lev;// 告警等级

	private String level;// 数据中心中 主页面用到的告警级别

	private String levelcount;// 告警个数

	private String netId; // 网络ID

	private String netName;// 网络名称

	private String usedCount; // 已用IP数

	private String allCount; // 总 IP数

	private String freeCount;// 可用IP数

	private String cpuAllCount;// CPU总数,主机中CPU的个数

	private String cpuUsedCount;// 已使用CPU,虚拟机中CPU的总数

	private String ipusage;
	// 工单
	private String allWO;// 工单总数

	private String unDealWO;// 未处理工单总数

	private String BOMCWO;// BOMC工单总数

	private String unDealBOMCWO;// BOMC未处理工单总数

	private String cloudWO;// 云平台工单总数

	private String unDealCloudWO;// 云平台未处理工单总数

	public String getLev() {
		return lev;
	}

	public void setLev(String lev) {
		this.lev = lev;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevelcount() {
		return levelcount;
	}

	public void setLevelcount(String levelcount) {
		this.levelcount = levelcount;
	}

	public String getUsedCount() {
		return usedCount;
	}

	public void setUsedCount(String usedCount) {
		this.usedCount = usedCount;
	}

	public String getAllCount() {
		return allCount;
	}

	public void setAllCount(String allCount) {
		this.allCount = allCount;
	}

	public String getFreeCount() {
		return freeCount;
	}

	public void setFreeCount(String freeCount) {
		this.freeCount = freeCount;
	}

	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}

	public String getCpuAllCount() {
		return cpuAllCount;
	}

	public void setCpuAllCount(String cpuAllCount) {
		this.cpuAllCount = cpuAllCount;
	}

	public String getCpuUsedCount() {
		return cpuUsedCount;
	}

	public void setCpuUsedCount(String cpuUsedCount) {
		this.cpuUsedCount = cpuUsedCount;
	}

	public String getIpusage() {
		return ipusage;
	}

	public void setIpusage(String ipusage) {
		this.ipusage = ipusage;
	}

	public String getAllWO() {
		return allWO;
	}

	public void setAllWO(String allWO) {
		this.allWO = allWO;
	}

	public String getUnDealWO() {
		return unDealWO;
	}

	public void setUnDealWO(String unDealWO) {
		this.unDealWO = unDealWO;
	}

	public String getBOMCWO() {
		return BOMCWO;
	}

	public void setBOMCWO(String bOMCWO) {
		BOMCWO = bOMCWO;
	}

	public String getUnDealBOMCWO() {
		return unDealBOMCWO;
	}

	public void setUnDealBOMCWO(String unDealBOMCWO) {
		this.unDealBOMCWO = unDealBOMCWO;
	}

	public String getCloudWO() {
		return cloudWO;
	}

	public void setCloudWO(String cloudWO) {
		this.cloudWO = cloudWO;
	}

	public String getUnDealCloudWO() {
		return unDealCloudWO;
	}

	public void setUnDealCloudWO(String unDealCloudWO) {
		this.unDealCloudWO = unDealCloudWO;
	}
}
