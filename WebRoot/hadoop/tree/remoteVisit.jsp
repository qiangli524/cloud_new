<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	var parent_nodeType = '<s:property value="obj.node_type"/>';
	var parent_serviceType = '<s:property value="obj.service_type"/>';
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:remoteVisit,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	
	function remoteVisit(){
		var ip = $("#ip").val();
		var port = $("#port").val();
		var username = $("#username").val();
		var password = $("#password").val();
		window.open("depvideo_makeDeployVideo.do?user="+username+"&port="+port+"&pwd="+password+"&ip="+ip,"ssh");
	}
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="obj" cssStyle="theForm">
	<s:hidden name="obj.parent_id" id="parent_id"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" align="left">
						主机IP地址 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="" id="ip"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						端口 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="" id="port"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						用户名 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="" id="username"></s:textfield>
					</td> 
				</tr>
				<tr>
					<td class="til" align="left">
						密码 <font color="red">*</font>
					</td>
					<td>
						<s:password name="" id="password"></s:password>
					</td> 
				</tr>
			</table>
		</div>
	</s:form>
</body>