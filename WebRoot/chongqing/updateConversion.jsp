<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;
	 api.button({
	     id:'Ok',
	     name: '修改',
	     callback:updateConvert,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	 var dialogName = '<%=request.getAttribute("dialogName")%>';
	 function updateConvert(){
		 var id = $("#id").val();
		 var state = $("#state option:selected").val();
		 w.updateConvert(id,state);
	 }
	 
</script>
<style type="text/css">
</style>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
	<s:hidden name="convertObj.id" id="id" />
	<div class="right">
		<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
			<tr>
				<td>源IP:</td>
				<td><s:property value="convertObj.sourceIp"/></td>
			</tr>
			<tr>
				<td>目标IP:</td>
				<td><s:property value="convertObj.destiIp"/></td>
			</tr>
			<tr>
				<td>转换类型:</td>
				<td>
					<s:if test="convertObj.convertType==1">V2P</s:if>
					<s:elseif test="convertObj.convertType==2">V2V</s:elseif>
					<s:elseif test="convertObj.convertType==3">P2V</s:elseif>
					<s:elseif test="convertObj.convertType==4">P2P</s:elseif>
             	</td>
			</tr>
			<tr>
				<td>状态:</td>
				<td>
					<s:select list="#{'1':'进行中','2':'完成'}" name="convertObj.state" id="state"
							cssStyle="width:80px;" onchange="changeConverType()"></s:select>
				</td>
			</tr>
		</table>
	</div>
	</s:form>
</body>
