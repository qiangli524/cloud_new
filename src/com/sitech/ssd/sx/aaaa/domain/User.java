/**
 * User.java
 * com.sitech.ssd.sx.aaaa.domain
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 三月 25 		duangh
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
 */

package com.sitech.ssd.sx.aaaa.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * ClassName:User Function: 4A用户管理类
 * 
 * @author duangh
 * @version
 * @since Ver 1.0
 * @Date 2014 三月 25 14:25:07
 */

@XmlType(name = "User")
public class User {
	private String uid;
	private String employeenumber;
	private String cn;
	private String mobile;
	private String mail;
	private String role;
	private String enable;
	private String startdate;
	private String enddate;
	private String updatedate;
	private String position;
	private String job;
	private String org;
	private String sort;
	private String misassperid;

	@XmlElement(name = "uid")
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@XmlElement(name = "employeenumber")
	public String getEmployeenumber() {
		return employeenumber;
	}

	public void setEmployeenumber(String employeenumber) {
		this.employeenumber = employeenumber;
	}

	@XmlElement(name = "cn")
	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	@XmlElement(name = "mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@XmlElement(name = "mail")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@XmlElement(name = "role")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@XmlElement(name = "enable")
	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	@XmlElement(name = "startdate")
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	@XmlElement(name = "enddate")
	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	@XmlElement(name = "updatedate")
	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	@XmlElement(name = "position")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@XmlElement(name = "job")
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@XmlElement(name = "org")
	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	@XmlElement(name = "sort")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@XmlElement(name = "misassperid")
	public String getMisassperid() {
		return misassperid;
	}

	public void setMisassperid(String misassperid) {
		this.misassperid = misassperid;
	}

}
