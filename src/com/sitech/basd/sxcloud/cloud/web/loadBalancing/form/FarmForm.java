/**
 * Farm.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sitech.basd.sxcloud.cloud.web.loadBalancing.form;

import java.util.List;

/**
 * This structure describes the parameters of a Farm
 */
@SuppressWarnings("serial")
public class FarmForm {
	private String id;
	/* The name assigned by the user to this WEB farm. */
	private java.lang.String farmName;

	/* Enable/Disable server dispatcher. */
	private String adminStatus;

	/*
	 * Farm status. Active - farm is active. NotInService - service is not
	 * available.
	 */
	private String operationalStatus;

	/*
	 * Maximal period of time in seconds a non-active client is kept in the
	 * clients table.
	 */
	private String agingTime;

	/* Type of dispatching required. */
	private String dispatchMethod;

	/* Connectivity check method. */
	private String connectivityCheckMethod;

	/* Application port for TCP or UDP connectivity check. */
	private Integer connectivityCheckPort;

	/* Polling interval in seconds for servers polling. */
	private Integer connectivityCheckInterval;

	/*
	 * This variable indicate the Minimal number of unsuccessful polling
	 * attempts before considering a server disconnected.
	 */
	private Integer connectivityCheckRetries;

	/*
	 * This variable indicate the Number of connection requests from clients
	 * that were denied by the the server dispatcher.
	 */
	private Integer connectionDenials;

	/*
	 * The frequency at which an extended http check will be sent if
	 * rsWSDCheckConnectivityMethod is equal to 2. After this number of normal
	 * http checks a extended http check will be performed.
	 */
	private Integer extendedCheckFrequency;

	/*
	 * The absolute path of the web page to be requested in the extended
	 * connectivity check. Example: ~smith/home.html
	 */
	private String homePage;

	/*
	 * Client mode for this farm. How a single client's sessions are handled in
	 * the client table. Regular: all sessions to the same server,
	 * differentiated by destination port. EntryPerSession: all sessions to the
	 * same server, differentiated by both ports. ServerPerSession: new server
	 * selected for every session. RemoveEntry: extends EntryPerSession by
	 * removing terminated TCP sessions. RemoveServerPerSession: extends
	 * ServerPerSession to remove terminated TCP sessions.
	 */
	private String sessionsMode;

	/* Band width for farm. */
	private Integer bandwidthLimit;

	/* Username used for extended connectivity checks. */
	private String authorizedUsername;

	/* Password used for extended connectivity checks. */
	private String authorizedPassword;

	/* Content type of the farm. */
	private String content;
	private List resultList = null;

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public String getAgingTime() {
		return agingTime;
	}

	public void setAgingTime(String agingTime) {
		this.agingTime = agingTime;
	}

	public String getAuthorizedPassword() {
		return authorizedPassword;
	}

	public void setAuthorizedPassword(String authorizedPassword) {
		this.authorizedPassword = authorizedPassword;
	}

	public String getAuthorizedUsername() {
		return authorizedUsername;
	}

	public void setAuthorizedUsername(String authorizedUsername) {
		this.authorizedUsername = authorizedUsername;
	}

	public Integer getBandwidthLimit() {
		return bandwidthLimit;
	}

	public void setBandwidthLimit(Integer bandwidthLimit) {
		this.bandwidthLimit = bandwidthLimit;
	}

	public Integer getConnectionDenials() {
		return connectionDenials;
	}

	public void setConnectionDenials(Integer connectionDenials) {
		this.connectionDenials = connectionDenials;
	}

	public Integer getConnectivityCheckInterval() {
		return connectivityCheckInterval;
	}

	public void setConnectivityCheckInterval(Integer connectivityCheckInterval) {
		this.connectivityCheckInterval = connectivityCheckInterval;
	}

	public String getConnectivityCheckMethod() {
		return connectivityCheckMethod;
	}

	public void setConnectivityCheckMethod(String connectivityCheckMethod) {
		this.connectivityCheckMethod = connectivityCheckMethod;
	}

	public Integer getConnectivityCheckPort() {
		return connectivityCheckPort;
	}

	public void setConnectivityCheckPort(Integer connectivityCheckPort) {
		this.connectivityCheckPort = connectivityCheckPort;
	}

	public Integer getConnectivityCheckRetries() {
		return connectivityCheckRetries;
	}

	public void setConnectivityCheckRetries(Integer connectivityCheckRetries) {
		this.connectivityCheckRetries = connectivityCheckRetries;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDispatchMethod() {
		return dispatchMethod;
	}

	public void setDispatchMethod(String dispatchMethod) {
		this.dispatchMethod = dispatchMethod;
	}

	public Integer getExtendedCheckFrequency() {
		return extendedCheckFrequency;
	}

	public void setExtendedCheckFrequency(Integer extendedCheckFrequency) {
		this.extendedCheckFrequency = extendedCheckFrequency;
	}

	public java.lang.String getFarmName() {
		return farmName;
	}

	public void setFarmName(java.lang.String farmName) {
		this.farmName = farmName;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperationalStatus() {
		return operationalStatus;
	}

	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}

	public String getSessionsMode() {
		return sessionsMode;
	}

	public void setSessionsMode(String sessionsMode) {
		this.sessionsMode = sessionsMode;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

}
