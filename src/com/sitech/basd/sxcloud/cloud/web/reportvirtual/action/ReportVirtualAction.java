package com.sitech.basd.sxcloud.cloud.web.reportvirtual.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.sitech.basd.sxcloud.cloud.domain.cubinetmanage.TbCubinetObj;
import com.sitech.basd.sxcloud.cloud.domain.reportvirtual.ReportTreeObj;
import com.sitech.basd.sxcloud.cloud.domain.reportvirtual.TbHyObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualPoolObj;
import com.sitech.basd.sxcloud.cloud.service.cubinetmanage.CubinetService;
import com.sitech.basd.sxcloud.cloud.service.reportvirtual.ReportVirtualService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.virtual.VirtualPoolService;
import com.sitech.basd.sxcloud.cloud.service.virtual.VirtualService;
import com.sitech.basd.sxcloud.cloud.util.BuildXmlString;
import com.sitech.basd.sxcloud.cloud.util.DateUtil;
import com.sitech.basd.sxcloud.cloud.web.reportvirtual.form.ReportVirtualForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class ReportVirtualAction extends BaseAction {
	private ReportVirtualForm theForm;
	
	public ReportVirtualForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ReportVirtualForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有虚拟机管理模块信息
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String getVirtualListReport() throws BaseException {
		if(theForm == null){
			theForm = new ReportVirtualForm();
		}
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		String IBMPower = "1";
		obj.setEq_type(IBMPower);
		obj.setControl(1);
		List hostList = hostInfoService.queryForListByObj(obj);
		ReportTreeObj[] reportTreeObjArray = null;
		if (hostList != null && hostList.size() > 0) {
			reportTreeObjArray = new ReportTreeObj[hostList.size()];
			for (int i = 0; i < hostList.size(); i++) {
				TbCloud2HostInfoObj tempObj = (TbCloud2HostInfoObj) hostList
						.get(i);
				reportTreeObjArray[i] = new ReportTreeObj();
				TbCubinetObj cubObj = new TbCubinetObj();
				cubObj.setC_id(tempObj.getCq_id());
				reportTreeObjArray[i].setC_addr(cubinetService.queryByObj(
						cubObj).getC_addr());
				reportTreeObjArray[i].setMName(tempObj.getEq_name());
				// TbCloud2VirtualInfoObj vObj = new TbCloud2VirtualInfoObj();
				TbCloud2VirtualPoolObj vObj = new TbCloud2VirtualPoolObj();
				// vObj.setEQ_ID(tempObj.getEq_id());
				vObj.setEq_id(tempObj.getEq_id());
				// List vHost = virtualService.queryForListByObj(vObj);
				List vHost = virtualPoolService.queryForListByObj(vObj);
				if (vHost != null && vHost.size() > 0) {
					// TbCloud2VirtualInfoObj[] vHostArray = new
					// TbCloud2VirtualInfoObj[vHost
					// .size()];
					TbCloud2VirtualPoolObj[] vHostArray = new TbCloud2VirtualPoolObj[vHost
							.size()];
					vHost.toArray(vHostArray);
					reportTreeObjArray[i].setVirPoolObjs(vHostArray);
				}
			}
		}
		Struts2Utils.getRequest().setAttribute("reportTreeObjArray", reportTreeObjArray);
		return "getVirtualListReport";
	}

	/**
	 * @Title:查询虚拟机的周报表
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String getWeekReportView() throws BaseException {
		if(theForm == null){
			theForm = new ReportVirtualForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String hy_id = request.getParameter("hy_id");
		String tag = request.getParameter("tag");
		try {
			tag = new String(tag.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		TbHyObj cpuobj = new TbHyObj();
		cpuobj.setHy_id(hy_id);
		String table_name = "TB_CLOUD2_HY_COLL_"
				+ DateUtil.getFormatNMonth("yyyyMM", -1);
		cpuobj.setTable_name(table_name);
		cpuobj.setKpi_id("PM-V-01-010-11");
		
		TbHyObj memoryObj = new TbHyObj();
		memoryObj.setHy_id(hy_id);
		memoryObj.setTable_name(table_name);
		memoryObj.setKpi_id("PM-V-01-010-12");
		
		TbHyObj storageObj = new TbHyObj();
		storageObj.setHy_id(hy_id);
		storageObj.setTable_name(table_name);
		storageObj.setKpi_id("PM-V-01-010-13");
		
		TbHyObj netObj = new TbHyObj();
		netObj.setHy_id(hy_id);
		netObj.setTable_name(table_name);
		netObj.setKpi_id("PM-V-01-010-14");
		
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
			cpuweekList = reportVirtualService.queryTbHyForWeek(cpuobj);
			cpulastWeekList = reportVirtualService.queryTbHyForLastWeek(cpuobj);
			memoryweekList = reportVirtualService.queryTbHyForWeek(memoryObj);
			memorylastWeekList = reportVirtualService.queryTbHyForLastWeek(memoryObj);
			storageweekList = reportVirtualService.queryTbHyForWeek(storageObj);
			storagelastWeekList = reportVirtualService.queryTbHyForLastWeek(storageObj);
			netweekList = reportVirtualService.queryTbHyForWeek(netObj);
			netlastWeekList = reportVirtualService.queryTbHyForLastWeek(netObj);
		} else {
			cpulastWeekList = reportVirtualService.queryUnionTbHyForLastWeek(cpuobj);
			memorylastWeekList = reportVirtualService.queryUnionTbHyForLastWeek(memoryObj);
			storagelastWeekList = reportVirtualService.queryUnionTbHyForLastWeek(storageObj);
			netlastWeekList = reportVirtualService.queryUnionTbHyForLastWeek(netObj);
			if (dd > 7) {
				cpuweekList = reportVirtualService.queryTbHyForWeek(cpuobj);
				memoryweekList = reportVirtualService.queryTbHyForWeek(memoryObj);
				storageweekList = reportVirtualService.queryTbHyForWeek(storageObj);
				netweekList = reportVirtualService.queryTbHyForWeek(netObj);
			} else {
				cpuweekList = reportVirtualService.queryUnionTbHyForWeek(cpuobj);
				memoryweekList = reportVirtualService.queryUnionTbHyForWeek(memoryObj);
				storageweekList = reportVirtualService.queryUnionTbHyForWeek(storageObj);
				netweekList = reportVirtualService.queryUnionTbHyForWeek(netObj);
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
			TbHyObj tempObj = (TbHyObj) cpuweekList.get(i);
			TbHyObj tempLastObj = null;
			if(i <= cpulastWeekList.size()-1){
				tempLastObj = (TbHyObj)cpulastWeekList.get(i);
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
			TbHyObj tempObj = (TbHyObj) memoryweekList.get(i);
			TbHyObj tempLastObj = null;
			if(i <= memorylastWeekList.size()-1){
				tempLastObj = (TbHyObj)memorylastWeekList.get(i);
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
			TbHyObj tempObj = (TbHyObj) storageweekList.get(i);
			TbHyObj tempLastObj = null;
			if(i <= storagelastWeekList.size()-1){
				tempLastObj = (TbHyObj)storagelastWeekList.get(i);
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
			TbHyObj tempObj = (TbHyObj) netweekList.get(i);
			TbHyObj tempLastObj = null;
			if(i <= netlastWeekList.size()-1){
				tempLastObj = (TbHyObj)netlastWeekList.get(i);
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
		cpuparaMap.put("seriesName1", "本周");
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
		memoryparaMap.put("seriesName1", "本周");
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
		storageparaMap.put("seriesName1", "本周");
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
		netparaMap.put("seriesName1", "本周");
		netparaMap.put("step", netStep);
		
		request.setAttribute("cpuXML", BuildXmlString.BuildXmlData(cpuparaMap, cpuxyAxisList, "MSLine").toString());
		request.setAttribute("memoryXML", BuildXmlString.BuildXmlData(memoryparaMap,memoryxyAxisList, "MSLine").toString());
		request.setAttribute("storageXML",BuildXmlString.BuildXmlData(storageparaMap,storagexyAxisList, "MSLine").toString());
		request.setAttribute("netXML",BuildXmlString.BuildXmlData(netparaMap,netxyAxisList, "MSLine").toString());
		request.setAttribute("hy_id", hy_id);
		request.setAttribute("tag", tag);

		return "getWeekReportView";

	}

	/**
	 * @Title:查询虚拟机的当前报表
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String getCurrentReportView() throws BaseException {
		if(theForm == null){
			theForm = new ReportVirtualForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String hy_id = request.getParameter("hy_id");
		String tag = request.getParameter("tag");

		try {
			tag = new String(tag.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		TbHyObj cpuobj = new TbHyObj();
		cpuobj.setHy_id(hy_id);
		String cpuKPI_ID = "PM-V-01-010-11";
		cpuobj.setKpi_id(cpuKPI_ID);
		List cpuTodayList = reportVirtualService.queryTbHyForToday(cpuobj);

		TbHyObj memoryobj = new TbHyObj();
		memoryobj.setHy_id(hy_id);
		String memoryKPI_ID = "PM-V-01-010-12";
		memoryobj.setKpi_id(memoryKPI_ID);
		List memoryTodayList = reportVirtualService.queryTbHyForToday(memoryobj);

		TbHyObj storageobj = new TbHyObj();
		storageobj.setHy_id(hy_id);
		String storageKPI_ID = "PM-V-01-010-13";
		storageobj.setKpi_id(storageKPI_ID);
		List storageTodayList = reportVirtualService
				.queryTbHyForToday(storageobj);

		TbHyObj netobj = new TbHyObj();
		netobj.setHy_id(hy_id);
		String netKPI_ID = "PM-V-01-010-14";
		netobj.setKpi_id(netKPI_ID);
		List netTodayList = reportVirtualService.queryTbHyForToday(netobj);
		 
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
			TbHyObj tempObj = (TbHyObj) cpuTodayList.get(i);
			String time = tempObj.getColl_time().substring(11, 16);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("X", time);
			map.put("Y", tempObj.getKpi_value());
			cpuxyAxisList.add(map);
		}
		for (int i = 0; i < memoryTodayList.size(); i++) {
			TbHyObj tempObj = (TbHyObj) memoryTodayList.get(i);
			String time = tempObj.getColl_time().substring(11, 16);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("X", time);
			map.put("Y", tempObj.getKpi_value());
			memoryxyAxisList.add(map);
		}
		for (int i = 0; i < storageTodayList.size(); i++) {
			TbHyObj tempObj = (TbHyObj) storageTodayList.get(i);
			String time = tempObj.getColl_time().substring(11, 16);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("X", time);
			map.put("Y", tempObj.getKpi_value());
			storagexyAxisList.add(map);
		}
		for (int i = 0; i < netTodayList.size(); i++) {
			TbHyObj tempObj = (TbHyObj) netTodayList.get(i);
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
		request.setAttribute("hy_id", hy_id);
		request.setAttribute("tag", tag);
		return "getCurrentReportView";

	}

	/**
	 * @Title:查询相应虚拟机的月报表
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String getMonthReportView() throws BaseException {
		if(theForm == null){
			theForm = new ReportVirtualForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String hy_id = request.getParameter("hy_id");
		String tag = request.getParameter("tag");

		try {
			tag = new String(tag.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		TbHyObj  cpuobj = new TbHyObj();
		cpuobj.setHy_id(hy_id);
		String table_name = "TB_CLOUD2_HY_COLL_"
				+ DateUtil.getFormatNMonth("yyyyMM", -1);
		cpuobj.setTable_name(table_name);
		cpuobj.setKpi_id("PM-V-01-010-11");
		
		TbHyObj memoryObj = new TbHyObj();
		memoryObj.setHy_id(hy_id);
		memoryObj.setTable_name(table_name);
		memoryObj.setKpi_id("PM-V-01-010-12");
		
		TbHyObj storageObj = new TbHyObj();
		storageObj.setHy_id(hy_id);
		storageObj.setTable_name(table_name);
		storageObj.setKpi_id("PM-V-01-010-13");
		
		TbHyObj netObj = new TbHyObj();
		netObj.setHy_id(hy_id);
		netObj.setTable_name(table_name);
		netObj.setKpi_id("PM-V-01-010-14");
		
		List cpumonthList = reportVirtualService.queryTbHyForMonth(cpuobj);
		List cpulastMonthList = reportVirtualService.queryTbHyForLastMonth(cpuobj);
		List memorymonthList = reportVirtualService.queryTbHyForMonth(memoryObj);
		List memorylastMonthList = reportVirtualService.queryTbHyForLastMonth(memoryObj);
		List storagemonthList = reportVirtualService.queryTbHyForMonth(storageObj);
		List storagelastMonthList = reportVirtualService.queryTbHyForLastMonth(storageObj);
		List netmonthList = reportVirtualService.queryTbHyForMonth(netObj);
		List netlastMonthList = reportVirtualService.queryTbHyForLastMonth(netObj);

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
			TbHyObj tempObj = (TbHyObj) xCpumonthList.get(i);
			String coll_time = tempObj.getColl_time().substring(11, 16);
			Map<String , Object> map = new HashMap<String, Object>();
			map.put("X", coll_time);
			if(xCpumonthList == cpulastMonthList){
				String value1 = tempObj.getKpi_value();
				map.put("Y1", value1);
				TbHyObj tempObj1 = null;
				if(i <= cpumonthList.size() - 1){
					tempObj1 = (TbHyObj) cpumonthList.get(i);
				}
				String value2 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y2", value2);
			}else {
				String value2 = tempObj.getKpi_value();
				map.put("Y2", value2);
				TbHyObj tempObj1 = null;
				if(i <= cpulastMonthList.size() - 1){
					tempObj1 = (TbHyObj) cpulastMonthList.get(i);
				}
				String value1 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y1", value1);
			}
			cpuxyAxisList.add(map);
		}
		
		List xMemorymonthList = memorylastMonthList.size() > memorymonthList.size() ? memorylastMonthList
				: memorymonthList;
		for (int i = 0; i < xMemorymonthList.size(); i++) {
			TbHyObj tempObj = (TbHyObj) xMemorymonthList.get(i);
			String coll_time = tempObj.getColl_time().substring(11, 16);
			Map<String , Object> map = new HashMap<String, Object>();
			map.put("X", coll_time);
			if(xMemorymonthList == memorylastMonthList){
				String value1 = tempObj.getKpi_value();
				map.put("Y1", value1);
				TbHyObj tempObj1 = null;
				if(i <= memorymonthList.size() - 1){
					tempObj1 = (TbHyObj) memorymonthList.get(i);
				}
				String value2 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y2", value2);
			}else {
				String value2 = tempObj.getKpi_value();
				map.put("Y2", value2);
				TbHyObj tempObj1 = null;
				if(i <= memorylastMonthList.size() - 1){
					tempObj1 = (TbHyObj) memorylastMonthList.get(i);
				}
				String value1 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y1", value1);
			}
			memoryxyAxisList.add(map);
		}
		
		List xStoragemonthList = storagelastMonthList.size() > storagemonthList.size() ? storagelastMonthList
				: storagemonthList;
		for (int i = 0; i < xStoragemonthList.size(); i++) {
			TbHyObj tempObj = (TbHyObj) xStoragemonthList.get(i);
			String coll_time = tempObj.getColl_time().substring(11, 16);
			Map<String , Object> map = new HashMap<String, Object>();
			map.put("X", coll_time);
			if(xStoragemonthList == storagelastMonthList){
				String value1 = tempObj.getKpi_value();
				map.put("Y1", value1);
				TbHyObj tempObj1 = null;
				if(i <= storagemonthList.size() - 1){
					tempObj1 = (TbHyObj) storagemonthList.get(i);
				}
				String value2 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y2", value2);
			}else {
				String value2 = tempObj.getKpi_value();
				map.put("Y2", value2);
				TbHyObj tempObj1 = null;
				if(i <= storagelastMonthList.size() - 1){
					tempObj1 = (TbHyObj) storagelastMonthList.get(i);
				}
				String value1 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y1", value1);
			}
			storagexyAxisList.add(map);
		}
		
		List xNetmonthList = netlastMonthList.size() > netmonthList.size() ? netlastMonthList
				: netmonthList;
		for (int i = 0; i < xNetmonthList.size(); i++) {
			TbHyObj tempObj = (TbHyObj) xNetmonthList.get(i);
			String coll_time = tempObj.getColl_time().substring(11, 16);
			Map<String , Object> map = new HashMap<String, Object>();
			map.put("X", coll_time);
			if(xNetmonthList == netlastMonthList){
				String value1 = tempObj.getKpi_value();
				map.put("Y1", value1);
				TbHyObj tempObj1 = null;
				if(i <= netmonthList.size() - 1){
					tempObj1 = (TbHyObj) netmonthList.get(i);
				}
				String value2 = tempObj1 == null? "0" :tempObj.getKpi_value();
				map.put("Y2", value2);
			}else {
				String value2 = tempObj.getKpi_value();
				map.put("Y2", value2);
				TbHyObj tempObj1 = null;
				if(i <= netlastMonthList.size() - 1){
					tempObj1 = (TbHyObj) netlastMonthList.get(i);
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
		request.setAttribute("hy_id", hy_id);
		request.setAttribute("tag", tag);

		return "getMonthReportView";
	}

	/**
	 * @Title:进入虚拟机报表的初始界面
	 * @Copyright: Copyright (c) 20120405
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listVirtualReport() throws BaseException {
		if(theForm == null){
			theForm = new ReportVirtualForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		List resultList = new ArrayList();

		StringBuilder listVirtual = new StringBuilder(
				"<chart palette=\"2\" caption=\"虚拟机监控\" showLabels=\"1\" showvalues=\"0\"  showSum=\"1\" decimals=\"0\" useRoundEdges=\"1\" bgColor=\"B5D4F3\" bgAlpha=\"10\" legendBorderAlpha=\"0\">");
		listVirtual.append("<categories>");

		TbCloud2HostInfoObj tempObj = new TbCloud2HostInfoObj();
		String IBMPower = "1";
		tempObj.setEq_type(IBMPower);
		tempObj.setControl(1);
		List<TbCloud2HostInfoObj> hostList = hostInfoService
				.queryForListByObj(tempObj);
		if (hostList != null && hostList.size() > 0) {
			int i = 0;
			for (TbCloud2HostInfoObj tObj : hostList) {
				// TbCubinetObj cubObj = new TbCubinetObj();
				// cubObj.setC_id(tObj.getCq_id());
				// String str = tObj.getEq_name() + "("
				// + cubinetService.queryByObj(cubObj).getC_addr() + ")";
				String str = tObj.getEq_name();
				listVirtual.append("<category label=\"" + str + "\"/>");
			}
			listVirtual.append("</categories>");
			listVirtual
					.append("<dataset seriesName=\"已监控\" color=\"1E90FF\" alpha=\"70\" showValues=\"0\">");
			HashMap map = new HashMap();
			for (TbCloud2HostInfoObj tObj : hostList) {
				String eq_id = tObj.getEq_id();
				obj.setEQ_ID(eq_id);
				i = reportVirtualService.queryMonitorVirtualCount(obj); // 查询已监控的个数
				int a = reportVirtualService.queryAllVirtualCount(obj); // 查询能够监控的总数
				map.put(eq_id, (a - i));
				listVirtual.append("<set value=\"" + i + "\" />");
			}
			listVirtual.append("</dataset>");
			listVirtual
					.append("<dataset seriesName=\"未监控\" color=\"EEC900\" alpha=\"90\" showValues=\"0\">");
			for (TbCloud2HostInfoObj tObj : hostList) {
				String eq_id = tObj.getEq_id();
				// obj.setEQ_ID(eq_id);
				// int a = reportVirtualService.queryAllVirtualCount(obj); //
				// 查询能够监控的总数
				Set<Map.Entry<String, Integer>> set = map.entrySet();
				for (Iterator<Map.Entry<String, Integer>> it = set.iterator(); it
						.hasNext();) {
					Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it
							.next();
					if (entry.getKey().equals(eq_id)) {
						listVirtual.append("<set value=\"" + entry.getValue()
								+ "\" />"); // 查询能够监控的总数
					}
				}

			}
			listVirtual.append("</dataset>");

			/* 查询已监控，未监控，所有可以监控的虚拟机列表显示在界面 */
			for (int j = 0; j < hostList.size(); j++) {
				TbCloud2HostInfoObj tObj = (TbCloud2HostInfoObj) hostList
						.get(j);
				String eq_id = tObj.getEq_id();
				obj.setEQ_ID(eq_id);
				int m = reportVirtualService.queryMonitorVirtualCount(obj); // 查询已监控的个数
				int n = reportVirtualService.queryAllVirtualCount(obj); // 查询能够监控的总数
				TbCubinetObj cubObj = new TbCubinetObj();
				cubObj.setC_id(tObj.getCq_id());
				String str = tObj.getEq_name() + "("
						+ cubinetService.queryByObj(cubObj).getC_addr() + ")";
				tObj.setEq_name(str);
				tObj.setMonitorCount(m);
				tObj.setNotMonitorCount(n - m);
				tObj.setAllCount(n);
				resultList.add(tObj);
			}
			theForm.setResultList(resultList);
		}

		listVirtual.append("</chart>");
		Struts2Utils.getRequest().setAttribute("listVirtual", listVirtual.toString());
		return "listVirtualReport";
	}

	private VirtualService virtualService;
	private ReportVirtualService reportVirtualService;
	private HostInfoService hostInfoService;
	private CubinetService cubinetService;
	private VirtualPoolService virtualPoolService;

	public void setVirtualPoolService(VirtualPoolService virtualPoolService) {
		this.virtualPoolService = virtualPoolService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setreportVirtualService(
			ReportVirtualService reportVirtualService) {
		this.reportVirtualService = reportVirtualService;
	}

	public void setVirtualService(VirtualService virtualService) {
		this.virtualService = virtualService;
	}

	public void setCubinetService(CubinetService cubinetService) {
		this.cubinetService = cubinetService;
	}
}
