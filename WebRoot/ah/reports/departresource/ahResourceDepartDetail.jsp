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

<link href="<%=request.getContextPath()%>/sxcloud/images/nresources/ziyuan-css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<script type="text/javascript">
	$(function(){
		insertFusionChartData();

		$("#aaa").die().live("click",function(){
			$.dialog({
				id:'showDetail',
				title:'部门资源历史走势图',
				width:'1000px',
				height:'750px',
				max:true,
				min:true,
				lock:true,
				content:'url:departresource_showDetail.do'
			});
		});	
	})
	
	function insertFusionChartData(){
		$("#cpu_chart").insertFusionCharts({
          	swfUrl: "FusionCharts/ScrollCombi2D.swf", 
            dataSource: "resourceOutline_showDepartResourceTrendCpu.do",
            dataFormat: "jsonurl", 
            width: "400", 
            height: "270", 
            id: "sdf23dfs"
      }); 
		$("#mem_chart").insertFusionCharts({
          	swfUrl: "FusionCharts/ScrollCombi2D.swf", 
            dataSource: "resourceOutline_showDepartResourceTrendMem.do",
            dataFormat: "jsonurl", 
            width: "400", 
            height: "270", 
            id: "sdfs435fs"
      }); 
		$("#net_chart").insertFusionCharts({
          	swfUrl: "FusionCharts/ScrollCombi2D.swf", 
            dataSource: "resourceOutline_showDepartResourceTrendNet.do",
            dataFormat: "jsonurl", 
            width: "400", 
            height: "270", 
            id: "sdfs2323fs"
      }); 
		$("#sr_chart").insertFusionCharts({
          	swfUrl: "FusionCharts/ScrollCombi2D.swf", 
            dataSource: "resourceOutline_showDepartResourceTrendSr.do",
            dataFormat: "jsonurl", 
            width: "400", 
            height: "270", 
            id: "5656sdsd"
      }); 
	}
</script>
</head>
<body>

<s:form action="" method="post"  id="theForm">
<div class="scrollbody" >
	<table width="100%"  border="0" >
	        <tr>
 			<td colspan="2" height="20px"></td>
 			</tr>
 			<tr>
  			<td style="text-align:center;">
  				<div id="systemLoad"></div>
  			</td>
  			<td style="text-align:center;">
  				<div id="systemCpu"></div>
  			</td>
 			</tr>
 			<tr>
 			<td colspan="2" height="20px"></td>
 			</tr>
 			<tr>
  			<td style="text-align:center;">
  				<div id="systemMemory"></div>
  			</td>
  			<td style="text-align:center;">
  				<div id="systemNetwork"></div>
  			</td>
 			</tr>
 			<tr>
 			<td colspan="2" height="20px"></td>
 			</tr>
 			<tr>
  			<td style="text-align:center;">
  				<div id="systemDFS"></div>
  			</td>
  			<td style="text-align:center;">
<%--  				<div id="systemNetwork"></div>--%>
  			</td>
 			</tr>
 	 </table>
</div>
</s:form>
</body>
