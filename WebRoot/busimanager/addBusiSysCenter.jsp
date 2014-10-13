<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<head>
	<title></title>
	<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;

	 api.button({
	     id:'Ok',
	     name: '添加',
	     callback:ok,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	 
	 function ok(){
		 var name=$("#name").val();
		 if(name==null||name==""){
			 alert("名称不能为空.");
			 return false;
		 }
		 var number = $("#number").val();
		 if(number==null||number==''){
		 	alert("简称不能为空");
		 	return false;
		 }
		 var form = $("#theForm").serialize();
		 w.saveBusiSystemCenter(form);
	 }
	</script>
</head>
<body class="mainbody" onload="document.theForm.name.focus()">
	<s:form action="bmanager_saveBusiSysCenter.do" method="post" id="theForm">
		<s:hidden name="theForm.id" id="id"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					业务中心名称：<span style="color:red">*</span>
				</td>
				<td>
				
					<s:textfield name="theForm.name" id="name" maxlength="30" cssClass="required inpt-2"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					业务中心简称：<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.number" id="number" maxlength="30" cssClass="required inpt-2"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					业务中心描述：
				</td>
				<td>
					<s:textfield name="theForm.desc" id="desc" maxlength="30" cssClass="required inpt-2"/>
				</td>
			</tr>
		</table>
	</s:form>
</body>
