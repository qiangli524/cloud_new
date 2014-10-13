package com.sitech.basd.sxcloud.rsmu.web.system.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysLoginLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.system.FunctionsService;
import com.sitech.basd.sxcloud.rsmu.service.system.LoginLogService;
import com.sitech.basd.sxcloud.rsmu.service.system.OperationLogService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.system.form.BaseLogForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class BaseLogAction extends CRUDBaseAction {
	private BaseLogForm theForm;
	private LoginLogService loginLogService;
	private OperationLogService operationLogService;
	private FunctionsService functionsService;
	private static int countnum = 0;

	public BaseLogForm getTheForm() {
		return theForm;
	}

	public void setTheForm(BaseLogForm theForm) {
		this.theForm = theForm;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	public LoginLogService getLoginLogService() {
		return loginLogService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public OperationLogService getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	/**
	 * @Title:得到登录日志列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author hubo
	 * @version 1.0
	 */
	public String listLoginLogList() throws BaseException {
		if (theForm == null) {
			theForm = new BaseLogForm();
		}
		TbSysLoginLogObj obj = new TbSysLoginLogObj();
		if (theForm.getACCOUNT() != null && !theForm.getACCOUNT().equals("")) {
			obj.setACCOUNT(theForm.getACCOUNT());
		}
		if (theForm.getIP() != null && !theForm.getIP().equals("")) {
			obj.setIP(theForm.getIP());
		}
		if (theForm.getHOSTNAME() != null && !theForm.getHOSTNAME().equals("")) {
			obj.setHOSTNAME(theForm.getHOSTNAME());
		}
		if (theForm.getStart_time() != null
				&& !theForm.getStart_time().equals("")) {
			obj.setStart_logintime(theForm.getStart_time());
		}
		if (theForm.getEnd_time() != null && !theForm.getEnd_time().equals("")) {
			obj.setEnd_logintime(theForm.getEnd_time());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List resultList = this.loginLogService.queryForListByObj(obj);
		theForm.setResultList(resultList);
		return "listlogin";
	}

	/**
	 * @Title:得到操作日志列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author hubo
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listOperationLogList() throws BaseException {
		if (theForm == null) {
			theForm = new BaseLogForm();
		}
		TbSysOperationLogObj obj = new TbSysOperationLogObj();
		if (theForm.getACCOUNT() != null && !theForm.getACCOUNT().equals("")) {
			obj.setACCOUNT(theForm.getACCOUNT());
		}
		if (theForm.getNAME() != null && !theForm.getNAME().equals("")) {
			obj.setNAME(theForm.getNAME());
		}
		if (theForm.getResoursePoolObject() != null
				&& !theForm.getResoursePoolObject().equals("")) {
			obj.setResoursePoolObject(theForm.getResoursePoolObject());
		}
		if (theForm.getStart_time() != null
				&& !theForm.getStart_time().equals("")) {
			obj.setStart_opertime(theForm.getStart_time());
		}
		if (theForm.getEnd_time() != null && !theForm.getEnd_time().equals("")) {
			obj.setEnd_opertime(theForm.getEnd_time());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List resultList = this.operationLogService.queryForListByObj(obj);
		boolean bl = false;
		countnum++;
		if (countnum > 5) {
			bl = true;
			countnum = 0;
		}
		for (int i = 0; i < resultList.size(); i++) {

			obj = (TbSysOperationLogObj) resultList.get(i);
			String funcName = functionsService.FunctionModule(obj
					.getFUNCID_STR(), bl);
			if (funcName != null && !"".equals(funcName))
				;
			{
				obj.setFUNCID_STR(funcName);
			}
			resultList.set(i, obj);
			bl = false;
		}

		theForm.setResultList(resultList);
		return "listoper";
	}

	/**
	 * @Title:根据条件查询操作日志列表
	 * @Copyright: Copyright (c) 2012-10-24
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String queryListOperationLogList() throws BaseException {
		if (theForm == null) {
			theForm = new BaseLogForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String date = request.getParameter("time");
		String name = request.getParameter("code");
		String count = request.getParameter("count");

		TbSysOperationLogObj obj = new TbSysOperationLogObj();
		obj.setResoursePoolObject(name);
		obj.setCount(count);
		obj.setEnd_opertime(date);
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List resultList = this.operationLogService.queryOperationLogList(obj);
		theForm.setResultList(resultList);
		return "listOperLog";
	}
}
