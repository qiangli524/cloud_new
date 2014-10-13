<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/link.jsp"%>
<%@ include file="/common/view.jsp"%>
<html:html locale="true">
<head>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> --%>
<title>error</title>
<style type="text/css">
body {
	margin-left: 0px;
}
.error {
	width: 445px;
	margin: 100px auto 0 auto;
	height: 300px;
}
.error .text {
	margin: 10px 50px;
	color: #999;
	font-size: 12px;
}

</style>
</head>

<body>
<div class="error">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><img src="../cjs/ui2/nresources/common/images/error.jpg" width="445" height="197" /></td>
    </tr>
    <tr>
      <td>
      <div class="text">
      	<% Boolean b = (Boolean)request.getAttribute("error");
      		if(b !=null && !"".equals(b) && b){
      	%>
			<p class="text">对不起，您没有权限</p>      		
      	<%		
      		}else{
      			
      			int status_code = -1;  
      	      	String exception_info = null;  
      	      	Exception theException = null;  
      	    	status_code = ((Integer) request.getAttribute("javax.servlet.error.status_code"));  
      	      	exception_info = (String) request.getAttribute("javax.servlet.error.message");  
      	      	theException = (Exception) request.getAttribute("javax.servlet.error.exception_type");
      			if(status_code==500){ 
      	%>	
      				<p>500-内部服务器错误</p>
      	<%			
      			}else if(status_code==404){
		%>
				<p>404 NOT FOUND - 请求的页面未找到</p>      		
      		<% } %>	
	    <% } %>  
      </div>
      
      </td>
    </tr>
  </table>

</div>
</body>
</html:html>
