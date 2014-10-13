<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.sitech.basd.sxcloud.util.ssh.SshConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html style="width:100%; height:80%; overflow:hidden">
<script language='javascript'>
    var sequence = <%=request.getAttribute("sequence")%>;
	var xmlHttp;
	//ajax
	function createXmlHttp() {
	    if (window.XMLHttpRequest) {
	       xmlHttp = new XMLHttpRequest();                  
	    } else {
	       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }
	}
	
	function showLog(){
		createXmlHttp();
		var connection=document.getElementById("<%=SshConstants.PARAMETER_CONNECTION%>").value;
		var shellCmd=document.getElementById("writeLine").value;
	//	var id = document.getElementById("videoid").value;
		//禁止VI
		if(shellCmd.length >=2 && shellCmd.substr(0,2)=="vi"){
			alert("禁止vi命令!");
			return;
		}
		var id = <%=request.getAttribute("videoid")%>;
	    xmlHttp.open("GET", "depvideo_sendCmd.do?connection="+connection+"&shellCmd="+shellCmd+"&videoid="+id+"&sequence="+sequence, true);
	    xmlHttp.setRequestHeader("If-Modified-Since","0");        
	    xmlHttp.send(null);
	    var writeLine = document.getElementById("writeLine");
		writeLine.value="";
		xmlHttp.onreadystatechange = function(){
   	    if (xmlHttp.readyState == 4) {
   	    	if(xmlHttp.status == 200){
   	    		var shell = document.getElementById("shell");
   	    		shell.innerHTML=xmlHttp.responseText;	
				$("#shell").css("height","378px");
				shell.scrollTop =shell.scrollHeight;
 				writeLine.focus();
			}
	   	}
    }
	}
  setInterval( "showLog1()",800);
  function showLog1(){
		createXmlHttp();
	//	var id = document.getElementById("videoid").value;
		var id = <%=request.getAttribute("videoid")%>;
		var writeLine=document.getElementById('writeLine');
		var connection=document.getElementById("<%=SshConstants.PARAMETER_CONNECTION%>").value;
	    xmlHttp.open("GET", "depvideo_sendCmd.do?connection="+connection+"&shellCmd=&videoid="+id+"&sequence="+sequence, true);
	    xmlHttp.setRequestHeader("If-Modified-Since","0");        
	    xmlHttp.send(null);
		xmlHttp.onreadystatechange = function(){
   	    if (xmlHttp.readyState == 4) {
   	    	if(xmlHttp.status == 200){
   	    		var shell = document.getElementById("shell");
   	    		shell.innerHTML=xmlHttp.responseText;	
				$("#shell").css("height","378px");
				shell.scrollTop =shell.scrollHeight;
 				writeLine.focus();
			}
	   	}
    }
	}
	function getKey( event )
	{
	    if (window.event)
	    {
	        return window.event.keyCode;
	    }
	    else if (event)
	    {
	        return event.which;
	    }
	    else
	    {
	        return null;
	    }
	}

	function textBoxKeypress( event )
	{
	    var key = getKey( event );
	    if (key == null) return true;
	
	    if ( key==13 )
	    {
			showLog();
	        return false;
	    }
	
	}

	onkeydown="if ((event.which && event.which == 13) || (event.keyCode && event.keyCode == 13)) {;return false;} else return true;"
	
	function writeControlChar( chars )
	{
	    document.forms['ShellClient'].<%=SshConstants.PARAMETER_DATA%>.value = document.forms['ShellClient'].<%=SshConstants.PARAMETER_DATA%>.value + chars;
	    document.forms['ShellClient'].<%=SshConstants.PARAMETER_DATA%>.focus();
	}
	
	function viewBuffer()
	{
	    window.open("shell-buffer.jsp?<%=request.getQueryString()%>", "Buffer", "width=800, height=400, location=no, menubar=no, status=no, toolbar=yes, scrollbars=yes, resizable=yes");
	}
	
	function refresh()
	{
	    window.location = "<%=request.getRequestURL() + "?" + request.getQueryString()%>";
	}
	//open sftp
	function toSftp(){
		document.forms['ShellClient'].action="depvideo_showSshFile.do";
		document.forms['ShellClient'].target="_blank";
		document.forms['ShellClient'].submit();
	}
	function logoOut(){
		document.forms['ShellClient'].action="depvideo_loginoutHost.do";
		document.forms['ShellClient'].submit();
	}
	function windowopen(){
		win = window.open("depcomm_reploy_process.do","reploy_process",'width=500, height=400, resizable=yes,left=900,top=10');
	}
	function save(){
		$.getJSON("tree_saveSolution.do?videoid=" + <%=request.getAttribute("videoid")%> + "&Date"+(new Date()),function(data){
			if(data ==1){
				alert("保存为模板成功");
			}else{
				alert("保存为模板失败");
			}
		})
	}
