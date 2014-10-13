/**
 * L4Policy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sitech.basd.sxcloud.cloud.web.loadBalancing.form;

import java.util.List;

/**
 * This structure describes the parameters of a L4Policy
 */
@SuppressWarnings("serial")
public class L4PolicyForm {
	private Integer id;
	/* Virtual destination IP address. */
	private java.lang.String virtualIP;

	/* The L4 protocol type */
	private String l4Protocol;

	/* Destination port. Destination port 0 means any port */
	private String l4Port;

	/*
	 * Start address of the source IP range. The 0.0.0.0 address means that
	 * sourceIP ranges are not used for that entry
	 */
	private java.lang.String sourceIPFrom;

	/* Last address of the source IP range. */
	private java.lang.String sourceIPTo;

	/* The L4 policy name. */
	private java.lang.String l4PolicyName;

	/* The L7 policy name. */
	private java.lang.String l7PolicyName;

	/* The farm name. */
	private java.lang.String farmName;

	/*
	 * Treat as given port number. Tells how to treat the traffic that
	 * matchesthe L4 policy.
	 */
	private String application;

	/* Backend encryption port. */
	private String backendEncryptionPort;

	/* A number of bytes of request to read for classification. */
	private String bytesOfRequestToRead;

	/* The L4 policy on backup or master device. */
	private String redundancyStatus;

	/* The part of request to read for classification. */
	private String POSTClassificationInput;

	/* HTTP normalization status. */
	private String HTTPNormalization;

	/* L7 persistency method. */
	private String l7PersistentSwitchingMode;

	/*
	 * The L4 policy origin. The policy may be defined by user, or by
	 * applicationfor internal purpose.
	 */
	private java.lang.String policyDefinedBy;

	/* The segment name. */
	private java.lang.String segmentName;

	/*
	 * Explicit Farm Name defines the farm to be used for load and global
	 * considerations, when the L4 Policy is bound to a L7 Policy.This is
	 * required becausetables such as LRP Table and Hostname Tablerefer to L4
	 * Policy entries, and at the same time must be correlatedto a specific
	 * farm.
	 */
	private java.lang.String explicitFarmName;

	/*
	 * Type of action to take on classification success. Farm - farm name to
	 * select server from. L7 Policy - perform L7 classification to select farm.
	 * Route - specify a SIP route table. URI - send request to specified URI.
	 * IP - send request to IP. None - destination is determined by SIP headers.
	 */
	private String actionType;

	/*
	 * Action to take on classification success. Must match action type.
	 * Replaces both rsWSDL4PolicyL7PolicyName and rsWSDL4PolicyFarmName.
	 */
	private java.lang.String action;

	/* The Client SSL Policy name. */
	private java.lang.String SSLPolicy;

	/* The Compression Policy name. */
	private java.lang.String compressionPolicy;

	/* The Caching Policy name. */
	private java.lang.String cachingPolicy;

	/* The Client Authentication Policy name. */
	private java.lang.String clientAuthenticationPolicy;

	/* The HTTP Policy name. */
	private java.lang.String httpPolicy;

	private List farmList = null;
	private List resultList = null;

	public L4PolicyForm() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.lang.String getAction() {
		return action;
	}

