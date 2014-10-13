package com.sitech.ssd.ah.zookeeper.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>
 * Title: ZookeeperTreeObj
 * </p>
 * <p>
 * Description: zookeeper树节点属性
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-7-18 上午8:33:26
 * 
 */
public class ZookeeperTreeObj extends BaseObj {
	private String name;// 节点名称
	private String dataValue;// 节点数据属性
	private String fullPath;// 节点所在完整路径
	private Boolean isParent = true; // 是否是父节点
	private String hiddName;
	private String icon;// 图标
	private String flag;// 用于tab页显示标识
	private String flagT;// 用于页显示
	private String aliasName;// 节点别名

	// 静态树叶子节点属性
	private String ddName;// 动态节点名称
	private String staSto;// 启停标识
	private String processID;// 进程唯一标识
	private String dirMessage;// 目录信息
	private String hickyMessage;// 接口表信息
	// 动态树叶子节点属性
	private String cpuUse;// CPU使用率
	private String menUse;// 内存使用率
	private String ip;// IP地址
	private String port;// 端口号
	private String pid;// PID
	private String processName;// 进程名称

	// 集群属性
	private String ipAndYm;// IP和掩码
	private String areaName;// 域名

	// 池属性
	private String mlbID;// MLB默认返回标识
	private String serverPortArr;// 服务端口号列表

	public String getIpAndYm() {
		return ipAndYm;
	}

	public void setIpAndYm(String ipAndYm) {
		this.ipAndYm = ipAndYm;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getMlbID() {
		return mlbID;
	}

	public void setMlbID(String mlbID) {
		this.mlbID = mlbID;
	}

	public String getServerPortArr() {
		return serverPortArr;
	}

	public void setServerPortArr(String serverPortArr) {
		this.serverPortArr = serverPortArr;
	}

	public String getHickyMessage() {
		return hickyMessage;
	}

	public void setHickyMessage(String hickyMessage) {
		this.hickyMessage = hickyMessage;
	}

	public String getDdName() {
		return ddName;
	}

	public void setDdName(String ddName) {
		this.ddName = ddName;
	}

	public String getStaSto() {
		return staSto;
	}

	public void setStaSto(String staSto) {
		this.staSto = staSto;
	}

	public String getProcessID() {
		return processID;
	}

	public void setProcessID(String processID) {
		this.processID = processID;
	}

	public String getDirMessage() {
		return dirMessage;
	}

	public void setDirMessage(String dirMessage) {
		this.dirMessage = dirMessage;
	}

	public String getCpuUse() {
		return cpuUse;
	}

	public void setCpuUse(String cpuUse) {
		this.cpuUse = cpuUse;
	}

	public String getMenUse() {
		return menUse;
	}

	public void setMenUse(String menUse) {
		this.menUse = menUse;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getFlagT() {
		return flagT;
	}

	public void setFlagT(String flagT) {
		this.flagT = flagT;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHiddName() {
		return hiddName;
	}

	public void setHiddName(String hiddName) {
		this.hiddName = hiddName;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}
}
