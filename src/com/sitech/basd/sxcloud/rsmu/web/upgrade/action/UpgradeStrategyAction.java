package com.sitech.basd.sxcloud.rsmu.web.upgrade.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeStrategyObj;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.service.upgrade.UpgradeStrategyService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.upgrade.form.UpgradeStrategyForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class UpgradeStrategyAction extends CRUDBaseAction {

	/**
	 * @Title:查询出所有升级策略信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String upgradeStrategyList() throws BaseException {

		UpgradeStrategyObj obj = new UpgradeStrategyObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		if (theForm == null) {
			theForm = new UpgradeStrategyForm();
		}
		if (theForm.getHOSTNAME() != null && !"".equals(theForm.getHOSTNAME())) {
			obj.setHOSTNAME(theForm.getHOSTNAME());
		}
		if (theForm.getSTRATEGYNAME() != null
				&& !"".equals(theForm.getSTRATEGYNAME())) {
			obj.setSTRATEGYNAME(theForm.getSTRATEGYNAME());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List list = upgradeStrategyService.queryForListByObj(obj);
		theForm.setResultList(list);
		return LIST;
	}

	/**
	 * @Title:新增升级策略信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String addUpgradeStrategy() throws BaseException {

		TbBusiHostObj obj = new TbBusiHostObj();
		List hostList = busiHostService.queryForListByObj(obj);

		theForm.setHostList(hostList);
		return ADD;
	}

	/**
	 * @Title:保存升级策略信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String sureAddUpgradeStrategy() throws BaseException {

		UpgradeStrategyObj obj = new UpgradeStrategyObj();
		obj.setSTRATEGYNAME(theForm.getSTRATEGYNAME());
		obj.setTYPE(theForm.getTYPE());
		obj.setSTRATEGY(theForm.getSTRATEGY());
		obj.setFREQUENCY(theForm.getFREQUENCY());
		obj.setBASEPATH(theForm.getBASEPATH());
		upgradeStrategyService.insertByObj(obj);
		return "save_add";
	}

	/**
	 * @Title:修改升级策略信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String modUpgradeStrategy() throws BaseException {

		UpgradeStrategyObj obj = new UpgradeStrategyObj();
		TbBusiHostObj obj1 = new TbBusiHostObj();
		List hostList = busiHostService.queryForListByObj(obj1);
		obj.setID(theForm.getID());
		UpgradeStrategyObj objTemp = upgradeStrategyService.queryByObj(obj);
		theForm.setID(objTemp.getID());
		theForm.setSTRATEGYNAME(objTemp.getSTRATEGYNAME());
		theForm.setTYPE(objTemp.getTYPE());
		theForm.setFREQUENCY(objTemp.getFREQUENCY());
		theForm.setSTRATEGY(objTemp.getSTRATEGY());
		theForm.setBASEPATH(objTemp.getBASEPATH());
		theForm.setHostList(hostList);
		return MODIFY;
	}

	/**
	 * @Title:保存修改策略信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String sureModUpgradeStrategy() throws BaseException {

		UpgradeStrategyObj obj = new UpgradeStrategyObj();
		obj.setID(theForm.getID());
		obj.setSTRATEGYNAME(theForm.getSTRATEGYNAME());
		obj.setTYPE(theForm.getTYPE());
		obj.setSTRATEGY(theForm.getSTRATEGY());
		obj.setFREQUENCY(theForm.getFREQUENCY());
		obj.setBASEPATH(theForm.getBASEPATH());
		upgradeStrategyService.updateByObj(obj);
		return "save_mod";
	}

	/**
	 * @Title:删除升级策略信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String delUpgradeStrategy() throws BaseException {

		UpgradeStrategyObj obj = new UpgradeStrategyObj();
		obj.setID(theForm.getID());
		upgradeStrategyService.deleteByObj(obj);
		return "del";
	}

	UpgradeStrategyService upgradeStrategyService;
	private UpgradeStrategyForm theForm;

	public UpgradeStrategyForm getTheForm() {
		return theForm;
	}

	public void setTheForm(UpgradeStrategyForm theForm) {
		this.theForm = theForm;
	}

	public void setUpgradeStrategyService(
			UpgradeStrategyService upgradeStrategyService) {
		this.upgradeStrategyService = upgradeStrategyService;
	}

	BusiHostService busiHostService;

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}
}
