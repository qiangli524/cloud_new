<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
</head>
<body>
<s:form action="yvm_hostInfo" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						名称
					</td>
					<td>
						<s:property value="theForm.NAME_ZH"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						SN编号
					</td>
					<td>
						<s:property value="theForm.CODE"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						内网IP地址
					</td>
					<td>
						<s:property value="theForm.IP"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						品牌
					</td>
					<td>
						<s:property value="theForm.BRAND"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						型号
					</td>
					<td>
						<s:property value="theForm.MODEL"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						CPU
					</td>
					<td>
						<s:property value="theForm.CPU"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						内存
					</td>
					<td>
						<s:property value="theForm.MEMORY"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						存储空间
					</td>
					<td>
						<s:property value="theForm.STORAGE"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						外围接口
					</td>
					<td>
						<s:property value="theForm.INTERFACE"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						附属设备
					</td>
					<td>
						<s:property value="theForm.AUXILIARY"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						备注
					</td>
					<td>
						<s:property value="theForm.REMARK"/>
					</td>
				</tr> 
			</table>
		</div>
</s:form>
</body>
