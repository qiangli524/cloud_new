package com.sitech.basd.sxcloud.cloud.web.monitor.action;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sitech.basd.rest.configure.domain.ConfigCloudInfo;
import com.sitech.basd.rest.configure.operation.ConfigurationOperation;
import com.sitech.basd.rest.resource.domain.StatisticsResourceStateInfo;
import com.sitech.basd.rest.resource.operation.StatisticsResourceOperation;
import com.sitech.basd.rest.workloads.domain.StatsInfo;
import com.sitech.basd.rest.workloads.domain.WorkloadsStatsInfo;
import com.sitech.basd.rest.workloads.operation.WorkloadsOperation;
import com.sitech.basd.sxcloud.cloud.domain.eventmanage.EventManageObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorObj;
import com.sitech.basd.sxcloud.cloud.service.alarm.TbCloud2MonitorAlarmService;
import com.sitech.basd.sxcloud.cloud.service.eventmanage.EventManageService;
import com.sitech.basd.sxcloud.cloud.service.monitor.MonitorService;
import com.sitech.basd.sxcloud.cloud.web.monitor.form.MonitorForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
@Component("monitorAction")
public class MonitorAction extends BaseAction {
	private MonitorForm theForm;

	public MonitorForm getTheForm() {
		return theForm;
	}

	public void setTheForm(MonitorForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询物理主机信息
	 * @Copyright: Copyright (c) 20120107
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listPhysicalServerInfo() {
		if (theForm == null) {
			theForm = new MonitorForm();
		}
		MonitorObj obj = new MonitorObj();
		if (theForm.getEQ_ID() != null && !"".equals(theForm.getEQ_ID())) {
			obj.setEQ_ID(theForm.getEQ_ID().trim());
		}
		List monitorList = monitorService.queryPhysicForListByObj(obj);
		theForm.setResultList(monitorList);
		return "";
	}

	/**
	 * @Title:查询虚拟主机信息
	 * @Copyright: Copyright (c) 20120107
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listVirtualServerInfo() {
		if (theForm == null) {
			theForm = new MonitorForm();
		}
		MonitorObj obj = new MonitorObj();
		if (theForm.getHY_ID() != null && !"".equals(theForm.getHY_ID())) {
			obj.setHY_ID(theForm.getHY_ID().trim());
		}
		List monitorList = monitorService.queryVirtualForListByObj(obj);
		theForm.setResultList(monitorList);
		return "";
	}

	/**
	 * @Title:查询应用信息
	 * @Copyright: Copyright (c) 20120107
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listAppInfo() {
		if (theForm == null) {
			theForm = new MonitorForm();
		}
		MonitorObj obj = new MonitorObj();
		if (theForm.getAPP_ID() != null && !"".equals(theForm.getAPP_ID())) {
			obj.setAPP_ID(theForm.getAPP_ID().trim());
		}
		List monitorList = monitorService.queryAppForListByObj(obj);
		theForm.setResultList(monitorList);
		return "";
	}

	/**
	 * @Title:查询出监控的云状态信息
	 * @Copyright: Copyright (c) 20120107
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listCloudInfo() {
		if (theForm == null) {
			theForm = new MonitorForm();
		}
		EventManageObj obj = new EventManageObj();

		ConfigCloudInfo info = null;
		try {
			info = ConfigurationOperation.getConfigurationCloud();
		} catch (Exception e) {
			LogHelper.error("KVM Or Director Connect Error：" + e.getMessage());
			/** 插入告警 */
			tbCloud2MonitorAlarmService.insertAlarmWhenConnError();
		}
		if (info != null) {
			theForm.setHostname(info.getHostname());
			theForm.setPort(info.getPort());
			theForm.setVersion(info.getVersion());
			theForm.setCloudType(info.getCloudType());
			theForm.setState(info.getState());
			theForm.setUsername(info.getUsername());
			Struts2Utils.getRequest().setAttribute("cloud", info.getStateId());

			WorkloadsStatsInfo wsinfo = WorkloadsOperation.getWorkloadsStats();
			List<StatsInfo> stats = wsinfo.getStats();
			for (StatsInfo statsinfo : stats) {
				if (statsinfo.getValue().equals("OK")) {
					theForm.setCount1(statsinfo.getCount());
				}
				if (statsinfo.getValue().equals("ERROR")) {
					theForm.setCount2(statsinfo.getCount());
				}
				if (statsinfo.getValue().equals("ATTEMPTED")) {
					theForm.setCount2(theForm.getCount2()
							+ statsinfo.getCount());
				}
				if (statsinfo.getValue().equals("CANCELED")) {
					theForm.setCount2(theForm.getCount2()
							+ statsinfo.getCount());
				}
				if (statsinfo.getValue().equals("FAILED")) {
					theForm.setCount2(theForm.getCount2()
							+ statsinfo.getCount());
				}
				if (statsinfo.getValue().equals("DELETING")) {
					theForm.setCount3(statsinfo.getCount());
				}
				if (statsinfo.getValue().equals("EXECUTING")) {
					theForm.setCount3(theForm.getCount3()
							+ statsinfo.getCount());
				}
				if (statsinfo.getValue().equals("RESIZING")) {
					theForm.setCount3(theForm.getCount3()
							+ statsinfo.getCount());
				}
				if (statsinfo.getValue().equals("STOPPED")) {
					theForm.setCount4(statsinfo.getCount());
				}
				if (statsinfo.getValue().equals("UNKNOWN")) {
					theForm.setCount5(statsinfo.getCount());
				}

			}
			// theForm.setWorkloadsList(stats);

			List<StatisticsResourceStateInfo> resourceList = StatisticsResourceOperation
					.getStatsUsage();// 获取所有已使用的CPU、Memory和Storage
			for (StatisticsResourceStateInfo srsinfo : resourceList) {
				if (!srsinfo.getId().equals("usageCPU")) {
					double x = Double.parseDouble(srsinfo.getValue()) / 1024;
					BigDecimal b = new BigDecimal(x); // 保留小数点后一位
					double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
					srsinfo.setValue(Double.toString(f1));
					srsinfo.setLabel(srsinfo.getLabel() + "(千兆字节)");
				} else {
					srsinfo.setLabel(srsinfo.getLabel() + "(CPU)");
					BigDecimal b = new BigDecimal(Double.parseDouble(srsinfo
							.getValue())); // 保留小数点后一位
					double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
					srsinfo.setValue(Double.toString(f1));
				}
			}
			theForm.setResourceList(resourceList);

			List eventManageList = eventManageService.queryForListByObj(obj);// 查询最新事件
			theForm.setEventManageList(eventManageList);
		} else {
			return "listCloudInfo";
		}

