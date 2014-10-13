<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
</head>
<body>
<s:form action="yvm_dataCenterInfo" method="post" id="theForm">
		<div class="table-ct">
						<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td class="til" width="20%" align="left">
					主机个数
				</td>
				<td>
					<s:property value="#request.hostCount"/>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					虚拟机个数
				</td>
				<td>
					<s:property value="#request.vmCount"/>
				</td>
			</tr>
			</table>
		</div>
</s:form>
</body>
