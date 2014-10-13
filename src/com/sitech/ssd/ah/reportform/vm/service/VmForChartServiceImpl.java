package com.sitech.ssd.ah.reportform.vm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;

@Service("vmForChartService")
public class VmForChartServiceImpl implements VmForChartService {
	@Autowired
	private ReportformVM reportformVM;

	/**
	 * 
	 * @Title: buildBusiTopNChartJSON
	 * @Description: 准备报表数据
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-17-41 下午5:37:13
	 */
	@Override
	public String buildBusiTopNChartJSON_cpu(VmReportForm vf) {
		if ("top".equals(vf.getTop_line())) {
			// Top图
			FusionCharts fusionCharts = new FusionCharts();
			Chart chart = new Chart();
			chart.setYaxisname("单位：%");
			// chart.setXaxisname("虚拟机名称");
			chart.setCaption("虚拟机-CPU数据展示");
			// chart.setSubcaption("CPU数据展示"); 子标题
			// chart.setNumberSuffix("核");//数字后缀
			chart.setDecimals("2");
			chart.setPlotGradientColor(""); // 渐变色
			chart.setThousandseparator(",");// 千分位的分隔符
			chart.setBgcolor("FFFFFF,FFFFFF");// 面板背景色
			chart.setShowborder("0");
			// chart.setShowvalues("0");//是否显示值，1显示，0不显示，默认显示
			chart.setCanvasbordercolor("E6E6E6");
			// 横坐标
			List<Categories> categoriesLst = new ArrayList<Categories>();
			List<Category> cList = new ArrayList<Category>();
			Categories categories = new Categories();
			if(!"".equals(vf.getVmId())){//不为空
			String[] arr = vf.getVmId().split(",");
			List<String> resultList = new ArrayList<String>();
			for (String str : arr) {
				resultList.add(str);
			}
			vf.setResultList(resultList);
			}
			List<Data> list = reportformVM
					.queryHyTimeLabelAndDataForChart_cpu(vf);
			//集合为空时，这样迭代可以
			for (Data data : list) {
				Category category = new Category();
				category.setLabel(data.getValue());
				cList.add(category);
			}
			categories.setCategory(cList);
			categoriesLst.add(categories);
			fusionCharts.setCategories(categoriesLst);

			List<Dataset> dataset = new ArrayList<Dataset>();
			// 数据:CPUmax
			List<Data> date1 = new ArrayList<Data>();
			Dataset dataset1 = new Dataset();
			dataset1.setSeriesname("CPUmax");
			dataset1.setColor("FF6347");
			for (Data data : list) {
				Data dat1 = new Data();
				dat1.setValue(data.getLabel());
				date1.add(dat1);
			}
			dataset1.setData(date1);
			dataset.add(dataset1);
			// 数据:CPUavg
			List<Data> date3 = new ArrayList<Data>();
			Dataset dataset3 = new Dataset();
			dataset3.setSeriesname("CPUavg");
			for (Data data : list) {
				Data dat3 = new Data();
				dat3.setValue(data.getLabel3());
				date3.add(dat3);
			}
			dataset3.setData(date3);
			dataset.add(dataset3);
			// 数据:CPUmin
			List<Data> date2 = new ArrayList<Data>();
			Dataset dataset2 = new Dataset();
			dataset2.setSeriesname("CPUmin");
			for (Data data : list) {
				Data dat2 = new Data();
				dat2.setValue(data.getLabel2());
				date2.add(dat2);
			}
			dataset2.setData(date2);
			dataset.add(dataset2);
			fusionCharts.setChart(chart);
			fusionCharts.setDataset(dataset);
			return JacksonUtil.toJson(fusionCharts);// 以Json字符串返回
		} else {
			// 折线图
			FusionCharts fusionCharts = new FusionCharts();
			Chart chart = new Chart();
			chart.setYaxisname("单位：%");
			chart.setXaxisname("时间");
			chart.setCaption("虚拟机-CPU走势图");
			chart.setShowvalues("0");//是否显示值，1显示，0不显示，默认显示
			chart.setDecimals("2");
			chart.setPlotGradientColor(""); // 渐变色
			chart.setThousandseparator(",");// 千分位的分隔符
			chart.setBgcolor("FFFFFF,FFFFFF");// 面板背景色
			chart.setShowborder("0");
			chart.setShowNames("0");//是否显示x横坐标值
			chart.setCanvasbordercolor("E6E6E6");//画布四边的边框颜色
			chart.setAnchorRadius("2");//折线节点的半径大小
			chart.setLinethickness("2");//折线的厚度
			//chart.setLabelDisplay("none");
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
			// 横坐标，即时间按点数取limit范围
			List<Categories> categoriesList = new ArrayList<Categories>();
			List<Dataset> dataset = new ArrayList<Dataset>();
			String oldId =vf.getVmId();//保存原有ID字符串
			String[] arr = vf.getVmId().split(",");
			List<String> resultList = new ArrayList<String>();
			for (String str : arr) {
				resultList.add(str);
			}
			vf.setResultList(resultList);
			List<Data> list = reportformVM
					.queryHyTimeLabelAndDataForChart_cpu(vf);
			Categories categories = new Categories();
			List<Category> cList = new ArrayList<Category>();// 查询所有的时间点，取limit值
			for (Data data : list) {
				Category category = new Category();
				category.setLabel(data.getValue());
				cList.add(category);
			}
			categories.setCategory(cList);
			categoriesList.add(categories);
			// 数据层
			a:for (String str : arr) {
				Dataset dataset1 = new Dataset();
				List<Data> date1 = new ArrayList<Data>();
				vf.setVmId(str);
				// 查出时间和名字对应的值,放入map集合
				List<Data> lit = reportformVM
						.queryHyTimeLabelAndDataForChart_time(vf);
				if(lit.isEmpty()){
					continue a; 
				}else{
				dataset1.setSeriesname(lit.get(0).getLabel());// 虚拟机的名称
				Map<String, String> colls = new HashMap<String, String>();
				for (Data d : lit) {
					colls.put(d.getValue(), d.getLabel2());// 放入时间和cpu值
				}
				// 迭代时间集合
				String previousValue="";//前一个的值
				for (Category category : cList) {
					Data dat1 = new Data();
					// 横坐标（时间）是否在colls集合里
					String time = category.getLabel();
					if (colls.containsKey(time)) {
						// 在
						previousValue=colls.get(time); 
						dat1.setValue(previousValue);
						date1.add(dat1);
					} else {
						// 不在集合里
							dat1.setValue(previousValue);
							date1.add(dat1);
						
						}
					}
				dataset1.setData(date1);
				dataset.add(dataset1);
				}
			}
			vf.setVmId(oldId);
			fusionCharts.setChart(chart);
			fusionCharts.setCategories(categoriesList);
			fusionCharts.setDataset(dataset);
			System.out.println(JacksonUtil.toJson(fusionCharts));
			return JacksonUtil.toJson(fusionCharts);// 以Json字符串返回
		}
	}

