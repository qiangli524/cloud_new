<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<%
String path = request.getContextPath();
%>
<link href="<%=path%>/home/ui/nresources/css/framework.css" rel="stylesheet" />
<link href="<%=path%>/home/ui/nresources/css/home.css" rel="stylesheet" />
<html:html locale="true">
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<script type="text/javascript">
		function resetForm() {
			$("#clusterName").attr("value","CLUSTER_AOO");
			$("#poolName").attr("value","MLB");
			$("#type").attr("style","display:none;");
			$("#onlineFlag").attr("style","display:none;");
			$("#onlineFlag").attr("value","");
		}
		
		function searchRequest() {
			theForm.submit();
		}

		function checkPoolName(){
			var poolName = $("#poolName").val();
			if(poolName == 'NCT'){
				$("#type").attr("style","");
				$("#onlineFlag").attr("style","");
				}else{
					$("#type").attr("style","display:none;");
					$("#onlineFlag").attr("style","display:none;");
					$("#onlineFlag").attr("value","");
					}
			}
		
		$(function(){
			initReportData("PM-01-01-001-01","trafficArea");
			initReportData("PM-01-01-001-04","fluxArea");
			initReportData("PM-01-01-001-05","portArea");
			initReportData("PM-01-01-001-06","remindArea");
		});
		
		//插入话务量折线图
		function initReportData(busiKpi,divId){
			var clusterName = $("#clusterName").val();
			var poolName = $("#poolName").val();
			//var busiKpi ="PM-01-01-001-01";
				$.ajax({
						type : 'post',
						url : 'monitorReport_queryChartJSONForTraffic.do?obj.clusterName='+
						clusterName+"&obj.poolName="+poolName+"&obj.busiKpi="+busiKpi,
						dataType : 'json',
						success : function(msg) {
							var chartXml = msg.chartXml;
							var chart1 = new FusionCharts(
									"FusionCharts/ZoomLine.swf", "Chart" + divId, "100%",
									"300", "0", "0");
							chart1.setJSONData(chartXml);
							chart1.render(divId);
						}
			});
		}

		function checkDetail(type){
			var url = "";
			var title = "";
			if("traffic" == type){
				url = "bossBusiAction_hwTabs.do";
				title = "话务量监控";
			}else if("flux" == type){
				url = "bossBusiAction_fluxTabs.do";
				title = "流量查询监控";
			}else if("port" == type){
				url = "bossBusiAction_tabs.do";
				title = "端口收发量监控";
			}else if("remind" == type){
				url = "bossBusiAction_remindTabs.do";
				title = "提醒服务监控";
			}
			$.dialog({
				id:'add',
				title:title,
				width: '1300px',
				height: '600px',
				max: true,
			    min: true,
			    lock:true,
				content: 'url:' + url
				});
		}
		
</script>
</head>
<body>
<div class="mainbody">
<s:form action="monitorReport_reportMain.do" method="post" cssStyle="theForm" id="theForm">
	<s:hidden name="id" id="id"></s:hidden>
		 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">运营指标</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">集群名称：</label>
				<s:select list="clusterList" cssClass="select-1 vm" id="clusterName"  
						listKey="nodeId" listValue="nodeName" name="obj.clusterName" >
					</s:select>
				<label class="mgl-20 vm">程序池：</label>
				<s:select list="poolList" cssClass="select-1 vm" id="poolName"  
						listKey="nodeId" listValue="nodeName" name="obj.poolName"  onchange="checkPoolName();">
					</s:select>
				<label class="mgl-20 vm" id='type' style="display:none;">状态：</label>
				<s:select cssClass="select-1 vm" id="onlineFlag"  
						list="#{'':'--请选择--',1:'在线',0:'离线'}" name="obj.onlineFlag" style="display:none;">
					</s:select>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm()" value="重置" /></span>
			</div>
		</div>
		
		<div class="clearfix">
			<div class="box-fn fl">
		        <div class="tt-2">话务量走势图
		        	<a href="javascript:checkDetail('traffic');">
						<div style="float:right">查看详细&nbsp;&nbsp;</div>
					</a>		        
				</div>
			    <div class="boxfn-cont">
			    	<div id="trafficArea">加载话务量趋势图</div>
			    </div>
			</div>
			
			<div class="box-fn fr">
		       <div class="tt-2">流量查询走势图
			       	<a href="javascript:checkDetail('flux');">
						<div style="float:right">查看详细&nbsp;&nbsp;</div>
					</a>
		       </div>
		       <div class="boxfn-cont">
		       	<div id="fluxArea">加载流量查询趋势图</div>
		       </div>
		    </div>
		</div>
		
		<div class="clearfix">
			<div class="box-fn fl">
		        <div class="tt-2">
		        	端口收发量走势图
		        	<a href="javascript:checkDetail('port');">
						<div style="float:right">查看详细&nbsp;&nbsp;</div>
					</a>
		        </div>
			    <div class="boxfn-cont">
			    	<div id="portArea">加载端口收发量趋势图</div>
			    </div>
			</div>
			
			<div class="box-fn fr">
		       <div class="tt-2">提醒服务走势图
		       		<a href="javascript:checkDetail('remind');">
						<div style="float:right">查看详细&nbsp;&nbsp;</div>
					</a>
		       </div>
		       <div class="boxfn-cont">
		       	  <div id="remindArea">加载提醒服务趋势图</div>
		       </div>
		    </div>
		</div>
		   
			     	
			
			
			
			
</s:form>
</div>
</body>
</html:html>
