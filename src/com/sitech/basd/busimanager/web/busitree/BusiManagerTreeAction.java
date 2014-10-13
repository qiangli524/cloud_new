package com.sitech.basd.busimanager.web.busitree;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.busimanager.service.busitree.BusiManagerTreeService;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.util.systemlog.MethodLog;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: TbBusiSysTreeAction
 * </p>
 * <p>
 * Description: 业务系统树页面Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-20 下午1:53:45
 * 
 */
// Struts配置busisys_*
@Controller("busiManagerTreeAction")
@Scope("prototype")
public class BusiManagerTreeAction extends CRUDBaseAction {

	@Autowired
	private BusiManagerTreeService busiManagerTreeService;
	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;

	private static final long serialVersionUID = 7394314502309143247L;
	private String id;
	private String type;
	private String result;
	private List resultList;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @Title: listBusiTree
	 * @Description: 跳转到树界面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:58:56
	 */
	@MethodLog(remark = "TbBusiSysTreeAction-listBusiSysTree", type = 4, message = "业务中心树")
	public String listBusiSysTree() throws BaseException {
		return "list";
	}

	/**
	 * 
	 * @Title: saveBusiSysCenter
	 * @Description: 保存业务中心根节点
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:58:56
	 */
	// @MethodLog(remark = "TbBusiSysTreeAction-saveBusiSysCenter", type = 1,
	// message = "保存业务中心根节点")
	// public String saveBusiSysCenter() throws BaseException {
	// HttpServletResponse response = Struts2Utils.getResponse();
	// String name = Struts2Utils.getRequest().getParameter("name");
	// try {
	// name = URLDecoder.decode(name, "utf-8");
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// TbBusiSysTree parentNode = new TbBusiSysTree();
	// parentNode.setName(name);
	// parentNode.setParentId("-1");
	// parentNode.setType(0);
	// busiManagerTreeService.insertTbBusiSysTree(parentNode);
	// String json = "{\"result\":" + 1 + "}";
	// writeDataToResponse(response, json);
	// return null;
	// }

	/**
	 * 
	 * @Title: deleteBusiSysCenter
	 * @Description: 删除业务中心根节点
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:58:56
	 */
	// @MethodLog(remark = "TbBusiSysTreeAction-deleteBusiSysCenter", type = 2,
	// message = "删除业务中心根节点")
	// public String deleteBusiSysCenter() throws BaseException {
	// HttpServletResponse response = Struts2Utils.getResponse();
	// String id = Struts2Utils.getRequest().getParameter("id");
	// TbBusiSysTree parentNode = new TbBusiSysTree();
	// parentNode.setId(id);
	// int result = busiManagerTreeService.deleteTbBusiSysTreeById(parentNode);
	// String json = null;
	// if (result != -1) {
	// json = "{\"result\":" + 1 + "}";
	// } else {
	// json = "{\"result\":" + 0 + "}";
	// }
	// writeDataToResponse(response, json);
	// return null;
	// }

	/**
	 * 
	 * @Title: listBusiSysLimitTree
	 * @Description: 跳转到权限树界面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:58:56
	 */
	public String listBusiSysLimitTree() throws BaseException {
		return "limit";
	}

	/**
	 * 
	 * @Title: asyncForTree
	 * @Description: 采用异步的方式生成树
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:59:36
	 */
	public String asyncForTree() throws BaseException {
		String username = session.get("account").toString();
		String uid = session.get("id").toString();
		Assert.notNull(username, "用户名不能为空！");
		if (username != null) {
			HttpServletRequest request = Struts2Utils.getRequest();
			// 实例树数据
			List<BusiManagerTree> resultList = queryBusiSysTreeData(request, username);
			List<BusiManagerTree> list = busiManagerTreeService.initBusiSysTreelist(uid, username,
					resultList);
			HttpServletResponse response = Struts2Utils.getResponse();
			JSONArray json = JSONArray.fromObject(list);
			writeDataToResponse(response, json.toString());
		}
		// else {
		// HttpServletResponse response = Struts2Utils.getResponse();
		// JSONArray json =
		// JSONArray.fromObject("[{\"error\":\"数据错误,请重新登录或联系管理员！\"}]");
		// writeDataToResponse(response, json.toString());
		// }
		return null;
	}

	/**
	 * 
	 * @Title: asyncForLimitTree
	 * @Description: 采用异步的方式生成树
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:59:36
	 */
	// @MethodLog(remark = "TbBusiSysTreeAction-asyncForLimitTree", type = 4,
	// message = "采用异步的方式生成树(带权限)")
	// public String asyncForLimitTree() throws BaseException {
	// String username = "admin";
	// if (username != null) {
	// // 实例树数据
	// HttpServletRequest request = Struts2Utils.getRequest();
	// List<TbBusiSysTree> resultList = queryBusiSysTreeData(request, null);
	// List<BusiSysTree> list =
	// tbBusiSysTreeLimitService.initBusiSysTreeLimitlist(username,
	// resultList);
	// HttpServletResponse response = Struts2Utils.getResponse();
	// JSONArray json = JSONArray.fromObject(list);
	// writeDataToResponse(response, json.toString());
	// } else {
	// HttpServletResponse response = Struts2Utils.getResponse();
	// JSONArray json =
	// JSONArray.fromObject("[{\"error\":\"数据错误,请重新登录或联系管理员！\"}]");
	// writeDataToResponse(response, json.toString());
	// }
	// return null;
	// }

