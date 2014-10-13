<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<style type="text/css">
</style>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript">
	$(function(){
		var deviceId = $("#deviceId").val();
		var d = new Date();
		$.ajax({
			type:'get',
			dataType:'text',
			url:'resource_showHostDevicePie.do?deviceId='+deviceId+'&d='+d+'&type=1',
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/MyPie3D.swf","ChartId1","280","250","0","0");
				chart1.setJSONData(msg);
				chart1.render("chartarea1");
			}
		});
		$.ajax({
			type:'get',
			dataType:'text',
			url:'resource_showHostDevicePie.do?deviceId='+deviceId+'&d='+d+'&type=2',
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/MyPie3D.swf","ChartId2","280","250","0","0");
				chart1.setJSONData(msg);
				chart1.render("chartarea2");
			}
		});
		$.ajax({
			type:'get',
			dataType:'text',
			url:'resource_showHostDevicePie.do?deviceId='+deviceId+'&d='+d+'&type=3',
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/MyPie3D.swf","ChartId3","280","250","0","0");
				chart1.setJSONData(msg);
				chart1.render("chartarea3");
			}
		});
	});
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" id="storeDeviceObj">
<s:hidden id="deviceId" name="deviceId"></s:hidden>
<div class="scrollbody">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="20%" align="left">
						存储设备名称
					</td>
					<td align="left" width="30%">
						<s:property value="storeDeviceObj.name"/>
					</td>
					<td class="til" align="left" width="20%">
						CACHE容量(G)
					</td>
					<td align="left" width="30%">
					   <fmt:formatNumber maxFractionDigits="2" value="${storeDeviceObj.cache_space}"/>
					</td>
				</tr> 
				<tr>
					<td class="til" width="20%" align="left">
						RAID方式
					</td>
					<td align="left">
						<s:property value="storeDeviceObj.raid_way"/>
					</td>
					<td class="til" align="left">
						厂商
					</td>
					<td align="left">
						<s:property value="storeDeviceObj.supporter"/>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						磁盘裸容量(T)
					</td>
					<td align="left">
					     <fmt:formatNumber maxFractionDigits="2" value="${storeDeviceObj.capacity/1024}"/>
					</td>
					<td class="til" align="left">
						磁盘有效容量(T)
					</td>
					<td align="left">
					    <fmt:formatNumber maxFractionDigits="2" value="${storeDeviceObj.valid_space/1024}"/>		
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						端口数量
					</td>
					<td align="left">
					 <s:property value="storeDeviceObj.port_num"/>
					</td>
					<td class="til" align="left">
						磁盘已使用量(G)
					</td>
					<td align="left">
						<fmt:formatNumber maxFractionDigits="2" value="${(storeDeviceObj.valid_space-storeDeviceObj.storeBlockFreeSpace)/1024}"/>					 
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						磁盘使用率(已用量/有效容量)
					</td>
					<td align="left">
					 <fmt:formatNumber groupingUsed="false" maxFractionDigits="2" value="${((storeDeviceObj.valid_space-storeDeviceObj.storeBlockFreeSpace)*100/storeDeviceObj.valid_space)}"/>%
					</td>
					<td class="til" align="left">
						已挂载存储容量(T)
					</td>
					<td align="left">
					 <fmt:formatNumber maxFractionDigits="2" value="${storeDeviceObj.storeBlockTotalSize/1024 }"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						已挂载存储置备空间(T)
					</td>
					<td align="left">
					  <fmt:formatNumber maxFractionDigits="3" value="${storeDeviceObj.purchaseSpace/1024}"/>
					</td>
					<td class="til" align="left">
						已挂载存储使用率(挂载使用量/挂在容量)
					</td>
					<td align="left">
					 <fmt:formatNumber groupingUsed="false" maxFractionDigits="2" value="${((storeDeviceObj.storeBlockTotalSize-storeDeviceObj.storeBlockFreeSpace)*100/storeDeviceObj.storeBlockTotalSize)}"/>%
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div align="center">
		<table>
			<tr>
					<td>
						<div id="chartarea1" align="center">
							FusionCharts.
						</div>
					</td>
					<td>
						<div id="chartarea2" align="center">
							FusionCharts.
						</div>
					</td>
					<td>
						<div id="chartarea3" align="center">
							FusionCharts.
						</div>
					</td>
			</tr>
		</table>
	</div>
</s:form>
</body>
