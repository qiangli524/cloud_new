/**
 * Copyright (c) 2013 SI-TECH Software, Inc.
 * All right reserved. 
 * 
 * 云管理平台
 */
package com.sitech.ssd.hlj.report.domain;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 *<P>
 * 资产报表
 *</p>
 * Aug 4, 2014,9:31:39 AM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
public class AssetReportObj extends BaseObj implements Serializable{
	private static final long serialVersionUID = 5982540471766229469L;
	//查询条件
	private String resourceType;
	private String dataCenterId;
	private String clusterId;
	private String businessId;
	private String subBusinessId;
	
	//主机
	private String roomId; 
	private String eqIp;
	private String eqName;
	private String eqType;
	private String allocated;
	private String hasvertual;
	//虚拟机
	private String vhName;
	private String vhIp;
	private String vhType;
	
	//ip
	private String subnetId;
	private String netId;
	private String isused;
	private String ipAddress;
	private String vlanId;
	
	//分域
	private String domain;
	//导出excel使用，json对象，json对象中存放两个array：heads、keys
	private String exportjson;
	
	
	
	/**
	 *
	 * @return the exportjson
	 */
	public String getExportjson() {
		return exportjson;
	}
	/**
	 *
	 * @param exportjson the exportjson to set
	 */
	public void setExportjson(String exportjson) {
		this.exportjson = exportjson;
	}
	/**
	 *
	 * @return the resourceType
	 */
	public String getResourceType() {
		return resourceType;
	}
	/**
	 *
	 * @param resourceType the resourceType to set
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	/**
	 *
	 * @return the dataCenterId
	 */
	public String getDataCenterId() {
		return dataCenterId;
	}
	/**
	 *
	 * @param dataCenterId the dataCenterId to set
	 */
	public void setDataCenterId(String dataCenterId) {
		this.dataCenterId = dataCenterId;
	}
	/**
	 *
	 * @return the clusterId
	 */
	public String getClusterId() {
		return clusterId;
	}
	/**
	 *
	 * @param clusterId the clusterId to set
	 */
	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}
	/**
	 *
	 * @return the businessId
	 */
	public String getBusinessId() {
		return businessId;
	}
	/**
	 *
	 * @param businessId the businessId to set
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	/**
	 *
	 * @return the subBusinessId
	 */
	public String getSubBusinessId() {
		return subBusinessId;
	}
	/**
	 *
	 * @param subBusinessId the subBusinessId to set
	 */
	public void setSubBusinessId(String subBusinessId) {
		this.subBusinessId = subBusinessId;
	}
	/**
	 *
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 *
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	/**
	 *
	 * @return the eqIp
	 */
	public String getEqIp() {
		return eqIp;
	}
	/**
	 *
	 * @param eqIp the eqIp to set
	 */
	public void setEqIp(String eqIp) {
		this.eqIp = eqIp;
	}
	/**
	 *
	 * @return the eqName
	 */
	public String getEqName() {
		return eqName;
	}
	/**
	 *
	 * @param eqName the eqName to set
	 */
	public void setEqName(String eqName) {
		this.eqName = eqName;
	}
	/**
	 *
	 * @return the eqType
	 */
	public String getEqType() {
		return eqType;
	}
	/**
	 *
	 * @param eqType the eqType to set
	 */
	public void setEqType(String eqType) {
		this.eqType = eqType;
	}
	/**
	 *
	 * @return the allocated
	 */
	public String getAllocated() {
		return allocated;
	}
	/**
	 *
	 * @param allocated the allocated to set
	 */
	public void setAllocated(String allocated) {
		this.allocated = allocated;
	}
	/**
	 *
	 * @return the vhName
	 */
	public String getVhName() {
		return vhName;
	}
	/**
	 *
	 * @param vhName the vhName to set
	 */
	public void setVhName(String vhName) {
		this.vhName = vhName;
	}
	/**
	 *
	 * @return the vhIp
	 */
	public String getVhIp() {
		return vhIp;
	}
	/**
	 *
	 * @param vhIp the vhIp to set
	 */
	public void setVhIp(String vhIp) {
		this.vhIp = vhIp;
	}
	/**
	 *
	 * @return the vhType
	 */
	public String getVhType() {
		return vhType;
	}
	/**
	 *
	 * @param vhType the vhType to set
	 */
	public void setVhType(String vhType) {
		this.vhType = vhType;
	}
	/**
	 *
	 * @return the subnetId
	 */
	public String getSubnetId() {
		return subnetId;
	}
	/**
	 *
	 * @param subnetId the subnetId to set
	 */
	public void setSubnetId(String subnetId) {
		this.subnetId = subnetId;
	}
	/**
	 *
	 * @return the netId
	 */
	public String getNetId() {
		return netId;
	}
	/**
	 *
	 * @param netId the netId to set
	 */
	public void setNetId(String netId) {
		this.netId = netId;
	}
	/**
	 *
	 * @return the isused
	 */
	public String getIsused() {
		return isused;
	}
	/**
	 *
	 * @param isused the isused to set
	 */
	public void setIsused(String isused) {
		this.isused = isused;
	}
	/**
	 *
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 *
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 *
	 * @return the vlanId
	 */
	public String getVlanId() {
		return vlanId;
	}
	/**
	 *
	 * @param vlanId the vlanId to set
	 */
	public void setVlanId(String vlanId) {
		this.vlanId = vlanId;
	}
	/**
	 *
	 * @return the roomId
	 */
	public String getRoomId() {
		return roomId;
	}
	/**
	 *
	 * @param roomId the roomId to set
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	/**
	 *
	 * @return the hasvertual
	 */
	public String getHasvertual() {
		return hasvertual;
	}
	/**
	 *
	 * @param hasvertual the hasvertual to set
	 */
	public void setHasvertual(String hasvertual) {
		this.hasvertual = hasvertual;
	}
	
	
}
