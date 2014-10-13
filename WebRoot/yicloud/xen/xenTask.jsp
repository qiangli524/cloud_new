<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="yvm_dataCenterHost" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" id="theTable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">名称</th>
						<th onclick="sort(theTable,1,'string')">状态</th>
						<th onclick="sort(theTable,2,'date')">开始时间</th>
						<th onclick="sort(theTable,3,'date')">结束时间</th>
					</tr>
				</thead>    
				<tbody>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td><s:property value="#theBean.name"/> </td>
							<td><s:property value="#theBean.task_state"/> </td>
							<td><s:property value="#theBean.start_time"/> </td>
							<td><s:property value="#theBean.end_time"/> </td>
						</tr>
					</s:iterator>
				</tbody>               
			</table>
		</div>
</s:form>
</body>
