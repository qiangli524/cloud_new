package com.sitech.basd.sxcloud.cloud.domain.leader;

import java.io.Serializable;

public class MonitoringObj implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6118762467001378986L;

	private String id = null;       //设备编号
	
	private String kpi_id = null;	//监控指标
	private String kpi_value = null;//指标值
	private String coll_time = null;//指标值时间
	
	public String getColl_time() {
		return coll_time;
	}
	public void setColl_time(String coll_time) {
		this.coll_time = coll_time;
	}
	public String getKpi_id() {
		return kpi_id;
	}
	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}
	public String getKpi_value() {
		return kpi_value;
	}
	public void setKpi_value(String kpi_value) {
		this.kpi_value = kpi_value;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
