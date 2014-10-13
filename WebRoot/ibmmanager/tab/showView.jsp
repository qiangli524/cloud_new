<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
    <title>CPU视图</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
    <style type="text/css">
		select {margin-top:-5px;margin-left:3px;}
	</style>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	var param = queryParam();
	    	insertFusionChartData(param);
	     });
	    
	    function queryParam(){
	    	var type =$("#type").val();
	    	if(type==2||type==5){
	    		var machineType= 1;//主机
	    	}else if(type==3||type==6){
	    		var machineType= 2;//虚拟机
	    	}
	    	var machineId =$("#uuid").val();//主机ID
	    	var collType = $("#collType").val();//CPU 或存储
	    	var collDataType = $("#collDataType").val();
	    	var param = "machineType="+machineType+"&collType="+collType+"&collDataType="+collDataType+"&machineId="+machineId
	    	return param;
	    };
	    
	    function updateFusionChartData(){
	    	var param = queryParam();
	    	$("#dayChartContainer").updateFusionCharts({
               dataSource: "showview_queryChartDayData.do?"+param
          }); 
          $("#weekChartContainer").updateFusionCharts({
               dataSource: "showview_queryChartWeekData.do?"+param
         });
         $("#monthChartContainer").updateFusionCharts({
               dataSource: "showview_queryChartMonthData.do?"+param
         	}); 
           $("#yearChartContainer").updateFusionCharts({
               dataSource: "showview_queryChartYearData.do?"+param
         }); 
	    }
	    
    	function insertFusionChartData(param){
    		 $("#dayChartContainer").insertFusionCharts({
                 swfUrl: "FusionCharts/ScrollCombi2D.swf", 
                 dataSource: "showview_queryChartDayData.do?"+param,
                 dataFormat: "jsonurl", 
                 width: "455", 
                 height: "240", 
                 id: "dayChart"
           }); 
           $("#weekChartContainer").insertFusionCharts({
                swfUrl: "FusionCharts/ScrollCombi2D.swf", 
                dataSource: "showview_queryChartWeekData.do?"+param,
                dataFormat: "jsonurl", 
                width: "455", 
                height: "240", 
                id: "weekChart"
          });
          $("#monthChartContainer").insertFusionCharts({
                swfUrl: "FusionCharts/ScrollCombi2D.swf", 
                dataSource: "showview_queryChartMonthData.do?"+param,
                dataFormat: "jsonurl", 
                width: "455", 
                height: "240", 
                id: "monthChart"
          	}); 
            $("#yearChartContainer").insertFusionCharts({
                swfUrl: "FusionCharts/ScrollCombi2D.swf", 
                dataSource: "showview_queryChartYearData.do?"+param,
                dataFormat: "jsonurl", 
                width: "455", 
                height: "240", 
                id: "yearChart"
          }); 
    	}
    	
    	function updateMachineList(){
    		var machineType = $("#machineType").val();
    		var url = "showview_queryAllMachine.do?machineType="+machineType;
    		$.getJSON(url,{'time':new Date().toString()},function(data){
    			var  SelectNode = document.getElementById("machineId");
    			$("#machineId").empty();
    			SelectNode.length=0;
         		if(data != null){
         			$.each(data,function(k,v){
    					$.each(v,function(key,value){
    						SelectNode.appendChild(createSelect(value,value));
    					});
    				});
         		}
         		updateFusionChartData();
    		});
    	}
    	
    	function createSelect(value,text){
      		var opt=document.createElement("option");
      		opt.setAttribute("value",value);
      		opt.appendChild(document.createTextNode(text));
      		return opt;
    	}
    </script>  
  </head>   
  <body> 
  	<s:hidden name="id"></s:hidden>
	<s:hidden name="name"></s:hidden>
	<s:hidden name="type"></s:hidden>
	<s:hidden name="vtype"></s:hidden>
	<s:hidden name="parent_id"></s:hidden>
	<s:hidden name="uuid"></s:hidden>
	<s:hidden name="collType"></s:hidden>
  	<div style="margin-top:-3px;">
  		<s:select list="#{'1':'平均值','2':'最大值'}" id="collDataType" onchange="updateFusionChartData();"></s:select>
  	</div>
  	<table>
  		<tr>
  			<td>
  				<div id="dayChartContainer">天数据</div>
  			</td>
  			<td>
  				<div id="weekChartContainer">周数据</div>
  			</td>
  		</tr>
  		<tr>
  			<td>
  				<div id="monthChartContainer">月数据</div>
  			</td>
  			<td>
  				<div id="yearChartContainer">年数据</div>
  			</td>
  		</tr>
  	</table>
  </body> 
</html>