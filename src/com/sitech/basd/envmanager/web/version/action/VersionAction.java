package com.sitech.basd.envmanager.web.version.action;

import java.util.List;

import com.sitech.basd.envmanager.domain.version.VersionObj;
import com.sitech.basd.envmanager.service.version.VersionService;
import com.sitech.basd.envmanager.web.version.form.VersionForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class VersionAction  extends BaseAction{
	
	
	VersionService versionService;
	
	VersionForm theForm;

	
	
	/**
	 * @Title:查询所有版本信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String queryVersionList(){
		if(theForm==null){
			theForm = new VersionForm();
		}
		VersionObj obj = new VersionObj();
		if(theForm.getSOFTNAME()!=null&&!"".equals(theForm.getSOFTNAME())){
			obj.setSOFTNAME(theForm.getSOFTNAME().trim());
		}
		if(theForm.getSOFTEDITION()!=null&&!"".equals(theForm.getSOFTEDITION()))
		{
			obj.setSOFTEDITION(theForm.getSOFTEDITION().trim());
		}
		if(theForm.getSUPLINUX()!=null&&!"".equals(theForm.getSUPLINUX())){
			obj.setSUPLINUX(theForm.getSUPLINUX().trim());
		}
		if(theForm.getSUPUNIX()!=null&&!"".equals(theForm.getSUPUNIX())){
			obj.setSUPUNIX(theForm.getSUPUNIX().trim());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List versionList = versionService.queryVersionObj(obj);
		theForm.setVersion_List(versionList);
		return "success";
	}
	/**
	 * @Title:进入添加页面
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String insertVersionObj(){
		if(theForm!=null){
			theForm =null;
		}
		return "add";
	}
	public String updateVersionObj(){
		if(theForm==null){
			theForm = new VersionForm();
		}
		VersionObj obj = new VersionObj();
		obj.setS_id(theForm.getS_id());
		VersionObj vobj = versionService.queryVersionOne(obj);
		
		theForm.setS_id(vobj.getS_id());
		theForm.setSOFTNAME(vobj.getSOFTNAME());
		theForm.setSOFTEDITION(vobj.getSOFTEDITION());
		theForm.setSUPLINUX(vobj.getSUPLINUX());
		theForm.setSUPUNIX(vobj.getSUPUNIX());
		theForm.setSUPSUNOS(vobj.getSUPSUNOS());
		theForm.setSYSTEMPATCH(vobj.getSYSTEMPATCH());
		theForm.setSYSTEMSERVICE(vobj.getSYSTEMSERVICE());
		theForm.setDEPENDPACK(vobj.getDEPENDPACK());
		theForm.setCOMPILER(vobj.getCOMPILER());
		theForm.setOTHERPACK(vobj.getOTHERPACK());
		theForm.setSOFTPACK(vobj.getSOFTPACK());
		theForm.setSOFTLICENSE(vobj.getSOFTLICENSE());
		theForm.setFlag(1);
		return "update";
	}
	/**
	 * @Title:保存版本信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String saveVersionObj(){
		if(theForm==null){
			theForm = new VersionForm();
		}
		int flag = theForm.getFlag();
		VersionObj obj = new VersionObj();
		if(flag==1){obj.setS_id(theForm.getS_id());	}	
		obj.setSOFTNAME(theForm.getSOFTNAME());
		obj.setSOFTEDITION(theForm.getSOFTEDITION());
		obj.setSUPLINUX(theForm.getSUPLINUX());
		obj.setSUPUNIX(theForm.getSUPUNIX());
		obj.setSUPSUNOS(theForm.getSUPSUNOS());
		obj.setSYSTEMPATCH(theForm.getSYSTEMPATCH());
		obj.setSYSTEMSERVICE(theForm.getSYSTEMSERVICE());
		obj.setDEPENDPACK(theForm.getDEPENDPACK());
		obj.setCOMPILER(theForm.getCOMPILER());
		obj.setOTHERPACK(theForm.getOTHERPACK());
		obj.setSOFTPACK(theForm.getSOFTPACK());
		obj.setSOFTLICENSE(theForm.getSOFTLICENSE());
		if(flag==1){
			versionService.updateVersionObj(obj);
		}
		else {
			versionService.insertVersionObj(obj);
		}
		return "save";
	}
	public String deleteVersionObj(){
		if(theForm==null){
			theForm = new VersionForm();
		}
		int result = 0;
		VersionObj obj = new VersionObj();
		obj.setS_id(theForm.getS_id());
		result= versionService.deleteVersionObj(obj);
		return "del";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public VersionService getVersionService() {
		return versionService;
	}

	public void setVersionService(VersionService versionService) {
		this.versionService = versionService;
	}

	public VersionForm getTheForm() {
		return theForm;
	}

	public void setTheForm(VersionForm theForm) {
		this.theForm = theForm;
	}
	
	
	

}
