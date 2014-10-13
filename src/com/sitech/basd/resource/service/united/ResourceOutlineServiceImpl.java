package com.sitech.basd.resource.service.united;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.cloud3.dao.departproject.DepartHisInfoDao;
import com.sitech.basd.cloud3.domain.departproject.DepartHisInfoObj;
import com.sitech.basd.cloud3.domain.departproject.DepartInfoObj;
import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.resource.dao.global.HostGlobalDao;
import com.sitech.basd.resource.dao.united.ResourceOutlineDao;
import com.sitech.basd.resource.dao.united.ResourceStatisticsDao;
import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.domain.top.TopTargetObj;
import com.sitech.basd.resource.domain.united.ResourceOutlineObj;
import com.sitech.basd.resource.domain.united.ResourceStatisticsObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.util.ResourceStatisticsKey;
import com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao;
import com.sitech.basd.sxcloud.cloud.dao.virtual.TbVirtualDao;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.yicloud.domain.busisystem.BusiSystemObj;
import com.sitech.ssd.ah.fusioncharts.vo.pie.PieChart;
import com.sitech.ssd.ah.fusioncharts.vo.pie.PieData;
import com.sitech.ssd.ah.fusioncharts.vo.pie.PieVO;
import com.sitech.utils.date.TimeformatCommon;
import com.sitech.vo.util.UnitedConstant;

/**
 * 
 * <p>
 * Title: ResourceOutlineServiceImpl
 * </p>
 * <p>
 * Description:首页展示相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-1-22 下午2:47:07
 * 
 */
@Service("resourceOutlineService")
public class ResourceOutlineServiceImpl implements ResourceOutlineService {
	
	@Autowired
	private HostInfoService hostInfoService; 
	
	@Autowired
	private HostInfoDao hostInfoDao;
	
	@Autowired
	private TbVirtualDao tbVirtualDao;

	@Autowired
	private ResourceOutlineDao resourceOutlineDao;
	@Autowired
	private HostGlobalDao hostGlobalDao;
	@Autowired
	private DepartHisInfoDao departHisInfoDao;
	@Autowired
	private UnitedTreeDao unitedTreeDao;
	@Autowired
	private ResourceStatisticsDao resourceStatisticsDao;

	/**
	 * @Title: buildNetChart
	 * @Description: 构造网络fusionChart
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-22 下午3:38:56
	 */
	@Override
	public String buildNetChart() {
		ResourceOutlineObj resourceOutlineObj = new ResourceOutlineObj();
		// 根据子域划分
		List<ResourceOutlineObj> netList = resourceOutlineDao.queryForNetList(resourceOutlineObj);

		// 查询这些子域各自属于什么网络域
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setVtype(UnitedConstant.NETWORKS);
		unitedTreeObj.setType(UnitedConstant.SUBDOMIAN);
		List<UnitedTreeObj> treeList = unitedTreeDao.queryForListUnionParent(unitedTreeObj);
		Map<String, List<ResourceOutlineObj>> map = new HashMap<String, List<ResourceOutlineObj>>();
		for (UnitedTreeObj treeObj : treeList) {
			map.put(treeObj.getParent_name(), new ArrayList<ResourceOutlineObj>());
		}
		w: for (ResourceOutlineObj rolObj : netList) {
			for (UnitedTreeObj treeObj : treeList) {
				if (treeObj.getId().equals(rolObj.getDomainName())) {
					map.get(treeObj.getParent_name()).add(rolObj);
					continue w;
				}
			}
		}

		netList.clear();
		Set<String> keySet = map.keySet();
		for (String string : keySet) {
			List<ResourceOutlineObj> rList = map.get(string);
			ResourceOutlineObj obj = new ResourceOutlineObj();
			obj.setDomainName(string);
			for (ResourceOutlineObj outlineObj : rList) {
				obj.setIpAllCount(obj.getIpAllCount() + outlineObj.getIpAllCount());
				obj.setIpUsedCount(obj.getIpUsedCount() + outlineObj.getIpUsedCount());
			}
			netList.add(obj);
		}

//		FusionCharts fusionCharts = this.constructNetFusionChart(netList);
		PieVO pieVO = this.constructNetFusionChart(netList);
		return JacksonUtil.toJson(pieVO);
	}

