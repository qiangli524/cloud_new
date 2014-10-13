package com.sitech.ssd.sc.os.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.ssd.sc.os.domain.HostAdapter;
import com.sitech.ssd.sc.os.domain.HostModel;
import com.sitech.ssd.sc.os.domain.OsFileSystemModel;
import com.sitech.ssd.sc.os.domain.OsGroupModel;
import com.sitech.ssd.sc.os.domain.OsGroupUserModel;
import com.sitech.ssd.sc.os.domain.OsPartModel;
import com.sitech.ssd.sc.os.domain.OsSoftModel;
import com.sitech.ssd.sc.os.domain.OsUserModel;
import com.sitech.ssd.sc.os.domain.OsVolGroupModel;
import com.sitech.ssd.sc.os.service.OsFileSystemService;
import com.sitech.ssd.sc.os.service.OsGroupService;
import com.sitech.ssd.sc.os.service.OsGroupUserService;
import com.sitech.ssd.sc.os.service.OsHostAdapterService;
import com.sitech.ssd.sc.os.service.OsHostService;
import com.sitech.ssd.sc.os.service.OsPartService;
import com.sitech.ssd.sc.os.service.OsSoftService;
import com.sitech.ssd.sc.os.service.OsUserService;
import com.sitech.ssd.sc.os.service.OsVolGroupService;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;
import com.sitech.ssd.sc.ostempl.service.OsTemplateService;
import com.sitech.utils.date.DateUtil;
import com.sitech.utils.randomid.RandomUUID;

/**
 * @ClassName AutoOSAction
 * @Desc 自动安装OS
 * @Author JamTau
 * @date May 22, 2014 8:57:49 AM
 */

@Controller("autoOSAction")
@Scope("prototype")
public class AutoOSAction extends CRUDBaseAction {

	private static final long serialVersionUID = -7932951166055819211L;
	private AutoOSForm theForm = null;
	private String id;
	@Resource
	private OsHostService osHostService;
	@Resource
	private OsHostAdapterService osHostAdapterService;
	@Resource
	private OsFileSystemService osFileSystemService;
	@Resource
	private OsGroupService osGroupService;
	@Resource
	private OsUserService osUserService;
	@Resource
	private OsSoftService osSoftService;
	@Resource
	private OsPartService osPartService;
	@Resource
	private OsVolGroupService osVolGroupService;
	@Resource
	private OsGroupUserService osGroupUserService;
	@Resource
	private OsTemplateService osTemplateService;
	
	/**
	 * @Tittle listOsHost
	 * @Description 响应查询按钮
	 * @Modify Date May 22, 2014 5:56:51 PM
	 * @return String
	 */
	public String listOsHost() {
		HostModel host = new HostModel();
		if (theForm == null)
			theForm = new AutoOSForm();
		if (theForm.getSerial_num() != null && theForm.getSerial_num() != "") {
			host.setSerial_num(theForm.getSerial_num());
		}
		if(theForm.getMge_console_ip() != null && !theForm.getMge_console_ip().equals("")){
			host.setMge_console_ip(theForm.getMge_console_ip());
		}
		if (theForm.getEq_type() != null && theForm.getEq_type() != "") {
			host.setEq_type(theForm.getEq_type());
		}
		if (theForm.getStay_machroom() != null
				&& theForm.getStay_machroom() != "") {
			host.setStay_machroom(theForm.getStay_machroom());
		}
		if (theForm.getInstall_state() != null
				&& theForm.getInstall_state() != "") {
			host.setInstall_state(theForm.getInstall_state());
		}
		host.setPagination(this.getPaginater().initPagination(request));
		List result = osHostService.listOsHost(host);
		theForm.setResultList(result);
		return "list";
	}

	/**
	 * @Tittle deleteOsHost
	 * @Description 响应删除按钮
	 * @Modify Date May 22, 2014 5:56:28 PM
	 * @return String
	 */
	public String deleteOsHost() {
		String id = request.getParameter("id");
		if (id != null && !id.equals("")) {
			HostModel host = new HostModel();
			String[] ids = id.split(",");
			for (int i = 0; i < ids.length; i++) {
				host.setId(ids[i]);
				osHostService.deleteOsHost(host);
			}
		}
		return listOsHost();
	}

