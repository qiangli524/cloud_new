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
		//$("#hostIp").attr("disabled",false);
		//$("#username").attr("disabled",false);
		var name = $("#hostIp").val();
		var parent_id = '<s:property value="parent_id" />';
		var parent_uuid = '<s:property value="parent_uuid" />';
		var connect_id = '<s:property value="connect_id" />';
		var vtype = '<s:property value="vtype" />';
		var str = $("#hostUnitedVO").serialize();
		w.saveHost(name,parent_id,parent_uuid,connect_id,vtype,str);
	}
	
	//根据虚拟化类型选择未分配主机
	function selectUnAllocatedHost(){
		var vtype = '<s:property value="vtype" />';
		var connect_id = '<s:property value="connect_id" />';
			w.$.dialog({
				id:'unallocate',
	  			title:'选择未分配主机',
	  			width: '800px',
	  			height: '400px',
	  		    lock:true,
	  			content: 'url:united_selectUnAllocatedHost.do?vtype='+vtype+'&connect_id='+connect_id
		});
	}
	
	//获取选择未分配主机的用户名、密码
	function getHostInfo(ip,username){
		$("#hostIp").attr("value",ip);
		//$("#hostIp").attr("disabled",true);
		$("#username").attr("value",username);
		//$("#username").attr("disabled",true);
		$("#select").attr("value",ip);
	}
</script>
</head>
<body >
	<s:form action="" method="post" id="hostUnitedVO">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize" >
			<tr>
				<td class="til" >
					从预留资源池中选择主机：
				</td>
				<td>
					<input type="text" disabled="disabled" id="select" class="inpt-2 vm width:80px;"/>
					<span class="ubtn-green"><input type="button"  value="选择" onclick="selectUnAllocatedHost()"/></span>
				</td>
			</tr>
			<tr>
				<td class="til" >
					主机名称/IP地址：
				</td>
				<td>
					<s:textfield name="hostUnitedVO.hostIp" id="hostIp" cssClass="inpt-2 vm"/>
				</td>
			</tr>
			
			<tr id="hostName" >
					<td class="til" >
						用户名：
					</td>
					<td>
						<s:textfield name="hostUnitedVO.username" id="username" cssClass="inpt-2 vm"/>
					</td>
				</tr>
				<tr id="hostPassword">
					<td class="til"  >
						密码：
					</td>
					<td>
						<s:password name="hostUnitedVO.password" id="hostUnitedVO.password" cssClass="inpt-2 "/>
					</td>
				</tr>
		</table>
	</s:form>
</body>
