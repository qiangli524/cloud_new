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
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td class="til" width="10%" align="left">
					业务系统个数：
				</td>
				<td>
					<s:property value="theForm.busiSysNum"/>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					基准应用个数：
				</td>
				<td>
					<s:property value="theForm.busiAppNum"/>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					部署实例个数：
				</td>
				<td>
					<s:property value="theForm.busiDepNum"/>
				</td>
			</tr>
			</table>
		</div>
</s:form>
</body>
