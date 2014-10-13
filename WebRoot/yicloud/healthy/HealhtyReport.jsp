<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<% 
	String cpuXML = (String)request.getAttribute("cpuXML");
	String memoryXML = (String)request.getAttribute("memoryXML");
	String storageXML = (String)request.getAttribute("storageXML");
	String netXML = (String)request.getAttribute("netXML"); 
%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />  
<meta http-equiv="expires" content="0" />
<head> 
	<script type="text/javascript" src="sxcloud/js/FusionCharts.js">    </script>  
</head>
<body>
<s:form action="" method="post" id="theHealthyForm" cssStyle="theForm">
<div style="height:440px;overflow:auto;width: 780px">
	<div id="chartContainer2" style= "float:left;margin: 10px 10px 8px 0;">FusionCharts will load here!</div> 
		<script type="text/javascript"><!--            
			var myChart = new FusionCharts( "charts/ZoomLine.swf",       
			"myChartId2","370", "250", "0", "1" );      
			myChart.setXMLData('<%=cpuXML%>');      
			myChart.render("chartContainer2");         
			// -->         
		</script>      
		<div id="chartContainer3" style= "float:left;margin:10px 0 8px 0;">FusionCharts will load here!</div> 
		<script type="text/javascript"><!--              
			var myChart = new FusionCharts( "charts/ZoomLine.swf",       
			"myChartId3", "370", "250", "0", "1" );      
			myChart.setXMLData('<%=memoryXML%>');      
			myChart.render("chartContainer3");         
			// -->         
		</script>  
		<br />
		<div id="chartContainer4" style= "clear:left;float:left;margin: 8px 10px 0 0; ">FusionCharts will load here!</div> 
		<script type="text/javascript"><!--              
			var myChart = new FusionCharts( "charts/ZoomLine.swf",       
			"myChartId4", "370", "250", "0", "1" );      
			myChart.setXMLData('<%=storageXML%>');      
			myChart.render("chartContainer4");         
			// -->         
		</script>
		<div id="chartContainer5" style= "float:left;margin:8px 0 0 0; ">FusionCharts will load here!</div> 
		<script type="text/javascript"><!--              
			var myChart = new FusionCharts( "charts/ZoomLine.swf",       
			"myChartId5", "370", "250", "0", "1" );      
			myChart.setXMLData('<%=netXML%>');      
			myChart.render("chartContainer5");         
			// -->         
		</script>  
		</div>	
</s:form>

</body>
