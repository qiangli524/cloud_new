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
<div style="padding: 10px;">
<h1>ZooKeeper 报警设置</h1>

		<select id="clusterSelector" onchange="javascript:location.href=this.value;" >
			<c:forEach var="zooKeeperCluster" items="${zooKeeperClusterMap}">
				<c:choose>
		     			<c:when test="${ zooKeeperCluster.key eq clusterId }"><option value="alarmSettings.do?method=alarmSettingsPAGE&clusterId=${zooKeeperCluster.key}"  selected>${zooKeeperCluster.value.clusterName}</option></c:when>
						<c:otherwise><option value="taoKeeperMonitor_alarmSettingsPAGE&clusterId=${zooKeeperCluster.key}">${zooKeeperCluster.value.clusterName}</option></c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<span>${description}</span><br/><br/>
	<div class="table-ct">

<form name="updateAlarmSettings"  id="updateAlarmSettings" action="taoKeeperMonitor_updateAlarmSettingsHandle.do"  method="post" >
	<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
	
	
		<tr style="background-color:#D3D1D1; text-align:center;">
		 	<td><b>报警项目</b></td>
		 	<td><b>设置参数(多个参数之间请用英文半角状态 , 相隔)</b></td>
		 </tr>
		 <tr>
		 	<td valign="middle">ZooKeeper节点生命检测最大延时</td>
		 	<td valign="middle" ><input type="text" name="maxDelayOfCheck" id="maxDelayOfCheck"  value="${alarmSettings.maxDelayOfCheck}"/>s</td>
		 </tr>
		 <tr >
		 	<td valign="middle">Cpu 峰值报警</td>
		 	<td valign="middle" ><input type="text" name="maxCpuUsage"  id="maxCpuUsage"  value="${alarmSettings.maxCpuUsage}"/>%</td>
		 </tr>
		 <tr >
		 	<td valign="middle">Memory 峰值报警</td>
		 	<td valign="middle" ><input type="text" name="maxMemoryUsage" id="maxMemoryUsage" value="${alarmSettings.maxMemoryUsage}"/>%</td>
		 </tr>
		 <tr >
		 	<td valign="middle">Load 峰值报警</td>
		 	<td valign="middle" ><input type="text" name="maxLoad" id="maxLoad" value="${alarmSettings.maxLoad}"/></td>
		 </tr>
		 <tr >
		 	<td valign="middle">Data目录</td>
		 	<td valign="middle" ><input type="text" name="dataDir" id="maxLoad" value="${alarmSettings.dataDir}"/></td>
		 </tr>
		 <tr >
		 	<td valign="middle">Log目录</td>
		 	<td valign="middle" ><input type="text" name="dataLogDir" id="maxLoad" value="${alarmSettings.dataLogDir}"/></td>
		 </tr>
		 <tr >
		 	<td valign="middle">磁盘使用率 峰值报警</td>
		 	<td valign="middle" ><input type="text" name="maxDiskUsage" id="maxLoad" value="${alarmSettings.maxDiskUsage}"/>%</td>
		 </tr>
		 <tr >
		 	<td valign="middle">单机 连接数 峰值报警</td>
		 	<td valign="middle" ><input type="text" name="maxConnectionPerIp" id="maxConnectionPerIp" value="${alarmSettings.maxConnectionPerIp}"/></td>
		 </tr>
		 <tr >
		 	<td valign="middle">单机 Watcher数 峰值报警</td>
		 	<td valign="middle" ><input type="text" name="maxWatchPerIp" id="maxWatchPerIp" value="${alarmSettings.maxWatchPerIp}"/></td>
		 </tr>
		 <tr >
		 	<td valign="middle">Node Check Rule</td>
		 	<td valign="middle" ><input type="text" name="nodePathCheckRule" id="nodePathCheckRule" value="${alarmSettings.nodePathCheckRule}" size="50"/>
		 	<br>
		 	<font color="blue">样例</font>：<b>|只能出现|^|不能出现| </b>
		 	<br>
		 	<font color="blue">例子</font>：
		 	 <b>|/</b>:nileader,yinshi;<b>/nileader</b>:test<b>|</b>^<b>|/</b>:test<b>|</b><br>
	 		 <font color="blue">表示</font>：<br>
	  		"<b>/</b>"这个path下，只能够出现nileader和yinshi这两个节点，"<b>/nileader</b>" 这个path下，只能够出现test节点, "<b>/</b>" 这个path下，不能够出现test节点<br>
		 	</td>
		 </tr>


	
	
		<tr style="background-color:#D3D1D1; text-align:center;">
		 	<td colspan="2"   ><b>报警对象</b></td>
		 </tr>
		 <tr>
		 	<td>旺旺</td>
		 	<td><input type="text" name="wangwangList" id="wangwangList"  value="${alarmSettings.wangwangList}" size="50"/></td>
		 </tr>
		 <tr>
		 	<td>手机(线上使用)</td>
		 	<td><input type="text" name="phoneList" id="phoneList"  value="${alarmSettings.phoneList}" size="50" /></td>
		 </tr>
	
		 <tr >
		 	<td valign="middle"></td>
		 	<td align="right" ><input type="submit"  value="Update"  size="90"/> <font color="red">${handleMessage}</font> </td>
		 </tr>
	</table>
	<input type="hidden"  value="${clusterId}"  name="clusterId" id="clusterId" />
</form>


</div>
</div>
</html:html>	


