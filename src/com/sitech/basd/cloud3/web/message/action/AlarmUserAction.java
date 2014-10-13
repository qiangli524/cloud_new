package com.sitech.basd.cloud3.web.message.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.cloud3.domain.message.AlarmSubscribeObj;
import com.sitech.basd.cloud3.domain.message.AlarmUserObj;
import com.sitech.basd.cloud3.service.message.AlarmSubscribeService;
import com.sitech.basd.cloud3.service.message.AlarmUserService;
import com.sitech.basd.cloud3.web.message.form.AlarmSubscribeForm;
import com.sitech.basd.cloud3.web.message.form.AlarmUserForm;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.cloud.domain.bizsystem.BizSystemObj;
import com.sitech.basd.sxcloud.cloud.service.bizsystem.BizSystemService;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.UUIDGenerator;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.basd.yicloud.domain.vmauthority.AuthorityTreeObj;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.service.vmauthority.VmAuthorityService;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.vo.util.UnitedConstant;

@Controller("alarmUserAction")
@Scope("prototype")
public class AlarmUserAction extends CRUDBaseAction {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private static Map<String, Map<String, Object>> vmStatMap = new HashMap<String, Map<String, Object>>();

	private Map<String, Object> map;
	@Autowired
	private AlarmUserService alarmUserService;
	@Autowired
	private BizSystemService bizSystemService;
	@Autowired
	private AlarmSubscribeService alarmSubscribeService;
	@Autowired
	private EntityTreeService entityTreeService;
	@Autowired
	private VmAuthorityService vmAuthorityService;
	@Autowired
	private UnitedTreeService unitedTreeService;
	@Autowired
	private PropertyUtil unitedTreeIconProp;

	private AlarmUserForm theForm;

	private AlarmSubscribeForm theSubscribeForm;

	public void setTheForm(AlarmUserForm theForm) {
		this.theForm = theForm;
	}

	public AlarmUserForm getTheForm() {
		return theForm;
	}

	public AlarmSubscribeForm getTheSubscribeForm() {
		return theSubscribeForm;
	}

	public void setTheSubscribeForm(AlarmSubscribeForm theSubscribeForm) {
		this.theSubscribeForm = theSubscribeForm;
	}

