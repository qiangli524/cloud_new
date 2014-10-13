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
<style type="text/css">
div.hidden {
	width: 200px;
	height: 30px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	text-overflow: ellipsis; /* IE/Safari */
	-ms-text-overflow: ellipsis;
	-o-text-overflow: ellipsis; /* Opera */
	-moz-binding: url("ellipsis.xml#ellipsis"); /*FireFox*/
}

.font-more{ width:150px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;
}
</style>
<body class="scrollbody">
	<div class="mainbody">
		<s:form action="resourcestatistics_viewSeriousVM.do" method="post"
			cssClass="theForm" id="theForm">
			<s:hidden id="vtype" name="vtype"></s:hidden>
			<div class="pd-20 bgcolor-1">
				<div class="bord-1 pd-10">
					<table id="theTable" width="100%" class="blue-table sorttable"
						border="0" cellspacing="0">
						<thead>
							<tr>
								<th onclick="sort(theTable,0,'string')">虚拟机名称</th>
								<th onclick="sort(theTable,1,'string')">IP</th>
								<th onclick="sort(theTable,6,'string')">电源状态</th>
								<th onclick="sort(theTable,2,'string')">虚拟化类型</th>
								<th onclick="sort(theTable,3,'string')">操作系统</th>
								<th onclick="sort(theTable,4,'string')">CPU</th>
								<th onclick="sort(theTable,5,'string')">内存</th>
							<%--	<th onclick="sort(theTable,7,'string')">链接状态</th> --%>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="vmList" id="theBean">
								<tr>
									<td>
										<span style="color: black;" class="font-more" title='<s:property value="#theBean.VH_NAME" />'>
											<s:property value="#theBean.VH_NAME" />
										</span>
									</td>
									<td><s:property value="#theBean.VH_IP" /></td>
									
									<td><s:if test="#theBean.VH_STAT==1">
							正在运行
						</s:if> <s:if test="#theBean.VH_STAT==0">
							已关闭
						</s:if> <s:if test="#theBean.VH_STAT==2">
							挂起
						</s:if></td>
						
									<td><s:if test="#theBean.VH_TYPE==3">
			  				XEN虚拟化
			  			</s:if> <s:elseif test="#theBean.VH_TYPE==1">
			  				VMWARE虚拟化
			  			</s:elseif></td>
									<td>
										<span style="color: black;" class="font-more" title='<s:property value="#theBean.VH_SYSTEM" />'>
											<s:property value="#theBean.VH_SYSTEM" />
										</span>
									</td>
									<td><s:property value="#theBean.VH_CPU" />核</td>
									<td><s:property value="#theBean.VH_MEM" />MB</td>
									
								<%--	<td><s:if test="#theBean.CONNECT_STATUS=='connected'">正常链接</s:if>
										<s:elseif test="#theBean.CONNECT_STATUS=='disconnected'">断开连接</s:elseif>
										<s:elseif test="#theBean.CONNECT_STATUS=='orphaned'">孤立的</s:elseif>
										<s:elseif test="#theBean.CONNECT_STATUS=='inaccessible'">不可访问的</s:elseif>
										<s:else>不可用</s:else></td>  --%>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="pages mgb-10">
						<!-- 分页 -->
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
				</div>
		</s:form>
	</div>
</body>
