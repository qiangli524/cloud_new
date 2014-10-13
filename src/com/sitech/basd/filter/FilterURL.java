package com.sitech.basd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Title: FilterURL
 * </p>
 * <p>
 * Description: 禁止用户通过url直接访问
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-9-26 下午5:16:57
 * 
 */
public class FilterURL implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
			ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//判断登录信息
		Object account = req.getSession().getAttribute("account");
		if (account == null) {//未登录，跳转到登录页
			resp.sendRedirect("login_login.do");
			return ;
		}
		
		String conString = "";
		conString = req.getHeader("REFERER");// 获取父url--如果不是直接输入的话就是先前的访问过来的页面，要是用户输入了，这个父url是不存在的
		if ("".equals(conString) || null == conString) { // 判断如果上一个目录为空的话，说明是用户直接输入url访问的
			String servletPath = req.getServletPath();// 当前请求url，去掉几个可以直接访问的页面
			if (servletPath.contains("index.jsp") || servletPath.contains("login.jsp")
					|| servletPath.contains("heartbeat_heartbeatServer.do")) { // 跳过index.jsp和登陆Login.jsp
				filterChain.doFilter(request, response);
			} else {
				String path = req.getContextPath();
				resp.sendRedirect(path);// 跳回首页
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
