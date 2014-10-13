<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	 var cluster_uuid = '<%=request.getParameter("cluster_uuid")%>';
	 var api = frameElement.api;
	 var w = api.opener;

	 api.button({
	     id:'Ok',
	     callback:conn,
	     name: '连接',
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	 
	 function reset(){
	 	$("#theForm").reset();
	 }
	
    //function Trim(str){
	//return str.replace(/^\s+|\s+$/g,"");
	//}
	function conn(){
		var ip = $("#IP").val();
		var username = $("#USERNAME").val();
		var password = $("#PASSWORD").val();
		if(ip=='' ){
			alert("IP地址不能为空");
			return false;
		}
		if(username==''){
			alert("用户名不能为空");
			return false;
		}
		if(password==''){
			alert("密码不能为空");
			return false;
		}
		$.ajaxSettings.async = false;
		$.getJSON('xen_connectCluster.do?ip=' + ip+"&username="+username+"&password="+encodeURIComponent(password)+"&cluster_uuid="+cluster_uuid,{'time':new Date().toString()}, function(data){
			if(data.result ==2){
				alert("集群连接成功");
				w.refreshParentNode();
			}else if(data.result == 1){
				alert("连接可用");
			}else if(data.result == -1){
				alert("连接失败，请检查ip地址、用户名、密码！");
			}
		});
	}
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="datacenter_saveDataCenter.do" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til" width="20%">
					集群IP地址：
				</td>
				<td>
					<s:textfield name="theForm.IP" id="IP"/>
				</td>
			</tr>
			<tr>
				<td class="til"  width="20%">
					用户名：
				</td>
				<td>
					<s:textfield name="theForm.USERNAME" id="USERNAME"/>
				</td>
			</tr>
			<tr>
				<td class="til"  width="20%">
					密码：
				</td>
				<td>
					<s:password name="theForm.PASSWORD" id="PASSWORD"/>
				</td>
			</tr>
		</table>
	</s:form>
</body>
