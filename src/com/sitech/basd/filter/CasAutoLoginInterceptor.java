package com.sitech.basd.filter;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysLoginLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.LoginService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.util.session.UserSession;

/**
 * 
 * <p>
 * Title: CasAutoLoginInterceptor
 * </p>
 * <p>
 * Description: cas单点自动登录生成Session
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-7-23 下午4:03:41
 * 
 */
@Service("casAutoLoginInterceptor")
public class CasAutoLoginInterceptor extends AbstractInterceptor {

	@Autowired
	private LoginService LoginService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation aInvocation) throws Exception {
		// logger.info("CAS拦截器开始登录");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession loginSession = request.getSession();
		Object account = loginSession.getAttribute("account");
		if (account == null) {
			Assertion assertion = AssertionHolder.getAssertion();
			AttributePrincipal principal = null;
			String username = null;
			if (assertion != null) {
				principal = assertion.getPrincipal();
			}

			if (principal != null && principal.getName() != null && !"".equals(principal.getName())) {
				username = principal.getName();
			}

			if (username != null) {
				TbSysUserinfoObj obj = new TbSysUserinfoObj();
				obj.setACCOUNT(username);
				TbSysUserinfoObj tempObj = LoginService.queryByObj(obj);
				TbSysLoginLogObj loginLogObj = new TbSysLoginLogObj();
				loginLogObj.setACCOUNT(tempObj.getACCOUNT());
				try {
					loginLogObj.setHOSTNAME(java.net.Inet4Address.getLocalHost().getHostName());
				} catch (UnknownHostException e) {
					LogHelper.info("IP:" + request.getRemoteAddr() + " 获取HOSTNAME失败"
							+ getClass().getName());
				}
				loginLogObj.setIP(request.getRemoteAddr());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				loginLogObj.setLOGINTIME(format.format(new Date()));
				loginLogObj.setREMARK("");
				loginLogObj.setRESULT("1");
				loginLogObj.setUSERID(tempObj.getID());
				int loginId = LoginService.insertByObj(loginLogObj);

				HttpSession session = request.getSession(false);
				if (session != null) {
					session.invalidate();
					Cookie[] cookies = request.getCookies();
					if (cookies != null) {
						Cookie cookie = request.getCookies()[0];// 获取cookie
						cookie.setMaxAge(0);// 让cookie过期
					}
				}
				// 实例Session数据
				initSession(request, loginId, tempObj);
			} else {
				logger.error("获取CAS_SSO登录用户名失败！");
			}
		} else {
			logger.info("用户" + account + "@" + request.getRemoteAddr() + "已经登录！");
		}

		// logger.info("CAS拦截器开始结束");
		return aInvocation.invoke();
	}

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
		UserSession.setHttpSession(session);
	}

}
