package com.sitech.basd.sxcloud.rsmu.domain.cloudschedu;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class VirtualServerObj extends BaseObj implements Serializable, Cloneable{
	private int id ;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	private String name;                      
	private String virtualAddress;     //virtualAddress      
	private String scheduler;                 
	private String persistentType;    //persistentType
	private String persistent;         //
	private String netmask;
	private String protocol;
	private String fallback;
	private String checktype;
	private String checkcyc;
	private String service;
	private String checkport;
	private String request;
	private String receive;
	private String virtualHost;    //virtualHost
	private String login;
	private String password;
	private String checkdb;
	private String httpMethod;     //httpMethod
	private String emailalert;
	private int emailalertfreq;
	private String forwardingMethod; //forwardingMethod
	private String feedbackMethod;   //feedbackMethod
	private String optr_id;
	private String opt_time;
	private String optr_name;
	private String INFO;
	public String getCheckcyc() {
		return checkcyc;
	}
	public void setCheckcyc(String checkcyc) {
		this.checkcyc = checkcyc;
	}
	public String getCheckdb() {
		return checkdb;
	}
	public void setCheckdb(String checkdb) {
		this.checkdb = checkdb;
	}
	public String getCheckport() {
		return checkport;
	}
	public void setCheckport(String checkport) {
		this.checkport = checkport;
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
	public String getOptr_name() {
		return optr_name;
	}
	public void setOptr_name(String optr_name) {
		this.optr_name = optr_name;
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
