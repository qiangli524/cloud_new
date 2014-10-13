package com.sitech.taokeeper.model;

import java.util.Date;

/**
 * <p>Title: TaokeeperNodesInfo</p>
 * <p>Description: zookeeper节点信息</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author liudan
 * @version 1.0
 * @createtime 2014-9-5 下午5:15:36
 *
 */
public class TaokeeperNodesInfo {
	
	/** 物理主键 */
	private String uid;
	/** zookeeper集群id */
	private Integer clusterId;
	/** 集群节点id */
	private String server;
	/** 集群节点角色  */
	private String role;
	/** 连接数量 */
	private Integer connCount;
	/** 监视着数量 */
	private Integer watchCount;
	/** 被监视路径数量 */
	private Integer watchedPathCount;
	/** 路径总数量 */
	private Long totalPathCount;
	/** 发送数据量 */
	private String sentData;
	/** 接收数据量 */
	private String receivedData;
	/** 检查状态 */
	private String checkStatus;
	
	private Date lastUpdateDate;
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getClusterId() {
		return clusterId;
	}
	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getConnCount() {
		return connCount;
	}
	public void setConnCount(Integer connCount) {
		this.connCount = connCount;
	}
	public Integer getWatchCount() {
		return watchCount;
	}
	public void setWatchCount(Integer watchCount) {
		this.watchCount = watchCount;
	}
	public Integer getWatchedPathCount() {
		return watchedPathCount;
	}
	public void setWatchedPathCount(Integer watchedPathCount) {
		this.watchedPathCount = watchedPathCount;
	}
	public Long getTotalPathCount() {
		return totalPathCount;
	}
	public void setTotalPathCount(Long totalPathCount) {
		this.totalPathCount = totalPathCount;
	}
	public String getSentData() {
		return sentData;
	}
	public void setSentData(String sentData) {
		this.sentData = sentData;
	}
	public String getReceivedData() {
		return receivedData;
	}
	public void setReceivedData(String receivedData) {
		this.receivedData = receivedData;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	
	
}