	/**
	 * @Tittle editOsHost
	 * @Description 响应修改按钮
	 * @Modify Date May 22, 2014 6:00:26 PM
	 * @return String
	 */
	public String modifyOsHost() {
		String id = request.getParameter("id");
		HostModel host = new HostModel();
		if (id != null && !"".equals(id)) {
			host.setId(id);
			host = osHostService.queryForObject(host);
		}
		BeanUtils.copyProperties(host,theForm);
		return "modify";
	}

	/**
	 * @Tittle modifyOsHost
	 * @Description 保存修改记录
	 * @Modify Date May 22, 2014 6:03:49 PM
	 * @return String
	 */
	public String updateOsHost() {
		return "updateOsHost";
	}

	/**
	 * @Tittle addOsHost
	 * @Description 响应添加按钮
	 * @Modify Date May 22, 2014 6:04:44 PM
	 * @return String
	 */
	public String addOsHost() {
		theForm = null;
		return "addOsHost";
	}

	/**
	 * @Tittle insertOsHost
	 * @Description
	 * @Modify Date May 22, 2014 6:06:16 PM
	 * @return String
	 */
	public void insertOsHost() {
		HostModel host = new HostModel();
		
		BeanUtils.copyProperties(theForm,host);
		String insert_date = "";
		insert_date = DateUtil.getCurrentDateStr();
		host.setInsert_date(insert_date);
		host.setId(RandomUUID.getUuid());
		osHostService.addOsHost(host);
		// return null;
	}

	public String validUserInfo(){
		HostModel _host = new HostModel(theForm.getId());
		_host = osHostService.queryForObject(_host);
		BeanUtils.copyProperties(_host,theForm);
		//清空密码字段
		theForm.setMge_console_pass("");
		return "validUserInfo";
	}
	
