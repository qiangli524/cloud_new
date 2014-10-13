package com.sitech.basd.workflow.domain;

public class VMBean {
	//虚拟机名称
	public String name;
	//业务系统名称
	public String businessName;
	//业务系统ID
	public String businessId;
	//子业务系统名称
	public String subBusinessName;
	//子业务系统ID
	public String subBusinessId;
	//cpu数量
	public String cpuNums;
	//存储大小
	public String hardDiskSize;
	//ip设置
	public String ip;
	//网络域名称
	public String networkName;
	//网络域ID
	public String networkId;
	//vlan名称
	public String vlanName;
	//vlan ID
	public String vlanId;
	//模板类型
	public String modelType;
	//主机ID
	public String hostId;
	//主机名称
	public String hostName;
	//模板网卡数量
	public String modelNicCount;
	//项目负责人
	public String projectPeople;
	//集群ID
	public String clusterId;
	

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getProjectPeople() {
		return projectPeople;
	}

	public void setProjectPeople(String projectPeople) {
		this.projectPeople = projectPeople;
	}

	public String getModelNicCount() {
		return modelNicCount;
	}

	public void setModelNicCount(String modelNicCount) {
		this.modelNicCount = modelNicCount;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCpuNums() {
		return cpuNums;
	}

	public void setCpuNums(String cpuNums) {
		this.cpuNums = cpuNums;
	}

	public String getHardDiskSize() {
		return hardDiskSize;
	}

	public void setHardDiskSize(String hardDiskSize) {
		this.hardDiskSize = hardDiskSize;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getVlanName() {
		return vlanName;
	}

	public void setVlanName(String vlanName) {
		this.vlanName = vlanName;
	}

	public String getVlanId() {
		return vlanId;
	}

	public void setVlanId(String vlanId) {
		this.vlanId = vlanId;
	}

	public String getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(String memorySize) {
		this.memorySize = memorySize;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	//内存大小
	public String memorySize;
	//备注
	public String remark;
	//模板名称
	public String modelName;
	//模板ID
	public String modelId;
	//项目名称
	public String projectName;
	//项目id
	public String projectId;

	public void deleteMore(){
		name = name.substring(0,name.length()-1);
		businessName = businessName.substring(0,businessName.length()-1);
		businessId = businessId.substring(0,businessId.length()-1);
		subBusinessName = subBusinessName.substring(0,subBusinessName.length()-1);
		subBusinessId = subBusinessId.substring(0,subBusinessId.length()-1);
		networkName = networkName.substring(0,networkName.length()-1);
		networkId = networkId.substring(0,networkId.length()-1);
		vlanName = vlanName.substring(0,vlanName.length()-1);
		vlanId = vlanId.substring(0,vlanId.length()-1);
		modelName = modelName.substring(0,modelName.length()-1);
		modelId = modelId.substring(0,modelId.length()-1);
		projectName = projectName.substring(0,projectName.length()-1);
		cpuNums = cpuNums.substring(0,cpuNums.length()-1);
		hardDiskSize = hardDiskSize.substring(0,hardDiskSize.length()-1);
		ip = ip.substring(0,ip.length()-1);
		memorySize = memorySize.substring(0,memorySize.length()-1);
		remark = remark.substring(0,remark.length()-1);
		hostName = hostName.substring(0,hostName.length()-1);
		hostId = hostId.substring(0,hostId.length()-1);
		modelNicCount = modelNicCount.substring(0,modelNicCount.length()-1);
		projectPeople = projectPeople.substring(0,projectPeople.length()-1);
	}
}
