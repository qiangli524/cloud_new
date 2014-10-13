package com.sitech.basd.yicloud.web.healthy.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sitech.basd.common.VMWareKPI;
import com.sitech.basd.sxcloud.cloud.domain.monitor.HealthyAlarmObj;
import com.sitech.basd.sxcloud.cloud.domain.reporthost.TbHostObj;
import com.sitech.basd.sxcloud.cloud.domain.reportvirtual.TbHyObj;
import com.sitech.basd.sxcloud.cloud.service.monitor.MonitorService;
import com.sitech.basd.sxcloud.cloud.util.BuildXmlString;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.vmman.TreeObj;
import com.sitech.basd.yicloud.service.healthy.HealthyManagerService;
import com.sitech.basd.yicloud.web.healthy.form.HealthyAlarmForm;
import com.sitech.basd.yicloud.web.healthy.form.HealthyManagerForm;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: VMManagerAction
 * </p>
 * <p>
 * Description: 虚拟机管理Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Apr 18, 2012 11:05:32 AM
 * 
 */
public class HealthyManagerAction extends CRUDBaseAction {

	private HealthyManagerService healthyManagerService;

	private MonitorService monitorService;

	private HealthyManagerForm theHealthyForm;

	private HealthyAlarmForm theForm;

	public HealthyAlarmForm getTheForm() {
		return theForm;
	}

	public void setTheForm(HealthyAlarmForm theForm) {
		this.theForm = theForm;
	}

	public HealthyManagerForm getTheHealthyForm() {
		return theHealthyForm;
	}

	public void setTheHealthyForm(HealthyManagerForm theHealthyForm) {
		this.theHealthyForm = theHealthyForm;
	}

	public void setHealthyManagerService(HealthyManagerService healthyManagerService) {
		this.healthyManagerService = healthyManagerService;
	}

	public void setMonitorService(MonitorService monitorService) {
		this.monitorService = monitorService;
	}

