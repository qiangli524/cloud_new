package com.sitech.basd.sxcloud.rsmu.web.system.action;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.cloud3.domain.newui.HomePageSettingObj;
import com.sitech.basd.cloud3.service.newui.NewUIFuncService;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.FuncRoleObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbCloud2SecurityConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysFunctionsObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysLoginLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserShortcutObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TempSysUserShortctObj;
import com.sitech.basd.sxcloud.rsmu.service.system.FunctionsService;
import com.sitech.basd.sxcloud.rsmu.service.system.LoginService;
import com.sitech.basd.sxcloud.rsmu.service.system.TbCloud2SecurityConfigService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.system.form.LoginForm;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.rsmu.web.util.page.NaviDisplay;
import com.sitech.basd.sxcloud.util.EncryptUtil;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.util.session.UserSession;
import com.sitech.utils.servlet.PrintWriterOut;

public class LoginAction extends CRUDBaseAction {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -7910705672384757568L;
	private static final Logger log = Logger.getLogger(LoginAction.class);
	private LoginForm theForm;
	private NaviDisplay naviDisplay;
	private LoginService LoginService;
	private FunctionsService functionsService;
	private TbCloud2SecurityConfigService securityConfigService;
	@Autowired
	private NewUIFuncService newUIFuncService;
	
	private String casurl;

	public String getCasurl() {
		return casurl;
	}

