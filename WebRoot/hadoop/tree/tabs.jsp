<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />  
<meta http-equiv="expires" content="0" />
<head>


<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.core.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.widget.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.tabs.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/demos.css" />


		<style> 
            html { 
                font-size:10px; 
            } 
 
            .iframetab { 
                width:100%; 
                height:auto; 
                border:none;
                margin:0px; 
                position:relative; 
                top:0px;
                background：none;
            } 
 
            .ui-tabs-panel { 
                padding:5px !important; 
            } 
 
            .openout { 
                float:right; 
                position:relative; 
                top:-28px; 
                left:-5px; 
            } 
        </style> 
	<script>
	 $(document).ready(function() { 
		var $tabs = $('#tabs').tabs({
			ajaxOptions: {
				cache:false,	
				error: function( xhr, status, index, anchor ) {
					$( anchor.hash ).html(
						"数据加载失败,请重试！");
				}
			}
		}); 
 
        $("a.tabref").click(function() { 
        	loadTabFrame($(this).attr("href"),$(this).attr("rel")); 
        }); 
 
       //tab switching function 
        function loadTabFrame(tab, url) { 
        	if ($(tab).find("iframe").length == 0) { 
            	var html = []; 
            	html.push('<div class="tabIframeWrapper">'); 
            	html.push('<div class="openout"><a href="' + url + '">  </a></div><iframe class="iframetab" frameborder="0" allowtransparency="true" src="' + url + '">Load Failed?</iframe>'); 
            	html.push('</div>'); 
            	$(tab).append(html.join("")); 
            	$(tab).find("iframe").height($(window).height()-80); 
        	} 
            return false; 
        } 
       
	});
	</script>
</head>
<body>
<div class="demo">
<div id="tabs" style="position:absolute;bottom:2px;top:2px;left:5px;right:5px;overflow-y:auto;">
	<ul>
		<s:if test="obj.node_type==0"> <!--hadoop根节点 -->
			<li><a href="hadoopstatistics_statisticsRootNode.do?obj.id=<s:property value='obj.id'/>&obj.node_type=<s:property value='obj.node_type'/>">摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="topo_newTopo.do?parentId=<s:property value='obj.id'/>">拓扑图</a></li>
			<li><a class="tabref" href="#tabs-2" rel="hadoopHost_listHostInfo.do">主机</a></li>				
			<%--<li><a class="tabref" href="#tabs-3" rel="hadooptop_goTopPage.do?obj.id=<s:property value='obj.id'/>&obj.node_type=<s:property value='obj.node_type'/>">指标TopN</a></li>--%>
		</s:if>
		<s:if test="obj.node_type==1"><!-- 大数据中心节点 -->
			<li><a href="hadoopstatistics_statisticsCenterNode.do?obj.id=<s:property value='obj.id'/>&obj.node_type=<s:property value='obj.node_type'/>" >摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="topo_newTopo.do?parentId=<s:property value='obj.id'/>">拓扑图</a></li>
			<%--<li><a class="tabref" href="#tabs-2" rel="hadoopHostServer_listClusterHadoopHostServer.do?entityId=<s:property value='obj.uuid'/>&isHideKpiList=1">集群监控</a></li>--%>
			<li><a class="tabref" href="#tabs-3" rel="treeprocess_listHadoopProcess.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">进程</a></li>
			<li><a class="tabref" href="#tabs-4" rel="hadoopHost_queryHostInfoByNode.do?entityId=<s:property value='obj.uuid'/>&nodeId=<s:property value='obj.id'/>&type=<s:property value='obj.node_type'/>">主机</a></li>
		</s:if>
		<s:if test="obj.node_type==2"><!--hadoop集群 -->
			<li><a href="hadoopstatistics_statisticsHadoopNode.do?obj.id=<s:property value='obj.id'/>&obj.node_type=<s:property value='obj.node_type'/>" >摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="topo_newTopo.do?parentId=<s:property value='obj.id'/>">拓扑图</a></li>
			<li><a class="tabref" href="#tabs-2" rel="treeprocess_listHadoopProcess.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">进程</a></li>
			<li><a class="tabref" href="#tabs-4" rel="hadoopHost_queryHostInfoByNode.do?entityId=<s:property value='obj.uuid'/>&nodeId=<s:property value='obj.id'/>&type=<s:property value='obj.node_type'/>">主机</a></li>
