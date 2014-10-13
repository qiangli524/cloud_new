<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>

<head>
	<script type="text/javascript">
	  	var api = frameElement.api;
		api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:save,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '返回'
		 });  
		function save(){
			var ip = $('#ip').val();
			var username = $('#username').val();
			var password = $('#password').val();
			var type = $("#type").find('option:selected').val();
			var flag = $("#flag").val();
			api.opener.saveUser(ip,username,password,type,flag);
		}
	</script>
</head>
<body class="pop-body scrollbody">
    <s:form  method="post" id="theForm">
    <s:hidden value="%{flag}" id="flag"></s:hidden>
  	<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
					<td class="til">IP地址 <font color="red">*</font></td>
					<td>
						<s:textfield name="user.ip" id="ip" style="width:150px;   height:20px;" maxlength="30"  cssClass="ipAddress required"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">用户名 <font color="red">*</font></td>
					<td>
					<s:textfield name="user.username" id="username" style="width:150px;   height:20px;" maxlength="30" cssClass="required"></s:textfield>
					<span id="span"><font color="red" id="username_span"></font></span>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">密码 <font color="red">*</font></td>
					<td>
					<s:password type="password" name="user.password" id="password" style="width:150px;   height:20px;" maxlength="16" showPassword="true" cssClass="required"></s:password>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">用户类型</td>
					<td>
					<s:select list="#{'0':'普通用户','1':'管理员用户'}" style="width:150px;   height:20px;" id="type" name="user.type"></s:select>
					</td>
				</tr> 
			</table>
		</div>
    </s:form>
</body>
