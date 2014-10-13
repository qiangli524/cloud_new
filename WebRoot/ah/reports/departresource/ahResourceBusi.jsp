<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<script type="text/javascript">
	$(function(){
		insertFusionChartData();
	})
	
	function insertFusionChartData(){
		$("#chartarea").insertFusionCharts({
          	swfUrl: "FusionCharts/MSBar3D.swf", 
            dataSource: "newdepartresource_getChartInfoSyncByCenter.do",
            dataFormat: "jsonurl", 
            width: "500", 
            height: "370", 
            id: "sdfsdfdfs"
      }); 
	}
</script>
</head>
<body>

<s:form action="" method="post"  id="theForm">
<table border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td align="left" valign="top" class="panel-gj">
    	<!-- 部门预算分配走势 -->
    	<h2 class="resTrend"></h2>
    		<a href="javascript:;" name="resTrend" >
	    		<div id="chartarea" align="center">
	    			FusionCharts
	    		</div>
    		</a>
    </td>
  </tr>
</table>
</s:form>
</body>
