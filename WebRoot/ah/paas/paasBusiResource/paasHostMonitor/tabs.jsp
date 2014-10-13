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
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
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
		<%--<li><a href="paasHostMonitor_showHostMonitorPage.do?id=<s:property value="id"/>">摘要</a></li>
		<li><a class="tabref" href="#tabs-1" rel="paasHostMonitor_hostMonitorData_f.do?kpi=PM-H-01-010-11&id=<s:property value="id"/>&motionName=CPU">CPU</a></li>--%>
		
		<li><a href="paasHostMonitor_hostMonitorData_f.do?kpi=PM-V-01-010-11&id=<s:property value="id"/>&eq_type=<s:property value="eq_type"/>&motionName=CPU">CPU</a></li>
		<li><a class="tabref" href="#tabs-1" rel="paasHostMonitor_hostMonitorData_f.do?kpi=PM-V-01-010-12&id=<s:property value="id"/>&eq_type=<s:property value="eq_type"/>&motionName=MEMORY">内存</a></li>
		<%--
		<li><a class="tabref" href="#tabs-3" rel="paasHostMonitor_hostMonitorDNData_fusion.do?id=<s:property value="id"/>&eq_type=<s:property value="eq_type"/>&motionName=DISK">磁盘</a></li>
		<li><a class="tabref" href="#tabs-4" rel="paasHostMonitor_hostMonitorDNData_fusion.do?id=<s:property value="id"/>&eq_type=<s:property value="eq_type"/>&motionName=NET">网络</a></li>
		--%>
		<li><a class="tabref" href="#tabs-2" rel="paasHostMonitor_queryChartFileSystemDayDataView.do?id=<s:property value="id"/>&eq_type=<s:property value="eq_type"/>&motionName=File">文件系统</a></li>
		<li><a class="tabref" href="#tabs-3" rel="paasHostMonitor_toSwapPage.do?kpi=PM-H-01-010-22&id=<s:property value="id"/>&eq_type=<s:property value="eq_type"/>&motionName=SWAP">交换区</a></li>
	</ul>
	<div id="tabs-1">
    </div>
    <div id="tabs-2"> 
    </div> 
    <div id="tabs-3"> 
    </div> 
    <!-- <div id="tabs-4"> 
    </div> 
    <div id="tabs-5"> 
    </div> 
    <div id="tabs-6"> 
    </div>--> 
</div>
</div>
</body>