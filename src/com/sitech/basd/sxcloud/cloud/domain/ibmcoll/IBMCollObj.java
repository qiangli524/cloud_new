package com.sitech.basd.sxcloud.cloud.domain.ibmcoll;

public class IBMCollObj {
	private String id;
	private String kpi;
	private String totalKpi;
	private String kpiValue;
	private String collTime;
	
	public String getTotalKpi() {
		return totalKpi;
	}
	public void setTotalKpi(String totalKpi) {
		this.totalKpi = totalKpi;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKpi() {
		return kpi;
	}
	public void setKpi(String kpi) {
		this.kpi = kpi;
	}
	public String getKpiValue() {
		return kpiValue;
	}
	public void setKpiValue(String kpiValue) {
		this.kpiValue = kpiValue;
	}
	public String getCollTime() {
		return collTime;
	}
	public void setCollTime(String collTime) {
		this.collTime = collTime;
	}
	
}
