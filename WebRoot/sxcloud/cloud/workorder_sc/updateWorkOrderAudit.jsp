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
		     callback:createWorkOrder,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	
	function createWorkOrder(){
		var _audit_info = $("#audit_info").val();
		if (_audit_info == null || _audit_info == '') {
			alert('请填写审批意见');
			return false;
		}
		w.saveWorkOrderAudit($("#theForm").serialize());
	}
	

	
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="workorder_saveWorkOrderAudit.do" method="post" id="theForm" cssStyle="theForm" name="theForm">
	<s:hidden name="theForm.UUID" id="UUID" ></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" align="left">
						审批结果 ：<font color = "red">*</font>
					</td>
					<td>
						<s:select list="#{'2':'审批通过','3':'审批打回' }" name="theForm.AUDIT_TYPE" id="AUDIT_TYPE"></s:select>
					</td>
				</tr>
				
				<tr>
					<td class="til" align="left">
						审批意见：<font color = "red">*</font>
					</td>
					<td>
						<s:textarea cols="33" rows="5" name="theForm.audit_info" id="audit_info">
						</s:textarea>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>