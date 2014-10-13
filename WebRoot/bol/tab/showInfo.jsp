<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework_bol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<head>
<style type="text/css">
	span.orange-16 {
		font-size: 15px;
		color:#005AB5;
		font-family:Verdana, Geneva, sans-serif;
	}
	
	.font-gray {
		color: #999;
		font-size: 14px;
		padding-left: 8px;
	}
	.mgt-10 {
		margin-top: 10px;
	}
	.font-14 td {
		font-size: 14px;
		line-height: 32px;
	}
	.clr {
		clear: both;
		height: 0;
		font-size: 0;
	}
</style>
<title></title>
<script type="text/javascript">
	$(function(){
		var type = $("#type").val();
		if (type ==1) {
			document.getElementById("hostcount").style.display="none";
		}
	});
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
	<s:hidden name="type" id="type"></s:hidden>
		<table width="98%" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10" style="height: 40px;" bgcolor="#FFE6FF">
			<tr id="hostcount">
				<td width="3%">
					<img src="<%=request.getContextPath()%>/sxcloud/images/host.gif" width="16" height="16" />
				</td>
				<td width="22%">主机个数：
				</td>
				<td align="right" width="15%">	
					<span class="orange-16"><s:property value="hostCount"/>个</span>
				</td>
			</tr>
			<s:if test="processCount != 0">
				<tr>
					<td width="3%">
						<img src="<%=request.getContextPath()%>/sxcloud/images/virtual/process.png" width="16" height="16" />
					</td>
					<td width="22%">
						进程总数:
					</td>
					<td align="right" width="15%">	
						<span class="orange-16"><s:property value="processCount"/>个</span>
					</td>
					<td width="20%" align="center">
						<span class="font-gray">已使用：</span>
					</td>
					<td width="15%">
						<span class="font-gray"><s:property value="processUsedCount"/>个</span>
					</td>
					<td width="19%" align="center">
						<span class="font-gray">使用率：</span>
					</td>
					<td>	
						<span class="font-gray"><s:property value="@java.lang.Math@round(processUsedCount*100.0/processCount)"/>%</span>
					</td>
				</tr>
			</s:if>
			<s:if test="fileCount != 0">
				<tr>
					<td width="3%">
						<img src="<%=request.getContextPath()%>/sxcloud/images/alarm/greenfoldero.gif" width="16" height="16" />
					</td>
					<td width="22%">
						文件汇总（指定目录）：
					</td>
					<td align="right" width="15%">	
						<span class="orange-16"><s:property value="fileCount"/>个</span>
					</td>
					<td width="20%" align="center">
						<span class="font-gray">已使用：</span>
					</td>
					<td width="15%">
						<span class="font-gray"><s:property value="fileUsedCount"/>个</span>
					</td>
					<td width="19%" align="center">
						<span class="font-gray">使用率：</span>
					</td>
					<td>	
						<span class="font-gray"><s:property value="@java.lang.Math@round(fileUsedCount*100.0/fileCount)"/>%</span>
					</td>
				</tr>
			</s:if>
			<s:if test="fileDirCount != 0">
				<tr>
					<td width="3%">
						<img src="<%=request.getContextPath()%>/sxcloud/images/alarm/tree_off.gif" width="16" height="16" />
					</td>
					<td width="22%">
						文件句柄数（指定目录）：
					</td>
					<td align="right" width="15%">	
						<span class="orange-16"><s:property value="fileDirCount"/>个</span>
					</td>
					<td width="20%" align="center">
						<span class="font-gray">已使用：</span>
					</td>
					<td width="15%">
						<span class="font-gray"><s:property value="fileDirUsedCount"/>个</span>
					</td>
					<td width="19%" align="center">
						<span class="font-gray">使用率：</span>
					</td>
					<td>	
						<span class="font-gray"><s:property value="@java.lang.Math@round(fileDirUsedCount*100.0/fileDirCount)"/>%</span>
					</td>
				</tr>
			</s:if>
			<s:if test="fileProcessCount != 0">
				<tr>
					<td width="3%">
						<img src="<%=request.getContextPath()%>/sxcloud/images/alarm/tree_on.gif" width="16" height="16" />
					</td>
					<td width="22%">
						文件句柄数（指定进程）：
					</td>
					<td align="right" width="15%">	
						<span class="orange-16"><s:property value="fileProcessCount"/>个</span>
					</td>
					<td width="20%" align="center">
						<span class="font-gray">已使用：</span>
					</td>
					<td width="15%">
						<span class="font-gray"><s:property value="fileProcessUsedCount"/>个</span>
					</td>
					<td width="19%" align="center">
						<span class="font-gray">使用率：</span>
					</td>
					<td>	
						<span class="font-gray"><s:property value="@java.lang.Math@round(fileProcessUsedCount*100.0/fileProcessCount)"/>%</span>
					</td>
				</tr>
			</s:if>
		</table>
		<div class="clr" style=" height: 10px;"></div>
		<table width="98%" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10" style="height: 40px;" bgcolor="#FFE6FF ">
			<s:if test="cpuCount != 0">
				<tr>
					<td width="3%">
						<img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-cpu.gif" width="16" height="16" />
					</td>
					<td width="22%">
						CPU总数：
					</td>
					<td align="right" width="15%">	
						<span class="orange-16"><s:property value="cpuCount"/>个</span>
					</td>
					<td width="20%" align="center">
						<span class="font-gray">已使用：</span>
					</td>
					<td width="15%">
						<span class="font-gray"><s:property value="cpuUsedCount"/>个</span>
					</td>
					<td width="19%" align="center">
						<span class="font-gray">使用率：</span>
					</td>
					<td>	
						<span class="font-gray"><s:property value="@java.lang.Math@round(cpuUsedCount*100.0/cpuCount)"/>%</span>
					</td>
				</tr>
			</s:if>
			<s:if test="memoryCount != 0">
				<tr>
					<td width="3%">
						<img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-nc.gif" width="16" height="16" />
					</td>
					<td width="22%">
						内存汇总：
					</td>
					<td align="right" width="15%">	
						<span class="orange-16"><s:property value="memoryCount"/>MB</span>
					</td>
					<td width="20%" align="center">
						<span class="font-gray">已使用：</span>
					</td>
					<td width="15%">
						<span class="font-gray"><s:property value="memoryUsedCount"/>MB</span>
					</td>
					<td width="19%" align="center">
						<span class="font-gray">使用率：</span>
					</td>
					<td>	
						<span class="font-gray"><s:property value="@java.lang.Math@round(memoryUsedCount*100.0/memoryCount)"/>%</span>
					</td>
				</tr>
			</s:if>
			<s:if test="storeCount != 0">
				<tr>
					<td width="3%">
						<img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-yp.gif" width="16" height="16" />
					</td>
					<td width="22%">
						存储汇总：
					</td>
					<td align="right" width="15%">	
						<span class="orange-16"><s:property value="storeCount"/>MB</span>
					</td>
					<td width="20%" align="center">
						<span class="font-gray">已使用：</span>
					</td>
					<td width="15%">
						<span class="font-gray"><s:property value="storeUsedCount"/>MB</span>
					</td>
					<td width="19%" align="center">
						<span class="font-gray">使用率：</span>
					</td>
					<td>	
						<span class="font-gray"><s:property value="@java.lang.Math@round(storeUsedCount*100.0/storeCount)"/>%</span>
					</td>
				</tr>
			</s:if>
		</table>
		<div class="clr" style=" height: 10px;"></div>
		<table width="98%" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10" style="height: 40px;" bgcolor="#FFE6FF ">
			<s:if test="messageQueneCount != 0">
				<tr>
					<td width="3%">
						<img src="<%=request.getContextPath()%>/sxcloud/images/virtual/task.png" width="16" height="16" />
					</td>
					<td width="22%">
						消息队列总数：
					</td>
					<td align="right" width="15%">	
						<span class="orange-16"><s:property value="messageQueneCount"/>个</span>
					</td>
					<td width="20%" align="center">
						<span class="font-gray">已使用：</span>
					</td>
					<td width="15%">
						<span class="font-gray"><s:property value="messageQueneUsedCount"/>个</span>
					</td>
					<td width="19%" align="center">
						<span class="font-gray">使用率：</span>
					</td>
					<td>	
						<span class="font-gray"><s:property value="@java.lang.Math@round(messageQueneUsedCount*100.0/messageQueneCount)"/>%</span>
					</td>
				</tr>
			</s:if>
			<s:if test="signalCount != 0">
				<tr>
					<td width="3%">
						<img src="<%=request.getContextPath()%>/sxcloud/images/virtual/task.png" width="16" height="16" />
					</td>
					<td width="22%">
						信号总量：
					</td>
					<td align="right" width="15%">	
						<span class="orange-16"><s:property value="signalCount"/>个</span>
					</td>
					<td width="20%" align="center">
						<span class="font-gray">已使用：</span>
					</td>
					<td width="15%">
						<span class="font-gray"><s:property value="signalUsedCount"/>个</span>
					</td>
					<td width="19%" align="center">
						<span class="font-gray">使用率：</span>
					</td>
					<td>	
						<span class="font-gray"><s:property value="@java.lang.Math@round(signalUsedCount*100.0/signalCount)"/>%</span>
					</td>
				</tr>
			</s:if>
			<s:if test="dbConnCount != 0">
				<tr>
					<td width="3%">
						<img src="<%=request.getContextPath()%>/sxcloud/images/virtual/example.png" width="16" height="16" />
					</td>
					<td width="22%">
						数据库连接数：
					</td>
					<td align="right" width="15%">	
						<span class="orange-16"><s:property value="dbConnCount"/>个</span>
					</td>
					<td width="20%" align="center">
						<span class="font-gray">已使用：</span>
					</td>
					<td width="15%">
						<span class="font-gray"><s:property value="dbConnUsedCount"/>个</span>
					</td>
					<td width="19%" align="center">
						<span class="font-gray">使用率：</span>
					</td>
					<td>	
						<span class="font-gray"><s:property value="@java.lang.Math@round(dbConnUsedCount*100.0/dbConnCount)"/>%</span>
					</td>
				</tr>
			</s:if>
			<s:if test="netConnCount != 0">
				<tr>
					<td width="3%">
						<img src="<%=request.getContextPath()%>/sxcloud/images/virtual/net.gif" width="16" height="16" />
					</td>
					<td width="22%">
						网络连接数 ：
					</td>
					<td align="right" width="15%">	
						<span class="orange-16"><s:property value="netConnCount"/>个</span>
					</td>
					<td width="20%" align="center">
						<span class="font-gray">已使用：</span>
					</td>
					<td width="15%">
						<span class="font-gray"><s:property value="netConnUsedCount"/>个</span>
					</td>
					<td width="19%" align="center">
						<span class="font-gray">使用率：</span>
					</td>
					<td>	
						<span class="font-gray"><s:property value="@java.lang.Math@round(netConnUsedCount*100.0/netConnCount)"/>%</span>
					</td>
				</tr>
			</s:if>
		</table>
		
		<s:if test="type == 1">
			<div class="clr" style=" height: 10px;"></div>
		<table width="98%" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10" style="height: 40px;" bgcolor="#FFE6FF ">
			<tr>
				<td width="10%" align="right">
					节点类型 ：
				</td>
				<td align="left" width="12%">	
					<s:if test="hostType == 1">
						BOL Master
					</s:if>
					<s:elseif test="hostType == 2">
						BOL Slave
					</s:elseif>
					<s:else>
						--
					</s:else>		
				</td>
				<td width="10%" align="right">
					节点状态 ：
				</td>
				<td align="left" width="10%">
					<s:if test="nodeStatus == 1">
						<font color="orange">空闲</font>
					</s:if>
					<s:elseif test="nodeStatus == 2">
						<font color="green">空闲</font>
					</s:elseif>
					<s:elseif test="nodeStatus == 3">
						<font color="red">繁忙</font>
					</s:elseif>
					<s:else>
						 无更新数据 
					</s:else>		
				</td>
				<td width="15%" align="right">
					数据更新时间 ：
				</td>
				<td align="left">	
					<s:property value="nodeUpdateTime"/>
				</td>
			</tr>
		</table>
		</s:if>
	</s:form>
</body>
</html:html>