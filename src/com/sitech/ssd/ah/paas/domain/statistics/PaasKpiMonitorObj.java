package com.sitech.ssd.ah.paas.domain.statistics;

import domain.tab.ChartType;

/**
 * 
 * <p>
 * Title: PaasKpiMonitor
 * </p>
 * <p>
 * Description: paas 监控指标
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
 * @createtime 2014-3-31 上午10:41:33
 * 
 */
public class PaasKpiMonitorObj {
	private String id;
	private String name_kpi;// 指标的kpi
	private String desc_kpi;// 指标的描述
	private int weight_kpi;// 指标的权重（int，0高，1中，2低 默认-1）
	private double threshold_kpi;// 指标告警阀值
	private boolean is_need_threshold;// 是否需要告警阀值
	private String type_kpi;// 指标类型（2基本信息指标,基本监控指标没有阀值信息is_need_threshold=false，1监控指标）
	private String unit_kpi;// 指标单位
	private String server_kpi;// 监控指标服务（int, 1memcache、2tomcat、3oracle、4mysql）等
	private String kpi_name;
	private String kpi_value_last;
	private String kpi_type;
	private String entity_id;
	private String flag;
	private String parent_id;
	private String ext_val;
	private String timeline;
	private String tableName;
	private ChartType type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName_kpi() {
		return name_kpi;
	}

	public void setName_kpi(String name_kpi) {
		this.name_kpi = name_kpi;
	}

	public String getDesc_kpi() {
		return desc_kpi;
	}

	public void setDesc_kpi(String desc_kpi) {
		this.desc_kpi = desc_kpi;
	}

	public int getWeight_kpi() {
		return weight_kpi;
	}

	public void setWeight_kpi(int weight_kpi) {
		this.weight_kpi = weight_kpi;
	}

	public double getThreshold_kpi() {
		return threshold_kpi;
	}

	public void setThreshold_kpi(double threshold_kpi) {
		this.threshold_kpi = threshold_kpi;
	}

	public boolean isIs_need_threshold() {
		return is_need_threshold;
	}

	public void setIs_need_threshold(boolean is_need_threshold) {
		this.is_need_threshold = is_need_threshold;
	}

	public String getType_kpi() {
		return type_kpi;
	}

	public void setType_kpi(String type_kpi) {
		this.type_kpi = type_kpi;
	}

	public String getUnit_kpi() {
		return unit_kpi;
	}

	public void setUnit_kpi(String unit_kpi) {
		this.unit_kpi = unit_kpi;
	}

	public String getServer_kpi() {
		return server_kpi;
	}

	public void setServer_kpi(String server_kpi) {
		this.server_kpi = server_kpi;
	}

	public String getKpi_value_last() {
		return kpi_value_last;
	}

	public void setKpi_value_last(String kpi_value_last) {
		this.kpi_value_last = kpi_value_last;
	}

	public String getKpi_type() {
		return kpi_type;
	}

	public void setKpi_type(String kpi_type) {
		this.kpi_type = kpi_type;
	}

	public String getKpi_name() {
		return kpi_name;
	}

	public void setKpi_name(String kpi_name) {
		this.kpi_name = kpi_name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public ChartType getType() {
		return type;
	}

	public void setType(ChartType type) {
		this.type = type;
	}

	public PaasKpiMonitorObj() {
		super();
	}

	public PaasKpiMonitorObj(String name_kpi, String desc_kpi, ChartType type) {
		super();
		this.name_kpi = name_kpi;
		this.desc_kpi = desc_kpi;
		this.type = type;
	}

	public String getExt_val() {
		return ext_val;
	}

	public void setExt_val(String ext_val) {
		this.ext_val = ext_val;
	}

	public String getTimeline() {
		return timeline;
	}

	public void setTimeline(String timeline) {
		this.timeline = timeline;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
