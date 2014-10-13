package com.sitech.basd.yicloud.web.dictionary.action;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;
import com.sitech.basd.yicloud.service.dictionary.YiDataDictionaryService;
import com.sitech.basd.yicloud.web.dictionary.form.YiDataDictionaryForm;

public class YiDataDictionaryAction extends CRUDBaseAction {

	private YiDataDictionaryForm theForm;

	public YiDataDictionaryForm getTheForm() {
		return theForm;
	}

	public void setTheForm(YiDataDictionaryForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: 获取数据字典列表
	 * @Copyright: Copyright (c) 2012-4
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String listData() {
		if (theForm == null) {
			theForm = new YiDataDictionaryForm();
		}
		DictionaryObj obj = new DictionaryObj();
		if (theForm.getCODE() != null && !"".equals(theForm.getCODE())) {
			obj.setCODE(theForm.getCODE());
		}
		if (theForm.getNAME() != null && !"".equals(theForm.getNAME())) {
			obj.setNAME(theForm.getNAME());
		}
		if (theForm.getGROUP_NAME() != null
				&& !"".equals(theForm.getGROUP_NAME())) {
			obj.setGROUP_NAME(theForm.getGROUP_NAME());
		}
		if (theForm.getPARENT_ID() != 0) {
			obj.setPARENT_ID(theForm.getPARENT_ID());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List dataList = yiDataDictionaryService.queryForListByObj(obj);
		theForm.setResultList(dataList);
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: 添加信息
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String addData() {
		if (theForm == null) {
			theForm = new YiDataDictionaryForm();
		}
		DictionaryObj obj = new DictionaryObj();
		// form.reset(mapping, request);
		return ADD;
	}

	/**
	 * 
	 * @Title:保存信息
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String saveData() {
		if (theForm == null) {
			theForm = new YiDataDictionaryForm();
		}
		DictionaryObj obj = new DictionaryObj();
		obj.setID(theForm.getID());
		obj.setCODE(theForm.getCODE());
		obj.setNAME(theForm.getNAME());
		obj.setGROUP_NAME(theForm.getGROUP_NAME());
		obj.setPARENT_ID(theForm.getPARENT_ID());
		TbSysOperationLogObj operObj = this
				.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");
		int ret = 0;
		int result = 0;
		if (theForm.getFlag() == 0) {
			ret = yiDataDictionaryService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增功能信息");
		} else {
			ret = yiDataDictionaryService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改功能信息");
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
	 * 
	 * @Title:修改信息
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String modData() {
		if (theForm == null) {
			theForm = new YiDataDictionaryForm();
		}
		DictionaryObj obj = new DictionaryObj();
		obj.setID(theForm.getID());
		DictionaryObj yObj = yiDataDictionaryService.queryByObj(obj);
		theForm.setCODE(yObj.getCODE());
		theForm.setGROUP_NAME(yObj.getGROUP_NAME());
		theForm.setNAME(yObj.getNAME());
		theForm.setPARENT_ID(yObj.getPARENT_ID());
		return MODIFY;
	}

	/**
	 * 
	 * @Title:删除信息
	 * @Copyright: Copyright (c) 2012-2
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String delData() {
		if (theForm == null) {
			theForm = new YiDataDictionaryForm();
		}
		DictionaryObj obj = new DictionaryObj();
		obj.setID(theForm.getID());
		int ret = yiDataDictionaryService.deleteByObj(obj);
		return "del";
	}

	YiDataDictionaryService yiDataDictionaryService;

	public void setYiDataDictionaryService(
			YiDataDictionaryService yiDataDictionaryService) {
		this.yiDataDictionaryService = yiDataDictionaryService;
	}

}
