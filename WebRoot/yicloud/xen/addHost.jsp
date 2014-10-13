<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	 var ID = <%=request.getAttribute("ID")%>;
	// var pool_uuid=<%=request.getAttribute("poolUuid")%>;
	 
	 var api = frameElement.api;
	 var w = api.opener;

	 api.button({
	     id:'Ok13',
	     name: '添加服务器',
	     callback:add,
	     focus: true
	 },
	 {
	     id:'cancle13',
	     name: '取消'
	 });
	
	function add(){
		var name =$("#NAME").val();
		var username = $("#USERNAME").val();
		var password = $("#PASSWORD").val();
		if(name=='' ){
			alert("主服务器名称不能为空");
			return false;
		}
		if(username==''){
			alert("用户名不能为空");
			return false;
		}
		if(password==''){
			alert("密码不能为空");
			return false;
		}
		w.host_add2(ID,pool_uuid,name,username,password);
		return false;
	}
	
</script>

</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"class="pop-table nosize">
			<tr>
				<td class="til" >
					主服务器名称：
				</td>
				<td>
					<s:textfield name="theForm.NAME" id="NAME"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					用户名：
				</td>
				<td>
					<s:textfield name="theForm.USERNAME" id="USERNAME"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					密码：
				</td>
				<td>
					<s:password name="theForm.PASSWORD" id="PASSWORD" showPassword="true"/>
				</td>
			</tr>
		</table>
	</s:form>
</body>
