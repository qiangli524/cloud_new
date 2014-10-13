<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function() {
		api.button({
			id : 'OkAnd',
			name : '确定',
			callback : createRecoverResource,
			focus : true
		}, {
			id : 'cancle',
			name : '取消'
		});
	});

	function createRecoverResource() {
		var entityid = $("#entityid").val();
		if (entityid.length == 0) {
			alert("请选择一个资源进行回收");
			return false;
		}
		api.get("viewResource").saveResource($("#theForm").serialize());
	}

	$(function() {
		var projectid = $("#projectid").val();
		var oper = $("#oper").val();
		var resource_type = $("#resource_type").val();
		$("#chooseEntity")
				.click(
						function() {
							w.$
									.dialog({
										id : 'selectEntiy',
										title : '选择资源',
										width : '750px',
										height : '450px',
										max : false,
										min : false,
										lock : true,
										content : 'url:workorder_selectRecoverResouce.do?projectid='
												+ projectid
												+ '&oper='
												+ oper
												+ '&resource_type='
												+ resource_type
									});
						});
	});

	function selectRecoverResouce(eq_id, connectid, host_name) {
		$("#entityid").val(eq_id);
		$("#entityname").val(host_name);
	}
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
		<s:hidden name="oper" id="oper"></s:hidden>
		<s:hidden name="workOrderObj.ID" id="wrid"></s:hidden>
		<s:hidden name="projectid" id="projectid"></s:hidden>
		<s:hidden name="type" id="type"></s:hidden>
		<s:hidden name="uuid" id="uuid"></s:hidden>
		<s:hidden name="resource_type" id="resource_type"></s:hidden>
		<s:hidden name="workOrderObj.ENTITY_ID" id="entityid"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td>回收的资源</td>
					<td><s:textfield name="entityname" id="entityname"
							readonly="true"></s:textfield> <input type="button"
						class="thickbox btn-style02" value="选择" id="chooseEntity" />
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>