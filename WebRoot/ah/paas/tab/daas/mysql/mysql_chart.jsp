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
    		var time_qps=$("#time_qps option:selected").val();
    		var time_tps = $("#time_tps option:selected").val();
    		var time_query_cache=$("#time_query_cache option:selected").val();
    		var time_key_buffer = $("#time_key_buffer option:selected").val();
    		var time_table_cache=$("#time_table_cache option:selected").val();
    		var time_tmp_table = $("#time_tmp_table option:selected").val();
    		var time_binlog_cache=$("#time_binlog_cache option:selected").val();

    		if(kpi_type == "qps"){
    			$("#qps").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "mysqlTab_listInstanceCharts.do?kpi_type="+kpi_type+"&entity_id="+entity_id+"&cycle_time="+time_qps,
                    dataFormat: "jsonurl", 
                    width: "700", 
                    height: "346", 
                    id: "chart1"
               }); 
    		}else if(kpi_type == "tps"){
    			$("#tps").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "mysqlTab_listInstanceCharts.do?kpi_type="+kpi_type+"&entity_id="+entity_id+"&cycle_time="+time_tps,
                    dataFormat: "jsonurl", 
                    width: "700", 
                    height: "346", 
                    id: "chart2"
               }); 
    		}else if(kpi_type == "query_cache"){
    			$("#query_cache").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "mysqlTab_listInstanceCharts.do?kpi_type="+kpi_type+"&entity_id="+entity_id+"&cycle_time="+time_query_cache,
                    dataFormat: "jsonurl", 
                    width: "700", 
                    height: "346", 
                    id: "chart3"
               }); 
    		}else if(kpi_type == "key_buffer"){
    			$("#key_buffer").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "mysqlTab_listInstanceCharts.do?kpi_type="+kpi_type+"&entity_id="+entity_id+"&cycle_time="+time_key_buffer,
                    dataFormat: "jsonurl", 
                    width: "700", 
                    height: "346", 
                    id: "chart4"
               }); 
    		}else if(kpi_type == "table_cache"){
    			$("#table_cache").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "mysqlTab_listInstanceCharts.do?kpi_type="+kpi_type+"&entity_id="+entity_id+"&cycle_time="+time_table_cache,
                    dataFormat: "jsonurl", 
                    width: "700", 
                    height: "346", 
                    id: "chart5"
               }); 
    		}else if(kpi_type == "tmp_table"){
    			$("#tmp_table").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "mysqlTab_listInstanceCharts.do?kpi_type="+kpi_type+"&entity_id="+entity_id+"&cycle_time="+time_tmp_table,
                    dataFormat: "jsonurl", 
                    width: "700", 
                    height: "346", 
                    id: "chart4"
               }); 
    		}else if(kpi_type == "binlog_cache"){
    			$("#binlog_cache").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "mysqlTab_listInstanceCharts.do?kpi_type="+kpi_type+"&entity_id="+entity_id+"&cycle_time="+time_binlog_cache,
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
    		var show_qps = $("#show_qps").val();
    		var show_tps = $("#show_tps").val();
    		var show_query_cache = $("#show_query_cache").val();
    		var show_key_buffer = $("#show_key_buffer").val();
    		var show_table_cache = $("#show_table_cache").val();
    		var show_tmp_table = $("#show_tmp_table").val();
    		var show_binlog_cache = $("#show_binlog_cache").val();
    		
    		var start_time_qps = $("#start_time_qps").val();
    		var end_time_qps = $("#end_time_qps").val();
    		var start_time_tps = $("#start_time_tps").val();
    		var end_time_tps = $("#end_time_tps").val();
    		var start_time_query_cache = $("#start_time_query_cache").val();
    		var end_time_query_cache = $("#end_time_query_cache").val();
    		var start_time_key_buffer = $("#start_time_key_buffer").val();
    		var end_time_key_buffer = $("#end_time_key_buffer").val();
    		var start_time_table_cache = $("#start_time_table_cache").val();
    		var end_time_table_cache = $("#end_time_table_cache").val();
    		var start_time_tmp_table = $("#start_time_tmp_table").val();
    		var end_time_tmp_table = $("#end_time_tmp_table").val();
    		var start_time_binlog_cache = $("#start_time_binlog_cache").val();
    		var end_time_binlog_cache = $("#end_time_binlog_cache").val();

    		var time_qps=$("#time_qps option:selected").val();
    		var time_tps = $("#time_tps option:selected").val();
    		var time_query_cache=$("#time_query_cache option:selected").val();
    		var time_key_buffer = $("#time_key_buffer option:selected").val();
    		var time_table_cache=$("#time_table_cache option:selected").val();
    		var time_tmp_table = $("#time_tmp_table option:selected").val();
    		var time_binlog_cache=$("#time_binlog_cache option:selected").val();
    		
    		if(type=='qps'){
    			 $("#qps").updateFusionCharts({
	   	          	 dataSource: "mysqlTab_listInstanceCharts.do?kpi="+show_qps+"&entity_id="+entity_id+"&kpi_type=qps&start_time="+start_time_qps+"&end_time="+end_time_qps+"&cycle_time="+time_qps
	   	         });
    		}else if(type=='tps'){
	   			 $("#tps").updateFusionCharts({
	   	          	 dataSource: "mysqlTab_listInstanceCharts.do?kpi="+show_tps+"&entity_id="+entity_id+"&kpi_type=tps&start_time="+start_time_tps+"&end_time="+end_time_tps+"&cycle_time="+time_tps
	   	         });
			}else if(type=='query_cache'){
	   			 $("#query_cache").updateFusionCharts({
	   	          	 dataSource: "mysqlTab_listInstanceCharts.do?kpi="+show_query_cache+"&entity_id="+entity_id+"&kpi_type=query_cache&start_time="+start_time_query_cache+"&end_time="+end_time_query_cache+"&cycle_time="+time_query_cache
	   	         });
			}else if(type=='key_buffer'){
	   			 $("#key_buffer").updateFusionCharts({
	   	          	 dataSource: "mysqlTab_listInstanceCharts.do?kpi="+show_key_buffer+"&entity_id="+entity_id+"&kpi_type=key_buffer&start_time="+start_time_key_buffer+"&end_time="+end_time_key_buffer+"&cycle_time="+time_key_buffer
	   	         });
			}else if(type=='table_cache'){
	   			 $("#table_cache").updateFusionCharts({
	   	          	 dataSource: "mysqlTab_listInstanceCharts.do?kpi="+show_table_cache+"&entity_id="+entity_id+"&kpi_type=table_cache&start_time="+start_time_table_cache+"&end_time="+end_time_table_cache+"&cycle_time="+time_table_cache
	   	         });
			}else if(type=='tmp_table'){
	   			 $("#tmp_table").updateFusionCharts({
	   	          	 dataSource: "mysqlTab_listInstanceCharts.do?kpi="+show_tmp_table+"&entity_id="+entity_id+"&kpi_type=tmp_table&start_time="+start_time_tmp_table+"&end_time="+end_time_tmp_table+"&cycle_time="+time_tmp_table
	   	         });
			}else if(type=='binlog_cache'){
	   			 $("#binlog_cache").updateFusionCharts({
	   	          	 dataSource: "mysqlTab_listInstanceCharts.do?kpi="+show_binlog_cache+"&entity_id="+entity_id+"&kpi_type=binlog_cache&start_time="+start_time_binlog_cache+"&end_time="+end_time_binlog_cache+"&cycle_time="+time_binlog_cache
	   	         });
			}
    	}
    	
    	function chanage_qps(obj){
        	$("#dateBoxId_qps").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_qps").show();
        	}
        }
        
         function chanage_tps(obj){
        	$("#dateBoxId_tps").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_tps").show();
        	}
        }
        
        function chanage_query_cache(obj){
        	$("#dateBoxId_query_cache").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_query_cache").show();
        	}
        }
        
         function chanage_key_buffer(obj){
        	$("#dateBoxId_key_buffer").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_key_buffer").show();
        	}
        }
        
        function chanage_table_cache(obj){
        	$("#dateBoxId_table_cache").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_table_cache").show();
        	}
        }
        
        
        
        
        
        
        function chanage_tmp_table(obj){
        	$("#dateBoxId_tmp_table").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_tmp_table").show();
        	}
        }
        
        function chanage_binlog_cache(obj){
        	$("#dateBoxId_binlog_cache").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_binlog_cache").show();
        	}
        }
        
    </script>  
  </head>   
  <body> 
  <s:hidden name="kpi_type" id="kpi_type"></s:hidden>
  <s:hidden name="entity_id" id="entity_id"></s:hidden>
  <s:form action="" id="theForm">
  	<div class="scrollbody">
		<s:if test="kpi_type=='qps'">
			<div class="mainbox-kpi" style="width: 100%;border: 0px solid #FFFFFF;">
		 		<div class="item">
					<div class="top">
						<div class="ml5"><font color="orange" size="3" style="margin-right: 10%;">QPS</font>
							<select id="show_qps" onchange="selectShowType('QPS')" style="width:120px;">
								<option value="PS-04-02-001">每秒钟获得的查询数量</option>
							</select>
							<span>
						  		时间：
								<select onchange="chanage_qps(this)" id="time_qps">
								    <option value="oneHour" selected="selected">1小时</option>
								    <option value="fiveHour">5小时</option>
						  			<option value="thisMonth">本月</option>
						  			<option value="lastMonth">上月</option>
						  			<option value="selfDate">自定义</option>
						  		</select>
								<span id="dateBoxId_qps" style="display:none;">
								  		<input id="start_time_qps" style="txt" type="text" size="20"  class="Wdate"
											   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
											   		/>
											   		~
										<input id="end_time_qps" style="txt" type="text" size="20"  class="Wdate"
											   		onFocus="WdatePicker({minDate:$('#start_time_qps').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
											   		/>
								</span>
								<a href="#"	onclick="selectShowType('qps');" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
										style="width: 18px;height: 18px;"> </a>
							</span>		
						</div>
					</div>
					<div style="height: 10px;"></div>
					<div id="qps" align="center"></div>
					<div style="height: 10px;"></div>
				</div>
			</div>
		</s:if>	
		<s:elseif test="kpi_type=='tps'">
			<div class="mainbox-kpi" style="width: 100%;border: 0px solid #FFFFFF;">
	 		<div class="item">
				<div class="top">
					<div class="ml5"><font color="orange" size="3" style="margin-right: 10%;">TPS</font>
						<select id="show_tps" onchange="selectShowType('tps')" style="width:120px;">
							<option value="PS-04-03-001">每秒传输的事物处理数</option>
						</select>
						<span>
					  		时间：
							<select onchange="chanage_tps(this)" id="time_tps">
							    <option value="oneHour" selected="selected">1小时</option>
							    <option value="fiveHour">5小时</option>
					  			<option value="thisMonth">本月</option>
					  			<option value="lastMonth">上月</option>
					  			<option value="selfDate">自定义</option>
					  		</select>
							<span id="dateBoxId_tps" style="display:none;">
							  		<input id="start_time_tps" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
										   		~
									<input id="end_time_tps" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:$('#start_time_tps').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
							</span>
							<a href="#"	onclick="selectShowType('tps');" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
									style="width: 18px;height: 18px;"> </a>
						</span>				
					</div>
				</div>
				<div style="height: 10px;"></div>
				<div id="tps" align="center"></div>
				<div style="height: 10px;"></div>
			</div>
		</div>
		</s:elseif>
		<s:elseif test="kpi_type=='query_cache'">
			<div class="mainbox-kpi" style="width: 100%;border: 0px solid #FFFFFF;">
	 		<div class="item">
				<div class="top">
					<div class="ml5"><font color="orange" size="3" style="margin-right: 10%;">Query Cache</font>
						<select id="show_query_cache" onchange="selectShowType('query_cache')" style="width:120px;">
							<option value="PS-04-04-002">查询缓存被访问的次数</option>
							<option value="PS-04-04-003">加入到缓存的查询数量</option>
						</select>
						<span>
					  		时间：
							<select onchange="chanage_query_cache(this)" id="time_query_cache">
							    <option value="oneHour" selected="selected">1小时</option>
							    <option value="fiveHour">5小时</option>
					  			<option value="thisMonth">本月</option>
					  			<option value="lastMonth">上月</option>
					  			<option value="selfDate">自定义</option>
					  		</select>
							<span id="dateBoxId_query_cache" style="display:none;">
							  		<input id="start_time_query_cache" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
										   		~
									<input id="end_time_query_cache" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:$('#start_time_query_cache').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
							</span>
							<a href="#"	onclick="selectShowType('query_cache');" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
									style="width: 18px;height: 18px;"> </a>
						</span>
					</div>
				</div>
				<div style="height: 10px;"></div>
				<div id="query_cache" align="center"></div>
				<div style="height: 10px;"></div>
			</div>
		</div>
		</s:elseif>
		<s:elseif test="kpi_type=='key_buffer'">
			<div class="mainbox-kpi" style="width: 100%;border: 0px solid #FFFFFF;">
	 		<div class="item">
				<div class="top">
					<div class="ml5"><font color="orange" size="3" style="margin-right: 10%;">Key Buffer</font>
						<select id="show_key_buffer" onchange="selectShowType('key_buffer')" style="width:120px;">
							<option value="PS-04-05-002">读索引的次数</option>
							<option value="PS-04-05-003">从缓存读键的数据块的请求数</option>
							<option value="PS-04-05-004">将键的数据块写入缓存的请求数</option>
							<option value="PS-04-05-005">向硬盘写入将键的数据块的物理写操作的次数</option>
						</select>
						<span>
					  		时间：
							<select onchange="chanage_key_buffer(this)" id="time_key_buffer">
							    <option value="oneHour" selected="selected">1小时</option>
							    <option value="fiveHour">5小时</option>
					  			<option value="thisMonth">本月</option>
					  			<option value="lastMonth">上月</option>
					  			<option value="selfDate">自定义</option>
					  		</select>
							<span id="dateBoxId_key_buffer" style="display:none;">
							  		<input id="start_time_key_buffer" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
										   		~
									<input id="end_time_key_buffer" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:$('#start_time_key_buffer').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
							</span>
							<a href="#"	onclick="selectShowType('key_buffer');" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
									style="width: 18px;height: 18px;"> </a>
						</span>
					</div>
				</div>
				<div style="height: 10px;"></div>
				<div id="key_buffer" align="center"></div>
				<div style="height: 10px;"></div>
			</div>
		</div>
		</s:elseif>
		<s:elseif test="kpi_type=='table_cache'">
			<div class="mainbox-kpi" style="width: 100%;border: 0px solid #FFFFFF;">
	 		<div class="item">
				<div class="top">
					<div class="ml5"><font color="orange" size="3" style="margin-right: 10%;">异常</font>
						<select id="show_table_cache" onchange="selectShowType('table_cache')" style="width:120px;">
							<option value="PS-04-06-001">已经打开的表的数量</option>
							<option value="PS-04-06-002">当前打开的表的数目</option>
							<option value="PS-04-06-003">打开的文件的数目</option>
							<option value="PS-04-06-004">打开的流的数量</option>
							<option value="PS-04-06-005">被缓存的FRM文件数量</option>
							<option value="PS-04-06-006">已经创建的临时文件的数量</option>
							<option value="PS-04-06-007">被缓存过的FRM文件的数量</option>
						</select>
						<span>
					  		时间：
							<select onchange="chanage_table_cache(this)" id="time_table_cache">
							    <option value="oneHour" selected="selected">1小时</option>
							    <option value="fiveHour">5小时</option>
					  			<option value="thisMonth">本月</option>
					  			<option value="lastMonth">上月</option>
					  			<option value="selfDate">自定义</option>
					  		</select>
							<span id="dateBoxId_table_cache" style="display:none;">
							  		<input id="start_time_table_cache" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
										   		~
									<input id="end_time_table_cache" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:$('#start_time_table_cache').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
							</span>
							<a href="#"	onclick="selectShowType('table_cache');" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
									style="width: 18px;height: 18px;"> </a>
						</span>			
					</div>
				</div>
				<div style="height: 10px;"></div>
				<div id="table_cache" align="center"></div>
				<div style="height: 10px;"></div>
			</div>
		</div>
		</s:elseif>
		<s:elseif test="kpi_type=='tmp_table'">
			<div class="mainbox-kpi" style="width: 100%;border: 0px solid #FFFFFF;">
	 		<div class="item">
				<div class="top">
					<div class="ml5"><font color="orange" size="3" style="margin-right: 10%;">异常</font>
						<select id="show_tmp_table" onchange="selectShowType('tmp_table')" style="width:120px;">
							<option value="PS-04-08-001">服务器执行语句时在硬盘上自动创建的临时表的数量</option>
							<option value="PS-04-08-002">服务器执行语句时自动创建的内存中的临时表的数量</option>
							<option value="PS-04-08-003">mysqld已经创建的临时文件的数量</option>
						</select>
						<span>
					  		时间：
							<select onchange="chanage_tmp_table(this)" id="time_tmp_table">
							    <option value="oneHour" selected="selected">1小时</option>
							    <option value="fiveHour">5小时</option>
					  			<option value="thisMonth">本月</option>
					  			<option value="lastMonth">上月</option>
					  			<option value="selfDate">自定义</option>
					  		</select>
							<span id="dateBoxId_tmp_table" style="display:none;">
							  		<input id="start_time_tmp_table" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
										   		~
									<input id="end_time_tmp_table" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:$('#start_time_tmp_table').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
							</span>
							<a href="#"	onclick="selectShowType('tmp_table');" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
									style="width: 18px;height: 18px;"> </a>
						</span>			
					</div>
				</div>
				<div style="height: 10px;"></div>
				<div id="tmp_table" align="center"></div>
				<div style="height: 10px;"></div>
			</div>
		</div>
		</s:elseif>
		<s:elseif test="kpi_type=='binlog_cache'">
			<div class="mainbox-kpi" style="width: 100%;border: 0px solid #FFFFFF;">
	 		<div class="item">
				<div class="top">
					<div class="ml5"><font color="orange" size="3" style="margin-right: 10%;">异常</font>
						<select id="show_binlog_cache" onchange="selectShowType('binlog_cache')" style="width:120px;">
							<option value="PS-04-09-001">全索引的扫描次数</option>
							<option value="PS-04-09-002">日志缓存的事务数量</option>
							<option value="PS-04-09-003">非事务语句使用二进制日志缓存</option>
							<option value="PS-04-09-004">临时文件缓存的非事务数量</option>
						</select>
						<span>
					  		时间：
							<select onchange="chanage_binlog_cache(this)" id="time_binlog_cache">
							    <option value="oneHour" selected="selected">1小时</option>
							    <option value="fiveHour">5小时</option>
					  			<option value="thisMonth">本月</option>
					  			<option value="lastMonth">上月</option>
					  			<option value="selfDate">自定义</option>
					  		</select>
							<span id="dateBoxId_binlog_cache" style="display:none;">
							  		<input id="start_time_binlog_cache" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
										   		~
									<input id="end_time_binlog_cache" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:$('#start_time_binlog_cache').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
							</span>
							<a href="#"	onclick="selectShowType('binlog_cache');" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
									style="width: 18px;height: 18px;"> </a>
						</span>			
					</div>
				</div>
				<div style="height: 10px;"></div>
				<div id="binlog_cache" align="center"></div>
				<div style="height: 10px;"></div>
			</div>
		</div>
		</s:elseif>
  	</div>
  </s:form>
  </body> 
</html>