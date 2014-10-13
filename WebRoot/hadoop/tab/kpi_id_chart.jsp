<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
    <title>My First chart using FusionCharts XT</title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
    <script type="text/javascript">
    	$(function(){
    		systemLoad();
    	});
	  //系统负载
    	function systemLoad(){
		   var kpiUuid=$("#kpiUuid").val();
		   var hostName=$("#hostName").val();
		   var clusterName=$("#clusterName").val();
    		$("#systemLoad").insertFusionCharts({
                 swfUrl: "FusionCharts/ScrollCombi2D.swf", 
                 dataSource: "hadoopHostServer_hadoopAlertMonitor.do?kpiUuid="+kpiUuid+"&hostName="+hostName+"&clusterName="+clusterName,
                 dataFormat: "jsonurl", 
                 width: "780", 
                 height: "460", 
                 id: "sdfsfds"
           }); 
    	};
    </script>  
  </head>   
  <body> 
  <s:form action="" id="theForm">
  <s:hidden name="hostName" id="hostName"></s:hidden>
  <s:hidden name="clusterName" id="clusterName"></s:hidden>
  <s:hidden name="kpiId" id="kpiId"></s:hidden>
  <s:hidden name="kpiUuid" id="kpiUuid"></s:hidden>
  <div class="scrollbody" style="text-align: center;">
  		<div id="systemLoad" ></div>
  	</div>
  	</s:form>
  </body> 
</html>