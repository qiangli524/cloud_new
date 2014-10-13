package com.sitech.ssd.ah.busiMonitor.dao;

import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorReportObj;

public interface MonitorReportDao {
	public List<Data> queryChartJSONForTraffic(MonitorReportObj obj);
}
