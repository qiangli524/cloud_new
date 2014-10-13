<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/link_export.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<head></head>
<body class="mainbody">
	<s:form action="resourcestatistics_showVlan.do" method="post"
		id="theForm" cssStyle="theForm">
		<s:hidden name="id" id="id"></s:hidden>
		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">IP信息</h2>
			<div class="bord-1 pd-10">
				<table id="theTable" width="100%" border="0" cellspacing="0"
					class="blue-table sorttable">
					<thead id="table">
						<tr>
							<th onclick="sort(theTable,0,'ip')"
								onmouseover="this.style.cursor='hand'">IP地址</th>
							<th onclick="sort(theTable,1,'string')">虚拟机名称</th>
							<th onclick="sort(theTable,2,'string')">所属业务</th>
							<th onclick="sort(theTable,3,'string')">是否使用</th>
							<!-- <th onclick="sort(theTable,3,'string')">是否被阻塞</th> -->
						</tr>
					</thead>
					<tbody>
						<s:iterator value="ipList" id="theBean">
							<tr>
								<td><s:property value="#theBean.ip"></s:property></td>
								<td><s:property value="#theBean.vm" default="-" /></td>
								<td><s:property value="#theBean.busi" default="-" /></td>
								<td><s:if
										test="#theBean.isUsed== 0 && #theBean.isLocked == 0">
									否
								</s:if> <s:else>
									是
								</s:else></td>
								<%-- <td>
								<s:if test="#theBean.ISBLOCKED==1">
									是
								</s:if>
								<s:elseif test="#theBean.ISBLOCKED==0">
									否
								</s:elseif>
                			</td> --%>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<div class="pages mgb-10">
					<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
				</div>
			</div>
		</div>
	</s:form>
</body>