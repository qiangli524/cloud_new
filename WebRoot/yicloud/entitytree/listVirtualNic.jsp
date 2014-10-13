<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="#" method="post" id="theForm" cssClass="theForm">
 <div class="scrollbody">
 <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
					<th onclick="sort(theTable,0,'string')">名称</th> 
					<th onclick="sort(theTable,1,'string')">MAC</th>
					<th onclick="sort(theTable,2,'string')">端口组</th>
					<th onclick="sort(theTable,3,'string')">物理适配器</th>
					<th onclick="sort(theTable,4,'string')">IP</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><s:property value="#theBean.name"/></td>
			  		<td><s:property value="#theBean.uuid"/></td>
			  		<td><s:property value="#theBean.portgroupName"/></td>
			  		<td><s:property value="#theBean.nicName"/></td>
			  		<td><s:property value="#theBean.ipAddress"/></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
		</div>
</s:form>
</body>
