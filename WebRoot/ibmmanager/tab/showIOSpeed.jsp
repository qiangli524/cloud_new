<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
    <title>存储监控指标</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
    <style type="text/css">
		select {margin-top: 10px;margin-right: 10px;margin-bottom: 10px}
	</style>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	var uuid = $("#uuid").val();
	    	var type = $("#type").val();
	    	var kpi = $("#kpi").val();
	    	 $("#IOSpeedChart").insertFusionCharts({
                 swfUrl: "FusionCharts/Line.swf", 
                 dataSource: "ibmtab_queryIOSpeed.do?uuid="+uuid+"&type="+type+"&kpi="+kpi,
                 dataFormat: "jsonurl", 
                 width: "800", 
                 height: "400", 
                 id: "IOChart"
           }); 
	     });
    </script>  
  </head>   
  <body> 
  	<s:hidden name="id"></s:hidden>
	<s:hidden name="name"></s:hidden>
	<s:hidden name="type"></s:hidden>
	<s:hidden name="vtype"></s:hidden>
	<s:hidden name="parent_id"></s:hidden>
	<s:hidden name="uuid"></s:hidden>
	<s:hidden name="kpi"></s:hidden>
  	<table>
  		<tr>
  			<td>
  				<div id="IOSpeedChart">I/O</div>
  			</td>
  		</tr>
  	</table>
  </body> 
</html>