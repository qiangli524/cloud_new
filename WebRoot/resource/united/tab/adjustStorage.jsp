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
<body class="pop-body scrollbody" >
	<s:form action="" method="post" id="clusterUnitedVO">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			
			<tr>
				<td class="til">
					置备的存储：
				</td>
				<td>
					<s:textfield name="clusterUnitedVO.clusterName" id="clusterName" cssClass="txt"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					未共享的存储：
				</td>
				<td>
					1000MB
				</td>
			</tr>
			<tr>
				<td class="til">
					已使用的存储：
				</td>
				<td>
					200MB
				</td>
			</tr>
			<tr>
				<td class="til">
					存储器：
				</td>
				<td>
					NFS58
				</td>
			</tr>
			<tr>
				<td class="til">
						存储使用情况
				</td>
				<td>
					<div id="dsinfo"></div>
				</td>
			</tr>
		</table>
	</s:form>
</body>
