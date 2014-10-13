package com.sitech.ssd.sc.ostempl.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.sc.common.domain.TbSysDictObj;
import com.sitech.ssd.sc.common.service.TbSysDictService;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateFileSystem;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroup;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroupUser;
import com.sitech.ssd.sc.ostempl.domain.OsTemplatePart;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateSoft;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateUser;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateVolGroup;
import com.sitech.ssd.sc.ostempl.service.OsTemplateComponetService;
import com.sitech.ssd.sc.ostempl.service.OsTemplateService;

/**
 * 
 * @ClassName: OsTemplateAction
 * @Description: OS安装模版Action
 * @author JamTau
 * @date 2014-8-20 下午5:44:31
 *
 */
@Controller("osTemplateAction")
@Scope("prototype")
public class OsTemplateAction extends BaseAction {

	/**
	 * @Fields serialVersionUID:TODO
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OsTemplateService osTemplateService;
	@Autowired
	private OsTemplateComponetService osTemplateComponetService;
	@Resource
	private TbSysDictService tbSysDictService;
	
	private OsTemplateForm theForm;
	
	/****************************Begin******************************/
	public String addOsTemplate(){
		TbSysDictObj dict = new TbSysDictObj("OS_VERSION");
		theForm.setSdList(
				tbSysDictService.queryTbSysDictEffectList(dict));
		return "addOsTemplate";
	}
	public String saveOsTemplate(){
		OsTemplate obj = new OsTemplate();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateService.saveOsTemplate(obj);
		return "redirect";
	}
	public String deleteOsTemplate(){
		OsTemplate obj = new OsTemplate(theForm.getId());
		osTemplateService.deleteOsTemplate(obj);
		
		OsTemplateFileSystem fileSystem = new OsTemplateFileSystem("",theForm.getId());
		osTemplateComponetService.deleteOsTemplateFileSystem(fileSystem);
		
		OsTemplateGroup group = new OsTemplateGroup("",theForm.getId());
		osTemplateComponetService.deleteOsTemplateGroup(group);
		
		OsTemplateGroupUser groupUser = new OsTemplateGroupUser("",theForm.getId());
		osTemplateComponetService.deleteOsTemplateGroupUser(groupUser);
		
		OsTemplateUser user = new OsTemplateUser("",theForm.getId());
		osTemplateComponetService.deleteOsTemplateUser(user);
		
		OsTemplateSoft soft = new OsTemplateSoft("",theForm.getId());
		osTemplateComponetService.deleteOsTemplateSoft(soft);
		
		OsTemplatePart part = new OsTemplatePart("",theForm.getId());
		osTemplateComponetService.deleteOsTemplatePart(part);
		
		OsTemplateVolGroup vg = new OsTemplateVolGroup("",theForm.getId());
		osTemplateComponetService.deleteOsTemplateVolGroup(vg);
		
		return "redirect";
	}
	public String updateOsTemplate(){
		OsTemplate obj = new OsTemplate(theForm.getId());
		obj = osTemplateService.queryOsTemplate(obj);
		BeanUtils.copyProperties(obj,theForm);
		
		TbSysDictObj dict = new TbSysDictObj("OS_VERSION");
		theForm.setSdList(
				tbSysDictService.queryTbSysDictEffectList(dict));
		return "updateOsTemplate";
	}
	public String modifyOsTemplate(){
		OsTemplate obj = new OsTemplate();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateService.modifyOsTemplate(obj);
		return "redirect";
	}
	
	/**
	 * 
	 * @Title: showOsTemplate
	 * @Description: 安装模版列表页面
	 * @param  输入参数
	 * @return String 返回类型
	 * @throws
	 */
	public String showOsTemplate(){
		if(theForm == null){
			theForm = new OsTemplateForm();
		}
		OsTemplate obj = new OsTemplate();
		BeanUtils.copyProperties(theForm,obj);
		theForm.setTemplList(osTemplateService.queryOsTemplateList(obj));
		
		TbSysDictObj dict = new TbSysDictObj("OS_VERSION");
		theForm.setSdList(
				tbSysDictService.queryTbSysDictEffectList(dict));
		
		return "osTemplateList";
	}
	
	/**
	 * 
	 * @Title: configOsTemplate
	 * @Description: configOsTemplate
	 * @param  输入参数
	 * @return String 返回类型
	 * @throws
	 * 
	 * OsTemplateConfig.jsp
	 */
	public String configOsTemplate(){
		OsTemplate obj = new OsTemplate(theForm.getId());
		obj = osTemplateService.queryOsTemplate(obj);
		BeanUtils.copyProperties(obj, theForm);
		return "osTemplateConfig";
	}
	
