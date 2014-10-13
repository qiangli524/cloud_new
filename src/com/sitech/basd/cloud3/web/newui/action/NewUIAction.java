package com.sitech.basd.cloud3.web.newui.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.sitech.basd.cloud3.domain.newui.HomePageSettingObj;
import com.sitech.basd.cloud3.service.newui.NewUIFuncService;
import com.sitech.basd.heartbeat.service.HeartbeatInfoService;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: NewUIAction
 * </p>
 * <p>
 * Description: 新首页Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Feb 28, 2013 1:43:41 PM
 * 
 */
public class NewUIAction extends CRUDBaseAction {
	private static final Logger logger = Logger.getLogger(NewUIAction.class);
	private static final long serialVersionUID = -5842812776078845758L;
	private NewUIFuncService newUIFuncService;
	private String username;
	private String password;
	private String casurl;

	public String getCasurl() {
		return casurl;
	}

	public void setCasurl(String casurl) {
		this.casurl = casurl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Resource
	private HeartbeatInfoService heartbeatInfoService;

	public void setNewUIFuncService(NewUIFuncService newUIFuncService) {
		this.newUIFuncService = newUIFuncService;
	}

	private Map<String, String> beatMap = new HashMap<String, String>();

	public Map<String, String> getBeatMap() {
		return beatMap;
	}

	public void setBeatMap(Map<String, String> beatMap) {
		this.beatMap = beatMap;
	}

	/**
	 * 
	 * @Title: index
	 * @Description: 登陆后首页
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 28, 2013 1:45:46 PM
	 */
	public String index() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String module_name = request.getParameter("module_name");
		if (module_name != null) {
			module_name = module_name.trim();
		} else {
			module_name = "";
		}
		// 根据角色ID查找对应的首页
		if ("请输入应用名称".equals(module_name)) {
			module_name = "";
		}
		
		// TbSysUserinfoObj user = (TbSysUserinfoObj)
		// request.getSession().getAttribute(Constant.USER_SESSION_KEY);
		String ret = "error";
		try {
			String[] user = new String[] { request.getSession().getAttribute("id").toString(),
					(String) request.getSession().getAttribute("account"),
					(String) request.getSession().getAttribute("name") };
			if (user == null || user.length == 0) {
				return ret;
			}
			// 取得一级模块
			List oneList = this.initDefultModule(user[1], module_name);
			// 所有的一级模块
			request.setAttribute("displayModule", oneList);
			if (module_name == null) {
				// 把当前位置放入SESSION
				// NaviDisplay.getYourNaviURLA(request, role_id);
			}
			// 公告信息
			Map<String, String> information = new HashMap<String, String>();
			information.put("CONTENT", "欢迎访问云管理平台");
			request.setAttribute("information", information);
			// 将自监控信息展示在公告栏
			Map<String, String> map = heartbeatInfoService.getHeartbeatInfo();
			this.beatMap.putAll(map);
			/*
			 * 回传当前用户信息，用于CAS登录
			 */
			String account = (String) request.getSession().getAttribute("account");
			TbSysUserinfoObj tbSysUserinfoObj = newUIFuncService.queryUserInfo(account);
			this.username = tbSysUserinfoObj.getACCOUNT();
			this.password = tbSysUserinfoObj.getPASSWORD();
			this.casurl = newUIFuncService.queryCasUrlByGlobalConfig();
			ret = SUCCESS;
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 
	 * @Title: initDefultModule
	 * @Description: 实例用户默认模块
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 28, 2013 1:58:00 PM
	 */
	public List<Map<String, String>> initDefultModule(String loginid, String module_name) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("LOGINID", loginid);
		params.put("FUNNAME", module_name);
		List<Map<String, String>> oneModuleList = newUIFuncService.getNewUIFunc_new(params);
		return oneModuleList;
	}

	/**
	 * 
	 * @Title: getUserAllowAddName
	 * @Description: 获取当前用户可以添加列表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 1, 2013 1:12:50 PM
	 */
	public String getUserAllowAddName() {
		try {
			HttpServletRequest request = Struts2Utils.getRequest();
			HttpServletResponse response = Struts2Utils.getResponse();
			// TbSysUserinfoObj user = (TbSysUserinfoObj)
			// request.getSession().getAttribute(Constant.USER_SESSION_KEY);
			String[] user = new String[] { request.getSession().getAttribute("id").toString(),
					request.getSession().getAttribute("account").toString(),
					request.getSession().getAttribute("name").toString() };
			String result = "";
			String loginid = user[1];
			if (user == null) {
				return "[]";
			}
			String moudle_name = request.getParameter("module_name");
			if (moudle_name == null) {
				moudle_name = "";
			}
			moudle_name = java.net.URLDecoder.decode(moudle_name, "UTF-8");
			Map<String, String> params = new HashMap<String, String>();
			params.put("LOGINID", loginid);
			params.put("FUNNAME", moudle_name);
			List<Map<String, String>> list = newUIFuncService.getNewUIAllowAdd(params);
			JSONArray ja = JSONArray.fromObject(list);
			result = ja.toString();
			response.setContentType("text/html;charset=UTF-8");
			// out.print(result);
			PrintWriterOut.printWirter(response, result);
			// out.flush();
			// out.close();
		} catch (Exception e) {
			logger.error("异常:" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: addMoudleToDesk
	 * @Description: 添加模块到首页--桌面-index
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 1, 2013 4:04:11 PM
	 */
	public String addMoudleToDesk() {
		try {
			HttpServletRequest request = Struts2Utils.getRequest();
			HttpServletResponse response = Struts2Utils.getResponse();
			String result = "";
			// TbSysUserinfoObj user = (TbSysUserinfoObj)
			// request.getSession().getAttribute(Constant.USER_SESSION_KEY);
			String[] user = new String[] { request.getSession().getAttribute("id").toString(),
					request.getSession().getAttribute("account").toString(),
					request.getSession().getAttribute("name").toString() };
			String moduleIdStr = request.getParameter("moduleId");
			if (user == null) {
				result = "[]";
			}
			String loginid = user[1];
			if (null != moduleIdStr && !"".equals(moduleIdStr)) {
				String module_array[] = moduleIdStr.split(",");
				for (int i = 0; i < module_array.length; i++) {
					Map<String, String> params = new HashMap<String, String>();
					params.put("LOGINID", loginid);
					params.put("FUNCID", module_array[i]);
					newUIFuncService.insertNewUIUserFunc(params);
				}
			}
			response.setContentType("text/html; charset=UTF-8");
			// PrintWriter out;
			// out = response.getWriter();
			// out.print("1");
			PrintWriterOut.printWirter(response, "1");
			// out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: delMoudleToDesk
	 * @Description: 删除用户桌面图标
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 1, 2013 4:50:43 PM
	 */
	public String delMoudleToDesk() {
		try {
			HttpServletRequest request = Struts2Utils.getRequest();
			HttpServletResponse response = Struts2Utils.getResponse();
			String result = "";
			// TbSysUserinfoObj user = (TbSysUserinfoObj)
			// request.getSession().getAttribute(Constant.USER_SESSION_KEY);
			String[] user = new String[] { request.getSession().getAttribute("id").toString(),
					request.getSession().getAttribute("account").toString(),
					request.getSession().getAttribute("name").toString() };
			if (user == null) {
				result = "[]";
			}
			String loginid = user[1];
			String moduleId = request.getParameter("moduleId");
			if (null != moduleId && !"".equals(moduleId)) {
				Map<String, String> params = new HashMap<String, String>();
				params.put("LOGINID", loginid);
				params.put("FUNCID", moduleId);
				newUIFuncService.deleteNewUIUserFunc(params);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
}
