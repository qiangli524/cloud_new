package com.sitech.basd.report.service;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.Definition;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.fusioncharts.vo.Styles;
import com.sitech.basd.fusioncharts.vo.Trendline;
import com.sitech.basd.fusioncharts.vo.Trendlines;
import com.sitech.basd.report.dao.ReportDao;
import com.sitech.basd.report.domain.ResourceStatisticsHisObj;
import com.sitech.basd.resource.dao.united.ResourceStatisticsDao;
import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.domain.united.ResourceStatisticsObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.sxcloud.cloud.dao.reporthost.TbHostDao;
import com.sitech.basd.sxcloud.cloud.dao.reportvirtual.TbHyDao;
import com.sitech.basd.sxcloud.cloud.domain.reporthost.TbHostObj;
import com.sitech.basd.sxcloud.cloud.domain.reportvirtual.TbHyObj;
import com.sitech.basd.yicloud.dao.ipinfo.IpinfoDao;
import com.sitech.utils.date.DateUtil;
import com.sitech.utils.date.TimeformatCommon;
import com.sitech.utils.number.NumberFormatUtil;

/**
 * 
 * <p>
 * Title: HomePageReportServiceImpl
 * </p>
 * <p>
 * Description: 首页报表Service实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-6-17 下午2:25:42
 * 
 */
@Service("homePageReportService")
@SuppressWarnings("rawtypes")
public class HomePageReportServiceImpl implements HomePageReportService {
	private static final Logger LOG = LoggerFactory.getLogger(HomePageReportServiceImpl.class);
	@Autowired
	private ResourceStatisticsDao resourceStatisticsDao;
	@Autowired
	private UnitedTreeDao unitedTreeDao;
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	private TbHostDao hostDao;
	
	@Autowired
	private TbHyDao hyDao;
	
	@Autowired
	private IpinfoDao ipinfoDao;
	
	private String[] months = new String[] { "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月",
			"十月", "十一月", "十二月" };
	/**
	 * 
	 * @Title: initResourceStatisticsData
	 * @Description: 实例资源统计数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-17 下午4:43:26
	 */
	public String initResourceStatisticsData(String domain) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setBgColor("FFFFFF");
		chart.setUseroundedges("1");
		chart.setShowborder("0");
		chart.setYaxisname("百分比");
//		chart.setPlotGradientColor("");
//		chart.setShowvalues("1");
//		chart.setNumvdivlines("10");
//		chart.setDivlinealpha("10");
//		chart.setDrawanchors("0");
//		chart.setLabelpadding("10");
//		chart.setYaxisvaluespadding("10");
//		chart.setLegendborderalpha("0");
//		chart.setFormatNumberScale("0");

//		List<Categories> categories = new ArrayList<Categories>();
//		Categories categorie = new Categories();
//		List<Category> category = new ArrayList<Category>();
//		Category catcpu = new Category();
//		catcpu.setLabel("CPU");
//		Category catmem = new Category();
//		catmem.setLabel("内存");
//		Category catstorage = new Category();
//		catstorage.setLabel("存储");
//		Category catip = new Category();
//		catip.setLabel("IP");
//		category.add(catcpu);
//		category.add(catmem);
//		category.add(catstorage);
//		category.add(catip);
//		categorie.setCategory(category);
//		categories.add(categorie);
//
//		List<Dataset> dataset = new ArrayList<Dataset>();
//		Dataset usedset = new Dataset();
//		usedset.setSeriesname("占用率（%）");
//		usedset.setColor("ADC85A");
//
		ResourceStatisticsObj resourceStatisticsObj = new ResourceStatisticsObj();
		resourceStatisticsObj.setDomain(domain);
		List<ResourceStatisticsObj> rsoList = resourceStatisticsDao
				.queryForListByObj(resourceStatisticsObj);
		Map<String, String> dataMap = filterStatisticsData(rsoList);
		Data cpuAllData = new Data();
		cpuAllData.setValue(dataMap.get("cpu_percent"));
		cpuAllData.setLabel("CPU\n总量："+dataMap.get("vcpu_all_count")+"核\n已分配："+dataMap.get("vcpu_allo_count")+"核\n已使用："+dataMap.get("vcpu_used_count")+"核");
		Data memAllData = new Data();
		memAllData.setValue(dataMap.get("mem_percent"));
		memAllData.setLabel("内存\n总量："+dataMap.get("mem_all_mb")+"G\n已分配："+dataMap.get("mem_allo_mb")+"G\n已使用："+dataMap.get("mem_used_mb")+"G");
		Data storageAllData = new Data();
		storageAllData.setValue(dataMap.get("storage_percent"));
		storageAllData.setLabel("存储\n总量："+dataMap.get("storage_mount_mb")+"T\n已分配："+dataMap.get("storage_allo_mb")+"T\n已使用："+dataMap.get("storage_used_mb")+"T");
		Data ipAllData = new Data();
		ipAllData.setValue(dataMap.get("ip_percent"));
		ipAllData.setLabel("IP\n总量："+dataMap.get("ip_all_count")+"个\n已分配："+dataMap.get("ip_used_count")+"个");
		
//
//		useddata.add(cpuAllData);
//		useddata.add(memAllData);
//		useddata.add(storageAllData);
//		useddata.add(ipAllData);
//		usedset.setData(useddata);
//		dataset.add(usedset);

		List<Data> datas = new ArrayList<Data>();
		datas.add(cpuAllData);
		datas.add(memAllData);
		datas.add(storageAllData);
		datas.add(ipAllData);
		
