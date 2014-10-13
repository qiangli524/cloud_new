package com.sitech.basd.yicloud.domain.xen;

import java.util.Comparator;


public class Vif extends Network{
	
	private String vifUuid;
	private String vmIp;
	private String device;
	private boolean currentlyAttached;
	
	public boolean isCurrentlyAttached() {
		return currentlyAttached;
	}
	public void setCurrentlyAttached(boolean currentlyAttached) {
		this.currentlyAttached = currentlyAttached;
	}
	public String getVifUuid() {
		return vifUuid;
	}
	public void setVifUuid(String vifUuid) {
		this.vifUuid = vifUuid;
	}
	public String getVmIp() {
		return vmIp;
	}
	public void setVmIp(String vmIp) {
		this.vmIp = vmIp;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}

}
