<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<head>
	<title></title>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/chongqing/directoryType/js/directoryType.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
</head>

<body class="pop-body scrollbody" onload="document.theForm.name.focus()">
	<s:form action="" method="post" id="theForm">
				<div class="blue-wrap noborder">
					<div class="table-head">
						<ul class="btns">
							<li>
								<input type="button" class="thickbox btn-style02" value="增加" onclick="showTypeInfo('add')" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="修改" onclick="showTypeInfo('edit')" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="删除"/>
							</li>
						</ul>
<!-- 						<js
p:i -->
<!-- 						nclude page="../inc/Pagination.jsp?formId=theForm" /> -->
					</div>
	    <input type="hidden" name="id" id="id"/>
		<table  width="100%" border="0" cellspacing="0"	class="blue-table sorttable" id="theTable">
			<thead>
				<th>选择</th>
				<th>编码</th>
				<th>名称</th>
				<th>描述</th>
			</thead>
			<tbody>
				<c:forEach items="${directoryTypeList }" var="type" varStatus="index">
					<tr>
					    <td>
					    	<input type="checkbox" value="${type.id }" name="checkid"/>
					    </td>
						<td><input type="hidden"  id="typeId" value="${type.id }"/>
							${type.code }
						</td>
						<td>${type.name }</td>
						<td>${type.description }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</s:form>
</body>