package com.sitech.ssd.ah.reportform.vm.dao;

import java.util.List;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;

public interface ReportFormVmDao {
	// 应用于列表展示
	public List<VmReportForm> queryDataByCurrentDay(VmReportForm obj);
	public List<VmReportForm> queryDataByMonth(VmReportForm obj,String year, String month);
	public List<VmReportForm> queryDataByDay(VmReportForm obj,String year, String month, String day);

	/**
	 * 
	 * @Title: queryDataByCurrentDayForChartList
	 * @Description: 应用于列表展示,查询当天
	 * @param
	 * @return List<VmReportForm>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-4-22 上午9:18:08
	 */
	public List<VmReportForm> queryDataByCurrentDayForChartList(VmReportForm obj);
	/**
	 * 
	 * @Title: queryDataByMonthForChartList
	 * @Description: 应用于列表展示,按月查询
	 * @param
	 * @return List<VmReportForm>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-4-22 上午9:18:08
	 */
	public List<VmReportForm> queryDataByMonthForChartList(VmReportForm obj,String year, String month);
	
	/**
	 * 
	 * @Title: queryDataByDayForChartList
	 * @Description: 指定天数查询
	 * @param
	 * @return List<VmReportForm>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-4-22 上午9:20:01
	 */
	public List<VmReportForm> queryDataByDayForChartList(VmReportForm obj,String year, String month, String day);
	 /**
	  * 
	  * @Title: queryDataByCurrentDayForChart_cpu
	  * @Description: 用于图表数据查询，查询当天cpu数据
	  * @param
	  * @return List<Data>
	  * @throws
	  * @author qism
	  * @version 1.0
	  * @createtime 2014-4-22 上午9:20:30
	  */
	public List<Data> queryDataByCurrentDayForChart_cpu(VmReportForm obj);
	/**
	 * 
	 * @Title: queryDataByMonthForChart_cpu
	 * @Description: 用于图表数据查询，查询月表cpu数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-4-22 上午9:21:17
	 */
	public List<Data> queryDataByMonthForChart_cpu(VmReportForm obj,String year, String month);
	/**
	 * 
	 * @Title: queryDataByDayForChart_cpu
	 * @Description:用于图表数据查询，查询指定天表cpu数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-4-22 上午9:21:53
	 */
	public List<Data> queryDataByDayForChart_cpu(VmReportForm obj,String year, String month, String day);
	/**
	 * 
	 * @Title: queryDataByCurrentDayForChart_mem
	 * @Description: 用于图表数据查询，查询当天mem数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-4-22 上午9:22:56
	 */
	public List<Data> queryDataByCurrentDayForChart_mem(VmReportForm obj);
	/**
	 * 
	 * @Title: queryDataByMonthForChart_mem
	 * @Description:用于图表数据查询，查询月表mem数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-4-22 上午9:23:22
	 */
	public List<Data> queryDataByMonthForChart_mem(VmReportForm obj,String year, String month);
	/**
	 * 
	 * @Title: queryDataByDayForChart_mem
	 * @Description: 用于图表数据查询，查询指定天表mem数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-4-22 上午9:23:41
	 */
	public List<Data> queryDataByDayForChart_mem(VmReportForm obj,String year, String month, String day);
	/**
	 * 
	 * @Title: queryDataByCurrentDayForChart_time
	 * @Description: 用于折线图数据查询，查询当天
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-4-22 上午9:25:54
	 */
	public List<Data> queryDataByCurrentDayForChart_time(VmReportForm obj);
	/**
	 * 
	 * @Title: queryDataByMonthForChart_time
	 * @Description: 用于折线图数据查询，查询月表
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-4-22 上午9:26:26
	 */
	public List<Data> queryDataByMonthForChart_time(VmReportForm obj,String year, String month);
	/**
	 * 
	 * @Title: queryDataByDayForChart_time
	 * @Description: 用于折线图数据查询,查询指定天表
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-4-22 上午9:26:53
	 */
	public List<Data> queryDataByDayForChart_time(VmReportForm obj,String year, String month, String day);
	
	/**
	 * 
	 * @Title: queryDataFromAgentData
	 * @Description: 查询agent采集的数据
	 * @param
	 * @return List<VmReportForm>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014年6月5日17:23:00
	 */
	public List<VmReportForm> queryDataFromAgentData(VmReportForm obj);
	/**
	 * @Title: queryVmhostList
	 * @Description: 查询虚拟机列表
	 * @param
	 * @return List<VmReportForm>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-30 下午3:44:12
	 */
	public List<VmReportForm> queryVmhostList(VmReportForm obj);
}
