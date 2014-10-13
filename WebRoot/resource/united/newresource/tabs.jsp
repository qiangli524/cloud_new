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
    <link href="<%=request.getContextPath()%>/sxcloud/images/nresources/ziyuan-css.css" rel="stylesheet" type="text/css" />


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
            	$(tab).find("iframe").height($(window).height()-50); 
        	} 
            return false; 
        } 
       
	});
	</script>
</head>
<body>
<div class="demo">
	<s:hidden name="type"></s:hidden>
<div id="tabs" style="position:absolute;bottom:2px;top:2px;left:5px;right:5px;overflow-y:auto;">
	<ul>
	    <s:if test="type==0"> <!-- 0.0  -->
		<li><a href="resourceOutline_computerResourcePie.do">计算资源</a></li>
		<li><a class="tabref" href="#tabs-1" rel="resourceOutline_storeSanAndNas.do" >存储资源</a></li>
		<li><a class="tabref" href="#tabs-2" rel="resourceOutline_showNetInfo.do">网络资源</a></li>
		</s:if>
		<s:if test="type==1"> <!-- 0.1  -->
		<li><a href="resourceOutline_listBusiCalcResUsage.do">计算资源</a></li>
		<%--<li><a class="tabref" href="#tabs-3" rel="resourceOutline_listHostTopN.do?resourceType=3" >存储资源</a></li>
		--%></s:if>
		<s:if test="type==3"> <!-- 1.0  -->
		<li><a href="resourceOutline_showResourceDetail.do">计算资源</a></li>
		<li><a class="tabref" href="#tabs-4" rel="resourceOutline_showResourceDetail.do?resourceType=3" >存储资源</a></li>
		</s:if>
		<s:if test="type==4"> <!-- 1.1  -->
		<li><a href="resourceOutline_goToBusiTopN.do?resourceType=1">计算资源</a></li>
		<li><a class="tabref" href="#tabs-5" rel="resourceOutline_goToBusiTopN.do?resourceType=2" >存储资源</a></li>
		</s:if>
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
    
</div>

</div><!-- End demo -->
</body>
