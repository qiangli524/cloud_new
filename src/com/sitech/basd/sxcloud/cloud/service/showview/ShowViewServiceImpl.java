package com.sitech.basd.sxcloud.cloud.service.showview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.cloud.dao.ibmcoll.IBMCollDao;
import com.sitech.basd.sxcloud.cloud.domain.ibmcoll.IBMCollObj;

@Service("showViewService")
public class ShowViewServiceImpl implements ShowViewService {
	@Autowired
	private IBMCollDao ibmCollDao;
	
	/**
	 * 
	 * @Title: queryAllCollHost
	 * @Description: 获取所有采集的主机
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-29 下午7:12:53
	 */
	public List queryAllCollHost(){
		return ibmCollDao.queryAllCollHost();
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
	public FusionCharts queryChartDayData(String machineType,String collType,String collDataType,String machineId){
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		if(machineType.equals("1")){
			//主机
			if(collType.equals("1")){
				chart.setCaption("主机CPU概述：上一天");
			}else{
				chart.setCaption("主机内存概述：上一天");
			}
		}else{
			if(collType.equals("1")){
				chart.setCaption("虚拟机CPU概述：上一天");
			}else{
				chart.setCaption("虚拟机内存概述：上一天");
			}
		}
		chart.setNumvisibleplot("288");
		chart.setLabelstep("48");
		chart.setLabelDisplay("WRAP");
		chart.setXaxisname("时间：时");
		chart.setYaxisname("大小");
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		charts.setChart(chart);
		//横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		Categories categories = new Categories();
		IBMCollObj ibmCollObj = new IBMCollObj();
		ibmCollObj.setId(machineId);
		List<Category> lst = null;
		if(machineType.equals("1")){
			ibmCollObj.setKpi("PM-00-24-001-05");
			lst = ibmCollDao.queryPowerDayTimeLabel(ibmCollObj);
		}else{
			ibmCollObj.setKpi("PM-00-00-001-00");
			lst = ibmCollDao.queryLparDayTimeLabel(ibmCollObj);
		}
		categories.setCategory(lst);
		categoriesLst.add(categories);
		charts.setCategories(categoriesLst);
		List<Dataset> dataset = new ArrayList<Dataset>();
		//总数
		Dataset datasetf = new Dataset();
		if(collType.equals("1")){
			datasetf.setSeriesname("CPU总个数");
		}else{
			datasetf.setSeriesname("总内存");
		}
		datasetf.setRenderas("Area");
		datasetf.setShowplotborder("0");
		IBMCollObj ibmCollTotalObj = new IBMCollObj();
		ibmCollTotalObj.setId(machineId);
		List<Data> dataf = null;
		if(machineType.equals("1")){//主机
			if(collType.equals("1")){//cpu
				ibmCollTotalObj.setKpi("PM-00-24-001-05");
			}else{//内存
				ibmCollTotalObj.setKpi("PM-00-24-001-08");
			}
			dataf = ibmCollDao.queryPowerDayData(ibmCollTotalObj);
		}else{//虚拟机
			if(collType.equals("1")){
				ibmCollTotalObj.setKpi("PM-00-00-001-00");
			}else{
				ibmCollTotalObj.setKpi("PM-00-00-001-03");
			}
			dataf = ibmCollDao.queryLparDayData(ibmCollTotalObj);
		}
		datasetf.setData(dataf);
		dataset.add(datasetf);
		//可用
		Dataset datasets = new Dataset();
		if(collType.equals("1")){
			datasets.setSeriesname("CPU可用个数");
		}else{
			datasets.setSeriesname("可用内存");
		}
		datasets.setRenderas("Area");
		datasets.setShowplotborder("0");
		IBMCollObj ibmCollUseableObj = new IBMCollObj();
		ibmCollUseableObj.setId(machineId);
		List<Data> datas = null;
		if(machineType.equals("1")){//主机
			if(collType.equals("1")){//cpu
				ibmCollUseableObj.setKpi("PM-00-24-001-18");
			}else{//内存
				ibmCollUseableObj.setKpi("PM-00-24-001-12");
			}
			datas = ibmCollDao.queryPowerDayData(ibmCollUseableObj);
		}else{//虚拟机
			if(collType.equals("1")){
				ibmCollUseableObj.setKpi("PM-00-00-001-01");
			}else{
				ibmCollUseableObj.setKpi("PM-00-00-001-04");
			}
			datas = ibmCollDao.queryLparDayData(ibmCollUseableObj);
		}
		datasets.setData(datas);
		dataset.add(datasets);
		//使用率
		Dataset datasett = new Dataset();
		if(collType.equals("1")){
			datasett.setSeriesname("CPU使用率");
		}else{
			datasett.setSeriesname("内存使用率");
		}
		datasett.setRenderas("Line");
		datasett.setAnchorRadius("0.2");
		datasett.setLinethickness("1");
		IBMCollObj ibmCollTrendObj = new IBMCollObj();
		ibmCollTrendObj.setId(machineId);
		List<Data> datat = null;
		if(machineType.equals("1")){//主机
			if(collType.equals("1")){//cpu
				ibmCollTrendObj.setKpi("PM-00-24-001-06");
				ibmCollTrendObj.setTotalKpi("PM-00-24-001-05");
			}else{//内存
				ibmCollTrendObj.setKpi("PM-");
			}
			datat = ibmCollDao.queryPowerDayTrendData(ibmCollTrendObj);
		}else{//虚拟机
			if(collType.equals("1")){
				ibmCollTrendObj.setKpi("PM-00-00-001-02");
				ibmCollTrendObj.setTotalKpi("PM-00-00-001-00");
			}else{
				ibmCollTrendObj.setKpi("PM-");
			}
			datat = ibmCollDao.queryLparDayTrendData(ibmCollTrendObj);
		}
		datasett.setData(datat);
		dataset.add(datasett);
		charts.setDataset(dataset);
		return charts;
	}
	
	/**
	 * 
	 * @Title: queryChartWeekData
	 * @Description: 查询周表数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午10:04:58
	 */
	public FusionCharts queryChartWeekData(String machineType,String collType,String collDataType,String machineId){
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		if(machineType.equals("1")){
			//主机
			if(collType.equals("1")){
				chart.setCaption("主机CPU概述：上一周");
			}else{
				chart.setCaption("主机内存概述：上一周");
			}
		}else{
			if(collType.equals("1")){
				chart.setCaption("虚拟机CPU概述：上一周");
			}else{
				chart.setCaption("虚拟机内存概述：上一周");
			}
		}
		chart.setNumvisibleplot("336");
		chart.setLabelstep("48");
		chart.setLabelDisplay("WRAP");
		chart.setXaxisname("时间：日");
		chart.setYaxisname("大小");
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		charts.setChart(chart);
		//横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		Categories categories = new Categories();
		IBMCollObj ibmCollObj = new IBMCollObj();
		ibmCollObj.setId(machineId);
		List<Category> lst = null;
		if(machineType.equals("1")){
			ibmCollObj.setKpi("PM-50-04-001-01");
			lst = ibmCollDao.queryPowerWeekTimeLabel(ibmCollObj);
		}else{
			ibmCollObj.setKpi("PM-50-00-001-00");
			lst = ibmCollDao.queryLparWeekTimeLabel(ibmCollObj);
		}
		categories.setCategory(lst);
		categoriesLst.add(categories);
		charts.setCategories(categoriesLst);
		List<Dataset> dataset = new ArrayList<Dataset>();
		//总数
		Dataset datasetf = new Dataset();
		if(collType.equals("1")){
			datasetf.setSeriesname("CPU总个数");
		}else{
			datasetf.setSeriesname("总内存");
		}
		datasetf.setRenderas("Area");
		datasetf.setShowplotborder("0");
		IBMCollObj ibmCollTotalObj = new IBMCollObj();
		ibmCollTotalObj.setId(machineId);
		List<Data> dataf = null;
		if(machineType.equals("1")){//主机
			if(collType.equals("1")){//cpu
				if(collDataType.equals("1")){//平均值
					ibmCollTotalObj.setKpi("PM-50-04-001-01");
				}else{//最大值
					ibmCollTotalObj.setKpi("PM-50-04-002-01");
				}
			}else{//内存
				if(collDataType.equals("1")){//平均值
					ibmCollTotalObj.setKpi("PM-50-04-001-04");
				}else{//最大值
					ibmCollTotalObj.setKpi("PM-50-04-002-04");
				}
			}
			dataf = ibmCollDao.queryPowerWeekData(ibmCollTotalObj);
		}else{//虚拟机
			if(collType.equals("1")){//cpu
				if(collDataType.equals("1")){//平均值
					ibmCollTotalObj.setKpi("PM-50-00-001-00");
				}else{//最大值
					ibmCollTotalObj.setKpi("PM-50-00-002-00");
				}
			}else{//内存
				if(collDataType.equals("1")){//平均值
					ibmCollTotalObj.setKpi("PM-50-00-001-03");
				}else{//最大值
					ibmCollTotalObj.setKpi("PM-50-00-002-03");
				}
			}
			dataf = ibmCollDao.queryLparWeekData(ibmCollTotalObj);
		}
		datasetf.setData(dataf);
		dataset.add(datasetf);
		//可用
		Dataset datasets = new Dataset();
		if(collType.equals("1")){
			datasets.setSeriesname("CPU可用个数");
		}else{
			datasets.setSeriesname("可用内存");
		}
		datasets.setRenderas("Area");
		datasets.setShowplotborder("0");
		IBMCollObj ibmCollUseableObj = new IBMCollObj();
		ibmCollUseableObj.setId(machineId);
		List<Data> datas = null;
		if(machineType.equals("1")){//主机
			if(collType.equals("1")){//cpu
				if(collDataType.equals("1")){//平均值
					ibmCollUseableObj.setKpi("PM-50-04-001-02");
				}else{//最大值
					ibmCollUseableObj.setKpi("PM-50-04-002-02");
				}
			}else{//内存
				if(collDataType.equals("1")){//平均值
					ibmCollUseableObj.setKpi("PM-50-04-001-05");
				}else{//最大值
					ibmCollUseableObj.setKpi("PM-50-04-002-05");
				}
			}
			datas = ibmCollDao.queryPowerWeekData(ibmCollUseableObj);
		}else{//虚拟机
			if(collType.equals("1")){
				if(collDataType.equals("1")){//平均值
					ibmCollUseableObj.setKpi("PM-50-00-001-01");
				}else{//最大值
					ibmCollUseableObj.setKpi("PM-50-00-002-01");
				}
			}else{
				if(collDataType.equals("1")){//平均值
					ibmCollUseableObj.setKpi("PM-50-00-001-04");
				}else{//最大值
					ibmCollUseableObj.setKpi("PM-50-00-002-04");
				}
			}
			datas = ibmCollDao.queryLparWeekData(ibmCollUseableObj);
		}
		datasets.setData(datas);
		dataset.add(datasets);
		//使用率
		Dataset datasett = new Dataset();
		if(collType.equals("1")){
			datasett.setSeriesname("CPU使用率");
		}else{
			datasett.setSeriesname("内存使用率");
		}
		datasett.setRenderas("Line");
		datasett.setAnchorRadius("0.2");
		datasett.setLinethickness("1");
		IBMCollObj ibmCollTrendObj = new IBMCollObj();
		ibmCollTrendObj.setId(machineId);
		List<Data> datat = null;
		
		if(machineType.equals("1")){//主机
			if(collType.equals("1")){//cpu
				if(collDataType.equals("1")){//平均值
					ibmCollTrendObj.setKpi("PM-50-04-001-03");
					ibmCollTrendObj.setTotalKpi("PM-50-04-001-01");
				}else{//最大值
					ibmCollTrendObj.setKpi("PM-50-04-002-03");
					ibmCollTrendObj.setTotalKpi("PM-50-04-002-01");
				}
			}else{//内存
				ibmCollTrendObj.setKpi("PM-");
			}
			datat = ibmCollDao.queryPowerWeekTrendData(ibmCollTrendObj);
		}else{//虚拟机
			if(collType.equals("1")){
				if(collDataType.equals("1")){//平均值
					ibmCollTrendObj.setKpi("PM-50-00-001-02");
					ibmCollTrendObj.setTotalKpi("PM-50-00-001-00");
				}else{//最大值
					ibmCollTrendObj.setKpi("PM-50-00-002-02");
					ibmCollTrendObj.setTotalKpi("PM-50-00-002-00");
				}
			}else{
				ibmCollTrendObj.setKpi("PM-");
			}
			datat = ibmCollDao.queryLparWeekTrendData(ibmCollTrendObj);
		}
		datasett.setData(datat);
		dataset.add(datasett);
		charts.setDataset(dataset);
		return charts;
	}
	
	/**
	 * 
	 * @Title: queryChartWeekData
	 * @Description: 查询月表数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午10:04:58
	 */
	public FusionCharts queryChartMonthData(String machineType,String collType,String collDataType,String machineId){
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		if(machineType.equals("1")){
			//主机
			if(collType.equals("1")){
				chart.setCaption("主机CPU概述：上一月");
			}else{
				chart.setCaption("主机内存概述：上一月");
			}
		}else{
			if(collType.equals("1")){
				chart.setCaption("虚拟机CPU概述：上一月");
			}else{
				chart.setCaption("虚拟机内存概述：上一月");
			}
		}
		chart.setNumvisibleplot("372");
		chart.setLabelstep("93");
		chart.setLabelDisplay("WRAP");
		chart.setXaxisname("时间：日");
		chart.setYaxisname("大小");
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		charts.setChart(chart);
		//横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		Categories categories = new Categories();
		IBMCollObj ibmCollObj = new IBMCollObj();
		ibmCollObj.setId(machineId);
		List<Category> lst = null;
		if(machineType.equals("1")){
			ibmCollObj.setKpi("PM-50-04-001-01");
			lst = ibmCollDao.queryPowerMonthTimeLabel(ibmCollObj);
		}else{
			ibmCollObj.setKpi("PM-50-00-001-00");
			lst = ibmCollDao.queryLparMonthTimeLabel(ibmCollObj);
		}
		categories.setCategory(lst);
		categoriesLst.add(categories);
		charts.setCategories(categoriesLst);
		List<Dataset> dataset = new ArrayList<Dataset>();
		//总数
		Dataset datasetf = new Dataset();
		if(collType.equals("1")){
			datasetf.setSeriesname("CPU总个数");
		}else{
			datasetf.setSeriesname("总内存");
		}
		datasetf.setRenderas("Area");
		datasetf.setShowplotborder("0");
		IBMCollObj ibmCollTotalObj = new IBMCollObj();
		ibmCollTotalObj.setId(machineId);
		List<Data> dataf = null;
		if(machineType.equals("1")){//主机
			if(collType.equals("1")){//cpu
				if(collDataType.equals("1")){//平均值
					ibmCollTotalObj.setKpi("PM-50-04-001-01");
				}else{//最大值
					ibmCollTotalObj.setKpi("PM-50-04-002-01");
				}
			}else{//内存
				if(collDataType.equals("1")){//平均值
					ibmCollTotalObj.setKpi("PM-50-04-001-04");
				}else{//最大值
					ibmCollTotalObj.setKpi("PM-50-04-002-04");
				}
			}
			dataf = ibmCollDao.queryPowerMonthData(ibmCollTotalObj);
		}else{//虚拟机
			if(collType.equals("1")){//cpu
				if(collDataType.equals("1")){//平均值
					ibmCollTotalObj.setKpi("PM-50-00-001-00");
				}else{//最大值
					ibmCollTotalObj.setKpi("PM-50-00-002-00");
				}
			}else{//内存
				if(collDataType.equals("1")){//平均值
					ibmCollTotalObj.setKpi("PM-50-00-001-03");
				}else{//最大值
					ibmCollTotalObj.setKpi("PM-50-00-002-03");
				}
			}
			dataf = ibmCollDao.queryLparMonthData(ibmCollTotalObj);
		}
		datasetf.setData(dataf);
		dataset.add(datasetf);
		//可用
		Dataset datasets = new Dataset();
		if(collType.equals("1")){
			datasets.setSeriesname("CPU可用个数");
		}else{
			datasets.setSeriesname("可用内存");
		}
		datasets.setRenderas("Area");
		datasets.setShowplotborder("0");
		IBMCollObj ibmCollUseableObj = new IBMCollObj();
		ibmCollUseableObj.setId(machineId);
		List<Data> datas = null;
		if(machineType.equals("1")){//主机
			if(collType.equals("1")){//cpu
				if(collDataType.equals("1")){//平均值
					ibmCollUseableObj.setKpi("PM-50-04-001-02");
				}else{//最大值
					ibmCollUseableObj.setKpi("PM-50-04-002-02");
				}
			}else{//内存
				if(collDataType.equals("1")){//平均值
					ibmCollUseableObj.setKpi("PM-50-04-001-05");
				}else{//最大值
					ibmCollUseableObj.setKpi("PM-50-04-002-05");
				}
			}
			datas = ibmCollDao.queryPowerMonthData(ibmCollUseableObj);
		}else{//虚拟机
			if(collType.equals("1")){
				if(collDataType.equals("1")){//平均值
					ibmCollUseableObj.setKpi("PM-50-00-001-01");
				}else{//最大值
					ibmCollUseableObj.setKpi("PM-50-00-002-01");
				}
			}else{
				if(collDataType.equals("1")){//平均值
					ibmCollUseableObj.setKpi("PM-50-00-001-04");
				}else{//最大值
					ibmCollUseableObj.setKpi("PM-50-00-002-04");
				}
			}
			datas = ibmCollDao.queryLparMonthData(ibmCollUseableObj);
		}
		datasets.setData(datas);
		dataset.add(datasets);
		//使用率
		Dataset datasett = new Dataset();
		if(collType.equals("1")){
			datasett.setSeriesname("CPU使用率");
		}else{
			datasett.setSeriesname("内存使用率");
		}
		datasett.setRenderas("Line");
		datasett.setAnchorRadius("0.2");
		datasett.setLinethickness("1");
		IBMCollObj ibmCollTrendObj = new IBMCollObj();
		ibmCollTrendObj.setId(machineId);
		List<Data> datat = null;
		if(machineType.equals("1")){//主机
			if(collType.equals("1")){//cpu
				if(collDataType.equals("1")){//平均值
					ibmCollTrendObj.setKpi("PM-50-04-001-03");
					ibmCollTrendObj.setTotalKpi("PM-50-04-001-01");
				}else{//最大值
					ibmCollTrendObj.setKpi("PM-50-04-002-03");
					ibmCollTrendObj.setTotalKpi("PM-50-04-002-01");
				}
			}else{//内存
				ibmCollTrendObj.setKpi("PM-");
			}
			datat = ibmCollDao.queryPowerMonthTrendData(ibmCollTrendObj);
		}else{//虚拟机
			if(collType.equals("1")){
				if(collDataType.equals("1")){//平均值
					ibmCollTrendObj.setKpi("PM-50-00-001-02");
					ibmCollTrendObj.setTotalKpi("PM-50-00-001-00");
				}else{//最大值
					ibmCollTrendObj.setKpi("PM-50-00-002-02");
					ibmCollTrendObj.setTotalKpi("PM-50-00-002-00");
				}
			}else{
				ibmCollTrendObj.setKpi("PM-");
			}
			datat = ibmCollDao.queryLparMonthTrendData(ibmCollTrendObj);
		}
		datasett.setData(datat);
		dataset.add(datasett);
		charts.setDataset(dataset);
		return charts;
	}
	
	/**
	 * 
	 * @Title: queryChartWeekData
	 * @Description: 查询年表数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午10:04:58
	 */
	public FusionCharts queryChartYearData(String machineType,String collType,String collDataType,String machineId){
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		if(machineType.equals("1")){
			//主机
			if(collType.equals("1")){
				chart.setCaption("主机CPU概述：上一年");
			}else{
				chart.setCaption("主机内存概述：上一年");
			}
		}else{
			if(collType.equals("1")){
				chart.setCaption("虚拟机CPU概述：上一年");
			}else{
				chart.setCaption("虚拟机内存概述：上一年");
			}
		}
		chart.setNumvisibleplot("730");
		chart.setLabelstep("61");
		chart.setLabelDisplay("WRAP");
		chart.setXaxisname("时间：月");
		chart.setYaxisname("大小");
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		charts.setChart(chart);
		//横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		Categories categories = new Categories();
		IBMCollObj ibmCollObj = new IBMCollObj();
		ibmCollObj.setId(machineId);
		List<Category> lst = null;
		if(machineType.equals("1")){
			ibmCollObj.setKpi("PM-50-04-001-01");
			lst = ibmCollDao.queryPowerYearTimeLabel(ibmCollObj);
		}else{
			ibmCollObj.setKpi("PM-50-00-001-00");
			lst = ibmCollDao.queryLparYearTimeLabel(ibmCollObj);
		}
		categories.setCategory(lst);
		categoriesLst.add(categories);
		charts.setCategories(categoriesLst);
		List<Dataset> dataset = new ArrayList<Dataset>();
		//总数
		Dataset datasetf = new Dataset();
		if(collType.equals("1")){
			datasetf.setSeriesname("CPU总个数");
		}else{
			datasetf.setSeriesname("总内存");
		}
		datasetf.setRenderas("Area");
		datasetf.setShowplotborder("0");
		IBMCollObj ibmCollTotalObj = new IBMCollObj();
		ibmCollTotalObj.setId(machineId);
		List<Data> dataf = null;
		if(machineType.equals("1")){//主机
			if(collType.equals("1")){//cpu
				if(collDataType.equals("1")){//平均值
					ibmCollTotalObj.setKpi("PM-50-04-001-01");
				}else{//最大值
					ibmCollTotalObj.setKpi("PM-50-04-002-01");
				}
			}else{//内存
				if(collDataType.equals("1")){//平均值
					ibmCollTotalObj.setKpi("PM-50-04-001-04");
				}else{//最大值
					ibmCollTotalObj.setKpi("PM-50-04-002-04");
				}
			}
			dataf = ibmCollDao.queryPowerYearData(ibmCollTotalObj);
		}else{//虚拟机
			if(collType.equals("1")){//cpu
				if(collDataType.equals("1")){//平均值
					ibmCollTotalObj.setKpi("PM-50-00-001-00");
				}else{//最大值
					ibmCollTotalObj.setKpi("PM-50-00-002-00");
				}
			}else{//内存
				if(collDataType.equals("1")){//平均值
					ibmCollTotalObj.setKpi("PM-50-00-001-03");
				}else{//最大值
					ibmCollTotalObj.setKpi("PM-50-00-002-03");
				}
			}
			dataf = ibmCollDao.queryLparYearData(ibmCollTotalObj);
		}
		datasetf.setData(dataf);
		dataset.add(datasetf);
		//可用
		Dataset datasets = new Dataset();
		if(collType.equals("1")){
			datasets.setSeriesname("CPU可用个数");
		}else{
			datasets.setSeriesname("可用内存");
		}
		datasets.setRenderas("Area");
		datasets.setShowplotborder("0");
		IBMCollObj ibmCollUseableObj = new IBMCollObj();
		ibmCollUseableObj.setId(machineId);
		List<Data> datas = null;
		if(machineType.equals("1")){//主机
			if(collType.equals("1")){//cpu
				if(collDataType.equals("1")){//平均值
					ibmCollUseableObj.setKpi("PM-50-04-001-02");
				}else{//最大值
					ibmCollUseableObj.setKpi("PM-50-04-002-02");
				}
			}else{//内存
				if(collDataType.equals("1")){//平均值
					ibmCollUseableObj.setKpi("PM-50-04-001-05");
				}else{//最大值
					ibmCollUseableObj.setKpi("PM-50-04-002-05");
				}
			}
			datas = ibmCollDao.queryPowerYearData(ibmCollUseableObj);
		}else{//虚拟机
			if(collType.equals("1")){
				if(collDataType.equals("1")){//平均值
					ibmCollUseableObj.setKpi("PM-50-00-001-01");
				}else{//最大值
					ibmCollUseableObj.setKpi("PM-50-00-002-01");
				}
			}else{
				if(collDataType.equals("1")){//平均值
					ibmCollUseableObj.setKpi("PM-50-00-001-04");
				}else{//最大值
					ibmCollUseableObj.setKpi("PM-50-00-002-04");
				}
			}
			datas = ibmCollDao.queryLparYearData(ibmCollUseableObj);
		}
		datasets.setData(datas);
		dataset.add(datasets);
		//使用率
		Dataset datasett = new Dataset();
		if(collType.equals("1")){
			datasett.setSeriesname("CPU使用率");
		}else{
			datasett.setSeriesname("内存使用率");
		}
		datasett.setRenderas("Line");
		datasett.setAnchorRadius("0.2");
		datasett.setLinethickness("1");
		IBMCollObj ibmCollTrendObj = new IBMCollObj();
		ibmCollTrendObj.setId(machineId);
		List<Data> datat = null;
		if(machineType.equals("1")){//主机
			if(collType.equals("1")){//cpu
				if(collDataType.equals("1")){//平均值
					ibmCollTrendObj.setKpi("PM-50-04-001-03");
					ibmCollTrendObj.setTotalKpi("PM-50-04-001-01");
				}else{//最大值
					ibmCollTrendObj.setKpi("PM-50-04-002-03");
					ibmCollTrendObj.setTotalKpi("PM-50-04-002-01");
				}
			}else{//内存
				ibmCollTrendObj.setKpi("PM-");
			}
			datat = ibmCollDao.queryPowerYearTrendData(ibmCollTrendObj);
		}else{//虚拟机
			if(collType.equals("1")){
				if(collDataType.equals("1")){//平均值
					ibmCollTrendObj.setKpi("PM-50-00-001-02");
					ibmCollTrendObj.setTotalKpi("PM-50-00-001-00");
				}else{//最大值
					ibmCollTrendObj.setKpi("PM-50-00-002-02");
					ibmCollTrendObj.setTotalKpi("PM-50-00-002-00");
				}
			}else{
				ibmCollTrendObj.setKpi("PM-");
			}
			datat = ibmCollDao.queryLparYearTrendData(ibmCollTrendObj);
		}
		datasett.setData(datat);
		dataset.add(datasett);
		charts.setDataset(dataset);
		return charts;
	}
	
	/**
	 * 
	 * @Title: queryAllCollVm
	 * @Description: 查询所有虚拟机
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午8:45:25
	 */
	public List queryAllCollVm(){
		return ibmCollDao.queryAllCollVm();
	}
}
