<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
    <title>My First chart using FusionCharts XT</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
    <style type="text/css">
		select {margin-top: 10px;margin-right: 10px;margin-bottom: 10px}
	</style>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	var param = queryParam();
	    	insertFusionChartData(param);
	     });
	    
	    function queryParam(){
	    	var machineType= $("#machineType").val();
	    	var collType = $("#collType").val();
	    	var collDataType = $("#collDataType").val();
	    	var machineId = $("#machineId").val();
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
                 width: "500", 
                 height: "300", 
                 id: "dayChart"
           }); 
           $("#weekChartContainer").insertFusionCharts({
                swfUrl: "FusionCharts/ScrollCombi2D.swf", 
                dataSource: "showview_queryChartWeekData.do?"+param,
                dataFormat: "jsonurl", 
                width: "500", 
                height: "300", 
                id: "weekChart"
          });
          $("#monthChartContainer").insertFusionCharts({
                swfUrl: "FusionCharts/ScrollCombi2D.swf", 
                dataSource: "showview_queryChartMonthData.do?"+param,
                dataFormat: "jsonurl", 
                width: "500", 
                height: "300", 
                id: "monthChart"
          	}); 
            $("#yearChartContainer").insertFusionCharts({
                swfUrl: "FusionCharts/ScrollCombi2D.swf", 
                dataSource: "showview_queryChartYearData.do?"+param,
                dataFormat: "jsonurl", 
                width: "500", 
                height: "300", 
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
  	<div>
  		<s:select list="#{'1':'主机','2':'虚拟机'}" id="machineType" onchange="updateMachineList();"></s:select>
  		<s:select list="#{'1':'CPU','2':'内存'}" id="collType" onchange="updateFusionChartData();"></s:select>
  		<s:select list="#{'1':'平均值','2':'最大值'}" id="collDataType" onchange="updateFusionChartData();"></s:select>
  		<s:select list="resultList" listKey="ID" listValue="ID" id="machineId" onchange="updateFusionChartData();"></s:select>
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