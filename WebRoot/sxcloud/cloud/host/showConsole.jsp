<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    <title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<style type="text/css">
.pop-table tr td{text-align: left;}
</style>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/ipCheck/ip.js"></script>
	<script type="text/javascript">
	var api = frameElement.api; 
	var w = api.opener;
    //创建配置文件
     api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:showConsole,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
    function showConsole(){
    	var ip = $("#ip").val();
    	var port = $("#port").val();
    	var user = $("#user").val();
    	var pwd = $("#pwd").val();
    	if(ip.length == 0){
    		alert("请填写IP地址");
    		return false;
    	}
    	if(port.length == 0){
    		alert("请填写端口");
    		return false;
    	}
    	if(user.length == 0){
    		alert("请填写用户名");
    		return false;
    	}
    	if(pwd.length == 0){
    		alert("请填写密码");
    		return false;
    	}
    	var url ="depvideo_makeDeployVideo.do?ip="+ip+"&port="+port+"&user="+user+"&pwd="+pwd;
    	w.showConsole(url);
	}

	</script>
  </head>
  <body  class="pop-body scrollbody">
  	<s:form action="" method="post" name="theForm" id="theForm" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td class="til" width="20%" align="left">
					IP地址:<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="ip" id="ip" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" width="20%" align="left">
					端口:<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="port" id="port" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					用户名:<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="user" id="user" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					密码:<span style="color:red">*</span>
				</td>
				<td>
					<s:password name="pwd" id="pwd" cssStyle="height:30px;width:152px;"></s:password>
				</td>
			</tr>
		</table>
	</s:form>
  </body>
</html>
