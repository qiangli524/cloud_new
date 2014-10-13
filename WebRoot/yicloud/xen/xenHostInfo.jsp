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
						主机名称
					</td>
					<td  align="left">
						<s:property value="theForm.NAME"  />
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						描述
					</td>
					<td align="left">
					<s:if test="theForm.DESC==null ||theForm.DESC==''">暂无</s:if>
					<s:else><s:property value="theForm.DESC" /></s:else>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						cpu个数
					</td>
					<td align="left">
					
						<s:property value="theForm.CPU" />
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						内存(单位：M)
					</td>
					<td align="left">
						<s:property value="theForm.MEMORY"/> M
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						本地存储(单位：G)
					</td>
					<td align="left">
						<s:property value="theForm.STORAGE"/> G
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						是否可用
					</td>
					<td align="left">
					<s:if test="theForm.STATE=='true'">是</s:if>
					<s:else>否</s:else>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
					    IP 地址
					</td>
					<td align="left">
			         <s:property value="theForm.IP"/>
					</td>
				</tr> 
			</table>
		</div>
</s:form>
</body>
