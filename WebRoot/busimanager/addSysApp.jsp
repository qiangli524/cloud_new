<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<head>
	<title></title>
	<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;

	 api.button({
	     id:'sysApp',
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
		 var number = $("#number").val();
		 if(number==null||number==''){
		 	alert("简称不能为空");
		 	return false;
		 }
		 var userName = $("#userName").val();
		 if(userName==null||userName==''){
		 	alert("负责人不能为空");
		 	return false;
		 }
		 var oem = $("#oem").val();
		 if(oem==null||oem==''){
		 	alert("厂商不能为空");
		 	return false;
		 }
		 var form = $("#theForm").serialize();
		 w.saveSysApp(form);
	 }
	 
	$(function(){
		$("#chooseuser").click(function(){
			var flag = '<s:property value="flag" />';
			w.$.dialog({
		      id:'chooseuser',
		      title:'选择用户',
		      lock:true,
		      width:'800px',
		      height:'400px',
		      content:'url:bmanager_queryUserInfoList.do?flag='+flag 			});
		})
	});
	function getUser(id,name){
		$("#user_id").attr("value",id);
		$("#userName").attr("value",name);
	}
	</script>
</head>
<body class="mainbody" onload="document.theForm.name.focus()">
	<s:form action="" method="post" id="theForm">
		<s:hidden name="theForm.id" id="id"></s:hidden>
		<s:hidden name="theForm.parent_id"></s:hidden>
		<s:hidden name="theForm.user_id" id="user_id"></s:hidden>
		<s:hidden name="flag"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					子业务系统名称：<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.name" id="name" cssClass="txt"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					子业务系统简称：<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.number" id="number" cssClass="txt"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					子业务系统负责人：<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.userName" id="userName" cssClass="txt" disabled="true"/><span class="ubtn-1 mgl-20"><input id="chooseuser" value="选择" class="btn-style02" /></span>
				</td>
			</tr>
			<tr>
				<td class="til">
					子业务系统厂商：<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.oem" id="oem" cssClass="txt"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					子业务系统描述：
				</td>
				<td>
					<s:textfield name="theForm.desc" id="desc" cssClass="txt"/>
				</td>
			</tr>
		</table>
	</s:form>
</body>
