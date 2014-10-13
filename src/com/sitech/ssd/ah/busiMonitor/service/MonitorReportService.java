package com.sitech.ssd.ah.busiMonitor.service;

import java.util.Map;

import com.sitech.ssd.ah.busiMonitor.domain.MonitorReportObj;

public interface MonitorReportService {
	public String queryChartJSONForTraffic(MonitorReportObj obj);

}
