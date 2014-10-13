<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="yvm_dataCenterVM" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" id="theTable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">名称</th>
						<th onclick="sort(theTable,1,'string')">状况</th>
						<th onclick="sort(theTable,2,'string')">状态</th>
						<th onclick="sort(theTable,3,'string')">主机</th>
						<th onclick="sort(theTable,4,'string')">置备的空间</th>
						<th onclick="sort(theTable,5,'string')">已用空间</th>
						<th onclick="sort(theTable,6,'string')">主机CPU(MHz)</th>
						<th onclick="sort(theTable,7,'string')">主机内存(MB)</th>
						<th onclick="sort(theTable,8,'string')">客户机内存(%)</th>
						<th onclick="sort(theTable,9,'string')">备注</th>	
					</tr>
				</thead>    
				<tbody>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td><s:property value="#theBean.name"/></td>
							<td></td>
							<td></td>
							<td></td>
							<td><s:property value="#theBean.provisionStore"/></td>
							<td><s:property value="#theBean.store"/></td>
							<td><s:property value="#theBean.cpu"/></td>
							<td><s:property value="#theBean.mem"/></td>
							<td><s:property value="#theBean.clientMem"/></td>
							<td></td>
						</tr>
					</s:iterator>
				</tbody>               
			</table>
		</div>
</s:form>
</body>
