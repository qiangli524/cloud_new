package com.sitech.ssd.ah.paas.dao.statistics;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;
import com.sitech.ssd.ah.paas.domain.statistics.PaasKpiMonitorObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;

/**
 * 
 * <p>
 * Title: PaasStatisticsDaoImpl
 * </p>
 * <p>
 * Description: paas摘要展示
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-3-31 下午4:07:51
 * 
 */
@Repository("paasStatisticsDao")
public class PaasStatisticsDaoImpl extends BaseDao implements PaasStatisticsDao {
	/**
	 * 
	 * @Title: queryKpiNameAndValue
	 * @Description: 查询KPI名称和Value
	 * @param
	 * @return List<PaasKpiMonitorObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-31 下午4:37:35
	 */
	@Override
	public List<PaasKpiMonitorObj> queryKpiNameAndValue(PaasKpiMonitorObj obj) {
		List<PaasKpiMonitorObj> list = new ArrayList<PaasKpiMonitorObj>();
		try {
			list = getSqlMap().queryForList("PaasStatistics.queryKpiNameAndValue", obj);
		} catch (SQLException e) {
			logger.error("PaasStatistics.queryKpiNameAndValue: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: showHitsAndMisses
	 * @Description: 查询命中情况
	 * @param
	 * @return Data
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-1 下午3:06:05
	 */
	@Override
	public Data showHitsAndMisses(PaasKpiMonitorObj obj) {
		List<Data> list = new ArrayList<Data>();
		Data data = new Data();
		try {
			list = getSqlMap().queryForList("PaasStatistics.showHitsAndMisses", obj);
			if (list != null && list.size() > 0) {
				data = list.get(0);
			}
		} catch (SQLException e) {
			logger.error("PaasStatistics.showHitsAndMisses: " + e.getMessage()
					+ e.getClass().getName());
		}
		return data;
	}

	/**
	 * 
	 * @Title: showHitsAndMissesForPool
	 * @Description: 查询资源池命中情况
	 * @param
	 * @return Data
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午12:30:08
	 */
	@Override
	public Data showHitsAndMissesForPool(PaasKpiMonitorObj obj) {
		List<Data> list = new ArrayList<Data>();
		Data data = new Data();
		try {
			list = getSqlMap().queryForList("PaasStatistics.showHitsAndMissesForPool", obj);
			if (list != null && list.size() > 0) {
				data = list.get(0);
			}
		} catch (SQLException e) {
			logger.error("PaasStatistics.showHitsAndMissesForPool: " + e.getMessage()
					+ e.getClass().getName());
		}
		return data;
	}

	/**
	 * 
	 * @Title: queryMemcacheExamples
	 * @Description: 查询资源池下的实例
	 * @param
	 * @return List<PaasEntityObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 上午8:51:23
	 */
	@Override
	public List<PaasEntityObj> queryMemcacheExamples(PaasTreeObj obj) {
		List<PaasEntityObj> list = new ArrayList<PaasEntityObj>();
		try {
			list = getSqlMap().queryForList("PaasStatistics.queryMemcacheExamples", obj);
		} catch (SQLException e) {
			logger.error("PaasStatistics.queryMemcacheExamples: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryKpiNameAndValueForPool
	 * @Description: 资源池节点 摘要信息
	 * @param
	 * @return List<PaasKpiMonitorObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 上午11:19:33
	 */
	@Override
	public List<PaasKpiMonitorObj> queryKpiNameAndValueForPool(PaasTreeObj obj) {
		List<PaasKpiMonitorObj> list = new ArrayList<PaasKpiMonitorObj>();
		try {
			list = getSqlMap().queryForList("PaasStatistics.queryKpiNameAndValueForPool", obj);
		} catch (SQLException e) {
			logger.error("PaasStatistics.queryKpiNameAndValueForPool: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryForLastStatisticsList
	 * @Description: 查询最近时间内的监控数据
	 * @param
	 * @return List<PaasKpiMonitorObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014年4月23日11:03:42
	 */
	@Override
	public List<PaasKpiMonitorObj> queryForLastStatisticsList(PaasKpiMonitorObj obj) {
		List<PaasKpiMonitorObj> list = new ArrayList<PaasKpiMonitorObj>();
		try {
			list = getSqlMap().queryForList("PaasStatistics.queryForLastStatisticsList", obj);
		} catch (SQLException e) {
			logger.error("PaasStatistics.queryForLastStatisticsList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryForLastValue
	 * @Description: 查询最新数据
	 * @param
	 * @return List<PaasKpiMonitorObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-23 下午2:53:44
	 */
	@Override
	public List<PaasKpiMonitorObj> queryForLastValue(PaasKpiMonitorObj obj) {
		List<PaasKpiMonitorObj> list = null;
		try {
			list = getSqlMap().queryForList("PaasStatistics.queryForLastValue", obj);
		} catch (SQLException e) {
			logger.error("PaasStatistics.queryForLastValue: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryRealTimeKpi
	 * @Description:查询实时kpi的值
	 * @param
	 * @return List<PaasKpiMonitorObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-18 下午5:53:47
	 */
	@Override
	public List<PaasKpiMonitorObj> queryRealTimeKpi(PaasKpiMonitorObj obj) {
		List<PaasKpiMonitorObj> list = null;
		try {
			list = getSqlMap().queryForList("PaasStatistics.queryRealTimeKpi", obj);
		} catch (SQLException e) {
			logger.error("PaasStatistics.queryRealTimeKpi:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryForLastValueNoHandle
	 * @Description: 查询没有格式化的最新数据
	 * @param
	 * @return List<PaasKpiMonitorObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-8-27 上午11:11:22
	 */
	@Override
	public List<PaasKpiMonitorObj> queryForLastValueNoHandle(PaasKpiMonitorObj obj) {
		List<PaasKpiMonitorObj> list = null;
		try {
			list = getSqlMap().queryForList("PaasStatistics.queryForLastValueNoHandle", obj);
		} catch (SQLException e) {
			logger.error("PaasStatistics.queryForLastValueNoHandle:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

}
