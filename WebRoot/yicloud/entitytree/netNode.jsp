<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
	<title></title>
	<script type="text/javascript">

</script>
</head>
<body>
	<s:form action="net_listNetNode.do" method="post" cssStyle="theForm" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">网络名称</td>
					<td><s:property value="theForm.NAME"/> </td>
				</tr>
				<tr>
					<td class="til">网络描述</td>
					<td><s:property value="theForm.DESCRIPTION"/> </td>
				</tr>
				<tr>
					<td class="til">可用IP地址</td>
					<td><s:property value="theForm.FREECOUNT"/> </td>
				</tr>
				<tr>
					<td class="til">已分配IP地址</td>
					<td><s:property value="theForm.USEDCOUNT"/> </td>
				</tr>
				<tr>
					<td class="til">是否缺省网络</td>
					<td>
						<s:if test="theForm.ISDEFAULT==0">
							缺省网络
						</s:if>
						<s:elseif test="theForm.ISDEFAULT==1">
							非缺省网络
						</s:elseif> 
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>
