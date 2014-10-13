package com.sitech.ssd.ah.busiMonitor.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class MonitorReportObj extends BaseObj {
	
	private String id;//物理ID
	private String eqId;//采集实体标示
	private String extEqId;//采集主机唯一标示
	private String hostIp;//主机IP
	private String onlineFlag;//1-在线/0-离线
	private String clusterName;//集群名称
	private String poolName;//程序池名称
	private String progressNode;//进程节点
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String inCount;//业务量（入口）
	private String outCount;//业务量（出口）
	private String errorCount;//错误量
	private String busiKpi;//业务指标
	private String collTime;//采集时间
	private String dbTime;//入库时间
	private String busiNo;//业务编号，用于记录脚本返回的业务编号信息
	private String busiNoDesc;//业务编号描述
	private String noMasterCount;//无主量
	private String timeSeq;//数据时间戳
	
	/**
	 * 展示点数
	 */
	private Integer num;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEqId() {
		return eqId;
	}
	public void setEqId(String eqId) {
		this.eqId = eqId;
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
	public String getOnlineFlag() {
		return onlineFlag;
	}
	public void setOnlineFlag(String onlineFlag) {
		this.onlineFlag = onlineFlag;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getPoolName() {
		return poolName;
	}
	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}
	public String getProgressNode() {
		return progressNode;
	}
	public void setProgressNode(String progressNode) {
		this.progressNode = progressNode;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getInCount() {
		return inCount;
	}
	public void setInCount(String inCount) {
		this.inCount = inCount;
	}
	public String getOutCount() {
		return outCount;
	}
	public void setOutCount(String outCount) {
		this.outCount = outCount;
	}
	public String getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(String errorCount) {
		this.errorCount = errorCount;
	}
	public String getBusiKpi() {
		return busiKpi;
	}
	public void setBusiKpi(String busiKpi) {
		this.busiKpi = busiKpi;
	}
	public String getCollTime() {
		return collTime;
	}
	public void setCollTime(String collTime) {
		this.collTime = collTime;
	}
	public String getDbTime() {
		return dbTime;
	}
	public void setDbTime(String dbTime) {
		this.dbTime = dbTime;
	}
	public String getBusiNo() {
		return busiNo;
	}
	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}
	public String getBusiNoDesc() {
		return busiNoDesc;
	}
	public void setBusiNoDesc(String busiNoDesc) {
		this.busiNoDesc = busiNoDesc;
	}
	public String getNoMasterCount() {
		return noMasterCount;
	}
	public void setNoMasterCount(String noMasterCount) {
		this.noMasterCount = noMasterCount;
	}
	public String getTimeSeq() {
		return timeSeq;
	}
	public void setTimeSeq(String timeSeq) {
		this.timeSeq = timeSeq;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

}
