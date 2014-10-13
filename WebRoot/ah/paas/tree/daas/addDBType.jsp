<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:addDBType,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	
	function addDBType(){
		var parentId = $("#parent_id").val();
		var name = $("#name").val();
		if(name == null ||name===""){
			alert("名称不能为空！");
			return false;
		}
		w.saveDBType(parentId,$("#treeObj").serialize());
		return true;
	}
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="treeObj" cssStyle="theForm">
	<s:hidden name="treeObj.parent_id" id="parent_id"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til" width="30%">
					名称：
				</td>
				<td width="70%">
					<s:textfield name="treeObj.name" id="name" style="width: 50%"/>
				</td>
			</tr>
			<tr>
				<td class="til" width="30%">
					数据库类型：
				</td>
				<td width="70%">
					<s:select list="#{'3':'oracle','4':'mysql','6':'GP' }" name="treeObj.server_type" id="server_type" style="width: 50%"></s:select>
				</td>
			</tr>
		</table>
		</div>
	</s:form>
</body>