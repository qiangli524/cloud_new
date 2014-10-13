<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<style type="text/css">
.pop-table tr td{text-align: left;}
</style>
</head>
<body>
<s:form action="busitree_bizsysInfo" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
			<tr>
				<td class="til" width="10%" align="left">
					业务系统名称：
				</td>
				<td>
					<s:property value="theForm.name"/>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					业务系统状态：
				</td>
				<td>
					<s:if test="theForm.status==0">正常</s:if>
					<s:elseif test="theForm.status==1">异常告警</s:elseif>
					<s:elseif test="theForm.status==2">停止</s:elseif>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					业务系统描述：
				</td>
				<td>
					<s:property value="theForm.desc"/>
				</td>
			</tr>
			</table>
		</div>
</s:form>
</body>
