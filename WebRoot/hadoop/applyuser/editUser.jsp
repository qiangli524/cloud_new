<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:saveInfo,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	
	function saveInfo(){ 
		var username = $("#userName").val();
		$("#namespan").empty();
		if (username.length == 0) {
			$("#namespan").append("<font color='red'>ERROR：请填写用户名</font>");
			return false;
		}
		
		var oper = $("#oper").val();
		var clusterId = "";
		var serviceArr = "";
		if (oper == "add") {
			clusterId = $("#clusterId").val();
			$("#clusterspan").empty();
			if (clusterId == "-1") {
				$("#clusterspan").append("<font color='red'>ERROR：请选择集群</font>");
				return false;
			}
			
			serviceArr = $("#serviceArr").val();
			$("#servicespan").empty();
			if (serviceArr.length == 0) {
				$("#servicespan").append("<font color='red'>ERROR：请选择服务</font>");
				return false;
			}
		} else {
			clusterId = $("#editCluster").val();
			serviceArr = $("#editService").val();
		}
		var flag = true;
		mask('正在检测用户是否存在，请稍后','0.7','0px');
		$.ajax({
			type:'post',
			url:'hadoopUser_checkIfExsit.do?hadoopUserObj.username='+username+'&hadoopUserObj.cluster_id='
					+clusterId+'&hadoopUserObj.serviceArr='+serviceArr,
			async:false,
			dataType:'json',
			success:function(msg){
				removeMask();
				if (msg.ret == -1) {
					flag = false;
					alert("该服务下该用户已经存在，不能重复添加，请取消该操作或者更改用户名称");
				}
			}
		});
		if (flag) {
			w.saveUser($("#theForm").serialize());
		}
	}
	
	$(function(){
		$("#selectService").click(function(){
			var clusterId = $("#clusterId").val();
			$("#clusterspan").empty();
			if (clusterId == -1) {
				$("#clusterspan").append("<font color='red'>ERROR：请选择集群</font>");
				return false;
			}
			w.$.dialog({
        		id:'selectService',
        		title:'选择服务',
        		width: '800px',
    			height: '500px',
    		    lock:true,
    		    content:'url:hadoopUserGroup_selectService.do?hadoopUserGroup.cluster_id='+clusterId
        	});
		});
	});
	
	function selectService (serviceType,serviceTypeStr) {
		$("#serviceType").val(serviceTypeStr);
		$("#serviceArr").val(serviceType);
	}
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
		<s:hidden name="oper" id="oper"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						用户名：<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="hadoopUserObj.username" id="userName"></s:textfield>
						<span id="namespan"></span>
					</td>
				</tr>
				<tr>
					<td class="til">
						所属集群：<font color="red">*</font>
					</td>
					<td>
						<s:if test="oper == 'add'">
							<s:select list="clusterList" headerKey="-1" headerValue="--请选择--" listKey="cluster_id" listValue="cluster_name"
								name="hadoopUserObj.cluster_id" id="clusterId"></s:select>
							<span id="clusterspan"></span>
						</s:if>
						<s:else>
							<s:hidden name="hadoopUserObj.cluster_id" id="editCluster"></s:hidden>
							<s:textfield name="hadoopUserObj.cluster_name" readonly="true" disabled="true"></s:textfield>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til">
						所属服务：<font color="red">*</font>
					</td>
					<td>
						<s:if test="oper == 'add'">
							<s:textarea name="hadoopUserObj.serviceTypeStr" id="serviceType" readonly="true" rows="4" cols="30"></s:textarea>
							<s:hidden name="hadoopUserObj.serviceArr" id="serviceArr"></s:hidden>
							<input type="button" class="thickbox btn-style02" value="选择" id="selectService"/><br/>
							<span id="servicespan"></span>
						</s:if>
						<s:else>
							<s:hidden name="hadoopUserObj.serviceArr" id="editService"></s:hidden>
							<s:if test="hadoopUserObj.serviceArr == 1">
						  		<s:textfield readonly="true" value="nameNode" disabled="true"></s:textfield>
							</s:if>
						  	<s:elseif test="hadoopUserObj.serviceArr == 2">
						  		<s:textfield readonly="true" value="dataNode" disabled="true"></s:textfield>
						  	</s:elseif>
						  	<s:elseif test="hadoopUserObj.serviceArr == 3">
						  		<s:textfield readonly="true" value="journalNode" disabled="true"></s:textfield>
						  	</s:elseif>
						  	<s:elseif test="hadoopUserObj.serviceArr == 4">
						  		<s:textfield readonly="true" value="nodeManager" disabled="true"></s:textfield>
						  	</s:elseif>
						  	<s:elseif test="hadoopUserObj.serviceArr == 5">
						  		<s:textfield readonly="true" value="resourceManager" disabled="true"></s:textfield>
						  	</s:elseif>
						  	<s:elseif test="hadoopUserObj.serviceArr == 6">
						  		<s:textfield readonly="true" value="hmaster" disabled="true"></s:textfield>
						  	</s:elseif>
						  	<s:elseif test="hadoopUserObj.serviceArr == 7">
						  		<s:textfield readonly="true" value="regionServer" disabled="true"></s:textfield>
						  	</s:elseif>
						  	<s:elseif test="hadoopUserObj.serviceArr == 8">
						  		<s:textfield readonly="true" value="hbase_thirftServer" disabled="true"></s:textfield>
						  	</s:elseif>
						  	<s:elseif test="hadoopUserObj.serviceArr == 9">
						  		<s:textfield readonly="true" value="znode" disabled="true"></s:textfield>
						  	</s:elseif>
						  	<s:elseif test="hadoopUserObj.serviceArr == 10">
						  		<s:textfield readonly="true" value="hive_thirftServer" disabled="true"></s:textfield>
						  	</s:elseif>
						  	<s:elseif test="hadoopUserObj.serviceArr == 11">
						  		<s:textfield readonly="true" value="impalaxx" disabled="true"></s:textfield>
						  	</s:elseif>
						  	<s:elseif test="hadoopUserObj.serviceArr == 12">
						  		<s:textfield readonly="true" value="DFSzkFailoverController" disabled="true"></s:textfield>
						  	</s:elseif>
						  	<s:else>
						  		<s:textfield readonly="true" value="--" disabled="true"></s:textfield>
						  	</s:else>
						</s:else>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>
</html:html>