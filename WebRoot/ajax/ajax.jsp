<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page contentType="text/plain; charset=UTF-8"%>
<%
out.clear();
String info = (String)request.getAttribute("info") ;
out.println(info);
%>  