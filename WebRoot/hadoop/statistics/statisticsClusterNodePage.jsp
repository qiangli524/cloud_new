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
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
</head>
<script type="text/javascript">
$(function(){
	var id = $("#id").val();
	var node_type = $("#node_type").val();
	 $("#getHDFS").insertFusionCharts({
         swfUrl: "FusionCharts/Pie2D.swf", 
         dataSource: "hadoopstatistics_showFusionCharts.do?obj.id="+id+"&obj.node_type="+node_type+"&flag=hdfs",
         dataFormat: "jsonurl", 
         width: "66", 
         height: "66", 
         id: "gethdfs"
    });
    $("#getMapReduce").insertFusionCharts({
         swfUrl: "FusionCharts/Pie2D.swf", 
         dataSource: "hadoopstatistics_showFusionCharts.do?obj.id="+id+"&obj.node_type="+node_type+"&flag=mapreduce",
         dataFormat: "jsonurl", 
         width: "66", 
         height: "66", 
         id: "getmapreduce"
    });
});
</script>
<body style="overflow:scroll;">
	<s:form action="" method="post"  id="theForm">
		<s:hidden name="obj.id" id="id"></s:hidden>
		<s:hidden name="obj.node_type" id="node_type"></s:hidden>
		<div style="padding-left:1%">
			<table width="98%" border="0" cellspacing="0" cellpadding="0" >
				<tr>
					<td>
						<s:if test="nodeTypeMap.hadoop==2">
							<div class="mainbox">
								<h2 class="datacenter dc-tt mgt-15"><span class="txt">hadoop集群</span></h2>
								<div class="item" style="height:280px">
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
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.journalnodeCount-statisticsObj.journalnodeNormalCount"/></font></span>
						         			</li>
						     			</ul> 
						     			<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width: 110px;">nodeManager</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nodeManagerCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nodeManagerNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nodeManagerCount-statisticsObj.nodeManagerNormalCount"/></font></span>
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
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.reduceManagerCount-statisticsObj.reduceManagerNormalCount"/></font></span>
						         			</li>
						     			</ul> 
									</div>
								</div>	
								<div class="item">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">HDFS文件系统</font></div>
									</div>
									<div class="right" style="margin-left:10px;margin-top: 10px;">
										<table>
						  					<tr>
						  						<td width="50%;">
						  							<div>总容量：<span class="value"><fmt:formatNumber value="${(statisticsObj.totalCapacity)}" pattern="#,###.##" type="number"/></span> GB</div>
													<div>使用量：<span class="value"><fmt:formatNumber value="${(statisticsObj.totalUsedCapacity)}" pattern="#,###.##" type="number"/></span> GB</div>
													<div>使用率：<span class="value"><fmt:formatNumber value="${(statisticsObj.totalUsage*100)}" pattern="#,###.##" type="number"/></span> %</div>
						  						</td>
												<s:if test="statisticsObj.totalCapacity!=0">
							  						<td>
							  							<div id="getHDFS"></div>
							  						</td>
							  						<td>
														<div style="margin-left: 15px;"></div>
													</td>
							  						<td width="60px;">
														<div style="margin-top: 50px;"><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/blue.jpg" /> 分配率</span><div>
							  						</td>
							  						<td>
							  							<div style="margin-top: 50px;"><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/red.jpg" /> 未分配率</span></div>
							  						</td>
						  						</s:if>
						  					</tr>
						  				</table>
									</div>
								</div>	
								<div class="item" style="margin-top: 20px;">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">MapReduce</font></div>
									</div>
									<div class="right" style="margin-left:10px;margin-top: 10px;">
										<table>
							  				<tr>
								  				<td width="50%;">
									  				<div>总量：&nbsp;&nbsp;&nbsp;<span class="value"><fmt:formatNumber value="${(statisticsObj.totalMB)}" pattern="#,###.##" type="number"/></span> MB</div>
													<div>已分配：<span class="value"><fmt:formatNumber value="${(statisticsObj.allocatedMB)}" pattern="#,###.##" type="number"/></span> MB</div>
													<div>分配率：<span class="value"><fmt:formatNumber value="${(statisticsObj.allocatedAge*100)}" pattern="#,###.##" type="number"/></span> %</div>
								  				</td>
												<s:if test="statisticsObj.totalMB!=0">
									  				<td>
							  							<div id="getMapReduce"></div>
							  						</td>
							  						<td>
														<div style="margin-left: 15px;"></div>
													</td>
							  						<td width="60px;">
														<div style="margin-top: 50px;"><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/blue.jpg" /> 分配率</span><div>
							  						</td>
							  						<td>
							  							<div style="margin-top: 50px;"><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/red.jpg" /> 未分配率</span></div>
							  						</td>
						  						</s:if>
							  				</tr>
						  				</table>
									</div>
								</div>
							</div>
						</s:if>	
						<s:if test="nodeTypeMap.hbase==3">
							<div class="mainbox" style="height: 250px;">
								<h2 class="datacenter dc-tt mgt-15"><span class="txt">hbase集群</span></h2>
								<div class="item">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">重要服务</font></div>
									</div>
									<div class="right">
										<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width: 110px;">hmaster</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hmasterCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hmasterNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hmasterCount - statisticsObj.hmasterNormalCount"/></font></span>
						         			</li>
						     			</ul>  
										<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width: 110px;">regionserver</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.regionServerCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.regionServerNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.regionServerCount - statisticsObj.regionServerNormalCount"/></font></span>
						         			</li>
						     			</ul>  	
						     			<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width: 110px;">thirftserver</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hbaseThirftServerCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hbaseThirftServerNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hbaseThirftServerCount-statisticsObj.hbaseThirftServerNormalCount"/></font></span>
						         			</li>
						     			</ul> 
									</div>
								</div>
								<div class="item">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">blockCache</font></div>
									</div>
									<div class="right" style="margin-left:10px;margin-top: 10px;">
										<table>
											<tr>
												<td>
													<div style="width: 150px;">已使用大小：<span class="value"><fmt:formatNumber value="${(statisticsObj.blockCacheSize)}" pattern="#,###.##" type="number"/></span> MB</div>
												</td>
												<td>
													<div style="margin-left: 60px;">剩余大小：&nbsp;&nbsp;&nbsp;<span class="value"><fmt:formatNumber value="${(statisticsObj.blockCacheFree)}" pattern="#,###.##" type="number"/></span> MB</div>
												</td>
											</tr>
											<tr>
												<td>
													<div>已使用个数：<span class="value"><s:property value="statisticsObj.blockCacheCount"/></span> 个</div>
												</td>
												<td>
													<div style="margin-left: 60px;">命中次数：&nbsp;&nbsp;&nbsp;<span class="value"><s:property value="statisticsObj.blockCacheHitCount"/></span> 次</div>
												</td>
											</tr>
											<tr>
												<td>
													<div>最大命中率：<span class="value"><fmt:formatNumber value="${(statisticsObj.blockCacheHitRatioMax)}" pattern="#,###.##" type="number"/></span> %</div>
												</td>
												<td>
													<div style="margin-left: 60px;">最小命中率：<span class="value"><fmt:formatNumber value="${(statisticsObj.blockCacheHitRatioMin)}" pattern="#,###.##" type="number"/></span> %</div>
												</td>
											</tr>
										</table>
									</div>
								</div>	
							</div>
						</s:if>
						<s:if test="nodeTypeMap.hive==4">
							<div class="mainbox" style="height: 150px;">
								<h2 class="datacenter dc-tt mgt-15"><span class="txt">hive集群</span></h2>
								<div class="item">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">重要服务</font></div>
									</div>
									<div class="right">
										<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width:115px;">hive</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hiveThirftServerCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hiveThirftServerNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hiveThirftServerCount - statisticsObj.hiveThirftServerNormalCount"/></font></span>
						         			</li>
						     			</ul> 
					     			</div>
								</div>
							</div>
						</s:if>
						<s:if test="nodeTypeMap.zookeeper==5">
							<div class="mainbox" style="height: 150px;">
								<h2 class="datacenter dc-tt mgt-15"><span class="txt">zookeeper集群</span></h2>
								<div class="item">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">重要服务</font></div>
									</div>
									<div class="right">
										<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width:115px;">zookeeper</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.zookeeperServerCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.zookeeperServerNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.zookeeperServerCount - statisticsObj.zookeeperServerNormalCount"/></font></span>
						         			</li>
					     				</ul>  
					     			</div>
								</div>
							</div>
						</s:if>
						<s:if test="nodeTypeMap.impala==6">
							<div class="mainbox" style="height: 150px;">
								<h2 class="datacenter dc-tt mgt-15"><span class="txt">impala集群</span></h2>
								<div class="item">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">重要服务</font></div>
									</div>
									<div class="right">
									<ul class="dc-list">
										<li style="width:180px;">
					            			<i class="zhuji"></i><span class="txt" style="width:115px;">impala</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.impalaServerCount"/></font></span>
					            		</li>		
					        			<li style="width:95px;">
					            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.impalaServerNormalCount"/></span></font></span>
					        			</li>
					        			<li style="width:95px;">
					           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.impalaServerCount - statisticsObj.impalaServerNormalCount"/></font></span>
					         			</li>
					     				</ul>  
					     			</div>
								</div>
							</div>
						</s:if>
						<s:if test="nodeTypeMap.storm==7">
							<div class="mainbox" style="height: 150px;">
								<h2 class="datacenter dc-tt mgt-15"><span class="txt">storm集群</span></h2>
								<div class="item">
									<div class="top">
										<div class="ml5"><font color="orange" size="3">重要服务</font></div>
									</div>
									<div class="right">
									<ul class="dc-list">
										<li style="width:180px;">
					            			<i class="zhuji"></i><span class="txt" style="width:115px;">storm</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nameNodeCount"/></font></span>
					            		</li>		
					        			<li style="width:95px;">
					            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nameNodeNormalCount"/></span></font></span>
					        			</li>
					        			<li style="width:95px;">
					           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.nameNodeCount - statisticsObj.nameNodeNormalCount"/></font></span>
					         			</li>
					     				</ul>  
					     			</div>
								</div>
							</div>
						</s:if>
					</td>
				</tr>				
			</table>
		</div>	
	</s:form>
</body>