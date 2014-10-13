<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>

<head>
	<script type="text/javascript">
	 	var api = frameElement.api;
		api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:save,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '返回'
		 });   
		function save(){
			var name = $('#name').val();
			var description = $('#description').val();
			var flag = $('#flag').val();
			api.opener.saveUserGroup(name,description,flag);
		}
	</script>
</head>
<body class="pop-body scrollbody">
    <s:form method="post" id="theForm">
    <s:hidden value="%{flag}" id="flag"></s:hidden>
  	<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
					<td class="til">用户组名称 <font color="red">*</font></td>
					<td>
						<s:textfield name="group.name" id="name" style="width:150px;height:20px;border:1px solid #999" maxlength="30"  cssClass="ipAddress required"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">用户组描述 <font color="red">*</font></td>
					<td>
					<s:textarea name="group.description" id="description" style="width:500px;height:200px;overflow-y:auto;border:1px solid #999" maxlength="30" cssClass="required"></s:textarea>
					</td>
				</tr> 
			</table>
		</div>
    </s:form>
</body>
