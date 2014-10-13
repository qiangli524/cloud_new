package com.sitech.ssd.ah.paas.service.statistics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.ssd.ah.paas.dao.statistics.PaasStatisticsDao;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;
import com.sitech.ssd.ah.paas.domain.monitor.PaasResourceMonitorObj;
import com.sitech.ssd.ah.paas.domain.statistics.PaasKpiMonitorObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;
import com.sitech.ssd.ah.paas.util.PaasConstant;

/**
 * 
 * <p>
 * Title: paasStatisticsService
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
@Service("paasStatisticsService")
public class PaasStatisticsServiceImpl implements PaasStatisticsService {
	@Autowired
	private PaasStatisticsDao paasStatisticsDao;

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
		return paasStatisticsDao.queryKpiNameAndValue(obj);
	}

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
	@Override
	public FusionCharts showHitsAndMisses(PaasTreeObj obj) {
		FusionCharts fusionCharts = new FusionCharts();
		List<Data> dataList = new ArrayList<Data>();
		Chart chart = new Chart();
		chart.setBgcolor("FFFFFF");
		chart.setEnableRotation("1");// 开启旋转
		chart.setShowborder("0");// 显示边框
		chart.setPieRadius("30");// 饼的外半径
		chart.setShowlabels("0");// 是否显示Label
		chart.setShowpercentvalues("1");// labels上是否显示百分数
		chart.setShowvalues("0");// 是否显示值
		if (obj.getNode_type().equals(PaasConstant.CAAS_EXAMPLE)) {
			dataList = this.queryHitsAndMisses(obj);
		} else if (obj.getNode_type().equals(PaasConstant.CAAS_BUSI)
				|| obj.getNode_type().equals(PaasConstant.CAAS_TYPE)
				|| obj.getNode_type().equals(PaasConstant.CAAS)) {
			dataList = this.queryHitsAndMissesForPool(obj);
		}
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		return fusionCharts;
	}

	/**
	 * 
	 * @Title: queryHitsAndMisses
	 * @Description: 查询命中和未命中数
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-1 下午4:12:23
	 */
	private List<Data> queryHitsAndMisses(PaasTreeObj obj) {
		List<Data> dataList = new ArrayList<Data>();
		Data hitsData = new Data();
		Data missesData = new Data();
		PaasKpiMonitorObj getObj = new  PaasKpiMonitorObj();
		getObj.setServer_kpi(PaasConstant.MEMCACHE);
		getObj.setKpi_type(PaasConstant.MEMCACHE);
		getObj.setEntity_id(obj.getEntity_id());
		if(obj.getFlag().equals("get")){
			getObj.setName_kpi(PaasConstant.GET_HITS);
			hitsData = paasStatisticsDao.showHitsAndMisses(getObj);
			getObj.setName_kpi(PaasConstant.GET_MISSES);
			missesData = paasStatisticsDao.showHitsAndMisses(getObj);
		} else if (obj.getFlag().equals("delete")) {
			getObj.setName_kpi(PaasConstant.DELETE_HITS);
			hitsData = paasStatisticsDao.showHitsAndMisses(getObj);
			getObj.setName_kpi(PaasConstant.DELETE_MISSES);
			missesData = paasStatisticsDao.showHitsAndMisses(getObj);
		} else if (obj.getFlag().equals("incr")) {
			getObj.setName_kpi(PaasConstant.INCR_HITS);
			hitsData = paasStatisticsDao.showHitsAndMisses(getObj);
			getObj.setName_kpi(PaasConstant.INCR_MISSES);
			missesData = paasStatisticsDao.showHitsAndMisses(getObj);
		} else if (obj.getFlag().equals("decr")) {
			getObj.setName_kpi(PaasConstant.DECR_HITS);
			hitsData = paasStatisticsDao.showHitsAndMisses(getObj);
			getObj.setName_kpi(PaasConstant.DECR_MISSES);
			missesData = paasStatisticsDao.showHitsAndMisses(getObj);
		} else if (obj.getFlag().equals("cas")) {
			getObj.setName_kpi(PaasConstant.CAS_HITS);
			hitsData = paasStatisticsDao.showHitsAndMisses(getObj);
			getObj.setName_kpi(PaasConstant.CAS_MISSES);
			missesData = paasStatisticsDao.showHitsAndMisses(getObj);
		} else if (obj.getFlag().equals("touch")) {
			getObj.setName_kpi(PaasConstant.TOUCH_HITS);
			hitsData = paasStatisticsDao.showHitsAndMisses(getObj);
			getObj.setName_kpi(PaasConstant.TOUCH_MISSES);
			missesData = paasStatisticsDao.showHitsAndMisses(getObj);
		}
		Data dataHit = new Data();
		dataHit.setColor("2675B4");
		dataHit.setLabel("命中");
		if (hitsData != null && !"".equals(hitsData)) {
			dataHit.setValue(hitsData.getValue());
		} else {
			dataHit.setValue("0");
		}
		dataList.add(dataHit);

		Data dataMiss = new Data();
		dataMiss.setColor("C91D1D");
		dataMiss.setLabel("未命中");
		if (missesData != null && !"".equals(missesData)) {
			dataMiss.setValue(missesData.getValue());
		} else {
			dataMiss.setValue("0");
		}
		dataList.add(dataMiss);
		return dataList;
	}

	/**
	 * 
	 * @Title: queryHitsAndMissesForPool
	 * @Description: 查询资源池命中和未命中数
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-2 下午12:26:22
	 */
	public List<Data> queryHitsAndMissesForPool(PaasTreeObj obj) {
		List<Data> dataList = new ArrayList<Data>();
		Data hitsData = new Data();
		Data missesData = new Data();
		PaasKpiMonitorObj getObj = new PaasKpiMonitorObj();
		getObj.setParent_id(obj.getParent_id());
		if (obj.getFlag().equals("get")) {
			getObj.setName_kpi(PaasConstant.GET_HITS);
			hitsData = paasStatisticsDao.showHitsAndMissesForPool(getObj);
			getObj.setName_kpi(PaasConstant.GET_MISSES);
			missesData = paasStatisticsDao.showHitsAndMissesForPool(getObj);
		} else if (obj.getFlag().equals("delete")) {
			getObj.setName_kpi(PaasConstant.DELETE_HITS);
			hitsData = paasStatisticsDao.showHitsAndMissesForPool(getObj);
			getObj.setName_kpi(PaasConstant.DELETE_MISSES);
			missesData = paasStatisticsDao.showHitsAndMissesForPool(getObj);
		} else if (obj.getFlag().equals("incr")) {
			getObj.setName_kpi(PaasConstant.INCR_HITS);
			hitsData = paasStatisticsDao.showHitsAndMissesForPool(getObj);
			getObj.setName_kpi(PaasConstant.INCR_MISSES);
			missesData = paasStatisticsDao.showHitsAndMissesForPool(getObj);
		} else if (obj.getFlag().equals("decr")) {
			getObj.setName_kpi(PaasConstant.DECR_HITS);
			hitsData = paasStatisticsDao.showHitsAndMissesForPool(getObj);
			getObj.setName_kpi(PaasConstant.DECR_MISSES);
			missesData = paasStatisticsDao.showHitsAndMissesForPool(getObj);
		} else if (obj.getFlag().equals("cas")) {
			getObj.setName_kpi(PaasConstant.CAS_HITS);
			hitsData = paasStatisticsDao.showHitsAndMissesForPool(getObj);
			getObj.setName_kpi(PaasConstant.CAS_MISSES);
			missesData = paasStatisticsDao.showHitsAndMissesForPool(getObj);
		} else if (obj.getFlag().equals("touch")) {
			getObj.setName_kpi(PaasConstant.TOUCH_HITS);
			hitsData = paasStatisticsDao.showHitsAndMissesForPool(getObj);
			getObj.setName_kpi(PaasConstant.TOUCH_MISSES);
			missesData = paasStatisticsDao.showHitsAndMissesForPool(getObj);
		}
		Data dataHit = new Data();
		dataHit.setColor("2675B4");
		dataHit.setLabel("命中");
		if (hitsData != null && !"".equals(hitsData)) {
			dataHit.setValue(hitsData.getValue());
		} else {
			dataHit.setValue("0");
		}
		dataList.add(dataHit);

		Data dataMiss = new Data();
		dataMiss.setColor("C91D1D");
		dataMiss.setLabel("未命中");
		if (missesData != null && !"".equals(missesData)) {
			dataMiss.setValue(missesData.getValue());
		} else {
			dataMiss.setValue("0");
		}
		dataList.add(dataMiss);
		return dataList;
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
		return paasStatisticsDao.queryMemcacheExamples(obj);
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
		return paasStatisticsDao.queryKpiNameAndValueForPool(obj);
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
	 * @createtime 2014年4月23日11:06:04
	 */
	public List<PaasKpiMonitorObj> queryForLastStatisticsList(PaasKpiMonitorObj obj){
		int chunk_size = 0;
		int total_chunks = 0;
		int mem_requested = 0;
		List<PaasKpiMonitorObj> list = new ArrayList<PaasKpiMonitorObj>();
		list = paasStatisticsDao.queryForLastStatisticsList(obj);
		for(PaasKpiMonitorObj obj1 : list){
			if("total_chunks ".equals(obj1.getKpi_name())){
				total_chunks = obj1.getKpi_value_last()!=null&&obj1.getKpi_value_last()!=""?Integer.parseInt(obj1.getKpi_value_last()):0;
			}else if("chunk_size".equals(obj1.getKpi_name())){
				chunk_size = obj1.getKpi_value_last()!=null&&obj1.getKpi_value_last()!=""?Integer.parseInt(obj1.getKpi_value_last()):0;
			}else if("mem_requested".equals(obj1.getKpi_name())){
				mem_requested = obj1.getKpi_value_last()!=null&&obj1.getKpi_value_last()!=""?Integer.parseInt(obj1.getKpi_value_last()):0;
			}
		}
		PaasKpiMonitorObj obj2 = new PaasKpiMonitorObj();
		obj2.setKpi_name("被浪费内存数 ( total_chunks * chunk_size - mem_requested )");
		obj2.setDesc_kpi("west");
		obj2.setKpi_value_last(String.valueOf(total_chunks * chunk_size - mem_requested));
		list.add(obj2);
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
	 * @createtime 2014年4月23日11:06:04
	 */
	public PaasResourceMonitorObj queryForGlobalValue(PaasKpiMonitorObj obj){
		PaasResourceMonitorObj globalObj = new PaasResourceMonitorObj();
		List<PaasKpiMonitorObj> list = new ArrayList<PaasKpiMonitorObj>();
		list = paasStatisticsDao.queryForLastStatisticsList(obj);
		for(PaasKpiMonitorObj kpi : list){
			if("curr_items".equals(kpi.getKpi_name())&&"21".equals(kpi.getKpi_type().substring(0, 2))){
				globalObj.setCurr_items(kpi.getKpi_value_last());
			}else if("bytes".equals(kpi.getKpi_name())&&"21".equals(kpi.getKpi_type().substring(0, 2))){
				globalObj.setCurr_items(kpi.getKpi_value_last());
			}else if("actives_slabs".equals(kpi.getKpi_name())&&"23".equals(kpi.getKpi_type().substring(0, 2))){
				globalObj.setCurr_items(kpi.getKpi_value_last());
			}else if("total_malloced".equals(kpi.getKpi_name())&&"23".equals(kpi.getKpi_type().substring(0, 2))){
				globalObj.setTotal_malloced(kpi.getKpi_value_last());
			}
		}
		return globalObj;
		
		
	}
	
}
