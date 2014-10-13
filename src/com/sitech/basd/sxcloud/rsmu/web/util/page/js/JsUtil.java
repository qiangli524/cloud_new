package com.sitech.basd.sxcloud.rsmu.web.util.page.js;

import javax.servlet.http.HttpServletRequest;

public class JsUtil {
	public static String getJsPath(HttpServletRequest request, String path) {
		return request.getContextPath() + "/sxcloud/cjs/" + path;
	}

	public static String getJsTag(HttpServletRequest request, String path) {
		return "<script type=\"text/javascript\" src=\""
				+ getJsPath(request, path) + "\"></script>";
	}
}
