<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
        <title>TopN</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
    	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<style type="text/css">
		.font-more{ width:180px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}
</style>
<script type="text/javascript">
         $(function(){
			initData();
         });
         
         function initData(){
        	 $("#tab tr:not(:first)").remove();
			 var nodeType = $("#nodeType").val();
			 var hostname = "";
			 if (nodeType == 10) {//是服务节点
				hostname = $("#nodeId").val();
				//document.getElementById("hide1").style.display="none";
				//document.getElementById("hide2").style.display="none";
			 } else {//根节点下的
				 hostname = $("#serviceId").val();
				 //document.getElementById("hide1").style.display="none";
				// document.getElementById("hide2").style.display="none";
			 }
        	 var kpiId = $("#kpiId").val();
        	 var topnum = $("#topnum").val();
        	 $.ajax({
        		 type:'post',
        		 url:'hadooptop_showTopN.do?hadoopMonitorObj.host_name='+hostname+'&kpi_id='+kpiId+'&topnum='+topnum,
        		 dataType:'json',
        		 success:function(msg){
        			 var chartXml = msg.chartXml;
        			 var chart1 = new FusionCharts("FusionCharts/Column2D.swf","ChartId1","100%","300","0","0");
 					 chart1.setJSONData(chartXml);
 					 chart1.render("chartarea");
 					 
 					 var resultList = msg.resultList;
 					 if (resultList != null) {
 						 for(var i = 0;i<resultList.length;i++){
 							 $("<tr><td align=\"center\"><span style=\"color:black;\" class=\"font-more\" title='"+ resultList[i].description +"'>"
 							 	+resultList[i].description+"</span></td><td>"
 								+resultList[i].host_name+"</td><td>"
 								+resultList[i].ipaddress+"</td><td hostName='"+resultList[i].host_name+"'><a href=\"javascript:;\" name=\"viewhis\">"
 								+resultList[i].kpi_value+resultList[i].unit+"</a></td></tr>").insertAfter($("#tab tr:eq("+i+")"));
 						 }
					}
        		 }
        	 });
         }
         
         $(function(){
        	 $("[name='viewhis']").unbind().live("click",function(){
        		var $td = $(this).parent();
				var hostName = $td.attr("hostName");//主机名称
				var kpiId = $("#kpiId").val();//指标id
				var nodeType = $("#nodeType").val();
				var serviceNodeId = "";//服务节点的Id
				if (nodeType == 10) {//服务节点
					serviceNodeId = $("#nodeId").val()
				} else {//根节点
					serviceNodeId = $("#serviceId").val()
				}
				$.dialog({
					id:'viewhistorybyhost',
					title:'主机历史值走势',
					width:'700px',
					height:'550px',
					lock:true,
					content:'url:hadoopmonitor_goHisPage.do?obj.id='+serviceNodeId+'&obj.kpiId='+kpiId+'&hadoopMonitorObj.host_name='+hostName+'&refer=topN'
				});
        	 });
        	 
        	 $("#serviceId").change(function(){
        		 var serviceId = $("#serviceId").val();
        		 $.ajax({
        			 type:'post',
        			 dataType:'json',
        			 url:'hadooptop_queryKpiList.do?obj.id='+serviceId,
        			 success:function(msg){
						$("#kpiId").empty();
						var arr = msg.kpiList
						for(var i = 0;i<arr.length;i++){
							$("<option value="+arr[i].kpi_id+">"+arr[i].description+"</option>").appendTo("#kpiId");
						}
        			 }
        		 });
        	 });
         });
</script>
    </head>

    <body class="pop-body scrollbody" style="overflow-x:hidden">
    	<s:hidden name="obj.id" id="nodeId"></s:hidden>
    	<s:hidden name="obj.node_type" id="nodeType"></s:hidden>
    	<div align="right">
    		<table>
    			<tr>
    				<%--<td id="hide1">
    					服务类型:<s:select list="serviceList" id="serviceId" listKey="id" listValue="service_type"></s:select>
    				</td>
    				<td width="50px;" id="hide2"></td> --%>
    				<td>
			    		指标：<s:select list="kpiList" id="kpiId" listKey="kpi_id" listValue="description" name="kpiId" cssStyle="width:200px;"></s:select>
    				</td>
    				<td width="50px;"></td>
    				<td>
    					TOP<input type="text" style="width:30px;color: red" id="topnum" value="5"/>
    					<input type="button" value="GO" class="thickbox btn-style02" onclick="initData()"/>
    				</td>
    			</tr>
    			<tr height="10px;">
    				<td></td><td></td>
    			</tr>
    		</table>
    	</div>
    	
        <div id="chartarea" align="center">加载hadoop主机topN</div>
        <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="tab">
				<thead>	
					<tr>
						<th>指标名称</th>
						<th>主机名称</th>
						<th>主机IP地址</th>
						<th>指标值</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="theBean" value="resultList">
						<tr>
							<td align="center">
								<span style="color: black;" class="font-more" title='<s:property value="#theBean.description"/>'>
									<s:property value="#theBean.description" />
								</span>
							</td>
							<td><s:property value="#theBean.host_name"/></td>
							<td><s:property value="#theBean.ipaddress"/></td>
							<td><s:property value="#theBean.kpi_value"/><s:property value="#theBean.unit"/></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
    </body>
</html>
