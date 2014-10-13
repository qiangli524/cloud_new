package com.sitech.basd.envmanager.web.ledger.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.envmanager.domain.ledger.LedgerObj;
import com.sitech.basd.envmanager.service.ledger.LedgerService;
import com.sitech.basd.envmanager.web.ledger.form.LedgerForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class LedgerAction extends BaseAction{
	
	private LedgerForm theForm;
	
	LedgerService ledgerService;
	
	
	
	/**
	 * @Title:查询所有设备信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String queryLedgerList(){
		
		if(theForm==null){
			theForm = new LedgerForm();
		}
		LedgerObj obj = new LedgerObj();
		if(theForm.getMECH_ROOM()!=null&&!"".equals(theForm.getMECH_ROOM())){
			obj.setMECH_ROOM(theForm.getMECH_ROOM().trim());
		}
		if(theForm.getCAPITAL_TYPE()!=null&&!"".equals(theForm.getCAPITAL_TYPE()))
		{
			obj.setCAPITAL_TYPE(theForm.getCAPITAL_TYPE().trim());
		}
		if(theForm.getMECH_ID()!=null&&!"".equals(theForm.getMECH_ID())){
			obj.setMECH_ID(theForm.getMECH_ID().trim());
		}
		if(theForm.getCAPITAL_ID()!=null&&!"".equals(theForm.getCAPITAL_ID())){
			obj.setCAPITAL_ID(theForm.getCAPITAL_ID().trim());
		}
		
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List ledgerList = ledgerService.queryLedgerObj(obj);
		theForm.setLedger_List(ledgerList);
		return "success";
	}
	/**
	 * @Title:进入增加页面
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String deleteLedgerObj(){
		if(theForm==null){
			theForm = new LedgerForm();
		}
		int result = 0;
		LedgerObj obj = new LedgerObj();
		obj.setLD_ID(theForm.getLD_ID());
		result = ledgerService.deleteLedgerObj(obj);
		return "del";
	} 
	/**
	 * @Title:进入增加页面
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String insertLedgerObj(){
		
		return "add";
	}
	public String updateLedgerObj(){
		if(theForm==null){
			theForm = new LedgerForm();
		}
		LedgerObj obj = new LedgerObj();
		obj.setLD_ID(theForm.getLD_ID());
		LedgerObj lobj=ledgerService.queryLedgerOne(obj);
		theForm.setLD_ID(lobj.getLD_ID());
		theForm.setMECH_ROOM(lobj.getMECH_ROOM());
		theForm.setCAPITAL_TYPE(lobj.getCAPITAL_TYPE());
		theForm.setMECH_ID(lobj.getMECH_ID());
		theForm.setCAPITAL_ID(lobj.getCAPITAL_ID());
		theForm.setMECH_TYPE(lobj.getMECH_TYPE());
		theForm.setMECH_CONF(lobj.getMECH_CONF());
		theForm.setSYS_SYSTEM(lobj.getSYS_SYSTEM());
		theForm.setSYS_HOSTNAME(lobj.getSYS_HOSTNAME());
		theForm.setSYS_VM(lobj.getSYS_VM());
		theForm.setIP_PHYSICS(lobj.getIP_PHYSICS());
		
		theForm.setIP_VIRTUAL(lobj.getIP_VIRTUAL());
		theForm.setIP_ILO(lobj.getIP_ILO());
		theForm.setPWD_SYSTEM(lobj.getPWD_SYSTEM());
		theForm.setPWD_CONSOLE(lobj.getPWD_CONSOLE());
		theForm.setSTORE(lobj.getSTORE());
		theForm.setUSE_DOMAN(lobj.getUSE_DOMAN());
		theForm.setUSE_RES(lobj.getUSE_RES());
		theForm.setUSE_DEPART(lobj.getUSE_DEPART());
		theForm.setUSE_DESCRIP(lobj.getUSE_DESCRIP());
		theForm.setMANAG_PERSON(lobj.getMANAG_PERSON());
		theForm.setMANAG_RECORD(lobj.getMANAG_RECORD());
		theForm.setMANAG_REPAIR(lobj.getMANAG_REPAIR());
		theForm.setMANAG_USABLE(lobj.getMANAG_USABLE());
		theForm.setMANAG_USE(lobj.getMANAG_USE());
		theForm.setMANAG_DESCRIP(lobj.getMANAG_DESCRIP());
		theForm.setFlag(1);
		return "update";
	}
	public String saveLedgerObj(){
		if(theForm==null){
			theForm = new LedgerForm();
		}
		int flag = theForm.getFlag();
		int id = theForm.getLD_ID();
		HttpServletRequest request = Struts2Utils.getRequest();
		String test_id = request.getParameter("id");
		LedgerObj obj = new LedgerObj();
		if(flag==1){
			obj.setLD_ID(Integer.parseInt(test_id));
		}
		obj.setMECH_ROOM(theForm.getMECH_ROOM());
		obj.setCAPITAL_TYPE(theForm.getCAPITAL_TYPE());
		obj.setMECH_ID(theForm.getCAPITAL_TYPE());
		obj.setCAPITAL_ID(theForm.getCAPITAL_ID());
		obj.setMECH_TYPE(theForm.getMECH_TYPE());
		obj.setMECH_CONF(theForm.getMECH_CONF());
		obj.setSYS_SYSTEM(theForm.getSYS_SYSTEM());
		obj.setSYS_HOSTNAME(theForm.getSYS_HOSTNAME());
		obj.setSYS_VM(theForm.getSYS_VM());
		obj.setIP_PHYSICS(theForm.getIP_PHYSICS());
		
		obj.setIP_VIRTUAL(theForm.getIP_VIRTUAL());
		obj.setIP_ILO(theForm.getIP_ILO());
		obj.setPWD_SYSTEM(theForm.getPWD_SYSTEM());
		obj.setPWD_CONSOLE(theForm.getPWD_CONSOLE());
		obj.setSTORE(theForm.getSTORE());
		obj.setUSE_DOMAN(theForm.getUSE_DOMAN());
		obj.setUSE_RES(theForm.getUSE_RES());
		obj.setUSE_DEPART(theForm.getUSE_DEPART());
		obj.setUSE_DESCRIP(theForm.getUSE_DESCRIP());
		obj.setMANAG_PERSON(theForm.getMANAG_PERSON());
		obj.setMANAG_RECORD(theForm.getMANAG_RECORD());
		obj.setMANAG_REPAIR(theForm.getMANAG_REPAIR());
		obj.setMANAG_USABLE(theForm.getMANAG_USABLE());
		obj.setMANAG_USE(theForm.getMANAG_USE());
		obj.setMANAG_DESCRIP(theForm.getMANAG_DESCRIP());
		if(flag==1){
			ledgerService.updateLedgerObj(obj);
		}
		else{
			ledgerService.insertLedgerObj(obj);
		}
		return "save";
	}
	
	
	
	
	
	
	
	

	public LedgerForm getTheForm() {
		return theForm;
	}

	public void setTheForm(LedgerForm theForm) {
		this.theForm = theForm;
	}

	public LedgerService getLedgerService() {
		return ledgerService;
	}

	public void setLedgerService(LedgerService ledgerService) {
		this.ledgerService = ledgerService;
	}
	
	

}
