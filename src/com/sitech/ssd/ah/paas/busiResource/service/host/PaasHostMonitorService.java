package com.sitech.ssd.ah.paas.busiResource.service.host;

import java.util.List;

import com.sitech.basd.cloud3.domain.charts.MonitorObj;
import com.sitech.basd.fusioncharts.vo.FusionCharts;

/**
 * <p>
 * Title: PaasHostMonitorService
 * </p>
 * <p>
 * Description: paas主机监控服务接口
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
 * @createtime 2014-7-29 下午4:39:30
 * 
 */
public interface PaasHostMonitorService {

	/**
	 * 
	 * @Title: queryHostCpuMenByAvg
	 * @Description: 获取主机指定指标在指定时间段的平均值
	 * @param
	 * @return Double
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:19:28
	 */
	public Double queryHostCpuMenByAvg(String id, String kpi, String interval, String eq_type);

	/**
	 * 
	 * @Title: queryHostDiskNetMonitor
	 * @Description: 查询主机某一时间段的磁盘和网络的平均读写速度和最大读写速度
	 * @param
	 * @return MonitorObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:19:40
	 */
	public MonitorObj queryHostDiskNetMonitor(String id, String interval, String eq_type);

	/**
	 * 
	 * @Title: queryChartDayData
	 * @Description: CPU内存监控
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:19:47
	 */
	public FusionCharts queryChartDayData(String id, String kpi, String name, String time_inter,
			String startDate, String endDate, String pointCount, String eq_type);

	/**
	 * 
	 * @Title: queryHostDNChartDayData
	 * @Description: 查询主机磁盘和网络的监控数据
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 上午11:43:16
	 */
	public FusionCharts queryHostDNChartDayData(String id, String kpi, String motionName,
			String time_inter, String startDate, String endDate, String pointCount, String eq_type);

	/**
	 * 
	 * @Title: queryFileSystemUrl
	 * @Description: 查询文件系统路径
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午11:39:40
	 */
	public List queryFileSystemUrl(String id, String kpi, String eq_type);

	/**
	 * @Title: querySwapData_f
	 * @Description: 查询swap数据
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-31 下午5:32:13
	 */
	public FusionCharts querySwapData_f(String id, String kpi, String name, String time_inter,
			String startDate, String endDate, String pointCount, String eq_type);
}
