package com.sitech.basd.cloud3.web.charts.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.reporthost.ReportHostService;
import com.sitech.basd.sxcloud.cloud.service.reportvirtual.ReportVirtualService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.showresource.ShowResourceService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.service.xencluster.XenClusterService;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: HighChartsAction
 * </p>
 * <p>
 * Description: HighChartsAction--报表数据
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Feb 25, 2013 10:22:23 AM
 * 
 */
public class HighChartsAction extends CRUDBaseAction {
	private String type;
	private String rownum;
	private static final Logger logger = Logger.getLogger(HighChartsAction.class);
	private static final long serialVersionUID = 7830970944922735459L;
	private ReportVirtualService reportVirtualService;
	private ReportHostService reportHostService;
	private XenClusterService xenClusterService;
	private ShowResourceService showResourceService;

	private HostInfoService hostInfoService;
	private VMHostService vmHostService;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	public void setXenClusterService(XenClusterService xenClusterService) {
		this.xenClusterService = xenClusterService;
	}

	public void setReportHostService(ReportHostService reportHostService) {
		this.reportHostService = reportHostService;
	}

	public void setShowResourceService(ShowResourceService showResourceService) {
		this.showResourceService = showResourceService;
	}

	public void setReportVirtualService(ReportVirtualService reportVirtualService) {
		this.reportVirtualService = reportVirtualService;
	}

	/**
	 * 
	 * @Title: getVMMonitorData
	 * @Description: 获取虚拟机监控数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 25, 2013 10:25:41 AM
	 */
	@Deprecated
	public String getVMMonitorData() {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 获取查看数据时间间隔：若一天
		String time = request.getParameter("time");
		// 指标ID
		String kpi = request.getParameter("kpi");
		// 虚拟机ID
		String hyid = request.getParameter("hyid");
		double time_double = Double.parseDouble(time);
		// 3600000-一小时 * 24--一天
		double time_inter = time_double / (3600000 * 24);
		Map<String, String> params = new HashMap<String, String>();
		params.put("KPI_ID", kpi);
		params.put("HY_ID", hyid);
		params.put("TIME_INTER", String.valueOf(time_inter));
		List<Map> list = reportVirtualService.queryVirtualMonitorData(params);
		/**
		 * 查询后，分时间段计算，1年可以按照月份分为12个点，算出平均值，在展示
		 * 有时间再做------------------------------
		 * ------------------------------------
		 * -----------------------------待修改。。。。。。。
		 */
		String result = dealDataToCharts(list, "HY_DATA");
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// out = response.getWriter();
		// out.print(result);
		PrintWriterOut.printWirter(response, result);
		// out.close();
		return null;
	}

	/**
	 * 虚拟机监控数据，从tab页中调入。 network is wrong,update by duangh
	 * 
	 * @return
	 */
	@Deprecated
	public String vmMonitorData() {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 指标ID
		String kpi = request.getParameter("kpi");
		// 虚拟机ID
		String hyid = request.getParameter("hyid");

		/** 黑龙江修改，暂定修改vh_uuid为vh_id */
		VMHostObj v = new VMHostObj();
		v.setVH_UUID(hyid);
		v = vmHostService.queryByObj(v);
		hyid = v.getVH_ID();

		// 监控指标的名称
		String motionName = request.getParameter("motionName");

		request.setAttribute("kpi", kpi);
		request.setAttribute("hyid", hyid);
		request.setAttribute("motionName", motionName);
		return "moniter_vm";
	}

	/**
	 * 主机监控数据，从tab页中调入。
	 * 
	 * @return
	 */
	@Deprecated
	public String hostMonitorData() {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 指标ID
		String kpi = request.getParameter("kpi");
		// 主机ID
		String eqid = request.getParameter("eqid");

		/** 黑龙江修改，暂定，修改eqid 为 主机id */
		TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
		hostObj.setH_uuid(eqid);
		hostObj = hostInfoService.queryByObj(hostObj);
		eqid = String.valueOf(hostObj.getEq_id());

		// 监控指标的名称
		String motionName = request.getParameter("motionName");

		request.setAttribute("kpi", kpi);
		request.setAttribute("eqid", eqid);
		request.setAttribute("motionName", motionName);
		return "moniter_host";
	}

	/**
	 * 虚拟机监控数据，从调入tab的action. network is wrong,update by duangh
	 * 
	 * @return
	 */
	@Deprecated
	public String tabVmMonitorData() {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 虚拟机ID
		String hyid = request.getParameter("hyid");

		request.setAttribute("hyid", hyid);
		return "moniter_tab_vm";
	}

	/**
	 * 主机监控数据，从调入tab的action
	 * 
	 * @return
	 */
	@Deprecated
	public String tabHostMonitorData() {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 获取查看数据时间间隔：若一天
		String time = request.getParameter("time");
		String eqid = request.getParameter("eqid");

		request.setAttribute("time", time);
		request.setAttribute("eqid", eqid);
		return "moniter_tab_host";
	}

	/**
	 * 
	 * @Title: getHostMonitorData
	 * @Description: 获取虚拟机监控数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 25, 2013 10:25:41 AM
	 */
	@Deprecated
	public String getHostMonitorData() {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 获取查看数据时间间隔：若一天
		String time = request.getParameter("time");
		// 指标ID
		String kpi = request.getParameter("kpi");
		// 主机ID
		String eqid = request.getParameter("eqid");
		double time_double = Double.parseDouble(time);
		// 3600000-一小时 * 24--一天
		double time_inter = time_double / (3600000 * 24);
		Map<String, String> params = new HashMap<String, String>();
		params.put("KPI_ID", kpi);
		params.put("EQ_ID", eqid);
		params.put("TIME_INTER", String.valueOf(time_inter));
		List<Map> list = reportHostService.queryHostMonitorData(params);
		String result = dealDataToCharts(list, "HOST_DATA");
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// out = response.getWriter();
		// out.print(result);
		PrintWriterOut.printWirter(response, result);
		// out.close();
		return null;
	}

