<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<import />
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;
	 
	 var oper = '<%=request.getAttribute("oper") %>';
	 var id = '<%=request.getAttribute("id") %>';
	 
	 api.button({
	     id:'Ok',
	     name: '确定',
	     callback:saveBolProgramComposite,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	 
	 function saveBolProgramComposite(){
		 var compositeVO = new Object;
		 compositeVO.programId = $("#programId").val();
		 compositeVO.libId = $("#libId").val();
		 compositeVO.status = $("#status").val();
		 compositeVO.id = id;
		 w.saveBolProgramComposite(compositeVO,oper);
	 }
</script>
<style type="text/css">
</style>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
	<div>
		<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
			<tr>
				<td class="til">
					应用程序<font color="red">*</font>
				</td>
				<td>
					<s:select list="programList" id="programId"  name="compositeVO.programId" headerKey="0" headerValue="--请选择--" listKey="id" listValue="name" />
				</td>
			</tr>
			<tr>
				<td class="til">
					能力库
				</td>
				<td>
					<s:select list="libList" id="libId"  name="compositeVO.libId" headerKey="0" headerValue="--请选择--" listKey="id" listValue="name" />
				</td>
			</tr>
			<tr>
				<td class="til">
					能力库状态<font color="red">*</font>
				</td>
				<td>
					<s:select list="#{'1':'正常','0':'异常'}" name="libVO.status" id="status"/>
				</td>
			</tr>
		</table>
	</div>
	</s:form>
</body>
