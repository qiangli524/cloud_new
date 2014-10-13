<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<import />
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;
	 api.button({
	     id:'Ok',
	     name: '确定',
	     callback:releaseResource,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	 
	 function releaseResource(){
		 var NODE_IP = $("#NODE_IP").val();
		 var NUMBER = $("#NUMBER").val();
		 w.releaseResource(NODE_IP,NUMBER);
	 }
</script>
<style type="text/css">
</style>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
	<div>
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td class="til">
					编号<font color="red">*</font>
				</td>
				<td >
					<s:textfield name="NUMBER" id="NUMBER" cssStyle="text" cssClass="required"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					主机IP<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="NODE_IP" id="NODE_IP" cssStyle="text;" cssClass="ipAddress"></s:textfield>
				</td>
			</tr>
		</table>
	</div>
	</s:form>
</body>
