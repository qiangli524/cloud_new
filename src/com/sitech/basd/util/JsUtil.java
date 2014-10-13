package com.sitech.basd.util;

import javax.servlet.http.HttpServletRequest;

public class JsUtil {
	public static String getJsPath(HttpServletRequest request, String path) {
		return request.getContextPath() + "/cjs/" + path;
	}
	
	public static String getJsTag(HttpServletRequest request, String path) {
		return "<script type=\"text/javascript\" src=\"" + getJsPath(request, path) + "\"></script>";
	}
}
