<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />  
<meta http-equiv="expires" content="0" />
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
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
                font-size:5px; 
            } 
 
            .iframetab { 
                width:100%; 
                height:600px; 
                border:none;
                margin:1px; 
                position:relative; 
                top:2px;
                background：none;
            } 
 
            .ui-tabs-panel { 
                padding:3px !important; 
            } 
 
            .openout { 
                float:right; 
                position:relative; 
                top:-18px; 
                left:-10px; 
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
	        
	        $("#tabs a:eq(0)").trigger("click");
		});
	})(jQuery_1_7_2);
	</script>
</head>
<body>
	<div class="demo">
		<div id="tabs">
			<ul>
				<li>
					<a class="tabref" href="#tabs-1" rel="clusterKpi_viewCpu.do">cpu</a>
				</li>
				<li>
					<a class="tabref" href="#tabs-2" rel="clusterKpi_viewMem.do">内存</a>
				</li>
				<li>
					<a class="tabref" href="#tabs-3" rel="clusterKpi_viewStore.do">存储</a>
				</li>
			</ul>
			
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
</div>
</body>