	/**
	 * 
	 * @Title: showOsTemplateDetail
	 * @Description: 
	 * 	显示安装模版的详细信息页面
	 * @param  输入参数
	 * @return String 返回类型
	 * @throws
	 * 
	 * OsTemplateDetail.jsp
	 */
	public String showOsTemplateDetail(){
		OsTemplate obj = new OsTemplate(theForm.getId());
		obj = osTemplateService.queryOsTemplate(obj);
		BeanUtils.copyProperties(obj, theForm);
		
		OsTemplateFileSystem fileSystem = new OsTemplateFileSystem("",obj.getId());
		theForm.setOtfsList(osTemplateComponetService
				.queryOsTemplateFileSystemList(fileSystem));
		
		OsTemplateGroup group = new OsTemplateGroup("",obj.getId());
		theForm.setGroupList(osTemplateComponetService
				.queryOsTemplateGroupList(group));
		
		OsTemplateGroupUser groupUser = new OsTemplateGroupUser("",obj.getId());
		theForm.setGuList(osTemplateComponetService
				.queryOsTemplateGroupUserList(groupUser));
		
		OsTemplateUser user = new OsTemplateUser("",obj.getId());
		theForm.setUserList(osTemplateComponetService
				.queryOsTemplateUserList(user));	
		
		OsTemplateSoft soft = new OsTemplateSoft("",obj.getId());
		theForm.setSoftList(osTemplateComponetService
				.queryOsTemplateSoftList(soft));
		
		OsTemplatePart part = new OsTemplatePart("",obj.getId());
		theForm.setPartList(osTemplateComponetService
				.queryOsTemplatePartList(part));
		
		OsTemplateVolGroup vg = new OsTemplateVolGroup("",obj.getId());
		theForm.setVgList(osTemplateComponetService
				.queryOsTemplateVolGroupList(vg));
		return "osTemplateDetail";
	}
	
	public String copyOsTemplate(){
		return "copyOsTemplate";
	}
	
	public String validateOsTemplateName(){
		String _templ_name = request.getParameter("templ_name");
		OsTemplate obj = new OsTemplate();
		 
		try {
			obj.setTempl_name(URLDecoder.decode(_templ_name,"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		List<OsTemplate> _otl = osTemplateService.queryOsTemplateList(obj);
		String msg = _otl.size() > 0 ? "EXIT" : "OK";
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
	
	public String saveCopyOsTemplate(){
		OsTemplate obj = new OsTemplate(theForm.getId());
		obj = osTemplateService.queryOsTemplate(obj);
		
		obj.setId("");
		obj.setTempl_name(theForm.getTempl_name());
		osTemplateService.saveOsTemplate(obj);
		
		//String id 代替 新模版的“名称”,String template_id 代表旧模版的“template_id"
		OsTemplateFileSystem fileSystem = new OsTemplateFileSystem(
				theForm.getTempl_name(),theForm.getId());
		osTemplateComponetService.copyOsTemplateFileSystem(fileSystem);
		
		OsTemplateGroup group = new OsTemplateGroup(
				theForm.getTempl_name(),theForm.getId());
		osTemplateComponetService.copyOsTemplateGroup(group);
		
		OsTemplateGroupUser groupUser = new OsTemplateGroupUser(
				theForm.getTempl_name(),theForm.getId());
		osTemplateComponetService.copyOsTemplateGroupUser(groupUser);
		
		OsTemplateUser user = new OsTemplateUser(
				theForm.getTempl_name(),theForm.getId());
		osTemplateComponetService.copyOsTemplateUser(user);
		
		OsTemplateSoft soft = new OsTemplateSoft(
				theForm.getTempl_name(),theForm.getId());
		osTemplateComponetService.copyOsTemplateSoft(soft);
		
		OsTemplatePart part = new OsTemplatePart(
				theForm.getTempl_name(),theForm.getId());
		osTemplateComponetService.copyOsTemplatePart(part);
		
		OsTemplateVolGroup vg = new OsTemplateVolGroup(
				theForm.getTempl_name(),theForm.getId());
		osTemplateComponetService.copyOsTemplateVolGroup(vg);
		return "redirect";
	}
	
	/**************************** End ******************************/
	public OsTemplateForm getTheForm() {
		return theForm;
	}
	public void setTheForm(OsTemplateForm theForm) {
		this.theForm = theForm;
	}
}
