<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/ah/paas/statistics/css/statistics.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/table.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<script type="text/javascript">
	$(function(){
		$("input[name=checkboxId]").first().attr("checked",true);
		
		showCharts();
		
		if($.browser.msie){
			$("input:checkbox").click(function(){
				this.blur();
				this.focus();
			});
		}
		$("input[name=checkboxId]").change(function(){
			var entity_id = $(this).val();
			var time_response=$("#time_response option:selected").val();
    		var time_efficiency = $("#time_efficiency option:selected").val();
    		var time_capacity=$("#time_capacity option:selected").val();
    		var time_exception=$("#time_exception option:selected").val();
   			$("#response").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "weblogicTab_listDataSourceCharts.do?kpi_type=response&entity_id="+entity_id+"&cycle_time="+time_response,
                    dataFormat: "jsonurl", 
                    width: "90%", 
                    height: "346", 
                    id: "chart1"
               }); 
    			$("#efficiency").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "weblogicTab_listDataSourceCharts.do?kpi_type=efficiency&entity_id="+entity_id+"&cycle_time="+time_efficiency,
                    dataFormat: "jsonurl", 
                    width: "90%", 
                    height: "346", 
                    id: "chart2"
               }); 
    			$("#capacity").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "weblogicTab_listDataSourceCharts.do?kpi_type=capacity&entity_id="+entity_id+"&cycle_time="+time_capacity,
                    dataFormat: "jsonurl", 
                    width: "90%", 
                    height: "346", 
                    id: "chart3"
               }); 
    			$("#exception").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "weblogicTab_listDataSourceCharts.do?kpi_type=exception&entity_id="+entity_id+"&cycle_time="+time_exception,
                    dataFormat: "jsonurl", 
                    width: "90%", 
                    height: "346", 
                    id: "chart5"
               }); 
		});
		
		$("[name='showDataSource']").click(function(){
	     	$td=$(this).parent();
	        var entity_id=$td.attr("entity_id");
	        $.dialog({
	       		id:'show',
	       		title:'信息',
	       		height:'550px',
	       		width:'800px',
	       		lock:true,
	       		content:'url:paasTree_tabs.do?node_type=34&server_type=51'+'&entity_id='+entity_id
	       	});
		});
	});
	$(function(){
		$(".tabShow .tabCaption li").click(
			function(){
				$(this).addClass("on").siblings().removeClass("on");
				var index=$(this).index();
				$(this).parents(".tabShow").find(".tabContent").children(".box").eq(index).addClass("on").siblings().removeClass("on");
			})	   
		});
		
		
    	function showCharts(){
    		var entity_id = $("input[name='checkboxId']:checked").val();
    		var time_response=$("#time_response option:selected").val();
    		var time_efficiency = $("#time_efficiency option:selected").val();
    		var time_capacity=$("#time_capacity option:selected").val();
    		var time_exception=$("#time_exception option:selected").val();
    		
    			$("#response").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "weblogicTab_listDataSourceCharts.do?kpi_type=response&entity_id="+entity_id+"&cycle_time="+time_response,
                    dataFormat: "jsonurl", 
                    width: "90%", 
                    height: "346", 
                    id: "chart1"
               }); 
    			$("#efficiency").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "weblogicTab_listDataSourceCharts.do?kpi_type=efficiency&entity_id="+entity_id+"&cycle_time="+time_efficiency,
                    dataFormat: "jsonurl", 
                    width: "90%", 
                    height: "346", 
                    id: "chart2"
               }); 
    			$("#capacity").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "weblogicTab_listDataSourceCharts.do?kpi_type=capacity&entity_id="+entity_id+"&cycle_time="+time_capacity,
                    dataFormat: "jsonurl", 
                    width: "90%", 
                    height: "346", 
                    id: "chart3"
               }); 
    			$("#exception").insertFusionCharts({
                    swfUrl: "FusionCharts/ZoomLine.swf", 
                    dataSource: "weblogicTab_listDataSourceCharts.do?kpi_type=exception&entity_id="+entity_id+"&cycle_time="+time_exception,
                    dataFormat: "jsonurl", 
                    width: "90%", 
                    height: "346", 
                    id: "chart5"
               }); 
    	};
    	
    function selectShowType(type){
    		var entity_id = $("input[name='checkboxId']:checked").val();
    		var kpi_type = $("#kpi_type").val();
    		var show_response = $("#show_response").val();
    		var show_efficiency = $("#show_efficiency").val();
    		var show_capacity = $("#show_capacity").val();
    		var show_exception = $("#show_exception").val();
    		var start_time_response = $("#start_time_response").val();
    		var end_time_response = $("#end_time_response").val();
    		var start_time_efficiency = $("#start_time_efficiency").val();
    		var end_time_efficiency = $("#end_time_efficiency").val();
    		var start_time_capacity = $("#start_time_capacity").val();
    		var end_time_capacity = $("#end_time_capacity").val();
    		var start_time_exception = $("#start_time_exception").val();
    		var end_time_exception = $("#end_time_exception").val();
    		var time_response=$("#time_response option:selected").val();
    		var time_efficiency = $("#time_efficiency option:selected").val();
    		var time_capacity=$("#time_capacity option:selected").val();
    		var time_exception=$("#time_exception option:selected").val();
    		
    		if(type=='response'){
    			 $("#response").updateFusionCharts({
	   	          	 dataSource:  "weblogicTab_listDataSourceCharts.do?kpi="+show_response+"&entity_id="+entity_id+"&kpi_type=response&start_time="+start_time_response+"&end_time="+end_time_response+"&cycle_time="+time_response
	   	         });
    		}else if(type=='efficiency'){
	   			 $("#efficiency").updateFusionCharts({
	   	          	 dataSource:  "weblogicTab_listDataSourceCharts.do?kpi="+show_efficiency+"&entity_id="+entity_id+"&kpi_type=efficiency&start_time="+start_time_efficiency+"&end_time="+end_time_efficiency+"&cycle_time="+time_efficiency
	   	         });
			}else if(type=='capacity'){
	   			 $("#capacity").updateFusionCharts({
	   	          	 dataSource:  "weblogicTab_listDataSourceCharts.do?kpi="+show_capacity+"&entity_id="+entity_id+"&kpi_type=capacity&start_time="+start_time_capacity+"&end_time="+end_time_capacity+"&cycle_time="+time_capacity
	   	         });
			}else if(type=='exception'){
	   			 $("#exception").updateFusionCharts({
	   	          	 dataSource:  "weblogicTab_listDataSourceCharts.do?kpi="+show_exception+"&entity_id="+entity_id+"&kpi_type=exception&start_time="+start_time_exception+"&end_time="+end_time_exception+"&cycle_time="+time_exception
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
        function chanage_exception(obj){
        	$("#dateBoxId_exception").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_exception").show();
        	}
        }
</script>
</head>
<body>
	<s:hidden name="id" id="id"></s:hidden>
	<s:form action="" method="post"  id="theForm">
	 <div class="mainboxservice" style="overflow-y: auto;width: 100%;">
		<div class="title" style="border: solid 0px #FFFFFF;">
			<h2 class="datacenter dc-tt mgt-15"><span class="txt">数据源摘要</span></h2>
		</div>
		<div class="item" style="width: 97%;">
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">数据源:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.dsCount"/></font></span>
	            		</li>		
	     			</ul>  
				</div>
			</div>
		</div>
	</div> 
	<div class="mainboxservice" style="overflow-y: auto;width: 100%;">
		<div class="title" style="border: solid 0px #FFFFFF;">
			<h2 class="datacenter dc-tt mgt-15"><span class="txt" style="width: 60%">数据源列表</span></h2>
		</div>
		<div class="item" style="border: 0px solid #FFFFFF;">
			<div>
				<div class="right"  style="width: 100%;margin-bottom: 5px;margin-top: 10px;">
					<table width="100%" class="ued-table" border="0" cellspacing="0" id="tab" >
						<thead>
							<tr>
								<th>选择</th>
								<th width="40%;">数据源名称</th>
								<th>类型</th>
								<th>服务器地址</th>
								<th>端口</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="resultList" id="theBean">
								<tr>
									<td align="center">
										<input type="checkbox" name="checkboxId" value="<s:property value="#theBean.entity_id"/>" /> 
									</td>
									<td style="padding-left: 15%;" entity_id="<s:property value='#theBean.entity_id'/>">
										<a href="#" name="showDataSource"><span class="value"><s:property value="#theBean.entity_name"/></span></a>
									</td>
									<td align="center">
										<s:if test="#theBean.entity_type == 51">
											Data Source
										</s:if>
										<s:else>
											-
										</s:else>
									</td>
									<td align="center">
										<s:property value="#theBean.host_address" default="-"/>
									</td>
									<td align="center">
										<s:property value="#theBean.host_port" default="-"/>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div class="table-head"><%--
				  <jsp:include page="../../../../sxcloud/inc/Pagination.jsp?formId=theForm" />
				--%></div>
			</div>
		</div>
	</div>
	<div style="height: 15px;"></div>
     <div class="tabShow" style="height: 60px;">
         <div class="tabCaption tab-caption-02" >
             <ul>
               <li class="on"><span>响应</span></li>
               <li><span>负载</span></li>
               <li><span>容量</span></li>
               <li><span>异常</span></li>
             </ul>
         </div>
         <div class="tabContent tab-content-02 pd-10-15">
           <div class="box on">
            	<div class="mainbox-kpi" style="width: 97%;border: 0px solid #FFFFFF;">
			 		<div class="item">
						<div class="top">
							<div class="ml5"><font color="orange" size="3" ></font>
								<select id="show_response" onchange="selectShowType('response')" style="width:120px;">
									<option value="PS-05-05-002">数据源连接创建时间</option>
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
           </div>
           <DIV class="box">
            	<div class="mainbox-kpi" style="width: 97%;border: 0px solid #FFFFFF;">
		 			<div class="item">
						<div class="top">
							<div class="ml5"><font color="orange" size="3" ></font>
								<select id="show_efficiency" onchange="selectShowType('efficiency')" style="width:120px;">
									<option value="PS-05-04-003">数据源连接请求数</option>
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
           </DIV>
           <DIV class="box">
            	<div class="mainbox-kpi" style="width: 97%;border: 0px solid #FFFFFF;">
			 		<div class="item">
						<div class="top">
							<div class="ml5"><font color="orange" size="3" ></font>
								<select id="show_capacity" onchange="selectShowType('capacity')" style="width:120px;">
									<option value="PS-05-02-003">数据源连接池大小</option>
									<option value="PS-05-02-004">数据源可用连接数</option>
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
           </DIV>
           <DIV class="box">
            	<div class="mainbox-kpi" style="width: 97%;border: 0px solid #FFFFFF;">
			 		<div class="item">
						<div class="top">
							<div class="ml5"><font color="orange" size="3" ></font>
								<select id="show_exception" onchange="selectShowType('exception')" style="width:120px;">
									<option value="PS-05-06-003">数据源不可用连接数</option>
									<option value="PS-05-06-001">数据源连接请求失败数</option>
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
           </DIV>
         </div>
     </div>
	</s:form>
</body>