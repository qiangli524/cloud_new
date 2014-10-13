package com.sitech.basd.sxcloud.cloud.web.vusermanage.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.vusermanage.VuserManageObj;
import com.sitech.basd.sxcloud.cloud.service.vusermanage.VuserManageService;
import com.sitech.basd.sxcloud.cloud.web.vusermanage.form.VuserManageForm;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.system.form.UserInfoForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class VuserManageAction extends CRUDBaseAction {
	private VuserManageForm theForm;
	private UserInfoForm userInfoForm;

	public VuserManageForm getTheForm() {
		return theForm;
	}

	public UserInfoForm getUserInfoForm() {
		return userInfoForm;
	}

	public void setUserInfoForm(UserInfoForm userInfoForm) {
		this.userInfoForm = userInfoForm;
	}

	public void setTheForm(VuserManageForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listVuserManage() throws BaseException {
		if (theForm == null) {
			theForm = new VuserManageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		VuserManageObj obj = new VuserManageObj();
		if (theForm.getUSER_ID() != null && !"".equals(theForm.getUSER_ID())) {
			obj.setUSER_ID(theForm.getUSER_ID().trim());
		}

		if (theForm.getUSER_NAME() != null
				&& !"".equals(theForm.getUSER_NAME())) {
			obj.setUSER_NAME(theForm.getUSER_NAME().trim());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List vuserList = vuserManageService.queryForListByObj(obj);

		theForm.setResultList(vuserList);
		return "list";

	}

	/**
	 * @Title:增加V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String addVuserManage() throws BaseException {
		if (theForm == null) {
			theForm = new VuserManageForm();
		}
		TbSysUserinfoObj tbSysUserinfoObj = new TbSysUserinfoObj();
		List uList = vuserManageService.queryForListByUserObj(tbSysUserinfoObj);
		theForm.setUserList(uList);
		return "add";
	}

	/**
	 * @Title:保存V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String saveVuserManage() throws BaseException {
		if (theForm == null) {
			theForm = new VuserManageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		VuserManageObj obj = new VuserManageObj();
		theForm.setFlag(theForm.getFlag());
		try {
			BeanUtils.copyProperties(obj, theForm);
			// obj.setHOST_ID(String.valueOf(theForm.getID()));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");

		int ret = 0;
		int result = 0;
		if (theForm.getFlag() == 0) { // flag是来自JS中的隐藏变量，判断是新增操作或修改操作
			ret = vuserManageService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增V用户信息");
		} else {
			ret = vuserManageService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改V用户信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "list";
	}

	/**
	 * @Title:修改V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String modVuserManage() throws BaseException {
		if (theForm == null) {
			theForm = new VuserManageForm();
		}
		VuserManageObj obj = new VuserManageObj();
		obj.setUSER_ID(theForm.getUSER_ID());
		VuserManageObj tempObj = vuserManageService.queryByObj(obj);
		// TbBusiHostObj tbBusiHostObj = new TbBusiHostObj();
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "add";
	}

	/**
	 * @Title:删除V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public String delVuserManage() throws BaseException {
		if (theForm == null) {
			theForm = new VuserManageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		VuserManageObj obj = new VuserManageObj();
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		int ret = vuserManageService.deleteByObj(obj);
		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除V用户信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "list";
	}

	/**
	 * @Title:增加V用户信息时查询用户信息表弹出的窗口
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listUserInfoWindow() throws BaseException {
		if (userInfoForm == null) {
			userInfoForm = new UserInfoForm();
		}
		if (theForm == null) {
			theForm = new VuserManageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		if (theForm.getID() != 0) {
			obj.setID(theForm.getID());
		}

		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List vList = vuserManageService.queryForListByUserObj(obj);
		theForm.setResultList(vList);
		return "user_info";
	}

	private VuserManageService vuserManageService;

	public void setVuserManageService(VuserManageService vuserManageService) {
		this.vuserManageService = vuserManageService;
	}
}
