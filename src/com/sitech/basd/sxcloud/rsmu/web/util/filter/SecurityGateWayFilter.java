package com.sitech.basd.sxcloud.rsmu.web.util.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitech.basd.sxcloud.config.Constant;

/**
 * @Title:SESSION过期处理
 * @Copyright: Copyright (c) 201006
 * @Company: si-tech
 * @author zengls
 * @version 1.0
 */
public class SecurityGateWayFilter extends ServletFilter {
	// 发现访问非法后的转向页面
	private String failPage = "/index.jsp";
	// 默认只保护 jsp action不被随意访问
	private String[] protectedRes = { ".*\\.jsp", ".*\\.do",".*\\.action" };
	private String[] except = { ".*loginout\\.jsp.*", ".*index\\.jsp.*", ".*login\\.do", ".*loginout\\.do",
			".*ikm\\.do", ".*openIkm\\.jsp.*", ".*loginCasPage\\.do", ".*\\.rest", ".*index\\.do",
			".*heartbeatServer\\.do",".*login4A_userGroupEdit\\.do" };

	public SecurityGateWayFilter() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException,
			ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		res.addHeader("P3P","CP=CAO PSA OUR");
		// 判断是否是受保护的资源,如果不是，直接跳出过滤器
		if (!isProtected(req)) {
			filterchain.doFilter(request, response);
			return;
		}
		// TbSysUserinfoObj obj = (TbSysUserinfoObj) req.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		String account = (String) req.getSession().getAttribute("account");
		/**
		 * 如果是4A的用户,则认为是登录的
		 */
		if(Constant.TEMP4AUSER.equals(account)){
			filterchain.doFilter(request, response);
			return;
		}
		if (account == null) {
			request.getRequestDispatcher(failPage).forward(request, response);
			return;
		}
		filterchain.doFilter(request, response);
	}

	/**
	 * 判断被访问的资源是否需要保护，例如图片之类的可以暂时不管 只保护jsp和action不被随意访问
	 */
	private boolean isProtected(HttpServletRequest request) {
		String URI = request.getRequestURI();
		String URL = URI + "?" + request.getQueryString();
		String contextPath = request.getContextPath() + "/";
		// 根目录
		if (contextPath.indexOf(URI) >= 0) {
			return false;
		}
		// 判断是不是例外
		for (int index = 0; index < except.length; index++) {
			if (Pattern.matches(except[index], URI) || Pattern.matches(except[index], URL)) {
				return false;
			}
		}
		/*
		 * 判断是不是受保护的资源不是所有的资源都保护,像图片资源之类的没必要保护起来
		 */
		for (int index = 0; index < protectedRes.length; index++) {
			if (Pattern.matches(protectedRes[index], URI) || Pattern.matches(protectedRes[index], URL)) {
				return true;
			}
		}
		return false;

	}
}