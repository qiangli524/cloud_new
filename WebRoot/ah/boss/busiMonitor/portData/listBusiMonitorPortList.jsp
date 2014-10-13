<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	<% int i=1;%>
	//重置
	function resetForm() {
		theForm.CLUSTER_NAME.value = '';
		theForm.POOL_NAME.value = '';
	}
	//查询
	function searchRequest() {
		theForm.submit();
	}
	//明细
	function desc(a){
			var busiKpi ="<s:property value='kpi'/>";
			var timeSeq = $(a).parent().parent().find("td").eq(0).text();
			var cluster = $(a).parent().parent().find("td").eq(1).text();
			var poolName =  $(a).parent().parent().find("td").eq(4).text();
			$.dialog({
					id:'add',
					title:'端口收发明细',
					width: '650px',
					height: '350px',
					lock:true,
					content: 'url:bossBusiAction_queryProcessDataDescOfPort.do?obj.CLUSTER_NAME='+cluster
					+"&obj.POOL_NAME="+poolName+"&obj.BUSI_KPI="+busiKpi+"&obj.TIME_SEQ="+timeSeq+"&kpi="+busiKpi
				});	
	}
	//插入折线图
	function initData(){
		var CLUSTER_NAME = $("#CLUSTER_NAME").val();
		var POOL_NAME = $("#POOL_NAME").val();
		var BUSI_KPI ='<%=request.getAttribute("kpi")%>';
		//var uid = $("#pname").val();
			$.ajax({
					type : 'post',
					url : 'bossBusiAction_queryChartJSONOfPort.do?obj.CLUSTER_NAME='+
						CLUSTER_NAME+"&obj.POOL_NAME="+POOL_NAME+"&obj.BUSI_KPI="+BUSI_KPI,
					dataType : 'json',
					success : function(msg) {
						var chartXml = msg.chartXml;
						var chart1 = new FusionCharts(
								"FusionCharts/ZoomLine.swf", "ChartId0", "100%",
								"300", "0", "0");
						chart1.setJSONData(chartXml);
						chart1.render("chartarea");
					}
			});
	}
	//页面生成后的初始化
	$(function(){
		initData();
	});
</script>
</head>
<body >
<div class="mainbody">
<s:form action="bossBusiAction_queryMonitorDataListOfPort.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="kpi"></s:hidden>
		 <div class="pd-20 bgcolor-1">
		 	<s:if test="kpi == 'PM-01-01-001-05'">
		 		<h2 class="utt-1">端口收发监控</h2>
		 	</s:if>
		 	<s:elseif test="kpi == 'PM-01-01-001-04'">
		 		<h2 class="utt-1">流量查询监控</h2>	
		 	</s:elseif>
		 	<s:elseif test="kpi == 'PM-01-01-001-06'">
		 		<h2 class="utt-1">提醒服务监控</h2>	
		 	</s:elseif>
			<div class="bord-1 pd-20">			
			<div class="clearfix mgt-10">
				<label class="vm">集群名称：</label>
					<s:select  list="obj.clusterList" cssClass="select-1" id="CLUSTER_NAME"  
						listKey="nodeId" listValue="nodeName" name="obj.CLUSTER_NAME"  >
					</s:select>
				<label class="mgl-20 vm">程序池：</label>
					<s:select  list="obj.poolList"  cssClass="select-1 vm" id="POOL_NAME"
						listKey="nodeId" listValue="nodeName" name="obj.POOL_NAME" >
					</s:select>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest();" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm();" value="重置" /></span>
			</div>
			<div id="chartarea" align="center"></div>
			<!-- 端口 和 流量 -->
			<s:if test="kpi == 'PM-01-01-001-05' || kpi == 'PM-01-01-001-04'">
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
				  <thead>
				  <tr>
					   <th>编号</th>
					   <th>集群</th>  
	                   <th>程序池</th>
	                   <th>采集时间</th>
	                   <th>入口业务量</th>
	                   <th>出口业务量</th>
	                   <th>操作</th>
				  </tr>
				  </thead>
				  <tbody>
				  <s:iterator value="busiObjList" id="theBean">
							<tr>
								<td style="display: none;"><s:property value="#theBean.TIME_SEQ"/></td>
								<td style="display: none;"><s:property value="#theBean.CLUSTER_NAME"/></td>
								<td><%= i++%></td>
								<td><s:property value="#theBean.nodename"/></td>
								<td><s:property value="#theBean.POOL_NAME"/></td>
								<td><s:property value="#theBean.COLL_TMIE"/></td>
								<td><s:property value="#theBean.IN_COUNT"/></td>
								<td><s:property value="#theBean.OUT_COUNT"/></td>
								<td><a href="#" onclick="javascript:desc(this);" >明细</a></td>
							</tr>
					</s:iterator>		  
				  </tbody>
				</table>
			</s:if>
			<!-- 提醒服务 -->
			<s:if test="kpi == 'PM-01-01-001-06'">
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
				  <thead>
				  <tr>
					   <th>编号</th>
					   <th>集群</th>  
	                   <th>程序池</th>
	                   <th>收发时间</th>
	                   <th>提醒量</th>
	                   <th>操作</th>
				  </tr>
				  </thead>
				  <tbody>
				  <s:iterator value="busiObjList" id="theBean">
							<tr>
								<td style="display: none;"><s:property value="#theBean.TIME_SEQ"/></td>
								<td style="display: none;"><s:property value="#theBean.CLUSTER_NAME"/></td>
								<td><%= i++%></td>
								<td><s:property value="#theBean.nodename"/></td>
								<td><s:property value="#theBean.POOL_NAME"/></td>
								<td><s:property value="#theBean.COLL_TMIE"/></td>
								<td><s:property value="#theBean.IN_COUNT"/></td>
								<td><a href="#" onclick="javascript:desc(this);" >明细</a></td>
							</tr>
					</s:iterator>		  
				  </tbody>
				</table>
			</s:if>
			<div class="pages mgb-10"><!-- 分页 -->
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
</s:form>
</div>
</body>
</html:html>
