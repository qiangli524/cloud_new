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
import com.sitech.basd.sxcloud.cloud.service.reporthost.ReportHostService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.date.TimeformatCommon;
import com.sitech.utils.servlet.PrintWriterOut;

@Controller("hostMonitorDataAction")
@Scope("prototype")
public class HostMonitorDataAction extends BaseAction {
	private String id;// 主机UUID
	private String kpi;// KPI
	private String pointTime;// 时间点，单位为分钟
	private String motionName;// highCharts中显示的名称
	private String flag;// 性能查看信息标识根据不同的标识查询不同的表
	private String cycle_time;// 需要查询的时间周期
	private List<DataPoint> resultList;
	private List<DataPoint> resultList2;
	private String startDate;
	private String endDate;
	private String pointCount;
	private MonitorObj monitorObj;
	//指定时间段内cpu使用率的平均值
	private Double cpuUsedRate;
	//指定时间段内men使用率的平均值
	private Double menUsedRate;
	
	@Resource
	private ReportHostService reportHostService;

	public List<DataPoint> getResultList2() {
		return resultList2;
	}

	public void setResultList2(List<DataPoint> resultList2) {
		this.resultList2 = resultList2;
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
	 * @Title: hostMonitorData
	 * @Description: tab页进入后，开始查看监控数据,即进入highCharts的展示界面
	 * @author duangh
	 * @date Jul 9, 2013 9:08:48 PM
	 * @return
	 */
	public String hostMonitorData() {
		return "hostMonitor";
	}

	public String hostMonitorData_f() {
		return "hostMonitor_f";
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
	 * 查询cpu，内存，每周每天每月的数据
	 * 
	 * @Title: queryHostCMData
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2013-11-12 下午3:49:34
	 */
	public String queryHostCMData() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("KPI_ID", kpi);
		params.put("EQ_ID", id);
		params.put("TIME_INTER", pointTime); // all minutes
		if ("1".equals(flag)) {
			resultList = reportHostService.queryHostMonitorData(params);
		} else if ("week".equals(flag)) {
			resultList = reportHostService.queryHostMonitorDataByWeek(params);
		} else if ("month".equals(flag)) {
			resultList = reportHostService.queryHostMonitorDataByMonth(params);
		} else {
			System.out.println("flag标识错误，没有需要查询的时间段的性能信息!");
		}
		return "cmData";
	}

	public String queryHostCMData_f() {
		FusionCharts fChart = reportHostService.queryChartDayData(id, kpi, motionName, cycle_time,
				startDate, endDate, pointCount);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		printJson(fusionChartsString);
		return null;
	}

	public String queryHostCMDataDefined() {
		return "data_defined";
	}

	/**
	 * 
	 * @Title: queryHostDNData
	 * @Description:查询监控IO网络,磁盘IO数据
	 * @author duangh
	 * @date Jul 9, 2013 9:04:28 PM
	 * @return
	 */
	public String queryHostDNData() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("KPI_ID", kpi);
		params.put("EQ_ID", id);
		params.put("TIME_INTER", pointTime); // all minutes
		resultList = reportHostService.queryHostMonitorData(params);
		if (kpi.equals("PM-H-01-010-13")) {
			params.put("KPI_ID", "PM-H-01-010-14");
		}
		if (kpi.equals("PM-H-01-010-15")) {
			params.put("KPI_ID", "PM-H-01-010-16");
		}
		// I/O two lines
		resultList2 = reportHostService.queryHostMonitorData(params);
		return "dnData";
	}

	/**
	 * 
	 * @Title: validate
	 * @Description:struts2验证，传入的id不能为空
	 * @author duangh
	 * @date Jul 10, 2013 2:55:45 PM
	 */
	@Override
	public void validate() {
		Assert.notNull(id, "host's uuid is requried");
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
		cpuUsedRate = reportHostService.queryHostCpuMenByAvg(id, "PM-H-01-010-11", "60");
		menUsedRate = reportHostService.queryHostCpuMenByAvg(id, "PM-H-01-010-12", "60");
		monitorObj = reportHostService.queryHostDiskNetMonitor(id,"60");		
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
		FusionCharts fChart = reportHostService.queryHostDNChartDayData(id, kpi, motionName,
				cycle_time, startDate, endDate, pointCount);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		printJson(fusionChartsString);
		return null;
	}

	public void queryChartFileSystemDayData() {
		String kpi = "'PM-H-01-010-17','PM-H-01-010-18'";
		FusionCharts fChart = reportHostService.queryChartFileSystemDayData(id, kpi, startDate,
				endDate);
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

	/**
	 *
	 * @return the cpuUsedRate
	 */
	public Double getCpuUsedRate() {
		return cpuUsedRate;
	}

	/**
	 *
	 * @param cpuUsedRate the cpuUsedRate to set
	 */
	public void setCpuUsedRate(Double cpuUsedRate) {
		this.cpuUsedRate = cpuUsedRate;
	}

	/**
	 *
	 * @return the menUsedRate
	 */
	public Double getMenUsedRate() {
		return menUsedRate;
	}

	/**
	 *
	 * @param menUsedRate the menUsedRate to set
	 */
	public void setMenUsedRate(Double menUsedRate) {
		this.menUsedRate = menUsedRate;
	}

}
