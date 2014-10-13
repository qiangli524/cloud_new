package com.sitech.basd.ibmmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.ibmmanager.dao.IBMManagerTabDao;
import com.sitech.basd.ibmmanager.domain.CloudOSIndexObj;
import com.sitech.basd.ibmmanager.domain.IBMIndexObj;
import com.sitech.basd.ibmmanager.domain.IBMManagerTabObj;
import com.sitech.basd.ibmmanager.domain.IBMManagerTreeObj;
import com.sitech.basd.ibmmanager.domain.LogicalPartitionObj;
import com.sitech.basd.ibmmanager.domain.PowerObj;
import com.sitech.basd.ibmmanager.util.IBMConstant;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;

/**
 * 
 * <p>
 * Title: IBMManagerTabService
 * </p>
 * <p>
 * Description: 相应Tab页得操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-11-14 下午3:11:56
 * 
 */
@Service("ibmManagerTabService")
public class IBMManagerTabServiceImpl implements IBMManagerTabService {
	@Autowired
	private IBMManagerTabDao ibmManagerTabDao;

	/**
	 * 
	 * @Title: queryLparInfo
	 * @Description: 查询lpar摘要
	 * @param
	 * @return LogicalPartitionObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-5 下午3:54:51
	 */
	@Override
	public LogicalPartitionObj queryLparInfo(LogicalPartitionObj obj) {
		return ibmManagerTabDao.queryLparInfo(obj);
	}

	/**
	 * 
	 * @Title: queryPowerInfo
	 * @Description: 查询power摘要
	 * @param
	 * @return PowerObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-5 下午4:34:03
	 */
	@Override
	public PowerObj queryPowerInfo(PowerObj obj) {
		return ibmManagerTabDao.queryPowerInfo(obj);
	}

	/**
	 * 
	 * @Title: queryVMInfo
	 * @Description: 查询虚拟机摘要
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-11 上午9:59:00
	 */
	@Override
	public VMHostObj queryVMInfo(VMHostObj obj) {
		return ibmManagerTabDao.queryVMInfo(obj);
	}

	/**
	 * 
	 * @Title: queryCountByObj
	 * @Description:查询IBM个数
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-11 下午4:54:58
	 */
	@Override
	public int queryCountByObj(IBMManagerTreeObj obj) {
		return ibmManagerTabDao.queryCountByObj(obj);
	}

	/**
	 * 
	 * @Title: queryLparCountByOBj
	 * @Description: 通过hmc的id查询出lpar的个数
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-11 下午8:43:50
	 */
	@Override
	public int queryLparCountByOBj(IBMManagerTreeObj obj) {
		return ibmManagerTabDao.queryLparCountByOBj(obj);
	}

	/**
	 * 
	 * @Title: queryIBMPowerResource
	 * @Description: 主机资源使用情况
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-12 下午9:57:36
	 */
	@Override
	public IBMManagerTabObj queryIBMPowerResource(IBMManagerTreeObj obj) {
		List<IBMManagerTabObj> list = ibmManagerTabDao
				.queryIBMPowerResource(obj);
		IBMManagerTabObj ibmObj = new IBMManagerTabObj();
		if (list != null && list.size() > 0) {
			ibmObj = list.get(0);
		}
		return ibmObj;
	}

	/**
	 * 
	 * @Title: queryIBMHmcResource
	 * @Description: 查询HMC的资源使用情况
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 上午9:28:59
	 */
	@Override
	public IBMManagerTabObj queryIBMHmcResource(IBMManagerTreeObj obj) {
		return ibmManagerTabDao.queryIBMHmcResource(obj);
	}

	/**
	 * 
	 * @Title: queryHostAllResource
	 * @Description: X86查询每个主机总资源
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午3:59:30
	 */
	@Override
	public IBMManagerTabObj queryHostAllResource(IBMManagerTreeObj obj) {
		return ibmManagerTabDao.queryHostAllResource(obj);
	}

