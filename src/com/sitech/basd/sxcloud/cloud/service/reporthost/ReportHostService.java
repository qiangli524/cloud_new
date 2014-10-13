package com.sitech.basd.sxcloud.cloud.service.reporthost;

import java.util.List;
import java.util.Map;

import com.sitech.basd.cloud3.domain.charts.MonitorObj;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.cloud.domain.reporthost.TbHostObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;

public interface ReportHostService {

	/**
	 * @Title:查询当天的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHostForToday(TbHostObj obj);

	/**
	 * @Title:查询本周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHostForWeek(TbHostObj obj);

	/**
	 * @Title:跨表查询本周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryUnionTbHostForWeek(TbHostObj obj);

	/**
	 * @Title:查询上周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHostForLastWeek(TbHostObj obj);

	/**
	 * @Title:跨表查询本周的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryUnionTbHostForLastWeek(TbHostObj obj);

	/**
	 * @Title:查询当月的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHostForMonth(TbHostObj obj);

	/**
	 * @Title:查询上月的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHostForLastMonth(TbHostObj obj);

	/**
	 * @Title: 查询已经监控的主机
	 * @Copyright: Copyright (c) 20120405
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int queryMonitorHostCount(TbCloud2HostInfoObj obj);

	/**
	 * @Title: 查询所有能监控的主机
	 * @Copyright: Copyright (c) 20120405
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int queryAllHostCount(TbCloud2HostInfoObj obj);

	/**
	 * 
	 * @Title: queryVirtualData
	 * @Description: 查询虚拟机监控数据
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 25, 2013 1:41:39 PM
	 */
	public List queryHostMonitorData(Map map);

	/**
	 * 查询一周的监控数据
	 * 
	 * @param map
	 * @return
	 */
	public List queryHostMonitorDataByWeek(Map map);

	/**
	 * 查询一个月的监控数据
	 * 
	 * @param map
	 * @return
	 */
	public List queryHostMonitorDataByMonth(Map map);

	public FusionCharts queryChartDayData(String id, String kpi, String name,
			String time_inter, String startDate, String endDate,
			String pointCount);

	/**
	 * 
	 * @Title: queryAvg
	 * @Description: 获取主机指定指标在指定时间段的平均值
	 * @param
	 * @return Double
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-11-20 下午3:45:15
	 */
	public Double queryHostCpuMenByAvg(String id, String kpi, String interval);

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
	public FusionCharts queryHostDNChartDayData(String id, String kpi,
			String motionName, String time_inter, String startDate,
			String endDate, String pointCount);
	
	
	public FusionCharts queryChartFileSystemDayData(String id, String kpi,String startDate, String endDate);

	/**
	 * <p>查询主机某一时间段的磁盘和网络的平均读写速度和最大读写速度</p>
	 * 
	 * @param id
	 * @param string
	 * @author xugang
	 * @return
	 */
	public MonitorObj queryHostDiskNetMonitor(String id, String interval);
}
