<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" >
				<tr>
	  				<td class="til" width="20%" align="left">数据库名称</td>
	  				<td align="left">&nbsp;
	  					<s:property value="paasEntityObj.entity_name"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">类型</td>
	  				<td align="left">&nbsp;
	  					Oracle DB
					</td>
	  			</tr>
				<tr>
	  				<td class="til" align="left">服务器地址</td>
	  				<td align="left">&nbsp;
	  					<s:property value="paasEntityObj.host_address"/>
	  				</td>
	  			</tr>
	  			<tr >
					<td class="til" align="left">端口</td>
					<td align="left">&nbsp;
						<s:property value="paasEntityObj.host_port" />
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">版本</td>
					<td align="left">&nbsp;
						<s:property value="map.oracle_db_version" default="-"/>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">所属人</td>
					<td align="left">&nbsp;
						<s:property value="map.oracle_db_owner" default="-"/>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">描述</td>
					<td align="left">&nbsp;
						<s:property value="map.oracle_db_desc" default="-"/>
					</td>
				</tr> 
			</table>
		</div>
  </body>
</html>