	/**
	 * 
	 * @Title: treeTabs
	 * @Description: 单击时进入tab页面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-21 下午4:28:15
	 */
	public String treeTabs() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String entityId = request.getParameter("entityId");
		String hostIP = request.getParameter("hostIP");
		String type = request.getParameter("type");
		request.setAttribute("id", id);
		request.setAttribute("type", type);
		request.setAttribute("entityId", entityId);
		request.setAttribute("hostIP", hostIP);
		return "treetabs";
	}

	/**
	 * 
	 * @Title: queryBusiSysTreeData
	 * @Description: 查询数据库中数据
	 * @param
	 * @return List<TbBusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-21 上午11:29:11
	 */
	public List<BusiManagerTree> queryBusiSysTreeData(HttpServletRequest request, String username) {
		List<BusiManagerTree> list = new ArrayList<BusiManagerTree>();
		BusiManagerTree busiManagerTree = new BusiManagerTree();
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		String account = session.get("account").toString();
		int flag = 0;
		/***** 权限设置----超级管理员可绕过此权限 ******************/
		TbGlobalConfigObj gObj = new TbGlobalConfigObj();
		gObj.setKEY("user_auth");
		gObj = tbGlobalConfigService.queryByObj(gObj);
		String[] users = new String[] { "" };
		if (gObj.getVALUE() != null && !("").equals(gObj.getVALUE())) {
			users = gObj.getVALUE().split(",");
		}
		for (int i = 0; i < users.length; i++) {
			if (account.equals(users[i])) {
				flag = 1;
			}
		}
		if (!"1".equals(session.get("id").toString())) {
			if (flag != 1) {
				if ("1".equals(type)) {
					busiManagerTree.setUser_id(session.get("id").toString());
				}
			}
		}
		if (id == null || "".equals(id)) {
			busiManagerTree.setType(0);
		} else {
			busiManagerTree.setParent_id(id);
		}
		if (username != null) {
			// tbBusiSysTree.setUsername(username);
			list = busiManagerTreeService.queryForTree(busiManagerTree);
		} else {
			list = busiManagerTreeService.queryForLimitTree(busiManagerTree);
		}
		return list;
	}

	/**
	 * 
	 * @Title: writeDataToResponse
	 * @Description: 写数据到Response 页面
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-21 上午11:20:08
	 */
	private void writeDataToResponse(HttpServletResponse response, String data) {
		response.setContentType("text/html;charset=UTF-8");
		// p.print(data);
		PrintWriterOut.printWirter(response, data);
		// p.close();
	}

	/**
	 * 
	 * @Title: validateDelete
	 * @Description: 验证能否被删除
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-8-23 下午7:30:51
	 */
	// public String validateDelete(){
	// Boolean re = busiManagerTreeService.validateDelete(id, type);
	// result = re+"";
	// return "validateDelete";
	// }
	/**
	 * 
	 * @Title: getExpandNodes
	 * @Description: 获取展开节点集合
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-13 下午5:37:04
	 */
	public String getExpandNodes() {
		try {
			name = URLDecoder.decode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BusiManagerTree tree = new BusiManagerTree();
		if (name != null && !"".equals(name)) {
			tree.setName(name);
		}
		if (type != null && !"".equals(type)) {
			tree.setType(Integer.parseInt(type));
		}
		List<BusiManagerTree> list = new ArrayList<BusiManagerTree>();
		BusiManagerTree parentNode = new BusiManagerTree();
		parentNode.setId(id);
		list = busiManagerTreeService.queryForTree(parentNode);
		if (list != null && list.size() > 0) {
			parentNode = list.get(0);
		}
		if ("2".equals(type)) {
			resultList = busiManagerTreeService.queryExpandNodesForBusi(tree);
		} else if ("3".equals(type)) {
			if (parentNode.getType() == 4) {// 为承载业务下的虚拟机
				resultList = busiManagerTreeService.getExpandNodes1(tree);
			} else {
				resultList = busiManagerTreeService.getExpandNodes(tree);
			}
		} else if ("32".equals(type)) {
			tree.setType(2);
			List<BusiManagerTree> result2 = busiManagerTreeService.queryExpandNodesForBusi(tree);
			tree.setType(3);
			List<BusiManagerTree> result3 = null;
			if (parentNode.getType() == 4) {// 为承载业务下的虚拟机
				result3 = busiManagerTreeService.getExpandNodes1(tree);
			} else {
				result3 = busiManagerTreeService.getExpandNodes(tree);
			}
			resultList = new ArrayList<BusiManagerTree>();
			resultList.addAll(result2);
			resultList.addAll(result3);
		}
		return "expandNode";
	}

	/**
	 * 
	 * @Title: queryTreeNodeByName
	 * @Description: 查询
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-12 下午7:09:42
	 */
	public String queryTreeNodeByName() {
		if (name != null && !"".equals(name)) {
			BusiManagerTree obj = new BusiManagerTree();
			try {
				name = URLDecoder.decode(name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			obj.setName(name);
			obj.setType(Integer.parseInt(type));
			if ("2".equals(type)) {// 搜索业务系统列表
				resultList = busiManagerTreeService.queryForTree(obj);
			} else if ("3".equals(type)) {// 搜索主机或虚拟机
				resultList = busiManagerTreeService.queryForTree(obj);
			} else if ("32".equals(type)) {
				obj.setType(2);
				List<BusiManagerTree> result2 = busiManagerTreeService.queryForTree(obj);
				obj.setType(3);
				List<BusiManagerTree> result3 = busiManagerTreeService.queryForTree(obj);
				resultList = new ArrayList<BusiManagerTree>();
				resultList.addAll(result2);
				resultList.addAll(result3);
			}
		}
		return "querynode";
	}

}
