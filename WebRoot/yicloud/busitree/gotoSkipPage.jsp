<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<title></title>
<script type="text/javascript">
$(function(){
	var id = '<%=request.getParameter("id") %>';
	var type = '<%=request.getParameter("type") %>';
	var entityId = '<%=request.getParameter("entityId") %>';
	var hostIP = '<%=request.getParameter("hostIP") %>';
	var url = '<%=request.getParameter("url") %>'
	if(id == 'null'){id = "";}
	if(type == 'null'){type = "";}
	if(entityId == 'null'){entityId = "";}
	if(hostIP == 'null'){hostIP = "";}
	var newURL = url + "?id=" + id + "&type=" + type +"&entityId=" + entityId + "&hostIP=" + hostIP;
	$("#skipIframe").attr("src",newURL);
});

</script>
</head>
<body>
    <s:form action="alarmt_listDeployAlarm" method="post" cssStyle="theForm" id="theForm">
		<div id="deployAlarm">
			<iframe id="skipIframe" src="" name="deployAlarmFrame" height="100%" width="100%" frameborder="0"></iframe>
		</div>
	</s:form>
</body>
</html:html>
