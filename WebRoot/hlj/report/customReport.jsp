<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<html:html locale="true">
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/hlj/report/js/customReport.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/underscore/underscore-1.4.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/hlj/report/js/dump.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionChartsExportComponent.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/hlj/report/js/customReporKpis.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/hlj/report/js/jsonsql-0.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/hlj/report/js/Validator.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	//初始化查询
	initQuery();
	initFcExport();
	//资源类型事件,根据不同类型展示不同查询条件
	$('.resourceType').live({
		click: function() {
			initQuery();
		}
	})
})

</script>

<style>
.reportQuery{
	width: 20px;
	height: 20px;
	background:url(graph/editors/images/zoom.gif) no-repeat;
	float: left;
	cursor:pointer;
	margin-left:5px;
	margin-top:7px;
}
.blue-table .odd {
	background-color: #FFFFFF;
}
</style>

</head>  
<body  class="mainbody">
<div class="scrollbody">
    	<s:form action="customReport_query.do" method="post"  name="theForm" enctype="multipart/form-data" cssClass="theForm" id="theForm">
				<div class="pd-20 bgcolor-1">
					<h2 class="utt-1">自定义报表</h2>
       	 			<div class="bord-1 pd-10">
							<table width="100%" class="blue-table sorttable" border="0">
								<tr >
									<td width="15%">
										资源类型:
									</td>
									<td align="left"  colspan="3">
										<input name="obj.resourceType" id="radio_rt_host" class="resourceType" type="radio"  checked value="host"/>
										主机
										<input name="obj.resourceType" id="radio_rt_vm" class="resourceType"  type="radio"  value="vm"/>
										虚拟机
										<!--
										<input name="obj.resourceType" id="radio_rt_store" class="resourceType"  type="radio"  value="store"/>
										存储块
										<input name="obj.resourceType" id="radio_rt_ip" class="resourceType"  type="radio"  value="ip"/>
										IP
										-->
									</td>
								</tr>
							
								<tbody id="tbody">
								
								</tbody>							

								<tr>
									<td colspan="10" class="btns">
										<span class="ubtn-1 mgl-20"><input type="button"  value="查询" onclick="query()" /></span>
										<span class="ubtn-2 mgl-20"><input type="button"  value="重置" onclick="resert()" /></span>
									</td>
								</tr>																										
							</table>
					<div id="contentChart" class="hide contentDiv">
						<div class="clearfix mgt-10">
							<div id="exproterDiv" title="请在图中右键生成文件" style="float:left"></div>
							<div align="left"  style="float:right" id="top">								
								<font size="4">Top</font>
								<input type="text" Class="inpt-1" style="width:30px;color: red" disabled="true" id="topnum" value="5" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</div>	
						<div id="chartarea" align="center"></div>
					</div>
					<div id="contentTable" class="hide contentDiv">	
						<div class="mgt-10 utt-2" align="left">
							<a class="icon-export" href="javascript:void(0)" onclick="reportExcelExport();return false;">导出excel</a>
						</div>
						<table id="tableKpis" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
								<thead>
									<tr>
										<th>资源名称</th>
										<th>指标名称</th>
										<th id="kpivalueTh">指标值 </th>
										<th>采集时间</th>									
									</tr>		
								</thead>
								<tbody id="listTbody">	
								</tbody>	
						</table>	
						<div id="pageContent" class="pages mgb-10">
						</div>		
					</div>
				</div>
		</s:form>	
		<%@include file="/hlj/report/customReportTemp.jsp"%>								
</body>
</html:html>
