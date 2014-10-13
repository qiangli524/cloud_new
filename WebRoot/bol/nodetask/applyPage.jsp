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
	     id:'Ok1',
	     name: '确定',
	     callback:applyResource,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	 
	 function applyResource(){
		 var HOST_NAME = $("#HOST_NAME").val();
		 var NODE_TYPE = $("#NODE_TYPE").val();
		 var NODE_IP = $("#NODE_IP").val();
		 var NODE_CAPABILITY = $("#NODE_CAPABILITY").val();
		 var USER = $("#USER").val();
		 var PASSWORD = $("#PASSWORD").val();
		 w.applyResource(HOST_NAME,NODE_TYPE,NODE_IP,NODE_CAPABILITY,USER,PASSWORD);
	 }
</script>
<style type="text/css">
</style>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
	<div>
		<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til" width="33%">
						主机名称<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="HOST_NAME" id="HOST_NAME" cssClass="required"></s:textfield>
					</td>
				</tr> 
				<tr>
					<td class="til" >
						主机类型<font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'1':'X86','2':'小机','3':'刀片'}" name="NODE_TYPE" id="NODE_TYPE"/>
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
			<tr>
				<td class="til">
					主机能力<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="NODE_CAPABILITY" id="NODE_CAPABILITY" cssStyle="text" cssClass="posInt"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					用户<font color="red">*</font>
				</td>
				<td >
					<s:textfield name="USER" id="USER" cssStyle="text" cssClass="required"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					密码<font color="red">*</font>
				</td>
				<td >
					<input type="password" name="PASSWORD"  id="PASSWORD" cssClass="required"/>
				</td>
			</tr>
		</table>
	</div>
	</s:form>
</body>
