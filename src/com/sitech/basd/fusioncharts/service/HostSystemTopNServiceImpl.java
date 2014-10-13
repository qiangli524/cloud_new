package com.sitech.basd.fusioncharts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.resource.dao.global.HostGlobalDao;
import com.sitech.basd.resource.dao.global.VmGlobalDao;
import com.sitech.vo.util.UnitedConstant;

/**
 * 
 * <p>
 * Title: HostSystemTopNServiceImpl
 * </p>
 * <p>
 * Description: 宿主机TopN实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-9-14 上午9:27:22
 * 
 */
@Service("hostSystemTopNService")
public class HostSystemTopNServiceImpl implements HostSystemTopNService {
	@Autowired
	private HostGlobalDao hostGlobalDao;
	@Autowired
	private VmGlobalDao vmGlobalDao;

	/**
	 * 
	 * @Title: queryEntityTopN
	 * @Description: 查询实体TopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public FusionCharts queryEntityTopN(Map<String, Object> map) throws Exception {
		FusionCharts fusionCharts = new FusionCharts();
		// 设置报表基本参数
		Chart chart = new Chart();
		List<Data> dataList = new ArrayList<Data>();

		int entity_type = Integer.parseInt((String) map.get("entity_type"));
		String type = (String) map.get("type");

		if (entity_type == 1) {// 主机
			if (UnitedConstant.DATACENTER.equals(map.get("treeType"))) {// 数据中心下
				if ("cpu".equals(type)) {// cpu
					dataList = hostGlobalDao.queryHostCpuTopN(map);
					chart.setCaption("宿主机Cpu Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("Cpu使用量");
					chart.setNumberSuffix("%");
				} else if ("mem".equals(type)) {// 内存
					dataList = hostGlobalDao.queryHostMemTopN(map);
					chart.setCaption("宿主机内存 Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("内存使用量");
					chart.setNumberSuffix("%");
				} else if ("storeikbps".equals(type)) {
					dataList = hostGlobalDao.queryHostStoreIKbpsTopN(map);
					chart.setCaption("宿主机磁盘写 Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("磁盘写速率");
					chart.setNumberSuffix(" kbps");
				} else if ("storeokbps".equals(type)) {
					dataList = hostGlobalDao.queryHostStoreOKbpsTopN(map);
					chart.setCaption("宿主机磁盘读 Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("磁盘读速率");
					chart.setNumberSuffix(" kbps");
				} else if ("networkikbps".equals(type)) {
					dataList = hostGlobalDao.queryHostNetworkIKbpsTopN(map);
					chart.setCaption("宿主机网卡写 Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("网卡读速率");
					chart.setNumberSuffix(" kbps");
				} else if ("networkokbps".equals(type)) {
					dataList = hostGlobalDao.queryHostNetworkOKbpsTopN(map);
					chart.setCaption("宿主机网卡读 Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("网卡读速率");
					chart.setNumberSuffix(" kbps");
				} else {// 存储
					dataList = hostGlobalDao.queryHostStorageTopN(map);
					chart.setCaption("宿主机存储 Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("存储使用量");
					chart.setNumberSuffix("%");
				}
			} else {// 集群下
				map.put("isInCluster", "isInCluster");
				if ("cpu".equals(type)) {
					dataList = hostGlobalDao.queryClusterHostCpuTopN(map);
					chart.setCaption("集群下宿主机Cpu Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("Cpu使用量");
					chart.setNumberSuffix("%");
				} else if ("mem".equals(type)) {
					dataList = hostGlobalDao.queryClusterHostMemTopN(map);
					chart.setCaption("集群下宿主机内存 Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("内存使用量");
					chart.setNumberSuffix("%");
				} else if ("storeikbps".equals(type)) {
					dataList = hostGlobalDao.queryHostStoreIKbpsTopN(map);
					chart.setCaption("集群下宿主机磁盘写 Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("磁盘写速率");
					chart.setNumberSuffix(" kbps");
				} else if ("storeokbps".equals(type)) {
					dataList = hostGlobalDao.queryHostStoreOKbpsTopN(map);
					chart.setCaption("集群下宿主机磁盘读 Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("磁盘读速率");
					chart.setNumberSuffix(" kbps");
				} else if ("networkikbps".equals(type)) {
					dataList = hostGlobalDao.queryHostNetworkIKbpsTopN(map);
					chart.setCaption("集群下宿主机网卡写 Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("网卡读速率");
					chart.setNumberSuffix(" kbps");
				} else if ("networkokbps".equals(type)) {
					dataList = hostGlobalDao.queryHostNetworkOKbpsTopN(map);
					chart.setCaption("集群下宿主机网卡读 Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("网卡读速率");
					chart.setNumberSuffix(" kbps");
				} else {
					dataList = hostGlobalDao.queryClusterHostStorageTopN(map);
					chart.setCaption("集群下宿主机存储 Top N 报表");
					chart.setXaxisname("宿主机名称");
					chart.setYaxisname("存储使用量");
					chart.setNumberSuffix("%");
				}
			}
		} else {// 虚拟机
			if (UnitedConstant.DATACENTER.equals(map.get("treeType"))) {// 数据中心下
				map.put("isInDatacenter", "isInDatacenter");
				if ("cpu".equals(type)) {
					dataList = vmGlobalDao.queryVmCpuTopN(map);
					chart.setCaption("虚拟机Cpu Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("Cpu使用量");
					chart.setNumberSuffix("%");
				} else if ("mem".equals(type)) {
					dataList = vmGlobalDao.queryVmMemTopN(map);
					chart.setCaption("虚拟机内存 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("内存使用量");
					chart.setNumberSuffix("%");
				} else if ("storeikbps".equals(type)) {
					dataList = vmGlobalDao.queryVMStoreIKbpsTopN(map);
					chart.setCaption("虚拟机磁盘写 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("磁盘写速率");
					chart.setNumberSuffix(" kbps");
				} else if ("storeokbps".equals(type)) {
					dataList = vmGlobalDao.queryVMStoreOKbpsTopN(map);
					chart.setCaption("虚拟机磁盘读 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("磁盘读速率");
					chart.setNumberSuffix(" kbps");
				} else if ("networkikbps".equals(type)) {
					dataList = vmGlobalDao.queryVMNetworkIKbpsTopN(map);
					chart.setCaption("虚拟机网卡写 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("网卡读速率");
					chart.setNumberSuffix(" kbps");
				} else if ("networkokbps".equals(type)) {
					dataList = vmGlobalDao.queryVMNetworkOKbpsTopN(map);
					chart.setCaption("虚拟机网卡读 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("网卡读速率");
					chart.setNumberSuffix(" kbps");
				} else {
					dataList = vmGlobalDao.queryVmStorageTopN(map);
					chart.setCaption("虚拟机存储 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("存储使用量");
					chart.setNumberSuffix("%");
				}
			} else if (UnitedConstant.CLUSTER.equals(map.get("treeType"))) {// 集群下
				map.put("isInCluster", "isInCluster");
				if ("cpu".equals(type)) {
					dataList = vmGlobalDao.queryClusterVmCpuTopN(map);
					chart.setCaption("集群下虚拟机Cpu Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("Cpu使用量");
					chart.setNumberSuffix("%");
				} else if ("mem".equals(type)) {
					dataList = vmGlobalDao.queryClusterVmMemTopN(map);
					chart.setCaption("集群下虚拟机内存 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("内存使用量");
					chart.setNumberSuffix("%");
				} else if ("storeikbps".equals(type)) {
					dataList = vmGlobalDao.queryVMStoreIKbpsTopN(map);
					chart.setCaption("集群下虚拟机磁盘写 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("磁盘写速率");
					chart.setNumberSuffix(" kbps");
				} else if ("storeokbps".equals(type)) {
					dataList = vmGlobalDao.queryVMStoreOKbpsTopN(map);
					chart.setCaption("集群下虚拟机磁盘读 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("磁盘读速率");
					chart.setNumberSuffix(" kbps");
				} else if ("networkikbps".equals(type)) {
					dataList = vmGlobalDao.queryVMNetworkIKbpsTopN(map);
					chart.setCaption("集群下虚拟机网卡写 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("网卡读速率");
					chart.setNumberSuffix(" kbps");
				} else if ("networkokbps".equals(type)) {
					dataList = vmGlobalDao.queryVMNetworkOKbpsTopN(map);
					chart.setCaption("集群下虚拟机网卡读 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("网卡读速率");
					chart.setNumberSuffix(" kbps");
				} else {
					dataList = vmGlobalDao.queryClusterVmStorageTopN(map);
					chart.setCaption("集群下虚拟机存储 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("存储使用量");
					chart.setNumberSuffix("%");
				}
			} else {// 主机下
				map.put("isInHost", "isInHost");
				if ("cpu".equals(type)) {
					dataList = vmGlobalDao.queryHostVmCpuTopN(map);
					chart.setCaption("主机下虚拟机Cpu Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("Cpu使用量");
					chart.setNumberSuffix("%");
				} else if ("mem".equals(type)) {
					dataList = vmGlobalDao.queryHostVmMemTopN(map);
					chart.setCaption("主机下虚拟机内存 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("内存使用量");
					chart.setNumberSuffix("%");
				} else if ("storeikbps".equals(type)) {
					dataList = vmGlobalDao.queryVMStoreIKbpsTopN(map);
					chart.setCaption("主机下虚拟机磁盘写 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("磁盘写速率");
					chart.setNumberSuffix(" kbps");
				} else if ("storeokbps".equals(type)) {
					dataList = vmGlobalDao.queryVMStoreOKbpsTopN(map);
					chart.setCaption("主机下虚拟机磁盘读 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("磁盘读速率");
					chart.setNumberSuffix(" kbps");
				} else if ("networkikbps".equals(type)) {
					dataList = vmGlobalDao.queryVMStoreOKbpsTopN(map);
					chart.setCaption("主机下虚拟机网卡写 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("网卡读速率");
					chart.setNumberSuffix(" kbps");
				} else if ("networkokbps".equals(type)) {
					dataList = vmGlobalDao.queryVMStoreOKbpsTopN(map);
					chart.setCaption("主机下虚拟机网卡读 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("网卡读速率");
					chart.setNumberSuffix(" kbps");
				} else {
					dataList = vmGlobalDao.queryHostVmStorageTopN(map);
					chart.setCaption("主机下虚拟机存储 Top N 报表");
					chart.setXaxisname("虚拟机名称");
					chart.setYaxisname("存储使用量");
					chart.setNumberSuffix("%");
				}
			}
		}
		chart.setShowvalues("0");//是否显示值，1显示，0不显示，默认显示
		chart.setPlotspacepercent("20");
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		return fusionCharts;
	}

	/**
	 * 
	 * @Title: queryTopList
	 * @Description: 获取TopN实体列表
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-15 下午3:20:24
	 */
	@SuppressWarnings("rawtypes")
	public List queryTopList(Map<String, Object> map) {
		Integer entity_type = Integer.parseInt((String) map.get("entity_type"));
		String type = (String) map.get("type");
		List list = new ArrayList();
		if (entity_type == 1) {// 主机
			if (UnitedConstant.DATACENTER.equals(map.get("treeType"))) {
				if ("cpu".equals(type)) {
					list = hostGlobalDao.queryCPUList(map);
				} else if ("mem".equals(type)) {
					list = hostGlobalDao.queryMemList(map);
				} else {
					list = hostGlobalDao.queryStoreList(map);
				}
			} else {
				if ("cpu".equals(type)) {
					list = hostGlobalDao.queryCPUListClu(map);
				} else if ("mem".equals(type)) {
					list = hostGlobalDao.queryMemListClu(map);
				} else if ("storeikbps".equals(type)) {
					list = hostGlobalDao.queryMemListClu(map);
				} else if ("storeokbps".equals(type)) {
					list = hostGlobalDao.queryMemListClu(map);
				} else {
					list = hostGlobalDao.queryStoreListClu(map);
				}
			}
		} else {// 虚拟机
			if (UnitedConstant.DATACENTER.equals(map.get("treeType"))) {
				if ("cpu".equals(type)) {
					list = vmGlobalDao.queryCPUList(map);
				} else if ("mem".equals(type)) {
					list = vmGlobalDao.queryMemList(map);
				} else {
					list = vmGlobalDao.queryStoreList(map);
				}
			} else if (UnitedConstant.CLUSTER.equals(map.get("treeType"))) {
				if ("cpu".equals(type)) {
					list = vmGlobalDao.queryCPUListClu(map);
				} else if ("mem".equals(type)) {
					list = vmGlobalDao.queryMemListClu(map);
				} else {
					list = vmGlobalDao.queryStoreListClu(map);
				}
			} else {
				if ("cpu".equals(type)) {
					list = vmGlobalDao.queryCPUListHost(map);
				} else if ("mem".equals(type)) {
					list = vmGlobalDao.queryMemListHost(map);
				} else {
					list = vmGlobalDao.queryStoreListHost(map);
				}
			}
		}
		return list;
	}
}
