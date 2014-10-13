package com.sitech.basd.sxcloud.cloud.web.globalconfig.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.web.globalconfig.form.GlobalConfigForm;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class GlobalConfigAction extends BaseAction {
	private GlobalConfigForm theForm;

	public GlobalConfigForm getTheForm() {
		return theForm;
	}

	public void setTheForm(GlobalConfigForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listGlobalConfig() throws BaseException {
		if (theForm == null) {
			theForm = new GlobalConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		// theForm.setID(null);
		TbGlobalConfigObj obj = new TbGlobalConfigObj();
		if (theForm.getID() != null && !"".equals(theForm.getID())) {
			obj.setID(theForm.getID().trim());
		}
		if (theForm.getKEY() != null && !"".equals(theForm.getKEY())) {
			obj.setKEY(theForm.getKEY().trim());
		}

		if (theForm.getCFG_DESC() != null && !"".equals(theForm.getCFG_DESC())) {
			obj.setCFG_DESC(theForm.getCFG_DESC().trim());
		}

		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List globalConfigList = tbGlobalConfigService.queryForListByObj(obj);
		theForm.setResultList(globalConfigList);
		return "list";

	}

	/**
	 * @Title:增加全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String addGlobalConfig() throws BaseException {
		theForm = new GlobalConfigForm();
		theForm.setFlag("0");
		return "add";
	}

	/**
	 * @Title:保存全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String saveGlobalConfig() throws BaseException {
		if (theForm == null) {
			theForm = new GlobalConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String key = request.getParameter("key");
		TbGlobalConfigObj obj = new TbGlobalConfigObj();
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		obj.setID(theForm.getID());
		obj.setKEY(key);
		obj.setVALUE(theForm.getVALUE());
		obj.setCFG_DESC(theForm.getCFG_DESC());
		int ret = 0;
		int result = 0;
		if (theForm.getID() == null || "".equals(theForm.getID())) { // flag是来自JS中的隐藏变量，判断是新增操作或修改操作
			ret = tbGlobalConfigService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增全局配置信息");
		} else {
			ret = tbGlobalConfigService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改全局配置信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "save";
	}

	/**
	 * @Title:修改全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String modGlobalConfig() throws BaseException {

		if (theForm == null) {
			theForm = new GlobalConfigForm();
		}
		TbGlobalConfigObj obj = new TbGlobalConfigObj();
		obj.setID(theForm.getID());
		TbGlobalConfigObj tempObj = tbGlobalConfigService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		theForm.setFlag("1");
		return "edit";
	}

	/**
	 * @Title:删除全局配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String delGlobalConfig() throws BaseException {
		if (theForm == null) {
			theForm = new GlobalConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbGlobalConfigObj obj = new TbGlobalConfigObj();
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ret = tbGlobalConfigService.deleteByObj(obj);
		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除全局配置信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "del";
	}

	/**
	 * 
	 * @Title: validateForm
	 * @Description: 验证表单关键字是否唯一
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Nov 23, 2012 9:53:19 AM
	 */
	public String validateForm() {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// PrintWriter out = null;
		StringBuffer sbf = new StringBuffer();
		// out = response.getWriter();
		String id = request.getParameter("id");
		String key = request.getParameter("key");
		if (key != null && key.length() != 0) {
			TbGlobalConfigObj obj = new TbGlobalConfigObj();
			obj.setID(id);
			obj.setKEY(key);
			int count = tbGlobalConfigService.checkKeyExists(obj);
			if (count > 0) {
				sbf.append(key + "已存在！请更改关键字 ");
			} else {
				sbf.append("");
			}
		} else {
			sbf.append("");
		}
		// out.print(sbf.toString());
		return null;
	}

	private TbGlobalConfigService tbGlobalConfigService;

	public void setTbGlobalConfigService(TbGlobalConfigService tbGlobalConfigService) {
		this.tbGlobalConfigService = tbGlobalConfigService;
	}

}
