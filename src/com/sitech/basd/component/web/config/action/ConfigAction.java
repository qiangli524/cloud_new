package com.sitech.basd.component.web.config.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.domain.config.ConfigInfoObj;
import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.component.service.config.ConfigService;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.component.web.config.form.ConfigForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

@Controller("configAction")
@Scope("prototype")
public class ConfigAction extends BaseAction {
	@Autowired
	private ConfigService configService;
	@Autowired
	private UserService userService;
	private ConfigForm theForm;

	public ConfigForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ConfigForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: ConfigInfoList
	 * @Description: 查询配置文件列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 10:04:03 AM
	 */
	public String list() {
		if (theForm == null) {
			theForm = new ConfigForm();
		}
		ConfigInfoObj obj = new ConfigInfoObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		if (theForm.getName() != null && !"".equals(theForm.getName())) {
			obj.setName(theForm.getName());
		}
		if (theForm.getType() != null && !"".equals(theForm.getType())) {
			obj.setType(theForm.getType());
		}
		List list = configService.queryConfigInfoList(obj);
		theForm.setResultList(list);
		return "list";
	}

	/**
	 * 
	 * @Title: add
	 * @Description: 添加配置文件
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 3:20:57 PM
	 */
	public String add() {
		return "add";
	}

	public String save() throws BaseException, UnsupportedEncodingException {
		if (theForm == null) {
			theForm = new ConfigForm();
		}

		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("id");
		String str = request.getParameter("selectUsers");
		// String name = request.getParameter("name");
		// name = URLDecoder.decode(name, "utf-8");
		// String type = request.getParameter("type");
		// String category = request.getParameter("category");
		ConfigInfoObj obj = new ConfigInfoObj();
		// TbSysUserinfoObj user = (TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		String account = session.get("account").toString();
		try {
			BeanUtils.copyProperties(obj, theForm);
			obj.setMod_user(account);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (id != null && !"".equals(id)) {
			obj.setId(id);
			configService.updateConfig(obj);
		} else {
			obj.setUpload_user(account);
			String[] users = str.split(",");
			for (int i = 0; i < users.length; i++) {
				obj.setUser_id(users[i]);
				String uuid = RandomUUID.getUuid();
				obj.setId(uuid);
				configService.insertConfig(obj);
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
	 * @Title: mod
	 * @Description: 修改配置文件
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 8:54:47 AM
	 */
	public String mod() {
		if (theForm == null) {
			theForm = new ConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		Map map = new HashMap();
		String id = request.getParameter("id");
		ConfigInfoObj obj = new ConfigInfoObj();
		obj.setId(id);
		List<ConfigInfoObj> list = configService.queryConfigInfoList(obj);
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
		UserObj u = new UserObj();
		u.setId(obj.getUser_id());
		List<UserObj> userList = userService.list(u);
		if (userList != null && userList.size() > 0) {
			u = userList.get(0);
		}
		theForm.setIp(u.getIp());
		theForm.setUsername(u.getUsername());
		theForm.setPassword(u.getPassword());
		request.setAttribute("id", id);
		// request.setAttribute("ip", u.getIp());
		// request.setAttribute("username", u.getUsername());
		// request.setAttribute("password", u.getPassword());
		// request.setAttribute("path", obj.getPath());
		return "mod";
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
	 * @createtime May 23, 2013 4:45:23 PM
	 */
	public String delete() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		ConfigInfoObj obj = new ConfigInfoObj();
		obj.setId(id);
		configService.deleteConfig(obj);
		configService.deleteConfigRelation(obj);
		return list();
	}

	/**
	 * 
	 * @Title: listConfigDetail
	 * @Description: 获取文件内容
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 24, 2013 1:17:14 PM
	 */
	public String listConfigContent() {
		if (theForm == null) {
			theForm = new ConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		ConfigInfoObj obj = new ConfigInfoObj();
		obj.setId(id);
		Map map = new HashMap();
		List<ConfigInfoObj> list = configService.queryConfigInfoList(obj);
		if (list != null && list.size() > 0) {
			obj = list.get(0);
		}
		request.setAttribute("ip", obj.getIp());
		request.setAttribute("name", obj.getUsername());
		request.setAttribute("pwd", obj.getPassword());
		theForm.setResultList(list);
		return "content";
	}
}
