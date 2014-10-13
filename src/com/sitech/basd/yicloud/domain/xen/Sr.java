package com.sitech.basd.yicloud.domain.xen;

import java.util.List;


public class Sr extends Vm {
	private String srUuid;
	private String srName;
	private long srPhysicalSize; //存储大小
	private long srPhysicalUtilisation;
	private long srVirtualAllocation; //已用的大小
	private String srDesc;
	private String srType;
	private boolean srShared;
	private String hostName;
	private List<String> hostUuid;
	private String srState;//connection detach destroy
	private String srUrl;
	
	public String getSrUrl() {
		return srUrl;
	}
	public void setSrUrl(String srUrl) {
		this.srUrl = srUrl;
	}
	public String getSrState() {
		return srState;
	}
	public void setSrState(String srState) {
		this.srState = srState;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public List<String> getHostUuid() {
		return hostUuid;
	}
	public void setHostUuid(List<String> hostUuid) {
		this.hostUuid = hostUuid;
	}
	public String getSrUuid() {
		return srUuid;
	}
	public void setSrUuid(String srUuid) {
		this.srUuid = srUuid;
	}
	public String getSrName() {
		return srName;
	}
	public void setSrName(String srName) {
		this.srName = srName;
	}
	public long getSrPhysicalSize() {
		return srPhysicalSize;
	}
	public void setSrPhysicalSize(long srPhysicalSize) {
		this.srPhysicalSize = srPhysicalSize;
	}
	public long getSrPhysicalUtilisation() {
		return srPhysicalUtilisation;
	}
	public void setSrPhysicalUtilisation(long srPhysicalUtilisation) {
		this.srPhysicalUtilisation = srPhysicalUtilisation;
	}

	public long getSrVirtualAllocation() {
		return srVirtualAllocation;
	}
	public void setSrVirtualAllocation(long srVirtualAllocation) {
		this.srVirtualAllocation = srVirtualAllocation;
	}
	public String getSrDesc() {
		return srDesc;
	}
	public void setSrDesc(String srDesc) {
		this.srDesc = srDesc;
	}
	public String getSrType() {
		return srType;
	}
	public void setSrType(String srType) {
		this.srType = srType;
	}
	public boolean isSrShared() {
		return srShared;
	}
	public void setSrShared(boolean srShared) {
		this.srShared = srShared;
	}

}
