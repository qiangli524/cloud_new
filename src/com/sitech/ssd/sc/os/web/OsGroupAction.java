package com.sitech.ssd.sc.os.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.sc.os.domain.HostModel;
import com.sitech.ssd.sc.os.domain.OsGroupModel;
import com.sitech.ssd.sc.os.service.OsGroupService;
import com.sitech.ssd.sc.os.service.OsHostService;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroup;
import com.sitech.ssd.sc.ostempl.service.OsTemplateComponetService;
import com.sitech.ssd.sc.ostempl.service.OsTemplateService;

@Controller("osGroupAction")
public class OsGroupAction extends BaseAction {
	
	@Resource
	private OsGroupService osGroupService;
	@Resource
	private OsTemplateService osTemplateService;
	@Resource
	private OsTemplateComponetService osTemplateComponetService;
	@Resource
	private OsHostService osHostService;
	
	private static final long serialVersionUID = 1L;
	private OsGroupForm theForm;
	
	public String queryGroup(){
		HostModel host = new HostModel(theForm.getOs_host_id());
		host = osHostService.queryForObject(host);
		
		String _templId = host.getOs_template();
		if(_templId != null && !"".equals(_templId)){
			OsTemplateGroup obj = new OsTemplateGroup("",_templId);
			theForm.setOtglist(osTemplateComponetService
					.queryOsTemplateGroupList(obj));
		}else{
			theForm.setOtglist(null);
		}
		
		OsGroupModel model = new OsGroupModel(theForm.getOs_host_id());
		theForm.setOglist(osGroupService.queryOsGroupList(model));
		
		theForm.setTemplate_id(host.getOs_template());
		theForm.setTemplList(osTemplateService
				.queryOsTemplateList(new OsTemplate()));
		return "osGroup";
	}
	
	public String addGroup(){
		return "addOsGroup";
	}
	public String saveGroup(){
		OsGroupModel model = new OsGroupModel();
		BeanUtils.copyProperties(theForm, model);
		osGroupService.addOsGroup(model);
		resetForm(theForm);
		return null;
	}
	
	public String validGroup(){
		int msg = 0;
		OsGroupModel group = new OsGroupModel();
		group.setGroup_id(theForm.getGroup_id());
		group.setGroup_name(theForm.getGroup_name());
		group.setOs_host_id(theForm.getOs_host_id());
		if(osGroupService
				.queryOsGroupList(group).size() > 0){
			msg = -1;
		}else{
			OsTemplateGroup tg = new OsTemplateGroup();
			tg.setGroup_id(theForm.getGroup_id());
			tg.setGroup_name(theForm.getGroup_name());
			tg.setTemplate_id(theForm.getTemplate_id());
			if(osTemplateComponetService
					.queryOsTemplateGroupList(tg).size() > 0){
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
	public String deleteGroup(){
		OsGroupModel model = new OsGroupModel();
		model.setId(theForm.getId());
		osGroupService.deleteOsGroup(model);
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
	
	public String updateGroup(){
		OsGroupModel model = new OsGroupModel();
		model.setId(theForm.getId());
		model = osGroupService.queryOsGroup(model);
		BeanUtils.copyProperties(model, theForm);
		return "updateOsGroup";
	}
	public String modifyGroup(){
		OsGroupModel model = new OsGroupModel();
		BeanUtils.copyProperties(theForm, model);
		osGroupService.modifyOsGroup(model);
		resetForm(theForm);
		return null;
	}
	
	private void resetForm(OsGroupForm theForm){
		theForm.setGroup_id("");
		theForm.setGroup_name("");
		theForm.setGroup_desc("");
	}
	public OsGroupForm getTheForm() {
		return theForm;
	}
	public void setTheForm(OsGroupForm theForm) {
		this.theForm = theForm;
	}
}
