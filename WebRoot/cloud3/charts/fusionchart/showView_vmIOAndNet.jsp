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
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
     <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	insertFusionChartData();
	     });
	     
	     var hyId = '<s:property value="hyId"/>';
	    var kpi = '<s:property value="kpi"/>';
	    var motionName = '<s:property value="motionName"/>';
	    function updateFusionChartData(){
	    	var cycle_time=$("#time option:selected").val();
	    	var pointCount=$("#pointCount option:selected").val();
	    	var startDate=$("#startDateId").val();
	    	var endDate=$("#endDateId").val();
	    	var url="hyMonitor_queryHyDNData_fusion.do?cycle_time="+cycle_time+"&startDate="+startDate+"&endDate="+endDate+"&pointCount="+pointCount
 			 +"&hyId="+hyId+"&kpi="+kpi+"&motionName="+motionName;
	    	$("#dayChartContainer").insertFusionCharts({
                 swfUrl: "FusionCharts/ZoomLine.swf", 
                 dataSource: url,
                 dataFormat: "jsonurl", 
                 width: "730", 
                 height: "350", 
                 id: "dayChart12"
           }); 
	    }
	    
    	function insertFusionChartData(){
    		var flag=$("#flag").val();
 	        if(flag=='report'){
 	        	//此方式只会在加载的时候执行一次，
 	        	$("#time").children("option:last").attr("selected",true);
 	        	var cycle_time=$("#time option:selected").val();
	    	    var pointCount=$("#pointCount option:selected").val();
 	        	$("#startDateId").val($("#startDate").val());
 	        	$("#endDateId").val($("#endDate").val());
 	        	var startDate=$("#startDateId").val();
 		    	var endDate=$("#endDateId").val();
 		    	var url="hyMonitor_queryHyDNData_fusion.do?cycle_time="+cycle_time+"&startDate="+startDate+"&endDate="+endDate+"&pointCount="+pointCount
				+"&hyId="+hyId+"&kpi="+kpi+"&motionName="+motionName;
 		    	$("#dayChartContainer").insertFusionCharts({
 	                 swfUrl: "FusionCharts/ZoomLine.swf", 
 	                 dataSource: url,
 	                 dataFormat: "jsonurl", 
 	                 width: "730", 
 	                 height: "350", 
 	                 id: "dayChart"
 	             }); 
 	        }else{
	    		 $("#dayChartContainer").insertFusionCharts({
	                 swfUrl: "FusionCharts/ZoomLine.swf", 
	                 dataSource: "hyMonitor_queryHyDNData_fusion.do?cycle_time=oneHour"+"&hyId="+hyId+"&kpi="+kpi+"&motionName="+motionName,
	                 dataFormat: "jsonurl", 
	                 width: "730", 
	                 height: "350", 
	                 id: "dayChart"
	           }); 
 	        }
    	}
    	
        function chanageRq(obj){
        	$("#dateBoxId").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId").show();
        	}
        }
        
        $(function(){
	        var flag=$("#flag").val();
	        if(flag=='report'){
	        	$("#time").children("option:last").attr("selected",true);
	        	$("#time").attr("disabled",true);
	        	$("#dateBoxId").show();
	        }
        });
    </script>  
  </head>   
  <body> 
  <s:form>
  <s:hidden name="flag" id="flag"></s:hidden>
  <s:hidden name="startDate" id="startDate"></s:hidden>
  <s:hidden name="endDate" id="endDate"></s:hidden>
  <div >
		<table width="100%" class="querytable" border="0">
			<tr >
				 <td style="padding-right: 25px;text-align: right;">
			  		时间：
					<select onchange="chanageRq(this)" id="time" class="select-1">
					    <option value="oneHour" selected="selected">1小时</option>
					    <option value="fiveHour">5小时</option>
			  			<option value="thisMonth">本月</option>
			  			<option value="lastMonth">上月</option>
			  			<option value="selfDate">自定义</option>
			  		</select>
					<span id="dateBoxId" style="display:none;">
					  		<input id="startDateId" style="txt" type="text" name="startDate" size="20"  class="Wdate"
								   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								   		/>
								   		~
							<input id="endDateId" style="txt" type="text" name="endDate" size="20"  class="Wdate"
								   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								   		/>
					</span>
					<input type="button"  class="ubtn-3 vm mgl-3" value="查询"
										onclick="javascript:updateFusionChartData()" />
				</td>
			</tr>
  		<tr>
  			<td style="text-align: center;width: 10px" colspan="10">
  				<div id="dayChartContainer"></div>
  			</td>
  		</tr>
  	</table>
  	</div>
  </s:form>
  </body> 
</html>