	/**
	 * 
	 * @Title: queryHostUsedResource
	 * @Description: X86查询每个主机已用资源
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午4:02:10
	 */
	@Override
	public IBMManagerTabObj queryHostUsedResource(IBMManagerTreeObj obj) {
		return ibmManagerTabDao.queryHostUsedResource(obj);
	}

	/**
	 * 
	 * @Title: queryClusterAllResource
	 * @Description: X86查询每个集群总资源
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午5:14:27
	 */
	@Override
	public IBMManagerTabObj queryClusterAllResource(IBMManagerTreeObj obj) {
		return ibmManagerTabDao.queryClusterAllResource(obj);
	}

	/**
	 * 
	 * @Title: queryClusterUsedResource
	 * @Description: X86查询每个集群已用资源
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午5:15:00
	 */
	@Override
	public IBMManagerTabObj queryClusterUsedResource(IBMManagerTreeObj obj) {
		return ibmManagerTabDao.queryClusterUsedResource(obj);
	}

	/**
	 * 
	 * @Title: queryAllResource
	 * @Description: 查询x86总资源
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午5:42:16
	 */
	@Override
	public IBMManagerTabObj queryAllResource(IBMManagerTreeObj obj) {
		return ibmManagerTabDao.queryAllResource(obj);
	}

	/**
	 * 
	 * @Title: queryUsedResource
	 * @Description: 查询x86已用资源
	 * @param
	 * @return IBMManagerTabObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-13 下午5:42:48
	 */
	@Override
	public IBMManagerTabObj queryUsedResource(IBMManagerTreeObj obj) {
		return ibmManagerTabDao.queryUsedResource(obj);
	}

	/**
	 * 
	 * @Title: queryLparIndexCount
	 * @Description: 查询Lpar指标个数
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-14 下午4:32:48
	 */
	@Override
	public int queryLparIndexCount(LogicalPartitionObj obj) {
		return ibmManagerTabDao.queryLparIndexCount(obj);
	}

	/**
	 * 
	 * @Title: splitLparUuid
	 * @Description: 拆分Lpar的uuid(因为lpar的uuid存的是 hostId_lparId)
	 * @param
	 * @return LogicalPartitionObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-14 下午5:26:25
	 */
	@Override
	public LogicalPartitionObj splitLparUuid(String uuid) {
		LogicalPartitionObj obj = new LogicalPartitionObj();
		String[] ss = new String[10];
		ss = uuid.split("_");
		obj.setMachineserialnumber(ss[0]);
		obj.setLpar_id(ss[1]);
		return obj;
	}

