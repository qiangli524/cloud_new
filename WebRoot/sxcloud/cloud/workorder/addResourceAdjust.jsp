<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../common/taglib.jsp"%>
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
		     callback:createResource,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	
	function createResource(){
		var entityId = $("#entityid").val();
		if (entityId.length == 0) {
			alert("请选择资源");
			return false;
		}
		
		var cpu = $("#cpu").val();
		if (cpu == 0 || cpu.length == 0) {
			alert("请填写cpu大小");
			return false;
		}
		var mem = $("#memsize").val();
		if (mem == 0 || mem.length == 0) {
			alert("请填写内存大小");
			return false;
		}
		var sr = $("#srsize").val();
		if (sr == 0 || sr.length == 0) {
			alert("请填写存储大小");
			return false;
		}
		
		var flag = false;
		mask('正在检测配置是否满足要求，请稍后....','0.7','0px');
		$.ajax({
			type:'post',
			url:'workorder/workorder_checkAdjust.do?workOrderObj.ENTITY_ID='+entityId+'&cpu='+cpu+'&memsize='+mem+'&srsize='+sr,
			dataType:'text',
			async:false,
			success:function(msg){
				removeMask();
				if (msg == 1) {
					flag = true;
				} else {
					alert(msg);
				}
			}
		});
		
		if (flag) {
			api.get("viewResource").saveResource($("#theForm").serialize());
		}
	}
		
	$(function(){
		var oper = $("#oper").val();
		var type = $("#type").val();
		$("#chooseEntity").click(function(){
			w.$.dialog({
				id:'selectEntiy',
	  			title:'选择资源',
	  			width: '1000px',
	  			height: '550px',
	  		    lock:true,
	  			content: 'url:workorder/workorder_selectRecoverResouce.do?type='+type+'&oper='+oper
			});
		});
	});
	
	function selectRecoverResouce(vhuuid,connectid,vmname,cpu,mem,sr,ip){
		$("#entityid").val(connectid+"_"+vhuuid);
		$("#entityname").val(vmname);
		$("#ipaddress").val(ip);
		$("#cpu").val(cpu);
		$("#memsize").val(mem/1024);
		$("#srsize").val(sr/1024);
	}
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssClass="pdt-10">
	<s:hidden name="oper" id="oper"></s:hidden>
	<s:hidden name="workOrderObj.ID" id="wrid"></s:hidden>
	<s:hidden name="type" id="type"></s:hidden>
	<s:hidden name="uuid" id="uuid"></s:hidden>
	<s:hidden name="workOrderObj.ENTITY_ID" id="entityid"></s:hidden>
		<div>
				<table width="100%" border="0" cellspacing="0" class="pop-table nosize" id="vmware_table">
					<tr>
						<td class="til">选择虚拟机<font color="red">*</font></td>
						<td>
							<s:textfield name="entityname" id="entityname" readonly="true" cssClass="inpt-2 vm"></s:textfield>
							<input type="button" class="ubtn-3 vm mgl-3" value="选择" id="chooseEntity"/>
						</td>
						<td class="til">IP地址</td>
						<td>
							<s:textfield name="workOrderObj.IPADDRESS" id="ipaddress" readonly="true" cssClass="inpt-2 vm"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="til">CPU个数<font color="red">*</font></td>
						<td>
							<s:textfield name="workOrderObj.CPU_NUM" id="cpu" cssClass="inpt-2 vm"></s:textfield>
						</td>
						<td class="til">内存大小<font color="red">*</font></td>
						<td>
							<s:textfield name="workOrderObj.MEM_SIZE" id="memsize" cssClass="inpt-2 vm"></s:textfield>G
						</td>
					</tr>
					<tr>
						<td class="til">存储大小<font color="red">*</font></td>
						<td>
							<s:textfield name="workOrderObj.SR_SIZE" id="srsize" cssClass="inpt-2 vm"></s:textfield>G
						</td>
					</tr>
				</table>
		</div>
	</s:form>
</body>