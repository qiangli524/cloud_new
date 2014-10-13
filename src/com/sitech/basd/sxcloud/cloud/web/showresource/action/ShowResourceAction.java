package com.sitech.basd.sxcloud.cloud.web.showresource.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.alarm.TbCloud2MonitorAlarmService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.showresource.ShowResourceService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.AlarmHostStatistics;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.OtherHostStatistics;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.PowerHostStatistics;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.ShowResourceForm;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.VmwareHostStatistics;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.XenHostStatistics;
import com.sitech.basd.yicloud.service.xencluster.XenClusterService;
import com.sitech.basd.yicloud.util.InvokeUtil;

public class ShowResourceAction {
	private HostInfoService hostInfoService;
	private VMHostService vmHostService;
	private NetService netService;
	private TbCloud2MonitorAlarmService tbCloud2MonitorAlarmService;
	private XenClusterService xenClusterService;
	private ShowResourceForm theForm;
	private ShowResourceService showResourceService;
	private Map<String,String> map = new HashMap<String,String>();  

	public void setShowResourceService(ShowResourceService showResourceService) {
		this.showResourceService = showResourceService;
	}

	public ShowResourceForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ShowResourceForm theForm) {
		this.theForm = theForm;
	}

	public void setXenClusterService(XenClusterService xenClusterService) {
		this.xenClusterService = xenClusterService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setVmHostService(VMHostService vmHostService) {
		this.vmHostService = vmHostService;
	}

	public void setNetService(NetService netService) {
		this.netService = netService;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	/**
	 * 
	 * @Title: getAllHostMsg
	 * @Description: 获取所有资源信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 22, 2013 9:10:08 AM
	 */
	public String getAllHostMsg() {
		if (theForm == null) {
			theForm = new ShowResourceForm();
		}
		List list = new ArrayList();

		// 获取主机信息
		return null;
	}

	/**
	 * 
	 * @Title: getHostResource
	 * @Description: 获取主机资源信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue update by huojla @ 20130810
	 * @version 1.0
	 * @createtime Feb 20, 2013 1:59:46 PM
	 */
	public String getHostResource_old() {
		// HttpServletRequest request = Struts2Utils.getRequest();
		// String type = request.getParameter("type");
		if (theForm == null) {
			theForm = new ShowResourceForm();
		}
		// DecimalFormat nf = new DecimalFormat("0");
		// // 获取第几次调用
		// // int sequence = ;
		// // double cpu_all = 0.0;
		// // double cpu_all_use = 0.0;
		// // double mem_all = 0.0;
		// // double mem_all_use = 0.0;
		// // double store_all = 0.0;
		// // double store_all_use = 0.0;
		// // 获取xen的主机信息
		// ShowResourceObj sObj = new ShowResourceObj();
		// String pool_uuid = "5c16c2e3-0eb3-c62a-1a51-695cfd425d68";
		// // XenClusterObj obj = new XenClusterObj();
		// // List<XenClusterObj> list = xenClusterService.queryListByObj(obj);
		// double x_mem = 0.0;
		// double x_mem_use = 0.0;
		// double x_store = 0.0;
		// double x_store_use = 0.0;
		// String xen_param = "/xen/pool/motions/get/[poolUuid:" + pool_uuid
		// + "]/";
		//
		// String xen_result = InvokeUtil.invoke(xen_param);
		// JSONObject xen_js = JSONObject.fromObject(xen_result);
		// // double x_cpu = xen_js.getInt("cpuSize") * 8;
		// // double x_cpu_use = xen_js.getInt("cpuUtilization");
		// if ("1".equals(xen_js.get("responseCode"))) {
		// x_mem = xen_js.getLong("memSize");
		// x_mem_use = xen_js.getLong("memoryUtilization");
		// x_store = xen_js.getLong("srSize");
		// x_store_use = xen_js.getLong("srUtilisation");
		// } else {
		// x_mem = 40840;
		// x_mem_use = 39792;
		// x_store = 6897;
		// x_store_use = 719;
		// }
		//
		// // theForm.setX_cpu(nf.format(x_cpu));
		// // theForm.setX_cpu_use(nf.format(x_cpu_use));
		// // theForm.setX_cpu_free(nf.format(x_cpu - x_cpu_use));
		// theForm.setX_mem(nf.format(x_mem));
		// theForm.setX_mem_use(nf.format(x_mem_use));
		// theForm.setX_mem_free(nf.format(x_mem - x_mem_use));
		// theForm.setX_store(nf.format(x_store));
		// theForm.setX_store_use(nf.format(x_store_use));
		// theForm.setX_store_free(nf.format(x_store - x_store_use));
		// if (nf.format(x_mem).equals("0")) {
		// theForm.setX_mem_per("0");
		// } else {
		// theForm.setX_mem_per(nf.format(x_mem_use / x_mem * 100.0));
		// }
		// // theForm.setX_cpu_per(nf.format(x_cpu_use / x_cpu * 100.0));
		//
		// theForm.setX_store_per(nf.format(x_store_use / x_store * 100.0));

		// 获取vmvare主机信息
		// String vmvare_param = "/vmware/resource/msg";
		// String vmvare_result = InvokeUtil.invoke(vmvare_param);
		// JSONObject js = JSONObject.fromObject(vmvare_result);
		// // double v_cpu = Double.parseDouble(js.getString("sumcpu"));
		// // double v_cpu_use = Double.parseDouble(js.getString("usedcpu"));
		// double v_mem = Double.parseDouble(js.getString("summem"));
		// double v_mem_use = Double.parseDouble(js.getString("usedmem"));// Mb
		// double v_store = Double.parseDouble(js.getString("sumstorage"));
		// double v_store_use =
		// Double.parseDouble(js.getString("usedstorage"));// GB
		//
		// // theForm.setV_cpu(nf.format(v_cpu));
		// // theForm.setV_cpu_use(nf.format(v_cpu_use));
		// // theForm.setV_cpu_free(nf.format(v_cpu - v_cpu_use));
		// theForm.setV_mem(nf.format(v_mem));
		// theForm.setV_mem_use(nf.format(v_mem_use));
		// theForm.setV_mem_free(nf.format(v_mem - v_mem_use));
		// theForm.setV_store(nf.format(v_store));
		// theForm.setV_store_use(nf.format(v_store_use));
		// theForm.setV_store_free(nf.format(v_store - v_store_use));
		// // theForm.setV_cpu_per(nf.format(v_cpu_use / v_cpu * 100));
		// theForm.setV_mem_per(nf.format(v_mem_use / v_mem * 100));
		// theForm.setV_store_per(nf.format(v_store_use / v_store * 100));
		//
		// // 总的资源信息
		// // theForm.setCpu_all(nf.format(x_cpu + v_cpu));
		// // theForm.setCpu_all_use(nf.format(x_cpu_use + v_cpu_use));
		// theForm.setMem_all(nf.format(x_mem + v_mem));
		// theForm.setMem_all_use(nf.format(x_mem_use + v_mem_use));
		// theForm.setStore_all(nf.format(x_store + v_store));
		// theForm.setStore_all_use(nf.format(x_store_use + v_store_use));

		// 获取网络信息
		/*
		 * List<HashMap> netList = getNetAndIps(); List netLists = new
		 * ArrayList(); for (HashMap h : netList) { Map m = new HashMap();
		 * double isuse = Double.parseDouble(h.get("ISUSED").toString()); double
		 * alls = Double.parseDouble(h.get("ALLS").toString()); String per =
		 * nf.format(100 - isuse / alls * 100.0); m.put("per", per);
		 * m.put("name", h.get("NAME")); m.put("real", nf.format(isuse / alls *
		 * 100.0).toString()); netLists.add(m); } theForm.setNetList(netLists);
		 */
		// 获取告警信息
		List<HashMap> alarmList = new ArrayList<HashMap>();
		List alarmLists = new ArrayList();
		int all_alarm = 0;
		for (HashMap h : alarmList) {
			Map m = new HashMap();
			m.put("EVENT_LEVEL", h.get("EVENT_LEVEL"));
			m.put("NUM", h.get("NUM"));
			alarmLists.add(m);
			all_alarm += Integer.parseInt(h.get("NUM").toString());
		}
		theForm.setAlarmList(alarmLists);
		theForm.setAlarm_all(all_alarm);
		// 工单的数据暂时还无法获取

		/**
		 * 黑龙江移动修改，从数据库中查询主机cpu个数等信息 duangh update by huojla
		 * 
		 * @20130811
		 */
		XenHostStatistics xenStatistics = showResourceService.xenHostStatistics();
		VmwareHostStatistics vmwareStatistics = showResourceService.vmwareHostStatistics();
		PowerHostStatistics powerStatistics = showResourceService.powerHostStatictics();
		OtherHostStatistics otherStatistics = showResourceService.otherHostStatictics();
		theForm.setXenStatistics(xenStatistics);
		theForm.setVmwareStatistics(vmwareStatistics);
		theForm.setPowerStatistics(powerStatistics);
		theForm.setOtherStatistics(otherStatistics);
		return "list";
	}

	/**
	 * 
	 * @Title: getHostResource
	 * @Description: 获取资源首页信息
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-10 下午2:52:33
	 */
	public String getHostResource() {
		if (theForm == null) {
			theForm = new ShowResourceForm();
		}
		// 获取告警信息
		List<HashMap> alarmList = new ArrayList<HashMap>();
		List alarmLists = new ArrayList();
		int all_alarm = 0;
		for (HashMap h : alarmList) {
			Map m = new HashMap();
			m.put("EVENT_LEVEL", h.get("EVENT_LEVEL"));
			m.put("NUM", h.get("NUM"));
			alarmLists.add(m);
			all_alarm += Integer.parseInt(h.get("NUM").toString());
		}
		theForm.setAlarmList(alarmLists);
		theForm.setAlarm_all(all_alarm);
		// 工单的数据暂时还无法获取

		/** 黑龙江移动修改，从数据库中查询主机cpu个数等信息 duangh ，Update By huojla @ 20130810 */
		XenHostStatistics xenStatistics = showResourceService.xenHostStatistics();
		VmwareHostStatistics vmwareStatistics = showResourceService.vmwareHostStatistics();
		PowerHostStatistics powerStatistics = showResourceService.powerHostStatictics();
		OtherHostStatistics otherStatistics = showResourceService.otherHostStatictics();
		List<AlarmHostStatistics> resultList = showResourceService.alarmHostStatistics();
		List<AlarmHostStatistics> alarmResultList = showResourceService.queryIpData();
		for(AlarmHostStatistics ahObj : resultList){
			if(resultList!=null && resultList.size()>0){
				map.put(ahObj.getLevel(),ahObj.getLevelcount());
			}
		}
		theForm.setXenStatistics(xenStatistics);
		theForm.setVmwareStatistics(vmwareStatistics);
		theForm.setPowerStatistics(powerStatistics);
		theForm.setOtherStatistics(otherStatistics);
		theForm.setAlarmResultList(alarmResultList);
		return "list";
	}

	/**
	 * 
	 * @Title: getVmResource
	 * @Description: 获取虚拟机资源信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 20, 2013 2:28:40 PM
	 */
	public List getVmResource() {
		VMHostObj obj = new VMHostObj();
		List map = vmHostService.vmResourceByType(obj);
		return map;
	}

	/**
	 * 
	 * @Title: getNetAndIps
	 * @Description: 获取网络信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 20, 2013 3:37:30 PM
	 */
	public List getNetAndIps() {
		List map = netService.getNetResource();
		return map;
	}

	/**
	 * 
	 * @Title: getAlarms
	 * @Description: 获取告警信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 21, 2013 3:52:09 PM
	 */
	public List getAlarms() {
		// List list = tbCloud2MonitorAlarmService.getAlarms();
		List list = new ArrayList();
		return list;
	}

	/**
	 * 
	 * @Title: showResourceDetail
	 * @Description: 展示资源的细节
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue--Update By huojla @ 20130810
	 * @version 1.0
	 * @createtime Feb 25, 2013 2:56:41 PM
	 */
	public String showResourceDetail() {
		if (theForm == null) {
			theForm = new ShowResourceForm();
		}

		DecimalFormat nf = new DecimalFormat("0");
		// 获取不同类型虚拟化的主机的个数
		List<HashMap<String, Integer>> hostList = showResourceService.getHostList();
		int host_num = 0;
		int host_other = 0;
		int vm_other = 0;
		for (HashMap h : hostList) {
			// host_num += Integer.parseInt(h.get("HOST_NUM").toString());
			String type = (String) h.get("hasvertual");
			String num = h.get("HOST_NUM").toString();
			if ("3".equals(type)) {
				host_num += Integer.parseInt(num);
				theForm.setHost_x_num(num);
			} else if ("4".equals(type)) {
				host_num += Integer.parseInt(num);
				theForm.setHost_v_num(num);
			} else {
				host_other += Integer.parseInt(num);
				theForm.setHost_other(String.valueOf(host_other));
			}
		}
		theForm.setHost_all_num(String.valueOf(host_num));
		List<HashMap> vmList = showResourceService.getVmList();
		int vm_num = 0;
		for (HashMap h : vmList) {
			vm_num += Integer.parseInt(h.get("VM_NUM").toString());
			String type = (String) h.get("VH_TYPE");
			String num = h.get("VM_NUM").toString();
			if ("1".equals(type)) {
				theForm.setVm_v_num(num);
			} else if ("3".equals(type)) {
				theForm.setVm_x_num(num);
			} else {
				vm_other += Integer.parseInt(num);
				theForm.setVm_other(String.valueOf(vm_other));
			}
		}
		theForm.setVm_all_num(String.valueOf(vm_num));
		// List<HashMap> switchList = showResourceService.getSwitchList();
		// xen
		String pool_uuid = "5c16c2e3-0eb3-c62a-1a51-695cfd425d68";
		// XenClusterObj obj = new XenClusterObj();
		// List<XenClusterObj> list = xenClusterService.queryListByObj(obj);
		String xen_param = "/xen/pool/motions/get/[poolUuid:" + pool_uuid + "]/";
		String xen_result = InvokeUtil.invoke(xen_param);
		JSONObject xen_js = JSONObject.fromObject(xen_result);
		// double x_cpu = xen_js.getInt("cpuSize") * 8;
		// double x_cpu_use = xen_js.getInt("cpuUtilization");
		double x_mem = xen_js.getLong("memSize");
		double x_store = xen_js.getLong("srSize");
		double x_mem_use = xen_js.getLong("memoryUtilization");
		double x_store_use = xen_js.getLong("srUtilisation");
		// vmvare
		String vmvare_param = "/vmware/resource/msg";
		String vmvare_result = InvokeUtil.invoke(vmvare_param);
		JSONObject js = JSONObject.fromObject(vmvare_result);
		// double v_cpu = Double.parseDouble(js.getString("sumcpu"));
		// double v_cpu_use = Double.parseDouble(js.getString("usedcpu"));
		double v_mem = Double.parseDouble(js.getString("summem"));
		double v_mem_use = Double.parseDouble(js.getString("usedmem"));// Mb
		double v_store = Double.parseDouble(js.getString("sumstorage"));
		double v_store_use = Double.parseDouble(js.getString("usedstorage"));// GB

		// 获取CPU
		// theForm.setX_cpu(nf.format(x_cpu));
		// theForm.setV_cpu(nf.format(v_cpu));
		// theForm.setCpu_all(nf.format(x_cpu + v_cpu));
		// theForm.setX_cpu_per(nf.format(x_cpu / (x_cpu + v_cpu) * 100));
		// theForm.setV_cpu_per(nf.format(v_cpu / (x_cpu + v_cpu) * 100));
		// theForm.setX_cpu_free(nf.format(x_cpu - x_cpu_use));
		// theForm.setV_cpu_free(nf.format(v_cpu - v_cpu_use));

		// 获取不同类型内存
		theForm.setX_mem(nf.format(x_mem));
		theForm.setV_mem(nf.format(v_mem));
		theForm.setMem_all(nf.format(x_mem + v_mem));
		// theForm.setX_mem_per(nf.format(x_mem / (x_mem + v_mem) * 100));
		// theForm.setV_mem_per(nf.format(x_mem / (x_mem + v_mem) * 100));
		theForm.setX_mem_free(nf.format(x_mem - x_mem_use));
		theForm.setV_mem_free(nf.format(v_mem - v_mem_use));

		// 获取不同类型存储
		theForm.setX_store(nf.format(x_store));
		theForm.setV_store(nf.format(v_store));
		theForm.setStore_all(nf.format(x_store + v_store));
		// theForm.setX_store_per(nf.format(x_store / (x_store + v_store) *
		// 100));
		// theForm.setV_store_per(nf.format(v_store / (x_store + v_store) *
		// 100));
		theForm.setX_store_free(nf.format(x_store - x_store_use));
		theForm.setV_store_free(nf.format(v_store - v_store_use));

		return "detail";
	}

	/**
	 * 
	 * @Title: showResourceDetail_united_tree
	 * @Description: 资源首页（代码优化，无用代码删除）
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-10 下午2:28:49
	 */
	public String showResourceDetail_united_tree() {
		if (theForm == null) {
			theForm = new ShowResourceForm();
		}
		DecimalFormat nf = new DecimalFormat("0");
		// 获取不同类型虚拟化的主机的个数
		// 虚拟化类型，0:非虚拟化,1:PowerVM,2:KVM,3:XEN,4:VMWARE
		List<HashMap<String, Integer>> hostList = showResourceService.getHostList();
		int host_num = 0;
		int host_other = 0;
		int vm_other = 0;
		for (HashMap<String, Integer> h : hostList) {
			String type = h.get("hasvertual").toString();
			String num = h.get("HOST_NUM").toString();
			if ("3".equals(type)) {// Xen 虚拟化
				host_num += Integer.parseInt(num);
				theForm.setHost_x_num(num);
			} else if ("4".equals(type)) {// VMware虚拟化
				host_num += Integer.parseInt(num);
				theForm.setHost_v_num(num);
			} else {// 其他虚拟化
				host_other += Integer.parseInt(num);
				theForm.setHost_other(String.valueOf(host_other));
			}
		}
		theForm.setHost_all_num(String.valueOf(host_num));
		List<HashMap> vmList = showResourceService.getVmList();
		int vm_num = 0;
		for (HashMap h : vmList) {
			vm_num += Integer.parseInt(h.get("VM_NUM").toString());
			String type = h.get("vh_type").toString();
			String num = h.get("VM_NUM").toString();
			if ("1".equals(type)) {
				theForm.setVm_v_num(num);
			} else if ("3".equals(type)) {
				theForm.setVm_x_num(num);
			} else {
				vm_other += Integer.parseInt(num);
				theForm.setVm_other(String.valueOf(vm_other));
			}
		}
		theForm.setVm_all_num(String.valueOf(vm_num));

		XenHostStatistics xenStatistics = showResourceService.xenHostStatistics();
		VmwareHostStatistics vmwareStatistics = showResourceService.vmwareHostStatistics();
		PowerHostStatistics powerStatistics = showResourceService.powerHostStatictics();
		OtherHostStatistics otherStatistics = showResourceService.otherHostStatictics();
	
		// Xen内存总量
		double x_mem = xenStatistics.getAllMem() / 1024;
		// Xen存储总量
		double x_store = xenStatistics.getAllStor();
		// Xen内存使用量
		double x_mem_use = xenStatistics.getUsedMem() / 1024;
		// Xen存储使用量
		double x_store_use = xenStatistics.getUsedStor();
		//Xen CPU总数
		int x_cpu = xenStatistics.getAllVcpu();
		//Xen CPU已用数
		int x_cpu_use = xenStatistics.getUsedVcpu();
		//Xen CPU可用数
		int x_cpu_free = xenStatistics.getFreeVcpu();
		// vmware
		// Vmware内存总量
		double v_mem = vmwareStatistics.getAllMem() / 1024;
		// Vmware内存使用率
		double v_mem_use = vmwareStatistics.getUsedMem() / 1024;// GB
		// VMware存储总量
		double v_store = vmwareStatistics.getAllStor() / 1024;
		// VMware存储使用率
		double v_store_use = vmwareStatistics.getUsedStor() / 1024;// GB
		//VMWARE CPU总数
		int v_cpu = vmwareStatistics.getAllVcpu();
		//VMWARE CPU已用数
		int v_cpu_use = vmwareStatistics.getUsedVcpu();
		//VMWARE CPU可用数
		int v_cpu_free = vmwareStatistics.getFreeVcpu();
		//CPU总数
		int cpu_all = x_cpu + v_cpu;
		
		// 获取不同类型内存
		// Xen内存总量
		theForm.setX_mem(nf.format(x_mem));
		// Vmware内存总量
		theForm.setV_mem(nf.format(v_mem));
		// 两种虚拟化内存总量
		theForm.setMem_all(nf.format(x_mem + v_mem));
		// Xen内存剩余量
		theForm.setX_mem_free(nf.format(x_mem - x_mem_use));
		// Vmware内存剩余量
		theForm.setV_mem_free(nf.format(v_mem - v_mem_use));
		
		// 获取不同类型存储
		// Xen存储总量
		theForm.setX_store(nf.format(x_store));
		// VMware存储总量
		theForm.setV_store(nf.format(v_store));
		// 两周虚拟化存储总量
		theForm.setStore_all(nf.format(x_store + v_store));
		// Xen存储剩余量
		theForm.setX_store_free(nf.format(x_store - x_store_use));
		// Vmware存储剩余量
		theForm.setV_store_free(nf.format(v_store - v_store_use));
		
		// CPU个数统计
		theForm.setCpu_all(cpu_all+"");
		//XEN CPU总数
		theForm.setX_cpu(x_cpu+"");
		//Xen CPU已用数
		theForm.setX_cpu_use(x_cpu_use+"");
		//Xen CPU可用数
		theForm.setX_cpu_free(x_cpu_free+"");
		//VMWARE CPU总数
		theForm.setV_cpu(v_cpu+"");
		//VMWARE CPU已用数
		theForm.setV_cpu_use(v_cpu_use+"");
		//VMWARE CPU可用数
		theForm.setV_cpu_free(v_cpu_free+"");
		
		return "detail";
	}

	/**
	 * 
	 * @Title: forServiceManage
	 * @Description: 为服务管理提供资源页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 28, 2013 3:12:59 PM
	 */
	public String forServiceManage() {
		if (theForm == null) {
			theForm = new ShowResourceForm();
		}
		DecimalFormat nf = new DecimalFormat("0");
		String pool_uuid = "5c16c2e3-0eb3-c62a-1a51-695cfd425d68";
		// XenClusterObj obj = new XenClusterObj();
		// List<XenClusterObj> list = xenClusterService.queryListByObj(obj);
		String xen_param = "/xen/pool/motions/get/[poolUuid:" + pool_uuid + "]/";
		String xen_result = InvokeUtil.invoke(xen_param);
		JSONObject xen_js = JSONObject.fromObject(xen_result);
		double x_cpu = 50.0;
		double x_mem = xen_js.getLong("memSize");
		double x_store = xen_js.getLong("srSize");
		theForm.setCpu_all(nf.format(x_cpu));
		theForm.setMem_all(nf.format(x_mem));
		theForm.setStore_all(nf.format(x_store));
		return "xen_resource";

	}

	public void setTbCloud2MonitorAlarmService(TbCloud2MonitorAlarmService tbCloud2MonitorAlarmService) {
		this.tbCloud2MonitorAlarmService = tbCloud2MonitorAlarmService;
	}
}
