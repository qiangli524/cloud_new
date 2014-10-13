package com.sitech.basd.sxcloud.cloud.web.virtual.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualPoolObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.virtual.TbCloud2VirtualConfigService;
import com.sitech.basd.sxcloud.cloud.service.virtual.VirtualPoolService;
import com.sitech.basd.sxcloud.cloud.util.DES3;
import com.sitech.basd.sxcloud.cloud.web.virtual.form.TbCloud2VirtualConfigForm;
import com.sitech.basd.sxcloud.cloud.web.virtual.form.VirtualPoolForm;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

@SuppressWarnings("all")
public class VirtualPoolAction extends BaseAction {
	private VirtualPoolForm theForm ;
	private TbCloud2VirtualConfigForm thisForm ;
	
	public TbCloud2VirtualConfigForm getThisForm() {
		return thisForm;
	}

	public void setThisForm(TbCloud2VirtualConfigForm thisForm) {
		this.thisForm = thisForm;
	}

	public VirtualPoolForm getTheForm() {
		return theForm;
	}

	public void setTheForm(VirtualPoolForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: listVirtualPool
	 * @Description: 获取一监控虚拟机请求
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Mar 31, 2012 6:02:56 PM
	 */
	public String listVirtualPool() throws BaseException {
		if(theForm ==null){
			theForm = new VirtualPoolForm();
		}
		TbCloud2VirtualPoolObj obj = new TbCloud2VirtualPoolObj();
		if (theForm.getVH_ID() != null && !"".equals(theForm.getVH_ID())) {
			obj.setVH_ID(theForm.getVH_ID().trim());
		}
		if (theForm.getVH_NAME() != null && !"".equals(theForm.getVH_NAME())) {
			obj.setVH_NAME(theForm.getVH_NAME().trim());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List resultList = virtualPoolService.queryForListByObj(obj);
		theForm.setResultList(resultList);
		return "listPool";
	}

	/**
	 * 
	 * @Title: addVirtualToPool
	 * @Description: 增加监控虚拟机
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Mar 31, 2012
	 */
	public String addVirtualToPool() throws BaseException {
		if(theForm ==null){
			theForm = new VirtualPoolForm();
		}
		TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
		List eqidList = hostInfoService.queryForListByObj(hostObj);
		theForm.setEqidList(eqidList);
		return "add";
	}

	/**
	 * 
	 * @Title: saveVirtualToPool
	 * @Description: 保存主机配置请求
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Mar 31, 2012
	 */
	public String saveVirtualToPool() throws BaseException {
		if(theForm ==null){
			theForm = new VirtualPoolForm();
		}
		TbCloud2VirtualPoolObj obj = new TbCloud2VirtualPoolObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		int ret = 0;
		int result = 0;

		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");

		if (theForm.getFlag() == 0) {
			ret = virtualPoolService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增主机配置信息");

		} else {
			obj.setVH_ID(theForm.getVH_ID());
			ret = virtualPoolService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改主机配置信息");
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
	 * @Title: modVirtualFromPool
	 * @Description: 修改监控的虚拟机信息
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Mar 27, 2012
	 */
	public String modVirtualFromPool() throws BaseException {
		if(theForm ==null){
			theForm = new VirtualPoolForm();
		}
		TbCloud2VirtualPoolObj obj = new TbCloud2VirtualPoolObj();
		obj.setVH_ID(theForm.getVH_ID());
		TbCloud2VirtualPoolObj tempObj = virtualPoolService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
			theForm.setVH_ID(obj.getVH_ID());
			theForm.setFlag(1);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
		List eqidList = hostInfoService.queryForListByObj(hostObj);
		theForm.setEqidList(eqidList);
		return "mod";
	}

	/**
	 * 
	 * @Title: delBusiHostConfig
	 * @Description: 删除监控的虚拟机信息
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Mar 31, 2012
	 */
	public String delVirtualFromPool() throws BaseException {
		if(theForm ==null){
			theForm = new VirtualPoolForm();
		}
		TbCloud2VirtualPoolObj obj = new TbCloud2VirtualPoolObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		obj.setVH_ID(theForm.getVH_ID());
		int ret = virtualPoolService.deleteByObj(obj);

		return "del";
	}

	/**
	 * 
	 * @Title: listVirPoolConfig
	 * @Description: 查询虚拟机用户配置
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Mar 31, 2012
	 */
	public String listVirPoolConfig() throws BaseException {
		if(thisForm ==null){
			thisForm = new TbCloud2VirtualConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2VirtualConfigObj obj = new TbCloud2VirtualConfigObj();
		String vh_id = request.getParameter("VH_ID");
		if (vh_id != null && !"".equals(vh_id)) {
			obj.setHOSTID(vh_id);
			thisForm.setHOSTID(vh_id);
		}
		if (thisForm.getHOSTUSERNAME() != null
				&& !"".equals(thisForm.getHOSTUSERNAME())) {
			obj.setHOSTUSERNAME(thisForm.getHOSTUSERNAME());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页

		List resultList = tbCloud2VirtualConfigService.queryForListByObj(obj);
		
		thisForm.setResultList(resultList);
		return "listConfig";
	}

	/**
	 * 
	 * @Title: addVirPoolConfig
	 * @Description: 添加虚拟机用户配置
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Mar 31, 2012
	 */
	public String addVirPoolConfig() throws BaseException {
		if(thisForm ==null){
			thisForm = new TbCloud2VirtualConfigForm();
		}
		
		// TbCloud2VirtualConfigObj obj = new TbCloud2VirtualConfigObj();
		String vh_id = (String) Struts2Utils.getRequest().getParameter("VH_ID");
		if (vh_id != null && !"".endsWith(vh_id)) {
			thisForm.setHOSTID(vh_id);
		}
		return "addConfig";
	}

	/**
	 * 
	 * @Title: modVirPoolConfig
	 * @Description: 修改虚拟机用户配置
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Mar 31, 2012
	 */
	public String modVirPoolConfig() throws BaseException {
		if(thisForm ==null){
			thisForm = new TbCloud2VirtualConfigForm();
		}
		TbCloud2VirtualConfigObj obj = new TbCloud2VirtualConfigObj();
		int id = Integer.parseInt((String) Struts2Utils.getRequest().getParameter("id"));
		obj.setID(id);
		obj = tbCloud2VirtualConfigService.queryByObj(obj);
		String pwd = DES3.decrypt(obj.getHOSRPWD()); // 解密密码
		try {
			BeanUtils.copyProperties(thisForm, obj);
			thisForm.setFlag(1);
			thisForm.setHOSRPWD(pwd);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return "modConfig";
	}

	/**
	 * 
	 * @Title: saveVirPoolConfig
	 * @Description: 保存虚拟机用户配置
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Mar 31, 2012
	 */
	public String saveVirPoolConfig() throws BaseException {
		if(thisForm ==null){
			thisForm = new TbCloud2VirtualConfigForm();
		}
		TbCloud2VirtualConfigObj obj = new TbCloud2VirtualConfigObj();
		String password = DES3.encrypt(thisForm.getHOSRPWD());// 加密密码
		int ret;
		try {
			BeanUtils.copyProperties(obj, thisForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		String hostId = thisForm.getHOSTID();
		obj.setHOSTID(hostId);
		obj.setHOSRPWD(password);
		if (thisForm.getFlag() == 0) {
			ret = tbCloud2VirtualConfigService.insertByObj(obj);
		} else {
			obj.setID(thisForm.getID());
			ret = tbCloud2VirtualConfigService.updateByObj(obj);
		}
		// return new ActionForward("/listVirPoolConfig.do?VH_ID=" + hostId);
		try {
			Struts2Utils.getResponse().sendRedirect("virpool_listVirPoolConfig.do?VH_ID=" + hostId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		// return mapping.findForward("default");
	}

	/**
	 * 
	 * @Title: modVirPoolConfig
	 * @Description: 删除虚拟机用户配置
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Mar 31, 2012
	 */
	public String delVirPoolConfig() throws BaseException {
		if(thisForm ==null){
			thisForm = new TbCloud2VirtualConfigForm();
		}
		TbCloud2VirtualConfigObj obj = new TbCloud2VirtualConfigObj();
		int id = Integer.parseInt((String) Struts2Utils.getRequest().getParameter("id"));
		obj.setID(id);

		try {
			BeanUtils.copyProperties(obj, thisForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// obj = tbCloud2VirtualConfigService.queryByObj(obj);
		String vh_id = obj.getHOSTID();
		obj.setID(id);
		int ret = tbCloud2VirtualConfigService.deleteByObj(obj);
		// return new ActionForward("/listVirPoolConfig.do?VH_ID=" + vh_id);
		try {
			Struts2Utils.getResponse().sendRedirect("virpool_listVirPoolConfig.do?VH_ID=" + vh_id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		// return mapping.findForward("default");
	}

	/**
	 * 
	 * @Title: checkVirtualCanDel
	 * @Description: 检查虚拟机是否能删除
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Mar 27, 2012
	 */
	public String checkVirtualCanDel() throws BaseException {
	//	if(thisForm ==null){
	//		thisForm = new TbCloud2VirtualConfigForm();
	//	}
		TbCloud2VirtualConfigObj obj = new TbCloud2VirtualConfigObj();
		String vhid = (String)Struts2Utils.getRequest().getParameter("VH_ID");
		if(vhid !=null && !"".equals(vhid)){
			obj.setHOSTID(vhid);
		}
	//	obj.setHOSTID(thisForm.getVH_ID());
		List<?> list = tbCloud2VirtualConfigService.queryForListByObj(obj);
		List<String> jsonArr = new ArrayList<String>();
		JSONArray json = new JSONArray();
		if (list != null && list.size() > 0) {
			jsonArr.add("NO");
		} else {
			jsonArr.add("YES");
		}
		try {
			json = JSONArray.fromObject(jsonArr);
			Struts2Utils.getResponse().getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
			LogHelper.debug("VirtualPoolAction.checkVirtualCanDel():"
					+ e.getMessage() + getClass().getName());
		}
		return null;
	}

	private VirtualPoolService virtualPoolService;
	private TbCloud2VirtualConfigService tbCloud2VirtualConfigService;
	private HostInfoService hostInfoService;

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setTbCloud2VirtualConfigService(
			TbCloud2VirtualConfigService tbCloud2VirtualConfigService) {
		this.tbCloud2VirtualConfigService = tbCloud2VirtualConfigService;
	}

	public void setVirtualPoolService(VirtualPoolService virtualPoolService) {
		this.virtualPoolService = virtualPoolService;
	}

}
