package com.sitech.basd.component.tree.web.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.component.web.user.form.UserForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeService;
import com.sitech.utils.servlet.PrintWriterOut;

@Component("deployUserAction")
@Scope("prototype")
public class DeployUserAction extends BaseAction {
	@Autowired
	private UserService userService;
	@Autowired
	private TbBusiSysTreeService tbBusiSysTreeService;

	private UserForm theForm;

	public UserForm getTheForm() {
		return theForm;
	}

	public void setTheForm(UserForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: list
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 4:51:04 PM
	 */
	public String list() {
		if (theForm == null) {
			theForm = new UserForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		Map map = new HashMap();
		String ip = request.getParameter("hostIP");// 部署实例对应的IP
		String example_id = request.getParameter("example_id");// 节点实体ID
		String example_ids = request.getParameter("example_ids");// 节点实体ID
		String type = request.getParameter("type");
		String userids = request.getParameter("userids");
		request.setAttribute("userids", userids);
		List resulrList = new ArrayList();
		if ("2".equals(type)) {// // 获取基准应用下的所有实例的用户名，IP
			/*
			 * TbBusiSysTree tree = new TbBusiSysTree();
			 * tree.setBusiId(example_id); tree.setType(Integer.parseInt(type));
			 * List<TbBusiSysTree> treeList = tbBusiSysTreeService
			 * .queryForTree(tree); if (treeList != null && treeList.size() > 0)
			 * { tree = treeList.get(0); } List<TbBusiSysTree> exampleList =
			 * this.queryBusiSysTreeData(null, tree.getId()); UserObj obj = new
			 * UserObj(); for (TbBusiSysTree t : exampleList) { map.put("ip",
			 * t.getHostIP()); List<UserObj> list =
			 * userService.queryDeployUserList(map); resulrList.addAll(list); }
			 * if (resulrList != null && resulrList.size() > 0) {
			 * theForm.setResultList(resulrList); }
			 */
			map.put("app_id", example_id);
		} else {// 获取部署实例对应的用户的列表
			map.put("example_id", example_id);

		}
		List<UserObj> list = userService.queryDeployUserList(map);
		theForm.setResultList(list);
		request.setAttribute("example_id", example_id);
		request.setAttribute("example_ids", example_ids);
		request.setAttribute("type", type);
		return "list";
	}

	private List<TbBusiSysTree> queryBusiSysTreeData(String username, String id) {
		List<TbBusiSysTree> list = new ArrayList<TbBusiSysTree>();
		TbBusiSysTree tbBusiSysTree = new TbBusiSysTree();
		if (id == null || "".equals(id)) {
			tbBusiSysTree.setType(0);
		} else {
			tbBusiSysTree.setParentId(id);
		}
		if (username != null) {
			tbBusiSysTree.setUsername(username);
			list = tbBusiSysTreeService.queryForTree(tbBusiSysTree);
		} else {
			list = tbBusiSysTreeService.queryForLimitTree(tbBusiSysTree);
		}
		return list;
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
			userService.insertByObj(u);
			result = "1";
		}
		// out = response.getWriter();
		// out.println(result);
		// out.close();
		PrintWriterOut.printWirter(response, result);

		return null;
	}

}
