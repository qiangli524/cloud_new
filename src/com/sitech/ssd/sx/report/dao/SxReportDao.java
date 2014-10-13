/**
 * SxReportDao.java
 * com.sitech.ssd.sx.report.dao
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 四月 1 		duangh
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
 */

package com.sitech.ssd.sx.report.dao;

import java.util.List;

import monitor.IBMMonitorBase;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.ssd.sx.report.domain.ReportFilter;
import com.sitech.ssd.sx.report.domain.SearchObj;

/**
 * ClassName:SxReportDao Function: 山西定制报表，展示报表
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 四月 1 10:08:07
 */
public interface SxReportDao {
	/**
	 * 
	 * timePeriod:一段时间内的监控数据
	 * 
	 * @since duangh Ver 1.0
	 */
	public List<Data> timePeriod(ReportFilter filter);

	public String insertLparDayColl(IBMMonitorBase ibmMonitorBase);

	public List<Data> querySametimeData(SearchObj search);
}
