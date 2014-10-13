package com.sitech.basd.sxcloud.rsmu.web.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class ServletFilter implements Filter {

	protected FilterConfig filterConfig;

	public ServletFilter() {
	}

	public void init(FilterConfig filterconfig) throws ServletException {
		filterConfig = filterconfig;
	}

	public abstract void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException;

	public void destroy() {
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterconfig) {
		filterConfig = filterconfig;
	}
}
