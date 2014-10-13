package com.sitech.basd.yicloud.web.scheduler.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.scheduler.DefinitionObj;
import com.sitech.basd.yicloud.domain.scheduler.KPIWeightObj;
import com.sitech.basd.yicloud.service.scheduler.KPIService;
import com.sitech.basd.yicloud.service.scheduler.StrategyService;
import com.sitech.basd.yicloud.service.scheduler.TriggerDefService;
import com.sitech.basd.yicloud.web.scheduler.form.TriggerDefForm;
import com.sitech.utils.servlet.PrintWriterOut;

public class TriggerDefAction extends CRUDBaseAction {
	private static final long serialVersionUID = 1L;
	private TriggerDefForm theForm;
	private TriggerDefService triggerDefService;
	private StrategyService strategyService;
	private KPIService kpiService;

	public void setStrategyService(StrategyService strategyService) {
		this.strategyService = strategyService;
	}

	public void setTriggerDefService(TriggerDefService triggerDefService) {
		this.triggerDefService = triggerDefService;
	}

	public TriggerDefForm getTheForm() {
		return theForm;
	}

	public void setTheForm(TriggerDefForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: listTriggerDefinition
	 * @Description: 查询所有的触发器条件
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String listTriggerDefinition() {
		if (theForm == null) {
			theForm = new TriggerDefForm();
		}
		DefinitionObj obj = new DefinitionObj();
		if (theForm.getName() != null && !"".equals(theForm.getName())) {
			obj.setName(theForm.getName());
		}
		if (theForm.getType() != null && !"".equals(theForm.getType())) {
			if (!theForm.getType().equals("-1")) {
				obj.setType(theForm.getType());
			}
		}
		if (theForm.getLevel() != null && !"".equals(theForm.getLevel())) {
			if (!theForm.getLevel().equals("-1")) {
				obj.setLevel(theForm.getLevel());
			}
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List<DefinitionObj> resultList = triggerDefService.listTriggerDefinition(obj);
		if (resultList != null) {
			for (DefinitionObj tempObj : resultList) {
				int interval = Integer.parseInt(tempObj.getInterval());
				int tigger_n = Integer.parseInt(tempObj.getTigger_n());
				String operator = tempObj.getOperator();
				if (!operator.equals("in")) {
					tempObj.setContent(interval * tigger_n + "分钟内采集" + tempObj.getTigger_n()
							+ "次,触发条件：" + tempObj.getKpi_name() + tempObj.getOperator()
							+ tempObj.getThreshold() + "达到" + tempObj.getTrigger_m() + "次");
				} else {
					tempObj.setContent(interval * tigger_n + "分钟内采集" + tempObj.getTigger_n()
							+ "次,触发条件：" + tempObj.getKpi_name() + "大于等于" + tempObj.getMin()
							+ "且小于等于" + tempObj.getMax() + "达到" + tempObj.getTrigger_m() + "次");
				}
			}
		}
		theForm.setResultList(resultList);
		return "listTriggerDef";
	}

	/**
	 * 
	 * @Title: addTriggerDefinition
	 * @Description: 增加触发器条件
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String addTriggerDefinition() {
		KPIWeightObj obj = new KPIWeightObj();
		obj.setKpi_type("0");
		List kpiList = strategyService.listKPIWeights(obj);
		Struts2Utils.getRequest().setAttribute("kpiList", kpiList);
		return "addTriggerDef";
	}

	/**
	 * 
	 * @Title: updateTriggerDefinition
	 * @Description: 修改触发器条件
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String updateTriggerDefinition() {
		if (theForm == null) {
			theForm = new TriggerDefForm();
		}
		DefinitionObj obj = new DefinitionObj();
		obj.setTrigger_id(theForm.getTrigger_id());
		List resultList = triggerDefService.listTriggerDefinition(obj);
		if (resultList != null && resultList.size() > 0) {
			obj = (DefinitionObj) resultList.get(0);
		}
		try {
			BeanUtils.copyProperties(theForm, obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		KPIWeightObj kpiObj = new KPIWeightObj();
		List kpiList = strategyService.listKPIWeights(kpiObj);
		Struts2Utils.getRequest().setAttribute("kpiList", kpiList);
		theForm.setFlag(1);
		return "addTriggerDef";
	}

	/**
	 * 
	 * @Title: delTriggerDefinition
	 * @Description: 删除触发器条件
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String delTriggerDefinition() {
		DefinitionObj obj = new DefinitionObj();
		obj.setTrigger_id(theForm.getTrigger_id());
		triggerDefService.delTriggerDefinition(obj);
		return "delTriggerDef";
	}

	/**
	 * 
	 * @Title: saveTriggerDefinition
	 * @Description: 保存触发器条件
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String saveTriggerDefinition() {
		DefinitionObj obj = new DefinitionObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (theForm.getFlag() == 0) {
			triggerDefService.insertTriggerDefinition(obj);
		} else {
			triggerDefService.updateTriggerDefinition(obj);
		}

		return "saveTriggerDef";
	}

	/**
	 * 
	 * @Title:根据指标类型查询获取KPI
	 * @Copyright:Copyright (c) Aug 28, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String queryKpi() {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 获取入参
		String type = request.getParameter("type");
		KPIWeightObj obj = new KPIWeightObj();
		obj.setKpi_type(type);
		// 查询KPI结果
		List kpiList = kpiService.listKPI(obj);
		// 传值到前台
		JSONArray json = JSONArray.fromObject(kpiList);
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// PrintWriter p = response.getWriter();
		// p.print(json.toString());
		// p.close();
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	public void setKpiService(KPIService kpiService) {
		this.kpiService = kpiService;
	}
}
