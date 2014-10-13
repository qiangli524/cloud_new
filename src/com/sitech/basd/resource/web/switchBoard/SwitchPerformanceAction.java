package com.sitech.basd.resource.web.switchBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.domain.switchBoard.SwitchPerformanceObj;
import com.sitech.basd.resource.service.switchBoard.SwitchPerformanceService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;

@Controller("switchPerformanceAction")
@Scope("prototype")
public class SwitchPerformanceAction extends BaseAction {

	@Autowired
	private SwitchPerformanceService performanceService;
	private List<SwitchPerformanceObj> performanceList; // 接口性能列表
	private SwitchPerformanceObj theForm; // 封装页面表单

	public List<SwitchPerformanceObj> getPerformanceList() {
		return performanceList;
	}

	public void setPerformanceList(List<SwitchPerformanceObj> performanceList) {
		this.performanceList = performanceList;
	}

	public SwitchPerformanceObj getTheForm() {
		return theForm;
	}

	public void setTheForm(SwitchPerformanceObj theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: SwitchPerformanceList
	 * @Description: 查询接口性能列表
	 * @param
	 * @return List<SwitchPerfromanceObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 20, 2013
	 */
	public String list() {
		if (theForm == null) {
			theForm = new SwitchPerformanceObj();
		}
		SwitchPerformanceObj obj = new SwitchPerformanceObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		if (!"".equals(theForm.getInterf_id()) && theForm.getInterf_id() != null) {
			obj.setInterf_id(theForm.getInterf_id());
		}
		performanceList = performanceService.queryForList(obj);
		return "list";
	}
}
