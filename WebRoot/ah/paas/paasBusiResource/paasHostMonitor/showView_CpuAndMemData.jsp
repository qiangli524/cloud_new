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
	    $(document).ready(function(){
	    	insertFusionChartData();
	     });
	    var id = '<s:property value="id"/>';
	    var kpi = '<s:property value="kpi"/>';
	    var motionName = '<s:property value="motionName"/>';
	    var eq_type = '<s:property value="eq_type"/>';
	    function updateFusionChartData(){
	    	mask('正在加载数据,请稍后...','0.5','0px');
	    	var cycle_time=$("#time option:selected").val();
	    	var pointCount=$("#pointCount option:selected").val();
	    	var startDate=$("#startDateId").val();
	    	var endDate=$("#endDateId").val();
	    	$("#dayChartContainer").updateFusionCharts({
               dataSource: "paasHostMonitor_queryHostCMData_f.do?cycle_time="+cycle_time+"&startDate="+startDate+"&endDate="+endDate+"&pointCount="+pointCount
               				+"&id="+id+"&kpi="+kpi+"&motionName="+motionName+"&eq_type="+eq_type
            }); 
	    	removeMask();
	    }
	    
    	function insertFusionChartData(){
    		 $("#dayChartContainer").insertFusionCharts({
                 swfUrl: "FusionCharts/ZoomLine.swf", 
                 dataSource: "paasHostMonitor_queryHostCMData_f.do?cycle_time=oneHour"+"&id="+id+"&kpi="+kpi+"&motionName="+motionName+"&eq_type="+eq_type,
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
    </script>  
  </head>   
  <body> 
  <div class="scrollbody">
		<table width="100%" class="querytable" border="0">
			<tr>
			    <td style="padding-right: 25px;text-align: right;">
<%--			    显示点数：--%>
<%--                     <select id="pointCount">--%>
<%--					    <option value="0" >正常</option>--%>
<%--					    <option value="100">100</option>--%>
<%--			  			<option value="200">200</option>--%>
<%--			  			<option value="300">300</option>--%>
<%--			  			<option value="all">所有</option>--%>
<%--			  		</select>--%>
			  	时间：
					<select onchange="chanageRq(this)" id="time">
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
					<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:updateFusionChartData()" />
				</td>
			</tr>
  		<tr>
  			<td style="text-align: center;width: 10px;position:relative;5px" colspan="10">
  				<div id="dayChartContainer"></div>
  			</td>
  		</tr>
  	</table>
  	</div>
  </body> 
</html>