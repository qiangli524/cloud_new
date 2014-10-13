<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>

<html>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
<s:form action="" method="post" id="obj" >
		<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
					<td class="til" width="25%">模板名称</td>
			 		<td>
						<s:property value="obj.name"/>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">CPU</td>
					<td>
						<s:property value="obj.cpu"/>个
					</td>
				</tr> 
				<tr align="left">
					<td class="til">内存</td>
					<td>
						<s:property value="obj.mem"/>M
					</td>
				</tr> 
				<tr align="left">
					<td class="til">存储</td>
					<td>
						<s:property value="obj.store"/>G
					</td>
				</tr> 
				<tr align="left">
					<td class="til">操作系统</td>
					<td>
						<s:property value="obj.system"/>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">类型</td>
					<td>
						<s:if test="obj.type==1">vmware模板</s:if>
						<s:elseif test="obj.type==2">xen模板</s:elseif>
						<s:else>OVF模板</s:else>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">是否公有</td>
					<td>
					<s:if test="obj.isPublic==0">公有</s:if>
					<s:else>私有</s:else>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">位置</td>
					<td>
						<s:property value="obj.position"/>
					</td>
				</tr>
				<tr align="left">
					<td class="til">备注</td>
					<td>
						<s:property value="obj.remark"/>
					</td>
				</tr>  
			</table>
		</div>
</s:form>
</body>
</html>
