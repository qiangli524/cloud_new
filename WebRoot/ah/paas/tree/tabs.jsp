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
<script language="javascript" id="ss1" type="text/javascript" src="/cloud/js/My97DatePicker/WdatePicker.js"></script>


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
	<s:hidden name="id"></s:hidden>
	<s:hidden name="name"></s:hidden>
	<s:hidden name="entity_id"></s:hidden>
	<s:hidden name="node_type"></s:hidden>
	<s:hidden name="parent_id"></s:hidden>
	<s:hidden name="server_type"></s:hidden>
	<s:hidden name="coll_time"></s:hidden>
	<s:hidden name="timeline"></s:hidden>
	<s:hidden name="cycle_time"></s:hidden>
<div id="tabs" style="position:absolute;bottom:2px;top:2px;left:5px;right:5px;overflow-y:auto;">
	<ul>
		<s:if test="node_type==0"> <!-- 数据中心 -->
			<li><a href="oracleTab_showPaas.do?id=<s:property value='id' />" >摘要</a></li>
		</s:if>
		
		<!-- Caas -->
		<!-- memcache -->
		<s:elseif test="node_type==10">
			<li><a href="memcacheTab_caasResourcePool.do?id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==11 && server_type==1"><!-- 缓存-->
			<li><a href="memcacheTab_memcacheResourcePool.do?id=<s:property value='id' />&node_type=<s:property value='node_type'/>">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==12 && server_type==1"><!-- 业务-->
			<li><a href="memcacheTab_memcacheResourcePool.do?id=<s:property value='id' />&node_type=<s:property value='node_type'/>">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==13 && server_type==1"><!-- 实例 -->
			<li><a href="memcacheTab_memcacheExamples.do?id=<s:property value='id' />&entity_id=<s:property value='entity_id' />&node_type=<s:property value='node_type'/>">摘要</a></li>
			<li><a class="tabref" href="#tabs-2" rel="paasStatisticsMonitor_goStatisticsMonitorPage.do?entity_id=<s:property value='entity_id' />&node_type=<s:property value='node_type'/>">统计监控</a></li>
			<li><a class="tabref" href="#tabs-3" rel="paasHitMonitor_goPaasHitMonitorPage.do?entity_id=<s:property value='entity_id' />&node_type=<s:property value='node_type'/>">命中监控</a></li>
			<li><a class="tabref" href="#tabs-4" rel="paasDataMonitor_goPaasDataMonitorPage.do?entity_id=<s:property value='entity_id' />&node_type=<s:property value='node_type'/>">数据监控</a></li>
		</s:elseif>
		<s:elseif test="node_type==14 && server_type==1"><!-- 主机 -->
			<li><a href="memcacheTab_showhostInfo.do?entity_id=<s:property value='entity_id' />&node_type=<s:property value='node_type'/>">摘要</a></li>
		</s:elseif>
		
		<!-- DAAS -->
		<!-- oracle -->
		<s:elseif test="node_type==20">
			<li><a href="oracleTab_showDaas.do?id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==21 && server_type==3">
			<li><a href="oracleTab_showDBType.do?id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="treeprocess_listPaasProcess.do?example_id=<s:property value='id'/>&type=<s:property value='node_type'/>&server_type=<s:property value='server_type'/>">进程</a></li>		
		</s:elseif>
		<s:elseif test="node_type==22 && server_type==3"><!-- 业务-->
			<li><a href="oracleTab_showDBBusiness.do?id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="treeprocess_listPaasProcess.do?example_id=<s:property value='id'/>&type=<s:property value='node_type'/>&server_type=<s:property value='server_type'/>">进程</a></li>
		</s:elseif>
		<s:elseif test="node_type==23 && server_type==31 "><!-- 数据库 -->
			<li><a href="oracleTab_queryDBList.do?id=<s:property value='id' />">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==23 && server_type==32 "><!-- 数据库实例 -->
			<li><a href="oracleTab_queryDBInstanceList.do?id=<s:property value='id' />">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==23 && server_type==33 "><!-- 数据库服务 -->
			<li><a href="oracleTab_queryDBServerList.do?id=<s:property value='id' />">摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="treeprocess_listPaasProcess.do?example_id=<s:property value='id'/>&type=<s:property value='node_type'/>&server_type=<s:property value='server_type'/>">进程</a></li>
		</s:elseif>
		<s:elseif test="node_type==24 && server_type==31"><!-- 数据库 -->
			<li><a href="oracleTab_entityAbstract.do?entity_id=<s:property value='entity_id' />">摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="oracleTab_showDBChart.do?entity_id=<s:property value='entity_id' />&kpi_type=capacity" >容量</a></li>
			<li><a class="tabref" href="#tabs-2" rel="oracleTab_showDBChart.do?entity_id=<s:property value='entity_id' />&kpi_type=efficiency" >负载</a></li>
		</s:elseif>
		<s:elseif test="node_type==24 && server_type==32"><!-- 数据库实例 -->
			<li><a href="oracleTab_entityStatistics.do?entity_id=<s:property value='entity_id' />&id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>&coll_time=<s:property value='coll_time'/>&timeline=<s:property value='timeline'/>&cycle_time=<s:property value='cycle_time'/>">统计</a></li>
			<li><a class="tabref" href="#tabs-1" rel="oracleTab_showInstanceChart.do?entity_id=<s:property value='entity_id' />&kpi_type=response" >响应</a></li>
			<%--<li><a class="tabref" href="#tabs-2" rel="oracleTab_showInstanceChart.do?entity_id=<s:property value='entity_id' />&kpi_type=availability" >可用性</a></li>--%>
			<li><a class="tabref" href="#tabs-3" rel="oracleTab_showInstanceChart.do?entity_id=<s:property value='entity_id' />&kpi_type=efficiency" >负载</a></li>
			<li><a class="tabref" href="#tabs-4" rel="oracleTab_showInstanceChart.do?entity_id=<s:property value='entity_id' />&kpi_type=capacity" >容量</a></li>
			<li><a class="tabref" href="#tabs-5" rel="oracleTab_showInstanceChart.do?entity_id=<s:property value='entity_id' />&kpi_type=resource" >资源</a></li>
			<li><a class="tabref" href="#tabs-6" rel="oracleTab_showInstanceChart.do?entity_id=<s:property value='entity_id' />&kpi_type=exception" >异常</a></li>
		</s:elseif>
		<s:elseif test="node_type==24 && server_type==33"><!-- 数据库服务 -->
			<li><a href="oracleTab_entityStatistics.do?entity_id=<s:property value='entity_id' />&id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">统计</a></li>
			<li><a class="tabref" href="#tabs-1" rel="oracleTab_showServerChart.do?entity_id=<s:property value='entity_id' />&kpi_type=efficiency" >负载</a></li>
			<li><a class="tabref" href="#tabs-2" rel="oracleTab_showServerChart.do?entity_id=<s:property value='entity_id' />&kpi_type=response" >响应</a></li>
			<li><a class="tabref" href="#tabs-3" rel="oracleTab_showServerChart.do?entity_id=<s:property value='entity_id' />&kpi_type=resource" >资源</a></li>
		</s:elseif>
		
		<!-- mysql -->
		<s:elseif test="node_type==21 && server_type==4">
			<li><a href="mysqlTab_showDBType.do?id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==22 && server_type==4"><!-- 业务-->
			<li><a href="mysqlTab_showDBBusiness.do?id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==23 && server_type==4">
			<li><a href="mysqlTab_showMysqlEntityStatistics.do?id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==24 && server_type==4">
			<li><a href="mysqlTab_entityStatistics.do?entity_id=<s:property value='entity_id' />&id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>&coll_time=<s:property value='coll_time'/>&timeline=<s:property value='timeline'/>&cycle_time=<s:property value='cycle_time'/>">统计</a></li>
			<li><a class="tabref" href="#tabs-1" rel="mysqlTab_showMysqlChart.do?entity_id=<s:property value='entity_id' />&kpi_type=qps" >QPS</a></li>
			<li><a class="tabref" href="#tabs-2" rel="mysqlTab_showMysqlChart.do?entity_id=<s:property value='entity_id' />&kpi_type=tps" >TPS</a></li>
			<li><a class="tabref" href="#tabs-3" rel="mysqlTab_showMysqlChart.do?entity_id=<s:property value='entity_id' />&kpi_type=query_cache" >Query Cache</a></li>
			<li><a class="tabref" href="#tabs-4" rel="mysqlTab_showMysqlChart.do?entity_id=<s:property value='entity_id' />&kpi_type=key_buffer" >Key Buffer</a></li>
			<li><a class="tabref" href="#tabs-5" rel="mysqlTab_showMysqlChart.do?entity_id=<s:property value='entity_id' />&kpi_type=table_cache" >Table Cache</a></li>
			<li><a class="tabref" href="#tabs-6" rel="mysqlTab_showMysqlChart.do?entity_id=<s:property value='entity_id' />&kpi_type=tmp_table" >Tmp Table</a></li>
			<li><a class="tabref" href="#tabs-7" rel="mysqlTab_showMysqlChart.do?entity_id=<s:property value='entity_id' />&kpi_type=binlog_cache" >Binlog Cache</a></li>
		</s:elseif>
		
		<!-- GP -->
		<s:elseif test="node_type==23 && server_type==6">
			<li><a href="greenPlumTab_showGreenPlumHost.do?id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">摘要</a></li>
		</s:elseif>
		
		<!-- MAAS -->
		<!-- weblogic -->
		<s:elseif test="node_type==30">
			<li><a href="weblogicTab_showMaas.do?id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==31 && server_type==5">
			<li><a href="weblogicTab_showMiddlewareType.do?id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="treeprocess_listPaasProcess.do?example_id=<s:property value='id'/>&type=<s:property value='node_type'/>&server_type=<s:property value='server_type'/>">进程</a></li>		
		</s:elseif>
		<s:elseif test="node_type==32 && server_type==5"><!-- 业务-->
			<li><a href="weblogicTab_showMiddlewareBusiness.do?id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="treeprocess_listPaasProcess.do?example_id=<s:property value='id'/>&type=<s:property value='node_type'/>&server_type=<s:property value='server_type'/>">进程</a></li>
		</s:elseif>
		<s:elseif test="node_type==33 && server_type==51 "><!-- 中间件(数据源) -->
			<li><a href="weblogicTab_queryDataSourceList.do?id=<s:property value='id' />">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==33 && server_type==52 "><!-- 中间件(服务) -->
			<li><a href="weblogicTab_queryServerList.do?id=<s:property value='id' />">摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="treeprocess_listPaasProcess.do?example_id=<s:property value='id'/>&type=<s:property value='node_type'/>&server_type=<s:property value='server_type'/>">进程</a></li>
		</s:elseif>
		<s:elseif test="node_type==34 && server_type==51"><!-- 数据源 -->
			<li><a href="weblogicTab_entityStatistics.do?entity_id=<s:property value='entity_id' />&id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">统计</a></li>
			<li><a class="tabref" href="#tabs-1" rel="weblogicTab_showDataSourceChart.do?entity_id=<s:property value='entity_id' />&kpi_type=response" >响应</a></li>
			<li><a class="tabref" href="#tabs-2" rel="weblogicTab_showDataSourceChart.do?entity_id=<s:property value='entity_id' />&kpi_type=efficiency" >负载</a></li>
			<li><a class="tabref" href="#tabs-3" rel="weblogicTab_showDataSourceChart.do?entity_id=<s:property value='entity_id' />&kpi_type=capacity" >容量</a></li>
			<li><a class="tabref" href="#tabs-4" rel="weblogicTab_showDataSourceChart.do?entity_id=<s:property value='entity_id' />&kpi_type=exception" >异常</a></li>
		</s:elseif>
		<s:elseif test="node_type==34 && server_type==52"><!-- 实例 -->
			<li><a href="weblogicTab_entityStatistics.do?entity_id=<s:property value='entity_id' />&id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>&coll_time=<s:property value='coll_time'/>&timeline=<s:property value='timeline'/>&cycle_time=<s:property value='cycle_time'/>">统计</a></li>
			<li><a class="tabref" href="#tabs-1" rel="weblogicTab_showServerChart.do?entity_id=<s:property value='entity_id' />&kpi_type=efficiency" >负载</a></li>
			<li><a class="tabref" href="#tabs-2" rel="weblogicTab_showServerChart.do?entity_id=<s:property value='entity_id' />&kpi_type=resource" >资源</a></li>
			<li><a class="tabref" href="#tabs-3" rel="weblogicTab_showServerChart.do?entity_id=<s:property value='entity_id' />&kpi_type=response" >响应</a></li>
			<li><a class="tabref" href="#tabs-4" rel="weblogicTab_showServerChart.do?entity_id=<s:property value='entity_id' />&kpi_type=exception" >异常</a></li>
			<li><a class="tabref" href="#tabs-5" rel="weblogicTab_showServerChart.do?entity_id=<s:property value='entity_id' />&kpi_type=capacity" >容量</a></li>
		</s:elseif>
		
		<!-- tomcat -->
		<s:elseif test="node_type==31 && server_type==2">
			<li><a href="tomcatTab_showMiddlewareType.do?id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==32 && server_type==2"><!-- 业务-->
			<li><a href="tomcatTab_showMiddlewareBusiness.do?id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==33 && server_type==2 "><!-- 中间件(服务) -->
			<li><a href="tomcatTab_queryServerList.do?id=<s:property value='id' />">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==34 && server_type==2"><!-- 实例 -->
			<li><a href="tomcatTab_entityStatistics.do?entity_id=<s:property value='entity_id' />&id=<s:property value='id' />&server_type=<s:property value='server_type'/>&node_type=<s:property value='node_type'/>&coll_time=<s:property value='coll_time'/>&timeline=<s:property value='timeline'/>&cycle_time=<s:property value='cycle_time'/>">统计</a></li>
			<li><a class="tabref" href="#tabs-1" rel="tomcatTab_showServerChart.do?entity_id=<s:property value='entity_id' />&kpi_type=efficiency" >负载</a></li>
			<li><a class="tabref" href="#tabs-2" rel="tomcatTab_showServerChart.do?entity_id=<s:property value='entity_id' />&kpi_type=resource" >资源</a></li>
			<li><a class="tabref" href="#tabs-3" rel="tomcatTab_showServerChart.do?entity_id=<s:property value='entity_id' />&kpi_type=response" >响应</a></li>
			<li><a class="tabref" href="#tabs-4" rel="tomcatTab_showServerChart.do?entity_id=<s:property value='entity_id' />&kpi_type=exception" >异常</a></li>
			<li><a class="tabref" href="#tabs-5" rel="tomcatTab_showServerChart.do?entity_id=<s:property value='entity_id' />&kpi_type=capacity" >容量</a></li>
		</s:elseif>
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
