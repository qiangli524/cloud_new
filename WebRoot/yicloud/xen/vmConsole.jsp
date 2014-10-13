<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
</head>
<body>
<%		
        String path = (String)request.getAttribute("path");
		String host = (String)request.getAttribute("host");
%>
<s:if test='#request.state=="false"'>
    <div align="center" >
      <h2>虚拟机未开启,请开启虚拟机...</h2>
    </div>
</s:if>
<s:if test='#request.state=="Running"||#request.state==null'>
	<div align="center">
		<jsp:plugin type ="applet" align="middle" code="VncViewer.class" width="800" height="625" codebase="/cloud/console" align="middle" iepluginurl="http://58.32.224.18:8888/jre-6u33-windows-i586.exe">
		 <jsp:params> 
				<jsp:param  name="HOST" value="<%=path%>"/>
				<jsp:param  name="PORT" value="443"/>
				<jsp:param  name="PROXYHOST1" value="<%=host%>"/>
				<jsp:param  name="PROXYPORT1" value="443"/>
				<jsp:param  name="SocketFactory" value="HTTPSConnectSocketFactory"/>
		 </jsp:params>
		 <jsp:fallback>加载Java Applet失败！</jsp:fallback>
		</jsp:plugin>
	</div>
</s:if>
<s:elseif test='#request.state=="Halted"'>
    <div align="center" >
      <h2>虚拟机关闭状态,请开启虚拟机...</h2>
    </div>
</s:elseif>
<s:elseif test='#request.state=="Paused"'>
    <div align="center" >
      <h2>虚拟机暂停状态,请运行虚拟机...</h2>
    </div>
</s:elseif>
<s:elseif test='#request.state=="Suspended"'>
    <div align="center" >
      <h2>虚拟机挂起状态,请恢复虚拟机...</h2>
    </div>
</s:elseif>
</body>
