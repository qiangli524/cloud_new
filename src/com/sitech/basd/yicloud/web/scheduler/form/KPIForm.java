package com.sitech.basd.yicloud.web.scheduler.form;

import java.util.List;

public class KPIForm {
	private String kpi_id;
	private String kpi_weight;
	private String kpi_desc;
	private String query_id;
	private String kpi_type;
	private int flag;
	private List resultList;

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getQuery_id() {
		return query_id;
	}

	public void setQuery_id(String query_id) {
		this.query_id = query_id;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getKpi_id() {
		return kpi_id;
	}

	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}

	public String getKpi_weight() {
		return kpi_weight;
	}

	public void setKpi_weight(String kpi_weight) {
		this.kpi_weight = kpi_weight;
	}

	public String getKpi_desc() {
		return kpi_desc;
	}

	public void setKpi_desc(String kpi_desc) {
		this.kpi_desc = kpi_desc;
	}

	public String getKpi_type() {
		return kpi_type;
	}

	public void setKpi_type(String kpi_type) {
		this.kpi_type = kpi_type;
	}
}
