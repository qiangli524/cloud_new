package com.sitech.ssd.ah.paas.busiResource.dao.statistics;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;

/**
 * <p>
 * Title: PaasBusiStatisticsDaoImpl
 * </p>
 * <p>
 * Description: PAAS业务资源树tab页面DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-7-28 上午11:25:02
 * 
 */
@SuppressWarnings("unchecked")
@Repository("paasBusiStatisticsDao")
public class PaasBusiStatisticsDaoImpl extends BaseDao implements PaasBusiStatisticsDao {
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(PaasBusiStatisticsDaoImpl.class);

	@Override
	public int queryForSysCount(PaasBusiTreeObj obj) {
		int a;
		try {
			a = (Integer) getSqlMap().queryForObject("PaasBusiStatistics.queryForSysCount", obj);
		} catch (SQLException e) {
			a = 0;
			logger.error("查询系统下主机总数出错！", e);
		}
		return a;
	}

	@Override
	public int queryForBusiCount(PaasBusiTreeObj obj) {
		int a;
		try {
			a = (Integer) getSqlMap().queryForObject("PaasBusiStatistics.queryForBusiCount", obj);
		} catch (SQLException e) {
			a = 0;
			logger.error("查询业务下主机总数出错！", e);
		}
		return a;
	}

	@Override
	public List<GreenPlumHostInfoObj> queryHostsForService(PaasBusiTreeObj obj) {
		List<GreenPlumHostInfoObj> list = new ArrayList<GreenPlumHostInfoObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"PaasBusiStatistics.queryHostsForServiceCount", obj)).intValue()); // 分页查询的基本信息
			}
			list = getSqlMap().queryForList("PaasBusiStatistics.queryHostsForService", obj);
		} catch (Exception sqlException) {
			LogHelper.error("PaasBusiStatistics.queryHostsForService:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public int queryForSysNum(PaasBusiTreeObj obj) {
		int a;
		try {
			a = (Integer) getSqlMap().queryForObject("PaasBusiStatistics.queryForSysNum", obj);
		} catch (SQLException e) {
			a = 0;
			logger.error("查询系统下主机总数出错！", e);
		}
		return a;
	}

	@Override
	public List<VmReportForm> queryVmHostsForService(PaasBusiTreeObj obj) {
		List<VmReportForm> list = new ArrayList<VmReportForm>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"PaasBusiStatistics.queryVmHostsForServiceCount", obj)).intValue()); // 分页查询的基本信息
			}
			list = getSqlMap().queryForList("PaasBusiStatistics.queryVmHostsForService", obj);
		} catch (Exception sqlException) {
			LogHelper.error("PaasBusiStatistics.queryVmHostsForService:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

	@Override
	public int queryForBusiVmCount(PaasBusiTreeObj obj) {
		int a;
		try {
			a = (Integer) getSqlMap().queryForObject("PaasBusiStatistics.queryForBusiVmCount", obj);
		} catch (SQLException e) {
			a = 0;
			logger.error("查询业务下虚拟主机总数出错！", e);
		}
		return a;
	}

	@Override
	public List queryForSysList(PaasBusiTreeObj obj) {
		List<PaasBusiTreeObj> list = new ArrayList<PaasBusiTreeObj>();
		try {
			list = getSqlMap().queryForList("PaasBusiStatistics.queryForSysList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("PaasBusiStatistics.queryForSysList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public int queryForSysVmCount(PaasBusiTreeObj obj) {
		int a;
		try {
			a = (Integer) getSqlMap().queryForObject("PaasBusiStatistics.queryForSysVmCount", obj);
		} catch (SQLException e) {
			a = 0;
			logger.error("查询boss下虚拟主机总数出错！", e);
		}
		return a;
	}

	@Override
	public int queryForSysBusiCount(PaasBusiTreeObj obj) {
		int a;
		try {
			a = (Integer) getSqlMap()
					.queryForObject("PaasBusiStatistics.queryForSysBusiCount", obj);
		} catch (SQLException e) {
			a = 0;
			logger.error("查询boss下业务总数出错！", e);
		}
		return a;
	}

}
