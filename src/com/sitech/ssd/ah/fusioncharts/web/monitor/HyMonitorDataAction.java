package com.sitech.ssd.ah.fusioncharts.web.monitor;

import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.sitech.basd.cloud3.domain.charts.DataPoint;
import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.cloud.service.reportvirtual.ReportVirtualService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

@Controller("hyMonitorDataAction_ah")
public class HyMonitorDataAction extends BaseAction {
	private static final Logger log = LogManager.getLogger(HyMonitorDataAction.class);

	private String hyId;// 虚拟机ID
	private String kpi;// KPI
	private String pointTime;// 时间点
	private String motionName;// highCharts中显示的名称
	private String flag;
	private List<DataPoint> resultList;
	private List<DataPoint> resultList2;
	private String cycle_time;// 需要查询的时间周期
	private String startDate;
	private String endDate;
	private String pointCount;
	private String usage;// cpu/内存使用率过滤

	private String startUsage;// cpu/内存的使用率区间

	private String endUsage;// cpu/内存的使用率区间

	/** cpu近一个小时的内存使用率 */
	private Double cpuUsedRate;
	/** 内存近一个小时的内存使用率 */
	private Double menUsedRate;

	@Autowired
	private ReportVirtualService reportVirtualService_ah;

	public String getHyId() {
		return hyId;
	}

	public void setHyId(String hyId) {
		this.hyId = hyId;
	}

	public String getKpi() {
		return kpi;
	}

	public void setKpi(String kpi) {
		this.kpi = kpi;
	}

	public String getPointTime() {
		return pointTime;
	}

	public void setPointTime(String pointTime) {
		this.pointTime = pointTime;
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

	public List<DataPoint> getResultList2() {
		return resultList2;
	}

	public void setResultList2(List<DataPoint> resultList2) {
		this.resultList2 = resultList2;
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

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getStartUsage() {
		return startUsage;
	}

	public void setStartUsage(String startUsage) {
		this.startUsage = startUsage;
	}

	public String getEndUsage() {
		return endUsage;
	}

	public void setEndUsage(String endUsage) {
		this.endUsage = endUsage;
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

	public ReportVirtualService getReportVirtualService_ah() {
		return reportVirtualService_ah;
	}

	public void setReportVirtualService_ah(ReportVirtualService reportVirtualService_ah) {
		this.reportVirtualService_ah = reportVirtualService_ah;
	}

	public static Logger getLog() {
		return log;
	}

	// /////////////////////////////start虚拟机性能统计报表明细//////////////////////////////////////
	/**
	 * @Title: vmMonitorPerformationTabs
	 * @Description: 性能统计tab页面
	 * @param
	 * @return String
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-18 上午11:15:41
	 */
	public String hyMonitorPerformationTabs() {
		return "hyMonitorPerformationTabs";
	}

	/**
	 * 
	 * @Title: hyMonitorPerformationData
	 * @Description: 虚拟机性能查看
	 * @param
	 * @return String
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-18 上午11:10:33
	 */
	public String hyMonitorPerformation() {
		return "hyMonitorPerformation";
	}

	/**
	 * @Title: hyMonitorPerformationData
	 * @Description: 虚拟机性能报表明细
	 * @param
	 * @return String
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-18 下午3:14:07
	 */
	public String hyMonitorPerformationData() {
		FusionCharts fChart = reportVirtualService_ah.queryChartDayData(hyId, kpi, motionName,
				cycle_time, startDate, endDate, pointCount, startUsage, endUsage);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		printJson(fusionChartsString);
		log.info(fusionChartsString);
		return null;
	}

	/**
	 * 
	 * @Title: hyMonitorPerformationDataExport
	 * @Description: 虚拟机性能明细导出
	 * @param
	 * @return void
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @throws IOException
	 * @createtime 2013-12-18 下午4:45:30
	 */
	public void hyMonitorPerformationDataExport() throws IOException {
		OutputStream os = null;
		try {
			response.setContentType("application/msexcel");
			response.setHeader("Content-disposition", "attachment;filename=vmPerformation.xls");// 定义文件名
			os = response.getOutputStream();
			reportVirtualService_ah.hyMonitorPerformationDataExport(hyId, kpi, motionName,
					cycle_time, startDate, endDate, pointCount, os, startUsage, endUsage);
		} finally {
			if (os != null) {
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		}
	}

	// /////////////////////////////end虚拟机性能统计报表明细//////////////////////////////////////
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
		resultList = reportVirtualService_ah.queryHyData(params);
		if ("1".equals(flag)) {
			resultList = reportVirtualService_ah.queryHyData(params);
		} else if ("week".equals(flag)) {
			resultList = reportVirtualService_ah.queryVmMonitorDataByWeek(params);
		} else if ("month".equals(flag)) {
			resultList = reportVirtualService_ah.queryVmMonitorDataByMonth(params);
		} else {
			System.out.println("flag标识错误，没有需要查询的时间段的性能信息!");
		}
		return "cmData";
	}

	public String queryHyCMData_f() {
		FusionCharts fChart = reportVirtualService_ah.queryChartDayData(hyId, kpi, motionName,
				cycle_time, startDate, endDate, pointCount, null, null);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		System.out.println(fusionChartsString);
		printJson(fusionChartsString);
		return null;
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
		resultList = reportVirtualService_ah.queryHyData(params);
		if (kpi.equals("PM-V-01-010-13")) {
			params.put("KPI_ID", "PM-V-01-010-14");
		}
		if (kpi.equals("PM-V-01-010-15")) {
			params.put("KPI_ID", "PM-V-01-010-16");
		}
		resultList2 = reportVirtualService_ah.queryHyData(params);
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
		// response.setCharacterEncoding("UTF-8");
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
		cpuUsedRate = reportVirtualService_ah.queryVmCpuMenByAvg(hyId, "PM-V-01-010-11", "60");
		menUsedRate = reportVirtualService_ah.queryVmCpuMenByAvg(hyId, "PM-V-01-010-12", "60");
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
		FusionCharts fChart = reportVirtualService_ah.queryHyDNChartDayData(hyId, kpi, motionName,
				cycle_time, startDate, endDate, pointCount);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		System.out.println(fusionChartsString);
		printJson(fusionChartsString);
		return null;
	}
}
