/**  
* @Title: AdapterModel.java
* @Package com.sitech.ssd.sc.os.domain
* @Description: TODO(用一句话描述该文件做什么)
* @author chenjl
* @date 2014-7-15 下午4:28:57
* @version
*/
package com.sitech.ssd.sc.os.domain;

/**
 * @ClassName: AdapterModel
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjl
 * @date 2014-7-15 下午4:28:57
 *
 */
public class AdapterModel {
	
	private String os_host_id;
	private String nic_order;
	private String mac;
	
	public String getOs_host_id() {
		return os_host_id;
	}
	public void setOs_host_id(String os_host_id) {
		this.os_host_id = os_host_id;
	}
	public String getNic_order() {
		return nic_order;
	}
	public void setNic_order(String nic_order) {
		this.nic_order = nic_order;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}

}
