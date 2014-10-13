package com.sitech.basd.yicloud.domain.scheduler;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TriggerRelationObj extends BaseObj {
	private String scheduler_id;
	private String trigger_id;
	private String kpi_id;
	private String entity_id;
	private String kpi_n;
	private String kpi_m;
	private String kpi_s;
	private String ins_date;
	private String up_date;

	public String getScheduler_id() {
		return scheduler_id;
	}

	public void setScheduler_id(String scheduler_id) {
		this.scheduler_id = scheduler_id;
	}

	public String getTrigger_id() {
		return trigger_id;
	}

	public void setTrigger_id(String trigger_id) {
		this.trigger_id = trigger_id;
	}

	public String getKpi_id() {
		return kpi_id;
	}

	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getKpi_n() {
		return kpi_n;
	}

	public void setKpi_n(String kpi_n) {
		this.kpi_n = kpi_n;
	}

	public String getKpi_m() {
		return kpi_m;
	}

	public void setKpi_m(String kpi_m) {
		this.kpi_m = kpi_m;
	}

	public String getKpi_s() {
		return kpi_s;
	}

	public void setKpi_s(String kpi_s) {
		this.kpi_s = kpi_s;
	}

	public String getIns_date() {
		return ins_date;
	}

	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}

	public String getUp_date() {
		return up_date;
	}

	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}

}
