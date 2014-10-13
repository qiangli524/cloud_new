package com.sitech.basd.sxcloud.cloud.web.exportconfig.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.exportconfig.ExportConfigObj;
import com.sitech.basd.sxcloud.cloud.service.exportconfig.ExportConfigService;
import com.sitech.basd.sxcloud.cloud.web.exportconfig.form.ExportConfigForm;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

public class ExportConfigAction extends BaseAction {
	private ExportConfigForm theForm;
	private ExportConfigService exportConfigService;

	public ExportConfigForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ExportConfigForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listExportConfig() throws BaseException {
		if (theForm == null) {
			theForm = new ExportConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		// theForm.setID(null);
		ExportConfigObj obj = new ExportConfigObj();
		if (theForm.getKEY() != null && !"".equals(theForm.getKEY())) {
			obj.setKEY(theForm.getKEY().trim());
		}

		if (theForm.getCFG_DESC() != null && !"".equals(theForm.getCFG_DESC())) {
			obj.setCFG_DESC(theForm.getCFG_DESC().trim());
		}
		if (theForm.getTYPE() > 0) {
			obj.setTYPE(theForm.getTYPE());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List exportConfigList = exportConfigService.queryForListByObj(obj);
		theForm.setResultList(exportConfigList);
		return "list";

	}

	/**
	 * @Title:增加导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String addExportConfig() throws BaseException {
		theForm = new ExportConfigForm();
		theForm.setFlag("0");
		return "add";
	}

	/**
	 * @Title:保存导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String saveExportConfig() throws BaseException {
		if (theForm == null) {
			theForm = new ExportConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		ExportConfigObj obj = new ExportConfigObj();
		obj.setID(theForm.getID());
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		obj.setID(theForm.getID());
		int ret = 0;
		int result = 0;
		if (theForm.getID() == null || "".equals(theForm.getID())) { // flag是来自JS中的隐藏变量，判断是新增操作或修改操作
			ret = exportConfigService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增导出配置信息");
		} else {
			ret = exportConfigService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改导出配置信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		JSONObject js = JSONObject.fromObject(ret);
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(js);
		// out.close();
		PrintWriterOut.printWirter(response, js);
		return null;
	}

	/**
	 * @Title:修改导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String modExportConfig() throws BaseException {

		if (theForm == null) {
			theForm = new ExportConfigForm();
		}
		ExportConfigObj obj = new ExportConfigObj();
		obj.setID(theForm.getID());
		ExportConfigObj tempObj = exportConfigService.queryByObj(obj);
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
	 * @Title:删除导出配置信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String delExportConfig() throws BaseException {
		if (theForm == null) {
			theForm = new ExportConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		ExportConfigObj obj = new ExportConfigObj();
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
		int ret = exportConfigService.deleteByObj(obj);
		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除导出配置信息");
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
		int type = Integer.parseInt(request.getParameter("type"));
		if (key != null && key.length() != 0) {
			ExportConfigObj obj = new ExportConfigObj();
			obj.setID(id);
			obj.setKEY(key);
			obj.setTYPE(type);
			int count = exportConfigService.queryForObjByObj(obj);
			if (count > 0) {
				sbf.append(key + "已存在！请更改关键字 ");
			} else {
				sbf.append("");
			}
		} else {
			sbf.append("");
		}
		// out.print(sbf.toString());
		PrintWriterOut.printWirter(response, sbf.toString());
		return null;
	}

	public ExportConfigService getExportConfigService() {
		return exportConfigService;
	}

	public void setExportConfigService(ExportConfigService exportConfigService) {
		this.exportConfigService = exportConfigService;
	}
}
