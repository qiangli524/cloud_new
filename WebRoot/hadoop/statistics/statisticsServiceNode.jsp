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
<script type="text/javascript">
	/*$(function(){
		var serviceType = $("#serviceType").val();
		if (serviceType != 1 && serviceType != 2 && serviceType != 4 && serviceType != 6 && serviceType != 7) {
			document.getElementById("firsttr").style.display="none";
		}
	});*/
</script>
<style>
.blue {
background:none;
</style>
</head>

<body>
	<s:form action="" method="post"  id="theForm">
	<s:hidden name="obj.service_type" id="serviceType"></s:hidden>
	<div style="padding-left:1%">
		<table width="98%" border="0" cellspacing="0" cellpadding="0" >
			<tr>
				<td>
					<div class="mainbox">
					<s:if test="obj.service_type==1">
						<h2 class="datacenter dc-tt mgt-15"><span class="txt">主/从</span></h2>
						<div class="item">
							<div>
								<div class="right">
									<table>
										<s:iterator value="resultList" id="theBean" status="st">
												<s:if test="#st.getIndex()%2==0">
													<tr>
												</s:if>
													<td width="200px;">
														<div>
															<s:if test="#theBean.user_defined==1">active</s:if>
															<s:else>standby</s:else>
															<span class="value"><s:property value="#theBean.name"/></span>
														</div>
													</td>
												<s:if test="(#st.getIndex()+1)%2==0">
													</tr>
												</s:if>
										</s:iterator>
									</table>
								</div>
							</div>
						</div>
					</s:if>			
					<s:if test="nodeTypeMap.znode==9">	
						<h2 class="datacenter dc-tt mgt-15"><span class="txt">zookeeper集群</span></h2>
						<div class="item" >
							<div class="top">
								<div class="ml5"><font color="orange" size="3">重要服务</font></div>
							</div>
							<div class="right">
								<ul class="dc-list">
									<li>
            							<i class="zhuji"></i><span class="txt">zookeeper</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.zookeeperServerCount"/></font></span>
            						</li>		
        							<li>
            							<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.zookeeperServerNormalCount"/></span></font></span>
        							</li>
        							<li>
           								<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.zookeeperServerCount - statisticsObj.zookeeperServerNormalCount"/></font></span>
         							</li>
     							</ul>
     						</div>
     					</div>
     				</s:if>		
     				<s:if test="obj.service_type==1">	
						<h2 class="datacenter dc-tt mgt-15"><span class="txt">重要指标</span></h2>
						<div class="item" >
							<div class="right">
								<table>
									<tr>
										<td width="300px;">
											<div>fsimage加载时间：<span class="value"><fmt:formatNumber value="${(statisticsObj.fsImageLoadTime)}" pattern="#,###.##" type="number"/></span> ms</div>
										</td>
										<td>
											<div>blockreport次数：<span class="value"><fmt:formatNumber value="${(statisticsObj.blockReportNumOps)}" pattern="#,###.##" type="number"/></span> 次</div>
										</td>
									</tr>
									<tr>
										<td>
											<div>blockreport时间：<span class="value"><fmt:formatNumber value="${(statisticsObj.blockReportAvgTime)}" pattern="#,###.##" type="number"/></span> ms</div>
										</td>
										<td>
											<div>写入block次数：&nbsp;&nbsp;&nbsp;&nbsp;<span class="value"><fmt:formatNumber value="${(statisticsObj.addBlockOps)}" pattern="#,###.##" type="number"/></span> 次</div>
										</td>
									</tr>
								</table>
     						</div>
     					</div>
     				</s:if>		
     				<s:if test="obj.service_type==2">	
						<h2 class="datacenter dc-tt mgt-15"><span class="txt">重要指标</span></h2>
						<div class="item" >
							
							<div class="right">
								<table>
								<tr>
									<td>
										<div><div style="width: 120px;float: left;">flush平均时间：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.flushNanosAvgTime)}" pattern="#,###.##" type="number"/></span> ms</div>
										<div><div style="width: 120px;float: left;">flush次数：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.flushNanosNumOps)}" pattern="#,###.##" type="number"/></span> 次</div>
										<div><div style="width: 120px;float: left;">汇报block平均时间：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.blockReportsAvgTime)}" pattern="#,###.##" type="number"/></span> ms</div>
										<div><div style="width: 120px;float: left;">汇报block次数：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.blockReportsNumOps)}" pattern="#,###.##" type="number"/></span> 次</div>
									</td>
									<td width="40px;"></td>
									<td>
										<div><div style="width: 120px;float: left;">汇报的平均时间：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.heartbeatsAvgTime)}" pattern="#,###.##" type="number"/></span> ms</div>
										<div><div style="width: 120px;float: left;">汇报的次数：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.heartbeatsNumOps)}" pattern="#,###.##" type="number"/></span> 次</div>
										<div><div style="width: 120px;float: left;">写数据块时间：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.blockWrittenAvgTime)}" pattern="#,###.##" type="number"/></span> ms</div>
										<div><div style="width: 120px;float: left;">读数据块时间：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.blockReadAvgTime)}" pattern="#,###.##" type="number"/></span> ms</div>
									</td>
								</tr>
							</table>
     						</div>
     					</div>
     				</s:if>			
     				<s:if test="obj.service_type==4">	
						<h2 class="datacenter dc-tt mgt-15"><span class="txt">重要指标</span></h2>
						<div class="item" >
							
							<div class="right">
								<table>
								<tr>
									<td width="300px;">
										<div>活动的：<span class="value"><s:property value="statisticsObj.numActiveNMs"/></span>个</div>
									</td>
									<td>
										<div>重启过的：<span class="value"><s:property value="statisticsObj.numDecommissionedNMs"/></span>个</div>
									</td>
								</tr>
								<tr>
									<td>
										<div>退役的：<span class="value"><s:property value="statisticsObj.numLostNMs"/></span>个</div>
									</td>
									<td>
										<div>不健康的：<span class="value"><s:property value="statisticsObj.numUnhealthyNMs"/></span>个</div>
									</td>
								</tr>
								<tr>
									<td>
										<div>丢失的：<span class="value"><s:property value="statisticsObj.numRebootedNMs"/></span>个</div>
									</td>
									<td>
									</td>
								</tr>
							</table>
     						</div>
     					</div>
     				</s:if>					
     				<s:if test="obj.service_type==6">	
						<h2 class="datacenter dc-tt mgt-15"><span class="txt">重要指标</span></h2>
						<div class="item" >
							
							<div class="right">
								<table>
								<tr>
									<td width="300px;">
										<div>每次splitlog大小：<span class="value"><s:property value="statisticsObj.splitSize_avg_time"/></span></div>
									</td>
									<td>
										<div>执行splitlog次数：<span class="value"><s:property value="statisticsObj.splitSize_num_ops"/></span></div>
									</td>
								</tr>
								<tr>
									<td>
										<div>每次splitlog时间：<span class="value"><s:property value="statisticsObj.splitTime_avg_time"/></span></div>
									</td>
									<td>
										<div>执行splitlog时间：<span class="value"><s:property value="statisticsObj.splitTime_num_ops"/></span></div>
									</td>
								</tr>
								</table>
     						</div>
     					</div>
     				</s:if>			
     				<s:if test="obj.service_type==7">	
						<h2 class="datacenter dc-tt mgt-15"><span class="txt">重要指标</span></h2>
						<div class="item" >
							
							<div class="right">
								<table>
								<tr>
									<td>
										<div>BC已使用大小：<span class="value"><fmt:formatNumber value="${(statisticsObj.blockCacheSize)}" pattern="#,###.##" type="number"/></span> MB</div>
										<div>BC已使用个数：<span class="value"><s:property value="statisticsObj.blockCacheCount"/></span> 个</div>
										<div>BC剩余大小：&nbsp;&nbsp;&nbsp;<span class="value"><fmt:formatNumber value="${(statisticsObj.blockCacheFree)}" pattern="#,###.##" type="number"/></span> MB</div>
									</td>
									<td width="60px;"></td>
									<td>
										<div>BC命中次数：&nbsp;&nbsp;&nbsp;<span class="value"><s:property value="statisticsObj.blockCacheHitCount"/></span> 次</div>
										<div>BC最大命中率：<span class="value"><fmt:formatNumber value="${(statisticsObj.blockCacheHitRatioMax)}" pattern="#,###.##" type="number"/></span> %</div>
										<div>BC最小命中率：<span class="value"><fmt:formatNumber value="${(statisticsObj.blockCacheHitRatioMin)}" pattern="#,###.##" type="number"/></span> %</div>
									</td>
								</tr>
							</table>
     						</div>
     					</div>
     				</s:if>							
     				<s:if test="nodeTypeMap.hive_thirftServer==10">	
						<h2 class="datacenter dc-tt mgt-15"><span class="txt">hive集群</span></h2>
						<div class="item" >
							<div class="top">
								<div class="ml5"><font color="orange" size="3">重要服务</font></div>
							</div>
							<div class="right">
								<ul class="dc-list">
									<li>
            							<i class="zhuji"></i><span class="txt">zookeeper</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hiveThirftServerCount"/></font></span>
            						</li>		
        							<li>
            							<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hiveThirftServerNormalCount"/></span></font></span>
        							</li>
        							<li>
           								<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hiveThirftServerCount - statisticsObj.hiveThirftServerNormalCount"/></font></span>
         							</li>
     							</ul>
     						</div>
     					</div>
     				</s:if>		
     				<s:if test="nodeTypeMap.hive_thirftServer==11">	
						<h2 class="datacenter dc-tt mgt-15"><span class="txt">impala集群</span></h2>
						<div class="item" >
							<div class="top">
								<div class="ml5"><font color="orange" size="3">重要服务</font></div>
							</div>
							<div class="right">
								<ul class="dc-list">
									<li>
            							<i class="zhuji"></i><span class="txt">zookeeper</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.impalaServerCount"/></font></span>
            						</li>		
        							<li>
            							<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.impalaServerNormalCount"/></span></font></span>
        							</li>
        							<li>
           								<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.impalaServerCount - statisticsObj.impalaServerNormalCount"/></font></span>
         							</li>
     							</ul>
     						</div>
     					</div>
     				</s:if>		
		<div class="mainbody" style="clear:both">
		 	<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">节点列表</h2>
			<div class="bord-1 pd-10">			
			<table width="100%" cellspacing="0" border="0" class="blue-table sorttable" id="theTable">
							<thead>
								<tr>
									<th onclick="sort(theTable,0,'string')">节点名称</th>
									<th onclick="sort(theTable,1,'string')">主机名称</th>
									<th onclick="sort(theTable,2,'string')">所属主机IP</th>
									<th onclick="sort(theTable,3,'string')">主机状态</th>
									<th onclick="sort(theTable,4,'string')">节点状态</th>
									<s:if test="obj.service_type == 7">
										<th onclick="sort(theTable,5,'string')">BC命中率</th>
									</s:if>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="retHostList" id="theBean">
									<tr>
										<td>
											<s:property value="#theBean.label_name"/>
										</td>
										<td>
											<s:property value="#theBean.host_name"/>
										</td>
										<td>
											<s:property value="#theBean.ip"/>
										</td>
										<td>
											<s:if test="#theBean.status == 1">
												运行
											</s:if>
											<s:else>
												停止
											</s:else>
										</td>
										<td>
											<s:if test="#theBean.state == 1">
												正常
											</s:if>
											<s:else>
												异常
											</s:else>
										</td>
										<s:if test="obj.service_type == 7">
											<td>
												<s:property value="#theBean.kpi_value"/>%
											</td>
										</s:if>
									</tr>
								</s:iterator>
							</tbody>
							</table>	
							<div class="pages mgb-10"><!-- 分页 -->
								<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
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