<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	 var parent_id = <%=request.getAttribute("parent_id")%>;
	 var api = frameElement.api;
	 var w = api.opener;

	 api.button({
	     id:'Ok',
	     name: '添加',
	     callback:add,
	     focus: true
	 },
	 {
	     id:'cancle',
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
		var flag=false;
		$.ajaxSettings.async = false;
		$.getJSON('xen_checkConXenCluster.do?name=' + encodeURI(encodeURI(name)),{'time':new Date().toString()}, function(data){
			if(data.result==1){
				flag=true;
				w.conXenCluster2(name,username,password,parent_id);
			}else{
				alert("该服务器已存在，不能重复添加！");
			}
		});
		return flag;
	}
	
</script>

</head>
<body class="pop-body scrollbody">
	<s:form action="datacenter_saveDataCenter.do" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"class="pop-table nosize">
			<tr>
				<td class="til" >
					服务器名称：
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
