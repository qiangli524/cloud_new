package com.sitech.basd.sxcloud.rsmu.web.system.action;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import service.TbDirectoryCodeService;

import com.sitech.basd.cloud3.service.newui.NewUIFuncService;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.FuncRoleObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.FunctionTreeObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysFunctionsObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGrpmemberObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.service.system.FunctionsService;
import com.sitech.basd.sxcloud.rsmu.service.system.UserInfoService;
import com.sitech.basd.sxcloud.rsmu.service.system.UsergroupService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.system.form.GrpmemberForm;
import com.sitech.basd.sxcloud.rsmu.web.system.form.UserInfoForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.EncryptUtil;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class UserInfoAction extends CRUDBaseAction {
	private UserInfoForm theForm;
	private GrpmemberForm grpForm;
	@Autowired
	private NewUIFuncService newUIFuncService;
	@Autowired
	private UsergroupService usergroupService;
	private UserInfoService userInfoService;
	private FunctionsService functionsService;
	@Autowired
	private TbDirectoryCodeService tbDirectoryCodeService;

	private AppService appService;

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public GrpmemberForm getGrpForm() {
		return grpForm;
	}

	public void setGrpForm(GrpmemberForm grpForm) {
		this.grpForm = grpForm;
	}

	public UserInfoForm getTheForm() {
		return theForm;
	}

	public void setTheForm(UserInfoForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:获取账户列表请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String listUserInfo() throws BaseException {
		if (theForm == null) {
			theForm = new UserInfoForm();
		}
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		// 查询当前用户
		String userId = session.get("id").toString();
		List userInfoList = new ArrayList();
		if (userId != null && !"".equals(userId)) {
			// 若为超级管理员admin,无需根据用户进行过滤
			/**
			 * 只有超级用户有获取用户列表的权限
			 */
			if (theForm.getACCOUNT() != null && !theForm.getACCOUNT().equals("")) {
				obj.setACCOUNT(theForm.getACCOUNT());
			}
			if (theForm.getNAME() != null && !theForm.getNAME().equals("")) {
				obj.setNAME(theForm.getNAME());
			}
			obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
			userInfoList = userInfoService.queryForListByObj(obj);
			theForm.setResultList(userInfoList);
		}
		return LIST;
	}

	/**
	 * @Title:模糊查询获取账户列表请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String listLikeUserInfo() throws BaseException {
		if (theForm == null) {
			theForm = new UserInfoForm();
		}
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		if (theForm.getACCOUNT() != null && !theForm.getACCOUNT().equals("")) {
			obj.setACCOUNT(theForm.getACCOUNT());
		}
		if (theForm.getNAME() != null && !theForm.getNAME().equals("")) {
			obj.setNAME(theForm.getNAME());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List userInfoList = userInfoService.queryLikeForListByObj(obj);
		theForm.setResultList(userInfoList);
		return LIST;
	}

	/**
	 * @Title:增加账户请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String addUserInfo() throws BaseException {
		theForm = new UserInfoForm();
		try {
			theForm.setDomainList(tbDirectoryCodeService.queryForListByDiType("domain.type"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ADD;
	}

	/**
	 * @Title:修改账户请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String modUserInfo() throws BaseException {
		if (theForm == null) {
			theForm = new UserInfoForm();
		}
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		obj.setID(theForm.getID());
		TbSysUserinfoObj tempObj = userInfoService.queryByObj(obj);
		// 密码解密
		tempObj.setPASSWORD(EncryptUtil.decode(tempObj.getPASSWORD()));
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			theForm.setDomainList(tbDirectoryCodeService.queryForListByDiType("domain.type"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ADD;
	}

	/**
	 * @Title:删除账户请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String delUserInfo() throws BaseException {
		if (theForm == null) {
			theForm = new UserInfoForm();
		}
		TbSysUserinfoObj obj = new TbSysUserinfoObj();

		int result = 0;

		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obj = userInfoService.queryByObj(obj);
		String account = obj.getACCOUNT();
		int ret = userInfoService.deleteByObj(obj);
		// 删除用户组下的成员设置
		TbSysGrpmemberObj tempObj = new TbSysGrpmemberObj();
		tempObj.setUSERID(obj.getID());
		usergroupService.deleteUserByObj(tempObj);
		if (ret > 0) {
			result = 1;
			Map<String, String> param = new HashMap<String, String>();
			param.put("LOGINID", account);
			newUIFuncService.deleteNewUIUserFunc(param);
		}

		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除用户" + obj.getACCOUNT());
		operObj.setREMARK("");
		operObj.setRESULT(result);
		int retOper = operationService.insertByObj(operObj);// 写操作日志

		return "redirect";
	}

	/**
	 * @Title:保存账户请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String saveUserInfo() throws BaseException {
		if (theForm == null) {
			theForm = new UserInfoForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		// 密码加密
		theForm.setPASSWORD(EncryptUtil.encode(theForm.getPASSWORD().trim()));
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
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
			TbSysUserinfoObj tempObj2 = new TbSysUserinfoObj();
			tempObj2.setACCOUNT(obj.getACCOUNT());
			TbSysUserinfoObj retObj = userInfoService.queryByObj(tempObj2);
			if (retObj != null) {
				try {
					theForm.setDomainList(tbDirectoryCodeService.queryForListByDiType("domain.type"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("msg", "账号已经存在");
				return FAILURE;
			}
			ret = userInfoService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增用户" + obj.getACCOUNT());
		} else {
			ret = userInfoService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改用户" + obj.getACCOUNT() + "信息");
		}

		if (ret > 0) {
			result = 1;
			theForm = new UserInfoForm();
		}
		operObj.setRESULT(result);
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		if (ret < 0) {
			return FAILURE;
		}
		return "redirect";
	}

	/**
	 * @Title:查看用户权限请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String findUserAuthority() throws BaseException {
		if (theForm == null) {
			theForm = new UserInfoForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String userid = request.getParameter("USERID");
		String account = request.getParameter("ACCOUNT");
		theForm.setID(Integer.parseInt(userid));
		TbSysFunctionsObj tbSysFunctionsObj = new TbSysFunctionsObj();
		tbSysFunctionsObj.setSTATUS("1");
		List funcsList = functionsService.queryForListByObj(tbSysFunctionsObj);
		FuncRoleObj funcRoleObj = new FuncRoleObj();
		funcRoleObj.setUSERID(Integer.parseInt(userid));
		funcRoleObj.setACCOUNT(account);
		List roleFuncsList = functionsService.queryTbSysFunctionsObjByFUNCID(funcRoleObj);
		FunctionTreeObj[] functionTreeObjArray = null;
		if (funcsList != null && funcsList.size() > 0) {
			functionTreeObjArray = new FunctionTreeObj[funcsList.size()];
			for (int i = 0; i < funcsList.size(); i++) {
				TbSysFunctionsObj tempObj = (TbSysFunctionsObj) funcsList.get(i);
				functionTreeObjArray[i] = new FunctionTreeObj();
				functionTreeObjArray[i].setLevel(getTypeLevel(tempObj.getFUNCID()));
				functionTreeObjArray[i].setMId("" + tempObj.getID());
				functionTreeObjArray[i].setMName(tempObj.getFUNNAME());
				functionTreeObjArray[i].setStrChecked("false");
				for (int j = 0; j < roleFuncsList.size(); j++) {
					TbSysFunctionsObj tempObj2 = (TbSysFunctionsObj) roleFuncsList.get(j);
					if (tempObj2.getID() == tempObj.getID()) {
						functionTreeObjArray[i].setStrChecked("true");
						break;
					}
				}
			}
		}

		request.setAttribute("functionTreeObjArray", functionTreeObjArray);
		return SUCCESS;
	}

	/**
	 * @Title:用户应用关联设置
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String editUserDataAuthority() throws BaseException {
		if (grpForm == null) {
			grpForm = new GrpmemberForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String userid = request.getParameter("USERID");
		String account = request.getParameter("ACCOUNT");
		TbSysUserinfoObj tbSysUserinfoObj = new TbSysUserinfoObj();
		tbSysUserinfoObj.setACCOUNT(account);
		tbSysUserinfoObj.setID(Integer.parseInt(userid));
		TbSysUserinfoObj userInfoObj = userInfoService.queryByObj(tbSysUserinfoObj);
		TbSysAppObj tbSysAppObj = new TbSysAppObj();
		List appsList = appService.queryForListByObj(tbSysAppObj);
		List userIds = new ArrayList();
		List userForSelect = new ArrayList();
		String[] appIds = null;
		if (userInfoObj != null && userInfoObj.getDATAAUTHORITY() != null
				&& !userInfoObj.getDATAAUTHORITY().equals("")) {
			appIds = userInfoObj.getDATAAUTHORITY().split(",");
		}
		if (appIds != null && appIds.length > 0) {
			for (int i = 0; i < appIds.length; i++) {
				userIds.add(appIds[i]);
			}
		}
		List optionalList = new ArrayList();
		if (appsList != null && appsList.size() > 0) {
			for (int i = 0; i < appsList.size(); i++) {
				TbSysAppObj tempObj = (TbSysAppObj) appsList.get(i);
				if (!userIds.contains("" + tempObj.getID()) && !account.equals("admin")) {
					optionalList.add(tempObj);
				} else {
					userForSelect.add(tempObj);
				}
			}
		}
		grpForm.setSelectedUsers(optionalList);
		grpForm.setUserForSelect(userForSelect);
		return "edituserdataauthority";
	}

	/**
	 * @Title:保存用户应用关联设置请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String saveUserDataAuthority() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String appids = request.getParameter("APPIDS");
		String userid = request.getParameter("USERID");
		String account = request.getParameter("ACCOUNT");

		TbSysUserinfoObj tempObj = new TbSysUserinfoObj();
		tempObj.setID(Integer.parseInt(userid));
		tempObj.setDATAAUTHORITY(appids.trim());
		if (!account.equals("admin")) {
			int ret = userInfoService.updateForDATAAUTHORITYByObj(tempObj);
		}
		return "redirect";
	}

	public int getTypeLevel(String cModuleID) {
		int pos = cModuleID.indexOf("00");
		if (pos == -1)
			return 5;
		else
			return (pos + 1) / 2;
	}
}
