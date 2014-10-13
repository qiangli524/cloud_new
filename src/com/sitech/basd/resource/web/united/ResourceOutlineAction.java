package com.sitech.basd.resource.web.united;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.busimanager.service.busitree.BusiManagerTreeService;
import com.sitech.basd.cloud3.domain.departproject.DepartInfoObj;
import com.sitech.basd.fusioncharts.service.HostSystemTopNService;
import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.resource.domain.top.TopTargetObj;
import com.sitech.basd.resource.domain.united.ResourceOutlineObj;
import com.sitech.basd.resource.service.global.HostGlobalService;
import com.sitech.basd.resource.service.global.VmGlobalService;
import com.sitech.basd.resource.service.united.ResourceOutlineService;
import com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao;
import com.sitech.basd.sxcloud.cloud.dao.virtual.TbVirtualDao;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.showresource.ShowResourceService;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.AlarmHostStatistics;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.busisystem.BusiSystemObj;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: ResourceOutlineAction
 * </p>
 * <p>
 * Description: 首页展示相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-1-22 下午2:47:18
 * 
 */
@SuppressWarnings("serial")
@Controller("resourceOutlineAction")
@Scope("prototype")
public class ResourceOutlineAction extends BaseAction {

	private String resourceType;// 资源类型 1cpu 2mem 3sr 4ip

	private String startTime;// 起始时间

	private String endTime;// 截止时间

	private int top_num;

	private List<DepartInfoObj> departList;

	private List<AlarmHostStatistics> netList;

	private List<TopTargetObj> globalList;// 主机全量集合

	@Autowired
	private HostInfoDao hostInfoDao;
	@Autowired
	private HostSystemTopNService hostSystemTopNService;
	@Autowired
	private TbVirtualDao tbVirtualDao;

	@Autowired
	private ResourceOutlineService resourceOutlineService;
	@Autowired
	private ShowResourceService showResourceService;// 用于统计IP
	@Autowired
	private BusiManagerTreeService busiManagerTreeService;
	@Autowired
	private HostGlobalService hostGlobalService;
	@Autowired
	private VmGlobalService vmGlobalService;

	private ResourceOutlineObj sanStoreObj;

	private ResourceOutlineObj nasStoreObj;

	private TopTargetObj topTargetObj;

	private VMHostObj vmHostObj;

	private String type;

	public VMHostObj getVmHostObj() {
		return vmHostObj;
	}

	public void setVmHostObj(VMHostObj vmHostObj) {
		this.vmHostObj = vmHostObj;
	}

	public TopTargetObj getTopTargetObj() {
		return topTargetObj;
	}

	public void setTopTargetObj(TopTargetObj topTargetObj) {
		this.topTargetObj = topTargetObj;
	}

	public List<TopTargetObj> getGlobalList() {
		return globalList;
	}