	/**
	 * 
	 * @Title: listVMService
	 * @Description: 获取虚拟机主机树
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String listVMService() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		return "tree";
	}

	/**
	 * 
	 * @Title: editUserWarning
	 * @Description: TODO(编辑用户告警订阅主机)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-30 上午10:13:20
	 */
	public String editUserWarning() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		// String oper = request.getParameter("oper");
		String userId = request.getParameter("USERID");
		request.setAttribute("userId", userId);
		return "editUserWarning";
	}

	/**
	 * 
	 * @Title: editBusiUserWarning
	 * @Description: TODO(编辑用户告警订阅业务)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-30 上午10:13:20
	 */
	public String editBusiUserWarning() throws BaseException {
		if (theSubscribeForm == null) {
			theSubscribeForm = new AlarmSubscribeForm();
		}
		BizSystemObj obj = new BizSystemObj();
		theSubscribeForm.setBusiList(bizSystemService.queryForListByObj(obj));
		HttpServletRequest request = Struts2Utils.getRequest();
		// String oper = request.getParameter("oper");
		String userId = request.getParameter("USERID");
		request.setAttribute("userId", userId);
		return "editBusiUserWarning";
	}

	/**
	 * 
	 * @Title: saveBusiUserWarning
	 * @Description: TODO(保存用户告警订阅业务)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-7-19 上午10:27:50
	 */
	public String saveBusiUserWarning() throws BaseException {
		if (theSubscribeForm == null) {
			theSubscribeForm = new AlarmSubscribeForm();
		}
		AlarmSubscribeObj obj = new AlarmSubscribeObj();
		try {
			BeanUtils.copyProperties(obj, theSubscribeForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (theSubscribeForm.getId() == null || "".equals(theSubscribeForm.getId())) {
			obj.setId(UUIDGenerator.getUUID());
			alarmSubscribeService.insertByObj(obj);
		} else {
			alarmSubscribeService.updateByObj(obj);
		}

		// return "saveBusiUserWarning";
		return "redirectSubscribe";
	}

	/**
	 * 
	 * @Title: WarningInfoPage
	 * @Description: 告警订阅信息页面
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-30 上午11:35:18
	 */
	public String WarningInfoPage() {
		return "warningInfoPage";
	}

	/**
	 * 
	 * @Title: vmTree
	 * @Description: 异步生成树
	 * @param
	 * @return void
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-30 上午10:17:15
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
			if ("host".equals(flag)) {
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
				if (!UnitedConstant.HOST.equals(u.getType())
						&& !UnitedConstant.VM.equals(u.getType())) { // 主机 虚机
					tObj.setNocheck(true);
				}
				// // 判断checkbox是否处于选中状使,若在表中查询到信息，说明具有权限，否则不具有
				// VmAuthorityObj vObj = new VmAuthorityObj();
				// vObj.setENTITY_TYPE(tObj.getType());
				// vObj.setENTITY_ID(tObj.getUuid());
				// vObj.setCONNECT_ID(tObj.getConnect_id());
				// // 若是虚拟机权限配置，只显示虚拟机的是否check
				// if (!"host".equals(flag)) {
				// vObj.setENTITY_TYPE("4");
				// }
				// if (userId != null && !"".equals(userId)
				// && !"null".equals(userId)) {
				// vObj.setUSERID(Integer.parseInt(userId));
				// }
				// List<VmAuthorityObj> vList = vmAuthorityService
				// .queryForList(vObj);
				// if (vList != null && vList.size() > 0) {
				// tObj.setChecked(true);
				// vObj = (VmAuthorityObj) vList.get(0);
				// tObj.setAuthorityId(vObj.getID());
				// }
				list.add(tObj);
			}
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray json = JSONArray.fromObject(list);
		// p = response.getWriter();
		// p.print(json.toString());
		PrintWriterOut.printWirter(response, json.toString());
		// p.close();
	}

	/**
	 * 
	 * @Title: saveUserVirAuthority
	 * @Description: 保存用户和资源告警关系
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-6-3 上午11:06:35
	 */
	public String saveUserResources() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// VmAuthorityObj obj = new VmAuthorityObj();
		int ret = -1;
		int ret1 = -1;
		int ret2 = -1;
		String userId = request.getParameter("userId");
		String names = request.getParameter("names");
		String sendstyle = request.getParameter("sendstyle");
		// String subscribe = request.getParameter("subscribe");
		String alarmlevel = request.getParameter("alarmlevel");
		String sendmode = request.getParameter("sendmode");
		String sendtime1 = request.getParameter("sendtime1");
		String sendtime2 = request.getParameter("sendtime2");
		String unsendtime1 = request.getParameter("unsendtime1");
		String unsendtime2 = request.getParameter("unsendtime2");
		// String change = request.getParameter("change");// 获取勾选状态被改变的节点
		// TbSysUserinfoObj userObj = new TbSysUserinfoObj();
		// userObj.setID(Integer.parseInt(userId));
		// userObj = userInfoService.queryByObj(userObj);
		// String account = userObj.getACCOUNT();

		try {
			names = URLDecoder.decode(names, "UTF-8");
			// change = URLDecoder.decode(change, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// // 删除取消勾选的节点
		// if (change != null && !"".equals(change)) {
		// String[] changes = change.split(":");
		// for (String s : changes) {
		// String[] sr = s.split(",");
		// String type = sr[1];
		// String entityId = sr[2];
		//
		// VmAuthorityObj auth = new VmAuthorityObj();
		// auth.setENTITY_ID(entityId);
		// auth.setTYPE(type);
		// auth.setUSERID(Integer.parseInt(userId));
		// List<VmAuthorityObj> list = vmAuthorityService
		// .queryForList(auth);
		// if (list != null && list.size() > 0) {// 删除取消勾选的节点
		// ret = vmAuthorityService.deleteByObj(auth);
		// if (type.equals("0") || type.equals("27")) {
		// VMHostObj vObj = new VMHostObj();
		// if (type.equals("27")) {
		// vObj.setID(Integer.parseInt(entityId));
		// } else {
		// vObj.setVH_UUID(entityId);
		// }
		// // vObj = vmHostService.queryByObj(vObj);
		// if (vObj != null) {
		// VmRelationObj rObj = new VmRelationObj();
		// rObj.setVm_uuid(vObj.getVH_UUID());
		// rObj.setUser_name(account);
		// // vmHostService.deleteByRelationObj(rObj);
		// }
		// }
		// }
		// }
		// }
		if (names != null && !names.equals("")) {
			// obj.setUSERID(Integer.parseInt(userId));
			String[] name = names.split(":");
			// String[] authorityId =
			// allauthorityId.split(",");//得到页面所有节点，即已经加载的节点
			// VmAuthorityObj delObj = new VmAuthorityObj();
			/*
			 * for (String delId : authorityId) { if (delId != null &&
			 * !"".equals(delId)) { delObj.setID(Integer.parseInt(delId));
			 * vmAuthorityService.deleteByObj(delObj);//先删除所有数据,只删除已加载的节点 } }
			 */
			StringBuffer hostids = new StringBuffer();
			StringBuffer vmHostids = new StringBuffer();
			StringBuffer hostnames = new StringBuffer();
			StringBuffer vmHostnames = new StringBuffer();
			for (String str : name) {
				String[] data = str.split(",");
				String entityname = data[0];
				String type = data[1];
				String entityId = data[2];
				if (UnitedConstant.HOST.equals(type)) {
					hostids.append(entityId).append(",");
					hostnames.append(entityname).append(",");
				} else if (UnitedConstant.VM.equals(type)) {
					vmHostids.append(entityId).append(",");
					vmHostnames.append(entityname).append(",");
				}
			}

			AlarmSubscribeObj asObj = new AlarmSubscribeObj();
			asObj.setAlarmlevel(alarmlevel);
			asObj.setAlarmuserid(userId);
			asObj.setSendmode(sendmode);
			asObj.setSendstyle(sendstyle);
			asObj.setSendtime1(sendtime1);
			asObj.setSendtime2(sendtime2);
			asObj.setUnsendtime1(unsendtime1);
			asObj.setUnsendtime2(unsendtime2);
			if (!"".equals(hostids.toString()) && hostids.toString() != null) {
				asObj.setId(UUIDGenerator.getUUID());
				asObj.setObjid(hostids.toString());
				asObj.setObjname(hostnames.toString());
				asObj.setSubscribe("4"); // 按主机订阅
				ret1 = alarmSubscribeService.insertByObj(asObj);
			} else {
				ret1 = 1;
			}
			if (!"".equals(vmHostids.toString()) && vmHostids.toString() != null) {
				asObj.setId(UUIDGenerator.getUUID());
				asObj.setObjid(vmHostids.toString());
				asObj.setObjname(vmHostnames.toString());
				asObj.setSubscribe("5"); // 按虚拟机订阅
				ret2 = alarmSubscribeService.insertByObj(asObj);
			} else {
				ret2 = 1;
			}

		}
		if (ret1 != -1 && ret2 != -1) {
			ret = 1;
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
	 * @Title: listAlarmUser
	 * @Description: TODO(取得告警用户列表)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-23 上午11:29:53
	 */
	public String listAlarmUser() {
		if (theForm == null) {
			theForm = new AlarmUserForm();
		}
		AlarmUserObj obj = new AlarmUserObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List list = alarmUserService.queryForListByObj(obj);
		theForm.setResultList(list);
		return LIST;
	}

	/**
	 * 
	 * @Title: listAlarmUser
	 * @Description: TODO(取得告警用户列表)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-23 上午11:29:53
	 */
	public String listUserSubscribe() {
		if (theSubscribeForm == null) {
			theSubscribeForm = new AlarmSubscribeForm();
		}
		AlarmSubscribeObj obj = new AlarmSubscribeObj();
		try {
			BeanUtils.copyProperties(obj, theSubscribeForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		// String oper = request.getParameter("oper");
		String userId = request.getParameter("USERID");
		if (userId != null && !"".equals(userId)) {
			obj.setAlarmuserid(userId);
			theSubscribeForm.setAlarmuserid(userId);
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List list = alarmSubscribeService.queryForListByObj(obj);
		theSubscribeForm.setSubscribeList(list);
		return "listSubscribe";
	}

	/**
	 * 
	 * @Title: delAlarmUser
	 * @Description: TODO(删除告警用户信息)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-23 上午11:28:38
	 */
	public String delUserSubscribe() throws BaseException {
		if (theSubscribeForm == null) {
			theSubscribeForm = new AlarmSubscribeForm();
		}
		AlarmSubscribeObj obj = new AlarmSubscribeObj();
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theSubscribeForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int ret = alarmSubscribeService.deleteByObj(obj);

		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除告警用户信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "redirectSubscribe";
	}

	/**
	 * 
	 * @Title: delAlarmUser
	 * @Description: TODO(删除告警用户信息)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-23 上午11:28:38
	 */
	public String editUserSubscribe() throws BaseException {
		if (theSubscribeForm == null) {
			theSubscribeForm = new AlarmSubscribeForm();
		}
		AlarmSubscribeObj obj = new AlarmSubscribeObj();
		// int result = 0;
		try {
			BeanUtils.copyProperties(obj, theSubscribeForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		obj = alarmSubscribeService.queryForObject(obj);
		try {
			BeanUtils.copyProperties(theSubscribeForm, obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		BizSystemObj bizobj = new BizSystemObj();
		theSubscribeForm.setBusiList(bizSystemService.queryForListByObj(bizobj));
		// int ret = alarmSubscribeService.deleteByObj(obj);

		// TbSysOperationLogObj operObj = this
		// .getTbSysOperationLogObj(Struts2Utils.getRequest());
		// operObj.setOPERTYPE(2);
		// operObj.setMESSAGE("删除告警用户信息");
		// operObj.setREMARK("");
		// operObj.setRESULT(result);
		// @SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "editUserSubscribe";
	}

	/**
	 * 
	 * @Title: addAlarmUser
	 * @Description: TODO(增加告警用户)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-23 上午11:29:37
	 */
	public String addAlarmUser() throws BaseException {
		if (theForm == null) {
			theForm = new AlarmUserForm();
		}
		return ADD;
	}

	/**
	 * 
	 * @Title: saveAlarmUser
	 * @Description: TODO(保存告警用户信息)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-23 上午11:29:14
	 */
	public String saveAlarmUser() throws BaseException {
		if (theForm == null) {
			theForm = new AlarmUserForm();
		}
		AlarmUserObj obj = new AlarmUserObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");

		int ret = 0;
		int result = 0;
		if (theForm.getId().equals("")) {
			obj.setId(UUIDGenerator.getUUID());
			ret = alarmUserService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增告警订阅信息");
		} else {
			ret = alarmUserService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改告警订阅信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return REDIRECT;
	}

	/**
	 * 
	 * @Title: modAlarmUser
	 * @Description: TODO(修改告警用户信息)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-23 上午11:28:53
	 */
	public String modAlarmUser() throws BaseException {
		if (theForm == null) {
			theForm = new AlarmUserForm();
		}
		AlarmUserObj obj = new AlarmUserObj();
		obj.setId(theForm.getId());
		AlarmUserObj tempObj = (AlarmUserObj) alarmUserService.queryForListByObj(obj).get(0);

		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ADD;
	}

	/**
	 * 
	 * @Title: delAlarmUser
	 * @Description: TODO(删除告警用户信息)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-23 上午11:28:38
	 */
	public String delAlarmUser() throws BaseException {
		if (theForm == null) {
			theForm = new AlarmUserForm();
		}
		AlarmUserObj obj = new AlarmUserObj();
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int ret = alarmUserService.deleteByObj(obj);

		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除告警用户信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "redirect";
	}

	public UnitedTreeService getUnitedTreeService() {
		return unitedTreeService;
	}

	public void setUnitedTreeService(UnitedTreeService unitedTreeService) {
		this.unitedTreeService = unitedTreeService;
	}

}
