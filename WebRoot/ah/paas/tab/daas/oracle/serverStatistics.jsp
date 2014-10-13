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
			var time_efficiency = $("#time_efficiency option:selected").val();
    		var time_response=$("#time_response option:selected").val();
    		var time_resource = $("#time_resource option:selected").val();
   			$("#efficiency").insertFusionCharts({
                   swfUrl: "FusionCharts/ZoomLine.swf", 
                   dataSource: "oracleTab_listServerCharts.do?kpi_type=efficiency&entity_id="+entity_id+"&cycle_time="+time_efficiency,
                   dataFormat: "jsonurl", 
                   width: "90%", 
                   height: "346", 
                   id: "chart1"
              }); 
   			$("#response").insertFusionCharts({
                   swfUrl: "FusionCharts/ZoomLine.swf", 
                   dataSource: "oracleTab_listServerCharts.do?kpi_type=response&entity_id="+entity_id+"&cycle_time="+time_response,
                   dataFormat: "jsonurl", 
                   width: "90%", 
                   height: "346",  
                   id: "chart2"
              }); 
   			$("#resource").insertFusionCharts({
                   swfUrl: "FusionCharts/ZoomLine.swf", 
                   dataSource: "oracleTab_listServerCharts.do?kpi_type=resource&entity_id="+entity_id+"&cycle_time="+time_resource,
                   dataFormat: "jsonurl", 
                   width: "90%", 
                   height: "346", 
                   id: "chart3"
              }); 
		});
		//点击服务名字展示页面
		$("[name='showDBServer']").click(function(){
	     	$td=$(this).parent();
	        var entity_id=$td.attr("entity_id");
	        $.dialog({
	       		id:'show',
	       		title:'信息',
	       		height:'550px',
	       		width:'900px',
	       		lock:true,
	       		content:'url:paasTree_tabs.do?node_type=24&server_type=33'+'&entity_id='+entity_id
	       	});
		});
		//点击实例名字展示页面
		$("[name='showDBInstance']").click(function(){
     	$td=$(this).parent();
        var entity_id=$td.attr("instance_id");
        $.dialog({
       		id:'showDBInstance',
       		title:'信息',
       		height:'550px',
       		width:'900px',
       		lock:true,
       		content:'url:paasTree_tabs.do?node_type=24&server_type=32'+'&entity_id='+entity_id
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
    		var time_efficiency = $("#time_efficiency option:selected").val();
    		var time_response=$("#time_response option:selected").val();
    		var time_resource = $("#time_resource option:selected").val();
   			$("#efficiency").insertFusionCharts({
                   swfUrl: "FusionCharts/ZoomLine.swf", 
                   dataSource: "oracleTab_listServerCharts.do?kpi_type=efficiency&entity_id="+entity_id+"&cycle_time="+time_efficiency,
                   dataFormat: "jsonurl", 
                   width: "90%", 
                   height: "346", 
                   id: "chart1"
              }); 
   			$("#response").insertFusionCharts({
                   swfUrl: "FusionCharts/ZoomLine.swf", 
                   dataSource: "oracleTab_listServerCharts.do?kpi_type=response&entity_id="+entity_id+"&cycle_time="+time_response,
                   dataFormat: "jsonurl", 
                   width: "90%", 
                   height: "346",  
                   id: "chart2"
              }); 
   			$("#resource").insertFusionCharts({
                   swfUrl: "FusionCharts/ZoomLine.swf", 
                   dataSource: "oracleTab_listServerCharts.do?kpi_type=resource&entity_id="+entity_id+"&cycle_time="+time_resource,
                   dataFormat: "jsonurl", 
                   width: "90%", 
                   height: "346", 
                   id: "chart3"
              }); 
    	};
    	
    	function selectShowType(type){
    		var start_time_efficiency = $("#start_time_efficiency").val();
    		var end_time_efficiency = $("#end_time_efficiency").val();
    		var start_time_response = $("#start_time_response").val();
    		var end_time_response = $("#end_time_response").val();
    		var start_time_resource = $("#start_time_resource").val();
    		var end_time_resource = $("#end_time_resource").val();
    		var show_efficiency = $("#show_efficiency").val();
    		var show_response = $("#show_response").val();
    		var show_resource = $("#show_resource").val();
    		var entity_id = $("input[name='checkboxId']:checked").val();
    		
    		var time_efficiency = $("#time_efficiency option:selected").val();
    		var time_response=$("#time_response option:selected").val();
    		var time_resource = $("#time_resource option:selected").val();
    		
    		if(type=='efficiency'){
    			 $("#efficiency").updateFusionCharts({
	   	          	 dataSource: "oracleTab_listServerCharts.do?kpi="+show_efficiency+"&entity_id="+entity_id+"&kpi_type=efficiency&start_time="+start_time_efficiency+"&end_time="+end_time_efficiency+"&cycle_time="+time_efficiency
	   	         });
    		}else if(type=='response'){
	   			 $("#response").updateFusionCharts({
	   	          	 dataSource: "oracleTab_listServerCharts.do?kpi="+show_response+"&entity_id="+entity_id+"&kpi_type=response&start_time="+start_time_response+"&end_time="+end_time_response+"&cycle_time="+time_response
	   	         });
			}else if(type=='resource'){
	   			 $("#resource").updateFusionCharts({
	   	          	 dataSource: "oracleTab_listServerCharts.do?kpi="+show_resource+"&entity_id="+entity_id+"&kpi_type=resource&start_time="+start_time_resource+"&end_time="+end_time_resource+"&cycle_time="+time_resource
	   	         });
			}
    	}
    	
         function chanage_efficiency(obj){
        	$("#dateBoxId_efficiency").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_efficiency").show();
        	}
        }
        function chanage_response(obj){
        	$("#dateBoxId_response").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_response").show();
        	}
        }
         function chanage_resource(obj){
        	$("#dateBoxId_resource").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId_resource").show();
        	}
        }
