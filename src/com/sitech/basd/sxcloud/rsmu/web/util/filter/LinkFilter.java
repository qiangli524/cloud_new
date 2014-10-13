package com.sitech.basd.sxcloud.rsmu.web.util.filter;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysLoginLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.LoginService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.SpringUtil;

/**
 * @Title:访问链接拦截
 * @Copyright: Copyright (c) 201006
 * @Company: si-tech
 * @author duangh
 * @version 1.0
 */
public class LinkFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;

	public LoginService getLoginService() {
		return (LoginService) SpringUtil.getBean("loginService");
	}

	public LinkFilter() {
	}

	/**
	 * Filter初始化
	 */
	public void init(FilterConfig config) throws ServletException {
	}

	/**
	 * 拦截器核心
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String account = (String) req.getSession().getAttribute("account");
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
		obj.setACCOUNT(account);
		//查询用户信息
		TbSysUserinfoObj tempObj = this.getLoginService().queryByObj(obj);
		if (tempObj == null) {
			return;
		}
		String userid = String.valueOf(tempObj.getID());
		//插入登录日志
		TbSysLoginLogObj loginLogObj = new TbSysLoginLogObj();
		loginLogObj.setACCOUNT(tempObj.getACCOUNT());
		try {
			loginLogObj.setHOSTNAME(java.net.Inet4Address.getLocalHost()
					.getHostName());
		} catch (UnknownHostException e) {
			LogHelper.info("IP:" + req.getRemoteAddr() + " 获取HOSTNAME失败"
					+ getClass().getName());
		}
		loginLogObj.setIP(req.getRemoteAddr());
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS");
		loginLogObj.setLOGINTIME(format.format(new Date()));
		loginLogObj.setREMARK("");
		String result = "1";
		loginLogObj.setRESULT(result);
		loginLogObj.setUSERID(tempObj.getID());
		int loginId = this.getLoginService().insertByObj(loginLogObj);

		req.getSession().setAttribute(Constant.USER_SESSION_ID, userid);
//		req.getSession().setAttribute(Constant.USER_SESSION_KEY, tempObj);
//		req.getSession().setAttribute("id", tempObj.getID());
//		req.getSession().setAttribute("name", tempObj.getNAME());
//		req.getSession().setAttribute("account", tempObj.getACCOUNT());
//		req.getSession().setAttribute("datau", tempObj.getDATAAUTHORITY());
		req.getSession().setAttribute(Constant.LOGID_SESSION_KEY, loginId);
		//		res
		//				.sendRedirect("tree_listVMService.do?FUNCID=1201000000&FUNCSID=1056");
	}

	/**
	 * 
	 * @Title: destory
	 * @Description: Filter结束销毁资源
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Aug 22, 2011 1:53:54 PM
	 */
	public void destory() {

	}
}