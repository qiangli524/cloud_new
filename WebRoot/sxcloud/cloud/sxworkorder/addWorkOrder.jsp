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
function RequestChange(obj){
	var requestType = $(obj).val();
	if (requestType == '0') {
		$(".add").show();
		$(".expansion").hide();
		$(".expansion").hide();
	}else if(requestType == '1'){
		$(".add").hide();
		$(".expansion").show();
		$(".expansion").show();
	}
	
};
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
		w.saveWorkOrder($("#theForm").serialize());
	}
	
	
	
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
	<s:hidden name="oper"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" align="left">
						工单类型：
					</td>
					<td>
						<s:select list="#{'0':'申请','2':'回收' }" name="type" id="typer" onchange="showBusi()"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						需求类型：<font color="red">*</font>
					</td>
					<td>
						<s:radio  list="#{'0':'新增类','1':'扩容类' }" name = "requestType"  id = "requestType" value="0" onchange="RequestChange(this)" ></s:radio>
					</td>
				</tr>
				<tr class = "add">
					<td class="til" align="left">
					新增资源理由：
<%--						项目技术建议书:--%>
					</td>
					<td>
<%--					<s:file name="addReason" id="addReason"></s:file>--%>
						<s:textarea name="addReason"  id="addReason" cols="70" rows="8" ></s:textarea>
					</td>
				</tr>
				<tr class = "expansion"  style=" display: none">
					<td class="til" align="left">
						现网扩容理由:
					</td>
					<td>
						<s:textarea name="expansionReason"  id="expansionReason" cols="70" rows="8" ></s:textarea>
					</td>
				</tr>
				<tr class = "expansion" style=" display: none">
					<td class="til" align="left">
						现网扩容当前<br/>部署情况：
					</td>
					<td>
						<s:textarea name="currSituation" id="currSituation" cols="70" rows="8"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						是否计划内需求：<font color="red">*</font>
					</td>
					<td>
						<s:radio list="#{'0':'是','1':'否' }" name = "isInPlan" id = "IsInPlan" value="0"></s:radio>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						是否投资类项目：<font color="red">*</font>
					</td>
					<td>
						<s:radio list="#{'0':'是','1':'否' }" name = "isInvest" id = "IsInvest" value="0"></s:radio>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>