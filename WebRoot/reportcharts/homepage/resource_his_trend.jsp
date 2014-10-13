<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>历史趋势图</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/home/ui/nresources/css/home.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/tabs.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/table.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/colorbox.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/temp1/pub-ui/js/plugin/all-min.js">
</script>

<script type="text/javascript">
        $(document).ready(function(){
        	getChartsEntity();
        });
         //获取报表中实体列表
         function getChartsEntity() {
        	 showChart('cpu');
      		//插入报表数据源
         }
         
         function showChart(type){
        	 $("#chartContainer").insertFusionCharts({
                 swfUrl: "FusionCharts/Line.swf", 
                 dataSource: "homepagereport_resourceHisTrendData.do?type="+type, 
                 dataFormat: "jsonurl", 
                 width: "100%", 
                 height: "270"
            });
         }
         
</script>
</head>

<body>
	<div class="ued-tab tab-style" data-role="ued-tabs"
		data-rel="trigger:'click',activeIndex:0" id="choice">
		<div class="title-bg clearfix">
			<ul class="nav-tabs">
				<li class=""><a href="javascript:;" onclick="showChart('cpu')">
						CPU</a>
				</li>
				<li class=""><a href="javascript:;"
					onclick="showChart('memory')"> 内存</a>
				</li>
				<li class=""><a href="javascript:;"
					onclick="showChart('store')"> 存储</a>
				</li>
				<li class=""><a href="javascript:;" onclick="showChart('ip')">
						网络</a>
				</li>
			</ul>
		</div>
	</div>
	 <div id="chartContainer" ></div>

</body>
</html>
