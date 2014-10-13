package com.sitech.basd.yicloud.web.healthy.form;

import java.util.List;

@SuppressWarnings("serial")
public class HealthyAlarmForm {
	private String id; // 告警编号
	private String tag; // 告警类型
	private String EVENT_ID; // 告警编号
	private String EVENT_TYPE; // 告警类型
	private String EVENT_LEVEL; // 告警级别
	private String EVENT_STAT; // 当前状态
	private String TITILE; // 告警标题
	private String CONTENT; // 告警内容
	private String EVENT_LOCATION; // 告警位置
	private String ALARM_TIME; // 第一次告警产生时间
	private String INS_DATE; // 最后一次告警产生时间
	private String PRO_TIME; // 处理时间
	private String ENTITY_ID; // 实体ID-VMID、主机ID等
	private List resultList = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public String getEVENT_STAT() {
		return EVENT_STAT;
	}

	public void setEVENT_STAT(String event_stat) {
		EVENT_STAT = event_stat;
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

	public String getEVENT_LOCATION() {
		return EVENT_LOCATION;
	}

	public void setEVENT_LOCATION(String event_location) {
		EVENT_LOCATION = event_location;
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

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getPRO_TIME() {
		return PRO_TIME;
	}

	public void setPRO_TIME(String pro_time) {
		PRO_TIME = pro_time;
	}

	public String getENTITY_ID() {
		return ENTITY_ID;
	}

	public void setENTITY_ID(String entity_id) {
		ENTITY_ID = entity_id;
	}

}
