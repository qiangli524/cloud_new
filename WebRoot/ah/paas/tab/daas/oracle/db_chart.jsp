<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
  <head>
    <title>My First chart using FusionCharts XT</title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/ah/paas/statistics/css/statistics.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<script language="javascript" id="ss1" type="text/javascript" src="/cloud/js/My97DatePicker/WdatePicker.js"></script>
    <style type="text/css">
  	    html,body,form
     {
      margin:0px;
      height:100%;
     }
  	</style>
    <script type="text/javascript">
    	
	    $(function(){
	    	showChart();
	    });
	    
    	function showChart(){
    		var entity_id = $("#entity_id").val();
    		var time_capacity=$("#time_capacity option:selected").val();
    		var time_efficiency = $("#time_efficiency option:selected").val();
	   		 $("#capacity").insertFusionCharts({
	                  swfUrl: "FusionCharts/ZoomLine.swf", 
	                  dataSource: "oracleTab_listDBCharts.do?kpi_type=capacity&entity_id="+entity_id+"&cycle_time="+time_capacity,
	                  dataFormat: "jsonurl", 
	                  width: "700", 
	                  height: "346", 
	                  id: "chart2"
	            }); 
   			$("#efficiency").insertFusionCharts({
                   swfUrl: "FusionCharts/ZoomLine.swf", 
                   dataSource: "oracleTab_listDBCharts.do?kpi_type=efficiency&entity_id="+entity_id+"&cycle_time="+time_efficiency,
                   dataFormat: "jsonurl", 
                   width: "700", 
                   height: "346", 
                   id: "chart1"
             }); 
    	}
    	
    	function selectShowType(type){
    		var start_time_efficiency = $("#start_time_efficiency").val();
    		var end_time_efficiency = $("#end_time_efficiency").val();
    		var start_time_capacity = $("#start_time_capacity").val();
    		var end_time_capacity = $("#end_time_capacity").val();
    		var show_efficiency = $("#show_efficiency").val();
    		var show_capacity = $("#show_capacity").val();
    		var entity_id = $("#entity_id").val();
    		var time_capacity=$("#time_capacity option:selected").val();
    		var time_efficiency = $("#time_efficiency option:selected").val();
    		if(type=='efficiency'){
    			 $("#efficiency").updateFusionCharts({
	   	          	 dataSource: "oracleTab_listDBCharts.do?kpi="+show_efficiency+"&entity_id="+entity_id+"&kpi_type=efficiency&start_time="+start_time_efficiency+"&end_time="+end_time_efficiency+"&cycle_time="+time_efficiency
	   	         });
    		}else if(type=='capacity'){
	   			 $("#capacity").updateFusionCharts({
	   	          	 dataSource: "oracleTab_listDBCharts.do?kpi="+show_capacity+"&entity_id="+entity_id+"&kpi_type=capacity&start_time="+start_time_capacity+"&end_time="+end_time_capacity+"&cycle_time="+time_capacity
	   	         });
			}
    	}
    	
    	 function chanage_capacity(obj){
        	$("#dateBoxId_capacity").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_capacity").show();
        	}
        }
        
         function chanage_efficiency(obj){
        	$("#dateBoxId_efficiency").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_efficiency").show();
        	}
        }
    </script>  
  </head>   
  <body> 
  <s:hidden name="kpi_type" id="kpi_type"></s:hidden>
  <s:hidden name="entity_id" id="entity_id"></s:hidden>
  <s:form action="" id="theForm">
  <div class="scrollbody">
  		<s:if test="kpi_type=='capacity'">
			<div class="mainbox-kpi" style="width: 100%;border: 0px solid #FFFFFF;">
	 		<div class="item">
				<div class="top">
					<div class="ml5"><font color="orange" size="3" style="margin-right: 10%;">容量</font>
						<select id="show_capacity" onchange="selectShowType('capacity')" style="width:120px;">
							<option value="PS-03-02-003">表空间占用率</option>
							<option value="PS-03-02-004">SCN健康值</option>
						</select>
						<span>
					  		时间：
							<select onchange="chanage_capacity(this)" id="time_capacity">
							    <option value="oneHour" selected="selected">1小时</option>
							    <option value="fiveHour">5小时</option>
					  			<option value="thisMonth">本月</option>
					  			<option value="lastMonth">上月</option>
					  			<option value="selfDate">自定义</option>
					  		</select>
							<span id="dateBoxId_capacity" style="display:none;">
							  		<input id="start_time_capacity" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
										   		~
									<input id="end_time_capacity" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:$('#start_time_capacity').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
							</span>
							<a href="#"	onclick="selectShowType('capacity');" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
									style="width: 18px;height: 18px;"> </a>
						</span>
					</div>
				</div>
				<div style="height: 10px;"></div>
				<div id="capacity" align="center"></div>
				<div style="height: 10px;"></div>
			</div>
		</div>
		</s:if>
      	<s:elseif test="kpi_type=='efficiency'">
				<div class="mainbox-kpi" style="width: 100%;border: 0px solid #FFFFFF;">
		 		<div class="item">
					<div class="top">
						<div class="ml5"><font color="orange" size="3" style="margin-right: 10%;">负载</font>
							<select id="show_efficiency" onchange="selectShowType('efficiency')" style="width:120px;">
								<option value="PS-03-04-013">阻塞会话数</option>
							</select>
							<span>
						  		时间：
								<select onchange="chanage_efficiency(this)" id="time_efficiency">
								    <option value="oneHour" selected="selected">1小时</option>
								    <option value="fiveHour">5小时</option>
						  			<option value="thisMonth">本月</option>
						  			<option value="lastMonth">上月</option>
						  			<option value="selfDate">自定义</option>
						  		</select>
								<span id="dateBoxId_efficiency" style="display:none;">
								  		<input id="start_time_efficiency" style="txt" type="text" size="20"  class="Wdate"
											   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
											   		/>
											   		~
										<input id="end_time_efficiency" style="txt" type="text" size="20"  class="Wdate"
											   		onFocus="WdatePicker({minDate:$('#start_time_efficiency').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
											   		/>
								</span>
								<a href="#"	onclick="selectShowType('efficiency');" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
										style="width: 18px;height: 18px;"> </a>
							</span>
						</div>
					</div>
					<div style="height: 10px;"></div>
					<div id="efficiency" align="center"></div>
					<div style="height: 10px;"></div>
				</div>
			</div>
		</s:elseif>
  	</div>
  	</s:form>
  </body> 
</html>