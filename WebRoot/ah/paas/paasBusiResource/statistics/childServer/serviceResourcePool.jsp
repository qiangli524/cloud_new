<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/ah/paas/statistics/css/statistics.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
</head>
<body class="pop-body scrollbody">
		<s:hidden name="obj.uuid"></s:hidden>
		<s:hidden name="obj.server_type"></s:hidden>
		<div class="mainboxBus" style="width: 100%">
			<div class="item" style="width: 97.4%;height: 439px;">
	  			<div class="top">
	  				<div class="ml5"><div class="leftt hostimg"></div><font color="orange" size="3">物理主机</font></div>
	  			</div>
	  			<div style="width:97.4%;height:90%">
					<iframe style="width: 100%;height:100%" src="paasBusiStatistics_hostsList.do?obj.server_type=<s:property value='obj.server_type'/>&obj.uuid=<s:property value='obj.uuid'/>" name="host" frameborder="0">Load Failed?</iframe>
				</div>
	  		</div>
	  		<div class="item" style="width: 97.4%;height: 439px;">
	  			<div class="top">
	  				<div class="ml5"><div class="leftt vmimg"></div><font color="orange" size="3">虚拟机</font></div>
	  			</div>
	  			<div style="width:97.4%;height:90%">
					<iframe style="width: 100%;height:100%" src="paasBusiStatistics_vmHostsList.do?obj.server_type=<s:property value='obj.server_type'/>&obj.uuid=<s:property value='obj.uuid'/>" name="vmhost" frameborder="0">Load Failed?</iframe>
				</div>
	  		</div>			  		
		</div>
</body>