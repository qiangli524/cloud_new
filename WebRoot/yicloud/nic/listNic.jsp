<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="#" method="post" id="theForm" cssClass="theForm">
<s:hidden name="theForm.flag" id="flag"></s:hidden>
<div class="tit-zzi">
		<div id="zi"><s:property value="#request.vssName"/>物理适配器</div>
</div>
<div class="scrollbody">
 	<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
					<th onclick="sort(theTable,0,'string')">名称</th>
					<th onclick="sort(theTable,1,'string')">MAC</th> 
					<th onclick="sort(theTable,2,'string')">连接状态</th>
					<th onclick="sort(theTable,3,'string')">双工</th>
					<th onclick="sort(theTable,4,'string')">速度</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><s:property value="#theBean.name"/></td>
			  		<td><s:property value="#theBean.mac"/></td>
			  		<td><s:property value="#theBean.status"/></td>
			  		<td><s:property value="#theBean.linkduplex"/></td>
			  		<s:if test="#theBean.linkspeed==0">
			  			<td>-</td>
			  		</s:if>
			  		<s:else>
			  			<td><s:property value="#theBean.linkspeed"/>Mbit/s</td>
			  		</s:else>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
</div>
</s:form>
</body>
