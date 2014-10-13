package com.sitech.basd.yicloud.web.connect.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.yicloud.domain.connect.ConnectObj;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;
import com.sitech.basd.yicloud.service.connect.ConnectService;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.web.connect.form.ConnectForm;

public class ConnectAction extends CRUDBaseAction {
	private ConnectService connectService;
	
	private ConnectForm theForm;

	public ConnectForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ConnectForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:获取接口连通数据结果集
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 4:05:37 PM
	 * @version 1.0
	 */
	public String listConnect() throws BaseException {
		if (theForm == null) {
			theForm = new ConnectForm();
		}
		ConnectObj obj = new ConnectObj();
		if (theForm.getINTERFACE_A() != 0
				&& !"".equals(theForm.getINTERFACE_A())) {
			obj.setINTERFACE_A(theForm.getINTERFACE_A());
		}
		if (theForm.getINTERFACE_Z() != 0
				&& !"".equals(theForm.getINTERFACE_Z())) {
			obj.setINTERFACE_Z(theForm.getINTERFACE_Z());
		}
		if (!"0".equals(theForm.getSTATUS()) && !"".equals(theForm.getSTATUS())) {
			obj.setSTATUS(theForm.getSTATUS());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List connList = connectService.queryForListByObj(obj);
		theForm.setResultList(connList);
		ConnectObj connectObj = new ConnectObj();
		DictionaryObj dictionaryObj = new DictionaryObj();
		List interfaceList = connectService.queryForListByInterface(connectObj);
		List connectstatusList = connectService
				.queryForListByConStatus(dictionaryObj);
		theForm.setInterfaceList(interfaceList);
		theForm.setConnectstatusList(connectstatusList);
		return SUCCESS;
	}

	/**
	 * @Title:添加接口连通信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 4:07:45 PM
	 * @version 1.0
	 */
	public String addConnect() throws BaseException {
		if (theForm == null) {
			theForm = new ConnectForm();
		}
		ConnectObj connectObj = new ConnectObj();
		DictionaryObj dictionaryObj = new DictionaryObj();
		List interfaceList = connectService.queryForListByInterface(connectObj);
		List connectstatusList = connectService
				.queryForListByConStatus(dictionaryObj);
		theForm.setInterfaceList(interfaceList);
		theForm.setConnectstatusList(connectstatusList);
		return ADD;
	}

	/**
	 * @Title:保存接口连通信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 4:41:31 PM
	 * @version 1.0
	 */
	public String saveConnect() throws BaseException {
		if (theForm == null) {
			theForm = new ConnectForm();
		}
		ConnectObj connectObj = new ConnectObj();
		try {
			BeanUtils.copyProperties(connectObj, theForm);
			TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
			operObj.setREMARK("");
			int ret = 0;
			int result = 0;
			if (theForm.getFlag() == 0) {
				ret = connectService.insertByObj(connectObj);
				operObj.setOPERTYPE(1);
				operObj.setMESSAGE("新增功能信息");
			} else {
				ret = connectService.updateByObj(connectObj);
				operObj.setOPERTYPE(3);
				operObj.setMESSAGE("修改功能信息");
			}
			if (ret > 0) {
				result = 1;
			}
			operObj.setRESULT(result);
			@SuppressWarnings("unused")
			int retOper = operationService.insertByObj(operObj);// 写操作日志
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return REDIRECT;
	}

	/**
	 * @Title:修改接口连通信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 5:04:41 PM
	 * @version 1.0
	 */
	public String modConnect() throws BaseException {
		if (theForm == null) {
			theForm = new ConnectForm();
		}
		ConnectObj obj = new ConnectObj();
		obj.setID(theForm.getID());
		ConnectObj connectObj = new ConnectObj();
		DictionaryObj dictionaryObj = new DictionaryObj();
		List interfaceList = connectService.queryForListByInterface(connectObj);
		List connectstatusList = connectService
				.queryForListByConStatus(dictionaryObj);
		theForm.setInterfaceList(interfaceList);
		theForm.setConnectstatusList(connectstatusList);
		obj = connectService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return MODIFY;
	}

	/**
	 * @Title:删除接口连通信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 19, 2012 2:26:39 PM
	 * @version 1.0
	 */
	public String delConnect() throws BaseException {
		if (theForm == null) {
			theForm = new ConnectForm();
		}
		ConnectObj connectObj = new ConnectObj();
		connectObj.setID(theForm.getID());
		
		int result = connectService.deleteByObj(connectObj);
		return REDIRECT;
	}

	public void setConnectService(ConnectService connectService) {
		this.connectService = connectService;
	}

}
