package com.sitech.basd.sxcloud.cloud.web.virtual.action;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.cloud.service.virtual.TbCloud2VirtualConfigService;
import com.sitech.basd.sxcloud.cloud.service.virtual.VirtualService;
import com.sitech.basd.sxcloud.cloud.web.virtual.form.TbCloud2VirtualConfigForm;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigHisObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class TbCloud2VirtualConfigAction extends BaseAction {
	private TbCloud2VirtualConfigForm theForm ;
	
	public TbCloud2VirtualConfigForm getTheForm() {
		return theForm;
	}

	public void setTheForm(TbCloud2VirtualConfigForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: listBusiHostConfig
	 * @Description: 获取主机配置列表请求
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 6:02:56 PM
	 */
	public String listVirtualConfig() throws BaseException {
		if(theForm ==null){
			theForm = new TbCloud2VirtualConfigForm();
		}
		theForm.setID(0);
		TbCloud2VirtualConfigObj obj = new TbCloud2VirtualConfigObj();
		String id = Struts2Utils.getRequest().getParameter("id");
		if (id != null) {
			theForm.setHOSTID(id);
		}
		obj.setHOSTID(theForm.getHOSTID());
		obj.setHOSTNAME(theForm.getHOSTNAME());
		if (theForm.getHOSTUSERNAME() != null
				&& !"".equals(theForm.getHOSTUSERNAME())) {
			obj.setHOSTUSERNAME(theForm.getHOSTUSERNAME());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		obj.setSTATUS(1);
		List userInfoList = tbCloud2VirtualConfigService.queryForListByObj(obj);
		theForm.setResultList(userInfoList);
		return "list";
	}

	/**
	 * 
	 * @Title: addBusiHostConfig
	 * @Description: 增加主机配置请求
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 6:03:07 PM
	 */
	public String addVirtualConfig() throws BaseException {
		if(theForm ==null){
			theForm = new TbCloud2VirtualConfigForm();
		}
		TbCloud2VirtualInfoObj virtualHostobj = new TbCloud2VirtualInfoObj();
		String HOSTID = Struts2Utils.getRequest().getParameter("HOSTID");
		virtualHostobj.setVH_ID(HOSTID);
		List virtualHostList = virtualService.queryForListByObj(virtualHostobj);
		theForm.setHOSTNAME(((TbCloud2VirtualInfoObj) virtualHostList.get(0))
				.getVH_NAME());
		theForm.setHOSTID(((TbCloud2VirtualInfoObj) virtualHostList.get(0))
				.getVH_ID());
		theForm.setSTATUS(1);
		return "add";
	}

	/**
	 * 
	 * @Title: saveBusiHostConfig
	 * @Description: 保存主机配置请求
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 6:03:15 PM
	 */
	public String saveVirtualConfig() throws BaseException {
		if(theForm == null){
			theForm = new TbCloud2VirtualConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2VirtualConfigObj obj = new TbCloud2VirtualConfigObj();
		String HOSTID = request.getParameter("HOSTID");
		obj.setID(theForm.getID());
		obj.setHOSTID(HOSTID);
		// obj.setHOSTNAME(theForm.getHOSTNAME());
		obj.setHOSTUSERNAME(theForm.getHOSTUSERNAME());
		obj.setHOSRPWD(theForm.getHOSRPWD());
		obj.setSPACE(theForm.getSPACE());
		// obj.setTYPE(theForm.getTYPE());
		obj.setSTATUS(theForm.getSTATUS());
		/*
		 * try { BeanUtils.copyProperties(obj, theForm); } catch
		 * (IllegalAccessException e) { e.printStackTrace(); } catch
		 * (InvocationTargetException e) { e.printStackTrace(); }
		 */
		int ret = 0;
		int result = 0;

		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");

		if (theForm.getID() == 0) {
			obj.setHOSTID(HOSTID);
			// if (obj.getTYPE() == 1) {
			obj.setSTATUS(1);
			// }
			ret = tbCloud2VirtualConfigService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增主机配置信息");

		} else {
			ret = tbCloud2VirtualConfigService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改主机配置信息");
			if (ret > 0) {
				// 修改主机配置信息成功的同时将删除信息插入到主机配置历史记录表
				TbBusiHostConfigHisObj busihostConfigHisObj = new TbBusiHostConfigHisObj();
				try {
					BeanUtils.copyProperties(busihostConfigHisObj, theForm);
				} catch (Exception e) {
					e.printStackTrace();
				}
//				TbSysUserinfoObj tempObj = (TbSysUserinfoObj) request
//						.getSession().getAttribute(Constant.USER_SESSION_KEY);
				busihostConfigHisObj.setUPDATEUSER(session.get("name").toString());
				busihostConfigHisObj.setUPDATETYPE("1");
				// busiHostConfigHisService.insertByObj(busihostConfigHisObj);
			}
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "save";
	}

	/**
	 * 
	 * @Title: modBusiHostConfig
	 * @Description: 修改主机配置请求
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 6:03:27 PM
	 */
	public String modVirtualConfig() throws BaseException {
		if(theForm  == null){
			theForm = new TbCloud2VirtualConfigForm();
		}
		TbCloud2VirtualConfigObj obj = new TbCloud2VirtualConfigObj();
		obj.setID(theForm.getID());
		TbCloud2VirtualConfigObj tempObj = tbCloud2VirtualConfigService
				.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
			theForm.setHOSTUSERNAME_BF(tempObj.getHOSTUSERNAME());
			theForm.setTYPE_BF(tempObj.getTYPE());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "mod";
	}

	/**
	 * 
	 * @Title: delBusiHostConfig
	 * @Description: 删除主机配置信息请求
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 6:03:37 PM
	 */
	public String delVirtualConfig() throws BaseException {
		if(theForm  == null){
			theForm = new TbCloud2VirtualConfigForm();
		}
		TbCloud2VirtualConfigObj obj = new TbCloud2VirtualConfigObj();
		int result = 0;
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		obj.setID(theForm.getID());
		int ret = tbCloud2VirtualConfigService.deleteByObj(obj);

		// TbCloud2VirtualConfigObj busihostConfigObj =
		// tbCloud2VirtualConfigService
		// .queryByObj(obj);
		// TbCloud2VirtualConfigObj tempHostObj = tbCloud2VirtualConfigService
		// .queryByObj(busihostConfigObj);
		// tempHostObj.setSTATUS(2);
		// tempHostObj.setEXECUTE_FLAG(0);
		// int ret = tbCloud2VirtualConfigService.updateByObj(tempHostObj);
		/*
		 * if (ret > 0) { // 删除主机配置信息成功的同时将删除信息插入到主机配置历史记录表
		 * TbBusiHostConfigHisObj busihostConfigHisObj = new
		 * TbBusiHostConfigHisObj(); try {
		 * BeanUtils.copyProperties(busihostConfigHisObj, busihostConfigObj); }
		 * catch (Exception e) { e.printStackTrace(); } TbSysUserinfoObj tempObj =
		 * (TbSysUserinfoObj) request.getSession()
		 * .getAttribute(Constant.USER_SESSION_KEY);
		 * 
		 * busihostConfigHisObj.setUPDATEUSER(tempObj.getNAME());
		 * busihostConfigHisObj.setUPDATETYPE("2"); //
		 * busiHostConfigHisService.insertByObj(busihostConfigHisObj); result =
		 * 1; } // 插入操作日志表 TbSysOperationLogObj operObj =
		 * this.getTbSysOperationLogObj(request); operObj.setOPERTYPE(2);
		 * operObj.setMESSAGE("删除主机配置信息"); operObj.setREMARK("");
		 * operObj.setRESULT(result); @SuppressWarnings("unused") int retOper =
		 * operationService.insertByObj(operObj);// 写操作日志
		 */
		return "del";
	}

	/**
	 * 
	 * @Title: queryAjx_username
	 * @Description: 验证主机用户是否已经存在
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 6:03:47 PM
	 */
	public String queryAjx_Virtualusername() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2VirtualConfigObj obj = new TbCloud2VirtualConfigObj();
		String username = request.getParameter("hostusername");
		String hostid = request.getParameter("hostid");
		obj.setHOSTUSERNAME(username);
		obj.setHOSTID(hostid);
		TbCloud2VirtualConfigObj objt = tbCloud2VirtualConfigService
				.queryByObj(obj);
		try {
			request.setCharacterEncoding("gb2312");// 字符串转换
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			if (objt == null) {
				out.print("ok");
			} else {
				out.print("no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: queryAjx_usertype
	 * @Description: 验证超级管理员是否唯一
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 6:03:56 PM
	 */
	public String queryAjx_Virtualusertype() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2VirtualConfigObj obj = new TbCloud2VirtualConfigObj();
		String usertype = request.getParameter("usertype");
		String hostid = request.getParameter("hostid");
		obj.setTYPE(Integer.parseInt(usertype));
		obj.setHOSTID(hostid);
		TbCloud2VirtualConfigObj objt = tbCloud2VirtualConfigService
				.queryByObj(obj);
		try {
			request.setCharacterEncoding("gb2312");// 字符串转换
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			if (objt == null) {
				out.print("ok");
			} else {
				out.print("no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: queryAjx_type
	 * @Description: 删除用户时验证用户是否为超级管理员用户，如果是则不能删除
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 6:04:04 PM
	 */
	public String queryAjx_Virtualtype() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2VirtualConfigObj obj = new TbCloud2VirtualConfigObj();
		String userid = request.getParameter("userid");
		obj.setID(Integer.parseInt(userid));
		TbCloud2VirtualConfigObj objt = tbCloud2VirtualConfigService
				.queryByObj(obj);
		try {
			request.setCharacterEncoding("gb2312");// 字符串转换
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			if (objt.getTYPE() == 1) {
				out.print("ok");
			} else {
				out.print("no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private TbCloud2VirtualConfigService tbCloud2VirtualConfigService;
	private VirtualService virtualService;

	public void setTbCloud2VirtualConfigService(
			TbCloud2VirtualConfigService tbCloud2VirtualConfigService) {
		this.tbCloud2VirtualConfigService = tbCloud2VirtualConfigService;
	}

	public void setVirtualService(VirtualService virtualService) {
		this.virtualService = virtualService;
	}

}
