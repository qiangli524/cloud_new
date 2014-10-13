package com.sitech.basd.component.web.user.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.component.web.user.form.UserForm;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.util.JSONUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

@SuppressWarnings("all")
@Component("userAction")
@Scope("prototype")
public class UserAction extends BaseAction {
	@Autowired
	private UserService userService;

	private UserForm theForm;
	@Autowired
	private BusiHostService busiHostService;

	public UserForm getTheForm() {
		return theForm;
	}

	public void setTheForm(UserForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: list
	 * @Description: 用于关联用户
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 4:51:04 PM
	 */
	public String list() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String userids = request.getParameter("userids");
		if (theForm == null) {
			theForm = new UserForm();
		}
		UserObj obj = new UserObj();
		if (theForm.getIp() != null && !"".equals(theForm.getIp())) {
			obj.setIp(theForm.getIp());
		}
		if (theForm.getUsername() != null && !"".equals(theForm.getUsername())) {
			obj.setUsername(theForm.getUsername());
		}
		// obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List<UserObj> list = userService.list(obj);
		if (list != null && list.size() > 0) {
			theForm.setResultList(list);
		}
		if (userids != null && !userids.equals("")) {
			request.setAttribute("userids", userids);
		}
		return "list";
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 保存IP用户信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 27, 2013 8:43:39 AM
	 */
	public String save() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String ip = request.getParameter("ip");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserObj u = new UserObj();
		u.setIp(ip);
		u.setUsername(username);
		u.setPassword(password);
		List list = userService.list(u);
		String result = "";
		if (list != null && list.size() > 0) {
			result = "-1";
		} else {
			String uuid = RandomUUID.getUuid();
			u.setId(uuid);
			userService.insertByObj(u);
			result = "1";
		}
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);

		// 占用IP未写
		return null;
	}

	/**
	 * 
	 * @Title: listUsers
	 * @Description:获取用户列表(用于管理用户)
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 26, 2013 2:08:40 PM
	 */
	public String listUsers() {
		if (theForm == null) {
			theForm = new UserForm();
		}
		UserObj obj = new UserObj();
		if (theForm.getIp() != null && !"".equals(theForm.getIp())) {
			obj.setIp(theForm.getIp());
		}
		if (theForm.getUsername() != null && !"".equals(theForm.getUsername())) {
			obj.setUsername(theForm.getUsername());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List<UserObj> list = userService.list(obj);
		if (list != null && list.size() > 0) {
			theForm.setResultList(list);
		}
		return "listUsers";
	}

	/**
	 * 
	 * @Title: editUser
	 * @Description: 编辑用户
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 26, 2013 2:12:23 PM
	 */
	public String editUser() {
		if (theForm == null) {
			theForm = new UserForm();
		}
		String id = request.getParameter("id");
		UserObj obj = new UserObj();
		if (id != null && !"".equals(id) && !"null".equals(id)) {
			obj.setId(id);
			List<UserObj> list = userService.list(obj);
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
	 * @Title: saveUser
	 * @Description: 保存用户记录（用于管理用户）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 26, 2013 4:34:23 PM
	 */
	public String saveUser() {
		if (theForm == null) {
			theForm = new UserForm();
		}
		String id = request.getParameter("id");
		UserObj u = new UserObj();
		try {
			BeanUtils.copyProperties(u, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int ret = 0;
		if (id != null && !"".equals(id) && !"null".equals(id)) {
			u.setId(id);
			ret = userService.updateByObj(u);
		} else {
			String uuid = RandomUUID.getUuid();
			u.setId(uuid);
			ret = userService.insertByObj(u);
		}
		// out = response.getWriter();
		// out.println(ret);
		// out.close();
		PrintWriterOut.printWirter(response, ret);
		return null;
	}

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除一条或者多条记录
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 26, 2013 4:09:18 PM
	 */
	public String delete() {
		String id = request.getParameter("id");
		UserObj obj = new UserObj();
		String[] ids = id.split(",");
		for (int i = 0; i <= ids.length - 1; i++) {
			obj.setId(ids[i]);
			userService.deleteByObj(obj);
		}
		return listUsers();
	}

	/**
	 * 
	 * @Title: getUserListByHostId
	 * @Description: 根据主机的id查找主机下的用户
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 27, 2013 9:41:18 AM
	 */
	public String getUserListByHostId() {
		String hostId = request.getParameter("hostId");
		TbBusiHostObj hostObj = new TbBusiHostObj();
		hostObj.setID(Integer.parseInt(hostId));
		hostObj = busiHostService.queryByObj(hostObj);
		Map<String, String> map = new HashMap<String, String>();
		UserObj user = new UserObj();
		user.setIp(hostObj.getIP());
		List lst = userService.queryForExampleUserListByObj(user);
		JSONArray ja = JSONArray.fromObject(lst);
		try {
			JSONUtil.printJSON(ja);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据类型列出满足条件的用户列表
	 * 
	 * @author lipengpeng
	 * @return
	 */
	public String queryUsersByType() {
		String type = request.getParameter("type");
		UserObj userObj = new UserObj();
		userObj.setType(type);
		List<UserObj> list = userService.queryForListByType(userObj);
		if (theForm == null) {
			theForm = new UserForm();
		}
		theForm.setResultList(list);
		return "listusertype";
	}

	/**
	 * 
	 * @Title: validateAccount
	 * @Description: 验证
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 17, 2013 10:38:45 AM
	 */
	public String validateUserName() {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// PrintWriter out = null;
		StringBuffer sbf = new StringBuffer();
		// out = response.getWriter();
		String id = request.getParameter("id");
		if (theForm.getUsername() != null && theForm.getUsername().length() != 0) {
			UserObj obj = new UserObj();
			if (id != null && !"null".equals(id) && !"".equals(id)) {
				obj.setId(id);
			}
			obj.setUsername(theForm.getUsername());
			obj.setIp(theForm.getIp());
			int count = userService.validateNameByEdit(obj);
			if (count > 0) {
				sbf.append(theForm.getUsername() + "已存在！请更改用户名 ");
			} else {
				sbf.append("");
			}
		} else {
			sbf.append("");
		}
		// out.print(sbf.toString());
		PrintWriterOut.printWirter(response, sbf.toString());
		return null;
	}

	/**
	 * 
	 * @Title: listHadoopUsers
	 * @Description: Hadoop中关联的用户列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-9 下午7:38:19
	 */
	public String listHadoopUsers() {
		if (theForm == null) {
			theForm = new UserForm();
		}
		UserObj obj = new UserObj();
		if (theForm.getIp() != null && !"".equals(theForm.getIp())) {
			obj.setIp(theForm.getIp());
		}
		if (theForm.getUsername() != null && !"".equals(theForm.getUsername())) {
			obj.setUsername(theForm.getUsername());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List<UserObj> list = userService.list(obj);
		if (list != null && list.size() > 0) {
			theForm.setResultList(list);
		}
		return "listHadoopUsers";
	}
}
