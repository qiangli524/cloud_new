<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
    <title>My First chart using FusionCharts XT</title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	insertFusionChartData();
	     });
	    
	    function updateFusionChartData(){
	    	mask('正在加载数据,请稍后...','0.5','0px');
	    	var cycle_time='selfDate';
	    	var pointCount=$("#pointCount option:selected").val();
	    	var startDate=$("#startDateId").val();
	    	var endDate=$("#endDateId").val();
	    	var hyId = $("#hy_id").val();
	    	var kpi_id = $("#kpi_id").val();
	    	var startUsage =  $("#startUsageId").val();
	    	var endUsage =  $("#endUsageId").val();
	    	$("#dayChartContainer").updateFusionCharts({
               dataSource: "ah-hyMonitor_hyMonitorPerformationData.do?cycle_time="+cycle_time
            		   +"&startDate="+startDate+"&endDate="+endDate+"&pointCount="+pointCount+"&hyId="+hyId+"&kpi="+kpi_id
            		   +"&startUsage="+startUsage+"&endUsage="+endUsage
            }); 
	    	removeMask();
	    }
	    
    	function insertFusionChartData(){
    		var pointCount=$("#pointCount option:selected").val();
    		var startDate=$("#startDateId").val();
	    	var endDate=$("#endDateId").val();
	    	var hyId = $("#hy_id").val();
	    	var kpi_id = $("#kpi_id").val();
	    	var startUsage =  $("#startUsageId").val();
	    	var endUsage =  $("#endUsageId").val();
    		$("#dayChartContainer").insertFusionCharts({
                 swfUrl: "FusionCharts/ZoomLine.swf", 
                 dataSource: "ah-hyMonitor_hyMonitorPerformationData.do?cycle_time=selfDate&startDate="+startDate+"&endDate="+endDate+"&pointCount="+pointCount
                		 +"&hyId="+hyId+"&kpi="+kpi_id
                		 +"&startUsage="+startUsage+"&endUsage="+endUsage,
                 dataFormat: "jsonurl", 
                 width: "730", 
                 height: "350", 
                 id: "dayChart"
           }); 
    	}
    	
        function chanageRq(obj){
        	$("#dateBoxId").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId").show();
        	}
        }
        
        function exportPerformationData(){
        	var startDate=$("#startDateId").val();
	    	var endDate=$("#endDateId").val();
	    	var pointCount=$("#pointCount option:selected").val();
	    	var hyId = $("#hy_id").val();
	    	var kpi_id = $("#kpi_id").val();
	    	var startUsage =  $("#startUsageId").val();
	    	var endUsage =  $("#endUsageId").val();
        	var url="ah-hyMonitor_hyMonitorPerformationDataExport.do?cycle_time=selfDate&hyId="+hyId+"&kpi="+kpi_id
        			+"&startDate="+startDate+"&endDate="+endDate+"&pointCount=all"
        			+"&startUsage="+startUsage+"&endUsage="+endUsage;
    		exportForm.action = url;
    		exportForm.submit();
        }
        
    </script>  
  </head>   
  <body> 
  <div  class="scrollbody">
  		<input type="hidden" name="hyId" value="${hyId}" id="hy_id"/>
  		<input type="hidden" name="kpi" value="${kpi}" id="kpi_id"/>
		<table width="100%" class="querytable" border="0">
			<tr >
				 <td style="padding-right: 25px;text-align: right;">
					<!-- 显示点数： -->
                     <select id="pointCount" style="display:none;">
					    <option value="0" >正常</option>
					    <option value="100">100</option>
			  			<option value="200">200</option>
			  			<option value="300">300</option>
			  			<option value="all" selected="selected">所有</option>
			  		 </select>
			  		<s:if test="kpi=='PM-V-01-010-11'">
			  			CPU使用率：
			  		</s:if>
			  		<s:if test="kpi=='PM-V-01-010-12'">
			  			内存使用率：
			  		</s:if>
			  		<input type="input" id="startUsageId" name="startUsage" value="0" style="width: 30px"/>
			  		  ~
			  		<input type="input" id="endUsageId" name="endUsage" value="100" style="width: 30px"/>
			  		时间：
			  		<input id="startDateId" style="txt" type="text" name="startDate" size="20"  class="Wdate"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${startDate }"/>
						   		~
					<input id="endDateId" style="txt" type="text" name="endDate" size="20"  class="Wdate"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${endDate }"/>
					<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:updateFusionChartData()" />
					&nbsp;&nbsp;
					<input type="button" class="thickbox btn-style02" value="导出"
									onclick="javascript:exportPerformationData()" />				
										
					
				</td>
			</tr>
  			<tr>
	  			<td style="text-align: center;width: 10px;position:relative;top:5%;" colspan="10">
	  				<div id="dayChartContainer"></div>
	  			</td>
  			</tr>
  	</table>
  	<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
  	</div>
  </body> 
</html>