<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var oper = '<%=request.getAttribute("oper")%>';
	var example_id = '<%=request.getAttribute("example_id")%>';
	var tablespaceid = '<%=request.getAttribute("tablespaceid")%>';
    var api = frameElement.api;
    var w = api.opener;
	$(function(){
		 if("add"==oper){
			 api.button({
			     id:'OkAnd',
			     name: '添加',
			     callback:commitScript,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		 }else{
			 api.button({
			     id:'OkAnd',
			     name: '修改',
			     callback:commitScript,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		 }
	});	  
	function commitScript() {  
 
		if ($("#space_name").val().length == 0 ) {
			alert("表空间名称不能为空！");
			return false;
		}
		
		if ($("#space_size").val().length == 0) {
			alert("表空间大小不能为空！");
			return false;
		}
		api.get("addtablespace").saveTableSpace($("#theForm").serialize(),oper,example_id,tablespaceid);
 
	}
</script>
</head>
<body class="pop-body scrollbody"
<s:if test="dbTableSpaceObj.is_execute==1">
onLoad="self.focus();document.theForm.space_size.focus()"
</s:if>
<s:else>
onLoad="self.focus();document.theForm.space_name.focus()"
</s:else>
>
<s:hidden name="dbTableSpaceObj.is_execute" id="is_execute"/>
	<s:form action="dbexample_saveTableSpace.do" method="post" styleId="theForm" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">表空间名称:<font color="red">*</font></td>
				<td>
					<s:if test="dbTableSpaceObj.is_execute==1">
						<s:textfield name="dbTableSpaceObj.space_name" id="space_name"
							style="width:330px;height:20px;" disabled="true" readonly="true"></s:textfield>
					</s:if>
					<s:else>
						<s:textfield name="dbTableSpaceObj.space_name" id="space_name"
							style="width:330px;height:20px;"></s:textfield>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="til">大小:<font color="red">*</font></td>
				<td>
					<s:textfield name="dbTableSpaceObj.space_size" id="space_size"
						style="width:330px;height:20px;"></s:textfield>M
				</td>
			</tr>
			<tr>
				<td class="til">数据文件目录:<font color="red">*</font></td>
				<td>
					<s:if test="dbTableSpaceObj.is_execute==1">
						<s:textfield name="dbTableSpaceObj.data_file_path" id="data_file_path"
							style="width:330px;height:20px;" disabled="true" readonly="true"></s:textfield>
					</s:if>
					<s:else>
						<s:textfield name="dbTableSpaceObj.data_file_path" id="data_file_path"
							style="width:330px;height:20px;"></s:textfield>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="til">是否自动扩展:<font color="red">*</font></td>
				<td>
					<s:if test="dbTableSpaceObj.is_execute==1">
						<s:select list="#{'1':'是','2':'否'}" name="dbTableSpaceObj.is_expand" id="is_expand" readonly="true" disabled="true"></s:select>
					</s:if>	
					<s:else>
						<s:select list="#{'1':'是','2':'否'}" name="dbTableSpaceObj.is_expand" id="is_expand"></s:select>
					</s:else>
				</td>
			</tr>
			
		</table>
	</s:form>
</body>
</html:html>
