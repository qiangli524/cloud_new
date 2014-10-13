<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/cloud3/charts/jquery/jquery-1.8.2-min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
<script type="text/javascript">
	$(function(){
		insertFusionChartData();
		
		$("#hostMsg").click(function(){
			$.dialog({
    			id:'hostMsg',
    			title:'主机管理',
    			width: '1200px',
				height: '800px',
				lock:true,
    			zIndex:'1975',
    			content: 'url:resource_ah_allHostList.do'
    		});
		});
		
		$("#vmMsg").click(function(){
			$.dialog({
    			id:'hostMsg',
    			title:'虚拟机管理',
    			width: '1200px',
				height: '800px',
				lock:true,
    			zIndex:'1975',
    			content: 'url:showvm_ah_listvm.do'
    		});
		});
		
	});
	
	function insertFusionChartData(){
		var d = new Date();
		$.ajax({
			type:'get',
			dataType:'text',
			url:"resourceOutline_getComputerHostResourcePie.do?aaa="+d,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/Pie2D.swf","ChartId1","150px","150px","0","0");
				chart1.setJSONData(msg);
				chart1.render("chartarea");
			}
		});
		
		$.ajax({
			type:'get',
			dataType:'text',
			url:"resourceOutline_getComputerVmResourcePie.do?aaa="+d,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/Pie2D.swf","ChartId2","150px","150px","0","0");
				chart1.setJSONData(msg);
				chart1.render("chartarea1");
			}
		});
		
		$.ajax({
			type:'get',
			dataType:'json',
			url:"resourceOutline_getComputerVmResourcePieCount.do?aaa="+d,
			success:function(data){
				$("#vmCount span").text(data.vmCount);
				$("#vmUserCount span").text(data.vmUserCount+"  "+data.vmUserCountPre);
				$("#vmNoUserCount span").text(data.vmNoUserCount+"  "+data.vmNoUserCountPre);
			}
		});
		
		$.ajax({
			type:'get',
			dataType:'json',
			url:"resourceOutline_getComputerHostResourcePieCount.do?aaa="+d,
			success:function(data){
				$("#hostCount span").text(data.hostCount);
				$("#hostUserCount span").text(data.hostUserCount+"  "+data.hostUserCountPre);
				$("#hostNoUserCount span").text(data.hostNoUserCount+"  "+data.hostNoUserCountPre);
			}
		});
	}
</script>
</head>
<body class="pop-body scrollbody" >
	<table width="100%">
	    <tr><td height="30px"></td></tr>
	    <tr>
			<td class="tc" id="hostCount"><div class="mt10">物理机总数：<span></span></div>
			</td>
			<td class="tc" id="vmCount"><div class="mt10">虚拟机总数：<span></span></div>
			</td>
		</tr>
		<tr>
			<td class="tc"><div id="chartarea"></div>
			</td>
			<td class="tc"><div id="chartarea1"></div>
			</td>
		</tr>
		<tr>
			<td class="" id="hostUserCount"><div class="icon-blue fl"></div><div class="fl">使用:<span></span>%</div>
			</td>
			<td class="" id="vmUserCount"><div class="icon-blue fl"></div><div class="fl">使用：<span></span>%</div>
			</td>
		</tr>
		<tr>
			<td class="pt5" id="hostNoUserCount">
				<div class="icon-yellow fl"></div>
				<div  class="fl">未使用:<span></span>%</div>
				<div  class="fr pr10 font-blue"><a style="color:blue" href="javascript:;" id="hostMsg">更多物理机信息</a></div>							
			</td>
			<td class="pt5" id="vmNoUserCount">
				<div class="icon-yellow fl"></div>
				<div class="fl">未使用：<span></span>%</div>
				<div class="fr pr10"><a style="color:blue"  href="javascript:;" id="vmMsg">更多虚拟机信息</a></div>
			</td>
		</tr>
	</table>
</body>