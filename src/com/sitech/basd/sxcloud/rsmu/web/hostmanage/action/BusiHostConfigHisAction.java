package com.sitech.basd.sxcloud.rsmu.web.hostmanage.action;

import java.lang.reflect.InvocationTargetException;
import com.sitech.basd.sxcloud.config.Constant;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigHisObj;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostConfigHisService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.hostmanage.form.BusiHostConfigHisForm;
import com.sitech.basd.sxcloud.rsmu.web.hostmanage.form.BusiHostForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class BusiHostConfigHisAction extends CRUDBaseAction {

	/**
	 * @Title:获取主机配置历史列表请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	*/
	public String listBusiHostConfigHis() throws BaseException {                                    
		if (theForm == null) {
			theForm = new BusiHostConfigHisForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		theForm.setID(0);
		TbBusiHostConfigHisObj obj = new TbBusiHostConfigHisObj();
		if(theForm.getUPDATEUSER()!=null&&!"".equals(theForm.getUPDATEUSER())){
			obj.setUPDATEUSER(theForm.getUPDATEUSER());
		}
        if(theForm.getUPDATETYPE()!=null&&!"".equals(theForm.getUPDATETYPE())){
			obj.setUPDATETYPE(theForm.getUPDATETYPE());
		}
        obj.setPagination(this.getPaginater().initPagination(request)) ;//分页
		List userInfoList = busiHostConfigHisService.queryForListByObj(obj);
		theForm.setResultList(userInfoList);
		return LIST;                                         
	}
	
	/**
	 * @Title:删除主机配置历史请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	*/
	public String delBusiHostConfigHis() throws BaseException {                                    
		TbBusiHostConfigHisObj obj = new TbBusiHostConfigHisObj();
		try {
			BeanUtils.copyProperties(obj,theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unused")
		int ret = busiHostConfigHisService.deleteByObj(obj) ;
		return "del";                                         
	} 
	
	private BusiHostConfigHisService  busiHostConfigHisService ;
	private BusiHostConfigHisForm theForm;

	public BusiHostConfigHisForm getTheForm() {
		return theForm;
	}

	public void setTheForm(BusiHostConfigHisForm theForm) {
		this.theForm = theForm;
	}

	public void setBusiHostConfigHisService(
			BusiHostConfigHisService busiHostConfigHisService) {
		this.busiHostConfigHisService = busiHostConfigHisService;
	}

}
