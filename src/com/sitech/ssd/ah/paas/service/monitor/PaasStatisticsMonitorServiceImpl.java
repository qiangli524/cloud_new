package com.sitech.ssd.ah.paas.service.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.Application;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Definition;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.fusioncharts.vo.Styles;
import com.sitech.ssd.ah.paas.dao.monitor.PaasResourceMonitorDao;
import com.sitech.ssd.ah.paas.dao.statistics.PaasStatisticsDao;
import com.sitech.ssd.ah.paas.domain.monitor.PaasResourceMonitorObj;
import com.sitech.ssd.ah.paas.domain.statistics.PaasKpiMonitorObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;
import com.sitech.ssd.ah.paas.util.PaasConstant;


@Service("paasStatisticsMonitorService")
public class PaasStatisticsMonitorServiceImpl implements PaasStatisticsMonitorService {
	@Autowired
	private PaasResourceMonitorDao paasResourceMonitorDao;
	@Autowired
	private PaasStatisticsDao paasStatisticsDao;
	
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
	public Map<String,Object> getAllStatisticsMonitorData(String entity_id){
		PaasResourceMonitorObj obj = new PaasResourceMonitorObj();
		obj.setId(entity_id);
		List<PaasResourceMonitorObj> lst = paasResourceMonitorDao.queryForList(obj);
		Map<String,Object> map = allocateData(lst);
		return map;
	}
	
	/**
	 * 
	 * @Title: allocateData
	 * @Description: 分配数据
	 * @param
	 * @return Map<String,List<PaasResourceMonitorObj>>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-4-2 上午11:42:18
	 */
	public Map<String,Object> allocateData(List<PaasResourceMonitorObj> lst){
		Map<String,Object> map = new HashMap<String, Object>();
		List<PaasResourceMonitorObj> threadInfoLst = new ArrayList<PaasResourceMonitorObj>();
		List<PaasResourceMonitorObj> connInfoLst = new ArrayList<PaasResourceMonitorObj>();
		List<PaasResourceMonitorObj> reqInfoLst = new ArrayList<PaasResourceMonitorObj>();
		List<PaasResourceMonitorObj> totalByteInfoInfoLst = new ArrayList<PaasResourceMonitorObj>();
		List<PaasResourceMonitorObj> storeInfoLst = new ArrayList<PaasResourceMonitorObj>();
		List<PaasResourceMonitorObj> objInfoLst = new ArrayList<PaasResourceMonitorObj>();
		for (int i = 0; i < lst.size(); i++) {
			if(lst.get(i).getKpiName().equals("rusage_user") || lst.get(i).getKpiName().equals("rusage_system")){
				threadInfoLst.add(lst.get(i));
			}else if(lst.get(i).getKpiName().equals("curr_connections") || lst.get(i).getKpiName().equals("total_connections") ||
					lst.get(i).getKpiName().equals("accepting_conns") || lst.get(i).getKpiName().equals("listen_disabled_num") ||
					lst.get(i).getKpiName().equals("conn_yields")){
				connInfoLst.add(lst.get(i));
			}else if(lst.get(i).getKpiName().equals("cmd_get") || lst.get(i).getKpiName().equals("cmd_set") ||
					lst.get(i).getKpiName().equals("cmd_flush") || lst.get(i).getKpiName().equals("cmd_touch")){
				reqInfoLst.add(lst.get(i));
			}else if(lst.get(i).getKpiName().equals("bytes_read") || lst.get(i).getKpiName().equals("bytes_written") ||
					lst.get(i).getKpiName().equals("hash_bytes")){
				totalByteInfoInfoLst.add(lst.get(i));
			}else if(lst.get(i).getKpiName().equals("bytes") || lst.get(i).getKpiName().equals("curr_items") ||
					lst.get(i).getKpiName().equals("total_items")){
				storeInfoLst.add(lst.get(i));
			}else if(lst.get(i).getKpiName().equals("evictions") || lst.get(i).getKpiName().equals("expired_unfetched") ||
					lst.get(i).getKpiName().equals("evicted_unfetched") || lst.get(i).getKpiName().equals("reclaimed")){
				objInfoLst.add(lst.get(i));
			}
		}
		
		map.put("threadInfo", threadInfoLst);
		String threadInfoJo = changeToFusionchartDate(threadInfoLst,"threadInfo");
		map.put("threadInfoJSON", threadInfoJo);
		
		map.put("connInfo", connInfoLst);
		String connInfoJo = changeToFusionchartDate(connInfoLst,"connInfo");
		map.put("connInfoJSON", connInfoJo);
		
		map.put("reqInfo", reqInfoLst);
		String reqInfoJo = changeToFusionchartDate(reqInfoLst,"reqInfo");
		map.put("reqInfoJSON", reqInfoJo);
		
		map.put("totalByteInfo", totalByteInfoInfoLst);
		String totalByteInfoJo = changeToFusionchartDate(totalByteInfoInfoLst,"totalByteInfo");
		map.put("totalByteInfoJSON", totalByteInfoJo);
		
		map.put("storeInfo", storeInfoLst);
		String storeInfoJo = changeToFusionchartDate(storeInfoLst,"storeInfo");
		map.put("storeInfoJSON", storeInfoJo);
		
		map.put("objInfo", objInfoLst);
		String objInfoJo = changeToFusionchartDate(objInfoLst,"objInfo");
		map.put("objInfoJSON", objInfoJo);
		
		return map;
	}
	