	/**
	 * 
	 * @Title: listHealthyService
	 * @Description: 获取虚拟机主机树
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String listHealthyService() throws Exception {
		/*
		 * if (theForm == null) { theForm = new HealthyManagerForm(); }
		 * HttpServletRequest request = Struts2Utils.getRequest();
		 * HttpServletResponse response = Struts2Utils.getResponse();
		 * VMManagerObj obj = new VMManagerObj(); obj.setTYPE("4");
		 * obj.setPagination(this.getPaginater().initPagination(request));// 分页
		 * List<VMManagerObj> resultList =
		 * vmManagerService.queryForListByObj(obj); String result = "{ id:0120,
		 * name:\"数据中心\",icon:\"sxcloud/images/host.gif\",open:true},"; for
		 * (VMManagerObj o : resultList) { result += "{id:" + o.getID() +
		 * ",pId:0120,name:\"" + o.getNAME_ZH() +
		 * "\",icon:\"sxcloud/images/virtual/monitor.png\""; VMManagerObj vmo =
		 * new VMManagerObj(); vmo.setTYPE("3");
		 * vmo.setVH_HOST(String.valueOf(o.getID())); List<VMManagerObj> oList =
		 * vmManagerService.queryForListByObj(vmo); if (oList != null &&
		 * oList.size() > 0) { result += ",children:["; for (VMManagerObj ol :
		 * oList) { result += " {id:" + ol.getID() + ",name:\"" +
		 * ol.getNAME_ZH() + "\",icon:\"sxcloud/images/virtual/accept.png\"},";
		 * } result = result.substring(0, result.length() - 1); result += "]},";
		 * } else { result += "},"; } } result = result.substring(0,
		 * result.length() - 1); String result = "{ id:\'031\', name:\'n3.n1\',
		 * isParent:true},{ id:\'032\', name:\'n3.n2\', isParent:false},{
		 * id:\'033\', name:\'n3.n3\', isParent:true},{ id:\'034\',
		 * name:\'n3.n4\', isParent:false}"; JSONObject json =
		 * JSONObject.fromObject(result); PrintWriter p =
		 * Struts2Utils.getResponse().getWriter(); p.print(json.toString());
		 * p.close(); theForm.setResultList(resultList);
		 * request.setAttribute("treeStr", result); theForm.setTreeStr(result);
		 */
		return LIST;
	}

	/**
	 * 
	 * @Title: listHealthyServicetest
	 * @Description: 采用异步的方式生成树
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String listHealthyServicetest() throws Exception {
		EntityTreeObj treeObj = new EntityTreeObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String str = request.getParameter("id");
		if (str == null || "".equals(str)) {
			treeObj.setType("8");
		} else {
			treeObj.setParentId(Integer.valueOf(str));
		}
		List<EntityTreeObj> resultList = healthyManagerService.queryForTree(treeObj);
		List<TreeObj> list = new ArrayList<TreeObj>();
		if (list != null) {
			EntityTreeObj tempObj = new EntityTreeObj();
			for (EntityTreeObj obj : resultList) {
				TreeObj tObj = new TreeObj();
				tObj.setId(obj.getId());
				tObj.setName(obj.getName());
				tObj.setType(obj.getType());
				// 判断是不是父节点
				tempObj.setParentId(obj.getId());
				List<EntityTreeObj> lst = healthyManagerService.queryForTree(tempObj);
				if (lst == null || lst.size() == 0) {
					tObj.setIsParent(false);

				}
				// 设置图标
				if (obj.getType().equals("8")) {// 数据中心
					tObj.setIcon("sxcloud/images/virtual/cluster.png");
				}
				if (obj.getType().equals("3") || obj.getType().equals("21")
						|| obj.getType().equals("29") || obj.getType().equals("33")) { // 集群
					tObj.setIcon("sxcloud/images/virtual/cluster.png");
				}
				if (obj.getType().equals("1") || obj.getType().equals("25")
						|| obj.getType().equals("26") || obj.getType().equals("30")) { // 主机
					tObj.setIcon("sxcloud/images/virtual/Host.png");
				}
				if (obj.getType().equals("0") || obj.getType().equals("24")
						|| obj.getType().equals("27") || obj.getType().equals("31")) { // 虚拟机
					tObj.setIcon("sxcloud/images/virtual/VirtualMachine.png");
				}
				if (obj.getType().equals("18")) {// 存储
					tObj.setIcon("sxcloud/images/virtual/storage.png");
				}
				if (obj.getType().equals("19")) {// 网络
					tObj.setIcon("sxcloud/images/virtual/network.png");

				}
				if (!obj.getType().equals("2") && !obj.getType().equals("4")
						&& !obj.getType().equals("5") && !obj.getType().equals("7")
						&& !obj.getType().equals("9") && !obj.getType().equals("10")
						&& !obj.getType().equals("11") && !obj.getType().equals("12")
						&& !obj.getType().equals("13") && !obj.getType().equals("14")
						&& !obj.getType().equals("15") && !obj.getType().equals("16")
						&& !obj.getType().equals("17") && !obj.getType().equals("18")
						&& !obj.getType().equals("19") && !obj.getType().equals("20")
						&& !obj.getType().equals("22") && !obj.getType().equals("23")
						&& !obj.getType().equals("28") && !obj.getType().equals("32")) {
					list.add(tObj);
				}
			}
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray json = JSONArray.fromObject(list);
		// PrintWriter p = response.getWriter();
		// p.print(json.toString());
		// p.close();
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: HealthyTabs
	 * @Description: 单击时进入tab页面
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	public String HealthyTabs() throws BaseException {
		/*
		 * if (theForm == null) { theForm = new HealthyManagerForm(); }
		 */
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String tag = request.getParameter("tag");

		request.setAttribute("id", id);
		request.setAttribute("tag", tag);
		return "HealthyTabs";
	}

	/**
	 * @Title:查询健康报表
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String getHealthyReport() throws BaseException {
		// CPU使用率 PM-00-01-001-07
		// 内存使用情况 PM-00-01-001-12
		// 网络使用情况 PM-00-01-001-13
		// 磁盘使用情况 PM-00-01-001-15
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String tag = request.getParameter("tag");

		// try {
		// tag = new String(tag.getBytes("iso-8859-1"), "UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		List cpuTodayList = null;
		List memoryTodayList = null;
		List storageTodayList = null;
		List netTodayList = null;

		List cpuxyAxisList = new ArrayList();
		List memoryxyAxisList = new ArrayList();
		List storagexyAxisList = new ArrayList();
		List netxyAxisList = new ArrayList();
		if ("0".equals(tag)) {
			TbHyObj cpuobj = new TbHyObj();
			cpuobj.setHy_id(id);
			String cpuKPI_ID = VMWareKPI.CPU_usage;
			cpuobj.setKpi_id(cpuKPI_ID);

			TbHyObj memoryobj = new TbHyObj();
			memoryobj.setHy_id(id);
			String memoryKPI_ID = VMWareKPI.Memory_usage_situation2;
			memoryobj.setKpi_id(memoryKPI_ID);

			TbHyObj storageobj = new TbHyObj();
			storageobj.setHy_id(id);
			String storageKPI_ID = VMWareKPI.Disk_usage_situation;
			storageobj.setKpi_id(storageKPI_ID);

			TbHyObj netobj = new TbHyObj();
			netobj.setHy_id(id);
			String netKPI_ID = VMWareKPI.Network_usage_situation;
			netobj.setKpi_id(netKPI_ID);
			cpuTodayList = healthyManagerService.queryTbHyForToday(cpuobj);
			memoryTodayList = healthyManagerService.queryTbHyForToday(memoryobj);
			storageTodayList = healthyManagerService.queryTbHyForToday(storageobj);
			netTodayList = healthyManagerService.queryTbHyForToday(netobj);
			for (int i = 0; i < cpuTodayList.size(); i++) {
				TbHyObj tempObj = (TbHyObj) cpuTodayList.get(i);
				String time = tempObj.getColl_time().substring(11, 16);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("X", time);
				map.put("Y1", tempObj.getKpi_value());
				map.put("Y2", "80");
				cpuxyAxisList.add(map);
			}
			for (int i = 0; i < memoryTodayList.size(); i++) {
				TbHyObj tempObj = (TbHyObj) memoryTodayList.get(i);
				String time = tempObj.getColl_time().substring(11, 16);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("X", time);
				map.put("Y1", tempObj.getKpi_value());
				map.put("Y2", "80");
				memoryxyAxisList.add(map);
			}
			for (int i = 0; i < storageTodayList.size(); i++) {
				TbHyObj tempObj = (TbHyObj) storageTodayList.get(i);
				String time = tempObj.getColl_time().substring(11, 16);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("X", time);
				map.put("Y1", tempObj.getKpi_value());
				map.put("Y2", "80");
				storagexyAxisList.add(map);
			}
			for (int i = 0; i < netTodayList.size(); i++) {
				TbHyObj tempObj = (TbHyObj) netTodayList.get(i);
				String time = tempObj.getColl_time().substring(11, 16);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("X", time);
				map.put("Y1", tempObj.getKpi_value());
				map.put("Y2", "80");
				netxyAxisList.add(map);
			}
		} else if ("1".equals(tag)) {
			TbHostObj cpuobj = new TbHostObj();
			cpuobj.setEq_id(id);
			String cpuKPI_ID = "PM-00-01-001-07";
			cpuobj.setKpi_id(cpuKPI_ID);

			TbHostObj memoryobj = new TbHostObj();
			memoryobj.setEq_id(id);
			String memoryKPI_ID = "PM-00-01-001-12";
			memoryobj.setKpi_id(memoryKPI_ID);

			TbHostObj storageobj = new TbHostObj();
			storageobj.setEq_id(id);
			String storageKPI_ID = "PM-00-01-001-15";
			storageobj.setKpi_id(storageKPI_ID);

			TbHostObj netobj = new TbHostObj();
			netobj.setEq_id(id);
			String netKPI_ID = "PM-00-01-001-13";
			netobj.setKpi_id(netKPI_ID);
			cpuTodayList = healthyManagerService.queryTbHostForToday(cpuobj);
			memoryTodayList = healthyManagerService.queryTbHostForToday(memoryobj);
			storageTodayList = healthyManagerService.queryTbHostForToday(storageobj);
			netTodayList = healthyManagerService.queryTbHostForToday(netobj);
			for (int i = 0; i < cpuTodayList.size(); i++) {
				TbHostObj tempObj = (TbHostObj) cpuTodayList.get(i);
				String time = tempObj.getColl_time().substring(11, 16);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("X", time);
				map.put("Y1", tempObj.getKpi_value());
				map.put("Y2", "80");
				cpuxyAxisList.add(map);
			}
			for (int i = 0; i < memoryTodayList.size(); i++) {
				TbHostObj tempObj = (TbHostObj) memoryTodayList.get(i);
				String time = tempObj.getColl_time().substring(11, 16);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("X", time);
				map.put("Y1", tempObj.getKpi_value());
				map.put("Y2", "80");
				memoryxyAxisList.add(map);
			}
			for (int i = 0; i < storageTodayList.size(); i++) {
				TbHostObj tempObj = (TbHostObj) storageTodayList.get(i);
				String time = tempObj.getColl_time().substring(11, 16);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("X", time);
				map.put("Y1", tempObj.getKpi_value());
				map.put("Y2", "80");
				storagexyAxisList.add(map);
			}
			for (int i = 0; i < netTodayList.size(); i++) {
				TbHostObj tempObj = (TbHostObj) netTodayList.get(i);
				String time = tempObj.getColl_time().substring(11, 16);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("X", time);
				map.put("Y1", tempObj.getKpi_value());
				map.put("Y2", "80");
				netxyAxisList.add(map);
			}
		} else {
			return "";// 添加集群和数据中心的处理
		}

		String cpuStep = "4"; // X轴每一小时显示一个点
		String memoryStep = "8"; // X轴每两小时显示一个点
		String storageStep = "16"; // X轴每四小时显示一个点
		String netStep = "16"; // X轴每四小时显示一个点

		Map<String, Object> cpuparaMap = new HashMap<String, Object>();
		cpuparaMap.put("caption", "cpu使用率");
		cpuparaMap.put("color1", "1D8BD1");
		cpuparaMap.put("color2", "F1683C");
		cpuparaMap.put("anchorBorderColor1", "1D8BD1");
		cpuparaMap.put("anchorBorderColor2", "F1683C");
		cpuparaMap.put("anchorBgColor1", "1D8BD1");
		cpuparaMap.put("anchorBgColor2", "F1683C");
		cpuparaMap.put("seriesName1", "当前");
		cpuparaMap.put("seriesName2", "健康");
		cpuparaMap.put("step", cpuStep);
		Map<String, Object> memoryparaMap = new HashMap<String, Object>();
		memoryparaMap.put("caption", "内存使用率");
		memoryparaMap.put("color1", "1D8BD1");
		memoryparaMap.put("color2", "F1683C");
		memoryparaMap.put("anchorBorderColor1", "1D8BD1");
		memoryparaMap.put("anchorBorderColor2", "F1683C");
		memoryparaMap.put("anchorBgColor1", "1D8BD1");
		memoryparaMap.put("anchorBgColor2", "F1683C");
		memoryparaMap.put("seriesName1", "当前");
		memoryparaMap.put("seriesName2", "健康");
		memoryparaMap.put("step", memoryStep);
		Map<String, Object> storageparaMap = new HashMap<String, Object>();
		storageparaMap.put("caption", "存储使用率");
		storageparaMap.put("color1", "1D8BD1");
		storageparaMap.put("color2", "F1683C");
		storageparaMap.put("anchorBorderColor1", "1D8BD1");
		storageparaMap.put("anchorBorderColor2", "F1683C");
		storageparaMap.put("anchorBgColor1", "1D8BD1");
		storageparaMap.put("anchorBgColor2", "F1683C");
		storageparaMap.put("seriesName1", "当前");
		storageparaMap.put("seriesName2", "健康");
		storageparaMap.put("step", storageStep);
		Map<String, Object> netparaMap = new HashMap<String, Object>();
		netparaMap.put("caption", "网络吞吐量");
		netparaMap.put("color1", "C6E2FF");
		netparaMap.put("color2", "C8A1D1");
		netparaMap.put("anchorBorderColor1", "1D8BD1");
		netparaMap.put("anchorBorderColor2", "F1683C");
		netparaMap.put("anchorBgColor1", "1D8BD1");
		netparaMap.put("anchorBgColor2", "F1683C");
		netparaMap.put("seriesName1", "当前");
		netparaMap.put("seriesName2", "健康");
		netparaMap.put("step", netStep);

		// theForm.setCpuXML(cpuXML.toString());
		request.setAttribute("cpuXML",
				BuildXmlString.BuildXmlData(cpuparaMap, cpuxyAxisList, "ZoomLine").toString());
		request.setAttribute("memoryXML",
				BuildXmlString.BuildXmlData(memoryparaMap, memoryxyAxisList, "ZoomLine").toString());
		request.setAttribute("storageXML",
				BuildXmlString.BuildXmlData(storageparaMap, storagexyAxisList, "ZoomLine")
						.toString());
		request.setAttribute("netXML",
				BuildXmlString.BuildXmlData(netparaMap, netxyAxisList, "ZoomLine").toString());
		request.setAttribute("id", id);
		request.setAttribute("tag", tag);
		return "HealhtyReport";
	}

	/**
	 * 
	 * @Title: HealthyTabs
	 * @Description: 单击时进入tab页面
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime May 21, 2012 10:28:53 AM
	 */
	public String getWarnInfo() throws BaseException {

		if (theForm == null) {
			theForm = new HealthyAlarmForm();
		}

		HealthyAlarmObj obj = new HealthyAlarmObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页

		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String tag = request.getParameter("tag");
		// try {
		// tag = new String(tag.getBytes("iso-8859-1"), "UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		// /**
		// * 根据级别过滤告警信息
		// */
		// TbGlobalConfigObj levelObj = new TbGlobalConfigObj();
		// levelObj.setKEY("EVENT_LEVEL");
		// List<TbGlobalConfigObj> levelList = tbGlobalConfigService
		// .queryForListByObj(levelObj);
		// if (levelList != null && levelList.size() == 1) {
		// levelObj = levelList.get(0);
		// StringBuffer buffer = new StringBuffer();
		// buffer.append("(");
		// buffer.append(levelObj.getVALUE());
		// buffer.append(")");
		// obj.setFilterlevel(buffer.toString());
		// }
		// /**
		// * 根据类型过滤告警信息
		// */
		// TbGlobalConfigObj typeObj = new TbGlobalConfigObj();
		// typeObj.setKEY("EVENT_TYPE");
		// List<TbGlobalConfigObj> typeList = tbGlobalConfigService
		// .queryForListByObj(typeObj);
		// if (typeList != null && typeList.size() == 1) {
		// typeObj = typeList.get(0);
		// StringBuffer typeBuffer = new StringBuffer();
		// typeBuffer.append("(");
		// typeBuffer.append(typeObj.getVALUE());
		// typeBuffer.append(")");
		// obj.setFiltertype(typeBuffer.toString());
		// }
		EntityTreeObj treeObj = new EntityTreeObj();
		if ("0".equals(tag) || "1".equals(tag)) {
			treeObj.setId(Integer.valueOf(id));
		} else {
			return "WarnInfo";// 集群和数据中心
		}
		List resultList = null;
		if (!"".equals(tag) || tag != null) {
			if (id != null && !"".equals(id)) {
				if ("0".equals(tag)) {
					treeObj.setId(Integer.valueOf(id));
					List<String> entityIds = new ArrayList<String>();
					entityIds.add(((List<EntityTreeObj>) healthyManagerService
							.queryForTree(treeObj)).get(0).getEntityId());
					obj.setEntityIds(entityIds);
					resultList = monitorService.queryAlarmForListByEntityIds(obj);
				} else if ("1".equals(tag)) {
					EntityTreeObj treeObj1 = new EntityTreeObj();
					treeObj1.setParentId(Integer.valueOf(id));
					List<EntityTreeObj> treeList = healthyManagerService.queryForTree(treeObj1);
					List<String> entityIds = new ArrayList<String>();
					entityIds.add(((List<EntityTreeObj>) healthyManagerService
							.queryForTree(treeObj)).get(0).getEntityId());
					for (EntityTreeObj entityTreeObj : treeList) {
						entityIds.add(entityTreeObj.getEntityId());
					}
					obj.setEntityIds(entityIds);
					resultList = monitorService.queryAlarmForListByEntityIds(obj);
				} else {
					// 集群和数据中心
				}
			}
		}

		theForm.setResultList(resultList);

		request.setAttribute("id", id);
		request.setAttribute("tag", tag);
		return "WarnInfo";
	}
}
