/**
 * SxReportAction.java
 * com.sitech.ssd.sx.report.action
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 三月 27 		duangh
 *
 * Copyright (c) 2014, si-tech All Rights Reserved.
 */

package com.sitech.ssd.sx.report.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.ibmmanager.service.IBMManagerTreeService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.sx.report.domain.HostObj;
import com.sitech.ssd.sx.report.domain.SearchObj;
import com.sitech.ssd.sx.report.service.SxReportService;

/**
 * ClassName:SxReportAction Function: 同一个主机，不同时间段，同一时间段，不同主机报表展示
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 27 16:21:08
 */
@Scope("prototype")
@Controller("sxReportAction")
public class SxReportAction extends BaseAction {
	@Resource
	private IBMManagerTreeService ibmManagerTreeService;
	@Resource
	private SxReportService sxReportService;
	private SearchObj search;

	/**
	 * 
	 * queryAllEntities:查询所有主机，虚拟机等实体信息
	 * 
	 * @since duangh Ver 1.0
	 */
	private List<HostObj> queryAllEntities() {
		String entityType = request.getParameter("type");
		search = new SearchObj();
		search.setType(entityType);
		List<HostObj> hosts = sxReportService.getHostList(search);
		return hosts;
	}

	/**
	 * 
	 * sametimeReport:不同的主机,lpar同一时间段的报表展示
	 * 
	 * @since duangh Ver 1.0
	 */
	public String sametimeReport() {
		List<HostObj> list = this.queryAllEntities();
		ActionContext.getContext().getValueStack().set("entityList", list);
		return SUCCESS;
	}

	/**
	 * 
	 * sametimeData:不同主机，虚拟机实体同一阶段的数据
	 */
	public void sametimeData() {
		String starttime = request.getParameter("starttime");// 时间段开始时间
		String endtime = request.getParameter("endtime");// 时间段结束时间
		String kpi = request.getParameter("kpi");
		String ids = request.getParameter("ids");// 要查询的实体，默认为所有
		String type = request.getParameter("type");
		FusionCharts fusionCharts = sxReportService.sametimeData(ids, kpi, type, starttime, endtime);
		String fusionChartsString = JacksonUtil.toJson(fusionCharts);
		returnJson(fusionChartsString);
	}

	/**
	 * 
	 * <p>
	 * 查询多时间段报表数据
	 * </p>
	 * 
	 */
	public void getManytimeReportData() {
		String json = sxReportService.getManytimeReportData(search);
		returnJson(json);
	}

	public void getHostList() {
		List<HostObj> hosts = sxReportService.getHostList(search);
		returnJson(JSONArray.fromObject(hosts).toString());
	}

	public void getCardList() {
		List<HostObj> cards = sxReportService.getCardList(search);
		returnJson(JSONArray.fromObject(cards).toString());
	}

	/**
	 * 
	 * manytimeReport:相同主机，不同时间段报表
	 * 
	 * @since duangh Ver 1.0
	 */
	public String manytimeReport() {
		search = new SearchObj();
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
		Calendar calendar=Calendar.getInstance(); 
		search.setEndTime(sdf.format(calendar.getTime()));
		calendar.add(calendar.DATE, -7);
		search.setStartTime(sdf.format(calendar.getTime()));
		return SUCCESS;
	}

	/**
	 * 
	 * sameEntityReport:同一个实体（host,lpar,vm）不同时间段的报表展示
	 * 
	 * @since duangh Ver 1.0
	 */
	public void sameEntityReport() {

	}

	/**
	 * 
	 * @return the search
	 */
	public SearchObj getSearch() {
		return search;
	}

	/**
	 * 
	 * @param search
	 *            the search to set
	 */
	public void setSearch(SearchObj search) {
		this.search = search;
	}

}
