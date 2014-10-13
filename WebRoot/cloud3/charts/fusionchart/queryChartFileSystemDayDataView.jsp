<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
    <title>My First chart using FusionCharts XT</title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<%--
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
     --%>
      <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
    	var hyId = '<s:property value="hyId"/>';
	    $(document).ready(function(){
	    	insertFusionChartData();
	     });
	    
	    function updateFusionChartData(){
	    	mask('正在加载数据,请稍后...','0.5','0px');
	    	var startDate=$("#startDateId").val();
	    	var endDate=$("#endDateId").val();
	    	$("#dayChartContainer").updateFusionCharts({
               dataSource: "hyMonitor_queryChartFileSystemDayData.do?startDate="+startDate+"&endDate="+endDate
               				+"&hyId="+hyId
            }); 
	    	removeMask();
	    }
	    
    	function insertFusionChartData(){
    		var startDate=$("#startDateId").val();
	    	var endDate=$("#endDateId").val();
    		 $("#dayChartContainer").insertFusionCharts({
                 swfUrl: "FusionCharts/ZoomLine.swf", 
                 dataSource: "hyMonitor_queryChartFileSystemDayData.do?startDate="+startDate+"&endDate="+endDate
    				+"&hyId="+hyId,
                 dataFormat: "jsonurl", 
                 width: "730", 
                 height: "350", 
                 id: "dayChart"
           }); 
    	}
    	
    </script>  
  </head>   
  <body> 
  <div  class="scrollbody">
		<table width="100%" class="querytable" border="0">
			<tr >
				 <td style="padding-right: 25px;text-align: right;">
			  		时间：
			  		<input id="startDateId" style="txt" type="text" name="startDate" size="20"  class="Wdate"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						   		value="${ startDate}"/>
						   		~
					<input id="endDateId" style="txt" type="text" name="endDate" size="20"  class="Wdate"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						   		value="${ endDate}"/>
					<input type="button"  class="ubtn-3 vm mgl-3" value="查询"
										onclick="javascript:updateFusionChartData()" />
				</td>
			</tr>
  		<tr>
  			<td style="text-align: center;width: 10px;position:relative;top:5px;" colspan="10">
  				<div id="dayChartContainer"></div>
  			</td>
  		</tr>
  	</table>
  	</div>
  </body> 
</html>