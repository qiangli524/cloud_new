package com.sitech.basd.cloud3.web.charts.action;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.sitech.basd.cloud3.domain.charts.DataPoint;
import com.sitech.basd.cloud3.domain.charts.MonitorObj;
import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.cloud.service.reportvirtual.ReportVirtualService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.date.TimeformatCommon;
import com.sitech.utils.servlet.PrintWriterOut;

@Controller("hyMonitorDataAction")
@Scope("prototype")
public class HyMonitorDataAction extends BaseAction {
	private String hyId;// 虚拟机ID
	private String kpi;// KPI
	private String pointTime;// 时间点
	private String motionName;// highCharts中显示的名称
	private String flag;//标示
	private List<DataPoint> resultList;
	private List<DataPoint> resultList2;
	private String cycle_time;// 需要查询的时间周期
	private String startDate;
	private String endDate;
	private String pointCount;
	private MonitorObj monitorObj;
	/** cpu近一个小时的内存使用率 */
	private Double cpuUsedRate;
	/** 内存近一个小时的内存使用率 */
	private Double menUsedRate;

	@Resource
	private ReportVirtualService reportVirtualService;

	public List<DataPoint> getResultList2() {
		return resultList2;
	}

	public String getCycle_time() {
		return cycle_time;
	}

	public void setCycle_time(String cycle_time) {
		this.cycle_time = cycle_time;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPointCount() {
		return pointCount;
	}

	public void setPointCount(String pointCount) {
		this.pointCount = pointCount;
	}

	public void setResultList2(List<DataPoint> resultList2) {
		this.resultList2 = resultList2;
	}

	public String getMotionName() {
		return motionName;
	}

	public void setMotionName(String motionName) {
		this.motionName = motionName;
	}

	public List<DataPoint> getResultList() {
		return resultList;
	}

	public void setResultList(List<DataPoint> resultList) {
		this.resultList = resultList;
	}

	public String getPointTime() {
		return pointTime;
	}

	public void setPointTime(String pointTime) {
		this.pointTime = pointTime;
	}

	public String getKpi() {
		return kpi;
	}

	public void setKpi(String kpi) {
		this.kpi = kpi;
	}

	public String getHyId() {
		return hyId;
	}

	public void setHyId(String hyId) {
		this.hyId = hyId;
	}

	public Double getCpuUsedRate() {
		return cpuUsedRate;
	}

	public void setCpuUsedRate(Double cpuUsedRate) {
		this.cpuUsedRate = cpuUsedRate;
	}

	public Double getMenUsedRate() {
		return menUsedRate;
	}

	public void setMenUsedRate(Double menUsedRate) {
		this.menUsedRate = menUsedRate;
	}

	/**
	 * 
	 * @Title: highChartsTabs
	 * @Description:进入展示虚拟机监控数据界面,分cpu,内存，存储，网络4个tab
	 * @author duangh
	 * @date Jul 9, 2013 8:32:11 PM
	 * @return
	 */
	public String highChartsTabs() {
		return "tabs";
	}

	/**
	 * 
	 * @Title: hyMonitorData
	 * @Description: tab页进入后，开始查看监控数据,即进入highCharts的展示界面
	 * @author duangh
	 * @date Jul 9, 2013 9:08:48 PM
	 * @return
	 */
	public String hyMonitorData() {
		return "hyMonitor";
	}

	public String hyMonitorData_f() {
		return "hyMonitor_f";
	}

	/**
	 * 
	 * @Title: hostMonitorDNData_fusion
	 * @Description: tab页进入后，开始查看虚拟机机IO和网络监控数据,即进入FusionCharts的展示界面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 上午11:25:50
	 */
	public String hyMonitorDNData_fusion() {
		return "hyMonitorDNData_fusion";
	}

	/**
	 * 
	 * @Title: queryHyCMData
	 * @Description:查询监控CPU,内存数据,数据处理写到Service层去，不要在Action中处理
	 * @author duangh
	 * @date Jul 9, 2013 9:04:28 PM
	 * @return
	 */
	public String queryHyCMData() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("KPI_ID", kpi);
		params.put("HY_ID", hyId);
		params.put("TIME_INTER", pointTime);
		resultList = reportVirtualService.queryHyData(params);
		if ("1".equals(flag)) {
			resultList = reportVirtualService.queryHyData(params);
		} else if ("week".equals(flag)) {
			resultList = reportVirtualService.queryVmMonitorDataByWeek(params);
		} else if ("month".equals(flag)) {
			resultList = reportVirtualService.queryVmMonitorDataByMonth(params);
		} else {
			System.out.println("flag标识错误，没有需要查询的时间段的性能信息!");
		}
		return "cmData";
	}

