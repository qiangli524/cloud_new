<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
					<tr>
						<td class="til" align="left">
							虚拟机名称：
						</td>
						<td>
							<s:property value="vmHostObj.VH_NAME"/>
						</td>
					</tr>
					<tr>
						<td class="til" align="left">
							虚拟机IP：
						</td>
						<td>
							<s:property value="vmHostObj.VH_IP"/>
						</td>
					</tr>
					<tr>
						<td class="til" align="left">
							操作系统：
						</td>
						<td>
							<s:property value="vmHostObj.VH_SYSTEM"/>
						</td>
					</tr>
					<tr>
						<td class="til" align="left">
							所属主机：
						</td>
						<td>
							<s:property value="vmHostObj.name"/>
						</td>
					</tr>
					<tr>
						<td class="til" align="left">
							CPU个数：
						</td>
						<td>
							<s:property value="vmHostObj.VH_CPU"/>个
						</td>
					</tr>
					<tr>
						<td class="til" align="left">
							内存：
						</td>
						<td>
							<s:property value="vmHostObj.VH_MEM"/>MB
						</td>
					</tr>
					<tr>
						<td class="til" align="left">
							存储：
						</td>
						<td>
							<s:property value="vmHostObj.VH_STORAGE"/>MB
						</td>
					</tr>
					<tr>
						<td class="til" align="left">
							运行状态：
						</td>
						<td>
							<s:if test="vmHostObj.VH_STAT == 1">
								运行
							</s:if>
							<s:elseif test="vmHostObj.VH_STAT == 2">
								停止
							</s:elseif>
							<s:else>
								异常
							</s:else>
						</td>
					</tr>
			</table>
		</div>
	</s:form>
</body>