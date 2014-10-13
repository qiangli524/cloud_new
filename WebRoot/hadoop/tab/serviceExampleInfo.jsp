<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<style type="text/css">
</style>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>  
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
				<s:if test="retHostList.size() != 0">
					<s:iterator value="retHostList" id="theBean">
						<tr>
							<td class="til" width="23%" align="right">
								主机名称：
							</td>
							<td align="left">
								<s:property value="#theBean.host_name"/>
							</td>
						</tr>
						<tr>
							<td class="til" width="23%" align="right">
								主机IP：
							</td>
							<td align="left">
								<s:property value="#theBean.ip"/>
							</td>
						</tr>
						<tr>
							<td class="til" width="23%" align="right">
								主机类型：
							</td>
							<td align="left">
								<s:if test="#theBean.host_type == 0">
									物理机
								</s:if>
								<s:else>
									虚拟机
								</s:else>
							</td>
						</tr>
						<tr>
							<td class="til" width="23%" align="right">
								操作系统：
							</td>
							<td align="left">
								<s:property value="#theBean.os"/>
							</td>
						</tr>
						<%--<tr>
							<td class="til" width="23%" align="right">
								服务器类型：
							</td>
							<td>
								<s:if test="#theBean.service_type == 0">
									0x86服务器
								</s:if>
								<s:elseif  test="#theBean.service_type == 1">
									机架服务器
								</s:elseif>
								<s:elseif  test="#theBean.service_type == 2">
									vmware虚拟机
								</s:elseif>
								<s:elseif  test="#theBean.service_type == 3">
									xen虚拟机
								</s:elseif>
								<s:elseif  test="#theBean.service_type == 4">
									kvm虚拟机
								</s:elseif>
								<s:else>
									其他
								</s:else>
							</td>
						</tr>
						--%><tr>
							<td class="til" width="23%" align="right">
								cpu：
							</td>
							<td align="left">
								<s:property value="#theBean.cpu_num"/>个
							</td>
						</tr>
						<tr>
							<td class="til" width="23%" align="right">
								内存：
							</td>
							<td align="left">
								<s:property value="@java.lang.Math@round(#theBean.mem_size/1024*100)/100.0"/>G
							</td>
						</tr>
						<tr>
							<td class="til" width="23%" align="right">
								本地存储：
							</td>
							<td align="left">
								<s:property value="@java.lang.Math@round(#theBean.local_disk_size*100)/100.0"/>G
							</td>
						</tr>
						<tr>
							<td class="til" width="23%" align="right">
								交换区：
							</td>
							<td align="left">
								<s:property value="@java.lang.Math@round(#theBean.swap_size/1024*100)/100.0"/>G
							</td>
						</tr>
						<tr>
							<td class="til" width="23%" align="right">
								主机状态：
							</td>
							<td align="left">
								<s:if test="#theBean.status == 1">
									运行
								</s:if>
								<s:else>
									停止
								</s:else>
							</td>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					没有对应的主机数据....
				</s:else>
			</table>
		</div>
	</s:form>
</body>