	/**
	 * 
	 * @Title: dealDataToCharts
	 * @Description: use json not array;update by duangh
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.1
	 * @createtime Feb 25, 2013 1:50:48 PM
	 */
	@Deprecated
	public String dealDataToCharts(List<Map> list, String key) {
		StringBuffer temp = new StringBuffer();
		String result = "[]";
		try {
			if (list != null && list.size() > 0) {
				temp.append("[");
				for (int i = 0; i < list.size(); i++) {
					temp.append(list.get(i).get(key) + ",");
				}
				result = temp.toString();
				result = result.substring(0, result.lastIndexOf(","));
				result += "]";
			}
		} catch (Exception e) {
			result = "[]";
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 * @Title: getMarginResourceData
	 * @Description: 获取资源余量数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 25, 2013 10:25:41 AM
	 */
	@Deprecated
	public String getResourceChartsData() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String rownum = request.getParameter("rownum");
		Map<String, String> resultMap = new HashMap<String, String>();
		if (rownum == null || "".equals(rownum)) {
			// 默认显示200个监控数据点
			rownum = "200";
		}
		List resultList = new ArrayList();
		Map params = new HashMap();
		if ("all".equals(type)) {
			// CPU
			params.put("KPI_ID", "PM-R-01-010-09");
			params.put("ROWNUM", Integer.parseInt(rownum));
			resultList = showResourceService.getResourceList(params);
			resultMap.put("cpu", dealDataToCharts(resultList, "RESOURCE_DATA"));
			// 内存
			params.put("KPI_ID", "PM-R-01-010-010");
			params.put("ROWNUM", Integer.parseInt(rownum));
			resultList = showResourceService.getResourceList(params);
			resultMap.put("mem", dealDataToCharts(resultList, "RESOURCE_DATA"));
			// 存储
			params.put("KPI_ID", "PM-R-01-010-011");
			params.put("ROWNUM", Integer.parseInt(rownum));
			resultList = showResourceService.getResourceList(params);
			resultMap.put("storage", dealDataToCharts(resultList, "RESOURCE_DATA"));
		} else if ("vmware".equals(type)) {
			// CPU
			params.put("KPI_ID", "PM-R-01-010-06");
			params.put("ROWNUM", Integer.parseInt(rownum));
			resultList = showResourceService.getResourceList(params);
			resultMap.put("cpu", dealDataToCharts(resultList, "RESOURCE_DATA"));
			// 内存
			params.put("KPI_ID", "PM-R-01-010-07");
			params.put("ROWNUM", Integer.parseInt(rownum));
			resultList = showResourceService.getResourceList(params);
			resultMap.put("mem", dealDataToCharts(resultList, "RESOURCE_DATA"));
			// 存储
			params.put("KPI_ID", "PM-R-01-010-08");
			params.put("ROWNUM", Integer.parseInt(rownum));
			resultList = showResourceService.getResourceList(params);
			resultMap.put("storage", dealDataToCharts(resultList, "RESOURCE_DATA"));
		} else if ("xen".equals(type)) {
			// CPU
			params.put("KPI_ID", "PM-R-01-010-03");
			params.put("ROWNUM", rownum);
			resultList = showResourceService.getResourceList(params);
			resultMap.put("cpu", dealDataToCharts(resultList, "RESOURCE_DATA"));
			// 内存
			params.put("KPI_ID", "PM-R-01-010-04");
			params.put("ROWNUM", rownum);
			resultList = showResourceService.getResourceList(params);
			resultMap.put("mem", dealDataToCharts(resultList, "RESOURCE_DATA"));
			// 存储
			params.put("KPI_ID", "PM-R-01-010-05");
			params.put("ROWNUM", rownum);
			resultList = showResourceService.getResourceList(params);
			resultMap.put("storage", dealDataToCharts(resultList, "RESOURCE_DATA"));
		}
		String result = JSONObject.fromObject(resultMap).toString();

		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// out = response.getWriter();
		// out.print(result);
		PrintWriterOut.printWirter(response, result);
		// out.close();
		return null;
	}

	public String getMarginResourceData() {
		HttpServletResponse response = Struts2Utils.getResponse();

		DecimalFormat nf = new DecimalFormat("0");
		// XenClusterObj obj = new XenClusterObj();
		// List<XenClusterObj> list = xenClusterService.queryListByObj(obj);
		String pool_uuid = "5c16c2e3-0eb3-c62a-1a51-695cfd425d68";
		String xen_param = "/xen/pool/motions/get/[poolUuid:" + pool_uuid + "]/";
		String xen_result = InvokeUtil.invoke(xen_param);
		JSONObject xen_js = JSONObject.fromObject(xen_result);
		double x_cpu = 30;
		double x_cpu_use = 20;
		double x_mem = xen_js.getLong("memSize");
		double x_mem_use = xen_js.getLong("memoryUtilization");
		double x_store = xen_js.getLong("srSize");
		double x_store_use = xen_js.getLong("srUtilisation");
		String result = "{\"cpu\":\"[[\'未使用\'," + (x_cpu - x_cpu_use) + "],[\'已使用\'," + x_cpu_use
				+ "]]\",\"mem\":\"[[\'未使用\'," + (x_mem - x_mem_use) + "],[\'已使用\'," + x_mem_use
				+ "]]\",\"storage\":\"[[\'未使用\'," + (x_store - x_store_use) + "],[\'已使用\',"
				+ x_store_use + "]]\"}";

		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(result);
		PrintWriterOut.printWirter(response, result);
		// out.close();
		return null;
	}
}
