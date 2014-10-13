package com.sitech.basd.sxcloud.cloud.web.reporthost.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.cloud.domain.reporthost.TbHostObj;
import com.sitech.basd.sxcloud.cloud.domain.reportvirtual.ReportTreeObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.service.reporthost.ReportHostService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.util.BuildXmlString;
import com.sitech.basd.sxcloud.cloud.util.DateUtil;
import com.sitech.basd.sxcloud.cloud.web.reporthost.form.ReportHostForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class ReportHostAction extends BaseAction {
	private ReportHostForm theForm;
	
	public ReportHostForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ReportHostForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有主机管理模块信息
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String getHostListReport() throws BaseException {
		if(theForm == null){
			theForm = new ReportHostForm();
		}
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		String IBMPowerEq_type = "1";
		String IBMKnifeEq_type = "2";
		String HPKnifeEq_type = "3";
		int eq_typeCount = 3;
		ReportTreeObj[] reportTreeObjArray = new ReportTreeObj[eq_typeCount];
		obj.setControl(1);
		List hostList = hostInfoService.queryForListByObj(obj);
		List<TbCloud2HostInfoObj> IBMPowerHostList = new ArrayList<TbCloud2HostInfoObj>();
		List<TbCloud2HostInfoObj> IBMKnifeHostList = new ArrayList<TbCloud2HostInfoObj>();
		List<TbCloud2HostInfoObj> HPKnifeHostList = new ArrayList<TbCloud2HostInfoObj>();
		if(hostList!=null){
			for (int i = 0; i < hostList.size(); i++) {
				TbCloud2HostInfoObj tempObj = (TbCloud2HostInfoObj) hostList.get(i);
				String eq_type = tempObj.getEq_type();
				if (IBMPowerEq_type.equals(eq_type)) {
					IBMPowerHostList.add(tempObj);
				} else if (IBMKnifeEq_type.equals(eq_type)) {
					IBMKnifeHostList.add(tempObj);
				} else if (HPKnifeEq_type.equals(eq_type)) {
					HPKnifeHostList.add(tempObj);
				}
			}
		}
		if (IBMPowerHostList != null && IBMPowerHostList.size() > 0) {
			TbCloud2HostInfoObj[] vHostArray = new TbCloud2HostInfoObj[IBMPowerHostList
					.size()];
			IBMPowerHostList.toArray(vHostArray);
			reportTreeObjArray[0] = new ReportTreeObj();
			reportTreeObjArray[0].setMName("IBM Power");
			reportTreeObjArray[0].setHostObjs(vHostArray);
		}
		if (IBMKnifeHostList != null && IBMKnifeHostList.size() > 0) {
			TbCloud2HostInfoObj[] vHostArray = new TbCloud2HostInfoObj[IBMKnifeHostList
					.size()];
			IBMKnifeHostList.toArray(vHostArray);
			reportTreeObjArray[1] = new ReportTreeObj();
			reportTreeObjArray[1].setHostObjs(vHostArray);
			reportTreeObjArray[1].setMName("IBM刀片");
		}
		if (HPKnifeHostList != null && HPKnifeHostList.size() > 0) {
			TbCloud2HostInfoObj[] vHostArray = new TbCloud2HostInfoObj[HPKnifeHostList
					.size()];
			HPKnifeHostList.toArray(vHostArray);
			reportTreeObjArray[2] = new ReportTreeObj();
			reportTreeObjArray[2].setHostObjs(vHostArray);
			reportTreeObjArray[2].setMName("HP刀片");
		}

		Struts2Utils.getRequest().setAttribute("reportTreeObjArray", reportTreeObjArray);
		return "getHostListReport";
	}

	/**
	 * @Title:查询主机的周报表
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String getHostWeekReportView() throws BaseException {
		if(theForm == null){
			theForm = new ReportHostForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String eq_id = request.getParameter("eq_id");
		String tag = request.getParameter("tag");
		try {
			tag = new String(tag.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		TbHostObj cpuobj = new TbHostObj();
		cpuobj.setEq_id(eq_id);
		String table_name = "TB_CLOUD2_HOST_COLL_"
				+ DateUtil.getFormatNMonth("yyyyMM", -1);
		cpuobj.setTable_name(table_name);
		cpuobj.setKpi_id("PM-H-01-010-11");
		
		TbHostObj memoryObj = new TbHostObj();
		memoryObj.setEq_id(eq_id);
		memoryObj.setTable_name(table_name);
		memoryObj.setKpi_id("PM-H-01-010-12");
		
		TbHostObj storageObj = new TbHostObj();
		storageObj.setEq_id(eq_id);
		storageObj.setTable_name(table_name);
		storageObj.setKpi_id("PM-H-01-010-13");
		
		TbHostObj netObj = new TbHostObj();
		netObj.setEq_id(eq_id);
		netObj.setTable_name(table_name);
		netObj.setKpi_id("PM-H-01-010-14");
		
		Calendar calendar = Calendar.getInstance();
		int dd = calendar.get(Calendar.DATE);
		List cpuweekList = null;
		List cpulastWeekList = null;
		List memoryweekList = null;
		List memorylastWeekList = null;
		List storageweekList = null;
		List storagelastWeekList = null;
		List netweekList = null;
		List netlastWeekList = null;
		if (dd > 14) {
			cpuweekList = reportHostService.queryTbHostForWeek(cpuobj);
			cpulastWeekList = reportHostService.queryTbHostForLastWeek(cpuobj);
			memoryweekList = reportHostService.queryTbHostForWeek(memoryObj);
			memorylastWeekList = reportHostService.queryTbHostForLastWeek(memoryObj);
			storageweekList = reportHostService.queryTbHostForWeek(storageObj);
			storagelastWeekList = reportHostService.queryTbHostForLastWeek(storageObj);
			netweekList = reportHostService.queryTbHostForWeek(netObj);
			netlastWeekList = reportHostService.queryTbHostForLastWeek(netObj);
		} else {
			cpulastWeekList = reportHostService.queryUnionTbHostForLastWeek(cpuobj);
			memorylastWeekList = reportHostService.queryUnionTbHostForLastWeek(memoryObj);
			storagelastWeekList = reportHostService.queryUnionTbHostForLastWeek(storageObj);
			netlastWeekList = reportHostService.queryUnionTbHostForLastWeek(netObj);
			if (dd > 7) {
				cpuweekList = reportHostService.queryTbHostForWeek(cpuobj);
				memoryweekList = reportHostService.queryTbHostForWeek(memoryObj);
				storageweekList = reportHostService.queryTbHostForWeek(storageObj);
				netweekList = reportHostService.queryTbHostForWeek(netObj);
			} else {
				cpuweekList = reportHostService.queryUnionTbHostForWeek(cpuobj);
				memoryweekList = reportHostService.queryUnionTbHostForWeek(memoryObj);
				storageweekList = reportHostService.queryUnionTbHostForWeek(storageObj);
				netweekList = reportHostService.queryUnionTbHostForWeek(netObj);
			}
		}

		List cpuxyAxisList = new ArrayList();
		List memoryxyAxisList = new ArrayList();
		List storagexyAxisList = new ArrayList();
		List netxyAxisList = new ArrayList();
		
		
		String cpuStep = "96"; // X轴每天显示一个点
		String memoryStep = "96"; // X轴每天显示一个点
		String storageStep = "96"; // X轴每天显示一个点
		String netStep = "96"; // X轴每天显示一个点

		

		
		for (int i = 0; i < cpuweekList.size(); i++) {
			TbHostObj tempObj = (TbHostObj) cpuweekList.get(i);
			TbHostObj tempLastObj = null;
			if(i <= cpulastWeekList.size()-1){
				tempLastObj = (TbHostObj)cpulastWeekList.get(i);
			}
			String coll_time = tempObj.getColl_time().substring(11, 16);
			String value1 = tempObj.getKpi_value();
			String value2 = tempLastObj == null?"0":tempLastObj.getKpi_value();
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("X", coll_time);
			map.put("Y1", value1);
			map.put("Y2", value2);
			cpuxyAxisList.add(map);
		}
		for (int i = 0; i < memoryweekList.size(); i++) {
			TbHostObj tempObj = (TbHostObj) memoryweekList.get(i);
			TbHostObj tempLastObj = null;
			if(i <= memorylastWeekList.size()-1){
				tempLastObj = (TbHostObj)memorylastWeekList.get(i);
			}
			String coll_time = tempObj.getColl_time().substring(11, 16);
			String value1 = tempObj.getKpi_value();
			String value2 = tempLastObj == null?"0":tempLastObj.getKpi_value();
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("X", coll_time);
			map.put("Y1", value1);
			map.put("Y2", value2);
			memoryxyAxisList.add(map);
		}
		for (int i = 0; i < storageweekList.size(); i++) {
			TbHostObj tempObj = (TbHostObj) storageweekList.get(i);
			TbHostObj tempLastObj = null;
			if(i <= storagelastWeekList.size()-1){
				tempLastObj = (TbHostObj)storagelastWeekList.get(i);
			}
			String coll_time = tempObj.getColl_time().substring(11, 16);
			String value1 = tempObj.getKpi_value();
			String value2 = tempLastObj == null?"0":tempLastObj.getKpi_value();
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("X", coll_time);
			map.put("Y1", value1);
			map.put("Y2", value2);
			storagexyAxisList.add(map);
		}
		for (int i = 0; i < netweekList.size(); i++) {
			TbHostObj tempObj = (TbHostObj) netweekList.get(i);
			TbHostObj tempLastObj = null;
			if(i <= netlastWeekList.size()-1){
				tempLastObj = (TbHostObj)netlastWeekList.get(i);
			}
			String coll_time = tempObj.getColl_time().substring(11, 16);
			String value1 = tempObj.getKpi_value();
			String value2 = tempLastObj == null?"0":tempLastObj.getKpi_value();
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("X", coll_time);
			map.put("Y1", value1);
			map.put("Y2", value2);
			netxyAxisList.add(map);
		}
		Map<String,Object> cpuparaMap = new HashMap<String, Object>();
		cpuparaMap.put("caption", "cpu使用率");
		cpuparaMap.put("color1", "1D8BD1");
		cpuparaMap.put("color2", "F1683C");
		cpuparaMap.put("anchorBorderColor1", "1D8BD1");
		cpuparaMap.put("anchorBorderColor2", "F1683C");
		cpuparaMap.put("anchorBgColor1", "1D8BD1");
		cpuparaMap.put("anchorBgColor2", "F1683C");
		cpuparaMap.put("seriesName1", "上周");
		cpuparaMap.put("seriesName2", "本周");
		cpuparaMap.put("step", cpuStep);
		Map<String,Object> memoryparaMap = new HashMap<String, Object>();
		memoryparaMap.put("caption", "内存使用率");
		memoryparaMap.put("color1", "1D8BD1");
		memoryparaMap.put("color2", "F1683C");
		memoryparaMap.put("anchorBorderColor1", "1D8BD1");
		memoryparaMap.put("anchorBorderColor2", "F1683C");
		memoryparaMap.put("anchorBgColor1", "1D8BD1");
		memoryparaMap.put("anchorBgColor2", "F1683C");
		memoryparaMap.put("seriesName1", "上周");
		memoryparaMap.put("seriesName2", "本周");
		memoryparaMap.put("step", memoryStep);
		Map<String,Object> storageparaMap = new HashMap<String, Object>();
		storageparaMap.put("caption", "存储使用率");
		storageparaMap.put("color1", "1D8BD1");
		storageparaMap.put("color2", "F1683C");
		storageparaMap.put("anchorBorderColor1", "1D8BD1");
		storageparaMap.put("anchorBorderColor2", "F1683C");
		storageparaMap.put("anchorBgColor1", "1D8BD1");
		storageparaMap.put("anchorBgColor2", "F1683C");
		storageparaMap.put("seriesName1", "上周");
		storageparaMap.put("seriesName2", "本周");
		storageparaMap.put("step", storageStep);
		Map<String,Object> netparaMap = new HashMap<String, Object>();
		netparaMap.put("caption", "网络吞吐量");
		netparaMap.put("color1", "C6E2FF");
		netparaMap.put("color2", "C8A1D1");
		netparaMap.put("anchorBorderColor1", "1D8BD1");
		netparaMap.put("anchorBorderColor2", "F1683C");
		netparaMap.put("anchorBgColor1", "1D8BD1");
		netparaMap.put("anchorBgColor2", "F1683C");
		netparaMap.put("seriesName1", "上周");
		netparaMap.put("seriesName2", "本周");
		netparaMap.put("step", netStep);
		
		request.setAttribute("cpuXML", BuildXmlString.BuildXmlData(cpuparaMap, cpuxyAxisList, "MSLine").toString());
		request.setAttribute("memoryXML", BuildXmlString.BuildXmlData(memoryparaMap,memoryxyAxisList, "MSLine").toString());
		request.setAttribute("storageXML",BuildXmlString.BuildXmlData(storageparaMap,storagexyAxisList, "MSLine").toString());
		request.setAttribute("netXML",BuildXmlString.BuildXmlData(netparaMap,netxyAxisList, "MSLine").toString());
		request.setAttribute("eq_id", eq_id);
		request.setAttribute("tag", tag);

		return "hostWeek";

	}

	/**
	 * @Title:查询主机的当前报表
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String getHostCurrentReportView() throws BaseException {
		if(theForm == null){
			theForm = new ReportHostForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String eq_id = request.getParameter("eq_id");
		String tag = request.getParameter("tag");

		try {
			tag = new String(tag.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		TbHostObj cpuobj = new TbHostObj();
		cpuobj.setEq_id(eq_id);
		String cpuKPI_ID = "PM-H-01-010-11";
		cpuobj.setKpi_id(cpuKPI_ID);
		List cpuTodayList = reportHostService.queryTbHostForToday(cpuobj);

		TbHostObj memoryobj = new TbHostObj();
		memoryobj.setEq_id(eq_id);
		String memoryKPI_ID = "PM-H-01-010-12";
		memoryobj.setKpi_id(memoryKPI_ID);
		List memoryTodayList = reportHostService.queryTbHostForToday(memoryobj);

		TbHostObj storageobj = new TbHostObj();
		storageobj.setEq_id(eq_id);
		String storageKPI_ID = "PM-H-01-010-13";
		storageobj.setKpi_id(storageKPI_ID);
		List storageTodayList = reportHostService
				.queryTbHostForToday(storageobj);

		TbHostObj netobj = new TbHostObj();
		netobj.setEq_id(eq_id);
		String netKPI_ID = "PM-H-01-010-14";
		netobj.setKpi_id(netKPI_ID);
		List netTodayList = reportHostService.queryTbHostForToday(netobj);
		 
		String cpuStep = "4"; // X轴每一小时显示一个点
		String memoryStep = "8"; // X轴每两小时显示一个点
		String storageStep = "16"; // X轴每四小时显示一个点
		String netStep = "16"; // X轴每四小时显示一个点
		
		Map cpuparaMap = new HashMap<String, Object>();
		cpuparaMap.put("caption", "cpu使用率");
		cpuparaMap.put("step", cpuStep);
		cpuparaMap.put("numberSuffix", "%");
		cpuparaMap.put("paletteColors", "");
		Map memoryparaMap = new HashMap<String, Object>();
		memoryparaMap.put("caption", "内存使用率");
		memoryparaMap.put("step", memoryStep);
		memoryparaMap.put("numberSuffix", "%");
		memoryparaMap.put("paletteColors", "");
		Map storageparaMap = new HashMap<String, Object>();
		storageparaMap.put("caption", "存储使用率");
		storageparaMap.put("step", storageStep);
		storageparaMap.put("numberSuffix", "%");
		storageparaMap.put("paletteColors", "");
		Map netparaMap = new HashMap<String, Object>();
		netparaMap.put("caption", "网络吞吐量");
		netparaMap.put("step", netStep);
		netparaMap.put("numberSuffix", "tps");
		netparaMap.put("paletteColors", "C6E2FF");
		

		List cpuxyAxisList = new ArrayList();
		List memoryxyAxisList = new ArrayList();
		List storagexyAxisList = new ArrayList();
		List netxyAxisList = new ArrayList();
		
		for (int i = 0; i < cpuTodayList.size(); i++) {
			TbHostObj tempObj = (TbHostObj) cpuTodayList.get(i);
			String time = tempObj.getColl_time().substring(11, 16);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("X", time);
			map.put("Y", tempObj.getKpi_value());
			cpuxyAxisList.add(map);
		}
		for (int i = 0; i < memoryTodayList.size(); i++) {
			TbHostObj tempObj = (TbHostObj) memoryTodayList.get(i);
			String time = tempObj.getColl_time().substring(11, 16);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("X", time);
			map.put("Y", tempObj.getKpi_value());
			memoryxyAxisList.add(map);
		}
		for (int i = 0; i < storageTodayList.size(); i++) {
			TbHostObj tempObj = (TbHostObj) storageTodayList.get(i);
			String time = tempObj.getColl_time().substring(11, 16);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("X", time);
			map.put("Y", tempObj.getKpi_value());
			storagexyAxisList.add(map);
		}
		for (int i = 0; i < netTodayList.size(); i++) {
			TbHostObj tempObj = (TbHostObj) netTodayList.get(i);
			String time = tempObj.getColl_time().substring(11, 16);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("X", time);
			map.put("Y", tempObj.getKpi_value());
			netxyAxisList.add(map);
		}
		
		// theForm.setCpuXML(cpuXML.toString());
		request.setAttribute("cpuXML", BuildXmlString.BuildXmlData(cpuparaMap, cpuxyAxisList, "Line").toString());
		request.setAttribute("memoryXML", BuildXmlString.BuildXmlData(memoryparaMap, memoryxyAxisList, "Line").toString());
		request.setAttribute("storageXML", BuildXmlString.BuildXmlData(storageparaMap, storagexyAxisList, "Line").toString());
		request.setAttribute("netXML", BuildXmlString.BuildXmlData(netparaMap, netxyAxisList, "Line").toString());
		request.setAttribute("eq_id", eq_id);
		request.setAttribute("tag", tag);
		return "hostCurrent";

	}

	/**
	 * @Title:查询相应主机的月报表
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String getHostMonthReportView() throws BaseException {
		if(theForm == null){
			theForm = new ReportHostForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String eq_id = request.getParameter("eq_id");
		String tag = request.getParameter("tag");
		try {
			tag = new String(tag.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		TbHostObj cpuobj = new TbHostObj();
		cpuobj.setEq_id(eq_id);
		String table_name = "TB_CLOUD2_HOST_COLL_"
				+ DateUtil.getFormatNMonth("yyyyMM", -1);
		cpuobj.setTable_name(table_name);
		cpuobj.setKpi_id("PM-H-01-010-11");
		
		TbHostObj memoryObj = new TbHostObj();
		memoryObj.setEq_id(eq_id);
		memoryObj.setTable_name(table_name);
		memoryObj.setKpi_id("PM-H-01-010-12");
		
		TbHostObj storageObj = new TbHostObj();
		storageObj.setEq_id(eq_id);
		storageObj.setTable_name(table_name);
		storageObj.setKpi_id("PM-H-01-010-13");
		
		TbHostObj netObj = new TbHostObj();
		netObj.setEq_id(eq_id);
		netObj.setTable_name(table_name);
		netObj.setKpi_id("PM-H-01-010-14");
		
		List cpumonthList = reportHostService.queryTbHostForMonth(cpuobj);
		List cpulastMonthList = reportHostService.queryTbHostForLastMonth(cpuobj);
		List memorymonthList = reportHostService.queryTbHostForMonth(memoryObj);
		List memorylastMonthList = reportHostService.queryTbHostForLastMonth(memoryObj);
		List storagemonthList = reportHostService.queryTbHostForMonth(storageObj);
		List storagelastMonthList = reportHostService.queryTbHostForLastMonth(storageObj);
		List netmonthList = reportHostService.queryTbHostForMonth(netObj);
		List netlastMonthList = reportHostService.queryTbHostForLastMonth(netObj);

		String cpuStep = "96"; // X轴每天显示一个点
		String memoryStep = "96"; // X轴每天显示一个点
		String storageStep = "96"; // X轴每天显示一个点
		String netStep = "96"; // X轴每天显示一个点
		
		List cpuxyAxisList = new ArrayList();
		List memoryxyAxisList = new ArrayList();
		List storagexyAxisList = new ArrayList();
		List netxyAxisList = new ArrayList();
		
		List xCpumonthList = cpulastMonthList.size() > cpumonthList.size() ? cpulastMonthList
				: cpumonthList;
		for (int i = 0; i < xCpumonthList.size(); i++) {
			TbHostObj tempObj = (TbHostObj) xCpumonthList.get(i);
			String coll_time = tempObj.getColl_time().substring(11, 16);
			Map<String , Object> map = new HashMap<String, Object>();
			map.put("X", coll_time);
			if(xCpumonthList == cpulastMonthList){
				String value1 = tempObj.getKpi_value();
				map.put("Y1", value1);
				TbHostObj tempObj1 = null;
				if(i <= cpumonthList.size() - 1){
					tempObj1 = (TbHostObj) cpumonthList.get(i);
				}
				String value2 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y2", value2);
			}else {
				String value2 = tempObj.getKpi_value();
				map.put("Y2", value2);
				TbHostObj tempObj1 = null;
				if(i <= cpulastMonthList.size() - 1){
					tempObj1 = (TbHostObj) cpulastMonthList.get(i);
				}
				String value1 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y1", value1);
			}
			cpuxyAxisList.add(map);
		}
		
		List xMemorymonthList = memorylastMonthList.size() > memorymonthList.size() ? memorylastMonthList
				: memorymonthList;
		for (int i = 0; i < xMemorymonthList.size(); i++) {
			TbHostObj tempObj = (TbHostObj) xMemorymonthList.get(i);
			String coll_time = tempObj.getColl_time().substring(11, 16);
			Map<String , Object> map = new HashMap<String, Object>();
			map.put("X", coll_time);
			if(xMemorymonthList == memorylastMonthList){
				String value1 = tempObj.getKpi_value();
				map.put("Y1", value1);
				TbHostObj tempObj1 = null;
				if(i <= memorymonthList.size() - 1){
					tempObj1 = (TbHostObj) memorymonthList.get(i);
				}
				String value2 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y2", value2);
			}else {
				String value2 = tempObj.getKpi_value();
				map.put("Y2", value2);
				TbHostObj tempObj1 = null;
				if(i <= memorylastMonthList.size() - 1){
					tempObj1 = (TbHostObj) memorylastMonthList.get(i);
				}
				String value1 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y1", value1);
			}
			memoryxyAxisList.add(map);
		}
		
		List xStoragemonthList = storagelastMonthList.size() > storagemonthList.size() ? storagelastMonthList
				: storagemonthList;
		for (int i = 0; i < xStoragemonthList.size(); i++) {
			TbHostObj tempObj = (TbHostObj) xStoragemonthList.get(i);
			String coll_time = tempObj.getColl_time().substring(11, 16);
			Map<String , Object> map = new HashMap<String, Object>();
			map.put("X", coll_time);
			if(xStoragemonthList == storagelastMonthList){
				String value1 = tempObj.getKpi_value();
				map.put("Y1", value1);
				TbHostObj tempObj1 = null;
				if(i <= storagemonthList.size() - 1){
					tempObj1 = (TbHostObj) storagemonthList.get(i);
				}
				String value2 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y2", value2);
			}else {
				String value2 = tempObj.getKpi_value();
				map.put("Y2", value2);
				TbHostObj tempObj1 = null;
				if(i <= storagelastMonthList.size() - 1){
					tempObj1 = (TbHostObj) storagelastMonthList.get(i);
				}
				String value1 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y1", value1);
			}
			storagexyAxisList.add(map);
		}
		
		List xNetmonthList = netlastMonthList.size() > netmonthList.size() ? netlastMonthList
				: netmonthList;
		for (int i = 0; i < xNetmonthList.size(); i++) {
			TbHostObj tempObj = (TbHostObj) xNetmonthList.get(i);
			String coll_time = tempObj.getColl_time().substring(11, 16);
			Map<String , Object> map = new HashMap<String, Object>();
			map.put("X", coll_time);
			if(xNetmonthList == netlastMonthList){
				String value1 = tempObj.getKpi_value();
				map.put("Y1", value1);
				TbHostObj tempObj1 = null;
				if(i <= netmonthList.size() - 1){
					tempObj1 = (TbHostObj) netmonthList.get(i);
				}
				String value2 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y2", value2);
			}else {
				String value2 = tempObj.getKpi_value();
				map.put("Y2", value2);
				TbHostObj tempObj1 = null;
				if(i <= netlastMonthList.size() - 1){
					tempObj1 = (TbHostObj) netlastMonthList.get(i);
				}
				String value1 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y1", value1);
			}
			netxyAxisList.add(map);
		}
		
		Map<String,Object> cpuparaMap = new HashMap<String, Object>();
		cpuparaMap.put("caption", "cpu使用率");
		cpuparaMap.put("color1", "1D8BD1");
		cpuparaMap.put("color2", "F1683C");
		cpuparaMap.put("anchorBorderColor1", "1D8BD1");
		cpuparaMap.put("anchorBorderColor2", "F1683C");
		cpuparaMap.put("anchorBgColor1", "1D8BD1");
		cpuparaMap.put("anchorBgColor2", "F1683C");
		cpuparaMap.put("seriesName1", "上月");
		cpuparaMap.put("seriesName2", "本月");
		cpuparaMap.put("step", cpuStep);
		Map<String,Object> memoryparaMap = new HashMap<String, Object>();
		memoryparaMap.put("caption", "内存使用率");
		memoryparaMap.put("color1", "1D8BD1");
		memoryparaMap.put("color2", "F1683C");
		memoryparaMap.put("anchorBorderColor1", "1D8BD1");
		memoryparaMap.put("anchorBorderColor2", "F1683C");
		memoryparaMap.put("anchorBgColor1", "1D8BD1");
		memoryparaMap.put("anchorBgColor2", "F1683C");
		memoryparaMap.put("seriesName1", "上月");
		memoryparaMap.put("seriesName2", "本月");
		memoryparaMap.put("step", memoryStep);
		Map<String,Object> storageparaMap = new HashMap<String, Object>();
		storageparaMap.put("caption", "存储使用率");
		storageparaMap.put("color1", "1D8BD1");
		storageparaMap.put("color2", "F1683C");
		storageparaMap.put("anchorBorderColor1", "1D8BD1");
		storageparaMap.put("anchorBorderColor2", "F1683C");
		storageparaMap.put("anchorBgColor1", "1D8BD1");
		storageparaMap.put("anchorBgColor2", "F1683C");
		storageparaMap.put("seriesName1", "上月");
		storageparaMap.put("seriesName2", "本月");
		storageparaMap.put("step", storageStep);
		Map<String,Object> netparaMap = new HashMap<String, Object>();
		netparaMap.put("caption", "网络吞吐量");
		netparaMap.put("color1", "C6E2FF");
		netparaMap.put("color2", "C8A1D1");
		netparaMap.put("anchorBorderColor1", "1D8BD1");
		netparaMap.put("anchorBorderColor2", "F1683C");
		netparaMap.put("anchorBgColor1", "1D8BD1");
		netparaMap.put("anchorBgColor2", "F1683C");
		netparaMap.put("seriesName1", "上月");
		netparaMap.put("seriesName2", "本月");
		netparaMap.put("step", netStep);
		
		request.setAttribute("cpuXML", BuildXmlString.BuildXmlData(cpuparaMap, cpuxyAxisList, "MSLine").toString());
		request.setAttribute("memoryXML", BuildXmlString.BuildXmlData(memoryparaMap, memoryxyAxisList, "MSLine").toString());
		request.setAttribute("storageXML", BuildXmlString.BuildXmlData(storageparaMap, storagexyAxisList, "MSLine").toString());
		request.setAttribute("netXML", BuildXmlString.BuildXmlData(netparaMap, netxyAxisList, "MSLine").toString());
		request.setAttribute("eq_id", eq_id);
		request.setAttribute("tag", tag);
		return "hostMonth";
	}

	/**
	 * @Title:进入主机报表的初始界面
	 * @Copyright: Copyright (c) 20120405
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listHostReport()
			throws BaseException {
		if(theForm == null){
			theForm = new ReportHostForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		StringBuilder listHost = new StringBuilder(
				"<chart palette=\"2\" caption=\"主机监控\" showLabels=\"1\" showvalues=\"0\"  showSum=\"1\" decimals=\"0\" useRoundEdges=\"1\" bgColor=\"B5D4F3\" bgAlpha=\"10\" plotSpacePercent=\"65\" legendBorderAlpha=\"0\">");
		listHost.append("<categories>");
		listHost.append("<category label=\"IBM POWER\" />");
		listHost.append("<category label=\"IBM 刀片\" />");
		listHost.append("<category label=\"HP 刀片\" />");
		listHost.append("</categories>");

		obj.setEq_type("1"); // ibm power
		int i = reportHostService.queryMonitorHostCount(obj); // 查询已监控的个数
		int a = reportHostService.queryAllHostCount(obj); // 查询能够监控的总数
		obj.setEq_type("2"); // ibm 刀片
		int j = reportHostService.queryMonitorHostCount(obj); // 查询已监控的个数
		int b = reportHostService.queryAllHostCount(obj); // 查询能够监控的总数
		obj.setEq_type("3"); // hp 刀片
		int k = reportHostService.queryMonitorHostCount(obj); // 查询已监控的个数
		int c = reportHostService.queryAllHostCount(obj); // 查询能够监控的总数

		listHost
				.append("<dataset seriesName=\"已监控\" color=\"1E90FF\" alpha=\"70\" showValues=\"0\">");
		listHost.append("<set value=\"" + i + "\" />");
		listHost.append("<set value=\"" + j + "\" />");
		listHost.append("<set value=\"" + k + "\" />");
		listHost.append("</dataset>");
		listHost
				.append("<dataset seriesName=\"未监控\" color=\"EEC900\" alpha=\"90\" showValues=\"0\">");
		listHost.append("<set value=\"" + (a - i) + "\" />");
		listHost.append("<set value=\"" + (b - j) + "\" />");
		listHost.append("<set value=\"" + (c - k) + "\" />");
		listHost.append("</dataset>");
		listHost.append("</chart>");

		/* 将主机类型，已监控，未监控，可以监控的个数等信息放在List中显示在页面 */
		List<TbCloud2HostInfoObj> resultList = new ArrayList();
		TbCloud2HostInfoObj monitorObj = new TbCloud2HostInfoObj();
		monitorObj.setEq_type("1");
		monitorObj.setMonitorCount(i);
		monitorObj.setAllCount(a);
		monitorObj.setNotMonitorCount(a - i);
		resultList.add(monitorObj);
		TbCloud2HostInfoObj notObj = new TbCloud2HostInfoObj();
		notObj.setEq_type("2");
		notObj.setMonitorCount(j);
		notObj.setAllCount(b);
		notObj.setNotMonitorCount(b - j);
		resultList.add(notObj);
		TbCloud2HostInfoObj allObj = new TbCloud2HostInfoObj();
		allObj.setEq_type("3");
		allObj.setMonitorCount(k);
		allObj.setAllCount(c);
		allObj.setNotMonitorCount(c - k);
		resultList.add(allObj);
		theForm.setResultList(resultList);
		request.setAttribute("listHost", listHost.toString());
		return "listHostReport";
	}

	private ReportHostService reportHostService;

	private HostInfoService hostInfoService;

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setreportHostService(ReportHostService reportHostService) {
		this.reportHostService = reportHostService;
	}
}
