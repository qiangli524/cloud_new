<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%
  	String ID =	(String)request.getAttribute("ID");
	String hostId = (String)request.getAttribute("hostId");
	String oper = (String)request.getAttribute("oper");
	String id = (String)request.getAttribute("id");
	String type= (String)request.getAttribute("type");
%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />  
<meta http-equiv="expires" content="0" />
<head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/jquery-1.7.2.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.core.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.widget.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.tabs.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/demos.css" />
	<script>
	$(function() {
		$( "#tabs" ).tabs({
			ajaxOptions: {
				cache:false,	
				error: function( xhr, status, index, anchor ) {
					$( anchor.hash ).html(
						"Couldn't load this tab. We'll try to fix this as soon as possible. " +
						"If this wouldn't be a demo." );
				}
			}
		});
	});
	</script>
</head>
<body>
	<div class="demo">

<div id="tabs">
	<ul>
		<s:if test="#request.type==8"> <!-- 数据中心 -->
			<li><a href="yvm_dataCenterInfo.do?id=<%=id%>" >摘要</a></li>
			<li><a href="yvm_dataCenterVM.do?type=<%=type%>&id=<%=id%>" >虚拟机</a></li>
			<li><a href="yvm_dataCenterHost.do?type=<%=type%>&id=<%=id%>">主机</a></li>
		</s:if>
		<s:elseif test="#request.type==3 || #request.type==21"><!-- 集群 -->
			<li><a href="yvm_clusterInfo.do?id=<%=id%>" >摘要</a></li>
			<li><a href="yvm_clusterVM.do?type=<%=type%>&id=<%=id%>" >虚拟机</a></li>
			<li><a href="yvm_clusterHost.do?type=<%=type%>&id=<%=id%>" >主机</a></li>
			<li><a href="yvm_clusterResource.do?type=<%=type%>&id=<%=id%>" >资源分配</a></li>
		</s:elseif>
		<s:elseif test="#request.type==1 || #request.type==25"> <!-- 主机 -->
			<li><a href="yvm_hostInfo.do?id=<%=id%>" >摘要</a></li>
			<li><a href="yvm_dataCenterVM.do" >虚拟机</a></li>
		</s:elseif>
		<s:elseif test="#request.type==0"><!-- 虚拟机 -->
			<li><a href="yvm_vmInfo.do?id=<%=id%>" >摘要</a></li>
			<li><a href="yvm_dataCenterVM.do" >资源分配</a></li>
		</s:elseif>
		<s:elseif test="#request.type==22 || #request.type==23"><!-- 模板镜像 -->
			<li><a href="yvm_vmInfo.do?id=<%=id%>" >摘要</a></li>
		</s:elseif>
		<s:elseif test="#request.type==18">
			<li><a href="datastore_listDataStore.do?id=<%=id%>">摘要</a></li>
			<li><a href="yvm_dataCenterVM.do">虚拟机</a></li>
			<li><a href="yvm_dataCenterHost.do">主机</a></li>
		</s:elseif>
		<s:elseif test="#request.type==19">
			<li><a href="net_listNetInfo.do?oper=1">基本信息</a></li>
		</s:elseif>
		
	</ul>
</div>

</div><!-- End demo -->
<s:form method="post" id="theForm">
</s:form>
	<%--
	<s:form action="listHealthState" method="post" cssStyle="theForm"
		id="theForm">
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til" width="30%">
						名称
					</td>
					<td>
						<s:property value="theForm.NAME_ZH" />
					</td>
				</tr>
				<tr>
					<td class="til">
						IP
					</td>
					<td>
						<s:property value="theForm.IP" />
					</td>
				</tr>
				<tr>
					<td class="til">
						IP状态
					</td>
					<td>
						<s:if test="theForm.IP_ISBLOCKED==null">
							暂无信息
						</s:if>
						<s:else>
							<s:if test="theForm.IP_ISBLOCKED==0">
								<img src="sxcloud/images/virtual/open.png" />
							</s:if>
							<s:elseif test="theForm.IP_ISBLOCKED==1">
								<img src="sxcloud/images/virtual/close.png" />
							</s:elseif>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til">
						端口状态
					</td>
					<td>
						<%
							String PORT_ISBLOCKED = (String) request
									.getAttribute("PORT_ISBLOCKED");
							if (PORT_ISBLOCKED != null && !"".equals(PORT_ISBLOCKED)) {
						%>
						<%=request.getAttribute("PORT_ISBLOCKED")%>
						<%
							} else {
						%>
						暂无信息
						<%
							}
						%>
					</td>
				</tr>
				<tr>
					<td class="til">
						CPU利用率
					</td>
					<td>
						<s:if test="theForm.CPU==null">
							暂无信息
						</s:if>
						<s:else>
							<s:property value="theForm.CPU" />%
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til">
						内存利用率
					</td>
					<td>
						<s:if test="theForm.MEMORY==null">
							暂无信息
						</s:if>
						<s:else>
							<s:property value="theForm.MEMORY" />%
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til">
						IO(单位：kbps)
					</td>
					<td>
						<s:if test="theForm.IO==null">
							暂无信息
						</s:if>
						<s:else>
							<s:property value="theForm.IO" />
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til">
						存储空间利用率
					</td>
					<td>
						<s:if test="theForm.STORAGE==null">
							暂无信息
						</s:if>
						<s:else>
							<s:property value="theForm.STORAGE" />%
						</s:else>
					</td>
				</tr>
				<%--  
                   <tr>              
                  <td class="til">
						应用是否存在
					</td>
					<td>
					<logic:equal name="theForm" property="APP_HASDOWN" value="0" >否</logic:equal>
					<logic:equal name="theForm" property="APP_HASDOWN" value="1" >是</logic:equal>                  
					</td>
                   </tr>
                   <tr>              
                  <td class="til">
						中间件是否存在
					</td>
					<td>
					<logic:equal name="theForm" property="MIDDLE_HASDOWN" value="0" >否</logic:equal>
					<logic:equal name="theForm" property="MIDDLE_HASDOWN" value="1" >是</logic:equal>                  
					</td>
                   </tr>
                    <tr>              
                  <td class="til">
						应用名称
					</td>
					<td>
					    <bean:write name="theForm" property="APP_DOWN_NAMES"/>                 
					</td>
                   </tr>
                    <tr>              
                  <td class="til">
						中间件名称
					</td>
					<td>
					    <bean:write name="theForm" property="MIDDLE_DOWN_NAMES"/>                 
					</td>
                   </tr>
			</table>
		</div>
	</s:form>
	--%>
</body>