	public void setGlobalList(List<TopTargetObj> globalList) {
		this.globalList = globalList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTop_num() {
		return top_num;
	}

	public void setTop_num(int top_num) {
		this.top_num = top_num;
	}

	/**
	 * 显示部门的使用率跳转
	 * 
	 * @Title: showResourceDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-22 下午3:13:40
	 */
	public String showResourceDetail() {
		return "showNewResource";
	}

	/**
	 * 显示新的资源详细页面
	 * 
	 * @Title: showNewResourceDetail
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-22 下午4:04:06
	 */
	public String showNewResourceDetail() {
		return "showNewResourceDetail";
	}

	/**
	 * 显示部门预算趋势
	 * 
	 * @Title: showDepartResourceTrend
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return void
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-22 下午3:30:18
	 */
	public String showDepartResourceTrend() {
		// 预设展示的资源类型
		if (resourceType == null || "".equals(resourceType)) {
			resourceType = "1";
		}
		// 预设时间
		if (endTime == null || "".equals(endTime)) {
			endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			if (startTime == null || "".equals(startTime)) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 90);// 默认查询30天的资源变化
				startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
			}
		}
		// 查询所有部门
		DepartInfoObj departInfoObj = new DepartInfoObj();
		departInfoObj.setType("1");
		departList = resourceOutlineService.queryForDepartList(departInfoObj);
		// FusionCharts fChart =
		// resourceOutlineService.queryChartDayData(departList,resourceType);
		String fusionChartsString = resourceOutlineService.buildXmlData(resourceType, departList,
				startTime, endTime);
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		// out.print(fusionChartsString);
		// out.close();
		PrintWriterOut.printWirter(response, fusionChartsString);
		return null;
	}

	/**
	 * 获取cpu的资源分配趋势
	 * 
	 * @Title: showDepartResourceTrendCpu
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-22 下午4:00:28
	 */
	// public String showDepartResourceTrendCpu() {
	// resourceType = "1";
	// // 查询所有部门
	// DepartInfoObj departInfoObj = new DepartInfoObj();
	// departInfoObj.setType("1");
	// departList = resourceOutlineService.queryForDepartList(departInfoObj);
	// // FusionCharts fChart =
	// // resourceOutlineService.queryChartDayData(departList,resourceType);
	// // String fusionChartsString = JacksonUtil.toJson(fChart);
	// String fusionChartsString =
	// resourceOutlineService.buildXmlData(resourceType, departList,startTime,
	// endTime);
	// PrintWriter out = null;
	// try {
	// response.setCharacterEncoding("UTF-8");
	// out = response.getWriter();
	// out.print(fusionChartsString);
	// out.close();
	// } catch (IOException e2) {
	// e2.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * 获取内存的资源分配趋势
	 * 
	 * @Title: showDepartResourceTrendMem
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-22 下午4:00:52
	 */
	// public String showDepartResourceTrendMem() {
	// resourceType = "2";
	// // 查询所有部门
	// DepartInfoObj departInfoObj = new DepartInfoObj();
	// departInfoObj.setType("1");
	// departList = resourceOutlineService.queryForDepartList(departInfoObj);
	// // FusionCharts fChart =
	// // resourceOutlineService.queryChartDayData(departList,resourceType);
	// // String fusionChartsString = JacksonUtil.toJson(fChart);
	// String fusionChartsString =
	// resourceOutlineService.buildXmlData(resourceType, departList,
	// startTime, endTime);
	// PrintWriter out = null;
	// try {
	// response.setCharacterEncoding("UTF-8");
	// out = response.getWriter();
	// out.print(fusionChartsString);
	// out.close();
	// } catch (IOException e2) {
	// e2.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * 获取网络的资源分配趋势
	 * 
	 * @Title: showDepartResourceTrendNet
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-22 下午4:01:02
	 */
	// public String showDepartResourceTrendNet() {
	// resourceType = "4";
	// // 查询所有部门
	// DepartInfoObj departInfoObj = new DepartInfoObj();
	// departInfoObj.setType("1");
	// departList = resourceOutlineService.queryForDepartList(departInfoObj);
	// // FusionCharts fChart =
	// // resourceOutlineService.queryChartDayData(departList,resourceType);
	// // String fusionChartsString = JacksonUtil.toJson(fChart);
	// String fusionChartsString =
	// resourceOutlineService.buildXmlData(resourceType, departList,
	// startTime, endTime);
	// PrintWriter out = null;
	// try {
	// response.setCharacterEncoding("UTF-8");
	// out = response.getWriter();
	// out.print(fusionChartsString);
	// out.close();
	// } catch (IOException e2) {
	// e2.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * 获取存储的 资源分配趋势
	 * 
	 * @Title: showDepartResourceTrendSr
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-22 下午4:01:12
	 */
	// public String showDepartResourceTrendSr() {
	// resourceType = "3";
	// // 查询所有部门
	// DepartInfoObj departInfoObj = new DepartInfoObj();
	// departInfoObj.setType("1");
	// departList = resourceOutlineService.queryForDepartList(departInfoObj);
	// // FusionCharts fChart =
	// // resourceOutlineService.queryChartDayData(departList,resourceType);
	// // String fusionChartsString = JacksonUtil.toJson(fChart);
	// String fusionChartsString =
	// resourceOutlineService.buildXmlData(resourceType, departList,
	// startTime, endTime);
	// PrintWriter out = null;
	// try {
	// response.setCharacterEncoding("UTF-8");
	// out = response.getWriter();
	// out.print(fusionChartsString);
	// out.close();
	// } catch (IOException e2) {
	// e2.printStackTrace();
	// }
	// return null;
	// }

	/**
	 * 
	 * @Title: showHostAndVMs
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-22 下午7:56:27
	 */
	public String showHostAndVMs() {
		FusionCharts charts = resourceOutlineService.queryHostVmsData();
		// PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		// out.print(JacksonUtil.toJson(charts));
		// out.close();
		PrintWriterOut.printWirter(response, JacksonUtil.toJson(charts));
		return null;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @Title: showNetFusionChart
	 * @Description: 显示网络fusionchart
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-22 下午3:34:07
	 */
	public void showNetFusionChart() {
		String chartXml = resourceOutlineService.buildNetChart();
		this.printToScreen(chartXml);
	}

	/**
	 * @Title: printToScreen
	 * @Description: 将内容打印回屏幕
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-22 下午3:35:16
	 */
	private void printToScreen(Object value) {
		try {
			response.setCharacterEncoding("UTF-8");
			// PrintWriter pw = response.getWriter();
			// pw.print(value);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: showResource
	 * @Description: 跳转页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-22 下午4:17:19
	 */
	public String showResource() {
		return "showResource";
	}

	/**
	 * 
	 * @Title: querySANStore
	 * @Description: san存储总量和使用量
	 * @param
	 * @return ResourceOutlineObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-23 下午8:14:07
	 */
	private ResourceOutlineObj querySANStore() {
		ResourceOutlineObj resourceOutlineObj = new ResourceOutlineObj();
		resourceOutlineObj.setType("1");
		ResourceOutlineObj retObj = resourceOutlineService.queryStore(resourceOutlineObj);
		resourceOutlineObj = resourceOutlineService.queryValidStore(resourceOutlineObj);
		if (retObj.getStoreUsed() != null && retObj.getStoreUsed() != "") {
			retObj.setStoreValid(new DecimalFormat("0.00").format(new Double(resourceOutlineObj
					.getStoreValid()) / 1024));
			retObj.setStoreFree(new DecimalFormat("0.00").format(new Double(retObj.getStoreValid())
					- new Double(retObj.getStoreUsed()) / 1024 / 1024));
			retObj.setStoreUsed(new DecimalFormat("0.00").format(new Double(retObj.getStoreUsed()) / 1024 / 1024));
		} else {
			if (retObj.getStoreValid() == null || retObj.getStoreValid() == "") {
				retObj.setStoreValid("0");
				retObj.setStoreUsed("0");
				retObj.setStoreFree("0");
			}
			retObj.setStoreFree(retObj.getStoreValid());
		}
		return retObj;
	}

	private ResourceOutlineObj queryNASStore() {
		ResourceOutlineObj resourceOutlineObj = new ResourceOutlineObj();
		resourceOutlineObj.setType("2");
		ResourceOutlineObj retObj = resourceOutlineService.queryStore(resourceOutlineObj);
		resourceOutlineObj = resourceOutlineService.queryValidStore(resourceOutlineObj);
		if (retObj.getStoreUsed() != null && retObj.getStoreUsed() != "") {
			retObj.setStoreValid(new DecimalFormat("0.00").format(new Double(resourceOutlineObj
					.getStoreValid()) / 1024));
			retObj.setStoreFree(new DecimalFormat("0.00").format(new Double(retObj.getStoreValid())
					- new Double(retObj.getStoreUsed()) / 1024 / 1024));
			retObj.setStoreUsed(new DecimalFormat("0.00").format(new Double(retObj.getStoreUsed()) / 1024 / 1024));
		} else {
			if (retObj.getStoreValid() == null || retObj.getStoreValid() == "") {
				retObj.setStoreValid("0");
				retObj.setStoreUsed("0");
				retObj.setStoreFree("0");
			}
			retObj.setStoreFree(retObj.getStoreValid());
		}
		return retObj;
	}

	/**
	 * 
	 * @Title: queryResource
	 * @Description: 展示图形
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-22 下午4:18:39
	 */
	public String queryResource() {
		FusionCharts fusionCharts = new FusionCharts();
		fusionCharts = resourceOutlineService.queryResource();
		String fuString = JacksonUtil.toJson(fusionCharts);
		printJson(fuString);
		return null;
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
	 * 领导试图 业务展示跳转
	 * 
	 * @Title: newResourceBusi
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-22 下午7:53:58
	 */
	public String newResourceBusi() {
		return "newResourceBusi";
	}

	public String getChartInfoSyncByCenter() {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			BusiManagerTree obj = new BusiManagerTree();
			/**
			 * 通过id判断是从哪里进入页面的
			 */
			String id = "f80f3809cf8840a4af406112a06b5b8d";
			if (id != null) {
				obj.setParent_id(id);
			}
			List<BusiManagerTree> objs = busiManagerTreeService.queryForTree(obj);
			FusionCharts fChart = busiManagerTreeService.queryChartDayData(objs, "业务资源视图");
			String fusionChartsString = JacksonUtil.toJson(fChart);
			response.setCharacterEncoding("UTF-8");
			// out = response.getWriter();
			// out.print(fusionChartsString);
			// out.close();
			PrintWriterOut.printWirter(response, fusionChartsString);
		} catch (Exception e) {
		}
		return null;
	}

	public ResourceOutlineObj getSanStoreObj() {
		return sanStoreObj;
	}

	public void setSanStoreObj(ResourceOutlineObj sanStoreObj) {
		this.sanStoreObj = sanStoreObj;
	}

	public ResourceOutlineObj getNasStoreObj() {
		return nasStoreObj;
	}

	public void setNasStoreObj(ResourceOutlineObj nasStoreObj) {
		this.nasStoreObj = nasStoreObj;
	}

	/**
	 * @Title: showCalcTopN
	 * @Description: topN
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-23 下午7:13:14
	 */
	public void showTopN() {
		if (resourceType == null || "".equals(resourceType)) {
			resourceType = "1";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("top_num", 5);
		map.put("type", resourceType);
		try {
			String chartXml = resourceOutlineService.buildTopNChartXml(map);
			this.printToScreen(chartXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: goToCalcTopPage
	 * @Description: 进入topN页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-23 下午7:40:30
	 */
	public String goToCalcTopPage() {
		return "goToCalcTopPage";
	}

	public String computerResourcePie() {
		return "computerResourcePie";
	}

	public String getComputerHostResourcePie() {
		FusionCharts fChart = resourceOutlineService.queryChartDayDataForPie("host");
		String fusionChartsString = JacksonUtil.toJson(fChart);
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		// out.print(fusionChartsString);
		// out.close();
		PrintWriterOut.printWirter(response, fusionChartsString);
		return null;
	}

	public String getComputerVmResourcePie() {
		FusionCharts fChart = resourceOutlineService.queryChartDayDataForPie("vm");
		String fusionChartsString = JacksonUtil.toJson(fChart);
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		// out.print(fusionChartsString);
		// out.close();
		PrintWriterOut.printWirter(response, fusionChartsString);
		return null;
	}

	/**
	 * 和获取虚拟机的数量
	 * 
	 * @Title: getComputerVmResourcePieCount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-24 下午4:54:20
	 */
	public String getComputerVmResourcePieCount() {
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		// obj.setVH_STAT("1");//开机（已使用）
		List<TbCloud2VirtualInfoObj> resultList = tbVirtualDao.queryForListByObj(obj);
		obj.setVH_STAT("0");// //关机（未使用）
		List<TbCloud2VirtualInfoObj> resultList1 = tbVirtualDao.queryForListByObj(obj);
		int allCount = resultList.size();
		int userCount = allCount - resultList1.size();
		int noUserCount = resultList1.size();
		JSONObject json = new JSONObject();
		json.put("vmUserCount", userCount);
		DecimalFormat df = new DecimalFormat("#.##");
		double vmUserCountPre = userCount * 1.0 / allCount * 100;
		String vmUserCountPres = df.format(vmUserCountPre);
		json.put("vmUserCountPre", vmUserCountPres);
		json.put("vmNoUserCount", noUserCount);
		double vmNoUserCountPre = noUserCount * 1.0 / allCount * 100;
		String vmNoUserCountPres = df.format(vmNoUserCountPre);
		json.put("vmNoUserCountPre", vmNoUserCountPres);
		json.put("vmCount", allCount);
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		// out.print(json);
		// out.close();
		PrintWriterOut.printWirter(response, json);
		return null;
	}

	/**
	 * 和获取主机的数量
	 * 
	 * @Title: getComputerHostResourcePieCount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-24 下午4:54:28
	 */
	public String getComputerHostResourcePieCount() {
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		obj.setAllocated(0);// 未分配
		List<TbCloud2HostInfoObj> resultList = hostInfoDao.queryForListByObjByAllocated(obj);
		obj.setAllocated(1);// 已分配
		List<TbCloud2HostInfoObj> resultList1 = hostInfoDao.queryForListByObjByAllocated(obj);
		int userCount = resultList1.size();
		int noUserCount = resultList.size();
		int allCount = userCount + noUserCount;
		JSONObject json = new JSONObject();
		json.put("hostUserCount", userCount);
		DecimalFormat df = new DecimalFormat("#.##");
		double hostUserCountPre = userCount * 1.0 / allCount * 100;
		String hostUserCountPres = df.format(hostUserCountPre);
		json.put("hostUserCountPre", hostUserCountPres);
		json.put("hostNoUserCount", noUserCount);
		double hostNoUserCountPre = noUserCount * 1.0 / allCount * 100;
		String hostNoUserCountPres = df.format(hostNoUserCountPre);
		json.put("hostNoUserCountPre", hostNoUserCountPres);
		json.put("hostCount", allCount);
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		// out.print(json);
		// out.close();
		PrintWriterOut.printWirter(response, json);
		return null;
	}

	public String showNetInfo() {
		netList = showResourceService.queryIpData();
		return "unitedIp";
	}

	public List<AlarmHostStatistics> getNetList() {
		return netList;
	}

	public void setNetList(List<AlarmHostStatistics> netList) {
		this.netList = netList;
	}

	public String toTabs() {
		return "new_resource_tab";
	}

	/**
	 * 存储的跳转
	 * 
	 * @Title: storeSanAndNas
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-24 上午10:45:38
	 */
	public String storeSanAndNas() {
		try {
			sanStoreObj = this.querySANStore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			nasStoreObj = this.queryNASStore();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "storeSanAndNas";
	}

	/**
	 * @Title: goToBusiTopN
	 * @Description: 进入业务占用资源topN页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 上午9:15:15
	 */
	public String goToBusiTopN() {
		return "gotobusitopn";
	}

	/**
	 * @Title: goToBusiDetailTopN
	 * @Description: 进入业务系统占用资源topN详细信息页
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 上午11:29:51
	 */
	public String goToBusiDetailTopN() {
		return "gotobusidetailtopn";
	}

	/**
	 * @Title: showBusiTopN
	 * @Description: 显示业务占用资源topN
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 上午9:16:43
	 */
	public void showBusiTopN() {
		if (resourceType == null || "".equals(resourceType)) {
			resourceType = "1";// 默认虚拟机
		}
		if (top_num == 0) {
			top_num = 10;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resourceType", resourceType);
		paramMap.put("top_num", top_num);
		String chartJson = resourceOutlineService.buildBusiTopNChartJSON(paramMap);
		this.printToScreen(chartJson);
	}

	/**
	 * @Title: showBusiDetailTopN
	 * @Description: 业务系统占用资源topN详细信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 上午11:53:02
	 */
	public void showBusiDetailTopN() {
		if (resourceType == null || "".equals(resourceType)) {
			resourceType = "1";// 默认虚拟机
		}
		if (top_num == 0) {
			top_num = 10;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resourceType", resourceType);
		paramMap.put("top_num", top_num);
		String chartJson = resourceOutlineService.buildBusiTopNChartJSON(paramMap);
		List<BusiSystemObj> resultList = resourceOutlineService.queryBusiSystemTopList(paramMap);
		JSONObject jo = new JSONObject();
		jo.put("resultList", resultList);
		jo.put("chartXml", chartJson);
		this.printToScreen(jo);
	}

	/**
	 * @Title: listHostTopN
	 * @Description: 进入主机topN页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 下午4:48:35
	 */
	public String listHostTopN() {
		return "listHostTopN";
	}

	/**
	 * @Title: acquireHostTopNData
	 * @Description: 获取主机topN数据
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-25 上午11:44:51
	 */
	public void acquireHostTopNData() {
		if (resourceType == null || "".equals(resourceType)) {
			resourceType = "1";// 默认cpu
		}
		if (top_num == 0) {
			top_num = 5;// 默认top5
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("top_num", top_num);
		if ("2".equals(resourceType)) {
			paramMap.put("type", "mem");
			paramMap.put("entity_type", "2");
		} else {
			paramMap.put("type", "cpu");
			paramMap.put("entity_type", "2");
		}
		globalList = hostSystemTopNService.queryTopList(paramMap);
		JSONObject jo = new JSONObject();
		jo.put("globalList", globalList);
		this.printToScreen(jo);
	}

	/**
	 * @Title: listHostTopNDetail
	 * @Description: 展示主机topN详细信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-24 下午5:38:27
	 */
	public String listHostTopNDetail() {
		if (resourceType == null || "".equals(resourceType)) {
			resourceType = "1";
		}
		if (topTargetObj == null) {
			topTargetObj = new TopTargetObj();
		}
		topTargetObj.setPagination(this.getPaginater().initPagination(request));
		globalList = resourceOutlineService.queryHostTopList(resourceType, topTargetObj);
		return "listHostTopNDetail";
	}

	/**
	 * @Title: listBusiCalcResUsage
	 * @Description: 进入虚拟机资源利用率topN页面，从业务维度出发
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午1:50:48
	 */
	public String listBusiCalcResUsage() {
		return "listBusiCalcResUsage";
	}

	/**
	 * @Title: acquireVmList
	 * @Description: 查询虚拟机列表，业务维度
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午2:05:04
	 */
	public void acquireVmList() {
		if (resourceType == null || "".equals(resourceType)) {
			resourceType = "1";// 默认cpu
		}
		if (top_num == 0) {
			top_num = 5;// 默认top5
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("top_num", top_num);
		paramMap.put("resourceType", resourceType);
		globalList = vmGlobalService.queryForTopList(paramMap);
		JSONObject jo = new JSONObject();
		jo.put("globalList", globalList);
		this.printToScreen(jo);
	}

	/**
	 * @Title: listVmTopNDetail
	 * @Description: 查询虚拟机topn详细信息，业务维度
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午2:12:00
	 */
	public String listVmTopNDetail() {
		if (resourceType == null || "".equals(resourceType)) {
			resourceType = "1";
		}
		if (topTargetObj == null) {
			topTargetObj = new TopTargetObj();
		}
		topTargetObj.setPagination(this.getPaginater().initPagination(request));
		globalList = resourceOutlineService.queryVmTopList(resourceType, topTargetObj);
		return "listVmTopNDetail";
	}

	/**
	 * @Title: queryVMConfigInfo
	 * @Description: 展示虚拟机配置信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午6:41:13
	 */
	public String queryVMConfigInfo() {
		if (topTargetObj == null) {
			topTargetObj = new TopTargetObj();
		}
		vmHostObj = resourceOutlineService.queryVmHostConfigInfo(topTargetObj);
		return "vmconfiginfo";
	}
}
