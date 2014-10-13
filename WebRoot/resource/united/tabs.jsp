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
	<s:hidden name="id"></s:hidden>
	<s:hidden name="name"></s:hidden>
	<s:hidden name="type"></s:hidden>
	<s:hidden name="vtype"></s:hidden>
	<s:hidden name="connect_id"></s:hidden>
	<s:hidden name="parent_id"></s:hidden>
	<s:hidden name="uuid"></s:hidden>
<div id="tabs" style="position:absolute;bottom:2px;top:2px;left:5px;right:5px;overflow-y:auto;">
	<ul>
		<s:if test="type==0"> <!-- 安徽移动私有云管理平台 -->
			<li><a href="resourcestatistics_showFirstPage.do" >摘要</a></li>
<%--			<li><a href="resourcestatistics_showUnVirtual.do" >非虚拟化</a></li>--%>
		</s:if>
		<s:if test="type == -1"><!-- 地域 -->
			<li>
				<a href="resourcestatistics_showRegionInfo.do?id=<s:property value='id'/>">摘要</a>
			</li>
		</s:if>
		<s:elseif test="type==1 && vtype==1"><!-- 数据中心 -->
			<li>
				<a href="resourcestatistics_showDataCenterInfo.do?id=<s:property value='id'/>">摘要</a>
				
			</li>
			<li><a class="tabref" href="#tabs-5" rel="unitedTab_attchTask.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&type=<s:property value='type'/>" >任务</a></li>
			<li><a class="tabref" href="#tabs-6" rel="unitedTab_attchEvent.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&type=<s:property value='type'/>" >事件</a></li>
			<li>
				<a class="tabref" href="#tabs-1" rel="entityTopN_hostTopN.do?id=<s:property value='id'/>">主机资源使用率</a>
			</li>
			<li>
				<a class="tabref" href="#tabs-2" rel="entityTopN_vmTopN.do?id=<s:property value='id'/>">虚机资源使用率</a>
			</li>
			
			<%--<a href="resourcestatistics_showDataCenterPage.do?id=<s:property value='id'/>" >摘要</a></li>--%>
		</s:elseif>
		<s:elseif test="type==1 && vtype==2"><!-- 数据中心 -->
			<li>
				<a href="resourcestatistics_showDataCenterInfo.do?id=<s:property value='id'/>">摘要</a>
			</li>
			<li>
				<a class="tabref" href="#tabs-1" rel="entityTopN_hostTopN.do?id=<s:property value='id'/>">主机资源使用率</a>
			</li>
			<li>
				<a class="tabref" href="#tabs-2" rel="entityTopN_vmTopN.do?id=<s:property value='id'/>">虚机资源使用率</a>
			</li>
			<%--<a href="resourcestatistics_showDataCenterPage.do?id=<s:property value='id'/>" >摘要</a></li>--%>
		</s:elseif>
		<s:elseif test="type==2 && vtype==1"><!-- 集群-->
			<li>
				<a href="resourcestatistics_showClusterInfo.do?id=<s:property value='id'/>">摘要</a>
				<%--<a href="resourcestatistics_showClusterPage.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>">摘要</a>
			--%></li>
			<li><a class="tabref" href="#tabs-5" rel="unitedTab_attchTask.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&type=<s:property value='type'/>" >任务</a></li>
			<li><a class="tabref" href="#tabs-6" rel="unitedTab_attchEvent.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&type=<s:property value='type'/>" >事件</a></li>
			<li>
				<a class="tabref" href="#tabs-1" rel="entityTopN_hostTopN.do?id=<s:property value='id'/>">主机资源使用率</a>
			</li>
			<li>
				<a class="tabref" href="#tabs-2" rel="entityTopN_vmTopN.do?id=<s:property value='id'/>">虚机资源使用率</a>
			</li>
		</s:elseif>
		<s:elseif test="type==2 && vtype==2"><!-- 集群-->
			<li>
				<a href="resourcestatistics_showClusterInfo.do?id=<s:property value='id'/>">摘要</a>
				<%--<a href="resourcestatistics_showClusterPage.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>">摘要</a>
			--%></li>
			<li>
				<a class="tabref" href="#tabs-1" rel="entityTopN_hostTopN.do?id=<s:property value='id'/>">主机资源使用率</a>
			</li>
			<li>
				<a class="tabref" href="#tabs-2" rel="entityTopN_vmTopN.do?id=<s:property value='id'/>">虚机资源使用率</a>
			</li>
		</s:elseif>
		<s:elseif test="type==3 && vtype==1"><!-- 主机 -->
			<li>
				<a href="resourcestatistics_showHostInfo.do?id=<s:property value='id'/>" >摘要</a>
				<%--<a href="unitedTab_hostInfo.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>" >摘要</a>
			--%></li>
			<li><a class="tabref" href="#tabs-1" rel="hostMonitor_hostMonitorData_f.do?kpi=PM-H-01-010-11&id=<s:property value="monitor_id"/>&motionName=CPU">CPU</a></li>
			<li><a class="tabref" href="#tabs-2" rel="hostMonitor_hostMonitorData_f.do?kpi=PM-H-01-010-12&id=<s:property value="monitor_id"/>&motionName=MEMORY" >内存</a></li>
			<li><a class="tabref" href="#tabs-3" rel="unitedTab_hostStorage.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&vtype=<s:property value='vtype'/>">存储</a></li>
			<%--<li><a class="tabref" href="#tabs-4" rel="hostMonitor_hostMonitorData.do?kpi=PM-H-01-010-15&id=<s:property value="uuid"/>&motionName=NETWORK" >网络</a></li>--%>
			<%-- <li><a class="tabref" href="#tabs-4" rel="unitedTab_getHostPortGroup.do?connect_id=<s:property value='connect_id'/>&uuid=<s:property value='uuid'/>&vtype=<s:property value='vtype'/>" >网络</a></li> --%>
		<%--	<li><a class="tabref" href="#tabs-4" rel="unitedTab_getHostPortGroup.do?connect_id=<s:property value='connect_id'/>&uuid=<s:property value='uuid'/>&vtype=<s:property value='vtype'/>" >网络</a></li>  --%>
			
			<li><a class="tabref" href="#tabs-4" rel="networkvs_vswitchpg.do?connect_id=<s:property value='connect_id'/>&uuid=<s:property value='uuid'/>&vtype=<s:property value='vtype'/>" >网络</a></li>
			
		<%--	<li><a class="tabref" href="#tabs-4" rel="unitedOper_hostVswitch.do?connect_id=<s:property value='connect_id'/>&uuid=<s:property value='uuid'/>&vtype=<s:property value='vtype'/>" >网络</a></li>  --%>		
			<li><a class="tabref" href="#tabs-5" rel="unitedTab_attchTask.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&type=<s:property value='type'/>" >任务</a></li>
			<li><a class="tabref" href="#tabs-6" rel="unitedTab_attchEvent.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&type=<s:property value='type'/>" >事件</a></li>
			<li>
				<a class="tabref" href="#tabs-7" rel="entityTopN_vmTopN.do?id=<s:property value='id'/>">虚机资源使用率</a>
			</li>
		</s:elseif>
		<s:elseif test="type==3 && vtype==2"><!-- 主机 -->
			<li>
				<a href="resourcestatistics_showHostInfo.do?id=<s:property value='id'/>" >摘要</a>
				<%--<a href="unitedTab_hostInfo.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>" >摘要</a>
			--%></li>
			<li><a class="tabref" href="#tabs-1" rel="hostMonitor_hostMonitorData_f.do?kpi=PM-H-01-010-11&id=<s:property value="monitor_id"/>&motionName=CPU">CPU</a></li>
			<li><a class="tabref" href="#tabs-2" rel="hostMonitor_hostMonitorData_f.do?kpi=PM-H-01-010-12&id=<s:property value="monitor_id"/>&motionName=MEMORY" >内存</a></li>
				<li><a class="tabref" href="#tabs-3" rel="unitedTab_hostStorage.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&vtype=<s:property value='vtype'/>">存储</a></li>
			<%--<li><a class="tabref" href="#tabs-4" rel="hostMonitor_hostMonitorData.do?kpi=PM-H-01-010-15&id=<s:property value="uuid"/>&motionName=NETWORK" >网络</a></li>--%>
			<%-- <li><a class="tabref" href="#tabs-4" rel="unitedTab_getHostPortGroup.do?connect_id=<s:property value='connect_id'/>&uuid=<s:property value='uuid'/>&vtype=<s:property value='vtype'/>" >网络</a></li> --%>
			<li><a class="tabref" href="#tabs-4" rel="unitedTab_getHostPortGroup.do?connect_id=<s:property value='connect_id'/>&uuid=<s:property value='uuid'/>&vtype=<s:property value='vtype'/>" >网络</a></li>
		<%-- <li><a class="tabref" href="#tabs-4" rel="unitedOper_hostVswitch.do?connect_id=<s:property value='connect_id'/>&uuid=<s:property value='uuid'/>&vtype=<s:property value='vtype'/>" >网络</a></li>--%>		
			<%-- <li><a class="tabref" href="#tabs-5" rel="unitedTab_attchTask.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&type=<s:property value='type'/>" >任务</a></li> --%>
			<li><a class="tabref" href="#tabs-6" rel="unitedTab_attchLogs.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&type=<s:property value='type'/>&vtype=<s:property value='vtype'/>" >事件</a></li>
			<li>
				<a class="tabref" href="#tabs-7" rel="entityTopN_vmTopN.do?id=<s:property value='id'/>">虚机资源使用率</a>
			</li>
		</s:elseif>
		<s:elseif test="type==4 && vtype==1"><!-- 虚拟机 -->
			<li><a href="unitedTab_vmInfo.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>">摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="hyMonitor_hyMonitorData_f.do?kpi=PM-V-01-010-11&hyId=<s:property value="monitor_id"/>&motionName=CPU" >CPU</a></li>
			<li><a class="tabref" href="#tabs-2" rel="hyMonitor_hyMonitorData_f.do?kpi=PM-V-01-010-12&hyId=<s:property value="monitor_id"/>&motionName=MEMORY">内存</a></li>
			<li><a class="tabref" href="#tabs-3" rel="hyMonitor_hyMonitorDNData_fusion.do?hyId=<s:property value="monitor_id"/>&motionName=DISK">磁盘</a></li>
			<li><a class="tabref" href="#tabs-7" rel="unitedOper_editVMProperties.do?connect_id=<s:property value='connect_id'/>&uuid=<s:property value='uuid'/>&vtype=<s:property value='vtype'/>&parent_uuid=<s:property value='parent_uuid'/>" >硬件</a></li>
			<li><a class="tabref" href="#tabs-4" rel="unitedTab_getNetWork.do?connect_id=<s:property value='connect_id'/>&uuid=<s:property value='uuid'/>&vtype=<s:property value='vtype'/>" >网络</a></li>
			<li><a class="tabref" href="#tabs-5" rel="unitedTab_attchTask.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&type=<s:property value='type'/>" >任务</a></li>
			<li><a class="tabref" href="#tabs-6" rel="unitedTab_attchEvent.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&type=<s:property value='type'/>" >事件</a></li>
		</s:elseif>
		<s:elseif test="type==4 && vtype==2"><!-- 虚拟机 -->
			<li><a href="unitedTab_vmInfo.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>">摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="hyMonitor_hyMonitorData_f.do?kpi=PM-V-01-010-11&hyId=<s:property value="monitor_id"/>&motionName=CPU" >CPU</a></li>
			<li><a class="tabref" href="#tabs-2" rel="hyMonitor_hyMonitorData_f.do?kpi=PM-V-01-010-12&hyId=<s:property value="monitor_id"/>&motionName=MEMORY">内存</a></li>
			<li><a class="tabref" href="#tabs-3" rel="hyMonitor_hyMonitorDNData_fusion.do?hyId=<s:property value="monitor_id"/>&motionName=DISK">磁盘</a></li>
			<li><a class="tabref" href="#tabs-7" rel="unitedOper_listVmVdis.do?connect_id=<s:property value='connect_id'/>&uuid=<s:property value='uuid'/>&vtype=<s:property value='vtype'/>&parent_uuid=<s:property value='parent_uuid'/>" >硬件</a></li>
			<li><a class="tabref" href="#tabs-4" rel="unitedTab_getNetWork.do?connect_id=<s:property value='connect_id'/>&uuid=<s:property value='uuid'/>&vtype=<s:property value='vtype'/>" >网络</a></li>
			<%-- <li><a class="tabref" href="#tabs-5" rel="unitedTab_attchTask.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&type=<s:property value='type'/>" >任务</a></li> --%>
			<li><a class="tabref" href="#tabs-6" rel="unitedTab_attchLogs.do?uuid=<s:property value='uuid'/>&connect_id=<s:property value='connect_id'/>&type=<s:property value='type'/>&vtype=<s:property value='vtype'/>">事件</a></li>
		</s:elseif>
		<s:elseif test="type==1 && vtype==5"><!-- 网络 -->
			<li>
				<a href="resourcestatistics_showNetPage.do?id=<s:property value='id'/>">摘要</a>
			</li>
		</s:elseif>
		<s:elseif test="type==6"><!-- 网络域 -->
			<li>
				<a  href="resourcestatistics_showDomainPage.do?id=<s:property value='id'/>">摘要</a>
			</li>
		</s:elseif>
		<s:elseif test="type==7"><!-- 子域 -->
			<li>
				<a href="resourcestatistics_showSubDomainPage.do?id=<s:property value='id'/>">摘要</a>
			</li>
		</s:elseif>
		<s:elseif test="type==8"><!-- vlan -->
		    <li>
				<a href="resourcestatistics_showSubDomainPageForVlan.do?id=<s:property value='id'/>">摘要</a>
			</li>
			<li>
				<a class="tabref" href="#tabs-1" rel="resourcestatistics_showVlan.do?id=<s:property value="id"/>">IP信息</a>
			</li>
		</s:elseif>
		<s:elseif test="vtype==6 && type==1"><!-- 存储 -->
			<li>
				<a href="resourcestatistics_showStoreCenterPie.do?id=<s:property value='id'/>&type=<s:property value='type'/>">摘要</a>
			</li>
		</s:elseif>
		<s:elseif test="vtype==6 && type==9"><!-- 存储 -->
			<li>
				<a href="resourcestatistics_listStoreDevice.do?id=<s:property value='uuid'/>">摘要</a>
			</li>
