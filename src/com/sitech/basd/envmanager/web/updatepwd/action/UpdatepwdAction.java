package com.sitech.basd.envmanager.web.updatepwd.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.sitech.basd.envmanager.domain.updatepwd.UpdatepwdObj;
import com.sitech.basd.envmanager.service.updatepwd.UpdatepwdService;
import com.sitech.basd.envmanager.web.updatepwd.form.UpdatepwdForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class UpdatepwdAction extends BaseAction{
	
	private UpdatepwdForm theForm ;
	
	private UpdatepwdService updatepwdService;
	
	/**
	 * @Title:查询相应设备下的所有用户名密码信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String queryPwdObj(){
		if(theForm==null){
			theForm = new UpdatepwdForm();
		}
		
		UpdatepwdObj obj = new UpdatepwdObj();
		if(theForm.getPWD_USER()!=null&&!"".equals(theForm.getPWD_USER())){
			obj.setPWD_USER(theForm.getPWD_USER().trim());
		}
		if(theForm.getIPADDRESS()!=null&&!"".equals(theForm.getIPADDRESS())){
			obj.setIPADDRESS(theForm.getIPADDRESS().trim());
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		
		String device_id = request.getParameter("DEVICE_ID");
		int DEVICE_ID=0;
		if(device_id!=null){
		 DEVICE_ID = Integer.parseInt(device_id);
		obj.setDEVICE_ID(DEVICE_ID);
		}
		else{
			obj.setDEVICE_ID(theForm.getDEVICE_ID());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List pwdList = updatepwdService.queryUpdatepwdObj(obj);
		theForm.setPwdList(pwdList);
		if(device_id!=null){
		theForm.setDEVICE_ID(DEVICE_ID);}
		return "success";
	}
	/**
	 * @Title:进入添加用户与密码页面
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String insertPwdObj(){
		if(theForm==null){
			theForm = new UpdatepwdForm();
		}
		UpdatepwdObj obj = new UpdatepwdObj();
		obj.setDEVICE_ID(theForm.getDEVICE_ID());
		theForm.setPWD_USER(null);
		theForm.setIPADDRESS(null);
		return "add";
	}
	/**
	 * @Title:进入修改用户与密码页面
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String updatePwdObj(){
		if(theForm==null){
			theForm = new UpdatepwdForm();
		}
		UpdatepwdObj obj = new UpdatepwdObj();
		obj.setPwd_id(theForm.getPwd_id());
		UpdatepwdObj uobj = updatepwdService.queryPwdOne(obj);
		theForm.setPwd_id(uobj.getPwd_id());
		theForm.setPWD_NEW(uobj.getPWD_NEW());
		theForm.setPWD_USER(uobj.getPWD_USER());
		theForm.setIPADDRESS(uobj.getIPADDRESS());
		theForm.setDEVICE_ID(uobj.getDEVICE_ID());
		theForm.setFlag(1);
		return "update";
	}
	/**
	 * @Title:保存修改与添加用户与密码
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String savePwdObj(){
		if(theForm==null){
			theForm = new UpdatepwdForm();
		}
		int flag = theForm.getFlag();
		
		UpdatepwdObj obj = new UpdatepwdObj();
		if(flag==1){
			obj.setPwd_id(theForm.getPwd_id());
			obj.setPWD_NEW(theForm.getPWD_NEWT());
		}
		else{
			obj.setPWD_NEW(theForm.getPWD_NEW());
		}
		obj.setPWD_USER(theForm.getPWD_USER());
		obj.setIPADDRESS(theForm.getIPADDRESS());
		obj.setDEVICE_ID(theForm.getDEVICE_ID());
		if(flag==1){
				updatepwdService.updatePwdObj(obj);
			}
		else{
				updatepwdService.insertPwdObj(obj);
			}
		
		obj.setPWD_USER(null);
		obj.setIPADDRESS(null);
		theForm.setPWD_USER(null);
		theForm.setIPADDRESS(null);
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List pwdList = updatepwdService.queryUpdatepwdObj(obj);
		theForm.setPwdList(pwdList);
		return "save";
	}

	public String deletePwdObj(){
		if(theForm==null){
			theForm = new UpdatepwdForm();
		}
		UpdatepwdObj obj = new UpdatepwdObj();
		obj.setPwd_id(theForm.getPwd_id());
		updatepwdService.deletePwdObj(obj);
		//在次查询页面
		obj.setDEVICE_ID(theForm.getDEVICE_ID());
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List pwdList = updatepwdService.queryUpdatepwdObj(obj);
		theForm.setPwdList(pwdList);
		
		return "del";
	}
	
	public UpdatepwdForm getTheForm() {
		return theForm;
	}

	public void setTheForm(UpdatepwdForm theForm) {
		this.theForm = theForm;
	}

	public UpdatepwdService getUpdatepwdService() {
		return updatepwdService;
	}

	public void setUpdatepwdService(UpdatepwdService updatepwdService) {
		this.updatepwdService = updatepwdService;
	}
	
	
	

}
