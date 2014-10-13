<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
</head>
<body>
<s:form action="tree_imageInfo" method="post" id="imageForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
				<tr align="left">
					<td class="til" width="20%">
						模板名称
					</td>
					<td>
						<s:property value="imageForm.IM_NAME"/>
					</td>
				</tr align="left"> 
				<tr align="left">
					<td class="til">
						模板描述
					</td>
					<td>
						<s:property value="imageForm.IM_DESC"/>
					</td>
				</tr> 
				
			<%-- 	<tr >
					<td class="til">
						镜像状态
					</td>
					<td>
						<s:property value="imageForm.IM_STATE"/>
					</td>
				</tr>
			--%>	
				<tr align="left">
					<td class="til" width="20%">
						CPU(个)
					</td>
					<td>
						<s:property value="imageForm.IM_CPU"/>个
					</td>
				</tr> 
				<tr align="left">
					<td class="til">
						内存
					</td>
					<td>
						<s:property value="imageForm.IM_MEM"/>(MB)
					</td>
				</tr> 
				<tr align="left">
					<td class="til">
						存储
					</td>
					<td>
						<s:property value="imageForm.IM_STORAGE"/>(G)
					</td>
				</tr>
				
				<tr align="left">
					<td class="til">
						操作系统
					</td>
					<td>
						<s:property value="imageForm.IM_SYSTEM"/>
					</td>
				</tr>
				<tr align="left">
					<td class="til">
						模板路径
					</td>
					<td>
						<s:property value="imageForm.templetPath"/>
					</td>
				</tr>
				<tr align="left">
					<td class="til">
						关联的脚本
					</td>
					<td>
					<s:if test="imageForm.resultList==null || imageForm.resultList.size()<1"></s:if>
					<s:else>
						<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
								<tr>
									<th>脚本名称</th>
									<th>脚本内容</th>
									<th>脚本描述</th>
								</tr>
							<s:iterator value="imageForm.resultList" id="theBean">
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
			</table>
		</div>
</s:form>
</body>
