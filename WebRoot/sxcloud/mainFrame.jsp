<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="common/taglib.jsp" %>

<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/contextmenu.r2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<%--<title>思特奇云管理平台</title>--%>
<title>重庆移动云资源池管理</title>
<script type="text/javascript">
function extendIframe() {
}
function muneView(){
	$(".left_nav dl dd").removeClass("current");
	$("#工单查看").addClass("current")
}
</script>
</head>
<body>
	<%@ include file="top.jsp"%>
  <input type="hidden" id="showBS"/>
</body>
