<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%
	String id = (String)request.getAttribute("id");
	String type= (String)request.getAttribute("type");
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
	<script>
	$(function() {
		$( "#tabs" ).tabs({
			ajaxOptions: {
				cache:false,	
				error: function( xhr, status, index, anchor ) {
					$( anchor.hash ).html(
						"Couldn't load this tab. We'll try to fix this as soon as possible. " +
						"If this wouldn't be a demo." );
				}
			}
		});
	});
	</script>
</head>
<body>
	<div class="demo">

<div id="tabs" >
	<ul>
		<s:if test="#request.type==3"> <!-- 应用 -->
			<li><a href="busitree_appInfo.do?id=<%=id%>" >摘要</a></li>
			<li><a href="busitree_softwareInfo.do?type=<%=type%>&id=<%=id%>" >软件包</a></li>
			<li><a href="database_listDataBase.do?type=<%=type%>&id=<%=id%>">数据库</a></li>
		</s:if>
		<s:elseif test="#request.type==2"><!-- 业务系统 -->
			<li><a href="busitree_bizsysInfo.do?id=<%=id%>" >摘要</a></li>
			<li><a href="busitree_bizsysAppInfo.do?id=<%=id%>" >应用</a></li>
		</s:elseif>
	</ul>
</div>

</div><!-- End demo -->
</body>
