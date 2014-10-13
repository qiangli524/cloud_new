package com.sitech.ssd.ah.reportform.vm.service;

import java.util.List;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;

public interface ReportformVM {
	public List<VmReportForm> queryHyTimeLabelAndData(VmReportForm obj);
	//
	public List<VmReportForm> queryHyTimeLabelAndDataForChartList(VmReportForm obj);
	public List<Data> queryHyTimeLabelAndDataForChart_cpu(VmReportForm obj);
	public List<Data> queryHyTimeLabelAndDataForChart_men(VmReportForm obj);
	/**
	 * 查询虚拟机在每个时间点对应的cpu或内存的值
	 */
	public List<Data> queryHyTimeLabelAndDataForChart_time(VmReportForm obj);
	
	/**
	 * 
	 * @Title: queryHyTimeLabelAndData1
	 * @Description: 综合查询监控数据
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 下午4:17:13
	 */
	public List<VmReportForm> queryHyTimeLabelAndData1(VmReportForm obj);
	/**
	 * @Title: queryVmhostList
	 * @Description: 查询虚拟机列表
	 * @param
	 * @return List<VmReportForm>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-30 下午3:48:48
	 */
	public List<VmReportForm> queryVmhostList(VmReportForm obj);
}
