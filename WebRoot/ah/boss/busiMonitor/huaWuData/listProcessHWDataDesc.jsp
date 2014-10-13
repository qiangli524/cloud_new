<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	<% int i=1;%>
	 var api = frameElement.api;
	 var w = api.opener;
	function searchRequest() {
		theForm.submit();
	}
</script>
</head>
<body style="overflow-y: auto">
<div class="mainbody" >
<s:form action="bossBusiAction_queryProcessDataDescOfPort.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="obj.CLUSTER_NAME" id="CLUSTER_NAME"></s:hidden>
<s:hidden name="obj.POOL_NAME" id="POOL_NAME"></s:hidden>
<s:hidden name="obj.BUSI_KPI" id="BUSI_KPI"></s:hidden>
<s:hidden name="obj.ONLINE_FLAG" id="ONLINE_FLAG"></s:hidden>
<s:hidden name="kpi"></s:hidden>
	<div class="pd-20 bgcolor-1" >
		<div class="bord-1 pd-20" style="overflow-x: auto">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>编号</th>
				 <!--   <th>集群</th>  --> 
                   <th>进程名称</th>
                   <th>状态</th>
                   <th>采集时间</th>
                   <th>入口业务量</th>
                   <th>出口业务量</th>
                   <th>无主量</th>
                   <th>错单量</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="busiObjList" id="theBean">
						<tr>
							<td><%= i++%></td>
							<%-- <td><s:property value="#theBean.CLUSTER_NAME"/></td> --%>
							<td><s:property value="#theBean.PROGRESS_NODE"/></td>
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
