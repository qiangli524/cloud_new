<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/ah/paas/statistics/css/statistics.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
</head>
<script type="text/javascript">
	$(function(){
		var id = $("#id").val();
		var node_type = $("#node_type").val();
		 $("#getHits").insertFusionCharts({
             swfUrl: "FusionCharts/Pie2D.swf", 
             dataSource: "memcacheTab_showHits.do?id="+id+"&node_type="+node_type+"&flag=get",
             dataFormat: "jsonurl", 
             width: "64", 
             height: "64", 
             id: "getHit"
        });
		 $("#deleteHits").insertFusionCharts({
             swfUrl: "FusionCharts/Pie2D.swf", 
             dataSource: "memcacheTab_showHits.do?id="+id+"&node_type="+node_type+"&flag=delete",
             dataFormat: "jsonurl", 
             width: "64", 
             height: "64", 
             id: "deleteHit"
        });
		 $("#incrHits").insertFusionCharts({
             swfUrl: "FusionCharts/Pie2D.swf", 
             dataSource: "memcacheTab_showHits.do?id="+id+"&node_type="+node_type+"&flag=incr",
             dataFormat: "jsonurl", 
             width: "64", 
             height: "64", 
             id: "incrHit"
        });
		 $("#decrHits").insertFusionCharts({
             swfUrl: "FusionCharts/Pie2D.swf", 
             dataSource: "memcacheTab_showHits.do?id="+id+"&node_type="+node_type+"&flag=decr",
             dataFormat: "jsonurl", 
             width: "64", 
             height: "64", 
             id: "decrHit"
        });
		 $("#casHits").insertFusionCharts({
             swfUrl: "FusionCharts/Pie2D.swf", 
             dataSource: "memcacheTab_showHits.do?id="+id+"&node_type="+node_type+"&flag=cas",
             dataFormat: "jsonurl", 
             width: "64", 
             height: "64", 
             id: "casHit"
        });
		 $("#touchHits").insertFusionCharts({
             swfUrl: "FusionCharts/Pie2D.swf", 
             dataSource: "memcacheTab_showHits.do?id="+id+"&node_type="+node_type+"&flag=touch",
             dataFormat: "jsonurl", 
             width: "64", 
             height: "64", 
             id: "touchHit"
        });
	});
