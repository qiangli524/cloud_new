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
	<s:hidden name="parent_id"></s:hidden>
	<s:hidden name="uuid"></s:hidden>
<div id="tabs" style="position:absolute;bottom:2px;top:2px;left:5px;right:5px;overflow-y:auto;">
	<ul>
		<s:if test="type==0"> <!-- IBM小型机 -->
			<li><a href="ibmtab_showFirstPage.do" >摘要</a></li>
		</s:if>
		<s:elseif test="type==10"><!-- 小型机 -->
			<li>
				<a href="ibmtab_showMinPage.do?id=<s:property value='id'/>&parent_id=<s:property value='parent_id'/>">摘要</a>
			</li>
		</s:elseif>
		<s:elseif test="type==20"><!-- x86 -->
			<li>
				<a href="ibmtab_showCloudOSPage.do?id=<s:property value='id'/>&parent_id=<s:property value='parent_id'/>">摘要</a>
			</li>
		</s:elseif>
		<s:elseif test="type==1 && vtype==0"><!-- hmc -->
			<li>
				<a href="ibmtab_showHMCPage.do?id=<s:property value='id'/>&uuid=<s:property value='uuid'/>&parent_id=<s:property value='parent_id'/>">摘要</a>
			</li>
		</s:elseif>
		<s:elseif test="type==2 && vtype==0"><!-- power -->
			<li>
				<a href="ibmtab_powerInfo.do?id=<s:property value='id'/>&uuid=<s:property value='uuid'/>&name=<s:property value='name'/>&parent_id=<s:property value='parent_id'/>" >摘要</a>
			</li>
			<li><a class="tabref" href="#tabs-1" rel="ibmtab_showCpuView.do?id=<s:property value='id'/>&uuid=<s:property value="uuid"/>&parent_id=<s:property value='parent_id'/>&type=<s:property value='type'/>&collType=1">CPU</a></li>
			<li><a class="tabref" href="#tabs-2" rel="ibmtab_showMemView.do?id=<s:property value='id'/>&uuid=<s:property value="uuid"/>&parent_id=<s:property value='parent_id'/>&type=<s:property value='type'/>&collType=2">内存</a></li>
			<li><a class="tabref" href="#tabs-3" rel="ibmtab_showPowerFCSpeed.do?id=<s:property value='id'/>&uuid=<s:property value="uuid"/>&parent_id=<s:property value='parent_id'/>&type=<s:property value='type'/>">光纤卡流量信息</a></li>
			<li><a class="tabref" href="#tabs-4" rel="ibmtab_showPowerSEASpeed.do?id=<s:property value='id'/>&uuid=<s:property value="uuid"/>&parent_id=<s:property value='parent_id'/>&type=<s:property value='type'/>">网络性能信息</a></li>
			<li><a class="tabref" href="#tabs-5" rel="ibmtab_showPowerNetCardPerformance.do?id=<s:property value='id'/>&uuid=<s:property value="uuid"/>&parent_id=<s:property value='parent_id'/>&type=<s:property value='type'/>">网卡流量监控</a></li>
		</s:elseif>
		<s:elseif test="type==3 && vtype==0"><!-- lpar -->
			<li><a href="ibmtab_lparInfo.do?id=<s:property value='id'/>&uuid=<s:property value='uuid'/>&name=<s:property value='name'/>&parent_id=<s:property value='parent_id'/>">摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="ibmtab_showCpuView.do?id=<s:property value='id'/>&uuid=<s:property value="uuid"/>&parent_id=<s:property value='parent_id'/>&type=<s:property value='type'/>&collType=1">CPU</a></li>
			<li><a class="tabref" href="#tabs-2" rel="ibmtab_showMemView.do?id=<s:property value='id'/>&uuid=<s:property value="uuid"/>&parent_id=<s:property value='parent_id'/>&type=<s:property value='type'/>&collType=2">内存</a></li>
			<li><a class="tabref" href="#tabs-3" rel="ibmtab_showLparFCSpeed.do?id=<s:property value='id'/>&uuid=<s:property value="uuid"/>&parent_id=<s:property value='parent_id'/>&type=<s:property value='type'/>">光纤卡的读写速度</a></li>
			<li><a class="tabref" href="#tabs-4" rel="ibmtab_showLparDiskSpeed.do?id=<s:property value='id'/>&uuid=<s:property value="uuid"/>&parent_id=<s:property value='parent_id'/>&type=<s:property value='type'/>">磁盘的读写速度</a></li>
			<li><a class="tabref" href="#tabs-5" rel="ibmtab_showLparNetCardPerformance.do?id=<s:property value='id'/>&uuid=<s:property value="uuid"/>&parent_id=<s:property value='parent_id'/>&type=<s:property value='type'/>">网卡流量监控</a></li>
		</s:elseif>
		<s:elseif test="type==4 && vtype==1"><!--  集群 -->
			<li>
				<a href="ibmtab_showClusterInfo.do?id=<s:property value='id'/>&parent_id=<s:property value='parent_id'/>">摘要</a>
			</li>
		</s:elseif>
		<s:elseif test="type==5 && vtype==1"><!-- 主机 -->
			<li>
				<a href="ibmtab_showHostInfo.do?id=<s:property value='id'/>&uuid=<s:property value='uuid'/>&name=<s:property value='name'/>&parent_id=<s:property value='parent_id'/>" >摘要</a>
			</li>
			<li><a class="tabref" href="#tabs-1" rel="hostMonitor_hostMonitorData_f.do?kpi=PM-H-01-010-11&id=<s:property value="uuid"/>&motionName=CPU">CPU</a></li>
			<li><a class="tabref" href="#tabs-2" rel="hostMonitor_hostMonitorData_f.do?kpi=PM-H-01-010-12&id=<s:property value="uuid"/>&motionName=MEMORY" >内存</a></li>
			<li><a class="tabref" href="#tabs-3" rel="hostMonitor_hostMonitorDNData_fusion.do?id=<s:property value='uuid'/>&parent_id=<s:property value='parent_id'/>&motionName=DISK">I/O</a></li>
			<li><a class="tabref" href="#tabs-4" rel="hostMonitor_hostMonitorDNData_fusion.do?id=<s:property value='uuid'/>&parent_id=<s:property value='parent_id'/>&motionName=NET">网络</a></li>
		</s:elseif>
		<s:elseif test="type==6 && vtype==1"><!-- 虚拟机 -->
			<li><a href="ibmtab_showVMInfo.do?id=<s:property value='id'/>&uuid=<s:property value='uuid'/>&name=<s:property value='name'/>&parent_id=<s:property value='parent_id'/>">摘要</a></li>
			<li><a class="tabref" href="#tabs-1" rel="hyMonitor_hyMonitorData_f.do?kpi=PM-V-01-010-11&hyId=<s:property value="uuid"/>&motionName=CPU" >CPU</a></li>
			<li><a class="tabref" href="#tabs-2" rel="hyMonitor_hyMonitorData_f.do?kpi=PM-V-01-010-12&hyId=<s:property value="uuid"/>&motionName=MEMORY">内存</a></li>
			<li><a class="tabref" href="#tabs-3" rel="hyMonitor_hyMonitorDNData_fusion.do?hyId=<s:property value='uuid'/>&parent_id=<s:property value='parent_id'/>&motionName=DISK">I/O</a></li>
			<li><a class="tabref" href="#tabs-4" rel="hyMonitor_hyMonitorDNData_fusion.do?hyId=<s:property value='uuid'/>&parent_id=<s:property value='parent_id'/>&motionName=NET">网络</a></li>
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
    
</div>

</div><!-- End demo -->
</body>
