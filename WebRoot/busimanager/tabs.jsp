<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String id = (String)request.getAttribute("id");
    String entity_id = (String)request.getAttribute("entity_id");
    String connect_id = (String)request.getAttribute("connect_id");
	String type= (String)request.getAttribute("type");
%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />  
<meta http-equiv="expires" content="0" />
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/ui/jquery.ui.core.js"></script>
	<script src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/ui/jquery.ui.widget.js"></script>
	<script src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/ui/jquery.ui.tabs.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/js/jqueryui/demos.css" />
	<script type="text/javascript">
		var jQuery_1_7_2 = $.noConflict(true);
	</script>
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
	(function($) {
		$(document).ready(function() { 
			$( "#tabs" ).tabs({
				ajaxOptions: {
					cache:false,
					async:true,	
					error: function( xhr, status, index, anchor ) {
			//			$( anchor.hash ).html(
			//				"数据加载失败,请重试！");
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
	        
	        $("#tabs a:eq(0)").trigger("click");
		});
	})(jQuery_1_7_2);
	</script>
</head>
<body>
	<div class="demo">

<div id="tabs" style="position:absolute;bottom:2px;top:2px;left:5px;right:5px;overflow-y:auto;">
	<ul>
		<s:if test="type==0"> <!-- 根节点 -->
			<li><a href="abstractinfo_getAllAbstractInfo.do?id=<%=id%>" >摘要</a></li>
<%--		<li><a href="busitree_busiCenterInfo.do?id=<%=id%>" >告警</a></li>--%>
		</s:if>
		<s:elseif test="type==1"><!-- 业务系统 -->
		    <li><a href="abstractinfo_getFirstAbstractInfo.do?id=<%=id%>" >摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="bmanager_sysVmTopN.do?id=<%=id%>" >TOPN</a></li>
		</s:elseif>
		<s:elseif test="type==2"><!-- 业务子系统-->
		    <li><a href="abstractinfo_getSecondAbstractInfo.do?id=<%=id%>" >摘要</a></li>
		<li><a class="tabref" href="#tabs-1" rel="bmanager_vmTopN.do?id=<%=id%>" >TOPN</a></li>
		<li><a class="tabref" href="#tabs-2" rel="workorder/workorder_listWorkOrderForTabs.do?id=<%=id%>" >资源工单</a></li>
		</s:elseif>
		<s:elseif test="type==3"><!-- 虚拟机 -->
		    <li><a href="abstractinfo_vmInfo.do?id=<%=id%>&entity_id=<%=entity_id%>&connect_id=<%=connect_id%>" >摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="hyMonitor_hyMonitorData_f.do?kpi=PM-V-01-010-11&hyId=<s:property value="monitor_id"/>&motionName=CPU" >CPU</a></li>
			<li><a class="tabref" href="#tabs-2" rel="hyMonitor_hyMonitorData_f.do?kpi=PM-V-01-010-12&hyId=<s:property value="monitor_id"/>&motionName=MEMORY">内存</a></li>
			<li><a class="tabref" href="#tabs-3" rel="hyMonitor_hyMonitorDNData_fusion.do?hyId=<s:property value="monitor_id"/>&motionName=DISK">磁盘</a></li>
<%--		<li><a href="busitree_bizsysInfo.do?id=<%=id%>" >业务介绍</a></li>--%>
		</s:elseif>
		<s:elseif test="type==4"><!-- 承载业务 -->
		    <li><a href="abstractinfo_businessInfo.do?id=<%=id%>" >摘要</a></li>
		</s:elseif>
		<s:elseif test="type==5"><!-- 物理主机 -->
			<%-- 
		    <li><a href="abstractinfo_vmInfo.do?id=<%=id%>&entity_id=<%=entity_id%>&connect_id=<%=connect_id%>" >摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="hyMonitor_hyMonitorData_f.do?kpi=PM-V-01-010-11&hyId=<s:property value="monitor_id"/>&motionName=CPU" >CPU</a></li>
			<li><a class="tabref" href="#tabs-2" rel="hyMonitor_hyMonitorData_f.do?kpi=PM-V-01-010-12&hyId=<s:property value="monitor_id"/>&motionName=MEMORY">内存</a></li>
			<li><a class="tabref" href="#tabs-3" rel="hyMonitor_hyMonitorDNData_fusion.do?hyId=<s:property value="monitor_id"/>&motionName=DISK">磁盘</a></li>
			--%>
			<li><a href="hostMonitor_showHostMonitorPage.do?id=<s:property value="connect_id"/>">摘要</a></li>
			<li><a href="hostMonitor_hostMonitorData_f.do?kpi=PM-H-01-010-11&id=<s:property value="connect_id"/>&motionName=CPU">CPU</a></li>
			<li><a href="hostMonitor_hostMonitorData_f.do?kpi=PM-H-01-010-12&id=<s:property value="connect_id"/>&motionName=MEMORY">内存</a></li>
			<li><a href="hostMonitor_hostMonitorDNData_fusion.do?id=<s:property value="connect_id"/>&motionName=DISK">磁盘</a></li>
			<li><a href="hostMonitor_hostMonitorDNData_fusion.do?id=<s:property value="connect_id"/>&motionName=NET">网络</a></li>
		</s:elseif>		
	</ul>
	<!-- tab switching -->
	<div id="tabs-0">
    </div>
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
