package com.sitech.basd.sxcloud.rsmu.web.util.page.css;

import javax.servlet.http.HttpServletRequest;

public class CssUtil {
	public static String getCssPath(HttpServletRequest request, String path) {
		return request.getContextPath() + "/sxcloud/cresources/default/css/"
				+ path;
	}

	public static String getCssTag(HttpServletRequest request, String path) {
		return "<link href=\"" + getCssPath(request, path)
				+ "\" rel=\"stylesheet\" type=\"text/css\" />";
	}
}
