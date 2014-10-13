/**
 * SxReportService.java
 * com.sitech.ssd.sx.report.service
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 四月 1 		duangh
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
 */

package com.sitech.ssd.sx.report.service;

import java.util.List;

import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.ssd.sx.report.domain.HostObj;
import com.sitech.ssd.sx.report.domain.SearchObj;

/**
 * ClassName:SxReportService Function:山西同一时间，不同主机，不同主机，统一时间段等报表接口
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 四月 1 09:37:13
 */
public interface SxReportService {
	/**
	 * 
	 * sametimeData:不同实体同一时间段的报表数据
	 * 
	 * @since duangh Ver 1.0
	 */
	public FusionCharts sametimeData(String entities, String kpi, String type, String starttime, String endtime);

	/**
	 * <p>
	 * 描述该方法的主要功能
	 * </p>
	 * 
	 * @return
	 */
	public List<HostObj> getHostList(SearchObj search);

	/**
	 * <p>
	 * 描述该方法的主要功能
	 * </p>
	 * 
	 * @param search
	 * @return
	 */
	public List<HostObj> getCardList(SearchObj search);

	/**
	 * <p>
	 * 描述该方法的主要功能
	 * </p>
	 * 
	 * @param search
	 * @return
	 */
	public String getManytimeReportData(SearchObj search);
}
