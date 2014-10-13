package com.sitech.basd.yicloud.web.ipinfo.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;
import com.sitech.basd.yicloud.domain.ipinfo.IpinfoObj;
import com.sitech.basd.yicloud.service.ipinfo.IpinfoService;
import com.sitech.basd.yicloud.web.ipinfo.form.IpinfoForm;

public class IpinfoAction extends CRUDBaseAction {
	IpinfoService ipinfoService;

	private IpinfoForm theForm;

	public IpinfoForm getTheForm() {
		return theForm;
	}

	public void setTheForm(IpinfoForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询IP池数据结果集
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-7
	 * @version 1.0
	 */
	public String listIpinfo() throws BaseException {
		if (theForm == null) {
			theForm = new IpinfoForm();
		}
		IpinfoObj obj = new IpinfoObj();
		if (theForm.getISUSED() != "0" && !"0".equals(theForm.getISUSED())) {
			obj.setISUSED(theForm.getISUSED());
		}
		if (theForm.getISBLOCKED() != "0"
				&& !"0".equals(theForm.getISBLOCKED())) {
			obj.setISBLOCKED(theForm.getISBLOCKED());
		}
		if (theForm.getIPADDRESS() != null
				&& !theForm.getIPADDRESS().equals("")) {
			obj.setIPADDRESS(theForm.getIPADDRESS());
		}
		if (theForm.getIP_TYPE() != "0" && !"0".equals(theForm.getIP_TYPE())) {
			obj.setIP_TYPE(theForm.getIP_TYPE());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		DictionaryObj dictionaryObj = new DictionaryObj();
		List usedList = ipinfoService.queryForListByUsed(dictionaryObj);
		List blockList = ipinfoService.queryForListByBlocked(dictionaryObj);
		List typeList = ipinfoService.queryForListByType(dictionaryObj);
		theForm.setUsedList(usedList);
		theForm.setBlockList(blockList);
		theForm.setTypeList(typeList);
		List ipinfoList = ipinfoService.queryForListByObj(obj);
		theForm.setResultList(ipinfoList);
		return SUCCESS;
	}

	/**
	 * @Title:添加IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-8
	 * @version 1.0
	 */
	public String addIpinfo() throws BaseException {
		if (theForm == null) {
			theForm = new IpinfoForm();
		}
		DictionaryObj dictionaryObj = new DictionaryObj();
		List usedList = ipinfoService.queryForListByUsed(dictionaryObj);
		List blockList = ipinfoService.queryForListByBlocked(dictionaryObj);
		List typeList = ipinfoService.queryForListByType(dictionaryObj);
		theForm.setUsedList(usedList);
		theForm.setBlockList(blockList);
		theForm.setTypeList(typeList);
		return ADD;

	}

	/**
	 * @Title:修改IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-8
	 * @version 1.0
	 */
	public String modIpinfo() throws BaseException {
		if (theForm == null) {
			theForm = new IpinfoForm();
		}
		IpinfoObj ipinfoObj = new IpinfoObj();
		ipinfoObj.setIP_ID(theForm.getIP_ID());
		IpinfoObj obj = ipinfoService.queryByObj(ipinfoObj);
		ipinfoObj.setISBLOCKED(obj.getISBLOCKED());
		ipinfoObj.setISUSED(obj.getISUSED());
		ipinfoObj.setIP_TYPE(obj.getIP_TYPE());
		ipinfoObj.setINS_DATE(obj.getINS_DATE());
		ipinfoObj.setIPADDRESS(obj.getIPADDRESS());
		try {
			BeanUtils.copyProperties(theForm, obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		DictionaryObj dictionaryObj = new DictionaryObj();
		List usedList = ipinfoService.queryForListByUsed(dictionaryObj);
		List blockList = ipinfoService.queryForListByBlocked(dictionaryObj);
		List typeList = ipinfoService.queryForListByType(dictionaryObj);
		theForm.setUsedList(usedList);
		theForm.setBlockList(blockList);
		theForm.setTypeList(typeList);
		return MODIFY;

	}

	/**
	 * @Title:保存IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-12
	 * @version 1.0
	 */
	public String saveIpinfo() throws BaseException {
		if (theForm == null) {
			theForm = new IpinfoForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		IpinfoObj obj = new IpinfoObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");
		int ret = 0;
		int result = 0;
		if (theForm.getFlag() == 0) {
			ret = ipinfoService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增功能信息");
		} else {
			ret = ipinfoService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改功能信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return REDIRECT;
	}

	/**
	 * @Title:删除IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-12
	 * @version 1.0
	 */

	public String delIpinfo() throws BaseException {
		if (theForm == null) {
			theForm = new IpinfoForm();
		}
		IpinfoObj ipinfoObj = new IpinfoObj();
		ipinfoObj.setIP_ID(theForm.getIP_ID());
		IpinfoObj obj = ipinfoService.queryByObj(ipinfoObj);
		ipinfoObj.setISBLOCKED(obj.getISBLOCKED());
		ipinfoObj.setISUSED(obj.getISUSED());
		ipinfoObj.setIP_TYPE(obj.getIP_TYPE());
		ipinfoObj.setINS_DATE(obj.getINS_DATE());
		ipinfoObj.setIPADDRESS(obj.getIPADDRESS());
		try {
			BeanUtils.copyProperties(theForm, obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int result = ipinfoService.deleteByObj(obj);
		return REDIRECT;
	}

	public void setIpinfoService(IpinfoService ipinfoService) {
		this.ipinfoService = ipinfoService;
	}

}
