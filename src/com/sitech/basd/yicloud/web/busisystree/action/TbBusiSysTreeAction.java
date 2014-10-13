package com.sitech.basd.yicloud.web.busisystree.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.opensymphony.xwork2.ActionContext;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.UserInfoService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.system.form.UserInfoForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTreeLimit;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeLimitService;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeService;
import com.sitech.basd.yicloud.util.systemlog.MethodLog;
import com.sitech.basd.yicloud.web.busisystree.form.BusiSysTree;
import com.sitech.basd.yicloud.web.busisystree.form.TbBusiSysTreeForm;
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
@Controller("tbBusiSysTreeAction")
@Scope("prototype")
public class TbBusiSysTreeAction extends CRUDBaseAction {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 7394314502309143247L;

	@Autowired
	private TbBusiSysTreeService tbBusiSysTreeService;
	@Autowired
	private TbBusiSysTreeLimitService tbBusiSysTreeLimitService;
	@Resource
	private UserInfoService userInfoService;
	private UserInfoForm userForm;
	private String id;
	private String type;
	private String result;
	private TbBusiSysTreeForm theForm;
	// 是否是权限处理页面
	private Boolean ifLimit;

	public UserInfoForm getUserForm() {
		return userForm;
	}

	public void setUserForm(UserInfoForm userForm) {
		this.userForm = userForm;
	}

	public TbBusiSysTreeForm getTheForm() {
		return theForm;
	}

	public void setTheForm(TbBusiSysTreeForm theForm) {
		this.theForm = theForm;
	}

	public Boolean getIfLimit() {
		return ifLimit;
	}

	public void setIfLimit(Boolean ifLimit) {
		this.ifLimit = ifLimit;
	}

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
		if (theForm == null) {
			theForm = new TbBusiSysTreeForm();
		}
		return LIST;
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
	@MethodLog(remark = "TbBusiSysTreeAction-saveBusiSysCenter", type = 1, message = "保存业务中心根节点")
	public String saveBusiSysCenter() throws BaseException {
		HttpServletResponse response = Struts2Utils.getResponse();
		String name = Struts2Utils.getRequest().getParameter("name");
		try {
			name = URLDecoder.decode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		TbBusiSysTree parentNode = new TbBusiSysTree();
		parentNode.setName(name);
		parentNode.setParentId("-1");
		parentNode.setType(0);
		tbBusiSysTreeService.insertTbBusiSysTree(parentNode);
		String json = "{\"result\":" + 1 + "}";
		writeDataToResponse(response, json);
		return null;
	}

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
	@MethodLog(remark = "TbBusiSysTreeAction-deleteBusiSysCenter", type = 2, message = "删除业务中心根节点")
	public String deleteBusiSysCenter() throws BaseException {
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = Struts2Utils.getRequest().getParameter("id");
		TbBusiSysTree parentNode = new TbBusiSysTree();
		parentNode.setId(id);
		int result = tbBusiSysTreeService.deleteTbBusiSysTreeById(parentNode);
		String json = null;
		if (result != -1) {
			json = "{\"result\":" + 1 + "}";
		} else {
			json = "{\"result\":" + 0 + "}";
		}
		writeDataToResponse(response, json);
		return null;
	}

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
		String username = request.getParameter("ACCOUNT");
		if (username != null) {
			// 实例树数据
			// HttpServletRequest request = Struts2Utils.getRequest();
			// List<TbBusiSysTree> resultList = queryBusiSysTreeData(request,
			// null);
			List<BusiSysTree> list = tbBusiSysTreeLimitService.initBusiSysTreeLimitlist(username,
					null);
			// HttpServletResponse response = Struts2Utils.getResponse();
			JSONArray json = JSONArray.fromObject(list);
			// writeDataToResponse(response, json.toString());
			ActionContext.getContext().put("nodes", json.toString());
			ActionContext.getContext().put("account", username);
		} else {
			// HttpServletResponse response = Struts2Utils.getResponse();
			// JSONArray json =
			// JSONArray.fromObject("[{\"error\":\"数据错误,请重新登录或联系管理员！\"}]");
			// writeDataToResponse(response, json.toString());
		}
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
		String[] user = new String[] { session.get("id").toString(),
				session.get("account").toString(), session.get("name").toString() };
		String username = user[1];
		Assert.notNull(username, "用户名不能为空！");
		if (username != null) {
			HttpServletRequest request = Struts2Utils.getRequest();
			// 实例树数据
			List<TbBusiSysTree> resultList = queryBusiSysTreeData(request, username);
			List<BusiSysTree> list = tbBusiSysTreeService.initBusiSysTreelist(username, resultList);
			HttpServletResponse response = Struts2Utils.getResponse();
			JSONArray json = JSONArray.fromObject(list);
			writeDataToResponse(response, json.toString());
		} else {
			HttpServletResponse response = Struts2Utils.getResponse();
			JSONArray json = JSONArray.fromObject("[{\"error\":\"数据错误,请重新登录或联系管理员！\"}]");
			writeDataToResponse(response, json.toString());
		}
		return null;
	}

