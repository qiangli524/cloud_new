<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<title></title>
<script type="text/javascript">
$(function(){
	 var api = frameElement.api;
	 var w = api.opener;
			api.button({
			     id:'OkAnd',
			     name: '添加',
			     callback:addChildSys,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		
	 function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
		}
	 //添加
	 function addChildSys(){
		var parentId = $("#uuid").val();
		var name = $("#objName").val();
		var server_type = $("#sel").val();
		if(name == null || name ==  ""){
			alert("名称不能为空！");
			return false;
		}
		if(server_type == null || server_type ==  ""){
			alert("类型不能为空！");
			return false;
		}
		w.saveApp(parentId,$("#theForm").serialize());
		return true;
	}
});
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" styleId="theForm" id="theForm">
		<s:hidden name="uuid" id="uuid"></s:hidden>
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td class="til">服务名称:<font color="red">*</font></td>
				<td >
				<s:textfield  name="paasBusiTreeObj.name" cssClass="txt" id="objName" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">服务类型<font color="red">*</font></td>
				<td>
					<select id="sel" name="paasBusiTreeObj.server_type">
						<option value="">--请选择--</option>
						<option value="0">应用主机</option>
						<option value="1">oracle(x86)</option>
						<option value="2">应用进程</option>
					</select>
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html>