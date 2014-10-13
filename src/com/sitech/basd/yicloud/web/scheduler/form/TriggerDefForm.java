package com.sitech.basd.yicloud.web.scheduler.form;

import java.util.List;

public class TriggerDefForm {
	private String trigger_id;// 条件触发器ID，主键
	private String name;// '触发器名称';
	private String type;// 触发器类型，0CPU，1内存，2存储，3网络，4其它'
	private String tigger_n;// '触发器 采集次数';
	private String trigger_m;// '触发器 告警次数';
	private String interval;// '触发器 间隔时间，以分钟为单位';
	private String level;// '触发策略条件适用实体级别，VC 0,DC1,CL2,HOST3,VHOST4';
	private String description;// '触发器 描述';
	private String kpi_id;// 指标
	private String operator;// 调度策略规则 计算公式 > >= < <= != in not in
	private String threshold;// '触发条件 临界值';
	private String min;// 触发条件 区间下限';
	private String max;// 触发条件 区间上限'
	private List resultList;
	private String content;// 内容

	private int flag;// 增加或修改的标识
	private String queryName;

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
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

	public String getTrigger_m() {
		return trigger_m;
	}

	public void setTrigger_m(String trigger_m) {
		this.trigger_m = trigger_m;
	}

}
