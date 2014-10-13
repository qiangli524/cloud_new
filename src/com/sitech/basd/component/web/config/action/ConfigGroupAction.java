package com.sitech.basd.component.web.config.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.domain.config.ConfigGroupObj;
import com.sitech.basd.component.domain.config.ConfigRelationObj;
import com.sitech.basd.component.service.config.ConfigService;
import com.sitech.basd.component.web.config.form.ConfigGroupForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

@Controller("configGroupAction")
@Scope("prototype")
public class ConfigGroupAction extends BaseAction {
	@Autowired
	private ConfigService configService;
	private ConfigGroupForm theForm;

	public ConfigGroupForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ConfigGroupForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: list
	 * @Description: 获取配置文件组列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 4:51:29 PM
	 */
	public String list() {
		if (theForm == null) {
			theForm = new ConfigGroupForm();
		}
		ConfigGroupObj obj = new ConfigGroupObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		if (theForm.getName() != null && !"".equals(theForm.getName())) {
			obj.setName(theForm.getName());
		}
		List list = configService.queryConfigGroupList(obj);
		theForm.setResultList(list);
		return "list";
	}

	/**
	 * 
	 * @Title: add
	 * @Description: 增加组
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 5:01:27 PM
	 */
	public String add() {
		return "add";
	}

	/**
	 * 
	 * @Title: mod
	 * @Description: 修改组
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 5:02:42 PM
	 */
	public String mod() {
		if (theForm == null) {
			theForm = new ConfigGroupForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		ConfigGroupObj obj = new ConfigGroupObj();
		obj.setId(id);
		List<ConfigGroupObj> list = configService.queryConfigGroupList(obj);
		if (list != null && list.size() > 0) {
			obj = list.get(0);
		}
		try {
			BeanUtils.copyProperties(theForm, obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		request.setAttribute("id", id);
		return "mod";
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 保存组
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 5:07:29 PM
	 */
	public String save() {
		if (theForm == null) {
			theForm = new ConfigGroupForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		ConfigGroupObj obj = new ConfigGroupObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (id != null && !"".equals(id)) {
			obj.setId(id);
			configService.updateConfigGroup(obj);
		} else {
			String uuid = RandomUUID.getUuid();
			obj.setId(uuid);
			configService.insertConfigGroup(obj);
		}
		return list();
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除组
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 6:03:40 PM
	 */
	public String delete() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		ConfigGroupObj obj = new ConfigGroupObj();
		obj.setId(id);
		configService.deleteConfigGroup(obj);
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
			theForm = new ConfigGroupForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String group_id = request.getParameter("group_id");
		request.setAttribute("id", group_id);
		ConfigRelationObj obj = new ConfigRelationObj();
		obj.setGroup_id(group_id);
		List rList = configService.queryRemainList(obj);
		if (rList != null && rList.size() > 0) {
			theForm.setResultList(rList);
		} else {
			theForm.setResultList(new ArrayList());
		}
		List selectedList = configService.querySelectedList(obj);
		theForm.setSelectedList(selectedList);
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
		int data = 0;
		HttpServletRequest request = Struts2Utils.getRequest();
		String str = request.getParameter("str");
		String group_id = request.getParameter("group_id");
		String[] members = str.split(",");
		ConfigRelationObj obj = new ConfigRelationObj();
		obj.setGroup_id(group_id);
		configService.deleteGroupMember(obj);
		for (int i = 0; i <= members.length - 1; i++) {
			obj.setConfig_id(members[i]);
			configService.insertGroupMember(obj);
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// p = response.getWriter();
		// p.print(String.valueOf(data));
		// p.close();
		PrintWriterOut.printWirter(response, String.valueOf(data));
		return null;
	}

	/**
	 * 
	 * @Title: listMember
	 * @Description: 获取组成员列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 14, 2013 2:00:14 PM
	 */
	public String listMember() {
		if (theForm == null) {
			theForm = new ConfigGroupForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String group_id = request.getParameter("group_id");
		ConfigRelationObj obj = new ConfigRelationObj();
		obj.setGroup_id(group_id);
		List selectedList = configService.querySelectedList(obj);
		theForm.setSelectedList(selectedList);
		return "listmember";
	}
}
