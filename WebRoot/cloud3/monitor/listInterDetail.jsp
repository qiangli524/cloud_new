<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
</script>
</head>
<body>
	<s:form action="net_listNetInfo.do" method="post" cssStyle="theForm"
		id="theForm">
			<div class="table-ct" style="position:absolute;bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<thead>
						<tr>
									<th onclick="sort(theTable,0,'date')">
										开始时间
									</th>
									<th onclick="sort(theTable,1,'date')">
										结束时间
									</th>
									<th onclick="sort(theTable,2,'string')">
										时间间隔
									</th>
									<th onclick="sort(theTable,3,'string')">
										是否成功
									</th>
									<th onclick="sort(theTable,4,'string')">
										运行日志
									</th>
										</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
									<tr>
										<td>
											<s:property value="#theBean.start_time" />
										</td>
										<td>
												<s:property value="#theBean.end_time" />
										</td>
										<td>
											<s:property value="#theBean.interval" />
										</td>
										<td>
										<s:if test="#theBean.issuccess==1">是</s:if>
										<s:else>否</s:else>
										<td>
										<s:property value="#theBean.runlog"/>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
	</s:form>
</body>
</html:html>
