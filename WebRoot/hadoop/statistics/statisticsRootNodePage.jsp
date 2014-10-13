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
<body style="overflow:scroll;">
	<s:form action="" method="post" id="theForm">
	<div style="padding-left:1%">
		<table width="98%" border="0" cellspacing="0" cellpadding="0" >
			<tr>
				<td>
					<h2 class="clustertitle dc-tt"><span class="txt">集群</span><span class="other"><span class="num"><s:property value="statisticsObj.clusterCount"/></span>&nbsp;个</span></h2>
					<ul class="dc-list">
            			<li>
            				<i class="zhuji"></i><span class="txt">主机总数</span><span class="num orange-num"><font style="font-weight:bold;"><s:property value="statisticsObj.hostCount"/></font></span>
           				</li>
         			</ul>
			        <s:iterator value="resultList" id="theBean">
						<div class="mainbox">
							<h2 class="datacenter dc-tt mgt-15"><span class="txt"><s:property value="#theBean.clusterName"/></span></h2>
							<s:if test="#theBean.hadoopType==2">
								<div class="item" style="height:280px">
									<div class="top">
										<div class="ml5">
											<font color="orange" size="3">hadoop集群重要服务</font>
											<font style="padding-left: 40%;" color="orange" size="2">健康度:100</font>
										</div>
									</div>
									<div class="right">
										<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width: 110px;">namenode</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.nameNodeCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.nameNodeNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="#theBean.nameNodeCount - #theBean.nameNodeNormalCount"/></font></span>
						         			</li>
						     			</ul>  
										<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width: 110px;">datanode</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.datanodeCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.datanodeNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="#theBean.datanodeCount - #theBean.datanodeNormalCount"/></font></span>
						         			</li>
						     			</ul>  	
						     			<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width: 110px;">journalnode</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.journalnodeCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.journalnodeNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="#theBean.journalnodeCount-#theBean.journalnodeNormalCount"/></font></span>
						         			</li>
						     			</ul> 
						     			<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width: 110px;">nodeManager</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.nodeManagerCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.nodeManagerNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="#theBean.nodeManagerCount-#theBean.nodeManagerNormalCount"/></font></span>
						         			</li>
						     			</ul> 
						     			<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width: 110px;">resourceManager</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.reduceManagerCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.reduceManagerNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="#theBean.reduceManagerCount-#theBean.reduceManagerNormalCount"/></font></span>
						         			</li>
						     			</ul> 
									</div>
								</div>
							</s:if>
							<s:if test="#theBean.hbaseType==3">
						  		<div class="item" style="height:280px">
									<div class="top">
										<div class="ml5">
											<font color="orange" size="3">hbase集群重要服务</font>
											<font style="padding-left: 40%;" color="orange" size="2">健康度:100</font>
										</div>
									</div>
									<div class="right">
										<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width: 110px;">hmaster</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.hmasterCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.hmasterNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="#theBean.hmasterCount - #theBean.hmasterNormalCount"/></font></span>
						         			</li>
						     			</ul>  
										<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width: 110px;">regionserver</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.regionServerCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.regionServerNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="#theBean.regionServerCount - #theBean.regionServerNormalCount"/></font></span>
						         			</li>
						     			</ul>  	
						     			<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width: 110px;">thirftserver</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.hbaseThirftServerCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.hbaseThirftServerNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="#theBean.hbaseThirftServerCount-#theBean.hbaseThirftServerNormalCount"/></font></span>
						         			</li>
						     			</ul> 
									</div>
								</div>		
							</s:if>
							<s:if test="#theBean.hiveType==4">
								<div class="item">
									<div class="top">
										<div class="ml5">
											<font color="orange" size="3">hive集群重要服务</font>
											<font style="padding-left: 40%;" color="orange" size="2">健康度:100</font>	
										</div>
									</div>
									<div class="right">
										<ul class="dc-list">
											<li style="width:180px;">
						            			<i class="zhuji"></i><span class="txt" style="width: 110px;">hive</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.hiveThirftServerCount"/></font></span>
						            		</li>		
						        			<li style="width:95px;">
						            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.hiveThirftServerNormalCount"/></span></font></span>
						        			</li>
						        			<li style="width:95px;">
						           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="#theBean.hiveThirftServerCount - #theBean.hiveThirftServerNormalCount"/></font></span>
						         			</li>
						     			</ul>  
									</div>
								</div>									
							</s:if>
							<s:if test="#theBean.zookeeperType==5">
								<div class="item">
									<div class="top">
										<div class="ml5">
											<font color="orange" size="3">zookeeper集群重要服务</font>
											<font style="padding-left: 40%;" color="orange" size="2">健康度:100</font>
										</div>
									</div>
									<div class="right">
										<ul class="dc-list">
										<li style="width:180px;">
					            			<i class="zhuji"></i><span class="txt" style="width: 110px;">zookeeper</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.zookeeperServerCount"/></font></span>
					            		</li>		
					        			<li style="width:95px;">
					            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.zookeeperServerNormalCount"/></span></font></span>
					        			</li>
					        			<li style="width:95px;">
					           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="#theBean.zookeeperServerCount - #theBean.zookeeperServerNormalCount"/></font></span>
					         			</li>
					     				</ul>  
									</div>
								</div>		
							</s:if>
							<s:if test="#theBean.impalaType==6">
								<div class="item">
									<div class="top">
										<div class="ml5">
											<font color="orange" size="3">impala集群重要服务</font>
											<font style="padding-left: 40%;" color="orange" size="2">健康度:100</font>	
										</div>
									</div>
									<div class="right">
										<ul class="dc-list">
										<li style="width:180px;">
					            			<i class="zhuji"></i><span class="txt" style="width: 110px;">impala</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.impalaServerCount"/></font></span>
					            		</li>		
					        			<li style="width:95px;">
					            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.impalaServerNormalCount"/></span></font></span>
					        			</li>
					        			<li style="width:95px;">
					           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="#theBean.impalaServerCount - #theBean.impalaServerNormalCount"/></font></span>
					         			</li>
					     				</ul>  
									</div>
								</div>
							</s:if>
							<s:if test="#theBean.stormType==7">
								<div class="item">
									<div class="top">
										<div class="ml5">
											<font color="orange" size="3">storm集群重要服务</font>
											<font style="padding-left: 40%;" color="orange" size="2">健康度:100</font>
										</div>
									</div>
									<div class="right">
										<ul class="dc-list">
										<li style="width:180px;">
					            			<i class="zhuji"></i><span class="txt" style="width: 110px;">storm</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.nameNodeCount"/></font></span>
					            		</li>		
					        			<li style="width:95px;">
					            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.nameNodeNormalCount"/></span></font></span>
					        			</li>
					        			<li style="width:95px;">
					           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="#theBean.nameNodeCount - #theBean.nameNodeNormalCount"/></font></span>
					         			</li>
					     				</ul>  
									</div>
								</div>
							</s:if>
						</div>
					</s:iterator>
				</td>
			</tr>
		</table>
	</div>
	</s:form>
</body>