<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
</head>
<body>
<s:form action="yvm_dataCenterInfo" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td class="til">
					主机个数
				</td>
				<td>
					<s:property value="#request.hostCount"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					虚拟机个数
				</td>
				<td>
					<s:property value="#request.vmCount"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					集群个数
				</td>
				<td>
					<s:property value="#request.clusterCount"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					网络个数
				</td>
				<td>
					<s:property value="#request.netCount"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					存储个数
				</td>
				<td>
					<s:property value="#request.dsCount"/> 
				</td>
			</tr>
			</table>
		</div>
</s:form>
</body>
