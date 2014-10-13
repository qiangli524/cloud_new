<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/hadoop/statistics/css/statistics.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
</head>
<body>
	<s:form action="" method="post"  id="theForm">
	<div style="padding-left:1%">
		<table width="98%" border="0" cellspacing="0" cellpadding="0" >
			<tr>
				<td>
					<div class="mainbox">
     					<h2 class="datacenter dc-tt mgt-15"><span class="txt">mapreduce</span></h2>
     					<div class="item" style="height:130px">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">重要服务</font></div>
							</div>
							<div class="right">
								<ul class="dc-list">
									<li style="width: 180px;">
            							<i class="zhuji"></i><span class="txt" style="width: 110px;">nodeManager</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nodeManagerCount"/></font></span>
            						</li>		
        							<li style="width: 95px;">
            							<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nodeManagerNormalCount"/></span></font></span>
        							</li>
        							<li style="width: 95px;">
           								<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nodeManagerCount - statisticsObj.nodeManagerNormalCount"/></font></span>
         							</li>
     							</ul>
     							<ul class="dc-list">
									<li style="width: 180px;">
            							<i class="zhuji"></i><span class="txt" style="width: 110px;">resourceManager</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.reduceManagerCount"/></font></span>
            						</li>		
        							<li style="width: 95px;">
            							<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.reduceManagerNormalCount"/></span></font></span>
        							</li>
        							<li style="width: 95px;">
           								<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.reduceManagerCount - statisticsObj.reduceManagerNormalCount"/></font></span>
         							</li>
     							</ul>
     						</div>	  
     					</div>	
     					<div class="item" style="height:130px">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">分配给yarn的内存</font></div>
								<div class="right" style="margin-left:10px;margin-top: 10px;">
									<div><div style="width:100px; float: left;">总量：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.totalMB)}" pattern="#,###.##" type="number"/></span> MB</div>
									<div><div style="width:100px; float: left;">分配：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.allocatedMB)}" pattern="#,###.##" type="number"/></span> MB</div>
									<div><div style="width:100px; float: left;">可用：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.availabledMB)}" pattern="#,###.##" type="number"/></span> MB</div>
									<div><div style="width:100px; float: left;">预留：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.reservedMB)}" pattern="#,###.##" type="number"/></span> MB</div>
								</div>								
							</div>							
						</div>	
     					<div class="item">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">mr作业</font></div>
								<div class="right" style="margin-left:10px;margin-top: 10px;">
									<div><div style="width:100px; float: left;">提交：</div><span class="value"><s:property value="statisticsObj.jobsSubmittedCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">准备提交：</div><span class="value"><s:property value="statisticsObj.jobsPreparingCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">完成：</div><span class="value"><s:property value="statisticsObj.jobsCompletedCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">失败：</div><span class="value"><s:property value="statisticsObj.jobsFailedCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">运行：</div><span class="value"><s:property value="statisticsObj.jobsRunningCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">kill：</div><span class="value"><s:property value="statisticsObj.jobsKilledCount"/></span> 个</div>
									
								</div>								
							</div>							
						</div>	
						<div class="item">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">maps任务</font></div>
								<div class="right" style="margin-left:10px;margin-top: 10px;">
									<div><div style="width:100px; float: left;">启动：</div><span class="value"><s:property value="statisticsObj.mapsLaunchedCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">完成：</div><span class="value"><s:property value="statisticsObj.mapsCompletedCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">失败：</div><span class="value"><s:property value="statisticsObj.mapsFailedCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">kill：</div><span class="value"><s:property value="statisticsObj.mapsKilledCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">运行：</div><span class="value"><s:property value="statisticsObj.mapsRunningCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">等待：</div><span class="value"><s:property value="statisticsObj.mapsWaitingCount"/></span> 个</div>
								</div>								
							</div>							
						</div>	
						
     					<div class="item">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">reduce任务</font></div>
								<div class="right" style="margin-left:10px;margin-top: 10px;">
									<div><div style="width:100px; float: left;">启动：</div><span class="value"><s:property value="statisticsObj.reducesLaunchedCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">完成：</div><span class="value"><s:property value="statisticsObj.reducesCompletedCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">失败：</div><span class="value"><s:property value="statisticsObj.reducesFailedCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">kill：</div><span class="value"><s:property value="statisticsObj.reducesKilledCount"/></span> 个</div>
									<div><div style="width:100px; float: left;">运行：</div><span class="value"><s:property value="statisticsObj.reducesRunningCount"/></span> 个</div>
								</div>								
							</div>							
						</div>	
						<div class="item">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">nodemanager</font></div>
								<div class="right" style="margin-left:10px;margin-top: 10px;">
									<div><div style="width:100px; float: left;">活动的：</div><span class="value"><s:property value="statisticsObj.numActiveNMs"/></span> 个</div>
									<div><div style="width:100px; float: left;">退役的：</div><span class="value"><s:property value="statisticsObj.numDecommissionedNMs"/></span> 个</div>
									<div><div style="width:100px; float: left;">丢失的：</div><span class="value"><s:property value="statisticsObj.numLostNMs"/></span> 个</div>
									<div><div style="width:100px; float: left;">不健康的：</div><span class="value"><s:property value="statisticsObj.numUnhealthyNMs"/></span> 个</div>
									<div><div style="width:100px; float: left;">重启过的：</div><span class="value"><s:property value="statisticsObj.numRebootedNMs"/></span> 个</div>
								</div>								
							</div>							
						</div>	     					
					</div>	
				</td>
			</tr>
		</table>	
	</div>
	</s:form>
</body>