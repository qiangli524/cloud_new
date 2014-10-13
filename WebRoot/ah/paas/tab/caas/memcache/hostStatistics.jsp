<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body  class="pop-body scrollbody">
  <div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
				<tr>
	  				<td class="til" width="20%" align="left">主机名称</td>
	  				<td align="left">&nbsp;&nbsp;<s:property value="hostObj.host_name"/></td>
	  			</tr>
				<tr>
	  				<td class="til" align="left">IP地址</td>
	  				<td align="left">&nbsp;&nbsp;<s:property value="hostObj.ip"/></td>
	  			</tr>
	  			<tr >
					<td class="til" align="left">CPU</td>
					<td align="left">&nbsp;&nbsp;<s:property value="hostObj.cpu_num" />个</td>
				</tr> 
	  			<tr>
	  				<td class="til" align="left">内存</td>
	  				<td align="left">&nbsp;&nbsp;<fmt:formatNumber value="${(hostObj.mem_size/1024)}" pattern="#,###.##" type="number"/>GB</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">存储</td>
	  				<td align="left">&nbsp;&nbsp;<fmt:formatNumber value="${(hostObj.local_disk_size/1024)}" pattern="#,###.##" type="number"/>GB</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">操作系统</td>
	  				<td align="left">&nbsp;&nbsp;<s:property value="hostObj.os"/></td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">主机类型</td>
	  				<td align="left">&nbsp;&nbsp;
						<s:if test="hostObj.host_type==1">
	  						虚拟机
	  					</s:if>
	  					<s:else>
	  						物理机
	  					</s:else>
					</td>
	  			</tr>
			</table>
		</div>
  </body>
</html>