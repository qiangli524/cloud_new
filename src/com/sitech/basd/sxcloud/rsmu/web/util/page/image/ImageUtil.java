package com.sitech.basd.sxcloud.rsmu.web.util.page.image;

import javax.servlet.http.HttpServletRequest;

public class ImageUtil {
	public static String getImagePath(HttpServletRequest request, String path) {
		return request.getContextPath() + "/sxcloud/cresources/default/images/"
				+ path;
	}

	public static String getImageTag(HttpServletRequest request, String path) {
		return "<img src=\"" + getImagePath(request, path) + "\" />";
	}
}
