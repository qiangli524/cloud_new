<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css" />
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
		showBusiTopN();
		showLimitChar();
		var id = $("#id").val();
		var node_type = $("#node_type").val();
		$("[name='showDBInstance']").click(function(){
	     	$td=$(this).parent();
	        var entity_id=$td.attr("entity_id");
	        $.dialog({
	       		id:'show',
	       		title:'信息',
	       		height:'550px',
	       		width:'900px',
	       		lock:true,
	       		content:'url:paasTree_tabs.do?node_type=34&server_type=52'+'&entity_id='+entity_id
	       	});
		});
		// 总的严重告警
	 $("[name='serious_all']").unbind().live("click",function(){
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
		// 总的主要告警
            $("[name='serious1_all']").unbind().live("click",function(){
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
         // 总的次要告警
            $("[name='serious2_all']").unbind().live("click",function(){
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
         // 总的不确定告警
            $("[name='serious3_all']").unbind().live("click",function(){
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
	
	//业务topN
	function showBusiTopN(){
		var id = $("#id").val();
		var node_type = $("#node_type").val();
		var top_num = $("#topnum").val();
		$.ajax({
			type:'get',
			dataType:'text',
			url:'weblogicTab_showBusiTopN.do?id='+id+'&node_type='+node_type+'&top_num='+top_num,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/Column3D.swf","ChartId1","94.2%","240","0","1");
				chart1.setJSONData(msg);
				chart1.render("business");
			}
		});
	}
	//点击topN调用的方法
	function showChart(busiId){
		$("#busiId").attr("value",busiId);
		$.ajax({
			type:"post",
			url:"weblogicTab_showBusiChart.do?id="+busiId,
			dataType:"json",
			success:function(data){
				$("#entity_name").text(data.paasObj.nodeName);
       			$("#dsCount").text(data.paasObj.dsCount);
       			$("#instanceCount").text(data.paasObj.instanceCount);
       			$("#normalCount").text(data.paasObj.normalCount);
       			$("#unusualCount").text(data.paasObj.unusualCount);
       			
       			$("#seriousAlarmCount").text(data.paasObj.seriousAlarmCount);
       			$("#mainAlarmCount").text(data.paasObj.mainAlarmCount);
       			$("#minorAlarmCount").text(data.paasObj.minorAlarmCount);
       			$("#otherAlarmCount").text(data.paasObj.otherAlarmCount);
       			
       			$("#tbTab tr:not(:first)").remove(); 
       			var resultList = data.paasObj.resultList;
       			if (resultList != null) {
					for ( var i = 0; i < resultList.length; i++) {
						var health_score_total =  resultList[i].health_score_total;
						var health_score_response  = resultList[i].health_score_response;
						var health_score_efficiency = resultList[i].health_score_efficiency;
						var health_score_capacity = resultList[i].health_score_capacity;
						var health_score_resource = resultList[i].health_score_resource;
						var health_score_exception = resultList[i].health_score_exception;
						if(health_score_total == null || health_score_total == ""){
							health_score_total = "-";	
						}
						if(health_score_response == null || health_score_response == ""){
							health_score_response = "-";	
						}
						if(health_score_efficiency == null || health_score_efficiency == ""){
							health_score_efficiency = "-";	
						}
						if(health_score_capacity == null || health_score_capacity == ""){
							health_score_capacity = "-";	
						}
						if(health_score_resource == null || health_score_resource == ""){
							health_score_resource = "-";	
						}
						if(health_score_exception == null || health_score_exception == ""){
							health_score_exception = "-";	
						}
						$("<tr><td align=\"center\"><a href=\"#\" onclick='showDBInstance(\""+resultList[i].entity_id +"\")'><span class=\"value\">"
										+ resultList[i].entity_name
										+ "</span></a></td><td align=\"center\"><span class=\"percentage\"><b style=\"width: "+health_score_total +"\"></b></span>"
										+ health_score_total
										+ "</td><td align=\"center\">"
										+ health_score_response
										+ "</td><td align=\"center\">"
										+ health_score_efficiency
										+ "</td><td align=\"center\">"
										+ health_score_capacity
										+ "</td><td align=\"center\">"
										+ health_score_resource
										+ "</td><td align=\"center\">"
										+ health_score_exception
										+ "</td></tr>")
								.insertAfter(
										$("#tbTab tr:eq(" + i+ ")"));
					}
				}
			}
		});
		showLimitChar(busiId);
	}
	//健康度
	function showLimitChar(busiId){
		if(busiId == undefined){
			var node_type = $("#node_type").val();
			var busiId = $("#id").val();
		}else{
			var node_type = '32';
		}
		$.ajax({
			type:"post",
			url:"weblogicTab_showHealthLimitChart.do?id="+busiId+"&node_type="+node_type,
			dataType:"json",
			async:false,
			cache:false,
			success:function(data){
				var resultXml = data.jsonXml;	
				var chart = new FusionCharts("FusionCharts/AngularGauge.swf","ChartId2","350","200","0","1");
				chart.setJSONData(resultXml);
				chart.render("AngularGauge");
			}
		});
	}
	//ajax局部刷新之后,点击实例名称弹出的页面
	function showDBInstance(entity_id){
		 $.dialog({
	       		id:'show',
	       		title:'信息',
	       		height:'550px',
	       		width:'900px',
	       		lock:true,
	       		content:'url:paasTree_tabs.do?node_type=24&server_type=32'+'&entity_id='+entity_id
	       	});
	}
	//展示多少个topN
	function initData(){
		var id = $("#id").val();
		var node_type = $("#node_type").val();
		var top_num = $("#topnum").val();
		$.ajax({
			type:'get',
			dataType:'text',
			url:'weblogicTab_showBusiTopN.do?id='+id+'&node_type='+node_type+'&top_num='+top_num,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/Column3D.swf","ChartId1","90%","260","0","1");
				chart1.setJSONData(msg);
				chart1.render("business");
			}
		});
	}
	//业务告警
	function serious(type){
		var busi_id = $("#busiId").val();
		if(busi_id == ''||busi_id == null){
			busi_id =  $("#busi_id").val();
		}
		var title = '';
		if(type == 0){
			title = '严重告警';
		}else if(type == 1){
			title = '主要告警';
		}else if(type == 2){
			title = '次要告警';
		}else if(type == 3){
			title = '不确定告警';
		}
   		$.dialog({
   			id:'vdi',
   			title: title,
   			width: '850px',
   			height: '500px',
   			max: true,
   		    min: true,
   		  	lock:true,
   			content: 'url:paasAlarm_listPaasAlarm.do?obj.event_level='+type+'&id='+busi_id+'&node_type=22'
   			});
	}
</script>
</head>
<body>
	<s:hidden name="id" id="id"></s:hidden>
	<s:hidden name="node_type" id="node_type"></s:hidden>
	<s:hidden name="busiId" id="busiId"></s:hidden>
	<s:hidden name="paasEntityObj.busi_id" id="busi_id"></s:hidden>
	<s:form action="" method="post"  id="theForm">
	<div class="mainboxBus" style="overflow-y: auto;width: 100%;border: solid 0px #FFFFFF;">
		<div class="title" style="margin-top: 18px;"><h2 class="datacenter dc-tt mgt-15"><span class="txt">基本信息</span></h2></div>
		<div class="item" style="width: 48%;">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">汇总数据</font></div>
			</div>
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">业务:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.busiCount"/></font></span>
	            		</li>		
	        			<li style="width: 100px;">
	            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.busi_normalCount"/></span></font></span>
	        			</li>
	        			<li style="width: 100px;">
	           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.busi_unusualCount"/></font></span>
	         			</li>
	     			</ul>  
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">实例:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.instanceCount_all"/></font></span>
	            		</li>		
	        			<li style="width: 100px;">
	            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.normalCount_all"/></font></span>
	        			</li>
	        			<li style="width: 100px;">
	           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.unusualCount_all"/></font></span>
	         			</li>
	     			</ul>  
	     			<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">数据源:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.dsCount_all"/></font></span>
	            		</li>		
	     			</ul>  
				</div>
			</div>
		</div>
		<div class="item" style="width: 48%;">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">告警</font></div>
			</div>
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">严重告警:</span><span class="num blue-num"><font style="font-weight:bold;">
							<a href="javascript:;" name="serious_all"><s:property value="paasEntityObj.seriousAlarmCount_all"/></a></font></span>
	            		</li>		
	            		<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">主要告警:</span><span class="num blue-num"><font style="font-weight:bold;">
							<a href="javascript:;" name="serious1_all"><s:property value="paasEntityObj.mainAlarmCount_all"/></a></font></span></font></span>
	            		</li>	
	     			</ul>  
	     			<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">次要告警:</span><span class="num blue-num"><font style="font-weight:bold;">
							<a href="javascript:;" name="serious2_all"><s:property value="paasEntityObj.minorAlarmCount_all"/></a></font></span>
	            		</li>		
	            		<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">不确定告警:</span><span class="num blue-num"><font style="font-weight:bold;">
							<a href="javascript:;" name="serious3_all"><s:property value="paasEntityObj.otherAlarmCount_all"/></a></font></span>
	            		</li>	
	     			</ul>  
				</div>
			</div>
		</div>
		<div class="item" style="width:98%;border: 0px solid #FFFFFF;">
  			<div class="top">
  				<div class="ml5"><font color="orange" size="3" style="margin-right: 70%;">业务</font>
  					TOP<input type="text" style="width:30px;color: red" id="topnum" value="5"/>
    				<span class="ubtn-1 mgl-20"><input type="button" value="GO" onclick="initData()"/></span>
				</div>
  			</div>
  			<div style="height: 10px;"></div>
			<div id="business" align="center"></div>
			<div style="height: 10px;"></div>
  		</div>	
	</div>
		<div class="mainboxDaas" style="width: 100%;border: solid 0px #FFFFFF;" id="mainboxDaas">
			<div class="title" style="margin-top: 18px;"><h2 class="datacenter dc-tt mgt-15"><span class="txt">业务名称:<font id="entity_name"><s:property value="paasEntityObj.nodeName"/></font></span></h2></div>
			<div class="item" style="width: 48%;">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">统计信息</font></div>
				</div>
				<div>
					<div class="right">
						<ul class="dc-list">
							<li style="width: 135px;">
		            			<i class="zhuji"></i><span class="txt">数据源:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.dsCount"/></font></span>
		            		</li>		
		     			</ul> 
						<ul class="dc-list">
							<li style="width: 135px;">
		            			<i class="zhuji"></i><span class="txt">实例:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.instanceCount"/></font></span>
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
		            			<i class="zhuji"></i><span class="txt">严重告警:</span><span class="num blue-num"><font style="font-weight:bold;" id="seriousAlarmCount">
								<a href="javascript:;" onclick="serious('0')"><span id="seriousAlarmCount"><s:property value="paasEntityObj.seriousAlarmCount"/></span></a></font></span>
		            		</li>		
		        			<li style="width: 135px;">
		            			<i class="zhuji"></i><span class="txt">主要告警:</span><span class="num blue-num"><font style="font-weight:bold;" id="mainAlarmCount">
								<a href="javascript:;" onclick="serious('1')"><span id="mainAlarmCount"><s:property value="paasEntityObj.mainAlarmCount"/></a></font></span>
		        			</li>
		     			</ul>  
		     			<ul class="dc-list">
							<li style="width: 135px;">
		            			<i class="zhuji"></i><span class="txt">次要告警:</span><span class="num blue-num"><font style="font-weight:bold;" id="minorAlarmCount">
								<a href="javascript:;" onclick="serious('2')"><span id="minorAlarmCount"><s:property value="paasEntityObj.minorAlarmCount"/></a></font></span>
		            		</li>		
		        			<li style="width: 135px;">
		            			<i class="zhuji"></i><span class="txt">不确定告警:</span><span class="num blue-num"><font style="font-weight:bold;" id="otherAlarmCount">
		            			<a href="javascript:;" onclick="serious('3')"><span id="otherAlarmCount"><s:property value="paasEntityObj.otherAlarmCount"/></a></font></span>
		        			</li>
		     			</ul>  
					</div>
				</div>
			</div>
			<div class="item" style="width: 46.5%;">
	  			<div class="top">
	  				<div class="ml5"><font color="orange" size="3">健康度</font></div>
	  			</div>
	  			<div id="AngularGauge" align="center"></div>
	  		</div>	
		</div>
		<div class="mainboxservice" style="overflow-y: auto;width: 100%;border: solid 0px #FFFFFF;">
			<div class="item" style="width: 98%;">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">实例各个指标健康度</font></div>
				</div>
					<div class="right"  style="width: 100%;margin-bottom: 5px;margin-top: 10px;">
						<table width="100%" class="ued-table" border="0" cellspacing="0" id="tab" >
							<thead>
								<tr>
									<th width="40%;">实例名称</th>
									<th>健康度</th>
									<th>响应</th>
									<th>负载</th>
									<th>容量</th>
									<th>资源</th>
									<th>异常</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
									<tr>
										<td style="padding-left: 40px;" entity_id="<s:property value='#theBean.entity_id'/>">
											<a href="#" name="showDBInstance"><span class="value"><s:property value="#theBean.entity_name"/></span></a>
										</td>
										<td align="center">
											<span class="percentage">
												<b style='width: <s:property value="#theBean.health_score_total" default="0"/>'>
												</b>
											</span>&nbsp;
											<s:property value="#theBean.health_score_total" default="0"/>
										</td>
										<td align="center">
											<s:property value="#theBean.health_score_response" default="0"/>
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
	</s:form>
</body>