	/**
	 * 
	 * @Title: changeToFusionchartDate
	 * @Description: 转换成fusionchart数据
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-4-2 下午3:44:20
	 */
	public String changeToFusionchartDate(List<PaasResourceMonitorObj> lst,String type){
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		chart.setBgcolor("FFFFFF,FFFFFF");
		chart.setShowborder("0");
		if(type.equals("threadInfo")){
			chart.setCaption("进程信息");
		}else if(type.equals("connInfo")){
			chart.setCaption("连接信息");
		}else if(type.equals("reqInfo")){
			chart.setCaption("请求信息");
		}else if(type.equals("totalByteInfo")){
			chart.setCaption("内存信息");
		}else if(type.equals("storeInfo")){
			chart.setCaption("存储信息");
		}else if(type.equals("objInfo")){
			chart.setCaption("过期对象信息");
		}
		chart.setYaxisname("值");
		charts.setChart(chart);
		
		List<Data> data = new ArrayList<Data>();
		for (int i = 0; i < lst.size(); i++) {
			Data dat = new Data();
			dat.setLabel(lst.get(i).getKpiDec());
			dat.setValue(lst.get(i).getKpiValue());
			data.add(dat);
		}
		charts.setData(data);
	
		Styles style = new Styles();
		List<Definition> defLst = new ArrayList<Definition>();
		Definition def = new Definition();
		def.setName("CanvasAnim");
		def.setType("animation");
		def.setParam("_xScale");
		def.setStart("0");
		def.setDuration("1");
		defLst.add(def);
		style.setDefinition(defLst);
		
		List<Application> appLst =  new ArrayList<Application>();
		Application app = new Application();
		app.setToobject("Canvas");
		app.setStyles("CanvasAnim");
		appLst.add(app);
		style.setApplication(appLst);
		charts.setStyles(style);
		return JacksonUtil.toJson(charts);
	}
	
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
	public FusionCharts showHitsAndMisses(PaasTreeObj obj){
		FusionCharts fusionCharts = new FusionCharts();
		List<Data> dataList = new ArrayList<Data>();
		Chart chart = new Chart();
		chart.setBgcolor("FFFFFF");
		chart.setEnableRotation("0");// 开启旋转
		chart.setShowborder("0");// 显示边框
		chart.setPieRadius("65");// 饼的外半径
		chart.setShowlabels("0");// 是否显示Label
		chart.setShowpercentvalues("1");// labels上是否显示百分数
		chart.setShowvalues("0");// 是否显示值
		if (obj.getNode_type().equals(PaasConstant.EXAMPLES)) {
			dataList = this.queryHitsAndMisses(obj);
		} else if (obj.getNode_type().equals(PaasConstant.RESOURCEPOOL)) {
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
//		dataHit.setColor("2675B4");
		dataHit.setLabel("命中");
		if (hitsData != null && !"".equals(hitsData)) {
			dataHit.setValue(hitsData.getValue());
		} else {
			dataHit.setValue("0");
		}
		dataList.add(dataHit);

		Data dataMiss = new Data();
//		dataMiss.setColor("C91D1D");
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
	 * @Title: showHitsAndMisses
	 * @Description: 展示命中情况
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014年4月11日15:02:56
	 */
	public Map<String, String> showHitsAndMissesValue(PaasKpiMonitorObj obj){
		List<PaasKpiMonitorObj> list = paasStatisticsDao.queryKpiNameAndValue(obj);
		Map<String, String> map = new HashMap<String, String>();
		for(PaasKpiMonitorObj kpiObj : list){
			map.put(kpiObj.getName_kpi(), kpiObj.getKpi_value_last());
		}
		return map;
	}
	
}
