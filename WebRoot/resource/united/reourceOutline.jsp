<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>

<script type="text/javascript">
		$(document).ready(function(){
			insertFusionChartData();
			
			$("#aaa").die().live("click",function(){
				$.dialog({
					id:'showDetail',
					title:'部门资源历史走势图',
					width:'1000px',
					height:'750px',
					max:true,
					min:true,
					content:'url:resourceOutline_showNewResourceDetail.do'
				});
			});	
		 });

		function insertFusionChartData(){
			 $("#compute").insertFusionCharts({
		         swfUrl: "FusionCharts/MSColumn2D.swf", 
		         dataSource: "resourceOutline_queryResource.do",
		         dataFormat: "jsonurl", 
		         width: "400", 
		         height: "270", 
		         id: "computeChart"
		   }); 
			$("#chartarea").insertFusionCharts({
	          	swfUrl: "FusionCharts/ScrollCombi2D.swf", 
	            dataSource: "resourceOutline_showDepartResourceTrend.do",
	            dataFormat: "jsonurl", 
	            width: "400", 
	            height: "270", 
	            id: "sdfsdfdfs"
	      }); 
	       $("#net").insertFusionCharts({
		         swfUrl: "FusionCharts/StackedColumn2D.swf", 
		         dataSource: "resourceOutline_showNetFusionChart.do",
		         dataFormat: "jsonurl", 
		         width: "400", 
		         height: "270", 
		         id: "netChart"
		   }); 
	       $("#hostVm").insertFusionCharts({
		         swfUrl: "FusionCharts/StackedColumn2D.swf", 
		         dataSource: "resourceOutline_showHostAndVMs.do",
		         dataFormat: "jsonurl", 
		         width: "400", 
		         height: "270", 
		         id: "hostVmChart"
		   }); 
	       $("#busi").insertFusionCharts({
	          	swfUrl: "FusionCharts/MSBar3D.swf", 
	            dataSource: "resourceOutline_getChartInfoSyncByCenter.do",
	            dataFormat: "jsonurl", 
	            width: "400", 
	            height: "270", 
	            id: "sdfsdfdfsdfs"
	      }); 
		}
</script>
</head>
<body class="pop-body scrollbody" >
	<div id="compute">资源</div>
	<div id="net"></div>
	<div id="hostVm"></div>
	<div id="busi"></div>
	<div><a href="javascript:;" name="resTrend" ><div id="chartarea">FusionCharts</div><div id="aaa">详细信息</div></a></div>
</body>
