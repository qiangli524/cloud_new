<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
<script type="text/javascript">
	$(function(){
		initPie();
		
		$("#resourceType").change(function(){
			initPie();
		});
	});
	
	function initPie(){
		var id=$("#idd").val();
		var storemeasuretype = $("#resourceType").val();
		if (storemeasuretype.length == 0) {
			storemeasuretype = 'san';
		}
		var d = new Date();
		if (storemeasuretype == 'san') {
			document.getElementById("tr3").style.display="";
			document.getElementById("tr4").style.display="";
			document.getElementById("tr5").style.display="";
			document.getElementById("tr2").style.display="";
		
			$.ajax({
				type:'get',
				dataType:'text',
				url:'resourcestatistics_ah_showStoreCenterPie.do?storemeasuretype='+storemeasuretype+'&d='+d+'&type=1'+'&id='+id,
				success:function(msg){
					var chart1 = new FusionCharts("FusionCharts/Pie3D.swf","ChartId1","600","250","0","0");
					chart1.setJSONData(msg);
					chart1.render("chartarea1");
				}
			});
			$.ajax({
				type:'get',
				dataType:'text',
				url:'resourcestatistics_ah_showStoreCenterPie.do?storemeasuretype='+storemeasuretype+'&d='+d+'&type=2'+'&id='+id,
				success:function(msg){
					var chart1 = new FusionCharts("FusionCharts/Pie3D.swf","ChartId2","600","250","0","0");
					chart1.setJSONData(msg);
					chart1.render("chartarea2");
				}
			});
			$.ajax({
				type:'get',
				dataType:'text',
				url:'resourcestatistics_ah_showStoreCenterPie.do?storemeasuretype='+storemeasuretype+'&d='+d+'&type=3'+'&id='+id,
				success:function(msg){
					var chart1 = new FusionCharts("FusionCharts/Pie3D.swf","ChartId3","600","250","0","0");
					chart1.setJSONData(msg);
					chart1.render("chartarea3");
				}
			});
		} else if(storemeasuretype=='nas') {
			document.getElementById("tr3").style.display="";
			document.getElementById("tr4").style.display="";
			document.getElementById("tr5").style.display="";
			document.getElementById("tr2").style.display="";
		
			$.ajax({
				type:'get',
				dataType:'text',
				url:'resourcestatistics_ah_showStoreCenterPie.do?storemeasuretype='+storemeasuretype+'&d='+d+'&type=1'+'&id='+id,
				success:function(msg){
					var chart1 = new FusionCharts("FusionCharts/Pie3D.swf","ChartId1","600","250","0","0");
					chart1.setJSONData(msg);
					chart1.render("chartarea1");
				}
			});
			$.ajax({
				type:'get',
				dataType:'text',
				url:'resourcestatistics_ah_showStoreCenterPie.do?storemeasuretype='+storemeasuretype+'&d='+d+'&type=2'+'&id='+id,
				success:function(msg){
					var chart1 = new FusionCharts("FusionCharts/Pie3D.swf","ChartId2","600","250","0","0");
					chart1.setJSONData(msg);
					chart1.render("chartarea2");
				}
			});
			$.ajax({
				type:'get',
				dataType:'text',
				url:'resourcestatistics_ah_showStoreCenterPie.do?storemeasuretype='+storemeasuretype+'&d='+d+'&type=3'+'&id='+id,
				success:function(msg){
					var chart1 = new FusionCharts("FusionCharts/Pie3D.swf","ChartId3","600","250","0","0");
					chart1.setJSONData(msg);
					chart1.render("chartarea3");
				}
			});
		}else{
			document.getElementById("tr3").style.display="none";
			document.getElementById("tr4").style.display="none";
			document.getElementById("tr5").style.display="none";
			document.getElementById("tr2").style.display="none";
			$.ajax({
				type:'get',
				dataType:'text',
				url:'resourcestatistics_ah_showStoreCenterPie.do?storemeasuretype='+storemeasuretype+'&d='+d+'&id='+id,
				success:function(msg){
					var chart1 = new FusionCharts("FusionCharts/Pie3D.swf","ChartId4","600","250","0","0");
					chart1.setJSONData(msg);
					chart1.render("chartarea1");
				}
			});
		}
		
	}
</script>
</head>

<body class="pop-body scrollbody" >
	<s:form action="" method="post" id="theForm" cssStyle="theForm"> 
	<s:hidden name="id" id="idd"></s:hidden>
		<div align="left">
			<table>
				<tr>
					<td align="left">
						<s:select list="#{'san':'SAN存储','nas':'NAS存储','hadoop':'分布式存储' }" id="resourceType" style="width:200px"></s:select>	
					</td>
				</tr>
				<tr>
					<td align="center">
						<div id="chartarea1">
							FusionCharts.
						</div>
					</td>
				</tr>
				<tr id="tr4">
					<td>
						<hr/>
					</td>
				</tr>
				<tr id = "tr2">
					<td align="center">
						<div id="chartarea2">
							FusionCharts.
						</div>
					</td>
				</tr>
				<tr id="tr5">
					<td>
						<hr/>
					</td>
				</tr>
				<tr id = "tr3">
					<td align="center">
						<div id="chartarea3">
							FusionCharts.
						</div>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>