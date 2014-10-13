<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/ah/paas/statistics/css/statistics.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/table.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
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
		var id = $("#id").val();
		var node_type = $("#node_type").val();
		$.ajax({
			type:"post",
			url:"oracleTab_showHealthLimitChart.do?id="+id+"&node_type="+node_type,
			dataType:"json",
			async:false,
			cache:false,
			success:function(data){
				var resultXml = data.jsonXml;	
				var chart = new FusionCharts("FusionCharts/AngularGauge.swf","chat","350","180","0","1");
				chart.setJSONData(resultXml);
				chart.render("AngularGauge");
			}
		});
		showAverage();
		
		$("[name='showDBInstance']").click(function(){
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
		
		//严重告警
		 $("[name='serious']").unbind().live("click",function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'严重告警',
    			width: '850px',
    			height: '500px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:paasAlarm_listPaasAlarm.do?obj.event_level=0&id='+id+'&node_type='+node_type
    			});
              });
			//主要告警
            $("[name='serious1']").unbind().live("click",function(){
            	currentEdit=$(this);
        		$.dialog({
        			id:'vdi',
        			title:'主要告警',
        			width: '850px',
    				height: '500px',
        			max: true,
        		    min: true,
        		    lock:true,
        			content: 'url:paasAlarm_listPaasAlarm.do?obj.event_level=1&id='+id+'&node_type='+node_type
        			});
                  });
			//次要告警
            $("[name='serious2']").unbind().live("click",function(){
            	currentEdit=$(this);
        		$.dialog({
        			id:'vdi',
        			title:'次要告警',
        			width: '850px',
    				height: '500px',
        			max: true,
        		    min: true,
        		    lock:true,
        			content: 'url:paasAlarm_listPaasAlarm.do?obj.event_level=2&id='+id+'&node_type='+node_type
        			});
                  });
			//不确定告警
            $("[name='serious3']").unbind().live("click",function(){
            	currentEdit=$(this);
        		$.dialog({
        			id:'vdi',
        			title:'不确定告警',
        			width: '850px',
    				height: '500px',
        			max: true,
        		    min: true,
        		    lock:true,
        			content: 'url:paasAlarm_listPaasAlarm.do?obj.event_level=3&id='+id+'&node_type='+node_type
        			});
                  });
	});
	
	//ajax局部刷新之后,点击实例名称弹出的页面
	function showDBInstance(entity_id,coll_time,timeline,cycle_time){
		 $.dialog({
	       		id:'show',
	       		title:'信息',
	       		height:'550px',
	       		width:'900px',
	       		lock:true,
	       		content:'url:paasTree_tabs.do?node_type=24&server_type=32'+'&entity_id='+entity_id+'&coll_time='+coll_time+'&timeline='+timeline+'&cycle_time='+cycle_time
	       	});
	}
	//健康度历史
	function showAverage(){
		var id = $("#id").val();
		var node_type = $("#node_type").val();
		var start_time = $("#start_time").val();
    	var end_time = $("#end_time").val();
    	var time = $("#time option:selected").val();
		$.ajax({
			type:'get',
			dataType:'text',
			url:'oracleTab_showLimitAverage.do?id='+id+'&node_type='+node_type+"&start_time="+start_time+"&end_time="+end_time+'&cycle_time='+time,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/Line.swf","ChartId1","94.2%","240","0","1");
				chart1.setJSONData(msg);
				chart1.render("average");
			}
		});
	}
	//点击图形上的点,执行的方法,在service中写的,link的属性实现的
	function showInstanceList(busi_id,entity_id,cycle_time,coll_time,timeline){
		$.ajax({
			type:'post',
			dataType:'json',
			url:'oracleTab_showInstanceListForBusiNode.do?id='+busi_id+'&coll_time='+coll_time+'&cycle_time='+cycle_time,
			success:function(data){
			 	$("#tbTab tr:not(:first)").remove(); 
				var resultList = data.list;
				if (resultList != null) {
					for ( var i = 0; i < resultList.length; i++) {
						$("<tr><td align=\"center\"><a href=\"#\" onclick='showDBInstance(\""+resultList[i].entity_id +"\",\""
										+ resultList[i].coll_time +"\",\""+ resultList[i].timeline+"\",\""+ resultList[i].cycle_time+"\")'><span class=\"value\">"
										+ resultList[i].entity_name
										+ "</span></a></td><td align=\"center\">"
										+ resultList[i].entity_type
										+ "</td><td align=\"center\">"
										+ resultList[i].host_address
										+ "</td><td align=\"center\">"
										+ resultList[i].host_port
										+ "</td><td align=\"center\">"
										+ resultList[i].coll_time
										+ "</td><td align=\"center\"><a href=\"#\"onclick='showDBInstance(\""+resultList[i].entity_id+"\",\""
										+ resultList[i].coll_time +"\",\""+ resultList[i].timeline+"\",\""+ resultList[i].cycle_time+"\")'><span class=\"value\">"
										+ resultList[i].kpi_value_last
										+ "</span></a></td></tr>")
								.insertAfter(
										$("#tbTab tr:eq(" + i+ ")"));
					}
				}
				$("#mainboxservice").show();
			}
		});
	}
	 function chanage_time(obj){
        	$("#dateBoxId").hide();
        	if(obj.value == 'selfDate'){
        		$("#dateBoxId").show();
        	}
        }