	/**
	 * 
	 * @Title: queryHostFCSpeed
	 * @Description: 查询主机光纤卡读写速度
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-15 上午10:50:25
	 */
	@Override
	public FusionCharts queryIBMIndex(IBMIndexObj obj) {
		FusionCharts fusionCharts = new FusionCharts();
		LogicalPartitionObj lparObj = new LogicalPartitionObj();
		if (IBMConstant.POWER_FCSPEED.equals(obj.getFlag())) {// power光纤卡
			obj.setTableName("SX_POWER_FC_SPEED");
			Chart chart = new Chart();
			chart.setCaption("power光纤卡流量信息");
			chart.setXaxisname("时间");
			chart.setYaxisname("速率");
			chart.setNumvisibleplot("336");// 显示多少个节点
			chart.setLabelstep("48");// x轴坐标值的步长，即可以设置隔几个柱子显示一个值
			chart.setLabelDisplay("ROTATE");// x轴坐标值的具体展现形式
			chart.setShowvalues("0");// 是否在图形上显示每根柱子具体的值
			chart.setCanvasPadding("0");// 图两边的空的距离
			fusionCharts.setChart(chart);
			// 横坐标
			List<Categories> categoriesLst = new ArrayList<Categories>();
			List<Category> cList = new ArrayList<Category>();
			Categories categories = new Categories();
			cList = ibmManagerTabDao.queryIBMIndexTimeLable(obj);
			categories.setCategory(cList);
			categoriesLst.add(categories);
			fusionCharts.setCategories(categoriesLst);
			List<Dataset> dataset = new ArrayList<Dataset>();
			// 读速度
			List<Data> data1 = new ArrayList<Data>();
			Dataset dataset1 = new Dataset();
			dataset1.setSeriesname("读速度");
			dataset1.setRenderas("Line");
			dataset1.setAnchorRadius("0.2");
			dataset1.setLinethickness("1");
			data1 = ibmManagerTabDao.queryFCReadSpeed(obj);
			dataset1.setData(data1);
			dataset.add(dataset1);
			// 写速度
			List<Data> data2 = new ArrayList<Data>();
			Dataset dataset2 = new Dataset();
			dataset2.setSeriesname("写速度");
			dataset2.setRenderas("Line");
			dataset2.setAnchorRadius("0.2");// 折线节点半径
			dataset2.setLinethickness("1");// 折线的厚度
			data2 = ibmManagerTabDao.queryFCWriteSpeed(obj);
			dataset2.setData(data2);
			dataset.add(dataset2);
			fusionCharts.setDataset(dataset);
		} else if (IBMConstant.POWER_SEASPEED.equals(obj.getFlag())) {// power网络性能信息
			obj.setTableName("SX_POWER_SEA_SPEED");

			Chart chart = new Chart();
			chart.setCaption("power网络性能信息");
			chart.setXaxisname("时间");
			chart.setYaxisname("速率");
			chart.setNumvisibleplot("336");
			chart.setLabelstep("48");
			chart.setLabelDisplay("ROTATE");
			chart.setShowvalues("0");
			chart.setCanvasPadding("0");
			fusionCharts.setChart(chart);
			// 横坐标
			List<Categories> categoriesLst = new ArrayList<Categories>();
			List<Category> cList = new ArrayList<Category>();
			Categories categories = new Categories();
			cList = ibmManagerTabDao.queryIBMIndexTimeLable(obj);
			categories.setCategory(cList);
			categoriesLst.add(categories);
			fusionCharts.setCategories(categoriesLst);
			List<Dataset> dataset = new ArrayList<Dataset>();
			// 读速度
			List<Data> data1 = new ArrayList<Data>();
			Dataset dataset1 = new Dataset();
			dataset1.setSeriesname("读速度");
			dataset1.setRenderas("Line");
			dataset1.setAnchorRadius("0.2");
			dataset1.setLinethickness("1");
			data1 = ibmManagerTabDao.querySEAAndNetCardReadSpeed(obj);
			dataset1.setData(data1);
			dataset.add(dataset1);
			// 写速度
			List<Data> data2 = new ArrayList<Data>();
			Dataset dataset2 = new Dataset();
			dataset2.setSeriesname("写速度");
			dataset2.setRenderas("Line");
			dataset2.setAnchorRadius("0.2");// 折线节点半径
			dataset2.setLinethickness("1");// 折线的厚度
			data2 = ibmManagerTabDao.querySEAAndNetCardWriteSpeed(obj);
			dataset2.setData(data2);
			dataset.add(dataset2);
			fusionCharts.setDataset(dataset);
		} else if (IBMConstant.POWER_NETCARD_PERFORMANCE.equals(obj.getFlag())) {// power网卡流量监控
			obj.setTableName("SX_LPAR_NETCARD_PERFORMANCE");

			Chart chart = new Chart();
			chart.setCaption("power网卡流量监控");
			chart.setXaxisname("时间");
			chart.setYaxisname("速率");
			chart.setNumvisibleplot("336");
			chart.setLabelstep("48");
			chart.setLabelDisplay("ROTATE");
			chart.setShowvalues("0");
			chart.setCanvasPadding("0");
			fusionCharts.setChart(chart);
			// 横坐标
			List<Categories> categoriesLst = new ArrayList<Categories>();
			List<Category> cList = new ArrayList<Category>();
			Categories categories = new Categories();
			cList = ibmManagerTabDao.queryIBMIndexTimeLable(obj);
			categories.setCategory(cList);
			categoriesLst.add(categories);
			fusionCharts.setCategories(categoriesLst);
			List<Dataset> dataset = new ArrayList<Dataset>();
			// 读速度
			List<Data> data1 = new ArrayList<Data>();
			Dataset dataset1 = new Dataset();
			dataset1.setSeriesname("读速度");
			dataset1.setRenderas("Line");
			dataset1.setAnchorRadius("0.2");
			dataset1.setLinethickness("1");
			data1 = ibmManagerTabDao.querySEAAndNetCardReadSpeed(obj);
			dataset1.setData(data1);
			dataset.add(dataset1);
			// 写速度
			List<Data> data2 = new ArrayList<Data>();
			Dataset dataset2 = new Dataset();
			dataset2.setSeriesname("写速度");
			dataset2.setRenderas("Line");
			dataset2.setAnchorRadius("0.2");// 折线节点半径
			dataset2.setLinethickness("1");// 折线的厚度
			data2 = ibmManagerTabDao.querySEAAndNetCardWriteSpeed(obj);
			dataset2.setData(data2);
			dataset.add(dataset2);
			fusionCharts.setDataset(dataset);
		} else if (IBMConstant.LPAR_FCSPEED.equals(obj.getFlag())) {// lpar光纤卡
			obj.setTableName("SX_LPAR_FC_SPEED");
			lparObj = splitLparUuid(obj.getMachine_serial_number());
			obj.setMachine_serial_number(lparObj.getMachineserialnumber());
			obj.setLpar_id(lparObj.getLpar_id());

			Chart chart = new Chart();
			chart.setCaption("lpar光纤卡流量信息");
			chart.setXaxisname("时间");
			chart.setYaxisname("速率");
			chart.setNumvisibleplot("336");
			chart.setLabelstep("48");
			chart.setLabelDisplay("ROTATE");
			chart.setShowvalues("0");
			chart.setCanvasPadding("0");
			fusionCharts.setChart(chart);
			// 横坐标
			List<Categories> categoriesLst = new ArrayList<Categories>();
			List<Category> cList = new ArrayList<Category>();
			Categories categories = new Categories();
			cList = ibmManagerTabDao.queryIBMIndexTimeLable(obj);
			categories.setCategory(cList);
			categoriesLst.add(categories);
			fusionCharts.setCategories(categoriesLst);
			List<Dataset> dataset = new ArrayList<Dataset>();
			// 读速度
			List<Data> data1 = new ArrayList<Data>();
			Dataset dataset1 = new Dataset();
			dataset1.setSeriesname("读速度");
			dataset1.setRenderas("Line");
			dataset1.setAnchorRadius("0.2");
			dataset1.setLinethickness("1");
			data1 = ibmManagerTabDao.queryFCReadSpeed(obj);
			dataset1.setData(data1);
			dataset.add(dataset1);
			// 写速度
			List<Data> data2 = new ArrayList<Data>();
			Dataset dataset2 = new Dataset();
			dataset2.setSeriesname("写速度");
			dataset2.setRenderas("Line");
			dataset2.setAnchorRadius("0.2");// 折线节点半径
			dataset2.setLinethickness("1");// 折线的厚度
			data2 = ibmManagerTabDao.queryFCWriteSpeed(obj);
			dataset2.setData(data2);
			dataset.add(dataset2);
			fusionCharts.setDataset(dataset);
		} else if (IBMConstant.LPAR_DISKSPEED.equals(obj.getFlag())) {// lpar磁盘的读写速度
			obj.setTableName("SX_LPAR_DISK_SPEED");
			lparObj = splitLparUuid(obj.getMachine_serial_number());
			obj.setMachine_serial_number(lparObj.getMachineserialnumber());
			obj.setLpar_id(lparObj.getLpar_id());

			Chart chart = new Chart();
			chart.setCaption("lpar磁盘的读写速度");
			chart.setXaxisname("时间");
			chart.setYaxisname("速率");
			chart.setNumvisibleplot("336");
			chart.setLabelstep("48");
			chart.setLabelDisplay("ROTATE");
			chart.setShowvalues("0");
			chart.setCanvasPadding("0");
			fusionCharts.setChart(chart);
			// 横坐标
			List<Categories> categoriesLst = new ArrayList<Categories>();
			List<Category> cList = new ArrayList<Category>();
			Categories categories = new Categories();
			cList = ibmManagerTabDao.queryIBMIndexTimeLable(obj);
			categories.setCategory(cList);
			categoriesLst.add(categories);
			fusionCharts.setCategories(categoriesLst);
			List<Dataset> dataset = new ArrayList<Dataset>();
			// 读速度
			List<Data> data1 = new ArrayList<Data>();
			Dataset dataset1 = new Dataset();
			dataset1.setSeriesname("读速度");
			dataset1.setRenderas("Line");
			dataset1.setAnchorRadius("0.2");
			dataset1.setLinethickness("1");
			data1 = ibmManagerTabDao.queryLparDiskReadSpeed(obj);
			dataset1.setData(data1);
			dataset.add(dataset1);
			// 写速度
			List<Data> data2 = new ArrayList<Data>();
			Dataset dataset2 = new Dataset();
			dataset2.setSeriesname("写速度");
			dataset2.setRenderas("Line");
			dataset2.setAnchorRadius("0.2");// 折线节点半径
			dataset2.setLinethickness("1");// 折线的厚度
			data2 = ibmManagerTabDao.queryLparDiskWriteSpeed(obj);
			dataset2.setData(data2);
			dataset.add(dataset2);
			fusionCharts.setDataset(dataset);
		} else if (IBMConstant.LPAR_NETCARD_PERFORMANCE.equals(obj.getFlag())) {// lpar网卡流量监控
			obj.setTableName("SX_LPAR_NETCARD_PERFORMANCE");
			lparObj = splitLparUuid(obj.getMachine_serial_number());
			obj.setMachine_serial_number(lparObj.getMachineserialnumber());
			obj.setLpar_id(lparObj.getLpar_id());

			Chart chart = new Chart();
			chart.setCaption("lpar网卡流量监控");
			chart.setXaxisname("时间");
			chart.setYaxisname("速率");
			chart.setNumvisibleplot("336");
			chart.setLabelstep("48");
			chart.setLabelDisplay("ROTATE");
			chart.setShowvalues("0");
			chart.setCanvasPadding("0");
			fusionCharts.setChart(chart);
			// 横坐标
			List<Categories> categoriesLst = new ArrayList<Categories>();
			List<Category> cList = new ArrayList<Category>();
			Categories categories = new Categories();
			cList = ibmManagerTabDao.queryIBMIndexTimeLable(obj);
			categories.setCategory(cList);
			categoriesLst.add(categories);
			fusionCharts.setCategories(categoriesLst);
			List<Dataset> dataset = new ArrayList<Dataset>();
			// 读速度
			List<Data> data1 = new ArrayList<Data>();
			Dataset dataset1 = new Dataset();
			dataset1.setSeriesname("读速度");
			dataset1.setRenderas("Line");
			dataset1.setAnchorRadius("0.2");
			dataset1.setLinethickness("1");
			data1 = ibmManagerTabDao.querySEAAndNetCardReadSpeed(obj);
			dataset1.setData(data1);
			dataset.add(dataset1);
			// 写速度
			List<Data> data2 = new ArrayList<Data>();
			Dataset dataset2 = new Dataset();
			dataset2.setSeriesname("写速度");
			dataset2.setRenderas("Line");
			dataset2.setAnchorRadius("0.2");// 折线节点半径
			dataset2.setLinethickness("1");// 折线的厚度
			data2 = ibmManagerTabDao.querySEAAndNetCardWriteSpeed(obj);
			dataset2.setData(data2);
			dataset.add(dataset2);
			fusionCharts.setDataset(dataset);
		}
		return fusionCharts;
	}

