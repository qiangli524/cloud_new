<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="jxl.Workbook"%>
<%@page import="jxl.write.*"%>

<%	
	out.clear(); 
	out = pageContext.pushBody();

	//String FILE_NAME = new String(request.getParameter("FILE_NAME").getBytes("ISO-8859-1"),"UTF-8");
	//request.setCharacterEncoding("UTF-8");
	//response.reset();
	response.setContentType("application/octet-stream;");
	String path = request.getParameter("path");
	
%>
