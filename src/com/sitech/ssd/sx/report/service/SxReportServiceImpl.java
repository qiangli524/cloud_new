/**
 * SxReportServiceImpl.java
 * com.sitech.ssd.sx.report.service
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 四月 1 		duangh
 *
 * Copyright (c) 2014, si-tech All Rights Reserved.
 */

package com.sitech.ssd.sx.report.service;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.ssd.sx.report.dao.SxReportDao;
import com.sitech.ssd.sx.report.domain.HostObj;
import com.sitech.ssd.sx.report.domain.KpiObj;
import com.sitech.ssd.sx.report.domain.SearchObj;

/**
 * ClassName:SxReportServiceImpl Function:山西同一时间，不同主机，不同主机，统一时间段等报表接口实现类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 四月 1 09:39:26
 */
@Service("sxReportService")
public class SxReportServiceImpl extends BaseService implements SxReportService {
	@Resource
	private SxReportDao sxReportDao;
	private final static String type_power = "power";
	private final static String type_lpar = "lpar";

	@Override
	public FusionCharts sametimeData(String entities, String kpi, String type,
			String starttime, String endtime) {
		String[] ids = entities.split(",");

		FusionCharts fusionCharts = new FusionCharts();

		Chart chart = new Chart();
		chart.setCaption("time");
		chart.setLinethickness("1");
		chart.setShowvalues("0");
		chart.setFormatnumberscale("0");
		chart.setAnchorRadius("2");
		chart.setDivlinealpha("10");
		chart.setDivlinecolor("0000FF");
		chart.setDivlineisdashed("1");
		chart.setShowalternatevgridcolor("1");
		chart.setAlternatehgridcolor("6C7B8B");
		chart.setShadowalpha("40");
		chart.setLabelstep("2");
		chart.setNumvdivlines("5");
		chart.setChartrightmargin("35");
		chart.setBgcolor("FFFFFF,436EEE");
		chart.setBgangle("270");
		chart.setBgalpha("5,5");
		chart.setAlternatehgridalpha("5");
		chart.setLegendborderalpha("RIGHT");

		// ReportFilter filter = new ReportFilter();
		// filter.setKpi(kpi);
		// filter.setStarttime(starttime);
		// filter.setEndtime(endtime);

		SearchObj search = new SearchObj();
		search.setKpi(kpi);
		search.setType(type);
		search.setStartTime(starttime);
		search.setEndTime(endtime);

		List<Dataset> resultList = new ArrayList<Dataset>();
		List<Categories> cateList = new ArrayList<Categories>();
		Categories categories = new Categories();
		List<Category> categoryList = new ArrayList<Category>();
		List<Data> dataList = null;
		for (int i = 0; i < ids.length; i++) {
			search.setPowerId(ids[i]);
			dataList = sxReportDao.querySametimeData(search);
			Dataset set = new Dataset();
			set.setAnchorRadius("0");
			set.setData(dataList);
			resultList.add(set);
		}
		for (int i = 0; i < dataList.size(); i++) {
			Category category = new Category();
			category.setLabel(dataList.get(i).getLabel());
			categoryList.add(category);
		}
		categories.setCategory(categoryList);
		cateList.add(categories);
		fusionCharts.setCategories(cateList);
		fusionCharts.setDataset(resultList);
		fusionCharts.setChart(chart);
		return fusionCharts;
	}

	/**
	 * 
	 * @see com.sitech.ssd.sx.report.service.SxReportService#getPowerList()
	 */
	@Override
	public List<HostObj> getHostList(SearchObj search) {
		List<HostObj> list = null;
		try {
			if (type_power.equals(search.getType())) {
				list = this.getSqlMap().queryForList("SxReport.queryPowerList",
						null);
			} else {
				list = this.getSqlMap().queryForList("SxReport.queryLparList",
						null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @see com.sitech.ssd.sx.report.service.SxReportService#getCardList(com.sitech.ssd.sx.report.domain.SearchObj)
	 */
	@Override
	public List<HostObj> getCardList(SearchObj search) {
		List<HostObj> list = null;
		try {
			if (search.getLparId() != null) {
				String[] ids = search.getLparId().split("_");
				if (ids.length == 2) {
					search.setPowerId(ids[0]);
					search.setLparId(ids[1]);
				}
			}
			list = this.getSqlMap().queryForList("SxReport.queryCardList",
					search);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @see com.sitech.ssd.sx.report.service.SxReportService#getManytimeReportData(com.sitech.ssd.sx.report.domain.SearchObj)
	 */
	@Override
	public String getManytimeReportData(SearchObj search) {
		DecimalFormat formater = new DecimalFormat("#0.##");
		JSONObject obj = new JSONObject();
		JSONArray category = new JSONArray();
		JSONArray dataset = new JSONArray();
		try {
			// 处理lparId
			if (search.getLparId() != null) {
				String[] ids = search.getLparId().split("_");
				if (ids.length == 2) {
					search.setPowerId(ids[0]);
					search.setLparId(ids[1]);
				}
			}
			String[] startTime = (String[]) search.getTimeMap()
					.get("startTime");
			String[] endTime = (String[]) search.getTimeMap().get("endTime");
			for (int i = 0; i < startTime.length; i++) {
				search.setStartTime(startTime[i]);
				search.setEndTime(endTime[i]);
				// 数据部分
				JSONObject series = new JSONObject();
				series.put("seriesname", "时间段" + (i + 1));
				JSONArray data = new JSONArray();
				List<KpiObj> list = this.getSqlMap().queryForList(
						"SxReport.queryManytimeReportData", search);
				for (KpiObj kpiObj : list) {
					if (i == 0) {
						JSONObject lable = new JSONObject();
						lable.put("label", kpiObj.getTime());
						category.add(lable);
					}
					JSONObject d = new JSONObject();
					d.put("value", formater.format(Double.parseDouble(kpiObj
							.getValue())));
					data.add(d);
				}
				series.put("data", data);
				dataset.add(series);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("category", category);
		obj.put("dataset", dataset);
		return obj.toString();
	}

}
