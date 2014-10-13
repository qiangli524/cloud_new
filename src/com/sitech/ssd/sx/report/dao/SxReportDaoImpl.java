/**
 * SxReportDaoImpl.java
 * com.sitech.ssd.sx.report.dao
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 四月 1 		duangh
 *
 * Copyright (c) 2014, si-tech All Rights Reserved.
 */

package com.sitech.ssd.sx.report.dao;

import java.sql.SQLException;
import java.util.List;

import monitor.IBMMonitorBase;

import org.springframework.stereotype.Repository;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.sx.report.domain.ReportFilter;
import com.sitech.ssd.sx.report.domain.SearchObj;

/**
 * ClassName:SxReportDaoImpl Function: 山西定制报表，展示报表
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 四月 1 10:08:46
 */
@Repository("sxReportDao")
public class SxReportDaoImpl extends BaseDao implements SxReportDao {

	@Override
	public List<Data> timePeriod(ReportFilter filter) {
		List<Data> resultList = this.getSqlMapClientTemplate().queryForList("SxReport.timePeriod", filter);
		return resultList;
	}

	@Override
	public String insertLparDayColl(IBMMonitorBase ibmMonitorBase) {
		try {
			this.getSqlMapClient().insert("SxReport.insertLparDayColl", ibmMonitorBase);
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<Data> querySametimeData(SearchObj search) {
		List<Data> resultList = getSqlMapClientTemplate().queryForList("SxReport.querySametimeReportData", search);
		return resultList;
	}
}
