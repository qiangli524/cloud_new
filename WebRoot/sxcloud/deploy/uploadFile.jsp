<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<%
	String ftpuser = String.valueOf(request.getSession().getAttribute("ftpuser"));
	String ftppass = String.valueOf(request.getSession().getAttribute("ftppass"));
	String ftpport = String.valueOf(request.getSession().getAttribute("ftpport"));
	String ip = String.valueOf(request.getSession().getAttribute("ip"));
	String folderPath = String.valueOf(request.getSession().getAttribute("path"));
%>
</head>
  <body>
   <applet alt="" code="ftpupload.MainApplet.class" width="1000" height="500" codebase="sxcloud/deploy" archive="ftpupload.jar">
   <param name="ftpuser" value="<%=ftpuser%>">
   <param name="ftppass" value="<%=ftppass%>">
   <param name="ftpport" value="<%=ftpport%>">
   <param name="hostName" value="<%=ip%>">
   <param name="folderPath" value="<%=folderPath%>">
   </applet>
  </body>
</html>
<jsp:plugin type="applet" code="EmptyApplet.class" height="0" width="0" codebase="../applet" 
		jreversion="1.6.0" iepluginurl="plugin/jre-6u15-windows-i586.exe#Version=1,6,0,0" >
</jsp:plugin>