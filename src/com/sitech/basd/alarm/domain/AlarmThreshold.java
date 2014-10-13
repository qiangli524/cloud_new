package com.sitech.basd.alarm.domain;
import java.io.Serializable;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;
/**
 * <p>Title: AlarmThreshold</p>
 * <p>Description: TODO(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author qism
 * @version 1.0
 * @createtime 2014-6-20 下午5:34:38
 *
 */
@SuppressWarnings("serial")
public class AlarmThreshold extends BaseObj implements Serializable {
	private String id; 			// 唯一标识
	private String alarm_name;	//告警指标名称(中文)
	private String alarm_kpi; 	//告警指标(英文)
	private String alarm_desc;	//指标描述
	private Integer alarm_level;// 告警级别 (0严重告警;1主要告警;2次要告警;3不确定告警)
	private Integer alarm_type;	//告警实体类型 (6 hadoop,2 host,1 vm,等实体)
	private Integer alarm_threshold;//告警阀值
	private String alarm_unit;	//阀值单位(0百分比，1次数，2小时，3分钟，4秒，5天)
	private Integer alarm_trigger;//告警阀值判断符号 (0小于;1等于;2大于)
	private String start_time;	//不可告警时间开始值
	private String end_time;	//不可告警时间结束值
	private List<String> resultList;
	public List<String> getResultList() {
		return resultList;
	}
	public void setResultList(List<String> resultList) {
		this.resultList = resultList;
	}
	public Integer getAlarm_trigger() {
		return alarm_trigger;
	}
	public void setAlarm_trigger(Integer alarm_trigger) {
		this.alarm_trigger = alarm_trigger;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAlarm_kpi() {
		return alarm_kpi;
	}
	public void setAlarm_kpi(String alarm_kpi) {
		this.alarm_kpi = alarm_kpi;
	}
	public String getAlarm_desc() {
		return alarm_desc;
	}
	public void setAlarm_desc(String alarm_desc) {
		this.alarm_desc = alarm_desc;
	}
	public Integer getAlarm_level() {
		return alarm_level;
	}
	public void setAlarm_level(Integer alarm_level) {
		this.alarm_level = alarm_level;
	}
	public Integer getAlarm_threshold() {
		return alarm_threshold;
	}
	public void setAlarm_threshold(Integer alarm_threshold) {
		this.alarm_threshold = alarm_threshold;
	}
	public Integer getAlarm_type() {
		return alarm_type;
	}
	public void setAlarm_type(Integer alarm_type) {
		this.alarm_type = alarm_type;
	}

	public String getAlarm_unit() {
		return alarm_unit;
	}
	public void setAlarm_unit(String alarm_unit) {
		this.alarm_unit = alarm_unit;
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
	public String getAlarm_name() {
		return alarm_name;
	}
	public void setAlarm_name(String alarm_name) {
		this.alarm_name = alarm_name;
	}
}
