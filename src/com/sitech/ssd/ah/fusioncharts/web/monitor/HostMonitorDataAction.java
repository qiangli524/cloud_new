package com.sitech.ssd.ah.fusioncharts.web.monitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.sitech.basd.cloud3.domain.charts.DataPoint;
import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.cloud.service.reporthost.ReportHostService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;

@Controller("hostMonitorDataAction_ah")
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

	/** 指定时间段内cpu使用率的平均值 */
	private Double cpuUsedRate;
	/** 指定时间段内men使用率的平均值 */
	private Double menUsedRate;

	@Autowired
	private ReportHostService reportHostService_ah;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCycle_time() {
		return cycle_time;
	}

	public void setCycle_time(String cycle_time) {
		this.cycle_time = cycle_time;
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

	public ReportHostService getReportHostService_ah() {
		return reportHostService_ah;
	}

	public void setReportHostService_ah(ReportHostService reportHostService_ah) {
		this.reportHostService_ah = reportHostService_ah;
	}
	
	
}
