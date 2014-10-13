package com.sitech.cas.filter;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysLoginLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.FunctionsService;
import com.sitech.basd.sxcloud.rsmu.service.system.LoginService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class AutoSetUserAdapterFilter implements Filter {
	private String failPage = "index.jsp";
	private String successPage = "dispatcher.jsp";
	private WebApplicationContext wac;
	private LoginService loginService;
	private FunctionsService functionsService;

	public LoginService getLoginService() {
		return (LoginService) wac.getBean("loginService");
	}

	public FunctionsService getFunctionsService() {
		return (FunctionsService) wac.getBean("functionsService");
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// 获取CAS服务端返回属性
		// AttributePrincipal principal = (AttributePrincipal) httpRequest
		// .getUserPrincipal();
		// 暂未在服务端设置属性
		// Map<String, Object> map = principal.getAttributes();
		// 获取CAS服务端登录用户
		// Assertion assertion = AssertionHolder.getAssertion();
		// if (assertion == null) {
		// // request.getRequestDispatcher(failPage).forward(request,
		// // response);
		// chain.doFilter(request, response);
		// return;
		// }
		// AttributePrincipal principal = assertion.getPrincipal();
		// 获取CAS服务端登录用户
		// String remoteUser = httpRequest.getRemoteUser();
		// 获取当前Session，看是否有登录用户
		// if (principal == null || principal.getName() == null
		// || "".equals(principal.getName())) {
		// // request.getRequestDispatcher(failPage).forward(request,
		// // response);
		// chain.doFilter(request, response);
		// return;
		// }
		String remoteUser = (String) httpRequest.getSession().getAttribute(
				"edu.yale.its.tp.cas.client.filter.user");
		// 用户信息处理
		String result = "1";
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		obj.setACCOUNT(remoteUser);
		TbSysUserinfoObj tempObj = getLoginService().queryByObj(obj);
		if (tempObj == null) {
			return;
		}
		String userid = String.valueOf(tempObj.getID());
		TbSysLoginLogObj loginLogObj = new TbSysLoginLogObj();
		loginLogObj.setACCOUNT(tempObj.getACCOUNT());
		// try {
		// loginLogObj.setHOSTNAME(java.net.Inet4Address.getLocalHost()
		// .getHostName());
		// } catch (UnknownHostException e) {
		// LogHelper.info("IP:" + request.getRemoteAddr() + " 获取HOSTNAME失败"
		// + getClass().getName());
		// }
		// loginLogObj.setIP(request.getRemoteAddr());
		// SimpleDateFormat format = new SimpleDateFormat(
		// "yyyy-MM-dd hh:mm:ss.SSS");
		// loginLogObj.setLOGINTIME(format.format(new Date()));
		// loginLogObj.setREMARK(remoteUser + "登录系统！");
		// loginLogObj.setRESULT(result);
		// loginLogObj.setUSERID(tempObj.getID());
		// int loginId = getLoginService().insertByObj(loginLogObj);
		// FuncRoleObj funcRoleObj = new FuncRoleObj();
		// funcRoleObj.setACCOUNT(tempObj.getACCOUNT());
		// funcRoleObj.setFUNCID("__00000000");
		// funcRoleObj.setUSERID(tempObj.getID());
		// List lst = getFunctionsService().queryTbSysFunctionsObjByFUNCID(
		// funcRoleObj);
		// request.setAttribute("FUNCTIONS_ONE", lst);
		// List favoriteList =
		// getLoginService().findFavoriteList(tempObj.getID());
		// request.setAttribute("favoriteList", favoriteList);
		//
		// HttpSession session = httpRequest.getSession(false);
		// System.out.println("sessionid before:"
		// + httpRequest.getSession().getId());
		// if (session != null) {
		// session.invalidate();
		// Cookie cookie = httpRequest.getCookies()[0];// 获取cookie
		// cookie.setMaxAge(0);// 让cookie过期
		// }
		// session = httpRequest.getSession(true);
		// System.out.println("sessionid after:" + session.getId());
		// session.setAttribute(Constant.USER_SESSION_KEY, tempObj);
		// session.setAttribute(Constant.LOGID_SESSION_KEY, loginId);
		// session.setAttribute("FUNCTIONS_ONE", lst);
		// session.setAttribute("favoriteList", favoriteList);
		// request.getRequestDispatcher(successPage).forward(request, response);

		try {
			loginLogObj.setHOSTNAME(java.net.Inet4Address.getLocalHost()
					.getHostName());
		} catch (UnknownHostException e) {
			LogHelper.info("IP:" + httpRequest.getRemoteAddr()
					+ " 获取HOSTNAME失败" + getClass().getName());
		}
		loginLogObj.setIP(httpRequest.getRemoteAddr());
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS");
		loginLogObj.setLOGINTIME(format.format(new Date()));
		loginLogObj.setREMARK("");
		loginLogObj.setRESULT(result);
		loginLogObj.setUSERID(tempObj.getID());
		int loginId = this.getLoginService().insertByObj(loginLogObj);

		httpRequest.getSession().setAttribute(Constant.USER_SESSION_ID, userid);
		httpRequest.getSession().setAttribute(Constant.USER_SESSION_KEY,
				tempObj);
		httpRequest.getSession().setAttribute(Constant.LOGID_SESSION_KEY,
				loginId);
		httpRequest.getSession().setAttribute("id", tempObj.getID());
		httpRequest.getSession().setAttribute("account", tempObj.getACCOUNT());
		httpRequest.getSession().setAttribute("name", tempObj.getNAME());
		httpRequest.getSession().setAttribute("datau", tempObj.getDATAAUTHORITY());
		chain.doFilter(request, response);
	}
}
