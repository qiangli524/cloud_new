<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
<title></title>
<script type="text/javascript">
	$(function(){
		insertFusionChartData();
		
		$("#queryReports").click(function(){
			var resourceType = $("#changeType").val();
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
			updateFusionChartData(resourceType,startTime,endTime);
		});
	});

	function insertFusionChartData(){
		$("#chartarea").insertFusionCharts({
	          	swfUrl: "FusionCharts/ScrollCombi2D.swf", 
	            dataSource: "departresource_showDepartResourceTrend.do",
	            dataFormat: "jsonurl", 
	            width: "750", 
	            height: "400", 
	            id: "dayChart"
	      }); 
	}
	
	function updateFusionChartData(resourceType,startTime,endTime){
		$("#chartarea").updateFusionCharts({
            dataSource: "departresource_showDepartResourceTrend.do?resourceType="+resourceType+"&startTime="+startTime+"&endTime="+endTime
     	}); 
	}
	
</script>
</head>

<body>
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
	<div>
		<table width="95%">
			<tr>
				<td align="center">
					资源类型：<s:select list="#{'1':'CPU', '2':'内存','3':'存储','4':'IP'}" id="changeType"></s:select>&nbsp;&nbsp;&nbsp;&nbsp;
					时间段：从&nbsp;
					<input id="starttime" style="txt" type="text" name="startTime" size="20" class="Wdate"
					onFocus="WdatePicker({minDate:'1970-10-01',maxDate:'#F{$dp.$D(\'endtime\')||\'new Date()\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;&nbsp;
					到&nbsp;&nbsp;<input id="endtime" style="txt" type="text" name="endTime" size="20" class="Wdate"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',maxDate:'new Date()',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type = "button" class="thickbox btn-style02" value = "查询" id="queryReports" />
				</td>
			</tr>
			<tr>
				<td width="98%">
					<div id="chartarea" align="center">
					</div>
				</td>
			</tr>
		</table>
	</div>
	</s:form>
</body>