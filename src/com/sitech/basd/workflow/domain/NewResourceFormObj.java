package com.sitech.basd.workflow.domain;

/**
 * <p>Title: NewResourceFormObj</p>
 * <p>Description: 新增资源申请表单提交对象</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author jiangdi
 * @version 1.0
 * @createtime 2014-7-23 下午1:48:29
 *
 */
public class NewResourceFormObj {
	private int cpu;
	private int memory;
	private int store;
	private String netType;
	private String appType;
	private String serviceType;
	private String modelId;
	private String projectName;
	private String validateTime;
	private String remark;
	private String  fileIds;
	private String nicCount;
	private String busiId;
	private String storeStr;
	private String workOrderTitle;
	public String getStoreStr() {
		return storeStr;
	}
	public void setStoreStr(String storeStr) {
		this.storeStr = storeStr;
	}
	public String getBusiId() {
		return busiId;
	}
	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}
	public String getNicCount() {
		return nicCount;
	}
	public void setNicCount(String nicCount) {
		this.nicCount = nicCount;
	}
	private int count = 1;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCpu() {
		return cpu;
	}
	public void setCpu(int cpu) {
		this.cpu = cpu;
	}
	public int getMemory() {
		return memory;
	}
	public void setMemory(int memory) {
		this.memory = memory;
	}
	public int getStore() {
		return store;
	}
	public void setStore(int store) {
		this.store = store;
	}
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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
	public String getValidateTime() {
		return validateTime;
	}
	public void setValidateTime(String validateTime) {
		this.validateTime = validateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFileIds() {
		return fileIds;
	}
	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}
	public String getWorkOrderTitle() {
		return workOrderTitle;
	}
	public void setWorkOrderTitle(String workOrderTitle) {
		this.workOrderTitle = workOrderTitle;
	}
	
}