	/**
	 * 
	 * @Title: queryIOSpeed
	 * @Description: 存储监控指标
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-19 下午6:55:01
	 */
	@Override
	public FusionCharts queryIOSpeed(CloudOSIndexObj obj) {

		FusionCharts fusionCharts = new FusionCharts();
		if (IBMConstant.HOST.equals(obj.getType())) {
			obj.setTableName("TB_CLOUD2_HOST_COLL");
		} else if (IBMConstant.VM.equals(obj.getType())) {
			obj.setTableName("TB_CLOUD2_HY_COLL");
		}
		Chart chart = new Chart();
		chart.setCaption("存储监控指标");
		chart.setXaxisname("时间");
		chart.setYaxisname("速率");
		chart.setNumvisibleplot("336");// 显示多少个节点
		chart.setLabelstep("48");// x轴坐标值的步长，即可以设置隔几个柱子显示一个值
		chart.setLabelDisplay("ROTATE");// x轴坐标值的具体展现形式
		chart.setShowvalues("0");// 是否在图形上显示每根柱子具体的值
		chart.setCanvasPadding("0");// 图两边的空的距离
		fusionCharts.setChart(chart);
		// 横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		List<Category> cList = new ArrayList<Category>();
		Categories categories = new Categories();
		cList = ibmManagerTabDao.queryCloudOSTimeLable(obj);
		categories.setCategory(cList);
		categoriesLst.add(categories);
		fusionCharts.setCategories(categoriesLst);
		List<Dataset> datasetList = new ArrayList<Dataset>();

		List<Data> data = new ArrayList<Data>();
		Dataset dataset = new Dataset();
		dataset.setSeriesname("速度");
		dataset.setRenderas("Line");
		dataset.setAnchorRadius("0.2");
		dataset.setLinethickness("1");
		data = ibmManagerTabDao.queryIOAndNetSpeed(obj);
		dataset.setData(data);
		datasetList.add(dataset);
		fusionCharts.setDataset(datasetList);
		return fusionCharts;
	}

