<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ page import="java.lang.String"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
	<head>

		<title>物理服务器报表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="sxcloud/js/FusionCharts.js">    </script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
		<script type="text/javascript">
	 <%
	String listVirtual = (String)request.getAttribute("listVirtual");
%>
	</script>

	</head>

	<body>
		<div>
<div style=" float: left"></div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="tab_bg">
				<tr class="monitor_bg1">
				</tr>
			</table>
		</div>
	<s:form action="reportvir_listVirtualReport.do" method="post" cssClass="post" id="theForm">
			<div id="chartContainer2"
				style="float:left;margin-right: 10px; margin-bottom: 20px;">
				FusionCharts will load here!
			</div>
			<script type="text/javascript"><!--   
            var myChart = new FusionCharts( "charts/StackedColumn2D.swf",       
			"myChartId2", "840", "300", "0", "1" ); 
			myChart.setXMLData('<%=listVirtual%>'); 
			myChart.render("chartContainer2");       
			// -->         
		</script>
		
		<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,0,'string')">所属主机</th>
									<th onclick="sort(theTable,1,'int')">已监控</th>
									<th onclick="sort(theTable,2,'int')">未监控</th>
									<th onclick="sort(theTable,3,'int')">主机总数</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="theForm.resultList" id="theBean">
								<tr>
									<td> <s:property value="#theBean.eq_name"/>	</td>
									<td> <s:property value="#theBean.monitorCount"/>	</td>
									<td> <s:property value="#theBean.notMonitorCount"/>	</td>
									<td> <s:property value="#theBean.allCount"/>	</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>
					</div>
	</s:form>
	</body>
