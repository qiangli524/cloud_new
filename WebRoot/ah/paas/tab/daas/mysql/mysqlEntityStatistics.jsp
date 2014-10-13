<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/ah/paas/statistics/css/statistics.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/table.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
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
	showAverage();
	if($.browser.msie){
		$("input:checkbox").click(function(){
			this.blur();
			this.focus();
		});
	}
	$("input[name=checkboxId]").change(function(){
			var entity_id = $(this).val();
			var entity_name=$(this).attr("entity_name");
			var time = $("#time option:selected").val();
			$.ajax({
				type:'get',
				dataType:'text',
				url:'mysqlTab_showChartByEntityId.do?entity_id='+entity_id+'&name='+entity_name+"&cycle_time="+time,
				success:function(msg){
					var chart1 = new FusionCharts("FusionCharts/Line.swf","ChartId5","94.2%","240","0","1");
					chart1.setJSONData(msg);
					chart1.render("average1");
				}
			});
		});
	
	//点击实例名字展示页面
	$("[name='showDBInstance']").click(function(){
     	$td=$(this).parent();
        var entity_id=$td.attr("entity_id");
        $.dialog({
       		id:'show',
       		title:'信息',
       		height:'550px',
       		width:'900px',
       		lock:true,
       		content:'url:paasTree_tabs.do?node_type=24&server_type=4'+'&entity_id='+entity_id
       	});
	});
});

	//健康度历史值
	function showAverage(){
		var id = $("#id").val();
		var time = $("#time option:selected").val();
		$.ajax({
			type:'get',
			dataType:'text',
			url:'mysqlTab_showLimitAverage.do?id='+id+"&cycle_time="+time,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/Line.swf","ChartId3","94.2%","240","0","1");
				chart1.setJSONData(msg);
				chart1.render("average1");
			}
		});
	}
	
	function queryChart(){
		var id = $("#id").val();
		var start_time = $("#start_time").val();
    	var end_time = $("#end_time").val();
    	var time = $("#time option:selected").val();
		$.ajax({
			type:'get',
			dataType:'text',
			url:'mysqlTab_showLimitAverage.do?id='+id+"&start_time="+start_time+"&end_time="+end_time+"&cycle_time="+time,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/Line.swf","ChartId2","94.2%","240","0","1");
				chart1.setJSONData(msg);
				chart1.render("average1");
			}
		});
	}
	
	//点击图形上的点,执行的方法,在service中写的,link的属性实现的
	 function showInstanceList(id,entity_id,cycle_time,coll_time,timeline){
		$.dialog({
			id:'data',
			title:'实时数据',
			height:'550px',
       		width:'900px',
       		lock:true,
       		content:'url:paasTree_tabs.do?node_type=24&server_type=32'+'&entity_id='+entity_id+'&coll_time='+coll_time+'&timeline='+timeline+'&cycle_time='+cycle_time
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
	<s:form action="" method="post"  id="theForm">
	<div class="mainboxservice" style="overflow-y: auto;width: 100%;">
		<div class="title" style="border: 0px solid #FFFFFF;">
			<h2 class="datacenter dc-tt mgt-15"><span class="txt">数据库实例摘要</span></h2>
		</div>
		<div class="item">
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">数据库实例:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.dbCount"/></font></span>
	            		</li>		
	        			<li style="width: 100px;">
	            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.normalCount"/></font></span>
	        			</li>
	        			<li style="width: 100px;">
	           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.unusualCount"/></font></span>
	         			</li>
	     			</ul>  
				</div>
			</div>
		</div>
	</div>
	<div class="mainboxservice" style="overflow-y: auto;width: 100%;">
		<div class="title" style="border: 0px solid #FFFFFF;">
				<h2 class="datacenter dc-tt mgt-15"><span class="txt" style="width: 60%">数据库实例列表</span>
					<%--<span style="font-size: 16px;">实例名称：</span><input type="text" id="topnum"/><a href="#"	onclick="queryList();" title="查询">
					<img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif" style="width: 18px;height: 18px;"> </a>
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
								<th>数据库名称</th>
								<th>类型</th>
								<th>服务器地址</th>
								<th>端口</th>
								<th>健康度</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="resultList" id="theBean">
								<tr>
									<td align="center">
										<input type="checkbox" name="checkboxId" value="<s:property value="#theBean.entity_id"/>" entity_name="<s:property value='#theBean.entity_name'/>"/> 
									</td>
									<td align="center" entity_id="<s:property value='#theBean.entity_id'/>">
										<a href="#" name="showDBInstance"><span class="value"><s:property value="#theBean.entity_name"/></span></a>
									</td>
									<td align="center">
										<s:if test="#theBean.entity_type == 4">
											Mysql
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
										<span class="percentage">
											<s:if test="#theBean.kpi_value_last == 100.0">
												<b style='width: <s:property value="#theBean.kpi_value_last" default="0"/>'>
												</b>
											</s:if>
											<s:else>
												<b class="red" style='width: <s:property value="#theBean.kpi_value_last" default="0"/>'>
												</b>
											</s:else>
										</span>&nbsp;
										<s:property value="#theBean.kpi_value_last" default="0"/>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div class="table-head">
				  <%-- <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /> --%>
				</div>
			</div>
		</div>
	</div>
	<div class="mainboxDaas" style="width: 100%;border: 0px solid #FFFFFF;">
		<div class="item" style="width:97%">
  			<div class="top">
  				<div class="ml5"><font color="orange" size="3">实例健康度</font>
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
						<a href="#"	onclick="queryChart();" title="查询"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
								style="width: 18px;height: 18px;"> </a>
					</span>	
				</div>
  			</div>
  			<div style="height: 10px;"></div>
			<div id="average1" align="center"></div>
			<div style="height: 10px;"></div>
  		</div>	
	</div>
	</s:form>
</body>