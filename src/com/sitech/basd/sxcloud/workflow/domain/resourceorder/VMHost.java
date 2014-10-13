package com.sitech.basd.sxcloud.workflow.domain.resourceorder;

/**
 * 
*    
* 项目名称：CloudRESET   
* 类名称：VMHost   
* 类描述：   虚拟机信息
* 创建人：shijc   
* 创建时间：2012-7-12 下午03:41:04   
* 修改人：shijc   
* 修改时间：2012-7-12 下午03:41:04   
* 修改备注：   
* @version    
*
 */

public class VMHost {
	
	private String workOrderID;//需求单编码	必填                             NEED_NUMBERS    1
	private String vmSubTableNo;//虚拟机服务子表编码	必填        
	private String serverNo;//服务编码	必填                                                                                    1
	private String serverName;//服务名称	可选
	private String applyType;//申请类型	必填                                         
	private String resourceTemNo;//资源模板编码	可选                    TEM_ID
	private String resourceTemName;//资源模板名称	可选         TEM_NAME
	private String vmName;//虚拟机名称	可选
	private String vmId;//虚拟机标识	必填
	private String description;//对虚拟机服务需求的补充说明    必填  CONTENT         1
	private String cpuFrequency;//CPU主频 	可选
	private String cpuCount;//CPU个数	可选                         VH_CPU: VH_MAX_CPU: VH_MIN_CPU:
	private String memSize;//内存大小	可选                         VH_MEM: VH_MIN_MEM: VH_MAX_MEM:
	private String storageSize;//存储大小	可选
	private String osType;//操作系统类型 	可选
	private String osVersion;//操作系统版本	可选
	private String mwType;//中间件类型	可选
	private String mwVersion;//中间件版本	可选
	private String dbType;//数据库类型	可选
	private String dbVersion;//数据库版本	可选
	private String ipAddress;//IP地址	可选
	private String macAddress;//macAddress	可选
	
	public String getWorkOrderID() {
		return workOrderID;
	}
	public void setWorkOrderID(String workOrderID) {
		this.workOrderID = workOrderID;
	}
	public String getVmSubTableNo() {
		return vmSubTableNo;
	}
	public void setVmSubTableNo(String vmSubTableNo) {
		this.vmSubTableNo = vmSubTableNo;
	}
	public String getServerNo() {
		return serverNo;
	}
	public void setServerNo(String serverNo) {
		this.serverNo = serverNo;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getResourceTemNo() {
		return resourceTemNo;
	}
	public void setResourceTemNo(String resourceTemNo) {
		this.resourceTemNo = resourceTemNo;
	}
	public String getResourceTemName() {
		return resourceTemName;
	}
	public void setResourceTemName(String resourceTemName) {
		this.resourceTemName = resourceTemName;
	}
	public String getVmName() {
		return vmName;
	}
	public void setVmName(String vmName) {
		this.vmName = vmName;
	}
	public String getVmId() {
		return vmId;
	}
	public void setVmId(String vmId) {
		this.vmId = vmId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCpuFrequency() {
		return cpuFrequency;
	}
	public void setCpuFrequency(String cpuFrequency) {
		this.cpuFrequency = cpuFrequency;
	}
	public String getCpuCount() {
		return cpuCount;
	}
	public void setCpuCount(String cpuCount) {
		this.cpuCount = cpuCount;
	}
	public String getMemSize() {
		return memSize;
	}
	public void setMemSize(String memSize) {
		this.memSize = memSize;
	}
	public String getStorageSize() {
		return storageSize;
	}
	public void setStorageSize(String storageSize) {
		this.storageSize = storageSize;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getMwType() {
		return mwType;
	}
	public void setMwType(String mwType) {
		this.mwType = mwType;
	}
	public String getMwVersion() {
		return mwVersion;
	}
	public void setMwVersion(String mwVersion) {
		this.mwVersion = mwVersion;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getDbVersion() {
		return dbVersion;
	}
	public void setDbVersion(String dbVersion) {
		this.dbVersion = dbVersion;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
