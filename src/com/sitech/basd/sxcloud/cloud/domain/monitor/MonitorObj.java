package com.sitech.basd.sxcloud.cloud.domain.monitor;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class MonitorObj extends BaseObj implements Serializable, Cloneable {
	private String EQ_ID; // 物理服务器编号
	private String HY_ID; // 虚拟主机编号
	private String APP_ID; // 应用编号
	private String KPI_ID; // KPI ID
	private String KPI_VALUE; // 采集值
	private String COLL_TIME; // 采集时间

	public String getEQ_ID() {
		return EQ_ID;
	}

	public void setEQ_ID(String eq_id) {
		EQ_ID = eq_id;
	}

	public String getHY_ID() {
		return HY_ID;
	}

	public void setHY_ID(String hy_id) {
		HY_ID = hy_id;
	}

	public String getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(String app_id) {
		APP_ID = app_id;
	}

	public String getKPI_ID() {
		return KPI_ID;
	}

	public void setKPI_ID(String kpi_id) {
		KPI_ID = kpi_id;
	}

	public String getKPI_VALUE() {
		return KPI_VALUE;
	}

	public void setKPI_VALUE(String kpi_value) {
		KPI_VALUE = kpi_value;
	}

	public String getCOLL_TIME() {
		return COLL_TIME;
	}

	public void setCOLL_TIME(String coll_time) {
		COLL_TIME = coll_time;
	}
}
