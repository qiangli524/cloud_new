package com.sitech.ssd.ah.paas.service.monitor;

import java.util.Map;

import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.ssd.ah.paas.domain.statistics.PaasKpiMonitorObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;


public interface PaasStatisticsMonitorService {
	
	/**
	 * 
	 * @Title: getAllStatisticsMonitorData
	 * @Description: 获取所有的数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-4-2 上午11:36:50
	 */
	public Map<String,Object> getAllStatisticsMonitorData(String entity_id);
	
	/**
	 * 
	 * @Title: showHitsAndMisses
	 * @Description: 展示命中情况
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014年4月11日15:02:56
	 */
	public FusionCharts showHitsAndMisses(PaasTreeObj obj);
	
	/**
	 * 
	 * @Title: showHitsAndMisses
	 * @Description: 展示命中情况
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014年4月11日15:02:56
	 */
	public Map<String, String> showHitsAndMissesValue(PaasKpiMonitorObj obj);
	
}
