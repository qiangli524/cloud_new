package com.sitech.basd.sxcloud.cloud.domain.eventmanage;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class EventManageObj extends BaseObj implements Serializable, Cloneable {
	private String EVENT_ID; // 事件ID
	private String SERIOUS; // 严重性
	private String EVENT_INFO; // 事件名称
	private String SOURCE_HAPPEN; // 原发站
	private String INS_DATE; // 发生时间
	/** 事件开始时间 */
	private String start_eventTime;
	/** 事件结束时间 */
	private String end_eventTime;

	public String getEVENT_ID() {
		return EVENT_ID;
	}

	public void setEVENT_ID(String event_id) {
		EVENT_ID = event_id;
	}

	public String getSERIOUS() {
		return SERIOUS;
	}

	public void setSERIOUS(String serious) {
		SERIOUS = serious;
	}

	public String getEVENT_INFO() {
		return EVENT_INFO;
	}

	public void setEVENT_INFO(String event_info) {
		EVENT_INFO = event_info;
	}

	public String getSOURCE_HAPPEN() {
		return SOURCE_HAPPEN;
	}

	public void setSOURCE_HAPPEN(String source_happen) {
		SOURCE_HAPPEN = source_happen;
	}

	public String getINS_DATE() {
		return INS_DATE;
	}

	public void setINS_DATE(String ins_date) {
		INS_DATE = ins_date;
	}

	public String getStart_eventTime() {
		return start_eventTime;
	}

	public void setStart_eventTime(String start_eventTime) {
		this.start_eventTime = start_eventTime;
	}

	public String getEnd_eventTime() {
		return end_eventTime;
	}

	public void setEnd_eventTime(String end_eventTime) {
		this.end_eventTime = end_eventTime;
	}
}
