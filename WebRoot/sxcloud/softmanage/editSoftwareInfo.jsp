<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript">
		var api = frameElement.api;
		var w = api.opener;
		$(function(){
			 api.button({
			     id:'OkAnd',
			     name: '确定',
			     callback:createsoft,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		});
		
		function createsoft(){
			var MANUFACTURERS = $("#MANUFACTURERS").val();
			if (MANUFACTURERS == null || MANUFACTURERS == "") {
				alert("软件制作厂家不能为空");
				return false;
			}
			var SYSTEM_REQUEST = $("#SYSTEM_REQUEST").val();
			if (SYSTEM_REQUEST == null || SYSTEM_REQUEST == "") {
				alert("软件平台要求不能为空");
				return false;
			}
			var PROVIDERS = $("#PROVIDERS").val();
			if (PROVIDERS == null || PROVIDERS == "") {
				alert("软件提供者不能为空");
				return false;
			}
			var INTRODUCE = $("#INTRODUCE").val();
			if (INTRODUCE == null || INTRODUCE == "") {
				alert("软件功能介绍不能为空");
				return false;
			}
			w.saveSoftware($("#theForm").serialize());
		}

</script>
</head>
<body class="pop-body scrollbody">
<s:form action="software_saveSoftwareInfo.do" method="post" cssClass="theForm" id="theForm">
		<s:hidden name="tbBusiSoftwareInfoObj.ID" id="ID"></s:hidden>	
		<s:hidden name="tbBusiSoftwareInfoObj.APPID" id="APPID"></s:hidden>   
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						软件名称 <font color="red">*</font>
					</td>
					<td>
					 	<s:textfield name="tbBusiSoftwareInfoObj.NAME" cssClass="txt" readonly="true" id="NAME"  disabled="true"></s:textfield>                 
					</td>
					<td class="til">
						软件大小
					</td>
					<td>
					    <s:textfield name="tbBusiSoftwareInfoObj.SOFTWARE_SIZE" id="SOFTWARE_SIZE" cssClass="txt" disabled="true"></s:textfield>              
					</td>
				</tr>
				<tr>
				    <td class="til">
						软件制作厂家 <font color="red">*</font>
					</td>
					<td>
                    	<s:textfield name="tbBusiSoftwareInfoObj.MANUFACTURERS" cssClass="txt" id="MANUFACTURERS"></s:textfield>
					</td>
				    <td class="til">
						软件平台要求 <font color="red">*</font>
					</td>
					<td >
						<s:textfield name="tbBusiSoftwareInfoObj.SYSTEM_REQUEST" id="SYSTEM_REQUEST" cssClass="txt"></s:textfield>
					</td>
				</tr>
				<tr>
				    <td class="til">
						所属应用 <font color="red">*</font>
					</td>
					<td >
						<s:textfield name="tbBusiSoftwareInfoObj.APPNAME" id="APPNAME" cssClass="txt" disabled="true"></s:textfield>
					</td>
					<td class="til">
						软件提供者<font color="red">*</font>
					</td>
					<td>
                      <s:textfield name="tbBusiSoftwareInfoObj.PROVIDERS" id="PROVIDERS" cssClass="txt"></s:textfield>
					</td>
				</tr>
				<tr>
				    <td class="til">
						软件功能介绍 <font color="red">*</font>
					</td>
					<td colspan="3">
						<s:textarea name="tbBusiSoftwareInfoObj.INTRODUCE" id="INTRODUCE" cols="97" rows="5"></s:textarea>
					</td>	
				</tr>
				<tr>	
				    <td class="til">
						备注
					</td>
					<td colspan="3">
						<s:textarea name="tbBusiSoftwareInfoObj.REMARK" id="REMARK" cols="97" rows="5"></s:textarea>
					</td>		
				</tr>
			</table>
</s:form>
</body>
