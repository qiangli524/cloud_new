package com.sitech.ssd.sc.os.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.sc.os.domain.HostModel;
import com.sitech.ssd.sc.os.domain.OsGroupModel;
import com.sitech.ssd.sc.os.domain.OsGroupUserModel;
import com.sitech.ssd.sc.os.domain.OsUserModel;
import com.sitech.ssd.sc.os.service.OsGroupService;
import com.sitech.ssd.sc.os.service.OsGroupUserService;
import com.sitech.ssd.sc.os.service.OsHostService;
import com.sitech.ssd.sc.os.service.OsUserService;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroupUser;
import com.sitech.ssd.sc.ostempl.service.OsTemplateComponetService;
import com.sitech.ssd.sc.ostempl.service.OsTemplateService;

@Controller("osGroupUserAction")
public class OsGroupUserAction extends BaseAction {

	@Resource
	private OsGroupUserService osGroupUserService;
	@Resource
	private OsUserService osUserService;
	@Resource
	private OsGroupService osGroupService; 
	@Resource
	private OsTemplateService osTemplateService;
	@Resource
	private OsTemplateComponetService osTemplateComponetService;
	@Resource
	private OsHostService osHostService;
	
	private static final long serialVersionUID = 732026467346130014L;
	private OsGroupUserForm theForm;

	public String queryGroupUser(){
		
		HostModel host = new HostModel(theForm.getOs_host_id());
		host = osHostService.queryForObject(host);
		
		OsGroupUserModel model = new OsGroupUserModel(host.getId());
		theForm.setOguList(osGroupUserService.showOsGroupUserList(model));

		String _templId = host.getOs_template();
		if(_templId != null && !"".equals(_templId)){
			OsTemplateGroupUser obj = new OsTemplateGroupUser("",_templId);
			theForm.setOtguList(osTemplateComponetService.queryOsTemplateGroupUserList(obj));
		}else{
			theForm.setOtguList(null);
		}
		
		theForm.setTemplate_id(host.getOs_template());
		theForm.setTemplList(osTemplateService
				.queryOsTemplateList(new OsTemplate()));
		return "osGroupUser";
	}
	
	public String deleteGroupUser(){
		OsGroupUserModel model = new OsGroupUserModel();
		model.setId(theForm.getId());
		osGroupUserService.deleteOsGroupUser(model);
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("success");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String addGroupUser(){
		OsGroupModel group = new OsGroupModel(theForm.getOs_host_id());
		theForm.setOgList(osGroupService.queryOsGroupList(group));
		
		OsUserModel user = new OsUserModel(theForm.getOs_host_id());
		theForm.setOuList(osUserService.queryForList(user));
		
		return "addOsGroupUser";
	}
	
	public String saveGroupUser(){
		OsGroupUserModel model = new OsGroupUserModel();
		BeanUtils.copyProperties(theForm, model);
		osGroupUserService.addOsGroupUser(model);
		resetForm(theForm);
		return null;
	}
	
	
	public String updateGroupUser(){
		OsGroupUserModel model = new OsGroupUserModel();
		BeanUtils.copyProperties(theForm, model);
		model = osGroupUserService.queryOsGroupUser(model);
		BeanUtils.copyProperties(model, theForm);
		return "updateOsGroupUser";
	}
	public String modifyGroupUser(){
		OsGroupUserModel model = new OsGroupUserModel();
		BeanUtils.copyProperties(theForm, model);
		osGroupUserService.modifyOsGroupUser(model);
		resetForm(theForm);
		return null;
	}
	
	private void resetForm(OsGroupUserForm theForm){
		theForm.setGroup_id("");
		theForm.setUser_id("");
		theForm.setFlag("");
	}
	
	public OsGroupUserForm getTheForm() {
		return theForm;
	}
	public void setTheForm(OsGroupUserForm theForm) {
		this.theForm = theForm;
	}
}
