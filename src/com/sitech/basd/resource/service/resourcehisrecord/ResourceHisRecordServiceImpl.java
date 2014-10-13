package com.sitech.basd.resource.service.resourcehisrecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.resource.dao.resourcehisrecord.ResourceHisRecordDao;
import com.sitech.basd.resource.domain.resourcehisrecord.ResourceHisRecordObj;
import com.sitech.utils.xml.XmlProperties;

/**
 * <p>
 * Title: ResourceHisRecordServiceImpl
 * </p>
 * <p>
 * Description: 资源历史记录业务层接口实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-10-20 上午11:49:28
 * 
 */
@Service("resourceHisRecordService")
public class ResourceHisRecordServiceImpl implements ResourceHisRecordService {

	@Autowired
	private ResourceHisRecordDao resourceHisRecordDao;

	private Map<String, String> entryMap;

	private XmlProperties keySet = new XmlProperties(
			"xml/resource_his_record.xml");

	/**
	 * @Title: buildDataXml
	 * @Description: 生成报表数据
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-29 下午2:21:32
	 */
	@Override
	public String buildDataXml(String resourceType, String startTime,
			String endTime) {
		Map<String, List<ResourceHisRecordObj>> map = new HashMap<String, List<ResourceHisRecordObj>>();
		List<String> entryKeyList = new ArrayList<String>();
		String dataXml = "";
		String caption = "";
		String yname = "";
		int div = 1;
		if ("1".equals(resourceType)) {
			entryKeyList.add("vlan_all_count");
			entryKeyList.add("ip_all_count");
			entryKeyList.add("ip_allo_count");
			caption = "网络总量与分配量走势图";
			yname = "个";
			map = this.getRecordMap(entryKeyList, startTime, endTime);
		} else if ("2".equals(resourceType)) {
			entryKeyList.add("vcpu_all_count");
			entryKeyList.add("vcpu_allo_count");
			entryKeyList.add("vmware_vcpu_all_count");
			entryKeyList.add("vmware_vcpu_allo_count");
			entryKeyList.add("xen_vcpu_all_count");
			entryKeyList.add("xen_vcpu_allo_count");
			caption = "cpu总量与分配量走势图";
			yname = "核";
			map = this.getRecordMap(entryKeyList, startTime, endTime);
		} else if ("3".equals(resourceType)) {
			entryKeyList.add("mem_all_count");
			entryKeyList.add("mem_allo_count");
			entryKeyList.add("vmware_mem_all_count");
			entryKeyList.add("vmware_mem_allo_count");
			entryKeyList.add("xen_mem_all_count");
			entryKeyList.add("xen_mem_allo_count");
			caption = "内存总量与分配量走势图";
			yname = "G";
			div = 1024;
			map = this.getRecordMap(entryKeyList, startTime, endTime);
		} else if ("4".equals(resourceType)) {
			entryKeyList.add("vmware_store_all_count");
			entryKeyList.add("vmware_store_allo_count");
			entryKeyList.add("xen_store_all_count");
			entryKeyList.add("xen_store_allo_count");
			caption = "存储总量与分配量走势图";
			yname = "T";
			div = 1024 * 1024;
			map = this.getRecordMap(entryKeyList, startTime, endTime);
		} else if ("5".equals(resourceType)) {
			entryKeyList.add("host_vmware_count");
			entryKeyList.add("host_xen_count");
			entryKeyList.add("host_not_virtual_count");
			entryKeyList.add("vm_vmware_count");
			entryKeyList.add("vm_xen_count");
			caption = "主机与虚拟机总量与分配量走势图";
			yname = "个";
			map = this.getRecordMap(entryKeyList, startTime, endTime);
		}
		dataXml = this.constructXml(map, caption, yname, div);
		return dataXml;
	}

	/**
	 * @Title: constructXml
	 * @Description: 构造报表xml数据
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-29 下午3:56:24
	 */
	private String constructXml(Map<String, List<ResourceHisRecordObj>> map,
			String caption, String yname, int div) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setLabelDisplay("auto");

		Set<String> set = map.keySet();
		for (String string : set) {
			List<ResourceHisRecordObj> list = map.get(string);
			chart.setNumvisibleplot(list.size() + "");
			break;
		}
		chart.setShowvalues("0");
		chart.setCanvasPadding("0");
		chart.setCaption(caption);
		chart.setShowNames("1");
		chart.setBgColor("F0F8FF");
		chart.setCanvasBgColor("F2F2F2");
		chart.setYaxisname(yname);

		// 设置categories
		List<Categories> catsList = new ArrayList<Categories>();
		List<Category> catList = new ArrayList<Category>();

		Categories categories = new Categories();
		for (String string : set) {
			List<ResourceHisRecordObj> list = map.get(string);
			for (ResourceHisRecordObj resourceHisRecordObj : list) {
				Category c = new Category(resourceHisRecordObj.getCOLL_TIME());
				catList.add(c);
			}
			break;
		}
		categories.setCategory(catList);
		catsList.add(categories);

		// 设置dataSet
		List<Dataset> dataSetList = new ArrayList<Dataset>();
		for (String string : set) {
			List<ResourceHisRecordObj> list = map.get(string);
			List<Data> dataList = new ArrayList<Data>();
			for (ResourceHisRecordObj resourceHisRecordObj : list) {
				Data data = new Data();
				data.setValue(Double.parseDouble(resourceHisRecordObj
						.getKPI_VALUE() == null ? "0.0" : resourceHisRecordObj
						.getKPI_VALUE())
						/ div + "");
				dataList.add(data);
			}
			Dataset dataset = new Dataset();
			if (list.size() > 0) {
				dataset.setSeriesname(list.get(0).getDESCR());
			}
			dataset.setRenderas("Line");
			dataset.setAnchorRadius("0.2");
			dataset.setLinethickness("2");
			dataset.setData(dataList);
			dataSetList.add(dataset);
		}

		fusionCharts.setCategories(catsList);
		fusionCharts.setChart(chart);
		fusionCharts.setDataset(dataSetList);
		return JacksonUtil.toJson(fusionCharts);
	}
	/**
	 * @Title: getRecordMap
	 * @Description: 获取走势数据记录
	 * @param
	 * @return
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-29 下午2:36:43
	 */
	private Map<String, List<ResourceHisRecordObj>> getRecordMap(
			List<String> entryKeyList, String startTime, String endTime) {
		Map<String, List<ResourceHisRecordObj>> map = new HashMap<String, List<ResourceHisRecordObj>>();
		Map<String, String> paramMap = new HashMap<String, String>();
		for (String entryKey : entryKeyList) {
			String kpiId = this.getKpiId(entryKey).split("_")[0];
			paramMap.put("kpiId", kpiId);
			paramMap.put("startTime", startTime);
			paramMap.put("endTime", endTime);
			List<ResourceHisRecordObj> list = resourceHisRecordDao
					.queryForListByMap(paramMap);
			map.put(entryKey, list);
		}
		return map;
	}

	/**
	 * @Title: getKpiId
	 * @Description: 获取kpi
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-29 下午2:37:52
	 */
	private String getKpiId(String entryKey) {
		if (entryMap == null) {
			entryMap = keySet.getMap();
		}
		return entryMap.get(entryKey);
	}

}
