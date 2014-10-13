package com.sitech.basd.sxcloud.cloud.domain.showresource;

import java.util.Date;

public class ShowResourceObj {
	private String kpi_id;
	private String kpi_value;
	private Date coll_time;

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

	public Date getColl_time() {
		return coll_time;
	}

	public void setColl_time(Date coll_time) {
		this.coll_time = coll_time;
	}

}