	/**
	 * @Title: constructNetFusionChart
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-23 下午4:30:11
	 */
	private PieVO constructNetFusionChart(List<ResourceOutlineObj> netList) {
		PieVO pieVO = new PieVO();
		
		PieChart chart = new PieChart();
		chart.setCaption("各网络域ip总量占比图");
		chart.setBgcolor("FFFFFF,CCCCCC");
		chart.setShowpercetvalues("1");
		chart.setPlotbordercolor("FFFFFF");
		chart.setThousandseparator(",");
		chart.setIssmartlineslanted("0");
		chart.setShowvalue("0");
		chart.setShowlabels("0");
		chart.setShowlegend("1");
		
		pieVO.setChart(chart);
		
		List<PieData> list = new ArrayList<PieData>();
		for (ResourceOutlineObj outlineObj : netList) {
			PieData pieData = new PieData();
			pieData.setValue(outlineObj.getIpAllCount()+"");
		}
		pieVO.setData(list);
		
		return pieVO;
	}

	/**
	 * @Title: constructNetFusionChart
	 * @Description: 构建网络fusionChart实体，水桶状柱图
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-22 下午3:43:06
	 */
	/*private FusionCharts constructNetFusionChart(List<ResourceOutlineObj> netList) {
		FusionCharts fusionCharts = new FusionCharts();

		Chart chart = new Chart();
		chart.setPalette("2");
		chart.setCaption("网络资源统计");
		chart.setShowLabels("1");
		chart.setShowvalues("0");
		chart.setThousandseparator(",");
		chart.setShowsum("1");
		chart.setDecimals("0");
		chart.setLegendborderalpha("0");
		chart.setUseroundedges("1");
		fusionCharts.setChart(chart);

		List<Categories> csList = new ArrayList<Categories>();
		Categories categories = new Categories();
		List<Category> cList = new ArrayList<Category>();
		for (ResourceOutlineObj resourceOutlineObj : netList) {
			Category category = new Category();
			category.setLabel(resourceOutlineObj.getDomainName());
			cList.add(category);
		}
		categories.setCategory(cList);
		csList.add(categories);
		fusionCharts.setCategories(csList);

		List<Dataset> dsList = new ArrayList<Dataset>();
		for (int i = 0; i < 2; i++) {
			Dataset dataset = new Dataset();
			if (i == 0) {
				dataset.setSeriesname("使用量");
				dataset.setColor("FF34B3");
			} else {
				dataset.setSeriesname("余量");
				dataset.setColor("5CACEE");
			}
			dataset.setShowvalues("0");
			List<Data> dataList = new ArrayList<Data>();
			if (i == 0) {
				for (ResourceOutlineObj resourceOutlineObj : netList) {
					Data data = new Data();
					data.setValue(resourceOutlineObj.getIpUsedCount() + "");
					dataList.add(data);
				}
			} else {
				for (ResourceOutlineObj resourceOutlineObj : netList) {
					Data data = new Data();
					data.setValue((resourceOutlineObj.getIpAllCount() - resourceOutlineObj
							.getIpUsedCount()) + "");
					dataList.add(data);
				}
			}
			dataset.setData(dataList);
			dsList.add(dataset);
		}

		fusionCharts.setDataset(dsList);
		return fusionCharts;
	}*/

	@Override
	public List<DepartInfoObj> queryForDepartList(DepartInfoObj departInfoObj) {
		return resourceOutlineDao.queryForList(departInfoObj);
	}
	
