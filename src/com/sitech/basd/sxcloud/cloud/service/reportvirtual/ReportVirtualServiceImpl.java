package com.sitech.basd.sxcloud.cloud.service.reportvirtual;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.cloud3.domain.charts.DataPoint;
import com.sitech.basd.cloud3.domain.charts.MonitorObj;
import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.fusioncharts.vo.Trendline;
import com.sitech.basd.fusioncharts.vo.Trendlines;
import com.sitech.basd.sxcloud.cloud.dao.reportvirtual.TbHyDao;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.reportvirtual.TbHyObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.util.excel.ExcelUtils;
import com.sitech.utils.date.TimeformatCommon;

public class ReportVirtualServiceImpl extends BaseService implements
		ReportVirtualService {

	/**
	 * @Title:查询当天的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHyForToday(TbHyObj obj) {
		return tbHyDao.queryTbHyForToday(obj);
	}

	/**
	 * @Title:查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHyForWeek(TbHyObj obj) {
		return tbHyDao.queryTbHyForWeek(obj);
	}

	/**
	 * @Title:跨表查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryUnionTbHyForWeek(TbHyObj obj) {
		return tbHyDao.queryUnionTbHyForWeek(obj);
	}

	/**
	 * @Title:查询上周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHyForLastWeek(TbHyObj obj) {
		return tbHyDao.queryTbHyForLastWeek(obj);
	}

	/**
	 * @Title:跨表查询上周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryUnionTbHyForLastWeek(TbHyObj obj) {
		return tbHyDao.queryUnionTbHyForLastWeek(obj);
	}

	/**
	 * @Title:查询当月的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHyForMonth(TbHyObj obj) {
		return tbHyDao.queryTbHyForMonth(obj);
	}

	/**
	 * @Title:查询上月的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@Override
	public List queryTbHyForLastMonth(TbHyObj obj) {
		return tbHyDao.queryTbHyForLastMonth(obj);
	}

	/**
	 * @Title: 查询已经监控的虚拟机的个数
	 * @Copyright: Copyright (c) 20120406
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@Override
	public int queryMonitorVirtualCount(TbCloud2VirtualInfoObj obj) {
		return tbHyDao.queryMonitorVirtualCount(obj);
	}

	/**
	 * @Title: 查询已经监控的虚拟机的个数
	 * @Copyright: Copyright (c) 20120406
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@Override
	public int queryAllVirtualCount(TbCloud2VirtualInfoObj obj) {
		return tbHyDao.queryAllVirtualCount(obj);
	}

	/**
	 * 
	 * @Title: queryVirtualData
	 * @Description: 查询虚拟机监控数据.network is wrong,update by duangh
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 25, 2013 1:41:39 PM
	 */
	@Override
	@Deprecated
	public List queryVirtualMonitorData(Map map) {
		return tbHyDao.queryVirtualMonitorData(map);
	}

	/**
	 * 
	 * @Title: queryHyData
	 * @Description:查询虚拟机监控数据，highCharts展示
	 * @author duangh
	 * @date Jul 9, 2013 8:58:32 PM
	 * @return
	 */
	@Override
	public List<DataPoint> queryHyData(Map map) {
		List<DataPoint> list = null;
		list = tbHyDao.queryHyData(map);
		return list;
	}

	private TbHyDao tbHyDao;
	@Autowired
	private VMHostDao vmHostDao;
	

	public void setTbHyDao(TbHyDao tbHyDao) {
		this.tbHyDao = tbHyDao;
	}

	@Override
	public List queryVmMonitorDataByWeek(Map map) {
		return tbHyDao.queryVmMonitorDataByWeek(map);
	}

	@Override
	public List queryVmMonitorDataByMonth(Map map) {
		return tbHyDao.queryVmMonitorDataByMonth(map);
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
			String pointCount,String startUsage,String endUsage) {
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
		List<Data> datat = queryHyTimeLabelAndData(id, kpi, cycle_time,
				startDate, endDate, pointCount,startUsage,endUsage);
		Double avgD = 0d;
		for (Data d : datat) {
			Category c = new Category(d.getLabel());
			lst.add(c);
			avgD= avgD + Double.parseDouble(d.getValue());
		}
		if(datat != null && datat.size() > 0){
			avgD = avgD/datat.size();
			NumberFormat nf = NumberFormat.getIntegerInstance();
			Trendlines trendlines = new Trendlines();
			List<Trendline> trendLineList = new ArrayList<Trendline>(1);
			Trendline trendLine = new Trendline();
			trendLine.setStartValue(Integer.parseInt(nf.format(avgD)));
			trendLine.setDisplayValue("平均值:"+nf.format(avgD));
			trendLine.setColor("009933");
			trendLineList.add(trendLine);
			trendlines.setLine(trendLineList);
			charts.setTrendlines(trendlines);
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
	 * @Title: hyMonitorPerformationDataExport
	 * @Description: 虚拟机性能报表导出
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-18 下午3:29:38
	 */
	public void hyMonitorPerformationDataExport(String id, String kpi, String name,
			String cycle_time, String startDate, String endDate,
			String pointCount,OutputStream os,String startUsage,String endUsage) {
		String connectId = id.substring(0, id.indexOf("_"));
		String vh_uuid = id.substring(id.indexOf("_")+1);
		List<Data> datat = queryHyTimeLabelAndData(id, kpi, cycle_time,
				startDate, endDate, pointCount,startUsage, endUsage);
		//根据虚拟机id，获取虚拟机名称
		VMHostObj vmHostObjQuery = new VMHostObj();
		vmHostObjQuery.setConnectId(connectId);
		vmHostObjQuery.setVH_UUID(vh_uuid);
		VMHostObj vmHostObj = vmHostDao.queryByObj(vmHostObjQuery);
		List<String[]> rowsData = new ArrayList<String[]>(); 
		for(Data data : datat){
			rowsData.add(new String[]{vmHostObj.getVH_NAME(),data.getLabel2(),data.getValue()});
		}
		//获取采集指标数据
		String t1 = "PM-V-01-010-11".equals(kpi)?"CPU利用率":"内存利用率";
		String[] title = new String[]{"虚拟机名称","采集时间",t1};
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(t1).append("明细表").append("(时间区间：").append(startDate).append("~").append(endDate).append(")");
			ExcelUtils.writeDataExcel("sheet",sb.toString(),title,rowsData,os);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public Double queryVmCpuMenByAvg(String hyId, String kpi, String interval) {
		List<Data> datat = tbHyDao.queryPowerDayTimeLabelAndData(hyId, kpi,
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
	 * @Title: queryHyDNChartDayData
	 * @Description: 查询虚拟机磁盘和网络的监控数据
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 下午4:20:04
	 */
	@Override
	public FusionCharts queryHyDNChartDayData(String id, String kpi,
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
			kpi = "PM-V-01-010-13";// 读速率
			List<Data> datat1 = queryHyTimeLabelAndData(id, kpi, cycle_time,
					startDate, endDate, pointCount,null,null);
			datasett1.setData(datat1);
			dataset.add(datasett1);
			// 写速率
			Dataset datasett2 = new Dataset();
			datasett2.setSeriesname("写速度");
			datasett2.setRenderas("Line");
			datasett2.setAnchorRadius("0.2");
			datasett2.setLinethickness("2");
			kpi = "PM-V-01-010-14";// 写速率
			List<Data> datat2 = queryHyTimeLabelAndData(id, kpi, cycle_time,
					startDate, endDate, pointCount,null,null);
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
			kpi = "PM-V-01-010-15";// 接收速率
			List<Data> datat1 = queryHyTimeLabelAndData(id, kpi, cycle_time,
					startDate, endDate, pointCount,null,null);
			datasett1.setData(datat1);
			dataset.add(datasett1);
			// 发送速率
			Dataset datasett2 = new Dataset();
			datasett2.setSeriesname("发送速率");
			datasett2.setRenderas("Line");
			datasett2.setAnchorRadius("0.2");
			datasett2.setLinethickness("2");
			kpi = "PM-V-01-010-16";// 发送速率
			List<Data> datat2 = queryHyTimeLabelAndData(id, kpi, cycle_time,
					startDate, endDate, pointCount,null,null);
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
	 * @Title: queryHyTimeLabelAndData
	 * @Description: 查询虚拟机横坐标和监控数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 下午4:17:13
	 */
	public List<Data> queryHyTimeLabelAndData(String id, String kpi,
			String cycle_time, String startDate, String endDate,
			String pointCount,String startUsage,String endUsage) {
		List<Data> datat = new ArrayList<Data>();
		if ("oneHour".equalsIgnoreCase(cycle_time)) {
			datat = tbHyDao.queryPowerDayTimeLabelAndData(id, kpi, "60");
		} else if ("fiveHour".equalsIgnoreCase(cycle_time)) {
			datat = tbHyDao.queryPowerDayTimeLabelAndData(id, kpi, "300");
		} else if ("thisMonth".equalsIgnoreCase(cycle_time)) {
			datat = tbHyDao
					.queryPowerDayTimeLabelAndDataByCurrentMonth(id, kpi);
		} else if ("lastMonth".equalsIgnoreCase(cycle_time)) {
			datat = tbHyDao.queryPowerDayTimeLabelAndDataByLastMonth(id, kpi);
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
								//增加判断月份是一位，补全为两位月份
								month = month.length() == 1?"0"+month:month;
								if (c1.get(Calendar.DAY_OF_MONTH) == c2
										.get(Calendar.DAY_OF_MONTH)) {// 按照天表计算
									Date dateCurrend = new Date();
									Calendar c = Calendar.getInstance();
									c.setTime(dateCurrend);
									if (c.get(Calendar.DAY_OF_MONTH) == c2
											.get(Calendar.DAY_OF_MONTH)) {// 如果是当天的，需要查询当天的表
										datat = tbHyDao
												.queryPowerDayTimeLabelAndDataBetweenByCurrentDay(
														id, kpi, startTime,
														endTime,startUsage,endUsage);
									} else {// 查询每天的拼接表
										day = c2.get(Calendar.DAY_OF_MONTH)
												+ "";
										day = new DecimalFormat("00").format(
												Long.parseLong(day + ""))
												.toString();
										datat = tbHyDao
												.queryPowerDayTimeLabelAndDataBetween(
														id, kpi, year, month,
														day, startTime, endTime,startUsage,endUsage);
									}
								} else {// 按照月表计算
									datat = tbHyDao
											.queryPowerDayTimeLabelAndDataBetween(
													id, kpi, year, month,
													startTime, endTime,startUsage,endUsage);
								}
							} else {
								// 默认c1是小月
								int m1 = c1.get(Calendar.MONTH) + 1;
								int m2 = c2.get(Calendar.MONTH) + 1;
								startTime = startDate;
								endTime = endDate;
								for (int i = m1; i <= m2; i++) {
									List<Data> data1 = tbHyDao
											.queryPowerDayTimeLabelAndDataBetween(
													id, kpi, year, i + "",
													startTime, endTime,startUsage,endUsage);
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
	
	
	public FusionCharts queryChartFileSystemDayData(String id, String kpi,String kpi2,String startDate, String endDate) {
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		chart.setLabelDisplay("AUTO");
		chart.setYaxisname("容量");
		chart.setYaxismaxvalue("100");
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		chart.setDefaultNumberScale("M");
		chart.setFormatnumberscale("1");
		chart.setFormatNumber("1");
		chart.setFormatNumberScale("1");
		chart.setNumberScaleValue("1024,1024");
		chart.setNumberScaleUnit("G,T");
		
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
		List<Data> datat = queryHyFileSystemTimeLabelAndData(id, kpi,kpi2,startDate, endDate);
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
			seriesname = kk.lastIndexOf("_") == (kk.length()-1) || (kk.split("_").length == 3)?"总量":kk.substring(kk.lastIndexOf("_")+1);
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
	/**
	 * @Title: formatDiskCapacty
	 * @Description: M
	 * @param
	 * @return String
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-26 上午10:36:17
	 */
	public String formatDiskCapacty(String val){
		if(StringUtils.isNotEmpty(val)){
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(3);
			Double d = Double.parseDouble(val);
			if(d < 1024){
				return val+"M";
			}else if(d > 1024 && d < 1024*1024){
				return nf.format(d/1024.0)+"G";
			}else{
				return nf.format(d/1024.0/1024.0)+"T";
			}
		}
		return "";
	}
	
	public List<Data> queryHyFileSystemTimeLabelAndData(String id, String kpi,String kpi2, String startDate, String endDate){
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
							//增加判断月份是一位，补全为两位月份
							month = month.length() == 1?"0"+month:month;
							if (c1.get(Calendar.DAY_OF_MONTH) == c2
									.get(Calendar.DAY_OF_MONTH)) {// 按照天表计算
								Date dateCurrend = new Date();
								Calendar c = Calendar.getInstance();
								c.setTime(dateCurrend);
								if (c.get(Calendar.DAY_OF_MONTH) == c2
										.get(Calendar.DAY_OF_MONTH)) {// 如果是当天的，需要查询当天的表
									param = tbHyDao.convertMap(id, kpi, null, null, null,
											null,startTime, endTime,"day",null,kpi2);
									datat = tbHyDao.queryFileSystemDayTimeLabelAndData(param);
								} else {// 查询每天的拼接表
									day = c2.get(Calendar.DAY_OF_MONTH) + "";
									day = new DecimalFormat("00").format(
											Long.parseLong(day + ""))
											.toString();
									tbHyDao.convertMap(id, kpi, null, year, month, day,
											startTime, endTime, "userDefinedMonth",null,kpi2);
									datat = tbHyDao.queryFileSystemDayTimeLabelAndData(param);
								}
							} else {// 按照月表计算
								
								param = tbHyDao.convertMap(id, kpi, null, year, month, null,
										startTime, endTime, "userDefined",null,kpi2);
								datat = tbHyDao.queryFileSystemDayTimeLabelAndData(param);
							}
						} else {
							// 默认c1是小月
							int m1 = c1.get(Calendar.MONTH) + 1;
							int m2 = c2.get(Calendar.MONTH) + 1;
							startTime = startDate;
							endTime = endDate;
							for (int i = m1; i <= m2; i++) {
								param = tbHyDao.convertMap(id, kpi, null, year,  i + "", null,
										startTime, endTime, "userDefined",null,kpi2);
								List<Data> data1 = tbHyDao.queryFileSystemDayTimeLabelAndData(param);;
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
	 * @see com.sitech.basd.sxcloud.cloud.service.reportvirtual.ReportVirtualService#queryVmtDiskNetMonitor(java.lang.String, java.lang.String)
	 */
	@Override
	public MonitorObj queryVmtDiskNetMonitor(String hyId, String interval) {
		MonitorObj monitorObj = new MonitorObj();
		//获取近一小时磁盘读速度PM-H-01-010-13  
		Data dataDisRead = tbHyDao.queryHostDiskNetMonitor(hyId,"PM-V-01-010-13",interval);
		monitorObj.setDiskReadAvg(this.parseDouble(dataDisRead.getLabel()));
		monitorObj.setDiskReadMax(this.parseDouble(dataDisRead.getLabel2()));
		//获取近一小时磁盘写速度PM-H-01-010-14 
		Data dataDisWrite = tbHyDao.queryHostDiskNetMonitor(hyId,"PM-V-01-010-14",interval);
		monitorObj.setDiskWriteAvg(this.parseDouble(dataDisWrite.getLabel()));
		monitorObj.setDiskWriteMax(this.parseDouble(dataDisWrite.getLabel2()));
		//获取近一小时网络读速率PM-H-01-010-15 
		Data dataNetRead  = tbHyDao.queryHostDiskNetMonitor(hyId,"PM-V-01-010-15",interval);
		monitorObj.setNetReadAvg(this.parseDouble(dataNetRead.getLabel()));
		monitorObj.setNetReadMax(this.parseDouble(dataNetRead.getLabel2()));
		//获取近一小时网络写速率PM-H-01-010-16 
		Data dataNetWrite = tbHyDao.queryHostDiskNetMonitor(hyId,"PM-V-01-010-16",interval);
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