<%--			<li><a class="tabref" href="#tabs-3" rel="hadoopJvm_queryLogHostCount.do?obj.parent_id=<s:property value='obj.parent_id'/>&obj.id=<s:property value='obj.id'/>">JVM</a></li>		--%>
		</s:if>
		<s:if test="obj.node_type==3"><!--hbase集群 -->
			<li><a href="hadoopstatistics_statisticsHbaseNode.do?obj.id=<s:property value='obj.id'/>&obj.node_type=<s:property value='obj.node_type'/>" >摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="topo_newTopo.do?parentId=<s:property value='obj.id'/>">拓扑图</a></li>
			<li><a class="tabref" href="#tabs-2" rel="treeprocess_listHadoopProcess.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">进程</a></li>
			<li><a class="tabref" href="#tabs-4" rel="hadoopHost_queryHostInfoByNode.do?entityId=<s:property value='obj.uuid'/>&nodeId=<s:property value='obj.id'/>&type=<s:property value='obj.node_type'/>">主机</a></li>	
<%--			<li><a class="tabref" href="#tabs-3" rel="hadoopJvm_queryLogHostCount.do?obj.parent_id=<s:property value='obj.parent_id'/>&obj.id=<s:property value='obj.id'/>">JVM</a></li>		--%>
		</s:if>
		<s:if test="obj.node_type==4"><!--hive集群 -->
			<li><a href="hadoopstatistics_statisticsOtherClusterNode.do?obj.id=<s:property value='obj.id'/>" >摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="topo_newTopo.do?parentId=<s:property value='obj.id'/>">拓扑图</a></li>
			<li><a class="tabref" href="#tabs-2" rel="treeprocess_listHadoopProcess.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">进程</a></li>
			<li><a class="tabref" href="#tabs-4" rel="hadoopHost_queryHostInfoByNode.do?entityId=<s:property value='obj.uuid'/>&nodeId=<s:property value='obj.id'/>&type=<s:property value='obj.node_type'/>">主机</a></li>		
<%--			<li><a class="tabref" href="#tabs-3" rel="hadoopJvm_queryLogHostCount.do?obj.parent_id=<s:property value='obj.parent_id'/>&obj.id=<s:property value='obj.id'/>">JVM</a></li>	--%>
		</s:if>
		<s:if test="obj.node_type==5"><!--zookeeper集群 -->
			<li><a href="hadoopstatistics_statisticsOtherClusterNode.do?obj.id=<s:property value='obj.id'/>" >摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="topo_newTopo.do?parentId=<s:property value='obj.id'/>">拓扑图</a></li>
			<li><a class="tabref" href="#tabs-2" rel="treeprocess_listHadoopProcess.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">进程</a></li>
			<li><a class="tabref" href="#tabs-4" rel="hadoopHost_queryHostInfoByNode.do?entityId=<s:property value='obj.uuid'/>&nodeId=<s:property value='obj.id'/>&type=<s:property value='obj.node_type'/>">主机</a></li>	
<%--			<li><a class="tabref" href="#tabs-3" rel="hadoopJvm_queryLogHostCount.do?obj.parent_id=<s:property value='obj.parent_id'/>&obj.id=<s:property value='obj.id'/>">JVM</a></li>		--%>
		</s:if>
		<s:if test="obj.node_type==6"><!--impala集群 -->
			<li><a href="hadoopstatistics_statisticsOtherClusterNode.do?obj.id=<s:property value='obj.id'/>" >摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="topo_newTopo.do?parentId=<s:property value='obj.id'/>">拓扑图</a></li>
			<li><a class="tabref" href="#tabs-2" rel="treeprocess_listHadoopProcess.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">进程</a></li>
			<li><a class="tabref" href="#tabs-4" rel="hadoopHost_queryHostInfoByNode.do?entityId=<s:property value='obj.uuid'/>&nodeId=<s:property value='obj.id'/>&type=<s:property value='obj.node_type'/>">主机</a></li>	
