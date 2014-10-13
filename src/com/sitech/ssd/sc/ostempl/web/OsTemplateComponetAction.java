package com.sitech.ssd.sc.ostempl.web;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.sc.common.domain.TbSysDictObj;
import com.sitech.ssd.sc.common.service.TbSysDictService;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateFileSystem;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroup;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroupUser;
import com.sitech.ssd.sc.ostempl.domain.OsTemplatePart;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateSoft;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateUser;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateVolGroup;
import com.sitech.ssd.sc.ostempl.service.OsTemplateComponetService;

/**
 * 
 * @ClassName: OsTemplateComponetAction
 * @Description: OS安装模版 组件Action类：文件系统、用户、组、用户与组
 * @author JamTau
 * @date 2014-8-20 下午5:45:45
 *
 */
@Controller("osTemplateComponetAction")
@Scope("prototype")
public class OsTemplateComponetAction extends BaseAction {

	/**
	 * @Fields serialVersionUID:TODO
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OsTemplateComponetService osTemplateComponetService;
	@Autowired
	private TbSysDictService tbSysDictService;	
	private OsTemplateComponetForm theForm;
	
	/*---------------------------FileSystem Begin-----------------------------*/
	public String addOsTemplateFileSystem(){
		TbSysDictObj dict = new TbSysDictObj("OS_FILESYSTEM_FORMAT");
		theForm.setSdList(
				tbSysDictService.queryTbSysDictEffectList(dict));
		
		OsTemplateVolGroup vg = new OsTemplateVolGroup();
		vg.setTemplate_id(theForm.getTemplate_id());
		theForm.setVgList(
				osTemplateComponetService.queryOsTemplateVolGroupList(vg));
		
		return "addOsTemplFileSystem";
	}
	public String saveOsTemplateFileSystem(){
		OsTemplateFileSystem obj = new OsTemplateFileSystem();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.saveOsTemplateFileSystem(obj);
		//return "redirectFileSystem";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateFileSystem();
	}
	public String deleteOsTemplateFileSystem(){
		OsTemplateFileSystem obj = new OsTemplateFileSystem();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.deleteOsTemplateFileSystem(obj);
		//return "redirectFileSystem";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateFileSystem();
	}
	public String updateOsTemplateFileSystem(){
		
		TbSysDictObj dict = new TbSysDictObj("OS_FILESYSTEM_FORMAT");
		theForm.setSdList(tbSysDictService
				.queryTbSysDictEffectList(dict));
		OsTemplateVolGroup vg = new OsTemplateVolGroup();
		vg.setTemplate_id(theForm.getTemplate_id());
		theForm.setVgList(
				osTemplateComponetService.queryOsTemplateVolGroupList(vg));
		OsTemplateFileSystem obj = new OsTemplateFileSystem(
				theForm.getId(),theForm.getTemplate_id());		
		obj = osTemplateComponetService.queryOsTemplateFileSystem(obj);
		BeanUtils.copyProperties(obj, theForm);
		return "updateOsTemplFileSystem";
	}
	public String modifyOsTemplateFileSystem(){
		OsTemplateFileSystem obj = new OsTemplateFileSystem();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.modifyOsTemplateFileSystem(obj);
		//return "redirectFileSystem";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateFileSystem();
	}
	public String showOsTemplateFileSystem(){
		if(theForm == null){
			theForm = new OsTemplateComponetForm();
		}
		OsTemplateFileSystem obj = new OsTemplateFileSystem();
		BeanUtils.copyProperties(theForm, obj);
		theForm.setOtfsList(osTemplateComponetService
				.queryOsTemplateFileSystemList(obj));
		return "osTemplFileSystem";
	}
	/**
	 * @Title: validOsTemplateFileSystem
	 * @Description: 
     * 	验证文件系统 是否存在
     * 		新建时：是否存在同名的文件目录
     *      更新时：是否存在同名的文件目录；同名目录的ID是否跟当前ID相同
	 * @param  输入参数
	 * @return String 返回类型
	 * @throws
	 */
	public String validOsTemplateFileSystem(){
		OsTemplateFileSystem obj = new OsTemplateFileSystem();
		obj.setTemplate_id(theForm.getTemplate_id());	
		obj.setFilesystem_name(theForm.getFilesystem_name());
		obj = osTemplateComponetService.queryOsTemplateFileSystem(obj);
		int msg = -1;
		if(obj.getId()==null||
				"".equals(obj.getId())){
			msg = 0;
		}else if(theForm.getId().equals(obj.getId())){
			msg = 0;
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
	/*=========================== FileSystem End =============================*/
	
	/*---------------------------Group Begin-----------------------------*/
	public String addOsTemplateGroup(){
		return "addOsTemplGroup";
		
	}
	public String saveOsTemplateGroup(){
		OsTemplateGroup obj = new OsTemplateGroup();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.saveOsTemplateGroup(obj);
		//return "redirectGroup";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateGroup();
	}
	public String deleteOsTemplateGroup(){
		OsTemplateGroup obj = new OsTemplateGroup();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.deleteOsTemplateGroup(obj);
		//return "redirectGroup";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateGroup();
	}
	public String updateOsTemplateGroup(){
		OsTemplateGroup obj = new OsTemplateGroup();
		BeanUtils.copyProperties(theForm, obj);
		obj = osTemplateComponetService.queryOsTemplateGroup(obj);
		BeanUtils.copyProperties(obj, theForm);
		return "updateOsTemplGroup";
	}
	public String modifyOsTemplateGroup(){
		OsTemplateGroup obj = new OsTemplateGroup();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.modifyOsTemplateGroup(obj);
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateGroup();
	}
	public String showOsTemplateGroup(){
		if(theForm == null){
			theForm = new OsTemplateComponetForm();
		}
		OsTemplateGroup obj = new OsTemplateGroup();
		BeanUtils.copyProperties(theForm, obj);
		theForm.setGroupList(osTemplateComponetService
				.queryOsTemplateGroupList(obj));
		return "osTemplGroup";
	}
	public String validOsTemplateGroup(){
		OsTemplateGroup obj = new OsTemplateGroup();
		obj.setTemplate_id(theForm.getTemplate_id());
		obj.setGroup_name(theForm.getGroup_name());
		obj = osTemplateComponetService.queryOsTemplateGroup(obj);
		
		int msg = -1;
		if(obj.getId()==null||
				"".equals(obj.getId())){
			msg = 0;
		}else if(theForm.getId().equals(obj.getId())){
			msg = 0;
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
	/*=========================== Group End =============================*/
	
	/*---------------------------User Begin-----------------------------*/
	public String addOsTemplateUser(){
		OsTemplateGroup group = new OsTemplateGroup();
		BeanUtils.copyProperties(theForm, group);
		theForm.setGroupList(osTemplateComponetService
				.queryOsTemplateGroupList(group));		
		return "addOsTemplUser";
	}
	public String saveOsTemplateUser(){
		OsTemplateUser obj = new OsTemplateUser();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.saveOsTemplateUser(obj);
		//return "redirectUser";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateUser();
	}
	public String deleteOsTemplateUser(){
		OsTemplateUser obj = new OsTemplateUser();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.deleteOsTemplateUser(obj);
		//return "redirectUser";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateUser();
	}
	public String updateOsTemplateUser(){
		OsTemplateGroup group = new OsTemplateGroup();
		group.setTemplate_id(theForm.getTemplate_id());
		theForm.setGroupList(osTemplateComponetService
				.queryOsTemplateGroupList(group));
		
		OsTemplateUser obj = new OsTemplateUser();
		BeanUtils.copyProperties(theForm, obj);
		obj = osTemplateComponetService.queryOsTemplateUser(obj);
		BeanUtils.copyProperties(obj,theForm);
		return "updateOsTemplUser";
	}
	public String modifyOsTemplateUser(){
		OsTemplateUser obj = new OsTemplateUser();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.modifyOsTemplateUser(obj);
		//return "redirectUser";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateUser();
	}
	public String showOsTemplateUser(){
		if(theForm == null){
			theForm = new OsTemplateComponetForm();
		}
		OsTemplateUser obj = new OsTemplateUser();
		BeanUtils.copyProperties(theForm, obj);
		theForm.setUserList(osTemplateComponetService
				.queryOsTemplateUserList(obj));
		return "osTemplUser";
	}
	public String validOsTemplateUser(){
		OsTemplateUser obj = new OsTemplateUser();
		obj.setTemplate_id(theForm.getTemplate_id());
		obj.setUser_name(theForm.getUser_name());
		obj = osTemplateComponetService.queryOsTemplateUser(obj);
		int msg = -1;
		if(obj.getId()==null||
				"".equals(obj.getId())){
			msg = 0;
		}else if(theForm.getId().equals(obj.getId())){
			msg = 0;
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
	/*=========================== User End =============================*/
	
	/*---------------------------GroupUser Begin-----------------------------*/
	public String addOsTemplateGroupUser(){
		OsTemplateGroup group = new OsTemplateGroup();
		group.setTemplate_id(theForm.getTemplate_id());
		theForm.setGroupList(osTemplateComponetService
				.queryOsTemplateGroupList(group));	
		
		OsTemplateUser user = new OsTemplateUser();
		user.setTemplate_id(theForm.getTemplate_id());
		theForm.setUserList(osTemplateComponetService
				.queryOsTemplateUserList(user));
		
		return "addOsTemplGroupUser";
	}
	public String saveOsTemplateGroupUser(){
		OsTemplateGroupUser obj = new OsTemplateGroupUser();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.saveOsTemplateGroupUser(obj);
		//return "redirectGroupUser";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateGroupUser();
		
	}
	public String deleteOsTemplateGroupUser(){
		OsTemplateGroupUser obj = new OsTemplateGroupUser();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.deleteOsTemplateGroupUser(obj);
		//return "redirectGroupUser";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateGroupUser();
	}
	public String updateOsTemplateGroupUser(){
		
		OsTemplateGroup group = new OsTemplateGroup();
		//BeanUtils.copyProperties(theForm, group);
		group.setTemplate_id(theForm.getTemplate_id());
		theForm.setGroupList(osTemplateComponetService
				.queryOsTemplateGroupList(group));	
		
		OsTemplateUser user = new OsTemplateUser();
		//BeanUtils.copyProperties(theForm, user);
		user.setTemplate_id(theForm.getTemplate_id());
		theForm.setUserList(osTemplateComponetService
				.queryOsTemplateUserList(user));
		
		OsTemplateGroupUser obj = new OsTemplateGroupUser();
		BeanUtils.copyProperties(theForm, obj);
		obj = osTemplateComponetService.queryOsTemplateGroupUser(obj);
		BeanUtils.copyProperties(obj,theForm);
		return "updateOsTemplGroupUser";
	}
	public String modifyOsTemplateGroupUser(){
		OsTemplateGroupUser obj = new OsTemplateGroupUser();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.modifyOsTemplateGroupUser(obj);
		//return "redirectGroupUser";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateGroupUser();
	}
	public String showOsTemplateGroupUser(){
		if(theForm == null){
			theForm = new OsTemplateComponetForm();
		}
		OsTemplateGroupUser obj = new OsTemplateGroupUser();
		BeanUtils.copyProperties(theForm, obj);
		theForm.setGuList(osTemplateComponetService
				.queryOsTemplateGroupUserList(obj));
		return "osTemplGroupUser";
	}
	public String validOsTemplateGroupUser(){
		OsTemplateGroupUser obj = new OsTemplateGroupUser();
		obj.setTemplate_id(theForm.getTemplate_id());
		obj.setUser_id(theForm.getUser_id());
		obj.setGroup_id(theForm.getGroup_id());
		obj = osTemplateComponetService.queryOsTemplateGroupUser(obj);
		
		int msg = -1;
		if(obj.getId()==null||
				"".equals(obj.getId())){
			msg = 0;
		}else if(theForm.getId().equals(obj.getId())){
			msg = 0;
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
	/*=========================== GroupUser End =============================*/
	
	/*---------------------------Soft Begin-----------------------------*/
	public String addOsTemplateSoft(){
		TbSysDictObj dict = new TbSysDictObj("OS_SOFT_TYPE");
		theForm.setSdList(tbSysDictService
				.queryTbSysDictEffectList(dict));
		return "addOsTemplSoft";
	}
	public String saveOsTemplateSoft(){
		OsTemplateSoft obj = new OsTemplateSoft();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.saveOsTemplateSoft(obj);
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateSoft();
		
	}
	public String deleteOsTemplateSoft(){
		OsTemplateSoft obj = new OsTemplateSoft();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.deleteOsTemplateSoft(obj);
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateSoft();
	}
	public String updateOsTemplateSoft(){
		TbSysDictObj dict = new TbSysDictObj("OS_SOFT_TYPE");
		theForm.setSdList(tbSysDictService
				.queryTbSysDictEffectList(dict));
		
		OsTemplateSoft obj = new OsTemplateSoft();
		BeanUtils.copyProperties(theForm, obj);
		obj = osTemplateComponetService.queryOsTemplateSoft(obj);
		BeanUtils.copyProperties(obj,theForm);
		return "updateOsTemplSoft";
	}
	public String modifyOsTemplateSoft(){
		OsTemplateSoft obj = new OsTemplateSoft();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.modifyOsTemplateSoft(obj);
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateSoft();
	}
	public String showOsTemplateSoft(){
		if(theForm == null){
			theForm = new OsTemplateComponetForm();
		}
		OsTemplateSoft obj = new OsTemplateSoft();
		BeanUtils.copyProperties(theForm, obj);
		theForm.setSoftList(osTemplateComponetService
				.queryOsTemplateSoftList(obj));
		return "osTemplSoft";
	}
	public String validOsTemplateSoft(){
		OsTemplateSoft obj = new OsTemplateSoft();
		obj.setTemplate_id(theForm.getTemplate_id());
		obj.setSoft_name(theForm.getSoft_name());
		obj = osTemplateComponetService.queryOsTemplateSoft(obj);
		int msg = -1;
		if(obj.getId()==null||
				"".equals(obj.getId())){
			msg = 0;
		}else if(theForm.getId().equals(obj.getId())){
			msg = 0;
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
	/*=========================== Soft End =============================*/
	
	/*---------------------------Part Begin-----------------------------*/
	public String addOsTemplatePart(){
		TbSysDictObj dict = new TbSysDictObj("OS_FILESYSTEM_FORMAT");
		theForm.setSdList(tbSysDictService
				.queryTbSysDictEffectList(dict));
		return "addOsTemplPart";
	}
	public String saveOsTemplatePart(){
		OsTemplatePart obj = new OsTemplatePart();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.saveOsTemplatePart(obj);
		//return "redirectPart";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplatePart();
		
	}
	public String deleteOsTemplatePart(){
		OsTemplatePart obj = new OsTemplatePart();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.deleteOsTemplatePart(obj);
		//return "redirectPart";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplatePart();
	}
	public String updateOsTemplatePart(){
		
		TbSysDictObj dict = new TbSysDictObj("OS_FILESYSTEM_FORMAT");
		theForm.setSdList(tbSysDictService
				.queryTbSysDictEffectList(dict));
		
		OsTemplatePart obj = new OsTemplatePart();
		BeanUtils.copyProperties(theForm, obj);
		obj = osTemplateComponetService.queryOsTemplatePart(obj);
		BeanUtils.copyProperties(obj,theForm);
		return "updateOsTemplPart";
	}
	public String modifyOsTemplatePart(){
		OsTemplatePart obj = new OsTemplatePart();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.modifyOsTemplatePart(obj);
		//return "redirectPart";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplatePart();
	}
	public String showOsTemplatePart(){
		if(theForm == null){
			theForm = new OsTemplateComponetForm();
		}
		OsTemplatePart obj = new OsTemplatePart();
		BeanUtils.copyProperties(theForm, obj);
		theForm.setPartList(osTemplateComponetService
				.queryOsTemplatePartList(obj));
		return "osTemplPart";
	}
	public String validOsTemplatePart(){
		OsTemplatePart obj = new OsTemplatePart();
		obj.setTemplate_id(theForm.getTemplate_id());
		obj = osTemplateComponetService.queryOsTemplatePart(obj);
		
		int msg = -1;
		if(obj.getId()==null||
				"".equals(obj.getId())){
			msg = 0;
		}else if(theForm.getId().equals(obj.getId())){
			msg = 0;
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
	/*=========================== Part End =============================*/
	
	/*---------------------------VolGroup Begin-----------------------------*/
	public String addOsTemplateVolGroup(){
		OsTemplatePart part = new OsTemplatePart();
		part.setTemplate_id(theForm.getTemplate_id());
		theForm.setPartList(osTemplateComponetService
				.queryOsTemplatePartList(part));
		return "addOsTemplVolGroup";
	}
	public String saveOsTemplateVolGroup(){
		OsTemplateVolGroup obj = new OsTemplateVolGroup();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.saveOsTemplateVolGroup(obj);
		//return "redirectVolGroup";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateVolGroup();
		
	}
	public String deleteOsTemplateVolGroup(){
		OsTemplateVolGroup obj = new OsTemplateVolGroup();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.deleteOsTemplateVolGroup(obj);
		//return "redirectVolGroup";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateVolGroup();
	}
	public String updateOsTemplateVolGroup(){
		
		OsTemplatePart part = new OsTemplatePart();
		part.setTemplate_id(theForm.getTemplate_id());
		theForm.setPartList(osTemplateComponetService
				.queryOsTemplatePartList(part));
		
		OsTemplateVolGroup obj = new OsTemplateVolGroup();
		BeanUtils.copyProperties(theForm, obj);
		obj = osTemplateComponetService.queryOsTemplateVolGroup(obj);
		BeanUtils.copyProperties(obj,theForm);
		return "updateOsTemplVolGroup";
	}
	public String modifyOsTemplateVolGroup(){
		OsTemplateVolGroup obj = new OsTemplateVolGroup();
		BeanUtils.copyProperties(theForm, obj);
		osTemplateComponetService.modifyOsTemplateVolGroup(obj);
		//return "redirectVolGroup";
		resetOsTemplateComponetForm(theForm);
		return showOsTemplateVolGroup();
	}
	public String showOsTemplateVolGroup(){
		if(theForm == null){
			theForm = new OsTemplateComponetForm();
		}
		OsTemplateVolGroup obj = new OsTemplateVolGroup();
		BeanUtils.copyProperties(theForm, obj);
		theForm.setVgList(osTemplateComponetService
				.queryOsTemplateVolGroupList(obj));
		return "osTemplVolGroup";
	}
	public String validOsTemplateVolGroup(){
		OsTemplateVolGroup obj = new OsTemplateVolGroup();
		obj.setTemplate_id(theForm.getTemplate_id());
		obj = osTemplateComponetService.queryOsTemplateVolGroup(obj);
		
		int msg = -1;
		if(obj.getId()==null||
				"".equals(obj.getId())){
			msg = 0;
		}else if(theForm.getId().equals(obj.getId())){
			msg = 0;
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
	/*=========================== VolGroup End =============================*/	
	
	private void resetOsTemplateComponetForm(OsTemplateComponetForm theForm){
		theForm.setId("");
		theForm.setFilesystem_name("");
		theForm.setFilesystem_type("");
		theForm.setFilesystem_size("");
		theForm.setFilesystem_desc("");
		theForm.setVolume_group("");
		theForm.setLogical_volume("");
	
		theForm.setGroup_id("");
		theForm.setGroup_name("");
		theForm.setGroup_desc("");
		
		theForm.setUser_name("");
		theForm.setPass_word("");
		theForm.setPrimary_group("");
		theForm.setHome_dir("");
		theForm.setUser_desc("");
		
		theForm.setUser_id("");
		theForm.setFlag("");
		
		theForm.setSoft_name("");
		theForm.setSoft_type("");
		theForm.setSoft_desc("");
		
		theForm.setPart_name("");
		theForm.setPart_fstype("");
		theForm.setPart_grow("");
		theForm.setPart_ondisk("");
		theForm.setPart_size("");
		theForm.setFilesystem_desc("");
		
		theForm.setVg_name("");
		theForm.setVg_pvno("");
		theForm.setVg_pesize("");
		theForm.setVg_desc("");
	}
	
	public OsTemplateComponetForm getTheForm() {
		return theForm;
	}
	public void setTheForm(OsTemplateComponetForm theForm) {
		this.theForm = theForm;
	}
	
}
