package com.sitech.basd.sxcloud.rsmu.web.cloudschedu.form;

import java.util.List;

public class VirtualServerForm {

	private int id; // *编号唯一标识
	private String name; // *服务器名称
	private String virtualAddress; // *ip地址和端口 如 127.0.0.1:8080
	private String scheduler; // *负载均衡算法类型
	private String persistentType; // *是否需要会话保持
	private String persistent; // 客户端请求会话保持时间
	private String netmask; // *子网掩码 默认为255.255.255.255
	private String protocol; // *IP协议类型，可取值为tcp或udp，默认为tcp，必填项
	private String fallback; // 服务回退地址，默认为127.0.0.1:80
	private String checktype; // 服务健康检查方式 connect，连接 | negotiate，内容协商 |
	// ping，ping | on，开启 | off，关闭 | service，str类型
	private String checkcyc; // 连接次数
	private String service; // 服务类型
	private String checkport; // 服务健康检查端口
	private String request; // 服务健康检查请求内容
	private String receive; // 服务健康检查响应内容
	private String virtualHost; // *HTTP虚拟主机名
	private String login; // 服务登录用户名
	private String password; // 服务登录密码
	private String checkdb; // 数据库名
	private String httpMethod; // HTTP服务健康检查方法 可取值GET或HEAD
	private String emailalert; // email告警地址
	private int emailalertfreq;
	private String forwardingMethod;
	private String feedbackMethod;
	private String optr_id;
	private String optr_name;
	private String opt_time;
	private String INFO;
	private List resultList = null;


	public String getCheckdb() {
		return checkdb;
	}

	public void setCheckdb(String checkdb) {
		this.checkdb = checkdb;
	}

	public String getChecktype() {
		return checktype;
	}

	public void setChecktype(String checktype) {
		this.checktype = checktype;
	}

	public String getEmailalert() {
		return emailalert;
	}

	public void setEmailalert(String emailalert) {
		this.emailalert = emailalert;
	}

	public int getEmailalertfreq() {
		return emailalertfreq;
	}

	public void setEmailalertfreq(int emailalertfreq) {
		this.emailalertfreq = emailalertfreq;
	}

	public String getFallback() {
		return fallback;
	}

	public void setFallback(String fallback) {
		this.fallback = fallback;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public String getOpt_time() {
		return opt_time;
	}

	public void setOpt_time(String opt_time) {
		this.opt_time = opt_time;
	}

	public String getOptr_id() {
		return optr_id;
	}

	public void setOptr_id(String optr_id) {
		this.optr_id = optr_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getScheduler() {
		return scheduler;
	}

	public void setScheduler(String scheduler) {
		this.scheduler = scheduler;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}


	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getOptr_name() {
		return optr_name;
	}

	public void setOptr_name(String optr_name) {
		this.optr_name = optr_name;
	}

	public String getCheckcyc() {
		return checkcyc;
	}

	public void setCheckcyc(String checkcyc) {
		this.checkcyc = checkcyc;
	}

	public String getCheckport() {
		return checkport;
	}

	public void setCheckport(String checkport) {
		this.checkport = checkport;
	}

	public String getFeedbackMethod() {
		return feedbackMethod;
	}

	public void setFeedbackMethod(String feedbackMethod) {
		this.feedbackMethod = feedbackMethod;
	}

	public String getForwardingMethod() {
		return forwardingMethod;
	}

	public void setForwardingMethod(String forwardingMethod) {
		this.forwardingMethod = forwardingMethod;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getPersistent() {
		return persistent;
	}

	public void setPersistent(String persistent) {
		this.persistent = persistent;
	}

	public String getPersistentType() {
		return persistentType;
	}

	public void setPersistentType(String persistentType) {
		this.persistentType = persistentType;
	}

	public String getVirtualAddress() {
		return virtualAddress;
	}

	public void setVirtualAddress(String virtualAddress) {
		this.virtualAddress = virtualAddress;
	}

	public String getVirtualHost() {
		return virtualHost;
	}

	public void setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
	}

	public String getINFO() {
		return INFO;
	}

	public void setINFO(String info) {
		INFO = info;
	}

}
