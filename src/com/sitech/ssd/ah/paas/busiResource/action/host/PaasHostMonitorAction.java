package com.sitech.ssd.ah.paas.busiResource.action.host;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.cloud3.domain.charts.DataPoint;
import com.sitech.basd.cloud3.domain.charts.MonitorObj;
import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.paas.busiResource.service.host.PaasHostMonitorService;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * <p>
 * Title: PaasHostMonitorAction
 * </p>
 * <p>
 * Description: 用于主机监控
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
 * @createtime 2014-7-29 下午4:14:02
 * 
 */
@Controller("paasHostMonitorAction")
@Scope("prototype")
@SuppressWarnings("all")
public class PaasHostMonitorAction extends BaseAction {
	@Autowired
	private PaasHostMonitorService paasHostMonitorService;
	private String id;// 主机UUID
	private String kpi;// KPI
	private String pointTime;// 时间点，单位为分钟
	private String motionName;// highCharts中显示的名称
	private String cycle_time;// 需要查询的时间周期
	private String startDate;
	private String endDate;
	private String pointCount;
	private String fileUrl;
	private MonitorObj monitorObj;
	private Double cpuUsedRate;// 指定时间段内cpu使用率的平均值
	private Double menUsedRate;// 指定时间段内men使用率的平均值
	private List<DataPoint> resultList;
	private List urlList;
	private String eq_type;

	public String getEq_type() {
		return eq_type;
	}

	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}

	public String getId() {
		return id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getPointCount() {
		return pointCount;
	}

	public void setPointCount(String pointCount) {
		this.pointCount = pointCount;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCycle_time() {
		return cycle_time;
	}

	public void setCycle_time(String cycle_time) {
		this.cycle_time = cycle_time;
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

	public MonitorObj getMonitorObj() {
		return monitorObj;
	}

	public void setMonitorObj(MonitorObj monitorObj) {
		this.monitorObj = monitorObj;
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

	public List getUrlList() {
		return urlList;
	}

	public void setUrlList(List urlList) {
		this.urlList = urlList;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	/**
	 * 
	 * @Title: showHostMonitorPage
	 * @Description: 主机管理-性能-摘要信息
	 * @param
	 * @return String
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-11-20 下午4:50:16
	 */
	public String showHostMonitorPage() {
		// 获取近一个小时cpu和内存的使用情况PM-H-01-010-11
		cpuUsedRate = paasHostMonitorService.queryHostCpuMenByAvg(id, "PM-H-01-010-11", "60",
				eq_type);
		menUsedRate = paasHostMonitorService.queryHostCpuMenByAvg(id, "PM-H-01-010-12", "60",
				eq_type);
		monitorObj = paasHostMonitorService.queryHostDiskNetMonitor(id, "60", eq_type);
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.CHINA);
		nf.setMaximumFractionDigits(2);
		nf.setGroupingUsed(false);
		cpuUsedRate = Double.parseDouble(nf.format(cpuUsedRate));
		menUsedRate = Double.parseDouble(nf.format(menUsedRate));
		monitorObj.setCpuUsedRate(cpuUsedRate);
		monitorObj.setMenUsedRate(menUsedRate);
		return "showHostMonitorPage";
	}

	/**
	 * 
	 * @Title: highChartsTabs
	 * @Description:进入展示主机监控数据界面,分cpu,内存，存储，网络4个tab
	 * @author duangh
	 * @date Jul 9, 2013 8:32:11 PM
	 * @return
	 */
	public String highChartsTabs() {
		return "tabs";
	}

	/**
	 * 
	 * @Title: greenPlumTabs
	 * @Description: 进入展示GP主机监控数据界面,分cpu,内存，存储，网络,文件系统4个tab
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 下午4:47:27
	 */
	public String greenPlumTabs() {
		return "greenPlumTabs";
	}

	/**
	 * 
	 * @Title: hostMonitorData_f
	 * @Description: 跳转页，不添加跳转页，页面会卡住
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午9:51:58
	 */
	public String hostMonitorData_f() {
		return "showFirstTabData";
	}
	
	/**
	 * 
	 * @Title: showHostMonitorData_f
	 * @Description: CPU和内存页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-8-5 下午4:41:30
	 */
	public String showHostMonitorData_f(){
		return "hostMonitor_f";
	}

	/**
	 * 
	 * @Title: queryHostCMData_f
	 * @Description: CPU，内存
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午9:58:38
	 */
	public String queryHostCMData_f() {
		FusionCharts fChart = paasHostMonitorService.queryChartDayData(id, kpi, motionName,
				cycle_time, startDate, endDate, pointCount, eq_type);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		printJson(fusionChartsString);
		return null;
	}

	/**
	 * 
	 * @Title: hostMonitorDNData_fusion
	 * @Description: tab页进入后，开始查看主机IO和网络监控数据,即进入FusionCharts的展示界面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 上午11:25:50
	 */
	public String hostMonitorDNData_fusion() {
		return "hostMonitorDNData_fusion";
	}

	/**
	 * 
	 * @Title: queryHostDNData_fusion
	 * @Description: 查询主机磁盘和网络
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-25 上午11:40:04
	 */
	public String queryHostDNData_fusion() {
		FusionCharts fChart = paasHostMonitorService.queryHostDNChartDayData(id, kpi, motionName,
				cycle_time, startDate, endDate, pointCount, eq_type);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		printJson(fusionChartsString);
		return null;
	}

	/**
	 * 
	 * @Title: queryChartFileSystemDayDataView
	 * @Description: 文件系统页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午9:50:05
	 */
	public String queryChartFileSystemDayDataView() {
		String kpi = "'PM-H-01-010-17','PM-H-01-010-18'";
		urlList = paasHostMonitorService.queryFileSystemUrl(id, kpi, eq_type);
		return "queryChartFileSystemDayDataView";
	}

	/**
	 * 
	 * @Title: queryChartFileSystemDayData
	 * @Description: 文件系统
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-31 上午9:49:47
	 */
	public void queryChartFileSystemDayData() {
		String eq_id = "";
		if ("all".equals(fileUrl)) {
			eq_id = id;
		} else {
			eq_id = id + "_" + fileUrl;
		}
		FusionCharts fChart = paasHostMonitorService.queryHostDNChartDayData(eq_id, kpi,
				motionName, cycle_time, startDate, endDate, pointCount, eq_type);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		printJson(fusionChartsString);
	}

	/**
	 * @Title: toSwapPage
	 * @Description: 跳转到交换区tab页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-31 下午4:48:54
	 */
	public String toSwapPage() {
		return "toSwapPage";
	}

	/**
	 * @Title: querySwapData_f
	 * @Description: 查询交换区数据
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-31 下午5:27:23
	 */
	public void querySwapData_f() {
		FusionCharts fChart = paasHostMonitorService.querySwapData_f(id, kpi, motionName,
				cycle_time, startDate, endDate, pointCount, eq_type);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		printJson(fusionChartsString);
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
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				PrintWriterOut.printWirter(response, params[i]);
			}
		}
	}

}