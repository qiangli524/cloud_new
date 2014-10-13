<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<title></title>
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			.pop-table tr td{text-align: left;}
		</style>
		<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/ipCheck/ip.js"></script>
		<script type="text/javascript">
			var api = frameElement.api; 
			var w = api.opener;
			//创建配置文件
			api.button({
				id:'OkAnd',
				name: '确定',
				callback:updateState,
				focus: true
			},{
				id:'cancle',
				name: '取消'
			});
		
			function updateState(){
				var eq_ip = $("#eq_ip").val();
		    	var hostUserName = $("#hostUserName").val();
		    	var password = $("#password").val();
		    	if(eq_ip.length == 0){
		    		alert("管理IP地址为空，不允许操作");
		    		return false;
		    	}
		    	if(hostUserName.length == 0){
		    		alert("管理用户名为空，不允许操作");
		    		return false;
		    	}
		    	if(password.length == 0){
		    		alert("请输入密码");
		    		return false;
		    	}
		    	var url ="resource_updateHostState.do?"+$("#theForm").serialize();
		    	w.updateHostState(url);
			}
		</script>
	</head>
  
	<!-- <body  class="pop-body scrollbody"> 表单内容少，不需要滚动条。修改如下 -->
	<body  class="pop-body">
	<s:form action="" method="post" name="theForm" id="theForm" >
		<table width="100%" border="1" cellspacing="0" class="pop-table nosize">
		<s:hidden name="oper" id="oper"></s:hidden>
		<s:hidden name="theForm.eq_id" id="eq_id"></s:hidden>
			<tr>
				<td class="til" width="20%" align="left">IP地址:<span style="color:red">*</span></td>
				<td><s:textfield name="theForm.eq_ip" id="eq_ip"></s:textfield></td>
			</tr>
			<tr>
				<td class="til" align="left">用户名:<span style="color:red">*</span></td>
				<td><s:textfield name="theForm.hostUserName" id="hostUserName"></s:textfield></td>
			</tr>
			<tr>
				<td class="til" align="left">密码:<span style="color:red">*</span></td>
				<td><s:password name="theForm.password" id="password"  style="height: 30px;width: 152px;"></s:password></td>
			</tr>
		</table>
	</s:form>
	</body>
</html>
