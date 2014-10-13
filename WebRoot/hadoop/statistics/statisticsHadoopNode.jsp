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
							<s:if test="nodeTypeMap.hdfs==8">	
								<h2 class="datacenter dc-tt mgt-15"><span class="txt">HDFS文件系统</span></h2>
								<div class="item" style="height:200px">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">重要服务</font></div>
									</div>
									<div class="right">
										<ul class="dc-list">
											<li style="width:180px;">
		            							<i class="zhuji"></i><span class="txt" style="width: 110px;">namenode</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nameNodeCount"/></font></span>
		            						</li>		
		        							<li style="width:95px;">
		            							<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nameNodeNormalCount"/></span></font></span>
		        							</li>
		        							<li style="width:95px;">
		           								<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nameNodeCount - statisticsObj.nameNodeNormalCount"/></font></span>
		         							</li>
		     							</ul>
		     							<ul class="dc-list">
											<li style="width:180px;">
		            							<i class="zhuji"></i><span class="txt" style="width: 110px;">datanode</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.datanodeCount"/></font></span>
		            						</li>		
		        							<li style="width:95px;">
		            							<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.datanodeNormalCount"/></span></font></span>
		        							</li>
		        							<li style="width:95px;">
		           								<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.datanodeCount - statisticsObj.datanodeNormalCount"/></font></span>
		         							</li>
		     							</ul>
		     							<ul class="dc-list">
											<li style="width:180px;">
		            							<i class="zhuji"></i><span class="txt" style="width: 110px;">journalnode</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.journalnodeCount"/></font></span>
		            						</li>		
		        							<li style="width:95px;">
		            							<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.journalnodeNormalCount"/></span></font></span>
		        							</li>
		        							<li style="width:95px;">
		           								<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.journalnodeCount - statisticsObj.journalnodeNormalCount"/></font></span>
		         							</li>
		     							</ul>
		     						</div>	  
		     					</div>	
		     					<div class="item" style="height:200px">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">文件系统容量</font></div>
										<div class="right" style="margin-left:10px;margin-top: 10px;">
											<div style="margin-top:5px"><div style="width:100px; float: left;">总容量：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.totalCapacity)}" pattern="#,###.##" type="number"/></span> GB</div>
											<div><div style="width:100px; float: left;">使用量：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.totalUsedCapacity)}" pattern="#,###.##" type="number"/></span> GB</div>
											<div><div style="width:100px; float: left;">dfs使用量：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.fileSystemUsedCapacity)}" pattern="#,###.##" type="number"/></span> GB</div>
											<div><div style="width:100px; float: left;">dfs余量：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.fileSystemLeftCapacity)}" pattern="#,###.##" type="number"/></span> GB</div>
											<div><div style="width:100px; float: left;">非dfs使用量：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.nonDfsUsedCapacity)}" pattern="#,###.##" type="number"/></span> GB</div>
										</div>								
									</div>							
								</div>	
								<div class="item">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">block和文件</font></div>
										<div class="right" style="margin-left:10px;margin-top: 10px;">
											<div style="margin-top:5px"><div style="width:100px; float: left;">block个数：</div><span class="value"><s:property value="statisticsObj.blockCount"/></span> 个</div>
											<div><div style="width:100px; float: left;">文件数量：</div><span class="value"><s:property value="statisticsObj.fileCount"/></span> 个</div>				
										</div>								
									</div>							
								</div>	
		     					<div class="item">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">文件系统使用率和剩余率</font></div>
										<div class="right" style="margin-left:10px;margin-top: 10px;">
											<div style="margin-top:5px"><div style="width:100px; float: left;">dfs使用率：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.fileSystemUsage*100)}" pattern="#,###.##" type="number"/></span> %</div>
											<div><div style="width:100px; float: left;">dsf剩余率：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.fileSystemLeftage*100)}" pattern="#,###.##" type="number"/></span> %</div>
											<div><div style="width:100px; float: left;">非dfs使用率：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.nonDfsUsage*100)}" pattern="#,###.##" type="number"/></span> %</div>
										</div>								
									</div>							
								</div>	
							</s:if>	
							<s:if test="nodeTypeMap.mapReduce==9">	
		     					<h2 class="datacenter dc-tt mgt-15"><span class="txt">MapReduce</span></h2>
		     					<div class="item" style="height:150px">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">重要服务</font></div>
									</div>
									<div class="right">
										<ul class="dc-list">
											<li style="width:180px;">
		            							<i class="zhuji"></i><span class="txt" style="width: 110px;">nodeManager</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nodeManagerCount"/></font></span>
		            						</li>		
		        							<li style="width:95px;">
		            							<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nodeManagerNormalCount"/></span></font></span>
		        							</li>
		        							<li style="width:95px;">
		           								<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nodeManagerCount - statisticsObj.nodeManagerNormalCount"/></font></span>
		         							</li>
		     							</ul>
		     							<ul class="dc-list">
											<li style="width:180px;">
		            							<i class="zhuji"></i><span class="txt" style="width: 110px;">resourceManager</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.reduceManagerCount"/></font></span>
		            						</li>		
		        							<li style="width:95px;">
		            							<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.reduceManagerNormalCount"/></span></font></span>
		        							</li>
		        							<li style="width:95px;">
		           								<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.reduceManagerCount - statisticsObj.reduceManagerNormalCount"/></font></span>
		         							</li>
		     							</ul>
		     						</div>	  
		     					</div>	
		     					<div class="item" style="height:150px">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">分配给yarn的内存</font></div>
										<div class="right" style="margin-left:10px;margin-top: 10px;">
											<div style="margin-top:5px"><div style="width:100px; float: left;">总量：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.totalMB)}" pattern="#,###.##" type="number"/></span> MB</div>
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
											<div style="margin-top:5px"><div style="width:100px; float: left;">提交：</div><span class="value"><s:property value="statisticsObj.jobsSubmittedCount"/></span> 个</div>
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
										<div class="ml5"><font color="orange" size="3">reduce任务</font></div>
										<div class="right" style="margin-left:10px;margin-top: 10px;">
											<div style="margin-top:5px"><div style="width:100px; float: left;">启动：</div><span class="value"><s:property value="statisticsObj.reducesLaunchedCount"/></span> 个</div>
											<div><div style="width:100px; float: left;">完成：</div><span class="value"><s:property value="statisticsObj.reducesCompletedCount"/></span> 个</div>
											<div><div style="width:100px; float: left;">失败：</div><span class="value"><s:property value="statisticsObj.reducesFailedCount"/></span> 个</div>
											<div><div style="width:100px; float: left;">kill：</div><span class="value"><s:property value="statisticsObj.reducesKilledCount"/></span> 个</div>
											<div><div style="width:100px; float: left;">运行：</div><span class="value"><s:property value="statisticsObj.reducesRunningCount"/></span> 个</div>
										</div>								
									</div>							
								</div>	
		     				</s:if>
						</div>	
					</td>
				</tr>
			</table>	
		</div>
	</s:form>
</body>