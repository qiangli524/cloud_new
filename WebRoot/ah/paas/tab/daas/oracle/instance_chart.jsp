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
	    	showCharts();
	    });
	    
    	function showCharts(){
    		var kpi_type = $("#kpi_type").val();
    		var entity_id = $("#entity_id").val();
    		var time_response=$("#time_response option:selected").val();
    		var time_efficiency = $("#time_efficiency option:selected").val();
    		var time_capacity=$("#time_capacity option:selected").val();
    		var time_resource = $("#time_resource option:selected").val();
    		var time_exception=$("#time_exception option:selected").val();
    		if(kpi_type == "response"){
    			$("#response").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "oracleTab_listInstanceCharts.do?kpi_type="+kpi_type+"&entity_id="+entity_id+"&cycle_time="+time_response,
                    dataFormat: "jsonurl", 
                    width: "700", 
                    height: "346", 
                    id: "chart1"
               }); 
    		}else if(kpi_type == "efficiency"){
    			$("#efficiency").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "oracleTab_listInstanceCharts.do?kpi_type="+kpi_type+"&entity_id="+entity_id+"&cycle_time="+time_efficiency,
                    dataFormat: "jsonurl", 
                    width: "700", 
                    height: "346", 
                    id: "chart2"
               }); 
    		}else if(kpi_type == "capacity"){
    			$("#capacity").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "oracleTab_listInstanceCharts.do?kpi_type="+kpi_type+"&entity_id="+entity_id+"&cycle_time="+time_capacity,
                    dataFormat: "jsonurl", 
                    width: "700", 
                    height: "346", 
                    id: "chart3"
               }); 
    		}else if(kpi_type == "resource"){
    			$("#resource").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "oracleTab_listInstanceCharts.do?kpi_type="+kpi_type+"&entity_id="+entity_id+"&cycle_time="+time_resource,
                    dataFormat: "jsonurl", 
                    width: "700", 
                    height: "346", 
                    id: "chart4"
               }); 
    		}else if(kpi_type == "exception"){
    			$("#exception").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "oracleTab_listInstanceCharts.do?kpi_type="+kpi_type+"&entity_id="+entity_id+"&cycle_time="+time_exception,
                    dataFormat: "jsonurl", 
                    width: "700", 
                    height: "346", 
                    id: "chart5"
               }); 
    		}
    	};
    	
    	function selectShowType(type){
    		var entity_id = $("#entity_id").val();
    		var kpi_type = $("#kpi_type").val();
    		var show_response = $("#show_response").val();
    		var show_efficiency = $("#show_efficiency").val();
    		var show_capacity = $("#show_capacity").val();
    		var show_resource = $("#show_resource").val();
    		var show_exception = $("#show_exception").val();
    		var start_time_response = $("#start_time_response").val();
    		var end_time_response = $("#end_time_response").val();
    		var start_time_efficiency = $("#start_time_efficiency").val();
    		var end_time_efficiency = $("#end_time_efficiency").val();
    		var start_time_capacity = $("#start_time_capacity").val();
    		var end_time_capacity = $("#end_time_capacity").val();
    		var start_time_resource = $("#start_time_resource").val();
    		var end_time_resource = $("#end_time_resource").val();
    		var start_time_exception = $("#start_time_exception").val();
    		var end_time_exception = $("#end_time_exception").val();
    		var time_response=$("#time_response option:selected").val();
    		var time_efficiency = $("#time_efficiency option:selected").val();
    		var time_capacity=$("#time_capacity option:selected").val();
    		var time_resource = $("#time_resource option:selected").val();
    		var time_exception=$("#time_exception option:selected").val();
    		
    		if(type=='response'){
    			 $("#response").updateFusionCharts({
	   	          	 dataSource: "oracleTab_listInstanceCharts.do?kpi="+show_response+"&entity_id="+entity_id+"&kpi_type=response&start_time="+start_time_response+"&end_time="+end_time_response+"&cycle_time="+time_response
	   	         });
    		}else if(type=='efficiency'){
	   			 $("#efficiency").updateFusionCharts({
	   	          	 dataSource: "oracleTab_listInstanceCharts.do?kpi="+show_efficiency+"&entity_id="+entity_id+"&kpi_type=efficiency&start_time="+start_time_efficiency+"&end_time="+end_time_efficiency+"&cycle_time="+time_efficiency
	   	         });
			}else if(type=='capacity'){
	   			 $("#capacity").updateFusionCharts({
	   	          	 dataSource: "oracleTab_listInstanceCharts.do?kpi="+show_capacity+"&entity_id="+entity_id+"&kpi_type=capacity&start_time="+start_time_capacity+"&end_time="+end_time_capacity+"&cycle_time="+time_capacity
	   	         });
			}else if(type=='resource'){
	   			 $("#resource").updateFusionCharts({
	   	          	 dataSource: "oracleTab_listInstanceCharts.do?kpi="+show_resource+"&entity_id="+entity_id+"&kpi_type=resource&start_time="+start_time_resource+"&end_time="+end_time_resource+"&cycle_time="+time_resource
	   	         });
			}else if(type=='exception'){
	   			 $("#exception").updateFusionCharts({
	   	          	 dataSource: "oracleTab_listInstanceCharts.do?kpi="+show_exception+"&entity_id="+entity_id+"&kpi_type=exception&start_time="+start_time_exception+"&end_time="+end_time_exception+"&cycle_time="+time_exception
	   	         });
			}
    	}
    	
    	function chanage_response(obj){
        	$("#dateBoxId_response").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_response").show();
        	}
        }
        
         function chanage_efficiency(obj){
        	$("#dateBoxId_efficiency").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_efficiency").show();
        	}
        }
        
        function chanage_capacity(obj){
        	$("#dateBoxId_capacity").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_capacity").show();
        	}
        }
        
         function chanage_resource(obj){
        	$("#dateBoxId_resource").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_resource").show();
        	}
        }
        
        function chanage_exception(obj){
        	$("#dateBoxId_exception").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_exception").show();
        	}
        }
    </script>  
  </head>   
  <body> 
  <s:hidden name="kpi_type" id="kpi_type"></s:hidden>
  <s:hidden name="entity_id" id="entity_id"></s:hidden>
  <s:form action="" id="theForm">
  	<div class="scrollbody">
			<s:if test="kpi_type=='response'">
				<div class="mainbox-kpi" style="width: 100%;border: 0px solid #FFFFFF;">
			 		<div class="item">
						<div class="top">
							<div class="ml5"><font color="orange" size="3" style="margin-right: 10%;">响应</font>
								<select id="show_response" onchange="selectShowType('response')" style="width:120px;">
									<option value="PS-03-05-003">平均文件读取时间</option>
									<option value="PS-03-05-004">平均文件写入时间</option>
									<option value="PS-03-05-001">当前SQL平均响应时间</option>
								</select>
								<span>
							  		时间：
									<select onchange="chanage_response(this)" id="time_response">
									    <option value="oneHour" selected="selected">1小时</option>
									    <option value="fiveHour">5小时</option>
							  			<option value="thisMonth">本月</option>
							  			<option value="lastMonth">上月</option>
							  			<option value="selfDate">自定义</option>
							  		</select>
									<span id="dateBoxId_response" style="display:none;">
									  		<input id="start_time_response" style="txt" type="text" size="20"  class="Wdate"
												   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
												   		/>
												   		~
											<input id="end_time_response" style="txt" type="text" size="20"  class="Wdate"
												   		onFocus="WdatePicker({minDate:$('#start_time_response').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
												   		/>
									</span>
									<a href="#"	onclick="selectShowType('response');" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
											style="width: 18px;height: 18px;"> </a>
								</span>		
							</div>
						</div>
						<div style="height: 10px;"></div>
						<div id="response" align="center"></div>
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
								<option value="PS-03-03-002">解析次数</option>
								<option value="PS-03-04-003">阻塞会话数</option>
								<option value="PS-03-03-001">用户提交数</option>
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
			<s:elseif test="kpi_type=='capacity'">
				<div class="mainbox-kpi" style="width: 100%;border: 0px solid #FFFFFF;">
		 		<div class="item">
					<div class="top">
						<div class="ml5"><font color="orange" size="3" style="margin-right: 10%;">容量</font>
							<select id="show_capacity" onchange="selectShowType('capacity')" style="width:120px;">
								<option value="PS-03-02-001">SCN健康值</option>
								<option value="PS-03-02-002">表空间占用率</option>
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
			</s:elseif>
			<s:elseif test="kpi_type=='resource'">
				<div class="mainbox-kpi" style="width: 100%;border: 0px solid #FFFFFF;">
		 		<div class="item">
					<div class="top">
						<div class="ml5"><font color="orange" size="3" style="margin-right: 10%;">资源</font>
							<select id="show_resource" onchange="selectShowType('resource')" style="width:120px;">
								<option value="PS-03-04-010">CPU占用率</option>
								<option value="PS-03-07-001">内存使用量</option>
							</select>
							<span>
						  		时间：
								<select onchange="chanage_resource(this)" id="time_resource">
								    <option value="oneHour" selected="selected">1小时</option>
								    <option value="fiveHour">5小时</option>
						  			<option value="thisMonth">本月</option>
						  			<option value="lastMonth">上月</option>
						  			<option value="selfDate">自定义</option>
						  		</select>
								<span id="dateBoxId_resource" style="display:none;">
								  		<input id="start_time_resource" style="txt" type="text" size="20"  class="Wdate"
											   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
											   		/>
											   		~
										<input id="end_time_resource" style="txt" type="text" size="20"  class="Wdate"
											   		onFocus="WdatePicker({minDate:$('#start_time_resource').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
											   		/>
								</span>
								<a href="#"	onclick="selectShowType('resource');" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
										style="width: 18px;height: 18px;"> </a>
							</span>
						</div>
					</div>
					<div style="height: 10px;"></div>
					<div id="resource" align="center"></div>
					<div style="height: 10px;"></div>
				</div>
			</div>
			</s:elseif>
			<s:elseif test="kpi_type=='exception'">
				<div class="mainbox-kpi" style="width: 100%;border: 0px solid #FFFFFF;">
		 		<div class="item">
					<div class="top">
						<div class="ml5"><font color="orange" size="3" style="margin-right: 10%;">异常</font>
							<select id="show_exception" onchange="selectShowType('exception')" style="width:120px;">
								<option value="PS-03-06-001">无效对象总数</option>
							</select>
							<span>
						  		时间：
								<select onchange="chanage_exception(this)" id="time_exception">
								    <option value="oneHour" selected="selected">1小时</option>
								    <option value="fiveHour">5小时</option>
						  			<option value="thisMonth">本月</option>
						  			<option value="lastMonth">上月</option>
						  			<option value="selfDate">自定义</option>
						  		</select>
								<span id="dateBoxId_exception" style="display:none;">
								  		<input id="start_time_exception" style="txt" type="text" size="20"  class="Wdate"
											   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
											   		/>
											   		~
										<input id="end_time_exception" style="txt" type="text" size="20"  class="Wdate"
											   		onFocus="WdatePicker({minDate:$('#start_time_exception').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
											   		/>
								</span>
								<a href="#"	onclick="selectShowType('exception');" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
										style="width: 18px;height: 18px;"> </a>
							</span>			
						</div>
					</div>
					<div style="height: 10px;"></div>
					<div id="exception" align="center"></div>
					<div style="height: 10px;"></div>
				</div>
			</div>
			</s:elseif>
  	</div>
  </s:form>
  </body> 
</html>