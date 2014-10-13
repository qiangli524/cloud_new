package com.sitech.basd.sxcloud.cloud.domain.monitor;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;
/**
 * <p>Title:MonitorAlarmLocationObj
 * </p>
 * <p>Description:告警监控  定位实体
 * </p>
 * <p>Company: SI-TECH
 * </p>
 * @author liwq_bj
 * @version 1.0
 * @createtime 2014-8-9 下午05:07:16
 */
public class MonitorAlarmLocationObj extends BaseObj {
	
	private String oneLevelName;	//一级名称
	private String twoLevelName;	//二级名称
	private String threeLevelName;	//三级名称
	private String fourLevelName;	//四级名称
	private String fiveLevelName;	//五级名称
	private String domain;			//域
	private String connectId;		//连接id
	private String uuid;			//实体id
	private String vmname;			//虚拟机名称
	private String bsname;			//业务系统名称
	private String bcname;			//业务中心名称
	private String type;			//类型（vm,host）
	private String ip;				//IP
	
	
	/*	getter && setter */
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getConnectId() {
		return connectId;
	}
	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getOneLevelName() {
		return oneLevelName;
	}
	public void setOneLevelName(String oneLevelName) {
		this.oneLevelName = oneLevelName;
	}
	public String getTwoLevelName() {
		return twoLevelName;
	}
	public void setTwoLevelName(String twoLevelName) {
		this.twoLevelName = twoLevelName;
	}
	public String getThreeLevelName() {
		return threeLevelName;
	}
	public void setThreeLevelName(String threeLevelName) {
		this.threeLevelName = threeLevelName;
	}
	public String getFourLevelName() {
		return fourLevelName;
	}
	public void setFourLevelName(String fourLevelName) {
		this.fourLevelName = fourLevelName;
	}
	public String getFiveLevelName() {
		return fiveLevelName;
	}
	public void setFiveLevelName(String fiveLevelName) {
		this.fiveLevelName = fiveLevelName;
	}
	public String getVmname() {
		return vmname;
	}
	public void setVmname(String vmname) {
		this.vmname = vmname;
	}
	public String getBsname() {
		return bsname;
	}
	public void setBsname(String bsname) {
		this.bsname = bsname;
	}
	public String getBcname() {
		return bcname;
	}
	public void setBcname(String bcname) {
		this.bcname = bcname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
