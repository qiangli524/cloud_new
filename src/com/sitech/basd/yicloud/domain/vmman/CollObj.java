package com.sitech.basd.yicloud.domain.vmman;

public class CollObj {
	private Long cllTime;//采集时间
	private String kpiId;//kpi id
	private String kpiValue; //kpi value
	private String unitId;

	public Long getCllTime() {
		return cllTime;
	}

	public void setCllTime(Long cllTime) {
		this.cllTime = cllTime;
	}

	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}

	public String getKpiValue() {
		return kpiValue;
	}

	public void setKpiValue(String kpiValue) {
		this.kpiValue = kpiValue;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
}
