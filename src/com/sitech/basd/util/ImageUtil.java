package com.sitech.basd.util;

import javax.servlet.http.HttpServletRequest;

public class ImageUtil {
	public static String getImagePath(HttpServletRequest request, String path) {
		return request.getContextPath() + "/cresources/default/images/" + path;
	}
	
	public static String getImageTag(HttpServletRequest request, String path) {
		return "<img src=\"" + getImagePath(request, path) + "\" />";
	}
}
