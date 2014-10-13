package com.sitech.ssd.ah.busiMonitor.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class MonitorCfgObj extends BaseObj {

	private String id; // 物理主键
	private String extEqId; // 采集主机唯一标识，物理主机：主机ip_序列号，虚拟机：vcenterid_虚拟机id
	private String extEqName;// 采集主机名称
	private String hostIp; // 采集主机IP
	private String kpiId; // '指标ID 01-目录积压 02-端口收发
	private String kpiName;
	private String kpiCfgValue; // 指标配置值
	private String userId; // 用户ID
	private String busiDesc; // 业务描述
	private String busiType; // 业务类型 01-BOSS
	private String status; // 监控配置状态：1：有效，2：无效
	private String hostType; // 主机类型：1：物理主机，2：虚拟主机
	private String inCount;// 最新文件数量
	private String alarm_flag;// 告警标志 0-无告警 1-般告警 2-严重告警
	private String START_TIME;
	private String END_TIME;

	public String getSTART_TIME() {
		return START_TIME;
	}

	public void setSTART_TIME(String sTART_TIME) {
		START_TIME = sTART_TIME;
	}

	public String getEND_TIME() {
		return END_TIME;
	}

	public void setEND_TIME(String eND_TIME) {
		END_TIME = eND_TIME;
	}

	public String getAlarm_flag() {
		return alarm_flag;
	}

	public void setAlarm_flag(String alarm_flag) {
		this.alarm_flag = alarm_flag;
	}

	public String getInCount() {
		return inCount;
	}

	public void setInCount(String inCount) {
		this.inCount = inCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExtEqId() {
		return extEqId;
	}

	public void setExtEqId(String extEqId) {
		this.extEqId = extEqId;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}

	public String getKpiCfgValue() {
		return kpiCfgValue;
	}

	public void setKpiCfgValue(String kpiCfgValue) {
		this.kpiCfgValue = kpiCfgValue;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBusiDesc() {
		return busiDesc;
	}

	public void setBusiDesc(String busiDesc) {
		this.busiDesc = busiDesc;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHostType() {
		return hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	public String getExtEqName() {
		return extEqName;
	}

	public void setExtEqName(String extEqName) {
		this.extEqName = extEqName;
	}

	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}

}
