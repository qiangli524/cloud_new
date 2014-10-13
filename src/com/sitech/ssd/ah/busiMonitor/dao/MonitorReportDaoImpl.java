package com.sitech.ssd.ah.busiMonitor.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorReportObj;
@SuppressWarnings("all")
@Repository("monitorReportDao")
public class MonitorReportDaoImpl extends BaseDao  implements MonitorReportDao {

	@Override
	public List<Data> queryChartJSONForTraffic(MonitorReportObj obj) {
		List<Data> lst = null;
		try {
			lst = getSqlMap().queryForList("MonitorReport.queryChartJSONForTraffic", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("MonitorReport.queryChartJSONForTraffic:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

}