	public String validUserPassWD(){
		int msg = 0;
		HostModel _host = new HostModel(theForm.getId());
		_host = osHostService.queryForObject(_host);
		
		String m_mcpd = _host.getMge_console_pass();
		if(m_mcpd==null || "".equals(m_mcpd)){
			msg = -2;//没有设置管理密码
		}else{
			String f_mcpd = theForm.getMge_console_pass();
			if(!f_mcpd.equals(m_mcpd)){
				msg = -1;//输入密码错误
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
	/**
	 * @Tittle configInstall
	 * @Description 响应OS安装链接，展现配置页面
	 * @Modify Date May 23, 2014 2:31:53 PM
	 * @return String
	 */
	public String configInstall() {
		return "OsConfig";
	}
	
	/**
	 * @Title: configOsBase
	 * @param  输入参数  基础配置
	 * @return String 返回类型
	 * @throws
	 */
	public String configOsBase(){
		HostModel _host = new HostModel(theForm.getId());
		_host = osHostService.queryForObject(_host);
		BeanUtils.copyProperties(_host,theForm);
		theForm.setTemplList(osTemplateService
				.queryOsTemplateList(new OsTemplate()));
		return "OsConfigBase";
	}

	
	/**
	 * @Title: saveConfigOsBase
	 * @Description: saveConfigOsBase
	 * @param  输入参数
	 * @return String 返回类型
	 * @throws
	 */
	public String saveConfigOsBase(){
				
		HostModel host = new HostModel();
		BeanUtils.copyProperties(theForm,host);
		int msg = osHostService.modifyOsHost(host);//需要查看修改字段
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
	
	/**
	 * @Title: updateCustomedInstall
	 * @Description: 
	 * 		修改TB_OS_HOST为已配置状态
	 * 		【批量安装做准备】
	 * @Author: JamTau
	 * @Date 2014-7-15 下午06:12:12 void
	 */
	public String updateCustomedInstall() {
		HostModel host = new HostModel();
		host.setId(theForm.getId());
		host.setInstall_state("4");// 4 参数已配置(不安装系统)
		int msg = osHostService.updateInstallState(host);
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

	/**
	 * @Title: customedInstall
	 * @Description: 
	 * 		安装操作系统，更新状态
	 * @Author: JamTau
	 * @Date 2014-7-15 下午06:12:12 void
	 */
	public String customedInstall() {
		
		HostModel host = new HostModel();
		BeanUtils.copyProperties(theForm,host);
		HostModel temp = osHostService.queryForObject(host);//拷贝模版，不提倡
		
		OsFileSystemModel filesystem = new OsFileSystemModel();
		filesystem.setOs_host_id(temp.getId());
		filesystem.setFlag("1");//来自模版的数据
		osFileSystemService.deleteOsFileSystemList(filesystem);
		filesystem.setId(temp.getOs_template());
		osFileSystemService.copyOsTemplateFileSystem(filesystem);
		
		OsGroupModel group = new OsGroupModel();
		group.setOs_host_id(temp.getId());
		group.setFlag("1");
		osGroupService.deleteOsGroup(group);
		group.setId(temp.getOs_template());
		osGroupService.copyOsTemplateGroup(group);
		
		OsUserModel user = new OsUserModel();
		user.setOs_host_id(temp.getId());
		user.setFlag("1");
		osUserService.deleteOsUser(user);
		user.setId(temp.getOs_template());
		osUserService.copyOsTemplateUser(user);
		
		OsGroupUserModel groupuser = new OsGroupUserModel();
		groupuser.setOs_host_id(temp.getId());
		groupuser.setFlag("1");
		osGroupUserService.deleteOsGroupUser(groupuser);
		groupuser.setId(temp.getOs_template());
		osGroupUserService.copyOsTemplateGroupUser(groupuser);
		
		OsSoftModel soft = new OsSoftModel();
		soft.setOs_host_id(temp.getId());
		soft.setFlag("1");
		osSoftService.deleteOsTemplateSoft(soft);
		soft.setId(temp.getOs_template());
		osSoftService.copyOsTemplateSoft(soft);
		
		OsPartModel part = new OsPartModel();
		part.setOs_host_id(temp.getId());
		part.setFlag("1");
		osPartService.deleteOsTemplatePart(part);
		part.setId(temp.getOs_template());
		osPartService.copyOsTemplatePart(part);
		
		OsVolGroupModel vg = new OsVolGroupModel();
		vg.setOs_host_id(temp.getId());
		vg.setFlag("1");
		osVolGroupService.deleteOsTemplateVolGroup(vg);
		vg.setId(temp.getOs_template());
		osVolGroupService.copyOsTemplateVolGroup(vg);

		host.setInstall_state("1");// 1 完成参数配置，立即安装系统		
		String msg = osHostService.customedInstall(host);
		
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

	public String clearOsHostConfig() {
		HostModel host = new HostModel();
		BeanUtils.copyProperties(theForm,host);
		osHostService.clearOsHostConfig(host);
		try {
			response.setContentType("text/html;charset=utf-8"); 
			PrintWriter out = response.getWriter();
			out.print("OK");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String batchCustomedInstall() {

		String ids = request.getParameter("ids") == null ? "" : request
				.getParameter("ids");
		String msg = "";
		if (!"".equals(ids)) {
			ids = ids.substring(0, ids.length() - 1);
			msg = osHostService.batchCustomedInstall(ids);
		} else {
			msg = "0";
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


	/**
	 * @Title: showHostDetail 
	 * @Description: 
	 * @Author: JamTau
	 * @Date 2014-7-25 下午03:50:34
	 * @return  String
	 */
	public String showHostDetail(){
		
		String id = request.getParameter("id")==null ? "" 
				:request.getParameter("id");
		
		HostModel _host =  new HostModel(id);
		if (id != null && !"".equals(id)) {
			_host = osHostService.queryForObject(_host);
		}
		if (theForm == null) {
			theForm = new AutoOSForm();
		}
		
		BeanUtils.copyProperties(_host,theForm);
		Map map = osHostService.queryHostBuss(_host);
		theForm.setOwnResPool((String)map.get("ownResPool"));
		theForm.setOwnResPoolid("ownResPoolid");
		theForm.setBussId("bussId");
		theForm.setBussName((String)map.get("bussName"));
		
		theForm.setTemplList(osTemplateService
				.queryOsTemplateList(new OsTemplate()));
		theForm.setAdptList(osHostAdapterService
				.queryHostAdapterList(
						new HostAdapter(theForm.getId())));
		OsFileSystemModel fs = new OsFileSystemModel(theForm.getId());
		theForm.setFsList(osFileSystemService.queryOsFileSystemList(fs));
		
		OsGroupModel group = new OsGroupModel(theForm.getId());
		theForm.setGroupList(osGroupService.queryOsGroupList(group));
		
		OsUserModel user = new OsUserModel(theForm.getId());
		theForm.setUserList(osUserService.showOsUserList(user));
		
		OsGroupUserModel gu = new OsGroupUserModel(theForm.getId());
		theForm.setGuList(osGroupUserService.showOsGroupUserList(gu));
		
		OsSoftModel soft = new OsSoftModel(theForm.getId());
		theForm.setSoftList(osSoftService.queryOsSoftList(soft));
		
		OsPartModel part = new OsPartModel(theForm.getId());
		theForm.setPartList(osPartService.queryOsPartList(part));
		
		OsVolGroupModel vg = new OsVolGroupModel(theForm.getId());
		theForm.setVgList(osVolGroupService.queryOsVolGroupList(vg));
		
		theForm.setTemplList(osTemplateService
				.queryOsTemplateList(new OsTemplate()));		
		
		return "hostDetail";
	}
	
	/**
	 * 
	 * @Title: osConfigDetail
	 * @Description: 呈现主机(ALL)安装配置信息
	 * @param  输入参数
	 * @return String 返回类型
	 * @throws
	 * 
	 * OsConfigDetail.jsp
	 */
	public String osConfigDetail(){
		HostModel _host =  new HostModel(theForm.getId());
		if (theForm.getId() != null && !"".equals(theForm.getId())) {
			_host = osHostService.queryForObject(_host);
		}
		BeanUtils.copyProperties(_host,theForm);
		Map map = osHostService.queryHostBuss(_host);
		theForm.setOwnResPool((String)map.get("ownResPool"));
		theForm.setOwnResPoolid("ownResPoolid");
		theForm.setBussId("bussId");
		theForm.setBussName((String)map.get("bussName"));
		theForm.setAdptList(osHostAdapterService
				.queryHostAdapterList(
						new HostAdapter(theForm.getId())));
		
		OsFileSystemModel fs = new OsFileSystemModel(theForm.getId());
		fs.setId(_host.getOs_template());
		theForm.setFsList(osFileSystemService.unionOsFileSystemList(fs));
		
		OsGroupModel group = new OsGroupModel(theForm.getId());
		group.setId(_host.getOs_template());
		theForm.setGroupList(osGroupService.unionOsGroupList(group));
		
		OsUserModel user = new OsUserModel(theForm.getId());
		user.setId(_host.getOs_template());
		theForm.setUserList(osUserService.unionOsUserList(user));
		
		OsGroupUserModel gu = new OsGroupUserModel(theForm.getId());
		gu.setId(_host.getOs_template());
		theForm.setGuList(osGroupUserService.unionOsGroupUserList(gu));

		OsSoftModel soft = new OsSoftModel(theForm.getId());
		soft.setId(_host.getOs_template());
		theForm.setSoftList(osSoftService.unionOsSoftList(soft));
		
		OsPartModel part = new OsPartModel(theForm.getId());
		part.setId(_host.getOs_template());
		theForm.setPartList(osPartService.unionOsPartList(part));
		
		OsVolGroupModel vg = new OsVolGroupModel(theForm.getId());
		vg.setId(_host.getOs_template());
		theForm.setVgList(osVolGroupService.unionOsVolGroupList(vg));
		
		theForm.setTemplList(osTemplateService
				.queryOsTemplateList(new OsTemplate()));
		return "OsConfigDetail";
	}
	
	public AutoOSForm getTheForm() {
		return theForm;
	}

	public void setTheForm(AutoOSForm theForm) {
		this.theForm = theForm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
