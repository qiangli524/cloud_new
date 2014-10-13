package com.sitech.ssd.ah.paas.service.statistics;

import java.util.List;

import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;
import com.sitech.ssd.ah.paas.domain.monitor.PaasResourceMonitorObj;
import com.sitech.ssd.ah.paas.domain.statistics.PaasKpiMonitorObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;

/**
 * 
 * <p>
 * Title: PaasStatisticsService
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
 * @createtime 2014-3-31 下午4:05:37
 * 
 */
public interface PaasStatisticsService {
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
	 * @Description: 展示命中情况
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-1 下午3:31:39
	 */
	public FusionCharts showHitsAndMisses(PaasTreeObj obj);

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
	 * @createtime 2014年4月23日11:06:04
	 */
	public List<PaasKpiMonitorObj> queryForLastStatisticsList(PaasKpiMonitorObj obj);
	
	/**
	 * 
	 * @Title: queryForLastStatisticsList
	 * @Description: 查询数据监控中的全局参数
	 * @param
	 * @return PaasResourceMonitorObj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014年4月23日11:06:04
	 */
	public PaasResourceMonitorObj queryForGlobalValue(PaasKpiMonitorObj obj);

	
}
