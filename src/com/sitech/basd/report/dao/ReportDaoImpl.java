package com.sitech.basd.report.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import util.DomainUtil;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.report.domain.ResourceStatisticsHisObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

/**
 * 
 * <p>
 * Title: ReportDaoImpl
 * </p>
 * <p>
 * Description: 资源报表数据处理service
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author chenyu
 * @version 1.0
 * @createtime 2014-6-14 上午11:15:09
 * 
 */
@Repository("reportDao")
@SuppressWarnings({ "unchecked" })
public class ReportDaoImpl extends BaseDao implements ReportDao {

	@Override
	public List<Map<String, Long>> getResourceOccupancyRate(String userDomain) throws SQLException {
		List<Map<String, Long>> result = new ArrayList<Map<String, Long>>();
		try {
			result = getSqlMap().queryForList("TbReport.getResourceOccupancyRate", userDomain);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Map<String, Long>> getResourceTrend(String userDomain, String type,
			String startDateStr, String endDateStr) throws SQLException {
		List<Map<String, Long>> result = new ArrayList<Map<String, Long>>();
		try {
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("domain", userDomain);
			if (!StringUtils.isEmpty(type)) {
				paraMap.put("key", type);
			}
			if (!StringUtils.isEmpty(startDateStr)) {
				paraMap.put("startDate", startDateStr);
			}
			if (!StringUtils.isEmpty(endDateStr)) {
				paraMap.put("endDate", endDateStr);
			}
			result = getSqlMap().queryForList("TbReport.getResourceTrend", paraMap);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Map<String, Long>> getResourceAlarmCount() throws SQLException {
		List<Map<String, Long>> result = new ArrayList<Map<String, Long>>();
		try {
			result = getSqlMap().queryForList("TbReport.getResourceAlarmCount");
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	/**
	 * 
	 * @Title: queryStatisticsHistory
	 * @Description:查询统计历史数据
	 * @param
	 * @return List<ResourceStatisticsHisObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-17 下午10:27:23
	 */
	@Override
	public List<ResourceStatisticsHisObj> queryStatisticsHistory(ResourceStatisticsHisObj obj)
			throws SQLException {
		List<ResourceStatisticsHisObj> list = this.getSqlMap().queryForList(
				"TbReport.queryStatisticsHistory", obj);
		return list;
	}

	@Override
	public List<Map> getAlarmHistoryCount() throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>() ;
		DomainUtil.setDomainToMap(map);
		List<Map> list = this.getSqlMap().queryForList("TbReport.getAlarmHistoryCount",map);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.dao.ReportDao#getVmHostPfmcSummarySumInfo(java.util.HashMap)
	 */
	@Override
	public List getVmHostPfmcSummarySumInfo(HashMap<String, String> paramHashMap) throws SQLException {
		List<Map> list = this.getSqlMap().queryForList("TbReport.getVmHostPfmcSummarySumInfo",paramHashMap);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.dao.ReportDao#getVmHostPfmcSummaryInfo(java.util.HashMap)
	 */
	@Override
	public List getVmHostPfmcSummaryInfo(HashMap<String, String> paramHashMap) throws SQLException {
		List<Map> list = this.getSqlMap().queryForList("TbReport.getVmHostPfmcSummaryInfo",paramHashMap);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.dao.ReportDao#getVmHostDetailInfo(java.util.HashMap)
	 */
	@Override
	public List getVmHostDetailInfo(HashMap<String, String> paramHashMap) throws SQLException {
		List<Map> list = this.getSqlMap().queryForList("TbReport.getVmHostDetailInfo",paramHashMap);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.dao.ReportDao#queryVmHostDetailInfo(java.util.HashMap)
	 */
	@Override
	public List queryVmHostDetailInfo(HashMap<String, String> hashMap) throws SQLException {
		List<Map> list = this.getSqlMap().queryForList("TbReport.queryVmHostDetailInfo",hashMap);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.dao.ReportDao#queryCPUUsedRateReport(java.util.HashMap)
	 */
	@Override
	public List<Data> queryCPUUsedRateReport(HashMap<String, String> paraHashMap)
			throws SQLException {
		return queryOtherCollInfo(paraHashMap);
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.dao.ReportDao#queryLANPortRateReport(java.util.HashMap)
	 */
	@Override
	public List<Data> queryLANPortRateReport(HashMap<String, String> paraHashMap)
			throws SQLException {
		return queryOtherCollInfo(paraHashMap);
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.dao.ReportDao#queryMemUsedRateReport(java.util.HashMap)
	 */
	@Override
	public List<Data> queryMemUsedRateReport(HashMap<String, String> paraHashMap)
			throws SQLException {
		return queryOtherCollInfo(paraHashMap);
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.dao.ReportDao#queryPortErrorRateReport(java.util.HashMap)
	 */
	@Override
	public List<Data> queryPortErrorRateReport(
			HashMap<String, String> paraHashMap) throws SQLException {
		return queryOtherCollInfo(paraHashMap);
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.dao.ReportDao#queryPortLostRateReport(java.util.HashMap)
	 */
	@Override
	public List<Data> queryPortLostRateReport(
			HashMap<String, String> paraHashMap) throws SQLException {
		return queryOtherCollInfo(paraHashMap);
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.dao.ReportDao#queryWANPortInRateReport(java.util.HashMap)
	 */
	@Override
	public List<Data> queryWANPortInRateReport(
			HashMap<String, String> paraHashMap) throws SQLException {
		return queryOtherCollInfo(paraHashMap);
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.dao.ReportDao#queryWANPortOutRateReport(java.util.HashMap)
	 */
	@Override
	public List<Data> queryWANPortOutRateReport(
			HashMap<String, String> paraHashMap) throws SQLException {
		return queryOtherCollInfo(paraHashMap);
	}
	
	/**
	 * @Title:queryOtherCollInfo
	 * @Description:查询设备监控信息方法
	 * @param paraHashMap
	 * @return List<Data>
	 * @throws SQLException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 18, 2014 3:14:07 PM
	 */
	private List<Data> queryOtherCollInfo(HashMap<String, String> paraHashMap)throws SQLException {
		List<Data> list = this.getSqlMap().queryForList("TbReport.queryOtherCollInfo",paraHashMap);
		return list;
	}
}
