package com.sitech.basd.component.tree.web.rule;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.component.tree.domain.rule.RuleValidateObj;
import com.sitech.basd.component.tree.service.rule.RuleValidateService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

@Component("ruleValidateAction")
@Scope("prototype")
public class RuleValidateAction extends BaseAction {
	@Autowired
	private RuleValidateService ruleValidateService;
	private RuleValidateForm theForm;

	public RuleValidateForm getTheForm() {
		return theForm;
	}

	public void setTheForm(RuleValidateForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: list
	 * @Description: 查询列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 7, 2013 10:24:41 AM
	 */
	public String list() {
		if (theForm == null) {
			theForm = new RuleValidateForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String task_id = request.getParameter("task_id");
		request.setAttribute("task_id", task_id);
		RuleValidateObj obj = new RuleValidateObj();
		if (task_id != null && !"".equals(task_id)) {
			obj.setTask_id(task_id);
		}
		List resultList = ruleValidateService.queryForList(obj);
		theForm.setResultList(resultList);
		return "list";
	}

	/**
	 * 
	 * @Title: edit
	 * @Description: 进入添加或修改页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 7, 2013 10:32:58 AM
	 */
	public String edit() {
		if (theForm == null) {
			theForm = new RuleValidateForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String task_id = request.getParameter("task_id");
		if (id != null && !"".equals(id)) {
			RuleValidateObj obj = new RuleValidateObj();
			obj.setId(id);
			List<RuleValidateObj> list = ruleValidateService.queryForList(obj);
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

		}
		request.setAttribute("id", id);
		request.setAttribute("task_id", task_id);
		return "edit";

	}

	/**
	 * 
	 * @Title: save
	 * @Description: 保存记录
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 7, 2013 10:53:14 AM
	 */
	public String save() {
		if (theForm == null) {
			theForm = new RuleValidateForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("id");
		String task_id = request.getParameter("task_id");
		// TbSysUserinfoObj user = (TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		String account = session.get("account").toString();
		RuleValidateObj obj = new RuleValidateObj();
		JSONObject array = new JSONObject();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (id != null && !"".equals(id) && !"null".equals(id)) {
			obj.setId(id);
			ruleValidateService.updateByObj(obj);
		} else {
			String uuid = RandomUUID.getUuid();
			obj.setTask_id(task_id);
			obj.setId(uuid);
			obj.setRule_maker(account);
			ruleValidateService.insertByObj(obj);
			array = JSONObject.fromObject(obj);

		}
		// out = response.getWriter();
		// out.println(array);
		// out.close();
		PrintWriterOut.printWirter(response, array);
		return null;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description:删除一条记录
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 7, 2013 11:10:36 AM
	 */
	public String delete() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		RuleValidateObj obj = new RuleValidateObj();
		if (id != null && !"".equals(id)) {
			obj.setId(id);
			ruleValidateService.deleteByObj(obj);
		}
		return list();
	}
}
