package com.sitech.basd.sxcloud.rsmu.web.util.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.helpers.LogLog;

public class SetCharacterEncodingFilter extends ServletFilter {

	public SetCharacterEncodingFilter() {
	}

	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		try {
			servletrequest.setCharacterEncoding("gb2312");
		} catch (Exception exception) {
			LogLog
					.error(
							"SetCharacterEncodingFilter.java Method doFilter Exception:",
							exception);
		}
		filterchain.doFilter(servletrequest, servletresponse);
	}
}