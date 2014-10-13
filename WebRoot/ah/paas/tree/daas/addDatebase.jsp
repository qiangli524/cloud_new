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
		     callback:addDB,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	
	function addDB(){
		var parentId = $("#parent_id").val();
		var name = $("#name").val();
		var server_type = '<s:property value='treeObj.server_type'/>';
		if(name == null ||name===""){
			alert("数据库名称不能为空！");
			return false;
		}
		w.saveDB(parentId,$("#treeObj").serialize(),server_type);
		return true;
	}
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="treeObj" cssStyle="theForm">
	<s:hidden name="treeObj.parent_id" id="parent_id"></s:hidden>
	<s:if test="treeObj.server_type==6">
		<div>
			<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til" width="30%">
					服务节点名称：
				</td>
				<td width="70%">
					<s:textfield name="treeObj.name" id="name" style="width: 50%"/>
				</td>
			</tr>
		</table>
		</div>
	</s:if>
	<s:else>
		<div>
			<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til" width="30%">
					数据库名称：
				</td>
				<td width="70%">
					<s:textfield name="treeObj.name" id="name" style="width: 50%"/>
				</td>
			</tr>
			<s:if test="treeObj.server_type==3">
				<tr>
					<td class="til" width="30%">
						类型：
					</td>
					<td width="70%">
					<%--<s:select list="#{'31':'Oracle DB','32':'Oracle Instance','33':'Oracle Server','34':'Oracle 监控器','35':'业务服务'}" name="treeObj.server_type" id="server_type" style="width: 50%"></s:select>--%>
					<s:select list="#{'31':'Oracle DB','32':'Oracle Instance','33':'Oracle Service'}" name="treeObj.server_type" id="server_type" style="width: 50%"></s:select>
					</td>
				</tr>
			</s:if>
		</table>
		</div>
	</s:else>
	</s:form>
</body>