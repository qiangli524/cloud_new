<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
</head>
<body>
<s:form action="xen_hostInfo" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="20%" align="left"> 
						存储名称
					</td>
					<td  align="left">
						<s:property value="theForm.NAME"  />
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						类型
					</td>
					<td align="left">
						<s:property value="theForm.srType"/>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						状态
					</td>
					<td align="left">
						<s:if test="theForm.srState=='connection'">连接</s:if>
						<s:elseif test="theForm.srState=='detach'">分离</s:elseif>
						<s:elseif test="theForm.srState=='destroy'">损坏</s:elseif>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						是否共享
					</td>
					<td align="left">
						<s:if test="theForm.share=='true'">是</s:if>
						<s:else>否</s:else>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						存储总量
					</td>
					<td align="left">
						<s:property value="theForm.dataSize"/>G
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						使用情况
					</td>
					<td align="left">
						<s:property value="theForm.srUsePer"/>%(<s:property value="theForm.USE_SPACE"/>G已用)
					</td>
				</tr> 
			</table>
		</div>
</s:form>
</body>
