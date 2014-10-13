package com.sitech.basd.sxcloud.cloud.service.reportvirtual;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.sitech.basd.cloud3.domain.charts.DataPoint;
import com.sitech.basd.cloud3.domain.charts.MonitorObj;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.cloud.domain.reportvirtual.TbHyObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;

public interface ReportVirtualService {

	/**
	 * @Title:查询当天的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForToday(TbHyObj obj);

	/**
	 * @Title:查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForWeek(TbHyObj obj);

	/**
	 * @Title:跨表查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryUnionTbHyForWeek(TbHyObj obj);

	/**
	 * @Title:查询上周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForLastWeek(TbHyObj obj);

	/**
	 * @Title:跨表查询本周的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryUnionTbHyForLastWeek(TbHyObj obj);

	/**
	 * @Title:查询当月的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForMonth(TbHyObj obj);

	/**
	 * @Title:查询上月的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForLastMonth(TbHyObj obj);

	/**
	 * @Title: 查询已经监控的虚拟机的个数
	 * @Copyright: Copyright (c) 20120406
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int queryMonitorVirtualCount(TbCloud2VirtualInfoObj obj);

	/**
	 * @Title: 查询已经监控的虚拟机的个数
	 * @Copyright: Copyright (c) 20120406
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int queryAllVirtualCount(TbCloud2VirtualInfoObj obj);

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
	@Deprecated
	public List queryVirtualMonitorData(Map map);

	/**
	 * 
	 * @Title: queryHyData
	 * @Description:查询虚拟机监控数据，highCharts展示
	 * @author duangh
	 * @date Jul 9, 2013 8:58:32 PM
	 * @return
	 */
	public List<DataPoint> queryHyData(Map map);

	/**
	 * 查询一个星期的监控数据
	 * 
	 * @param map
	 * @return
	 */
	public List queryVmMonitorDataByWeek(Map map);

	/**
	 * 查询一个月的监控数据
	 * 
	 * @param map
	 * @return
	 */
	public List queryVmMonitorDataByMonth(Map map);

	public FusionCharts queryChartDayData(String id, String kpi, String name,
			String time_inter, String startDate, String endDate,
			String pointCount,String startUsage,String endUsage);

	/**
	 * 
	 * @Title: queryAvg
	 * @Description: 获取指定指标在指定时间段的平均值
	 */
	public Double queryVmCpuMenByAvg(String hyId, String kpi, String interval);

	/**
	 * 
	 * @Title: queryHyDNChartDayData
	 * @Description: 查询虚拟机磁盘和网络的监控数据
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 下午4:20:04
	 */
	public FusionCharts queryHyDNChartDayData(String id, String kpi,
			String motionName, String time_inter, String startDate,
			String endDate, String pointCount);
	
	/**
	 * 
	 * @Title: hyMonitorPerformationDataExport
	 * @Description: 虚拟机性能报表导出
	 * @param
	 * @return void
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-18 下午4:39:10
	 */
	public void hyMonitorPerformationDataExport(String id, String kpi, String name,
			String cycle_time, String startDate, String endDate,
			String pointCount,OutputStream os,String startUsage,String endUsage );
	
	
	public FusionCharts queryChartFileSystemDayData(String id, String kpi1,String kpi2,String startDate, String endDate);

	/**
	 * <p>获取磁盘和网络平均值和最大值</p>
	 * 
	 * @param hyId
	 * @param string
	 * @return
	 */
	public MonitorObj queryVmtDiskNetMonitor(String hyId, String string);
}
