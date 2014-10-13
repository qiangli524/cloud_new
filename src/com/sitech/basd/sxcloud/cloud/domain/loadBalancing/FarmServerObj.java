/**
 * FarmServer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sitech.basd.sxcloud.cloud.domain.loadBalancing;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * This structure describes the parameters of a FarmServer
 */
@SuppressWarnings("serial")
public class FarmServerObj extends BaseObj implements Serializable, Cloneable {
	private int id;
	/* Farm name to which this server belongs to. */
	private java.lang.String farmName;

	/* ApplicationIP address */
	private java.lang.String serverAddress;

	/*
	 * The port on which the server is handling the application. Each port
	 * creates a different logical server instance and automatic port
	 * multiplexing is performed. Servers with no port (value 0) don't have a
	 * specific designated port. No multiplexing is done to it and it may handle
	 * any traffic to any destination port
	 */
	private long serverPort;

	/* The name assigned by the user to the server. */
	private java.lang.String serverName;

	/*
	 * Administrative status of application on server. enable - activates the
	 * server. The rsADServerOperStatus will change to active. disable -stops
	 * the server.The rsADServerOperStatus will change to notInService. shutdown -
	 * gracefully shuts down the server.The rsADServerOperStatus will change to
	 * noNewSessions
	 */
	private String adminStatus;

	/*
	 * Operational status of application onserver. active- server is active
	 * notInService- server is or will become inactive. Existing sessions will
	 * be redirected to other servers. noNewSessions - server will receive no
	 * new sessions. Existing sessions are allowed to complete.
	 */
	private String operationalStatus;

	/*
	 * A numerical weight assigned by the user in order to impose some priority
	 * mechanism on the server dispatchers considerations of which server to
	 * choose for attaching a client.
	 */
	private Long weight;

	/*
	 * This variable indicate the status of the server, the AppDirector will not
	 * send any messages to a backup server unless all other server are
	 * downstatus. In case of more then one backup server, the AppDirector will
	 * decideto which backup server to send the messages acording to rsWSDWeight
	 */
	private String operationMode;

	/*
	 * This variable indicates the server's type. Regular type servers have no
	 * other connection to the network except this WSD. Distributed type servers
	 * are remote WSDs. A remote type server is a server cpnnected to the
	 * network at a different entry point.
	 */
	private String type;

	/*
	 * The maximal number of client sessions which can be opened on this sever.
	 */
	private Long connectionLimit;

	/* Band width for server. */
	private Long bandwidthLimit;

	/* String to be used by HTTP redirection. */
	private java.lang.String redirectTo;

	/*
	 * enable or disable the NAT client feature for this server Default value is
	 * disable
	 */
	private String clientNAT;

	/* The user describe the server. */
	private java.lang.String serverDescription;

	/*
	 * The amount of time (in milliseconds) that the server has to answer the
	 * client's request.
	 */
	private Long responseThreshold;

	/* Backup Server IP address */
	private java.lang.String backupServerAddress;

	/* Controls whether a regular server will preempt the backup server. */
	private String ADServerBackupPreemption;

	/*
	 * choose beginning of existing range interval from NAT IP range table
	 */
	private java.lang.String clientNATAddressRange;

	/* The name of real existing Farm */
	private java.lang.String farmNameForLocalFarm;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public String getADServerBackupPreemption() {
		return ADServerBackupPreemption;
	}

	public void setADServerBackupPreemption(String serverBackupPreemption) {
		ADServerBackupPreemption = serverBackupPreemption;
	}

	public java.lang.String getBackupServerAddress() {
		return backupServerAddress;
	}

	public void setBackupServerAddress(java.lang.String backupServerAddress) {
		this.backupServerAddress = backupServerAddress;
	}

	public Long getBandwidthLimit() {
		return bandwidthLimit;
	}

	public void setBandwidthLimit(Long bandwidthLimit) {
		this.bandwidthLimit = bandwidthLimit;
	}

	public String getClientNAT() {
		return clientNAT;
	}

	public void setClientNAT(String clientNAT) {
		this.clientNAT = clientNAT;
	}

	public java.lang.String getClientNATAddressRange() {
		return clientNATAddressRange;
	}

	public void setClientNATAddressRange(java.lang.String clientNATAddressRange) {
		this.clientNATAddressRange = clientNATAddressRange;
	}

	public Long getConnectionLimit() {
		return connectionLimit;
	}

	public void setConnectionLimit(Long connectionLimit) {
		this.connectionLimit = connectionLimit;
	}

	public java.lang.String getFarmName() {
		return farmName;
	}

	public void setFarmName(java.lang.String farmName) {
		this.farmName = farmName;
	}

	public java.lang.String getFarmNameForLocalFarm() {
		return farmNameForLocalFarm;
	}

	public void setFarmNameForLocalFarm(java.lang.String farmNameForLocalFarm) {
		this.farmNameForLocalFarm = farmNameForLocalFarm;
	}

	public String getOperationalStatus() {
		return operationalStatus;
	}

	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}

	public String getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(String operationMode) {
		this.operationMode = operationMode;
	}

	public java.lang.String getRedirectTo() {
		return redirectTo;
	}

	public void setRedirectTo(java.lang.String redirectTo) {
		this.redirectTo = redirectTo;
	}

	public Long getResponseThreshold() {
		return responseThreshold;
	}

	public void setResponseThreshold(Long responseThreshold) {
		this.responseThreshold = responseThreshold;
	}

	public java.lang.String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(java.lang.String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public java.lang.String getServerDescription() {
		return serverDescription;
	}

	public void setServerDescription(java.lang.String serverDescription) {
		this.serverDescription = serverDescription;
	}

	public java.lang.String getServerName() {
		return serverName;
	}

	public void setServerName(java.lang.String serverName) {
		this.serverName = serverName;
	}

	public long getServerPort() {
		return serverPort;
	}

	public void setServerPort(long serverPort) {
		this.serverPort = serverPort;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

}
