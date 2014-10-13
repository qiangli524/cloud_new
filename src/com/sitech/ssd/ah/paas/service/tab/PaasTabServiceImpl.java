package com.sitech.ssd.ah.paas.service.tab;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Color;
import com.sitech.basd.fusioncharts.vo.ColorRange;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.Dial;
import com.sitech.basd.fusioncharts.vo.Dials;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.ssd.ah.paas.dao.tab.PaasTabDao;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;
import com.sitech.ssd.ah.paas.domain.statistics.PaasKpiMonitorObj;
import com.sitech.ssd.ah.paas.domain.tab.MapChartSet;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;
import com.sitech.ssd.ah.paas.service.tree.PaasTreeService;
import com.sitech.ssd.ah.paas.util.PaasConstant;
import com.sitech.ssd.ah.paas.util.PaasStatisticsConstant;
import com.sitech.utils.date.TimeformatCommon;

import domain.tab.ChartType;

@Service("paasTabService")
public class PaasTabServiceImpl implements PaasTabService {
	@Autowired
	private PaasTabDao paasTabDao;

	@Autowired
	private PaasTreeService paasTreeService;

	@Override
	public FusionCharts showHealthLimitChart(PaasTreeObj obj, String node_type) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setBgcolor("FFFFFF");
		chart.setLowerlimit("0");
		chart.setUpperlimit("100");
		chart.setLowerlimitdisplay("Bad");
		chart.setUpperlimitdisplay("Good");
		chart.setNumberSuffix("%");
		chart.setShowborder("0");
		chart.setGaugeouterradius("120");
		chart.setGaugeoriginx("175");
		chart.setGaugeoriginy("170");
		fusionCharts.setChart(chart);
		ColorRange colorrange = new ColorRange();
		List<Color> colorList = new ArrayList<Color>();
		Color color1 = new Color();
		color1.setMinvalue("0");
		color1.setMaxvalue("45");
		color1.setCode("FF654F");
		colorList.add(color1);
		Color color2 = new Color();
		color2.setMinvalue("45");
		color2.setMaxvalue("80");
		color2.setCode("F6BD0F");
		colorList.add(color2);
		Color color3 = new Color();
		color3.setMinvalue("80");
		color3.setMaxvalue("100");
		color3.setCode("8BBA00");
		colorList.add(color3);
		colorrange.setColor(colorList);
		fusionCharts.setColorrange(colorrange);
		Dials dials = new Dials();
		List<Dial> dialList = new ArrayList<Dial>();
		Dial dial = new Dial();
		double limit = this.queryBusHealthyLimitForInstanceId(obj, node_type);// 查询健康度的值
		dial.setValue(limit + "");
		dial.setRearextension("10");
		dial.setRearextension("10");
		dialList.add(dial);
		dials.setDial(dialList);
		fusionCharts.setDials(dials);
		return fusionCharts;
	}

	@Override
	public List<PaasEntityObj> queryNodeList(PaasEntityObj obj) {
		return paasTabDao.queryNodeList(obj);
	}

	@Override
	public List<PaasEntityObj> queryInstanceNodeList(PaasEntityObj obj) {
		return paasTabDao.queryInstanceNodeList(obj);
	}

	@Override
	public List<PaasEntityObj> queryInstanceListForBusiNode(PaasEntityObj obj) {
		return paasTabDao.queryInstanceListForBusiNode(obj);
	}

	@Override
	public double queryBusHealthyLimitForInstanceId(PaasTreeObj obj, String node_type) {
		double limit = 0;
		try {
			List entityIdList = new ArrayList();
			List<PaasTreeObj> instanceIdList = null;
			if (PaasConstant.DAAS_TYPE.equals(node_type)
					|| PaasConstant.DAAS_BUSI.equals(node_type)) {// Daas显示的健康度
				instanceIdList = paasTreeService.acquireListForType(PaasConstant.DAAS_DB_ENTITY,
						PaasConstant.ORACLE_INSTANCE, obj, new ArrayList<PaasTreeObj>());
			} else if (PaasConstant.MAAS_TYPE.equals(node_type)
					|| PaasConstant.MAAS_APP.equals(node_type)) {// Maas显示的健康度
				instanceIdList = paasTreeService.acquireListForType(PaasConstant.MAAS_ENTITY,
						PaasConstant.WEBLOGIC_SERVER, obj, new ArrayList<PaasTreeObj>());
			}
			if (instanceIdList != null && instanceIdList.size() > 0) {
				for (PaasTreeObj paasTreeObj : instanceIdList) {
					entityIdList.add(paasTreeObj.getEntity_id());
				}
				PaasEntityObj entityObj = new PaasEntityObj();
				entityObj.setEntityIdList(entityIdList);
				entityObj.setKpi_name(PaasStatisticsConstant.health_score_total);
				limit = paasTabDao.queryBusHealthyLimitForInstanceId(entityObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return limit;
	}

	@Override
	public FusionCharts queryChartData(MapChartSet mcs, String unit) {
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		chart.setCaption(mcs.getCaption());
		chart.setNumvisibleplot("288");
		chart.setLabelstep("30");
		chart.setLabelDisplay("AUTO");
		chart.setXaxisname("");
		chart.setYaxisname(unit);
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		chart.setFormatNumberScale("0");// 不格式化数字
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
		// ///////////////////////////////////////////////
		charts.setChart(chart);

		/**
		 * 遍历kpiId，并对每一个查询数据库
		 */
		Map<String, PaasKpiMonitorObj> kpiIds = mcs.getKpiIds();
		Set<String> keys = kpiIds.keySet();
		List<MapChartSet> lists = new ArrayList<MapChartSet>();
		Map<String, MapChartSet> tempMaps = new HashMap<String, MapChartSet>();
		List<Data> dataf = new ArrayList<Data>();
		for (String key : keys) {
			MapChartSet temp = new MapChartSet();
			temp.setKpi_name(key);
			temp.setPotCount(mcs.getPotCount());
			temp.setEntity_id(mcs.getEntity_id());
			temp.setStartData(mcs.getStartData());
			temp.setEndData(mcs.getEndData());
			temp.setCycle_time(mcs.getCycle_time());
			dataf = this.queryTimeLabelAndData(temp);
			temp.setDatas(dataf);
			tempMaps.put(key, temp);
			lists.add(temp);
		}
		/**
		 * 无数据的时，也显示图形
		 */
		if (lists.get(0).getDatas().size() == 0) {
			DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Data data = new Data();
			data.setLabel(dfm.format(new Date()));
			dataf.add(data);
		}
		// 横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		Categories categories = new Categories();
		List<Category> lst = new ArrayList<Category>();
		for (Data d : dataf) {
			Category category = new Category();
			category.setLabel(d.getLabel());
			lst.add(category);
		}
		categories.setCategory(lst);
		categoriesLst.add(categories);
		charts.setCategories(categoriesLst);
		List<Dataset> dataset = new ArrayList<Dataset>();
		for (String key : keys) {
			Dataset datasetf = new Dataset();
			datasetf.setSeriesname(kpiIds.get(key).getDesc_kpi());
			PaasKpiMonitorObj kpiName = mcs.getKpiIds().get(key);
			List<Data> tempData = tempMaps.get(key).getDatas();
			if (ChartType.Area == kpiName.getType()) {
				datasetf.setRenderas("Area");
				datasetf.setShowplotborder("0");
				datasetf.setData(tempData);
				dataset.add(datasetf);
			} else if (ChartType.Line == kpiName.getType()) {
				datasetf.setRenderas("Line");
				datasetf.setAnchorRadius("4");
				datasetf.setLinethickness("1");
				datasetf.setData(tempData);
				dataset.add(datasetf);
			}
		}
		charts.setDataset(dataset);
		return charts;
	}

	@Override
	public FusionCharts queryBusiTopN(PaasEntityObj obj, String node_type) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		List<Data> dataList = new ArrayList<Data>();
		PaasTreeObj paasTreeObj = new PaasTreeObj();
		paasTreeObj.setParent_id(obj.getId());
		List<PaasTreeObj> treeIdList = paasTreeService.queryTreeNodeByObj(paasTreeObj);// 业务集合
		Map<String, PaasEntityObj> map = new HashMap<String, PaasEntityObj>();
		for (PaasTreeObj tObj : treeIdList) {
			PaasTreeObj treeObj = new PaasTreeObj();
			treeObj.setId(tObj.getId());
			treeObj.setNode_type(tObj.getNode_type());
			Double limit = this.queryBusHealthyLimitForInstanceId(treeObj, node_type);// 查询健康度的值
			PaasEntityObj entityObj = new PaasEntityObj();
			entityObj.setLimit(limit);
			entityObj.setId(tObj.getId());
			map.put(tObj.getName(), entityObj);
		}
		Set<String> setKey = map.keySet();
		for (String key : setKey) {
			Data data = new Data();
			data.setLabel(key);
			data.setValue(map.get(key).getLimit() + "");
			data.setLink("JavaScript:showChart('" + map.get(key).getId() + "')");
			dataList.add(data);
		}

		List<Data> dataSortList = this.limitSort(dataList, obj.getTop_num());// 排序
		int count = 0;
		for (Data data : dataList) {
			if (count == 0) {
				data.setColor("FF6347");
			} else if (count == 1) {
				data.setColor("CD69C9");
			} else if (count == 2) {
				data.setColor("EEC900");
			} else if (count == 3) {
				data.setColor("8EE5EE");
			} else if (count == 4) {
				data.setColor("4EEE94");
			}
			count++;
		}
		chart.setYaxisname("健康度");
		chart.setCaption("业务");
		fusionCharts.setData(dataSortList);
		chart.setPlotGradientColor("");
		chart.setThousandseparator(",");
		chart.setBgcolor("FFFFFF,FFFFFF");
		chart.setShowborder("0");
		chart.setCanvasbordercolor("E6E6E6");
		fusionCharts.setChart(chart);
		return fusionCharts;
	}

	@Override
	public FusionCharts queryLimitAverageChart(MapChartSet obj) {
		List<Data> dataList = this.queryTimeLabelAndData(obj);
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setCanvasbordercolor("E6E6E6");
		chart.setShowNames("0");
		chart.setThousandseparator(",");
		chart.setShowalternatevgridcolor("1");
		chart.setVdivlineisdashed("1");
		chart.setIsTrendZone("0");
		chart.setUseroundedges("1");
		chart.setBgcolor("FFFFFF,FFFFFF");
		chart.setShowborder("0");
		chart.setCanvasBgColor("F2F2F2");
		chart.setCaption(obj.getNode_name() + "健康度");
		chart.setLabelDisplay("WRAP");
		chart.setYaxisname("");
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");//
		chart.setShowlabels("0");
		chart.setCanvasPadding("0");
		chart.setNumvisibleplot(dataList.size() + "");
		chart.setLabelstep(dataList.size() + "");
		// chart.setLinecolor("8BBA00");
		chart.setAnchorRadius("3");
		chart.setLinethickness("2");
		for (Data data : dataList) {
			if (!"100.0".equals(data.getValue())) {
				data.setColor("FF654F");
			} else {
				data.setColor("8BBA00");
			}
			data.setLink("JavaScript:showInstanceList('" + obj.getId() + "','" + obj.getEntity_id()
					+ "','" + obj.getCycle_time() + "','" + data.getLabel() + "','"
					+ data.getLabel2() + "')");
		}
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		return fusionCharts;
	}

	@Override
	public List<PaasEntityObj> queryBusiListByEntityId(PaasEntityObj obj) {
		return paasTabDao.queryBusiListByEntityId(obj);
	}

	@Override
	public PaasEntityObj queryParentEntityByEntityId(PaasEntityObj obj) {
		PaasEntityObj paasEntityObj = new PaasEntityObj();
		List<PaasEntityObj> resultList = paasTabDao.queryParentEntityByEntityId(obj);
		if (resultList != null && resultList.size() > 0) {
			paasEntityObj = resultList.get(0);
		}
		return paasEntityObj;
	}

	@Override
	public List<Data> limitSort(List<Data> dataList, int size) {
		if (size > dataList.size()) {
			size = dataList.size();
		}
		List<Data> dataSortList = dataList.subList(0, size);
		/**
		 * > 是按从大到小排序,<是按从小到大排序(desc==0 等于,desc==1大于,desc==-1小于)
		 */
		for (int i = 0; i < size - 1; i++) {
			for (int j = 1; j < size - i; j++) {
				String lableData = "";
				String valueData = "";
				String linkData = "";
				if (dataSortList.get(j - 1).getValue().compareTo(dataSortList.get(j).getValue()) < 0) { // 比较两个数的大小
					lableData = dataSortList.get(j - 1).getLabel();
					valueData = dataSortList.get(j - 1).getValue();
					linkData = dataSortList.get(j - 1).getLink();
					Data minData = new Data();
					minData.setLabel(dataSortList.get(j).getLabel());
					minData.setValue(dataSortList.get(j).getValue());
					minData.setLink(dataSortList.get(j).getLink());
					dataSortList.set((j - 1), minData);
					Data maxData = new Data();
					maxData.setLabel(lableData);
					maxData.setValue(valueData);
					maxData.setLink(linkData);
					dataSortList.set(j, maxData);
				}
			}
		}
		return dataSortList;
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
	@Override
	public List<Data> queryTimeLabelAndData(MapChartSet mapObj) {
		List<Data> datat = new ArrayList<Data>();
		String tableName = "";
		if ("oneHour".equals(mapObj.getCycle_time())) {
			tableName = "tb_paas_resource_coll";// 天
			mapObj.setTableName(tableName);
			mapObj.setTime_inter("60");
			datat = paasTabDao.queryChartDataByDate(mapObj);
		} else if ("fiveHour".equals(mapObj.getCycle_time())) {
			tableName = "tb_paas_resource_coll";// 天
			mapObj.setTableName(tableName);
			mapObj.setTime_inter("300");
			datat = paasTabDao.queryChartDataByDate(mapObj);
		} else if ("thisMonth".equals(mapObj.getCycle_time())) {
			String temp = TimeformatCommon.GetCurrentSysTime("yyyyMM");
			tableName = "tb_paas_resource_coll_" + temp;// 本月
			mapObj.setTableName(tableName);
			datat = paasTabDao.queryChartDataByMonth(mapObj);
		} else if ("lastMonth".equals(mapObj.getCycle_time())) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String temp = TimeformatCommon.GetCurrentSysTime(c.getTime(), "yyyyMM");
			tableName = "tb_paas_resource_coll_" + temp;// 上月
			mapObj.setTableName(tableName);
			datat = paasTabDao.queryChartDataByMonth(mapObj);
		} else if ("selfDate".equals(mapObj.getCycle_time())) {
			// 2013-14-11 09:36:28
			if (mapObj.getStartData() != null && !"".equals(mapObj.getStartData())
					&& mapObj.getEndData() != null && !"".equals(mapObj.getEndData())) {
				try {
					Date date1 = TimeformatCommon.TimeStringToDate(mapObj.getStartData(),
							"yyyy-MM-dd hh:mm:ss");
					Date date2 = TimeformatCommon.TimeStringToDate(mapObj.getEndData(),
							"yyyy-MM-dd hh:mm:ss");
					if ((date2.getTime() - date1.getTime()) > 0) {// 结束时间大于开始时间，可以执行查询
						Calendar c1 = Calendar.getInstance();
						c1.setTime(date1);
						Calendar c2 = Calendar.getInstance();
						c2.setTime(date2);
						String year = c1.get(Calendar.YEAR) + "";
						String month = "";
						String day = "";
						if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {// 目前不支持跨年查询，只可以查询一年之内的
							if (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)) {// 查询范围在一个月内
								month = c2.get(Calendar.MONTH) + 1 + "";// 月份从0开始计算
								// 增加判断月份是一位，补全为两位月份
								month = month.length() == 1 ? "0" + month : month;
								if (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {// 按照天表计算
									Date dateCurrend = new Date();
									Calendar c = Calendar.getInstance();
									c.setTime(dateCurrend);
									if (c.get(Calendar.DAY_OF_MONTH) == c2
											.get(Calendar.DAY_OF_MONTH)) {// 如果是当天的，需要查询当天的表
										tableName = "tb_paas_resource_coll";// 天
										mapObj.setTableName(tableName);
										mapObj.setTime_inter("300");
										datat = paasTabDao.queryChartDataByDate(mapObj);
									} else {// 查询每天的拼接表
										day = c2.get(Calendar.DAY_OF_MONTH) + "";
										day = new DecimalFormat("00").format(
												Long.parseLong(day + "")).toString();
										tableName = "tb_paas_resource_coll_" + year + month + day;
										mapObj.setTableName(tableName);
										datat = paasTabDao.queryChartDataByDateBetween(mapObj);
									}
								} else {// 按照月表计算
									tableName = "tb_paas_resource_coll_" + year + month;
									mapObj.setTableName(tableName);
									datat = paasTabDao.queryChartDataByMonth(mapObj);
								}
							} else {
								// 默认c1是小月
								int m1 = c1.get(Calendar.MONTH) + 1;
								int m2 = c2.get(Calendar.MONTH) + 1;
								String month_i = "";
								for (int i = m1; i <= m2; i++) {
									month_i = i < 10 ? "0" + i : i + "";// 位数不够,补0
									tableName = "tb_paas_resource_coll_" + year + month_i;
									mapObj.setTableName(tableName);
									List<Data> data1 = paasTabDao.queryChartDataByMonth(mapObj);
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
	public String queryTableNameByCycle_time(String coll_time, String cycle_time) {
		String tableName = "";
		if ("oneHour".equals(cycle_time)) {
			tableName = "tb_paas_resource_coll";// 天
		} else if ("fiveHour".equals(cycle_time)) {
			tableName = "tb_paas_resource_coll";// 天
		} else if ("thisMonth".equals(cycle_time)) {
			String temp = TimeformatCommon.GetCurrentSysTime("yyyyMM");
			tableName = "tb_paas_resource_coll_" + temp;// 本月
		} else if ("lastMonth".equals(cycle_time)) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			String temp = TimeformatCommon.GetCurrentSysTime(c.getTime(), "yyyyMM");
			tableName = "tb_paas_resource_coll_" + temp;// 上月
		} else if ("selfDate".equals(cycle_time)) {
			try {
				Date date1 = TimeformatCommon.TimeStringToDate(coll_time, "yyyy-MM-dd hh:mm:ss");
				Calendar c1 = Calendar.getInstance();
				c1.setTime(date1);
				String year = c1.get(Calendar.YEAR) + "";
				String month = "";
				month = c1.get(Calendar.MONTH) + 1 + "";// 月份从0开始计算
				// 增加判断月份是一位，补全为两位月份
				month = month.length() == 1 ? "0" + month : month;
				tableName = "tb_paas_resource_coll_" + year + month;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("cycle_time参数传入错误.");
		}

		return tableName;
	}
}
