package com.sitech.basd.envmanager.web.configure.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitech.basd.envmanager.domain.configure.ConfigureObj;
import com.sitech.basd.envmanager.service.configure.ConfigureService;
import com.sitech.basd.envmanager.web.configure.form.ConfigureForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class ConfigureAction  extends BaseAction{
	
	ConfigureForm theForm;
	
	ConfigureService configureService;
	
	
	/**
	 * @Title:查询所有开发环境使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String queryConfigureObj(){
		if(theForm==null){
			theForm = new ConfigureForm();
		}
		ConfigureObj obj = new ConfigureObj();
		if(theForm.getCF_DOMAIN()!=null&&!"".equals(theForm.getCF_DOMAIN())){
			obj.setCF_DOMAIN(theForm.getCF_DOMAIN().trim());
		}
		if(theForm.getCF_SOURCE()!=null&&!"".equals(theForm.getCF_SOURCE())){
			obj.setCF_SOURCE(theForm.getCF_SOURCE().trim());
		}
		if(theForm.getCF_CLASS()!=null&&!"".equals(theForm.getCF_CLASS())){
			obj.setCF_CLASS(theForm.getCF_CLASS().trim());
		}
		if(theForm.getCF_HOSTTYPE()!=null&&!"".equals(theForm.getCF_HOSTTYPE())){
			obj.setCF_HOSTTYPE(theForm.getCF_HOSTTYPE().trim());
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String device_id = request.getParameter("DEVICE_ID");
		int DEVICE_ID=0;
		if(device_id!=null){
			 DEVICE_ID = Integer.parseInt(device_id);
			obj.setDEVICE_ID(DEVICE_ID);
		}
		
		if(device_id==null){obj.setDEVICE_ID(theForm.getDEVICE_ID());}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List configureList = configureService.queryConfigureObj(obj);
		theForm.setCon_List(configureList);
		if(device_id!=null){
		theForm.setDEVICE_ID(DEVICE_ID);}
		return "success";
	}
	/**
	 * @Title:进入添加开发环境使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String insertConfigureObj(){
		if(theForm==null){
			theForm = new ConfigureForm();
		}
		return "add";
	}
	public String updateConfigureObj(){
		if(theForm==null){
			theForm = new ConfigureForm();
		}
		ConfigureObj obj = new ConfigureObj();
		obj.setCf_env(theForm.getCf_env());
		ConfigureObj cobj =  configureService.queryConfigureOne(obj);
		theForm.setCf_env(cobj.getCf_env());
		theForm.setCF_DOMAIN(cobj.getCF_DOMAIN());
		theForm.setCF_SOURCE(cobj.getCF_SOURCE());
		theForm.setCF_CLASS(cobj.getCF_CLASS());
		theForm.setCF_HOSTTYPE(cobj.getCF_HOSTTYPE());
		theForm.setCF_HOSTNUM(cobj.getCF_HOSTNUM());
		theForm.setCF_DESCRIPTION(cobj.getCF_DESCRIPTION());
		theForm.setCF_SYSTEM(cobj.getCF_SYSTEM());
		theForm.setCF_HOSTNAME(cobj.getCF_HOSTNAME());
		theForm.setCF_IP(cobj.getCF_IP());
		theForm.setCF_PRODUCT(cobj.getCF_PRODUCT());
		
		theForm.setCF_DEVEPROD(cobj.getCF_DEVEPROD());
		theForm.setCF_SID(cobj.getCF_SID());
		theForm.setCF_TABLESPACE(cobj.getCF_TABLESPACE());
		theForm.setCF_FILESYSNAM(cobj.getCF_FILESYSNAM());
		theForm.setCF_FILEAPPNUM(cobj.getCF_FILEAPPNUM());
		theForm.setCF_CPUUSED(cobj.getCF_CPUUSED());
		theForm.setCF_MEM(cobj.getCF_MEM());
		theForm.setCF_FILEUSERD(cobj.getCF_FILEUSERD());
		theForm.setCF_FILEUSEPER(cobj.getCF_FILEUSEPER());
		theForm.setCF_SID1(cobj.getCF_SID1());
		theForm.setCF_TABSPAUSED(cobj.getCF_TABSPAUSED());
		theForm.setCF_TABSPAUSEPER(cobj.getCF_TABSPAUSEPER());
		theForm.setCF_SGA(cobj.getCF_SGA());
		theForm.setCF_CPULEFT(cobj.getCF_CPULEFT());
		theForm.setCF_MEMLEFT(cobj.getCF_MEMLEFT());
		theForm.setCF_STORAGE(cobj.getCF_STORAGE());
		theForm.setFlag(1);
		return "update";
	}
	/**
	 * @Title:保存修改和添加开发环境使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String saveConfigureObj(){
		if(theForm==null){
			theForm = new ConfigureForm();
		}
//		HttpServletRequest request = Struts2Utils.getRequest();
//		HttpServletResponse response = Struts2Utils.getResponse();
//		String device_id = request.getParameter("DEVICE_ID");
//		int DEVICE_ID = Integer.parseInt(device_id);
		int flag = theForm.getFlag();
		ConfigureObj obj = new ConfigureObj();
		if(flag==1){obj.setCf_env(theForm.getCf_env());}
		
		obj.setDEVICE_ID(theForm.getDEVICE_ID());
		obj.setCF_DOMAIN(theForm.getCF_DOMAIN());
		obj.setCF_SOURCE(theForm.getCF_SOURCE());
		obj.setCF_CLASS(theForm.getCF_CLASS());
		obj.setCF_HOSTTYPE(theForm.getCF_HOSTTYPE());
		obj.setCF_HOSTNUM(theForm.getCF_HOSTNUM());
		obj.setCF_DESCRIPTION(theForm.getCF_DESCRIPTION());
		obj.setCF_SYSTEM(theForm.getCF_SYSTEM());
		obj.setCF_HOSTNAME(theForm.getCF_HOSTNAME());
		obj.setCF_IP(theForm.getCF_IP());
		obj.setCF_PRODUCT(theForm.getCF_PRODUCT());
		
		obj.setCF_DEVEPROD(theForm.getCF_DEVEPROD());
		obj.setCF_SID(theForm.getCF_SID());
		obj.setCF_TABLESPACE(theForm.getCF_TABLESPACE());
		obj.setCF_FILESYSNAM(theForm.getCF_FILESYSNAM());
		obj.setCF_FILEAPPNUM(theForm.getCF_FILEAPPNUM());
		obj.setCF_CPUUSED(theForm.getCF_CPUUSED());
		obj.setCF_MEM(theForm.getCF_MEM());
		obj.setCF_FILEUSERD(theForm.getCF_FILEUSERD());
		obj.setCF_FILEUSEPER(theForm.getCF_FILEUSEPER());
		obj.setCF_SID1(theForm.getCF_SID1());
		obj.setCF_TABSPAUSED(theForm.getCF_TABSPAUSED());
		obj.setCF_TABSPAUSEPER(theForm.getCF_TABSPAUSEPER());
		obj.setCF_SGA(theForm.getCF_SGA());
		obj.setCF_CPULEFT(theForm.getCF_CPULEFT());
		obj.setCF_MEMLEFT(theForm.getCF_MEMLEFT());
		obj.setCF_STORAGE(theForm.getCF_STORAGE());
		//obj.setE_DATE(theForm.getE_DATE());
		if(flag==1){
			configureService.updateConfigureObj(obj);
		}
		else {
			configureService.insertConfigureObj(obj);
		}
		
		obj.setDEVICE_ID(theForm.getDEVICE_ID());
		obj.setCF_DOMAIN(null);
		obj.setCF_SOURCE(null);
		obj.setCF_CLASS(null);
		obj.setCF_HOSTTYPE(null);
		
		theForm.setCF_DOMAIN(null);
		theForm.setCF_SOURCE(null);
		theForm.setCF_CLASS(null);
		theForm.setCF_HOSTTYPE(null);
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List configureList = configureService.queryConfigureObj(obj);
		theForm.setCon_List(configureList);
		return "save";
	}
	public String deleteConfigureObj(){
		if(theForm==null){
			theForm = new ConfigureForm();
		}
		ConfigureObj obj = new ConfigureObj();
		obj.setCf_env(theForm.getCf_env());
		configureService.deleteConfigureObj(obj);
		obj.setDEVICE_ID(theForm.getDEVICE_ID());
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List configureList = configureService.queryConfigureObj(obj);
		theForm.setCon_List(configureList);
		
		return "del";
	}
	
	
	

	public ConfigureForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ConfigureForm theForm) {
		this.theForm = theForm;
	}

	public ConfigureService getConfigureService() {
		return configureService;
	}

	public void setConfigureService(ConfigureService configureService) {
		this.configureService = configureService;
	}


	

}
