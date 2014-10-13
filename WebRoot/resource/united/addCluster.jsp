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
		var name = $("#clusterName").val();
		if(name==""){
			alert("名字不能为空!");
			$("#clusterName").focus();
			return false;
		}
		var hostName = $("#hostName").val();
		var parent_id = '<s:property value="parent_id" />';
		var parent_uuid='<s:property value="parent_uuid" />';
		var connect_id = '<s:property value="connect_id" />';
		var vtype = '<s:property value="vtype" />';
		var str = $("#clusterUnitedVO").serialize();
		w.saveCluster(name,parent_id,parent_uuid,connect_id,vtype,str);
	}
	
	
</script>
</head>
<body >
	<s:form action="" method="post" id="clusterUnitedVO">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			
			<tr id="clusterNameTr">
				<td class="til" width="20%">
					集群名称：
				</td>
				<td>
					<s:textfield name="clusterUnitedVO.clusterName" id="clusterName" cssClass="inpt-2 vm"/>
				</td>
			</tr>
			<s:if test="vtype==2">
			<tr id="hostNameTr" >
					<td class="til" width="20%">
						服务器IP地址：
					</td>
					<td>
						<s:textfield name="clusterUnitedVO.address" id="address" cssClass="inpt-2 vm"/>
					</td>
				</tr>
				<tr id="hostUserNameTr">
					<td class="til"  width="20%">
						用户名：
					</td>
					<td>
						<s:textfield name="clusterUnitedVO.hostUserName" id="hostUserName" cssClass="inpt-2 vm"/>
					</td>
				</tr>
				<tr id="hostPasswordTr">
					<td class="til"  width="20%">
						密码：
					</td>
					<td>
						<s:password name="clusterUnitedVO.hostPassword" id="hostPassword" cssClass="inpt-2 vm"/>
					</td>
				</tr>
			</s:if>
		</table>
	</s:form>
</body>
