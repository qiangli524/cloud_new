package com.sitech.ssd.sc.os.domain;

public class XlsHostModel {

	private String id;
	private String serial_num;
	private String mac1;
	private String mac2;
	private String mac3;
	private String mac4;
	public String getMac2() {
		return mac2;
	}
	public void setMac2(String mac2) {
		this.mac2 = mac2;
	}
	public String getMac3() {
		return mac3;
	}
	public void setMac3(String mac3) {
		this.mac3 = mac3;
	}
	public String getMac4() {
		return mac4;
	}
	public void setMac4(String mac4) {
		this.mac4 = mac4;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSerial_num() {
		return serial_num;
	}
	public void setSerial_num(String serialNum) {
		serial_num = serialNum;
	}

	public void setMac1(String mac1) {
		this.mac1 = mac1;
	}
	public String getMac1() {
		return mac1;
	}
	
}
