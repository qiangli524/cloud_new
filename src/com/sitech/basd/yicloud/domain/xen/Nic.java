package com.sitech.basd.yicloud.domain.xen;

public class Nic {
	private String nicUuid;
	private String nicName;
	private String mac;
	private boolean connState;
	private long speed;
	private boolean duplex;
	private String vendorName;
	private String deviceName;
	private String pciBusPath;
    private String ip;
    private String netmask;
    private String gateway;
    private String DNS;
    private boolean management;
    private String slaveVlan;
    
	public String getSlaveVlan() {
		return slaveVlan;
	}
	public void setSlaveVlan(String slaveVlan) {
		this.slaveVlan = slaveVlan;
	}
	public String getNicUuid() {
		return nicUuid;
	}
	public void setNicUuid(String nicUuid) {
		this.nicUuid = nicUuid;
	}
	public boolean isManagement() {
		return management;
	}
	public void setManagement(boolean management) {
		this.management = management;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getNetmask() {
		return netmask;
	}
	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getDNS() {
		return DNS;
	}
	public void setDNS(String dNS) {
		DNS = dNS;
	}
	public String getNicName() {
		return nicName;
	}
	public void setNicName(String nicName) {
		this.nicName = nicName;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public boolean isConnState() {
		return connState;
	}
	public void setConnState(boolean connState) {
		this.connState = connState;
	}
	public boolean isDuplex() {
		return duplex;
	}
	public void setDuplex(boolean duplex) {
		this.duplex = duplex;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getPciBusPath() {
		return pciBusPath;
	}
	public void setPciBusPath(String pciBusPath) {
		this.pciBusPath = pciBusPath;
	}
	public long getSpeed() {
		return speed;
	}
	public void setSpeed(long speed) {
		this.speed = speed;
	}

}
