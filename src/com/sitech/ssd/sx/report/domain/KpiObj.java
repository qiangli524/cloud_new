/**
 * Copyright (c) 2013 SI-TECH Software, Inc.
 * All right reserved. 
 * 
 * 云管理平台
 */
package com.sitech.ssd.sx.report.domain;

/**
 *<P>
 * 类功能简述（例如：XXX功能）
 *</p>
 * Apr 13, 2014,10:54:27 AM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
public class KpiObj {
	private String time;
	private String value;
	/**
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 *
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 *
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
