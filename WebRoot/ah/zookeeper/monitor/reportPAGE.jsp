<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<%@ taglib prefix="fn"         uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c"          uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<body style="overflow-y:auto;">




<div class="pd-20 bgcolor-1">
			<input id="server" name="server" type="hidden" value="${ result.server }" />
			<input id="clusterId" name="clusterId" type="hidden" value="${ result.clusterId }" />
			
			
        	<h2 class="utt-1">ZooKeepr集群状态趋势图</h2>  
            <div class="bord-1 pd-20" >
	            <label class="vm">节点IP：</label>
	            <label class="vm">${result.server}</label>
	            <label class=" mgl-20 vm">日期：</label>
	            <input id="statDate" name="statDate" type="text" value="${ result.statDate }" class="Wdate" onfocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd'})"/>
	            <span class="ubtn-1 mgl-20"><input type="button" id="search" value="查询"/></span>
				<div style="margin: 10px;"></div>
				<div id="chartarea" align="center"></div>
            </div>
</div>



<script type="text/javascript">
	$(document).ready(function(){
		
		var clusterId = $("#clusterId").val();
		var server = $("#server").val();
		var statDate = $("#statDate").val();
		
		$("#chartarea").insertFusionCharts({
            swfUrl: "FusionCharts/ZoomLine.swf", 
            dataSource: "taoKeeperMonitor_reportPageData.do?clusterId="+clusterId+"&server="+server+"&statDate="+statDate,
            dataFormat: "jsonurl", 
            width: "730", 
            height: "350", 
            id: "dayChart"
      	}); 
		
		
		$("#search").click(function(){
			
			var clusterId = $("#clusterId").val();
			var server = $("#server").val();
			var statDate = $("#statDate").val();
			$("#chartarea").updateFusionCharts({
	               dataSource: "taoKeeperMonitor_reportPageData.do?clusterId="+clusterId+"&server="+server+"&statDate="+statDate
            }); 
		});
		
	});
</script>


</body>
