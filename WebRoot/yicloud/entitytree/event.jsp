<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="yvm_dataCenterHost" method="post" id="theForm">
<div style="overflow:auto;height: 400px;">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" id="theTable">
				<thead>
					<tr>
<!--						<th>类型</th>-->
						<th width="20%" onclick="sort(theTable,0,'date')">日期</th>
<!--						<th>任务</th>-->
<!--						<th>目标</th>-->
						<th width="10%" onclick="sort(theTable,1,'string')">用户</th>
						<th onclick="sort(theTable,2,'string')">描述</th>
					</tr>
				</thead>    
				<tbody>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr align="center">
<!--							<td><s:property value="#theBean.name"/> </td>-->
							<td><s:property value="#theBean.time"/></td>
<!--							<td><s:property value="#theBean.name"/></td>-->
<!--							<td><s:property value="#theBean.name"/></td>-->
							<td><s:property value="#theBean.user"/></td>
							<td><s:property value="#theBean.description"/></td>
						</tr>
					</s:iterator>
				</tbody>               
			</table>
		</div>
</div>
</s:form>
</body>
