package com.sitech.basd.sxcloud.cloud.web.defend.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.defend.DefendObj;
import com.sitech.basd.sxcloud.cloud.service.defend.DefendService;
import com.sitech.basd.sxcloud.cloud.web.defend.form.DefendForm;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class DefendAction extends BaseAction {
	private DefendForm theForm;
	
	public DefendForm getTheForm() {
		return theForm;
	}

	public void setTheForm(DefendForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listDefend()
			throws BaseException {
		if(theForm ==null){
			theForm = new DefendForm();
		}
		DefendObj obj = new DefendObj();

		if (theForm.getID() != 0 && !"".equals(theForm.getID())) {
			obj.setID(theForm.getID());
		}
		if (theForm.getDEFEND_DIR() != null
				&& !"".equals(theForm.getDEFEND_DIR())) {
			obj.setDEFEND_DIR(theForm.getDEFEND_DIR().trim());
		}
		if (theForm.getENABLE() != -1) {
			obj.setENABLE(theForm.getENABLE());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List defendList = defendService.queryForListByObj(obj);
		theForm.setResultList(defendList);
		TbBusiHostObj tbBusiHostObj = new TbBusiHostObj();
		List hpList = defendService.queryForListByTbBusiHostObj(tbBusiHostObj);
		theForm.setHphostList(hpList);
		return "list";

	}

	/**
	 * @Title:增加防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String addDefend()
			throws BaseException {
		if(theForm ==null){
			theForm = new DefendForm();
		}
		DefendObj obj = new DefendObj();
		List hpList = defendService.queryDefendIp(obj);
		theForm.setHphostList(hpList);
		return "add";
	}

	/**
	 * @Title:保存防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String saveDefend()
			throws BaseException {
		if(theForm ==null){
			theForm = new DefendForm();
		}
		DefendObj obj = new DefendObj();
		// TbBusiHostObj tbObj = new TbBusiHostObj();
		String mac = theForm.getPLATFORM_ID();
		obj.setPLATFORM_ID(mac);
		List<DefendObj> list = defendService.queryDefendIp(obj);
		DefendObj tempObj = new DefendObj();
		if (list != null && list.size() == 1) {
			tempObj = list.get(0);
			// tbObj.setIP(theForm.getIP());
			// List<TbBusiHostObj> idList = busiHostService.queryIDByIP(tbObj);
			// if (idList != null && idList.size() == 1) {
			// tbObj = idList.get(0);
			// }
		}

		theForm.setFlag(theForm.getFlag());
		try {
			BeanUtils.copyProperties(obj, theForm);
			if (mac != null && !"".equals(mac)) {
				obj.setHOST_MAC(mac.trim());
			} else {
				obj.setHOST_MAC(tempObj.getMAC());
			}
			obj.setHOST_ID(Integer.toString(tempObj.getID()));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");

		int ret = 0;
		int result = 0;
		if (theForm.getFlag() == 0) { // flag是来自JS中的隐藏变量，判断是新增操作或修改操作
			ret = defendService.insertByObj(obj);
			obj.setTYPE(1);
			defendService.insertHisByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增防篡改信息");
		} else {
			ret = defendService.updateByObj(obj);
			obj.setTYPE(2);
			defendService.insertHisByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改防篡改信息");
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
	 * @Title:修改防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String modDefend()
			throws BaseException {
		if(theForm ==null){
			theForm = new DefendForm();
		}
		DefendObj obj = new DefendObj();
		obj.setDEFEND_ID(theForm.getDEFEND_ID());
		DefendObj tempObj = defendService.queryByObj(obj);
		TbBusiHostObj tbBusiHostObj = new TbBusiHostObj();
		tbBusiHostObj.setID(tempObj.getID());
		tbBusiHostObj = busiHostService.queryByObj(tbBusiHostObj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
			theForm.setIP(tbBusiHostObj.getIP());
			theForm.setHOSTNAME(tbBusiHostObj.getHOSTNAME());
			theForm.setFlag(1);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "mod";
	}

	/**
	 * @Title:删除防篡改信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String delDefend()
			throws BaseException {
		if(theForm ==null){
			theForm = new DefendForm();
		}
		DefendObj obj = new DefendObj();
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		obj.setTYPE(3);
		defendService.insertHisByObj(obj);
		int ret = defendService.deleteByObj(obj);
		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除防篡改信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "del";
	}

	/**
	 * @Title:查询出所有防篡改历史
	 * @Copyright: Copyright (c) 201203
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listHisDefend()
			throws BaseException {
		if(theForm ==null){
			theForm = new DefendForm();
		}
		DefendObj obj = new DefendObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		if (theForm.getTYPE() != 0) {
			obj.setTYPE(theForm.getTYPE());
		}
		if (theForm.getDEFEND_DIR() != null
				&& !"".equals(theForm.getDEFEND_DIR())) {
			obj.setDEFEND_DIR(theForm.getDEFEND_DIR());
		}
		List defendList = defendService.queryHisListByObj(obj);
		theForm.setResultList(defendList);
		return "listHis";

	}

	private DefendService defendService;
	private BusiHostService busiHostService;

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public void setDefendService(DefendService defendService) {
		this.defendService = defendService;
	}

}