</script>
</head>
<body>
	<s:hidden name="id" id="id"></s:hidden>
	<s:form action="" method="post"  id="theForm">
	<div class="mainboxservice" style="overflow-y: auto;width: 100%;">
		<div class="title" style="border: solid 0px #FFFFFF;">
			<h2 class="datacenter dc-tt mgt-15"><span class="txt">数据库服务摘要</span></h2>
		</div>
		<div class="item">
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">数据库服务:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.serverCount"/></font></span>
	            		</li>		
	     			</ul>  
				</div>
			</div>
		</div>
	</div> 
	<div class="mainboxservice" style="overflow-y: auto;width: 100%;">
		<div class="title" style="border: solid 0px #FFFFFF;">
			<h2 class="datacenter dc-tt mgt-15"><span class="txt" style="width: 60%">数据库服务列表</span>
				<%--<span style="font-size: 16px;">服务名称：</span><input type="text" id="topnum"/>
    				<a href="#"	onclick="queryList();" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
											style="width: 18px;height: 18px;"> </a>
				--%>
			</h2>
		</div>
		<div class="item" style="border: 0px solid #FFFFFF;">
			<div>
				<div class="right"  style="width: 100%;margin-bottom: 5px;margin-top: 10px;">
					<table width="100%" class="ued-table" border="0" cellspacing="0" id="tab" >
						<thead>
							<tr>
								<th>选择</th>
								<th width="25%">数据库服务名称</th>
								<th>类型</th>
								<th>所属实例</th>
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
									<td style="padding-left: 8%;" entity_id="<s:property value='#theBean.entity_id'/>">
										<a href="#" name="showDBServer"><span class="value"><s:property value="#theBean.entity_name"/></span></a>
									</td>
									<td align="center">
										<s:property value="#theBean.entity_type" default="-"/>
									</td>
									<td align="center" instance_id="<s:property value='#theBean.id'/>">
										<a href="#" name="showDBInstance"><span class="value"><s:property value="#theBean.nodeName"/></span></a>
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
				<div class="table-head">
				  <%--<jsp:include page="../../../../sxcloud/inc/Pagination.jsp?formId=theForm" />	--%>
			</div>
			</div>
		</div>
	</div>
	<div style="height: 15px;"></div>
     <div class="tabShow" style="height: 60px;">
         <div class="tabCaption tab-caption-02" >
             <ul>
               <li class="on"><span>负载</span></li>
               <li><span>响应</span></li>
               <li><span>资源</span></li>
             </ul>
         </div>
         <div class="tabContent tab-content-02 pd-10-15">
           <div class="box on">
            	<div class="mainbox-kpi" style="width: 97%;border: 0px solid #FFFFFF;">
			 		<div class="item">
						<div class="top">
							<div class="ml5"><font color="orange" size="3" ></font>
								<select id="show_efficiency" onchange="selectShowType('efficiency')" style="width:120px;">
									<option value="PS-03-04-001">执行计数</option>
									<option value="PS-03-04-002">服务物理读数</option>
									<option value="PS-03-04-004">集群等待时间</option>
									<option value="PS-03-04-005">服务call总数</option>
									<option value="PS-03-04-006">并行等待时间</option>
									<option value="PS-03-04-007">数据库时间</option>
									<option value="PS-03-04-008">物理写入数</option>
									<option value="PS-03-04-009">数据库CPU</option>
									<option value="PS-03-04-011">事物处理数</option>
									<option value="PS-03-04-012">用户I/O等待时间</option>
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
           </div>
           <DIV class="box">
            	<div class="mainbox-kpi" style="width: 97%;border: 0px solid #FFFFFF;">
		 		<div class="item">
					<div class="top">
						<div class="ml5"><font color="orange" size="3" ></font>
							<select id="show_response" onchange="selectShowType('response')" style="width:180px;">
								<option value="PS-03-05-002">每次用户调用的服务响应时间</option>
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
           </DIV>
           <DIV class="box">
            	<div class="mainbox-kpi" style="width: 97%;border: 0px solid #FFFFFF;">
			 		<div class="item">
						<div class="top">
							<div class="ml5"><font color="orange" size="3" ></font>
								<select id="show_resource" onchange="selectShowType('resource')" style="width:180px;">
									<option value="PS-03-07-002">每次用户调用的服务CPU时间</option>
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
							</td>
							</div>
						</div>
						<div style="height: 10px;"></div>
						<div id="resource" align="center"></div>
						<div style="height: 10px;"></div>
					</div>
				</div>
           </DIV>
         </div>
     </div>
	</s:form>
</body>