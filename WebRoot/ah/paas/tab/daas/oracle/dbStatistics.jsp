<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/ah/paas/statistics/css/statistics.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/table.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<style type="text/css">
	.percentage { 
	vertical-align:middle;  
	display:inline-block; 
	background: url(sxcloud/images/nresources/greyp.gif) no-repeat; 
	width:73px; 
	height:12px; 
	margin-top: 4px;}
	.percentage b{ display:block; height:14px; background:url(sxcloud/images/nresources/greenp.gif) no-repeat; }
	.percentage b.red {background:url(sxcloud/images/nresources/redp.gif) no-repeat;}
</style>
<script type="text/javascript">
$(function(){
	$("input[name=checkboxId]").first().attr("checked",true);
	showChart();
	if($.browser.msie){
		$("input:checkbox").click(function(){
			this.blur();
			this.focus();
		});
	}
	$("input[name=checkboxId]").change(function(){
		var entity_id = $(this).val();
		var show_efficiency = $("#show_efficiency").val();
    	var show_capacity = $("#show_capacity").val();
   		var start_time = $("#start_time").val();
   		var end_time = $("#end_time").val();
   		var time_capacity = $("#time_capacity option:selected").val();
   		var time_efficiency = $("#time_efficiency option:selected").val();
		$.ajax({
			type:'post',
			url:'oracleTab_queryDBListByEntityId.do?entity_id='+entity_id,
			dataType:'json',
			success:function(data){
				$("#entity_name").text(data.daasObj.entity_name);
				$("#health_score_efficiency").text(data.daasObj.health_score_efficiency);
				$("#userblock_count_racdb").text(data.daasObj.userblock_count_racdb);
				$("#userblock_count_racdb_score").text(data.daasObj.userblock_count_racdb_score);
				
				$("#health_score_capacity").text(data.daasObj.health_score_capacity);
				$("#health_score_efficiency").text(data.daasObj.health_score_efficiency);
				$("#problemtbsp_pctused_racdb").text(data.daasObj.problemtbsp_pctused_racdb);
				$("#problemtbsp_pctused_racdb_score").text(data.daasObj.problemtbsp_pctused_racdb_score);
				$("#scn_growth_statistics_scn_health_racdb").text(data.daasObj.scn_growth_statistics_scn_health_racdb);
				$("#scn_growth_statistics_scn_health_racdb_score").text(data.daasObj.scn_growth_statistics_scn_health_racdb_score);
				
				$("#entityCount").text(data.daasObj.entityCount);
				if(data.daasObj.entityCount == 0){
					$("#isShowInstanceList").hide();
				}else{
					$("#isShowInstanceList").show();
				}
				$("#tbTab tr:not(:first)").remove(); 
				var resultList = data.daasObj.resultList;
				if (resultList != null) {
					for ( var i = 0; i < resultList.length; i++) {
						var health_score_total =  resultList[i].health_score_total;
						var tbspallocation_spaceallocated  = resultList[i].tbspallocation_spaceallocated;
						var instance_efficiency_cpuusage_ps = resultList[i].instance_efficiency_cpuusage_ps;
						var memory_usage_total_memory = resultList[i].memory_usage_total_memory;
						var response_status = resultList[i].response_status;
						if(health_score_total == null || health_score_total == ""){
							health_score_total = "0";	
						}
						if(tbspallocation_spaceallocated == null || tbspallocation_spaceallocated == ""){
							tbspallocation_spaceallocated = "0";	
						}
						if(instance_efficiency_cpuusage_ps == null || instance_efficiency_cpuusage_ps == ""){
							instance_efficiency_cpuusage_ps = "0";	
						}
						if(memory_usage_total_memory == null || memory_usage_total_memory == ""){
							memory_usage_total_memory = "0";	
						}
						if(response_status == null || response_status == ""){
							response_status = "未知";	
						}
						$("<tr><td align=\"center\"><a href=\"#\" onclick='showInstance(\""+resultList[i].entity_id +"\")'><span class=\"value\">"
										+ resultList[i].entity_name
										+ "</span></a></td><td align=\"center\"><span class=\"percentage\"><b style=\"width: "+health_score_total +"\"></b></span>"
										+ health_score_total
										+ "</td><td align=\"center\"><span class=\"percentage\"><b style=\"width: "+tbspallocation_spaceallocated +"\"></b></span>"
										+ tbspallocation_spaceallocated
										+ "%</td><td align=\"center\">"
										+ instance_efficiency_cpuusage_ps
										+ "</td><td align=\"center\">"
										+ memory_usage_total_memory
										+ "G</td><td align=\"center\">"
										+ response_status
										+ "</td></tr>")
								.insertAfter(
										$("#tbTab tr:eq(" + i+ ")"));
					}
				}
			}
		});	
  	         $("#efficiency").insertFusionCharts({
                   swfUrl: "FusionCharts/ZoomLine.swf", 
                   dataSource: "oracleTab_listDBCharts.do?kpi_type=efficiency&entity_id="+entity_id+"&cycle_time="+time_capacity,
                   dataFormat: "jsonurl", 
                   width: "97%", 
                   height: "346", 
                   id: "chart1"
              });
   			$("#capacity").insertFusionCharts({
                   swfUrl: "FusionCharts/ZoomLine.swf", 
                   dataSource: "oracleTab_listDBCharts.do?kpi_type=capacity&entity_id="+entity_id+"&cycle_time="+time_efficiency,
                   dataFormat: "jsonurl", 
                   width: "97%", 
                   height: "346", 
                   id: "chart2"
              }); 
	});
	//点击数据库名字展示
	$("[name='showDB']").click(function(){
     	$td=$(this).parent();
        var entity_id=$td.attr("entity_id");
        $.dialog({
       		id:'show',
       		title:'信息',
       		height:'550px',
       		width:'900px',
       		lock:true,
       		content:'url:paasTree_tabs.do?node_type=24&server_type=31'+'&entity_id='+entity_id
       	});
	});
	//点击实例名字展示
	$("[name='showInstance']").click(function(){
     	$td=$(this).parent();
        var entity_id=$td.attr("entity_id");
        $.dialog({
       		id:'show',
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
	    
    	function showChart(){
    		var entity_id = $("input[name='checkboxId']:checked").val();
    		var time_capacity = $("#time_capacity option:selected").val();
    		var time_efficiency = $("#time_efficiency option:selected").val();
   			$("#efficiency").insertFusionCharts({
                   swfUrl: "FusionCharts/ZoomLine.swf", 
                   dataSource: "oracleTab_listDBCharts.do?kpi_type=efficiency&entity_id="+entity_id+"&cycle_time="+time_capacity,
                   dataFormat: "jsonurl", 
                   width: "97%", 
                   height: "346", 
                   id: "chart1"
             }); 
             $("#capacity").insertFusionCharts({
                   swfUrl: "FusionCharts/ZoomLine.swf", 
                   dataSource: "oracleTab_listDBCharts.do?kpi_type=capacity&entity_id="+entity_id+"&cycle_time="+time_efficiency,
                   dataFormat: "jsonurl", 
                   width: "97%", 
                   height: "346", 
                   id: "chart2"
             }); 
    	}
    	
    	function selectShowType(type){
    		var start_time_efficiency = $("#start_time_efficiency").val();
    		var end_time_efficiency = $("#end_time_efficiency").val();
    		var start_time_capacity = $("#start_time_capacity").val();
    		var end_time_capacity = $("#end_time_capacity").val();
    		var show_efficiency = $("#show_efficiency").val();
    		var show_capacity = $("#show_capacity").val();
    		var time_capacity=$("#time_capacity option:selected").val();
    		var time_efficiency = $("#time_efficiency option:selected").val();
    		var entity_id = $("input[name='checkboxId']:checked").val();
    		if(type=='capacity'){
    			 $("#capacity").updateFusionCharts({
	   	          	 dataSource: "oracleTab_listDBCharts.do?kpi="+show_capacity+"&entity_id="+entity_id+"&kpi_type=capacity&start_time="+start_time_capacity+"&end_time="+end_time_capacity+"&cycle_time="+time_capacity
	   	         });
    		}else if(type=='efficiency'){
	   			 $("#efficiency").updateFusionCharts({
	   	          	dataSource: "oracleTab_listDBCharts.do?kpi="+show_efficiency+"&entity_id="+entity_id+"&kpi_type=efficiency&start_time="+start_time_efficiency+"&end_time="+end_time_efficiency+"&cycle_time="+time_efficiency
	   	         });
			}
    	}
    	//ajax局部刷新之后,点击实例名称弹出的页面
    	function showInstance(entity_id){
    		 $.dialog({
	       		id:'showInstance',
	       		title:'信息',
	       		height:'550px',
	       		width:'900px',
	       		lock:true,
	       		content:'url:paasTree_tabs.do?node_type=24&server_type=32'+'&entity_id='+entity_id
	       	});
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
	<s:hidden name="id" id="id"></s:hidden>
	<s:form action="" method="post"  id="theForm">
	<div class="mainboxservice" style="overflow-y: auto;width: 100%;">
		<div class="title" style="border: 0px solid #FFFFFF;"><h2 class="datacenter dc-tt mgt-15"><span class="txt">数据库列表</span></h2></div>
		<div class="item" style="border: 0px solid #FFFFFF;">
			<div>
				<div class="right"  style="width: 100%;margin-bottom: 5px;margin-top: 10px;">
					<table width="100%" class="ued-table" border="0" cellspacing="0" id="tab" >
						<thead>
							<tr>
								<th>选择</th>
								<th width="25%;">数据库名称</th>
								<th>类型</th>
								<th>服务器地址</th>
								<th>端口</th>
								<th>版本</th>
								<th>所有者</th>
								<th>描述</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="resultList" id="theBean">
								<tr>
									<td align="center">
										<input type="checkbox" name="checkboxId" value="<s:property value="#theBean.entity_id"/>" /> 
									</td>
									<td style="padding-left: 60px;" entity_id="<s:property value='#theBean.entity_id'/>">
										<a href="#" name="showDB"><span class="value"><s:property value="#theBean.entity_name"/></span></a>
									</td>
									<td align="center">
										<s:if test="#theBean.entity_type == 31">
											Oracle DB
										</s:if>
										<s:else>
											—
										</s:else>
									</td>
									<td align="center">
										<s:property value="#theBean.host_address"/>
									</td>
									<td align="center">
										<s:property value="#theBean.host_port"/>
									</td>
									<td align="center">
										<s:property value="#theBean.oracle_db_version" default="-"/>
									</td>
									<td align="center">
										<s:property value="#theBean.oracle_db_owner" default="-"/>
									</td>
									<td align="center">
										<s:property value="#theBean.oracle_db_desc" default="-"/>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="mainboxBus" style="overflow-y: auto;width: 100%;">
		<div class="title">
				<h2 class="datacenter dc-tt mgt-15"><span class="txt">数据库名称:<s:property value="daasDBObj.entity_name"/></span></h2>
		</div>
		<div class="item" style="width: 47%;">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">容量</font>
				<font color="orange" size="2" style="margin-left: 212px;">健康度：</font>
				<font color="orange" size="2" id="health_score_capacity"><s:property value="daasDBObj.health_score_capacity"/></font>
				</div>
			</div>
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 180px;">
	            			<i class="zhuji"></i>
	            			<span class="txt">表空间占用率:</span>
	            			<span class="num blue-num"><font style="font-weight:bold;" id="problemtbsp_pctused_racdb"><s:property value="daasDBObj.problemtbsp_pctused_racdb"/></font></span>
	            		</li>	
	            		<li style="width: 10px;border: 0px solid #FFFFFF"></li>
						<li>
	            			<i class="zhuji"></i><span class="txt">健康度:</span><span class="num blue-num">
	            			<font style="font-weight:bold;" id="problemtbsp_pctused_racdb_score"><s:property value="daasDBObj.problemtbsp_pctused_racdb_score"/></font></span>
	            		</li>		
		     		</ul> 
		     		<ul class="dc-list">
						<li style="width: 180px;">
	            			<i class="zhuji"></i>
	            			<span class="txt">SCN健康值:</span>
	            			<span class="num blue-num"><font style="font-weight:bold;" id="scn_growth_statistics_scn_health_racdb"><s:property value="daasDBObj.scn_growth_statistics_scn_health_racdb"/></font></span>
	            		</li>	
	            		<li style="width: 10px;border: 0px solid #FFFFFF"></li>
						<li>
	            			<i class="zhuji"></i><span class="txt">健康度:</span><span class="num blue-num">
	            			<font style="font-weight:bold;" id="scn_growth_statistics_scn_health_racdb_score"><s:property value="daasDBObj.scn_growth_statistics_scn_health_racdb_score"/></font></span>
	            		</li>		
		     		</ul> 
				</div>
			</div>
		</div>
		<div class="item" style="width: 47%;">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">负载</font>
					<font color="orange" size="2" style="margin-left: 45%;">健康度：</font>
					<font color="orange" size="2" id="health_score_efficiency"><s:property value="daasDBObj.health_score_efficiency"/></font>
				</div>
			</div>
			<div>
				<div class="right">
					<ul class="dc-list">
						<li>
	            			<i class="zhuji"></i><span class="txt">阻塞会话数:</span><span class="num blue-num">
	            			<font style="font-weight:bold;" id="userblock_count_racdb"><s:property value="daasDBObj.userblock_count_racdb"/></font></span>
	            		</li>		
	            		<li style="width: 50px;border: 0px solid #FFFFFF"></li>
						<li>
	            			<i class="zhuji"></i><span class="txt">健康度:</span><span class="num blue-num">
	            			<font style="font-weight:bold;" id="userblock_count_racdb_score"><s:property value="daasDBObj.userblock_count_racdb_score"/></font></span>
	            		</li>		
		     		</ul> 
				</div>
			</div>
		</div>
		<div class="item" style="width: 97%;" id="isShowInstanceList">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">数据库实例</font>
				<font color="orange" size="2" style="margin-left: 82%;" id="entityCount"><s:property value="paasEntityObj.instanceCount"/></font><font color="orange" size="2">个</font>
				</div>
			</div>
			<div>
				<div class="right" style="width: 100%;margin-bottom: 10px;margin-top: 10px;">
					<table width="100%" class="ued-table" border="0" cellspacing="0" id="tbTab"  >
						<thead>
							<tr>
								<th>实例名称</th>
								<th>实例健康度</th>
								<th>表空间使用率</th>
								<th>CPU占用量</th>
								<th>内存使用量</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="instanceList" id="theBean">
								<tr>
									<td align="center" entity_id="<s:property value='#theBean.entity_id'/>">
										<a href="#" name="showInstance"><span class="value"><s:property value="#theBean.entity_name"/></span></a>
									</td>
									<td align="center">
										<span class="percentage">
											<s:if test="#theBean.health_score_total == 100">
												<b style='width: <s:property value="#theBean.health_score_total" default="0"/>'>
												</b>
											</s:if>
											<s:else>
												<b class="red" style='width: <s:property value="#theBean.health_score_total" default="0"/>'>
												</b>
											</s:else>
										</span>
										<s:property value="#theBean.health_score_total" default="0"/>
									</td>
									<td align="center">
										 <span class="percentage">
											<b style='width: <s:property value="#theBean.tbspallocation_spaceallocated" default="0"/>%'>
											</b>
										</span> 
										<s:property value="#theBean.tbspallocation_spaceallocated" default="0"/>%
									</td>
									<td align="center">
										<s:property value="#theBean.instance_efficiency_cpuusage_ps" default="0"/>
									</td>
									<td align="center">
										<s:property value="#theBean.memory_usage_total_memory" default="0"/>G
									</td>
									<td align="center">
										<s:property value="#theBean.response_status" default="未知"/>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div style="height: 10px;"></div>
     <div class="tabShow" style="height: 60px;">
         <div class="tabCaption tab-caption-02" >
             <ul>
               <li class="on"><span>容量</span></li>
               <li><span>负载</span></li>
             </ul>
         </div>
         <div class="tabContent tab-content-02 pd-10-15">
           <div class="box on">
            	<div class="mainbox-kpi" style="width: 97%;border: 0px solid #FFFFFF;">
			 		<div class="item">
						<div class="top">
							<div class="ml5">
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
           </div>
           <DIV class="box">
            	<div class="mainbox-kpi" style="width: 97%;border: 0px solid #FFFFFF;">
			 		<div class="item">
						<div class="top">
							<div class="ml5">
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
           </div>
         </div>
     </div>
	</s:form>
</body>