package com.sitech.basd.sxcloud.cloud.web.reportapp.action;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.sitech.basd.sxcloud.cloud.domain.appmessage.AppMessageObj;
import com.sitech.basd.sxcloud.cloud.domain.reportapp.TbAppCollObj;
import com.sitech.basd.sxcloud.cloud.service.appmessage.AppMessageService;
import com.sitech.basd.sxcloud.cloud.service.reportapp.ReportAppCollService;
import com.sitech.basd.sxcloud.cloud.util.DateUtil;
import com.sitech.basd.sxcloud.cloud.web.appmessage.form.AppMessageForm;
import com.sitech.basd.sxcloud.cloud.web.reportapp.form.ReportAppForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class ReportAppAction extends BaseAction {
	private AppMessageForm theForm;
	
	public AppMessageForm getTheForm() {
		return theForm;
	}

	public void setTheForm(AppMessageForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有应用信息
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listAppReport()
			throws BaseException {
		if(theForm == null){
			theForm = new AppMessageForm();
		}
		AppMessageObj obj = new AppMessageObj();
		if (theForm.getAPP_ID() != null && !"".equals(theForm.getAPP_ID())) {
			obj.setAPP_ID(theForm.getAPP_ID().trim());
		}
		if (theForm.getAPP_NAME() != null && !"".equals(theForm.getAPP_NAME())) {
			obj.setAPP_NAME(theForm.getAPP_NAME().trim());
		}

		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List AppMessageList = appMessageService.queryForListByObj(obj);
		theForm.setResultList(AppMessageList);
		return "listAppReport";
	}

	/**
	 * @Title:查询当前应用监控报表
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String getAppCurrentReportView() throws BaseException {
		if(theForm == null){
			theForm = new AppMessageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String app_id = request.getParameter("app_id");
		String cpuKpiId = "PM-A-01-010-03";
		TbAppCollObj cpuObj = new TbAppCollObj();
		cpuObj.setApp_id(app_id);
		cpuObj.setKpi_id(cpuKpiId);
		List<TbAppCollObj> cpuTodayList = reportAppCollService
				.queryAppCollForToday(cpuObj);

		String memKpiId = "PM-A-01-010-04";
		TbAppCollObj memObj = new TbAppCollObj();
		memObj.setApp_id(app_id);
		memObj.setKpi_id(memKpiId);
		List<TbAppCollObj> memTodayList = reportAppCollService
				.queryAppCollForToday(memObj);

		StringBuilder cpuXML = new StringBuilder(
				"<chart caption=\"cpu监控\" subcaption=\"\" lineThickness=\"1\" showValues=\"0\" formatNumberScale=\"0\" anchorRadius=\"1\"   divLineAlpha=\"20\" divLineColor=\"CC3300\" divLineIsDashed=\"1\" showAlternateHGridColor=\"1\" alternateHGridColor=\"CC3300\" shadowAlpha=\"40\" labelStep=\"");
		StringBuilder memXML = new StringBuilder(
				"<chart caption=\"内存监控\" subcaption=\"\" lineThickness=\"1\" showValues=\"0\" formatNumberScale=\"0\" anchorRadius=\"1\"   divLineAlpha=\"20\" divLineColor=\"CC3300\" divLineIsDashed=\"1\" showAlternateHGridColor=\"1\" alternateHGridColor=\"CC3300\" shadowAlpha=\"40\" labelStep=\"");
		int cpuStep = 4; // X轴每一小时显示一个点
		int memStep = 4; // X轴每一小时显示一个点
		cpuXML.append(cpuStep);
		memXML.append(memStep);
		cpuXML
				.append("\" numvdivlines=\"5\" chartRightMargin=\"35\" bgColor=\"FFFFFF,CC3300\" bgAngle=\"270\" bgAlpha=\"10,10\" alternateHGridAlpha=\"5\"  legendPosition =\"RIGHT \"showBorder =\"0\" anchorBorderColor=\"F1683C\" anchorBgColor=\"F1683C\">");
		memXML
				.append("\" numvdivlines=\"5\" chartRightMargin=\"35\" bgColor=\"FFFFFF,CC3300\" bgAngle=\"270\" bgAlpha=\"10,10\" alternateHGridAlpha=\"5\"  legendPosition =\"RIGHT \"showBorder =\"0\" anchorBorderColor=\"F1683C\" anchorBgColor=\"F1683C\">");

		for (TbAppCollObj tempObj : cpuTodayList) {
			String time = tempObj.getColl_time().substring(11, 16);
			cpuXML.append("<set label=\"" + time + "\" value=\""
					+ tempObj.getKpi_value() + "\" color=\"F1683C\" />");
		}
		for (TbAppCollObj tempObj : memTodayList) {
			String time = tempObj.getColl_time().substring(11, 16);
			memXML.append("<set label=\"" + time + "\" value=\""
					+ tempObj.getKpi_value() + "\" color=\"F1683C\" />");
		}

		cpuXML.append("</chart>");
		memXML.append("</chart>");
		request.setAttribute("cpuXML", cpuXML.toString());
		request.setAttribute("memXML", memXML.toString());
		request.setAttribute("app_id", app_id);
		return "appCurrentReport";
	}

	/**
	 * @Title:查询当前应用监控周报表
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String getAppWeekReportView() throws BaseException {
		if(theForm == null){
			theForm = new AppMessageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String app_id = request.getParameter("app_id");
		TbAppCollObj obj = new TbAppCollObj();
		obj.setApp_id(app_id);
		String table_name = "TB_CLOUD2_APPLY_COLL_"
				+ DateUtil.getFormatNMonth("yyyyMM", -1);
		obj.setTable_name(table_name);
		Calendar calendar = Calendar.getInstance();
		int dd = calendar.get(Calendar.DATE);
		List<TbAppCollObj> weekList = null;
		List<TbAppCollObj> lastWeekList = null;
		if (dd > 14) {
			weekList = reportAppCollService.queryAppCollForWeek(obj);
			lastWeekList = reportAppCollService.queryAppCollLastWeek(obj);
		} else {
			lastWeekList = reportAppCollService.queryUnionAppCollLastWeek(obj);
			if (dd > 7) {
				weekList = reportAppCollService.queryAppCollForWeek(obj);
			} else {
				weekList = reportAppCollService.queryUnionAppCollLastWeek(obj);
			}
		}
		// int cpuStep = 0; // X轴每天显示一个点
		// int memStep = 0; // X轴每天显示一个点
		StringBuilder cpuXML = new StringBuilder(
				"<chart caption=\"cpu监控\" subcaption=\"\" lineThickness=\"1\" showValues=\"0\" showNames=\"0\" formatNumberScale=\"0\" anchorRadius=\"1\"   divLineAlpha=\"20\" divLineColor=\"CC3300\" divLineIsDashed=\"1\" showAlternateHGridColor=\"1\" alternateHGridColor=\"CC3300\" shadowAlpha=\"40\" labelStep=\"");
		StringBuilder memXML = new StringBuilder(
				"<chart caption=\"内存监控\" subcaption=\"\" lineThickness=\"1\" showValues=\"0\" showNames=\"0\" formatNumberScale=\"0\" anchorRadius=\"1\"   divLineAlpha=\"20\" divLineColor=\"CC3300\" divLineIsDashed=\"1\" showAlternateHGridColor=\"1\" alternateHGridColor=\"CC3300\" shadowAlpha=\"40\" labelStep=\"");
		// cpuXML.append(cpuStep);
		// memXML.append(memStep);
		cpuXML
				.append("\" numvdivlines=\"5\" chartRightMargin=\"35\" bgColor=\"FFFFFF,CC3300\" bgAngle=\"270\" bgAlpha=\"10,10\" alternateHGridAlpha=\"5\"  legendPosition =\"RIGHT \"showBorder =\"0\">");
		memXML
				.append("\" numvdivlines=\"5\" chartRightMargin=\"35\" bgColor=\"FFFFFF,CC3300\" bgAngle=\"270\" bgAlpha=\"10,10\" alternateHGridAlpha=\"5\"  legendPosition =\"RIGHT \" showBorder =\"0\">");
		cpuXML.append("<categories >");
		memXML.append("<categories >");
		/*
		 * for (int i = 0; i < weekList.size(); i++) { TbAppCollObj tempObj =
		 * weekList.get(i); String kpiStr = tempObj.getKpi_id(); String time =
		 * tempObj.getColl_time().substring(5, 16); if
		 * ("PM-A-01-010-03".equals(kpiStr)) { cpuXML.append("<category
		 * label=\"" + time + "\" />"); } else if
		 * ("PM-A-01-010-04".equals(kpiStr)) { memXML.append("<category
		 * label=\"" + time + "\" />"); } }
		 */
		for (TbAppCollObj tempObj : weekList) {
			String kpiStr = tempObj.getKpi_id();
			String time = tempObj.getColl_time().substring(5, 16);
			if ("PM-A-01-010-03".equals(kpiStr)) {
				cpuXML.append("<category label=\"" + time + "\" />");
			} else if ("PM-A-01-010-04".equals(kpiStr)) {
				memXML.append("<category label=\"" + time + "\" />");
			}
		}
		cpuXML.append("</categories>");
		memXML.append("</categories>");
		cpuXML
				.append("<dataset seriesName=\"上周\" color=\"1D8BD1\" anchorBorderColor=\"1D8BD1\" anchorBgColor=\"1D8BD1\">");
		memXML
				.append("<dataset seriesName=\"上周\" color=\"1D8BD1\" anchorBorderColor=\"1D8BD1\" anchorBgColor=\"1D8BD1\">");
		for (TbAppCollObj tempObj : lastWeekList) {
			String kpiStr = tempObj.getKpi_id();
			String kpivalue = tempObj.getKpi_value();
			String coll_time = tempObj.getColl_time().substring(5, 10);
			if ("PM-A-01-010-03".equals(kpiStr)) {
				cpuXML.append("<set value=\"" + kpivalue + "\" />");
			} else if ("PM-A-01-010-04".equals(kpiStr)) {
				memXML.append("<set value=\"" + kpivalue + "\" />");
			}
		}
		cpuXML.append("</dataset>");
		memXML.append("</dataset>");
		cpuXML
				.append("<dataset seriesName=\"本周\" color=\"F1683C\" anchorBorderColor=\"F1683C\" anchorBgColor=\"F1683C\">");
		memXML
				.append("<dataset seriesName=\"本周\" color=\"F1683C\" anchorBorderColor=\"F1683C\" anchorBgColor=\"F1683C\">");
		for (TbAppCollObj tempObj : weekList) {
			String kpiStr = tempObj.getKpi_id();
			String kpivalue = tempObj.getKpi_value();
			if ("PM-A-01-010-03".equals(kpiStr)) {
				cpuXML.append("<set value=\"" + kpivalue + "\" />");
			} else if ("PM-A-01-010-04".equals(kpiStr)) {
				memXML.append("<set value=\"" + kpivalue + "\" />");
			}
		}
		cpuXML.append("</dataset>");
		memXML.append("</dataset>");
		cpuXML.append("</chart>");
		memXML.append("</chart>");

		request.setAttribute("cpuXML", cpuXML.toString());
		request.setAttribute("memXML", memXML.toString());
		request.setAttribute("app_id", app_id);

		return "appWeekReport";
	}

	/**
	 * @Title:查询应用监控月报表
	 * @Copyright: Copyright (c) 20120328
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String getAppMonthReportView() throws BaseException {
		if(theForm == null){
			theForm = new AppMessageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String app_id = request.getParameter("app_id");
		TbAppCollObj obj = new TbAppCollObj();
		obj.setApp_id(app_id);
		String table_name = "TB_CLOUD2_APPLY_COLL_"
				+ DateUtil.getFormatNMonth("yyyyMM", -1);
		obj.setTable_name(table_name);
		List<TbAppCollObj> monthList = reportAppCollService
				.queryAppCollForMonth(obj);
		List<TbAppCollObj> lastMonthList = reportAppCollService
				.queryAppCollLastMonth(obj);
		int cpuStep = 96; // X轴每天显示一个点
		int memStep = 96; // X轴每天显示一个点
		StringBuilder cpuXML = new StringBuilder(
				"<chart caption=\"cpu监控\" subcaption=\"\" lineThickness=\"1\" showValues=\"0\" showNames=\"0\" formatNumberScale=\"0\" anchorRadius=\"0.3\"   divLineAlpha=\"20\" divLineColor=\"CC3300\" divLineIsDashed=\"1\" showAlternateHGridColor=\"1\" alternateHGridColor=\"CC3300\" shadowAlpha=\"40\" labelStep=\"");
		StringBuilder memXML = new StringBuilder(
				"<chart caption=\"内存监控\" subcaption=\"\" lineThickness=\"1\" showValues=\"0\" showNames=\"0\" formatNumberScale=\"0\" anchorRadius=\"0.3\"   divLineAlpha=\"20\" divLineColor=\"CC3300\" divLineIsDashed=\"1\" showAlternateHGridColor=\"1\" alternateHGridColor=\"CC3300\" shadowAlpha=\"40\" labelStep=\"");
		cpuXML.append(cpuStep);
		memXML.append(memStep);
		cpuXML
				.append("\" numvdivlines=\"5\" chartRightMargin=\"35\" bgColor=\"FFFFFF,CC3300\" bgAngle=\"270\" bgAlpha=\"10,10\" alternateHGridAlpha=\"5\"  legendPosition =\"RIGHT \"showBorder =\"0\">");
		memXML
				.append("\" numvdivlines=\"5\" chartRightMargin=\"35\" bgColor=\"FFFFFF,CC3300\" bgAngle=\"270\" bgAlpha=\"10,10\" alternateHGridAlpha=\"5\"  legendPosition =\"RIGHT \"showBorder =\"0\">");
		cpuXML.append("<categories >");
		memXML.append("<categories >");
		List<TbAppCollObj> xMonthList = null;
		if (lastMonthList != null) {
			xMonthList = lastMonthList.size() > monthList.size() ? lastMonthList
					: monthList;
		} else {
			xMonthList = monthList;
		}

		for (TbAppCollObj tempObj : xMonthList) {
			String kpiStr = tempObj.getKpi_id();
			String coll_time = tempObj.getColl_time().substring(5, 16);
			if ("PM-A-01-010-03".equals(kpiStr)) {
				cpuXML.append("<category label=\"" + coll_time + "\" />");
			} else if ("PM-A-01-010-04".equals(kpiStr)) {
				memXML.append("<category label=\"" + coll_time + "\" />");
			}
		}
		cpuXML.append("</categories>");
		memXML.append("</categories>");
		cpuXML
				.append("<dataset seriesName=\"上月\" color=\"1D8BD1\" anchorBorderColor=\"1D8BD1\" anchorBgColor=\"1D8BD1\">");
		memXML
				.append("<dataset seriesName=\"上月\" color=\"1D8BD1\" anchorBorderColor=\"1D8BD1\" anchorBgColor=\"1D8BD1\">");
		if (lastMonthList != null) {
			for (TbAppCollObj tempObj : lastMonthList) {
				String kpiStr = tempObj.getKpi_id();
				String kpiValue = tempObj.getKpi_value();
				if ("PM-A-01-010-03".equals(kpiStr)) {
					cpuXML.append("<set value=\"" + kpiValue + "\" />");
				} else if ("PM-A-01-010-04".equals(kpiStr)) {
					memXML.append("<set value=\"" + kpiValue + "\" />");
				}
			}
		}
		cpuXML.append("</dataset>");
		memXML.append("</dataset>");
		cpuXML
				.append("<dataset seriesName=\"本月\" color=\"F1683C\" anchorBorderColor=\"F1683C\" anchorBgColor=\"F1683C\">");
		memXML
				.append("<dataset seriesName=\"本月\" color=\"F1683C\" anchorBorderColor=\"F1683C\" anchorBgColor=\"F1683C\">");
		for (TbAppCollObj tempObj : monthList) {
			String kpiStr = tempObj.getKpi_id();
			String kpiValue = tempObj.getKpi_value();
			if ("PM-A-01-010-03".equals(kpiStr)) {
				cpuXML.append("<set value=\"" + kpiValue + "\" />");
			} else if ("PM-A-01-010-04".equals(kpiStr)) {
				memXML.append("<set value=\"" + kpiValue + "\" />");
			}
		}
		cpuXML.append("</dataset>");
		memXML.append("</dataset>");

		cpuXML.append("</chart>");
		memXML.append("</chart>");

		request.setAttribute("cpuXML", cpuXML.toString());
		request.setAttribute("memXML", memXML.toString());
		request.setAttribute("app_id", app_id);

		return "AppMonthReport";
	}

	private AppMessageService appMessageService;
	private ReportAppCollService reportAppCollService;

	public void setAppMessageService(AppMessageService appMessageService) {
		this.appMessageService = appMessageService;
	}

	public void setReportAppCollService(
			ReportAppCollService reportAppCollService) {
		this.reportAppCollService = reportAppCollService;
	}

}
