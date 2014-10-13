package com.sitech.basd.sxcloud.rsmu.web.system.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.FuncRoleObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.FunctionTreeObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysFunctionsObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGroupfuncObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGrpmemberObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUsergroupObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.FunctionsService;
import com.sitech.basd.sxcloud.rsmu.service.system.UserInfoService;
import com.sitech.basd.sxcloud.rsmu.service.system.UsergroupService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.system.form.GrpmemberForm;
import com.sitech.basd.sxcloud.rsmu.web.system.form.UsergroupForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.ssd.cq.service.HomeService;

public class UsergroupAction extends CRUDBaseAction {
	private UsergroupForm theForm;
	private GrpmemberForm grpForm;
	

	public GrpmemberForm getGrpForm() {
		return grpForm;
	}

	public void setGrpForm(GrpmemberForm grpForm) {
		this.grpForm = grpForm;
	}

	public UsergroupForm getTheForm() {
		return theForm;
	}

	public void setTheForm(UsergroupForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:获取用户组列表请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String listUsergroup() throws BaseException {
		if (theForm == null) {
			theForm = new UsergroupForm();
		}
		TbSysUsergroupObj obj = new TbSysUsergroupObj();
		if (theForm.getGROUPNAME() != null && !theForm.getGROUPNAME().equals("")) {
			obj.setGROUPNAME(theForm.getGROUPNAME());
		}
		if (theForm.getSTATUS() != -1) {
			obj.setSTATUS(theForm.getSTATUS());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List<TbSysUsergroupObj> userInfoList = usergroupService.queryForListByObj(obj);
		theForm.setResultList(userInfoList);
		return LIST;
	}

	/**
	 * @Title:增加用户组请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String addUsergroup() throws BaseException {
		theForm = new UsergroupForm();
		return ADD;
	}

	/**
	 * @Title:修改用户组请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String modUsergroup() throws BaseException {
		if (theForm == null) {
			theForm = new UsergroupForm();
		}
		TbSysUsergroupObj obj = new TbSysUsergroupObj();
		obj.setID(theForm.getID());
		TbSysUsergroupObj tempObj = usergroupService.queryByObj(obj);
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
	 * @Title:删除用户组请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String delUsergroup() throws BaseException {
		if (theForm == null) {
			theForm = new UsergroupForm();
		}
		TbSysUsergroupObj obj = new TbSysUsergroupObj();

		int result = 0;

		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int ret = usergroupService.deleteByObj(obj);
		// 删除用户组下的权限设置
		TbSysGroupfuncObj tbGroupfuncObj = new TbSysGroupfuncObj();
		tbGroupfuncObj.setGROUPID(obj.getID());
		functionsService.deleteByObj(tbGroupfuncObj);
		// 删除用户组下的成员设置
		TbSysGrpmemberObj tempObj = new TbSysGrpmemberObj();
		tempObj.setGROUPID(obj.getID());
		usergroupService.deleteByObj(tempObj);

		if (ret > 0) {
			result = 1;
		}

		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除用户组" + obj.getGROUPNAME());
		operObj.setREMARK("");
		operObj.setRESULT(result);
		int retOper = operationService.insertByObj(operObj);// 写操作日志

		return "redirect";
	}

	/**
	 * @Title:保存用户组请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String saveUsergroup() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		if (theForm == null) {
			theForm = new UsergroupForm();
		}
		TbSysUsergroupObj obj = new TbSysUsergroupObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
			Object account = request.getSession().getAttribute("account");
			if(Constant.TEMP4AUSER.equals(account)){
				obj.setDomain("domain.type.IT");
			}
//			obj.setSTATUS(1);// 用户组都设置为有效
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ret = 0;
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		int result = 0;
		operObj.setREMARK("");
		if (theForm.getID() == 0) {
			// TbSysUserinfoObj tempObj = (TbSysUserinfoObj)
			// request.getSession()
			// .getAttribute(Constant.USER_SESSION_KEY);
			obj.setCREATEUSER((Integer) session.get("id"));
			TbSysUsergroupObj tempObj2 = new TbSysUsergroupObj();
			tempObj2.setGROUPNAME(obj.getGROUPNAME());
			TbSysUsergroupObj retObj = usergroupService.queryByObj(tempObj2);
			if (retObj != null) {
				request.setAttribute("msg", "用户组已经存在");
				return FAILURE;
			}
			ret = usergroupService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增用户组" + obj.getGROUPNAME());
		} else {
			ret = usergroupService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改用户组" + obj.getGROUPNAME() + "信息");
		}

		if (ret > 0) {
			result = 1;
			theForm = new UsergroupForm();
		}
		operObj.setRESULT(result);
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		if (ret < 0) {
			return FAILURE;
		}
		return "redirect";
	}

	/**
	 * 
	 * @Title: validateFormName
	 * @Description: 验证名称是否重复
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Nov 20, 2012 3:48:13 PM
	 */
	public String validateFormName() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String groupname = request.getParameter("groupname");
		String id = request.getParameter("id");

		try {

			groupname = URLDecoder.decode(groupname, "UTF-8");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TbSysUsergroupObj tb = null;

		if (id.equals("0")) {// 新增
			TbSysUsergroupObj tempObj2 = new TbSysUsergroupObj();
			tempObj2.setGROUPNAME(groupname);
			tempObj2 = usergroupService.queryByObj(tempObj2);
			tb = tempObj2;
		} else {// 修改
			TbSysUsergroupObj t = new TbSysUsergroupObj();
			t.setID(Integer.parseInt(id));
			t = usergroupService.queryByObj(t);
			if (groupname.equals(t.getGROUPNAME())) {// 说明名称未修改
				tb = null;
			} else {
				TbSysUsergroupObj tbs = new TbSysUsergroupObj();
				// tbs.setGROUPNAME(groupname);
				// tbs = usergroupService.queryByObj(tbs);
				List<TbSysUsergroupObj> list = usergroupService.queryForListByObj(tbs);
				for (TbSysUsergroupObj ts : list) {
					if (ts.getGROUPNAME().equals(groupname)) {
						tb = ts;
					}

				}

			}
		}

		List<String> jsonArr = new ArrayList<String>();
		JSONArray json = new JSONArray();
		if (null != tb) {
			jsonArr.add("NO");
		} else {
			jsonArr.add("YES");
		}
		try {
			json = JSONArray.fromObject(jsonArr);
			Struts2Utils.getResponse().getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title:编辑用户组成员请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String editGrpmember() throws BaseException {
		if (grpForm == null) {
			grpForm = new GrpmemberForm();
		}
		String groupid = Struts2Utils.getRequest().getParameter("GROUPID");
		TbSysUserinfoObj tbSysUserinfoObj = new TbSysUserinfoObj();
		List userInfoList = userInfoService.queryForListByObj(tbSysUserinfoObj);
		TbSysGrpmemberObj tbSysGrpmemberObj = new TbSysGrpmemberObj();
		tbSysGrpmemberObj.setGROUPID(Integer.parseInt(groupid));
		List<TbSysGrpmemberObj> usergroupList = usergroupService.queryForListByObj(tbSysGrpmemberObj);
		List userIds = new ArrayList();
		if (usergroupList != null && usergroupList.size() > 0) {
			for (int i = 0; i < usergroupList.size(); i++) {
				TbSysGrpmemberObj tempObj = (TbSysGrpmemberObj) usergroupList.get(i);
				userIds.add(tempObj.getUSERID());
			}
		}
		List<TbSysUserinfoObj> optionalList = new ArrayList<TbSysUserinfoObj>();
		if (userInfoList != null && userInfoList.size() > 0) {
			for (int i = 0; i < userInfoList.size(); i++) {
				TbSysUserinfoObj tempObj = (TbSysUserinfoObj) userInfoList.get(i);
				if (!userIds.contains(tempObj.getID()) && !tempObj.getACCOUNT().equals("admin"))
					optionalList.add(tempObj);
			}
		}
		grpForm.setSelectedUsers(optionalList);
		grpForm.setUserForSelect(usergroupList);
		return "editgrpmember";
	}

	/**
	 * @Title:保存用户组成员请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String saveGrpmember() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String USERIDS = request.getParameter("USERIDS");
		String GROUPID = request.getParameter("GROUPID");
		TbSysGrpmemberObj tempObj = new TbSysGrpmemberObj();
		tempObj.setGROUPID(Integer.parseInt(GROUPID));
		int ret = usergroupService.deleteByObj(tempObj);
		if (USERIDS != null && !USERIDS.equals("")) {
			String[] userids = USERIDS.split(",");
			for (int i = 0; i < userids.length; i++) {
				tempObj.setUSERID(Integer.parseInt(userids[i]));
				usergroupService.insertByObj(tempObj);
			}
		}
		return "redirect";
	}

	/**
	 * @Title:编辑用户组权限请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String editGroupAuthority() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		if (grpForm == null) {
			grpForm = new GrpmemberForm();
		}
		String groupID = request.getParameter("GROUPID");
		grpForm.setGroup_id(groupID);
		// TbSysUserinfoObj tbSysUserinfoObj = (TbSysUserinfoObj) request
		// .getSession().getAttribute(Constant.USER_SESSION_KEY);
		FuncRoleObj funcRoleObj = new FuncRoleObj();
		String account = session.get("account").toString();
		int accid = Integer.parseInt(session.get("id").toString());
		funcRoleObj.setACCOUNT(account);
		funcRoleObj.setUSERID(accid);
//		List funcsList = functionsService.queryTbSysFunctionsObjByFUNCID(funcRoleObj);
		
		List funcsList = homeService.getMenus(funcRoleObj);
		TbSysGroupfuncObj tbSysGroupfuncObj = new TbSysGroupfuncObj();
		tbSysGroupfuncObj.setGROUPID(Integer.parseInt(groupID));
		List groupFuncsList = functionsService.queryForListByObj(tbSysGroupfuncObj);
		FunctionTreeObj[] functionTreeObjArray = null;
		/*if (funcsList != null && funcsList.size() > 0) {
			functionTreeObjArray = new FunctionTreeObj[funcsList.size()];
			for (int i = 0; i < funcsList.size(); i++) {
				TbSysFunctionsObj tempObj = (TbSysFunctionsObj) funcsList.get(i);
				functionTreeObjArray[i] = new FunctionTreeObj();
				functionTreeObjArray[i].setLevel(getTypeLevel(tempObj.getFUNCID()));
				functionTreeObjArray[i].setMId("" + tempObj.getID());
				functionTreeObjArray[i].setMName(tempObj.getFUNNAME());
				functionTreeObjArray[i].setStrChecked("false");
				for (int j = 0; j < groupFuncsList.size(); j++) {
					TbSysGroupfuncObj tempObj2 = (TbSysGroupfuncObj) groupFuncsList.get(j);
					if (tempObj2.getGROUPID() == Integer.parseInt(groupID)
							&& tempObj2.getFUNCID() == tempObj.getID()) {
						functionTreeObjArray[i].setStrChecked("true");
						break;
					}
				}
			}
		}*/

//		request.setAttribute("functionTreeObjArray", functionTreeObjArray);
		request.setAttribute("funcsList", funcsList);
		request.setAttribute("groupFuncsList", groupFuncsList);
		request.setAttribute("groupID",groupID);
		return "groupauthority";
	}
	


	/**
	 * @Title:保存用户组权限请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String saveGroupAuthority() throws BaseException {
		if (grpForm == null) {
			grpForm = new GrpmemberForm();
		}
		
		String groupIdByParameter=request.getParameter("GROUPID");
		
		TbSysGroupfuncObj obj = new TbSysGroupfuncObj();
		obj.setGROUPID(Integer.parseInt(groupIdByParameter));
		obj.setPERMIT("1");
		functionsService.deleteByObj(obj);
		String funcIds = (String) Struts2Utils.getRequest().getParameter("FUNCIDS");
		if (funcIds != null && !"".equals(funcIds)) {
			String[] funcIdsArr = funcIds.split(",");
			if (funcIdsArr != null && funcIdsArr.length > 0) {
				for (int i = 0; i < funcIdsArr.length; i++) {
					obj.setFUNCID(Integer.parseInt(funcIdsArr[i]));
					functionsService.insertByObj(obj);
				}
			}
		}
		grpForm = new GrpmemberForm();
		return "redirect";
	}

	private UsergroupService usergroupService;
	private UserInfoService userInfoService;
	private FunctionsService functionsService;
	private HomeService homeService;

	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}

	public void setUsergroupService(UsergroupService usergroupService) {
		this.usergroupService = usergroupService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	public int getTypeLevel(String cModuleID) {
		int pos = cModuleID.indexOf("00");
		if (pos == -1) {
			return -1;// 暂时不懂代码意图，待研究@huojla@20130824
		} else {
			return (pos + 1) / 2;
		}
	}

}
