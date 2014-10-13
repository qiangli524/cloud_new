<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="yvm_vmInfo" method="post" id="theForm">
		<div  align="left">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="20%">
						虚拟机名称
					</td>
					<td>
						<s:property value="theForm.NAME_EN"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						虚拟机操作系统
					</td>
					<td>
						<s:property value="theForm.osType"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						虚拟机版本
					</td>
					<td>
						<s:property value="theForm.CODE"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						CPU(个)
					</td>
					<td>
						<s:property value="theForm.CPU"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						内存(MB)
					</td>
					<td>
						<s:property value="theForm.MEMORY"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						存储(G)
					</td>
					<td>
						<s:property value="theForm.dataSize"/>
					</td>
				</tr> 
				<%-- 
				<tr>
					<td class="til">
						内存开销
					</td>
					<td>
						<s:property value="theForm.MODEL"/>
					</td>
				</tr> 
					--%>
				<tr>
					<td class="til">
						IP地址
					</td>
					<td>
						<s:if test="theForm.IP==null">
							未采集到IP
						</s:if>
						<s:else>
							<s:property value="theForm.IP"/>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til">
						DNS名称
					</td>
					<td>
						<s:property value="theForm.DNS"/>
					</td>
				</tr> 
				<%-- 
				<tr>
					<td class="til">
						EVC模式
					</td>
					<td>
						<s:property value="theForm.STORAGE"/>
					</td>
				</tr> 
				--%>
				<tr>
					<td class="til">
						状况
					</td>
					<td>
						<s:property value="theForm.INTERFACE"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						主机
					</td>
					<td>
						<s:property value="theForm.HostIP"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						关联的脚本
					</td>
					<td>
					<s:if test="theForm.resultList==null || theForm.resultList.size()<1"></s:if>
					<s:else>
						<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
								<tr>
									<th onclick="sort(theTable,0,'string')">脚本名称</th>
									<th onclick="sort(theTable,1,'string')">脚本内容</th>
									<th onclick="sort(theTable,2,'string')">脚本描述</th>
								</tr>
							<s:iterator value="theForm.resultList" id="theBean">
								<tr>
									<td><s:property value="#theBean.name"/></td>
									<td><s:property value="#theBean.content"/></td>
									<td><s:property value="#theBean.des"/></td>
								</tr>
							</s:iterator>
						</table>
					</s:else>
					</td>
				</tr>
				<%-- 
				<tr>
					<td class="til">
						vSphere HA 保护
					</td>
					<td>
						<s:property value="theForm.REMARK"/>
					</td>
				</tr> 
				--%>
			</table>
		</div>
</s:form>
</body>
