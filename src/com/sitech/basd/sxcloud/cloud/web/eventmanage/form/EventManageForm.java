package com.sitech.basd.sxcloud.cloud.web.eventmanage.form;

import java.util.List;

public class EventManageForm {

	private String EVENT_ID; // 事件ID
	private String SERIOUS; // 严重性
	private String EVENT_INFO; // 事件名称
	private String SOURCE_HAPPEN; // 原发站
	private String INS_DATE; // 发生时间
	/** 事件开始时间 */
	private String start_time;
	/** 事件结束时间 */
	private String end_time;

	@SuppressWarnings("unchecked")
	private List resultList = null;

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

	@SuppressWarnings("unchecked")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
}
