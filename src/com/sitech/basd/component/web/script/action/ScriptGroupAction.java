package com.sitech.basd.component.web.script.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.component.domain.script.ScriptGroupObj;
import com.sitech.basd.component.domain.script.ScriptRelationObj;
import com.sitech.basd.component.service.script.ScriptsService;
import com.sitech.basd.component.web.script.form.ScriptForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

@Component("scriptGroupAction")
@Scope("prototype")
public class ScriptGroupAction extends BaseAction {
	@Autowired
	private ScriptsService scriptsService;

	private ScriptForm theForm;

	public ScriptForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ScriptForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询脚本信息列表
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String list() {
		if (theForm == null) {
			theForm = new ScriptForm();
		}

		ScriptGroupObj obj = new ScriptGroupObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		if (theForm.getName() != null && !"".equals(theForm.getName())) {
			obj.setName(theForm.getName());
		}
		List resultList = scriptsService.queryScriptGroupList(obj);
		theForm.setResultList(resultList);
		return "list";
	}

	/**
	 * 
	 * @Title: edit
	 * @Description: 进入编辑脚本页面（包含添加）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 2:05:12 PM
	 */
	public String edit() {
		if (theForm == null) {
			theForm = new ScriptForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		if (id != null && !"".equals(id) && !"null".equals(id)) {
			ScriptGroupObj obj = new ScriptGroupObj();
			obj.setId(id);
			List<ScriptGroupObj> list = scriptsService.queryScriptGroupList(obj);
			if (list != null && list.size() > 0) {
				obj = list.get(0);
				try {
					BeanUtils.copyProperties(theForm, obj);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("id", id);
		return "edit";
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 保存信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 3:51:22 PM
	 */
	public String save() {
		if (theForm == null) {
			theForm = new ScriptForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("id");
		ScriptGroupObj obj = new ScriptGroupObj();
		// TbSysUserinfoObj user = (TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (id != null && !"".equals(id) && !"null".equals(id)) {
			obj.setId(id);
			scriptsService.updateGroup(obj);
		} else {
			String uuid = RandomUUID.getUuid();
			obj.setId(uuid);
			scriptsService.insertGroup(obj);

		}
		// out = response.getWriter();
		// out.println("1");
		// out.close();
		PrintWriterOut.printWirter(response, "1");
		return null;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除一条记录
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 3:03:15 PM
	 */
	public String delete() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		ScriptGroupObj obj = new ScriptGroupObj();
		obj.setId(id);
		scriptsService.deleteGroup(obj);
		return list();
	}

	/**
	 * 
	 * @Title: member
	 * @Description: 成员设置
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 6:14:17 PM
	 */
	public String member() {
		if (theForm == null) {
			theForm = new ScriptForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String group_id = request.getParameter("group_id");
		request.setAttribute("id", group_id);
		ScriptRelationObj obj = new ScriptRelationObj();
		obj.setGroup_id(group_id);
		List rList = scriptsService.queryRemainList(obj);
		if (rList != null && rList.size() > 0) {
			theForm.setResultList(rList);
		} else {
			theForm.setResultList(new ArrayList());
		}
		List selectedList = scriptsService.querySelectedList(obj);
		theForm.setSelectedList(selectedList);
		request.setAttribute("group_id", group_id);
		return "member";
	}

	/**
	 * 
	 * @Title: saveGroupMember
	 * @Description: 保存组成员
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 24, 2013 11:20:05 AM
	 */
	public String saveGroupMember() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String str = request.getParameter("str");
		String group_id = request.getParameter("group_id");
		String[] members = str.split(",");
		ScriptRelationObj obj = new ScriptRelationObj();
		obj.setGroup_id(group_id);
		scriptsService.deleteGroupMember(obj);
		for (int i = 0; i <= members.length - 1; i++) {
			obj.setScript_id(members[i]);
			scriptsService.insertGroupMember(obj);
		}
		return list();
	}

	/**
	 * 
	 * @Title: listMemeber
	 * @Description: 获取组成员信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 14, 2013 3:02:36 PM
	 */
	public String listMember() {
		if (theForm == null) {
			theForm = new ScriptForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String group_id = request.getParameter("group_id");
		ScriptRelationObj obj = new ScriptRelationObj();
		obj.setGroup_id(group_id);
		List selectedList = scriptsService.querySelectedList(obj);
		theForm.setSelectedList(selectedList);
		return "listmember";
	}
}
