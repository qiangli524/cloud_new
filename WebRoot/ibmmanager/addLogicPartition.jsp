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
	     name: '确定',
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
		 var data = $("#obj").serialize();
		 w.saveLogicPartition(data);
	 }
	</script>
</head>
<body class="pop-body scrollbody" onload="document.obj.name.focus()">
	<s:form action="ibmmanager_saveIBMManager.do" method="post" id="obj">
		<s:hidden name="obj.id" id="id"></s:hidden>
		<s:hidden name="obj.parent_id"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					名称：<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="obj.name" id="name" cssClass="txt"/>
				</td>
			</tr>
		</table>
	</s:form>
</body>
