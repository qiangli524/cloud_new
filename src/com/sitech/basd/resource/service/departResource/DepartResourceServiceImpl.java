package com.sitech.basd.resource.service.departResource;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.cloud3.dao.departproject.DepartHisInfoDao;
import com.sitech.basd.cloud3.dao.departproject.DepartInfoDao;
import com.sitech.basd.cloud3.domain.departproject.DepartHisInfoObj;
import com.sitech.basd.cloud3.domain.departproject.DepartInfoObj;
import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;

@Service("departResourceService")
public class DepartResourceServiceImpl implements DepartResourceService {

	@Autowired
	private DepartHisInfoDao departHisInfoDao;

	@Autowired
	private DepartInfoDao departInfoDao;

	/**
	 * @Title: buildXmlData
	 * @Description: 组装报表数据
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-17 下午9:00:23
	 */
	@Override
	public String buildXmlData(String resourceType,
			List<DepartInfoObj> departList, String startTime, String endTime) {
		Map<String, List<DepartHisInfoObj>> departMap = new HashMap<String, List<DepartHisInfoObj>>();
		for (DepartInfoObj departInfoObj : departList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("endTime", endTime);
			map.put("startTime", startTime);
			map.put("departId", departInfoObj.getId());
			map.put("resourceType", resourceType);
			List<DepartHisInfoObj> list = departHisInfoDao
					.queryForListByMap(map);
			departMap.put(departInfoObj.getId(), list);
		}
		String subCaption = "";
		if ("1".equals(resourceType)) {
			subCaption = "CPU走势图";
		} else if ("2".equals(resourceType)) {
			subCaption = "内存走势图";
		} else if ("3".equals(resourceType)) {
			subCaption = "存储走势图";
		} else {
			subCaption = "IP走势图";
		}
		String chartXml = JacksonUtil.toJson(this.buildChart(departMap,
				subCaption, departList));
		return chartXml;
	}

	/**
	 * @Title: queryForDepartList
	 * @Description: 加载所有部门信息
	 * @param
	 * @return List<DepartInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-18 上午9:59:11
	 */
	@Override
	public List<DepartInfoObj> queryForDepartList(DepartInfoObj departInfoObj) {
		return departInfoDao.queryForList(departInfoObj);
	}

	/**
	 * @Title: buildChart
	 * @Description: 组装报表对象，json方式
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-18 下午4:46:48
	 */
	private FusionCharts buildChart(
			Map<String, List<DepartHisInfoObj>> departMap, String subCaption,
			List<DepartInfoObj> departList) {
		Set<String> keySet = departMap.keySet();
		List<DepartHisInfoObj> list = null;
		for (String key : keySet) {
			list = departMap.get(key);
			break;
		}

		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		// chart.setNumvisibleplot(list.size() + "");
		// chart.setLabelDisplay("AUTO");
		// chart.setYaxisname("部门预算分配率%");
		// chart.setShowvalues("0");
		// chart.setCanvasPadding("0");
		// chart.setCaption(subCaption);
		 chart.setShowNames("1");
	     chart.setThousandseparator(",");
		chart.setUseroundedges("1");
		chart.setBgcolor("FFFFFF,FFFFFF");
		chart.setShowborder("0");
		// chart.setCanvasBgColor("F2F2F2");
		int size = list.size();
		chart.setCaption("部门预算使用率");
		chart.setNumvisibleplot(size + "");
		chart.setLabelstep(size+ "");
		chart.setLabelDisplay("WARP");
		chart.setXaxisname("");
		chart.setYaxisname("persent");
//		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		fusionCharts.setChart(chart);

		// categories
		Categories categories = new Categories();
		List<Category> categoryList = new ArrayList<Category>();
		List<Categories> categoriesList = new ArrayList<Categories>();

		for (DepartHisInfoObj departHisInfoObj : list) {
			Category c = new Category(departHisInfoObj.getColl_time());
			categoryList.add(c);
		}
		categories.setCategory(categoryList);
		categoriesList.add(categories);

		// dataset
		List<Dataset> datasetList = new ArrayList<Dataset>();
		DecimalFormat df = new DecimalFormat("#.##");
		for (DepartInfoObj departInfoObj : departList) {
			List<DepartHisInfoObj> dlist = departMap.get(departInfoObj.getId());
			List<Data> dataList = new ArrayList<Data>();
			for (DepartHisInfoObj departHisInfoObj : dlist) {
				Data data1 = new Data();
				double temp=(Double.parseDouble(departHisInfoObj.getResource_used_size() == null ? "0.0"
						: departHisInfoObj.getResource_used_size()))
						/ (Double.parseDouble(departHisInfoObj.getResource_size() == null ? "1.0"
								: departHisInfoObj.getResource_size())) * 100 ;
				data1.setValue(df.format(temp));
				dataList.add(data1);
			}
			Dataset dataset = new Dataset();
			dataset.setSeriesname(departInfoObj.getName());
			 dataset.setRenderas("Line");
			 dataset.setAnchorRadius("4");
			 dataset.setLinethickness("2");
//			dataset.setRenderas("Area");
//			dataset.setShowplotborder("0");
			dataset.setData(dataList);
			datasetList.add(dataset);
		}
		fusionCharts.setChart(chart);
		fusionCharts.setCategories(categoriesList);
		fusionCharts.setDataset(datasetList);
		return fusionCharts;
	}

}
