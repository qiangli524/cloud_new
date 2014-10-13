/*
 * @(#)SecurityFileter.java	1.0 Created on 2005-6-8 17:17:47
 *
 * Copyright (c) 2005  SI-TECH Information Technology Ltd. All rights reserved.
 */
package com.test.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * 用来判断session是否有效（已实现，这样在程序中可以直接调用session，而不用考虑session是否有效），
 * 还可在此判断用户是否具有操作某一功能的权限（未实现，需要重新设计权限系统)
 * 
 */
public class SecurityFilter extends HttpServlet implements Filter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(SecurityFilter.class);

	private final static String URL_LOGIN = "/index.jsp";

	private FilterConfig filterConfig;

	private boolean enabled = true;

	private String loginUrl;

	private String timeOutUrl;

	// Handle the passed-in FilterConfig
	public void init(FilterConfig filterConfig) {
		this.setFilterConfig(filterConfig);
		String value = filterConfig.getInitParameter("enabled");

		loginUrl = filterConfig.getInitParameter("loginUrl");
		timeOutUrl = filterConfig.getInitParameter("timeOutUrl");
		if (loginUrl == null)
			loginUrl = URL_LOGIN;
		if (timeOutUrl == null)
			timeOutUrl = URL_LOGIN;

		if (logger.isDebugEnabled()) {
			logger.debug("enabled=" + value + ",loginUrl=" + loginUrl
					+ ",timeOutUrl=" + timeOutUrl);
		}

		if ("true".equalsIgnoreCase(value)) {
			this.enabled = true;
		} else if ("false".equalsIgnoreCase(value)) {
			this.enabled = false;
		} else {
			this.enabled = true;
			if (logger.isDebugEnabled()) {
				logger.debug("The SecurityFilter is enabled by default. ");
			}
		}
	}

	// Process the request/response pair
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//String remoteUser = (String) httpRequest.getSession().getAttribute("edu.yale.its.tp.cas.client.filter.user");
		//判断SESSION
		HttpSession session = httpRequest.getSession();
		/**判断SESSION用户名是否存在，不存在则去CAS服务端返回中找*/
		/*一下if else语句的逻辑：
		 * 
		 * 
		 * */
		if(session.getAttribute("_USER_NAME")==null){
			System.out.println("-------无SESSION---------");
			//获取CAS服务端返回属性，此处返回用户名
			
			String userName = AssertionHolder.getAssertion().getPrincipal().getName();//方式一
			
			AttributePrincipal principal = (AttributePrincipal) httpRequest.getUserPrincipal();//方式二
			//暂未在服务端设置属性
			Map<String, Object> map = principal.getAttributes();
			System.out.println("-------map.size = -------"+map.size());
			 for (String key : map.keySet()) {
				   System.out.println("key= "+ key + " and value= " + map.get(key));
		       }
			
			//String userName = principal.getName();
			if(userName != null){
				session.setAttribute("_USER_NAME",userName);
				/*此处可以加上通过用户名从数据库去有用信息的代码，并放到SESSION里备用*/
			}
		}else{
			System.out.println("-------有SESSION---------");
		}
		filterChain.doFilter(request, response);
	}

	// Clean up resources
	public void destroy() {
	}

	private boolean isLoginAction(HttpServletRequest request) {
		String path = request.getServletPath();
		if (path.equals(URL_LOGIN))
			return true;
		else if (path.equals(timeOutUrl))
			return true;
		else if (loginUrl.indexOf(path) >= 0)
			return true;
		else
			return false;
	}

	private boolean hasLogin(HttpServletRequest request) {
		String userName = (String) request.getSession().getAttribute("_USER_NAME");
		//logger.info("sessionVO"+sessionVO);
		if (userName != null) {
			return true;
		} else {
			return false;
		}

	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
}
