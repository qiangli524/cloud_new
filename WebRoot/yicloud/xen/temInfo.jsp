<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
</head>
<body>
<s:form action="xentree_imageInfo" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="20%" align="left">
						镜像名称
					</td>
					<td align="left">
						<s:property value="theForm.IM_NAME"/>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						镜像描述
					</td>
					<td align="left">
					<s:if test="theForm.IM_DESC==null||theForm.IM_DESC==''">暂无</s:if>
					<s:else><s:property value="theForm.IM_DESC"/></s:else>
					</td>
				</tr> 
				<tr>
					<td class="til" width="20%" align="left">
						CPU
					</td>
					<td align="left">
						<s:property value="theForm.CPU"/>个
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						内存
					</td>
					<td align="left">
						<s:property value="theForm.MEMORY" />MB
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						操作系统
					</td>
					<td align="left">
					<s:if test="theForm.OsType==null || theForm.OsType==''">暂无</s:if>
					<s:else><s:property value="theForm.OsType"/></s:else>
						
					</td>
				</tr>
			</table>
		</div>
</s:form>
</body>
