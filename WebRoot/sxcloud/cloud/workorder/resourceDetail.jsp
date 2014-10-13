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
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
</head>
<body class="pop-body">
	<div>
		<div class="table-ct">
			<s:iterator value="resultList" id="theBean">
				<table width="100%" border="0" cellspacing="0"
					class="blue-table sorttable">
					<tr>
						<td class="til" width="20%" align="left">项目名称:</td>
						<td align="left" width="30%"><span class="blue"> <s:property
									value="#theBean.PROJECT_NAME" /> </span></td>
						<td class="til vt" align="left" width="20%">业务名称:</td>
						<td align="left" width="30%"><span class="blue"> <s:property
									value="#theBean.ResBusiSystemName" /> </span></td>
					</tr>
					<tr>
						<td class="til" width="20%" align="left">项目责任人:</td>
						<td align="left" width="30%"><span class="blue"> <s:property
									value="#theBean.PROJECT_USER_NAME" /> </span></td>
						<td class="til vt" align="left" width="20%">&nbsp;</td>
						<td align="left" width="30%"><span class="blue">
								&nbsp; </span></td>
					</tr>
					<tr>
						<td class="til" width="20%" align="left">虚拟机名称:</td>
						<td align="left"><span class="blue"> <s:property
									value="#theBean.VM_NAME" /> </span></td>
						<td class="til vt" align="left">CPU数量:</td>
						<td align="left"><span class="blue"> <s:property
									value="#theBean.CPU_NUM" />核 </span></td>
					</tr>
					<tr>
						<td class="til" align="left">内存大小:</td>
						<td align="left"><span class="blue"> <s:if
									test="%{MEM_SIZE%1024==0}">
									<fmt:formatNumber type="currency" pattern="#0G"
										value="${MEM_SIZE/1024}"></fmt:formatNumber>
								</s:if> <s:else>
									<fmt:formatNumber type="currency" pattern="#0.00G"
										value="${MEM_SIZE/1024}"></fmt:formatNumber>
								</s:else> </span></td>
						<td class="til vt" align="left">存储大小:</td>
						<td align="left"><span class="blue"> <s:if
									test="%{SR_SIZE%1024==0}">
									<fmt:formatNumber type="currency" pattern="#0G"
										value="${SR_SIZE/1024}"></fmt:formatNumber>
								</s:if> <s:else>
									<fmt:formatNumber type="currency" pattern="#0.00G"
										value="${SR_SIZE/1024}"></fmt:formatNumber>
								</s:else> </span></td>
					</tr>
					<tr>
						<td class="til" align="left">网络域:</td>
						<td align="left"><span class="blue"> <s:property
									value="#theBean.NETWORK_NAME" /> </span></td>
						<td class="til vt" align="left">IP地址:</td>
						<td align="left"><span class="blue"> <s:property
									value="#theBean.IPADDRESS" /> </span></td>
					</tr>
					<tr>
						<td class="til" align="left">模板名称:</td>
						<td align="left"><span class="blue"> <s:property
									value="#theBean.TEMPLATENAME" /> </span></td>
						<td class="til vt" align="left">模板类型:</td>
						<td align="left"><span class="blue"> <s:property
									value="#theBean.TEMPLATE_TYPE" /> </span></td>
					</tr>
					<tr>
						<td class="til" align="left">虚拟化类型:</td>
						<td align="left"><span class="blue"> <s:if
									test="#theBean.TEMP_TYPE==1">VMWARE</s:if> <s:if
									test="#theBean.TEMP_TYPE==2">XEN</s:if> </span></td>
						<td class="til vt" align="left">&nbsp;</td>
						<td align="left">&nbsp;</td>
					</tr>


					<tr>
						<td class="til" align="left">处理状态:</td>
						<td align="left"><span class="blue"> <s:if
									test="#theBean.STATE==0">
									<s:if test="#theBean.STATUS==3">处理失败</s:if>
									<s:elseif test="#theBean.STATUS==4">创建成功，未挂载存储</s:elseif>
									<s:elseif test="#theBean.STATUS==2">处理成功</s:elseif>
									<s:elseif test="#theBean.STATUS==1">处理中</s:elseif>
									<s:elseif test="#theBean.STATUS==0">待处理</s:elseif>
									<s:else>
								未知状态
							</s:else>
								</s:if> <s:else>
							工单数据不合法，不可处理
						</s:else> </span></td>
						<td class="til vt" align="left">处理信息:</td>
						<td align="left"> <span
								style="color: red;" class="font-more"
								title='<s:property value="#theBean.MESSAGE"/>'> <s:property
										value="#theBean.MESSAGE" default="无" /> </span></td>
					</tr>
					<tr>
						<td class="til" align="left">处理时间:</td>
						<td align="left"><span class="blue"> <s:property
									value="#theBean.DEALTIME" default="---" /> </span></td>
						<td class="til vt" align="left">处理次数:</td>
						<td align="left"><span class="blue"> <s:property
									value="#theBean.DEAL_COUNT" /> </span></td>
					</tr>

					<tr>
						<td class="til" align="left">应用目录(","分割):</td>
						<td align="left" colspan="3"><span class="blue"> <s:property
									value="#theBean.resAppDir" /> </span></td>
					</tr>
					<tr>
						<td class="til vt" align="left">应用大小(G):</td>
						<td align="left" colspan="3"><span class="blue"> <s:property
									value="#theBean.resAppSize" /> </span></td>
					</tr>
				</table>
			</s:iterator>
		</div>
	</div>
</body>
