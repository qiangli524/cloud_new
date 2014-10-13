package com.sitech.basd.sxcloud.rsmu.lbsms;

public class VirtualServiceBean {

	/**  */
	String id;
	/** str类型， 唯一指定虚拟服务名字，必填项 */
	String name;
	/** str类型， 唯一指定虚拟服务IP地址+端口，如192.168.10.10:10000，必填项 */
	String virtualAddress;
	/** str类型， 子网掩码，默认为255.255.255.255，必填项 */
	String netmask;
	/** str类型， 四层协议类型，可取值为tcp或udp，默认为tcp，必填项 */
	String protocol;
	/** str类型， 可取值为yes或no，默认为yes，标识客户端请求是否需要会话保持，必填项 */
	String persistentType;
	/**
	 * int类型， 客户端请求会话保持时间（单位为秒），默认为600; 如果上面的persistent_type为yes，则此项为必填项
	 */
	String persistent;
	/**
	 * str类型， 负载均衡算法，包含如下取值及含义，默认为wrr，必填项<br>
	 * dh，按目的地址分发 <br>
	 * lblc，基于局部性的最少链接 <br>
	 * lblcr，带复制的基于局部性最少链接<br>
	 * lc，最小连接调度 <br>
	 * rr，轮叫调度 <br>
	 * sh，按源地址分发 <br>
	 * wlc，加权最小连接调度<br>
	 * wrr，加权轮叫调度
	 */
	String scheduler;
	/** str类型， 回退服务器地址+端口，默认为127.0.0.1:80 */
	String fallback;
	/**
	 * str类型， 健康检查服务方式，包含以下取值及含义，默认为connect<br>
	 * connect，连接 <br>
	 * negotiate，内容协商 <br>
	 * ping，ping <br>
	 * on，开启 <br>
	 * off，关闭
	 */
	String checktype;
	/** 连接次数 */
	String checkcyc;
	/**
	 * str类型， 健康检查服务类型，可取值为ftp│smtp│http│pop│pops│nntp│imap│imaps│ldap
	 * │https│dns│mysql│pgsql│sip│none
	 */
	String service;
	/** str类型， http或https服务检查参数 */
	String virtualHost;
	/** int类型， 健康检查服务端口 */
	String checkport;
	/** str类型， ftp、ldap等健康服务检查时的登录用户名 */
	String login;
	/** str类型， ftp、ldap等健康服务检查时的登录口令 */
	String password;
	/** str类型， 用于MySQL或PostgreSQL服务健康检查，指定查询的数据库地址 */
	String checkdb;
	/** str类型， 用于http服务健康检查，可取值GET或HEAD */
	String httpMethod;
	/** str类型， 健康检查请求内容，如index.html；如果上面的checktype为negotiate，则此项为必填项 */
	String request;
	/** str类型， 健康检查请求结果，如OK；如果上面的checktype为negotiate，则此项为必填项 */
	String receive;
	/** email告警频率 */
	String emailalert;
	/** email告警地址 */
	String emailalertfreq;

	/**  */
	String forwardingMethod;
	/**  */
	String feedbackMethod;

	String loginNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVirtualAddress() {
		return virtualAddress;
	}

	public void setVirtualAddress(String virtualAddress) {
		this.virtualAddress = virtualAddress;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getPersistentType() {
		return persistentType;
	}

	public void setPersistentType(String persistentType) {
		this.persistentType = persistentType;
	}

	public String getPersistent() {
		return persistent;
	}

	public void setPersistent(String persistent) {
		this.persistent = persistent;
	}

	public String getScheduler() {
		return scheduler;
	}

	public void setScheduler(String scheduler) {
		this.scheduler = scheduler;
	}

	public String getFallback() {
		return fallback;
	}

	public void setFallback(String fallback) {
		this.fallback = fallback;
	}

	public String getChecktype() {
		return checktype;
	}

	public void setChecktype(String checktype) {
		this.checktype = checktype;
	}

	public String getCheckcyc() {
		return checkcyc;
	}

	public void setCheckcyc(String checkcyc) {
		this.checkcyc = checkcyc;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getVirtualHost() {
		return virtualHost;
	}

	public void setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
	}

	public String getCheckport() {
		return checkport;
	}

	public void setCheckport(String checkport) {
		this.checkport = checkport;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckdb() {
		return checkdb;
	}

	public void setCheckdb(String checkdb) {
		this.checkdb = checkdb;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
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

	public String getEmailalert() {
		return emailalert;
	}

	public void setEmailalert(String emailalert) {
		this.emailalert = emailalert;
	}

	public String getEmailalertfreq() {
		return emailalertfreq;
	}

	public void setEmailalertfreq(String emailalertfreq) {
		this.emailalertfreq = emailalertfreq;
	}

	public String getLoginNo() {
		return loginNo;
	}

	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}

	public String getForwardingMethod() {
		return forwardingMethod;
	}

	public void setForwardingMethod(String forwardingMethod) {
		this.forwardingMethod = forwardingMethod;
	}

	public String getFeedbackMethod() {
		return feedbackMethod;
	}

	public void setFeedbackMethod(String feedbackMethod) {
		this.feedbackMethod = feedbackMethod;
	}

}
