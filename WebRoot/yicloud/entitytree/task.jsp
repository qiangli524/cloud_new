<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
</head>
<body>
<form action="" method="post" id="theForm">
	<div style="overflow:auto;height: 400px;">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" id="theTable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">名称</th>
						<th onclick="sort(theTable,1,'string')">状态</th>
						<th onclick="sort(theTable,2,'date')">请求开始时间</th>
						<th onclick="sort(theTable,3,'date')">完成时间</th>
					</tr>
				</thead> 
				<tbody>
					<c:forEach items="${page.pageData }" var="theBean" >
						<tr align="center">
							<td>${theBean.name }</td>
							<td>${theBean.status }</td>
							<td>${theBean.requestTime }</td>
							<td>${theBean.completeTime }</td>
						</tr>
					</c:forEach>
				</tbody>  
			</table>
			<jsp:include page="../../pageutil.jsp">
			
	            <jsp:param value="tree_hostTask.do?pagesize=10&type=${type }&name=${name }&entity_id=${entity_id }" name="url"/>
	        </jsp:include>
		</div>
	</div>
</form>
</body>
