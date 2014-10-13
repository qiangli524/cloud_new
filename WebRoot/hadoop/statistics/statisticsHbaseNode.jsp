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
     					<h2 class="datacenter dc-tt mgt-15"><span class="txt">hbase集群</span></h2>
     					<div class="item" style="height:180px">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">重要服务</font></div>
							</div>
							<div class="right">
								<ul class="dc-list">
									<li style="width:160px;">
            							<i class="zhuji"></i><span class="txt" style="width:80px;">hmaster</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hmasterCount"/></font></span>
            						</li>		
        							<li style="width:105px;">
            							<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hmasterNormalCount"/></span></font></span>
        							</li>
        							<li style="width:105px;">
           								<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hmasterCount - statisticsObj.hmasterNormalCount"/></font></span>
         							</li>
     							</ul>
     							<ul class="dc-list">
									<li style="width:160px;">
            							<i class="zhuji"></i><span class="txt" style="width:80px;">regionserver</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.regionServerCount"/></font></span>
            						</li>		
        							<li style="width:105px;">
            							<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.regionServerNormalCount"/></span></font></span>
        							</li>
        							<li style="width:105px;">
           								<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.regionServerCount - statisticsObj.regionServerNormalCount"/></font></span>
         							</li>
     							</ul>
     							<ul class="dc-list">
									<li style="width:160px;">
            							<i class="zhuji"></i><span class="txt" style="width:80px;">thirftserver</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hbaseThirftServerCount"/></font></span>
            						</li>		
        							<li style="width:105px;">
            							<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hbaseThirftServerNormalCount"/></span></font></span>
        							</li>
        							<li style="width:105px;">
           								<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hbaseThirftServerCount - statisticsObj.hbaseThirftServerNormalCount"/></font></span>
         							</li>
     							</ul>
     						</div>	  
     					</div>	
     					<div class="item" style="height:180px">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">hmaster</font></div>
								<div class="right" style="margin-left:10px;margin-top: 10px;">
									<div style="margin-top:5px"><div style="width:180px; float: left;">每次splitlog大小：</div><span class="value"><s:property value="statisticsObj.splitSize_avg_time"/></span></div>
									<div><div style="width:180px; float: left;">执行splitlog次数：</div><span class="value"><s:property value="statisticsObj.splitSize_num_ops"/></span></div>
									<div><div style="width:180px; float: left;">每次splitlog时间：</div><span class="value"><s:property value="statisticsObj.splitTime_avg_time"/></span></div>
								</div>								
							</div>							
						</div>	
     					<div class="item">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">regionserver</font></div>
								<div class="right" style="margin-left:10px;margin-top: 10px;">
									<div style="margin-top:5px"><div style="width:120px; float: left;">BC已使用大小：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.blockCacheSize)}" pattern="#,###.##" type="number"/></span> MB</div>
									<div><div style="width:120px; float: left;">BC已使用个数：</div><span class="value"><s:property value="statisticsObj.blockCacheCount"/></span> 个</div>
									<div><div style="width:120px; float: left;">BC剩余大小：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.blockCacheFree)}" pattern="#,###.##" type="number"/></span> MB</div>
									<div><div style="width:120px; float: left;">BC命中次数：</div><span class="value"><s:property value="statisticsObj.blockCacheHitCount"/></span> 次</div>
									<div><div style="width:120px; float: left;">BC最大命中率：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.blockCacheHitRatioMax)}" pattern="#,###.##" type="number"/></span> %</div>
									<div><div style="width:120px; float: left;">BC最小命中率：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.blockCacheHitRatioMin)}" pattern="#,###.##" type="number"/></span> %</div>								
								</div>								
							</div>							
						</div>	
						<div class="item">
							<div class="top">
								<div class="ml5"><font color="orange" size="3">regionserver</font></div>
								<div class="right" style="margin-left:10px;margin-top: 10px;">
									<div style="margin-top:5px"><div style="width:180px; float: left;">regions个数：</div><span class="value"><s:property value="statisticsObj.regionsCount"/></span> 个</div>
									<div><div style="width:180px; float: left;">compaction操作QUEUE大小：</div><span class="value"><s:property value="statisticsObj.compactionQueueSize"/></span> 个</div>
									<div><div style="width:180px; float: left;">flushQueue大小：</div><span class="value"><s:property value="statisticsObj.flushQueueSize"/></span> 个</div>
									<div><div style="width:180px; float: left;">memstore：</div><span class="value"><fmt:formatNumber value="${(statisticsObj.memstoreSizeMB)}" pattern="#,###.##" type="number"/></span> MB</div>
									<div><div style="width:180px; float: left;">请求个数：</div><span class="value"><s:property value="statisticsObj.requests"/></span> 个</div>
		  							<div><div style="width:180px; float: left;">region split成功次数：</div><span class="value"><s:property value="statisticsObj.regionSplitSuccessCount"/></span> 次</div>
									<div><div style="width:180px; float: left;">region split失败次数：</div><span class="value"><s:property value="statisticsObj.regionSplitFailureCount"/></span> 次</div>
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