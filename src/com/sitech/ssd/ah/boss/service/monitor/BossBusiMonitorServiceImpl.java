package com.sitech.ssd.ah.boss.service.monitor;

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
import com.sitech.ssd.ah.boss.dao.monitor.BossBusiMonitorDao;
import com.sitech.ssd.ah.boss.domain.monitor.BossBusiMonitorObj;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;

/**
 * <p>
 * Title: BossBusiMonitorServiceImpl
 * </p>
 * <p>
 * Description: Boss进程监控服务接口实现类
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
 * @createtime 2014-9-2 上午10:06:29
 * 
 */
@Service("bossBusiMonitorService")
public class BossBusiMonitorServiceImpl implements BossBusiMonitorService {
	@Autowired
	BossBusiMonitorDao bossBusiMonitorDao;

	@Override
	public List queryListForPort(BossBusiMonitorObj obj) {
		return bossBusiMonitorDao.queryListForPort(obj);
	}

	@Override
	public List queryListForPortProcess(BossBusiMonitorObj obj) {
		return bossBusiMonitorDao.queryListForPortProcess(obj);
	}

	@Override
	public String queryChartJSONOfPort(BossBusiMonitorObj obj) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		this.getChart(chart);
		chart.setXaxisname("时间（单位：h）");
		// 横坐标，即时间按点数取limit范围
		List<Categories> categoriesList = new ArrayList<Categories>();
		List<Data> list = bossBusiMonitorDao.queryChartJSONOfPort(obj);
		Categories categories = new Categories();
		List<Category> cList = new ArrayList<Category>();
		for (Data data : list) {
			Category category = new Category();
			category.setLabel(data.getValue());
			cList.add(category);
		}
		categories.setCategory(cList);
		categoriesList.add(categories);
		// 数据层
		List<Dataset> datasetList = new ArrayList<Dataset>();
		/** 提醒服务 */
		if ("PM-01-01-001-06".equals(obj.getBUSI_KPI())) {
			// 一条线
			Dataset dataset1 = new Dataset();
			dataset1.setSeriesname("提醒量");
			List<Data> dataList = new ArrayList<Data>();
			for (Data data : list) {
				Data dt1 = new Data();
				dt1.setValue(data.getLabel());
				dataList.add(dt1);
			}
			dataset1.setData(dataList);
			datasetList.add(dataset1);
		} else {
			/** 端口、流量 */
			// 第一条线
			Dataset dataset1 = new Dataset();
			dataset1.setSeriesname("服务入口量");
			List<Data> dataList = new ArrayList<Data>();
			for (Data data : list) {
				Data dt1 = new Data();
				dt1.setValue(data.getLabel());
				dataList.add(dt1);
			}
			dataset1.setData(dataList);
			datasetList.add(dataset1);
			// 第二条线
			Dataset dataset2 = new Dataset();
			dataset2.setSeriesname("服务出口量");
			List<Data> dataList2 = new ArrayList<Data>();
			for (Data data : list) {
				Data dt2 = new Data();
				dt2.setValue(data.getLabel2());
				dataList2.add(dt2);
			}
			dataset2.setData(dataList2);
			datasetList.add(dataset2);
			/** 话务量，还需要再添两条线 */
			if ("PM-01-01-001-01".equals(obj.getBUSI_KPI())) {
				// 第三条线
				Dataset dataset3 = new Dataset();
				dataset3.setSeriesname("无主量");
				List<Data> dataList3 = new ArrayList<Data>();
				for (Data data : list) {
					Data dt3 = new Data();
					dt3.setValue(data.getLabel3());
					dataList3.add(dt3);
				}
				dataset3.setData(dataList3);
				datasetList.add(dataset3);
				// 第四条线
				Dataset dataset4 = new Dataset();
				dataset4.setSeriesname("错单量");
				List<Data> dataList4 = new ArrayList<Data>();
				for (Data data : list) {
					Data dt4 = new Data();
					dt4.setValue(data.getLabel4());
					dataList4.add(dt4);
				}
				dataset4.setData(dataList4);
				datasetList.add(dataset4);
			}
		}
		fusionCharts.setChart(chart);
		fusionCharts.setCategories(categoriesList);
		fusionCharts.setDataset(datasetList);
		System.out.println(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	@Override
	public List queryHosiListForPort(BossBusiMonitorObj obj) {
		return bossBusiMonitorDao.queryHosiListForPort(obj);
	}

	@Override
	public List queryHosiListForPortDesc(BossBusiMonitorObj obj) {
		return bossBusiMonitorDao.queryHosiListForPortDesc(obj);
	}

	@Override
	public String queryHosiChartJSONForPool(BossBusiMonitorObj obj) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		this.getChart(chart);
		chart.setXaxisname("时间（单位：天）");
		// 横坐标，即时间按点数取limit范围
		List<Categories> categoriesList = new ArrayList<Categories>();
		List<Data> list = bossBusiMonitorDao.queryHosiChartJSONForPool(obj);
		Categories categories = new Categories();
		List<Category> cList = new ArrayList<Category>();
		for (Data data : list) {
			Category category = new Category();
			String time = data.getValue();
			String val = time.substring(0, 4) + "/" + time.substring(4, 6) + "/"
					+ time.substring(6, 8);
			category.setLabel(val);
			cList.add(category);
		}
		categories.setCategory(cList);
		categoriesList.add(categories);
		// 数据层
		List<Dataset> datasetList = new ArrayList<Dataset>();
		/** 提醒服务 */
		if ("PM-01-01-001-06".equals(obj.getBUSI_KPI())) {
			// 一条线
			Dataset dataset1 = new Dataset();
			dataset1.setSeriesname("提醒量");
			List<Data> dataList = new ArrayList<Data>();
			for (Data data : list) {
				Data dt1 = new Data();
				dt1.setValue(data.getLabel());
				dataList.add(dt1);
			}
			dataset1.setData(dataList);
			datasetList.add(dataset1);
		} else {
			// 第一条线
			Dataset dataset1 = new Dataset();
			dataset1.setSeriesname("服务入口量");
			List<Data> dataList = new ArrayList<Data>();
			for (Data data : list) {
				Data dt1 = new Data();
				dt1.setValue(data.getLabel());
				dataList.add(dt1);
			}
			dataset1.setData(dataList);
			datasetList.add(dataset1);
			// 第二条线
			Dataset dataset2 = new Dataset();
			dataset2.setSeriesname("服务出口量");
			List<Data> dataList2 = new ArrayList<Data>();
			for (Data data : list) {
				Data dt2 = new Data();
				dt2.setValue(data.getLabel2());
				dataList2.add(dt2);
			}
			dataset2.setData(dataList2);
			datasetList.add(dataset2);
			/** 话务量，还需要再添两条线 */
			if ("PM-01-01-001-01".equals(obj.getBUSI_KPI())) {
				// 第三条线
				Dataset dataset3 = new Dataset();
				dataset3.setSeriesname("无主量");
				List<Data> dataList3 = new ArrayList<Data>();
				for (Data data : list) {
					Data dt3 = new Data();
					dt3.setValue(data.getLabel3());
					dataList3.add(dt3);
				}
				dataset3.setData(dataList3);
				datasetList.add(dataset3);
				// 第四条线
				Dataset dataset4 = new Dataset();
				dataset4.setSeriesname("错单量");
				List<Data> dataList4 = new ArrayList<Data>();
				for (Data data : list) {
					Data dt4 = new Data();
					dt4.setValue(data.getLabel4());
					dataList4.add(dt4);
				}
				dataset4.setData(dataList4);
				datasetList.add(dataset4);
			}
		}
		fusionCharts.setChart(chart);
		fusionCharts.setCategories(categoriesList);
		fusionCharts.setDataset(datasetList);
		System.out.println(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	public Chart getChart(Chart chart) {
		chart.setYaxisname("单位：个");
		chart.setCaption("趋势图");
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
		return chart;
	}

	@Override
	public List queryListForErrorLog(BossBusiMonitorObj obj) {
		// TODO Auto-generated method stub
		return bossBusiMonitorDao.queryListForErrorLog(obj);
	}

	@Override
	public String queryHosiChartJSONForLog(BossBusiMonitorObj obj) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		this.getChart(chart);
		chart.setLinecolor("#20B2AA");
		chart.setAnchorRadius("3");// 折线节点的半径大小
		chart.setXaxisname("时间（单位：天）");
		// 横坐标，即时间按点数取limit范围
		List<Categories> categoriesList = new ArrayList<Categories>();
		List<Data> list = bossBusiMonitorDao.queryHosiChartJSONForLog(obj);
		Categories categories = new Categories();
		List<Category> cList = new ArrayList<Category>();
		for (Data data : list) {
			Category category = new Category();
			String time = data.getValue();
			String val = time.substring(0, 4) + "/" + time.substring(4, 6) + "/"
					+ time.substring(6, 8);
			category.setLabel(val);
			cList.add(category);
		}
		categories.setCategory(cList);
		categoriesList.add(categories);
		// 数据层
		List<Dataset> datasetList = new ArrayList<Dataset>();
		// 第一条线
		Dataset dataset1 = new Dataset();
		dataset1.setSeriesname("错误量");
		List<Data> dataList = new ArrayList<Data>();
		for (Data data : list) {
			Data dt1 = new Data();
			dt1.setValue(data.getLabel());
			dataList.add(dt1);
		}
		dataset1.setData(dataList);
		datasetList.add(dataset1);
		fusionCharts.setChart(chart);
		fusionCharts.setCategories(categoriesList);
		fusionCharts.setDataset(datasetList);
		System.out.println(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	@Override
	public List queryForErrorLogDesc(BossBusiMonitorObj obj) {
		return bossBusiMonitorDao.queryForErrorLogDesc(obj);
	}

	@Override
	public List queryListForMonitorDir(MonitorCfgObj obj) {
		return bossBusiMonitorDao.queryListForMonitorDir(obj);
	}

	@Override
	public List queryListForMonitorDirDesc(BossBusiMonitorObj obj) {
		List<BossBusiMonitorObj> listt = new ArrayList<BossBusiMonitorObj>();
		List<BossBusiMonitorObj> list = bossBusiMonitorDao.queryListForMonitorDirDesc(obj);
		for (BossBusiMonitorObj foo : list) {
			String[] arr = foo.getEQ_ID().split("_");
			// 得到目录名
			foo.setDirName(arr[2]);
			listt.add(foo);
		}
		return listt;
	}

	@Override
	public String queryChartJSONForDirDesc(BossBusiMonitorObj obj) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		this.getChart(chart);
		chart.setYaxisname("单位：个");
		// 横坐标，即时间按点数取limit范围
		List<Categories> categoriesList = new ArrayList<Categories>();
		List<Data> list = bossBusiMonitorDao.queryChartJSONForDirDesc(obj);
		Categories categories = new Categories();
		List<Category> cList = new ArrayList<Category>();
		for (Data data : list) {
			Category category = new Category();
			category.setLabel(data.getValue());
			cList.add(category);
		}
		categories.setCategory(cList);
		categoriesList.add(categories);
		// 数据层
		List<Dataset> datasetList = new ArrayList<Dataset>();
		// 第一条线
		Dataset dataset1 = new Dataset();
		dataset1.setSeriesname("文件数");
		List<Data> dataList = new ArrayList<Data>();
		for (Data data : list) {
			Data dt1 = new Data();
			dt1.setValue(data.getLabel());
			dataList.add(dt1);
		}
		dataset1.setData(dataList);
		datasetList.add(dataset1);
		fusionCharts.setChart(chart);
		fusionCharts.setCategories(categoriesList);
		fusionCharts.setDataset(datasetList);
		System.out.println(JacksonUtil.toJson(fusionCharts));
		return JacksonUtil.toJson(fusionCharts);
	}

	@Override
	public Map queryNctRatio(BossBusiMonitorObj obj) {
		return bossBusiMonitorDao.queryNctRatio(obj);

	}

	@SuppressWarnings("unchecked")
	@Override
	public FusionCharts queryNctRatioChartJSONF(BossBusiMonitorObj obj) {
		// 设置报表基本参数
		Chart chart = new Chart();
		// chart.setSubcaption("单位:个");// 报表子标题
		chart.setBaseFontSize("10");// 图表字体大小
		chart.setOutCnvBaseFont("10");// 图表画布以外的字体样式
		chart.setPieRadius("50");// 圆半径
		chart.setBgcolor("FFFFFF,FFFFFF");// 画板背景色
		chart.setShowborder("0");// 画板边框
		chart.setCaption("在线、离线比例");
		// chart.setShowpercentvalues("1");
		// chart.setShowpercentageinlabel("1");
		chart.setShowlegend("1");
		// chart.setShowvalues("1");
		// chart.setShowlabels("1");
		List<Data> dataList = new ArrayList<Data>();
		Data data = new Data();
		Data data1 = new Data();
		FusionCharts fusionCharts = new FusionCharts();
		// 获取CPU数据
		Map<String, Double> map = bossBusiMonitorDao.queryNctRatio(obj);
		data.setLabel("在线量");
		data.setValue(map.get("onLineSum") + "");
		dataList.add(data);
		data1.setLabel("离线量");
		data1.setValue(map.get("offLineSum") + "");
		dataList.add(data1);
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		System.out.println(JacksonUtil.toJson(fusionCharts));
		return fusionCharts;
	}

	@Override
	public Map queryHosiNctRatio(BossBusiMonitorObj obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FusionCharts queryHosiNctRatioChartJSONF(BossBusiMonitorObj obj) {
		// 设置报表基本参数
		Chart chart = new Chart();
		// chart.setSubcaption("单位:个");// 报表子标题
		chart.setBaseFontSize("10");// 图表字体大小
		chart.setOutCnvBaseFont("10");// 图表画布以外的字体样式
		chart.setPieRadius("50");// 圆半径
		chart.setBgcolor("FFFFFF,FFFFFF");// 画板背景色
		chart.setShowborder("0");// 画板边框
		chart.setCaption("在线、离线比例");
		chart.setShowpercentvalues("1");
		chart.setShowpercentageinlabel("1");
		chart.setShowlegend("1");
		// chart.setShowvalues("1");
		// chart.setShowlabels("1");
		List<Data> dataList = new ArrayList<Data>();
		Data data = new Data();
		Data data1 = new Data();
		FusionCharts fusionCharts = new FusionCharts();
		// 获取CPU数据
		Map<String, Double> map = bossBusiMonitorDao.queryHosiNctRatio(obj);
		data.setLabel("在线量");
		data.setValue(map.get("onLineSum") + "");
		dataList.add(data);
		data1.setLabel("离线量");
		data1.setValue(map.get("offLineSum") + "");
		dataList.add(data1);
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		System.out.println(JacksonUtil.toJson(fusionCharts));
		return fusionCharts;
	}
}
