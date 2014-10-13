<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<title></title>
</style>
<style type="text/css">
		
</style>
<script type="text/javaScript">
	
</script>
</head>
<body class="scrollbody">
<s:form action="zookeeperMonitor_monitorTree.do" method="post" cssClass="obj" id="obj" name="obj">
<s:hidden name="currentTreeObj.fullPath" id="fullPath"></s:hidden>
<div style="padding: 10px;">

	<h1>ZooKeeper 集群信息<font size="2">  <a href="taoKeeperMonitor_zooKeeperRegisterPAGE.do">加入监控</a></font></h1>

	<select id="clusterSelector" onchange="javascript:location.href=this.value;" >
		<c:forEach var="zooKeeperCluster" items="${zooKeeperClusterMap}">
			<c:choose>
	     			<c:when test="${ zooKeeperCluster.key eq clusterId }"><option value="zooKeeper.do?method=zooKeeperSettingsPAGE&clusterId=${zooKeeperCluster.key}"  selected>${zooKeeperCluster.value.clusterName}</option></c:when>
					<c:otherwise><option value="zooKeeper.do?method=zooKeeperSettingsPAGE&clusterId=${zooKeeperCluster.key}">${zooKeeperCluster.value.clusterName}</option></c:otherwise>
			</c:choose>
		</c:forEach>
	</select>

	<div class="table-ct">
	<form name="updateZooKeeperSettings"  id="updateZooKeeperSettings"  action="taoKeeperMonitor_updateZooKeeperSettingsHandle.do"  method="post">
	<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
		 <tr style="background-color:#D3D1D1; text-align:center;">
		 	<td><b>配置项</b></td>
		 	<td><b>设置参数</b></td>
		 </tr>
		 <tr>
		 	<td valign="middle">ZooKeeper集群名称</td>
		 	<td valign="middle" ><input type="text" name="clusterName" id="clusterName"  value="${zooKeeperSettingsPAGEResult.zooKeeperCluster.clusterName}" size="100"/></td>
		 </tr>
		 <tr >
		 	<td valign="middle">机器列表</td>
		 	<td valign="middle" ><input type="text" name="serverListString"  id="serverListString"  value="${zooKeeperSettingsPAGEResult.zooKeeperClusterServerList}" size="100"/></td>
		 </tr>
		 <tr >
		 	<td valign="middle">描述</td>
		 	<td valign="middle" ><input type="text" name="description" id="description" value="${zooKeeperSettingsPAGEResult.zooKeeperCluster.description}" size="100"/></td>
		 </tr>
		 <tr >
		 	<td valign="middle"></td>
		 	<td align="right" ><input type="submit"  value="Update"  size="90"/> <font color="red">${zooKeeperSettingsPAGEResult.handleMessage}</font> </td>
		 </tr>
	</table>
	<input type="hidden"  value="${zooKeeperSettingsPAGEResult.clusterId}"  name="clusterId" id="clusterId" />
</form>
	
	</div>
</div>
</s:form>
</html:html>	