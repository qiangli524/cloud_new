<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">

	 var api = frameElement.api;
	 var w = api.opener;

	 api.button({
	     id:'Ok',
	     name: '添加',
	     callback:add,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	
	function add(){
		var name = $("#name").val();
		if(name==""){
			alert("名字不能为空!");
			$("#name").focus();
			return false;
		}
		var parentId = $("#parent_id").val();
		var url = "unitedNetwork_saveDomain.do?"+$("#obj").serialize();
		w.saveDomain(url,parentId);
	}
	
</script>
</head>
<body class="pop-body scrollbody" >
	<s:form action="" method="post" id="obj">
	<s:hidden name="obj.parent_id" id="parent_id"></s:hidden>
	<s:hidden name="obj.vtype" id="vtype"></s:hidden>
	<s:hidden name="obj.type" id="type"></s:hidden>
	<s:if test="obj.type==6">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize" >
			<tr>
				<td class="til" >
					网络域名称：
				</td>
				<td>
					<s:textfield name="obj.name" id="name" />
				</td>
			</tr>
		</table>
	</s:if><s:elseif test="obj.type==7">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize" >
			<tr>
				<td class="til" >
					子网络域名称：
				</td>
				<td>
					<s:textfield name="obj.name" id="name" />
				</td>
			</tr>
		</table>
	</s:elseif>
	</s:form>
</body>