	public void setCasurl(String casurl) {
		this.casurl = casurl;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	public void setLoginService(LoginService loginService) {
		LoginService = loginService;
	}

	public TbCloud2SecurityConfigService getSecurityConfigService() {
		return securityConfigService;
	}

	public void setSecurityConfigService(TbCloud2SecurityConfigService securityConfigService) {
		this.securityConfigService = securityConfigService;
	}

	public static int getTypeLevel(String cModuleID) {
		int pos = cModuleID.indexOf("00");
		if (pos == -1)
			return 5;
		else
			return (pos + 1) / 2;
	}

	public LoginForm getTheForm() {
		return theForm;
	}

	public void setTheForm(LoginForm theForm) {
		this.theForm = theForm;
	}

	public void setNaviDisplay(NaviDisplay naviDisplay) {
		this.naviDisplay = naviDisplay;
	}

	/**
	 * @Title:登录请求处理
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String login() throws BaseException {
		if (theForm == null) {
			theForm = new LoginForm();
		}
		/**
		 * 防止后续有问题，加一个临时转换
		 */
		String password = theForm.getPASSWORD();
		if (password != null && !"".equals(password)) {
			theForm.setPASSWORD(EncryptUtil.decode(password));
		}
		Assertion assertion = AssertionHolder.getAssertion();
		AttributePrincipal principal = null;
		if (assertion != null) {
			principal = assertion.getPrincipal();
		}
		// 获取CAS服务端登录用户
		if (principal != null && principal.getName() != null && !"".equals(principal.getName())) {
			theForm = new LoginForm();
			// 查询系统用户信息
			TbSysUserinfoObj obj = new TbSysUserinfoObj();
			obj.setACCOUNT(principal.getName());
			TbSysUserinfoObj tempObj = LoginService.queryByObj(obj);
			theForm.setPASSWORD(EncryptUtil.decode(tempObj.getPASSWORD()));
			theForm.setACCOUNT(tempObj.getACCOUNT());
		}

		HttpServletRequest request = Struts2Utils.getRequest();
		String result = "1";
		if (null == theForm.getACCOUNT() || "".equals(theForm.getACCOUNT())) {
			request.setAttribute("msg", "请输入账号");
			LogHelper.info("请输入账号!" + getClass().getName());
			return INPUT;
		} else if (null == theForm.getPASSWORD() || "".equals(theForm.getPASSWORD())) {
			request.setAttribute("msg", "请输入密码");
			LogHelper.info("请输入密码!" + getClass().getName());
			return INPUT;
		}
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		obj.setACCOUNT(theForm.getACCOUNT().trim());
		TbSysUserinfoObj tempObj = LoginService.queryByObj(obj);
		if (null == tempObj) {
			request.setAttribute("msg", " 账号或密码不正确");
			LogHelper.info(theForm.getACCOUNT() + " 账号不存在" + getClass().getName());
			return INPUT;
		}
		if (tempObj.getSTATUS() == 0) {
			request.setAttribute("msg", "账号冻结!");
			LogHelper.info("账号冻结! 账号:" + tempObj.getACCOUNT() + getClass().getName());
			return INPUT;
		} else if (tempObj.getSTATUS() == 2) {
			request.setAttribute("msg", "账号被锁定");
			LogHelper.info("账号被锁定! 账号:" + tempObj.getACCOUNT() + getClass().getName());
			return INPUT;
		}
		/*
		 * IP 鉴权
		 */

		// String ipDetection =
		// securityConfigService.queryForObjByObj(Constant.GROUBLE_KEY_IP); //
		// 是否需要IP鉴权
		// if (null != ipDetection && "1".equals(ipDetection)) {
		// String ipAdd = request.getRemoteAddr();
		// TbCloud2SecurityConfigObj ipObj = new TbCloud2SecurityConfigObj();
		// ipObj.setType(Constant.SECURITY_TYPE_IP); // 查询IP鉴权类型的且生效的配置
		// List<TbCloud2SecurityConfigObj> ipList =
		// securityConfigService.queryForListByObj(ipObj);
		// boolean ipFlag = false;
		// if (null != ipList && !ipList.isEmpty()) {
		// ipObj = ipList.get(0);
		// String[] ipArr = ipObj.getValue().split(",");
		// if (null != ipArr && ipArr.length > 0) {
		// for (int i = 0; i < ipArr.length; i++) {
		// String ip = ipArr[i];
		// if (-1 != ip.indexOf("*")) {
		// ip.replace("*", "(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-4])");
		// if (ipAdd.matches(ip)) {
		// ipFlag = true;
		// break;
		// }
		// } else if (-1 != ip.indexOf("-")) {
		// int start = Integer.parseInt(ip.substring(ip.indexOf(".", 3) + 1,
		// ip.indexOf("-")));
		// int end = Integer.parseInt(ip.substring(ip.lastIndexOf(".") + 1));
		// int cmpr = Integer.parseInt(ip.substring(ipAdd.indexOf(".", 3) + 1));
		// if (cmpr >= start && cmpr <= end) {
		// ipFlag = true;
		// break;
		// }
		// } else {
		// if (ipAdd.equals(ip)) {
		// ipFlag = true;
		// break;
		// }
		// }
		// }
		// }
		// } else {//
		// // 若查询不到IP鉴权而且生效配置数据
		// ipFlag = true;
		// }
		// if (!ipFlag) {
		// request.setAttribute("msg", "IP地址不在允许访问范围! IP:" + ipAdd);
		// LogHelper.info("IP地址不在允许访问范围! IP:" + ipAdd + getClass().getName());
		// return INPUT;
		// }
		// }

		/*
		 * 错误登录次数鉴权
		 */
		String numbers = securityConfigService.queryForObjByObj(Constant.GROUBLE_KEY_NUM);
		if (null != numbers) {
			TbCloud2SecurityConfigObj numObj = new TbCloud2SecurityConfigObj();
			numObj.setType(Constant.SECURITY_TYPE_NUM);
			List<TbCloud2SecurityConfigObj> numList = securityConfigService
					.queryForListByObj(numObj);
			if (null != numList && !numList.isEmpty()) {
				numObj = numList.get(0);
				String[] values = numObj.getValue().split(":");
				int total = Integer.parseInt(values[0]); // 总共次数
				int left = Integer.parseInt(values[1]); // 剩余次数

				DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
						DateFormat.MEDIUM);// 格式化输出
				TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");// 获取时区
				dateFormat.setTimeZone(timeZoneChina);// 设置系统时区

				Calendar sysDate = Calendar.getInstance(); // 系统当前的时间
				Calendar upd_date = Calendar.getInstance();// 可以操作的时间
				try {
					upd_date.setTime(dateFormat.parse(numObj.getUpd_date()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				System.out.println(sysDate.getTime());
				System.out.println(upd_date.getTime());
				int leftTime = sysDate.compareTo(upd_date);
				System.out.println(leftTime);
				if (sysDate.before(upd_date)) {
					request.setAttribute("msg", "登录错误次数超过规定次数" + total + " 次限制，请一个小时后再重新登录！");
					LogHelper.info("登录错误次数超过规定次数" + total + " 次限制，请一个小时后再重新登录！"
							+ getClass().getName());
					return INPUT;
				}
				if (left > 0) {
					if (tempObj.getPASSWORD() == null
							|| !tempObj.getPASSWORD().equals(
									EncryptUtil.encode(theForm.getPASSWORD().trim()))) {
						request.setAttribute("msg", "密码不正确! 还可以尝试:" + left + " 次");
						left--;
						LogHelper.info("密码不正确! 账号:" + tempObj.getACCOUNT() + getClass().getName());
						numObj.setValue("" + total + ":" + left);
						numObj.setUpd_date(null);
						securityConfigService.updateObjByObj(numObj);
						return INPUT;
					}
				} else if (left == 0) {
					sysDate.add(Calendar.HOUR_OF_DAY, +1);
					numObj.setUpd_date(dateFormat.format(sysDate.getTime()));
					numObj.setValue("" + total + ":" + total);
					securityConfigService.updateObjByObj(numObj);
					request.setAttribute("msg", "登录错误次数超过规定次数" + total + " 次限制，请一个小时后再重新登录！");
					LogHelper.info("登录错误次数超过规定次数" + total + " 次限制，请一个小时后再重新登录！"
							+ getClass().getName());
					return INPUT;
				}
				numObj.setValue("" + total + ":" + total);
				numObj.setUpd_date(null);
				securityConfigService.updateObjByObj(numObj);
			}
		} else {
			if (tempObj.getPASSWORD() == null
					|| !tempObj.getPASSWORD().equals(
							EncryptUtil.encode(theForm.getPASSWORD().trim()))) {
				request.setAttribute("msg", "密码不正确! 账号:" + tempObj.getACCOUNT());
				LogHelper.info("密码不正确! 账号:" + tempObj.getACCOUNT() + getClass().getName());
				return INPUT;
			}
		}

		if (tempObj.getPASSWORD() == null
				|| !tempObj.getPASSWORD().equals(EncryptUtil.encode(theForm.getPASSWORD().trim()))) {
			request.setAttribute("msg", "账号或密码不正确");
			LogHelper.info("密码不正确! 账号:" + tempObj.getACCOUNT() + getClass().getName());
			return INPUT;
		}
		TbSysLoginLogObj loginLogObj = new TbSysLoginLogObj();
		loginLogObj.setACCOUNT(tempObj.getACCOUNT());
		try {
			loginLogObj.setHOSTNAME(java.net.Inet4Address.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			LogHelper
					.info("IP:" + request.getRemoteAddr() + " 获取HOSTNAME失败" + getClass().getName());
		}
		loginLogObj.setIP(request.getRemoteAddr());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		loginLogObj.setLOGINTIME(format.format(new Date()));
		loginLogObj.setREMARK("");
		loginLogObj.setRESULT(result);
		loginLogObj.setUSERID(tempObj.getID());
		loginLogObj.setDomain(tempObj.getDomain());
		int loginId = LoginService.insertByObj(loginLogObj);
		FuncRoleObj funcRoleObj = new FuncRoleObj();
		funcRoleObj.setACCOUNT(tempObj.getACCOUNT());
		funcRoleObj.setFUNCID("__00000000");
		funcRoleObj.setUSERID(tempObj.getID());
		List lst = functionsService.queryTbSysFunctionsObjByFUNCID(funcRoleObj);
		request.setAttribute("FUNCTIONS_ONE", lst);
		List favoriteList = LoginService.findFavoriteList(tempObj.getID());
		request.setAttribute("favoriteList", favoriteList);

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				Cookie cookie = request.getCookies()[0];// 获取cookie
				cookie.setMaxAge(0);// 让cookie过期
			}
		}
		request.getSession(true);

		// 实例Session数据
		initSession(request, loginId, tempObj);
		/*
		 * 处理CAScookie
		 */
		//
		this.casurl = newUIFuncService.queryCasUrlByGlobalConfig();

		// 对用户权限进行判断
		String leader = LoginService.queryLeaderOrNotByUid(tempObj.getID());
		if (null != leader && "1".equals(leader)) {
			request.getSession().setAttribute(Constant.LEADER_SESSION_KEY, true);
			return SUCCESS;
		}
		return SUCCESS;
	}
	
	private void test(){
		
	}

	/**
	 * @Title:登录请求处理---单点登录用
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	/*
	 * public String login() throws BaseException { if (theForm == null) {
	 * theForm = new LoginForm(); } //** 防止后续有问题，加一个临时转换
	 *//*
		 * String password = theForm.getPASSWORD(); if (password != null &&
		 * !"".equals(password)) {
		 * theForm.setPASSWORD(EncryptUtil.decode(password)); } Assertion
		 * assertion = AssertionHolder.getAssertion(); AttributePrincipal
		 * principal = null; if (assertion != null) { principal =
		 * assertion.getPrincipal(); } // 获取CAS服务端登录用户
		 * 
		 * HttpServletRequest request = Struts2Utils.getRequest(); String result
		 * = "1"; if (null == theForm.getACCOUNT() ||
		 * "".equals(theForm.getACCOUNT())) { request.setAttribute("msg",
		 * "请输入账号"); LogHelper.info("请输入账号!" + getClass().getName()); return
		 * INPUT; } else if (null == theForm.getPASSWORD() ||
		 * "".equals(theForm.getPASSWORD())) { request.setAttribute("msg",
		 * "请输入密码"); LogHelper.info("请输入密码!" + getClass().getName()); return
		 * INPUT; } TbSysUserinfoObj obj = new TbSysUserinfoObj();
		 * 
		 * //单点登录保存的用户名 String userName = (String)
		 * request.getSession().getAttribute("_USER_NAME");
		 * obj.setACCOUNT(userName);
		 * obj.setACCOUNT("admin");//此处因为数据库没有同步，所以用户名写死了。
		 * 
		 * TbSysUserinfoObj tempObj = LoginService.queryByObj(obj);
		 * 
		 * if (null == tempObj) { request.setAttribute("msg", " 账号或密码不正确");
		 * LogHelper.info(theForm.getACCOUNT() + " 账号不存在" +
		 * getClass().getName()); return INPUT; } if (tempObj.getSTATUS() == 0)
		 * { request.setAttribute("msg", "账号冻结!"); LogHelper.info("账号冻结! 账号:" +
		 * tempObj.getACCOUNT() + getClass().getName()); return INPUT; } else if
		 * (tempObj.getSTATUS() == 2) { request.setAttribute("msg", "账号被锁定");
		 * LogHelper.info("账号被锁定! 账号:" + tempObj.getACCOUNT() +
		 * getClass().getName()); return INPUT; }
		 * 
		 * IP 鉴权
		 * 
		 * 
		 * String ipDetection =
		 * securityConfigService.queryForObjByObj(Constant.GROUBLE_KEY_IP); //
		 * 是否需要IP鉴权 if (null != ipDetection && "1".equals(ipDetection)) { String
		 * ipAdd = request.getRemoteAddr(); TbCloud2SecurityConfigObj ipObj =
		 * new TbCloud2SecurityConfigObj();
		 * ipObj.setType(Constant.SECURITY_TYPE_IP); // 查询IP鉴权类型的且生效的配置
		 * List<TbCloud2SecurityConfigObj> ipList =
		 * securityConfigService.queryForListByObj(ipObj); boolean ipFlag =
		 * false; if (null != ipList && !ipList.isEmpty()) { ipObj =
		 * ipList.get(0); String[] ipArr = ipObj.getValue().split(","); if (null
		 * != ipArr && ipArr.length > 0) { for (int i = 0; i < ipArr.length;
		 * i++) { String ip = ipArr[i]; if (-1 != ip.indexOf("*")) {
		 * ip.replace("*", "(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-4])"); if
		 * (ipAdd.matches(ip)) { ipFlag = true; break; } } else if (-1 !=
		 * ip.indexOf("-")) { int start =
		 * Integer.parseInt(ip.substring(ip.indexOf(".", 3) + 1,
		 * ip.indexOf("-"))); int end =
		 * Integer.parseInt(ip.substring(ip.lastIndexOf(".") + 1)); int cmpr =
		 * Integer.parseInt(ip.substring(ipAdd.indexOf(".", 3) + 1)); if (cmpr
		 * >= start && cmpr <= end) { ipFlag = true; break; } } else { if
		 * (ipAdd.equals(ip)) { ipFlag = true; break; } } } } } else {//
		 * 若查询不到IP鉴权而且生效配置数据 ipFlag = true; } if (!ipFlag) {
		 * request.setAttribute("msg", "IP地址不在允许访问范围! IP:" + ipAdd);
		 * LogHelper.info("IP地址不在允许访问范围! IP:" + ipAdd + getClass().getName());
		 * return INPUT; } } /* 错误登录次数鉴权
		 * 
		 * 
		 * String numbers =
		 * securityConfigService.queryForObjByObj(Constant.GROUBLE_KEY_NUM); if
		 * (null != numbers && "3".equals(numbers)) { TbCloud2SecurityConfigObj
		 * numObj = new TbCloud2SecurityConfigObj();
		 * numObj.setType(Constant.SECURITY_TYPE_NUM);
		 * List<TbCloud2SecurityConfigObj> numList = securityConfigService
		 * .queryForListByObj(numObj); if (null != numList &&
		 * !numList.isEmpty()) { numObj = numList.get(0); String[] values =
		 * numObj.getValue().split(":"); int total =
		 * Integer.parseInt(values[0]); // 总共次数 int left =
		 * Integer.parseInt(values[1]); // 剩余次数
		 * 
		 * DateFormat dateFormat =
		 * DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
		 * DateFormat.MEDIUM);// 格式化输出 TimeZone timeZoneChina =
		 * TimeZone.getTimeZone("Asia/Shanghai");// 获取时区
		 * dateFormat.setTimeZone(timeZoneChina);// 设置系统时区
		 * 
		 * Calendar sysDate = Calendar.getInstance(); // 系统当前的时间 Calendar
		 * upd_date = Calendar.getInstance();// 可以操作的时间 try {
		 * upd_date.setTime(dateFormat.parse(numObj.getUpd_date())); } catch
		 * (ParseException e) { e.printStackTrace(); } int leftTime =
		 * sysDate.compareTo(upd_date); if (sysDate.before(upd_date)) {
		 * request.setAttribute("msg", "登录错误次数超过规定次数" + total + "
		 * 次限制，请一个小时后再重新登录！"); LogHelper.info("登录错误次数超过规定次数" + total + "
		 * 次限制，请一个小时后再重新登录！" + getClass().getName()); return INPUT; } if (left >
		 * 0) { if (tempObj.getPASSWORD() == null ||
		 * !tempObj.getPASSWORD().equals(
		 * EncryptUtil.encode(theForm.getPASSWORD().trim()))) { left--;
		 * request.setAttribute("msg", "密码不正确! 还可以尝试:" + left + " 次");
		 * LogHelper.info("密码不正确! 账号:" + tempObj.getACCOUNT() +
		 * getClass().getName()); numObj.setValue("" + total + ":" + left);
		 * numObj.setUpd_date(null);
		 * securityConfigService.updateObjByObj(numObj); return INPUT; } } else
		 * if (left == 0) { sysDate.add(Calendar.HOUR_OF_DAY, +1);
		 * numObj.setUpd_date(dateFormat.format(sysDate.getTime()));
		 * numObj.setValue("" + total + ":" + total);
		 * securityConfigService.updateObjByObj(numObj);
		 * request.setAttribute("msg", "登录错误次数超过规定次数限制，请一个小时后再重新登录！");
		 * LogHelper.info("登录错误次数超过规定次数" + total + " 次限制，请一个小时后再重新登录！" +
		 * getClass().getName()); return INPUT; } numObj.setValue("" + total +
		 * ":" + total); numObj.setUpd_date(null);
		 * securityConfigService.updateObjByObj(numObj); } } else {
		 * 
		 * 
		 * 
		 * 错误登录次数鉴权
		 * 
		 * String numbers =
		 * securityConfigService.queryForObjByObj(Constant.GROUBLE_KEY_NUM); if
		 * (null != numbers) { TbCloud2SecurityConfigObj numObj = new
		 * TbCloud2SecurityConfigObj();
		 * numObj.setType(Constant.SECURITY_TYPE_NUM);
		 * List<TbCloud2SecurityConfigObj> numList = securityConfigService
		 * .queryForListByObj(numObj); if (null != numList &&
		 * !numList.isEmpty()) { numObj = numList.get(0); String[] values =
		 * numObj.getValue().split(":"); int total =
		 * Integer.parseInt(values[0]); // 总共次数 int left =
		 * Integer.parseInt(values[1]); // 剩余次数
		 * 
		 * DateFormat dateFormat =
		 * DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
		 * DateFormat.MEDIUM);// 格式化输出 TimeZone timeZoneChina =
		 * TimeZone.getTimeZone("Asia/Shanghai");// 获取时区
		 * dateFormat.setTimeZone(timeZoneChina);// 设置系统时区
		 * 
		 * Calendar sysDate = Calendar.getInstance(); // 系统当前的时间 Calendar
		 * upd_date = Calendar.getInstance();// 可以操作的时间 try {
		 * upd_date.setTime(dateFormat.parse(numObj.getUpd_date())); } catch
		 * (ParseException e) { e.printStackTrace(); }
		 * System.out.println(sysDate.getTime());
		 * System.out.println(upd_date.getTime()); int leftTime =
		 * sysDate.compareTo(upd_date); System.out.println(leftTime); if
		 * (sysDate.before(upd_date)) { request.setAttribute("msg",
		 * "登录错误次数超过规定次数" + total + " 次限制，请一个小时后再重新登录！");
		 * LogHelper.info("登录错误次数超过规定次数" + total + " 次限制，请一个小时后再重新登录！" +
		 * getClass().getName()); return INPUT; } if (left > 0) { if
		 * (tempObj.getPASSWORD() == null || !tempObj.getPASSWORD().equals(
		 * EncryptUtil.encode(theForm.getPASSWORD().trim()))) {
		 * request.setAttribute("msg", "密码不正确! 还可以尝试:" + left + " 次"); left--;
		 * LogHelper.info("密码不正确! 账号:" + tempObj.getACCOUNT() +
		 * getClass().getName()); numObj.setValue("" + total + ":" + left);
		 * numObj.setUpd_date(null);
		 * securityConfigService.updateObjByObj(numObj); return INPUT; } } else
		 * if (left == 0) { sysDate.add(Calendar.HOUR_OF_DAY, +1);
		 * numObj.setUpd_date(dateFormat.format(sysDate.getTime()));
		 * numObj.setValue("" + total + ":" + total);
		 * securityConfigService.updateObjByObj(numObj);
		 * request.setAttribute("msg", "登录错误次数超过规定次数" + total + "
		 * 次限制，请一个小时后再重新登录！"); LogHelper.info("登录错误次数超过规定次数" + total + "
		 * 次限制，请一个小时后再重新登录！" + getClass().getName()); return INPUT; }
		 * numObj.setValue("" + total + ":" + total); numObj.setUpd_date(null);
		 * securityConfigService.updateObjByObj(numObj); } } else { if
		 * (tempObj.getPASSWORD() == null || !tempObj.getPASSWORD().equals(
		 * EncryptUtil.encode(theForm.getPASSWORD().trim()))) {
		 * request.setAttribute("msg", "密码不正确! 账号:" + tempObj.getACCOUNT());
		 * LogHelper.info("密码不正确! 账号:" + tempObj.getACCOUNT() +
		 * getClass().getName()); return INPUT; } }
		 * 
		 * if (tempObj.getPASSWORD() == null ||
		 * !tempObj.getPASSWORD().equals(EncryptUtil
		 * .encode(theForm.getPASSWORD().trim()))) { request.setAttribute("msg",
		 * "账号或密码不正确"); LogHelper.info("密码不正确! 账号:" + tempObj.getACCOUNT() +
		 * getClass().getName()); return INPUT; } TbSysLoginLogObj loginLogObj =
		 * new TbSysLoginLogObj(); loginLogObj.setACCOUNT(tempObj.getACCOUNT());
		 * try {
		 * loginLogObj.setHOSTNAME(java.net.Inet4Address.getLocalHost().getHostName
		 * ()); } catch (UnknownHostException e) { LogHelper .info("IP:" +
		 * request.getRemoteAddr() + " 获取HOSTNAME失败" + getClass().getName()); }
		 * loginLogObj.setIP(request.getRemoteAddr()); SimpleDateFormat format =
		 * new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		 * loginLogObj.setLOGINTIME(format.format(new Date()));
		 * loginLogObj.setREMARK(""); loginLogObj.setRESULT(result);
		 * loginLogObj.setUSERID(tempObj.getID()); int loginId =
		 * LoginService.insertByObj(loginLogObj); FuncRoleObj funcRoleObj = new
		 * FuncRoleObj(); funcRoleObj.setACCOUNT(tempObj.getACCOUNT());
		 * funcRoleObj.setFUNCID("__00000000");
		 * funcRoleObj.setUSERID(tempObj.getID()); List lst =
		 * functionsService.queryTbSysFunctionsObjByFUNCID(funcRoleObj);
		 * request.setAttribute("FUNCTIONS_ONE", lst); List favoriteList =
		 * LoginService.findFavoriteList(tempObj.getID());
		 * request.setAttribute("favoriteList", favoriteList);
		 * 
		 * HttpSession session = request.getSession(false); if (session != null)
		 * { session.invalidate(); Cookie[] cookies = request.getCookies(); if
		 * (cookies != null) { Cookie cookie = request.getCookies()[0];//
		 * 获取cookie cookie.setMaxAge(0);// 让cookie过期 } }
		 * request.getSession(true); //
		 * request.getSession().setAttribute(Constant.USER_SESSION_KEY, //
		 * tempObj); //
		 * request.getSession().setAttribute(Constant.USER_SESSION_KEY, new //
		 * String
		 * []{tempObj.getID()+"",tempObj.getACCOUNT(),tempObj.getPASSWORD()});
		 * // 实例Session数据 initSession(request, loginId, tempObj); // 对用户权限进行判断
		 * String leader = LoginService.queryLeaderOrNotByUid(tempObj.getID());
		 * if (null != leader && "1".equals(leader)) {
		 * request.getSession().setAttribute(Constant.LEADER_SESSION_KEY, true);
		 * return SUCCESS; } return SUCCESS; }
		 */

	/**
	 * 
	 * @Title: initSession
	 * @Description: 实例当前Session
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-29 上午8:32:35
	 */
	private void initSession(HttpServletRequest request, int loginId, TbSysUserinfoObj tempObj) {
		HttpSession session = request.getSession();
		session.setAttribute(Constant.LOGID_SESSION_KEY, loginId);
		session.setAttribute("id", tempObj.getID());
		session.setAttribute("account", tempObj.getACCOUNT());
		session.setAttribute("name", tempObj.getNAME());
		session.setAttribute("datau", tempObj.getDATAAUTHORITY());
		session.setAttribute(Constant.REQUEST_HOST, request.getRemoteAddr());
		session.setAttribute(Constant.REQUEST_PORT, request.getRemotePort());
		session.setAttribute(Constant.LOCAL_HOST, request.getLocalAddr());
		session.setAttribute(Constant.LOCAL_PORT, request.getLocalPort());
		session.setAttribute(Constant.USER_DOMAIN, tempObj.getDomain());
		session.setAttribute(Constant.GROUP_ID, tempObj.getGroupId());
		UserSession.setHttpSession(session);
	}

	/**
	 * 
	 * @Title: loginCasPage
	 * @Description: CAS单点登录成功页面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Dec 17, 2012 3:58:07 PM
	 */
	public String loginCasPage() {
		Assertion assertion = AssertionHolder.getAssertion();
		if (assertion == null) {
			return FAILURE;
		}
		AttributePrincipal principal = assertion.getPrincipal();
		// 获取CAS服务端登录用户
		if (principal == null || principal.getName() == null || "".equals(principal.getName())) {
			return FAILURE;
		}
		if (theForm == null) {
			theForm = new LoginForm();
			theForm.setACCOUNT(principal.getName().trim());
			theForm.setPASSWORD(principal.getName());
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String result = "1";
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		obj.setACCOUNT(theForm.getACCOUNT().trim());
		TbSysUserinfoObj tempObj = LoginService.queryByObj(obj);
		TbSysLoginLogObj loginLogObj = new TbSysLoginLogObj();
		loginLogObj.setACCOUNT(tempObj.getACCOUNT());
		try {
			loginLogObj.setHOSTNAME(java.net.Inet4Address.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			LogHelper
					.info("IP:" + request.getRemoteAddr() + " 获取HOSTNAME失败" + getClass().getName());
		}
		loginLogObj.setIP(request.getRemoteAddr());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		loginLogObj.setLOGINTIME(format.format(new Date()));
		loginLogObj.setREMARK("");
		loginLogObj.setRESULT(result);
		loginLogObj.setUSERID(tempObj.getID());
		int loginId = LoginService.insertByObj(loginLogObj);
		FuncRoleObj funcRoleObj = new FuncRoleObj();
		funcRoleObj.setACCOUNT(tempObj.getACCOUNT());
		funcRoleObj.setFUNCID("__00000000");
		funcRoleObj.setUSERID(tempObj.getID());
		List lst = functionsService.queryTbSysFunctionsObjByFUNCID(funcRoleObj);
		request.setAttribute("FUNCTIONS_ONE", lst);
		List favoriteList = LoginService.findFavoriteList(tempObj.getID());
		request.setAttribute("favoriteList", favoriteList);

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			Cookie cookie = request.getCookies()[0];// 获取cookie
			cookie.setMaxAge(0);// 让cookie过期
		}
		request.getSession(true);

		// request.getSession().setAttribute(Constant.USER_SESSION_KEY,
		// tempObj);
		request.getSession().setAttribute(Constant.LOGID_SESSION_KEY, loginId);
		// 实例Session数据
		initSession(request, loginId, tempObj);

		// 对用户权限进行判断
		String leader = LoginService.queryLeaderOrNotByUid(tempObj.getID());
		if (null != leader && "1".equals(leader)) {
			request.getSession().setAttribute(Constant.LEADER_SESSION_KEY, true);
			return SUCCESS;
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: demo,演示实例
	 * @Description: 演示测试实例
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jun 20, 2012 10:09:03 AM
	 */
	public String gobackDemoindex() {
		// TbSysUserinfoObj accountObj = (TbSysUserinfoObj)
		// session.get(Constant.USER_SESSION_KEY);
		String[] accountObj = new String[] { session.get("id").toString(),
				session.get("account").toString(), session.get("name").toString() };
		FuncRoleObj funcRoleObj = new FuncRoleObj();
		funcRoleObj.setACCOUNT(accountObj[1]);
		funcRoleObj.setFUNCID("__00000000");
		funcRoleObj.setUSERID(Integer.parseInt(accountObj[0]));
		List lst = functionsService.queryTbSysFunctionsObjByFUNCID(funcRoleObj);
		request.setAttribute("FUNCTIONS_ONE", lst);
		List favoriteList = LoginService.findFavoriteList(Integer.parseInt(accountObj[0]));
		request.setAttribute("favoriteList", favoriteList);
		return "main";
	}

	/**
	 * 
	 * @Title: gotoLeaderPage
	 * @Description: 去领导视图页面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jun 20, 2012 10:09:03 AM
	 */
	public String gotoLeaderPage() throws BaseException {
		Struts2Utils.getRequest().getSession().setAttribute(Constant.LEADER_SESSION_KEY, true);
		return LEADER;
	}

	/**
	 * 
	 * @Title: 从Flex页面进入的Web主页面
	 * @Copyright: Copyright (c) 2012-2-19
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String gotoMainPage() throws BaseException {
		// HttpSession session = Struts2Utils.getSession();
		HttpServletRequest request = Struts2Utils.getRequest();
		// TbSysUserinfoObj accountObj = (TbSysUserinfoObj)
		// session.getAttribute(Constant.USER_SESSION_KEY);
		FuncRoleObj funcRoleObj = new FuncRoleObj();
		funcRoleObj.setACCOUNT(session.get("account").toString());
		funcRoleObj.setFUNCID("__00000000");
		funcRoleObj.setUSERID((Integer) session.get("id"));
		List lst = functionsService.queryTbSysFunctionsObjByFUNCID(funcRoleObj);
		request.setAttribute("FUNCTIONS_ONE", lst);
		List favoriteList = LoginService.findFavoriteList((Integer) session.get("id"));
		request.setAttribute("favoriteList", favoriteList);
		return SUCCESS;
	}

	/**
	 * @Title:退出系统处理请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String loginout() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 判断Session是否存在
		try {
			int resNum = Integer.parseInt("" + session.get(Constant.LOGID_SESSION_KEY));
			TbSysLoginLogObj obj = new TbSysLoginLogObj();
			obj.setID(resNum);
			int ret = LoginService.updateByObj(obj);
			request.getSession().invalidate();// 销毁session
			// request.getSession().removeAttribute("id");
			// request.getSession().removeAttribute("account");
			// request.getSession().removeAttribute("name");
		} catch (Exception e) {
			return "loginout";
		}
		return "loginout";
	}

	/**
	 * @Title:获取功能路径
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String getTitle() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String funcId = request.getParameter("FUNCID");
		// 特殊处理
		if ("1101000000".equals(funcId)) {
			// //报表分析
			funcId = "1101010000";
		} else if ("2201000000".equals(funcId)) {
			// 综合信息特殊处理
			funcId = "2201010000";
		} else if ("3301000000".equals(funcId)) {
			// 业务管理特殊处理转业务能力
			funcId = "3301010000";
		}
		String naviURL = "";
		if (funcId != null) {
			if (!funcId.equals("null"))
				try {
					naviURL = naviDisplay.getNaviURLA(funcId, false, "main");
				} catch (Exception e) {
					LogHelper.error(e.getMessage() + getClass().getName());
				}
		}
		response.setContentType("text/html; charset=gb2312");
		// out = response.getWriter();
		// out.print("当前位置:" + naviURL);
		// out.flush();
		PrintWriterOut.printWirter(response, "当前位置:" + naviURL);
		return null;

	}

	/**
	 * @Title:左边菜单处理请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String leftTree() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String funcId = request.getParameter("FUNCID");
		// 当前画面为top 时为HOMEPAGE
		String topFlag = request.getParameter("topFlag");
		// 类似导航等，需要对应的
		// 有三级菜单的时候传递过来的时候
		String tempFuncId = funcId;
		String subStr = funcId.substring(0, 2);
		FuncRoleObj funcRoleObj = new FuncRoleObj();
		// TbSysUserinfoObj tempObj = (TbSysUserinfoObj)
		// session.get(Constant.USER_SESSION_KEY);
		funcRoleObj.setACCOUNT(session.get("account").toString());
		funcRoleObj.setFUNCID(subStr);
		funcRoleObj.setUSERID(Integer.parseInt(session.get("id").toString()));
		List listTree = functionsService.queryTbSysFunctionsObjByFUNCID(funcRoleObj);
		StringBuffer sb = new StringBuffer();
		// 首页转为其他页面时
		if ("top".equals(topFlag)) {
			sb.append("<div class=\"wrap wrap-bg2\">");
			sb.append("<div class=\"sidebar\">");
		}
		// 左边树
		sb.append("<dl class=\"UI_accordion\">");
		if (listTree.size() > 0) {
			for (int i = 1; i < listTree.size(); i++) {
				TbSysFunctionsObj obj = (TbSysFunctionsObj) listTree.get(i);
				String isRefreh = "" + obj.getISREFRESH();
				String tempId = (String) obj.getFUNCID();
				int id = obj.getID();
				String tempName = (String) obj.getFUNNAME();
				String tempPath = "";
				if ((String) obj.getFUNCREQUEST() != null) {
					String single = String.valueOf(obj.getFUNCREQUEST()).contains("?") ? "&" : "?";
					tempPath = String.valueOf(obj.getFUNCREQUEST())
					/** 浙江竞标暂去除 */
					+ single + "FUNCSID=" + id;
				}
				int level = getTypeLevel(tempId);
				if (level == 2) {
					if (i != 1) {
						sb.append(" 		</ul>");
						sb.append(" 	</div>");
						sb.append("</dd>");
					}
					sb.append("<dt>" + tempName + "</dt>");
					if (tempId.substring(0, 4).equals(tempFuncId.substring(0, 4))) {
						// 默认第一个TAB选中
						sb.append("<dd class=\"active\">");
					} else {
						sb.append("<dd >");
					}
					sb.append("	<div class=\"tree\">");
					sb.append(" 		<ul class=\"root\">");
				} else if (level == 3) {
					// 三级循环
					sb.append(" 			<li class=\"node\">");
					sb.append(" 				<a href=\"javascript:void(0)\" mid=\"" + id + "\" turl=\""
							+ tempPath + "\" refresh=\"" + isRefreh
							+ "\" ajx=\"login_getTitle.do?FUNCID=" + tempId + "\"");
					sb.append(" 				<span>" + tempName + "</span>");
					sb.append(" 			</a></li>");
				}
				// 循环末尾加上
				if (i == listTree.size() - 1) {
					sb.append(" 		</ul>");
					sb.append(" 	</div>");
					sb.append("</dd>");
				}
			}
		}
		sb.append("</dl>");
		if ("top".equals(topFlag)) {
			// sidebar 和 border的尾巴
			sb.append("</div>");
			// 当前位置
			sb.append("<div class=\"frame-ct\">");
			sb.append("	<div class=\"whereme\"></div>");
			sb.append(" <ul class=\"tabtil tab-style02\">");
			sb.append("</ul>");
			sb.append("<div class=\"tabct\">");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</div>");
		}
		response.setContentType("text/html; charset=gb2312");
		// out = response.getWriter();
		// out.print(sb.toString());
		// out.flush();
		PrintWriterOut.printWirter(response, sb.toString());
		return null;

	}

	/**
	 * @Title:设置收藏菜单
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String userShortcutConfig() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String funcsId = request.getParameter("FUNCSID");
		// TbSysUserinfoObj userinfoObj = (TbSysUserinfoObj)
		// session.get(Constant.USER_SESSION_KEY);
		int userId = (Integer) session.get("id");
		TbSysUserShortcutObj obj = new TbSysUserShortcutObj();
		obj.setFUNCID(Integer.parseInt(funcsId));
		obj.setUSERID(userId);
		int ret = LoginService.insertByObj(obj);
		return null;
	}

	/**
	 * @Title:删除收藏菜单
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String delShortcutConfig() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		// TbSysUserinfoObj userinfoObj = (TbSysUserinfoObj)
		// session.get(Constant.USER_SESSION_KEY);
		String funcId = request.getParameter("FUNCID");
		TempSysUserShortctObj obj = new TempSysUserShortctObj();
		obj.setFUNCID(Integer.parseInt(funcId));
		obj.setUSERID((Integer) session.get("id"));
		LoginService.deleteForTbSysUserShortcut(obj);
		return null;
	}
	
}
