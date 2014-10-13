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
			var cluster = $(a).parent().parent().find("td").eq(1).text();
			var timeSeq = $(a).parent().parent().find("td").eq(0).text();
			var online_flag = $(a).parent().parent().find("td").eq(5).text();
			if(Trim(online_flag) == "在线"){
				online_flag = "1";
			}else{
				online_flag = "0";
			}
			var poolName =  $(a).parent().parent().find("td").eq(4).text();
			$.dialog({
					id:'add',
					title:'话务明细',
					width: '650px',
					height: '350px',
					lock:true,
					content: 'url:bossBusiAction_queryProcessDataDescOfPort.do?obj.CLUSTER_NAME='+cluster
					+"&obj.POOL_NAME="+poolName+"&obj.BUSI_KPI="+busiKpi+"&obj.TIME_SEQ="+timeSeq+"&obj.ONLINE_FLAG="+online_flag+"&kpi="+busiKpi
				});	
	}
	//插入折线图
	function initData(){
		var CLUSTER_NAME = $("#CLUSTER_NAME").val();
		var POOL_NAME = $("#POOL_NAME").val();
		var BUSI_KPI ='<%=request.getAttribute("kpi")%>';
		var flag = $("#ONLINE_FLAG").val();
			//显示走势图
			$.ajax({
					type : 'post',
					cache:false,
					url : 'bossBusiAction_queryChartJSONOfPort.do?obj.CLUSTER_NAME='+
						CLUSTER_NAME+"&obj.POOL_NAME="+POOL_NAME+"&obj.BUSI_KPI="+BUSI_KPI+"&obj.ONLINE_FLAG="+flag,
					dataType : 'json',
					success : function(msg) {
						var chartXml = msg.chartXml;
						var chart1 = new FusionCharts(
								"FusionCharts/ZoomLine.swf", "ChartId0", "75%",
								"300", "0", "0");
						chart1.setJSONData(chartXml);
						chart1.render("chartarea");
					}
			});
			//显示饼图
            $("#ratio").insertFusionCharts({  
            	swfUrl: "FusionCharts/Pie2D.swf", 
        	    dataSource: "bossBusiAction_showNctRatio.do?obj.CLUSTER_NAME="+CLUSTER_NAME+"&obj.POOL_NAME="+POOL_NAME+"&obj.BUSI_KPI="+BUSI_KPI,
        	    dataFormat: "jsonurl", 
        	    width: "300", 
        	    height: "220", 
        	    id: "ratios"
        	});
	}
	//页面生成后的初始化
	$(function(){
		initData();
		chan();
	});
	//判断所选程序池是否为NCT
	function chan(){
		var poolName = $("#POOL_NAME").val();
		if(poolName == "NCT"){
			$("#sel").show();
		}else{
			$("#sel").hide();
			$("#sel").val("");
		}
	}
	//去空
	function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	
</script>
</head>
<body>
<div class="mainbody" style="overflow-y: auto;">
<s:form action="bossBusiAction_queryMonitorDataListOfPort.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="kpi"></s:hidden>
		 <div class="pd-20 bgcolor-1">
		 	<h2 class="utt-1">话务量监控</h2>
			<div class="bord-1 pd-20">			
			<div class="clearfix mgt-10">
				<label class="vm">集群名称：</label>
					<s:select  list="obj.clusterList" cssClass="select-1 vm" id="CLUSTER_NAME"  
						listKey="nodeId" listValue="nodeName" name="obj.CLUSTER_NAME"  >
					</s:select>
				<label class="mgl-20 vm">程序池：</label>
					<s:select  list="obj.poolList"  cssClass="select-1 vm" id="POOL_NAME"
						listKey="nodeId" listValue="nodeName" name="obj.POOL_NAME" onchange="javascript:chan();">
					</s:select>
				<label id="sel">
					<label class="mgl-20 vm">状态：</label>
						<s:select list="#{'':'请选择','1':'在线','0':'离线'}" cssClass="select-1 vm" name="obj.ONLINE_FLAG" id="ONLINE_FLAG"></s:select>
				</label>
					<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest();" value="查询" /></span>
					<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm();" value="重置" /></span><br>
					<%-- NCT在线入口:<s:property value="nctRatio.onLineSum"/>,离线入口<s:property value="nctRatio.offLineSum"/>,
					在线比例:<fmt:formatNumber type="currency" pattern="#0.00" value="${nctRatio.onLineSum/(nctRatio.offLineSum+nctRatio.onLineSum)*100}"/>% --%>
					<div id="ratio" style="margin-right: 5px;margin-top:15px;position:absolute ;"></div>
					<%-- <div style="position:absolute;margin-top:200px;text-align:center;">
						在线量：<s:property value="nctRatio.onLineSum"/><br/>
						离线量：<s:property value="nctRatio.offLineSum"/>
					</div> --%>
			</div>
			<div id="chartarea" align="right" ></div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>编号</th>
				   <th>集群</th>  
                   <th>程序池</th>
                   <th>状态</th>
                   <th>采集时间</th>
                   <th>入口业务量</th>
                   <th>出口业务量</th>
                   <th>无主量</th>
                   <th>错单量</th>
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
							<td>
							<s:if test="#theBean.ONLINE_FLAG == 1">
								在线
							</s:if>
							<s:else>
								离线
							</s:else>
							</td>
							<td><s:property value="#theBean.COLL_TMIE"/></td>
							<td><s:property value="#theBean.IN_COUNT"/></td>
							<td><s:property value="#theBean.OUT_COUNT"/></td>
							<td><s:property value="#theBean.NO_MASTER_COUNT"/></td>
							<td><s:property value="#theBean.ERROR_COUNT"/></td>
							<td><a href="#" onclick="javascript:desc(this);" >明细</a></td>
						</tr>
				</s:iterator>		  
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
</s:form>
</div>
</body>
</html:html>
