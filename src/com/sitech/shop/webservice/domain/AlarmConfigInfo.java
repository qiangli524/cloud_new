package com.sitech.shop.webservice.domain;

/**
 * 告警配置信息
 * @author jily
 *
 */
public class AlarmConfigInfo {
	private String connect_id;//connect标识
	private String unit_id;//实体ID*
	private String unit_name;//实体名称
	private String kpi_id;//指标ID*
	private String kpi_name;//指标名称
	private String expression;//表达式
	private String expression_desc;//中文表达式
	private String condition;//条件
	private String value;//阀值
	private String alarm_count;//告警次数
	private String alarm_level;//告警级别（严重:1，重要：2，一般：3）
	private String alarm_type="1";//告警类型(单指标告警：1，多指标告警：2，默认是1)
	private String type;//操作类型（增add：1，删del：2，改 mdf：3）
	private String subscribePersons;//告警订阅人信息 格式json
	private String enable;//是否生效 是：1 否：0 默认：1
	private String value_unit;//阀值单位
	
	
	public String getConnect_id() {
		return connect_id;
	}
	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}
	public String getExpression_desc() {
		return expression_desc;
	}
	public void setExpression_desc(String expression_desc) {
		this.expression_desc = expression_desc;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getValue_unit() {
		return value_unit;
	}
	public void setValue_unit(String value_unit) {
		this.value_unit = value_unit;
	}
	public String getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getKpi_id() {
		return kpi_id;
	}
	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}
	public String getKpi_name() {
		return kpi_name;
	}
	public void setKpi_name(String kpi_name) {
		this.kpi_name = kpi_name;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getAlarm_count() {
		return alarm_count;
	}
	public void setAlarm_count(String alarm_count) {
		this.alarm_count = alarm_count;
	}
	public String getAlarm_level() {
		return alarm_level;
	}
	public void setAlarm_level(String alarm_level) {
		this.alarm_level = alarm_level;
	}
	public String getAlarm_type() {
		return alarm_type;
	}
	public void setAlarm_type(String alarm_type) {
		this.alarm_type = alarm_type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubscribePersons() {
		return subscribePersons;
	}
	public void setSubscribePersons(String subscribePersons) {
		this.subscribePersons = subscribePersons;
	}
	
}