		fusionCharts.setData(datas);
		fusionCharts.setChart(chart);
//		fusionCharts.setCategories(categories);
//		fusionCharts.setDataset(dataset);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	/**
	 * 
	 * @Title: initResourceStatisticsTrendData
	 * @Description: 实例资源历史趋势数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-17 下午10:51:29
	 */
	public String initResourceStatisticsTrendData(String type) {
		FusionCharts fusionCharts = new FusionCharts();
		/*
		 * 图表基本信息
		 */
		Chart chart = new Chart();
		chart.setSlantLabels("1");
		chart.setShowborder("0");
		// chart.setCanvasPadding("10");
		chart.setBgColor("FFFFFF");
		// chart.setCaption("资源池历史趋势");
		chart.setXaxisname("月份");
		chart.setYaxisname("使用率");
		chart.setShowlabels("1");
		chart.setAlternatehgridcolor("ff5904");
		chart.setShowalternatevgridcolor("1");
		chart.setDivlinealpha("20");
		chart.setDivlinecolor("ff5904");
		chart.setShowvalues("1");
		chart.setLinecolor("00FFFF");
		chart.setLinealpha("85");
		chart.setLabelDisplay("STAGGER");
		chart.setBasefontcolor("666666");
		chart.setCanvasbordercolor("666666");
		chart.setShowcolumnshadow("1");
		chart.setAnimation("1");
		chart.setAlternatehgridalpha("5");
		chart.setRotatevalues("1");
		chart.setValueposition("auto");
		chart.setDivlinealpha("10");
		chart.setFormatNumberScale("0");

		Styles style = new Styles();
		List<Definition> definition = new ArrayList<Definition>();
		Definition def = new Definition();
		def.setType("font");
		def.setColor("32d2c9");
		definition.add(def);
		style.setDefinition(definition);
		fusionCharts.setStyles(style);
		// 查询几个月
		int quereInterval = Calendar.getInstance().get(Calendar.MONTH);

		/*
		 * 查询数据
		 */
		ResourceStatisticsHisObj param = new ResourceStatisticsHisObj();
		if ("cpu".equals(type)) {
			param.setKey("cpu_used_mhz");
			param.setInterval(quereInterval);
		} else if ("memory".equals(type)) {
			param.setKey("mem_used_mb");
			param.setInterval(quereInterval);
		} else if ("store".equals(type)) {
			param.setKey("storage_used_mb");
			param.setInterval(quereInterval);
		} else if ("ip".equals(type)) {
			param.setKey("ip_used_count");
			param.setInterval(quereInterval);
		}
		List<ResourceStatisticsHisObj> hisList = null;
		try {
			/*
			 * 测试暂时写出IT域
			 */
			// param.setDomain("domain.type.IT");
			hisList = reportDao.queryStatisticsHistory(param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*
		 * 实例数据
		 */
		// List<String> monthList = computeOlder12MonthString();
		List<Data> data = new ArrayList<Data>();
		if (hisList != null && hisList.size() > 0) {
			for (int i = 0; i < hisList.size(); i++) {
				Data da = new Data();
				da.setLabel(hisList.get(i).getMonth() + "月");
				da.setValue(hisList.get(i).getValue());
				data.add(da);
			}
		}
		fusionCharts.setData(data);
		fusionCharts.setChart(chart);
		return JacksonUtil.toJson(fusionCharts);
	}

	/**
	 * 
	 * @Title: initHistoryAlarmData
	 * @Description: 实例历史告警信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-18 下午2:54:50
	 */
	public String initHistoryAlarmData() {
		FusionCharts fusionCharts = new FusionCharts();
		/*
		 * 图表基本信息
		 */
		Chart chart = new Chart();
		chart.setLabelDisplay("STAGGER");
		chart.setShowborder("0");
		chart.setSlantLabels("1");
		chart.setPlotspacepercent("20");
		// chart.setCaption("历史告警信息统计");
		chart.setBgColor("FFFFFF");
		chart.setShowvalues("0");
		chart.setLegendborderalpha("0");
		// chart.setUseroundedges("1");
		// chart.setPalette("0");
		chart.setYaxisname("告警总量");
		chart.setPlotGradientColor("");
		// chart.setShowpercentvalues("1");
		List<Categories> categories = new ArrayList<Categories>();
		Categories categorie = new Categories();
		List<Category> category = new ArrayList<Category>();
		/*
		 * X轴显示
		 */
		List<String> monthList = computeOlder12MonthString();
		for (String month : monthList) {
			Category cat = new Category();
			cat.setLabel(month);
			category.add(cat);
		}
		categorie.setCategory(category);
		categories.add(categorie);
		/*
		 * 柱状值
		 */
		List<Dataset> dataset = new ArrayList<Dataset>();
		Dataset criticalSet = new Dataset();
		criticalSet.setSeriesname("严重告警");
		criticalSet.setColor("F79263");
		List<Data> criticalData = new ArrayList<Data>();
		Dataset majorset = new Dataset();
		majorset.setSeriesname("主要告警");
		majorset.setColor("8BC1E5");
		List<Data> majordata = new ArrayList<Data>();
		Dataset minorset = new Dataset();
		minorset.setSeriesname("一般告警");
		minorset.setColor("ADC85A");
		List<Data> minordata = new ArrayList<Data>();
		try {
			List<Map> list = reportDao.getAlarmHistoryCount();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();

				String label = map.get("month").toString();
				boolean flag = false;
				for (Iterator iterator2 = category.iterator(); iterator2.hasNext();) {
					Category ca = (Category) iterator2.next();
					if (ca.getLabel().equals(label)) {
						flag = true;
						break;
					}
				}

				// if (!flag){
				// Category cat = new Category();
				// cat.setLabel(map.get("month").toString());
				// category.add(cat);
				// }

				// Data data = new Data();
				// data.setValue(map.get("counter").toString());
				// if (map.get("level").toString().equals("1")){
				// criticalData.add(data);
				// }else if (map.get("level").toString().equals("2")){
				// majordata.add(data);
				// }else if (map.get("level").toString().equals("3")){
				// minordata.add(data);
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// categorie.setCategory(category);
		// categories.add(categorie);
		/*
		 * 测试数据
		 */
		Random random = new Random(30);
		for (int i = 0; i < 12; i++) {
			Data data1 = new Data();
			data1.setValue(random.nextInt(30) + "");
			Data data2 = new Data();
			data2.setValue(random.nextInt(30) + "");
			Data data3 = new Data();
			data3.setValue(random.nextInt(30) + "");
			criticalData.add(data1);
			majordata.add(data2);
			minordata.add(data3);
		}

		minorset.setData(minordata);
		majorset.setData(majordata);
		criticalSet.setData(criticalData);

		dataset.add(minorset);
		dataset.add(majorset);
		dataset.add(criticalSet);
		fusionCharts.setChart(chart);
		fusionCharts.setCategories(categories);
		fusionCharts.setDataset(dataset);
		return JacksonUtil.toJson(fusionCharts);
	}

	/**
	 * 
	 * @Title: filterStatisticsData
	 * @Description: 过滤统计数据，用于数据顺序
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-17 下午5:26:34
	 */
	private Map<String, String> filterStatisticsData(List<ResourceStatisticsObj> rsoList) {
		Map<String, String> result = new HashMap<String, String>();
		double vcpu_all_count = 0;
		double vcpu_allo_count = 0;
		double vcpu_used_count = 0;
		double mem_all_mb = 0;
		double mem_allo_mb = 0;
		double mem_used_mb = 0;
		double storage_mount_mb = 0;
		double storage_allo_mb = 0;
		double storage_used_mb = 0;
		double ip_all_count = 0;
		double ip_used_count = 0;
		if (rsoList != null && rsoList.size() > 0) {
			for (ResourceStatisticsObj rso : rsoList) {
				if ("vcpu_all_count".equals(rso.getKey())) {
					vcpu_all_count = Double.valueOf(rso.getValue());
				} else if ("vcpu_allo_count".equals(rso.getKey())) {
					vcpu_allo_count = Double.valueOf(rso.getValue());
				} else if ("vcpu_used_count".equals(rso.getKey())) {
					vcpu_used_count = Double.valueOf(rso.getValue());
				} else if ("mem_all_mb".equals(rso.getKey())) {
					mem_all_mb = Double.valueOf(rso.getValue());
				} else if ("mem_allo_mb".equals(rso.getKey())) {
					mem_allo_mb = Double.valueOf(rso.getValue());
				} else if ("mem_used_mb".equals(rso.getKey())) {
					mem_used_mb = Double.valueOf(rso.getValue());
				} else if ("storage_mount_mb".equals(rso.getKey())) {
					storage_mount_mb = Double.valueOf(rso.getValue());
				} else if ("storage_allo_mb".equals(rso.getKey())) {
					storage_allo_mb = Double.valueOf(rso.getValue());
				} else if ("storage_used_mb".equals(rso.getKey())) {
					storage_used_mb = Double.valueOf(rso.getValue());
				} else if ("ip_all_count".equals(rso.getKey())) {
					ip_all_count = Double.valueOf(rso.getValue());
				} else if ("ip_used_count".equals(rso.getKey())) {
					ip_used_count = Double.valueOf(rso.getValue());
				}
			}
		}
		double cpu_percent = vcpu_allo_count / vcpu_all_count;
		double mem_percent = mem_allo_mb / mem_all_mb;
		double storage_percent = storage_allo_mb / storage_mount_mb;
		double ip_percent = ip_used_count / ip_all_count;
		result.put("cpu_percent", NumberFormatUtil.numFormat(cpu_percent * 100, "0.00"));
		result.put("mem_percent", NumberFormatUtil.numFormat(mem_percent * 100, "0.00"));
		result.put("storage_percent", NumberFormatUtil.numFormat(storage_percent * 100, "0.00"));
		result.put("ip_percent", NumberFormatUtil.numFormat(ip_percent * 100, "0.00"));
		result.put("vcpu_all_count", NumberFormatUtil.numFormat(vcpu_all_count, "0"));
		result.put("vcpu_allo_count", NumberFormatUtil.numFormat(vcpu_allo_count, "0"));
		result.put("vcpu_used_count", NumberFormatUtil.numFormat(vcpu_used_count, "0"));
		result.put("mem_all_mb", NumberFormatUtil.numFormat(mem_all_mb/1024, "0.00"));
		result.put("mem_allo_mb", NumberFormatUtil.numFormat(mem_allo_mb/1024, "0.00"));
		result.put("mem_used_mb", NumberFormatUtil.numFormat(mem_used_mb/1024, "0.00"));
		result.put("storage_mount_mb", NumberFormatUtil.numFormat(storage_mount_mb/(1024*1024), "0.00"));
		result.put("storage_allo_mb", NumberFormatUtil.numFormat(storage_allo_mb/(1024*1024), "0.00"));
		result.put("storage_used_mb", NumberFormatUtil.numFormat(storage_used_mb/(1024*1024), "0.00"));
		result.put("ip_all_count", NumberFormatUtil.numFormat(ip_all_count, "0"));
		result.put("ip_used_count", NumberFormatUtil.numFormat(ip_used_count, "0"));
		return result;
	}

	/**
	 * 
	 * @Title: computeOlder12MonthString
	 * @Description: 根据当前时间月份获取前12个月
	 * @param
	 * @return List<String>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-18 上午11:22:01
	 */
	private List<String> computeOlder12MonthString() {
		List<String> result = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		int m = month;
		for (int i = 0; i < 12; i++) {
			result.add(months[m]);
			m--;
			if (m < 0) {
				m = 11;
			}
		}
		Collections.reverse(result);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.service.HomePageReportService#initVmHostPfmcSummaryData(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String initVmHostPfmcSummaryData(String type,String domain) {
		FusionCharts fusionCharts = new FusionCharts();
		//设置基本chart
		Chart chart = new Chart();
		chart.setXaxisname("虚拟机近五次采集点");
		chart.setYaxisname("百分比");
		chart.setShowvalues("1");
		chart.setCanvasPadding("5");
		chart.setBgcolor("ffffff");
		chart.setShowvalues("0");
		chart.setAnchorRadius("2");
		chart.setLinethickness("1");
		fusionCharts.setChart(chart);
		//设置横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		List<Category> categoryList = new ArrayList<Category>();
		Categories categories = new Categories();
		for(int i = 1;i <= 5; i++){
			Category category = new Category();
			category.setLabel(String.valueOf(i));
			categoryList.add(category);
		}
		categories.setCategory(categoryList);
		categoriesLst.add(categories);
		fusionCharts.setCategories(categoriesLst);
		//查询条件设置
		HashMap<String, String> paramHashMap = new HashMap<String, String>();
		if ("cpu".equals(type)) {
			paramHashMap.put("KPI_ID", "PM-V-01-010-11");
		} else if ("memory".equals(type)) {
			paramHashMap.put("KPI_ID", "PM-V-01-010-12");
		} else if ("diskRead".equals(type)) {
			paramHashMap.put("KPI_ID", "PM-V-01-010-13");
			chart.setYaxisname("KB/S");
		}else if ("diskWrite".equals(type)) {
			paramHashMap.put("KPI_ID", "PM-V-01-010-14");
			chart.setYaxisname("KB/S");
		}
		paramHashMap.put("domain", domain);
		LOG.info("getVmHostPfmcSummarySumInfo方法的参数paramHashMap为："+paramHashMap);
		List list = null;
		try {
			list = reportDao.getVmHostPfmcSummarySumInfo(paramHashMap);
		} catch (SQLException e) {
			LOG.error("虚拟机性能情况查询方法getVmHostPfmcSummarySumInfo(paramHashMap)查询失败",e);
			throw new RuntimeException("虚拟机性能情况查询方法getVmHostPfmcSummarySumInfo(paramHashMap)查询失败",e);
		}
		List<Dataset> dataSetList = new ArrayList<Dataset>();
		if(list != null && list.size() != 0){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				HashMap<String, String> hashMap = (HashMap<String, String>) iterator.next();
				String hyId = hashMap.get("HY_ID");
				paramHashMap.put("HY_ID", hyId);
				Dataset dataset = new Dataset();
				dataset.setSeriesname(hashMap.get("VH_NAME"));
				List vmHostPfmcSummaryInfoList = null;
				LOG.info("getVmHostPfmcSummaryInfo方法的参数paramHashMap为:"+paramHashMap);
				try {
					vmHostPfmcSummaryInfoList = reportDao.getVmHostPfmcSummaryInfo(paramHashMap);
				} catch (SQLException e) {
					LOG.error("虚拟机性能情况查询方法reportDao.getVmHostPfmcSummaryInfo(paramHashMap)查询失败",e);
					throw new RuntimeException("虚拟机性能情况查询方法reportDao.getVmHostPfmcSummaryInfo(paramHashMap)查询失败",e);
				}
				int i = 1;
				List<Data> dataList = new ArrayList<Data>();
				if(vmHostPfmcSummaryInfoList != null && vmHostPfmcSummaryInfoList.size() != 0){
					for (Iterator listIterator = vmHostPfmcSummaryInfoList.iterator(); listIterator.hasNext();) {
						HashMap<String, String> summaryInfohashMap = (HashMap<String, String>)listIterator.next();
						Data data = new Data();
						data.setValue(summaryInfohashMap.get("KPI_VALUE"));
						data.setLabel(String.valueOf(i));
						data.setLink("j-chartClickEventHandle-"+summaryInfohashMap.get("HY_ID")+","+summaryInfohashMap.get("TYPE"));
						dataList.add(data);
						i++;
					}
				}
				dataset.setData(dataList);
				dataSetList.add(dataset);
			}
			fusionCharts.setDataset(dataSetList);
		}
		fusionCharts.setChart(chart);
		return JacksonUtil.toJson(fusionCharts);
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.service.HomePageReportService#initVmHostDetailInfoData(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String initVmHostDetailInfoData(String hyId, String type,String domain) {
		FusionCharts fusionCharts = new FusionCharts();
		//设置基本chart
		Chart chart = new Chart();
		chart.setBgColor("FFFFFF");
		chart.setShowborder("0");
		chart.setPlotGradientColor("");
		chart.setShowvalues("1");
		chart.setNumvdivlines("10");
		chart.setDivlinealpha("10");
		chart.setDrawanchors("0");
		chart.setLabelpadding("10");
		chart.setYaxisvaluespadding("10");
		chart.setLegendborderalpha("0");
		chart.setFormatNumberScale("0");
		chart.setXaxisname("进程名称");
		if ("cpu".equals(type))
			chart.setYaxisname("CPU占用百分比");
		else if ("memory".equals(type))
			chart.setYaxisname("内存占用百分比");
		//查询条件设置
		HashMap<String, String> paramHashMap = new HashMap<String, String>();
		paramHashMap.put("HY_ID", hyId);
		if ("cpu".equals(type)) {
			paramHashMap.put("KPI_ID", "PM-A-01-010-03");
			chart.setCaption("进程占用处理器TOP5统计");
		} else if ("memory".equals(type)) {
			paramHashMap.put("KPI_ID", "PM-A-01-010-04");
			chart.setCaption("进程占用内存TOP5统计");
		} else if ("diskRead".equals(type)||"diskWrite".equals(type)) {
			paramHashMap.put("KPI_ID", "PM-A-01-010-02");
			chart.setYaxisname("进程个数");
			chart.setCaption("进程个数TOP5统计");
		}
		paramHashMap.put("domain", domain);
		LOG.info("reportDao.getVmHostDetailInfo方法的参数paramHashMap为："+paramHashMap);
		List list = null;
		try {
			list = reportDao.getVmHostDetailInfo(paramHashMap);
		} catch (SQLException e) {
			LOG.error("查询虚拟机详细信息的方法reportDao.getVmHostDetailInfo(paramHashMap)失败",e);
			throw new RuntimeException("查询虚拟机详细信息的方法reportDao.getVmHostDetailInfo(paramHashMap)失败",e);
		}
		//设置横坐标，横坐标设置为最近几次数据采集的时间点
		List<Categories> categoriesList = new ArrayList<Categories>();
		Categories categories = new Categories();
		List<Category> categoryList = new ArrayList<Category>();
		List<Data> dataList = new ArrayList<Data>();
		//TODO jiangdi 暂时没有进程数据，构造假数据，需修改
		for (int i=5; i>0; i--){
			Category category = new Category();
			//java init vmtoolsd  wnck-applet escd
			if (i==5)
				category.setLabel("java");
			else if (i==4)
				category.setLabel("vmtoolsd");
			else if (i==3)
				category.setLabel("init");
			else if (i==2)
				category.setLabel("wnck-applet ");
			else if (i==1)
				category.setLabel("escd");
			categoryList.add(category);
			
			Data data = new Data();
			data.setLabel(DateUtil.getCurrentDateStr());
//			NumberFormatUtil.numFormat(Math.random()*(i)*3, null);
			data.setValue(NumberFormatUtil.numFormat(Math.random()*(i)*3, "####.00"));
			dataList.add(data);
		}
		
		ComparatorTemp temp = new ComparatorTemp();
		Collections.sort(dataList, temp);
//		if (list != null && list.size() > 0) {
//			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//				HashMap<String, String> hashMap = (HashMap<String, String>) iterator.next();
//				Category category = new Category();
//				category.setLabel(hashMap.get("COLL_TIME"));
//				categoryList.add(category);
//				Data data = new Data();
//				data.setLabel(hashMap.get("COLL_TIME"));
//				data.setValue(hashMap.get("KPI_VALUE"));
//				dataList.add(data);
//			}
//		}
		categories.setCategory(categoryList);
		categoriesList.add(categories);
		List<Dataset> dataset = new ArrayList<Dataset>();
		Dataset usedset = new Dataset();
		usedset.setColor("ADC85A");
		usedset.setData(dataList);
		dataset.add(usedset);
		fusionCharts.setChart(chart);
		fusionCharts.setCategories(categoriesList);
		fusionCharts.setDataset(dataset);
		return JacksonUtil.toJson(fusionCharts);
	}

	/* (non-Javadoc)
	 * @see com.sitech.basd.report.service.HomePageReportService#queryVmHostDetailInfo(java.util.HashMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List queryVmHostDetailInfo(HashMap<String, String> hashMap) {
		LOG.info("queryVmHostDetailInfo(HashMap<String, String> hashMap)开始执行");
		LOG.info("参数hashMap的值为："+hashMap);
		List list = null;
		try {
			list = reportDao.queryVmHostDetailInfo(hashMap);
		} catch (SQLException e) {
			LOG.error("查询虚拟机详细信息的方法queryVmHostDetailInfo(HashMap<String, String> hashMap)失败",e);
			throw new RuntimeException("查询虚拟机详细信息的方法queryVmHostDetailInfo(HashMap<String, String> hashMap)失败",e);
		}
		LOG.info("queryVmHostDetailInfo(HashMap<String, String> hashMap)执行结束");
		return list;
	}

	@Override
	public String queryLANPortRateReport(String domain) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setBgColor("FFFFFF");
		chart.setUseroundedges("1");
		chart.setShowborder("0");
		
		List<Data> datas = new ArrayList<Data>();
		for (int i = 1; i <6; i++) {
			Data data = new Data();
			data.setValue(NumberFormatUtil.numFormat(Math.random()*(i)*3, "####.00"));
			data.setLabel("设备"+i);
			datas.add(data);
		}
		//add by wangjl_cmi_jl 20140818 从数据库中查询信息
		HashMap<String, String> paraHashMap = new HashMap<String, String>();
		paraHashMap.put("domain", domain);
		paraHashMap.put("KPI_ID", "PM-00-02-001-01");
		try {
			datas = reportDao.queryLANPortRateReport(paraHashMap);
		} catch (SQLException e) {
			LOG.error("LAN（半双工）端口利用率查询失败",e);
			throw new RuntimeException("LAN（半双工）端口利用率",e);
		}
		
		fusionCharts.setData(datas);
		fusionCharts.setChart(chart);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	@Override
	public String queryWANPortInRateReport(String domain) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setBgColor("FFFFFF");
		chart.setUseroundedges("1");
		chart.setShowborder("0");
		
		List<Data> datas = new ArrayList<Data>();
		for (int i = 1; i <6; i++) {
			Data data = new Data();
			data.setValue(NumberFormatUtil.numFormat(Math.random()*(i)*3, "####.00"));
			data.setLabel("设备"+i);
			datas.add(data);
		}
		//add by wangjl_cmi_jl 20140818 从数据库中查询信息
		HashMap<String, String> paraHashMap = new HashMap<String, String>();
		paraHashMap.put("domain", domain);
		paraHashMap.put("KPI_ID", "PM-00-02-001-02");
		try {
			datas = reportDao.queryWANPortInRateReport(paraHashMap);
		} catch (SQLException e) {
			LOG.error("WAN（全双工）端口流入利用率查询失败",e);
			throw new RuntimeException("WAN（全双工）端口流入利用率查询失败",e);
		}
		
		fusionCharts.setData(datas);
		fusionCharts.setChart(chart);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	@Override
	public String queryWANPortOutRateReport(String domain) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setBgColor("FFFFFF");
		chart.setUseroundedges("1");
		chart.setShowborder("0");
		
		List<Data> datas = new ArrayList<Data>();
		for (int i = 1; i <6; i++) {
			Data data = new Data();
			data.setValue(NumberFormatUtil.numFormat(Math.random()*(i)*3, "####.00"));
			data.setLabel("设备"+i);
			datas.add(data);
		}
		//add by wangjl_cmi_jl 20140818 从数据库中查询信息
		HashMap<String, String> paraHashMap = new HashMap<String, String>();
		paraHashMap.put("domain", domain);
		paraHashMap.put("KPI_ID", "PM-00-02-001-03");
		try {
			datas = reportDao.queryWANPortOutRateReport(paraHashMap);
		} catch (SQLException e) {
			LOG.error("WAN（全双工）端口流出利用率查询失败",e);
			throw new RuntimeException("WAN（全双工）端口流出利用率查询失败",e);
		}
		
		fusionCharts.setData(datas);
		fusionCharts.setChart(chart);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	@Override
	public String queryPortErrorRateReport(String domain) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setBgColor("FFFFFF");
		chart.setUseroundedges("1");
		chart.setShowborder("0");
		
		List<Data> datas = new ArrayList<Data>();
		for (int i = 1; i <6; i++) {
			Data data = new Data();
			data.setValue(NumberFormatUtil.numFormat(Math.random()*(i)*3, "####.00"));
			data.setLabel("设备"+i);
			datas.add(data);
		}
		//add by wangjl_cmi_jl 20140818 从数据库中查询信息
		HashMap<String, String> paraHashMap = new HashMap<String, String>();
		paraHashMap.put("domain", domain);
		paraHashMap.put("KPI_ID", "PM-00-02-001-04");
		try {
			datas = reportDao.queryPortErrorRateReport(paraHashMap);
		} catch (SQLException e) {
			LOG.error("端口误码率查询失败",e);
			throw new RuntimeException("WAN（全双工）端口流出利用率查询失败",e);
		}
		
		fusionCharts.setData(datas);
		fusionCharts.setChart(chart);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	@Override
	public String queryPortLostRateReport(String domain) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setBgColor("FFFFFF");
		chart.setUseroundedges("1");
		chart.setShowborder("0");
		
		List<Data> datas = new ArrayList<Data>();
		for (int i = 1; i <6; i++) {
			Data data = new Data();
			data.setValue(NumberFormatUtil.numFormat(Math.random()*(i)*3, "####.00"));
			data.setLabel("设备"+i);
			datas.add(data);
		}
		//add by wangjl_cmi_jl 20140818 从数据库中查询信息
		HashMap<String, String> paraHashMap = new HashMap<String, String>();
		paraHashMap.put("domain", domain);
		paraHashMap.put("KPI_ID", "PM-00-02-001-05");
		try {
			datas = reportDao.queryPortLostRateReport(paraHashMap);
		} catch (SQLException e) {
			LOG.error("端口丢包率查询失败",e);
			throw new RuntimeException("端口丢包率查询失败",e);
		}
		
		fusionCharts.setData(datas);
		fusionCharts.setChart(chart);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	@Override
	public String queryCPUUsedRateReport(String domain) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setBgColor("FFFFFF");
		chart.setUseroundedges("1");
		chart.setShowborder("0");
		
		List<Data> datas = new ArrayList<Data>();
		for (int i = 1; i <6; i++) {
			Data data = new Data();
			data.setValue(NumberFormatUtil.numFormat(Math.random()*(i)*3, "####.00"));
			data.setLabel("设备"+i);
			datas.add(data);
		}
		//add by wangjl_cmi_jl 20140818 从数据库中查询信息
		HashMap<String, String> paraHashMap = new HashMap<String, String>();
		paraHashMap.put("domain", domain);
		paraHashMap.put("KPI_ID", "PM-00-02-001-06");
		try {
			datas = reportDao.queryCPUUsedRateReport(paraHashMap);
		} catch (SQLException e) {
			LOG.error("设备CPU利用率查询失败",e);
			throw new RuntimeException("设备CPU利用率查询失败",e);
		}
		
		fusionCharts.setData(datas);
		fusionCharts.setChart(chart);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	@Override
	public String queryMemUsedRateReport(String domain) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setBgColor("FFFFFF");
		chart.setUseroundedges("1");
		chart.setShowborder("0");
		
		List<Data> datas = new ArrayList<Data>();
		for (int i = 1; i <6; i++) {
			Data data = new Data();
			data.setValue(NumberFormatUtil.numFormat(Math.random()*(i)*3, "####.00"));
			data.setLabel("设备"+i);
			datas.add(data);
		}
		//add by wangjl_cmi_jl 20140818 从数据库中查询信息
		HashMap<String, String> paraHashMap = new HashMap<String, String>();
		paraHashMap.put("domain", domain);
		paraHashMap.put("KPI_ID", "PM-00-02-001-07");
		try {
			datas = reportDao.queryMemUsedRateReport(paraHashMap);
		} catch (SQLException e) {
			LOG.error("设备内存利用率查询失败",e);
			throw new RuntimeException("设备内存利用率查询失败",e);
		}
		
		fusionCharts.setData(datas);
		fusionCharts.setChart(chart);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	@Override
	public String initResourceReportData(HttpServletRequest request) throws SQLException {
		
		String id = request.getParameter("id");
		id = (id == null)?"1":id;
		String parent_id = request.getParameter("parent_id");
		parent_id = (parent_id == null)?"1":parent_id;
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		
		chart.setBgColor("FFFFFF");
		chart.setUseroundedges("1");
		chart.setShowborder("0");
		chart.setYaxisname("百分比");
		chart.setShowAboutMenuItem("1");
		chart.setAboutMenuItemLabel("返回上一级");
		chart.setAboutMenuItemLink("homepagereport_resourceReport.do?id="+id+"&parent_id="+parent_id);
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setParent_id(id);
		List<UnitedTreeObj> list = unitedTreeDao.queryForUnitedTree(obj);
		int i = 1;
		List<Data> datas = new ArrayList<Data>();
		for(UnitedTreeObj tree : list){
			Data data = new Data();
			data.setValue(String.valueOf(i));
			data.setLabel(tree.getName());
			data.setLink("homepagereport_resourceReport.do?id="+tree.getId()+"&parent_id="+tree.getParent_id());
			i++;
			datas.add(data);
		}

		
		fusionCharts.setData(datas);
		fusionCharts.setChart(chart);
//		fusionCharts.setCategories(categories);
//		fusionCharts.setDataset(dataset);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取父节点下所有子节点
	 *  @作者 
	 *	@时间 2014-9-3 下午4:40:11
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getChildren(String parent_id) throws SQLException{
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setParent_id(parent_id);
		List<UnitedTreeObj> list = unitedTreeDao.queryForUnitedTree(obj);
		return list;
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取主机下所有虚拟机
	 *  @作者 
	 *	@时间 2014-9-3 下午4:26:41
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getVirtualMachines(String parent_id) throws SQLException{
		return getChildren(parent_id);
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取集群下所有主机
	 *  @作者 
	 *	@时间 2014-9-3 下午4:27:43
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getHosts(String parent_id) throws SQLException{
		return getChildren(parent_id);
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取集群下所有虚拟机
	 *  @作者 
	 *	@时间 2014-9-3 下午4:29:16
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getHostVMs(String parent_id) throws SQLException{
		List<UnitedTreeObj> returnList = new ArrayList<UnitedTreeObj>();
		//获取所有主机列表
		List<UnitedTreeObj> list = getHosts(parent_id);
		for(UnitedTreeObj obj : list){
			//遍历主机下所有虚拟机
			List<UnitedTreeObj> vmList = getVirtualMachines(obj.getId());
			returnList.addAll(vmList);
		}
		return returnList;
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取数据中心下所有主机
	 *  @作者 
	 *	@时间 2014-9-3 下午4:37:41
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getDCHosts(String parent_id) throws SQLException{
		List<UnitedTreeObj> returnList = new ArrayList<UnitedTreeObj>();
		//获取所有集群
		List<UnitedTreeObj> list = getChildren(parent_id);
		for(UnitedTreeObj obj : list){
			//遍历集群下所有主机
			List<UnitedTreeObj> vmList = getHosts(obj.getId());
			returnList.addAll(vmList);
		}
		return returnList;
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取数据中心下所有虚拟机
	 *  @作者 
	 *	@时间 2014-9-3 下午4:43:47
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getDCHostVMs(String parent_id) throws SQLException{
		List<UnitedTreeObj> returnList = new ArrayList<UnitedTreeObj>();
		//获取所有集群
		List<UnitedTreeObj> list = getChildren(parent_id);
		for(UnitedTreeObj obj : list){
			//遍历集群下所有主机
			List<UnitedTreeObj> vmList = getHostVMs(obj.getId());
			returnList.addAll(vmList);
		}
		return returnList;
	}
	
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取所有数据中心
	 *  @作者 
	 *	@时间 2014-9-3 下午4:46:32
	 *  @版权 si-tech 2014 All right reserved.
	 *  @param parent_id
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getDCs() throws SQLException{
		List<UnitedTreeObj> returnList = new ArrayList<UnitedTreeObj>();
		//获取子节点
		List<UnitedTreeObj> list = getChildren("1");
		for(UnitedTreeObj obj : list){
			if("1".equals(obj.getVtype())||"2".equals(obj.getVtype())){
				returnList.add(obj);
			}
		}
		return returnList;
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取所有主机 
	 *  @作者 
	 *	@时间 2014-9-4 上午10:52:22
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getAllHosts() throws SQLException{
		List<UnitedTreeObj> returnList = new ArrayList<UnitedTreeObj>();
		//获取所有数据中心
		List<UnitedTreeObj> list = getDCs();
		for(UnitedTreeObj obj : list){
			//遍历数据中心下所有主机
			List<UnitedTreeObj> hostList = getDCHosts(obj.getId());
			returnList.addAll(hostList);
		}
		return returnList;
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取所有虚拟机 
	 *  @作者 
	 *	@时间 2014-9-4 上午10:52:22
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 *  @throws SQLException
	 */
	public List<UnitedTreeObj> getAllVms() throws SQLException{
		List<UnitedTreeObj> returnList = new ArrayList<UnitedTreeObj>();
		//获取所有数据中心
		List<UnitedTreeObj> list = getDCs();
		for(UnitedTreeObj obj : list){
			//遍历数据中心下所有虚拟机
			List<UnitedTreeObj> hostList = getDCHostVMs(obj.getId());
			returnList.addAll(hostList);
		}
		return returnList;
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取数据
	 *  @作者 
	 *	@时间 2014-9-4 上午9:26:18
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 */
	public String getDataValue(String host_kpi_id,String vm_kpi_id,List<UnitedTreeObj> hostList,List<UnitedTreeObj> vmList){
		Double sum = new Double(0);
		int count = 1;
		//获取主机平均值
		StringBuffer eq_ids = new StringBuffer("1");
		if(hostList.size()!=0){
			eq_ids.delete(0, eq_ids.length());
			eq_ids.append("'");
			for(UnitedTreeObj obj : hostList){
				eq_ids.append(obj.getConnect_id()).append("_").append(obj.getUuid()).append("','");
			}
			eq_ids = eq_ids.delete(eq_ids.length()-2, eq_ids.length());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("eq_ids", eq_ids.toString());
		map.put("kpi_id", host_kpi_id);
		List<TbHostObj> resultList = hostDao.queryTheNewestDatas(map);
		if(resultList != null){
			for(TbHostObj obj :resultList){
				sum += Double.parseDouble(obj.getKpi_value());
				count++;
			}
		}
		Double hostValue = sum/count;
		DecimalFormat df = new DecimalFormat("######0.00");   
		return String.valueOf(df.format(hostValue));
	}

	@Override
	public String resourceMainReport(HttpServletRequest request) throws SQLException {
		//获取所有主机
		List<UnitedTreeObj> hostList = this.getAllHosts();
		//获取所有虚拟机
		//List<UnitedTreeObj> vmList = this.getAllVms();
		//生成报表json
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		
		chart.setBgColor("FFFFFF");
		chart.setUseroundedges("1");
		chart.setShowborder("0");
		chart.setYaxisname("百分比");
		List<Data> datas = new ArrayList<Data>();
		//CPU
		Data cpuData = new Data();
		cpuData.setValue(this.getDataValue("PM-H-01-010-11", "PM-V-01-010-11", hostList, null));
		cpuData.setLabel("CPU");
		cpuData.setLink("homepagereport_resourceDCReport.do?id=1&type=cpu");
		datas.add(cpuData);
		//内存
		Data memoryData = new Data();
		memoryData.setValue(this.getDataValue("PM-H-01-010-12", "PM-V-01-010-12", hostList, null));
		memoryData.setLabel("内存");
		memoryData.setLink("homepagereport_resourceDCReport.do?id=1&type=memory");
		datas.add(memoryData);		
		//存储
		Data storageData = new Data();
		storageData.setValue(this.getDataValue("PM-00-24-001-14", "PM-40-05-001-12", hostList, null));
		storageData.setLabel("存储");
		storageData.setLink("homepagereport_resourceDCReport.do?id=1&type=storage");
		datas.add(storageData);
		//IP
		Data ipData = new Data();
		ipData.setValue(this.getIpAllData());
		ipData.setLabel("IP");
		//获取网络
		UnitedTreeObj param = new UnitedTreeObj();
		param.setType("1");
		param.setVtype("5");
		UnitedTreeObj rootObj = unitedTreeDao.queryByObj(param);
		ipData.setLink("homepagereport_resourceNextIpReport.do?level=2&id="+rootObj.getId());
		datas.add(ipData);
		
		fusionCharts.setData(datas);
		fusionCharts.setChart(chart);
//		fusionCharts.setCategories(categories);
//		fusionCharts.setDataset(dataset);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	@Override
	public String resourceDCReport(HttpServletRequest request) throws SQLException {
		String type = request.getParameter("type");
		String host_kpi_id = null;
		String vm_kpi_id = null;
		String name = null;
		if("cpu".equals(type)){
			host_kpi_id = "PM-H-01-010-11";
			vm_kpi_id = "PM-V-01-010-11";
			name = "CPU";
		}else if("memory".equals(type)){
			host_kpi_id = "PM-H-01-010-12";
			vm_kpi_id = "PM-V-01-010-12";
			name = "内存";
		}else if("storage".equals(type)){
			host_kpi_id = "PM-00-24-001-14";
			vm_kpi_id = "PM-40-05-001-12";
			name = "存储";
		}
		
		//生成报表json
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setBgColor("FFFFFF");
		chart.setUseroundedges("1");
		chart.setShowborder("0");
		chart.setYaxisname("百分比");
		chart.setShowAboutMenuItem("1");
		chart.setAboutMenuItemLabel("返回上一级");
		chart.setAboutMenuItemLink("homepagereport_resourceMainReport.do");
		chart.setCaption("各数据中心"+name+"使用率报表");
		
		List<Data> datas = new ArrayList<Data>();
		//获取所有数据中心
		List<UnitedTreeObj> list = this.getDCs();
		for(UnitedTreeObj obj : list){
			//获取数据中心下所有主机
			List<UnitedTreeObj> hostList = this.getDCHosts(obj.getId());
			Data data = new Data();
			data.setValue(this.getDataValue(host_kpi_id, vm_kpi_id, hostList, null));;
			data.setLabel(obj.getName());
			data.setLink("homepagereport_resourceGroupReport.do?id="+obj.getId()+"&type="+type);
			datas.add(data);
		}
		fusionCharts.setData(datas);
		fusionCharts.setChart(chart);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	@Override
	public String resourceGroupReport(HttpServletRequest request) throws SQLException {
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		
		String host_kpi_id = null;
		String vm_kpi_id = null;
		String name = null;
		if("cpu".equals(type)){
			host_kpi_id = "PM-H-01-010-11";
			vm_kpi_id = "PM-V-01-010-11";
			name = "CPU";
		}else if("memory".equals(type)){
			host_kpi_id = "PM-H-01-010-12";
			vm_kpi_id = "PM-V-01-010-12";
			name = "内存";
		}else if("storage".equals(type)){
			host_kpi_id = "PM-00-24-001-14";
			vm_kpi_id = "PM-40-05-001-12";
			name = "存储";
		}
		
		//生成报表json
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setBgColor("FFFFFF");
		chart.setUseroundedges("1");
		chart.setShowborder("0");
		chart.setYaxisname("百分比");
		chart.setShowAboutMenuItem("1");
		chart.setAboutMenuItemLabel("返回上一级");
		chart.setAboutMenuItemLink("homepagereport_resourceDCReport.do?id=1&type="+type);
		chart.setCaption("各集群"+name+"使用率报表");
		
		List<Data> datas = new ArrayList<Data>();
		//获取某数据中下所有集群
		List<UnitedTreeObj> list = this.getChildren(id);
		for(UnitedTreeObj obj : list){
			//获取数据中心下所有主机
			List<UnitedTreeObj> hostList = this.getHosts(obj.getId());
			Data data = new Data();
			data.setValue(this.getDataValue(host_kpi_id, vm_kpi_id, hostList, null));;
			data.setLabel(obj.getName());
			//data.setLink("homepagereport_resourceHostReport.do?id="+obj.getId()+"&type="+type+"&parent_id="+id);
			datas.add(data);
			
		}
		fusionCharts.setData(datas);
		fusionCharts.setChart(chart);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	@Override
	public String resourceHostReport(HttpServletRequest request)
			throws SQLException {
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		String parent_id = request.getParameter("parent_id");
		String host_kpi_id = null;
		String vm_kpi_id = null;
		String name = null;
		if("cpu".equals(type)){
			host_kpi_id = "PM-H-01-010-11";
			vm_kpi_id = "PM-V-01-010-11";
			name = "CPU";
		}else if("memory".equals(type)){
			host_kpi_id = "PM-H-01-010-12";
			vm_kpi_id = "PM-V-01-010-12";
			name = "内存";
		}else if("storage".equals(type)){
			host_kpi_id = "PM-00-24-001-14";
			vm_kpi_id = "PM-40-05-001-12";
			name = "存储";
		}
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setShowNames("0");
		chart.setShowvalues("0");
		chart.setShowborder("0");
		chart.setYaxisname("百分比");
		chart.setShowAboutMenuItem("1");
		chart.setAboutMenuItemLabel("返回上一级");
		chart.setAboutMenuItemLink("homepagereport_resourceGroupReport.do?id="+parent_id+"&type="+type);
		chart.setCaption("各主机"+name+"使用率报表");
		//chart.setDrawanchors("0");
		// categories
		Categories categories = new Categories();
		List<Category> categoryList = new ArrayList<Category>();
		List<Categories> categoriesList = new ArrayList<Categories>();
		
		List<Dataset> datasetList = new ArrayList<Dataset>();
		//某集群下所有主机
		List<UnitedTreeObj> hostList = this.getHosts(id);
		if(hostList.size()!=0){
			for(UnitedTreeObj obj : hostList){
				Dataset dataset = new Dataset();
				dataset.setSeriesname(obj.getName());
				dataset.setRenderas("Line");
				dataset.setAnchorRadius("5");
				dataset.setLinethickness("2");
				List<Data> dataList = new ArrayList<Data>();
				
				
				TbHostObj tbHostObj = new TbHostObj();
				tbHostObj.setEq_id(obj.getConnect_id()+"_"+obj.getUuid());
				tbHostObj.setKpi_id(host_kpi_id);
				List<TbHostObj> todayList = hostDao.queryTodayData(tbHostObj);
				for(TbHostObj obj2 : todayList){
					//15分钟一次
						Data data = new Data();
						DecimalFormat df = new DecimalFormat("######0.00");
						data.setValue(df.format(Double.parseDouble(obj2.getKpi_value())));
						data.setShowLabel(0);
						dataList.add(data);
						String time=obj2.getColl_time();
						/**
						 * 取个最小时间，最大步长
						 */
						Category c = new Category(time);
						categoryList.add(c);
				}
				dataset.setData(dataList);
				datasetList.add(dataset);
				categories.setCategory(categoryList);
				
			}
		}
		categoriesList.add(categories);
		fusionCharts.setChart(chart);
		fusionCharts.setCategories(categoriesList);
		fusionCharts.setDataset(datasetList);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	public String queryIpUnusedAndUsedCount(String net_id){
		String ret = ipinfoDao.queryIpUnusedAndUsedCount(net_id);
		return ret;
	}

	public String getIpAllData() throws SQLException {
		String ret = ipinfoDao.queryAllIpUnusedAndUsedCount();
		String[] arr = ret.split(",");
		Double value =  100*(Double.parseDouble(arr[1])/(Double.parseDouble(arr[0])+Double.parseDouble(arr[1])));
		DecimalFormat df = new DecimalFormat("######0.00");   
		return String.valueOf(df.format(value));
		
	}
	
	public List<UnitedTreeObj> getVlanList(int level,String id) throws SQLException {
		List<UnitedTreeObj> list = new ArrayList<UnitedTreeObj>();
		if(level == 2){
			//获取子网络域
			List<UnitedTreeObj> list2 = this.getChildren(id);
			for(UnitedTreeObj obj1 : list2){
				List<UnitedTreeObj> list3 = this.getChildren(obj1.getId());
				//VLAN
				list.addAll(list3);
			}
		}
		if(level == 3){
			//获取VLAN
			List<UnitedTreeObj> list2 = this.getChildren(id);
			list.addAll(list2);
		}
		
		return list;
	}
	
	public String getIPData(HttpServletRequest request) throws SQLException {
		String id = request.getParameter("id");
		int level = Integer.parseInt(request.getParameter("level"));
		// 生成报表json
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setBgColor("FFFFFF");
		chart.setUseroundedges("1");
		chart.setShowborder("0");
		chart.setYaxisname("百分比");
		chart.setShowAboutMenuItem("1");
		chart.setAboutMenuItemLabel("返回上一级");
		if(level==2){
			chart.setAboutMenuItemLink("homepagereport_resourceMainReport.do");
		}else{
			UnitedTreeObj param = new UnitedTreeObj();
			param.setId(id);
			UnitedTreeObj parentObj = unitedTreeDao.queryByObj(param);
			chart.setAboutMenuItemLink("homepagereport_resourceNextIpReport.do?level="+(level-1)+"&id="+parentObj.getParent_id());
		}
		String name = null;
		switch (level) {
			case 2:name = "网络域";break;
			case 3:name = "子网络域";break;
			case 4:name = "VLAN ";break;
		}
		chart.setCaption(name+"IP使用率报表");

		List<Data> datas = new ArrayList<Data>();
		// 获取所有数据中心
		List<UnitedTreeObj> list = this.getChildren(id);
		for (UnitedTreeObj obj : list) {
			Data data = new Data();
			//第二三级节点
			if(level != 4){
				List<UnitedTreeObj> list2 = this.getVlanList(level, obj.getId());
				Double used = new Double(0);
				Double all = new Double(0);
				for(UnitedTreeObj obj1: list2){
					String ret = this.queryIpUnusedAndUsedCount(obj1.getUuid());
					String[] arr = ret.split(",");
					all += (Double.parseDouble(arr[0])+Double.parseDouble(arr[1]));
					used += Double.parseDouble(arr[1]);
				}
				Double value = 100*used/all;
				DecimalFormat df = new DecimalFormat("######0.00");   
				data.setValue(String.valueOf(df.format(value)));
				
				data.setLink("homepagereport_resourceNextIpReport.do?level="+(level+1)+"&id="+obj.getId());
			}else{
				//第四级节点，VLAN
				String ret = this.queryIpUnusedAndUsedCount(obj.getUuid());
				String[] arr = ret.split(",");
				Double value =  100*(Double.parseDouble(arr[1])/(Double.parseDouble(arr[0])+Double.parseDouble(arr[1])));
				DecimalFormat df = new DecimalFormat("######0.00");   
				data.setValue(String.valueOf(df.format(value)));
			}
			
			data.setLabel(obj.getName());
			datas.add(data);
		}
		fusionCharts.setData(datas);
		fusionCharts.setChart(chart);
		LOG.info(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}
}

class ComparatorTemp implements Comparator{
	 public int compare(Object data0, Object data1) {
		 Data d0=(Data)data0;
		 Data d1=(Data)data1;
		 Double.valueOf(d0.getValue());
		 if (Double.valueOf(d0.getValue())<Double.valueOf(d1.getValue()))	return 1;
		 if (Double.valueOf(d0.getValue())>Double.valueOf(d1.getValue()))	return 0;
		 return 0;
	 }
}