	public FusionCharts queryChartDayDataForPie(String type){
		 FusionCharts charts = new FusionCharts();
		 Chart chart = new Chart();
		 chart.setBgcolor("FFFFFF");
		 chart.setEnableRotation("1");
		 chart.setShowborder("0");
		 chart.setPieRadius("60");
		 chart.setShowborder("0");
		 chart.setShowlabels("0");
		 chart.setShowpercentvalues("1");
		 chart.setEnableRotation("0");
		 chart.setShowvalues("0");
		 charts.setChart(chart);
		 List<Data> datas=new ArrayList<Data>();
		 String userCount="0";
		 String noUserCount="0";
		 if("host".equalsIgnoreCase(type)){
			 TbCloud2HostInfoObj obj=new TbCloud2HostInfoObj();
			 obj.setAllocated(0);//未分配
			 List<TbCloud2HostInfoObj> resultList=hostInfoDao.queryForListByObjByAllocated(obj);
			 obj.setAllocated(1);//已分配
			 List<TbCloud2HostInfoObj> resultList1=hostInfoDao.queryForListByObjByAllocated(obj);
			 userCount=resultList1.size()+"";
			 noUserCount=resultList.size()+"";
		 }else if("vm".equalsIgnoreCase(type)){
			 TbCloud2VirtualInfoObj obj=new TbCloud2VirtualInfoObj();
//			 obj.setVH_STAT("1");//开机（已使用）
			 List<TbCloud2VirtualInfoObj> resultList=tbVirtualDao.queryForListByObj(obj);
			 obj.setVH_STAT("0");////关机（未使用）
			 List<TbCloud2VirtualInfoObj> resultList1=tbVirtualDao.queryForListByObj(obj);
			 userCount=resultList.size()-resultList1.size()+"";
			 noUserCount=resultList1.size()+"";
		 }else{
			 System.out.println("类型错误。");
		 }
		 Data d=new Data();
		 d.setLabel("使用");
		 d.setColor("#b3daf8");
		 d.setValue(userCount);
		 Data d1=new Data();
		 d1.setLabel("未使用");
		 d1.setColor("#f6c11d");
		 d1.setValue(noUserCount);
		 datas.add(d1);
		 datas.add(d);
		 charts.setData(datas);
		 return charts;
	}

	@Override
	public String buildXmlData(String resourceType, List<DepartInfoObj> departList,
			String startTime, String endTime) {
		Map<String, List<DepartHisInfoObj>> departMap = new HashMap<String, List<DepartHisInfoObj>>();
		for (DepartInfoObj departInfoObj : departList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("endTime", endTime);
			map.put("startTime", startTime);
			map.put("departId", departInfoObj.getId());
			map.put("resourceType", resourceType);
			List<DepartHisInfoObj> list = departHisInfoDao.queryForListByMap(map);
			departMap.put(departInfoObj.getId(), list);
		}
		String chartXml = null;
		if(departMap.size()>0&&departList.size()>0){
			chartXml = JacksonUtil.toJson(this.buildChart(departMap, departList));
		}
		return chartXml;
	}

