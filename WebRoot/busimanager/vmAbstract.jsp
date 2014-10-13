<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
				<tr>
	  				<td class="til" width="20%" align="left">虚拟化类型</td>
	  				<td align="left">
	  					<s:if test="vmObj.VH_TYPE==1">&nbsp;&nbsp;vmware</s:if>
	  					<s:else>&nbsp;&nbsp;xen</s:else>
	  				</td>
	  			</tr>
				<tr>
	  				<td class="til" width="20%" align="left">虚拟机名称</td>
	  				<td align="left">&nbsp;&nbsp;<s:property value="vmObj.VH_NAME"/></td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">虚拟机操作系统</td>
	  				<td align="left">&nbsp;&nbsp;<s:property value="vmObj.VH_SYSTEM"/></td>
	  			</tr>
	  			<tr >
					<td class="til" align="left">CPU(个)</td>
					<td align="left">&nbsp;&nbsp;<s:property value="vmObj.VH_CPU" />个</td>
				</tr> 
	  			<tr>
	  				<td class="til" align="left">内存</td>
	  				<td align="left">&nbsp;&nbsp;<s:property value="vmObj.VH_MEM"/>MB</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">存储</td>
	  				<td align="left">&nbsp;&nbsp;<s:property value="vmObj.VH_STORAGE"/>MB</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">IP地址</td>
	  				<td align="left">&nbsp;&nbsp;<s:property value="vmObj.VH_IP"/></td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">DNS名称</td>
	  				<td align="left">&nbsp;&nbsp;<s:property value="vmObj.DNS"/></td>
	  			</tr>
	  			<%-- <tr>
	  				<td class="til" align="left">所属主机名称</td>
	  				<td align="left">
	  					<td align="left">&nbsp;&nbsp;<s:property value="vmObj.EQ_NAME"/></td>
	  				</td>
	  			</tr> --%>
			</table>
		</div>
  </body>
</html>
