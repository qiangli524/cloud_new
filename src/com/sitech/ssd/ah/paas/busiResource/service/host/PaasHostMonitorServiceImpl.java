package com.sitech.ssd.ah.paas.busiResource.service.host;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.cloud3.domain.charts.MonitorObj;
import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.ssd.ah.paas.busiResource.dao.host.PaasHostMonitorDao;
import com.sitech.utils.date.TimeformatCommon;

/**
 * <p>
 * Title: PaasHostMonitorServiceImpl
 * </p>
 * <p>
 * Description:paas主机监控服务实现类
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
 * @createtime 2014-7-29 下午4:39:10
 * 
 */
@SuppressWarnings("unchecked")
@Service("paasHostMonitorService")
public class PaasHostMonitorServiceImpl extends BaseService implements PaasHostMonitorService {

	@Autowired
	PaasHostMonitorDao paasHostMonitorDao;

	/**
	 * 
	 * @Title: queryHostCpuMenByAvg
	 * @Description: 获取主机指定指标在指定时间段的平均值
	 * @param
	 * @return Double
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:19:28
	 */
	@Override
	public Double queryHostCpuMenByAvg(String id, String kpi, String interval, String eq_type) {
		List<Data> datat = paasHostMonitorDao.queryPowerDayTimeLabelAndData(id, kpi, interval,
				eq_type);
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
	 * @Title: queryHostDiskNetMonitor
	 * @Description: 查询主机某一时间段的磁盘和网络的平均读写速度和最大读写速度
	 * @param
	 * @return MonitorObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:19:40
	 */
	@Override
	public MonitorObj queryHostDiskNetMonitor(String id, String interval, String eq_type) {
		MonitorObj monitorObj = new MonitorObj();
		// 获取近一小时磁盘读速度PM-H-01-010-13
		Data dataDisRead = paasHostMonitorDao.queryHostDiskNetMonitor(id, "PM-H-01-010-13",
				interval, eq_type);
		monitorObj.setDiskReadAvg(this.parseDouble(dataDisRead.getLabel()));
		monitorObj.setDiskReadMax(this.parseDouble(dataDisRead.getLabel2()));
		// 获取近一小时磁盘写速度PM-H-01-010-14
		Data dataDisWrite = paasHostMonitorDao.queryHostDiskNetMonitor(id, "PM-H-01-010-14",
				interval, eq_type);
		monitorObj.setDiskWriteAvg(this.parseDouble(dataDisWrite.getLabel()));
		monitorObj.setDiskWriteMax(this.parseDouble(dataDisWrite.getLabel2()));
		// 获取近一小时网络读速率PM-H-01-010-15
		Data dataNetRead = paasHostMonitorDao.queryHostDiskNetMonitor(id, "PM-H-01-010-15",
				interval, eq_type);
		monitorObj.setNetReadAvg(this.parseDouble(dataNetRead.getLabel()));
		monitorObj.setNetReadMax(this.parseDouble(dataNetRead.getLabel2()));
		// 获取近一小时网络写速率PM-H-01-010-16
		Data dataNetWrite = paasHostMonitorDao.queryHostDiskNetMonitor(id, "PM-H-01-010-16",
				interval, eq_type);
		monitorObj.setNetWriteAvg(this.parseDouble(dataNetWrite.getLabel()));
		monitorObj.setNetWriteMax(this.parseDouble(dataNetWrite.getLabel2()));
		return monitorObj;
	}

	/**
	 * 
	 * @Title: queryChartDayData
	 * @Description: CPU内存监控
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:19:47
	 */
	@Override
	public FusionCharts queryChartDayData(String id, String kpi, String name, String cycle_time,
			String startDate, String endDate, String pointCount, String eq_type) {
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		// chart.setCaption("性能监控");//监控名称
		// if (pointCount != null && !pointCount.equals("")
		// && !"0".equals(pointCount) && !"all".equals(pointCount)) {
		// chart.setNumvisibleplot(pointCount);// 展示点数
		// }
		// chart.setLabelstep("1");
		chart.setLabelDisplay("AUTO");
		// chart.setXaxisname("时间");
		chart.setYaxisname("%");
		chart.setYaxismaxvalue("100");
		// chart.setYaxismaxvalue("100");
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		// ///////////////////////////////////////////////
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
		// ////////////////////////////////////////////////

		// 横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		List<Dataset> dataset = new ArrayList<Dataset>();
		Categories categories = new Categories();
		List<Category> lst = new ArrayList<Category>();
		List<Data> datat = queryHostTimeLabelAndData(id, kpi, cycle_time, startDate, endDate,
				pointCount, eq_type);
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

		// if ("all".equals(pointCount)) {
		// chart.setNumvisibleplot(datat.size() + "");// 展示点数
		// }
		charts.setChart(chart);
		charts.setCategories(categoriesLst);
		charts.setDataset(dataset);
		return charts;
	}

	/**
	 * 
	 * @Title: queryHostDNChartDayData
	 * @Description: 查询主机磁盘和网络的监控数据
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 上午11:43:16
	 */
	@Override
	public FusionCharts queryHostDNChartDayData(String id, String kpi, String motionName,
			String cycle_time, String startDate, String endDate, String pointCount, String eq_type) {
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		// chart.setCaption("性能监控");//监控名称
		if (pointCount != null && !pointCount.equals("") && !"0".equals(pointCount)
				&& !"all".equals(pointCount)) {
			chart.setNumvisibleplot(pointCount);// 展示点数
		}
		// chart.setLabelstep("1");
		chart.setLabelDisplay("AUTO");
		// chart.setXaxisname("时间");
		// chart.setYaxismaxvalue("100");
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		// ///////////////////////////////////////////////
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
		// ////////////////////////////////////////////////

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
			List<Data> datat1 = queryHostTimeLabelAndData(id, kpi, cycle_time, startDate, endDate,
					pointCount, eq_type);
			datasett1.setData(datat1);
			dataset.add(datasett1);
			// 写速率
			Dataset datasett2 = new Dataset();
			datasett2.setSeriesname("写速度");
			datasett2.setRenderas("Line");
			datasett2.setAnchorRadius("0.2");
			datasett2.setLinethickness("2");
			kpi = "PM-H-01-010-14";// 写速率
			List<Data> datat2 = queryHostTimeLabelAndData(id, kpi, cycle_time, startDate, endDate,
					pointCount, eq_type);
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
			List<Data> datat1 = queryHostTimeLabelAndData(id, kpi, cycle_time, startDate, endDate,
					pointCount, eq_type);
			datasett1.setData(datat1);
			dataset.add(datasett1);
			// 发送速率
			Dataset datasett2 = new Dataset();
			datasett2.setSeriesname("发送速率");
			datasett2.setRenderas("Line");
			datasett2.setAnchorRadius("0.2");
			datasett2.setLinethickness("2");
			kpi = "PM-H-01-010-16";// 发送速率
			List<Data> datat2 = queryHostTimeLabelAndData(id, kpi, cycle_time, startDate, endDate,
					pointCount, eq_type);
			datasett2.setData(datat2);
			dataset.add(datasett2);
			if ("all".equals(pointCount)) {
				chart.setNumvisibleplot(datat1.size() + "");// 展示点数
			}
			for (Data d : datat1) {
				Category c = new Category(d.getLabel());
				lst.add(c);
			}
		} else if ("File".equals(motionName)) {// 网络
			chart.setYaxisname("容量");
			// 已用量
			Dataset datasett1 = new Dataset();
			datasett1.setSeriesname("已用量");
			datasett1.setRenderas("Line");
			datasett1.setAnchorRadius("0.2");
			datasett1.setLinethickness("2");
			kpi = "PM-H-01-010-17";//
			List<Data> datat1 = queryHostFileSystemTimeLabelAndData(id, kpi, cycle_time, startDate,
					endDate, eq_type);
			datasett1.setData(datat1);
			dataset.add(datasett1);
			// 剩余量
			Dataset datasett2 = new Dataset();
			datasett2.setSeriesname("剩余量");
			datasett2.setRenderas("Line");
			datasett2.setAnchorRadius("0.2");
			datasett2.setLinethickness("2");
			kpi = "PM-H-01-010-18";// 发送速率
			List<Data> datat2 = queryHostFileSystemTimeLabelAndData(id, kpi, cycle_time, startDate,
					endDate, eq_type);
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
	public List<Data> queryHostTimeLabelAndData(String id, String kpi, String cycle_time,
			String startDate, String endDate, String pointCount, String eq_type) {
		// 横坐标
		List<Data> datat = new ArrayList<Data>();
		if ("oneHour".equalsIgnoreCase(cycle_time)) {
			datat = paasHostMonitorDao.queryPowerDayTimeLabelAndData(id, kpi, "60", eq_type);
		} else if ("fiveHour".equalsIgnoreCase(cycle_time)) {
			datat = paasHostMonitorDao.queryPowerDayTimeLabelAndData(id, kpi, "300", eq_type);
		} else if ("thisMonth".equalsIgnoreCase(cycle_time)) {
			datat = paasHostMonitorDao
					.queryPowerDayTimeLabelAndDataByCurrentMonth(id, kpi, eq_type);
		} else if ("lastMonth".equalsIgnoreCase(cycle_time)) {
			datat = paasHostMonitorDao.queryPowerDayTimeLabelAndDataByLastMonth(id, kpi, eq_type);
		} else if ("selfDate".equalsIgnoreCase(cycle_time)) {
			// 2013-14-11 09:36:28
			if (startDate != null && !"".equals(startDate) && endDate != null
					&& !"".equals(endDate)) {
				try {
					Date date1 = TimeformatCommon
							.TimeStringToDate(startDate, "yyyy-MM-dd hh:mm:ss");
					Date date2 = TimeformatCommon.TimeStringToDate(endDate, "yyyy-MM-dd hh:mm:ss");
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
							if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {// 查询范围在一个月内
								startTime = startDate;
								endTime = endDate;
								month = c2.get(Calendar.MONTH) + 1 + "";// 月份从0开始计算
								// 增加判断月份是一位，补全为两位月份
								month = month.length() == 1 ? "0" + month : month;
								if (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {// 按照天表计算
									Date dateCurrend = new Date();
									Calendar c = Calendar.getInstance();
									c.setTime(dateCurrend);
									if (c.get(Calendar.DAY_OF_MONTH) == c2
											.get(Calendar.DAY_OF_MONTH)) {// 如果是当天的，需要查询当天的表
										datat = paasHostMonitorDao
												.queryPowerDayTimeLabelAndDataBetweenByCurrentDay(
														id, kpi, startTime, endTime, eq_type);
									} else {// 查询每天的拼接表
										day = c2.get(Calendar.DAY_OF_MONTH) + "";
										day = new DecimalFormat("00").format(
												Long.parseLong(day + "")).toString();
										datat = paasHostMonitorDao
												.queryPowerDayTimeLabelAndDataBetween(id, kpi,
														year, month, day, startTime, endTime);
									}
								} else {// 按照月表计算
									datat = paasHostMonitorDao
											.queryPowerDayTimeLabelAndDataBetween(id, kpi, year,
													month, startTime, endTime, eq_type);
								}
							} else {
								// 默认c1是小月
								int m1 = c1.get(Calendar.MONTH) + 1;
								int m2 = c2.get(Calendar.MONTH) + 1;
								startTime = startDate;
								endTime = endDate;
								for (int i = m1; i <= m2; i++) {
									List<Data> data1 = paasHostMonitorDao
											.queryPowerDayTimeLabelAndDataBetween(id, kpi, year, i
													+ "", startTime, endTime, eq_type);
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

	/**
	 * 
	 * @Title: queryHostFileSystemTimeLabelAndData
	 * @Description: 文件系统数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:00:34
	 */
	public List<Data> queryHostFileSystemTimeLabelAndData(String id, String kpi, String cycle_time,
			String startDate, String endDate, String eq_type) {
		List<Data> datat = new ArrayList<Data>();
		Map<String, String> param = new HashMap<String, String>();
		if ("oneHour".equalsIgnoreCase(cycle_time)) {
			param = paasHostMonitorDao.convertMap(id, kpi, "60", null, null, null, null, null,
					"day", eq_type);
			datat = paasHostMonitorDao.queryFileSystemDayTimeLabelAndDataByDate(param);
		} else if ("fiveHour".equalsIgnoreCase(cycle_time)) {
			param = paasHostMonitorDao.convertMap(id, kpi, "300", null, null, null, null, null,
					"day", eq_type);
			datat = paasHostMonitorDao.queryFileSystemDayTimeLabelAndDataByDate(param);
		} else if ("thisMonth".equalsIgnoreCase(cycle_time)) {
			param = paasHostMonitorDao.convertMap(id, kpi, null, null, null, null, null, null,
					"currentMonth", eq_type);
			datat = paasHostMonitorDao.queryFileSystemDayTimeLabelAndDataByMonth(param);
		} else if ("lastMonth".equalsIgnoreCase(cycle_time)) {
			param = paasHostMonitorDao.convertMap(id, kpi, null, null, null, null, null, null,
					"lastMonth", eq_type);
			datat = paasHostMonitorDao.queryFileSystemDayTimeLabelAndDataByMonth(param);
		} else if ("selfDate".equalsIgnoreCase(cycle_time)) {
			// 2013-14-11 09:36:28
			if (startDate != null && !"".equals(startDate) && endDate != null
					&& !"".equals(endDate)) {
				try {
					Date date1 = TimeformatCommon
							.TimeStringToDate(startDate, "yyyy-MM-dd hh:mm:ss");
					Date date2 = TimeformatCommon.TimeStringToDate(endDate, "yyyy-MM-dd hh:mm:ss");
					if ((date2.getTime() - date1.getTime()) > 0) {// 结束时间大于开始时间，可以执行查询
						Calendar c1 = Calendar.getInstance();
						c1.setTime(date1);
						Calendar c2 = Calendar.getInstance();
						c2.setTime(date2);
						String startTime = "", endTime = "";
						String year = c1.get(Calendar.YEAR) + "";
						String month = "", day = "";
						if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {// 目前不支持跨年查询，只可以查询一年之内的
							if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {// 查询范围在一个月内
								startTime = startDate;
								endTime = endDate;
								month = c2.get(Calendar.MONTH) + 1 + "";// 月份从0开始计算
								// 增加判断月份是一位，补全为两位月份
								month = month.length() == 1 ? "0" + month : month;
								if (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {// 按照天表计算
									Date dateCurrend = new Date();
									Calendar c = Calendar.getInstance();
									c.setTime(dateCurrend);
									if (c.get(Calendar.DAY_OF_MONTH) == c2
											.get(Calendar.DAY_OF_MONTH)) {// 如果是当天的，需要查询当天的表
										param = paasHostMonitorDao.convertMap(id, kpi, null, null,
												null, null, startTime, endTime, "day", eq_type);
										datat = paasHostMonitorDao
												.queryFileSystemDayTimeLabelAndData(param);
									} else {// 查询每天的拼接表
										day = c2.get(Calendar.DAY_OF_MONTH) + "";
										day = new DecimalFormat("00").format(
												Long.parseLong(day + "")).toString();
										paasHostMonitorDao.convertMap(id, kpi, null, year, month,
												day, startTime, endTime, "userDefinedMonth",
												eq_type);
										datat = paasHostMonitorDao
												.queryFileSystemDayTimeLabelAndData(param);
									}
								} else {// 按照月表计算
									param = paasHostMonitorDao
											.convertMap(id, kpi, null, year, month, null,
													startTime, endTime, "userDefined", eq_type);
									datat = paasHostMonitorDao
											.queryFileSystemDayTimeLabelAndData(param);
								}
							} else {
								// 默认c1是小月
								int m1 = c1.get(Calendar.MONTH) + 1;
								int m2 = c2.get(Calendar.MONTH) + 1;
								startTime = startDate;
								endTime = endDate;
								for (int i = m1; i <= m2; i++) {
									String month_i = i + "";
									month_i = month_i.length() == 1 ? "0" + month_i : month_i;
									param = paasHostMonitorDao.convertMap(id, kpi, null, year,
											month_i + "", null, startTime, endTime, "userDefined",
											eq_type);
									List<Data> data1 = paasHostMonitorDao
											.queryFileSystemDayTimeLabelAndData(param);
									;
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
		}
		return datat;
	}

	private Double parseDouble(String val) {
		if (val == null) {
			return 0.0;
		} else {
			return Double.parseDouble(val);
		}
	}

	/**
	 * 
	 * @Title: queryFileSystemUrl
	 * @Description: 查询文件系统路径
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午11:39:40
	 */
	@Override
	public List queryFileSystemUrl(String id, String kpi, String eq_type) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("KPI_ID", kpi);
		map.put("EXT_EQ_ID", id);
		map.put("EQ_TYPE", "2");
		List<Data> resultList = paasHostMonitorDao.queryFileSystemUrl(map);
		List<String> urlList = new ArrayList<String>();
		for (Data data : resultList) {
			String eq_id = data.getLabel();
			String url = null;
			if (eq_id.contains("/")) {
				url = eq_id.substring(eq_id.lastIndexOf("_") + 1, eq_id.length());
			} else {
				url = "all";
			}
			urlList.add(url);
		}
		return urlList;
	}

	@Override
	public FusionCharts querySwapData_f(String id, String kpi, String name, String cycle_time,
			String startDate, String endDate, String pointCount, String eq_type) {
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		// chart.setCaption("性能监控");//监控名称
		// if (pointCount != null && !pointCount.equals("")
		// && !"0".equals(pointCount) && !"all".equals(pointCount)) {
		// chart.setNumvisibleplot(pointCount);// 展示点数
		// }
		// chart.setLabelstep("1");
		chart.setLabelDisplay("AUTO");
		// chart.setXaxisname("时间");
		chart.setYaxisname("%");
		chart.setYaxismaxvalue("100");
		// chart.setYaxismaxvalue("100");
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		// ///////////////////////////////////////////////
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
		// ////////////////////////////////////////////////

		// 横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		List<Dataset> dataset = new ArrayList<Dataset>();
		Categories categories = new Categories();
		List<Category> lst = new ArrayList<Category>();
		List<Data> datat = queryHostTimeLabelAndData(id, kpi, cycle_time, startDate, endDate,
				pointCount, eq_type);
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

		// if ("all".equals(pointCount)) {
		// chart.setNumvisibleplot(datat.size() + "");// 展示点数
		// }
		charts.setChart(chart);
		charts.setCategories(categoriesLst);
		charts.setDataset(dataset);
		return charts;
	}
}
