<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/link.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/yicloud/xen/js/common.js"></script>
</head>
<body>
	<div class="mainbody">
			<s:form action="resourcestatistics_viewSeriousHost.do" method="post"
			cssClass="theForm" id="theForm">
			<s:hidden id="allo" name="allo"></s:hidden>
			<s:hidden id="id" name="id"></s:hidden>
			<div class="pd-20 bgcolor-1">
				<div class="bord-1 pd-10">
					<table id="theTable" width="100%" class="blue-table sorttable"
						border="0" cellspacing="0">
						<thead>
							<tr>
								<th onclick="sort(theTable,0,'string')">主机名称</th>
								<th onclick="sort(theTable,1,'string')">主机类型</th>
								<th onclick="sort(theTable,7,'string')">虚拟化类型</th>
								<th onclick="sort(theTable,8,'string')">电源状态</th>
								<th onclick="sort(theTable,2,'string')">主机IP地址</th>
								<th onclick="sort(theTable,3,'string')">CPU</th>
								<th onclick="sort(theTable,4,'string')">内存</th>
								<th onclick="sort(theTable,5,'string')">存储</th>
								<th onclick="sort(theTable,6,'string')">网卡</th>
							<%--	<th onclick="sort(theTable,9,'string')">链接状态</th> --%>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="hostList" id="theBean">
								<tr>
									<td><s:property value="#theBean.eq_name" /></td>
									<td><s:if test="#theBean.eq_type == 1">
												IBM小型机
											</s:if> <s:elseif test="#theBean.eq_type == 2">
												IBM刀片
											</s:elseif> <s:elseif test="#theBean.eq_type == 3">
												IBM普通刀片
											</s:elseif> <s:elseif test="#theBean.eq_type == 4">
												HPx86刀片
											</s:elseif> <s:elseif test="#theBean.eq_type == 5">
												机架服务器
											</s:elseif></td>
									<td><s:if test="#theBean.hasvertual == 3">
												XEN
											</s:if> <s:elseif test="#theBean.hasvertual==4">
												VMWARE	
											</s:elseif> <s:elseif test="#theBean.hasvertual==1">
												PowerVM
											</s:elseif> <s:elseif test="#theBean.hasvertual==2">
												KVM
											</s:elseif> <s:elseif test="#theBean.hasvertual==0">
												非虚拟化
											</s:elseif></td>
									<td><s:if test="#theBean.STATUS == 0">
												未采集到
											</s:if> <s:elseif test="#theBean.STATUS == 1">
												正常启动
											</s:elseif> <s:elseif test="#theBean.STATUS == 2">
												关闭
											</s:elseif> <s:else>
												异常
											</s:else></td>
									<td><s:property value="#theBean.eq_ip" /></td>
									<td><s:property value="#theBean.cpu_cl" /> 核</td>
									<td width="50px;"><s:if test='#theBean.mem != null'>
											<s:property
												value="@java.lang.Math@round(#theBean.mem/1024) " />
												(G)
											</s:if></td>
									<td><s:if test="#theBean.storage_num == null">
												0
											</s:if> <s:else>
											<s:property value="#theBean.storage_num" />
										</s:else> 块</td>
									<td><s:property value="#theBean.NIC_NUM" />个</td>
									
								<%--	<td><s:if test='#theBean.connectStatus=="connected"'>正常链接</s:if>
										<s:elseif test='#theBean.connectStatus=="disconnected"'>断开连接</s:elseif>
										<s:else>无应答</s:else></td>  --%>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="pages mgb-10">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
				</div>
		</s:form>
	</div>
</body>