</script>
<head>
    <title>SSH SHOW</title>
    
<style type="text/css">
<!--
#shell { /*background-color: #F7F7F7;*/
	white-space: pre;
	font-size: 10pt;
	color: white;
	font-family: monospace;
}
body {
font-color:white;
background-color: black;
}
	
-->
</style>

</head>
<%
String connectionInfo = String.valueOf(request.getAttribute(SshConstants.PARAMETER_CONNECTION ));
%>

<body onload="showLog()" onfocus="setFocus()" onmouseover="setFocus()">

<form name="ShellClient" method="post" action="<%=SshConstants.SERVLET_SHELL%>">
<input id="videoid" name="videoid" type="hidden" value="<%=request.getParameter("videoid")==null?"":request.getParameter("videoid") %>">
<%--<b><a href="#" onclick="logoOut()"><font color=white>退出</font></a></b><hr/>--%>
<div id="shell" style="overflow:auto;white-space:pre; margin-top: 10px;" onmouseover="setFocus()" >
	装载中...
</div>

<hr/>
<p>
<b><input type="button" onclick="logoOut()" value="退出" style="-moz-opacity:0.5;   filter:alpha(opacity=50);-khtml-opacity: 0.5; opacity: 0.5;" ></b>
<b><input type="button" onclick="toSftp()" value="SFTP" style="-moz-opacity:0.5;   filter:alpha(opacity=50);-khtml-opacity: 0.5; opacity: 0.5;" >
<b><input type="button" onclick="windowopen()" value="打开" style="-moz-opacity:0.5;   filter:alpha(opacity=50);-khtml-opacity: 0.5; opacity: 0.5;"/></b>
<b><input type="button" onclick="save()" value="确定" style="-moz-opacity:0.5;   filter:alpha(opacity=50);-khtml-opacity: 0.5; opacity: 0.5;"/></b>
</p>
<hr/>
<font style="color:white;font-size: 10pt;">$ </font>
<input type='text' id='writeLine' name='writeLine' onKeyPress='return textBoxKeypress(event)' style='border:none;background-color:black;width:400px;color:white;'/> 
    <input type="hidden" id="<%=SshConstants.PARAMETER_CONNECTION%>" name="<%=SshConstants.PARAMETER_CONNECTION%>" value="<%=connectionInfo%>" />
    <!-- <input type="text" name="<%=SshConstants.PARAMETER_DATA%>" onKeyPress="return textBoxKeypress(event)" />-->
    <input type="hidden" name="writeline" value="Write Line" />
     <!--<input type="submit" name="writeline" value="Write Line" />-->
    <!--<input type="submit" name="write" value="Write" />-->
    <!--<input type="button" value="View Buffer" onClick="viewBuffer()" />-->
    <!--<input type="button" value="Refresh" onClick="refresh()" />-->
</form>
<p>
To write control characters, write them as their hex value, prefixed with #.
For example, ESC is #1B.  To write a # Character, write #23.  The control
key is sent as #-1, and causes the next character to be sent as if the
Ctrl key were held down.
</p>
<p>
The following buttons add control characters to the write input field.
</p>

<form name="SpecialChars">

     <!--<input type="button" value="CTRL" onclick="writeControlChar('#-1');" />-->
   <!-- <input type="button" value="ESC" onclick="writeControlChar('#1B');" />-->
    <!--<input type="button" value="#" onclick="writeControlChar('#23');" />-->

</form>
<script language="javascript">
 function setFocus(){
	 var writeLine = document.getElementById("writeLine");
	 writeLine.focus();
 }
</script>
</body>
</html>