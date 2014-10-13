package com.sitech.basd.yicloud.web.scheduler.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.scheduler.KPIWeightObj;
import com.sitech.basd.yicloud.service.scheduler.KPIService;
import com.sitech.basd.yicloud.service.scheduler.StrategyService;
import com.sitech.basd.yicloud.web.scheduler.form.KPIForm;

public class KPIAction extends CRUDBaseAction {
	private KPIForm theForm;
	private KPIService kpiService;
	private StrategyService strategyService;

	public void setKpiService(KPIService kpiService) {
		this.kpiService = kpiService;
	}

	public KPIForm getTheForm() {
		return theForm;
	}

	public void setTheForm(KPIForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: listKPI
	 * @Description: 显示所有KPI
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Dec 17, 2012 4:18:38 PM
	 */
	public String listKPI() {
		if (theForm == null) {
			theForm = new KPIForm();
		}
		KPIWeightObj obj = new KPIWeightObj();
		if (theForm.getQuery_id() != null && !theForm.getQuery_id().equals("")) {
			obj.setKpi_id(theForm.getQuery_id());
		}
		if (theForm.getKpi_desc() != null && !theForm.getKpi_desc().equals("")) {
			obj.setKpi_desc(theForm.getKpi_desc());
		}
		if (theForm.getKpi_type() != null && !theForm.getKpi_type().equals("")) {
			obj.setKpi_type(theForm.getKpi_type());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List resutlList = kpiService.listKPI(obj);
		theForm.setResultList(resutlList);
		return "listKPI";
	}

	/**
	 * 
	 * @Title: addKPI
	 * @Description: 进入增加kpi页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Dec 17, 2012 5:21:10 PM
	 */
	public String addKPI() {
		return "addKPI";
	}

	/**
	 * 
	 * @Title: updateKPI
	 * @Description: 进入修改kpi页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Dec 17, 2012 5:21:50 PM
	 */
	public String updateKPI() {
		if (theForm == null) {
			theForm = new KPIForm();
		}
		KPIWeightObj obj = new KPIWeightObj();
		obj.setKpi_id(theForm.getKpi_id());
		KPIWeightObj reObj = kpiService.queryForObj(obj);
		theForm.setKpi_id(reObj.getKpi_id());
		theForm.setKpi_weight(reObj.getKpi_weight());
		theForm.setKpi_desc(reObj.getKpi_desc());
		theForm.setFlag(1);
		return "updateKPI";
	}

	/**
	 * 
	 * @Title: saveKPI
	 * @Description: 保存增加或修改的kpi
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Dec 17, 2012 5:22:18 PM
	 */
	public String saveKPI() {
		if (theForm == null) {
			theForm = new KPIForm();
		}
		int flag = theForm.getFlag();
		KPIWeightObj obj = new KPIWeightObj();
		obj.setKpi_id(theForm.getKpi_id());
		obj.setKpi_weight(theForm.getKpi_weight());
		obj.setKpi_desc(theForm.getKpi_desc());
		obj.setKpi_type(theForm.getKpi_type());
		if (flag == 1) {
			kpiService.updateKPI(obj);
		} else {
			kpiService.addKPI(obj);
		}
		return "saveKPI";
	}

	/**
	 * 
	 * @Title: deleteKPI
	 * @Description: 删除kpi
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Dec 17, 2012 5:22:40 PM
	 */
	public String deleteKPI() {
		if (theForm == null) {
			theForm = new KPIForm();
		}
		KPIWeightObj obj = new KPIWeightObj();
		obj.setKpi_id(theForm.getKpi_id());
		int ret = kpiService.deleteKPI(obj);
		return "deleteKPI";
	}

	/**
	 * 
	 * @Title: 校验KPIID是否已生成
	 * @Copyright:Copyright (c) Aug 28, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String checkKPI() {

		HttpServletRequest request = Struts2Utils.getRequest();
		// 获取入参
		String kpiid = request.getParameter("kpiid");
		KPIWeightObj obj = new KPIWeightObj();
		obj.setKpi_id(kpiid);

		List resutlList = strategyService.listKPIWeights(obj);
		if (resutlList != null && resutlList.size() > 0) {
			// 有相同的KPIID
			showErrorMsg(1);
		} else {
			showErrorMsg(0);
		}
		return null;
	}

	public void setStrategyService(StrategyService strategyService) {
		this.strategyService = strategyService;
	}
}
