<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:migrate,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	
	function migrate(){
		var hostUuid = $("#hostCode").val();
		if (hostUuid == "-1") {
			alert("请选择主机");
			return false;
		}
		
		var dataStoreCode = $("#dataStoreCode").val();
		if (dataStoreCode == "-1") {
			alert("请选择存储");
			return false;
		}
		
		var connectCode = $("#connectCode").val();
		var datacenterCode = $("#datacenterCode").val();
		var vtype = $("#vtype").val();
		var uuid = $("#uuid").val();
		w.exeMigrateVM(vtype,hostUuid,dataStoreCode,connectCode,uuid);
	}
	
	$(function(){
		$("#hostCode").change(function(){
			$("#dataStoreCode").empty();
			var hostUuid = $("#hostCode").val();
			var connectCode = $("#connectCode").val();
			var datacenterCode = $("#datacenterCode").val();
			if (hostUuid != "-1") {
				$.ajax({
					type:'post',
					dataType:'json',
					url:'united_selectDataStoreForMigrate.do?uuid='+hostUuid+'&connect_id='+connectCode + '&datacenterCode='+datacenterCode,
					async:false,
					success:function(msg){
						for ( var i = 0; i < msg.length; i++) {
							$("<option value="+msg[i].store_uuid+">"+msg[i].NAME+"</option>").appendTo("#dataStoreCode");
						}
						initDatastoreInfo();
					}
				});
			}
		});
		
		$("#dataStoreCode").change(initDatastoreInfo());
	});
	
	function initDatastoreInfo() {
		var hostUuid = $("#hostCode").val();
		var connectCode = $("#connectCode").val();
		var datacenterCode = $("#datacenterCode").val();
		var dataStoreCode = $("#dataStoreCode").val();
		$.ajax({
			type:'post',
			dataType:'json',
			url:'united_queryDataStoreInfo.do?uuid='+hostUuid+'&connect_id='+connectCode + '&datacenterCode='+datacenterCode + '&dataStoreCode='+dataStoreCode,
			async:false,
			success:function(msg){
				//alert(JSON.stringify(msg));
				$("#dsinfo").html('<span class="blue">总量：'+msg.capacity+'G<span class="mgl-10">余量：'+msg.purchaseSpace+'G</span>'+
						'<span class="mgl-10">置备量：'+msg.free_SPACE+'G</span></span></span>');
			}
		});
	}
	
</script>
</head>
<body>
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
		<s:hidden name="connect_id" id="connectCode"></s:hidden>
		<s:hidden name="datacenterCode" id="datacenterCode"></s:hidden>
		<s:hidden name="vtype" id="vtype"></s:hidden>
		<s:hidden name="uuid" id="uuid"></s:hidden>
		<s:if test="vtype==1">
			<div>
				<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
					<tr>
						<td class="til" align="left">
							选择主机：
						</td>
						<td>
							<s:select list="resultList" headerKey="-1" headerValue="--请选择--" listKey="uuid" listValue="name" id="hostCode" cssClass="select-1"></s:select>
						</td>
					</tr>
					<tr>
						<td class="til" align="left">
							选择存储：
						</td>
						<td>
							<select id="dataStoreCode" cssClass="select-1">
								<option value="-1">
									--请选择--
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="til" align="left">
							存储信息：
						</td>
						<td>
							<span id="dsinfo"></span>
						</td>
					</tr>
				</table>
			</div>
		</s:if>
		<s:elseif test="vtype == 2">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td align="left" colspan="2">
						<span id="text">迁移过程中虚拟机会一直保持在线状态,并且过程中会消耗大量的网络和IO!</span>
					</td>
				</tr>
				<tr id="migHost1" style="display:">
					<td class="til">
						选择迁移的目标主机：
					</td>
					<td>
						<s:select list="resultList" headerKey="-1" headerValue="请选择" listKey="h_uuid"
								 listValue="eq_name" id="hostCode"></s:select>
					</td>
				</tr>
			</table>
		</s:elseif>
	</s:form>
</body>