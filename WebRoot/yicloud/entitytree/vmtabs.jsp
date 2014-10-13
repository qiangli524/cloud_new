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
			/*
			,
			create: function (event,ui) {
				if(type==0){
					mask();
				}
				if(type==1){
					mask();
				}
			},
			select: function (event,ui) {
				if(type==1){
					if(ui.index==0 || ui.index==1 || ui.index==2 || ui.index==3 ||ui.index==4 ||ui.index==5 ||ui.index==6){
						mask();
					}
				}
				if(type==25){
					if(ui.index==2 || ui.index==3){
						mask();
					}
				}
				if(type==0){
					if(ui.index==0 || ui.index==1 || ui.index==2 || ui.index==3 ||ui.index==4 ||ui.index==5){
						mask();
					}
				}
				
			},
			load: function(event, ui) {
				if(type==1){
					if(ui.index==0 || ui.index==1 || ui.index==2 || ui.index==3 ||ui.index==4 ||ui.index==5 ||ui.index==6){
						removeMask();
					}
				}
				if(type==25){
					if(ui.index==2 || ui.index==3){
						removeMask();
					}
				}
				if(type==0){
					if(ui.index==0 || ui.index==1 || ui.index==2 || ui.index==3 ||ui.index==4 ||ui.index==5){
						removeMask();
					}
				}
				/*
				if(type==27){//xen虚拟机
					if(ui.index==0){
						removeMask();
					}
				}
				if(type==26){//xen主机
					if(ui.index==0 || ui.index==1){
						removeMask();
					}
				}
				if(type==28){//xen镜像
					removeMask();
				}
				$('a', ui.panel).click(function() {
	                $(ui.panel).load(this.href);  
	               	return false;    
             	});
             	$(ui.panel).delegate('a', 'click', function(event) {
        			$(ui.panel).load(this.href);
        				event.preventDefault();
    			});
			},
			fx: { opacity: 'toggle' }
		});
		*/
		
	</script>
</head>
<body>
	<div class="demo">

