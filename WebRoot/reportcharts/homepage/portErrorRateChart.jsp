<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
        <title>TopN</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
        <script type="text/javascript">
        $(document).ready(function(){
        	getChartsEntity();
        });
         //获取报表中实体列表
         function getChartsEntity() {
      		//插入报表数据源
          	  $("#chartContainer").insertFusionCharts({
	                 swfUrl: "FusionCharts/Column2D.swf", 
	                 dataSource: "homepagereport_PortErrorRateReportData.do", 
	                 dataFormat: "jsonurl", 
	                 width: "100%", 
	                 height: "270", 
	                 id: "PortErrorRateReport"
	           });
         }
        </script>
    </head>

    <body class="pop-body scrollbody" style="overflow-x:hidden">
        <div id="chartContainer">加载宿主机TopN</div>
    </body>
</html>
