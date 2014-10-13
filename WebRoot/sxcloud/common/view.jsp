<!-- 
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.css.CssUtil" %>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.js.JsUtil" %>
-->
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
/*
public String getCssTag(HttpServletRequest request, String path) {
	return CssUtil.getCssTag(request, path);
}
public String getJsTag(HttpServletRequest request, String path) {
	return JsUtil.getJsTag(request, path);
}
*/
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}
%>