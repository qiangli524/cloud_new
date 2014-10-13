package com.sitech.ssd.ah.paas.domain.alarm;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: PaasAlarmObj
 * </p>
 * <p>
 * Description: paas告警相关属性
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-7-24 上午11:35:23
 * 
 */
@SuppressWarnings("serial")
public class PaasAlarmObj extends BaseObj implements Serializable, Cloneable {
	private String event_id; // 告警编号
	private String event_type; // 告警类型
	private String event_level; // 告警级别
	private String titile; // 告警标题
	private String content; // 告警内容
	private String alarm_time; // 第一次告警产生时间
	private String ins_date; // 最后一次告警产生时间
	private String event_stat; // 当前状态
	private String event_location; // 告警位置
	private String pro_time; // 处理时间
	private String filterlevel; // 用于告警过滤级别；
	private String filtertype; // 用于过滤告警类型
	private String entity_id; // 实体id-vmid、主机id等
	private String event_num;// 告警编号
	private String event_count;// 告警次数
	private String event_value;// 健康度的值

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public String getEvent_level() {
		return event_level;
	}

	public void setEvent_level(String event_level) {
		this.event_level = event_level;
	}

	public String getTitile() {
		return titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAlarm_time() {
		return alarm_time;
	}

	public void setAlarm_time(String alarm_time) {
		this.alarm_time = alarm_time;
	}

	public String getIns_date() {
		return ins_date;
	}

	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}

	public String getEvent_stat() {
		return event_stat;
	}

	public void setEvent_stat(String event_stat) {
		this.event_stat = event_stat;
	}

	public String getEvent_location() {
		return event_location;
	}

	public void setEvent_location(String event_location) {
		this.event_location = event_location;
	}

	public String getPro_time() {
		return pro_time;
	}

	public void setPro_time(String pro_time) {
		this.pro_time = pro_time;
	}

	public String getFilterlevel() {
		return filterlevel;
	}

	public void setFilterlevel(String filterlevel) {
		this.filterlevel = filterlevel;
	}

	public String getFiltertype() {
		return filtertype;
	}

	public void setFiltertype(String filtertype) {
		this.filtertype = filtertype;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getEvent_num() {
		return event_num;
	}

	public void setEvent_num(String event_num) {
		this.event_num = event_num;
	}

	public String getEvent_count() {
		return event_count;
	}

	public void setEvent_count(String event_count) {
		this.event_count = event_count;
	}

	public String getEvent_value() {
		return event_value;
	}

	public void setEvent_value(String event_value) {
		this.event_value = event_value;
	}

}
