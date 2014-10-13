package com.sitech.basd.cloud3.domain.sysapp;

import java.util.Date;

public class SysAppObj {

	private Integer id;
	private String appname;
	private Short status;
	private Short type;
	private String remark;
	private String app_identify;
	private String deploypath;
	private String hoseusername;
	private String startshell;
	private String stopshell;
	private Short start_stop_flag;
	private Short execute_flag;
	private Short release_flag;
	private Short strategytype;
	private Short strategy;
	private String basepath;
	private String operationname;
	private Date catch_time;
	private Short catch_status;
	private String sys_id;
	private Short initversionstatus;
	private String onlinepath;
	private String appmark;
	private String versiondesc;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getApp_identify() {
		return app_identify;
	}
	public void setApp_identify(String app_identify) {
		this.app_identify = app_identify;
	}
	public String getDeploypath() {
		return deploypath;
	}
	public void setDeploypath(String deploypath) {
		this.deploypath = deploypath;
	}
	public String getHoseusername() {
		return hoseusername;
	}
	public void setHoseusername(String hoseusername) {
		this.hoseusername = hoseusername;
	}
	public String getStartshell() {
		return startshell;
	}
	public void setStartshell(String startshell) {
		this.startshell = startshell;
	}
	public String getStopshell() {
		return stopshell;
	}
	public void setStopshell(String stopshell) {
		this.stopshell = stopshell;
	}
	public Short getStart_stop_flag() {
		return start_stop_flag;
	}
	public void setStart_stop_flag(Short start_stop_flag) {
		this.start_stop_flag = start_stop_flag;
	}
	public Short getExecute_flag() {
		return execute_flag;
	}
	public void setExecute_flag(Short execute_flag) {
		this.execute_flag = execute_flag;
	}
	public Short getRelease_flag() {
		return release_flag;
	}
	public void setRelease_flag(Short release_flag) {
		this.release_flag = release_flag;
	}
	public Short getStrategytype() {
		return strategytype;
	}
	public void setStrategytype(Short strategytype) {
		this.strategytype = strategytype;
	}
	public Short getStrategy() {
		return strategy;
	}
	public void setStrategy(Short strategy) {
		this.strategy = strategy;
	}
	public String getBasepath() {
		return basepath;
	}
	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}
	public String getOperationname() {
		return operationname;
	}
	public void setOperationname(String operationname) {
		this.operationname = operationname;
	}
	public Date getCatch_time() {
		return catch_time;
	}
	public void setCatch_time(Date catch_time) {
		this.catch_time = catch_time;
	}
	public Short getCatch_status() {
		return catch_status;
	}
	public void setCatch_status(Short catch_status) {
		this.catch_status = catch_status;
	}
	public String getSys_id() {
		return sys_id;
	}
	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}
	public Short getInitversionstatus() {
		return initversionstatus;
	}
	public void setInitversionstatus(Short initversionstatus) {
		this.initversionstatus = initversionstatus;
	}
	public String getAppmark() {
		return appmark;
	}
	public void setAppmark(String appmark) {
		this.appmark = appmark;
	}
	public String getVersiondesc() {
		return versiondesc;
	}
	public void setVersiondesc(String versiondesc) {
		this.versiondesc = versiondesc;
	}
	public String getOnlinepath() {
		return onlinepath;
	}
	public void setOnlinepath(String onlinepath) {
		this.onlinepath = onlinepath;
	}
}
