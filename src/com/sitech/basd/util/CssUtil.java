package com.sitech.basd.util;

import javax.servlet.http.HttpServletRequest;

public class CssUtil {
	public static String getCssPath(HttpServletRequest request, String path) {
		return request.getContextPath() + "/cresources/default/css/" + path;
	}
	
	public static String getCssTag(HttpServletRequest request, String path) {
		return "<link href=\"" + getCssPath(request, path) + "\" rel=\"stylesheet\" type=\"text/css\" />";
	}
}
