<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
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
			var authId = $("#authId").val();
			var autho1 = $("#autho1").val();
			var autho2 = $("#autho2").val();
			var autho3 = $("#autho3").val();
			
			var autho = autho1+""+autho2+""+autho3;
			
			var entity_id = "";
			var serviceType = "";
			var clusterId = "";
			var flag = "";
			
			var groupId = $("#groupId").val();
			if (groupId.length == 0) {
				entity_id = $("#userId").val();
				serviceType = $("#userServiceType").val();
				clusterId = $("#userClu").val();
				flag = "user";
			} else {
				entity_id = groupId;
				serviceType = $("#groupServiceType").val();
				clusterId = $("#groupClu").val();
				flag = "group";
			}
			
			api.get("viewStra").delPath(authId,entity_id,serviceType,clusterId,autho,flag);
		}
	</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
		<s:hidden name="hadoopUserGroup.service_type" id="groupServiceType"></s:hidden>
		<s:hidden name="hadoopUserGroup.id" id="groupId"></s:hidden>
		<s:hidden name="hadoopUserGroup.cluster_id" id="groupClu"></s:hidden>
		<s:hidden name="hadoopUserObj.id" id="userId"></s:hidden>
		<s:hidden name="hadoopUserObj.cluster_id" id="userClu"></s:hidden>
		<s:hidden name="hadoopUserObj.service_type" id="userServiceType"></s:hidden>
		<s:hidden name="hadoopAuthorityObj.id" id="authId"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						路径：<font color="red">*</font>
					</td>
					<td>
						<s:textarea cols="30" rows="4" name="authPath" id="path" readonly="true"></s:textarea><br/>
					</td>
				</tr>
				<tr>
					<td class="til">
						属主权限：
					</td>
					<td>
						<s:select list="#{'1':'执行权', '2':'写权限', '3':'写权限+执行权', '4':'读权限', '5':'读权限+执行权', '6':'读写权限', '7':'所有权限','0':'无权限'}" id="autho1" name="auto1"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						属主组用户权限：
					</td>
					<td>
						<s:select list="#{'0':'无权限','1':'执行权', '2':'写权限', '3':'写权限+执行权', '4':'读权限', '5':'读权限+执行权', '6':'读写权限', '7':'所有权限'}" id="autho2" name="auto2"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						其他用户权限：
					</td>
					<td>
						<s:select list="#{'0':'无权限','1':'执行权', '2':'写权限', '3':'写权限+执行权', '4':'读权限', '5':'读权限+执行权', '6':'读写权限', '7':'所有权限'}" id="autho3" name="auto3"></s:select>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>
</html:html>