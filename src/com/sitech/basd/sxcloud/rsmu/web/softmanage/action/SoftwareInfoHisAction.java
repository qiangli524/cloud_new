package com.sitech.basd.sxcloud.rsmu.web.softmanage.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiSoftwareInfoHisObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.SoftwareInfohisService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.softmanage.form.SoftwareInfoHisForm;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.exception.BaseException;

public class SoftwareInfoHisAction extends BaseAction {
	private SoftwareInfoHisForm theForm;
	
	public SoftwareInfoHisForm getTheForm() {
		return theForm;
	}

	public void setTheForm(SoftwareInfoHisForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有软件历史信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	*/
	public String listSoftwareInfohis() throws BaseException {  
		if(theForm == null){
			theForm = new SoftwareInfoHisForm();
		}
		theForm.setID(0);
		TbBusiSoftwareInfoHisObj obj = new TbBusiSoftwareInfoHisObj();
		if(theForm.getNAME()!=null&&!"".equals(theForm.getNAME())){
			obj.setNAME(theForm.getNAME().trim());
		}
        if(theForm.getUPDATETYPE()!=null&&!"".equals(theForm.getUPDATETYPE())){
			obj.setUPDATETYPE(theForm.getUPDATETYPE());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest())) ;//分页
		List softwareInfoHisList = softwareInfohisService.queryForListByObj(obj);
		theForm.setResultList(softwareInfoHisList);
		return "listSoftwareInfohis";                                         
       
	}
	
	/**
	 * @Title:删除软件历史信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	*/
	public String delSoftwareInfohis() throws BaseException {                                    
		if(theForm == null){
			theForm = new SoftwareInfoHisForm();
		} 
		TbBusiSoftwareInfoHisObj obj = new TbBusiSoftwareInfoHisObj();		
		int result = 0 ;
		try {
			BeanUtils.copyProperties(obj,theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ret = softwareInfohisService.deleteByObj(obj) ;
		if(ret>0){
			result = 1 ;
		}
		
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest()) ;
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除软件历史"+obj.getID());
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);//写操作日志
		return "delSoftwareInfohis";                                         
	}
	
	
	private SoftwareInfohisService softwareInfohisService ;

	public void setSoftwareInfohisService(
			SoftwareInfohisService softwareInfohisService) {
		this.softwareInfohisService = softwareInfohisService;
	}

	
	
}
