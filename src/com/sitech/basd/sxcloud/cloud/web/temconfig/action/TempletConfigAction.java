package com.sitech.basd.sxcloud.cloud.web.temconfig.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.temconfig.TempletConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.temconfig.TempletTypeConfigObj;
import com.sitech.basd.sxcloud.cloud.service.temconfig.TempletConfigServiceImpl;
import com.sitech.basd.sxcloud.cloud.web.temconfig.form.TempletConfigForm;
import com.sitech.basd.sxcloud.cloud.web.temconfig.form.TempletConfigConForm;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.Struts2Utils;

@SuppressWarnings("serial")
public class TempletConfigAction extends CRUDBaseAction {
	private TempletConfigForm theForm;
	@SuppressWarnings("unused")
	private TempletConfigConForm theConForm;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TempletConfigForm getTheForm() {
		return theForm;
	}

	public void setTheForm(TempletConfigForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:显示服务信息项
	 * @Copyright: Copyright (c) 2012-08-27
	 * @Company: si-tech
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listTempletConfig() {
		if (theConForm == null) {
			theConForm = new TempletConfigConForm();
		}
		if (theForm == null) {
			theForm = new TempletConfigForm();
		}
		TempletConfigObj obj = new TempletConfigObj();
		if (theForm.getTYPE() != null && !"".equals(theForm.getTYPE())) {
			obj.setTYPE(theForm.getTYPE());
		} else {
			obj.setTYPE(Struts2Utils.getRequest().getParameter("TYPE"));
		}
		// if(obj.getTYPE() !=null && !"".equals(obj.getTYPE())){
		// obj.setTYPE(type)
		// }
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List templetConfigShowList = templetConfigService
				.queryForListByConObj(obj);
		theConForm.setResultList(templetConfigShowList);
		theConForm.setTYPE(obj.getTYPE());
		return "listTempletTypeConfig";
	}

	/**
	 * @Title:显示服务类型信息
	 * @Copyright: Copyright (c) 2012-08-27
	 * @Company: si-tech
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listTempletTypeConfig() {
		if (theForm == null) {
			theForm = new TempletConfigForm();
		}
		TempletTypeConfigObj obj = new TempletTypeConfigObj();
		if (theForm.getTYPE_NAME() != null
				&& !"".equals(theForm.getTYPE_NAME())) {
			obj.setTYPE_NAME(theForm.getTYPE_NAME());
		}
		if (theForm.getTYPE() != null && !"".equals(theForm.getTYPE())) {
			obj.setTYPE(theForm.getTYPE());
		}

		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List templetConfigList = templetConfigService.queryForListByObj(obj);
		theForm.setResultList(templetConfigList);
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: 服务编号唯一性判断
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String uniqueJudgement() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String TYPE = request.getParameter("TYPE");

		TempletTypeConfigObj obj = new TempletTypeConfigObj();

		obj.setTYPE(TYPE);
		this.paginater.initPagination(request);
		List<?> list = templetConfigService.queryForListByObj(obj);
		List<String> jsonArr = new ArrayList<String>();
		JSONArray json = new JSONArray();
		if (null != list && !list.isEmpty()) {
			jsonArr.add("NO");
		} else {
			jsonArr.add("YES");
		}
		try {
			json = JSONArray.fromObject(jsonArr);
			Struts2Utils.getResponse().getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
			LogHelper.debug("TempletConfigAction.uniqueJudgement():"
					+ e.getMessage() + getClass().getName());
		}
		return null;
	}

	/**
	 * 
	 * @Title: 添加新的服务类型
	 * @Copyright: Copyright (c) 2012-8-29
	 * @Company: si-tech
	 * @version 1.0
	 */
	public String addTempletTypeConfig() {
		return "add";
	}

	/**
	 * 
	 * @Title: 修改服务的类型
	 * @Copyright: Copyright (c) 2012-8-29
	 * @Company: si-tech
	 * @version 1.0
	 */
	public String modTempletTypeConfig() {
		if (theForm == null) {
			theForm = new TempletConfigForm();
		}
		String type = request.getParameter("TYPE");
		theForm.setTYPE(type);
		TempletTypeConfigObj obj = new TempletTypeConfigObj();
		obj.setTYPE(theForm.getTYPE());
		TempletTypeConfigObj tempObj = templetConfigService.queryByObj(obj);

		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		theForm.setFlag(1);
		return "modTempletTypeConfig";
	}

	/**
	 * 
	 * @Title: 保存新的服务信息
	 * @Copyright: Copyright (c) 2012-8-29
	 * @Company: si-tech
	 * @version 1.0
	 */
	public String saveTempletType() {
		if (theForm == null) {
			theForm = new TempletConfigForm();
		}
		TempletTypeConfigObj obj = new TempletTypeConfigObj();
		theForm.setFlag(theForm.getFlag());
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int ret = 0;
		int result = 0;
		if (theForm.getFlag() == 0) {
			ret = templetConfigService.insertByObj(obj);
		} else {
			ret = templetConfigService.updateByObj(obj);
		}
		if (ret > 0) {
			result = 1;
		}
		return "saveTempletType";
	}

	/**
	 * 
	 * @Title: 删除服务信息
	 * @Copyright: Copyright (c) 2012-8-29
	 * @Company: si-tech
	 * @version 1.0
	 */
	public String delTempletTypeConfig() {
		if (theForm == null) {
			theForm = new TempletConfigForm();
		}
		String type = request.getParameter("TYPE");
		theForm.setTYPE(type);
		TempletTypeConfigObj obj = new TempletTypeConfigObj();
		TempletConfigObj conObj = new TempletConfigObj();
		obj.setTYPE(theForm.getTYPE());
		conObj.setTYPE(theForm.getTYPE());
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int ret = templetConfigService.deleteByObj(obj);
		int conRet = templetConfigService.deleteConObj(conObj);
		if (ret > 0 && conRet > 0) {
			result = 1;
		}
		return "delTempletTypeConfig";
	}

	/**
	 * 
	 * @Title: 增加服务信息项
	 * @Copyright: Copyright (c) 2012-8-29
	 * @Company: si-tech
	 * @version 1.0
	 */
	public String addTempletConfig() {
		if (theConForm == null) {
			theConForm = new TempletConfigConForm();
		}
		// TempletConfigObj obj = new TempletConfigObj();

		return "addTempletConfig";
	}

	/**
	 * 
	 * @Title: 保存服务信息项
	 * @Copyright: Copyright (c) 2012-8-29
	 * @Company: si-tech
	 * @version 1.0
	 */
	public String saveTempletConfig() {
		if (theConForm == null) {
			theConForm = new TempletConfigConForm();
		}
		TempletConfigObj obj = new TempletConfigObj();
		obj.setFlag(theConForm.getFlag());
		obj.setID(theConForm.getID());
		try {
			BeanUtils.copyProperties(obj, theConForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int ret = 0;
		int result = 0;
		if (theConForm.getFlag() == 0) {
			ret = templetConfigService.insertByConObj(obj);
		} else {
			ret = templetConfigService.updateByConObj(obj);
		}
		if (ret > 0) {
			result = 1;
		}
		this.setType(theConForm.getTYPE());
		return "saveTempletConfig";
	}

	/**
	 * 
	 * @Title: 修改服务信息项
	 * @Copyright: Copyright (c) 2012-8-29
	 * @Company: si-tech
	 * @version 1.0
	 */
	public String modTempletConfig() {
		if (theConForm == null) {
			theConForm = new TempletConfigConForm();
		}
		TempletConfigObj obj = new TempletConfigObj();
		obj.setID(theConForm.getID());
		obj.setTYPE(theConForm.getTYPE());
		TempletConfigObj tempObj = templetConfigService.queryByConObj(obj);

		try {
			BeanUtils.copyProperties(theConForm, tempObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		theConForm.setFlag(1);
		return "modTempletConfig";
	}

	/**
	 * 
	 * @Title: 删除服务信息项
	 * @Copyright: Copyright (c) 2012-8-29
	 * @Company: si-tech
	 * @version 1.0
	 */
	public String delTempletConfig() {
		if (theConForm == null) {
			theConForm = new TempletConfigConForm();
		}
		TempletConfigObj obj = new TempletConfigObj();
		obj.setID(theConForm.getID());
		obj.setTYPE(theConForm.getTYPE());
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theConForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int ret = templetConfigService.deleteConObj(obj);
		if (ret > 0) {
			result = 1;
		}
		this.setType(theConForm.getTYPE());
		return "delTempletConfig";
	}

	private TempletConfigServiceImpl templetConfigService;

	public void setTempletConfigService(
			TempletConfigServiceImpl templetConfigService) {
		this.templetConfigService = templetConfigService;
	}

	public TempletConfigConForm getTheConForm() {
		return theConForm;
	}

	public void setTheConForm(TempletConfigConForm theConForm) {
		this.theConForm = theConForm;
	}

}
