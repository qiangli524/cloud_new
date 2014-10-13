<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
}%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">

var service_type = '<s:property value="tree.service_type" />';

function searchRequest(){
	if(service_type==2){//datanode
		datanode.action = "hadoopService_listServiceList.do?tree.service_type="+service_type;
	    datanode.submit();
	}else{
		nodeManager.action = "hadoopService_listServiceList.do?tree.service_type="+service_type;
		nodeManager.submit();
	}
}

function resetForm1(data){
	if(service_type == '2'){//datanode
		$("#host_ip").attr("value","");
		$("#admin_state").attr("value","");
	}else{
		$("#host_ip").attr("value","");
		$("#node_address").attr("value","");
	}
}

</script>
</head>
<body class="pop-body scrollbody">
	<s:if test="tree.service_type==2"><!-- datanode -->
		<s:form id="datanode" action="hadoopService_listServiceList.do?tree.service_type=2">
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">服务状态</h2>
		       	<div class="bord-1 pd-10">
					<table width="100%"  border="0">
						<tr>
							<td class="til">所属主机IP:</td>
							<td>
								<s:textfield name="datanode.host_ip" id="host_ip" size="15"></s:textfield>
							</td>
							<td class="til">服务状态:</td>
							<td>
								<s:textfield name="datanode.admin_state" id="admin_state" size="15"></s:textfield>
							</td>
							<td colspan="10" class="pd-10">
								<div align="center">
									<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" id="searchForm" onclick="javascript:searchRequest()" /></span>
									<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" id="resetForm" onclick="resetForm1('2')"/></span>
								</div>
							</td>
						</tr>
					</table>
					<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
						<thead>
					  		<tr>
					  			<th>所属主机IP</th>
						   		<%--<th>最后链接时间</th>--%>
						   		<th>服务状态</th>
						   		<th>配置容量</th>
						   		<th>DFS使用量</th>   
						   		<th>非DFS使用量</th>
						   		<th>剩余容量</th>
						   		<th>DFS使用百分比</th>
						   		<th>剩余百分比</th>
						   		<th>块数</th>
						   		<th>损坏卷宗数</th>
					 	 	</tr>
					 	 </thead>
					 	 <tbody>
					 		 <s:iterator value="resultList" id="theBean">
								 <tr>
									<td>
										<s:if test="#theBean.host_ip!=null && #theBean.host_ip!=''">
											<s:property value="#theBean.host_ip"/>
										</s:if>
										<s:else>
											--
										</s:else>
									</td>
									<%--<td>
										<s:property value="#theBean.last_contact"/>s前
									</td>
									--%><td>
										<s:property value="#theBean.admin_state"/>
									</td>
									<td>
										<fmt:formatNumber value="${(theBean.configured_capacity/1024)}" pattern="#,###.##" type="number"></fmt:formatNumber>G
									</td>
									<td>
										<fmt:formatNumber value="${(theBean.dfs_used/1024)}" pattern="#,###.##" type="number"></fmt:formatNumber>G
									</td>
									<td>
										<fmt:formatNumber value="${(theBean.non_dfs_used/1024)}" pattern="#,###.##" type="number"></fmt:formatNumber>G
									</td>
									<td>
										<fmt:formatNumber value="${(theBean.remaining/1024)} " pattern="#,###.##" type="number"></fmt:formatNumber>G
									</td>
									<td>
										<s:property value="@java.lang.Math@round(#theBean.dfs_used_percent*10000)/100.0"/>%
									</td>
									<td>
										<s:property value="@java.lang.Math@round(#theBean.remaining_percent*10000)/100.0"/>%
									</td>
									<td>
										<s:property value="#theBean.blocks"/>
									</td>
									<td>
									<s:if test="#theBean.failed_volumes!=null && #theBean.failed_volumes!=''">
											<s:property value="#theBean.failed_volumes"/>
										</s:if>
										<s:else>
											--
										</s:else>	
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="pages mgb-10"><!-- 分页 -->
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=hostForm" />
					</div>
				</div>
			</div>
		</s:form>
	</s:if>
	<s:elseif test="tree.service_type==4"><!-- nodemanager -->
		<s:form id="nodeManager" action="hadoopService_listServiceList.do?tree.service_type=4">
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">服务状态</h2>
		       	<div class="bord-1 pd-10">
					<table width="100%"  border="0">
						<tr>
							<td class="til">节点地址:</td>
							<td>
								<s:textfield name="nodeManager.node_address" id="node_address" size="15"></s:textfield>
							</td>
							<td class="til">所属主机IP:</td>
							<td>
								<s:textfield name="nodeManager.host_ip" id="host_ip" size="15"></s:textfield>
							</td>
							<td colspan="10" class="pd-10">
								<div align="center">
									<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" id="searchForm" onclick="javascript:searchRequest()" /></span>
									<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" id="resetForm" onclick="resetForm1('4')"/></span>
								</div>
							</td>
						</tr>
					</table>
					<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
						<thead>
					  		<tr>
					  			<th>所属主机IP</th>
						   		<th>机架位置</th>
						   		<th>节点状态</th>
						   		<th>节点地址</th>   
						   		<th>访问地址</th>
						   		<th>健康状态</th>
						   		<%--<th>最后一次更新健康状态时间</th>
						   		--%><th>健康报告</th>
						   		<th>容器数量</th>
						   		<th>已用内存</th>
						   		<th>可用内存</th>
					 	 	</tr>
					 	 </thead>
					 	 <tbody>
					 		 <s:iterator value="resultList" id="theBean">
								 <tr>
									<td>
									<s:if test="#theBean.host_ip!=null && #theBean.host_ip!=''">
											<s:property value="#theBean.host_ip"/>
										</s:if>
										<s:else>
											--
										</s:else>
									</td>
									<td>
										<s:property value="#theBean.rack"/>
									</td>
									<td>
										<s:property value="#theBean.node_state"/>
									</td>
									<td>
										<s:property value="#theBean.node_address"/>
									</td>
									<td>
										<s:if test="#theBean.visit_address!=null">
											<s:property value="#theBean.visit_address"/>
										</s:if>
										<s:else>
											--
										</s:else>
										
									</td>
									<td>
										<s:property value="#theBean.health_status"/>
									</td>
									<%--<td>
										<s:property value="#theBean.last_health_update"/>
									</td>
									--%><td>
										<s:if test="#theBean.health_report!=null && #theBean.health_report!=''">
											<s:property value="#theBean.health_report"/>
										</s:if>
										<s:else>
											--
										</s:else>
									</td>
									<td>
										<s:property value="#theBean.containers_num"/>
									</td>
									<td>
										<fmt:formatNumber value="${(theBean.used_mem/1024)}" pattern="#,###.##" type="number"></fmt:formatNumber>G
									</td>
									<td>
										<fmt:formatNumber value="${(theBean.available_mem/1024)}" pattern="#,###.##" type="number"></fmt:formatNumber>G
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="pages mgb-10"><!-- 分页 -->
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=nodeManager" />
					</div>
				</div>
			</div>
		</s:form>
	</s:elseif>
</body>