<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<head>
<title></title>

<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:saveInfo,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	
	function saveInfo(){
		var appname = $("#appName").val();
		$("#namespan").empty();
		if (appname.length == 0) {
			$("#namespan").append("<font color='red'>ERROR：请填写应用名称</font>");
			return false;
		}
		
		var appurl = $("#appUrl").val();
		$("#urlspan").empty();
		if (appurl.length == 0) {
			$("#urlspan").append("<font color='red'>ERROR：请填写应用地址</font>");
			return false;
		}
		w.saveApp($("#theForm").serialize());
	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="tbcloud2application_save.do" method="post" id="theForm">
	<s:hidden name="applicationObj.id"></s:hidden>
	<s:hidden name="oper" id="oper"></s:hidden>
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td class="til">
					应用名：<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="applicationObj.appname" id="appName"></s:textfield>
					<span id="namespan"></span>
				</td>
			</tr>
			<tr>
				<td class="til">
					应用地址：<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="applicationObj.url" id="appUrl"></s:textfield>
					<span id="urlspan"></span>
				</td>
			</tr>
			<tr>
				<td class="til">
					应用描述：
				</td>
				<td>
					<s:textarea name="applicationObj.description" id="appDescr" rows="4" cols="40"></s:textarea>
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html:html>
