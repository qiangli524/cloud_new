/**
 * ReportEntity.java
 * com.sitech.ssd.sx.report.domain
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 四月 1 		duangh
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
 */

package com.sitech.ssd.sx.report.domain;

/**
 * ClassName:ReportEntity Function: 报表数据类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 四月 1 15:02:15
 */
public class ReportFilter {
	private String id;
	private String kpi;
	private String starttime;
	private String endtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKpi() {
		return kpi;
	}

	public void setKpi(String kpi) {
		this.kpi = kpi;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
}