<%--			<li>--%>
<%--				<a class="tabref" href="#tabs-1" rel="resourcestatistics_showStorePage.do?">详细</a>--%>
<%--			</li>--%>
		</s:elseif>
		<s:elseif test="type==1 && vtype==7"><!-- 预留数据中心 -->
			<li>
				<a href="resourcestatistics_showDataCenterInfo.do?id=<s:property value='id'/>&vtype=7">摘要</a>
			</li>
		</s:elseif>
		<s:elseif test="type==2 && vtype==7"><!-- 预留集群 -->
			<li>
				<a href="resourcestatistics_showClusterInfo.do?id=<s:property value='id'/>&vtype=7">摘要</a>
			</li>
		</s:elseif>
		<s:elseif test="type==3 && vtype==7"><!-- 预留主机 -->
			<li>
				<a href="resourcestatistics_showHostDetail.do?id=<s:property value='id'/>&connect_id=<s:property value='connect_id'/>&vtype=7">摘要</a>
			</li>
		</s:elseif>
		<s:elseif test="type==1 && vtype==8"><!-- 非虚拟化数据中心 -->
			<li>
				<a href="resourcestatistics_showDataCenterInfo.do?id=<s:property value='id'/>&vtype=8">摘要</a>
			</li>
		</s:elseif>
		<s:elseif test="type==2 && vtype==8"><!-- 非虚拟化集群 -->
			<li>
				<a href="resourcestatistics_showClusterInfo.do?id=<s:property value='id'/>&vtype=8">摘要</a>
			</li>
		</s:elseif>
		<s:elseif test="type==3 && vtype==8"><!-- 非虚拟化主机 -->
			<li><a href="hostMonitor_showHostMonitorPage.do?id=<s:property value="connect_id"/>">摘要</a></li>
			<li><a href="hostMonitor_hostMonitorData_f.do?kpi=PM-H-01-010-11&id=<s:property value="connect_id"/>&motionName=CPU">CPU</a></li>
			<li><a href="hostMonitor_hostMonitorData_f.do?kpi=PM-H-01-010-12&id=<s:property value="connect_id"/>&motionName=MEMORY">内存</a></li>
			<li><a href="hostMonitor_hostMonitorDNData_fusion.do?id=<s:property value="connect_id"/>&motionName=DISK">磁盘</a></li>
			<li><a href="hostMonitor_hostMonitorDNData_fusion.do?id=<s:property value="connect_id"/>&motionName=NET">网络</a></li>
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