<%--			<li><a class="tabref" href="#tabs-3" rel="hadoopJvm_queryLogHostCount.do?obj.parent_id=<s:property value='obj.parent_id'/>&obj.id=<s:property value='obj.id'/>">JVM</a></li>		--%>
		</s:if>
		<s:if test="obj.node_type==7"><!--storm集群 -->
			<li><a href="hadoopstatistics_statisticsOtherClusterNode.do?obj.id=<s:property value='obj.id'/>" >摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="topo_newTopo.do?parentId=<s:property value='obj.id'/>">拓扑图</a></li>
			<li><a class="tabref" href="#tabs-2" rel="treeprocess_listHadoopProcess.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">进程</a></li>
			<li><a class="tabref" href="#tabs-4" rel="hadoopHost_queryHostInfoByNode.do?entityId=<s:property value='obj.uuid'/>&nodeId=<s:property value='obj.id'/>&type=<s:property value='obj.node_type'/>">主机</a></li>	
<%--			<li><a class="tabref" href="#tabs-3" rel="hadoopJvm_queryLogHostCount.do?obj.parent_id=<s:property value='obj.parent_id'/>&obj.id=<s:property value='obj.id'/>">JVM</a></li>		--%>
		</s:if>
		<s:if test="obj.node_type==8"><!--hdfs -->
			<li><a href="hadoopstatistics_statisticsHdfsNode.do?obj.id=<s:property value='obj.id'/>&obj.node_type=<s:property value='obj.node_type'/>" >摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="topo_newTopo.do?parentId=<s:property value='obj.id'/>">拓扑图</a></li>
			<li><a class="tabref" href="#tabs-2" rel="treeprocess_listHadoopProcess.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">进程</a></li>
			<li><a class="tabref" href="#tabs-4" rel="hadoopHost_queryHostInfoByNode.do?entityId=<s:property value='obj.uuid'/>&nodeId=<s:property value='obj.id'/>&type=<s:property value='obj.node_type'/>">主机</a></li>
<%--			<li><a class="tabref" href="#tabs-3" rel="hadoopJvm_queryLogHostCount.do?obj.parent_id=<s:property value='obj.parent_id'/>&obj.id=<s:property value='obj.id'/>">JVM</a></li>--%>
		</s:if>
		<s:if test="obj.node_type==9"><!--mapReduce -->
			<li><a href="hadoopstatistics_statisticsMapReduceNode.do?obj.id=<s:property value='obj.id'/>&obj.node_type=<s:property value='obj.node_type'/>" >摘要</a></li>
			<li><a class="tabref" href="#tabs-6" rel="topo_newTopo.do?parentId=<s:property value='obj.id'/>">拓扑图</a></li>
			<li><a class="tabref" href="#tabs-1" rel="hadoopApplication_queryApplicationListByObj.do?obj.id=<s:property value='obj.id'/>">Job</a></li>
			<li><a class="tabref" href="#tabs-2" rel="treeprocess_listHadoopProcess.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">进程</a></li>
			<li><a class="tabref" href="#tabs-4" rel="deployconfig_listHadoopConfig.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">配置文件</a></li>
			<li><a class="tabref" href="#tabs-5" rel="hadoopHost_queryHostInfoByNode.do?entityId=<s:property value='obj.uuid'/>&nodeId=<s:property value='obj.id'/>&type=<s:property value='obj.node_type'/>">主机</a></li>
