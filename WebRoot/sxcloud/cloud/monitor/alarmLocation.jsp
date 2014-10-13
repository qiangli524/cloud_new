<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/view.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css">
.pop-table tr td{text-align: left;}
</style>
  </head>
  <body >
  	<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" >
			<tr>
				<td class="til" width="20%" align="left">
					数据中心名称
				</td>
				<td>
					<s:property value="monitorAlarmLocationObj.oneLevelName"/>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					集群名称
				</td>
				<td>
					<s:property value="monitorAlarmLocationObj.twoLevelName"/>
				</td>
			</tr>

			<tr>
				<td class="til" align="left"> 
					<s:if test='monitorAlarmLocationObj.type=="store"'>
						存储名称
					</s:if>
					<s:else>
						主机名称
					</s:else>
				</td>
				<td>
					<s:property value="monitorAlarmLocationObj.threeLevelName"/>
				</td>
			</tr>
			<s:if test='monitorAlarmLocationObj.type=="vm"'>
				<tr>
					<td class="til" align="left">
						虚拟机名称
					</td>
					<td>
						<s:property value="monitorAlarmLocationObj.fourLevelName"/>
					</td>
				</tr>
			</s:if>
			<s:if test='monitorAlarmLocationObj.type=="store"'>
						
			</s:if>
			<s:else>
				<tr>
					<td class="til" align="left">
						IP地址
					</td>
					<td>
						<s:property value="monitorAlarmLocationObj.ip"/>
					</td>
				</tr>
			</s:else>
			<s:if test='monitorAlarmLocationObj.type=="vm"'>
				<tr>
					<td class="til" align="left">
						业务中心
					</td>
					<td>
						<s:property value="monitorAlarmLocationObj.bcname"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						业务系统
					</td>
					<td>
						<s:property value="monitorAlarmLocationObj.bsname"/>
					</td>
				</tr>
			</s:if>
			</table>
		</div>
  </body>
</html>
