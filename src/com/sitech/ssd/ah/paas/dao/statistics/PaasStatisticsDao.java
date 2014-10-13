package com.sitech.ssd.ah.paas.dao.statistics;

import java.util.List;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;
import com.sitech.ssd.ah.paas.domain.statistics.PaasKpiMonitorObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;

/**
 * 
 * <p>
 * Title: PaasStatisticsDao
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
 * @createtime 2014-4-2 下午4:05:44
 * 
 */
public interface PaasStatisticsDao {
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
	public List<PaasKpiMonitorObj> queryKpiNameAndValue(PaasKpiMonitorObj obj);

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
	public Data showHitsAndMisses(PaasKpiMonitorObj obj);

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
	public Data showHitsAndMissesForPool(PaasKpiMonitorObj obj);

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
	public List<PaasEntityObj> queryMemcacheExamples(PaasTreeObj obj);

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
	public List<PaasKpiMonitorObj> queryKpiNameAndValueForPool(PaasTreeObj obj);

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
	public List<PaasKpiMonitorObj> queryForLastStatisticsList(PaasKpiMonitorObj obj);

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
	public List<PaasKpiMonitorObj> queryForLastValue(PaasKpiMonitorObj obj);

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
	public List<PaasKpiMonitorObj> queryRealTimeKpi(PaasKpiMonitorObj obj);

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
	public List<PaasKpiMonitorObj> queryForLastValueNoHandle(PaasKpiMonitorObj obj);

}
