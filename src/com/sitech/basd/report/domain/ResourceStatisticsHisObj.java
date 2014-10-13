package com.sitech.basd.report.domain;

import java.util.Date;

import com.sitech.basd.common.domain.BasePrivilegeObj;

/**
 * 
 * <p>
 * Title: ResourceStatisticsHisObj
 * </p>
 * <p>
 * Description: 资源统计历史表
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-6-17 下午10:24:54
 * 
 */
public class ResourceStatisticsHisObj extends BasePrivilegeObj {
	private String key;
	private String value;
	private String month;
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	private int interval = -1;

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
