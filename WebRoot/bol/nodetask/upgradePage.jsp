<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<import />
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;
	 api.button({
	     id:'Ok',
	     name: '确定',
	     callback:upgradeResource,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	 
	 function upgradeResource(){
		 var NODE_IP = $("#NODE_IP").val();
		 var HOST_NAME = $("#HOST_NAME").val();
		 var UPDATE_VERSION = $("#UPDATE_VERSION").val();
		 var UPDATE_MODE = $("#UPDATE_MODE").val();
		 var programName = $("#programName").val();
		 w.upgradeResource(NODE_IP,HOST_NAME,UPDATE_VERSION,UPDATE_MODE,programName);
	 }
	 
	 function changMode(){
		 var UPDATE_MODE = $("#UPDATE_MODE").val();
		 if(UPDATE_MODE == 8){
			 $("#programTr").show();
		 }else{
			 $("#programTr").hide();
		 }
	 }
	 
	 function selectParam(){
		 w.$.dialog({
				id:'selectParamPage',
				title:'升级',
				width: '400px',
				height: '225px',
				content: 'url:bolnodetask_selectParam.do',
				lock:true
				});
	 }
	 
	 function updateProgramName(programNames){
		 $("#programName").val(programNames);
		 w.closeDialog("selectParamPage");
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
					主机名称<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="HOST_NAME" id="HOST_NAME" cssStyle="text;" cssClass="required"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					主机IP<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="NODE_IP" id="NODE_IP" cssStyle="text;" cssClass="ipAddress"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					升级版本<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="UPDATE_VERSION" id="UPDATE_VERSION" cssStyle="text;" cssClass="required"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					升级模式<font color="red">*</font>
				</td>
				<td>
					<s:select list="#{'1':'BOL_M','2':'BOL_S','4':'HLA_M','8':'HLA'}" name="UPDATE_MODE" id="UPDATE_MODE" onchange="changMode();"/>
				</td>
			</tr>
			<tr style="display:none" id="programTr">
				<td class="til">
					应用程序<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="programName" id="programName" cssStyle="text;" cssClass="required" disabled="true"></s:textfield>
					<a href="javascript:selectParam();" style="padding-left: 2px">选 择</a>
				</td>
			</tr>
			<%-- 
			<tr style="display:none" id="programTr">
				<td class="til">
					应用程序<font color="red">*</font>
				</td>
				<td>
					<s:select list="programList" name="programName" id="programName" listKey="name" listValue="name"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					用户<font color="red">*</font>
				</td>
				<td >
					<s:textfield name="USER" id="USER" cssStyle="text" cssClass="required"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					密码<font color="red">*</font>
				</td>
				<td >
					<input type="password" name="PASSWORD"  id="PASSWORD" cssClass="required"/>
				</td>
			</tr>
			<tr>
				<td class="til" width="30%">
					原程序状态<font color="red">*</font>
				</td>
				<td>
					<s:select list="#{'1':'正在运行','2':'停止'}" name="PROGRAM_STATE" id="PROGRAM_STATE"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					原程序路径<font color="red">*</font>
				</td>
				<td >
					<s:textfield name="PROGRAM_PATH"  id="PROGRAM_PATH" cssStyle="width: 250px" cssClass="required"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					升级包路径<font color="red">*</font>
				</td>
				<td >
					<s:textfield name="UPGRADEPACKAGE_PATH"  id="UPGRADEPACKAGE_PATH" cssStyle="width: 250px" cssClass="required"></s:textfield>
				</td>
			</tr>
			 --%>
		</table>
	</div>
	</s:form>
</body>
