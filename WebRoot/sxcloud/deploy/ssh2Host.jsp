<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<head>
	<title></title>
	<script type="text/javascript">
	//登陆
	function ssh(){
		var ip = $("#ip").val();
		var port = $("#port").val();
		var user = $("user").val();
		var pwd = $("pwd").val();
		if($.trim(port) == ""){
			alert("端口号不能为空!");
			return false;
		}
		if($.trim(user) == ""){
			return false;
		}
		if($.trim(pwd) == ""){
			return false;
		}
		window.open("depvideo_makeDeployVideo.do?user="+user+"&port="+port+"&pwd="+pwd+"&ip="+ip,"ssh");
	}
	//重置
	function reset(){
	}
    </script>
</head>
<body class="pop-body scrollbody">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						IP地址
					</td>
					<td>
                       <s:textfield  id="ip" cssClass="txt" disabled="true" value="%{#request.ip}" name="ip"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						端口号
					</td>
					<td>
				      <s:textfield  id="port" cssClass="txt" value="22" name="port"></s:textfield>
					</td>
				</tr>
				<tr>
				    <td class="til">
						用户名
					</td>
					<td>
                       <s:textfield  id="user" cssClass="txt" name="user"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						密码
					</td>
					<td>
				      <s:password id="pwd" cssClass="txt" name="pwd"></s:password>
					</td>
				</tr>
				<%-- 
				<tr>
					<td colspan="4" class="btnCenter">
					<input type="submit" value="连接" class="thickbox btn-style02" onclick="ssh();return false;"/>
		        	<input type="button" value="重置" class="thickbox btn-style02" onclick="reset();"/>
					</td>
				</tr>
				--%>
			</table>
</body>
