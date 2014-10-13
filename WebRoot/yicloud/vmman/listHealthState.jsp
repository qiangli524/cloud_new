<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
</head>
<body>
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
                   	--%>
			</table>
		</div>
	</s:form>
</body>
