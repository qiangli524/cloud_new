<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
    <title>逻辑分区磁盘速率</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
    <style type="text/css">
		select {margin-top: 10px;margin-right: 10px;margin-bottom: 10px}
	</style>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	var uuid = $("#uuid").val();
	    	 $("#LparDiskSpeedChart").insertFusionCharts({
                 swfUrl: "FusionCharts/MSLine.swf", 
                 dataSource: "ibmtab_queryIBMIndex.do?uuid="+uuid+"&flag=LparDiskSpeed",
                 dataFormat: "jsonurl", 
                 width: "800", 
                 height: "400", 
                 id: "LparDiskChart"
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
  	<table>
  		<tr>
  			<td>
  				<div id="LparDiskSpeedChart">磁盘的读写速度</div>
  			</td>
  		</tr>
  	</table>
  </body> 
</html>