<%--			<li><a class="tabref" href="#tabs-3" rel="hadoopJvm_queryLogHostCount.do?obj.parent_id=<s:property value='obj.parent_id'/>&obj.id=<s:property value='obj.id'/>">JVM</a></li>--%>
		</s:if>
		<s:if test="obj.node_type==10"><!-- 服务节点 -->
			<li>
				<a href="hadoopstatistics_statisticsServiceNode.do?obj.id=<s:property value='obj.id'/>&obj.service_type=<s:property value='obj.service_type'/>">摘要</a>
			</li>
			<li><a class="tabref" href="#tabs-7" rel="topo_newTopo.do?parentId=<s:property value='obj.id'/>">拓扑图</a></li>
			<%--<li>
				<a class="tabref" href="#tabs-1" rel="hadooptop_goTopPage.do?obj.id=<s:property value='obj.id'/>&obj.node_type=<s:property value='obj.node_type'/>">指标TopN</a>
			</li>--%>
			<li><a class="tabref" href="#tabs-2" rel="treeprocess_listHadoopProcess.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">进程</a></li>
			<s:if test="obj.service_type==2||obj.service_type==4">
				<li><a class="tabref" href="#tabs-4" rel="hadoopService_listServiceList.do?tree.service_type=<s:property value='obj.service_type'/>">服务状态</a></li>
			</s:if>
			<s:if test="obj.service_type==4||obj.service_type==5">
				<li><a class="tabref" href="#tabs-5" rel="deployconfig_listHadoopConfig.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">配置文件</a></li>
			</s:if>
			<li><a class="tabref" href="#tabs-6" rel="hadoopHost_queryHostInfoByNode.do?entityId=<s:property value='obj.uuid'/>&nodeId=<s:property value='obj.id'/>&type=<s:property value='obj.node_type'/>">主机</a></li>
<%--			<li><a class="tabref" href="#tabs-3" rel="hadoopJvm_queryLogHostCount.do?obj.parent_id=<s:property value='obj.parent_id'/>&obj.id=<s:property value='obj.id'/>">JVM</a></li>--%>
		</s:if>
		<s:if test="obj.node_type==11"><!-- 主机节点 -->
		    <li><a href="hadoopstatistics_statisticsExampleNode.do?obj.id=<s:property value='obj.id'/>">摘要</a></li>
			<li><a class="tabref" href="#tabs-2" rel="hadoopHostServer_listHadoopHostServer.do?entityId=<s:property value='obj.uuid'/>&parentId=<s:property value='obj.parent_id'/>">主机监控</a></li>
			<li><a class="tabref" href="#tabs-3" rel="hadoopHostServer_listHadoopHostServerKpi.do?entityId=<s:property value='obj.uuid'/>&parentId=<s:property value='obj.parent_id'/>">指标监控</a></li>
			<li><a class="tabref" href="#tabs-4" rel="treeprocess_listHadoopProcess.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">进程</a></li>
			<s:if test="obj.service_type==4||obj.service_type==5">
				<li><a class="tabref" href="#tabs-5" rel="deployconfig_listHadoopConfig.do?example_id=<s:property value='obj.id'/>&hostIP=<s:property value='hostInfoObj.ip'/>&type=<s:property value='obj.node_type'/>">配置文件</a></li>
			</s:if>
			<%--<li><a class="tabref" href="#tabs-6" rel="hadoopHost_queryHostInfoByNode.do?entityId=<s:property value='obj.uuid'/>&nodeId=<s:property value='obj.id'/>&type=<s:property value='obj.node_type'/>">主机</a></li>--%>
			<li><a class="tabref" href="#tabs-7" rel="hadoopJvm_showJvm.do?hostInfoObj.id=<s:property value='obj.uuid'/>">JVM</a></li>
		</s:if>
	</ul>
	<!-- tab switching -->
	<div id="tabs-1">
    </div>
    <div id="tabs-2"> 
    </div> 
    <div id="tabs-3"> 
    </div> 
    <div id="tabs-4"> 
    </div>
    <div id="tabs-5"> 
    </div> 
    <div id="tabs-6"> 
    </div>
    <div id="tabs-7"> 
    </div> 
    <div id="tabs-8"> 
    </div>
    
</div>

</div><!-- End demo -->
</body>
