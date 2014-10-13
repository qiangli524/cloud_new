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
		$("#numberspan").empty();
		var disk_number = $("#disk_number").val();
		if (disk_number.length == 0) {
			$("#numberspan").append("<font color='red'>ERROR：请填写磁盘号</font>");
			return false;
		}
		
		$("#idspan").empty();
		var disk_id = $("#disk_id").val();
		if (disk_id.length == 0) {
			$("#idspan").append("<font color='red'>ERROR：请填写磁盘编号</font>");
			return false;
		}
		
		$("#namespan").empty();
		var disk_name = $("#disk_name").val();
		if (disk_name.length == 0) {
			$("#namespan").append("<font color='red'>ERROR：请填写磁盘名称</font>");
			return false;
		}
		
		$("#typespan").empty();
		var disk_type = $("#disk_type").val();
		if (disk_type == -1) {
			$("#typespan").append("<font color='red'>ERROR：请选择磁盘类型</font>");
			return false;
		}
		
		$("#sizespan").empty();
		var disk_dize = $("#disk_dize").val();
		if (disk_dize.length == 0) {
			$("#sizespan").append("<font color='red'>ERROR：请填写磁盘大小</font>");
			return false;
		}
	    w.saveDisk($("#diskInfoObj").serialize());
	}
	
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="diskInfoObj" cssStyle="theForm">
	<s:hidden name="flag" id="flag"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						磁盘号：<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="diskInfoObj.disk_number" id="disk_number"></s:textfield>
						<span id="numberspan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">
						磁盘编号：<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="diskInfoObj.disk_id" id="disk_id"></s:textfield>
						<span id="idspan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">
						磁盘名称:<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="diskInfoObj.disk_name" id="disk_name"></s:textfield>
						<span id="namespan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">
						磁盘类型:<font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'-1':'--请选择--','1':'热备盘','2':'普通磁盘'}" name="diskInfoObj.disk_type" id="disk_type"></s:select>
						<span id="typespan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">
						磁盘大小(G):<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="diskInfoObj.disk_dize" id="disk_dize"></s:textfield>
						<span id="sizespan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">
						所属磁盘组：<font color="red">*</font>
					</td>
					<td>
						<s:if test="diskGroupList == null">
							<select id="diskgroup_id"><option value="-1">--请选择--</option></select>
						</s:if><s:else>
							<s:select list="diskGroupList" listKey="uuid" listValue="name" headerKey="-1" headerValue="--请选择--" id="diskgroup_id" name="diskInfoObj.diskgroup_id"></s:select>
						</s:else>
						<span id="groupspan"></span>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>
</html:html>