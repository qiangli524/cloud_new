package com.sitech.ssd.ah.paas.domain.tab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.ssd.ah.paas.domain.statistics.PaasKpiMonitorObj;

public class MapChartSet {
	private String id;

	private String kpi_name;

	private String entity_id;

	private String Caption;// 标题

	private int interval;// 间隔时间

	private int potCount;// 查询的点数

	private String startData;// 开始时间

	private String endData;// 结束时间

	private String cycle_time;// 自定义时间

	private String node_name;

	private String tableName;

	private String time_inter;

	private List<Data> datas = new ArrayList<Data>();

	private Map<String, PaasKpiMonitorObj> kpiIds = new HashMap<String, PaasKpiMonitorObj>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKpi_name() {
		return kpi_name;
	}

	public void setKpi_name(String kpi_name) {
		this.kpi_name = kpi_name;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getCaption() {
		return Caption;
	}

	public void setCaption(String caption) {
		Caption = caption;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getPotCount() {
		return potCount;
	}

	public void setPotCount(int potCount) {
		this.potCount = potCount;
	}

	public String getStartData() {
		return startData;
	}

	public void setStartData(String startData) {
		this.startData = startData;
	}

	public String getEndData() {
		return endData;
	}

	public void setEndData(String endData) {
		this.endData = endData;
	}

	public List<Data> getDatas() {
		return datas;
	}

	public void setDatas(List<Data> datas) {
		this.datas = datas;
	}

	public Map<String, PaasKpiMonitorObj> getKpiIds() {
		return kpiIds;
	}

	public void setKpiIds(Map<String, PaasKpiMonitorObj> kpiIds) {
		this.kpiIds = kpiIds;
	}

	public String getNode_name() {
		return node_name;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	public String getCycle_time() {
		return cycle_time;
	}

	public void setCycle_time(String cycle_time) {
		this.cycle_time = cycle_time;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTime_inter() {
		return time_inter;
	}

	public void setTime_inter(String time_inter) {
		this.time_inter = time_inter;
	}

}
