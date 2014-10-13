<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%
	String id = (String)request.getAttribute("id");
	String tag = (String)request.getAttribute("tag"); 
%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />  
<meta http-equiv="expires" content="0" />
<head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/jquery-1.7.2.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.core.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.widget.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.tabs.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/demos.css" />
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/ui.js"></script>
	<style type="text/css">
body {
	margin-left: 0px;
}
.show-tab {
	position: relative;
}
.show-tab .show-bg-border {
	
	padding: 0 1px;
}
.show-tab .show-bg {
	
	height: 235px;
	overflow: hidden;
}
.show-tab-4 .tab-caption,.show-tab-4 .tab-caption-2 {
	border-bottom:3px solid #79a723;
	height:32px;
	overflow: visible;
	position:relative;
	
}
.show-tab-4 .tab-caption li,.show-tab-4 .tab-caption-2 li {
	float:left;
	height:32px;
	line-height:32px;
	display:inline;
	margin-right:0px;
}

.show-tab-4 .tab-caption li.first,.show-tab-4 .tab-caption-2 li.first {
	
}
.show-tab-4 .tab-caption li a {
	float:left;
	height:100%;
	padding:0 15px;
	font-size:13px;
	font-weight:bold;
	color:#000;
}
.show-tab-4 .tab-caption-2 li a {
	padding:0 10px;
}

.show-tab-4 .tab-caption li.on ,.show-tab-4 .tab-caption-2 li.on {
	
}
.show-tab-4 .tab-caption li.on a,.show-tab-4 .tab-caption-2 li.on a {
	color: orange;
	
}
.show-tab-4 .tab-content {
	width:100%;
	overflow:hidden;
}
.show-tab-4 .tab-content .box {
	padding:0 0 10px 0;
	border:1px solid #dadada;
	border-top:none;
	background:url(../images/bg-common.gif) left bottom repeat-x;
}
</style>
<script type="text/javascript">
$(document).ready(function(){

	
	//$(".tab-caption li:first").addClass("on");	
	$(".tab-content>div").hide();
	$(".tab-content div.on").show();
	$(".tab-caption li").click(function(){
		var jf=$(this).index();
		$(this).addClass("on").siblings().removeClass("on");
		$(this).parent().next().children("div").hide().eq(jf).show();
		});//登录口tab切换功能
	
	$(".tab-con .box:not(:first)").hide();
	$(".show-tab li").click(function(){
		var intjtk=$(this).index();
		$(this).addClass("on").siblings().removeClass("on");
		$(".tab-con").show();
		$(".tab-con .box").filter(":visible").hide().parent().children().eq(intjtk).show();

		});

	});
</script>
</head>
<body>
<div class="show-tab-4  mgt-10" style="height: 500px;overflow: hidden">
      <div class="tab-caption">
        <ul class="show-tab">
          <li class="first on"><a href="javascript:void(0);" target="xxxx">健康曲线</a></li>
          <li><a href="javascript:void(0);" target="xxxxoooo">告警信息</a></li>
        </ul>
      </div>
<div class="tab-content tab-con">
        <div class="box on">
        	<iframe  src="healthy_getHealthyReport.do?id=<%=id%>&tag=<%=tag%>" name = "xxxx" width="800" height="460" frameborder="0"></iframe>
        </div>
        <div class="box">
       <iframe  src="healthy_getWarnInfo.do?id=<%=id%>&tag=<%=tag%>" name = "xxxxoooo" width="800" height="460" frameborder="0"></iframe>
        </div>
	  </div>
	  </div>
<%----%>
<%--		<div class="demo">--%>
<%----%>
<%--<div id="tabs" style="height: 490px;width: 765px">--%>
<%--	<ul>--%>
<%--		<li><a href="healthy_getHealthyReport.do?id=<%=id%>&tag=<%=tag%>" >健康曲线</a></li>--%>
<%--		<li><a href="healthy_getWarnInfo.do?id=<%=id%>&tag=<%=tag%>" target='xxxx'>告警信息</a></li>--%>
<%--	</ul>--%>
<%--	<iframe id = "ui-tabs-2" src="healthy_getWarnInfo.do?id=<%=id%>&tag=<%=tag%>" name = "xxxx" width="900" height="650" frameborder="0"></iframe>--%>
<%--	</div>--%>
<%--</div><!-- End demo -->--%>
<s:form method="post" id="theForm1">
</s:form>
</body>
