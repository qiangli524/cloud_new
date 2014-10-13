package com.sitech.basd.sxcloud.rsmu.lbsms;

public class RealServerBean {

	/**  */
	String id;
	/** str类型，唯一指定相应的虚拟服务IP地址+端口，如192.168.10.10:10000，必填项 */
	String virtualAddress;
	/** str类型，唯一指定真实服务器名字，必填项 */
	String name;
	/** str类型，唯一指定真实服务器IP地址+端口，如192.168.1.1:10000，必填项 */
	String realAddress;
	/** int类型，标识该真实服务器权重，必填项 */
	String weight;
	/** str类型，该真实服务器健康检查请求内容，如index.html */
	String request;
	/** str类型，该真实服务器健康检查响应内容，如OK */
	String receive;

	/**  */
	String loginNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginNo() {
		return loginNo;
	}

	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}

	public String getVirtualAddress() {
		return virtualAddress;
	}

	public void setVirtualAddress(String virtualAddress) {
		this.virtualAddress = virtualAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealAddress() {
		return realAddress;
	}

	public void setRealAddress(String realAddress) {
		this.realAddress = realAddress;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

}
