<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
  	String ID =	(String)request.getAttribute("ID");
	String hostId = (String)request.getAttribute("hostId");
	String oper = (String)request.getAttribute("oper");
	String id = (String)request.getAttribute("id");
	String type= (String)request.getAttribute("type");
	String name = (String)request.getAttribute("name");
	String parentName = (String)request.getAttribute("parentName");
	String tempId = (String)request.getAttribute("tempId");
	String pool_uuid = (String)request.getAttribute("pool_uuid");
	String host_uuid = (String)request.getAttribute("host_uuid");
	String vm_uuid = (String)request.getAttribute("vm_uuid");
%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />  
<meta http-equiv="expires" content="0" />
<head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="<%=request.getContextPath()%>/cloud3/charts/jquery/jquery-1.8.2-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/cloud3/charts/highcharts/highcharts.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.core.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.widget.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.tabs.js"></script>
	<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
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
	var type = '<%=(String)request.getAttribute("type")%>';
	$(function() {
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
			/*
			,
			create: function (event,ui) {
				if(type==27){//xen虚拟机
					mask();
				}
				if(type==29){//集群摘要
					mask();
				}
				if(type==28){//xen镜像
					mask();
				}
				if(type==26){//xen主机
					mask();
				}
				if(type==35){//xen存储
					mask();
				}
			},
			select: function (event,ui) {
				
				if(type==26){//xen主机
					if(ui.index==0 ||ui.index==1||ui.index==4 ){
						mask();
					}
				}

				if(type==29){//集群摘要
					if(ui.index==0 ||ui.index==1){
						mask();
					}
				}
				if(type==27){//xen虚拟化机
					if(ui.index==0||ui.index==3){
						mask();
					}
				}

				if(type==28){//xen镜像
					if(ui.index==0 ||ui.index==1 ){
						mask();
					}
				}
				
				if(type==35){//xen存储
					if(ui.index==0 ||ui.index==1){
						mask();
					}
				}
			},
			load: function(event, ui) {
				if(type==27){//xen虚拟机
					if(ui.index==0||ui.index==3){
						removeMask();
					}
				}
				if(type==29){//集群摘要
					if(ui.index==0 ||ui.index==1){
						removeMask();
					}
				}
				if(type==26){//xen主机
					if(ui.index==0 || ui.index==1|| ui.index==4 ){
						removeMask();
					}
				}
				if(type==28||ui.index==1 ){//xen镜像
					removeMask();
				}
				
				if(type==35){//xen存储
					if(ui.index==0 || ui.index==1){
						removeMask();
					}
				}
				$('a', ui.panel).click(function() {
	                $(ui.panel).load(this.href);    
	                return false;    
             	});
			}
		});
		*/
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

<div id="tabs" style="width: 830px;height: 450px;">
	<ul>
		<s:if test="#request.type==34"> <!-- xen数据中心 -->
			<li><a href="xentree_dataCenterInfo.do?id=<%=id%>" >摘要</a></li>
<%--			<li><a href="tree_dataCenterVM.do?type=<%=type%>&id=<%=id%>" >虚拟机</a></li>--%>
<%--			<li><a href="tree_dataCenterHost.do?type=<%=type%>&id=<%=id%>">主机</a></li>--%>
		<%-- 	<li><a href="tree_listAlarm.do">警报</a></li> --%>
		</s:if>
		<s:elseif test="#request.type==27"><!-- xen虚拟机 -->
			<li><a href="xen_listVMInfo.do?entity_id=<%=id%>&oper=0&pool_uuid=<%=pool_uuid%>&host_uuid=<%=host_uuid%>&type=<%=type %>" >摘要</a></li>
			<li><a href="charts_vmMonitorData.do?kpi=PM-V-01-010-11&hyid=<%=vm_uuid%>&motionName=CPU">CPU</a></li>
			<li><a href="charts_vmMonitorData.do?kpi=PM-V-01-010-12&hyid=<%=vm_uuid%>&motionName=MEMORY">内存</a></li>
			<li><a href="xen_listVmVdis.do?entity_id=<%=id%>&pool_uuid=<%=pool_uuid%>&host_uuid=<%=host_uuid%>" >存储</a></li>
			<li><a href="xen_listVmNetworks.do?entity_id=<%=id%>&pool_uuid=<%=pool_uuid%>&host_uuid=<%=host_uuid%>">网络连接</a></li>
			<li><a href="xentree_hostTask.do?type=<%=type%>&name=<%=name%>&entity_id=<%=id%>" >任务</a></li>
			<li><a href="xentree_hostEvent.do?type=<%=type%>&name=<%=name%>&entity_id=<%=id%>" >事件</a></li>
		</s:elseif>
		<s:elseif test="#request.type==29"><!-- xen集群 -->
		<!--  
			<li><a href="xentree_clusterHost.do?type=<%=type%>&id=<%=id%>&pool_uuid=<%=pool_uuid%>" >搜索</a></li>
		-->
			<li><a href="xentree_clusterInfo.do?id=<%=id%>">摘要</a></li>
<%--    		<li><a href="xen_clusterVM.do?id=<%=id%>&pool_uuid=<%=pool_uuid%>&type=<%=type%>" >虚拟机</a></li>--%>
		</s:elseif>
		<s:elseif test="#request.type==26"><!-- xen主机 -->
<!-- 			<li><a href="xen_hostVM.do?id=<%=tempId%>&pool_uuid=<%=pool_uuid%>&host_uuid=<%=host_uuid %>&name=<%=name%>" >搜索</a></li> -->
			<li><a href="xen_listHostInfo.do?entity_id=<%=id%>&pool_uuid=<%=pool_uuid%>&host_uuid=<%=host_uuid%>" >摘要</a></li>
			<li><a href="charts_hostMonitorData.do?kpi=PM-H-01-010-11&eqid=<%=host_uuid%>&motionName=CPU">CPU</a></li>
			<li><a href="charts_hostMonitorData.do?kpi=PM-H-01-010-12&eqid=<%=host_uuid%>&motionName=MEMORY">内存</a></li>
			<li><a href="xen_listStorage.do?entity_id=<%=id%>&pool_uuid=<%=pool_uuid%>&host_uuid=<%=host_uuid%>" >存储</a></li>
			<li><a href="xen_listNetworks.do?entity_id=<%=id%>&pool_uuid=<%=pool_uuid%>&host_uuid=<%=host_uuid%>" >网络连接</a></li>
			<li><a href="xen_listNics.do?entity_id=<%=id%>&pool_uuid=<%=pool_uuid%>&host_uuid=<%=host_uuid%>" >NIC</a></li>
			<li><a href="xentree_hostTask.do?type=<%=type%>&name=<%=name%>&entity_id=<%=id%>" >任务</a></li>
			<li><a href="xentree_hostEvent.do?type=<%=type%>&name=<%=name%>&entity_id=<%=id%>" >事件</a></li>
		</s:elseif>
		<s:elseif test="#request.type==28"><!-- xen模板 -->
			<li><a href="xen_listVMInfo.do?entity_id=<%=id%>&pool_uuid=<%=pool_uuid%>&host_uuid=<%=host_uuid%>&oper=0&type=<%=type%>" >摘要</a></li>
			<li><a href="xen_listVmVdis.do?entity_id=<%=id%>&pool_uuid=<%=pool_uuid%>&host_uuid=<%=host_uuid%>" >存储</a></li>
			<li><a href="xentree_hostTask.do?type=<%=type%>&name=<%=name%>&entity_id=<%=id%>" >任务</a></li>
			<li><a href="xentree_hostEvent.do?type=<%=type%>&name=<%=name%>&entity_id=<%=id%>" >事件</a></li>
		</s:elseif>
		<s:elseif test="#request.type==35"><!-- xen存储 -->
			<li><a href="xen_listStoreInfo.do?entity_id=<%=id%>">摘要</a></li>
			<li><a href="xen_listStoreVdi.do?entity_id=<%=id%>&pool_uuid=<%=pool_uuid%>&host_uuid=<%=host_uuid%>" >存储</a></li>
			<li><a href="xentree_hostEvent.do?type=<%=type%>&name=<%=name%>&entity_id=<%=id%>" >事件</a></li>
		</s:elseif>
	</ul>
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

</div>
</body>
