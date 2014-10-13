package com.sitech.ssd.ah.busiMonitor.service;

import java.util.ArrayList;
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
import com.sitech.ssd.ah.busiMonitor.dao.MonitorReportDao;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorReportObj;

@Service("monitorReportService")
public class MonitorReportServiceImpl implements MonitorReportService {
	
	@Autowired
	private MonitorReportDao monitorReportDao;

	@Override
	public String queryChartJSONForTraffic(MonitorReportObj obj) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		if("PM-01-01-001-01".equals(obj.getBusiKpi()) || "PM-01-01-001-06".equals(obj.getBusiKpi())){
			chart.setYaxisname("单位：条");
		}else{
			chart.setYaxisname("单位：字节");
		}
		chart.setXaxisname("时间:小时");
		
		//chart.setCaption("话务量走势图");
		chart.setShowvalues("0");// 是否显示值，1显示，0不显示，默认显示
		chart.setShowLabels("1");// 是否显示横坐标 1显示
		chart.setDecimals("2");
		chart.setPlotGradientColor(""); // 渐变色
		chart.setThousandseparator(",");// 千分位的分隔符
		chart.setBgcolor("FFFFFF,FFFFFF");// 面板背景色
		chart.setShowborder("0");
		chart.setShowNames("0");// 是否显示x横坐标值
		chart.setCanvasbordercolor("E6E6E6");// 画布四边的边框颜色
		chart.setAnchorRadius("2");// 折线节点的半径大小
		chart.setLinethickness("2");// 折线的厚度
		// chart.setLabelDisplay("none");
		chart.setDivintervalhints("0,10,20,30,40,50,60,80,100");
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
		List<Data> list = monitorReportDao.queryChartJSONForTraffic(obj);
		//List<Data> list = new ArrayList<Data>();
		Categories categories = new Categories();
		List<Category> cList = new ArrayList<Category>();
		for (Data data : list) {
			Category category = new Category();
			String time = data.getValue();
			String ss = time.substring(8, 13).replace(" ", "日") + "时";
			category.setLabel(ss);
			cList.add(category);
		}
		categories.setCategory(cList);
		categoriesList.add(categories);
		// 数据层
		List<Dataset> datasetList = new ArrayList<Dataset>();
		// 第一条线
		Dataset dataset1 = new Dataset();
		if("PM-01-01-001-06".equals(obj.getBusiKpi())){
			dataset1.setSeriesname("提醒量");
		}else{
			dataset1.setSeriesname("入口量");
		}
		
		List<Data> dataList = new ArrayList<Data>();
		for (Data data : list) {
			Data dt1 = new Data();
			dt1.setValue(data.getLabel2());
			dataList.add(dt1);
		}
		dataset1.setData(dataList);
		datasetList.add(dataset1);
		
		if("PM-01-01-001-01".equals(obj.getBusiKpi()) || "PM-01-01-001-04".equals(obj.getBusiKpi()) || "PM-01-01-001-05".equals(obj.getBusiKpi())){
			// 第二条线
			Dataset dataset2 = new Dataset();
			dataset2.setSeriesname("出口量");
			List<Data> dataList2 = new ArrayList<Data>();
			for (Data data : list) {
				Data dt2 = new Data();
				dt2.setValue(data.getLabel3());
				dataList2.add(dt2);
			}
			dataset2.setData(dataList2);
			datasetList.add(dataset2);
		}
		
		if("PM-01-01-001-01".equals(obj.getBusiKpi())){
			// 第三条线
			Dataset dataset3 = new Dataset();
			dataset3.setSeriesname("错单量");
			List<Data> dataList3 = new ArrayList<Data>();
			for (Data data : list) {
				Data dt3 = new Data();
				dt3.setValue(data.getLabel4());
				dataList3.add(dt3);
			}
			dataset3.setData(dataList3);
			datasetList.add(dataset3);
			
			// 第四条线
			Dataset dataset4 = new Dataset();
			dataset4.setSeriesname("无主量");
			List<Data> dataList4 = new ArrayList<Data>();
			for (Data data : list) {
				Data dt4 = new Data();
				dt4.setValue(data.getLabel());
				dataList4.add(dt4);
			}
			dataset4.setData(dataList4);
			datasetList.add(dataset4);
		}
		
		fusionCharts.setChart(chart);
		fusionCharts.setCategories(categoriesList);
		fusionCharts.setDataset(datasetList);
		System.out.println(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);// 以Json字符串返回
	}
	

}
