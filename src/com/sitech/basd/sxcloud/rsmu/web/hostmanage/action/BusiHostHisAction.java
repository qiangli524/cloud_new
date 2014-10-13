package com.sitech.basd.sxcloud.rsmu.web.hostmanage.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostHisObj;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostHisService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.hostmanage.form.BusiHostHisForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class BusiHostHisAction extends CRUDBaseAction {

	/**
	 * @Title:获取主机历史列表请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String listBusiHostHis() throws BaseException {
		if (theForm == null) {
			theForm = new BusiHostHisForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		theForm.setID(0);
		TbBusiHostHisObj obj = new TbBusiHostHisObj();
		if (theForm.getHOSTNAME() != null && !"".equals(theForm.getHOSTNAME())) {
			obj.setHOSTNAME(theForm.getHOSTNAME());
		}
		if (theForm.getSTATUS() != null && !"".equals(theForm.getSTATUS())) {
			obj.setSTATUS(theForm.getSTATUS());
		}
		if (theForm.getUPDATETYPE() != null
				&& !"".equals(theForm.getUPDATETYPE())) {
			obj.setUPDATETYPE(theForm.getUPDATETYPE());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List userInfoList = busiHostHisService.queryForListByObj(obj);
		theForm.setResultList(userInfoList);
		return LIST;
	}

	/**
	 * @Title:删除主机历史请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String delBusiHostHis() throws BaseException {
		TbBusiHostHisObj obj = new TbBusiHostHisObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unused")
		int ret = busiHostHisService.deleteByObj(obj);
		return "del";
	}

	private BusiHostHisService busiHostHisService;
	private BusiHostHisForm theForm;

	public BusiHostHisForm getTheForm() {
		return theForm;
	}

	public void setTheForm(BusiHostHisForm theForm) {
		this.theForm = theForm;
	}

	public void setBusiHostHisService(BusiHostHisService busiHostHisService) {
		this.busiHostHisService = busiHostHisService;
	}

}
