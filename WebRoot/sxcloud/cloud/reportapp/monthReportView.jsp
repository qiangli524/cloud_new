<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
  <head>
    
    <title>物理服务器报表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="sxcloud/js/FusionCharts.js">    </script> 
<%
	String cpuXML = (String)request.getAttribute("cpuXML");
	String memXML = (String)request.getAttribute("memXML");
	String app_id = (String)request.getAttribute("app_id");
%>
  </head>
  
    <script type="text/javascript">
  function findCurrentChart()
	{
	    document.theForm.action = "reapp_getAppCurrentReportView.do?app_id=" + "<%=app_id%>";
	    document.theForm.submit();
	}
	
	function findWeekChart()
	{
	    document.theForm.action = "reapp_getAppWeekReportView.do?app_id=" + "<%=app_id%>";
	    document.theForm.submit();
	}
	
	function findMonthChart()
	{
	    document.theForm.action = "reapp_getAppMonthReportView.do?app_id=" + "<%=app_id%>";
	    document.theForm.submit();
	}
  </script>
  
  <body> 
   <div >
<%--   <ul >    --%>
<%--        <li><a class="selected" href="#" onclick="findCurrentChart();return false;">当前统计</a></li>    --%>
<%--        <li><a href="#" onclick="findWeekChart();return false;" >本周统计</a></li>    --%>
<%--        <li><a href="#" onclick="findMonthChart();return false;" >本月统计</a></li>    --%>
<%--    </ul>--%>
<div style=" float: left"></div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="tab_bg">
			<tr class="monitor_bg1">
				<td width="300" class="tab_grey">
					<a href="javascript:findCurrentChart();">当前统计</a>
				</td>
				<td width="300" class="tab_current">
					<a href="javascript:findWeekChart();" >本周统计</a>
				</td>
				<td width="300" class="tab_current">
					<a href="javascript:findMonthChart();" >本月统计</a>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</div>
	<s:form action="reapp_getHostWeekReportView" method="post" cssClass="theForm" id="theForm">
		<div id="chartContainer2" style= "float:left;margin-right: 10px; margin-bottom: 20px;">FusionCharts will load here!</div> 
		<script type="text/javascript"><!--              
			var myChart = new FusionCharts( "charts/MSLine.swf",       
			"myChartId2", "400", "300", "0", "1" );      
			myChart.setXMLData('<%=cpuXML%>');      
			myChart.render("chartContainer2");         
			// -->         
		</script>      
		<div id="chartContainer3" style= "float:left;margin-left: 10px; margin-bottom: 20px;">FusionCharts will load here!</div> 
		<script type="text/javascript"><!--              
			var myChart = new FusionCharts( "charts/MSLine.swf",       
			"myChartId3", "400", "300", "0", "1" );      
			myChart.setXMLData('<%=memXML%>');      
			myChart.render("chartContainer3");         
			// -->         
		</script>  
		</s:form>
  </body>
