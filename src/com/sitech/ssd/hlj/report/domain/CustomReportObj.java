/**
 * Copyright (c) 2013 SI-TECH Software, Inc.
 * All right reserved. 
 * 
 * 云管理平台
 */
package com.sitech.ssd.hlj.report.domain;

import java.io.Serializable;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 *<P>
 * 类功能简述（例如：XXX功能）
 *</p>
 * Aug 4, 2014,9:31:39 AM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
public class CustomReportObj extends BaseObj implements Serializable{
	//查询条件
	private String resourceType;
	private String dataCenterId;
	private String clusterId;
	private String businessId;
	private String subBusinessId;
	private String vhName;
	private String kpiId;
	private String kpiName;
	private String kpiunit;
	private String dateType;
	private String startDate;
	private String endDate;
	private String resourceIds;
	private String domain;
	
	private String tableName;
	
	private List<String> resIdsList;
	
	//虚拟机和主机查询字段
	private String connectId;
	private String uuid ;
	private String name;
	private String ip;
	private String subBusi;
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
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 *
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 *
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 *
	 * @return the subBusi
	 */
	public String getSubBusi() {
		return subBusi;
	}
	/**
	 *
	 * @param subBusi the subBusi to set
	 */
	public void setSubBusi(String subBusi) {
		this.subBusi = subBusi;
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
	 * @return the kpiId
	 */
	public String getKpiId() {
		return kpiId;
	}
	/**
	 *
	 * @param kpiId the kpiId to set
	 */
	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}
	/**
	 *
	 * @return the dateType
	 */
	public String getDateType() {
		return dateType;
	}
	/**
	 *
	 * @param dateType the dateType to set
	 */
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	/**
	 *
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 *
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 *
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 *
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 *
	 * @return the resourceIds
	 */
	public String getResourceIds() {
		return resourceIds;
	}
	/**
	 *
	 * @param resourceIds the resourceIds to set
	 */
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	/**
	 *
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 *
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 *
	 * @return the kpiName
	 */
	public String getKpiName() {
		return kpiName;
	}
	/**
	 *
	 * @param kpiName the kpiName to set
	 */
	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}
	/**
	 *
	 * @return the resIdsList
	 */
	public List<String> getResIdsList() {
		return resIdsList;
	}
	/**
	 *
	 * @param resIdsList the resIdsList to set
	 */
	public void setResIdsList(List<String> resIdsList) {
		this.resIdsList = resIdsList;
	}
	/**
	 *
	 * @return the connectId
	 */
	public String getConnectId() {
		return connectId;
	}
	/**
	 *
	 * @param connectId the connectId to set
	 */
	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}
	/**
	 *
	 * @return the kpiunit
	 */
	public String getKpiunit() {
		return kpiunit;
	}
	/**
	 *
	 * @param kpiunit the kpiunit to set
	 */
	public void setKpiunit(String kpiunit) {
		this.kpiunit = kpiunit;
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
	
	
}
