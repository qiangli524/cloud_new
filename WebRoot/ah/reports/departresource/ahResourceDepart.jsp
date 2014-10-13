<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
		.font-more{ width:180px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}
　a.redlink a:link, 
a.redlink a:visiteid { 
color:#FF0000; 
text-decoration:none; 
} 
a.redlink a:hover, 
a.redlink a:active { 
color:#000000; 
text-decoration:underline; 
background:#FFFFFF; 
} 
</style>
<!-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/all.css"> -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/tabs.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/table.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/colorbox.css">
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/temp1/pub-ui/js/plugin/all-min.js">
</script>

<script type="text/javascript">
	$(function(){
		var resourceType = $("#resourceType").val();
		if(resourceType=="3"){
			insertFusionChartDataBySr();
		    $("#choice").hide();
		}else{
			$("#choice").show();
		    insertFusionChartData();
		}
		
		$("#more").click(function(){
			$.dialog({
				id:'showDetail',
				title:'部门资源预算',
				width:'1200px',
				height:'800px',
				lock:true,
				zIndex:'1975',
				content:'url:section_listSection.do'
			});
		});
	})
	
	function insertFusionChartData(){
		var d = new Date();
		$.ajax({
			type:'get',
			dataType:'text',
			url:"resourceOutline_showDepartResourceTrend.do?aaa="+d,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/ScrollCombi2D.swf","ChartId1","100%","280px","0","0");
				chart1.setJSONData(msg);
				chart1.render("chartareasss");
			}
		});
	}
	
	function insertFusionChartDataBySr(){
		var d = new Date();
		$.ajax({
			type:'get',
			dataType:'text',
			url:"resourceOutline_showDepartResourceTrend.do?resourceType=3&aaa="+d,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/ScrollCombi2D.swf","ChartId2","100%","280px","0","0");
				chart1.setJSONData(msg);
				chart1.render("chartareasss");
			}
		});
	}
	
	function showCpuN(){
		var d = new Date();
		$.ajax({
			type:'get',
			dataType:'text',
			url:"resourceOutline_showDepartResourceTrend.do?resourceType=1&aaa="+d,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/ScrollCombi2D.swf","ChartI333","100%","280px","0","0");
				chart1.setJSONData(msg);
				chart1.render("chartareasss");
			}
		});
	}
	
	function showMemN(){
		var d = new Date();
		$.ajax({
			type:'get',
			dataType:'text',
			url:"resourceOutline_showDepartResourceTrend.do?resourceType=2&aaa="+d,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/ScrollCombi2D.swf","ChartI4444","100%","280px","0","0");
				chart1.setJSONData(msg);
				chart1.render("chartareasss");
			}
		});
	}
</script>
</head>
<body>

<s:form action="" method="post"  id="theForm">
	<s:hidden name="resourceType" id="resourceType"></s:hidden>
<table border="0" cellspacing="0" cellpadding="0" width="100%" >
	<tr>
	<td>
     <div class="ued-tab tab-style" data-role="ued-tabs" data-rel="trigger:'click',activeIndex:0" id="choice">
         <div class="title-bg clearfix">
             <ul class="nav-tabs">
                 <li class="">
                     <a href="javascript:;" onclick="showCpuN()">
                         CPU维度
                     </a>
                 </li>
                 <li class="">
                     <a href="javascript:;" onclick="showMemN()">
                                                          内存维度
                     </a>
                 </li>
             </ul>
         </div>
     </div>
	</td>
	</tr>
	<tr>
		<td align="center">
			<div id="chartareasss" align="center"></div></td>
	</tr>
	<tr>
		<td align="right"><a href="javascript:;" style="color: blue;font-size: 12px" id="more">更多预算信息</a>
		</td>
	</tr>
		</table>
</s:form>
</body>
