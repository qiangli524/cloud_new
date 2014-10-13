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
<style type="text/css">
		div.hidden{
		width:150px;
		height:20px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
		}
</style>
</head>
<body style="overflow-y: auto">
<div class="mainbody">
<s:form action="bossBusiAction_queryListForErrorLogDesc.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="obj.CLUSTER_NAME" id="CLUSTER_NAME"></s:hidden>
<s:hidden name="obj.POOL_NAME" id="POOL_NAME"></s:hidden>
<s:hidden name="obj.BUSI_KPI" id="BUSI_KPI"></s:hidden>
<s:hidden name="obj.TIME_SEQ" id="TIME_SEQ"></s:hidden>
<s:hidden name="kpi"></s:hidden>
	<div class="pd-20 bgcolor-1">
		<div class="bord-1 pd-20" style="overflow-x: auto">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
				  <thead>
				  <tr>
					   <th>编号</th>
					   <th>集群</th>  
	                   <th>进程名称</th>
	                   <th>指标编码</th>
	                   <th>指标描述</th>
	                   <th>数量</th>
	                   <th>采集时间</th>
				  </tr>
				  </thead>
				  <tbody>
				  <s:iterator value="busiObjList" id="theBean">
							<tr>
								<td><%= i++%></td>
								<td><s:property value="#theBean.nodename"/></td>
								<td><s:property value="#theBean.PROGRESS_NODE"/></td>
								<td><s:property value="#theBean.BUSI_NO"/></td>
								<td align="center">
									<div class="hidden" title='<s:property value="#theBean.BUSI_NO_DESC"/>' style="width: 15px;">
										<s:property value="#theBean.BUSI_NO_DESC"/>
									</div>
								</td>
								<td><s:property value="#theBean.IN_COUNT"/></td>
								<td><s:property value="#theBean.COLL_TMIE"/></td>
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
