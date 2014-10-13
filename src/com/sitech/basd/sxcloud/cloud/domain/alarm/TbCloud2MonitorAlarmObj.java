package com.sitech.basd.sxcloud.cloud.domain.alarm;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: TbCloud2MonitorAlarmObj
 * </p>
 * <p>
 * Description: 监控警告属性类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Mar 26, 2012 4:35:36 PM
 * 
 */
public class TbCloud2MonitorAlarmObj extends BaseObj implements Serializable, Cloneable {
	/** 告警ID */
	private String EVENT_ID;
	/** 告警编号 */
	private String EVENT_NUM;

	public String getEVENT_NUM() {
		return EVENT_NUM;
	}

	public void setEVENT_NUM(String eVENT_NUM) {
		EVENT_NUM = eVENT_NUM;
	}

	/** 告警类型 */
	private String EVENT_TYPE;
	/** 告警等级 */
	private String EVENT_LEVEL;
	/** 告警标题 */
	private String TITILE;
	/** 告警内容 */
	private String CONTENT;
	/** 告警时间 */
	private String ALARM_TIME;
	/** 写入时间 */
	private String INS_DATE;
	/** 告警状态：0未处理，1已处理 */
	private String EVENT_STAT;
	/** 告警发生位置 */
	private String EVENT_LOCATION;
	/** 告警次数 */
	private int EVENT_COUNT;

	public int getEVENT_COUNT() {
		return EVENT_COUNT;
	}

	public void setEVENT_COUNT(int event_count) {
		EVENT_COUNT = event_count;
	}

	public String getEVENT_ID() {
		return EVENT_ID;
	}

	public void setEVENT_ID(String event_id) {
		EVENT_ID = event_id;
	}

	public String getEVENT_TYPE() {
		return EVENT_TYPE;
	}

	public void setEVENT_TYPE(String event_type) {
		EVENT_TYPE = event_type;
	}

	public String getEVENT_LEVEL() {
		return EVENT_LEVEL;
	}

	public void setEVENT_LEVEL(String event_level) {
		EVENT_LEVEL = event_level;
	}

	public String getTITILE() {
		return TITILE;
	}

	public void setTITILE(String titile) {
		TITILE = titile;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String content) {
		CONTENT = content;
	}

	public String getALARM_TIME() {
		return ALARM_TIME;
	}

	public void setALARM_TIME(String alarm_time) {
		ALARM_TIME = alarm_time;
	}

	public String getINS_DATE() {
		return INS_DATE;
	}

	public void setINS_DATE(String ins_date) {
		INS_DATE = ins_date;
	}

	public String getEVENT_STAT() {
		return EVENT_STAT;
	}

	public void setEVENT_STAT(String event_stat) {
		EVENT_STAT = event_stat;
	}

	public String getEVENT_LOCATION() {
		return EVENT_LOCATION;
	}

	public void setEVENT_LOCATION(String event_location) {
		EVENT_LOCATION = event_location;
	}

}
