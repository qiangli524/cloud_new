package com.sitech.basd.sxcloud.cloud.domain.reporthost;

public class TbHostObj {

	private String eq_id;
	private String kpi_id;
	private String kpi_value;
	private String coll_time;
	private String table_name;
	private String totalKpi;

	public String getColl_time() {
		return coll_time;
	}

	public void setColl_time(String coll_time) {
		this.coll_time = coll_time;
	}

	public String getTotalKpi() {
		return totalKpi;
	}

	public void setTotalKpi(String totalKpi) {
		this.totalKpi = totalKpi;
	}

	public String getKpi_id() {
		return kpi_id;
	}

	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}

	public String getKpi_value() {
		return kpi_value;
	}

	public void setKpi_value(String kpi_value) {
		this.kpi_value = kpi_value;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

}
