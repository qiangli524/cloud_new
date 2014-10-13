<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="yvm_dataCenterHost" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" id="theTable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">名称</th>
					<!-- 	<th>状况</th>
						<th>状态</th>
						<th>CPU(%)</th>
						<th>内存(%)</th>
						<th>内存大小</th>
						<th>CPU计数</th>
						<th>网卡计数</th>
						 -->
					</tr>
				</thead>    
				<tbody>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td><s:property value="#theBean.name"/> </td>
						<!-- 	<td>
								<s:if test="#theBean.ifOpen==1">已连接</s:if>
								<s:else>断开连接</s:else>  
							</td>
							<td>
								<s:if test="#theBean.status==1">警告</s:if>
							</td>
							<td><s:property value="#theBean.cpu"/></td>
							<td><s:property value="#theBean.mem"/></td>
							<td></td>
							<td><s:property value="1"/> </td>
							<td><s:property value="2"/> </td>
							 -->
						</tr>
					</s:iterator>
				</tbody>               
			</table>
		</div>
</s:form>
</body>
