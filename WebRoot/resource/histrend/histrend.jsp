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
	  insertReportsData();
	  
	  $("#queryReports").click(function(){
		  	var resourceType = $("#resourceType").val();
			var startTime = $("#starttime").val();
			var endTime = $("#endtime").val();
			if (endTime.length != 0) {
				if (startTime.length == 0) {
					alert("请选择起始时间！");
					return false;
				}
			}
			updateReportsData(resourceType,startTime,endTime);
	  });
  });

  function insertReportsData(){
	  var d = new Date();
	  $("#chartarea").insertFusionCharts({
		  swfUrl:'FusionCharts/ScrollCombi2D.swf',
		  dataSource:'resourcehis_queryResourceHisReports.do?d='+d,
		  dataFormat:'jsonurl',
		  width:'800',
		  height:'500',
		  id:'resourceReports'
	  });
  }
  
  function updateReportsData(resourceType,startTime,endTime){
	  var d = new Date();
	  $("#chartarea").updateFusionCharts({
		  dataSource:'resourcehis_queryResourceHisReports.do?resourceType='+resourceType+'&startTime='+startTime+'&endTime='+endTime+'&d='+d
	  });
  }
</script>
</head>

<body>
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
	<div class="pd-20 bgcolor-1">
		<h2 class="utt-1">资源历史走势</h2>
		<table width="95%" bgcolor="F0F8FF">
			<tr>
				<td align="center">
					资源类型：<s:select cssClass="select-1" list="#{'1':'网络','2':'cpu','3':'内存','4':'存储','5':'主机和虚拟机'}" id="resourceType"></s:select>&nbsp;&nbsp;&nbsp;&nbsp;
					时间段：从&nbsp;
					<input id="starttime" style="txt" type="text" name="startTime" size="20" class="Wdate"
					onFocus="WdatePicker({minDate:'1970-10-01',maxDate:'#F{$dp.$D(\'endtime\')||\'new Date()\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;&nbsp;
					到&nbsp;&nbsp;<input id="endtime" style="txt" type="text" name="endTime" size="20" class="Wdate"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',maxDate:'new Date()',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" id="queryReports" /></span>
				</td>
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