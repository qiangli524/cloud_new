<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
    <title>My First chart using FusionCharts XT</title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />	
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
    </script>  
  </head>   
  <body> 
  <div class="scrollbody">
	<iframe width="100%" height="400px;" src="paasHostMonitor_showHostMonitorData_f.do?id=<s:property value='id' />&kpi=<s:property value='kpi'/>&motionName=<s:property value='motionName'/>&eq_type=<s:property value='eq_type'/>" id="cpuAndMem" name="cpuAndMem" frameborder="0" >Load Failed</iframe>
  </div>
  </body> 
</html>