<div id="tabs" style="position:fixed;bottom:0px;top:0px;right:0px;left:0px;overflow-x:auto;overflow-x:hidden;">
	<ul>
		<s:if test="#request.type==8"> <!-- 数据中心 -->
			<li><a href="tree_dataCenterInfo.do?id=<%=id%>" >摘要</a></li>
			<!--  
			<li><a href="tree_dataCenterVM.do?type=<%=type%>&id=<%=id%>" >虚拟机</a></li>
			<li><a href="tree_dataCenterHost.do?type=<%=type%>&id=<%=id%>">主机</a></li>
			-->
		<%-- 	<li><a href="tree_listAlarm.do">警报</a></li> --%>
		</s:if>
		<s:elseif test="#request.type==3 || #request.type==21"><!-- 集群 -->
			<li><a href="tree_clusterInfo.do?id=<%=id%>" >摘要</a></li>
		<%--
			<li><a href="tree_clusterVM.do?type=<%=type%>&id=<%=id%>" >虚拟机</a></li>
			<li><a href="tree_clusterHost.do?type=<%=type%>&id=<%=id%>" >主机</a></li>
			-- %>
		<%-- <li><a href="tree_clusterResource.do?type=<%=type%>&id=<%=id%>" >资源分配</a></li> --%>
		</s:elseif>
		<s:elseif test="#request.type==1 || #request.type==25"> <!-- 主机 -->
			<li><a href="tree_hostInfo.do?id=<%=id%>&type=<%=type%>" >摘要</a></li>
			<%--<li><a class="tabref" href="#tabs-2" rel="hostMonitor_hostMonitorData.do?kpi=PM-H-01-010-11&id=<%=id%>&motionName=CPU">CPU</a></li>
			<li><a class="tabref" href="#tabs-3" rel="hostMonitor_hostMonitorData.do?kpi=PM-H-01-010-12&id=<%=id%>&motionName=MEMORY">内存</a></li>
			--%><li><a class="tabref" href="#tabs-4" rel="datastore_listHostDs.do?id=<%=id%>&type=<%=type%>&name=<%=name%>" >数据存储</a></li>
			<li><a class="tabref" href="#tabs-5" rel="tree_hostVM.do?id=<%=tempId%>&type=<%=type%>">虚拟机</a></li>
			<!--  
			<li><a href="tree_hostNic.do?id=<%=id%>" >物理网卡</a></li>
			-->
			<li><a class="tabref" href="#tabs-6" rel="portgroup_listVirtualSwitch.do?host_code=<%=id%>&oper=1" >网络</a></li>
			<%--<li><a class="tabref" href="#tabs-7" rel="tree_hostTask.do?type=<%=type%>&name=<%=id%>" >任务</a></li>--%>
			<li><a class="tabref" href="#tabs-8" rel="tree_hostEvent.do?type=<%=type%>&name=<%=id%>" >事件</a></li>
		</s:elseif>
		<s:elseif test="#request.type==0"><!-- vmware虚拟机 -->
			<li><a href="vmw_vmInfo.do?id=<%=id%>&name=<%=name%>&parentName=<%=parentName%>" >摘要</a></li>
			<%--<li><a href="hyMonitor_hyMonitorData.do?kpi=PM-V-01-010-11&hyId=<%=id%>&motionName=CPU">CPU</a></li>
			<li><a href="hyMonitor_hyMonitorData.do?kpi=PM-V-01-010-12&hyId=<%=id%>&motionName=MEMORY">内存</a></li>
			--%><li><a href="tree_getVMStorage.do?vm_code=<%=id%>" >存储</a></li>
			<li><a href="tree_vmVirtualNic.do?vm_code=<%=id%>" >网络适配器</a></li>
			<%--<li><a href="tree_hostTask.do?type=<%=type%>&name=<%=id%>" >任务</a></li>--%>
			<li><a href="tree_hostEvent.do?type=<%=type%>&name=<%=id%>" >事件</a></li>
		</s:elseif>
		<s:elseif test="#request.type==22 || #request.type==23"><!-- 模板镜像 -->
			<li><a href="tree_imageInfo.do?id=<%=id%>" >摘要</a></li>
		</s:elseif>
		<s:elseif test="#request.type==18"><!-- 存储 -->
			<li><a href="datastore_listDataStore.do?id=<%=id%>">摘要</a></li>
			<li><a href="tree_dataCenterVM.do">虚拟机</a></li>
			<li><a href="tree_dataCenterHost.do">主机</a></li>
		</s:elseif>
		<s:elseif test="#request.type==19"><!-- 网络 -->
			<li><a href="net_listNetNode.do?id=<%=id%>">基本信息</a></li>
			<li><a href="ip_listIpInfo.do?netId=<%=id%>">IP地址</a></li>
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
	<%--
	<s:form action="listHealthState" method="post" cssStyle="theForm"
		id="theForm">
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til" width="30%">
						名称
					</td>
					<td>
						<s:property value="theForm.NAME_ZH" />
					</td>
				</tr>
				<tr>
					<td class="til">
						IP
					</td>
					<td>
						<s:property value="theForm.IP" />
					</td>
				</tr>
				<tr>
					<td class="til">
						IP状态
					</td>
					<td>
						<s:if test="theForm.IP_ISBLOCKED==null">
							暂无信息
						</s:if>
						<s:else>
							<s:if test="theForm.IP_ISBLOCKED==0">
								<img src="sxcloud/images/virtual/open.png" />
							</s:if>
							<s:elseif test="theForm.IP_ISBLOCKED==1">
								<img src="sxcloud/images/virtual/close.png" />
							</s:elseif>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til">
						端口状态
					</td>
					<td>
						<%
							String PORT_ISBLOCKED = (String) request
									.getAttribute("PORT_ISBLOCKED");
							if (PORT_ISBLOCKED != null && !"".equals(PORT_ISBLOCKED)) {
						%>
						<%=request.getAttribute("PORT_ISBLOCKED")%>
						<%
							} else {
						%>
						暂无信息
						<%
							}
						%>
					</td>
				</tr>
				<tr>
					<td class="til">
						CPU利用率
					</td>
					<td>
						<s:if test="theForm.CPU==null">
							暂无信息
						</s:if>
						<s:else>
							<s:property value="theForm.CPU" />%
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til">
						内存利用率
					</td>
					<td>
						<s:if test="theForm.MEMORY==null">
							暂无信息
						</s:if>
						<s:else>
							<s:property value="theForm.MEMORY" />%
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til">
						IO(单位：kbps)
					</td>
					<td>
						<s:if test="theForm.IO==null">
							暂无信息
						</s:if>
						<s:else>
							<s:property value="theForm.IO" />
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til">
						存储空间利用率
					</td>
					<td>
						<s:if test="theForm.STORAGE==null">
							暂无信息
						</s:if>
						<s:else>
							<s:property value="theForm.STORAGE" />%
						</s:else>
					</td>
				</tr>
				<%--  
                   <tr>              
                  <td class="til">
						应用是否存在
					</td>
					<td>
					<logic:equal name="theForm" property="APP_HASDOWN" value="0" >否</logic:equal>
					<logic:equal name="theForm" property="APP_HASDOWN" value="1" >是</logic:equal>                  
					</td>
                   </tr>
                   <tr>              
                  <td class="til">
						中间件是否存在
					</td>
					<td>
					<logic:equal name="theForm" property="MIDDLE_HASDOWN" value="0" >否</logic:equal>
					<logic:equal name="theForm" property="MIDDLE_HASDOWN" value="1" >是</logic:equal>                  
					</td>
                   </tr>
                    <tr>              
                  <td class="til">
						应用名称
					</td>
					<td>
					    <bean:write name="theForm" property="APP_DOWN_NAMES"/>                 
					</td>
                   </tr>
                    <tr>              
                  <td class="til">
						中间件名称
					</td>
					<td>
					    <bean:write name="theForm" property="MIDDLE_DOWN_NAMES"/>                 
					</td>
                   </tr>
			</table>
		</div>
	</s:form>
	--%>
</body>
