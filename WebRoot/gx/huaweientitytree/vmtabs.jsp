<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String id = (String)request.getAttribute("id");
	String type= (String)request.getAttribute("type");
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
	</script>
</head>
<body>
	<div class="demo">

<div id="tabs" style="position:fixed;bottom:0px;top:0px;right:0px;left:0px;overflow-x:auto;overflow-x:hidden;">
	<ul>
		<s:if test="#request.type==1"> <!-- 数据中心 -->
			<li><a href="huaweientitytree_dataCenterInfo.do?id=<%=id%>" >摘要</a></li>
		</s:if>
		<s:elseif test="#request.type==2"><!-- 集群 -->
			<li><a href="huaweientitytree_clusterInfo.do?id=<%=id%>" >摘要</a></li>
		</s:elseif>
		<s:elseif test="#request.type==3"> <!-- 主机 -->
			<li><a href="huaweientitytree_hostInfo.do?id=<%=id%>" >摘要</a></li>
		</s:elseif>
		<s:elseif test="#request.type==4 || #request.type==5"><!-- vmware虚拟机/模板 -->
			<li><a href="huaweientitytree_vmInfo.do?id=<%=id%>&type=<%=type%>" >摘要</a></li>
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

</body>
