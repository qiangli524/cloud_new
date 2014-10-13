package com.sitech.basd.yicloud.web.vmauthority.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.UserInfoService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.system.form.UserInfoForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.vmauthority.AuthorityTreeObj;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.service.vmauthority.VmAuthorityService;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.vo.util.UnitedConstant;

@Component("vmAuthorityAction")
@Scope("prototype")
public class VmAuthorityAction extends CRUDBaseAction {
	private UserInfoForm userForm;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private PropertyUtil unitedTreeIconProp;
	private Map<String, Map<String, String>> vmPowerState = new HashMap<String, Map<String, String>>();
	// private EntityTreeService entityTreeService;
	@Autowired
	private VmAuthorityService vmAuthorityService;
	@Autowired
	private UnitedTreeService unitedTreeService;
	@Autowired
	private EntityTreeService entityTreeService;
	@Autowired
	private VMHostService vmHostService;
	private VmAuthorityObj vmAuthorityObj;

	public UserInfoForm getUserForm() {
		return userForm;
	}

	public void setUserForm(UserInfoForm userForm) {
		this.userForm = userForm;
	}

	/**
	 * @Title:获取账户列表请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String listUserInfo() throws BaseException {
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
		return LIST;
	}

	/**
	 * @Title:编辑用户虚拟机权限请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String editUserVirAuthority() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		// String oper = request.getParameter("oper");
		String userId = request.getParameter("USERID");
		String flag = request.getParameter("flag");
		request.setAttribute("userId", userId);
		// 分配主机还是虚拟机的标识
		request.setAttribute("flag", flag);
		return "editUserVirAuthority";
	}

	/**
	 * 
	 * @Title: 获取用户关联主机下的操作权限
	 * @Copyright:Copyright (c) Aug 23, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String getOperAuthority() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 获取入参
		// 用户ID
		String userid = request.getParameter("userid");
		// 实体类型：虚拟机或主机
		String entityType = request.getParameter("type");
		// 实体ID
		String entityId = request.getParameter("uuid");
		// 资源池ID
		String connect_id = request.getParameter("connect_id");
		// 资源实体名
		String entityName = request.getParameter("name");
		// 资源虚拟化类型
		String vtype = request.getParameter("vtype");
		request.setAttribute("userid", userid);
		request.setAttribute("connectid", connect_id);
		request.setAttribute("uuid", entityId);
		request.setAttribute("type", entityType);
		request.setAttribute("name", entityName);
		request.setAttribute("vtype", vtype);
		// 查询获取该实体的操作权限
		VmAuthorityObj reqObj = new VmAuthorityObj();
		reqObj.setUSERID(Integer.parseInt(userid));
		reqObj.setENTITY_TYPE(entityType);
		reqObj.setENTITY_ID(entityId);
		reqObj.setCONNECT_ID(connect_id);
		// 查询结果对象
		List<VmAuthorityObj> resList = vmAuthorityService.queryForList(reqObj);
		if (resList != null && resList.size() > 0) {
			vmAuthorityObj = (VmAuthorityObj) resList.get(0);

		}

		return "operAuthority";
	}

	/**
	 * 
	 * @Title:更新用户对应主机或虚拟机的权限
	 * @Copyright:Copyright (c) Aug 23, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String updateUserOperAuthority() throws BaseException {
		if (vmAuthorityObj == null) {
			vmAuthorityObj = new VmAuthorityObj();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		// 获取入参
		// 用户ID
		String userid = request.getParameter("userid");
		// 实体类型：虚拟机或主机
		String entityType = request.getParameter("type");
		// 实体ID
		String entityId = request.getParameter("uuid");
		// 资源池ID
		String connect_id = request.getParameter("connectid");
		// 权限ID
		String oper = vmAuthorityObj.getOPERAUTHORITY();
		// 资源实体名
		String entityName = request.getParameter("name");
		// 资源虚拟化类型
		String vtype = request.getParameter("vtype");

		// 执行更新权限
		VmAuthorityObj reqObj = new VmAuthorityObj();
		reqObj.setCONNECT_ID(connect_id);
		reqObj.setENTITY_ID(entityId);
		reqObj.setENTITY_TYPE(entityType);
		reqObj.setUSERID(Integer.parseInt(userid));
		reqObj.setOPERAUTHORITY(oper);
		reqObj.setENTITY_NAME(entityName);
		reqObj.setTYPE(vtype);
		List<VmAuthorityObj> resList = vmAuthorityService.queryForList(reqObj);
		// 若已添加到entityuser表则进行更新操作
		if (resList != null && resList.size() > 0) {
			vmAuthorityService.updateByObj(reqObj);
		}
		return null;
	}

	/**
	 * @Title:异步生成树
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * @throws SQLException
	 */
	public void vmTree() throws SQLException {
		UnitedTreeObj treeObj = new UnitedTreeObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String uuid = request.getParameter("uuid");
		String connect_id = request.getParameter("connect_id");
		String node_type = request.getParameter("type");
		String vtype = request.getParameter("vtype");
		// String oper = request.getParameter("oper");
		String userId = request.getParameter("userId");
		// 获取分配主机还是虚拟机的标识 host为分配主机 vm为分配虚拟机
		String flag = request.getParameter("flag");
		if (id == null || "".equals(id)) {
			treeObj.setType(UnitedConstant.ROOT);
		} else {
			treeObj.setParent_id(id);
			if (flag.equals("host")) {
				// 只显示到主机那一层
				treeObj.setFlag("host");
			}
		}
		List<UnitedTreeObj> resultList = unitedTreeService.queryForTreeList(treeObj);
		List<AuthorityTreeObj> list = new ArrayList<AuthorityTreeObj>();
		if (list != null) {
			UnitedTreeObj tempObj = new UnitedTreeObj();
			for (UnitedTreeObj u : resultList) {
				AuthorityTreeObj tObj = new AuthorityTreeObj();
				tObj.setId(u.getId());
				tObj.setName(u.getName());
				tObj.setType(u.getType());
				tObj.setVtype(u.getVtype());
				tObj.setUuid(u.getUuid());
				tObj.setConnect_id(u.getConnect_id());
				// 判断是不是父节点
				tempObj.setParent_id(u.getId());
				List<UnitedTreeObj> lst = unitedTreeService.queryForTreeList(tempObj);
				if (lst == null || lst.size() == 0) {
					tObj.setIsParent(false);
				}
				// 设置图标
				String type = u.getType();
				if (UnitedConstant.ROOT.equals(type)) {// 安徽移动私有云管理平台名称
					tObj.setIcon(unitedTreeIconProp.getString("anhui"));
				} else if (UnitedConstant.DATACENTER.equals(type)) { // 数据中心
					tObj.setIcon(unitedTreeIconProp.getString("datacenter"));
				} else if (UnitedConstant.CLUSTER.equals(type)) { // 集群
					tObj.setIcon(unitedTreeIconProp.getString("cluster"));
				} else if (UnitedConstant.HOST.equals(type)) { // 主机
					tObj.setIcon(unitedTreeIconProp.getString("host"));
				} else if (UnitedConstant.VM.equals(type)) {
					tObj.setIcon(unitedTreeIconProp.getString("vm_stop"));
				}
				// 判断checkbox是否处于选中状使,若在表中查询到信息，说明具有权限，否则不具有
				VmAuthorityObj vObj = new VmAuthorityObj();
				vObj.setENTITY_TYPE(tObj.getType());
				vObj.setENTITY_ID(tObj.getUuid());
				vObj.setCONNECT_ID(tObj.getConnect_id());
				// 若是虚拟机权限配置，只显示虚拟机的是否check
				if (userId != null && !"".equals(userId) && !"null".equals(userId)) {
					vObj.setUSERID(Integer.parseInt(userId));
				}
				List<VmAuthorityObj> vList = vmAuthorityService.queryForList(vObj);
				if (vList != null && vList.size() > 0) {
					tObj.setChecked(true);
					vObj = (VmAuthorityObj) vList.get(0);
					tObj.setAuthorityId(vObj.getID());
				}
				/*
				 * 父节点
				 */
				if (UnitedConstant.HOST.equals(u.getType())) {
					if ("vm".equals(flag)) {
						tObj.setNocheck(true);
					} else if ("host".equals(flag)) {
						tObj.setIsParent(false);
					}
				}
				list.add(tObj);
			}
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray json = JSONArray.fromObject(list);
		// PrintWriter p;
		try {
			// p = response.getWriter();
			// p.print(json.toString());
			// p.close();
			PrintWriterOut.printWirter(response, json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @Title:保存用户分配虚拟机权限
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String saveUserVirAuthority() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		int ret = -1;
		String userId = request.getParameter("userId");
		String names = request.getParameter("names");
		// 主机权限配置还是虚拟机权限配置的标识 host为主机权限配置
		String flag = request.getParameter("flag");
		String oper = request.getParameter("oper");
		/*
		 * 处理操作转换
		 */
		if ("all".equals(oper)) {
			oper = "0";
		} else if ("oper".equals(oper)) {
			oper = "1";
		} else if ("view".equals(oper)) {
			oper = "2";
		}

		String change = request.getParameter("change");
		try {
			names = URLDecoder.decode(names, "UTF-8");
			change = URLDecoder.decode(change, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if (change != null && !"".equals(change)) {
			// 先删除数据库里的所有数据，然后将以选中的数据插入到数据库中
			VmAuthorityObj auth = new VmAuthorityObj();
			auth.setUSERID(Integer.parseInt(userId));
			String[] changes = change.split(":");
			for (String c : changes) {
				String[] cData = c.split(",");
				String entityType = cData[1];
				if (!UnitedConstant.ROOT.equals(entityType)) {
					auth.setENTITY_ID(cData[2]);
					if (cData.length == 5) {
						auth.setCONNECT_ID(cData[4]);
					}
				} else {
					auth.setENTITY_TYPE(entityType);
				}
				ret = vmAuthorityService.deleteByObj(auth);
			}

		}
		VmAuthorityObj obj = new VmAuthorityObj();
		// 所有被选中的节点，先进行查询是否已经存在，如果存在，则更新，如果不存在
		if (names != null && !"".equals(names)) {
			obj.setUSERID(Integer.parseInt(userId));
			String[] name = names.split(":");

			for (String str : name) {
				String[] data = str.split(",");
				String entityname = data[0];
				String entityType = data[1];
				// 只有是主机或虚拟机的节点时才进行操作

				if (!UnitedConstant.ROOT.equals(entityType)) {
					String entityId = "";
					String vtype = "";
					if (data.length >= 3) {
						entityId = data[2];
					}
					if (data.length >= 4) {
						vtype = data[3];
					}
					String connect_id = "";
					if (data.length == 5) {
						connect_id = data[4];
					}
					obj.setENTITY_NAME(entityname);
					obj.setENTITY_TYPE(entityType);
					obj.setENTITY_ID(entityId);
					obj.setCONNECT_ID(connect_id);
					obj.setTYPE(vtype);
				} else {
					obj.setENTITY_NAME(entityname);
					obj.setENTITY_TYPE(entityType);
					if (data.length == 5) {
						obj.setCONNECT_ID(data[4]);
					}
				}
				List result = vmAuthorityService.queryForList(obj);
				if (result != null && result.size() > 0) {// 查询当前节点在数据库中是不是存在，如果存在则进行相应的更新操作
					if ((UnitedConstant.HOST.equals(entityType) && "host".equals(flag))
							|| (UnitedConstant.VM.equals(entityType) && "vm".equals(flag))) {
						obj.setOPERAUTHORITY(oper);
						ret = vmAuthorityService.updateByObj(obj);
					}
				} else {
					if ((UnitedConstant.HOST.equals(entityType) && "host".equals(flag))
							|| (UnitedConstant.VM.equals(entityType) && "vm".equals(flag))) {
						obj.setOPERAUTHORITY(oper);
						ret = vmAuthorityService.insertByObj(obj);// 插入主机或虚拟机权限
					} else {
						ret = vmAuthorityService.insertByObj(obj);// 插入其他类型数据
					}
				}
			}
		} else {// 没有节点被选中
			VmAuthorityObj auth = new VmAuthorityObj();
			auth.setUSERID(Integer.parseInt(userId));
			if ("host".equals(flag)) {// /用于判断当前是对于虚拟机还是主机的权限分配
				auth.setTYPE("3");
			} else {
				auth.setTYPE("4");
			}

			ret = vmAuthorityService.deleteByObj(auth);
		}

		String json = "{\"result\":\"" + ret + "\"}";
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: listITSMUserInfo
	 * @Description: 展示ITSM用户设置权限
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-11-18 下午3:47:57
	 */
	public String listITSMUserInfo() throws BaseException {
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
		return "listItsmUser";
	}

	/**
	 * 
	 * @Title: editItsmUserVirAuthority
	 * @Description: 编辑ITSM用户权限请求
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-11-18 下午3:55:23
	 */
	public String editItsmUserVirAuthority() {
		HttpServletRequest request = Struts2Utils.getRequest();
		// String oper = request.getParameter("oper");
		String userId = request.getParameter("USERID");
		String flag = request.getParameter("flag");
		request.setAttribute("userId", userId);
		// 分配主机还是虚拟机的标识
		request.setAttribute("flag", flag);
		return "editItsmUserVirAuthority";
	}

	/**
	 * 
	 * @Title: authotitySelectItsmPage
	 * @Description: 进入虚拟机权限选择页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-11-18 下午4:02:14
	 */
	public String authotitySelectItsmPage() {
		return "select_page";
	}

	/**
	 * 
	 * @Title: itsmVmTree
	 * @Description: 异步生成树
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-11-18 下午4:09:16
	 */
	public void itsmVmTree() {
		EntityTreeObj treeObj = new EntityTreeObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String uuid = request.getParameter("uuid");
		String node_type = request.getParameter("type");
		// String oper = request.getParameter("oper");
		String userId = request.getParameter("userId");
		// 获取分配主机还是虚拟机的标识 host为分配主机 vm为分配虚拟机
		String flag = request.getParameter("flag");
		if (id == null || "".equals(id)) {
			treeObj.setType("8");
		} else {
			treeObj.setParentId(Integer.parseInt(id));
			if (flag.equals("host")) {
				// 只显示到主机那一层
				treeObj.setFlag("host");
			}
		}
		List<EntityTreeObj> resultList = entityTreeService.queryForTree(treeObj);
		List<AuthorityTreeObj> list = new ArrayList<AuthorityTreeObj>();
		if (list != null) {
			EntityTreeObj tempObj = new EntityTreeObj();
			for (EntityTreeObj u : resultList) {
				AuthorityTreeObj tObj = new AuthorityTreeObj();
				tObj.setId(u.getId() + "");
				tObj.setName(u.getName());
				tObj.setType(u.getType());
				tObj.setUuid(u.getEntityId());
				// 判断是不是父节点
				if (flag.equals("host") && u.getType().equals("1")) {
					tObj.setIsParent(false);
				} else {
					tempObj.setParentId(u.getId());
					List<EntityTreeObj> lst = entityTreeService.queryForTree(tempObj);
					if (lst == null || lst.size() == 0) {
						tObj.setIsParent(false);
					}
				}
				// 设置图标
				String type = u.getType();
				if ("8".equals(type)) { // 数据中心
					tObj.setIcon(unitedTreeIconProp.getString("datacenter"));
				} else if ("3".equals(type)) { // 集群
					tObj.setIcon(unitedTreeIconProp.getString("cluster"));
				} else if ("1".equals(type)) { // 主机
					tObj.setIcon(unitedTreeIconProp.getString("host"));
				} else if ("0".equals(type)) {
					tObj.setIcon(unitedTreeIconProp.getString("vm_stop"));
				}
				// 判断checkbox是否处于选中状使,若在表中查询到信息，说明具有权限，否则不具有
				VmAuthorityObj vObj = new VmAuthorityObj();
				vObj.setTYPE(tObj.getType());
				vObj.setENTITY_ID(tObj.getUuid());
				vObj.setCONNECT_ID(tObj.getConnect_id());
				// 若是虚拟机权限配置，只显示虚拟机的是否check
				if (userId != null && !"".equals(userId) && !"null".equals(userId)) {
					vObj.setUSERID(Integer.parseInt(userId));
				}
				List<VmAuthorityObj> vList = vmAuthorityService.queryForList(vObj);
				if (vList != null && vList.size() > 0) {
					tObj.setChecked(true);
					vObj = (VmAuthorityObj) vList.get(0);
					tObj.setAuthorityId(vObj.getID());
				}
				list.add(tObj);
			}
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray json = JSONArray.fromObject(list);
		// PrintWriter p;
		try {
			// p = response.getWriter();
			// p.print(json.toString());
			// p.close();
			PrintWriterOut.printWirter(response, json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: getItsmOperAuthority
	 * @Description: 获取用户关联主机下的操作权限
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-11-18 下午4:33:31
	 */
	public String getItsmOperAuthority() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 获取入参
		// 用户ID
		String userid = request.getParameter("userid");
		// 实体类型：虚拟机或主机
		String entityType = request.getParameter("type");
		// 实体ID
		String entityId = request.getParameter("uuid");
		// 资源实体名
		String entityName = request.getParameter("name");
		request.setAttribute("userid", userid);
		request.setAttribute("uuid", entityId);
		request.setAttribute("type", entityType);
		request.setAttribute("name", entityName);
		// 查询获取该实体的操作权限
		VmAuthorityObj reqObj = new VmAuthorityObj();
		reqObj.setUSERID(Integer.parseInt(userid));
		reqObj.setENTITY_TYPE(entityType);
		reqObj.setENTITY_ID(entityId);
		// 查询结果对象
		List<VmAuthorityObj> resList = vmAuthorityService.queryForList(reqObj);
		if (resList != null && resList.size() > 0) {
			vmAuthorityObj = (VmAuthorityObj) resList.get(0);
		}
		return "itsmOperAuthority";
	}

	/**
	 * 
	 * @Title: updateItsmUserOperAuthority
	 * @Description: 更新用户对应主机或虚拟机的权限
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-11-18 下午4:34:04
	 */
	public String updateItsmUserOperAuthority() throws BaseException {
		if (vmAuthorityObj == null) {
			vmAuthorityObj = new VmAuthorityObj();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		// 获取入参
		// 用户ID
		String userid = request.getParameter("userid");
		// 实体类型：虚拟机或主机
		String entityType = request.getParameter("type");
		// 实体ID
		String entityId = request.getParameter("uuid");
		// 权限ID
		String oper = vmAuthorityObj.getOPERAUTHORITY();
		// 资源实体名
		String entityName = request.getParameter("name");

		// 执行更新权限
		VmAuthorityObj reqObj = new VmAuthorityObj();
		reqObj.setENTITY_ID(entityId);
		reqObj.setENTITY_TYPE(entityType);
		reqObj.setUSERID(Integer.parseInt(userid));
		reqObj.setOPERAUTHORITY(oper);
		reqObj.setENTITY_NAME(entityName);
		List<VmAuthorityObj> resList = vmAuthorityService.queryForList(reqObj);
		// 若已添加到entityuser表则进行更新操作
		if (resList != null && resList.size() > 0) {
			vmAuthorityService.updateByObj(reqObj);
		}
		return null;
	}

	/**
	 * 
	 * @Title: saveItsmUserVirAuthority
	 * @Description: 保存用户分配虚拟机权限
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-11-18 下午4:45:36
	 */
	public String saveItsmUserVirAuthority() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		int ret = -1;
		String userId = request.getParameter("userId");
		String names = request.getParameter("names");
		// 主机权限配置还是虚拟机权限配置的标识 host为主机权限配置
		String flag = request.getParameter("flag");
		String oper = request.getParameter("oper");
		String change = request.getParameter("change");
		try {
			names = URLDecoder.decode(names, "UTF-8");
			change = URLDecoder.decode(change, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		System.out.println(oper.length());
		if (oper.length() == 1) {
			if (change != null && !"".equals(change)) {
				// 先删除数据库里的所有数据，然后将以选中的数据插入到数据库中
				VmAuthorityObj auth = new VmAuthorityObj();
				auth.setUSERID(Integer.parseInt(userId));
				String[] changes = change.split(":");
				for (String c : changes) {
					String[] cData = c.split(",");
					auth.setENTITY_ID(cData[2]);
					String entityType = cData[1];
					ret = vmAuthorityService.deleteByObj(auth);
				}
			}
			VmAuthorityObj obj = new VmAuthorityObj();
			// 所有被选中的节点，先进行查询是否已经存在，如果存在，则更新，如果不存在
			if (names != null && !"".equals(names)) {
				obj.setUSERID(Integer.parseInt(userId));
				String[] name = names.split(":");

				for (String str : name) {
					String[] data = str.split(",");
					String entityname = data[0];
					String entityType = data[1];
					// 只有是主机或虚拟机的节点时才进行操作
					String entityId = data[2];
					obj.setENTITY_NAME(entityname);
					obj.setENTITY_ID(entityId);
					obj.setTYPE(entityType);
					List result = vmAuthorityService.queryForList(obj);
					if (result != null && result.size() > 0) {// 查询当前节点在数据库中是不是存在，如果存在则进行相应的更新操作
						if (("1".equals(entityType) && "host".equals(flag))
								|| ("0".equals(entityType) && "vm".equals(flag))) {
							obj.setOPERAUTHORITY(oper);
							ret = vmAuthorityService.updateByObj(obj);
						}
					} else {
						if (("1".equals(entityType) && "host".equals(flag))
								|| ("0".equals(entityType) && "vm".equals(flag))) {
							obj.setOPERAUTHORITY(oper);
							ret = vmAuthorityService.insertByObj(obj);// 插入主机或虚拟机权限
						} else {
							ret = vmAuthorityService.insertByObj(obj);// 插入其他类型数据
						}
					}
				}
			} else {// 没有节点被选中
				VmAuthorityObj auth = new VmAuthorityObj();
				auth.setUSERID(Integer.parseInt(userId));
				if ("host".equals(flag)) {// /用于判断当前是对于虚拟机还是主机的权限分配
					auth.setTYPE("1");
				} else {
					auth.setTYPE("0");
				}
				ret = vmAuthorityService.deleteByObj(auth);
			}
		}
		String json = "{\"result\":\"" + ret + "\"}";
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}