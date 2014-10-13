<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<%@ taglib prefix="fn"         uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c"          uri="http://java.sun.com/jsp/jstl/core"%>
<html:html locale="true">
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<title></title>
</style>
<script type="text/javaScript">
	var api = frameElement.api;
	var w = api.opener;
	function changeCluster(){
		$("#objForm").submit();
	}
	function viewZookeeper(clusterId,server){
			var url = 'url:taoKeeperMonitor_reportPAGE.do?clusterId='+clusterId+'&server='+server;
			$.dialog({
				id:'selectEntiy',
	  			title:'zookeeper集群性能',
	  			width: '800px',
	  			height: '550px',
	  		    lock:true,
	  			content:url 
			});
		
	}
</script>
</head>
<body>
 <div class="scrollbody">

<div class="box on">

<s:form action="taoKeeperMonitor_showZooKeeperStatusPAGE.do" method="post" id="objForm">	

<div class="pd-20 bgcolor-1">
        	<h2 class="utt-1">ZooKeepr集群状态趋势图</h2>  
            <div class="bord-1 pd-20" >
	            <label class="vm">集群:&nbsp;&nbsp;</label>
	            <label class="vm">
					<select class="select-1 vm" id="clusterSelector" onchange="javascript:changeCluster();" name="clusterId" style="width: 15%;">
						<c:forEach var="zc" items="${allZookeeperCluster}">
							<c:choose>
					     			<c:when test="${ zc.clusterId eq zooKeeperCluster.clusterId }"><option value="${zc.clusterId}"  selected>${zc.clusterName}</option></c:when>
									<c:otherwise><option value="${zc.clusterId}">${zc.clusterName}</option></c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</label>
	            <span class="ubtn-1 mgl-20"><input type="button" id="search" value="刷新" onclick="javascript:changeCluster()"/></span>
				<div style="margin: 10px;"></div>
				
				
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			<thead>
			  <tr>
				<td><b>节点IP</b></td>
				<td><b>Role</b></td>
				<td><b>连接数</b></td>
				<td><b>Watch数</b></td>
				<td><b>Watched /Total Path</b></td>
				<td><b>数据量 Sent/Received</b></td>
				<td><b>节点自检状态</b></td>
				<td><b>查看趋势</b></td>
				<td><b>更新时间</b></td>
			</tr>
			  </thead>
			  <tbody>
			<c:forEach var="nodesInfo" items="${taokeeperNodesInfoList}"> 
				<tr>
					<td>${ nodesInfo.server }</td>
					<td>${ nodesInfo.role }</td>
					<td>${ nodesInfo.connCount }</td>
					<td>${ nodesInfo.watchCount }</td>
					<td>${ nodesInfo.watchedPathCount }/${ nodesInfo.totalPathCount }</td>
					<td>${ nodesInfo.sentData }/${ nodesInfo.receivedData }</td>
					
					<c:choose>
			     			<c:when test="${ nodesInfo.checkStatus eq 'OK' }"><td style="background-color: green;" >OK</td></c:when>
			     			<c:when test="${ nodesInfo.checkStatus eq 'Checking' }"><td style="background-color: yellow;" >Checking</td></c:when>
			     			<c:when test="${ nodesInfo.checkStatus eq 'Initializing' }"><td style="background-color: #D1F3D6;" >Initializing</td></c:when>
							<c:otherwise><td style="background-color: red;" >ERROR</td></c:otherwise>
					</c:choose>
				<td>&nbsp;&nbsp;
				<a href="#"  onclick="viewZookeeper('${nodesInfo.clusterId}','${ nodesInfo.server }')">查看</a></td>		
				<td>
					<fmt:formatDate value="${ nodesInfo.lastUpdateDate }" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<br/><br/>
		<div style="float:left;text-align: left;">
			<b>提示</b>:<br><br>
			 1. 节点自检 是指对集群中每个IP所在ZK节点上的PATH:  <b>/YINSHI.MONITOR.ALIVE.CHECK</b> 定期进行三次如下流程 : <br/>
			<b>节点连接</b> - <b>数据发布</b> - <b>修改通知</b> - <b>获取数据</b> - <b>数据对比</b>, 三次流程均成功视为该节点处于正常状态。<br><br>
			 2. 角色分类：<b>L</b>: Leader, <b>F</b>: Follower, <b>O</b>: Observer, <b>S</b>: Standalone
			<br><br>
		</div>
		</div>
</div>

</s:form>
<br />
	</div>
</div>
</html:html>	