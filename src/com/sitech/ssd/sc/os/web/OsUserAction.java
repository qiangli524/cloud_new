package com.sitech.ssd.sc.os.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.sc.os.domain.HostModel;
import com.sitech.ssd.sc.os.domain.OsGroupModel;
import com.sitech.ssd.sc.os.domain.OsUserModel;
import com.sitech.ssd.sc.os.service.OsGroupService;
import com.sitech.ssd.sc.os.service.OsHostService;
import com.sitech.ssd.sc.os.service.OsUserService;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateUser;
import com.sitech.ssd.sc.ostempl.service.OsTemplateComponetService;
import com.sitech.ssd.sc.ostempl.service.OsTemplateService;

@Controller("osUserAction")
public class OsUserAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private OsUserForm theForm;
	
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
	
	public String queryUser(){
		
		HostModel host = new HostModel(theForm.getOs_host_id());
		host = osHostService.queryForObject(host);

		String _templId = host.getOs_template();
		if(_templId != null && !"".equals(_templId)){
			OsTemplateUser obj = new OsTemplateUser("",_templId);
			theForm.setOtuList(osTemplateComponetService
					.queryOsTemplateUserList(obj));
		}else{
			theForm.setOtuList(null);
		}
		
		OsUserModel model = new OsUserModel(host.getId());
		theForm.setOuList(osUserService.showOsUserList(model));
		
		theForm.setTemplate_id(host.getOs_template());	
		theForm.setTemplList(osTemplateService
				.queryOsTemplateList(new OsTemplate()));
		return "osUser";
	}
	
	/**
	 * @Title: deleteUser 
	 * @Description: 
	 * @Author: JamTau
	 * @Date 2014-7-15 上午11:16:13
	 * @return  String
	 */
	public String deleteUser(){
		OsUserModel model = new OsUserModel();
		model.setId(theForm.getId());
		osUserService.deleteOsUser(model);
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
	
	public String addUser(){
		OsGroupModel group = new OsGroupModel();
		group.setOs_host_id(theForm.getOs_host_id());
		theForm.setOgList(osGroupService
				.queryOsGroupList(group));
		return "addOsUser";
	}
	public String saveUser(){
		OsUserModel model = new OsUserModel();
		BeanUtils.copyProperties(theForm, model);
		osUserService.addOsUser(model);
		resetForm(theForm);
		return null;
	}
	
	public String validUser(){
		int msg = 0;
		
		OsUserModel user = new OsUserModel();
		user.setUser_name(theForm.getUser_name());
		user.setOs_host_id(theForm.getOs_host_id());
		if(osUserService.queryForList(user)
				.size() > 0){
			msg = -1;			
		}else{
			OsTemplateUser templUser = new OsTemplateUser();
			templUser.setUser_name(theForm.getUser_name());
			templUser.setTemplate_id(theForm.getTemplate_id());
			if(osTemplateComponetService
					.queryOsTemplateUserList(templUser).size() > 0){
				msg = -2;
			}
		}
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateUser(){
		
		OsGroupModel group = new OsGroupModel();
		group.setOs_host_id(theForm.getOs_host_id());
		theForm.setOgList(osGroupService
				.queryOsGroupList(group));
		
		OsUserModel model = new OsUserModel();
		BeanUtils.copyProperties(theForm, model);
		model = osUserService.queryOsUser(model);
		BeanUtils.copyProperties(model, theForm);
		
		return "updateOsUser";
	}
	public String modifyUser(){
		OsUserModel model = new OsUserModel();
		BeanUtils.copyProperties(theForm, model);
		osUserService.modifyOsUser(model);
		resetForm(theForm);
		return null;
	}
	private void resetForm(OsUserForm theForm){
		theForm.setHome_dir("");
		theForm.setPass_word("");
		theForm.setPrimary_group("");
		theForm.setUser_name("");
	}
	
	public OsUserForm getTheForm() {
		return theForm;
	}
	public void setTheForm(OsUserForm theForm) {
		this.theForm = theForm;
	}
}