	public void setAction(java.lang.String action) {
		this.action = action;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getBackendEncryptionPort() {
		return backendEncryptionPort;
	}

	public void setBackendEncryptionPort(String backendEncryptionPort) {
		this.backendEncryptionPort = backendEncryptionPort;
	}

	public String getBytesOfRequestToRead() {
		return bytesOfRequestToRead;
	}

	public void setBytesOfRequestToRead(String bytesOfRequestToRead) {
		this.bytesOfRequestToRead = bytesOfRequestToRead;
	}

	public java.lang.String getCachingPolicy() {
		return cachingPolicy;
	}

	public void setCachingPolicy(java.lang.String cachingPolicy) {
		this.cachingPolicy = cachingPolicy;
	}

	public java.lang.String getClientAuthenticationPolicy() {
		return clientAuthenticationPolicy;
	}

	public void setClientAuthenticationPolicy(
			java.lang.String clientAuthenticationPolicy) {
		this.clientAuthenticationPolicy = clientAuthenticationPolicy;
	}

	public java.lang.String getCompressionPolicy() {
		return compressionPolicy;
	}

	public void setCompressionPolicy(java.lang.String compressionPolicy) {
		this.compressionPolicy = compressionPolicy;
	}

	public java.lang.String getExplicitFarmName() {
		return explicitFarmName;
	}

	public void setExplicitFarmName(java.lang.String explicitFarmName) {
		this.explicitFarmName = explicitFarmName;
	}

	public java.lang.String getFarmName() {
		return farmName;
	}

	public void setFarmName(java.lang.String farmName) {
		this.farmName = farmName;
	}

	public String getHTTPNormalization() {
		return HTTPNormalization;
	}

	public void setHTTPNormalization(String normalization) {
		HTTPNormalization = normalization;
	}

	public java.lang.String getHttpPolicy() {
		return httpPolicy;
	}

	public void setHttpPolicy(java.lang.String httpPolicy) {
		this.httpPolicy = httpPolicy;
	}

	public java.lang.String getL4PolicyName() {
		return l4PolicyName;
	}

	public void setL4PolicyName(java.lang.String policyName) {
		l4PolicyName = policyName;
	}

	public String getL4Port() {
		return l4Port;
	}

	public void setL4Port(String port) {
		l4Port = port;
	}

	public String getL4Protocol() {
		return l4Protocol;
	}

	public void setL4Protocol(String protocol) {
		l4Protocol = protocol;
	}

	public String getL7PersistentSwitchingMode() {
		return l7PersistentSwitchingMode;
	}

	public void setL7PersistentSwitchingMode(String persistentSwitchingMode) {
		l7PersistentSwitchingMode = persistentSwitchingMode;
	}

	public java.lang.String getL7PolicyName() {
		return l7PolicyName;
	}

	public void setL7PolicyName(java.lang.String policyName) {
		l7PolicyName = policyName;
	}

	public java.lang.String getPolicyDefinedBy() {
		return policyDefinedBy;
	}

	public void setPolicyDefinedBy(java.lang.String policyDefinedBy) {
		this.policyDefinedBy = policyDefinedBy;
	}

	public String getPOSTClassificationInput() {
		return POSTClassificationInput;
	}

	public void setPOSTClassificationInput(String classificationInput) {
		POSTClassificationInput = classificationInput;
	}

	public String getRedundancyStatus() {
		return redundancyStatus;
	}

	public void setRedundancyStatus(String redundancyStatus) {
		this.redundancyStatus = redundancyStatus;
	}

	public java.lang.String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(java.lang.String segmentName) {
		this.segmentName = segmentName;
	}

	public java.lang.String getSourceIPFrom() {
		return sourceIPFrom;
	}

	public void setSourceIPFrom(java.lang.String sourceIPFrom) {
		this.sourceIPFrom = sourceIPFrom;
	}

	public java.lang.String getSourceIPTo() {
		return sourceIPTo;
	}

	public void setSourceIPTo(java.lang.String sourceIPTo) {
		this.sourceIPTo = sourceIPTo;
	}

	public java.lang.String getSSLPolicy() {
		return SSLPolicy;
	}

	public void setSSLPolicy(java.lang.String policy) {
		SSLPolicy = policy;
	}

	public java.lang.String getVirtualIP() {
		return virtualIP;
	}

	public void setVirtualIP(java.lang.String virtualIP) {
		this.virtualIP = virtualIP;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public List getFarmList() {
		return farmList;
	}

	public void setFarmList(List farmList) {
		this.farmList = farmList;
	}

}
