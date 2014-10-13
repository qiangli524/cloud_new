<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<title></title>
	<script type="text/javascript">
		var pool_uuid = '<%=request.getAttribute("pool_uuid") %>';
		var host_uuid = '<%=request.getAttribute("host_uuid") %>';
		var vm_uuid = '<%=request.getAttribute("vm_uuid") %>';
		var vifUuid = '<%=request.getAttribute("vifUuid") %>';
		
		var api = frameElement.api;
		var w = api.opener;
		
		api.button({
		    id:'Ok',
		    name: '确定',
		    callback:xenVirtualNic,
		    focus: true
		},
		{
		    id:'cancle',
		    name: '取消'
		});
		
		$(function(){
			var flag = theForm.flag.value;
			var mac = theForm.mac.value;
			if(flag==1){
				$("#createMacType[value='1']").attr("checked","checked");
				$("#mac").val(mac);
			}
		});
		
		function xenVirtualNic(){
			var flag = theForm.flag.value;
			var createMac = $("#createMacType:checked").val();
			var netUuid = $("#net").find("option:selected").val();
			var mac = $("#mac").val();
			if(flag == 0){
				var url = "xen_addXenVirtualNic.do?createMac="+createMac+"&netUuid="+netUuid+"&mac="+mac+"&pool_uuid="+pool_uuid+"&vm_uuid="+vm_uuid;
				w.addXenVirtualNic(url);
			}else {
				var url = "xen_adjustXenVirtualNic.do?createMac="+createMac+"&netUuid="+netUuid+"&mac="+mac+"&pool_uuid="+pool_uuid+"&vm_uuid="+vm_uuid+"&vifUuid="+vifUuid;
				w.adjustXenVirtualNic(url,vifUuid);
			}
		}
	</script>
	<style type="text/css" >
		.pop-table{border:1px solid #b5d4f3; margin:0 auto}
		.pop-table td{padding:3px 8px; font-size:12px;border-width: 0px 0px 0px 0px;}
	</style>
</head>
<body class="pop-body scrollbody" onload="pageOnLoad();">
	<s:form action="cluster_saveCluster.do" method="post" id="theForm" cssStyle="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<div>
		<table border="0" cellspacing="0" class="pop-table nosize"  style="width: 95%;margin-top: 2px" align="center">
			<tr>
				<td style="padding-top: 20px">
					为此虚拟机接口选择网络和MAC地址。
				</td>
			</tr>
			<tr>
				<td>
					<label>网络(N):</label>
					<s:select list="theForm.resultList" id="net"  name="theForm.networkUuid" listKey="networkUuid" listValue="networkName"
						cssStyle="width:265px; height:20px;margin-left: 15px"/>
				</td>
			</tr>
			<tr>
				<td>
					MAC地址：
				</td>
			</tr>
			<tr>
				<td>
					<input type="radio" id="createMacType" value="0" name="macAddress"  checked="checked" /><label>自动生成MAC地址(G)</label>
				</td>
			</tr>
			<tr style="padding-bottom: 50px">
				<td style="padding-bottom: 20px">
					<input type="radio" id="createMacType" value="1" name="macAddress"/>
					<label>使用此MAC地址(U):</label>
					<s:textfield name="theForm.mac" id="mac" cssClass="txt" cssStyle="width:200px;color:#aaa"></s:textfield>
				</td>
			</tr>
		</table>
	</div>
	</s:form>
</body>