	/**
	 * 
	 * @Title: asyncForLimitTree
	 * @Description: 采用异步的方式生成树 Deprecated by duangh，分权限时不采用异步加载方式
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:59:36
	 */
	@Deprecated
	public String asyncForLimitTree() throws BaseException {
		String username = "admin";
		if (username != null) {
			// 实例树数据
			HttpServletRequest request = Struts2Utils.getRequest();
			List<TbBusiSysTree> resultList = queryBusiSysTreeData(request, null);
			List<BusiSysTree> list = tbBusiSysTreeLimitService.initBusiSysTreeLimitlist(username,
					resultList);
			HttpServletResponse response = Struts2Utils.getResponse();
			JSONArray json = JSONArray.fromObject(list);
			writeDataToResponse(response, json.toString());
		} else {
			HttpServletResponse response = Struts2Utils.getResponse();
			JSONArray json = JSONArray.fromObject("[{\"error\":\"数据错误,请重新登录或联系管理员！\"}]");
			writeDataToResponse(response, json.toString());
		}
		return null;
	}

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
	@MethodLog(remark = "TbBusiSysTreeAction-queryBusiSysTreeData", type = 4, message = "查询数据库中数据")
	public List<TbBusiSysTree> queryBusiSysTreeData(HttpServletRequest request, String username) {
		List<TbBusiSysTree> list = new ArrayList<TbBusiSysTree>();
		TbBusiSysTree tbBusiSysTree = new TbBusiSysTree();
		String id = request.getParameter("id");
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
	public String validateDelete() {
		Boolean re = tbBusiSysTreeService.validateDelete(id, type);
		result = re + "";
		return "validateDelete";
	}

	/**
	 * 
	 * deployListUserInfo:应用部署权限用户
	 * 
	 * @return String
	 * @since duangh Ver 1.0
	 */
	public String deployListUserInfo() {
		if (userForm == null) {
			userForm = new UserInfoForm();
		}
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		if (userForm.getACCOUNT() != null && !"".equals(userForm.getACCOUNT())) {
			obj.setACCOUNT(userForm.getACCOUNT());
		}
		if (userForm.getNAME() != null && !"".equals(userForm.getNAME())) {
			obj.setNAME(userForm.getNAME());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List userInfoList = userInfoService.queryLikeForListByObj(obj);
		userForm.setResultList(userInfoList);
		return "deployUserList";
	}

	/**
	 * 
	 * saveAuthRelation:保存权限关系信息
	 * 
	 * @since duangh Ver 1.0
	 */
	public void saveAuthRelation() {
		String account = request.getParameter("account");
		String nodesIds = request.getParameter("nodesIds");
		String[] ids = nodesIds.split(",");
		List<TbBusiSysTreeLimit> limitList = new ArrayList<TbBusiSysTreeLimit>();
		for (String id : ids) {
			TbBusiSysTreeLimit limitObj = new TbBusiSysTreeLimit();
			limitObj.setTreeNodeId(id);
			limitObj.setUsername(account);
			limitList.add(limitObj);
		}
		int result = tbBusiSysTreeLimitService.reInitSysTreeLimitByUser(limitList, account);
		ActionContext.getContext().getValueStack().push(result);
	}
}
