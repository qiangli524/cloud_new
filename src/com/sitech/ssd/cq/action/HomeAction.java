/**
 * Copyright (c) 2013 SI-TECH Software, Inc.
 * All right reserved. 
 * 
 * 云管理平台
 */
package com.sitech.ssd.cq.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.cloud3.domain.newui.HomePageSettingObj;
import com.sitech.basd.cloud3.service.newui.NewUIFuncService;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.workorder.WorkOrderObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.showresource.ShowResourceService;
import com.sitech.basd.sxcloud.cloud.service.workorder.WorkOrderService;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.FuncRoleObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.page.Pagination;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.ssd.cq.service.HomeService;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 *<P>
 * 类功能简述（例如：XXX功能）
 *</p>
 * Jun 19, 2014,3:48:05 PM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
@Controller("homeAction")
@Scope("prototype")
public class HomeAction  extends BaseAction {
	@Resource
	private HomeService homeService;
	@Autowired
	private ShowResourceService showResourceService;// 用于统计IP及告警
	@Autowired
	private NewUIFuncService newUIFuncService;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;
	private List list = new ArrayList();
	private List<HomePageSettingObj> homePageSettings;
	private List<HomePageSettingObj> leftPageSettings;
	private String name;
	
	public String home(){
		String groupId =request.getSession().getAttribute(Constant.GROUP_ID)==null ? "":request.getSession().getAttribute(Constant.GROUP_ID).toString();
		homePageSettings = newUIFuncService.getGroupHomePageSetting(groupId,"1");
		leftPageSettings = newUIFuncService.getGroupHomePageSetting(groupId,"2");
		return SUCCESS;
	}
	public String main(){
		//查询用户的所有菜单
		FuncRoleObj funcRoleObj = new FuncRoleObj();
		funcRoleObj.setACCOUNT(session.get("account").toString());
		funcRoleObj.setFUNCID("");
		funcRoleObj.setUSERID(Integer.parseInt(session.get("id").toString()));
		list = homeService.getMenus(funcRoleObj);
		//登陆人
		name = session.get("name").toString();

        /*//福建项目：移动动漫临时添加，演示完 删除，start
        String areaId = "c0ba3dfad670429095045fab86d5b45d";
        if (session.containsKey("areaid")) {
            session.remove("areaid");
            session.put("areaid", areaId);
        }else{
            session.put("areaid", areaId);
            LogHelper.info("设置用户地域成功："+areaId);
        }
        //福建项目：移动动漫临时添加，演示完 删除，end*/
		return SUCCESS;
	}
	/**
	 * 
	 * <p>获取告警信息</p>
	 *
	 */
	public void getAlarmHostStatistics(){
		HttpServletResponse response = Struts2Utils.getResponse();
		// 查询告警
		List alarmList = showResourceService.alarmHostStatistics();
		JSONArray array = JSONArray.fromObject(alarmList);
		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, array.toString());
		
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:获取代办工单的数量
	 *  @作者 
	 *	@时间 2014-9-18 上午9:18:11
	 *  @版权 si-tech 2014 All right reserved.
	 */
	public void getMyTaskCount(){
		HttpServletResponse response = Struts2Utils.getResponse();
		String userId = session.get("id").toString();
		String account = session.get("account").toString();
		WorkOrderObj workOrderObj = new WorkOrderObj();
		
		
		// 全局配置里边配置了几个用户的权限
		TbGlobalConfigObj global = new TbGlobalConfigObj();
		global.setKEY("user_auth");
		global = tbGlobalConfigService.queryByObj(global);
		String[] users = new String[] { "" };
		if (global != null) {
			users = global.getVALUE().split(",");
		}
		if (!"1".equals(userId)) {// 对于admin用户，不需要分配权限
			// 对于普通登录用户，需要进行权限控制
			int flag = 0;
			for (int i = 0; i < users.length; i++) {
				if (account.equals(users[i])) {
					flag = 1;
				}
			}
			if (flag != 1) {
				workOrderObj.setPROJECT_USER_ID(userId);
			}
		}
		List<Integer> returnList = new ArrayList<Integer>(); 
		//获取待办数量
		workOrderObj.setWSTAT(0);
		int waitCount = ((List<WorkOrderObj>)workOrderService.queryByObj(workOrderObj)).size();
		returnList.add(waitCount);
		//获取已办数量
		workOrderObj.setWSTAT(1);
		int alreadyCount = ((List<WorkOrderObj>)workOrderService.queryByObj(workOrderObj)).size();
		returnList.add(alreadyCount);
		//获取全部
//		workOrderObj.setWSTAT(null);
//		int allCount = ((List<WorkOrderObj>)workOrderService.queryByObj(workOrderObj)).size();
		returnList.add(waitCount+alreadyCount);
		JSONArray array = JSONArray.fromObject(returnList);
		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, array.toString());
	}
	/**
	 * 我的待办任务
	 */
	public void getMyWaitTask(){
		HttpServletResponse response = Struts2Utils.getResponse();
		String userId = session.get("id").toString();
		String account = session.get("account").toString();
		WorkOrderObj workOrderObj = new WorkOrderObj();
		workOrderObj.setPagination(this.getPaginater().initPagination(request));
		//查询状态为待办的工单
		workOrderObj.setWSTAT(0);
		// 全局配置里边配置了几个用户的权限
		TbGlobalConfigObj global = new TbGlobalConfigObj();
		global.setKEY("user_auth");
		global = tbGlobalConfigService.queryByObj(global);
		String[] users = new String[] { "" };
		if (global != null) {
			users = global.getVALUE().split(",");
		}
		if (!"1".equals(userId)) {// 对于admin用户，不需要分配权限
			// 对于普通登录用户，需要进行权限控制
			int flag = 0;
			for (int i = 0; i < users.length; i++) {
				if (account.equals(users[i])) {
					flag = 1;
				}
			}
			if (flag != 1) {
				workOrderObj.setPROJECT_USER_ID(userId);
			}

		}
		List<WorkOrderObj> resultList = workOrderService.queryByObj(workOrderObj);
		
		JSONArray array = JSONArray.fromObject(resultList);
		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		PrintWriterOut.printWirter(response, array.toString());
	}
	
	/**
	 *
	 * @return the list
	 */
	public List getList() {
		return list;
	}
	/**
	 *
	 * @param list the list to set
	 */
	public void setList(List list) {
		this.list = list;
	}
	/**
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	public List<HomePageSettingObj> getHomePageSettings() {
		return homePageSettings;
	}
	public void setHomePageSettings(List<HomePageSettingObj> homePageSettings) {
		this.homePageSettings = homePageSettings;
	}
	public List<HomePageSettingObj> getLeftPageSettings() {
		return leftPageSettings;
	}
	public void setLeftPageSettings(List<HomePageSettingObj> leftPageSettings) {
		this.leftPageSettings = leftPageSettings;
	}
	
}
