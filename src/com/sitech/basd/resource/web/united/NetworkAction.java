/**
 * NetworkAction.java
 * com.sitech.basd.resource.web.united
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 四月 7 		duangh
 *
 * Copyright (c) 2014, si-tech All Rights Reserved.
 */

package com.sitech.basd.resource.web.united;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.sitech.basd.resource.service.united.UnitedNetworkService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.vo.united.HostPnicVO;
import com.sitech.vo.united.HostVSwitchVO;

/**
 * ClassName:NetworkAction Function: vmware网络管理
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 四月 7 15:10:52
 */
@Controller("networkAction")
@Scope("prototype")
public class NetworkAction extends BaseAction {
	@Resource
	private UnitedNetworkService unitedNetworkService;
	
	/**
	 * 
	 * vswitchpg:vmware查看网络信息
	 * 
	 * @since duangh Ver 1.0
	 */
	public String vswitchpg() {
		String connect_id = request.getParameter("connect_id");
		String uuid = request.getParameter("uuid");
//		String vtype = request.getParameter("vtype");
		
		//因为存在两块物理网卡连接一个交换机或者不连接交换机的情况，修改原来的先获取物理网卡情况
//		List<HostPnicVO> pnicList = unitedNetworkService.getpnicInfo(connect_id, uuid, vtype);
//		ActionContext.getContext().getValueStack().set("pnicList", pnicList);
		List<HostVSwitchVO> vsList = unitedNetworkService.getVswitchInfo(connect_id, uuid);
		
		ActionContext.getContext().getValueStack().set("vsList", vsList);
		ActionContext.getContext().getValueStack().set("connect_id", connect_id);
		ActionContext.getContext().getValueStack().set("uuid", uuid);
		return SUCCESS;
	}

	// 获取
	@Deprecated
	public String vswitchInfo() {
		String connect_id = request.getParameter("connect_id");
		String uuid = request.getParameter("uuid");
		String pKey = request.getParameter("pKey");
		List<HostVSwitchVO> vsList = unitedNetworkService.getVswitchInfo(connect_id, uuid);
		PrintWriterOut.printWirter(response, JacksonUtil.toJson(vsList));
		return null;
	}
	//编辑交换机属性页面
	public String editVswitch(){
		String connect_id = request.getParameter("connect_id");
		String uuid = request.getParameter("uuid");
//		String pKey = request.getParameter("pKey");
		String vsName = request.getParameter("vsName");
		List<HostVSwitchVO> resultList = unitedNetworkService.getStandandVswitchPG(connect_id, uuid, vsName);
		for(HostVSwitchVO vo:resultList){
			if(vo.getVsName().equals(vsName)){
				ActionContext.getContext().getValueStack().set("resultList", vo.getPgList());
			}
		}
		ActionContext.getContext().getValueStack().set("connect_id", connect_id);
		ActionContext.getContext().getValueStack().set("uuid", uuid);
//		ActionContext.getContext().getValueStack().set("pKey", pKey);
		return SUCCESS;
	}
	//设置VLAN信息
	public void setVlan(){
		String connect_id= request.getParameter("connect_id");
		String uuid= request.getParameter("uuid");
		String pgName= request.getParameter("pgName");
		String vlanId= request.getParameter("vlanId");
		String result = unitedNetworkService.setVlan(connect_id, uuid, pgName, vlanId);
		PrintWriterOut.printWirter(response, result);
	}
}
