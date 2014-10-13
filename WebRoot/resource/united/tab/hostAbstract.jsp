<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
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
  
  <body>
  <div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
				<tr>
	  				<td class="til" width="20%" align="left">主机名称</td>
	  				<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="hostInfo.eq_name"/></td>
	  			</tr>
	  			<tr>
	  				<td class="til" width="20%" align="left">虚拟化类型</td>
	  				<td align="left">&nbsp;&nbsp;&nbsp;
	  				<s:if test="hostInfo.hasvertual==3">Xen</s:if>
	  				<s:elseif test="hostInfo.hasvertual==4">Vmware</s:elseif>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">IP地址</td>
	  				<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="hostInfo.eq_ip"/></td>
	  			</tr>
	  			<tr >
					<td class="til" align="left">型号</td>
					<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="hostInfo.MODEL" /></td>
				</tr> 
	  			<tr>
	  				<td class="til" align="left">cpu核数</td>
	  				<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="hostInfo.cpu_cl"/></td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">cpu主频</td>
	  				<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="hostInfo.cpu_fq"/>GHZ</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">内存</td>
	  				<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="hostInfo.mem"/>M</td>
	  			</tr>
	  			<!--  
	  			<tr>
	  				<td class="til" align="left">是否可用</td>
	  				<td align="left">&nbsp;&nbsp;&nbsp;
	  				<s:if test="hostInfo.STATUS == 1">
	  					可用
	  				</s:if>
	  				<s:else>
	  					不可用
	  				</s:else>
	  				</td>
	  			</tr>
	  			-->
			</table>
		</div>
  </body>
</html>