		return "listCloudInfo";
	}

	/**
	 * 
	 * @Title: 进入首页
	 * @Copyright: Copyright (c) 2012-3-20
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String gotoFirstPage() throws BaseException {
		if (theForm == null) {
			theForm = new MonitorForm();
		}
		EventManageObj obj = new EventManageObj();

		// 获得云状态信息
		ConfigCloudInfo info = ConfigurationOperation.getConfigurationCloud();
		theForm.setHostname(info.getHostname());
		theForm.setPort(info.getPort());
		theForm.setVersion(info.getVersion());
		theForm.setCloudType(info.getCloudType());
		theForm.setState(info.getState());
		theForm.setUsername(info.getUsername());

		// 获得工作负载摘要信息
		WorkloadsStatsInfo wsinfo = WorkloadsOperation.getWorkloadsStats();
		List<StatsInfo> stats = wsinfo.getStats();
		for (StatsInfo statsinfo : stats) {
			if (statsinfo.getValue().equals("OK")) {
				theForm.setCount1(statsinfo.getCount());
			}
			if (statsinfo.getValue().equals("ERROR")) {
				theForm.setCount2(statsinfo.getCount());
			}
			if (statsinfo.getValue().equals("ATTEMPTED")) {
				theForm.setCount2(theForm.getCount2() + statsinfo.getCount());
			}
			if (statsinfo.getValue().equals("CANCELED")) {
				theForm.setCount2(theForm.getCount2() + statsinfo.getCount());
			}
			if (statsinfo.getValue().equals("FAILED")) {
				theForm.setCount2(theForm.getCount2() + statsinfo.getCount());
			}
			if (statsinfo.getValue().equals("DELETING")) {
				theForm.setCount3(statsinfo.getCount());
			}
			if (statsinfo.getValue().equals("EXECUTING")) {
				theForm.setCount3(theForm.getCount3() + statsinfo.getCount());
			}
			if (statsinfo.getValue().equals("RESIZING")) {
				theForm.setCount3(theForm.getCount3() + statsinfo.getCount());
			}
			if (statsinfo.getValue().equals("STOPPED")) {
				theForm.setCount4(statsinfo.getCount());
			}
			if (statsinfo.getValue().equals("UNKNOWN")) {
				theForm.setCount5(statsinfo.getCount());
			}

		}
		// theForm.setWorkloadsList(stats);

		// 获取所有已使用的CPU、Memory和Storage
		List<StatisticsResourceStateInfo> resourceList = StatisticsResourceOperation
				.getStatsUsage();
		for (StatisticsResourceStateInfo srsinfo : resourceList) {
			if (!srsinfo.getLabel().equals("处理器数")) {
				double x = Double.parseDouble(srsinfo.getValue()) / 1024;
				BigDecimal b = new BigDecimal(x); // 保留小数点后一位
				double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				srsinfo.setValue(Double.toString(f1));
				srsinfo.setLabel(srsinfo.getLabel() + "(千兆字节)");
			} else {
				srsinfo.setLabel(srsinfo.getLabel() + "(CPU)");
				BigDecimal b = new BigDecimal(Double.parseDouble(srsinfo
						.getValue())); // 保留小数点后一位
				double f1 = b.setScale(1, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				srsinfo.setValue(Double.toString(f1));
			}
		}
		theForm.setResourceList(resourceList);

		List eventManageList = eventManageService.queryForListByObj(obj);// 查询最新事件
		theForm.setEventManageList(eventManageList);

		return ""; // 去往首页
	}

	private MonitorService monitorService;
	private TbCloud2MonitorAlarmService tbCloud2MonitorAlarmService;

	public void setTbCloud2MonitorAlarmService(
			TbCloud2MonitorAlarmService tbCloud2MonitorAlarmService) {
		this.tbCloud2MonitorAlarmService = tbCloud2MonitorAlarmService;
	}

	public void setMonitorService(MonitorService monitorService) {
		this.monitorService = monitorService;
	}

	private EventManageService eventManageService;

	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}
}
