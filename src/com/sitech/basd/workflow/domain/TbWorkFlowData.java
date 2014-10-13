package com.sitech.basd.workflow.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_workflow表子表，用于存放申请流程相关信息
 * @author jiangdi
 *
 */
public class TbWorkFlowData implements Serializable{
	//主键
	private int id;
	//tb_workflow id
	private int workFlowId;
	//虚拟机名称
	private String vmName;
	//业务系统名称
	private String businessName;
	//业务系统id
	private String businessId;
	//子业务系统名称
	private String subBusinessName;
	//子业务系统id
	private String subBusinessId;
	//cpu数量
	private int cpuNums;
	//存储大小
	private int storeSize;
	//内存大小
	private int memorySize;
	//ip字符串
	private String ips;
	//vlan名称字符串
	private String vlanNames;
	//vlan id字符串
	private String vlanIds;
	//网络域名称字符串
	private String networkNames;
	//网络域id字符串
	private String networkIds;
	//模板类型名称
	private String modelType;
	//模板名称
	private String modelName; 
	//模板id
	private String modelId;
	//工程名称
	private String projectName;
	//模板网卡数量
	private int modelNicCount=1;
	//工程负责人姓名
	private String projectPeople;
	//过期时间
	private Date validateTime;
	
	public Date getValidateTime() {
		return validateTime;
	}
	public void setValidateTime(Date validateTime) {
		this.validateTime = validateTime;
	}
	public String getProjectPeople() {
		return projectPeople;
	}
	public void setProjectPeople(String projectPeople) {
		this.projectPeople = projectPeople;
	}
	public int getModelNicCount() {
		return modelNicCount;
	}
	public void setModelNicCount(int modelNicCount) {
		this.modelNicCount = modelNicCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(int workFlowId) {
		this.workFlowId = workFlowId;
	}
	public String getVmName() {
		return vmName;
	}
	public void setVmName(String vmName) {
		this.vmName = vmName;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getSubBusinessName() {
		return subBusinessName;
	}
	public void setSubBusinessName(String subBusinessName) {
		this.subBusinessName = subBusinessName;
	}
	public String getSubBusinessId() {
		return subBusinessId;
	}
	public void setSubBusinessId(String subBusinessId) {
		this.subBusinessId = subBusinessId;
	}
	public int getCpuNums() {
		return cpuNums;
	}
	public void setCpuNums(int cpuNums) {
		this.cpuNums = cpuNums;
	}
	public int getStoreSize() {
		return storeSize;
	}
	public void setStoreSize(int storeSize) {
		this.storeSize = storeSize;
	}
	public int getMemorySize() {
		return memorySize;
	}
	public void setMemorySize(int memorySize) {
		this.memorySize = memorySize;
	}
	public String getIps() {
		return ips;
	}
	public void setIps(String ips) {
		this.ips = ips;
	}
	public String getVlanNames() {
		return vlanNames;
	}
	public void setVlanNames(String vlanNames) {
		this.vlanNames = vlanNames;
	}
	public String getVlanIds() {
		return vlanIds;
	}
	public void setVlanIds(String vlanIds) {
		this.vlanIds = vlanIds;
	}
	public String getNetworkNames() {
		return networkNames;
	}
	public void setNetworkNames(String networkNames) {
		this.networkNames = networkNames;
	}
	public String getNetworkIds() {
		return networkIds;
	}
	public void setNetworkIds(String networkIds) {
		this.networkIds = networkIds;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	//工程id
	private String projectId;
	//备注
	private String remark;
	
}
