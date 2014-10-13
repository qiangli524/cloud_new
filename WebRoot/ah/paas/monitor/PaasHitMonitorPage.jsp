<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/cloud3/charts/jquery/jquery-1.8.2-min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
</head>
<body class="pop-body scrollbody" >
    	<s:hidden name="id" id="id"></s:hidden>
    	<s:hidden name="entity_id" id="entity_id"></s:hidden>
		<s:hidden name="node_type" id="node_type"></s:hidden>
	<table width="98%" height="690px" border="1px">
		<tr>
			<td width="45%" height="30px" bgcolor="#B0E2FF" style="text-align: center">
				<font size="4px" color="#FFFFFF">GET命令命中情况</font>
			</td>
			<td width="2%"></td>
			<td width="45%" height="30px" bgcolor="#B0E2FF" style="text-align: center">
				<font size="4px" color="#FFFFFF">DELETE命令命中情况</font>
			</td>
		</tr>
		<tr height="200px" valign="top">
			<td width="32%">
						<div style="text-align: right;width: 40%;float: left;">
							<br/><br/>
							<span>GET请求总数：<fmt:formatNumber value="${(map.get_hits+map.get_misses)}" pattern="#,###" type="number" minIntegerDigits="0"/></span><br/><br/>
							<span>GET命中次数：<s:property value="map.get_hits" default="0" /></span><br/><br/>
							<span>GET未命中次数：<s:property value="map.get_misses" default="0" /></span><br/><br/>
							<span>GET命中率(%)：<fmt:formatNumber value="${(map.get_misses/(map.get_hits+map.get_misses))*100}" pattern="#,###" type="number" minIntegerDigits="0"/></span>
						</div>
						<br /> 
						<div style="text-align: center;width: 60%;float: left;">
							<div style="text-align: left;" id="getHits"></div>
							<div style="text-align: right; float: left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="icon-blue">&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;命中</span></div>
							<div style="text-align: center;float: left" >&nbsp;&nbsp;&nbsp;<span class="icon-yellow">&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;未命中</span></div>
						</div>
			</td>
			<td width="2%"></td>
			<td width="32%">
						<div style="text-align: right;width: 40%;float: left;">
							<br/><br/>
							<span>DELETE请求总数：<fmt:formatNumber value="${(map.delete_hits+map.delete_misses)}" pattern="#,###" type="number" minIntegerDigits="0"/></span><br/><br/>
							<span>DELETE命中次数：<s:property value="map.delete_hits" default="0" /></span><br/><br/>
							<span>DELETE未命中次数：<s:property value="map.delete_misses" default="0" /></span><br/><br/>
							<span>DELETE命中率(%)：<fmt:formatNumber value="${(map.delete_hits/(map.delete_hits+map.delete_misses))*100}" pattern="#,###" type="number"/></span>
						</div>
						<br /> 
						<div style="text-align: center;width: 60%;float: left;">
							<div style="text-align: left;" id="deleteHits"></div>
							<div style="text-align: right; float: left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="icon-blue">&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;命中</span></div>
							<div style="text-align: center;float: left" >&nbsp;&nbsp;&nbsp;<span class="icon-yellow">&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;未命中</span></div>
						</div>
			</td>
		</tr>
		<tr height="32px">
			<td width="32%" bgcolor="#B0E2FF" style="text-align: center">
				<font size="4px" color="#FFFFFF">INCR命令命中情况</font>
			</td>
			<td width="2%"></td>
			<td width="32%" bgcolor="#B0E2FF" style="text-align: center">
				<font size="4px" color="#FFFFFF" >DECR命令命中情况</font>
			</td>
		</tr>
		<tr height="200px">
			<td width="32%">
						<div style="text-align: right;width: 40%;float: left;">
							<br/><br/>
							<span>INCR请求总数：<fmt:formatNumber value="${(map.incr_hits+map.incr_misses)}" pattern="#,###" type="number" minIntegerDigits="0"/></span><br/><br/>
							<span>INCR命中次数：<s:property value="map.incr_hits" default="0" /></span><br/><br/>
							<span>INCR未命中次数：<s:property value="map.incr_misses" default="0" /></span><br/><br/>
							<span>INCR命中率(%)：<fmt:formatNumber value="${(map.incr_hits/(map.incr_hits+map.cmd_incr))*100}" pattern="#,###" type="number"/></span>
						</div>
						<br /> 
						<div style="text-align: center;width: 60%;float: left;">
							<div style="text-align: left;" id="incrHits"></div>
							<div style="text-align: right; float: left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="icon-blue">&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;命中</span></div>
							<div style="text-align: center;float: left" >&nbsp;&nbsp;&nbsp;<span class="icon-yellow">&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;未命中</span></div>
						</div>
			</td>
			<td width="2%"></td>
			<td width="32%">
						<div style="text-align: right;width: 40%;float: left;">
							<br/><br/>
							<span>DECR请求总数：<fmt:formatNumber value="${(map.decr_hits+map.decr_misses)}" pattern="#,###" type="number" minIntegerDigits="0"/></span><br/><br/>
							<span>DECR命中次数：<s:property value="map.decr_hits" default="0" /></span><br/><br/>
							<span>DECR未命中次数：<s:property value="map.decr_misses" default="0" /></span><br/><br/>
							<span>DECR命中率(%)：<fmt:formatNumber value="${(map.decr_hits/(map.decr_hits+map.decr_misses))*100}" pattern="#,###" type="number"/></span>
						</div>
						<br /> 
						<div style="text-align: center;width: 60%;float: left;">
							<div style="text-align: left;" id="decrHits"></div>
							<div style="text-align: right; float: left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="icon-blue">&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;命中</span></div>
							<div style="text-align: center;float: left" >&nbsp;&nbsp;&nbsp;<span class="icon-yellow">&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;未命中</span></div>
						</div>
			</td>
		</tr>
		<tr height="32px">
			<td width="32%" bgcolor="#B0E2FF" style="text-align: center">
				<font size="4px" color="#FFFFFF">CAS命令命中情况</font>
			</td>
			<td width="2%"></td>
			<td width="32%" bgcolor="#B0E2FF" style="text-align: center">
				<font size="4px" color="#FFFFFF">TOUCH命令命中情况</font>
			</td>
		</tr>
		<tr height="200px">
			<td width="32%">
						<div style="text-align: right;width: 40%;float: left;">
							<br/><br/>
							<span>CAS请求总数：<fmt:formatNumber value="${(map.cas_hits+map.cas_misses)}" pattern="#,###" type="number" minIntegerDigits="0"/><s:property value="map.cmd_cas" default="0" /></span><br/><br/>
							<span>CAS命中次数：<s:property value="map.cas_hits" default="0" /></span><br/><br/>
							<span>CAS未命中次数：<s:property value="map.cas_misses" default="0" /></span><br/><br/>
							<span>CAS命中率(%)：<fmt:formatNumber value="${(map.cas_hits/(map.cas_hits+map.cas_misses))*100}" pattern="#,###" type="number"/></span>
						</div>
						<br /> 
						<div style="text-align: center;width: 60%;float: left;">
							<div style="text-align: left;" id="casHits"></div>
							<div style="text-align: right; float: left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="icon-blue">&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;命中</span></div>
							<div style="text-align: center;float: left" >&nbsp;&nbsp;&nbsp;<span class="icon-yellow">&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;未命中</span></div>
						</div>
			</td>
			<td width="2%"></td>
			<td width="32%">
						<div style="text-align: right;width: 40%;float: left;">
							<br/><br/>
							<span>TOUCH请求总数：<fmt:formatNumber value="${(map.touch_hits+map.touch_misses)}" pattern="#,###" type="number" minIntegerDigits="0"/></span><br/><br/>
							<span>TOUCH命中次数：<s:property value="map.touch_hits" default="0" /></span><br/><br/>
							<span>TOUCH未命中次数：<s:property value="map.touch_misses" default="0" /></span><br/><br/>
							<span>TOUCH命中率(%)：<fmt:formatNumber value="${(map.touch_hits/(map.touch_hits+map.cmd_touch))*100}" pattern="#,###" type="number"/></span>
						</div>
						<br /> 
						<div style="text-align: center;width: 60%;float: left;">
							<div style="text-align: left;" id="touchHits"></div>
							<div style="text-align: right; float: left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="icon-blue">&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;命中</span></div>
							<div style="text-align: center;float: left" >&nbsp;&nbsp;&nbsp;<span class="icon-yellow">&nbsp;&nbsp;&nbsp;&nbsp;</span><span>&nbsp;未命中</span></div>
						</div>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	$(function(){
		insertFusionChartData();
		
	});
	
	function insertFusionChartData(){
		var id = $("#id").val();
		var entity_id = $("#entity_id").val();
		var node_type = $("#node_type").val();
		 $("#getHits").insertFusionCharts({
             swfUrl: "FusionCharts/Pie2D.swf", 
             dataSource: "paasHitMonitor_showHits.do?entity_id="+entity_id+"&node_type="+node_type+"&flag=get"+"&time="+new Date(),
             dataFormat: "jsonurl", 
             width: "180", 
             height: "150", 
             id: "getHit"
        });
		 $("#deleteHits").insertFusionCharts({
             swfUrl: "FusionCharts/Pie2D.swf", 
             dataSource: "paasHitMonitor_showHits.do?entity_id="+entity_id+"&node_type="+node_type+"&flag=delete"+"&time="+new Date(),
             dataFormat: "jsonurl", 
             width: "180", 
             height: "150",
             id: "deleteHit"
        });
		 $("#incrHits").insertFusionCharts({
             swfUrl: "FusionCharts/Pie2D.swf", 
             dataSource: "paasHitMonitor_showHits.do?entity_id="+entity_id+"&node_type="+node_type+"&flag=incr",
             dataFormat: "jsonurl", 
            width: "180", 
             height: "150",
             id: "incrHit"
        });
		 $("#decrHits").insertFusionCharts({
             swfUrl: "FusionCharts/Pie2D.swf", 
             dataSource: "paasHitMonitor_showHits.do?entity_id="+entity_id+"&node_type="+node_type+"&flag=decr",
             dataFormat: "jsonurl", 
             width: "180", 
             height: "150", 
             id: "decrHit"
        });
		 $("#casHits").insertFusionCharts({
             swfUrl: "FusionCharts/Pie2D.swf", 
             dataSource: "paasHitMonitor_showHits.do?entity_id="+entity_id+"&node_type="+node_type+"&flag=cas",
             dataFormat: "jsonurl", 
            width: "180", 
             height: "150",
             id: "casHit"
        });
		 $("#touchHits").insertFusionCharts({
             swfUrl: "FusionCharts/Pie2D.swf", 
             dataSource: "paasHitMonitor_showHits.do?entity_id="+entity_id+"&node_type="+node_type+"&flag=touch",
             dataFormat: "jsonurl", 
             width: "180", 
             height: "150",
             id: "touchHit"
        });
	}
</script>
