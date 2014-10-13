<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<head>
</head>
<body>
<s:form action="yvm_hostInfo" method="post" id="theForm">
		<div  align="left">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="20%" > 
						主机IP地址
					</td>
					<td  >
						<s:property value="theForm.ip" />
					</td>
				</tr>
				<tr >
					<td class="til" width="20%" > 
						制造商
					</td>
					<td  >
						<s:property value="theForm.HOST_PROC" />
					</td>
				</tr> 
				<tr >
					<td class="til" >
						型号
					</td>
					<td >
						<s:property value="theForm.MODEL" />
					</td>
				</tr> 
				<tr >
					<td class="til" >
						cpu内核
					</td>
					<td >
						<s:property value="theForm.cpu" />
					</td>
				</tr> 
				<tr >
					<td class="til" >
						处理器类型
					</td>
					<td >
						<s:property value="theForm.CPU_DESC" />
					</td>
				</tr> 
				<%--
				<tr>
					<td class="til" align="left">
						处理器插槽
					</td>
					<td align="left">
						<s:property value="theForm.MODEL" value="1"/>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						每个插槽的内核数
					</td>
					<td align="left">
						<s:property value="theForm.CPU" value="4"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						逻辑处理器
					</td>
					<td align="left">
						<s:property value="theForm.MEMORY" value="4"/>
					</td>
				</tr> 
				--%>
				<tr >
					<td class="til" >
						内存
					</td>
					<td >
						<s:property value="theForm.mem"/>MB
					</td>
				</tr> 
				<tr >
					<td class="til" >
						网卡数目
					</td>
					<td >
						<s:property value="theForm.NIC_NUM"/>
					</td>
				</tr> 
				<tr >
					<td class="til">
						状况
					</td>
					<td >
						<s:if test="theForm.Host_status==1">
							已连接
						</s:if>
						<s:else>
							未连接
						</s:else>
					</td>
				</tr> 
				<%--
				<tr>
					<td class="til" align="left">
						虚拟机和模板
					</td>
					<td align="left">
						<s:property value="theForm.AUXILIARY" value="3"/>
					</td>
				</tr> 
				--%>
			</table>
		</div>
</s:form>
</body>