</script>
<body class="pop-body scrollbody">
		<s:hidden name="id"></s:hidden>
		<s:hidden name="node_type"></s:hidden>
		<div class="mainboxBus" style="width: 100%">
			<div class="title" style="margin-top: 18px;"><h2 class="datacenter dc-tt mgt-15"><span class="txt">服务器STATS信息</span></h2></div>
			<div class="item" style="width: 48%">
	  			<div class="top">
	  				<div class="ml5"><font color="orange" size="3">内存</font></div>
	  			</div>
	  			<div>
		  			<div class="right">
		  				<ul class="dc-list">
							<s:iterator value="memcacheList" id="theBean">
								<s:if test="#theBean.type_kpi=='213'">
									<li>
				            			<i class="zhuji"></i><span class="txt" style="width: 142px;">
				            			<s:property value="#theBean.desc_kpi"/>:</span>
				            			<span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.kpi_value_last"/></font></span>
				            		</li>	
			            		</s:if>
							</s:iterator>	
		     			</ul>
		  			</div>
	  			</div>
	  		</div>	
	  		<div class="item" style="width: 48%">
	  			<div class="top">
	  				<div class="ml5"><font color="orange" size="3">存储</font></div>
	  			</div>
	  			<div>
		  			<div class="right">
		  				<ul class="dc-list">
							<s:iterator value="memcacheList" id="theBean">
								<s:if test="#theBean.type_kpi=='214'">
									<li>
				            			<i class="zhuji"></i><span class="txt" style="width: 142px;">
				            			<s:property value="#theBean.desc_kpi"/>:</span>
				            			<span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.kpi_value_last"/></font></span>
				            		</li>	
			            		</s:if>
							</s:iterator>	
		     			</ul>
		  			</div>
	  			</div>
	  		</div>	
			<div class="item" style="width: 48%">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">请求次数</font></div>
				</div>
				<div>
					<div class="right">
		  				<ul class="dc-list">
							<s:iterator value="memcacheList" id="theBean">
								<s:if test="#theBean.type_kpi=='216'">
									<li>
				            			<i class="zhuji"></i><span class="txt" style="width: 142px;">
				            			<s:property value="#theBean.desc_kpi"/>:</span>
				            			<span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.kpi_value_last"/></font></span>
				            		</li>	
			            		</s:if>
							</s:iterator>	
		     			</ul>
		  			</div>
				</div>
			</div>
			<div class="item" style="width: 48%">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">对象</font></div>
				</div>
				<div>
					<div class="right">
		  				<ul class="dc-list">
							<s:iterator value="memcacheList" id="theBean">
								<s:if test="#theBean.type_kpi=='215'">
									<li>
				            			<i class="zhuji"></i><span class="txt" style="width: 142px;">
				            			<s:property value="#theBean.desc_kpi"/>:</span>
				            			<span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.kpi_value_last"/></font></span>
				            		</li>	
			            		</s:if>
							</s:iterator>	
		     			</ul>
		  			</div>
				</div>
			</div>
			<div class="item" style="width: 48%">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">get命令命中情况</font></div>
				</div>
				<div>
					<div class="right">
						<table>
							<td>
								<s:iterator value="memcacheList" id="theBean">
									<s:if test="#theBean.type_kpi=='217'">
										<ul class="dc-list">
											<li>
						            			<i class="zhuji"></i><span class="txt" style="width: 142px;">
						            			<s:property value="#theBean.desc_kpi"/>:</span>
						            			<span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.kpi_value_last"/></font></span>
						            		</li>	
				     					</ul>
				     				</s:if>
								</s:iterator>	
							</td>
							<td style="width: 50px;"></td>
							<td>
								<div class="left" id="getHits"></div>
							</td>
							<td style="width: 50px;"></td>
							<td>
								<div style="margin-top: 50px;"><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/blue.jpg" />命中</span><div>
								<div><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/red.jpg" />未命中</span></div>
	  						</td>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width: 48%">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">delete命令命中情况</font></div>
				</div>
				<div>
					<div class="right">
						<table>
							<td>
								<s:iterator value="memcacheList" id="theBean">
									<s:if test="#theBean.type_kpi=='218'">
										<ul class="dc-list">
											<li>
						            			<i class="zhuji"></i><span class="txt" style="width: 142px;">
						            			<s:property value="#theBean.desc_kpi"/>:</span>
						            			<span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.kpi_value_last"/></font></span>
						            		</li>	
				     					</ul>
				     				</s:if>
								</s:iterator>	
							</td>
							<td style="width: 50px;"></td>
							<td>
								<div class="left" id="deleteHits"></div>
							</td>
							<td style="width: 50px;"></td>
							<td>
								<div style="margin-top: 50px;"><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/blue.jpg" />命中</span><div>
								<div><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/red.jpg" />未命中</span></div>
	  						</td>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width: 48%">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">incr命令命中情况</font></div>
				</div>
				<div>
					<div class="right">
						<table>
							<td>
								<s:iterator value="memcacheList" id="theBean">
									<s:if test="#theBean.type_kpi=='219'">
										<ul class="dc-list">
											<li>
						            			<i class="zhuji"></i><span class="txt" style="width: 142px;">
						            			<s:property value="#theBean.desc_kpi"/>:</span>
						            			<span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.kpi_value_last"/></font></span>
						            		</li>	
				     					</ul>
				     				</s:if>
								</s:iterator>	
							</td>
							<td style="width: 50px;"></td>
							<td>
								<div class="left" id="incrHits"></div>
							</td>
							<td style="width: 50px;"></td>
							<td>
								<div style="margin-top: 50px;"><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/blue.jpg" />命中</span><div>
								<div><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/red.jpg" />未命中</span></div>
	  						</td>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width: 48%">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">decr命令命中情况</font></div>
				</div>
				<div>
					<div class="right">
						<table>
							<td>
								<s:iterator value="memcacheList" id="theBean">
									<s:if test="#theBean.type_kpi=='2110'">
										<ul class="dc-list">
											<li>
						            			<i class="zhuji"></i><span class="txt" style="width: 142px;">
						            			<s:property value="#theBean.desc_kpi"/>:</span>
						            			<span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.kpi_value_last"/></font></span>
						            		</li>	
				     					</ul>
				     				</s:if>
								</s:iterator>	
							</td>
							<td style="width: 50px;"></td>
							<td>
								<div class="left" id="decrHits"></div>
							</td>
							<td style="width: 50px;"></td>
							<td>
								<div style="margin-top: 50px;"><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/blue.jpg" />命中</span><div>
								<div><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/red.jpg" />未命中</span></div>
	  						</td>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width: 48%">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">cas命令命中情况</font></div>
				</div>
				<div>
					<div class="right">
						<table>
							<td>
								<s:iterator value="memcacheList" id="theBean">
									<s:if test="#theBean.type_kpi=='2111'">
										<ul class="dc-list">
											<li>
						            			<i class="zhuji"></i><span class="txt" style="width: 142px;">
						            			<s:property value="#theBean.desc_kpi"/>:</span>
						            			<span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.kpi_value_last"/></font></span>
						            		</li>	
				     					</ul>
				     				</s:if>
								</s:iterator>	
							</td>
							<td style="width: 50px;"></td>
							<td>
								<div class="left" id="casHits"></div>
							</td>
							<td style="width: 50px;"></td>
							<td>
								<div style="margin-top: 50px;"><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/blue.jpg" />命中</span><div>
								<div><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/red.jpg" />未命中</span></div>
	  						</td>
						</table>
					</div>
				</div>
			</div>
			<div class="item" style="width: 48%">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">touch命令命中情况</font></div>
				</div>
				<div>
					<div class="right">
						<table>
							<td>
								<s:iterator value="memcacheList" id="theBean">
									<s:if test="#theBean.type_kpi=='2112'">
										<ul class="dc-list">
											<li>
						            			<i class="zhuji"></i><span class="txt" style="width: 142px;">
						            			<s:property value="#theBean.desc_kpi"/>:</span>
						            			<span class="num blue-num"><font style="font-weight:bold;"><s:property value="#theBean.kpi_value_last"/></font></span>
						            		</li>	
				     					</ul>
				     				</s:if>
								</s:iterator>	
							</td>
							<td style="width: 50px;"></td>
							<td>
								<div class="left" id="touchHits"></div>
							</td>
							<td style="width: 50px;"></td>
							<td>
								<div style="margin-top: 50px;"><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/blue.jpg" />命中</span><div>
								<div><span><img src="<%=request.getContextPath()%>/hadoop/statistics/css/red.jpg" />未命中</span></div>
	  						</td>
						</table>
					</div>
				</div>
			</div>
	</div>
</body>