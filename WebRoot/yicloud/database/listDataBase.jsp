<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js">
</script>
</head>
<body>
<s:form action="datastore_listDataStore.do" method="post" id="theForm">
<div class="scrollbody">
	<div class="blue-wrap noborder">
	<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
			<thead>
				<tr>
					<th onclick="sort(theTable,0,'string')">用户名</th>
					<th onclick="sort(theTable,1,'string')">命名空间</th>
					<th onclick="sort(theTable,2,'string')">IP地址</th>
					<th onclick="sort(theTable,3,'int')">端口</th>
					<th onclick="sort(theTable,4,'string')">服务名</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="theForm.resultList" id="theBean">
					<tr>
						<td>
							<s:property value="#theBean.usrname"/>
						</td>
						<td>
							<s:property value="#theBean.tabale_space"/>
						</td>
						<td>
							<s:property value="#theBean.ipaddr"/>
						</td>
						<td>
							<s:property value="#theBean.port"/>
						</td>
						<td>
							<s:property value="#theBean.service_name"/>
						</td>
					</tr>
				</s:iterator>				
			</tbody>
			</table>
		</div>
	</div>
</div>
</s:form>
</body>