	/**
	 * 
	 * @Title: queryNetSpeed
	 * @Description: 网络监控指标
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-19 下午7:57:31
	 */
	@Override
	public FusionCharts queryNetSpeed(CloudOSIndexObj obj) {
		FusionCharts fusionCharts = new FusionCharts();
		if (IBMConstant.HOST.equals(obj.getType())) {
			obj.setTableName("TB_CLOUD2_HOST_COLL");
		} else if (IBMConstant.VM.equals(obj.getType())) {
			obj.setTableName("TB_CLOUD2_HY_COLL");
		}
		Chart chart = new Chart();
		chart.setCaption("网络监控指标");
		chart.setXaxisname("时间");
		chart.setYaxisname("速率");
		chart.setNumvisibleplot("336");// 显示多少个节点
		chart.setLabelstep("48");// x轴坐标值的步长，即可以设置隔几个柱子显示一个值
		chart.setLabelDisplay("ROTATE");// x轴坐标值的具体展现形式
		chart.setShowvalues("0");// 是否在图形上显示每根柱子具体的值
		chart.setCanvasPadding("0");// 图两边的空的距离
		fusionCharts.setChart(chart);
		// 横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		List<Category> cList = new ArrayList<Category>();
		Categories categories = new Categories();
		cList = ibmManagerTabDao.queryCloudOSTimeLable(obj);
		categories.setCategory(cList);
		categoriesLst.add(categories);
		fusionCharts.setCategories(categoriesLst);
		List<Dataset> datasetList = new ArrayList<Dataset>();

		List<Data> data = new ArrayList<Data>();
		Dataset dataset = new Dataset();
		dataset.setSeriesname("速度");
		dataset.setRenderas("Line");
		dataset.setAnchorRadius("0.2");
		dataset.setLinethickness("1");
		data = ibmManagerTabDao.queryIOAndNetSpeed(obj);
		dataset.setData(data);
		datasetList.add(dataset);
		fusionCharts.setDataset(datasetList);
		return fusionCharts;
	}
}
