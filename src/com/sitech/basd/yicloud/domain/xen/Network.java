package com.sitech.basd.yicloud.domain.xen;

public class Network extends Nic{
	private int networkCount;
	private String networkUuid;
	private String networkName;
	private String networkDesc;
	private boolean autoMatic;
	private long vlanNum;
	private String hostName;
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getNetworkUuid() {
		return networkUuid;
	}

	public void setNetworkUuid(String networkUuid) {
		this.networkUuid = networkUuid;
	}

	public long getVlanNum() {
		return vlanNum;
	}

	public void setVlanNum(long vlanNum) {
		this.vlanNum = vlanNum;
	}

	public boolean isAutoMatic() {
		return autoMatic;
	}

	public void setAutoMatic(boolean autoMatic) {
		this.autoMatic = autoMatic;
	}

	public String getNetworkDesc() {
		return networkDesc;
	}

	public void setNetworkDesc(String networkDesc) {
		this.networkDesc = networkDesc;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public int getNetworkCount() {
		return networkCount;
	}

	public void setNetworkCount(int networkCount) {
		this.networkCount = networkCount;
	}

	

}
