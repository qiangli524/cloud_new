<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page import="java.lang.String" %>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
  <head>
    
    <title>虚拟服务器报表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>sxcloud/cresources/default/css/report.tab.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="sxcloud/js/FusionCharts.js">    </script> 
	<script type="text/javascript">
	 <%
	String cpuXML = (String)request.getAttribute("cpuXML");
	String memoryXML = (String)request.getAttribute("memoryXML");
	String storageXML = (String)request.getAttribute("storageXML");
	String netXML = (String)request.getAttribute("netXML");
	String hy_id = (String)request.getAttribute("hy_id");
	String tag = (String)request.getAttribute("tag");
%>
	</script>

  </head>
  <script type="text/javascript">
   function findCurrentChart()
	{
	    document.theForm.action = "reportvir_getCurrentReportView.do?hy_id=<%=hy_id%>&tag=<%=tag%>" ;
	    document.theForm.submit();
	}
	
	function findWeekChart()
	{
	    document.theForm.action = "reportvir_getWeekReportView.do?hy_id=<%=hy_id%>&tag=<%=tag%>";
	    document.theForm.submit();
	}
	
	function findMonthChart()
	{
	    document.theForm.action = "reportvir_getMonthReportView.do?hy_id=<%=hy_id%>&tag=<%=tag%>";
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
<div style=" float: left"><%=tag %></div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="tab_bg">
			<tr class="monitor_bg1">
				<td width="300" class="tab_grey tabfocus">
					<a href="javascript:findCurrentChart();">当前统计</a>
				</td>
				<td width="300" class="tab_current tabnofocus">
					<a href="javascript:findWeekChart();" >本周统计</a>
				</td>
				<td width="300" class="tab_current tabnofocus">
					<a href="javascript:findMonthChart();" >本月统计</a>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</div>
	<s:form action="reportvir_getCurrentReportView.do" method="post" cssClass="theForm" id="theForm">
		<div id="chartContainer2" style= "float:left;margin-right: 10px; margin-bottom: 20px;">FusionCharts will load here!</div> 
		<script type="text/javascript"><!--   
            var myChart = new FusionCharts( "charts/Line.swf",       
			"myChartId2", "400", "300", "0", "1" );    
			myChart.setXMLData('<%=cpuXML%>');      
			myChart.render("chartContainer2");       
			// -->         
		</script>    
		<div id="chartContainer3" style= "float:left;margin-left: 10px; margin-bottom: 20px;">FusionCharts will load here!</div> 
		<script type="text/javascript"><!--   
			var myChart = new FusionCharts( "charts/Line.swf",       
			"myChartId3", "400", "300", "0", "1" );    
			myChart.setXMLData('<%=memoryXML%>');      
			myChart.render("chartContainer3");         
			// -->         
		</script>  
		<br>
		<div id="chartContainer4" style= "clear:left;float:left;margin-right: 10px;">FusionCharts will load here!</div> 
		<script type="text/javascript"><!--              
			var myChart = new FusionCharts( "charts/Line.swf",       
			"myChartId4", "400", "300", "0", "1" );      
			myChart.setXMLData('<%=storageXML%>');      
			myChart.render("chartContainer4");         
			// -->         
		</script>
		<div id="chartContainer5" style= "float:left;margin-left: 10px">FusionCharts will load here!</div> 
		<script type="text/javascript"><!--              
			var myChart = new FusionCharts( "charts/Area2D.swf",       
			"myChartId5", "400", "300", "0", "1" );      
			myChart.setXMLData('<%=netXML%>');      
			myChart.render("chartContainer5");         
			// -->         
		</script> 
	</s:form>
  </body>
