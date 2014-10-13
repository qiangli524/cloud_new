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
        var type = '';
        var topName = '';
        $(document).ready(function(){
            var fusioncharts = 'fusioncharts';
         
        	   getChartsEntity();
           
         });
         function getChartsEntity() {
         
              $("#tab tr:not(:first)").remove(); 
          
          	  $("#chartContainer").insertFusionCharts({
	                 swfUrl: "FusionCharts/Column2D.swf", 
	                 dataSource: "clusterKpi_showCpu.do" , 
	                 dataFormat: "jsonurl", 
	                 width: "100%", 
	                 height: "400", 
	                 id: "topnHostCharts"
	           });
      	 
         }
         
        </script>
    </head>

    <body class="pop-body scrollbody" style="overflow-x:hidden">
     <div id="chartContainer">TopN</div>
       
       
    
    </body>
</html>
