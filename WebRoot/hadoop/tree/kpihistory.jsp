<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
<title></title>
<script type="text/javascript">
	$(function(){
		var uuid = $("#uuid").val();
		var kpiId = $("#kpiId").val();
		var refer = $("#refer").val();
		var hostName = $("#hostName").val();
		insertFusionChartData(uuid,kpiId,refer,hostName);
		
		$("#queryReports").click(function(){
			var startTime = $("#starttime").val();
			var endTime = $("#endtime").val();
			if (endTime.length != 0) {
				if (startTime.length == 0) {
					alert("请选择起始时间！");
					return false;
				}
			}
			if (startTime > endTime) {
				alert("起始时间大于截止时间！");
				return false;
			}
			updateFusionChartData(startTime,endTime,kpiId,uuid,refer,hostName);
		});
	});

	function insertFusionChartData(uuid,kpiId,refer,hostName){
		if (refer == "topN") {//从topN列表指向而来
			$("#chartarea").insertFusionCharts({
	          	swfUrl: "FusionCharts/Line.swf", 
	            dataSource: "hadoopmonitor_viewHisData.do?obj.id="+uuid+"&obj.kpiId="+kpiId+"&refer="+refer+"&hadoopMonitorObj.host_name="+hostName,
	            dataFormat: "jsonurl", 
	            width: "500", 
	            height: "400", 
	            id: "dayChart"
	     	}); 
		} else {
			$("#chartarea").insertFusionCharts({
	          	swfUrl: "FusionCharts/Line.swf", 
	            dataSource: "hadoopmonitor_viewHisData.do?obj.id="+uuid+"&obj.kpiId="+kpiId,
	            dataFormat: "jsonurl", 
	            width: "500", 
	            height: "400", 
	            id: "dayChart"
	      	}); 
		}
		
	}
	
	function updateFusionChartData(startTime,endTime,kpiId,uuid,refer,hostName){
		if (refer == "topN") {
			$("#chartarea").updateFusionCharts({
	            dataSource: "hadoopmonitor_viewHisData.do?obj.startTime="+startTime+"&obj.endTime="+endTime+"&obj.id="+uuid+"&obj.kpiId="+kpiId+"&refer="+refer+"&hadoopMonitorObj.host_name="+hostName
	     	});
		} else {
			$("#chartarea").updateFusionCharts({
	            dataSource: "hadoopmonitor_viewHisData.do?obj.startTime="+startTime+"&obj.endTime="+endTime+"&obj.id="+uuid+"&obj.kpiId="+kpiId
	     	}); 
		}
	}
	
</script>
</head>

<body>
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
	<s:hidden name="obj.id" id="uuid"></s:hidden>
	<s:hidden name="obj.kpiId" id="kpiId"></s:hidden>
	<s:hidden name="refer" id="refer"></s:hidden>
	<s:hidden name="hadoopMonitorObj.host_name" id="hostName"></s:hidden>
	<div>
		<table width="95%" bgcolor="F0F8FF">
			<tr>
				<td align="center">
					时间段：从&nbsp;
					<input id="starttime" style="txt" type="text" name="startTime" size="20" class="Wdate"
					onFocus="WdatePicker({minDate:'1970-10-01',maxDate:'#F{$dp.$D(\'endtime\')||\'new Date()\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;&nbsp;
					到&nbsp;&nbsp;<input id="endtime" style="txt" type="text" name="endTime" size="20" class="Wdate"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',maxDate:'new Date()',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type = "button" class="thickbox btn-style02" value = "查询" id="queryReports" />
				</td>
			</tr>
			<tr>
				<td height="10px;"></td>
			</tr>
			<tr>
				<td width="98%">
					<div id="chartarea" align="center">
						FusionCharts.
					</div>
				</td>
			</tr>
		</table>
	</div>
	</s:form>
</body>