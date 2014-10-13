package com.sitech.taokeeper.model;


/**
 * Description:	Model: TaoKeeper_Stat_DAO
 * @author   yinshi.nc
 */
public class TaoKeeperStat {

	private Integer clusterId;
	private String server;
	private String statDateTime;
	private String statDate;
	private Integer connections;
	private Integer watches;
	private Long sendTimes;
	private Long receiveTimes;
	private Long nodeCount;
	private String rwps;
	
	
	
	public TaoKeeperStat(){}
	public TaoKeeperStat(int clusterId, String server, String statDateTime, String statDate, Integer connections, Integer watches, Long sendTimes, Long receiveTimes, Long nodeCount, String rwps ){
		this.clusterId    = clusterId;
		this.server       = server;
		this.statDateTime = statDateTime;
		this.statDate     = statDate;
		this.connections  = connections;
		this.watches      = watches;
		this.sendTimes    = sendTimes;
		this.receiveTimes = receiveTimes;
		this.nodeCount    = nodeCount;
		this.rwps         = rwps;
	}
	

	public int getClusterId() {
		return clusterId;
	}
	public void setClusterId( int clusterId ) {
		this.clusterId = clusterId;
	}
	public String getServer() {
		return server;
	}
	public void setServer( String server ) {
		this.server = server;
	}
	public String getStatDateTime() {
		return statDateTime;
	}
	public void setStatDateTime( String statDateTime ) {
		this.statDateTime = statDateTime;
	}
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate( String statDate ) {
		this.statDate = statDate;
	}
	public Integer getWatches() {
		return watches;
	}
	public void setWatches( Integer watches ) {
		this.watches = watches;
	}
	public Long getSendTimes() {
		return sendTimes;
	}
	public void setSendTimes( Integer sendTimes ) {
		this.sendTimes = sendTimes.longValue();
	}
	public Long getReceiveTimes() {
		return receiveTimes;
	}
	public void setReceiveTimes( Integer receiveTimes ) {
		this.receiveTimes = receiveTimes.longValue();
	}
	public Long getNodeCount() {
		return nodeCount;
	}
	public void setNodeCount( Integer nodeCount ) {
		this.nodeCount = nodeCount.longValue();
	}
	public Integer getConnections() {
		return connections;
	}
	public void setConnections( Integer connections ) {
		this.connections = connections;
	}
	public String getRwps() {
		return rwps;
	}
	public void setRwps( String rwps ) {
		this.rwps = rwps;
	}
	
}
