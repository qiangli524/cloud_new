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
	     callback:saveBolProcess,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	 
	 function saveBolProcess(){
		 var processVO = new Object;
		 processVO.name = $("#name").val();
		 processVO.status = $("#status").val();
		 processVO.descrip = $("#descrip").val();
		 processVO.nodeId = $("#nodeId").val();
		 processVO.programId = $("#programId").val();
		 processVO.id = id;
		 w.saveBolProcess(processVO,oper);
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
			<s:if test="#request.oper=='add'">
				<tr>
					<td class="til">
						进程名<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="processVO.name" id="name" cssStyle="text;" cssClass="required"></s:textfield>	
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td class="til">
						进程名
					</td>
					<td>
						<s:textfield name="processVO.name" id="name" cssStyle="text;" disabled="true"></s:textfield>	
					</td>
				</tr>
			</s:else>
			<tr>
				<td class="til">
					云节点
				</td>
				<td>
					<s:select list="nodeList" id="nodeId"  name="processVO.nodeId" headerKey="0" headerValue="--请选择--" listKey="id" listValue="name" />
				</td>
			</tr>
			<tr>
				<td class="til">
					应用程序
				</td>
				<td>
					<s:select list="programList" id="programId"  name="processVO.programId" headerKey="0" headerValue="--请选择--" listKey="id" listValue="name" />
				</td>
			</tr>
			<tr>
				<td class="til">
					进程状态<font color="red">*</font>
				</td>
				<td>
					<s:select list="#{'1':'正常','0':'异常'}" name="processVO.status" id="status"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					进程描述
				</td>
				<td>
					<s:textarea name="processVO.descrip" id="descrip" cssStyle="width:240px;height:100px"></s:textarea>
				</td>
			</tr>
		</table>
	</div>
	</s:form>
</body>
