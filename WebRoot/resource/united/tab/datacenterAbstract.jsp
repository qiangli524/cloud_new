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
	  				<td class="til" width="20%" align="left">集群个数</td>
	  				<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="map.clucount"/>个</td>
	  			</tr>
				<tr>
	  				<td class="til" width="20%" align="left">主机个数</td>
	  				<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="map.hostcount"/>个</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">虚拟机个数</td>
	  				<td align="left">&nbsp;&nbsp;&nbsp;<s:property value="map.vmcount"/>个</td>
	  			</tr>
			</table>
		</div>
  </body>
</html>
