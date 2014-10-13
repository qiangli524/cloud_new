<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />  
<meta http-equiv="expires" content="0" />
<head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/demos.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.core.js"></script>
<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.widget.js"></script>
<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
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
	function wait(){
		if ($("#tabs-1").find("iframe").length == 0) {
			mask('正在监控扫描,请稍后....','0.5','0px');
		}
	}
	</script>
</head>
<body >
<div class="demo">
<div id="tabs" style="position:absolute;bottom:2px;top:2px;left:5px;right:5px;overflow-y:auto;">
	<ul>
		<s:if test="node_type==0"> <!-- 根节点 -->
			<li><a href="paasBusiStatistics_bossResourcePool.do?obj.uuid=<s:property value='uuid' />&obj.node_type=1&obj.server_type=<s:property value='server_type'/>" >摘要</a></li>
		</s:if>
		<s:elseif test="node_type==1"><!-- Boss云化 -->
			<li><a href="paasBusiStatistics_childSysResourcePool.do?obj.uuid=<s:property value='uuid' />&obj.node_type=<s:property value='node_type'/>&obj.server_type=<s:property value='server_type'/>">摘要</a></li>
			<s:if test="entity_id == xxx">
				<li><a class="tabref" href="#tabs-1" rel="zookeeperMonitor_monitorTree.do" onclick="wait();">监控</a></li>
			</s:if>
		</s:elseif>
		<s:elseif test="node_type==2"><!-- 业务（系统）-->
			<li><a href="paasBusiStatistics_businessResourcePool.do?obj.uuid=<s:property value='uuid' />&obj.node_type=<s:property value='node_type'/>&obj.server_type=<s:property value='server_type'/>">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==3 && server_type == 0"><!-- 主机服务 -->
			<li><a href="paasBusiStatistics_serviceResourcePool.do?obj.uuid=<s:property value='uuid' />&obj.node_type=<s:property value='node_type'/>&obj.server_type=<s:property value='server_type'/>">摘要</a></li>
		</s:elseif>
		<s:elseif test="node_type==3 && server_type == 2"><!-- 进程服务 -->
			<li><a href="paasBusiStatistics_serviceResourcePool.do?obj.uuid=<s:property value='uuid' />&obj.node_type=<s:property value='node_type'/>&obj.server_type=<s:property value='server_type'/>">摘要</a></li>
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
    
</div>

</div><!-- End demo -->
</body>