	public String queryHyCMData_f() {
		FusionCharts fChart = reportVirtualService.queryChartDayData(hyId, kpi, motionName,
				cycle_time, startDate, endDate, pointCount, null, null);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		System.out.println(fusionChartsString);
		printJson(fusionChartsString);
		return null;
	}

	public void queryChartFileSystemDayData() {
		String kpi1 = "PM-H-01-010-17";
		String kpi2 = "PM-H-01-010-18";
		FusionCharts fChart = reportVirtualService.queryChartFileSystemDayData(hyId, kpi1,kpi2,
				startDate, endDate);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		System.out.println(fusionChartsString);
		printJson(fusionChartsString);
	}

	public String queryChartFileSystemDayDataView() {
		if (StringUtils.isEmpty(startDate)) {
			startDate = TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd") + " 00:00:00";
		}
		if (StringUtils.isEmpty(endDate)) {
			endDate = startDate.substring(0, 10) + " 23:59:59";
		}
		return "queryChartFileSystemDayDataView";
	}

	/**
	 * 
	 * @Title: queryHyDNData
	 * @Description:查询监控磁盘，网络I/O数据
	 * @author duangh
	 * @date Jul 22, 2013 9:10:59 AM
	 * @return
	 */
	public String queryHyDNData() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("KPI_ID", kpi);
		params.put("HY_ID", hyId);
		params.put("TIME_INTER", pointTime);
		resultList = reportVirtualService.queryHyData(params);
		if (kpi.equals("PM-V-01-010-13")) {
			params.put("KPI_ID", "PM-V-01-010-14");
		}
		if (kpi.equals("PM-V-01-010-15")) {
			params.put("KPI_ID", "PM-V-01-010-16");
		}
		resultList2 = reportVirtualService.queryHyData(params);
		return "dnData";
	}

	/**
	 * 
	 * @Title: validate
	 * @Description:struts2验证，传入的hyId不能为空
	 * @author duangh
	 * @date Jul 10, 2013 2:55:45 PM
	 */
	@Override
	public void validate() {
		Assert.notNull(hyId, "vm's uuid is requried");
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 
	 * @Title: printJson
	 * @Description: 传输Json数据到界面
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-14 上午10:41:00
	 */
	private synchronized void printJson(String... params) {
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				// out.println(params[i]);
				PrintWriterOut.printWirter(response, params[i]);
			}
		}
		// out.close();
	}

	/**
	 * 
	 * @Title: showhyMonitorPage
	 * @Description: 虚拟机监控，摘要信息
	 * @param
	 * @return String
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-11-20 下午2:51:51
	 */
	public String showhyMonitorPage() {
		// 获取近一个小时cpu和内存的使用情况
		cpuUsedRate = reportVirtualService.queryVmCpuMenByAvg(hyId, "PM-V-01-010-11", "60");
		menUsedRate = reportVirtualService.queryVmCpuMenByAvg(hyId, "PM-V-01-010-12", "60");
		monitorObj = reportVirtualService.queryVmtDiskNetMonitor(hyId,"60");		
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.CHINA);
		nf.setMaximumFractionDigits(2);
		nf.setGroupingUsed(false);
		cpuUsedRate = Double.parseDouble(nf.format(cpuUsedRate));
		menUsedRate = Double.parseDouble(nf.format(menUsedRate));
		return "showhyMonitorPage";
	}

	/**
	 * 
	 * @Title: queryHostDNData_fusion
	 * @Description: 查询虚拟机磁盘和网络
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 上午11:40:04
	 */
	public String queryHyDNData_fusion() {
		FusionCharts fChart = reportVirtualService.queryHyDNChartDayData(hyId, kpi, motionName,
				cycle_time, startDate, endDate, pointCount);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		System.out.println(fusionChartsString);
		printJson(fusionChartsString);
		return null;
	}

	/**
	 *
	 * @return the monitorObj
	 */
	public MonitorObj getMonitorObj() {
		return monitorObj;
	}

	/**
	 *
	 * @param monitorObj the monitorObj to set
	 */
	public void setMonitorObj(MonitorObj monitorObj) {
		this.monitorObj = monitorObj;
	}
	
}
