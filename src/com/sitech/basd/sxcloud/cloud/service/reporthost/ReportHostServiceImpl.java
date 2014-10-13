package com.sitech.basd.sxcloud.cloud.service.reporthost;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.cloud3.domain.charts.MonitorObj;
import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.cloud.dao.reporthost.TbHostDao;
import com.sitech.basd.sxcloud.cloud.domain.reporthost.TbHostObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.utils.date.TimeformatCommon;

public class ReportHostServiceImpl extends BaseService implements
		ReportHostService {

	/**
	 * @Title:查询当天的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHostForToday(TbHostObj obj) {
		return tbHostDao.queryTbHostForToday(obj);
	}

	/**
	 * @Title:查询本周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHostForWeek(TbHostObj obj) {
		return tbHostDao.queryTbHostForWeek(obj);
	}

	/**
	 * @Title:跨表查询本周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryUnionTbHostForWeek(TbHostObj obj) {
		return tbHostDao.queryUnionTbHostForWeek(obj);
	}

	/**
	 * @Title:查询上周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHostForLastWeek(TbHostObj obj) {
		return tbHostDao.queryTbHostForLastWeek(obj);
	}

	/**
	 * @Title:跨表查询上周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryUnionTbHostForLastWeek(TbHostObj obj) {
		return tbHostDao.queryUnionTbHostForLastWeek(obj);
	}

	/**
	 * @Title:查询当月的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHostForMonth(TbHostObj obj) {
		return tbHostDao.queryTbHostForMonth(obj);
	}

	/**
	 * @Title:查询上月的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHostForLastMonth(TbHostObj obj) {
		return tbHostDao.queryTbHostForLastMonth(obj);
	}

	/**
	 * @Title: 查询已经监控的虚拟机
	 * @Copyright: Copyright (c) 20120405
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@Override
	public int queryMonitorHostCount(TbCloud2HostInfoObj obj) {
		return tbHostDao.queryMonitorHostCount(obj);
	}

	/**
	 * @Title: 查询所有能监控的主机
	 * @Copyright: Copyright (c) 20120405
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@Override
	public int queryAllHostCount(TbCloud2HostInfoObj obj) {
		return tbHostDao.queryAllHostCount(obj);
	}

	/**
	 * 查询一天之内的监控数据
	 */
	@Override
	public List queryHostMonitorData(Map map) {
		return tbHostDao.queryHostMonitorData(map);
	}

	/**
	 * 查询1周 的监控数据从月表中查询
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public List queryHostMonitorDataByWeek(Map map) {
		return tbHostDao.queryHostMonitorDataByWeek(map);
	}

	/**
	 * 查询一个月的监控数据
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public List queryHostMonitorDataByMonth(Map map) {
		return tbHostDao.queryHostMonitorDataByMonth(map);
	}

	private TbHostDao tbHostDao;

	public void setTbHostDao(TbHostDao tbHostDao) {
		this.tbHostDao = tbHostDao;
	}

	/**
	 * 
	 * @Title: queryChartDayData
	 * @Description: 查询天表数据
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-29 下午8:17:12
	 */
	@Override
	public FusionCharts queryChartDayData(String id, String kpi, String name,
			String cycle_time, String startDate, String endDate,
			String pointCount) {
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		// chart.setCaption("性能监控");//监控名称
//		if (pointCount != null && !pointCount.equals("")
//				&& !"0".equals(pointCount) && !"all".equals(pointCount)) {
//			chart.setNumvisibleplot(pointCount);// 展示点数
//		}
		// chart.setLabelstep("1");
		chart.setLabelDisplay("AUTO");
		// chart.setXaxisname("时间");
		chart.setYaxisname("%");
		chart.setYaxismaxvalue("100");
		// chart.setYaxismaxvalue("100");
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		/////////////////////////////////////////////////
		chart.setDivintervalhints("0, 10, 20, 30,40,50,60,80,100");
		chart.setPalette("3");
		chart.setBtnResetChartTitle("恢复");
		chart.setBtnResetChartTooltext("恢复");
		chart.setBtnSwitchToPinModeTitle("Pin模式");
		chart.setBtnSwitchToPinModeTooltext("Pin模式");
		chart.setBtnSwitchtoZoomModeTitle("缩放模式");
		chart.setBtnSwitchToZoomModeTooltext("缩放模式");
		chart.setBtnZoomOutTitle("上一步");
		chart.setBtnZoomOutTooltext("上一步");
		//////////////////////////////////////////////////

		// 横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		List<Dataset> dataset = new ArrayList<Dataset>();
		Categories categories = new Categories();
		List<Category> lst = new ArrayList<Category>();
		List<Data> datat = queryHostTimeLabelAndData(id, kpi, cycle_time,
				startDate, endDate, pointCount);
		for (Data d : datat) {
			Category c = new Category(d.getLabel());
			lst.add(c);
		}
		categories.setCategory(lst);
		categoriesLst.add(categories);
		// 使用率
		Dataset datasett = new Dataset();
		datasett.setSeriesname(name);
		datasett.setRenderas("Line");
		datasett.setAnchorRadius("0.2");
		datasett.setLinethickness("2");
		datasett.setData(datat);
		dataset.add(datasett);

//		if ("all".equals(pointCount)) {
//			chart.setNumvisibleplot(datat.size() + "");// 展示点数
//		}
		charts.setChart(chart);
		charts.setCategories(categoriesLst);
		charts.setDataset(dataset);
		return charts;
	}

	/**
	 * 
	 * @Title: queryAvg
	 * @Description: 获取指定指标在指定时间段的平均值
	 * @param
	 * @return Double
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-11-20 下午3:45:15
	 */
	@Override
	public Double queryHostCpuMenByAvg(String id, String kpi, String interval) {
		List<Data> datat = tbHostDao.queryPowerDayTimeLabelAndData(id, kpi,
				interval);
		Double d = 0d;
		if (datat != null && datat.size() > 0) {
			for (Data data : datat) {
				d = d + Double.parseDouble(data.getValue());
			}
			d = d / datat.size();
		}
		return d;
	}

	/**
	 * 
	 * @Title: queryDNChartDayData
	 * @Description: 查询磁盘和网络的监控数据
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 上午11:43:16
	 */
	@Override
	public FusionCharts queryHostDNChartDayData(String id, String kpi,
			String motionName, String cycle_time, String startDate,
			String endDate, String pointCount) {
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		// chart.setCaption("性能监控");//监控名称
		if (pointCount != null && !pointCount.equals("")
				&& !"0".equals(pointCount) && !"all".equals(pointCount)) {
			chart.setNumvisibleplot(pointCount);// 展示点数
		}
		// chart.setLabelstep("1");
		chart.setLabelDisplay("AUTO");
		// chart.setXaxisname("时间");
		// chart.setYaxismaxvalue("100");
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		/////////////////////////////////////////////////
		chart.setDivintervalhints("0, 10, 20, 30,40,50,60,80,100");
		chart.setPalette("3");
		chart.setBtnResetChartTitle("恢复");
		chart.setBtnResetChartTooltext("恢复");
		chart.setBtnSwitchToPinModeTitle("Pin模式");
		chart.setBtnSwitchToPinModeTooltext("Pin模式");
		chart.setBtnSwitchtoZoomModeTitle("缩放模式");
		chart.setBtnSwitchToZoomModeTooltext("缩放模式");
		chart.setBtnZoomOutTitle("上一步");
		chart.setBtnZoomOutTooltext("上一步");
		//////////////////////////////////////////////////

		List<Dataset> dataset = new ArrayList<Dataset>();
		List<Categories> categoriesLst = new ArrayList<Categories>();
		Categories categories = new Categories();
		List<Category> lst = new ArrayList<Category>();
		if ("DISK".equals(motionName)) {// 磁盘读写速率
			chart.setYaxisname("KB/秒");
			// 读速率
			Dataset datasett1 = new Dataset();
			datasett1.setSeriesname("读速度");
			datasett1.setRenderas("Line");
			datasett1.setAnchorRadius("0.2");
			datasett1.setLinethickness("2");
			kpi = "PM-H-01-010-13";// 读速率
			List<Data> datat1 = queryHostTimeLabelAndData(id, kpi, cycle_time,
					startDate, endDate, pointCount);
			datasett1.setData(datat1);
			dataset.add(datasett1);
			// 写速率
			Dataset datasett2 = new Dataset();
			datasett2.setSeriesname("写速度");
			datasett2.setRenderas("Line");
			datasett2.setAnchorRadius("0.2");
			datasett2.setLinethickness("2");
			kpi = "PM-H-01-010-14";// 写速率
			List<Data> datat2 = queryHostTimeLabelAndData(id, kpi, cycle_time,
					startDate, endDate, pointCount);
			datasett2.setData(datat2);
			dataset.add(datasett2);
			if ("all".equals(pointCount)) {
				chart.setNumvisibleplot(datat1.size() + "");// 展示点数
			}
			for (Data d : datat1) {
				Category c = new Category(d.getLabel());
				lst.add(c);
			}
		} else if ("NET".equals(motionName)) {// 网络
			chart.setYaxisname("kbps");
			// 接收速率
			Dataset datasett1 = new Dataset();
			datasett1.setSeriesname("接收速率");
			datasett1.setRenderas("Line");
			datasett1.setAnchorRadius("0.2");
			datasett1.setLinethickness("2");
			kpi = "PM-H-01-010-15";// 接收速率
			List<Data> datat1 = queryHostTimeLabelAndData(id, kpi, cycle_time,
					startDate, endDate, pointCount);
			datasett1.setData(datat1);
			dataset.add(datasett1);
			// 发送速率
			Dataset datasett2 = new Dataset();
			datasett2.setSeriesname("发送速率");
			datasett2.setRenderas("Line");
			datasett2.setAnchorRadius("0.2");
			datasett2.setLinethickness("2");
			kpi = "PM-H-01-010-16";// 发送速率
			List<Data> datat2 = queryHostTimeLabelAndData(id, kpi, cycle_time,
					startDate, endDate, pointCount);
			datasett2.setData(datat2);
			dataset.add(datasett2);
			if ("all".equals(pointCount)) {
				chart.setNumvisibleplot(datat1.size() + "");// 展示点数
			}
			for (Data d : datat1) {
				Category c = new Category(d.getLabel());
				lst.add(c);
			}
		}
		categories.setCategory(lst);
		categoriesLst.add(categories);
		charts.setChart(chart);
		charts.setCategories(categoriesLst);
		charts.setDataset(dataset);
		return charts;
	}

	/**
	 * 
	 * @Title: queryHostTimeLabelAndData
	 * @Description: 查询主机横坐标和监控数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 下午2:19:26
	 */
	public List<Data> queryHostTimeLabelAndData(String id, String kpi,
			String cycle_time, String startDate, String endDate,
			String pointCount) {
		// 横坐标
		List<Data> datat = new ArrayList<Data>();
		if ("oneHour".equalsIgnoreCase(cycle_time)) {
			datat = tbHostDao.queryPowerDayTimeLabelAndData(id, kpi, "60");
		} else if ("fiveHour".equalsIgnoreCase(cycle_time)) {
			datat = tbHostDao.queryPowerDayTimeLabelAndData(id, kpi, "300");
		} else if ("thisMonth".equalsIgnoreCase(cycle_time)) {
			datat = tbHostDao.queryPowerDayTimeLabelAndDataByCurrentMonth(id,
					kpi);
		} else if ("lastMonth".equalsIgnoreCase(cycle_time)) {
			datat = tbHostDao.queryPowerDayTimeLabelAndDataByLastMonth(id, kpi);
		} else if ("selfDate".equalsIgnoreCase(cycle_time)) {
			// 2013-14-11 09:36:28
			if (startDate != null && !"".equals(startDate) && endDate != null
					&& !"".equals(endDate)) {
				try {
					Date date1 = TimeformatCommon.TimeStringToDate(startDate,
							"yyyy-MM-dd hh:mm:ss");
					Date date2 = TimeformatCommon.TimeStringToDate(endDate,
							"yyyy-MM-dd hh:mm:ss");
					if ((date2.getTime() - date1.getTime()) > 0) {// 结束时间大于开始时间，可以执行查询
						Calendar c1 = Calendar.getInstance();
						c1.setTime(date1);
						Calendar c2 = Calendar.getInstance();
						c2.setTime(date2);
						String startTime = "";
						String endTime = "";
						String year = c1.get(Calendar.YEAR) + "";
						String month = "";
						String day = "";
						if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {// 目前不支持跨年查询，只可以查询一年之内的
							if (c1.get(Calendar.MONTH) == c2
									.get(Calendar.MONTH)) {// 查询范围在一个月内
								startTime = startDate;
								endTime = endDate;
								month = c2.get(Calendar.MONTH) + 1 + "";// 月份从0开始计算
								// 增加判断月份是一位，补全为两位月份
								month = month.length() == 1 ? "0" + month
										: month;
								if (c1.get(Calendar.DAY_OF_MONTH) == c2
										.get(Calendar.DAY_OF_MONTH)) {// 按照天表计算
									Date dateCurrend = new Date();
									Calendar c = Calendar.getInstance();
									c.setTime(dateCurrend);
									if (c.get(Calendar.DAY_OF_MONTH) == c2
											.get(Calendar.DAY_OF_MONTH)) {// 如果是当天的，需要查询当天的表
										datat = tbHostDao
												.queryPowerDayTimeLabelAndDataBetweenByCurrentDay(
														id, kpi, startTime,
														endTime);
									} else {// 查询每天的拼接表
										day = c2.get(Calendar.DAY_OF_MONTH)
												+ "";
										day = new DecimalFormat("00").format(
												Long.parseLong(day + ""))
												.toString();
										datat = tbHostDao
												.queryPowerDayTimeLabelAndDataBetween(
														id, kpi, year, month,
														day, startTime, endTime);
									}
								} else {// 按照月表计算
									datat = tbHostDao
											.queryPowerDayTimeLabelAndDataBetween(
													id, kpi, year, month,
													startTime, endTime);
								}
							} else {
								// 默认c1是小月
								int m1 = c1.get(Calendar.MONTH) + 1;
								int m2 = c2.get(Calendar.MONTH) + 1;
								startTime = startDate;
								endTime = endDate;
								for (int i = m1; i <= m2; i++) {
									List<Data> data1 = tbHostDao
											.queryPowerDayTimeLabelAndDataBetween(
													id, kpi, year, i + "",
													startTime, endTime);
									datat.addAll(data1);
								}
							}
						} else {
							/**
							 * 待开发---
							 */
							System.out.println("跨年查询");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("cycle_time参数传入错误.");
		}
		return datat;
	}

	@Override
	public FusionCharts queryChartFileSystemDayData(String id, String kpi,String startDate, String endDate) {
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		chart.setLabelDisplay("AUTO");
		chart.setYaxisname("容量");
		chart.setYaxismaxvalue("100");
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		chart.setFormatnumberscale("3");
		chart.setNumberSuffix("G");
		/////////////////////////////////////////////////
		chart.setDivintervalhints("0, 10, 20, 30,40,50,60,80,100");
		chart.setPalette("3");
		chart.setBtnResetChartTitle("恢复");
		chart.setBtnResetChartTooltext("恢复");
		chart.setBtnSwitchToPinModeTitle("Pin模式");
		chart.setBtnSwitchToPinModeTooltext("Pin模式");
		chart.setBtnSwitchtoZoomModeTitle("缩放模式");
		chart.setBtnSwitchToZoomModeTooltext("缩放模式");
		chart.setBtnZoomOutTitle("上一步");
		chart.setBtnZoomOutTooltext("上一步");
		//////////////////////////////////////////////////
		// 横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		Categories categories = new Categories();
		List<Category> lst = new ArrayList<Category>();
		List<Data> datat = queryHostFileSystemTimeLabelAndData(id, kpi,startDate, endDate);
		Map<String,List<Data>> dataMap = new LinkedHashMap<String,List<Data>>();
		String k = null;
		List<Data> dList = null;
		
		for(int ii = 0;ii<datat.size();ii++){
			Data data = datat.get(ii);
			
			
			//data.setDisplayValue(formatDiskCapacty(data.getValue()));
			k = data.getLabel3()+"_"+data.getLabel4();
			if(dataMap.containsKey(k)){
				dList = dataMap.get(k);
			}else{
				dList = new ArrayList<Data>();
				dataMap.put(k, dList);
			}
			if(dList.size() <= 2 || (dList.size() > 2 
					&& !dList.get(dList.size()-2).getValue().equals(data.getValue())
					&& dList.get(dList.size()-1).getValue().equals(data.getValue()))
					|| !dList.get(dList.size()-1).getValue().equals(data.getValue())
					){
				dList.add(data);
			}else if(dList.size() > 2 
					&& dList.get(dList.size()-2).getValue().equals(data.getValue())
					&& dList.get(dList.size()-1).getValue().equals(data.getValue())){
				dList.remove(dList.size()-1);
				dList.add(data);
			}
		}
		List<Dataset> dataSetList = new ArrayList<Dataset>();
		Iterator<String> iter =  dataMap.keySet().iterator();
		String seriesname = null;
		String kk = null,kpiName = null;
		while(iter.hasNext()){
			kk = iter.next();
			kpiName = "PM-H-01-010-17".equals(kk.substring(0, kk.indexOf("_")))?"已用量":"剩余量";
			seriesname = kk.substring(kk.lastIndexOf("_")+1);
			Dataset dataSet = new Dataset();
			dataSet.setSeriesname(seriesname+"("+kpiName+")");
			dataSet.setRenderas("Line");
			dataSet.setAnchorRadius("0.2");
			dataSet.setLinethickness("2");
			dataSet.setData(dataMap.get(kk));
			dataSetList.add(dataSet);
		}
		charts.setDataset(dataSetList);
		if(dataMap.get(kk) != null){
			for (Data d : dataMap.get(kk)) {
				Category c = new Category(d.getLabel());
				lst.add(c);
			}
		}
		categories.setCategory(lst);
		categoriesLst.add(categories);

		charts.setChart(chart);
		charts.setCategories(categoriesLst);
		return charts;
	}
	public List<Data> queryHostFileSystemTimeLabelAndData(String id, String kpi, String startDate, String endDate){
		List<Data> datat = new ArrayList<Data>();
		Map<String,String> param = new HashMap<String,String>();
		// 2013-14-11 09:36:28
		if (startDate != null && !"".equals(startDate) && endDate != null
				&& !"".equals(endDate)) {
			try {
				Date date1 = TimeformatCommon.TimeStringToDate(startDate,
						"yyyy-MM-dd hh:mm:ss");
				Date date2 = TimeformatCommon.TimeStringToDate(endDate,
						"yyyy-MM-dd hh:mm:ss");
				if ((date2.getTime() - date1.getTime()) > 0) {// 结束时间大于开始时间，可以执行查询
					Calendar c1 = Calendar.getInstance();
					c1.setTime(date1);
					Calendar c2 = Calendar.getInstance();
					c2.setTime(date2);
					String startTime = "",endTime = "";
					String year = c1.get(Calendar.YEAR) + "";
					String month = "",day = "";
					if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {// 目前不支持跨年查询，只可以查询一年之内的
						if (c1.get(Calendar.MONTH) == c2
								.get(Calendar.MONTH)) {// 查询范围在一个月内
							startTime = startDate;
							endTime = endDate;
							month = c2.get(Calendar.MONTH) + 1 + "";// 月份从0开始计算
							// 增加判断月份是一位，补全为两位月份
							month = month.length() == 1 ? "0" + month : month;
							if (c1.get(Calendar.DAY_OF_MONTH) == c2
									.get(Calendar.DAY_OF_MONTH)) {// 按照天表计算
								Date dateCurrend = new Date();
								Calendar c = Calendar.getInstance();
								c.setTime(dateCurrend);
								if (c.get(Calendar.DAY_OF_MONTH) == c2
										.get(Calendar.DAY_OF_MONTH)) {// 如果是当天的，需要查询当天的表
									param = tbHostDao.convertMap(id, kpi, null, null, null,
											null,startTime, endTime,"day");
									datat = tbHostDao.queryFileSystemDayTimeLabelAndData(param);
								} else {// 查询每天的拼接表
									day = c2.get(Calendar.DAY_OF_MONTH) + "";
									day = new DecimalFormat("00").format(
											Long.parseLong(day + ""))
											.toString();
									tbHostDao.convertMap(id, kpi, null, year, month, day,
											startTime, endTime, "userDefinedMonth");
									datat = tbHostDao.queryFileSystemDayTimeLabelAndData(param);
								}
							} else {// 按照月表计算
								
								param = tbHostDao.convertMap(id, kpi, null, year, month, null,
										startTime, endTime, "userDefined");
								datat = tbHostDao.queryFileSystemDayTimeLabelAndData(param);
							}
						} else {
							// 默认c1是小月
							int m1 = c1.get(Calendar.MONTH) + 1;
							int m2 = c2.get(Calendar.MONTH) + 1;
							startTime = startDate;
							endTime = endDate;
							for (int i = m1; i <= m2; i++) {
								param = tbHostDao.convertMap(id, kpi, null, year,  i + "", null,
										startTime, endTime, "userDefined");
								List<Data> data1 = tbHostDao.queryFileSystemDayTimeLabelAndData(param);;
								datat.addAll(data1);
							}
						}
					} else {
						/**
						 * 待开发---
						 */
						System.out.println("跨年查询");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return datat;
	}

	/**
	 *
	 * @see com.sitech.basd.sxcloud.cloud.service.reporthost.ReportHostService#queryHostDiskNetMonitor(java.lang.String, java.lang.String)
	 */
	@Override
	public MonitorObj queryHostDiskNetMonitor(String id, String interval) {
		MonitorObj monitorObj = new MonitorObj();
		//获取近一小时磁盘读速度PM-H-01-010-13  
		Data dataDisRead = tbHostDao.queryHostDiskNetMonitor(id,"PM-H-01-010-13",interval);
		monitorObj.setDiskReadAvg(this.parseDouble(dataDisRead.getLabel()));
		monitorObj.setDiskReadMax(this.parseDouble(dataDisRead.getLabel2()));
		//获取近一小时磁盘写速度PM-H-01-010-14 
		Data dataDisWrite = tbHostDao.queryHostDiskNetMonitor(id,"PM-H-01-010-14",interval);
		monitorObj.setDiskWriteAvg(this.parseDouble(dataDisWrite.getLabel()));
		monitorObj.setDiskWriteMax(this.parseDouble(dataDisWrite.getLabel2()));
		//获取近一小时网络读速率PM-H-01-010-15 
		Data dataNetRead  = tbHostDao.queryHostDiskNetMonitor(id,"PM-H-01-010-15",interval);
		monitorObj.setNetReadAvg(this.parseDouble(dataNetRead.getLabel()));
		monitorObj.setNetReadMax(this.parseDouble(dataNetRead.getLabel2()));
		//获取近一小时网络写速率PM-H-01-010-16 
		Data dataNetWrite = tbHostDao.queryHostDiskNetMonitor(id,"PM-H-01-010-16",interval);
		monitorObj.setNetWriteAvg(this.parseDouble(dataNetWrite.getLabel()));
		monitorObj.setNetWriteMax(this.parseDouble(dataNetWrite.getLabel2()));
		return monitorObj;
	}
	private Double parseDouble(String val){
		if(val==null){
			return 0.0;
		}else{
			return Double.parseDouble(val);
		}
	}
}
