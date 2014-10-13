package com.sitech.basd.sxcloud.cloud.web.ajax.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.envmanager.domain.scriptmanage.ScriptmanageObj;
import com.sitech.basd.envmanager.service.scriptmanage.ScriptmanageService;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.templet.TempletObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualPoolObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.templet.TempletService;
import com.sitech.basd.sxcloud.cloud.service.virtual.VirtualPoolService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class AjaxAction extends CRUDBaseAction {

	public String getTempletList() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String TYPE = request.getParameter("TYPE");
		StringBuffer buff = new StringBuffer("{");
		String info = "";
		TempletObj obj = new TempletObj();
		obj.setTYPE(TYPE);
		List listTree = templetService.queryConByObjBase(obj);
		for (int i = 0; i < listTree.size(); i++) {
			TempletObj objT = new TempletObj();
			objT = (TempletObj) listTree.get(i);
			if (i != 0) {
				buff.append(",");
			}
			buff.append("'");
			buff.append((String) objT.getKEY());
			buff.append("':'");
			buff.append((String) objT.getVALUE());
			buff.append("'");
		}
		buff.append("}");
		info = buff.toString();
		request.setAttribute("info", info);
		return SUCCESS;
	}
	
	public String getStrategyList() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String TEM_ID = request.getParameter("TEM_ID");
		StringBuffer buff = new StringBuffer("{");
		String info = "";
		TempletObj obj = new TempletObj();
		obj.setTEM_ID(TEM_ID);
		//getCPUwithTemp
		List templet = templetService.queryForListValue(obj);
		Struts2Utils.getRequest().setAttribute("templet", templet);
		int count = 1;
		for (int i = 0; i < templet.size(); i++) {
			TempletObj objT = new TempletObj();
			objT = (TempletObj) templet.get(i);
			if (i != 0) {
				buff.append(",");
			}
			buff.append("'");
			buff.append("KV"+count);
			buff.append("':'");
			buff.append((String) objT.getVALUE());
			buff.append("'");
			count++;
		}
		buff.append("}");
		info = buff.toString();
		request.setAttribute("info", info);
		return SUCCESS;
	}
	
	/**
	 * 
	 * @Title:得到应用的IP地址
	 * @Copyright: Copyright (c) Mar 26, 2012
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String getAppIp()
			throws BaseException {
		String APP_TYPE = Struts2Utils.getRequest().getParameter("APP_TYPE");
		StringBuffer buff = new StringBuffer("{");
		String info = "";
		if (APP_TYPE.equals("1")) {
			// VMHostObj vmObj = new VMHostObj();
			TbCloud2VirtualPoolObj vmObj = new TbCloud2VirtualPoolObj();
			// List listTree = vmHostService.queryForListByVMHostObj(vmObj);
			List listTree = virtualPoolService.queryForListByObj(vmObj);
			int j = 0;
			for (int i = 0; i < listTree.size(); i++) {
				// VMHostObj objT = new VMHostObj();
				TbCloud2VirtualPoolObj objT = new TbCloud2VirtualPoolObj();
				objT = (TbCloud2VirtualPoolObj) listTree.get(i);
				if (objT.getVH_IP() != null && !"".equals(objT.getVH_IP())) {
					if (j != 0) {
						buff.append(",");
					}
					buff.append("'");
					buff.append((String) objT.getVH_ID());
					buff.append("':'");
					buff.append((String) objT.getVH_IP());
					buff.append("'");
					j++;
				}
			}
		}
		if (APP_TYPE.equals("0")) {
			// TbBusiHostObj tbObj = new TbBusiHostObj();
			TbCloud2HostInfoObj tbObj = new TbCloud2HostInfoObj();
			tbObj.setEq_type("1");
			List listTree = hostInfoService.queryForListByType(tbObj);
			// List listTree = busiHostService.queryForListByObj(tbObj);
			int j = 0;
			for (int i = 0; i < listTree.size(); i++) {
				TbCloud2HostInfoObj objT = new TbCloud2HostInfoObj();
				objT = (TbCloud2HostInfoObj) listTree.get(i);
				if (objT.getEq_ip() != null && !"".equals(objT.getEq_ip())) {
					if (j != 0) {
						buff.append(",");
					}
					buff.append("'");
					buff.append((String) objT.getEq_id());
					buff.append("':'");
					buff.append((String) objT.getEq_ip());
					buff.append("'");
					j++;
				}
			}
		}
		buff.append("}");
		info = buff.toString();
		Struts2Utils.getRequest().setAttribute("info", info);
		return SUCCESS;
	}
	/**
	 * 
	 * @Title:得到主机用户名
	 * @Copyright: Copyright (c) May , 2013
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String getHostId(){
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		StringBuffer buff = new StringBuffer("{");
		String info = "";
		ScriptmanageObj obj = new ScriptmanageObj ();
		obj.setHost_id(id);
		List hostidList = scriptmanageService.queryConfigObj(obj);
		
		int count = 0;
		for (int i = 0; i < hostidList.size(); i++) {
			// VMHostObj objT = new VMHostObj();
			ScriptmanageObj sobj = new ScriptmanageObj ();
			sobj = (ScriptmanageObj) hostidList.get(i);
			if (sobj.getHOSTUSERNAME()!= null && !"".equals(sobj.getHOSTUSERNAME())) {
				if (count != 0) {
					buff.append(",");
				}
				buff.append("'");
				buff.append((String) sobj.getId());
				buff.append("':'");
				buff.append((String) sobj.getHOSTUSERNAME());
				buff.append("'");
				count++;
			}
		}
		buff.append("}");
		info = buff.toString();
		Struts2Utils.getRequest().setAttribute("info", info);
		return SUCCESS;
	}
	
	/**
	 * 
	 * @Title:判断是否存在IP
	 * @Copyright: Copyright (c) May , 2013
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String getCheckIP(){
		HttpServletRequest request = Struts2Utils.getRequest();
		String EQ_IP = request.getParameter("EQ_IP");
		StringBuffer buff = new StringBuffer("{");
		String info = "";
		ScriptmanageObj obj = new ScriptmanageObj ();
		obj.setEQ_IP(EQ_IP);
		List hostidList = scriptmanageService.queryScriptmanageObj(obj);
		
		int count = 0;
		for (int i = 0; i < hostidList.size(); i++) {
//			// VMHostObj objT = new VMHostObj();
//			ScriptmanageObj sobj = new ScriptmanageObj ();
//			sobj = (ScriptmanageObj) hostidList.get(i);
//			if (sobj.getHOSTUSERNAME()!= null && !"".equals(sobj.getHOSTUSERNAME())) {
//				if (count != 0) {
//					buff.append(",");
//				}
//				buff.append("'");
//				buff.append((String) sobj.getId());
//				buff.append("':'");
//				buff.append((String) sobj.getHOSTUSERNAME());
//				buff.append("'");
				count++;
			}
	
		if(count!=0){
			request.setAttribute("info", "服务器IP已存在");
			
		}
		
		return SUCCESS;
	}
	private ScriptmanageService scriptmanageService;
	private TempletService templetService;
	private HostInfoService hostInfoService;
	private VirtualPoolService virtualPoolService;
	public void setVirtualPoolService(VirtualPoolService virtualPoolService) {
		this.virtualPoolService = virtualPoolService;
	}

	public void setTempletService(TempletService templetService) {
		this.templetService = templetService;
	}
	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public ScriptmanageService getScriptmanageService() {
		return scriptmanageService;
	}

	public void setScriptmanageService(ScriptmanageService scriptmanageService) {
		this.scriptmanageService = scriptmanageService;
	}
	
}
