package com.sitech.basd.yicloud.domain.scheduler;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class DefinitionObj extends BaseObj {
	private String trigger_id;// 条件触发器ID
	private String name;// 触发器名称
	private String level;// 触发策略条件适用实体级别，VC 0,DC1,CL2,HOST3,VHOST4
	private String description;// 触发器 描述
	private String kpi_id;// 指标
	private String operator;// 调度策略规则 计算公式 > >= < <= != in not in
	private String threshold;// 触发条件 临界值
	private String min;// 触发条件 区间下限
	private String max;// 触发条件 区间上限
	private String type;// 触发器类型，0CPU，1内存，2存储，3网络，4其它'
	private String tigger_n;// '触发器 采集次数';
	private String trigger_m;// '触发器 告警次数';
	private String interval;// '触发器 间隔时间，以分钟为单位';
	private String ins_date;
	private String kpi_name;// kpi名称
	private String content;// 内容

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKpi_name() {
		return kpi_name;
	}

	public void setKpi_name(String kpi_name) {
		this.kpi_name = kpi_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTigger_n() {
		return tigger_n;
	}

	public void setTigger_n(String tigger_n) {
		this.tigger_n = tigger_n;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getTrigger_id() {
		return trigger_id;
	}

	public void setTrigger_id(String trigger_id) {
		this.trigger_id = trigger_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKpi_id() {
		return kpi_id;
	}

	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getIns_date() {
		return ins_date;
	}

	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}

	public String getTrigger_m() {
		return trigger_m;
	}

	public void setTrigger_m(String trigger_m) {
		this.trigger_m = trigger_m;
	}

}