	/**
	 * 建立chart
	 * 
	 * @Title: buildChart
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-22 下午4:39:15
	 */
	private FusionCharts buildChart(Map<String, List<DepartHisInfoObj>> departMap, List<DepartInfoObj> departList) {
		Set<String> keySet = departMap.keySet();
		List<DepartHisInfoObj> list = null;
		for (String key : keySet) {
			list = departMap.get(key);
			if (list!=null && list.size()>0)
				break;
		}
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
		// chart.setCanvasBgColor("F2F2F2");
		chart.setCaption("部门预算分配率");
		chart.setLabelDisplay("WARP");
		chart.setXaxisname("分配次数");
		chart.setYaxisname("分配率(百分比)");
		chart.setPlotGradientColor("");
		chart.setShowvalues("0");
//		chart.setShowlabels("0");
		chart.setCanvasPadding("0");

		// categories
		Categories categories = new Categories();
		List<Category> categoryList = new ArrayList<Category>();
		List<Categories> categoriesList = new ArrayList<Categories>();
//		String time1="";
		
		Date minDate=new Date();
		/**
		 * 找出最小的时间
		 */
		for (DepartHisInfoObj departHisInfoObj : list) {
			String time=departHisInfoObj.getColl_time();
			try {
				Date date1=TimeformatCommon.TimeStringToDate(time, "yyyy-MM-dd");
				if(minDate.getTime()>date1.getTime()){
					minDate=date1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * 找出最长的list
		 */
		List<DepartHisInfoObj> maxList=null;
		int num=0;
		for (DepartInfoObj departInfoObj : departList) {
			List<DepartHisInfoObj> dlist = departMap.get(departInfoObj.getId());
			if(num==0){
				maxList=dlist;
				continue;
			}
			if(maxList.size()<dlist.size()){
				maxList=dlist;
			}
		}
		int count=0;
		for (DepartHisInfoObj departHisInfoObj : list) {
			String time=departHisInfoObj.getColl_time();
			/**
			 * 取个最小时间，最大步长
			 */
			if(count==0){
				Category c = new Category(TimeformatCommon.GetCurrentSysTime(minDate, "yyyy-MM-dd"));
				categoryList.add(c);
			}else{
				Category c = new Category(time);
				categoryList.add(c);
			}
			count++;
		}
		
		chart.setNumvisibleplot(maxList.size() + "");
		chart.setLabelstep(maxList.size()+"");
		categories.setCategory(categoryList);
		categoriesList.add(categories);

		// dataset
		List<Dataset> datasetList = new ArrayList<Dataset>();
		DecimalFormat df = new DecimalFormat("#.##");
		for (DepartInfoObj departInfoObj : departList) {
			List<DepartHisInfoObj> dlist = departMap.get(departInfoObj.getId());
			List<Data> dataList = new ArrayList<Data>();
			String time="";
			for (DepartHisInfoObj departHisInfoObj : dlist) {
				String unit="";
				String resourceType=departHisInfoObj.getResource_type()+"";
				Data data1 = new Data();
				double t1=(Double.parseDouble(departHisInfoObj.getResource_used_size() == null ? "0.0"
						: departHisInfoObj.getResource_used_size()));
				double t2=(Double.parseDouble(departHisInfoObj.getResource_size() == null ? "1.0"
						: departHisInfoObj.getResource_size())) ;
				double temp=t1/t2*100;
				time=departHisInfoObj.getColl_time();
//				String tempTime=time.substring(0, 10);
//				if(time.equalsIgnoreCase(tempTime)){
//					continue;
//				}
				String strTemp=df.format(temp);
				if ("1".equals(resourceType)) {
					unit="核数";
					data1.setToolText("时间："+time+" "+"总量："+t2+unit+" , "+"分配率："+strTemp+"%");
				} else if ("2".equals(resourceType)) {
					unit="GB";
					data1.setToolText("时间："+time+" "+"总量："+(t2/1024)+unit+" , "+"分配率："+strTemp+"%");
				} else if ("3".equals(resourceType)) {
					unit="GB";
					data1.setToolText("时间："+time+" "+"总量："+(t2/1024)+unit+" , "+"分配率："+strTemp+"%");
				} else {
					unit="个数";
					data1.setToolText("时间："+time+" "+"总量："+t2+unit+" , "+"分配率："+strTemp+"%");
				}
				data1.setValue(strTemp);
				dataList.add(data1);
			}
			Dataset dataset = new Dataset();
			dataset.setSeriesname(departInfoObj.getName());
			 dataset.setRenderas("Line");
			 dataset.setAnchorRadius("5");
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

	@Override
	public FusionCharts queryResource() {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setPalette("3");
		chart.setCaption("资源概览");
		chart.setYaxisname("");
		chart.setShowvalues("0");// 是否在图形上显示每根柱子具体的值
		chart.setNumvdivlines("10");
		chart.setDivlinealpha("30");
		chart.setDrawanchors("0");
		chart.setLabelpadding("10");
		chart.setYaxisvaluespadding("10");
		chart.setUseroundedges("1");
		chart.setLegendborderalpha("0");
		chart.setFormatNumberScale("0");// 不格式化数字
		fusionCharts.setChart(chart);
		// 横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		List<Category> cList = new ArrayList<Category>();
		Categories categories = new Categories();
		Category category = new Category();
		Category category1 = new Category();
		Category category2 = new Category();
		category.setLabel("CPU(核)");
		category1.setLabel("MEM(G)");
		category2.setLabel("STORAGE(G)");
		cList.add(category);
		cList.add(category1);
		cList.add(category2);
		categories.setCategory(cList);
		categoriesLst.add(categories);
		fusionCharts.setCategories(categoriesLst);
		List<Dataset> datasetList = new ArrayList<Dataset>();
		Dataset dataset1 = new Dataset();
		dataset1.setSeriesname("总量");
		dataset1.setColor("A66EDD");
		List<Data> allResource = this.queryAllResource();
		dataset1.setData(allResource);

		Dataset dataset2 = new Dataset();
		dataset2.setSeriesname("使用量");
		dataset2.setColor("F6BD0F");
		List<Data> usedResource = this.queryUsedResource();
		dataset2.setData(usedResource);

		datasetList.add(dataset1);
		datasetList.add(dataset2);
		fusionCharts.setDataset(datasetList);
		return fusionCharts;
	}

	/**
	 * 
	 * @Title: queryAllResource
	 * @Description: 所以资源
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-22 下午4:15:03
	 */
	private List<Data> queryAllResource() {
		List<Data> dataList = new ArrayList<Data>();
		ResourceOutlineObj cpuAll = new ResourceOutlineObj();
		cpuAll.setKey(ResourceStatisticsKey.VCPU_ALL_COUNT);
		Data cpuAllData = resourceOutlineDao.queryCpuResource(cpuAll);// CPU总量

		ResourceOutlineObj memAll = new ResourceOutlineObj();
		memAll.setKey(ResourceStatisticsKey.MEM_ALL_MB);
		Data memAllData = resourceOutlineDao.queryMemResource(memAll);// mem总量

		ResourceOutlineObj storeAll = new ResourceOutlineObj();
		storeAll.setKey(ResourceStatisticsKey.STORAGE_VALID_MB);
		Data storeAllData = resourceOutlineDao.queryStoreResource(storeAll);// store总量

		dataList.add(cpuAllData);
		dataList.add(memAllData);
		dataList.add(storeAllData);
		return dataList;
	}

	/**
	 * 
	 * @Title: queryUsedResource
	 * @Description: 已用资源
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-22 下午4:15:16
	 */
	private List<Data> queryUsedResource() {
		List<Data> dataList = new ArrayList<Data>();
		ResourceOutlineObj cpuUsed = new ResourceOutlineObj();
		cpuUsed.setKey(ResourceStatisticsKey.VCPU_USED_COUNT);
		Data cpuUsedData = resourceOutlineDao.queryCpuResource(cpuUsed);// CPU使用量

		ResourceOutlineObj memUsed = new ResourceOutlineObj();
		memUsed.setKey(ResourceStatisticsKey.MEM_USED_MB);
		Data memUsedData = resourceOutlineDao.queryMemResource(memUsed);// mem使用量

		ResourceOutlineObj storeUsed = new ResourceOutlineObj();
		storeUsed.setKey(ResourceStatisticsKey.STORAGE_ALLO_MB);
		Data storeUsedData = resourceOutlineDao.queryStoreResource(storeUsed);// store使用量

		dataList.add(cpuUsedData);
		dataList.add(memUsedData);
		dataList.add(storeUsedData);
		return dataList;
	}

	/**
	 * 
	 * @Title: queryHostVmsData
	 * @Description: 构造主机及虚拟机数据
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-22 下午7:53:32
	 */
	@Override
	public FusionCharts queryHostVmsData() {
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		chart.setPalette("2");
		chart.setCaption("服务器统计");
		chart.setShowLabels("1");
		chart.setShowvalues("0");
		// chart.setNumberprefix("$");
		chart.setShowsum("1");
		chart.setDecimals("0");
		chart.setUseroundedges("1");
		chart.setLegendborderalpha("0");
		charts.setChart(chart);
		// //构造横轴数据---------------------------
		List<Category> cateList = new ArrayList<Category>();
		Category x86 = new Category();
		x86.setLabel("X86服务器");
		cateList.add(x86);
		Category frame = new Category();
		frame.setLabel("机架服务器");
		cateList.add(frame);
		Category vmware = new Category();
		vmware.setLabel("VMware虚拟机");
		cateList.add(vmware);
		Category xen = new Category();
		xen.setLabel("Xen虚拟机");
		cateList.add(xen);
		Categories cas = new Categories();
		cas.setCategory(cateList);
		List<Categories> catesList = new ArrayList<Categories>();
		catesList.add(cas);
		charts.setCategories(catesList);
		// 构造横轴数据结束-------------------------------
		// 构造纵轴数据-----------------------------------
		List<Data> runList = new ArrayList<Data>();

		List<Data> stopList = new ArrayList<Data>();
		List<Data> seriousList = new ArrayList<Data>();
		// /正在运行的主机个数，以及正常的虚拟机个数
		ResourceStatisticsObj obj = new ResourceStatisticsObj();
		Data x86_run = new Data();
		x86_run.setValue("4");
		runList.add(x86_run);
		Data frame_run = new Data();
		frame_run.setValue("9");
		runList.add(frame_run);
		// 查询正在运行的虚拟机个数
		obj.setKey("vm_vmware_run_count");
		List<ResourceStatisticsObj> list = resourceStatisticsDao.queryForListByObj(obj);
		if (list != null && list.size() > 0) {
			Data vmware_run = new Data();
			vmware_run.setValue(list.get(0).getValue());
			runList.add(vmware_run);
		}
		// 查询正在运行的xen虚拟机的个数
		obj.setKey("vm_xen_run_count");
		list = resourceStatisticsDao.queryForListByObj(obj);
		if (list != null && list.size() > 0) {
			Data xen_run = new Data();
			xen_run.setValue(list.get(0).getValue());
			runList.add(xen_run);
		}

		// 停止的主机个数和虚拟机个数
		x86_run.setValue("4");
		stopList.add(x86_run);
		frame_run.setValue("9");
		stopList.add(frame_run);
		// 查询正在运行的虚拟机个数
		obj.setKey("vm_vmware_stop_count");
		list = resourceStatisticsDao.queryForListByObj(obj);
		if (list != null && list.size() > 0) {
			Data vmware_run = new Data();
			vmware_run.setValue(list.get(0).getValue());
			stopList.add(vmware_run);
		}
		// 查询正在运行的xen虚拟机的个数
		obj.setKey("vm_xen_stop_count");
		list = resourceStatisticsDao.queryForListByObj(obj);
		if (list != null && list.size() > 0) {
			Data xen_run = new Data();
			xen_run.setValue(list.get(0).getValue());
			stopList.add(xen_run);
		}

		// 查询异常的主机及虚拟机
		x86_run.setValue("4");
		seriousList.add(x86_run);
		frame_run.setValue("8");
		seriousList.add(frame_run);
		// 查询正在运行的虚拟机个数
		obj.setKey("vmware_serious_count");
		list = resourceStatisticsDao.queryForListByObj(obj);
		if (list != null && list.size() > 0) {
			Data vmware_run = new Data();
			vmware_run.setValue(list.get(0).getValue());
			seriousList.add(vmware_run);
		}
		// 查询正在运行的xen虚拟机的个数
		obj.setKey("xen_serious_count");
		list = resourceStatisticsDao.queryForListByObj(obj);
		if (list != null && list.size() > 0) {
			Data xen_run = new Data();
			xen_run.setValue(list.get(0).getValue());
			seriousList.add(xen_run);
		}
		Dataset set1 = new Dataset();
		Dataset set2 = new Dataset();
		Dataset set3 = new Dataset();
		List<Dataset> setList = new ArrayList<Dataset>();
		set1.setData(runList);
		set1.setSeriesname("运行");
		set2.setData(stopList);
		set2.setSeriesname("停止");
		set3.setData(seriousList);
		set3.setSeriesname("异常");
		setList.add(set1);
		setList.add(set2);
		setList.add(set3);
		charts.setDataset(setList);
		return charts;
	}

	/**
	 * @Title: buildTopNChartXml
	 * @Description: topN
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014-1-23 下午7:24:56
	 */
	@Override
	public String buildTopNChartXml(Map<String, Object> map) throws Exception {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		List<Data> dataList = new ArrayList<Data>();
		String resourceType = (String) map.get("type");
		if ("1".equals(resourceType)) {// cpu
			dataList = hostGlobalDao.queryHostCpuTopN(map);
			chart.setCaption("宿主机Cpu Top 5 报表");
			chart.setXaxisname("宿主机名称");
			chart.setYaxisname("Cpu使用量");
			chart.setNumberSuffix("%");
		} else if ("2".equals(resourceType)) {// 内存
			dataList = hostGlobalDao.queryHostMemTopN(map);
			chart.setCaption("宿主机内存 Top 5 报表");
			chart.setXaxisname("宿主机名称");
			chart.setYaxisname("内存使用量");
			chart.setNumberSuffix("%");
		} else {// 存储
			dataList = hostGlobalDao.queryHostStorageTopN(map);
			chart.setCaption("宿主机存储 Top 5 报表");
			chart.setXaxisname("宿主机名称");
			chart.setYaxisname("存储使用量");
			chart.setNumberSuffix("%");
		}
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		return JacksonUtil.toJson(fusionCharts);
	}

	/**
	 * 
	 * @Title: queryStore
	 * @Description: 查询存储总量和使用量
	 * @param
	 * @return ResourceOutlineObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-23 下午8:09:24
	 */
	@Override
	public ResourceOutlineObj queryStore(ResourceOutlineObj obj) {
		return resourceOutlineDao.queryStore(obj);
	}


	/**
	 * @Title: buildBusiTopNChartJSON
	 * @Description: 创建业务系统资源占用topN
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 上午9:30:10
	 */
	@Override
	public String buildBusiTopNChartJSON(Map<String, Object> paramMap) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();

		String resourceType = (String) paramMap.get("resourceType");
		if ("1".equals(resourceType)) {
			List<Data> dataList = resourceOutlineDao.queryVMCountGroupByBusi(paramMap);
			for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
				Data data = (Data) iterator.next();
				data.setDisplayValue(data.getLabel()+"："+data.getValue()+"台");
			}
			int count = 0;
			for (Data data : dataList) {
				if (count == 0) {
					data.setColor("8BC1E5");
				} else if (count ==1) {
					data.setColor("F79263");
				} else if (count == 2) {
					data.setColor("ADC85A");
				} else if (count == 3) {
					data.setColor("D48BDE");
				} else if (count == 4) {
					data.setColor("4EEE94");
				}
				count ++;
			}
			chart.setYaxisname("单位:个数");
//			chart.setCaption("业务系统虚拟机个数top"+paramMap.get("top_num"));
			fusionCharts.setData(dataList);
		} else {
			List<Data> dataList = resourceOutlineDao.queryStoreCountGroupByBusi(paramMap);
			int count = 0;
			for (Data data : dataList) {
				if (count == 0) {
					data.setColor("FF6347");
				} else if (count ==1) {
					data.setColor("CD69C9");
				} else if (count == 2) {
					data.setColor("EEC900");
				} else if (count == 3) {
					data.setColor("8EE5EE");
				} else if (count == 4) {
					data.setColor("4EEE94");
				}
				count ++;
			}
			chart.setYaxisname("单位:TB");
			chart.setCaption("业务系统存储大小top"+paramMap.get("top_num"));
			chart.setDecimals("2");
			fusionCharts.setData(dataList);
		}
		chart.setPlotGradientColor("");
		chart.setThousandseparator(",");
//		chart.setUseroundedges("1");
		chart.setBgcolor("FFFFFF,FFFFFF");
		chart.setShowborder("0");
		chart.setCanvasbordercolor("E6E6E6");
//		chart.setFormatnumberscale("0");
		fusionCharts.setChart(chart);
		return JacksonUtil.toJson(fusionCharts);
	}

	/**
	 * @Title: queryBusiSystemTopList
	 * @Description: 查询虚拟机占用资源topN列表
	 * @param
	 * @return List<BusiSystemObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 下午2:00:06
	 */
	@Override
	public List<BusiSystemObj> queryBusiSystemTopList(Map<String, Object> paramMap) {
		List<BusiSystemObj> list = new ArrayList<BusiSystemObj>();
		String resourceType = (String) paramMap.get("resourceType");
		if ("1".equals(resourceType)) {
			list = resourceOutlineDao.queryBusiSystemVmTopList(paramMap);
		} else {
			list = resourceOutlineDao.queryBusiSystemStoreTopList(paramMap);
			DecimalFormat df = new DecimalFormat(".00");
			for (BusiSystemObj busiSystemObj : list) {
				busiSystemObj.setStoreSize(Double.parseDouble(df.format(busiSystemObj.getStoreSize())));
			}
		}
		return list;
	}

	/**
	 * @Title: queryHostTopList
	 * @Description: 查询主机topN列表
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 下午5:57:53
	 */
	@Override
	public List<TopTargetObj> queryHostTopList(String resourceType, TopTargetObj topTargetObj) {
		List<TopTargetObj> list = new ArrayList<TopTargetObj>();
		if ("1".equals(resourceType)) {
			list = resourceOutlineDao.queryCpuTopList(topTargetObj);
		} else if ("2".equals(resourceType)) {
			list = resourceOutlineDao.queryMemTopList(topTargetObj);
		} else {
			list = resourceOutlineDao.queryStoreTopList(topTargetObj);
		}
		DecimalFormat df = new DecimalFormat(".00");
		for (TopTargetObj targetObj : list) {
			targetObj.setStore_usage(Double.parseDouble(df.format(targetObj.getStore_usage())));
		}
		return list;
	}

	/**
	 * @Title: queryValidStore
	 * @Description: 查询有效存储
	 * @param
	 * @return ResourceOutlineObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午12:54:13
	 */
	@Override
	public ResourceOutlineObj queryValidStore(ResourceOutlineObj resourceOutlineObj) {
		return resourceOutlineDao.queryValidStore(resourceOutlineObj);
	}

	/**
	 * @Title: queryVmTopList
	 * @Description: 查询虚拟机topN列表
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午2:14:15
	 */
	@Override
	public List<TopTargetObj> queryVmTopList(String resourceType, TopTargetObj topTargetObj) {
		List<TopTargetObj> list = new ArrayList<TopTargetObj>();
		if ("1".equals(resourceType)) {
			list = resourceOutlineDao.queryCpuTopListVM(topTargetObj);
		} else {
			list = resourceOutlineDao.queryMemTopListVM(topTargetObj);
		}
		return list;
	}

	/**
	 * @Title: queryVmHostConfigInfo
	 * @Description: 查询虚拟机配置信息
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午6:44:49
	 */
	@Override
	public VMHostObj queryVmHostConfigInfo(TopTargetObj topTargetObj) {
		return resourceOutlineDao.queryVmHostConfigInfo(topTargetObj);
	}
	
}