	@Override
	public String buildBusiTopNChartJSON_mem(VmReportForm vf) {
		if ("top".equals(vf.getTop_line())) {
			// Top图
			FusionCharts fusionCharts = new FusionCharts();
			Chart chart = new Chart();
			chart.setYaxisname("单位：G");
			chart.setXaxisname("虚拟机名称");
			chart.setCaption("虚拟机-内存Top展示");
			// chart.setNumberSuffix("G");
			chart.setDecimals("2");
			chart.setPlotGradientColor("");
			chart.setThousandseparator(",");
			chart.setBgcolor("FFFFFF,FFFFFF");
			chart.setShowborder("0");
			chart.setCanvasbordercolor("E6E6E6");
			// 横坐标
			List<Categories> categoriesLst = new ArrayList<Categories>();
			List<Category> cList = new ArrayList<Category>();
			Categories categories = new Categories();
			if(!"".equals(vf.getVmId())){
				String[] arr = vf.getVmId().split(",");
				List<String> resultList = new ArrayList<String>();
				for (String str : arr) {
					resultList.add(str);
				}
				vf.setResultList(resultList);
				}
			List<Data> list = reportformVM
					.queryHyTimeLabelAndDataForChart_men(vf);
			for (Data data : list) {
				Category category = new Category();
				category.setLabel(data.getValue());
				cList.add(category);
			}
			categories.setCategory(cList);
			categoriesLst.add(categories);
			fusionCharts.setCategories(categoriesLst);

			List<Dataset> dataset = new ArrayList<Dataset>();
			// 数据:内存max
			List<Data> data1 = new ArrayList<Data>();
			List<Data> date1 = new ArrayList<Data>();
			Dataset dataset1 = new Dataset();
			dataset1.setSeriesname("内存max");
			for (Data data : list) {
				Data dat1 = new Data();
				dat1.setValue(data.getLabel());
				date1.add(dat1);
			}
			dataset1.setData(date1);
			dataset.add(dataset1);
			// 数据:内存avg
			List<Data> date3 = new ArrayList<Data>();
			Dataset dataset3 = new Dataset();
			dataset3.setSeriesname("内存avg");
			for (Data data : list) {
				Data dat3 = new Data();
				dat3.setValue(data.getLabel3());
				date3.add(dat3);
			}
			dataset3.setData(date3);
			dataset.add(dataset3);
			// 数据:内存min
			List<Data> date2 = new ArrayList<Data>();
			Dataset dataset2 = new Dataset();
			dataset2.setSeriesname("内存min");
			for (Data data : list) {
				Data dat2 = new Data();
				dat2.setValue(data.getLabel2());
				date2.add(dat2);
			}
			dataset2.setData(date2);
			dataset.add(dataset2);
			fusionCharts.setChart(chart);
			fusionCharts.setDataset(dataset);
			return JacksonUtil.toJson(fusionCharts);// 以Json字符串返回
		} else {
			// 折线图
			FusionCharts fusionCharts = new FusionCharts();
			Chart chart = new Chart();
			chart.setYaxisname("单位：G");
			// chart.setXaxisname("虚拟机名称");
			chart.setCaption("虚拟机-内存走势图");
			chart.setShowvalues("0");
			chart.setDecimals("2");
			chart.setPlotGradientColor(""); // 渐变色
			chart.setThousandseparator(",");// 千分位的分隔符
			chart.setBgcolor("FFFFFF,FFFFFF");// 面板背景色
			chart.setShowborder("0");
			chart.setShowNames("0");//是否显示横坐标值
			chart.setAnchorRadius("2");//折线节点的半径大小
			chart.setLinethickness("2");//折线的厚度
			// chart.setShowvalues("0");//是否显示值，1显示，0不显示，默认显示
			chart.setCanvasbordercolor("E6E6E6");
			/////////////////
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
			// 横坐标，即时间按点数取limit范围
			List<Categories> categoriesList = new ArrayList<Categories>();
			List<Dataset> dataset = new ArrayList<Dataset>();
			String oldId=vf.getVmId();
			String[] arr = vf.getVmId().split(",");
			List<String> resultList = new ArrayList<String>();
			for (String str : arr) {
				resultList.add(str);
			}
			vf.setResultList(resultList);
			List<Data> list = reportformVM
					.queryHyTimeLabelAndDataForChart_cpu(vf);
			Categories categories = new Categories();
			List<Category> cList = new ArrayList<Category>();// 查询所有的时间点，取limit值
			for (Data data : list) {
				Category category = new Category();
				category.setLabel(data.getValue());
				cList.add(category);
			}
			categories.setCategory(cList);
			categoriesList.add(categories);
			// 数据层
			a:for (String str : arr) {
				Dataset dataset1 = new Dataset();
				List<Data> date1 = new ArrayList<Data>();
				vf.setVmId(str);
				// 查出时间和名字对应的值,放入map集合
				List<Data> lit = reportformVM
						.queryHyTimeLabelAndDataForChart_time(vf);
				if(lit.isEmpty()){
					continue a; 
				}else{
				dataset1.setSeriesname(lit.get(0).getLabel());// 虚拟机的名称
				Map<String, String> colls = new HashMap<String, String>();
				for (Data d : lit) {
					colls.put(d.getValue(), d.getLabel3());// 放入时间和内存值
				}
				// 迭代时间集合
				String previousValue="";
				for (Category category : cList) {
					Data dat1 = new Data();
					String time = category.getLabel();
					// 横坐标（时间）是否在colls集合里
					if (colls.containsKey(time)) {
						// 在
						previousValue=colls.get(time);
						dat1.setValue(previousValue);
						date1.add(dat1);
					} else {
						// 不在集合里，则该时间点对应值设为0
						dat1.setValue(previousValue);
						date1.add(dat1);
					}
				}
				dataset1.setData(date1);
				dataset.add(dataset1);
				}
			}
			vf.setVmId(oldId);
			fusionCharts.setChart(chart);
			fusionCharts.setCategories(categoriesList);
			fusionCharts.setDataset(dataset);
			System.out.println(JacksonUtil.toJson(fusionCharts));
			return JacksonUtil.toJson(fusionCharts);// 以Json字符串返回
		}
	}
}
