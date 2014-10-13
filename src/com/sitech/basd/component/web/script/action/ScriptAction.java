package com.sitech.basd.component.web.script.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.component.domain.script.ScriptObj;
import com.sitech.basd.component.service.script.ScriptsService;
import com.sitech.basd.component.web.script.form.ScriptForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

@SuppressWarnings("all")
@Component("scriptAction")
@Scope("prototype")
public class ScriptAction extends BaseAction {

	@Autowired
	private ScriptsService scriptsService;

	private ScriptForm theForm;
	private String id;
	private String oper;
	private String script;
	private String runResult;

	public String getRunResult() {
		return runResult;
	}

	public void setRunResult(String runResult) {
		this.runResult = runResult;
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
		HttpServletRequest request = Struts2Utils.getRequest();
		if (oper == null) {
			oper = request.getParameter("oper");// 判断是不是进程使用list
		}
		ScriptObj obj = new ScriptObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		if (theForm.getName() != null && !"".equals(theForm.getName())) {
			obj.setName(theForm.getName());
		}
		if (theForm.getType() != null && !"".equals(theForm.getType())) {
			obj.setType(theForm.getType());
		}
		if (theForm.getDescription() != null && !"".equals(theForm.getDescription())) {
			obj.setDescription(theForm.getDescription());
		}
		if (theForm.getIp() != null && !"".equals(theForm.getIp())) {
			obj.setIp(theForm.getIp());
		}
		if (theForm.getApp_type() != null && !"".equals(theForm.getApp_type())) {
			obj.setApp_type(theForm.getApp_type());
		}
		List resultList = scriptsService.queryForList(obj);
		theForm.setResultList(resultList);
		/**
		 * 如果是为进程使用的，那么返回相应的页面
		 */
		if ("process".equals(oper)) {
			if (script == null) {
				script = request.getParameter("script");// 判断是不是进程使用list
			}
			return "listprocess";
		}
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
			ScriptObj obj = new ScriptObj();
			obj.setId(id);
			List<ScriptObj> list = scriptsService.queryForList(obj);
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

	public String save() {
		if (theForm == null) {
			theForm = new ScriptForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("id");
		String str = request.getParameter("selectUsers");
		ScriptObj obj = new ScriptObj();
		// TbSysUserinfoObj user = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		String account = session.get("account").toString();
		try {

			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (id != null && !"".equals(id) && !"null".equals(id)) {
			obj.setId(id);
			obj.setUpdate_person(account);
			scriptsService.updateByObj(obj);
		} else {
			obj.setUpload_person(account);
			String[] users = str.split(",");
			for (int i = 0; i < users.length; i++) {
				obj.setUser_id(users[i]);
				String uuid = RandomUUID.getUuid();
				obj.setId(uuid);
				scriptsService.insertByObj(obj);
			}

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
		ScriptObj obj = new ScriptObj();
		obj.setId(id);
		scriptsService.deleteByObj(obj);
		scriptsService.deleteRelation(obj);
		return list();
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		String result = "";
		List<ScriptObj> list = new ArrayList<ScriptObj>();
		ScriptObj obj = new ScriptObj();
		if (id != null && !"".equals(id)) {
			obj.setId(id);
		}
		list = scriptsService.queryForList(obj);
		ScriptObj sObj = list.get(0);
		if (sObj != null) {
			result = scriptsService.executeScript(sObj);
		}
		sObj.setCount(sObj.getCount() + 1);
		scriptsService.updateByObj(sObj);
		try {
			JSONObject json = new JSONObject();
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setCharacterEncoding("UTF-8");
			json.put("result", result);
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: alert
	 * @Description: 弹出执行结果
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime 2014-9-11 下午6:52:15
	 */
	public String alert() throws UnsupportedEncodingException {
		return "alert";
	}

	public ScriptForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ScriptForm theForm) {
		this.theForm = theForm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}
