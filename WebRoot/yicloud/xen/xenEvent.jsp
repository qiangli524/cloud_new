<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
</head>
<body>
<s:form action="yvm_dataCenterHost" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<thead>
					<tr>
						<th>名称</th>
						<th>日期</th>
						<th>描述</th>
					</tr>
				</thead>    
				<tbody>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td><s:property value="#theBean.name"/> </td>
							<td><s:property value="#theBean.event_time"/></td>
							<td><s:property value="#theBean.event_desc"/></td>
						</tr>
					</s:iterator>
				</tbody>               
			</table>
		</div>
</s:form>
</body>