</script>
</head>
<body class="pop-body scrollbody">
	<s:hidden name="id" id="id"></s:hidden>
	<s:hidden name="node_type" id="node_type"></s:hidden>
	<s:form action="" method="post"  id="theForm">
		<div class="mainboxBus" style="overflow-y: auto;width: 100%;">
			<div class="title" style="margin-top: 18px;"><h2 class="datacenter dc-tt mgt-15"><span class="txt">业务名称:<s:property value="paasEntityObj.nodeName"/></span></h2></div>
			<div class="item" style="width: 46.5%;">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">摘要</font></div>
				</div>
				<div>
					<div class="right">
						<ul class="dc-list">
							<li style="width: 135px;">
		            			<i class="zhuji"></i><span class="txt">数据库:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.dbCount"/></font></span>
		            		</li>		
		     			</ul>  
						<ul class="dc-list">
							<li style="width: 135px;">
		            			<i class="zhuji"></i><span class="txt">数据库实例:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.instanceCount"/></font></span>
		            		</li>		
		        			<li style="width: 100px;">
		            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.normalCount"/></font></span>
		        			</li>
		        			<li style="width: 100px;">
		           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.unusualCount"/></font></span>
		         			</li>
		     			</ul>  
		     			<ul class="dc-list">
							<li style="width: 135px;">
		            			<i class="zhuji"></i><span class="txt">数据库服务:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.serverCount"/></font></span>
		            		</li>		
		     			</ul>  
					</div>
				</div>
			</div>
			<div class="item" style="width: 48%;">
	  			<div class="top">
	  				<div class="ml5"><font color="orange" size="3">最新业务健康度</font></div>
	  			</div>
	  			<div id="AngularGauge" align="center"></div>
	  		</div>	
	  		<div class="item" style="width: 97%;">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">告警</font></div>
				</div>
				<div>
					<div class="right">
		     			<ul class="dc-list">
							<li style="width: 135px;">
		            			<i class="zhuji"></i><span class="txt">严重告警:</span><span class="num blue-num"><font style="font-weight:bold;">
		            			<a href="javascript:;" name="serious"><s:property value="paasEntityObj.seriousAlarmCount"/></a></font></span>
		            		</li>		
		        			<li style="width: 135px;">
		            			<i class="zhuji"></i><span class="txt">主要告警:</span><span class="num blue-num"><font style="font-weight:bold;">
		            			<a href="javascript:;" name="serious1"><s:property value="paasEntityObj.mainAlarmCount"/></a></font></span>
		        			</li>
							<li style="width: 135px;">
		            			<i class="zhuji"></i><span class="txt">次要告警:</span><span class="num blue-num"><font style="font-weight:bold;">
		            			<a href="javascript:;" name="serious2"><s:property value="paasEntityObj.minorAlarmCount"/></a></font></span>
		            		</li>		
		        			<li style="width: 135px;">
		            			<i class="zhuji"></i><span class="txt">不确定告警:</span><span class="num blue-num"><font style="font-weight:bold;">
		            			<a href="javascript:;" name="serious3"><s:property value="paasEntityObj.otherAlarmCount"/></a></font></span>
		        			</li>
		     			</ul> 
					</div>
				</div>
			</div>
		</div>
		<div class="mainboxBus" style="border: solid 0px #FFFFFF;">
			<div class="item" style="width: 98%;">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">数据库实例指标健康度</font></div>
				</div>
				<div>
					<div class="right"  style="width: 100%;margin-bottom: 5px;margin-top: 10px;">
						<table width="100%" class="ued-table" border="0" cellspacing="0" id="tab" >
							<thead>
								<tr>
									<th>实例名称</th>
									<th>健康度</th>
									<th>响应</th>
									<th>可用性</th>
									<th>负载</th>
									<th>容量</th>
									<th>资源</th>
									<th>异常</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
									<tr>
										<td align="center" entity_id="<s:property value='#theBean.entity_id'/>">
											<a href="#" name="showDBInstance"><span class="value"><s:property value="#theBean.entity_name"/></span></a>
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
											</span>&nbsp;
											<s:property value="#theBean.health_score_total" default="0"/>
										</td>
										<td align="center">
											<s:property value="#theBean.health_score_response" default="0"/>
										</td>
										<td align="center">
											<s:property value="#theBean.health_score_availability" default="0"/>
										</td>
										<td align="center">
											<s:property value="#theBean.health_score_efficiency" default="0"/>
										</td>
										<td align="center">
											<s:property value="#theBean.health_score_capacity" default="0"/>
										</td>
										<td align="center">
											<s:property value="#theBean.health_score_resource" default="0"/>
										</td>
										<td align="center">
											<s:property value="#theBean.health_score_exception" default="0"/>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				<div class="table-head">
				  <%-- <jsp:include page="../../../../sxcloud/inc/Pagination.jsp?formId=theForm" /> --%>
				</div>
				</div>
			</div>
		</div>
		<div class="mainboxDaas" style="width: 100%;">
			<div class="item" style="width:94.2%;border: 0px solid #FFFFFF;">
	  			<div class="top">
	  				<div class="ml5"><font color="orange" size="3" style="margin-right: 23%;">历史业务健康度</font>
	  					<span>
					  		时间：
							<select onchange="chanage_time(this)" id="time">
							    <option value="oneHour" selected="selected">1小时</option>
							    <option value="fiveHour">5小时</option>
					  			<option value="thisMonth">本月</option>
					  			<option value="lastMonth">上月</option>
					  			<option value="selfDate">自定义</option>
					  		</select>
							<span id="dateBoxId" style="display:none;">
							  		<input id="start_time" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
										   		~
									<input id="end_time" style="txt" type="text" size="20"  class="Wdate"
										   		onFocus="WdatePicker({minDate:$('#start_time').val(),maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										   		/>
							</span>
							<a href="#"	onclick="showAverage();" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
									style="width: 18px;height: 18px;"> </a>
						</span>	
					</div>
	  			</div>
	  			<div style="height: 10px;"></div>
				<div id="average" align="center"></div>
				<div style="height: 10px;"></div>
	  		</div>	
		</div>
		<div class="mainboxservice" style="overflow-y: auto;width: 100%;display: none;" id="mainboxservice">
			<div class="item" style="width: 98%;">
			<div class="top">
					<div class="ml5"><font color="orange" size="3">数据库实例列表</font></div>
			</div>
			<div>
				<div class="right"  style="width: 100%;margin-bottom: 5px;margin-top: 10px;">
						<table width="100%" class="ued-table" border="0" cellspacing="0" id="tbTab" >
							<thead>
								<tr>
									<th>数据库实例名称</th>
									<th>类型</th>
									<th>服务器地址</th>
									<th>端口</th>
									<th>时间</th>
									<th>健康度</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</body>